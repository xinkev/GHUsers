//
// Created by xinkev on 17/01/2023.
// Copyright (c) 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

struct UserDetailsView: View {
    let username: String

    @StateObject var viewModel = UserDetailsViewModel(
        userRepo: RepositoryResolver.shared.githubUserRepository,
        repoRepo: RepositoryResolver.shared.githubRepoRepository
    )

    var body: some View {
        VStack(alignment: .center) {
            if viewModel.showLoading {
                ProgressView()
            } else {
                header
                    .padding()
                    .onAppear {
                        viewModel.fetchRepositories(for: username)
                    }
            }
            if let bio = viewModel.userDetails?.bio {
                Text("Bio")
                        .font(.title3)
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .padding([.leading, .trailing])
                Text(bio)
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .padding([.leading, .trailing])
                        .padding(.bottom, 8)
            }
            if viewModel.showReposLoading {
                ProgressView()
            } else {
                UserRepositoriesView(userRepos: viewModel.userRepos)
                        .environmentObject(viewModel)
            }
        }
        .onAppear { viewModel.fetchDetails(for: username) }
    }

    @ViewBuilder
    var header: some View {
        if let details = viewModel.userDetails {
            VStack {
                Avatar(path: details.avatarUrl)
                        .frame(width: 114, height: 114)
                        .padding(.bottom, 8)

                if let name = details.name {
                    Text(name).font(.title)
                }

                Text("@\(username)")
                        .font(.title3)
                        .opacity(0.6)

                HStack {
                    UserStat(label: "followers", numbers: details.followers)
                    Spacer()
                    UserStat(label: "following", numbers: details.following)
                    Spacer()
                    UserStat(label: "repos", numbers: details.publicRepos)
                    Spacer()
                    UserStat(label: "gists", numbers: details.publicGists)
                }.padding(.top, 8)

                UserVCard(contactInfo: details.contactInfo)
            }
        } else {
            Spacer()
        }
    }
}

private class DetailsView_Preview: PreviewProvider {
    static var previews: some View {
        UserDetailsView(username: "xinkev")
    }
}
