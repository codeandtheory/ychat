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
    
    @State
    private var link: Link?
    
    @State
    private var textFieldHeight: CGFloat = 0.0
    
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
        .fullScreenCover(item: $link) { SafariView(url: $0.url) }
        .environment(\.openURL, OpenURLAction(handler: handleURL))
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
                .animation(Animation.easeIn, value: viewModel.chatMessageList)
            }
            .background(Color(hex: 0xF8F8F8))
            .onChange(of: viewModel.chatMessageList) {
                value.scrollTo($0.last?.id, anchor: .bottom)
            }
        }
    }
    
    @ViewBuilder
    private func buildChatBubble(messageType: MessageState) -> some View {
        switch messageType.type {
        case .botMessage(let text):
            BallonBotMessageFactory(message: text)
        case .senderMessage(let text, let hasError):
            BallonSenderMessage(text, isError: hasError)
                .padding(.horizontal, 24)
        case .typing:
            BallonTyping()
                .padding(.horizontal, 24)
        }
    }
    
    @ViewBuilder
    private func sendMessageSection() -> some View {
        ZStack {
            Color.white
                .cornerRadius(24, corners: [.topLeft, .topRight])
                .shadow(radius: 1)
            GeometryReader { proxy in
                HStack(spacing: 8) {
                    TextField(text: $viewModel.message, axis: .vertical) {}
                        .disabled(viewModel.isLoading)
                        .opacity(viewModel.isLoading ? 0.4 : 1)
                        .lineLimit(5)
                    sendButton()
                }
                .padding(8)
                .overlay(
                    RoundedRectangle(cornerRadius: 16)
                        .stroke(Color(hex: 0xB7B7B7), lineWidth: 1)
                )
                .padding(.horizontal, 16)
                .onChange(of: proxy.size.height) { self.textFieldHeight = $0 }
            }
            .padding(.top, 8)
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
                .overlay { Image("ic_arrow_up") }
        }
        .disabled(viewModel.message.isEmpty)
    }
    
    @ViewBuilder
    private func downloadQRCodeButton() -> some View {
        Button(action: {
            guard let inputImage = UIImage(named: "fake_qr_code") else { return }
            let imageSaver = ImageSaver()
            imageSaver.writeToPhotoAlbum(image: inputImage)
        }) {
            Text("Save QR Code")
                .foregroundColor(Color(hex: 0x5EAEFF))
                .font(.system(size: 16))
                .fontWeight(.bold)
        }
    }
    
    private func handleURL(_ url: URL) -> OpenURLAction.Result {
        self.link = Link(url: url)
        return .handled
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
