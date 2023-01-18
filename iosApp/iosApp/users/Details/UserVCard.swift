//
// Created by xinkev on 18/01/2023.
// Copyright (c) 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct UserVCard: View {
    let contactInfo: UserDetails.ContactInfo

    var body: some View {
        HStack(alignment: .center, spacing: 8) {
            if let github = contactInfo.github {
                IconLink("github", path: github)
            }
            if let email = contactInfo.email {
                IconLink(systemName: "envelope.fill",path:"mailto:\(email)")
            }
            if let twitter = contactInfo.twitter {
                IconLink("twitter", path: twitter)
            }
            if let blog = contactInfo.blog {
                IconLink(systemName: "link", path: blog)
            }
        }
    }


}

fileprivate struct IconLink: View {
    @State private var showInAppBrowser = false

    let path: String
    var name: String?
    var systemName: String?

    init(_ name: String, path: String) {
        self.path = path
        self.name = name
    }

    init(systemName: String, path: String) {
        self.path = path
        self.systemName = systemName
    }

    var body: some View {
        if let url = URL(string: path) {
            Button {
                showInAppBrowser = true
            } label: {
                if let name {
                    Image(name)
                } else {
                    Image(systemName: systemName!)
                        .tint(.black)
                }
            }.fullScreenCover(isPresented: $showInAppBrowser) {
                InAppBrowser(url: url)
            }
        }
    }
}
