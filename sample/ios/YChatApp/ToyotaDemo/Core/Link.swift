//
//  Link.swift
//  Y-Chat
//
//  Created by Koji Osugi on 15/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

struct Link: Identifiable {
    var id: String = UUID().uuidString
    var url: URL
    
    init(_ link: String) {
        self.url = URL(string: link)! // swiftlint:disable:this force_unwrapping
    }
    
    init(url: URL) {
        self.url = url
    }
}
