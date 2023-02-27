//
//  MainView.swift
//  ios-sample
//
//  Created by Koji Osugi on 22/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

internal struct MainView: View {
    @ObservedObject
    private var mainRouter: MainRouter = MainRouter.shared

    @State private var showSidebar: Bool = false

    private var topBarTitle: String {
        switch mainRouter.navGraph.destination {
        case .completion: return "Completion"
        }
    }

    var body: some View {
        NavigationStack {
            ZStack {
                VStack(alignment: .leading) {
                    topBar()
                    Group {
                        switch mainRouter.navGraph.destination {
                        case .completion: CompletionView()
                        }
                    }
                    .transition(mainRouter.navGraph.animation)
                    .animation(.default, value: mainRouter.navGraph.destination)
                }
                .fullScreen()
                SideMenu(isVisible: $showSidebar) {
                    sideMenuContent()
                }
            }
        }
    }

    @ViewBuilder
    private func topBar() -> some View {
        HStack(spacing: 0) {
            ImageButton(
                Icon.menu.uiImage,
                color: .accentColor,
                action: { showSidebar.toggle() }
            )
            Text(topBarTitle)
                .style(.title)
                .padding(.leading, 16)
            Spacer()
        }
        .padding(16)
        .frame(maxWidth: .infinity)
        .compositingGroup()
    }

    @ViewBuilder
    private func sideMenuContent() -> some View {
        VStack(alignment: .leading, spacing: 0) {
            HStack(spacing: 8) {
                Image(uiImage: Icon.logo.uiImage)
                Text("YChat GPT")
                    .style(.bodyBold)
            }
            .padding(.horizontal, 16)
            .padding(.vertical, 16)
            Divider()
                .padding(.bottom, 16)
            menuItem(
                icon: Icon.chat.uiImage,
                text: "Completion",
                destination: .completion
            )
        }
        .frame(
            maxWidth: .infinity,
            maxHeight: .infinity,
            alignment: .topLeading
        )
    }

    @ViewBuilder
    private func menuItem(
        icon: UIImage,
        text: String,
        destination: MainRouter.Destination
    ) -> some View {
        let isSelected: Bool = mainRouter.navGraph.destination == destination
        let bgColor = isSelected ? Color.primaryExtraLight : .background
        let foregroundColor = isSelected ? Color.accentColor : .grayDark
        Button {
            mainRouter.replace(destination)
            showSidebar.toggle()
        } label: {
            HStack(spacing: 8) {
                Image(uiImage: icon)
                    .renderingMode(.template)
                    .foregroundColor(foregroundColor)
                Text(text)
                    .foregroundColor(foregroundColor)
                    .style(.caption)
                Spacer()
            }
            .padding(.vertical, 12)
            .padding(.horizontal, 16)
            .frame(maxWidth: .infinity)
            .background(bgColor)
            .cornerRadius(20, corners: [.topRight, .bottomRight])
            .padding(.trailing, 16)
        }
    }
}

internal struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        MainView()
    }
}
