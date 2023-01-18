//
// Created by xinkev on 18/01/2023.
// Copyright (c) 2023 orgName. All rights reserved.
//

import SwiftUI

struct IconWithLabel: View {
    var name: String?
    let label: String
    var systemName: String?

    init(name: String, label: String) {
        self.name = name
        self.label = label
    }

    init(systemName: String, label: String) {
        self.systemName = systemName
        self.label = label
    }

    var body: some View {
        HStack(alignment: .center) {
            if let name {
                Image(name)
            } else {
                Image(systemName: systemName!)
            }
            Text(label)
                .font(.caption)
        }
        .tint(.black)
        .frame(height: 16)
    }
}
