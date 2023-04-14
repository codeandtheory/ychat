//
//  CompletionsViewModel.swift
//  Y-Chat
//
//  Created by Koji Osugi on 11/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import YChat

internal class CompletionsViewModel: ObservableObject {
    @Published
    var input: String = ""
    
    @Published
    var isLoading: Bool = false
    
    @Published
    var outputBoxStates: [OutputState] = []
    
    private var yChat: YChat {
        YChatCompanion.shared.create(apiKey: Config.apiKey)
    }
    
    @MainActor
    func requestCompletions() {
        let completions = yChat.completion()
            .setInput(input: input)
        outputBoxStates = []
        outputBoxStates.append(OutputState.text(text: input))
        setLoading(isLoading: true)
        Task.init {
            do {
                let result = try await completions.execute()
                setLoading(isLoading: false)
                outputBoxStates.append(OutputState.text(text: result, isMarked: true))
            } catch {
                setLoading(isLoading: false)
                outputBoxStates.append(OutputState.error)
            }
        }
    }
    
    private func setLoading(isLoading: Bool) {
        self.isLoading = isLoading
        if isLoading {
            outputBoxStates.append(OutputState.loading)
        } else {
            outputBoxStates.removeAll { $0 == .loading }
        }
    }
}
