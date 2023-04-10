//
//  SideMenu.swift
//  ios-sample
//
//  Created by Koji Osugi on 22/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SideMenu<SidebarContent: View>: View {
    @Binding private var isVisible: Bool
    private let width: CGFloat
    private let bgColor: Color
    private let content: SidebarContent
    
    init(
        isVisible: Binding<Bool>,
        width: CGFloat = UIScreen.main.bounds.size.width * 0.7,
        bgColor: Color = .background,
        @ViewBuilder content: () -> SidebarContent
    ) {
        self._isVisible = isVisible
        self.width = width
        self.bgColor = bgColor
        self.content = content()
    }
    
    var body: some View {
        ZStack {
            GeometryReader { _ in
                EmptyView()
            }
            .background(.black.opacity(0.6))
            .opacity(isVisible ? 1 : 0)
            .animation(.easeInOut.delay(0.2), value: isVisible)
            .onTapGesture { isVisible.toggle() }
            sideMenuStructure()
        }
    }
    
    @ViewBuilder
    private func sideMenuStructure() -> some View {
        GeometryReader { proxy in
            HStack(alignment: .top) {
                ZStack(alignment: .top) {
                    bgColor
                    VStack {
                        Spacer().frame(height: 1)
                        content.padding(.top, proxy.safeAreaInsets.top)
                    }
                }
                .frame(width: width)
                .offset(x: isVisible ? 0 : -width)
                .animation(.default, value: isVisible)
                Spacer()
            }
            .edgesIgnoringSafeArea(.all)
        }
    }
}

struct SideMenu_Previews: PreviewProvider {
    static var previews: some View {
        SideMenu(isVisible: .constant(true)) {
            Text("Side Menu")
        }
    }
}
