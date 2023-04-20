//
//  MessageState.swift
//  Y-Chat
//
//  Created by Koji Osugi on 18/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

struct MessageState: Identifiable, Equatable {
    var id: String = UUID().uuidString
    var type: MessageType
    
    enum MessageType: Equatable {
        case botMessage(text: String)
        case senderMessage(text: String, hasError: Bool = false)
        case typing
    }
}
