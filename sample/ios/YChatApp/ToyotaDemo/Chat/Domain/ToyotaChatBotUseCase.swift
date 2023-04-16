//
//  ToyotaChatBotUseCase.swift
//  Y-Chat
//
//  Created by Koji Osugi on 15/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import YChat

internal class ToyotaChatBotUseCase {
    private var chatCompletions: ChatCompletions
    
    init(chatCompletions: ChatCompletions = ToyotaChatProvider.provide()) {
        self.chatCompletions = chatCompletions
    }
    
    func getInitialMessage() -> Message {
        let message = "Hello there! Let me know what your budget is, if you want to buy or lease, and any other details that can help me find the perfect vehicle for you."
        chatCompletions.addMessage(role: "assistant", content: message)
        return Message(type: .botMessage(text: message))
    }
    
    @MainActor
    func sendMessage(message: String) async throws -> [Message] {
        let result = try await chatCompletions.execute(content: message)[0].content
        let formattedResult = processResultMessage(content: result)
        return formattedResult
    }
    
    private func processResultMessage(content: String) -> [Message] {
        if let range = content.range(of: "SHOW_BUYING_LEASING") {
            let newString = content.replacingOccurrences(of: "SHOW_BUYING_LEASING", with: "", range: range)
            return [Message(type: .botMessage(text: newString)), Message(type: .buyingLeasing)]
        }
        if let range = content.range(of: "SHOW_QR_CODE") {
            let newString = content.replacingOccurrences(of: "SHOW_QR_CODE", with: "", range: range)
            return [Message(type: .botMessage(text: newString)), Message(type: .qrCode)]
        }
        return [Message(type: .botMessage(text: content))]
    }
}
