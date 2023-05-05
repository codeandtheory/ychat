//
//  CacheManager.swift
//  Y-Chat
//
//  Created by Koji Osugi on 04/05/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

extension UserDefaults {
    static var instance: UserDefaults = .standard
    
    func put(key: String, value: String) {
        UserDefaults.instance.set(value, forKey: key)
    }
    
    func put<T: Codable>(key: String, value: T) {
        let encoder = JSONEncoder()
        guard let jsonData = try? encoder.encode(value) else { return }
        let jsonString = String(data: jsonData, encoding: .utf8)
        UserDefaults.instance.set(jsonString, forKey: key)
    }
    
    func getString(key: String, defaultValue: String = "") -> String {
        UserDefaults.instance.string(forKey: key) ?? defaultValue
    }
    
    func getObject<T: Codable>(_ type: T.Type, key: String) -> T? {
        let decoder = JSONDecoder()
        guard let jsonString = UserDefaults.instance.string(forKey: key) else { return nil }
        guard let data = jsonString.data(using: .utf8) else { return nil }
        return try? decoder.decode(type, from: data)
    }
}
