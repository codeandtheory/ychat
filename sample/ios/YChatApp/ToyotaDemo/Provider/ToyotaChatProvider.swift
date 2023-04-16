//
//  ToyotaChatProvider.swift
//  Y-Chat
//
//  Created by Koji Osugi on 15/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import YChat

struct ToyotaChatProvider {
    static func provide() -> ChatCompletions {
        let path = Bundle.main.url(forResource: "toyota_chatbot_memory", withExtension: "json")! // swiftlint:disable:this force_unwrapping
        let data = try! Data(contentsOf: path) // swiftlint:disable:this force_try
        let results: [ChatBotContext] = try! JSONDecoder().decode([ChatBotContext].self, from: data) // swiftlint:disable:this force_try
        let chatCompletions = YChatCompanion.shared.create(apiKey: Config.apiKey)
            .chatCompletions()
            .setMaxTokens(tokens: 256)
        results.forEach {
            chatCompletions.addMessage(role: $0.type, content: $0.message)
        }
        return chatCompletions
    }
}
