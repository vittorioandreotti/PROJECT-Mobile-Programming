package it.univpm.mobile_programming_project.utils.auth_helper;

import android.app.Activity;
import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import it.univpm.mobile_programming_project.R;
import it.univpm.mobile_programming_project.fragment.autenticazione.GoogleAuthFragment;

public class GoogleAutenticationManager extends AuthenticationManager {

    public static final int GOOGLE_SIGN_IN = 1;

    private FirebaseAuth mAuth;

    private GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient mGoogleSignInClient;

    // Utilizzo il fragment per ottenere il risultato di startActivityForResult
    private GoogleAuthFragment googleAuthFragment;



    public GoogleAutenticationManager( GoogleAuthFragment googleAuthFragment )
    {
        super(AUTH_TYPE.GOOGLE);
        this.googleAuthFragment = googleAuthFragment;

        Activity activity = googleAuthFragment.getActivity();

        this.mAuth = FirebaseAuth.getInstance();
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        this.googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getString(R.string.server_client_id))
                .requestEmail()
                .requestProfile()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        this.mGoogleSignInClient = GoogleSignIn.getClient(googleAuthFragment.getActivity(), this.googleSignInOptions);

        //// DELETE ME! IT'S FOR DEBUG.
        //// DELETE ME! IT'S FOR DEBUG.
        //// DELETE ME! IT'S FOR DEBUG.
        this.logout();
        //// DELETE ME! IT'S FOR DEBUG.
        //// DELETE ME! IT'S FOR DEBUG.
        //// DELETE ME! IT'S FOR DEBUG.
    }


    @Override
    public void login(OnCompleteListener<AuthResult> authResultOnCompleteListener) {
        if(this.isLoggedIn()) return;

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        this.googleAuthFragment.startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }

    public void handleLogin( Intent data, int resultCode, OnCompleteListener<AuthResult> authResultOnCompleteListener )
    {
        // The Task returned from this call is always completed, no need to attach
        // a listener.
        Task<GoogleSignInAccount> completedTask = GoogleSignIn.getSignedInAccountFromIntent(data);

        // Ignora se si preme indietro al momento del login.
        if(resultCode == Activity.RESULT_CANCELED) return;

        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

//            Log.d("ChooseLoginRegisterActivity::handleGoogleSignInResult", "firebaseAuthWithGoogle:" + account.getId());
//            // Signed in successfully with google. Let's sign in to firebase.
            String idToken = account.getIdToken();

            AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
            mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this.googleAuthFragment.getActivity(), authResultOnCompleteListener);

        } catch (ApiException e) {
            this.googleAuthFragment.showGoogleError(e);
        }
    }

    @Override
    public void registrazione(OnCompleteListener<AuthResult> authResultOnCompleteListener) {
        return;
    }

    @Override
    public boolean logout() {

        // Firebase logout
        super.logout();

        // Google logout
        this.mGoogleSignInClient.signOut();

        return true;
    }


}
