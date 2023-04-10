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
                .foregroundColor(.onAccent)
                .style(.smallTitle)
                .frame(minWidth: 0, maxWidth: .infinity)
                .padding()
        }
        .background(Color.accent)
        .cornerRadius(8)
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
