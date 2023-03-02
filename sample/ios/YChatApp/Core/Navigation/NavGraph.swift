//
//  NavGraph.swift
//  ios-sample
//
//  Created by Koji Osugi on 22/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct NavGraph<T> {
    var navStack: [T] = []
    var destination: T
    var animation: AnyTransition

    init(destination: T) {
        self.animation = .nextSlide
        self.navStack.append(destination)
        self.destination = destination
    }

    mutating func replace(_ destination: T, animation: AnyTransition = .nextSlide) {
        self.animation = animation
        _ = navStack.popLast()
        self.navStack.append(destination)
        self.destination = destination
    }

    mutating func push(destination: T) {
        self.animation = .nextSlide
        self.navStack.append(destination)
        self.destination = destination
    }

    mutating func pop() {
        self.animation = .backSlide
        _ = navStack.popLast()
        guard let lastDestination = navStack.last else { return }
        self.destination = lastDestination
    }
}
