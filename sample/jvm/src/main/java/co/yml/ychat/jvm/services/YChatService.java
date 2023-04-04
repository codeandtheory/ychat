package co.yml.ychat.jvm.services;

import co.yml.ychat.YChat;
import co.yml.ychat.domain.model.AIModel;
import co.yml.ychat.domain.model.ChatMessage;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class YChatService {

    @Autowired
    private YChat ychat;

    private static final int MAX_TOKENS = 512;

    public String getCompletionAnswer(String input) throws Exception {
        final CompletableFuture<String> future = new CompletableFuture<>();
        ychat.completion()
                .setMaxTokens(MAX_TOKENS)
                .setInput(input)
                .execute(new CompletionCallbackResult<>(future));
        return future.get();
    }

    public String getChatCompletionsAnswer(String input, String topic) throws Exception {
        final CompletableFuture<List<ChatMessage>> future = new CompletableFuture<>();
        String content = "You are a helpful assistant the only answer questions related to " + topic;
        ychat.chatCompletions()
                .setMaxTokens(MAX_TOKENS)
                .addMessage("system", content)
                .execute(input, new CompletionCallbackResult<>(future));
        return future.get().get(0).getContent();
    }

    public String getImageGenerationsAnswer(String prompt) throws Exception {
        final CompletableFuture<List<String>> future = new CompletableFuture<>();
        ychat.imageGenerations()
                .execute(prompt, new CompletionCallbackResult<>(future));
        return future.get().get(0);
    }

    public String getEditsAnswer(String input, String instruction) throws Exception {
        final CompletableFuture<List<String>> future = new CompletableFuture<>();
        ychat.edits()
                .setInput(input)
                .execute(instruction, new CompletionCallbackResult<>(future));
        return future.get().get(0);
    }

    public List<AIModel> getModels() throws Exception {
        final CompletableFuture<List<AIModel>> future = new CompletableFuture<>();
        ychat.listModels()
                .execute(new CompletionCallbackResult<>(future));
        return future.get();
    }

    public AIModel getModel(String id) throws Exception {
        final CompletableFuture<AIModel> future = new CompletableFuture<>();
        ychat.retrieveModel()
                .execute(id, new CompletionCallbackResult<>(future));
        return future.get();
    }

    public String getAudioTranscription(MultipartFile multipartFile) throws Exception {
        final CompletableFuture<String> future = new CompletableFuture<>();
        String filename = Optional.ofNullable(multipartFile.getOriginalFilename())
                .orElseThrow(() -> new IllegalStateException("Filename not found"));
        byte[] bytes = multipartFile.getBytes();
        ychat.audioTranscriptions().execute(filename, bytes, new CompletionCallbackResult<>(future));
        return future.get();
    }

    private static class CompletionCallbackResult<T> implements YChat.Callback<T> {

        private final CompletableFuture<T> future;

        CompletionCallbackResult(CompletableFuture<T> future) {
            this.future = future;
        }

        @Override
        public void onSuccess(T result) {
            future.complete(result);
        }

        @Override
        public void onError(@NotNull Throwable throwable) {
            future.completeExceptionally(throwable);
        }
    }
}
