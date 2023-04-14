//
//  ButtonOutlined.swift
//  ychat-ios
//
//  Created by Koji Osugi on 07/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ButtonOutlined: View {
    private var text: String
    private var onAction: () -> Void
    
    init(
        _ text: String,
        onAction: @escaping () -> Void = {}
    ) {
        self.text = text
        self.onAction = onAction
    }
    
    var body: some View {
        Button(action: { onAction() }) {
            Text(text.capitalized)
                .foregroundColor(.accent)
                .style(.smallTitle)
                .frame(minWidth: 0, maxWidth: .infinity)
                .padding()
                .overlay(
                    RoundedRectangle(cornerRadius: 8)
                        .stroke(Color.accent, lineWidth: 1)
                )
        }
    }
}

internal struct ButtonOutlinedLight_Previews: PreviewProvider {
    static var previews: some View {
        ButtonOutlined("Button Outlined")
            .padding(.horizontal, 16)
    }
}

internal struct ButtonOutlinedDark_Previews: PreviewProvider {
    static var previews: some View {
        ButtonOutlined("Button Outlined")
            .padding(.horizontal, 16)
            .preferredColorScheme(.dark)
    }
}
