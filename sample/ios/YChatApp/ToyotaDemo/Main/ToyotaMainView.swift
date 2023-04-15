//
//  ToyotaMainView.swift
//  Y-Chat
//
//  Created by Koji Osugi on 14/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ToyotaMainView: View {
    @State
    private var showToyotaChat: Bool = false
    
    var body: some View {
        NavigationStack {
            ZStack(alignment: .bottom) {
                Image("image_main_screen")
                ButtonContained(
                    "Have questions? Let us help!",
                    onAction: { showToyotaChat = true }
                )
                .padding(.bottom, 48)
                .padding(.horizontal, 32)
            }
            .fullScreen()
            .navigationDestination(isPresented: $showToyotaChat) {
                ToyotaChatView()
                    .applyToyotaToolbar()
            }
        }
    }
}

struct ToyotaMainView_Previews: PreviewProvider {
    static var previews: some View {
        ToyotaMainView()
    }
}
