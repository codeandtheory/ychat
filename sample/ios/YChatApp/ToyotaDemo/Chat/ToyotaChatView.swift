//
//  ToyotaChatView.swift
//  Y-Chat
//
//  Created by Koji Osugi on 14/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ToyotaChatView: View {
    @ObservedObject
    private var viewModel: ToyotaChatViewModel
    
    init(viewModel: ToyotaChatViewModel = .init()) {
        self.viewModel = viewModel
    }
    
    var body: some View {
        VStack {
            contentState()
            sendMessageSection()
        }
        .background(Color(hex: 0xF8F8F8))
        .fullScreen()
        .edgesIgnoringSafeArea(.bottom)
    }
    
    @ViewBuilder
    private func contentState() -> some View {
        ScrollViewReader { value in
            ScrollView {
                ForEach(viewModel.chatMessageList, id: \.id) {
                    buildChatBubble(messageType: $0)
                        .padding(.top, 16)
                        .id($0.id)
                }
                .padding(.horizontal, 24)
            }
            .background(Color(hex: 0xF8F8F8))
            .onChange(of: viewModel.chatMessageList) {
                value.scrollTo($0.last?.id, anchor: .bottom)
            }
        }
    }
    
    @ViewBuilder
    private func buildChatBubble(messageType: Message) -> some View {
        switch messageType.type {
        case .botMessage(let text):
            BallonBotMessage(text)
        case .senderMessage(let text):
            BallonSenderMessage(text, isError: false)
        case .typing:
            BallonTyping()
        }
    }
    
    @ViewBuilder
    private func sendMessageSection() -> some View {
        ZStack {
            Color.white
                .cornerRadius(24, corners: [.topLeft, .topRight])
                .shadow(radius: 8)
            HStack(spacing: 8) {
                TextField(text: $viewModel.message) {}
                    .disabled(viewModel.isLoading)
                    .opacity(viewModel.isLoading ? 0.4 : 1)
                sendButton()
            }
            .padding(8)
            .overlay(
                RoundedRectangle(cornerRadius: 16)
                    .stroke(Color(hex: 0xB7B7B7), lineWidth: 1)
            )
            .padding(.horizontal, 16)
            .padding(.bottom, 32)
        }
        .frame(maxHeight: 124)
    }
    
    @ViewBuilder
    private func sendButton() -> some View {
        Button { viewModel.sendMessage() } label: {
            Rectangle()
                .fill(viewModel.enableButton ? Color.accent : Color(hex: 0xEBEBEB))
                .frame(width: 44, height: 44)
                .cornerRadius(8)
                .overlay { Icon.send.image(.onAccent) }
        }
        .disabled(viewModel.message.isEmpty)
    }
}

struct ToyotaChatView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationStack {
            ToyotaChatView()
                .applyToyotaToolbar()
        }
    }
}
