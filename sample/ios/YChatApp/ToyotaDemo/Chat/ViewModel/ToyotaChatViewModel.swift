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
        case senderMessage(text: String)
        case typing
    }
}

internal final class ToyotaChatViewModel: ObservableObject {
    @Published
    var message: String = ""
    
    @Published
    var chatMessageList: [Message] = [
        Message(type: .botMessage(text: "[Hello there!](https://www.google.com)")),
        Message(type: .senderMessage(text: "Hello there!"))
    ]
    
    var isLoading: Bool {
        chatMessageList.contains { $0.type == .typing }
    }
    
    var enableButton: Bool {
        !isLoading && !message.isEmpty
    }
    
    @MainActor
    func sendMessage() {
        print("teste")
    }
}
