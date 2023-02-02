//
//  FitLogo.swift
//  ios-sample
//
//  Created by Koji Osugi on 31/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

internal struct FitLogo: View {
    
    var size: CGFloat = 24
    var color = Color.primaryExtraLight
    
    var body: some View {
        Group {
            Text("FIT")
                .font(.system(size: size))
                .tracking(2.56)
                .foregroundColor(color)
                .fontWeight(.bold) +
            Text("CHATGPT")
                .font(.system(size: size))
                .tracking(2.56)
                .foregroundColor(color)
                .fontWeight(.regular)
        }
    }
}

internal struct FitLogo_Previews: PreviewProvider {
    static var previews: some View {
        FitLogo(color: .black)
    }
}
