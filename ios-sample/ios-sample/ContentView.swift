import SwiftUI
import chat_gpt



struct ContentView: View {
    
    init() {
        let chat = ChatGptCompanion.shared.create(apiKey: "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")
        Task.init {
            let result = try! await chat.completion(input: "")
            let a = result
        }
    }
    
    var body: some View {
        Text("Hello world!")
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
