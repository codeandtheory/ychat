package co.yml.ychat.jvm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.yml.ychat.jvm.services.CompletionService;

@RestController
@RequestMapping("api/ychat")
public class YChatController {

    @Autowired
    private CompletionService completionService;

    @GetMapping("completion")
    public ResponseEntity<String> completion(
            @RequestParam(value = "input", defaultValue = Defaults.COMPLETION_INPUT) String input
    ) throws Exception {
        String result = completionService.getCompletionAnswer(input);
        return ResponseEntity.ok(result);
    }

    private static class Defaults {
        static final String COMPLETION_INPUT = "Say this is a test.";
    }
}
