//
//  BallonBotMessageFactory.swift
//  Y-Chat
//
//  Created by Koji Osugi on 19/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct BallonBotMessageFactory: View {
    enum Intents: String, CaseIterable {
        case showQrCode = "SHOW_QR_CODE"
        case showBuyingLeasing = "SHOW_BUYING_LEASING"
        case showVehicleList = "SHOW_VEHICLE_LIST"
        case showLeaseSummary = "SHOW_LEASE_SUMMARY"
    }
    
    var message: String
    private var intents: [Intents] = []
    @State
    private var hasFinished: Bool = false
    private let vehicles = [
        VehicleModel.generateRav4(), VehicleModel.generateBZ4X(),
        VehicleModel.generateSienna(), VehicleModel.generateHighlander()
    ]
    
    init(message: String) {
        self.message = message
        var newMessage = message
        Intents.allCases.forEach {
            if let range = newMessage.range(of: $0.rawValue) {
                newMessage = newMessage.replacingOccurrences(of: $0.rawValue, with: "", range: range)
                self.intents.append($0)
            }
        }
        self.message = newMessage
    }
    
    var body: some View {
        VStack(spacing: 16) {
            BallonBotMessage(message, hasFinished: $hasFinished)
                .padding(.horizontal, 24)
            if hasFinished {
                ForEach(intents, id: \.self) {
                    switch $0 {
                    case .showQrCode: BallonQrCode()
                            .padding(.horizontal, 24)
                            .padding(.bottom, 4)
                    case .showBuyingLeasing: BuyingLeasingCard()
                            .padding(.horizontal, 24)
                            .padding(.bottom, 4)
                    case .showVehicleList:
                        VehicleCardListView(vehicles: vehicles)
                            .padding(.bottom, 4)
                    case .showLeaseSummary:
                        LeaseSummaryView()
                            .padding(.horizontal, 24)
                            .padding(.bottom, 4)
                    }
                }
            }
        }
    }
}

struct BallonBotMessageFactory_Previews: PreviewProvider {
    static var previews: some View {
        let message = "This is a test. SHOW_QR_CODE. SHOW_VEHICLE_LIST"
        BallonBotMessageFactory(message: message)
    }
}
