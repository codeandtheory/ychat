//
//  ToyotaChatViewModel.swift
//  Y-Chat
//
//  Created by Koji Osugi on 14/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

internal final class ToyotaChatViewModel: ObservableObject {
    @Published
    var message: String = ""
    
    @Published
    var chatMessageList: [MessageState] = []
    
    var isLoading: Bool {
        chatMessageList.contains { $0.type == .typing }
    }
    
    var enableButton: Bool {
        !isLoading && !message.isEmpty
    }
    
    private var toyotaChatBotUseCase: ToyotaChatBotUseCase
    
    init(toyotaChatBotUseCase: ToyotaChatBotUseCase = .init()) {
        self.toyotaChatBotUseCase = toyotaChatBotUseCase
        chatMessageList.append(toyotaChatBotUseCase.getInitialMessage())
    }
    
    @MainActor
    func sendMessage() {
        Task.init {
            let input = message
            addSenderMessage(message: message)
            cleanLastMessage()
            setLoading(isLoading: true)
            do {
                let result = try await toyotaChatBotUseCase.sendMessage(message: input)
                setLoading(isLoading: false)
                chatMessageList.append(MessageState(type: .botMessage(text: result)))
            } catch {
                setLoading(isLoading: false)
                setError()
            }
        }
    }
    
    private func addSenderMessage(message: String) {
        let message = MessageState(type: .senderMessage(text: message))
        chatMessageList.append(message)
    }
    
    private func cleanLastMessage() {
        message = ""
    }
    
    private func addLoading() {
        let message = MessageState(type: .typing)
        chatMessageList.append(message)
    }
    
    private func setLoading(isLoading: Bool) {
        if isLoading {
            let message = MessageState(type: .typing)
            chatMessageList.append(message)
        } else {
            chatMessageList.removeAll { $0.type == .typing }
        }
    }
    
    private func setError() {
        let lastRemoved = chatMessageList.removeLast()
        switch lastRemoved.type {
        case .senderMessage(let text, _):
            chatMessageList.append(MessageState(type: .senderMessage(text: text, hasError: true)))
        default: return
        }
    }
}
