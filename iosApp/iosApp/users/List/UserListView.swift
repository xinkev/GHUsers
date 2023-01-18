//
// Created by xinkev on 17/01/2023.
// Copyright (c) 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

struct UserListView: View {
    @StateObject private var viewModel = UserListViewModel(repo: RepositoryResolver.shared.githubUserRepository)

    var body: some View {
        NavigationView {
            List {
                ForEach(viewModel.users, id: \.id) { user in
                    ZStack {
                        UserListItem(user: user)
                        NavigationLink {
                            UserDetailsView(username: user.username)
                        } label: {
                            EmptyView()
                        }
                    }
                }

                if viewModel.shouldDisplayNextPage {
                    NextPageView().onAppear {
                        viewModel.fetchNextPage()
                    }
                }
            }
            .navigationTitle("GHUsers")
            .onAppear {
                viewModel.fetchUsers()
            }
        }
    }
}
