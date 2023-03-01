//
//  FitChatView.swift
//  ios-sample
//
//  Created by Koji Osugi on 31/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

internal struct FitChatView: View {
    @ObservedObject
    private var viewModel: FitChatViewModel
    
    @Environment(\.dismiss)
    private var dismiss
    
    private var enableButton: Bool {
        !viewModel.isLoading && !viewModel.message.isEmpty
    }
    
    init(viewModel: FitChatViewModel = .init()) {
        self.viewModel = viewModel
        viewModel.initChat()
    }
    
    var body: some View {
        VStack {
            FitToolbar("Fitness coach", onAction: { dismiss() })
            switch viewModel.state {
            case .loading:
                Spacer()
                ProgressView().tint(.white)
                Spacer()
            case .error:
                Spacer()
                FeedbackView.buildErrorState(
                    textColor: .white,
                    onButtonTap: { viewModel.initChat() }
                )
                EmptyView()
                Spacer()
            case .success:
                successState()
            }
        }.fullScreen(background: .grayDark)
    }
    
    @ViewBuilder
    private func successState() -> some View {
        VStack {
            if viewModel.chatMessageList.isEmpty {
                emptyMessage()
                    .padding(.top, 16)
            } else {
                ScrollViewReader { value in
                    ScrollView {
                        ForEach(viewModel.chatMessageList) {
                            chatBubble(chatMessage: $0)
                                .padding(.top, 16)
                                .id($0.id)
                        }
                        .padding(.horizontal, 8)
                    }
                    .onChange(of: viewModel.chatMessageList) {
                        value.scrollTo($0.last?.id, anchor: .bottom)
                    }
                }
            }
            Spacer()
            sendMessageSection()
                .padding(.horizontal, 12)
                .padding(.vertical, 12)
        }
    }
    
    @ViewBuilder
    private func emptyMessage() -> some View {
        ZStack {
            Text("Workout routines, diet plans, fitness advice - you ask it, fitchat GPT will answer it.")
                .foregroundColor(.grayMedium)
                .style(.caption)
                .multilineTextAlignment(.center)
                .bold()
        }
        .padding(.horizontal, 24)
        .padding(.vertical, 12)
        .background(Color.black)
        .cornerRadius(16)
        .padding(.horizontal, 32)
    }
    
    @ViewBuilder
    private func chatBubble(chatMessage: ChatMessage) -> some View {
        switch chatMessage.type {
        case .human(let error):
            HStack(spacing: 4) {
                Spacer()
                Spacer().frame(width: 60)
                humanChatBubble(message: chatMessage.message)
                if error {
                    Image(uiImage: Icon.warningOutline.uiImage)
                        .renderingMode(.template)
                        .foregroundColor(.red)
                }
            }
        case .bot:
            HStack {
                AIChatBubble(message: chatMessage.message)
                Spacer().frame(width: 60)
                Spacer()
            }
        case .loading:
            HStack {
                TypingLoading(
                    background: .grayMain,
                    foreground: .grayMedium
                )
                Spacer()
            }
        }
    }
    
    @ViewBuilder
    private func humanChatBubble(message: String) -> some View {
        ZStack {
            Text(message)
                .foregroundColor(.white)
                .style(.body)
        }
        .padding(.horizontal, 16)
        .padding(.vertical, 8)
        .background(Color.accentMain)
        .cornerRadius(16, corners: [.bottomLeft, .topLeft, .topRight])
    }
    
    @ViewBuilder
    private func AIChatBubble(message: String) -> some View {
        HStack(alignment: .top, spacing: 4) {
            Circle()
                .fill(.green)
                .frame(width: 40, height: 40)
                .overlay {
                    Image(uiImage: Icon.bot.uiImage)
                        .renderingMode(.template)
                        .foregroundColor(.white)
                }
            ZStack {
                Text(message)
                    .foregroundColor(.white)
                    .style(.body)
                    .multilineTextAlignment(.leading)
            }
            .padding(.horizontal, 16)
            .padding(.vertical, 8)
            .background(Color.grayMain)
            .cornerRadius(16, corners: [.bottomLeft, .bottomLeft, .topRight])
        }
    }
    
    @ViewBuilder
    private func sendMessageSection() -> some View {
        HStack(spacing: 8) {
            TextField(text: $viewModel.message, axis: .vertical) {
                Text("Message")
                    .foregroundColor(.grayMedium)
                    .style(.subtitle)
            }
            .textFieldStyle(DefaultTextFieldStyle(
                background: .grayMain,
                foregroundColor: .white
            ))
            .lineLimit(5)
            .disabled(viewModel.isLoading)
            .opacity(viewModel.isLoading ? 0.4 : 1)
            sendButton()
        }
    }
    
    @ViewBuilder
    private func sendButton() -> some View {
        Button(action: { viewModel.sendMessage() }) {
            Circle()
                .fill(enableButton ? Color.accentMain : .grayMain)
                .frame(width: 40, height: 40)
                .overlay {
                    Image(uiImage: Icon.send.uiImage)
                        .renderingMode(.template)
                        .foregroundColor(.white)
                }
        }
        .disabled(viewModel.message.isEmpty)
    }
}

struct FitChatView_Previews: PreviewProvider {
    static var previews: some View {
        FitChatView()
    }
}
