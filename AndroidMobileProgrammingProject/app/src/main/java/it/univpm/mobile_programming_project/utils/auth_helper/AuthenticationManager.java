package it.univpm.mobile_programming_project.utils.auth_helper;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthenticationManager implements AuthenticationDriverInterface {

    private final FirebaseAuth mAuth;
    private AUTH_TYPE authenticationType = null;

    public AuthenticationManager() {
        this.mAuth = FirebaseAuth.getInstance();
    }

    public AuthenticationManager(AUTH_TYPE authenticationType) {
        this();
        this.authenticationType = authenticationType;
    }

    @Override
    public AUTH_TYPE getAuthenticationType() {
        return this.authenticationType;
    }


    @Override
    public FirebaseUser getUser()
    {
        return this.mAuth.getCurrentUser();
    }

    public boolean isLoggedIn()
    {
        FirebaseUser firebaseUser = this.getUser();
        return ( firebaseUser != null );
    }

    @Override
    public void login(OnCompleteListener<AuthResult> authResultOnCompleteListener) {

    }

    @Override
    public void registrazione(OnCompleteListener<AuthResult> authResultOnCompleteListener) {

    }


    @Override
    public boolean logout() {
        // Firebase logout
        this.mAuth.signOut();

        return true;
    }
}
