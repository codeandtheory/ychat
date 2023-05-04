#  OpenAI Provider

The OpenAI Provider library provides an abstraction for OpenAI APIs logic.

## ⚡️ Getting Started

### iOS setup
To use this library in your iOS project, follow these steps:

1. Go to your project’s file settings and click "Add Package".
2. In the search bar at the top right, enter https://github.com/yml-org/ychat.git.
3. Once you have found the package, click the "Add Package" button.
4. In the next screen, select the `OpenAI` product and click the "Add Package" button again to add it to your project.

You can now start using the OpenAI Provider SDK in your iOS project!

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
implementation("co.yml:openai-provider:<latest-version>")
```

## 🧬 Features

- [ListModels](#listModels)
- [RetrieveModel](#retrieveModel)
- [Completion](#completion)
- [ChatCompletions](#chatcompletions)
- [ImageGenerations](#imagegenerations)
- [Edits](#edits)
- [AudioTranscriptions](#audioTranscriptions)
- [AudioTranslations](#audioTranslations)

### ListModels

The listModels api lists the currently available models, and provides basic information about each one such as the owner and availability.

#### Swift

```swift
var openAi: OpenAI {
    OpenAICompanion.shared.create(apiKey: "your-api-key") 
}

do {
  let result = try await openAi.listModels().execute()
} catch {
  // catch any error that may occurs on api call.  
}
```

#### Kotlin

```kotlin
val openAi by lazy {
    OpenAI.create("your-api-key")
}

try {
    val result = openAi.listModels().execute()

} catch (e: exception) {
    // catch any error that may occurs on api call.  
}
```

### RetrieveModel

The retrieveModel api retrieve the currently model based on the given id, and provides basic information about it such as the owner and availability.

#### Swift

```swift
var openAi: OpenAI {
    OpenAICompanion.shared.create(apiKey: "your-api-key") 
}

do {
  let result = try await openAi.retrieveModel().execute(id: "babbage")
} catch {
  // catch any error that may occurs on api call.  
}
```

#### Kotlin

```kotlin
val openAi by lazy {
    OpenAI.create("your-api-key")
}

try {
    val result = openAi.retrieveModel().execute("babbage")

} catch (e: exception) {
    // catch any error that may occurs on api call.  
}
```

### Completion

The completions api can be used for a wide variety of tasks. You input some text as a prompt, and the model will generate a text completion that attempts to match whatever context or pattern you gave it. For example, if you give the API the prompt, "As Descartes said, I think, therefore", it will return the completion " I am" with high probability.

#### Swift

```swift
var openAi: OpenAI {
    OpenAICompanion.shared.create(apiKey: "your-api-key") 
}

do {
  let result = try await openAi.completion()
                    .setModel(input: "text-davinci-003")
                    .setInput(input: "Say this is a test.")
                    .setMaxTokens(tokens: 1024)
                    .setTemperature(temperature: 1.0)
                    .setTopP(topP: 1.0)
                    .saveHistory(isSaveHistory: false)
                    .execute()
} catch {
  // catch any error that may occurs on api call.  
}
```

#### Kotlin

```kotlin
val openAi by lazy {
    OpenAI.create("your-api-key")
}

try {
    val result = openAi.completion()
        .setModel("text-davinci-003")
        .setInput("Say this is a test.")
        .setMaxTokens(1024)
        .setTemperature(1.0)
        .setTopP(1.0)
        .saveHistory(false)
        .execute()
} catch (e: exception) {
    // catch any error that may occurs on api call.  
}
```

### ChatCompletions

The chatCompletions api generates a list of chat completions for the given input message. It uses machine learning algorithms to generate responses that match the context or pattern provided in the input message.

#### Swift

```swift
var openAi: OpenAI {
    OpenAICompanion.shared.create(apiKey: "your-api-key") 
}

do {
  let result = try await openAi.chatCompletions()
                    .setModel(model: "gpt-3.5-turbo")
                    .setMaxTokens(tokens: 1024)
                    .setMaxResults(results: 1)
                    .setTemperature(temperature: 1.0)
                    .setTopP(topP: 1.0)
                    .addMessage(
                        role: "assistant",
                        content: "You are a helpful assistant that only answers questions related to fitness"
                    )
                    .execute(content: "What is the best exercise for building muscle?")
} catch {
  // catch any error that may occurs on api call.  
}
```

#### Kotlin

```kotlin
val openAi by lazy {
    OpenAI.create("your-api-key")
}

