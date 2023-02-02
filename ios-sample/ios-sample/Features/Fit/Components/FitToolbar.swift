//
//  FitToolbar.swift
//  ios-sample
//
//  Created by Koji Osugi on 31/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

internal struct FitToolbar: View {
    
    var text: String
    var onAction: () -> Void
    
    init(_ text: String, onAction: @escaping () -> Void = {}) {
        self.text = text
        self.onAction = onAction
    }
    
    var body: some View {
        Toolbar()
    }
    
    @ViewBuilder
    private func Toolbar() -> some View {
        ZStack {
            HStack(spacing: 0) {
                Spacer()
                Button(action: { onAction() }) {
                    Image(uiImage: .close)
                        .renderingMode(.template)
                        .foregroundColor(.white)
                }
                .padding(.horizontal, 24)
            }
            Title()
        }
    }
    
    @ViewBuilder
    private func Title() -> some View {
        Text(text)
            .font(.system(size: 16))
            .tracking(2.56)
            .foregroundColor(.primaryExtraLight)
            .fontWeight(.semibold)
            .textCase(.uppercase)
    }
}

struct FitToolbar_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            FitToolbar("Fitness coach")
        }.fullScreen(background: .grayDark)
    }
}
