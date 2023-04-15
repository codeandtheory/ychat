//
//  ButtonContained.swift
//  ychat-ios
//
//  Created by Koji Osugi on 07/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ButtonContained: View {
    private var text: String
    private var onAction: () -> Void
    private var isEnabled: Bool
    private var backgroundColor: Color {
        if isEnabled { return Color.accent } else { return Color.primary5 }
    }
    private var foregroundColor: Color {
        if isEnabled { return Color.onAccent } else { return Color.primary4 }
    }
    
    init(
        _ text: String,
        isEnabled: Bool = true,
        onAction: @escaping () -> Void = {}
    ) {
        self.text = text
        self.isEnabled = isEnabled
        self.onAction = onAction
    }
    
    var body: some View {
        Button(action: { onAction() }) {
            Text(text.capitalized)
                .foregroundColor(foregroundColor)
                .font(.system(size: 16))
                .foregroundColor(.text1)
                .fontWeight(.semibold)
                .frame(minWidth: 0, maxWidth: .infinity)
                .padding()
        }
        .background(backgroundColor)
        .cornerRadius(28)
        .disabled(!isEnabled)
    }
}

internal struct ButtonContainedLight_Previews: PreviewProvider {
    static var previews: some View {
        ButtonContained("Button Contained")
            .padding(.horizontal, 16)
    }
}

internal struct ButtonContainedDark_Previews: PreviewProvider {
    static var previews: some View {
        ButtonContained("Button Contained")
            .padding(.horizontal, 16)
            .preferredColorScheme(.dark)
    }
}
