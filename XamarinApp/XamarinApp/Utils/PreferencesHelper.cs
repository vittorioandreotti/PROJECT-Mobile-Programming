using Android.Content;
using Java.Lang;
using Java.Util;
using System;
using String = Java.Lang.String;
using System.Collections.Generic;
using System.Text;
using Xamarin.Essentials;

namespace XamarinApp.Utils
{
    abstract class PreferencesHelper
    {
        // https://docs.microsoft.com/en-us/xamarin/essentials/preferences?tabs=android
        private Preferences Preferences;
        private Context Context;

        PreferencesHelper(Context Context, String TipoPreferences)
        {
            this.Context = Context;
            this.Preferences = Context.GetSharedPreferences(TipoPreferences, Context.MODE_PRIVATE);
        }

        public Boolean Contains(String Key)
        {

            return Preferences.ContainsKey(Key);
        }

        public void ClearPreferences()
        {
            Preferences.Editor Editor = this.Preferences.Edit();
            Editor.clear();
            Editor.apply();
        }

        String GetString(String Key)
        {
            if (this.Contains(Key))
                return Preferences.Get(Key, "");
            return null;
        }

        Integer GetInteger(String Key)
        {
            if (this.Contains(Key))
                return this.Preferences.GetInt(Key, 0);
            return null;
        }

        Boolean GetBoolean(String Key)
        {
            if (this.Contains(Key))
                return this.Preferences.GetBoolean(Key, false);
            return null;
        }

        Long GetLong(String Key)
        {
            if (this.Contains(Key))
                return this.Preferences.GetLong(Key, 0);
            return null;
        }

        Set<String> GetStringSet(String Key)
        {
            if (this.Contains(Key))
                return this.Preferences.GetStringSet(Key, null);
            return null;
        }


        void SetString(String Key, String Value)
        {
            Preferences.Editor editor = this.Preferences.edit();
            editor.putString(Key, Value);
            editor.commit();
        }

        void SetInteger(String Key, Integer Value)
        {
            Preferences.Editor editor = this.Preferences.edit();
            editor.putInt(Key, Value);
            editor.commit();
        }

        void SetBoolean(String Key, Boolean Value)
        {
            Preferences.Editor editor = this.Preferences.edit();
            editor.putBoolean(Key, Value);
            editor.commit();
        }

        void SetLong(String Key, Long Value)
        {
            Preferences.Editor editor = this.Preferences.edit();
            editor.putLong(Key, Value);
            editor.commit();
        }

        void SetStringSet(String Key, Set<String> Value)
        {
            Preferences.Editor editor = this.Preferences.edit();
            editor.putStringSet(Key, Value);
            editor.commit();
        }
    }
}
