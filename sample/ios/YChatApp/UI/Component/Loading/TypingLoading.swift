//
//  TypingLoading.swift
//  ychat-ios
//
//  Created by Koji Osugi on 07/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct TypingLoading: View {
    @State
    private var typingState = "Typing."
    
    @State
    private var timer: Timer?
    
    var body: some View {
        Text(typingState)
            .foregroundColor(.text3)
            .style(.mediumBody)
            .onAppear {
                timer = Timer.scheduledTimer(withTimeInterval: 0.25, repeats: true) { _ in
                    switch typingState {
                    case "Typing...": typingState = "Typing."
                    case "Typing.": typingState = "Typing.."
                    default: typingState = "Typing..."
                    }
                }
            }
            .onDisappear { timer?.invalidate() }
    }
}

struct TypingLoading_Previews: PreviewProvider {
    static var previews: some View {
        TypingLoading()
    }
}
