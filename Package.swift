// swift-tools-version:5.3
import PackageDescription

let package = Package(
    name: "YChatGPT",
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: "YChatGPT",
            targets: ["YChatGPT"]
        ),
    ],
    targets: [
        .binaryTarget(
            name: "YChatGPT",
            path: "./YChatGPT.xcframework"
        ),
    ]
)
