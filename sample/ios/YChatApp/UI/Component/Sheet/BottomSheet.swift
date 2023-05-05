//
//  BottomSheet.swift
//  Y-Chat
//
//  Created by Koji Osugi on 04/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct BottomSheet<SheetContent: View>: ViewModifier {
    @Binding private var show: Bool
    private let contentSheet: () -> SheetContent
    
    @GestureState private var translation: CGFloat = 0
    
    init(show: Binding<Bool>, @ViewBuilder content: @escaping () -> SheetContent) {
        _show = show
        self.contentSheet = content
    }
    
    private var offset: CGFloat {
        show ? 0 : UIScreen.main.bounds.height
    }
    
    private var overlay: some View {
        let view = show ? Color.primary4 : Color.primary4.opacity(0)
        return view.animation(.spring(), value: show)
    }
    
    func body(content: Content) -> some View {
        ZStack(alignment: .bottom) {
            content
                .overlay { overlay }
                .onTapGesture {
                    show = false
                }
            ZStack {
                VStack(spacing: 0) {
                    Divider()
                        .frame(width: 32, height: 4)
                        .background(Color.primary3)
                        .cornerRadius(8)
                        .padding(.top, 8)
                        .padding(.bottom, 32)
                    contentSheet()
                }
            }
            .padding(.horizontal, 16)
            .padding(.bottom, 16)
            .frame(maxWidth: .infinity)
            .background(Color.background)
            .cornerRadius(24, corners: [.topLeft, .topRight])
            .offset(y: offset + self.translation)
            .animation(.spring(), value: show)
            .gesture(
                DragGesture()
                    .updating(self.$translation) { value, state, _ in
                        state = value.translation.height
                    }
                    .onEnded { value in
                        self.show = value.translation.height < 0
                    }
            )
        }
    }
}

extension View {
    func bottomSheet<SheetContent: View>(
        show: Binding<Bool>,
        @ViewBuilder content: @escaping () -> SheetContent
    ) -> some View {
        self.modifier(BottomSheet(show: show, content: content))
    }
}
