//
//  FitHomeView.swift
//  ios-sample
//
//  Created by Koji Osugi on 31/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

internal struct FitHomeView: View {
    
    @State
    private var moveToFitChatView = false
    
    private var controller = FitMainView.Controller.shared
    
    var body: some View {
        NavigationStack {
            VStack {
                TopContent()
                Spacer()
                BottomContent()
                    .padding(.bottom, 24)
            }
            .background { Image(uiImage: .fitHome) }
            .navigationDestination(isPresented: $moveToFitChatView) {
                FitChatView()
                    .navigationBarBackButtonHidden(true)
                    .navigationBarHidden(true)
                    .onAppear { controller.hideTabView = true }
                    .onDisappear { controller.hideTabView = false }
            }
        }
    }
    
    @ViewBuilder
    private func TopContent() -> some View {
        ZStack {
            HStack(spacing: 0) {
                Spacer()
                Circle()
                    .stroke(Color.white)
                    .frame(width: 40, height: 40)
                    .overlay {
                        Image(uiImage: .person)
                            .renderingMode(.template)
                            .foregroundColor(.white)
                    }
                    .padding(.horizontal, 16)
            }
            FitLogo(size: 20)
        }
    }
    
    @ViewBuilder
    private func BottomContent() -> some View {
        VStack(alignment: .leading, spacing: 16) {
            Image(uiImage: .chat)
                .resizable()
                .renderingMode(.template)
                .foregroundColor(.white)
                .frame(width: 32, height: 32)
            Text("A personal AI fitness coach for you.")
                .font(.system(size: 48))
                .foregroundColor(.white)
                .fontWeight(.bold)
            Text("Get workout routines, meal plans, and anything else you need advice on. Just ask the chat GPT fitness coach!")
                .font(.system(size: 16))
                .foregroundColor(.white)
                .fontWeight(.regular)
            Button(action: { moveToFitChatView.toggle() }) {
                Spacer()
                HStack(spacing: 14) {
                    Image(uiImage: .chat)
                    Text("Ask your AI coach")
                        .foregroundColor(.grayDark)
                        .style(.button)
                }
                Spacer()
            }
            .padding()
            .background(Color.primaryExtraLight)
            .clipShape(RoundedCorner())
            .padding(.top, 4)
        }
        .padding(.horizontal, 32)
    }
}

internal struct FitHomeView_Previews: PreviewProvider {
    static var previews: some View {
        FitHomeView()
    }
}
