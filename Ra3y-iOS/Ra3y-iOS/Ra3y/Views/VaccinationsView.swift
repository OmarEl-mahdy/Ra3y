//
//  VaccinationsView.swift
//  Ra3y
//
//  Created by Omar Elewa on 19/01/2022.
//

import SwiftUI

struct VaccinationsView: View {
    @EnvironmentObject var viewModel : HomeViewModel
    
    var body: some View {
        List {
            Section(header: Text("Dogs Vaccinations")){
                HStack {
                    Text("Distemper")
                    Toggle(isOn: $viewModel.dogsDistemper) {
                    }
                }
                HStack{
                    Text("DHPP")
                    Toggle(isOn: $viewModel.dogsDHPP) {
                    }
                }
                HStack{
                    Text("Parvovirus")
                    Toggle(isOn: $viewModel.dogsParvovirus) {
                    }
                }
                HStack{
                    Text("Rabies")
                    Toggle(isOn: $viewModel.dogsRabies) {
                    }
                }
            }
            Section(header: Text("Cats Vaccinations")){
                HStack {
                    Text("Distemper")
                    Toggle(isOn: $viewModel.catsDistemper) {
                    }
                }
                HStack{
                    Text("DHPP")
                    Toggle(isOn: $viewModel.catsDHPP) {
                    }
                }
                HStack{
                    Text("Parvovirus")
                    Toggle(isOn: $viewModel.catsParvovirus) {
                    }
                }
                HStack{
                    Text("Rabies")
                    Toggle(isOn: $viewModel.catsRabies) {
                    }
                }
            }
            Section(header: Text("Birds Vaccinations")){
                HStack {
                    Text("Distemper")
                    Toggle(isOn: $viewModel.birdsDistemper) {
                    }
                }
                HStack{
                    Text("DHPP")
                    Toggle(isOn: $viewModel.birdsDHPP) {
                    }
                }
                HStack{
                    Text("Parvovirus")
                    Toggle(isOn: $viewModel.birdsParvovirus) {
                    }
                }
                HStack{
                    Text("Rabies")
                    Toggle(isOn: $viewModel.birdsRabies) {
                    }
                }
            }
        }
        .listStyle(.grouped)
        .navigationBarTitle("Vaccinations")
    }
}


struct VaccinationsView_Previews: PreviewProvider {
    static var previews: some View {
        VaccinationsView()
    }
}
