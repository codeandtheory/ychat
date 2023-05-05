//
//  ProviderListView.swift
//  Y-Chat
//
//  Created by Koji Osugi on 04/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import YChat

struct ProviderListView: View {
    @StateObject
    var router: HomeRouter
    
    @StateObject
    private var providerManager: ProviderManager = .shared
    
    @Environment(\.dismiss)
    private var dismiss
    
    @State
    private var showOpenAiSheet: Bool = false
    
    @State
    private var showDucAiSheet: Bool = false
    
    var body: some View {
        VStack(spacing: 0) {
            ForEach(providerManager.providers, id: \.rawValue) {
                switch $0 {
                case .openAi:
                    buildItem(
                        name: "OpenAI",
                        isFavourite: providerManager.selectedProvider == .openAi,
                        onClickItem: { showOpenAiSheet.toggle() }
                    )
                case .ducAi:
                    buildItem(
                        name: "DucAI",
                        isFavourite: providerManager.selectedProvider == .ducAi,
                        onClickItem: { showDucAiSheet.toggle() }
                    )
                }
            }
        }
        .padding(.top, 16)
        .applyToolbar(
            "Provider",
            startIcon: .arrowLeft,
            onButtonAction: { dismiss() }
        )
        .fullScreen()
        .bottomSheet(show: $showOpenAiSheet) {
            buildOpenAiSheet()
        }
        .bottomSheet(show: $showDucAiSheet) {
            buildDucAiSheet()
        }
    }
    
    @ViewBuilder
    private func buildItem(
        name: String,
        isFavourite: Bool = false,
        onClickItem: @escaping () -> Void = {}
    ) -> some View {
        Button(action: onClickItem) {
            HStack(spacing: 0) {
                Text(name)
                    .style(.mediumBody)
                Spacer()
                if isFavourite {
                    Icon.star.image()
                }
                Icon.menuVertical.image()
                    .padding(.leading, 8)
            }
            .padding(16)
        }
    }
    
    @ViewBuilder
    private func buildOpenAiSheet() -> some View {
        VStack(spacing: 0) {
            ItemMenu(
                startIcon: .star,
                startText: "Set as default",
                isDividerVisible: false,
                onClickItem: {
                    providerManager.setSelectedProvider(providers: .openAi)
                    showOpenAiSheet.toggle()
                }
            )
            ItemMenu(
                startIcon: .edit,
                startText: "edit",
                isDividerVisible: false
            )
        }
    }
    
    @ViewBuilder
    private func buildDucAiSheet() -> some View {
        VStack(spacing: 0) {
            ItemMenu(
                startIcon: .star,
                startText: "Set as default",
                isDividerVisible: false,
                onClickItem: {
                    providerManager.setSelectedProvider(providers: .ducAi)
                    showDucAiSheet.toggle()
                }
            )
        }
    }
}

struct ProviderListView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationStack {
            ProviderListView(router: .init())
        }
    }
}
