//
//  TypeWriterText.swift
//  Y-Chat
//
//  Created by Koji Osugi on 17/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct TypeWriterText: View {
    @State
    private var text: String = ""
    @Binding
    private var hasFinished: Bool
    private var finalText: String = ""
    
    init(
        _ finalText: String,
        hasFinished: Binding<Bool> = .constant(false)
    ) {
        self._hasFinished = hasFinished
        self.finalText = finalText
    }
    
    var body: some View {
        Text(.init(text))
            .foregroundColor(.text1)
            .style(.mediumBody)
            .multilineTextAlignment(.leading)
            .onAppear { typeWriter() }
    }
    
    func typeWriter(at position: Int = 0) {
        if position == 0 {
            text = ""
        }
        if position < finalText.count {
            DispatchQueue.main.asyncAfter(deadline: .now() + 0.03) {
                text.append(finalText[position])
                typeWriter(at: position + 1)
            }
        } else {
            hasFinished = true
        }
    }
}

class TypeWriter {
    private var finalText: String = ""
    private var lastReleasedWord: String = ""
    
    func setText(_ finalText: String) {
        self.finalText = finalText
    }
    
    func typeWriter(
        at position: Int = 0,
        _ onResult: @escaping (String) -> Void
    ) {
        if position < finalText.count {
            let releaseWord = self.finalText[position]
            DispatchQueue.main.asyncAfter(deadline: .now() + 0.03) {
                let newWord = String(self.lastReleasedWord) + String(releaseWord)
                onResult(newWord)
                self.lastReleasedWord = newWord
                self.typeWriter(at: position + 1, onResult)
            }
        }
    }
}

struct TypeWriterText_Previews: PreviewProvider {
    static var previews: some View {
        TypeWriterText("Hello world")
    }
}
