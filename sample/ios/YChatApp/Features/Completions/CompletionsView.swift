//
//  CompletionsView.swift
//  Y-Chat
//
//  Created by Koji Osugi on 11/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CompletionsView: View {
    @ObservedObject
    private var viewModel: CompletionsViewModel
    
    init(viewModel: CompletionsViewModel = .init()) {
        self.viewModel = viewModel
    }
    
    var body: some View {
        VStack(spacing: 0) {
            TextField(text: $viewModel.input) {
                Text("Write a tagline for an ice cream shop.")
                    .foregroundColor(.text3)
                    .style(.mediumBody)
            }
            .textFieldStyle(DefaultTextFieldStyle())
            .disabled(viewModel.isLoading)
            .opacity(viewModel.isLoading ? 0.4 : 1)
            ButtonContained(
                "Submit",
                isEnabled: !viewModel.input.isEmpty
            )
            .padding(.top, 16)
            .padding(.bottom, 24)
            OutputBox(states: [])
        }
        .padding(16)
        .fullScreen()
    }
}

struct CompletionsView_Previews: PreviewProvider {
    static var previews: some View {
        CompletionsView()
    }
}
