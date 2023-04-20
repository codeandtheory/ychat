//
//  ViewExtensions.swift
//  Y-Chat
//
//  Created by Koji Osugi on 18/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

extension View {
    func applyCardStyle(cornerRadius: CGFloat = 8) -> some View {
        self.background(.white)
            .clipped()
            .cornerRadius(cornerRadius)
            .shadow(color: Color.primary4, radius: 10, x: 0, y: 0)
    }
}
