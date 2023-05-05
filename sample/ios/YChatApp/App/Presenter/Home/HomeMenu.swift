//
//  HomeMenu.swift
//  ychat-ios
//
//  Created by Koji Osugi on 06/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

internal struct HomeMenu: View {
    @Binding
    private var showSidebar: Bool
    
    @Binding
    private var selectedMenuItem: Item
    
    @StateObject
    private var providerManager: ProviderManager = .shared
    
    init(
        showSidebar: Binding<Bool>,
        selectedMenuItem: Binding<Item>
    ) {
        self._showSidebar = showSidebar
        self._selectedMenuItem = selectedMenuItem
    }
    
    var body: some View {
        SideMenu(isVisible: $showSidebar) {
            VStack(alignment: .leading, spacing: 0) {
                LogoLabel()
                    .padding(.vertical, 20)
                    .padding(.horizontal, 16)
                Divider().background(Color.divider)
                    .padding(.bottom, 12)
                switch providerManager.selectedProvider {
                case .openAi: openAiMenu()
                case .ducAi: ducAiMenu()
                }
            }
        }
    }
    
    private func openAiMenu() -> some View {
        ForEach(Item.openAiMenu, id: \.self) {
            if $0.hasTopDivider {
                Divider().background(Color.divider)
                    .padding(.vertical, 8)
                    .padding(.leading, 48)
            }
            sideMenuItemView(item: $0)
                .padding(.top, 4)
        }
    }
    
    private func ducAiMenu() -> some View {
        ForEach(Item.ducAiMenu, id: \.self) {
            if $0.hasTopDivider {
                Divider().background(Color.divider)
                    .padding(.vertical, 8)
                    .padding(.leading, 48)
            }
            sideMenuItemView(item: $0)
                .padding(.top, 4)
        }
    }
    
    @ViewBuilder
    private func sideMenuItemView(
        item: Item
    ) -> some View {
        let isSelected: Bool = selectedMenuItem == item
        let bgColor = isSelected ? Color.accentLight : .background
        let textStyle = isSelected ? Typography.mediumTitle : .mediumBody
        Button {
            showSidebar.toggle()
            self.selectedMenuItem = item
        } label: {
            HStack(spacing: 8) {
                item.icon.image()
                Text(item.title)
                    .foregroundColor(Color.text1)
                    .style(textStyle)
                Spacer()
            }
            .padding(.vertical, 14)
            .padding(.horizontal, 16)
            .frame(maxWidth: .infinity)
            .background(bgColor)
            .cornerRadius(20, corners: [.topRight, .bottomRight])
            .padding(.trailing, 16)
        }
    }
}

extension HomeMenu {
    enum Item: CaseIterable {
        case models
        case completions
        case chatCompletions
        case edits
        case images
        case audio
        case settings
        
        var icon: Icon {
            switch self {
            case .models: return .bot
            case .completions: return .text
            case .settings: return .settings
            case .chatCompletions: return .chatBubbleOutline
            case .edits: return .edit
            case .images: return .image
            case .audio: return .audio
            }
        }
        
        var title: String {
            switch self {
            case .models: return "Models"
            case .completions: return "Completions"
            case .settings: return "Settings"
            case .chatCompletions: return "ChatCompletions"
            case .edits: return "Edits"
            case .images: return "Images"
            case .audio: return "Audio"
            }
        }
        
        var hasTopDivider: Bool {
            switch self {
            case .settings: return true
            default: return false
            }
        }
        
        static var openAiMenu: [Item] {
            Item.allCases
        }
        
        static var ducAiMenu: [Item] {
            [Item.completions, .chatCompletions, .settings]
        }
    }
}

internal struct HomeMenu_Previews: PreviewProvider {
    static var previews: some View {
        HomeMenu(
            showSidebar: .constant(true),
            selectedMenuItem: .constant(.models)
        )
    }
}
