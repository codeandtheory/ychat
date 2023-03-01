//
//  MainRouter.swift
//  ios-sample
//
//  Created by Koji Osugi on 22/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

internal final class MainRouter: ObservableObject {
    static var shared = MainRouter()

    private init() {}

    enum Destination: Equatable {
        case completion
    }

    @Published var navGraph: NavGraph<Destination> = .init(destination: .completion)

    func replace(_ destination: Destination) {
        navGraph.replace(destination, animation: .opacity)
    }
}
