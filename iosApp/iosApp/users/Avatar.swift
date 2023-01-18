//
// Created by xinkev on 17/01/2023.
// Copyright (c) 2023 orgName. All rights reserved.
//

import SwiftUI

internal struct Avatar: View {
    let path: String?
    private var url: URL? {
        guard let path = path else { return nil }
        return URL(string: path)
    }

    var body: some View {
        if let url = url {
            AsyncImage(url: url) { image in
                image.resizable()
            } placeholder: {
                ProgressView()
            }.clipShape(Circle())
        } else {
            Circle().foregroundColor(.gray)
        }
    }
}
