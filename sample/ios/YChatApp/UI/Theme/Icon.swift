//
//  Icon.swift
//  ios-sample
//
//  Created by Koji Osugi on 21/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

enum Icon: String, CaseIterable {
    case audio = "ic_audio"
    case model = "ic_model"
    case text = "ic_text"
    case chatBubbleOutline = "ic_chat_bubble_outline"
    case edit = "ic_edit"
    case image = "ic_image"
    case settings = "ic_settings"
    case send = "ic_send"
    case bot = "ic_bot"
    case menu = "ic_menu"
    case warningOutline = "ic_warning_outline"
    case warning = "ic_warning"
    case construction = "ic_construction"
    case arrowRight = "ic_arrow_right"
    case arrowLeft = "ic_arrow_left"
    case api = "ic_api"
    case star = "ic_star"
    case menuVertical = "ic_menu_vertical"
    
    func image(_ tint: Color = .icon, size: CGFloat = 24) -> some View {
        Image(self.rawValue)
            .resizable()
            .renderingMode(.template)
            .foregroundColor(tint)
            .scaledToFit()
            .frame(width: size, height: size)
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
                    $0.image()
                }
            }
        }
    }
}

internal struct IconSampleLight_Previews: PreviewProvider {
    static var previews: some View {
        IconSample()
    }
}

internal struct IconSampleDark_Previews: PreviewProvider {
    static var previews: some View {
        IconSample()
            .preferredColorScheme(.dark)
    }
}
