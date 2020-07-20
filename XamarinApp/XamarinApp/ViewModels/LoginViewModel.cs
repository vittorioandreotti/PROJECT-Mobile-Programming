using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Windows.Input;
using Xamarin.Forms;
using System.Text;
using XamarinApp.Utils.Interfaces;

namespace XamarinApp.ViewModels
{
    class LoginViewModel : INotifyPropertyChanged
    {
        public Action DisplayInvalidLoginPrompt;
        public Action<string, string> Login;
        public event PropertyChangedEventHandler PropertyChanged = delegate { };
        
        private string email;
        public string Email
        {
            get { return email; }
            set
            {
                email = value;
                PropertyChanged(this, new PropertyChangedEventArgs(nameof(Email)));
            }
        }
        
        private string password;
        public string Password
        {
            get { return password; }
            set
            {
                password = value;
                PropertyChanged(this, new PropertyChangedEventArgs(nameof(Password)));
            }
        }

        private IFirebaseAuth firebaseAuth;

        public ICommand SubmitCommand { protected set; get; }
        public LoginViewModel(IFirebaseAuth firebaseAuth)
        {
            this.firebaseAuth = firebaseAuth;

            SubmitCommand = new Command(OnSubmit);
        }
        public void OnSubmit()
        {
            if ( email == null || password == null || email.Length ==  0 || password.Length == 0 || !Utils.Helper.IsEmailValid(Email) )
            {
                DisplayInvalidLoginPrompt();
                return;
            }

            Login(Email, Password);

            // Azione di login con firebase
        }
    }
}
