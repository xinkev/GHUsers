//
// Created by xinkev on 18/01/2023.
// Copyright (c) 2023 orgName. All rights reserved.
//

import SwiftUI

struct UserStat: View {
    let label: String
    let numbers: Int32

    var body: some View {
        VStack(alignment: .center) {
            Text(label)
                    .font(.caption)
                    .opacity(0.6)
            Text(String(numbers))
        }
    }
}
