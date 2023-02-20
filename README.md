[![Maven Central](https://maven-badges.herokuapp.com/maven-central/co.yml/ychatgpt/badge.svg)](https://maven-badges.herokuapp.com/maven-central/co.yml/ychatgpt/)
![Apache-2.0](https://img.shields.io/badge/license-Apache-blue)
![Build Status](https://github.com/yml-org/chatgpt-sdk/actions/workflows/test.yml/badge.svg?branch=main)

# YChatGPT (Working in Progress 🚧)

ChatGPT is a large language model developed by OpenAI that is trained to generate human-like text based on a given prompt or context.

YChatGPT aims to abstract all API call logic from ChatGPT for multiple platforms. YChatGPT is a Kotlin Multiplatform (KMP) project, that generates artifacts for both iOS and Android.


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
implementation("co.yml:ychatgpt:1.0.0")
```

In the snippet below, you can see how to initialize the object and perform a first search:


```kotlin
val yChatGpt by lazy {
  YChatGpt.create("your-api-key")
}

try {
  val result = yChatGpt.completion()
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

Feel free to make a suggestion or if you find any error in this project, please open an issue. Make sure to read our [contribution guidelines](https://github.com/yml-org/chatgpt-sdk/blob/main/CONTRIBUTING.md) before.

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
