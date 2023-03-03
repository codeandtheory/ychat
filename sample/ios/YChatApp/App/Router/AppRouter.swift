//
//  AppRouter.swift
//  ios-sample
//
//  Created by Koji Osugi on 22/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

internal final class AppRouter: ObservableObject {
    static var shared = AppRouter()

    private init() {}

    enum Destination: Equatable {
        case splash
        case main
    }

    @Published var navGraph: NavGraph<Destination> = .init(destination: .splash)

    func push(_ destination: Destination) {
        navGraph.push(destination: destination)
    }
}
