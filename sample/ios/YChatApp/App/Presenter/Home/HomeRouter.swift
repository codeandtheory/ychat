//
//  HomeRouter.swift
//  Y-Chat
//
//  Created by Koji Osugi on 04/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

internal class HomeRouter: ObservableObject {
    @Published
    var navigationPath = NavigationPath()
    
    func navigateTo(_ route: SettingsView.Route) {
        navigationPath.append(route)
    }
}
