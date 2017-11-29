package example.com.ustadiapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOG="TESTLOG";
    private static final int RC_SIGN_IN = 123;


    private FirebaseAuth mAuth;

// ...

    // Choose authentication providers
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()==null){
            SignIn signIn = new SignIn();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_frame,signIn);
            fragmentTransaction.commit();
        }else {
            Log.i(LOG,"User has Signed In");

        }


    }
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
//        if (requestCode == RC_SIGN_IN) {
//            IdpResponse response = IdpResponse.fromResultIntent(data);
//            // Successfully signed in
//            if (resultCode == RESULT_OK) {
////                startActivity(SignedInActivity.createIntent(this, response));
//                finish();
//                return;
//            } else {
//                // Sign in failed
//                if (response == null) {
//                    // User pressed back button
////                    showSnackbar(R.string.sign_in_cancelled);
//                    return;
//                }
//
//                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
////                    showSnackbar(R.string.no_internet_connection);
////                    return;
//                }
//
//                if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
////                    showSnackbar(R.string.unknown_error);
////                    return;
//                }
//            }
//
////            showSnackbar(R.string.unknown_sign_in_response);
//        }
//    }
 }


