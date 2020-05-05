package it.univpm.mobile_programming_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class ChooseLoginRegisterActivity extends AppCompatActivity {

    SignInButton btnLoginWithGoogle;
    Button btnAccedi;
    Button btnRegistrati;
    GoogleSignInClient mGoogleSignInClient;

    private FirebaseAuth mAuth;

    final int GOOGLE_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_login_register);

        showWelcomeMessage();

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .requestEmail()
                .requestProfile()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        //// DELETE ME! IT'S FOR DEBUG.
        //// DELETE ME! IT'S FOR DEBUG.
        //// DELETE ME! IT'S FOR DEBUG.
        //mGoogleSignInClient.signOut();
        //// DELETE ME! IT'S FOR DEBUG.
        //// DELETE ME! IT'S FOR DEBUG.
        //// DELETE ME! IT'S FOR DEBUG.

        // Get instance of firebase auth
        mAuth = FirebaseAuth.getInstance();


        btnLoginWithGoogle = findViewById(R.id.btnLoginWithGoogle);
        btnAccedi = findViewById(R.id.btnAccedi);
        btnRegistrati = findViewById(R.id.btnRegistrati);

        btnLoginWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginWithGoogle(v);
            }
        });
    }

    private void showWelcomeMessage() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(getString(R.string.welcome_title) + " " + getString(R.string.app_name));
        alert.setMessage(getString(R.string.welcome_message));

        alert.setPositiveButton(getString(R.string.chiudi), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });

        alert.show();
    }

    public void runAccediForm(View v)
    {
        Intent intent = new Intent(ChooseLoginRegisterActivity.this, AccediActivity.class);
        startActivity(intent);
    }

    public void runRegistratiForm(View v)
    {
        Intent intent = new Intent(ChooseLoginRegisterActivity.this, RegistrazioneActivity.class);
        startActivity(intent);
    }

    public void loginWithGoogle(View v)
    {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
        Toast.makeText(ChooseLoginRegisterActivity.this, "loginWithGoogle", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent
        switch (requestCode)
        {
            case GOOGLE_SIGN_IN:
                // The Task returned from this call is always completed, no need to attach
                // a listener.
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                // Handle signin result
                handleGoogleSignInResult(task);
                break;

            default:
                break;
        }

    }

    private void handleGoogleSignInResult(Task<GoogleSignInAccount> completedTask) {

        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            Log.d("ChooseLoginRegisterActivity::handleGoogleSignInResult", "firebaseAuthWithGoogle:" + account.getId());
            // Signed in successfully with google. Let's sign in to firebase.
            firebaseAuthWithGoogle(account.getIdToken());

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("ChooseLoginRegisterActivity::handleGoogleSignInResult", "signInResult:failed code=" + e.getStatusCode());


        }
    }

    private void firebaseAuthWithGoogle(String idToken)
    {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("OK", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            user.getDisplayName();

                            Intent intent = new Intent(ChooseLoginRegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("ERROR", "signInWithCredential:failure", task.getException());
//                            Snackbar.make(mBinding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                    }
                });
    }
}
