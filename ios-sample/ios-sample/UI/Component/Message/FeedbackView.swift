//
//  FeedbackView.swift
//  ios-sample
//
//  Created by Koji Osugi on 21/02/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct FeedbackView: View {
    var icon: UIImage
    var title: String
    var message: String
    var buttonText: String
    var textColor: Color = .black
    var onButtonTap: () -> Void = {}
    
    var body: some View {
        VStack(spacing: 0) {
            Image(uiImage: icon)
                .renderingMode(.template)
                .resizable()
                .frame(width: 112, height: 112)
                .foregroundColor(.primaryMain)
            Text(title)
                .foregroundColor(textColor)
                .style(.titleBold)
                .multilineTextAlignment(.center)
                .padding(.top, 16)
            Text(message)
                .foregroundColor(textColor)
                .style(.body)
                .multilineTextAlignment(.center)
                .padding(.top, 8)
            ButtonOutlined(buttonText, onTap: onButtonTap)
                .padding(.top, 16)
        }.padding(.horizontal, 24)
    }
}

extension FeedbackView {
    static func buildErrorState(
        textColor: Color = .black,
        onButtonTap: @escaping () -> Void = {}
    ) -> some View {
        FeedbackView(
            icon: Icon.warningOutline.uiImage,
            title: "Something went wrong",
            message: "There was a problem with the request. Please try again in a few moments.",
            buttonText: "Try again",
            textColor: textColor,
            onButtonTap: onButtonTap
        )
    }
}

struct FeedbackView_Previews: PreviewProvider {
    static var previews: some View {
        FeedbackView.buildErrorState()
    }
}
