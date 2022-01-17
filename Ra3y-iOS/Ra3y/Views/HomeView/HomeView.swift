//
//  ContentView.swift
//  Ra3y
//
//  Created by Omar Elewa on 17/01/2022.
//

import SwiftUI

struct HomeView: View {
    
    @StateObject var viewModel = HomeViewModel()
    
//    @State var isLoginViewPresented = false
    
    var body: some View {
        VStack {
            VStack {
                Spacer()
                Text("Ra3y")
                    .font(.largeTitle)
            }
            VStack{
                Text("The Pet Sitter App")
                    .font(.subheadline)
                Spacer()
                Button {
                    viewModel.isLoginViewPresented = true
                } label:{
                    LoginButton(title: "Sign In", backgroundColor: Color(.systemBlue))
                }
                Button {
                
                } label: {
                    LoginButton(title: "Sign Up", backgroundColor: Color(.systemCyan))
                }
            }
            .fullScreenCover(isPresented: $viewModel.isLoginViewPresented){
                 LoginView()
            }
        }
        
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
