//
//  ImageButton.swift
//  ios-sample
//
//  Created by Koji Osugi on 21/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ImageButton: View {
    
    private let icon: UIImage
    private var color: Color?
    private let action: () -> Void
    
    init(
        _ icon: UIImage,
        color: Color? = nil,
        action: @escaping () -> Void = {}
    ) {
        self.icon = icon
        self.color = color
        self.action = action
    }
    
    var body: some View {
        Button(action: action) {
            if let color = color {
                Image(uiImage: icon)
                    .renderingMode(.template)
                    .foregroundColor(color)
            } else {
                Image(uiImage: icon)
            }
        }
    }
}

struct ImageButton_Previews: PreviewProvider {
    static var previews: some View {
        ImageButton(.menu)
    }
}
