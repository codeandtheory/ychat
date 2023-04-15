//
//  BuyingLeasingView.swift
//  Y-Chat
//
//  Created by Koji Osugi on 15/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct BuyingLeasingCard: View {
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text("Things to Know About Buying vs Leasing")
                .font(.system(size: 20))
                .foregroundColor(.text1)
                .fontWeight(.bold)
            Text("Benefits of Buying")
                .font(.system(size: 12))
                .foregroundColor(.text1)
                .fontWeight(.semibold)
                .padding(.top, 24)
            Text("Buying a vehicle provides ownership and long-term savings compared to leasing, without mileage limits or restrictions on customization.")
                .font(.system(size: 12))
                .foregroundColor(.text1)
                .fontWeight(.light)
                .padding(.top, 8)
            Text("Benefits of Leasing")
                .font(.system(size: 12))
                .foregroundColor(.text1)
                .fontWeight(.semibold)
                .padding(.top, 16)
            Text("Leasing a vehicle offers lower upfront costs, lower monthly payments, and the potential for fewer repair costs, with the ability to upgrade to a newer car more frequently.")
                .font(.system(size: 12))
                .foregroundColor(.text1)
                .fontWeight(.light)
                .padding(.top, 8)
                .padding(.bottom, 24)
            Button(action: { }) {
                Text("Learn More")
                    .foregroundColor(.onAccent)
                    .font(.system(size: 16))
                    .foregroundColor(.text1)
                    .fontWeight(.bold)
                    .frame(minWidth: 0, maxWidth: .infinity)
                    .padding()
            }
            .background(Color.accent)
            .cornerRadius(16)
        }
        .padding(.top, 16)
        .padding(.horizontal, 16)
        .padding(.bottom, 8)
        .background(Color.white)
        .cornerRadius(24)
        .shadow(radius: 8)
    }
}

struct BuyingLeasingView_Previews: PreviewProvider {
    static var previews: some View {
        BuyingLeasingCard()
    }
}
