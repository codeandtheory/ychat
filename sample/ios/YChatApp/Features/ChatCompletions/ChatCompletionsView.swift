import SwiftUI

struct ChatCompletionsView: View {
    @ObservedObject
    private var viewModel: ChatCompletionsViewModel
    
    private var enableButton: Bool {
        !viewModel.isLoading && !viewModel.message.isEmpty
    }
    
    init(viewModel: ChatCompletionsViewModel = .init()) {
        self.viewModel = viewModel
    }
    
    var body: some View {
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
        }.fullScreen()
    }
}

private extension ChatCompletionsView {
    @ViewBuilder
    private func emptyMessage() -> some View {
        ZStack {
            Text("Enter any message and Y-Chat will answer it")
                .foregroundColor(.text1)
                .style(.extraSmallTitle)
        }
        .padding(.horizontal, 24)
        .padding(.vertical, 8)
        .background(Color.accentLight)
        .cornerRadius(16)
    }
    
    @ViewBuilder
    private func chatBubble(chatMessage: ChatMessage) -> some View {
        switch chatMessage.type {
        case .human(let error):
            BallonSenderMessage(chatMessage.message, isError: error)
        case .bot:
            BallonBotMessage(chatMessage.message)
        case .loading:
            BallonTyping()
        }
    }
    
    @ViewBuilder
    private func sendMessageSection() -> some View {
        HStack(spacing: 8) {
            TextField(text: $viewModel.message) {
                Text("Message")
                    .foregroundColor(.text3)
                    .style(.mediumBody)
            }
            .textFieldStyle(DefaultTextFieldStyle())
            .disabled(viewModel.isLoading)
            .opacity(viewModel.isLoading ? 0.4 : 1)
            sendButton()
        }
    }
    
    @ViewBuilder
    private func sendButton() -> some View {
        Button { viewModel.sendMessage() } label: {
            Circle()
                .fill(enableButton ? Color.accent : .primary4)
                .frame(width: 40, height: 40)
                .overlay { Icon.send.image(.onAccent) }
        }
        .disabled(viewModel.message.isEmpty)
    }
}

struct ChatCompletionsView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationStack {
            ChatCompletionsView()
                .applyToolbar("Completion", startIcon: .menu)
        }
    }
}
