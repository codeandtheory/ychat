import SwiftUI

@main
struct iOSApp: App {
    
    @ObservedObject
    private var appRouter: AppRouter = AppRouter.shared
    
	var body: some Scene {
        WindowGroup {
            Group {
                switch appRouter.navGraph.destination {
                case .splash: SplashView()
                case .main: MainView()
                case .fitSplash: FitSplashView()
                case .fitMain: FitMainView()
                }
            }
            .transition(appRouter.navGraph.animation)
            .animation(.default, value: appRouter.navGraph.destination)
        }
	}
}
