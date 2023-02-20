//
//  CompletionViewModel.swift
//  ios-sample
//
//  Created by Koji Osugi on 20/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import YChatGPT

internal final class CompletionViewModel: ObservableObject {
    
    private var chatGpt: YChatGpt {
        YChatGptCompanion.shared.create(apiKey: Config.apiKey)
    }
    
    @Published
    var message: String = ""
    
    @Published
    var chatMessageList: [ChatMessage] = []
    
    var isLoading: Bool {
        chatMessageList.contains { $0.type == .loading }
    }
    
    @MainActor
    func sendMessage() {
        Task.init {
            let input = message
            addHumanMessage(message: message)
            cleanLastMessage()
            addLoading()
            do {
                let result = try await chatGpt.completion()
                    .setInput(input: input)
                    .setMaxTokens(tokens: 1024)
                    .saveHistory(isSaveHistory: false)
                    .execute()
                removeLoading()
                addAIMessage(message: result)
            } catch {
                removeLoading()
                setError()
            }
        }
    }
    
    private func addHumanMessage(message: String) {
        let chatMessage = ChatMessage(
            id: UUID().uuidString,
            message: message,
            type: .human(error: false)
        )
        chatMessageList.append(chatMessage)
    }
    
    private func addAIMessage(message: String) {
        let chatMessage = ChatMessage(
            id: UUID().uuidString,
            message: message,
            type: .ai
        )
        chatMessageList.append(chatMessage)
    }
    
    private func addLoading() {
        let chatMessage = ChatMessage(
            id: UUID().uuidString,
            type: .loading
        )
        chatMessageList.append(chatMessage)
    }
    
    private func removeLoading() {
        chatMessageList.removeAll { $0.type == .loading }
    }
    
    private func cleanLastMessage() {
        message = ""
        chatMessageList.removeAll(where: { $0.type == .human(error: true) })
    }
    
    private func setError() {
        if let row = self.chatMessageList.lastIndex(where: { $0.type == .human(error: false) }) {
            chatMessageList[row].type = .human(error: true)
        }
    }
}
