//
//  FitSplashView.swift
//  ios-sample
//
//  Created by Koji Osugi on 31/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct FitSplashView: View {
    
    private let appRouter: AppRouter
    
    init(appRouter: AppRouter = AppRouter.shared) {
        self.appRouter = appRouter
    }
    
    var body: some View {
        ZStack {
            FitLogo()
        }
        .background {
            Image(uiImage: .fitSplash)
                .edgesIgnoringSafeArea(.all)
                .statusBarHidden(true)
        }
        .onAppear {
            DispatchQueue.main.asyncAfter(deadline: .now() + 2) {
                appRouter.push(.fitMain)
            }
        }
    }
}

struct FitSplashView_Previews: PreviewProvider {
    static var previews: some View {
        FitSplashView()
    }
}
