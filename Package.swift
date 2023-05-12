// swift-tools-version:5.3
import PackageDescription

let package = Package(
    name: "YChat",
    platforms: [
        .iOS(.v13),
.macOS(.v10.0)
    ],
    products: [
        .library(
            name: "YChat",
            targets: ["YChat"]
        ),
    ],
    targets: [
        .binaryTarget(
            name: "YChat",
            path: "./YChat.xcframework"
        ),
    ]
)
