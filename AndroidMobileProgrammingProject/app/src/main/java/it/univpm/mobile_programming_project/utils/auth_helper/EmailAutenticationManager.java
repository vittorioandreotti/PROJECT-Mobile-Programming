package it.univpm.mobile_programming_project.utils.auth_helper;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailAutenticationManager extends AuthenticationManager {

    private FirebaseAuth mAuth;

    private String email = null;
    private String password = null;

    EmailAutenticationManager( )
    {
        super(AUTH_TYPE.EMAIL_AND_PASSWORD);
        this.mAuth = FirebaseAuth.getInstance();
    }

    EmailAutenticationManager( String email, String password )
    {
        this();
        setCredentials(email, password);
    }

    public void setCredentials(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

    @Override
    public void login(OnCompleteListener<AuthResult> authResultOnCompleteListener) {
        if(this.isLoggedIn()) return;

        this.mAuth
                .signInWithEmailAndPassword(this.email, this.password)
                .addOnCompleteListener(authResultOnCompleteListener);
    }

    @Override
    public void registrazione(OnCompleteListener<AuthResult> authResultOnCompleteListener) {
        if(this.isLoggedIn()) return;

        this.mAuth
                .createUserWithEmailAndPassword(this.email, this.password)
                .addOnCompleteListener(authResultOnCompleteListener);
    }


}
