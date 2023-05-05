//
//  ItemMenu.swift
//  Y-Chat
//
//  Created by Koji Osugi on 11/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ItemMenu: View {
    var startIcon: Icon?
    var startText: String
    var startCaption: String?
    var endCaption: String?
    var endIcon: Icon?
    var isDividerVisible: Bool = true
    var onClickItem: (() -> Void)?
    
    var body: some View {
        Button(action: { onClickItem?() }) {
            VStack {
                HStack(spacing: 0) {
                    if let startIcon = startIcon {
                        startIcon.image()
                    }
                    VStack(alignment: .leading, spacing: 4) {
                        Text(startText)
                            .style(.mediumBody)
                        if let startCaption = startCaption {
                            Text(startCaption)
                                .foregroundColor(Color.text2)
                                .style(.smallBody)
                        }
                    }.padding(.horizontal, 16)
                    Spacer()
                    if let endCaption = endCaption {
                        Text(endCaption)
                            .foregroundColor(Color.text2)
                            .style(.smallBody)
                    }
                    if let endIcon = endIcon {
                        endIcon.image()
                            .padding(.leading, 8)
                    }
                }
                .padding(.horizontal, 16)
                .padding(.vertical, 14)
                .frame(maxWidth: .infinity, alignment: .leading)
                if isDividerVisible {
                    Divider().padding(.horizontal, 16)
                }
            }
        }.disabled(onClickItem == nil)
    }
}

struct ItemMenu_Previews: PreviewProvider {
    static var previews: some View {
        VStack(spacing: 0) {
            ItemMenu(
                startIcon: .settings,
                startText: "Line 1",
                startCaption: "Caption 1"
            )
            ItemMenu(
                startText: "Line 2",
                startCaption: "Caption 2"
            )
            ItemMenu(
                startText: "Line 3",
                startCaption: "Caption 3",
                isDividerVisible: false
            )
            ItemMenu(
                startText: "Line 3",
                startCaption: "Caption 3",
                endCaption: "teste",
                endIcon: .model,
                isDividerVisible: false
            )
        }
    }
}
