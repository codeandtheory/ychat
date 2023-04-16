//
//  ChatBotContext.swift
//  Y-Chat
//
//  Created by Koji Osugi on 15/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

struct ChatBotContext: Decodable {
    let type: String
    let message: String
}
