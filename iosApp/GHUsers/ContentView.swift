//
//  ContentView.swift
//  GHUsers
//
//  Created by xinkev on 26/12/2022.
//

import data
import SwiftUI

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel

    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundColor(.accentColor)
            Text(viewModel.text)
        }
        .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView(viewModel: ContentView.ViewModel())
    }
}

extension ContentView {
    class ViewModel: ObservableObject {
        @Published var text = "Loading..."
        init() {
            let api = GithubAPI()
            api.greeting { data, err in
                DispatchQueue.main.async {
                    if let data = data {
                        self.text = data
                    } else {
                        self.text = err?.localizedDescription ?? "oh"
                    }
                }
            }
        }
    }
}
