//
// Created by xinkev on 18/01/2023.
// Copyright (c) 2023 orgName. All rights reserved.
//

import SwiftUI

struct NextPageView: View {
    var body: some View {
        HStack {
            Spacer()
            VStack {
                ProgressView()
                Text("Loading next page...")
            }
            Spacer()
        }
    }
}
