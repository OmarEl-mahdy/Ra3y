//
//  RequestView.swift
//  Ra3y
//
//  Created by Omar Elewa on 19/01/2022.
//

import SwiftUI
import CoreLocationUI
import CoreLocation

struct RequestView: View {
    @EnvironmentObject var viewModel : HomeViewModel
    @State private var startDate = Date()
    @State private var endDate = Date()
    @StateObject var locationManager = LocationManager()

    var body: some View {
        HStack{
            Text("Please Provide the following details:").padding()
            Spacer()
        }
        Form{
            Section("Location"){

                if let location = locationManager.location {
                    Text("Your location: \(location.latitude), \(location.longitude)")
                }

                LocationButton {
                    locationManager.requestLocation()
                }
            }
            Section("Duration"){
                DatePicker(selection: $startDate, in: Date()..., displayedComponents: .date)
                {
                    Text("From")
                }
                DatePicker(selection: $endDate, in: Date()..., displayedComponents: .date)
                {
                    Text("To")
                }
            }
            Section("Additional Information"){
                TextEditor(text: $viewModel.additionalInformation)
            }
        }.navigationBarTitle("Request")
    }
}

struct RequestView_Previews: PreviewProvider {
    static var previews: some View {
        RequestView()
    }
}
