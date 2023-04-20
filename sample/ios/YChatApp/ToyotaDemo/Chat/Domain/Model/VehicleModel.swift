//
//  VehicleModel.swift
//  Y-Chat
//
//  Created by Koji Osugi on 18/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

struct VehicleModel: Identifiable {
    let id: String = UUID().uuidString
    let image: String
    let year: String
    let model: String
    let description: String
    let mpg: String
    let leasingPrice: String
    let leasingMonths: String
    let engineType: EngineType
    
    enum EngineType: String {
        case fuel, hybrid, electric
    }
    
    static func generateMock() -> [VehicleModel] {
        [generateSienna(), generateHighlander()]
    }
    
    static func generateRav4() -> VehicleModel {
        VehicleModel(
            image: "image_rav4_prime",
            year: "2023",
            model: "RAV4 Prime",
            description: "The RAV4 Prime is a brand new hybrid midsize SUV with great reviews.",
            mpg: "48/42",
            leasingPrice: "$350",
            leasingMonths: "36",
            engineType: .hybrid
        )
    }
    
    static func generateBZ4X() -> VehicleModel {
        VehicleModel(
            image: "image_bz4x",
            year: "2023",
            model: "bZ4X",
            description: "The bZ4X is a fully electric crossover that has a stylish, design and spacious interior.",
            mpg: "55/50",
            leasingPrice: "$380",
            leasingMonths: "36",
            engineType: .electric
        )
    }
    
    static func generateSienna() -> VehicleModel {
        VehicleModel(
            image: "image_sienna",
            year: "2023",
            model: "Sienna",
            description: "This minivan has plenty of space for your family and all of their gear.",
            mpg: "41/38",
            leasingPrice: "$290",
            leasingMonths: "36",
            engineType: .fuel
        )
    }
    
    static func generateHighlander() -> VehicleModel {
        VehicleModel(
            image: "image_highlander",
            year: "2023",
            model: "Highlander",
            description: "The Highlander Hybrid is a midsize electric hybrid SUV with tons of seating.",
            mpg: "48/43",
            leasingPrice: "$375",
            leasingMonths: "36",
            engineType: .hybrid
        )
    }
}
