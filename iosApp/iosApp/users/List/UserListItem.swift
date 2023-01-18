//
// Created by xinkev on 17/01/2023.
// Copyright (c) 2023 orgName. All rights reserved.
//
import shared
import SwiftUI

struct UserListItem: View {
    let user: User

    var body: some View {
        HStack {
            Avatar(path: user.avatar)
                    .frame(width: 48, height: 48)
            Text(user.username)
                .font(.caption)
            Spacer()
            if user.isAdmin {
                UserType(type: "Admin", isAdmin: true)
                    .padding(.trailing, 8)
            }
            UserType(type: user.type)
        }
    }
}

fileprivate struct UserType: View {
    let type: String
    var isAdmin: Bool = false
    var backgroundColor: Color {
        if isAdmin {
            return Color(red: 0.88, green: 0.40, blue: 0.27)
        } else {
            return Color(red: 0.38, green: 0.00, blue: 0.93)
        }
    }

    var body: some View {
        Text(type)
            .font(.footnote)
            .padding(.horizontal, 4)
            .padding(.vertical, 2)
            .background(backgroundColor)
            .foregroundColor(.white)
            .clipShape(RoundedRectangle(cornerRadius: 5))
    }
}

