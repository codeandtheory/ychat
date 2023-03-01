//
//  Icon.swift
//  ios-sample
//
//  Created by Koji Osugi on 21/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

enum Icon: String, CaseIterable {
    case menu = "ic_menu"
    case refresh = "ic_refresh"
    case arrowLeft = "ic_arrow_left"
    case arrowDown = "ic_arrow_down"
    case swap = "ic_swap"
    case settings = "ic_settings"
    case bot = "ic_bot"
    case send = "ic_send"
    case chat = "ic_chat"
    case edit = "ic_edit"
    case warningOutline = "ic_warning_outline"
    case logo = "ic_logo"
    case logoBig = "ic_logo_big"
    case fitSplash = "bg_fit_splash"
    case person = "ic_person"
    case fitHome = "bg_fit_home"
    case moreHorizontal = "ic_more_horizontal"
    case addCircle = "ic_add_circle"
    case home = "ic_home"
    case close = "ic_close"
    
    var uiImage: UIImage {
        guard let image = UIImage(named: self.rawValue) else {
            fatalError("Image not found for the given value: " + self.rawValue)
        }
        return image
    }
}

private struct IconSample: View {
    private let columns = [
        GridItem(.adaptive(minimum: 80))
    ]

    var body: some View {
        ScrollView {
            LazyVGrid(columns: columns, spacing: 8) {
                ForEach(Icon.allCases, id: \.self) {
                    Image($0.rawValue)
                        .resizable()
                        .frame(width: 24, height: 24)
                }
            }
        }
    }
}

internal struct IconSample_Previews: PreviewProvider {
    static var previews: some View {
        IconSample()
    }
}
