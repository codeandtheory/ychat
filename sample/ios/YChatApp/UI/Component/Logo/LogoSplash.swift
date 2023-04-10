//
//  LogoSplash.swift
//  ychat-ios
//
//  Created by Koji Osugi on 07/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct LogoSplash: View {
    var body: some View {
        Image("logo_splash")
    }
}

internal struct LogoSplashLight_Previews: PreviewProvider {
    static var previews: some View {
        LogoSplash()
    }
}

internal struct LogoSplashDark_Previews: PreviewProvider {
    static var previews: some View {
        LogoSplash()
            .preferredColorScheme(.dark)
    }
}
