//
//  StarterView.swift
//  Ra3y
//
//  Created by Omar Elewa on 19/01/2022.
//

import SwiftUI

struct StarterView: View {
    @EnvironmentObject var viewModel : HomeViewModel
    @State private var selection:String? = nil
    
    var body: some View {
        NavigationView{
            VStack {
                VStack {
                    Spacer()
                    Image("ra3yyy")
                    Spacer()
                    NavigationLink(destination: UserTypeView()) {
                        Button{
                            
                        }label:{
                            Text("Proceed")
                            
                        }
                    }
                }
                
            }.navigationBarHidden(true)
        }//.environmentObject(viewModel)
    }
}

struct StarterView_Previews: PreviewProvider {
    static var previews: some View {
        StarterView()
    }
}
