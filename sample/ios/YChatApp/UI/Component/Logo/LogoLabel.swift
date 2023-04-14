//
//  LogoLabel.swift
//  ychat-ios
//
//  Created by Koji Osugi on 06/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct LogoLabel: View {
    var body: some View {
        Image("logo_label")
    }
}

internal struct LogoLabelLight_Previews: PreviewProvider {
    static var previews: some View {
        LogoLabel()
    }
}

internal struct LogoLabelDark_Previews: PreviewProvider {
    static var previews: some View {
        LogoLabel()
            .preferredColorScheme(.dark)
    }
}
