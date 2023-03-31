# Features

- [ListModels](#listModels)
- [RetrieveModel](#retrieveModel)
- [Completion](#completion)
- [ChatCompletions](#chatcompletions)
- [ImageGenerations](#imagegenerations)
- [Edits](#edits)

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
                    .setInput(input: "Say this is a test.")
                    .setMaxTokens(tokens: 1024)
                    .set... // you can set more parameters
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
        .setInput("Say this is a test.")
        .setMaxTokens(1024)
        .set... // you can set more parameters
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
                    .setMaxTokens(tokens: 1024)
                    .addMessage(
                        role: "assistant",
                        content: "You are a helpful assistant that only answers questions related to fitness"
                    )
                    .set... // you can set more parameters
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
        .setMaxTokens(1024)
        .addMessage(
            role = "assistant",
            content = "You are a helpful assistant that only answers questions related to fitness"
        )
        .set... // you can set more parameters
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
                    .setResults(results: 2)
                    .setSize(size: "1024x1024")
                    .set... // you can set more parameters
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
        .setResults(2)
        .setSize("1024x1024")
        .set... // you can set more parameters
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
                    .set... // you can set more parameters
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
        .set... // you can set more parameters
        .execute("Fix the spelling mistakes")
} catch (e: exception) {
    // catch any error that may occurs on api call.  
}
```