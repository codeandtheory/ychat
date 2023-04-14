//
//  ItemMenu.swift
//  Y-Chat
//
//  Created by Koji Osugi on 11/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ItemMenu: View {
    var startText: String
    var caption: String
    var isDividerVisible: Bool = true
    
    var body: some View {
        VStack {
            VStack(alignment: .leading, spacing: 4) {
                Text(startText)
                    .style(.mediumBody)
                Text(caption)
                    .foregroundColor(Color.text2)
                    .style(.smallBody)
            }
            .padding(.horizontal, 16)
            .padding(.vertical, 14)
            .frame(maxWidth: .infinity, alignment: .leading)
            if isDividerVisible {
                Divider().padding(.horizontal, 16)
            }
        }
    }
}

struct ItemMenu_Previews: PreviewProvider {
    static var previews: some View {
        VStack(spacing: 0) {
            ItemMenu(
                startText: "Line 1",
                caption: "Caption 1"
            )
            ItemMenu(
                startText: "Line 2",
                caption: "Caption 2"
            )
            ItemMenu(
                startText: "Line 3",
                caption: "Caption 3",
                isDividerVisible: false
            )
        }
    }
}
