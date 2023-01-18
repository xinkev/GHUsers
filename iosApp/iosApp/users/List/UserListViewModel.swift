//
// Created by xinkev on 17/01/2023.
// Copyright (c) 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

@MainActor class UserListViewModel: ObservableObject {
    @Published var users = [User]()
    private let pager: Pager<KotlinInt, User>
    var hasNextPage = false
    var shouldDisplayNextPage: Bool { hasNextPage }

    init(repo: GithubUserRepository) {
        pager = repo.getList(coroutineScope: ExposedKt.defaultCoroutineScopeForIOS)
    }

    func fetchUsers() {
        ExposedKt.collect(flow: pager.pagingData) { [unowned self] data in
            DispatchQueue.main.async {
                guard let pagingData = data as? Array<User?> else {return}
                let list = pagingData.compactMap({ $0 })
                self.users = list
                self.hasNextPage = self.pager.hasNextPage
            }
        }
    }

    func fetchNextPage() {
        pager.loadNext()
    }

}
