//
//  Icon.swift
//  ios-sample
//
//  Created by Koji Osugi on 21/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

enum Icon: String, CaseIterable {
    case menu = "ic_menu"
    case refresh = "ic_refresh"
    case arrowLeft = "ic_arrow_left"
    case arrowDown = "ic_arrow_down"
    case swap = "ic_swap"
    case settings = "ic_settings"
    case bot = "ic_bot"
    case send = "ic_send"
    case chat = "ic_chat"
    case edit = "ic_edit"
    case warningOutline = "ic_warning_outline"
    case logo = "ic_logo"
    case logoBig = "ic_logo_big"
}

extension UIImage {
    
    public class var logoBig: UIImage {
        UIImage(named: Icon.logoBig.rawValue)!
    }
    
    public class var logo: UIImage {
        UIImage(named: Icon.logo.rawValue)!
    }
    
    public class var warningOutline: UIImage {
        UIImage(named: Icon.warningOutline.rawValue)!
    }
    
    public class var edit: UIImage {
        UIImage(named: Icon.edit.rawValue)!
    }
    
    public class var chat: UIImage {
        UIImage(named: Icon.chat.rawValue)!
    }
    
    public class var send: UIImage {
        UIImage(named: Icon.send.rawValue)!
    }
    
    public class var bot: UIImage {
        UIImage(named: Icon.bot.rawValue)!
    }
    
    public class var settings: UIImage {
        UIImage(named: Icon.settings.rawValue)!
    }
    
    public class var swap: UIImage {
        UIImage(named: Icon.swap.rawValue)!
    }
    
    public class var menu: UIImage {
        UIImage(named: Icon.menu.rawValue)!
    }
    
    public class var refresh: UIImage {
        UIImage(named: Icon.refresh.rawValue)!
    }
    
    public class var arrowDown: UIImage {
        UIImage(named: Icon.arrowDown.rawValue)!
    }
    
    public class var arrowLeft: UIImage {
        UIImage(named: Icon.arrowLeft.rawValue)!
    }
}

private struct IconSample: View {
    
    private let columns = [
        GridItem(.adaptive(minimum: 80))
    ]
    
    var body: some View {
        ScrollView {
            LazyVGrid(columns: columns, spacing: 8) {
                ForEach(Icon.allCases, id: \.self) {
                    Image($0.rawValue)
                        .resizable()
                        .frame(width: 24, height: 24)
                }
            }
        }
    }
}

internal struct IconSample_Previews: PreviewProvider {
    static var previews: some View {
        IconSample()
    }
}

