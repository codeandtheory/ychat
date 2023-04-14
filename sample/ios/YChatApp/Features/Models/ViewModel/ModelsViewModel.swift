//
//  ModelsViewModel.swift
//  Y-Chat
//
//  Created by Koji Osugi on 11/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import YChat

internal class ModelsViewModel: ObservableObject {
    private var listModels: ListModels {
        YChatCompanion.shared.create(apiKey: Config.apiKey)
            .listModels()
    }
    
    @Published
    var state: State = .loading
    
    @MainActor
    func fetchListModels() {
        Task.init {
            state = .loading
            do {
                let result = try await listModels.execute()
                state = .success(result)
            } catch {
                state = .error
            }
        }
    }
    
    enum State {
        case success([AIModel])
        case loading
        case error
    }
}
