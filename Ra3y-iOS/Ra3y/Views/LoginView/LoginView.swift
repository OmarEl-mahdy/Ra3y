//
//  LoginView.swift
//  Ra3y
//
//  Created by Omar Elewa on 17/01/2022.
//

import SwiftUI

struct LoginView: View {
    var body: some View {
        
        VStack{
            Text("Login")
            Spacer()
            Button{
                
            } label: {
                LoginButton(title: "Return", backgroundColor: Color(.systemBlue))
            }
        }
    }
}

struct LoginView_Previews: PreviewProvider {
    static var previews: some View {
        LoginView()
    }
}
