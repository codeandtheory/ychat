//
//  FitChatViewModel.swift
//  ios-sample
//
//  Created by Koji Osugi on 31/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import YChat

internal final class FitChatViewModel: ObservableObject {
    private var chatGpt: YChat {
        YChatCompanion.shared.create(apiKey: Config.apiKey)
    }
    
    @Published
    var state: State = .loading
    
    @Published
    var message: String = ""
    
    @Published
    var chatMessageList: [ChatMessage] = []
    
    var isLoading: Bool {
        chatMessageList.contains { $0.type == .loading }
    }
    
    @MainActor
    func initChat() {
        Task.init {
            state = .loading
            do {
                try await chatGpt.completion()
                    // swiftlint:disable:next line_length
                    .setInput(input: "write your best answer if the question is related to fitness. If the question is not related to fitness write “I cant answer”")
                    .saveHistory(isSaveHistory: true)
                    .execute()
                state = .success
            } catch {
                state = .error
            }
        }
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
                    .saveHistory(isSaveHistory: true)
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
            type: .bot
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
    
    enum State {
        case loading, error, success
    }
}
