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
    private var mock: ToyotaApiMock = .init()
    
    init(chatCompletions: ChatCompletions = ToyotaChatProvider.provide()) {
        self.chatCompletions = chatCompletions
    }
    
    func getInitialMessage() -> MessageState {
        let message = "Hello there! Let me know what your budget is, if you want to buy or lease, and any other details that can help me find the perfect vehicle for you."
        chatCompletions.addMessage(role: "assistant", content: message)
        return MessageState(type: .botMessage(text: message))
    }
    
    @MainActor
    func sendMessage(message: String) async throws -> String {
        try await mock.sendMessage(message: message)
        //        let result = try await chatCompletions.execute(content: message)[0].content
        //        let formattedResult = processResultMessage(content: result)
        //        return formattedResult
    }
    
//    private func processResultMessage(content: String) -> [MessageState] {
//        if let range = content.range(of: "SHOW_BUYING_LEASING") {
//            let newString = content.replacingOccurrences(of: "SHOW_BUYING_LEASING", with: "", range: range)
//            return [MessageState(type: .botMessage(text: newString)), MessageState(type: .buyingLeasing)]
//        }
//        if let range = content.range(of: "SHOW_QR_CODE") {
//            let newString = content.replacingOccurrences(of: "SHOW_QR_CODE", with: "", range: range)
//            return [MessageState(type: .botMessage(text: newString)), MessageState(type: .qrCode)]
//        }
//        return [MessageState(type: .botMessage(text: content))]
//    }
}
