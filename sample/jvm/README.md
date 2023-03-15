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

`GET http://localhost:8080/api/ychat/generations?prompt="ocean"