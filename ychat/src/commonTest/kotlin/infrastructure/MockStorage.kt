package infrastructure

object MockStorage {

    fun completionSuccessResult(text: String) = """
    {
        "id": "1",
        "object": "text_completion",
        "created": 1675532539,
        "model": "text-davinci-003",
        "choices": [
            {
                "text": "\n\n$text",
                "index": 0,
                "logprobs": null,
                "finish_reason": "stop"
            }
        ],
        "usage": {
            "prompt_tokens": 8,
            "completion_tokens": 9,
            "total_tokens": 17
        }
    }
    """

    fun chatCompletionsSuccessResult(text: String) = """
    {
        "id": "1",
        "object": "chat.completion",
        "created": 1678144798,
        "model": "gpt-3.5-turbo-0301",
        "usage": {
            "prompt_tokens": 13,
            "completion_tokens": 12,
            "total_tokens": 25
        },
        "choices": [
            {
                "message": {
                    "role": "assistant",
                    "content": "$text"
                },
                "finish_reason": "stop",
                "index": 0
            }
        ]
    }
    """

    fun imageGenerationsSuccessResult(text: String) = """
    {
        "created": 1678805561,
        "data": [
            {
                "url": "$text"
            }
        ]
    }
    """

    fun editsSuccessResult(text: String) = """
    {
        "object": "edit",
        "created": 1679072839,
        "choices": [
            {
                "text": "$text",
                "index": 0
            }
        ],
        "usage": {
            "prompt_tokens": 25,
            "completion_tokens": 28,
            "total_tokens": 53
        }
    }
    """

    fun listModelsSuccessResult(id: String) = """
    {
        "object": "list",
        "data": [
            {
                "id": "$id",
                "object": "model",
                "created": 1649358449,
                "owned_by": "openai",
                "permission": [
                    {
                        "id": "modelperm-49FUp5v084tBB49tC4z8LPH5",
                        "object": "model_permission",
                        "created": 1669085501,
                        "allow_create_engine": false,
                        "allow_sampling": true,
                        "allow_logprobs": true,
                        "allow_search_indices": false,
                        "allow_view": true,
                        "allow_fine_tuning": false,
                        "organization": "*",
                        "group": null,
                        "is_blocking": false
                    }
                ],
                "root": "$id",
                "parent": null
            }
        ]
    }
    """

    fun modelSuccessResult(id: String) = """
    {
        "id": "$id",
        "object": "model",
        "created": 1649358449,
        "owned_by": "openai",
        "permission": [
            {
                "id": "modelperm-49FUp5v084tBB49tC4z8LPH5",
                "object": "model_permission",
                "created": 1669085501,
                "allow_create_engine": false,
                "allow_sampling": true,
                "allow_logprobs": true,
                "allow_search_indices": false,
                "allow_view": true,
                "allow_fine_tuning": false,
                "organization": "*",
                "group": null,
                "is_blocking": false
            }
        ],
        "root": "$id",
        "parent": null
    }
    """
}
