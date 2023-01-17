//
// Created by xinkev on 17/01/2023.
// Copyright (c) 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

struct UserListView: View {
    @StateObject private var viewModel = UsersViewModel(repo: RepositoryResolver.shared.githubUserRepository)

    var body: some View {
        NavigationView {
            List {
                ForEach(viewModel.users, id: \.id) { user in
                    ZStack {
                        UserListItem(user: user)
                        NavigationLink {
                            Text("hi")
                        } label: {
                            EmptyView()
                        }
                    }
                }

                if viewModel.shouldDisplayNextPage {
                    nextPageView
                }
            }
            .navigationTitle("GHUsers")
            .onAppear {
                viewModel.fetchUsers()
            }
        }
    }

    private var nextPageView: some View {
        HStack {
            Spacer()
            VStack {
                ProgressView()
                Text("Loading next page...")
            }
            Spacer()
        }
        .onAppear {
            viewModel.fetchNextPage()
        }
    }
}
