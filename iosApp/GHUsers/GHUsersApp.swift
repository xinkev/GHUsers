//
//  GHUsersApp.swift
//  GHUsers
//
//  Created by xinkev on 26/12/2022.
//

import SwiftUI

@main
struct GHUsersApp: App {
    var body: some Scene {
        
        WindowGroup {
            ContentView(viewModel: ContentView.ViewModel())
        }
    }
}
