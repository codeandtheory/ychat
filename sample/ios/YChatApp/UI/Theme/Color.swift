//
//  Color.swift
//  ios-sample
//
//  Created by Koji Osugi on 21/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

extension Color {
    static var text1: Color {
        Color("Text1")
    }
    
    static var text2: Color {
        Color("Text2")
    }
    
    static var text3: Color {
        Color("Text3")
    }
    
    static var text4: Color {
        Color("Text4")
    }
    
    static var background: Color {
        Color("Background")
    }
    
    static var icon: Color {
        Color("Icon")
    }
    
    static var divider: Color {
        Color("Divider")
    }
    
    static var accent: Color {
        Color("Accent")
    }
    
    static var onAccent: Color {
        Color("OnAccent")
    }
    
    static var accentLight: Color {
        Color("AccentLight")
    }
    
    static var primary1: Color {
        Color("Primary1")
    }
    
    static var primary2: Color {
        Color("Primary2")
    }
    
    static var primary3: Color {
        Color("Primary3")
    }
    
    static var primary4: Color {
        Color("Primary4")
    }
    
    static var primary5: Color {
        Color("Primary5")
    }
}

private struct ColorSample: View {
    var body: some View {
        ScrollView {
            VStack(spacing: 0) {
                textColors()
                otherColors()
                accentColors()
                primaryColors()
            }
        }
    }
    
    private func textColors() -> some View {
        Group {
            Text("text1")
                .applyStyle(.text1, .onAccent)
            Text("text2")
                .applyStyle(.text2, .onAccent)
            Text("text3")
                .applyStyle(.text3, .onAccent)
            Text("text4")
                .applyStyle(.text4, .accent)
        }
    }
    
    private func otherColors() -> some View {
        Group {
            Text("background")
                .applyStyle(.background, .accent)
            Text("icon")
                .applyStyle(.icon, .onAccent)
            Text("divider")
                .applyStyle(.divider, .accent)
        }
    }
    
    private func accentColors() -> some View {
        Group {
            Text("accent")
                .applyStyle(.accent, .onAccent)
            Text("onAccent")
                .applyStyle(.onAccent, .accent)
            Text("accentLight")
                .applyStyle(.accentLight, .accent)
        }
    }
    
    private func primaryColors() -> some View {
        Group {
            Text("primary1")
                .applyStyle(.primary1, .onAccent)
            Text("primary2")
                .applyStyle(.primary2, .onAccent)
            Text("primary3")
                .applyStyle(.primary3, .accent)
            Text("primary4")
                .applyStyle(.primary4, .accent)
            Text("primary5")
                .applyStyle(.primary5, .accent)
        }
    }
}

private extension Text {
    func applyStyle(_ background: Color, _ foregroundColor: Color) -> some View {
        self.frame(maxWidth: .infinity)
            .padding()
            .border(.black, width: 0.5)
            .background(background)
            .foregroundColor(foregroundColor)
    }
}

struct ColorLightSample_Previews: PreviewProvider {
    static var previews: some View {
        ColorSample()
    }
}

struct ColorDarkSample_Previews: PreviewProvider {
    static var previews: some View {
        ColorSample()
            .preferredColorScheme(.dark)
    }
}
