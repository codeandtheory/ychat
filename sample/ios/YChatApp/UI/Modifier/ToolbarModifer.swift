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
    
    func applyToyotaToolbar() -> some View {
        self
            .navigationBarBackButtonHidden(true)
            .toolbar {
                ToolbarItem(placement: .navigationBarLeading) {
                    Image("logo_toyota_label")
                }
                ToolbarItem(placement: .navigationBarTrailing) {
                    HStack(spacing: 16) {
                        Text("AI Concierge")
                            .font(.system(size: 16))
                            .foregroundColor(.text1)
                            .fontWeight(.regular)
                        Image("ic_bottom_arrow")
                    }
                }
            }
            .toolbarBackground(.visible, for: .navigationBar)
            .toolbarBackground(Color.white, for: .navigationBar)
    }
}
