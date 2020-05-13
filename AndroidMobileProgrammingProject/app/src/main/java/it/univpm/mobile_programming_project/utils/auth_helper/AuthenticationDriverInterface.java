package it.univpm.mobile_programming_project.utils.auth_helper;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;

public interface AuthenticationDriverInterface {

    public enum AUTH_TYPE {
            GOOGLE,
            EMAIL_AND_PASSWORD,
            FACEBOOK
    };

    public boolean isLoggedIn();

    public void login(OnCompleteListener<AuthResult> authResultOnCompleteListener);

    public void registrazione(OnCompleteListener<AuthResult> authResultOnCompleteListener);

    public boolean logout();

    public AUTH_TYPE getAuthenticationType();

    public Object getUser();

}
