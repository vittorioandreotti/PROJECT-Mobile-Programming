using Xamarin.Essentials;

namespace XamarinApp.Utils.Helpers
{
     public abstract class PreferencesHelper
    {
  

        public bool Contains(string Key)
        {

            return Preferences.ContainsKey(Key);
        }

        public void ClearPreferences()
        {        
            Preferences.Clear();
            
        }

        protected string GetString(string Key)
        {
            if (this.Contains(Key))
                return Preferences.Get(Key, "");
            return null;
        }

        protected int GetInteger(string Key)
        {
            if (this.Contains(Key))
                return Preferences.Get(Key, 0);
            return 0;
        }

        protected bool GetBoolean(string Key)
        {
            if (this.Contains(Key))
                return Preferences.Get(Key, false);
            return false;
        }

        protected long GetLong(string Key)
        {
            if (this.Contains(Key))
                return Preferences.Get(Key, 0);
            return 0;
        }




        protected void SetString(string Key, string Value)
        {
            Preferences.Set(Key, Value);
        }

        protected void SetInteger(string Key, int Value)
        {
            Preferences.Set(Key, Value);
        }

        protected void SetBoolean(string Key, bool Value)
        {
           Preferences.Set(Key, Value);
        }

        protected void SetLong(string Key, long Value)
        {
            Preferences.Set(Key, Value);
        }



    }
}
