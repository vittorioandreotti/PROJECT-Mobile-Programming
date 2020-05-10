package it.univpm.mobile_programming_project.utils;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;

public abstract class AuthenticationManager implements AuthenticationDriverInterface {

    private AUTH_TYPE authenticationType = null;

    public AuthenticationManager() {
        //
    }

    public AuthenticationManager(AUTH_TYPE authenticationType) {
        this.authenticationType = authenticationType;
    }

    @Override
    public AUTH_TYPE getAuthenticationType() {
        return this.authenticationType;
    }

}
