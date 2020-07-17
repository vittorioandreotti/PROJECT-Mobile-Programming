using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace XamarinApp.Utils.Interfaces
{
    public interface IFirebaseAuth
    {
        Task<string> LoginWithEmailPassword(string email, string password);
        Task<string> RegisterWithEmailAndPassword(string email, string password);


    }
}
