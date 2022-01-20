//
//  SignUpView.swift
//  Ra3y
//
//  Created by Omar Elewa on 18/01/2022.
//

import SwiftUI

struct SignUpView: View {
    @EnvironmentObject var viewModel : HomeViewModel

    @State private var showingImagePicker = false
    @State private var image:Image?
    
    
    var body: some View {
        ScrollView {
        VStack {
            HStack {
                Text("Create your new account")
                    .font(.title)
                    .padding()
                Spacer()
            }
                
            
            ZStack {
                Rectangle()
                        .fill(.secondary)
                        .frame(minWidth: 300, idealWidth: .infinity, maxWidth: .infinity, minHeight: 300, idealHeight: 500, maxHeight: 500)

                Text("Tap to select a picture")
                        .foregroundColor(.white)
                        .font(.headline)

                image?
                        .resizable()
                        .scaledToFit()
                }
                    .onTapGesture {
                        // select an image
                    }
            Spacer(minLength: 30)
            
            HStack {
                TextField("First Name", text: $viewModel.signupFirstName)
                    .padding()
                    .background(Color.lightGreyColor)
                    .cornerRadius(5.0)
                
                TextField("Last Name", text: $viewModel.signupLastName)
                    .padding()
                    .background(Color.lightGreyColor)
                    .cornerRadius(5.0)
            }
            Group{
                TextField("Email", text: $viewModel.signupEmail)
                    .padding()
                    .background(Color.lightGreyColor)
                    .cornerRadius(5.0)
        
                TextField("Phone Number", text: $viewModel.signupPhoneNumber)
                    .padding()
                    .background(Color.lightGreyColor)
                    .cornerRadius(5.0)
        
                TextField("Password", text: $viewModel.signupPassword)
                    .padding()
                    .background(Color.lightGreyColor)
                    .cornerRadius(5.0)
        
                TextField("Confirm Password", text: $viewModel.signupConfirmPassword)
                    .padding()
                    .background(Color.lightGreyColor)
                    .cornerRadius(5.0)
            }
            
                
            }.padding(EdgeInsets(top: 0, leading: 10, bottom: 0, trailing: 10))
            
            Button {
            } label:{
                Text("Sign Up")
                    .font(.headline)
                    .fontWeight(.semibold)
                    .frame(width: 220, height: 60)
                    .background(Color.ra3y)
                    .foregroundColor(.white)
                    .cornerRadius(15.0)
                
            }.padding(EdgeInsets(top: 0, leading: 0, bottom: 15, trailing: 0))
            
        }.edgesIgnoringSafeArea(.bottom)
            
    }
}

struct SignUpView_Previews : PreviewProvider {
    static var previews: some View {
        SignUpView()
.previewInterfaceOrientation(.portrait)
    }
}
