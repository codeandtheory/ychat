//
//  Typography.swift
//  ios-sample
//
//  Created by Koji Osugi on 21/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

enum Typography {
    case title
    case titleBold
    case subtitle
    case subtitleBold
    case body
    case bodyBold
    case button
    case caption
}

extension Text {
    func style(_ typography: Typography) -> Text {
        switch typography {
        case .title: return title()
        case .titleBold: return titleBold()
        case .subtitle: return subtitle()
        case .subtitleBold: return subtitleBold()
        case .body: return body()
        case .bodyBold: return bodyBold()
        case .button: return button()
        case .caption: return caption()
        }
    }

    private func title() -> Text {
        self.font(.system(size: 18))
            .foregroundColor(.grayDark)
    }

    private func titleBold() -> Text {
        self.title()
            .bold()
    }

    private func subtitle() -> Text {
        self.font(.system(size: 16))
            .foregroundColor(.grayDark)
    }

    private func subtitleBold() -> Text {
        self.subtitle()
            .bold()
    }

    private func body() -> Text {
        self.font(.system(size: 14))
            .foregroundColor(.grayDark)
    }

    private func bodyBold() -> Text {
        self.body()
            .bold()
    }

    private func button() -> Text {
        self.font(.system(size: 14))
            .foregroundColor(.grayDark)
    }

    private func caption() -> Text {
        self.font(.system(size: 12))
            .foregroundColor(.grayDark)
    }
}

private struct TypographySample: View {
    var body: some View {
        Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
    }
}

internal struct Tipography_Previews: PreviewProvider {
    static var previews: some View {
        ScrollView {
            VStack(spacing: 16) {
                Text("title")
                    .style(.title)
                Text("titleBold")
                    .style(.titleBold)
                Text("subtitle")
                    .style(.subtitle)
                Text("subtitleBold")
                    .style(.subtitleBold)
                Text("body")
                    .style(.body)
                Text("bodyBold")
                    .style(.bodyBold)
                Text("button")
                    .style(.button)
                Text("caption")
                    .style(.caption)
            }
        }
    }
}
