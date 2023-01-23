//
// Created by xinkev on 18/01/2023.
// Copyright (c) 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

struct UserRepositoriesView: View {
    var userRepos: [Repo]
    @EnvironmentObject var viewModel: UserDetailsViewModel

    var body: some View {
        List {
            Section {
                ForEach(userRepos, id: \.id) { repo in
                    RepositoryListItem(repo: repo)
                }

                if viewModel.showReposNextPage {
                    NextPageView().onAppear {
                        viewModel.fetchNextRepositoriesPage()
                    }
                }
            } header: {
                Text("Repositories")
            }.listStyle(.inset)
        }
    }
}
