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
    private var selectedMenu: HomeMenu.Item = .models
    
    var body: some View {
        ZStack {
            NavigationStack {
                Group {
                    switch selectedMenu {
                    case .models: ModelsView()
                    case .chatCompletions: ChatCompletionsView()
                    default: Feedback(state: .construction)
                    }
                }
                .applyToolbar(
                    selectedMenu.title,
                    startIcon: .menu,
                    onButtonAction: { showSidebar.toggle() }
                )
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
