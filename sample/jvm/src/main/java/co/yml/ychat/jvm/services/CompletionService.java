package co.yml.ychat.jvm.services;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;
import co.yml.ychat.YChat;

@Service
public class CompletionService {

    @Autowired
    private YChat ychat;

    private static final int MAX_TOKENS = 512;

    public String getCompletionAnswer(String input) throws Exception {
        final CompletableFuture<String> future = new CompletableFuture<>();
        ychat.completion()
                .setMaxTokens(MAX_TOKENS)
                .setInput(input)
                .execute(new CompletionCallbackResult(future));
        return future.get();
    }

    private static class CompletionCallbackResult implements YChat.Callback<String> {

        private final CompletableFuture<String> future;

        CompletionCallbackResult(CompletableFuture<String> future) {
            this.future = future;
        }

        @Override
        public void onSuccess(String result) {
            future.complete(result);
        }

        @Override
        public void onError(@NotNull Throwable throwable) {
            future.completeExceptionally(throwable);
        }
    }
}
