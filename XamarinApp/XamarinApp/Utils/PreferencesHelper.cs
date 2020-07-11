using Android.Content;
using Java.Lang;
using Java.Util;
using System;
using System.Collections.Generic;
using System.Text;
using Xamarin.Essentials;
using Boolean = Java.Lang.Boolean;

namespace XamarinApp.Utils
{
    abstract class PreferencesHelper
    {
        // https://docs.microsoft.com/en-us/xamarin/essentials/preferences?tabs=android
        // private Preferences Preferences;
        private Context Context;

        PreferencesHelper(Context Context, string TipoPreferences)
        {
            this.Context = Context;
            // this.Preferences = Context.GetSharedPreferences(TipoPreferences, Context.MODE_PRIVATE);
        }

        /*

        public Boolean Contains(string Key)
        {

            return Preferences.ContainsKey(Key);
        }

        public void ClearPreferences()
        {
            Preferences.Editor Editor = this.Preferences.Edit();
            Editor.clear();
            Editor.apply();
        }

        string Getstring(string Key)
        {
            if (this.Contains(Key))
                return Preferences.Get(Key, "");
            return null;
        }

        Integer GetInteger(string Key)
        {
            if (this.Contains(Key))
                return this.Preferences.GetInt(Key, 0);
            return null;
        }

        Boolean GetBoolean(string Key)
        {
            if (this.Contains(Key))
                return this.Preferences.GetBoolean(Key, false);
            return null;
        }

        Long GetLong(string Key)
        {
            if (this.Contains(Key))
                return this.Preferences.GetLong(Key, 0);
            return null;
        }

        Set<string> GetstringSet(string Key)
        {
            if (this.Contains(Key))
                return this.Preferences.GetstringSet(Key, null);
            return null;
        }


        void Setstring(string Key, string Value)
        {
            Preferences.Editor editor = this.Preferences.edit();
            editor.putstring(Key, Value);
            editor.commit();
        }

        void SetInteger(string Key, Integer Value)
        {
            Preferences.Editor editor = this.Preferences.edit();
            editor.putInt(Key, Value);
            editor.commit();
        }

        void SetBoolean(string Key, Boolean Value)
        {
            Preferences.Editor editor = this.Preferences.edit();
            editor.putBoolean(Key, Value);
            editor.commit();
        }

        void SetLong(string Key, Long Value)
        {
            Preferences.Editor editor = this.Preferences.edit();
            editor.putLong(Key, Value);
            editor.commit();
        }

        void SetstringSet(string Key, Set<string> Value)
        {
            Preferences.Editor editor = this.Preferences.edit();
            editor.putstringSet(Key, Value);
            editor.commit();
        }
        */
    }
}
