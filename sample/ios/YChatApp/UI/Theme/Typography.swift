//
//  Typography.swift
//  ios-sample
//
//  Created by Koji Osugi on 21/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

enum Typography {
    case displayTitle
    case displayBody
    case largeTitle
    case largeBody
    case mediumTitle
    case mediumBody
    case smallTitle
    case smallBody
    case extraSmallTitle
    case extraSmallBody
}

extension Text {
    func style(_ typography: Typography) -> Text {
        switch typography {
        case .displayTitle: return displayTitle()
        case .displayBody: return displayBody()
        case .largeTitle: return largeTitle()
        case .largeBody: return largeBody()
        case .mediumTitle: return mediumTitle()
        case .mediumBody: return mediumBody()
        case .smallTitle: return smallTitle()
        case .smallBody: return smallBody()
        case .extraSmallTitle: return extraSmallTitle()
        case .extraSmallBody: return extraSmallBody()
        }
    }

    private func displayTitle() -> Text {
        self.font(.system(size: 19))
            .foregroundColor(.text1)
            .fontWeight(.medium)
    }

    private func displayBody() -> Text {
        self.font(.system(size: 19))
            .foregroundColor(.text1)
            .fontWeight(.light)
    }

    private func largeTitle() -> Text {
        self.font(.system(size: 17))
            .foregroundColor(.text1)
            .fontWeight(.medium)
    }

    private func largeBody() -> Text {
        self.font(.system(size: 17))
            .foregroundColor(.text1)
            .fontWeight(.light)
    }

    private func mediumTitle() -> Text {
        self.font(.system(size: 15))
            .foregroundColor(.text1)
            .fontWeight(.bold)
    }

    private func mediumBody() -> Text {
        self.font(.system(size: 14))
            .foregroundColor(.text1)
            .fontWeight(.regular)
    }

    private func smallTitle() -> Text {
        self.font(.system(size: 13))
            .foregroundColor(.text1)
            .fontWeight(.bold)
    }

    private func smallBody() -> Text {
        self.font(.system(size: 13))
            .foregroundColor(.text1)
            .fontWeight(.regular)
    }
    
    private func extraSmallTitle() -> Text {
        self.font(.system(size: 11))
            .foregroundColor(.text1)
            .fontWeight(.bold)
    }

    private func extraSmallBody() -> Text {
        self.font(.system(size: 11))
            .foregroundColor(.text1)
            .fontWeight(.regular)
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
                Text("displayTitle")
                    .style(.displayTitle)
                Text("displayBody")
                    .style(.displayBody)
                Text("largeTitle")
                    .style(.largeTitle)
                Text("largeBody")
                    .style(.largeBody)
                Text("mediumTitle")
                    .style(.mediumTitle)
                Text("mediumBody")
                    .style(.mediumBody)
                Text("smallTitle")
                    .style(.smallTitle)
                Text("smallBody")
                    .style(.smallBody)
                Text("extraSmallTitle")
                    .style(.extraSmallTitle)
                Text("extraSmallBody")
                    .style(.extraSmallBody)
            }
        }
    }
}
