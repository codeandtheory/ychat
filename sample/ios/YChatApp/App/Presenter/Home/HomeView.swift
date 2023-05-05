//
//  MainView.swift
//  ios-sample
//
//  Created by Koji Osugi on 22/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

internal struct HomeView: View {
    @State
    private var showSidebar: Bool = false
    
    @State
    private var selectedMenu: HomeMenu.Item
    
    @StateObject
    private var router: HomeRouter = .init()
    
    init() {
        let selectedProvider = ProviderManager.shared.selectedProvider
        switch selectedProvider {
        case .openAi: selectedMenu = HomeMenu.Item.openAiMenu.first ?? .models
        case .ducAi: selectedMenu = HomeMenu.Item.ducAiMenu.first ?? .completions
        }
    }
    
    var body: some View {
        ZStack {
            NavigationStack(path: $router.navigationPath) {
                Group {
                    switch selectedMenu {
                    case .models: ModelsView()
                    case .completions: CompletionsView()
                    case .chatCompletions: ChatCompletionsView()
                    case .settings: SettingsView(router: router)
                    default: Feedback(state: .construction)
                    }
                }
                .applyToolbar(
                    selectedMenu.title,
                    startIcon: .menu,
                    onButtonAction: { showSidebar.toggle() }
                )
                .navigationDestination(for: SettingsView.Route.self) {
                    switch $0 {
                    case .providers: ProviderListView(router: router)
                    case .openAiProvider: Feedback(state: .construction)
                    }
                }
            }
            HomeMenu(showSidebar: $showSidebar, selectedMenuItem: $selectedMenu)
        }
    }
}

internal struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
