//
//  ContentView.swift
//  Ra3y
//
//  Created by Omar Elewa on 17/01/2022.
//

import SwiftUI

struct HomeView: View {
    @EnvironmentObject var viewModel : HomeViewModel
    @State private var selection:String? = nil
    @State private var showingOptions = false
    @State private var selection_ = "None"
    
    var body: some View {
        NavigationView{
            VStack
            {
                VStack
                {
                    Spacer()
                    Image("ra3yyy")
                }
                VStack
                {
                        Text("The Pet Sitter App")
                            .font(.subheadline)
                            .foregroundColor(Color.ra3y)
                        
                    Spacer()
                    
                    NavigationLink(destination: LoginView(),
                                   tag: "Login",
                                   selection: $viewModel.navigator)
                    {
                        EmptyView()
                    }.isDetailLink(false)
                    NavigationLink(destination: SignUpNewView(),
                                   tag: "SignUp",
                                   selection: $viewModel.navigator)
                    {
                        EmptyView()
                    }.isDetailLink(false)
                    
                    Picker("What describes you best?",
                           selection: $viewModel.userTypeSelection
                           )
                    {
                        Text("I am a pet owner").tag("owner")
                        Text("I am a pet sitter").tag("sitter")
                    }
                    Button
                    {
                        viewModel.selection = "Login"
                        selection = "Login"
                        viewModel.navigator = "Login"
                        showingOptions = true
//                            
                    } label:{
                        LoginButton(title: "Sign In", backgroundColor: Color.ra3y)
                    }
                    
                    Button (action:{
                        selection = "SignUp"
                        viewModel.navigator = "SignUp"
                    }, label: {
                        LoginButton(title: "Sign Up", backgroundColor: Color(.systemCyan))
                    })
                    
                }
                
            }.navigationBarHidden(true)
        }
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView().environmentObject(HomeViewModel())
    }
}
