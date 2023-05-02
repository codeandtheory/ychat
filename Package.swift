// swift-tools-version:5.3
import PackageDescription

let package = Package(
    name: "YChat",
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: "YChat",
            targets: ["YChat"]
        ),
        .library(
            name: "OpenAI",
            targets: ["OpenAI"]
        ),
    ],
    targets: [
        .binaryTarget(
            name: "YChat",
            path: "./YChat.xcframework"
        ),
        .binaryTarget(
            name: "OpenAI",
            path: "./OpenAI.xcframework"
        ),
    ]
)
