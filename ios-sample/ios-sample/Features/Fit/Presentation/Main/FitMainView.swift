//
//  FitMainView.swift
//  ios-sample
//
//  Created by Koji Osugi on 31/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

internal struct FitMainView: View {
    
    enum Tab {
        case home, log, more
    }
    
    @State private var selectedTab: Tab = .home
    
    @ObservedObject
    private var controller = Controller.shared
    
    init() {
        _selectedTab = State(initialValue: selectedTab)
        let tabBarAppearance = UITabBarAppearance()
        tabBarAppearance.configureWithOpaqueBackground()
        tabBarAppearance.backgroundColor = UIColor(Color.grayDark)
        UITabBar.appearance().unselectedItemTintColor = UIColor(Color.primaryExtraLight)
        UITabBar.appearance().backgroundColor = UIColor(Color.primaryExtraLight)
        UITabBar.appearance().standardAppearance = tabBarAppearance
        UITabBar.appearance().scrollEdgeAppearance = tabBarAppearance
    }
    
    var body: some View {
        TabView(selection: $selectedTab) {
            FitHomeView()
                .tabItem {
                    Image(uiImage: .home)
                    Text("Home")
                }
                .toolbar(controller.hideTabView ? .hidden : .visible, for: .tabBar)
                .animation(.linear(duration: 0.5), value: controller.hideTabView)
                .tag(Tab.home)
            Text("Log Screen")
                .tabItem {
                    Image(uiImage: .addCircle)
                    Text("Log")
                }
                .toolbar(controller.hideTabView ? .hidden : .visible, for: .tabBar)
                .animation(.linear(duration: 0.5), value: controller.hideTabView)
                .tag(Tab.log)
            Text("More Screen")
                .tabItem {
                    Image(uiImage: .moreHorizontal)
                    Text("More")
                }
                .toolbar(controller.hideTabView ? .hidden : .visible, for: .tabBar)
                .animation(.linear(duration: 0.5), value: controller.hideTabView)
                .tag(Tab.more)
        }
        .accentColor(.primaryLight)
    }
}

extension FitMainView {
    
    class Controller: ObservableObject {
        private init() {}
        
        static var shared = Controller()
        
        @Published var hideTabView = false
    }
}

internal struct FitMainView_Previews: PreviewProvider {
    static var previews: some View {
        FitMainView()
    }
}
