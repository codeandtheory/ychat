import SwiftUI

@main
struct SampleApp: App {
    @ObservedObject
    private var appRouter: AppRouter = AppRouter.shared

	var body: some Scene {
        WindowGroup {
            Group {
                switch appRouter.navGraph.destination {
                case .splash: SplashView()
                case .main: MainView()
                }
            }
            .transition(appRouter.navGraph.animation)
            .animation(.default, value: appRouter.navGraph.destination)
        }
	}
}
