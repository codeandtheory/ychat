//
//  ImageButton.swift
//  ios-sample
//
//  Created by Koji Osugi on 21/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ImageButton: View {
    private let icon: Icon
    private let action: () -> Void

    init(
        _ icon: Icon,
        action: @escaping () -> Void = {}
    ) {
        self.icon = icon
        self.action = action
    }

    var body: some View {
        Button(action: action) { icon.image() }
    }
}

struct ImageButton_Previews: PreviewProvider {
    static var previews: some View {
        ImageButton(Icon.menu)
    }
}
