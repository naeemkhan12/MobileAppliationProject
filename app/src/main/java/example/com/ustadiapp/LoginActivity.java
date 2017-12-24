package example.com.ustadiapp;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "TESTLOG";
    private static final int RC_SIGN_IN =123;
    private FirebaseAuth mAuth;

    // Choose authentication providers
    private List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
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
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setTheme(R.style.signup_theme)
                            .build(),
                    RC_SIGN_IN);
        }
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
