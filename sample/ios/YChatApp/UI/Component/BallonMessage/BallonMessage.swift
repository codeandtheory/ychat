//
//  BallonMessage.swift
//  ychat-ios
//
//  Created by Koji Osugi on 08/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct BallonSenderMessage: View {
    private var text: String
    
    private var isError = false
    
    init(_ text: String, isError: Bool = false) {
        self.text = text
        self.isError = isError
    }
    
    var body: some View {
        HStack(spacing: 4) {
            Spacer()
            Spacer().frame(width: 60)
            HStack(spacing: 8) {
                Text(text)
                    .foregroundColor(.onAccent)
                    .style(.mediumBody)
                if isError {
                    Icon.warning.image(.red)
                }
            }
            .padding(.horizontal, 16)
            .padding(.vertical, 8)
            .background(Color.accent)
            .cornerRadius(16, corners: [.bottomLeft, .topLeft, .topRight])
        }
    }
}

struct BallonBotMessage: View {
    private var text: String
    
    init(_ text: String) {
        self.text = text
    }
    
    var body: some View {
        HStack {
            HStack(alignment: .top, spacing: 4) {
                Circle()
                    .fill(.green)
                    .frame(width: 40, height: 40)
                    .overlay { Icon.bot.image(.white) }
                ZStack {
                    TypeWriterText(text)
                }
                .padding(.horizontal, 16)
                .padding(.vertical, 8)
                .background(Color.text4)
                .cornerRadius(16, corners: [.bottomLeft, .bottomRight, .topRight])
            }
            Spacer().frame(width: 60)
            Spacer()
        }
    }
}

struct BallonTyping: View {
    var body: some View {
        HStack {
            Circle()
                .fill(.green)
                .frame(width: 40, height: 40)
                .overlay { Icon.bot.image(.white) }
            ZStack {
                TypingLoading()
            }
            .padding(.horizontal, 16)
            .padding(.vertical, 8)
            .background(Color.text4)
            .cornerRadius(16, corners: [.bottomLeft, .bottomRight, .topRight])
            Spacer()
        }
    }
}

internal struct BallonMessage_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            BallonSenderMessage("Say this is a test")
            BallonSenderMessage("Say this is a test", isError: true)
            BallonBotMessage("This is indeed a test.")
            BallonTyping()
        }
    }
}
