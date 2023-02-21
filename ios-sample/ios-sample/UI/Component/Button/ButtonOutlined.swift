//
//  ButtonOutlined.swift
//  ios-sample
//
//  Created by Koji Osugi on 21/02/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ButtonOutlined: View {
    
    private var text: String
    private var onTap: () -> Void
    
    init(_ text: String, onTap: @escaping () -> Void = {}) {
        self.text = text
        self.onTap = onTap
    }
    
    var body: some View {
        Button(action: {
            onTap()
        }) {
            Text(text)
                .foregroundColor(.accentMain)
                .style(.button)
                .frame(minWidth: 0, maxWidth: .infinity)
                .padding()
                .overlay(
                    RoundedRectangle(cornerRadius: 8)
                        .stroke(Color.accentMain, lineWidth: 2)
            )
        }
        .cornerRadius(8)
    }
}

struct ButtonOutlined_Previews: PreviewProvider {
    static var previews: some View {
        ButtonOutlined("Outlined button")
            .padding()
    }
}
