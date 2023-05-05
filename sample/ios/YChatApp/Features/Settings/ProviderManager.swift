//
//  ProviderManager.swift
//  Y-Chat
//
//  Created by Koji Osugi on 04/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import YChat

internal class ProviderManager: ObservableObject {
    static let shared = ProviderManager()
    
    @Published
    var providers: [Providers] = []
    
    @Published
    var selectedProvider: Providers = .openAi
    
    private let providerKey = "selected_provider"
    
    private init() {
        fetchSelectedProvider()
        fetchProviders()
    }
    
    func fetchProviders() {
        var providers = Providers.allCases
        providers.placeFirst(where: { $0 == selectedProvider })
        self.providers = providers
    }
    
    func setSelectedProvider(providers: Providers) {
        UserDefaults.instance.put(key: providerKey, value: providers.rawValue)
        fetchSelectedProvider()
        fetchProviders()
    }
    
    func fetchSelectedProvider() {
        let providerString = UserDefaults
            .instance
            .getString(key: providerKey)
        self.selectedProvider = Providers.init(rawValue: providerString) ?? .openAi
    }
    
    enum Providers: String, CaseIterable {
        case openAi
        case ducAi
        
        var name: String {
            switch self {
            case .openAi: return "OpenAI"
            case .ducAi: return "DucAI"
            }
        }
    }
}

extension RangeReplaceableCollection {
    mutating func placeFirst(where predicate: (Iterator.Element) -> Bool) {
        if let index = firstIndex(where: predicate) {
            insert(remove(at: index), at: startIndex)
        }
    }
}
