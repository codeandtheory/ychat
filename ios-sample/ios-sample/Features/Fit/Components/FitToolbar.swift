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
        toolbar()
    }
    
    @ViewBuilder
    private func toolbar() -> some View {
        ZStack {
            HStack(spacing: 0) {
                Spacer()
                Button(action: { onAction() }) {
                    Image(uiImage: Icon.close.uiImage)
                        .renderingMode(.template)
                        .foregroundColor(.white)
                }
                .padding(.horizontal, 24)
            }
            title()
        }
    }
    
    @ViewBuilder
    private func title() -> some View {
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
