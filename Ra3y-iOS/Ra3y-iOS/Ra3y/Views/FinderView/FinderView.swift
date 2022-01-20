//
//  FinderView.swift
//  Ra3y
//
//  Created by Omar Elewa on 18/01/2022.
//

import SwiftUI

struct FinderView: View {
    @EnvironmentObject var viewModel : HomeViewModel
    
    var body: some View {
            VStack{
                Text("Select a suitable option")
                    .font(.largeTitle)
                
                Spacer()
                Group{
                    NavigationLink(destination: RequestView(),
                                   tag: "Request",
                                   selection: $viewModel.finderMode
                    )
                    {
                        EmptyView()
                    }.isDetailLink(false)
                    NavigationLink(destination: VaccinationsView(),
                                   tag: "Vaccinations",
                                   selection: $viewModel.finderMode
                    )
                    {
                        EmptyView()
                    }.isDetailLink(false)
                    NavigationLink(destination: ServicesView(),
                                   tag: "Services",
                                   selection: $viewModel.finderMode
                    )
                    {
                        EmptyView()
                    }.isDetailLink(false)
                }
                
                Group{
                    Button(
                        action:
                        {
                            viewModel.finderMode = "Request"
                        }, label:
                        {
                            LoginButton(title: "Request", backgroundColor: Color.ra3y)
                        }
                    )
                    
                    Button(
                        action:
                        {
                            viewModel.finderMode = "Vaccinations"
                        }, label:
                        {
                            LoginButton(title: "Vaccinations", backgroundColor: Color.ra3y)
                        }
                    )
                
                    Button(
                        action:
                        {
                            viewModel.finderMode = "Services"
                        }, label:
                        {
                            LoginButton(title: "Services", backgroundColor: Color.ra3y)
                        }
                    )
                }
              
                Spacer()
            }
    }
}

struct FinderView_Previews: PreviewProvider {
    static var previews: some View {
        FinderView()
    }
}
