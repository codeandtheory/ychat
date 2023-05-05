//
//  SettingsView.swift
//  Y-Chat
//
//  Created by Koji Osugi on 04/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SettingsView: View {
    enum Route: Hashable {
        case providers
        case openAiProvider
    }
    
    @StateObject
    var router: HomeRouter
    
    @StateObject
    private var providerManager: ProviderManager = .shared
    
    var body: some View {
        VStack {
            ItemMenu(
                startIcon: .api,
                startText: "Provider",
                endCaption: providerManager.selectedProvider.name,
                endIcon: .arrowRight,
                isDividerVisible: false,
                onClickItem: { router.navigateTo(.providers) }
            )
            .padding(.top, 16)
        }
        .fullScreen()
    }
}

struct SettingsView_Previews: PreviewProvider {
    static var previews: some View {
        SettingsView(router: .init())
    }
}
