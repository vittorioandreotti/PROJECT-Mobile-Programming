using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Firebase.Auth;
using Foundation;
using UIKit;
using XamarinApp.Utils.Interfaces;

namespace XamarinApp.iOS
{
    class IosFirebaseAuth : IFirebaseAuth
    {
        public async Task<string> LoginWithEmailPassword(string email, string password)
        {
            try
            {
                var user = await Auth.DefaultInstance.SignInWithPasswordAsync(email, password);
                return await user.User.GetIdTokenAsync();
            }
            catch (Exception e)
            {
                return "";
            }

        }

        public Task<string> SignInWithEmailPassword(string email, string password)
        {
            return LoginWithEmailPassword(email, password);
        }
    }
}