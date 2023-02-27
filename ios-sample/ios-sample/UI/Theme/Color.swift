//
//  Color.swift
//  ios-sample
//
//  Created by Koji Osugi on 21/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

extension Color {
    static var background: Color {
        Color.white
    }

    static var accentMain: Color {
        Color("AccentMain")
    }

    static var primaryDark: Color {
        Color("PrimaryDark")
    }

    static var primaryMain: Color {
        Color("PrimaryMain")
    }

    static var primaryMedium: Color {
        Color("PrimaryMedium")
    }

    static var primaryLight: Color {
        Color("PrimaryLight")
    }

    static var primaryExtraLight: Color {
        Color("PrimaryExtraLight")
    }

    static var grayDark: Color {
        Color("GrayDark")
    }

    static var grayMain: Color {
        Color("GrayMain")
    }

    static var grayMedium: Color {
        Color("GrayMedium")
    }

    static var grayLight: Color {
        Color("GrayLight")
    }

    static var grayExtraLight: Color {
        Color("GrayExtraLight")
    }
}

private struct ColorSample: View {
    var body: some View {
        ScrollView {
            VStack(spacing: 0) {
                primaryColors()
                grayColors()
            }
        }
    }

    private func primaryColors() -> some View {
        Group {
            Text("accentMain")
                .applyStyle(Color.accentMain)
            Text("primaryDark")
                .applyStyle(Color.primaryDark)
            Text("primaryMain")
                .applyStyle(Color.primaryMain)
            Text("primaryMedium")
                .applyStyle(Color.primaryMedium)
            Text("primaryLight")
                .applyStyle(Color.primaryLight)
            Text("primaryExtraLight")
                .applyStyle(Color.primaryExtraLight)
        }
    }

    private func grayColors() -> some View {
        Group {
            Text("grayDark")
                .applyStyle(Color.grayDark)
            Text("grayMain")
                .applyStyle(Color.grayMain)
            Text("grayMedium")
                .applyStyle(Color.grayMedium)
            Text("grayLight")
                .applyStyle(Color.grayLight)
            Text("grayExtraLight")
                .applyStyle(Color.grayExtraLight)
        }
    }
}

private extension Text {
    func applyStyle(_ color: Color) -> some View {
        self.frame(maxWidth: .infinity)
        .padding()
        .border(.black, width: 0.5)
        .background(color)
    }
}

struct ColorSample_Previews: PreviewProvider {
    static var previews: some View {
        ColorSample()
    }
}
