//
//  ToyotaChatViewModel.swift
//  Y-Chat
//
//  Created by Koji Osugi on 14/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

struct Message: Identifiable, Equatable {
    let id: String = UUID().uuidString
    var type: MessageType
    
    enum MessageType: Equatable {
        case botMessage(text: String)
        case senderMessage(text: String, hasError: Bool = false)
        case typing
        case buyingLeasing
        case qrCode
    }
}

internal final class ToyotaChatViewModel: ObservableObject {
    @Published
    var message: String = ""
    
    @Published
    var chatMessageList: [Message] = []
    
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
                result.forEach { chatMessageList.append($0) }
            } catch {
                setLoading(isLoading: false)
                setError()
            }
        }
    }
    
    private func addSenderMessage(message: String) {
        let message = Message(type: .senderMessage(text: message))
        chatMessageList.append(message)
    }
    
    private func cleanLastMessage() {
        message = ""
    }
    
    private func addLoading() {
        let message = Message(type: .typing)
        chatMessageList.append(message)
    }
    
    private func setLoading(isLoading: Bool) {
        if isLoading {
            let message = Message(type: .typing)
            chatMessageList.append(message)
        } else {
            chatMessageList.removeAll { $0.type == .typing }
        }
    }
    
    private func setError() {
        let lastRemoved = chatMessageList.removeLast()
        switch lastRemoved.type {
        case .senderMessage(let text, _):
            chatMessageList.append(Message(type: .senderMessage(text: text, hasError: true)))
        default: return
        }
    }
}
