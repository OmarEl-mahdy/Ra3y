//
//  SitterView.swift
//  Ra3y
//
//  Created by Omar Elewa on 20/01/2022.
//

import SwiftUI

struct SitterView: View {
    @State var rate: Float = 50
    @State var mode: String = "Requests"
    
    var body: some View {
        TabView(selection: $mode) {
            VStack{
                List{
                    Section("Requests"){
                        VStack{
                            HStack{
                                Text("Salma is requesting...")
                                Spacer()
                            }
                            Spacer()
                            HStack{
                                Button{}label:{Text("Accept")}
                                Spacer()
                                Button{}label:{Text("Decline").foregroundColor(.red)}
                            }
                        }
                        VStack{
                            HStack{
                                Text("Mahdy is requesting...")
                                Spacer()
                            }
                            Spacer()
                            HStack{
                                Button{}label:{Text("Accept")}
                                Spacer()
                                Button{}label:{Text("Decline").foregroundColor(.red)}
                            }
                        }
                        VStack{
                            HStack{
                                Text("Ashraf is requesting...")
                                Spacer()
                            }
                            Spacer()
                            HStack{
                                Button{}label:{Text("Accept")}
                                Spacer()
                                Button{}label:{Text("Decline").foregroundColor(.red)}
                            }
                        }
                    }
                    
                }
            }.tabItem { Image(systemName: "bell.fill") }.tag("Requests")
            
            VStack(){
                
                List{
                    Text("Account Details").font(.title2)
                    Section("Name"){
                            Text("First Name")
                            Text("Last Name")
                    }
                    Section("Email"){
                        HStack{
                            Image(systemName: "envelope.fill")
                            Text("user@ra3y.pet")
                        }
                    }
                    Section("Phone"){
                        HStack{
                            Image(systemName: "phone.fill")
                            Text("01007788991")
                        }
                    }
                    Section("Rate/Hour"){
                        Text("$\(rate, specifier: "%.2f")")
                        Slider(value: $rate, in: 0...100)
                    }
                }
            }.tabItem { Image(systemName: "person.crop.circle.fill") }.tag("Account")
        }.navigationTitle("Sitter")
        .navigationBarTitleDisplayMode(.inline)
    }
}


struct SitterView_Previews: PreviewProvider {
    static var previews: some View {
        SitterView()
    }
}
