using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using Firebase.Auth;
using Xamarin.Forms;
using XamarinApp.Droid.Utils;
using XamarinApp.Utils.Interfaces;

[assembly: Dependency(typeof(AndroidFirebaseAuth))]
namespace XamarinApp.Droid.Utils
{
    class AndroidFirebaseAuth : IFirebaseAuth
    {
        public async Task<string> LoginWithEmailPassword(string email, string password)
        {
            try
            {
                var user = await FirebaseAuth.Instance.SignInWithEmailAndPasswordAsync(email, password);
                var token = await user.User.GetIdTokenAsync(false);
                return token.Token;
            }
            catch (Exception e)
            {
                // e.PrintStackTrace();
                return "";
            }
        }

        public Task<string> SignInWithEmailPassword(string email, string password)
        {
            return LoginWithEmailPassword(email, password);
        }
    }
}