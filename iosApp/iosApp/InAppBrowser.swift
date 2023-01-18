//
// Created by xinkev on 17/01/2023.
// Copyright (c) 2023 orgName. All rights reserved.
//

import SafariServices
import SwiftUI

struct InAppBrowser: UIViewControllerRepresentable {
    var url: URL

    func makeUIViewController(context _: UIViewControllerRepresentableContext<InAppBrowser>) -> SFSafariViewController {
        let safariView = SFSafariViewController(url: url)
        return safariView
    }

    func updateUIViewController(_: SFSafariViewController, context _: UIViewControllerRepresentableContext<InAppBrowser>) {}
}
