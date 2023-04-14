//
//  ToolbarModifer.swift
//  ios-sample
//
//  Created by Koji Osugi on 21/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

extension View {
    func applyToolbar(
        _ title: String,
        startIcon: Icon,
        onButtonAction: @escaping () -> Void = {}
    ) -> some View {
        self
            .navigationBarBackButtonHidden(true)
            .toolbar {
                ToolbarItemGroup(placement: .navigationBarLeading) {
                    HStack {
                        ImageButton(startIcon, action: onButtonAction)
                        Text(title).style(.displayTitle)
                    }
                }
            }
    }
}
