//
//  VehicleCardView.swift
//  Y-Chat
//
//  Created by Koji Osugi on 18/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import ACarousel

struct VehicleCardView: View {
    var vehicleModel: VehicleModel
    
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            Image(vehicleModel.image)
                .frame(maxWidth: .infinity)
                .clipped()
                .offset(x: 40)
                .overlay(alignment: .bottomLeading) {
                    if vehicleModel.engineType != .fuel {
                        Text(vehicleModel.engineType.rawValue.uppercased())
                            .foregroundColor(.white)
                            .font(.system(size: 10))
                            .fontWeight(.bold)
                            .padding(.horizontal, 8)
                            .padding(.vertical, 4)
                            .background(Color(hex: 0x238BF3))
                            .cornerRadius(4)
                            .padding(.horizontal, 16)
                    }
                }
            Text(vehicleModel.year + " " + vehicleModel.model)
                .font(.system(size: 16))
                .foregroundColor(.text1)
                .fontWeight(.black)
                .padding(.vertical, 8)
                .padding(.horizontal, 16)
            Text(vehicleModel.description)
                .font(.system(size: 12))
                .foregroundColor(.text1)
                .fontWeight(.regular)
                .multilineTextAlignment(.leading)
                .fixedSize(horizontal: false, vertical: true)
                .padding(.horizontal, 16)
            Group {
                Text(vehicleModel.mpg)
                    .font(.system(size: 14))
                    .foregroundColor(.text1)
                    .fontWeight(.semibold) +
                Text(" Est. MPG")
                    .font(.system(size: 10))
                    .foregroundColor(Color(hex: 0x7A7A7A))
                    .fontWeight(.regular)
            }
            .padding(.vertical, 16)
            .padding(.horizontal, 16)
            Text("starting at")
                .font(.system(size: 10))
                .foregroundColor(Color(hex: 0x7A7A7A))
                .fontWeight(.regular)
                .padding(.horizontal, 16)
            Group {
                Text(vehicleModel.leasingPrice)
                    .font(.system(size: 14))
                    .foregroundColor(.text1)
                    .fontWeight(.semibold) +
                Text(" monthly")
                    .font(.system(size: 10))
                    .foregroundColor(Color(hex: 0x7A7A7A))
                    .fontWeight(.regular)
            }
            .padding(.bottom, 8)
            .padding(.horizontal, 16)
            Group {
                Text(vehicleModel.leasingMonths)
                    .font(.system(size: 12))
                    .foregroundColor(.text1)
                    .fontWeight(.semibold) +
                Text(" Month Lease Term")
                    .font(.system(size: 12))
                    .foregroundColor(.text1)
                    .fontWeight(.regular)
            }
            .padding(8)
            .background(Color(hex: 0xEFEFEF))
            .cornerRadius(4)
            .padding(.horizontal, 16)
            .padding(.bottom, 12)
        }
        .applyCardStyle()
    }
}

struct VehicleCardListView: View {
    var vehicles: [VehicleModel]
    @State
    private var currentIndex: Int = 0
    
    var body: some View {
        VStack(spacing: 16) {
            ACarousel(
                vehicles,
                index: $currentIndex,
                spacing: 60,
                headspace: 56,
                sidesScaling: 1,
                isWrap: false
            ) {
                VehicleCardView(vehicleModel: $0)
                    .offset(x: -70)
                    .frame(width: 200)
            }
            .frame(height: 330)
            HStack(spacing: 8) {
                ForEach(vehicles.indices, id: \.self) { index in
                    Circle()
                        .fill(Color.black.opacity(currentIndex == index ? 1 : 0.1))
                        .frame(width: 8, height: 8)
                        .scaleEffect(currentIndex == index ? 1.4 : 1)
                        .animation(.spring(), value: currentIndex == index)
                }
            }
        }
    }
}

struct VehicleCardView_Previews: PreviewProvider {
    static var previews: some View {
        let cars = VehicleModel.generateMock() + VehicleModel.generateMock()
        VStack {
            VehicleCardListView(vehicles: cars)
        }
    }
}
