//
//  VerificationCode.swift
//  Ra3y
//
//  Created by Omar Elewa on 18/01/2022.
//

import SwiftUI

struct VerificationCode: View {
    
    @EnvironmentObject var viewModel : HomeViewModel
    @State private var selection: String? = nil
    
    @State var code :String = ""
    var body: some View {
        VStack{
            Text("Please enter verification code")
                .font(.title)
            TextField("", text: $code){
                
            }.padding()
                .background(Color.lightGreyColor)
                .cornerRadius(5.0)
                .padding(.bottom, 20)
            NavigationLink(destination: LoginView(), tag: "Send", selection: $selection){EmptyView()}
            Button{
                selection="Send"
            } label:{
                LoginButton(title: "Proceed", backgroundColor: Color.ra3y)
            }
        }.padding()
    }
}

struct VerificationCode_Previews: PreviewProvider {
    static var previews: some View {
        VerificationCode()
    }
}
