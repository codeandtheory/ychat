//
//  SplashView.swift
//  ios-sample
//
//  Created by Koji Osugi on 22/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

internal struct SplashView: View {
    private let appRouter: AppRouter

    init(appRouter: AppRouter = AppRouter.shared) {
        self.appRouter = appRouter
    }

    var body: some View {
        LogoSplash().fullScreen(alignment: .center)
        .onAppear {
            DispatchQueue.main.asyncAfter(deadline: .now() + 2) {
                appRouter.push(.main)
            }
        }
    }
}

internal struct SplashViewLight_Previews: PreviewProvider {
    static var previews: some View {
        SplashView()
    }
}

internal struct SplashViewDark_Previews: PreviewProvider {
    static var previews: some View {
        SplashView()
            .preferredColorScheme(.dark)
    }
}
