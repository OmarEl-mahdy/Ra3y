//
//  LoginButton.swift
//  Ra3y
//
//  Created by Omar Elewa on 17/01/2022.
//

import SwiftUI

struct LoginButton: View {
    
    let title: String
    let backgroundColor: Color
    
    var body: some View {
        Text(title)
            .font(.headline)
            .fontWeight(.semibold)
            .frame(width: 220, height: 60)
            .background(backgroundColor)
            .foregroundColor(.white)
            .cornerRadius(15.0)
            .padding()
    }
}

struct LoginButton_Previews: PreviewProvider {
    static var previews: some View {
        LoginButton(title: "Login", backgroundColor: .blue)
    }
}
