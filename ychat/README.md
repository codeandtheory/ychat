#  YChat

YChat is a library that offers a range of AI model API consumer providers. It's designed to help developers integrate AI models into their applications with ease. It provides a simple and intuitive interface that allows you to quickly and easily consume AI model APIs without having to write complex code. YChat supports various AI model APIs and makes it easy for developers to integrate them into their projects.

## ⚡️ Getting Started

### iOS setup
To use this library in your iOS project, follow these steps:

1. Go to your project’s file settings and click "Add Package".
2. In the search bar at the top right, enter https://github.com/yml-org/ychat.git.
3. Once you have found the package, click the "Add Package" button.
4. In the next screen, select the `YChat` product and click the "Add Package" button again to add it to your project.

You can now start using the YChat SDK in your iOS project!

### Android/JVM setup

Add the following line to import the library via Gradle. First, make sure Maven Central has been added:


```kotlin
repositories {
    mavenCentral()
    // ...
}
```

Then, simply import the dependency to your `build.gradle` dependencies:

```kotlin
implementation("co.yml:ychat:<latest-version>")
```

## 🧬 Features

Using the `Provider` class, you can choose the provider that will implement the functionality. See below an example of how to initialize the SDK, passing the provider:

#### Swift
```swift
var yChat: YChat {
    YChatCompanion.shared.create(provider: .OpenAi(apiKey: "teste"))
}
```

#### Kotlin
```kotlin
val yChat = YChat.create(Provider.OpenAi(apiKey = "your-api-key"))
```

After obtaining the `YChat` instance, you can start calling the functionality you want. See below an example of using the `completions` functionality with OpenAI as the provider:

#### Swift
```swift
do {
    let yChat = YChatCompanion
        .shared
        .create(provider: .OpenAi(apiKey: "your-api-key"))
    try await yChat.completions()
        .setPrompt(prompt: "As descartes said")
        .execute()
} catch {
    // handle exception
}
```

#### Kotlin
```kotlin
val yChat = YChat.create(Provider.OpenAi(apiKey = "your-api-key"))
try {
    val result = yChat
        .completions()
        .setPrompt("As descartes said")
        .execute()
} catch (yChatException: YChatException) {
    // handle exception
}
```