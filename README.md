![Maven Central](https://img.shields.io/maven-central/v/co.yml/ychat)
![Apache-2.0](https://img.shields.io/badge/license-Apache-blue)
![Build Status](https://github.com/yml-org/ychat/actions/workflows/test.yml/badge.svg?branch=main)
![Y-Chat](https://github.com/yml-org/ychat/raw/main/art/logo.png)

# YChat

YChat is a Kotlin Multiplatform (KMP) project that provides a simple API for integrating the powerful ChatGPT language model developed by OpenAI into mobile applications running on both iOS and Android. The goal of this project is to abstract all the API call logic from ChatGPT, allowing developers to easily leverage the capabilities of the language model in their mobile applications.

The repository contains the source code for the YChat library, along with examples and documentation for getting started with the library. The YChat library provides a consistent interface for interacting with ChatGPT, regardless of the platform, and makes it easy to generate human-like text based on a given prompt or context.

The library uses Kotlin Multiplatform to generate artifacts for both iOS and Android, allowing developers to write code once and use it on both platforms. The project is open source and actively maintained, with contributions from the community encouraged. Overall, YChat provides a convenient and powerful way for mobile developers to incorporate the advanced natural language processing capabilities of ChatGPT into their applications.


## iOS setup

- Go to your project’s file settings and click "Add Package":

![Y-ChatGPT iOS first screenshot](https://github.com/yml-org/ychat/raw/main/art/ios-1.png)

- To add a new package, search for https://github.com/yml-org/ychat.git in the top right corner:

![Y-ChatGPT iOS second screenshot](https://github.com/yml-org/ychat/raw/main/art/ios-2.png)

Once you have found the package click the "Add Package" button to add it to your project. Now you can start using the SDK in your iOS project!

See the code snippet below on how to initialize and use it:

```swift
var yChat: YChat {
    YChatCompanion.shared.create(apiKey: "your-api-key") 
}

do {
  let result = try await chatGpt.completion()
                    .setInput(input: "Say this is a test.")
                    .setMaxTokens(tokens: 1024)
                    .saveHistory(isSaveHistory: false)
                    .set... // you can set more parameters
                    .execute()
} catch {
  // catch any error that may occurs on api call.  
}
```

## Android setup

Add the following line to import the library via Gradle. First, make sure Maven Central has been added:


```kotlin
repositories {
    mavenCentral()
    // ...
}
```

Then, simply import the dependency to your common source-set dependencies:

```kotlin
implementation("co.yml:ychat:1.0.0")
```

In the snippet below, you can see how to initialize the object and perform a first search:


```kotlin
val yChat by lazy {
  YChat.create("your-api-key")
}

try {
  val result = yChat.completion()
     .setInput("Say this is a test.")
     .saveHistory(false)
     .setMaxTokens(1024)
     .set... // you can set more parameters
     .execute()
  
} catch (e: exception) {
  // catch any error that may occurs on api call.  
}
```

## 🤝 Contributions

Feel free to make a suggestion or if you find any error in this project, please open an issue. Make sure to read our [contribution guidelines](https://github.com/yml-org/ychat/blob/main/CONTRIBUTING.md) before.

## License

```
    Copyright 2023 YML

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```