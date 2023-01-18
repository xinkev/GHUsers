//
// Created by xinkev on 18/01/2023.
// Copyright (c) 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

struct RepositoryListItem: View {
    let repo: Repo
    @Environment(\.openURL) var openURL: OpenURLAction

    var body: some View {
        Button {
            if let url = URL(string: repo.htmlUrl) {
                openURL(url)
            }
        } label: {
            VStack(alignment: .leading) {
                Text(repo.fullName)
                    .font(.caption)
                    .foregroundColor(Color(red: 0.51, green: 0.74, blue: 0.93))
                if let description = repo.repoDescription {
                    Text(description).tint(.black)
                }
                HStack(spacing: 8) {
                    IconWithLabel(systemName: "star", label: String(repo.starGazersCount))
                    IconWithLabel(name: "source_fork", label: String(repo.forksCount))
                    if repo.hasIssues {
                        IconWithLabel(name: "repo_issues", label: String(repo.openIssuesCount))
                    }
                }
            }
        }
    }
}
