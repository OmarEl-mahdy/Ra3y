//
//  UserTypeView.swift
//  Ra3y
//
//  Created by Omar Elewa on 18/01/2022.
//

import SwiftUI

struct UserTypeView: View {
    
    @EnvironmentObject var viewModel : HomeViewModel
    
    var body: some View {
        VStack{
            HStack {
                Text("Are you a pet owner today?")
                    .font(.headline)
                Spacer()
            }
            NavigationLink(destination: FinderView()){
                Button(action:{
                    viewModel.userTypeSelection = "owner"
                }, label:{
                    Image("petOwner")
                        .resizable(capInsets: EdgeInsets(top: 4.0, leading: 4.0, bottom: -2.0, trailing: 4.0))
                        .aspectRatio(contentMode: .fill)
                })
            }
            Spacer()
            HStack {
                Spacer()
                Text("Or a pet sitter?")
                    .font(.headline)
                
            }
                    
                NavigationLink(destination: FinderView()){
                    Button(action:{
                        viewModel.userTypeSelection = "sitter"
                    }, label:{
                        Image("petSitter")
                            .resizable(capInsets: EdgeInsets(top: 0.0, leading: 4.0, bottom: -2.0, trailing: 4.0)).aspectRatio(contentMode: .fill)
                    })
                }
            
        }.padding(40)
    }
}

struct UserTypeView_Previews: PreviewProvider {
    static var previews: some View {
        UserTypeView()
    }
}
