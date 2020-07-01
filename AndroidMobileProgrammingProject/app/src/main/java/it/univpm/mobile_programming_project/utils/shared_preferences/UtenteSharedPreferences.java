package it.univpm.mobile_programming_project.utils.shared_preferences;

import android.content.Context;

public class UtenteSharedPreferences extends SharedPreferencesHelper {

    private static final String tipoPreferences = "PROFILO";

    private static final String keyInizializzato    = "Inizializzato";

    private static final String keyIdUtente = "IdUtente";
    private static final String keyNome     = "Nome";
    private static final String keyCognome  = "Cognome";
    private static final String keyEmail    = "Email";
    private static final String keyTipo     = "TipoUtente";

    private static final String keyIdCasa         = "IdCasa";
    private static final String keyNomeCasa       = "NomeCasa";
    private static final String keyIndirizzoCasa  = "IndirizzoCasa";

    //    new UtenteSharedPreferencesHelper(this.getApplicationContext())  // DA ACTIVITY
    //    new UtenteSharedPreferencesHelper(getActivity().getApplicationContext())  // DA FRAGMENT

    public UtenteSharedPreferences(Context context) {
        super(context, UtenteSharedPreferences.tipoPreferences);
    }

    public Boolean isInitialized() {
        return this.contains(keyInizializzato) && this.getBoolean(keyInizializzato);
    }
    public void setInitialized() {
        this.setBoolean(keyInizializzato, true);
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

    public String getIdUtente() {
        return this.getString(keyIdUtente);
    }
    public void setIdUtente( String idUtente ) {
        this.setString(keyIdUtente, idUtente);
    }

    public String getTipo() {
        return this.getString(keyTipo);
    }
    public void setTipo( String tipo ) {
        this.setString(keyTipo, tipo);
    }

    public boolean isProprietario() {
        String tipo;
        tipo = this.getTipo();
        if(tipo == null) return false;
        return tipo.toLowerCase().equals("proprietario");
    }

    public boolean isAffittuario() {
        String tipo;
        tipo = this.getTipo();
        if(tipo == null) return false;
        return tipo.toLowerCase().equals("affittuario");
    }
}
