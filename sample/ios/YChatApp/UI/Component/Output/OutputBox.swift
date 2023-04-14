//
//  OutputBox.swift
//  Y-Chat
//
//  Created by Koji Osugi on 11/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

enum OutputState: Hashable {
    case text(text: String, isMarked: Bool = false)
    case error
    case loading
}

struct OutputBox: View {
    var states: [OutputState]
    
    var body: some View {
        VStack(alignment: .leading, spacing: 16) {
            ForEach(states, id: \.self) {
                switch $0 {
                case .error:
                    errorState()
                case .loading:
                    TypingLoading()
                case .text(let text, let isMarked):
                    textRow(text: text, isMarked: isMarked)
                }
            }
        }
        .frame(minHeight: 200, alignment: .top)
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding(.horizontal, 16)
        .padding(.vertical, 20)
        .background(Color.primary5)
        .cornerRadius(8)
        .overlay(
            RoundedRectangle(cornerRadius: 8)
                .stroke(Color.primary4, lineWidth: 1)
        )
    }
    
    @ViewBuilder
    private func textRow(text: String, isMarked: Bool) -> some View {
        if isMarked {
            Text(text)
                .foregroundColor(Color.black)
                .style(.mediumBody)
                .background(Color.green2)
        } else {
            Text(text)
                .foregroundColor(Color.text1)
                .style(.mediumBody)
        }
    }
    
    @ViewBuilder
    private func errorState() -> some View {
        HStack(spacing: 8) {
            Icon.warning.image(.red, size: 16)
            Text("Something went wrong. Try again.")
                .style(.mediumBody)
        }
    }
}

struct OutputBox_Previews: PreviewProvider {
    static var previews: some View {
        let states = [
            OutputState.text(
                text: "Write a tagline for an ice cream shop."
            ),
            OutputState.loading,
            OutputState.text(
                text: "We serve up smiles with every scoop!",
                isMarked: true
            ),
            OutputState.error
        ]
        OutputBox(states: states)
            .padding(.horizontal, 16)
    }
}
