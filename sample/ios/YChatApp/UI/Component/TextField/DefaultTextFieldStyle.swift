//
//  DefaultTextFieldStyle.swift
//  ios-sample
//
//  Created by Koji Osugi on 21/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct DefaultTextFieldStyle: TextFieldStyle {
    // swiftlint:disable:next identifier_name
    func _body(configuration: TextField<Self._Label>) -> some View {
        configuration
            .padding(.vertical)
            .padding(.horizontal, 24)
            .background(Color.primary5)
            .foregroundColor(.text1)
            .cornerRadius(8)
    }
}

struct DefaultTextFieldStyle_Previews: PreviewProvider {
    static var previews: some View {
        TextField(text: .constant("")) {
            Text("Search")
                .foregroundColor(.text3)
                .style(.largeTitle)
        }
        .padding(.horizontal, 16)
        .textContentType(.countryName)
        .textFieldStyle(DefaultTextFieldStyle())
    }
}
