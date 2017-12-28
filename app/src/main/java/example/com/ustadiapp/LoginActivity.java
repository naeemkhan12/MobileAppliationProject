package example.com.ustadiapp;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "TESTLOG";
    private static final int RC_SIGN_IN =123;
    private FirebaseAuth mAuth;
    private Button logIn;
    private TextView signUp;
    private EditText email;
    private EditText password;

    // Choose authentication providers
    private List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
            new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
            new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser()!=null){
//            SendMessage sendMessage = new SendMessage("naeemr2014@namal.edu.pk","1");
//            sendMessage.execute();
            Log.i(TAG,"User has id "+mAuth.getCurrentUser().getUid());
            startActivity();
//            myRef = database.getReference("schedule/").child(mAuth.getCurrentUser().getUid());
        }else {
            setContentView(R.layout.auth);
            logIn = (Button)findViewById(R.id.btn_login);
            email = (EditText)findViewById(R.id.input_email);
            password = (EditText)findViewById(R.id.input_password);
            logIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String emailStr=email.getText().toString();
                    String passwordStr=password.getText().toString();
                    // authenticate the user with firebase
                    Log.i(TAG,emailStr+" "+passwordStr);
                    authWithEmailPassword(emailStr,passwordStr);
                }
            });
            signUp = (TextView) findViewById(R.id.link_signup);
            signUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(providers)
                                    .setTheme(R.style.signup_theme)
                                    .setLogo(R.drawable.logo_white)
                                    .build(),
                            RC_SIGN_IN);
                }
            });
        }
    }

public void authWithEmailPassword(String email, String password){
//    https://firebase.google.com/docs/auth/android/password-auth
    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()){
                Log.d(TAG, "createUserWithEmail:success");
                startActivity();
            }else {
                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                Toast.makeText(LoginActivity.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();

            }

        }
    });
}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            // Successfully signed in
            if (resultCode == RESULT_OK) {
//                Intent intent = new Intent(this,MainActivity.class);
//                startActivity(intent);
                startActivity();
                return;
            } else {
                // Sign in failed
                Log.i(TAG,"sign in failed");
                return;
            }
//            showSnackbar(R.string.unknown_sign_in_response);
        }
    }

    public void startActivity(){
        Intent intent;
        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            Log.i(TAG,"checking activity");
            ArrayList<String> stringArrayList = extras.getStringArrayList("FLAG");
            if (stringArrayList.get(0).equals("device")){
                intent = new Intent(this,MainActivity.class);
                intent.putExtra("index",stringArrayList.get(1));
            }else {
                intent = new Intent(this,MarkAvailableActivity.class);
            }
        }
        else {
            intent = new Intent(this,MainActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
