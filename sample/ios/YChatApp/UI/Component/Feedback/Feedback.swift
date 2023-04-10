//
//  Feedback.swift
//  ychat-ios
//
//  Created by Koji Osugi on 07/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct Feedback: View {
    private var icon: Icon
    private var title: String
    private var message: String
    private var buttonText: String?
    private var onAction: () -> Void
    
    init(
        icon: Icon,
        title: String,
        message: String,
        buttonText: String? = nil,
        onAction: @escaping () -> Void = {}
    ) {
        self.icon = icon
        self.title = title
        self.message = message
        self.buttonText = buttonText
        self.onAction = onAction
    }
    
    init(state: State, onAction: @escaping () -> Void = {}) {
        self.icon = state.icon
        self.title = state.title
        self.message = state.message
        self.buttonText = state.buttonText
        self.onAction = onAction
    }
    
    var body: some View {
        VStack(spacing: 0) {
            icon.image(size: 128)
            Text(title)
                .style(.largeTitle)
                .multilineTextAlignment(.center)
                .padding(.top, 16)
                .padding(.bottom, 8)
            Text(message)
                .style(.smallBody)
                .multilineTextAlignment(.center)
            if let buttonText = buttonText {
                ButtonOutlined(buttonText, onAction: { onAction() })
                    .padding(.top, 16)
            }
        }
        .padding(.horizontal, 56)
    }
}

extension Feedback {
    enum State {
        case error, construction
        
        var icon: Icon {
            switch self {
            case .error: return .warningOutline
            case .construction: return .construction
            }
        }
        
        var title: String {
            switch self {
            case .error: return "Something went wrong"
            case .construction: return "Under construction"
            }
        }
        
        var message: String {
            switch self {
            case .error: return "There was a problem with the request. Please try again in a few moments."
            case .construction: return "We are working on it, and this feature will be available soon."
            }
        }
        
        var buttonText: String? {
            switch self {
            case .error: return "Try again"
            default: return nil
            }
        }
    }
}

struct FeedbackLight_Previews: PreviewProvider {
    static var previews: some View {
        Feedback(state: .error)
    }
}

struct FeedbackDark_Previews: PreviewProvider {
    static var previews: some View {
        Feedback(state: .error)
            .preferredColorScheme(.dark)
    }
}