try {
    val result = openAi.chatCompletions()
        .setModel("gpt-3.5-turbo")
        .setMaxTokens(1024)
        .setMaxResults(1)
        .setTemperature(1.0)
        .setTopP(1.0)
        .addMessage(
            role = "assistant",
            content = "You are a helpful assistant that only answers questions related to fitness"
        )
        .execute("What is the best exercise for building muscle?")
} catch (e: exception) {
    // catch any error that may occurs on api call.  
}
```

### ImageGenerations

The image generations api is used to generate images based on a prompt. You input some text as a prompt, and the model will generate one or more images.

#### Swift

```swift
var openAi: OpenAI {
    OpenAICompanion.shared.create(apiKey: "your-api-key") 
}

do {
  let result = try await openAi.imageGenerations()
                    .setResults(results: 1)
                    .setSize(size: "1024x1024")
                    .setResponseFormat(responseFormat: "url")
                    .execute(prompt: "ocean")
} catch {
  // catch any error that may occurs on api call.  
}
```

#### Kotlin

```kotlin
val openAi by lazy {
    OpenAI.create("your-api-key")
}

try {
    val result = openAi.imageGenerations()
        .setResults(1)
        .setSize("1024x1024")
        .setResponseFormat("url")
        .execute("ocean")
} catch (e: exception) {
    // catch any error that may occurs on api call.  
}
```

### Edits

The edits api is used to edit prompts and re-generate. Given a prompt and an instruction, the model will return an edited version of the prompt.

#### Swift

```swift
var openAi: OpenAI {
    OpenAICompanion.shared.create(apiKey: "your-api-key") 
}

do {
  let result = try await openAi.edits()
                    .setInput(input: "What day of the wek is it?")
                    .setResults(result: 1)
                    .setModel(model: "text-davinci-edit-001")
                    .setTemperature(temperature: 1.0)
                    .setTopP(topP: 1.0)
                    .execute(instruction: "Fix the spelling mistakes")
} catch {
  // catch any error that may occurs on api call.  
}
```

#### Kotlin

```kotlin
val openAi by lazy {
    OpenAI.create("your-api-key")
}

try {
    val result = openAi.edits()
        .setInput("What day of the wek is it?")
        .setResults(1)
        .setModel("text-davinci-edit-001")
        .setTemperature(1.0)
        .setTopP(1.0)
        .execute("Fix the spelling mistakes")
} catch (e: exception) {
    // catch any error that may occurs on api call.  
}
```

### AudioTranscriptions

The audioTranscriptions api is used to transcribes audio into the input language.

#### Swift

```swift
var openAi: OpenAI {
    OpenAICompanion.shared.create(apiKey: "your-api-key") 
}

guard let audioFileUrl = Bundle.main.url(forResource: "audio", withExtension: "m4a") else {
    print("Unable to find the audio file.")
    return
}

let audioData = try! Data(contentsOf: audioFileUrl)

do {
  let result = try await openAi.audioTranscriptions()
                    .setModel(model: "whisper-1")
                    .setPrompt(prompt: "")
                    .setResponseFormat(format: "json")
                    .setTemperature(temperature: 0.4)
                    .setLanguage(language: "en")
                    .execute(filename: "audio.m4a", audioFile: audioData)
} catch {
  // catch any error that may occurs on api call.  
}
```

#### Kotlin

```kotlin
val openAi by lazy {
    OpenAI.create("your-api-key")
}

val inputStream = application.resources.openRawResource(R.raw.audio)
val byteArray = inputStream.readBytes()

try {
    val result = openAi.audioTranscriptions()
        .setModel("whisper-1")
        .setPrompt("")
        .setResponseFormat("json")
        .setTemperature(0.4)
        .setLanguage("en")
        .execute("audio.m4a", byteArray)
} catch (e: exception) {
    // catch any error that may occurs on api call.  
}
```

### AudioTranslations

The audioTranscriptions api is used to translates audio into English.

#### Swift

```swift
var openAi: OpenAI {
    OpenAICompanion.shared.create(apiKey: "your-api-key") 
}

guard let audioFileUrl = Bundle.main.url(forResource: "audio", withExtension: "m4a") else {
    print("Unable to find the audio file.")
    return
}

let audioData = try! Data(contentsOf: audioFileUrl)

do {
  let result = try await openAi.audioTranslations()
                    .setModel(model: "whisper-1")
                    .setPrompt(prompt: "")
                    .setResponseFormat(format: "json")
                    .setTemperature(temperature: 0.4)
                    .execute(filename: "audio.m4a", audioFile: audioData)
} catch {
  // catch any error that may occurs on api call.  
}
```

#### Kotlin

```kotlin
val openAi by lazy {
    OpenAI.create("your-api-key")
}

val inputStream = application.resources.openRawResource(R.raw.audio)
val byteArray = inputStream.readBytes()

try {
    val result = openAi.audioTranslations()
        .setModel("whisper-1")
        .setPrompt("")
        .setResponseFormat("json")
        .setTemperature(0.4)
        .execute("audio.m4a", byteArray)
} catch (e: exception) {
    // catch any error that may occurs on api call.  
}
```