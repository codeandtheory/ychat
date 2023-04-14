//
//  ModelsView.swift
//  Y-Chat
//
//  Created by Koji Osugi on 11/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ModelsView: View {
    @ObservedObject
    private var viewModel: ModelsViewModel
    
    init(viewModel: ModelsViewModel = .init()) {
        self.viewModel = viewModel
        viewModel.fetchListModels()
    }
    
    var body: some View {
        VStack(spacing: 0) {
            switch viewModel.state {
            case .loading:
                Spacer()
                ProgressView()
                Spacer()
            case .error:
                Spacer()
                Feedback(state: .error, onAction: { viewModel.fetchListModels() })
                Spacer()
            case .success(let models):
                ScrollView {
                    ForEach(models, id: \.self) {
                        ItemMenu(startText: $0.id, caption: $0.ownedBy)
                    }.padding(.top, 16)
                }
            }
        }
        .fullScreen()
    }
}

struct ModelsView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationStack {
            ModelsView()
                .applyToolbar("Models", startIcon: .menu)
        }
    }
}
