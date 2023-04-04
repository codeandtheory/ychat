#  YChat GPT JVM Sample

This sample exposes some RESTful APIs to demonstrate how the YChat SDK can work in a backend environment.

## Setup

Before running the sample, you need to follow these steps:

1. Remove the suffix ".txt" from the `application.properties.txt` file located in `sample/jvm/main/src/resources`
2. Set your API key in the `apiKey` variable. Click [here](https://beta.openai.com/docs/api-reference/authentication) to get more information on how to get the api key.

After you configure, you can run the server with the following command: `./gradlew bootRun -Dserver.port=[port_number]`. Replace `[port_number]` with any unused port number such as `8080`, `8081`, etc.

## How it Works

After running the server, you can start playing around with the following endpoints:

### Completion Endpoint

This endpoint generates text based on the provided prompt.

##### Endpoint: http://localhost:[port_number]/api/ychat/completion

##### Parameters:

- `input`: The prompt for generating text.

##### Example:

`GET http://localhost:8080/api/ychat/completion?input="What is 1 + 1?"`

### Chat Completions Endpoint

This endpoint generates text based on the provided prompt and a specified topic. The generated text will be related to the topic provided.

##### Endpoint: http://localhost:[port_number]/api/ychat/chat-completions

##### Parameters:

- `input`: The prompt for generating text.
- `topic`: The topic to limit the response to.

##### Example:

`GET http://localhost:8080/api/ychat/chat-completions?input="Tell me an exercise plan"&topic=fitness`

### Image Generations Endpoint

This endpoint generates images based on the provided prompt.

##### Endpoint: http://localhost:[port_number]/api/ychat/generations

##### Parameters:

- `prompt`: The prompt for generating images.

##### Example:

`GET http://localhost:8080/api/ychat/generations?prompt="ocean"`

### Edits Endpoint

This endpoint edits the prompt based on the provided instruction.

##### Endpoint: http://localhost:[port_number]/api/ychat/edits

##### Parameters:

- `input`: The input text to use as a starting point for the edit.
- `instruction`: The instruction that tells the model how to edit the prompt.

##### Example:

`GET http://localhost:8080/api/ychat/edits?input=What day of the wek is it?&instruction=Fix the spelling mistakes`

### List Models Endpoint

This endpoint retrieve a list of currently available artificial intelligence models.

##### Endpoint: http://localhost:[port_number]/api/ychat/models

##### Example:

`GET http://localhost:8080/api/ychat/models`

### Model Endpoint

This endpoint retrieve the artificial intelligence model based on the given ID.

##### Endpoint: http://localhost:[port_number]/api/ychat/models/{modelID}

##### Example:

`GET http://localhost:8080/api/ychat/models/babbage`

### Audio Transcriptions Endpoint

This endpoint transcribes audio into the input language.

##### Endpoint: http://localhost:[port_number]/api/ychat/audio/transcriptions

##### Example:

```
curl -X POST \
  -H "Content-Type: multipart/form-data" \
  -F "file=@/path/to/audio/file" \
  "http://localhost:8080/api/ychat/audio/transcriptions"
```

### Audio Translations Endpoint

This endpoint translates audio into English

##### Endpoint: http://localhost:[port_number]/api/ychat/audio/translations

##### Example:

```
curl -X POST \
  -H "Content-Type: multipart/form-data" \
  -F "file=@/path/to/audio/file" \
  "http://localhost:8080/api/ychat/audio/translations"
```