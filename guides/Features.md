# Features

- [ListModels](#listModels)
- [RetrieveModel](#retrieveModel)
- [Completion](#completion)
- [ChatCompletions](#chatcompletions)
- [ImageGenerations](#imagegenerations)
- [Edits](#edits)
- [AudioTranscriptions](#audioTranscriptions)

## ListModels

The listModels api lists the currently available models, and provides basic information about each one such as the owner and availability.

### Swift

```swift
var yChat: YChat {
    YChatCompanion.shared.create(apiKey: "your-api-key") 
}

do {
  let result = try await yChat.listModels().execute()
} catch {
  // catch any error that may occurs on api call.  
}
```

### Kotlin

```kotlin
val yChat by lazy {
    YChat.create("your-api-key")
}

try {
    val result = yChat.listModels().execute()

} catch (e: exception) {
    // catch any error that may occurs on api call.  
}
```

## RetrieveModel

The retrieveModel api retrieve the currently model based on the given id, and provides basic information about it such as the owner and availability.

### Swift

```swift
var yChat: YChat {
    YChatCompanion.shared.create(apiKey: "your-api-key") 
}

do {
  let result = try await yChat.retrieveModel().execute(id: "babbage")
} catch {
  // catch any error that may occurs on api call.  
}
```

### Kotlin

```kotlin
val yChat by lazy {
    YChat.create("your-api-key")
}

try {
    val result = yChat.retrieveModel().execute("babbage")

} catch (e: exception) {
    // catch any error that may occurs on api call.  
}
```

## Completion

The completions api can be used for a wide variety of tasks. You input some text as a prompt, and the model will generate a text completion that attempts to match whatever context or pattern you gave it. For example, if you give the API the prompt, "As Descartes said, I think, therefore", it will return the completion " I am" with high probability.

### Swift

```swift
var yChat: YChat {
    YChatCompanion.shared.create(apiKey: "your-api-key") 
}

do {
  let result = try await yChat.completion()
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

### Kotlin

```kotlin
val yChat by lazy {
    YChat.create("your-api-key")
}

try {
    val result = yChat.completion()
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

## ChatCompletions

The chatCompletions api generates a list of chat completions for the given input message. It uses machine learning algorithms to generate responses that match the context or pattern provided in the input message.

### Swift

```swift
var yChat: YChat {
    YChatCompanion.shared.create(apiKey: "your-api-key") 
}

do {
  let result = try await yChat.chatCompletions()
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

### Kotlin

```kotlin
val yChat by lazy {
    YChat.create("your-api-key")
}

try {
    val result = yChat.chatCompletions()
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

## ImageGenerations

The image generations api is used to generate images based on a prompt. You input some text as a prompt, and the model will generate one or more images.

### Swift

```swift
var yChat: YChat {
    YChatCompanion.shared.create(apiKey: "your-api-key") 
}

do {
  let result = try await yChat.imageGenerations()
                    .setResults(results: 1)
                    .setSize(size: "1024x1024")
                    .setResponseFormat(responseFormat: "url")
                    .execute(prompt: "ocean")
} catch {
  // catch any error that may occurs on api call.  
}
```

### Kotlin

```kotlin
val yChat by lazy {
    YChat.create("your-api-key")
}

try {
    val result = yChat.imageGenerations()
        .setResults(1)
        .setSize("1024x1024")
        .setResponseFormat("url")
        .execute("ocean")
} catch (e: exception) {
    // catch any error that may occurs on api call.  
}
```

## Edits

The edits api is used to edit prompts and re-generate. Given a prompt and an instruction, the model will return an edited version of the prompt.

### Swift

```swift
var yChat: YChat {
    YChatCompanion.shared.create(apiKey: "your-api-key") 
}

do {
  let result = try await yChat.edits()
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

### Kotlin

```kotlin
val yChat by lazy {
    YChat.create("your-api-key")
}

try {
    val result = yChat.edits()
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

## AudioTranscriptions

The audioTranscriptions api is used to transcribes audio into the input language.

### Swift

```swift
var yChat: YChat {
    YChatCompanion.shared.create(apiKey: "your-api-key") 
}

guard let audioFileUrl = Bundle.main.url(forResource: "audio", withExtension: "m4a") else {
    print("Unable to find the audio file.")
    return
}

let audioData = try! Data(contentsOf: audioFileUrl)

do {
  let result = try await yChat.audioTranscriptions()
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

### Kotlin

```kotlin
val yChat by lazy {
    YChat.create("your-api-key")
}

val inputStream = application.resources.openRawResource(R.raw.audio)
val byteArray = inputStream.readBytes()

try {
    val result = yChat.audioTranscriptions()
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