//
//  ViewModifer.swift
//  ios-sample
//
//  Created by Koji Osugi on 21/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

extension View {
    
    func fullScreen(alignment: Alignment = .top, background: Color = .background) -> some View {
        self.frame(maxWidth: .infinity, maxHeight: .infinity, alignment: alignment)
            .background(background)
    }
}
