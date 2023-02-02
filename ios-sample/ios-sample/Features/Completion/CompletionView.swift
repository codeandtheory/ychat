import SwiftUI

struct CompletionView: View {
    
    @ObservedObject
    private var viewModel: CompletionViewModel
    
    private var enableButton: Bool {
        !viewModel.isLoading && !viewModel.message.isEmpty
    }
    
    init(viewModel: CompletionViewModel = .init()) {
        self.viewModel = viewModel
    }
    
    var body: some View {
        VStack {
            if (viewModel.chatMessageList.isEmpty) {
                EmptyMessage()
                    .padding(.top, 16)
            } else {
                ScrollViewReader { value in
                    ScrollView {
                        ForEach(viewModel.chatMessageList) {
                            ChatBubble(chatMessage: $0)
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
            SendMessageSection()
                .padding(.horizontal, 12)
                .padding(.vertical, 12)
        }.fullScreen()
    }
    
    @ViewBuilder
    private func EmptyMessage() -> some View {
        ZStack {
            Text("Enter any message and chat GPT will answer it.")
                .foregroundColor(.grayMain)
                .style(.caption)
                .bold()
        }
        .padding(.horizontal, 16)
        .padding(.vertical, 8)
        .background(Color.grayLight)
        .cornerRadius(16)
        .padding(.horizontal, 32)
    }
    
    @ViewBuilder
    private func ChatBubble(chatMessage: ChatMessage) -> some View {
        switch chatMessage.type {
        case .human(let error):
            HStack(spacing: 4) {
                Spacer()
                Spacer().frame(width: 60)
                HumanChatBubble(message: chatMessage.message)
                if error {
                    Image(uiImage: .warningOutline)
                        .renderingMode(.template)
                        .foregroundColor(.red)
                }
            }
        case .ai:
            HStack {
                AIChatBubble(message: chatMessage.message)
                Spacer().frame(width: 60)
                Spacer()
            }
        case .loading:
            HStack {
                TypingLoading()
                Spacer()
            }
        }
    }
    
    @ViewBuilder
    private func HumanChatBubble(message: String) -> some View {
        ZStack() {
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
                    Image(uiImage: .bot)
                        .renderingMode(.template)
                        .foregroundColor(.white)
                }
            ZStack() {
                Text(message)
                    .foregroundColor(.grayDark)
                    .style(.body)
                    .multilineTextAlignment(.leading)
            }
            .padding(.horizontal, 16)
            .padding(.vertical, 8)
            .background(Color.grayLight)
            .cornerRadius(16, corners: [.bottomLeft, .bottomLeft, .topRight])
        }
    }
    
    @ViewBuilder
    private func SendMessageSection() -> some View {
        HStack(spacing: 8) {
            TextField(text: $viewModel.message) {
                Text("Message")
                    .foregroundColor(.grayMedium)
                    .style(.subtitle)
            }
            .textFieldStyle(DefaultTextFieldStyle())
            .disabled(viewModel.isLoading)
            .opacity(viewModel.isLoading ? 0.4 : 1)
            SendButton()
        }
    }
    
    @ViewBuilder
    private func SendButton() -> some View {
        Button(action: { viewModel.sendMessage() }) {
            Circle()
                .fill(enableButton ? Color.accentMain : .grayLight)
                .frame(width: 40, height: 40)
                .overlay {
                    Image(uiImage: .send)
                        .renderingMode(.template)
                        .foregroundColor(.white)
                }
        }
        .disabled(viewModel.message.isEmpty)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationStack {
            CompletionView()
                .applyToolbar("Completion")
        }
    }
}
