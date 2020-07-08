using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Windows.Input;
using Xamarin.Forms;
using System.Text;

namespace XamarinApp.ViewModels
{
    class RegistrazioneViewModel : INotifyPropertyChanged
    {
        
        public Action DisplayInvalidLoginPrompt;
        public event PropertyChangedEventHandler PropertyChanged = delegate { };

        private string nome;
        public string Nome
        {
            get { return nome; }
            set
            {
                nome = value;
                PropertyChanged(this, new PropertyChangedEventArgs("Nome"));
            }
        }

        private string cognome;
        public string Cognome
        {
            get { return cognome; }
            set
            {
                cognome = value;
                PropertyChanged(this, new PropertyChangedEventArgs("Cognome"));
            }
        }

        private string email;
        public string Email
        {
            get { return email; }
            set
            {
                email = value;
                PropertyChanged(this, new PropertyChangedEventArgs("Email"));
            }
        }
        private string password;
        public string Password
        {
            get { return password; }
            set
            {
                password = value;
                PropertyChanged(this, new PropertyChangedEventArgs("Password"));
            }
        }

        private string conferma_password;
        public string ConfermaPassword
        {
            get { return conferma_password; }
            set
            {
                conferma_password = value;
                PropertyChanged(this, new PropertyChangedEventArgs("ConfermaPassword"));
            }
        }
        public ICommand SubmitCommand { protected set; get; }
        public RegistrazioneViewModel()
        {
            SubmitCommand = new Command(OnSubmit);
        }
        public void OnSubmit()
        {
            if (nome.Length == 0 || cognome.Length == 0 || email.Length == 0 || password.Length == 0 || conferma_password.Length == 0)
            {
                DisplayInvalidLoginPrompt();
                return;
            }

            // Azione di registrazione con firebase
        }
    }
}
