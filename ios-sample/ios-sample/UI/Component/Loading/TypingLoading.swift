//
//  TypingLoading.swift
//  ios-sample
//
//  Created by Koji Osugi on 31/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct TypingLoading: View {
    
    var background = Color.grayLight
    
    var foreground = Color.grayMedium
    
    @State
    private var typingState = "Typing."
    
    @State
    private var timer: Timer?
    
    var body: some View {
        ZStack() {
            Text(typingState)
                .foregroundColor(foreground)
                .style(.body)
        }
        .padding(.horizontal, 16)
        .padding(.vertical, 8)
        .background(background)
        .cornerRadius(16, corners: [.bottomLeft, .bottomLeft, .topRight])
        .onAppear {
            timer = Timer.scheduledTimer(withTimeInterval: 0.25, repeats: true) { _ in
                switch typingState {
                case "Typing...": typingState = "Typing."
                case "Typing.": typingState = "Typing.."
                default: typingState = "Typing..."
                }
            }
        }
        .onDisappear {
            timer?.invalidate()
        }
    }
}

struct TypingLoading_Previews: PreviewProvider {
    static var previews: some View {
        TypingLoading()
    }
}
