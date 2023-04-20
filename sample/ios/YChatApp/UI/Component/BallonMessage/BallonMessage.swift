//
//  BallonMessage.swift
//  ychat-ios
//
//  Created by Koji Osugi on 08/04/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct BallonSenderMessage: View {
    private var text: String
    
    private var isError = false
    
    init(_ text: String, isError: Bool = false) {
        self.text = text
        self.isError = isError
    }
    
    var body: some View {
        HStack(spacing: 4) {
            Spacer()
            Spacer().frame(width: 60)
            HStack(spacing: 8) {
                Text(.init(text))
                    .foregroundColor(.onAccent)
                    .style(.mediumBody)
                if isError {
                    Icon.warning.image(.red)
                }
            }
            .padding(.horizontal, 16)
            .padding(.vertical, 8)
            .background {
                LinearGradient(
                    gradient:
                        Gradient(colors: [Color(hex: 0x676767), Color(hex: 0x313131)]),
                    startPoint: .top,
                    endPoint: .bottom
                )
            }
            .cornerRadius(16, corners: [.bottomLeft, .topLeft, .topRight])
        }
    }
}

struct BallonBotMessage: View {
    private var text: String
    @Binding
    private var hasFinished: Bool
    
    init(
        _ text: String,
        hasFinished: Binding<Bool> = .constant(false)
    ) {
        self.text = text
        self._hasFinished = hasFinished
    }
    
    var body: some View {
        HStack {
            HStack(alignment: .top, spacing: 4) {
                Image("logo_toyota")
                    .cornerRadius(32)
                ZStack {
                    TypeWriterText(text, hasFinished: $hasFinished)
//                    Text(.init(text))
//                        .foregroundColor(.text1)
//                        .style(.mediumBody)
//                        .multilineTextAlignment(.leading)
                }
                .padding(.horizontal, 16)
                .padding(.vertical, 8)
                .background {
                    LinearGradient(
                        gradient:
                            Gradient(colors: [Color(hex: 0xE7E7E7), Color(hex: 0xDCDCDC)]),
                        startPoint: .top,
                        endPoint: .bottom
                    )
                }
                .cornerRadius(16, corners: [.bottomLeft, .bottomRight, .topRight])
            }
            Spacer().frame(width: 60)
            Spacer()
        }
    }
}

struct BallonQrCode: View {
    var body: some View {
        HStack {
            HStack(alignment: .top, spacing: 4) {
                Image("logo_toyota")
                    .cornerRadius(32)
                VStack {
                    ZStack {
                        Image("fake_qr_code")
                    }
                    .padding(16)
                    .background {
                        LinearGradient(
                            gradient:
                                Gradient(colors: [Color(hex: 0xE7E7E7), Color(hex: 0xDCDCDC)]),
                            startPoint: .top,
                            endPoint: .bottom
                        )
                    }
                    .cornerRadius(16, corners: [.bottomLeft, .bottomLeft, .topRight])
                    downloadQRCodeButton()
                }
            }
            Spacer().frame(width: 60)
            Spacer()
        }
    }
    
    @ViewBuilder
    private func downloadQRCodeButton() -> some View {
        Button(action: {
            guard let inputImage = UIImage(named: "fake_qr_code") else { return }
            let imageSaver = ImageSaver()
            imageSaver.writeToPhotoAlbum(image: inputImage)
        }) {
            Text("Save QR Code")
                .foregroundColor(Color(hex: 0x5EAEFF))
                .font(.system(size: 16))
                .fontWeight(.bold)
        }
    }
}

struct BallonTyping: View {
    var body: some View {
        HStack {
            Image("logo_toyota")
                .cornerRadius(32)
            ZStack {
                TypingLoading()
            }
            .padding(.horizontal, 16)
            .padding(.vertical, 8)
            .background {
                LinearGradient(
                    gradient:
                        Gradient(colors: [Color(hex: 0xE7E7E7), Color(hex: 0xDCDCDC)]),
                    startPoint: .top,
                    endPoint: .bottom
                )
            }
            .cornerRadius(16, corners: [.bottomLeft, .bottomLeft, .topRight])
            Spacer()
        }
    }
}

internal struct BallonMessage_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            BallonSenderMessage("Hello")
            BallonSenderMessage("Say this is a test", isError: true)
            BallonBotMessage("This is indeed a test.")
            BallonTyping()
            BallonQrCode()
        }
    }
}
