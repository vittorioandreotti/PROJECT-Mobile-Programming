package it.univpm.mobile_programming_project.utils.shared_preferences;

import android.content.Context;

public class UtenteSharedPreferences extends SharedPreferencesHelper {

    private static final String tipoPreferences = "PROFILO";

    private static final String keyNome    = "Nome";
    private static final String keyCognome = "Cognome";
    private static final String keyEmail   = "Email";

    private static final String keyIdCasa         = "IdCasa";
    private static final String keyNomeCasa       = "NomeCasa";
    private static final String keyIndirizzoCasa  = "IndirizzoCasa";

    //    new UtenteSharedPreferencesHelper(this.getApplicationContext())  // DA ACTIVITY
    //    new UtenteSharedPreferencesHelper(getActivity().getApplicationContext())  // DA FRAGMENT

    protected UtenteSharedPreferences(Context context) {
        super(context, UtenteSharedPreferences.tipoPreferences);
    }

    public String getNome() {
        return this.getString(keyNome);
    }
    public void setNome( String nome ) {
        this.setString(keyNome, nome);
    }

    public String getCognome() {
        return this.getString(keyCognome);
    }
    public void setCognome( String cognome ) {
        this.setString(keyCognome, cognome);
    }

    public String getEmail() {
        return this.getString(keyEmail);
    }
    public void setEmail( String email ) {
        this.setString(keyEmail, email);
    }

    public String getIdCasa() {
        return this.getString(keyIdCasa);
    }
    public void setIdCasa( String idCasa ) {
        this.setString(keyIdCasa, idCasa);
    }

    public String getNomeCasa() {
        return this.getString(keyNomeCasa);
    }
    public void setNomeCasa( String nomeCasa ) {
        this.setString(keyNomeCasa, nomeCasa);
    }

    public String getIndirizzoCasa() {
        return this.getString(keyIndirizzoCasa);
    }
    public void setIndirizzoCasa( String indirizzoCasa ) {
        this.setString(keyIndirizzoCasa, indirizzoCasa);
    }


}
