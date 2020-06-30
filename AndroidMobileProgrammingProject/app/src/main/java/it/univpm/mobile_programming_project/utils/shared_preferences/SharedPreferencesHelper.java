package it.univpm.mobile_programming_project.utils.shared_preferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.Set;

public abstract class SharedPreferencesHelper {

    private SharedPreferences sharedpreferences;
    private Context context;

    SharedPreferencesHelper(Context context, String tipoPreferences) {
        this.context = context;
        this.sharedpreferences = context.getSharedPreferences(tipoPreferences, Context.MODE_PRIVATE);
    }

    public boolean contains(String key) {
        return this.sharedpreferences.contains(key);
    }

    void clearPreferences() {
        this.sharedpreferences.edit().clear().commit();
    }

    String getString( String key ) {
        if(this.contains(key))
            return this.sharedpreferences.getString(key, "");
        return null;
    }

    Integer getInteger( String key ) {
        if(this.contains(key))
            return this.sharedpreferences.getInt(key, 0);
        return null;
    }

    Boolean getBoolean( String key ) {
        if(this.contains(key))
            return this.sharedpreferences.getBoolean(key, false);
        return null;
    }

    Long getLong( String key ) {
        if(this.contains(key))
            return this.sharedpreferences.getLong(key, 0);
        return null;
    }

    Set<String> getStringSet( String key ) {
        if(this.contains(key))
            return this.sharedpreferences.getStringSet(key, null);
        return null;
    }


    void setString(String key, String value) {
        SharedPreferences.Editor editor = this.sharedpreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    void setInteger( String key, Integer value ) {
        SharedPreferences.Editor editor = this.sharedpreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    void setBoolean( String key, Boolean value ) {
        SharedPreferences.Editor editor = this.sharedpreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    void setLong( String key, Long value ) {
        SharedPreferences.Editor editor = this.sharedpreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    void setStringSet( String key, Set<String> value ) {
        SharedPreferences.Editor editor = this.sharedpreferences.edit();
        editor.putStringSet(key, value);
        editor.commit();
    }
}
