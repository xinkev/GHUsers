//
// Created by xinkev on 17/01/2023.
// Copyright (c) 2023 orgName. All rights reserved.
//

import shared

class UserDetailsViewModel: ObservableObject {
    @Published private(set) var userDetails: UserDetails? = nil
    @Published private(set) var userRepos: [Repo] = []
    @Published private(set) var showLoading: Bool = true
    @Published private(set) var showReposLoading: Bool = false
    @Published private(set) var showReposNextPage = false

    private var repoPager: Pager<KotlinInt, Repo>? = nil

    private let userRepo: GithubUserRepository
    private let repoRepo: GithubRepoRepository

    init(userRepo: GithubUserRepository, repoRepo: GithubRepoRepository) {
        self.userRepo = userRepo
        self.repoRepo = repoRepo
    }

    func fetchDetails(for username: String) {
        userRepo.getDetails(username: username) { details, err in
            if let err {
                print("An error occurred while fetching user details. \(err.localizedDescription)")
            } else {
                DispatchQueue.main.async {
                    self.userDetails = details
                    self.showLoading = false
                }
            }
        }
    }

    func fetchRepositories(for username: String) {
        showReposLoading = true
        repoPager = repoRepo.getUserRepo(username: username, coroutineScope: ExposedKt.defaultCoroutineScopeForIOS)
        ExposedKt.collect(flow: repoPager!.pagingData) { data in
            DispatchQueue.main.async { [unowned self] in
                guard let pagingData = data as? [Repo?] else { return }
                let list = pagingData.compactMap { $0 }
                self.userRepos = list
                self.showReposNextPage = self.repoPager!.hasNextPage
                self.showReposLoading = false
            }
        }
    }

    func fetchNextRepositoriesPage() {
        repoPager?.loadNext()
    }
}
