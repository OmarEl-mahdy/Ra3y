//
//  SignupViewModel.swift
//  Ra3y
//
//  Created by Omar Elewa on 20/01/2022.
//

import SwiftUI
import Combine

class SignupViewModel: ObservableObject {
    // Input
    @Published var username = ""
    @Published var password = ""
    @Published var confirmPassword = ""
    
    // Input
    @Published var isValid = false
    @Published var usernameMessage = " "
    @Published var passwordMessage = " "
    
    private var cancellableSet: Set<AnyCancellable> = []
    
    init() {
        usernameValidPublisher
            .receive(on: RunLoop.main)
            .map { $0 ? " "
                : "Username must be at least 6 characters long" }
            .assign(to: \.usernameMessage, on: self)
            .store(in: &cancellableSet)
        
        passwordValidPublisher
            .receive(on: RunLoop.main)
            .map { passwordCheck in
                switch passwordCheck {
                case .invalidLength:
                    return "Password must be at least 8 characters long"
                case .noMatch:
                    return "Passwords don't match"
                case .weakPassword:
                    return "Password is too weak"
                default:
                    return " "
                }
            }
            .assign(to: \.passwordMessage, on: self)
            .store(in: &cancellableSet)
        
        formValidPublisher
            .receive(on: RunLoop.main)
            .assign(to: \.isValid, on: self)
            .store(in: &cancellableSet)
        
    }
}


private extension SignupViewModel {
    var usernameValidPublisher: AnyPublisher<Bool, Never> {
        $username
            .debounce(for: 0.5, scheduler: RunLoop.main)
            .removeDuplicates()
            .map { $0.count >= 5 }
            .eraseToAnyPublisher()
    }
    
    var validPasswordLengthPublisher: AnyPublisher<Bool, Never> {
        $password
            .debounce(for: 0.5, scheduler: RunLoop.main)
            .removeDuplicates()
            .map { $0.count >= 8 }
            .eraseToAnyPublisher()
    }
    
    var strongPasswordPublisher: AnyPublisher<Bool, Never> {
        $password
            .debounce(for: 0.2, scheduler: RunLoop.main)
            .removeDuplicates()
            .map(\.isStrong)
            .eraseToAnyPublisher()
    }
    
    var matchingPasswordsPublisher: AnyPublisher<Bool, Never> {
        Publishers
            .CombineLatest($password, $confirmPassword)
            .debounce(for: 0.2, scheduler: RunLoop.main)
            .map { password, confirmedPassword in
                password == confirmedPassword
            }
            .eraseToAnyPublisher()
    }
    
    var passwordValidPublisher: AnyPublisher<PasswordCheck, Never> {
        Publishers
            .CombineLatest3(validPasswordLengthPublisher,
                            strongPasswordPublisher,
                            matchingPasswordsPublisher)
            .map { validLength, strong, matching in
                if (!validLength) {
                    return .invalidLength
                }
                if (!strong) {
                    return .weakPassword
                }
                if (!matching) {
                    return .noMatch
                }
                return .valid
            }
            .eraseToAnyPublisher()
    }
    
    var formValidPublisher: AnyPublisher<Bool, Never> {
        Publishers
            .CombineLatest(usernameValidPublisher, passwordValidPublisher)
            .map { usernameIsValid, passwordIsValid in
                usernameIsValid && (passwordIsValid == .valid)
            }
            .eraseToAnyPublisher()
    }
}

enum PasswordCheck {
    case valid
    case invalidLength
    case noMatch
    case weakPassword
}

extension String {
    var isStrong: Bool {
        containsACharacter(from: .lowercaseLetters) &&
        containsACharacter(from: .uppercaseLetters) &&
        containsACharacter(from: .decimalDigits) &&
        containsACharacter(from: .alphanumerics.inverted)
    }
    
    private func containsACharacter(from set: CharacterSet) -> Bool {
        rangeOfCharacter(from: set) != nil
    }
}

struct CustomStyle: ViewModifier {
    func body(content: Content) -> some View {
        content
            .frame(height: 40)
            .background(Color.white)
            .cornerRadius(5)
    }
}

extension TextField {
    func custom() -> some View {
        modifier(CustomStyle())
            .autocapitalization(.none)
    }
}

extension SecureField {
    func custom() -> some View {
        modifier(CustomStyle())
    }
}

struct SignUpNewView: View {
    @ObservedObject private var signupViewModel = SignupViewModel()
    @EnvironmentObject var viewModel : HomeViewModel
    
    @State private var showingImagePicker = false
    @State private var image:Image?
    @State private var registered:String? = nil
    @State private var lessThanTwo = false
    
    var body: some View {
        ScrollView {
                ZStack {
                    VStack(spacing: 24) {
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
                        
                        VStack(alignment: .leading) {
                            Text(signupViewModel.usernameMessage).foregroundColor(.red)
                            TextField("Username", text: $signupViewModel.username)
                                .padding()
                                .background(Color.lightGreyColor)
                                .cornerRadius(5.0)
                        }
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
                        }
                        VStack(alignment: .leading) {
                            Text(signupViewModel.passwordMessage).foregroundColor(.red)
                            SecureField("Password", text: $signupViewModel.password)
                                .padding()
                                .background(Color.lightGreyColor)
                                .cornerRadius(5.0)
                            SecureField("Repeat Password", text: $signupViewModel.confirmPassword)
                                .padding()
                                .background(Color.lightGreyColor)
                                .cornerRadius(5.0)
                            Toggle(isOn: $lessThanTwo){
                                Text("Are you over 18?")
                            }
                            
                        }
                        NavigationLink(destination: FinderView(),
                                       tag: "SignInOwner",
                                       selection: $registered
                        )
                        {
                            EmptyView()
                        }
                        
                        Button {
                            print("Succesfully registered!")
                        } label: {
                            Text("Register")
                                .font(.headline)
                                .fontWeight(.semibold)
                                .frame(width: 220, height: 60)
                                .background(Color.ra3y)
                                .foregroundColor(.white)
                                .cornerRadius(15.0)
                        }.disabled(!signupViewModel.isValid)
                    }
                    .padding(.horizontal, 24)
                }
                .edgesIgnoringSafeArea(.all)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        SignUpNewView()
    }
}
