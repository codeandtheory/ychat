//
//  LeaseSummaryView.swift
//  Y-Chat
//
//  Created by Koji Osugi on 19/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct LeaseSummaryView: View {
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text("Lease Agreement Summary")
                .font(.system(size: 12))
                .foregroundColor(Color(hex: 0x7A7A7A))
                .fontWeight(.medium)
            Text("2023 RAV4 Prime")
                .font(.system(size: 20))
                .foregroundColor(.text1)
                .fontWeight(.bold)
                .padding(.top, 8)
            Text("4WD SE L4 CVT")
                .font(.system(size: 12))
                .foregroundColor(.text1)
                .fontWeight(.regular)
                .padding(.top, 2)
            summaryDetails()
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
            .padding(.top, 32)
        }
        .padding(.top, 16)
        .padding(.horizontal, 16)
        .padding(.bottom, 8)
        .background(Color.white)
        .cornerRadius(24)
        .shadow(radius: 8)
    }
    
    @ViewBuilder
    private func summaryDetails() -> some View {
        VStack(alignment: .leading, spacing: 0) {
            Text("36 month lease")
                .font(.system(size: 16  ))
                .foregroundColor(.text1)
                .fontWeight(.semibold)
                .padding(.top, 32)
            
            Text("$355 monthly payment")
                .font(.system(size: 16))
                .foregroundColor(.text1)
                .fontWeight(.semibold)
                .padding(.top, 16)
            
            Text("$3,999 down payment")
                .font(.system(size: 16))
                .foregroundColor(.text1)
                .fontWeight(.semibold)
                .padding(.top, 16)
            
            Text("Due at signing")
                .font(.system(size: 10))
                .foregroundColor(Color(hex: 0x7A7A7A))
                .fontWeight(.regular)
                .padding(.top, 2)
            
            Text("12,000 miles per year")
                .font(.system(size: 16))
                .foregroundColor(.text1)
                .fontWeight(.semibold)
                .padding(.top, 16)
            
            Text("Maximum mileage")
                .font(.system(size: 10))
                .foregroundColor(Color(hex: 0x7A7A7A))
                .fontWeight(.regular)
                .padding(.top, 2)
            
            Text("*Lease excludes tax, title, license, registration fees and dealer options and charges.")
                .font(.system(size: 10))
                .foregroundColor(.text1)
                .fontWeight(.medium)
                .padding(.top, 16)
        }
    }
}

struct LeaseSummaryView_Previews: PreviewProvider {
    static var previews: some View {
        LeaseSummaryView()
    }
}
