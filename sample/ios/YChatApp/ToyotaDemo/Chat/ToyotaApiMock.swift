//
//  ToyotaApiMock.swift
//  Y-Chat
//
//  Created by Koji Osugi on 19/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

class ToyotaApiMock {
    @MainActor
    func sendMessage(message: String) async throws -> String {
        try await Task.sleep(until: .now + .seconds(3), clock: .continuous)
        if message == "I’m not quite sure if buying or leasing would be a better option for me. Can you help me understand which makes more sense?" {
            return "Of course! There are pros and cons to both buying and leasing a vehicle, and the best option for you will depend on your personal circumstances and preferences. If you'd like, I can provide you with more information about the differences between the two and help you weigh the pros and cons. SHOW_BUYING_LEASING"
        }
        if message == "It seems like leasing is going to be a better option for me right now." {
            return "Great, let me know what your budget is and any other details that can help me find the perfect vehicle for you."
        }
        if message == "I have a budget of $350/month and I have a family of 4. We will be using the vehicle to go camping but I also need a good commuter vehicle for driving in the city." {
            return "Based on your budget, family size, and lifestyle needs, I would recommend looking at midsize SUVs or minivans for your leasing options. Check out these recommendations! SHOW_VEHICLE_LIST"
        }
        if message == "I like the RAV 4 Prime. Can you give me details on the lease?" {
            return "Take a look at some of the basics when it comes to leasing this car. SHOW_LEASE_SUMMARY"
        }
        if message == "Can I see this car in person near me?" {
            return "Yes, you can check if there are any 2023 RAV4 Prime vehicles available to test drive in person by visiting the Toyota website and using the \"Find a Dealer\" tool. Just enter your zip code and select the RAV4 Prime as the model you're interested in, and it will show you the nearest Toyota dealerships that have the vehicle in stock. Here’s a QR code you can take to the dealer so they can easily see all of your saved preferences and needs. SHOW_QR_CODE"
        }
        return "this is a test."
    }
}
