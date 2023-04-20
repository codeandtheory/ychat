//
//  StringExtensions.swift
//  Y-Chat
//
//  Created by Koji Osugi on 18/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

extension String {
    subscript(offset: Int) -> Character {
        self[index(startIndex, offsetBy: offset)]
    }
}
