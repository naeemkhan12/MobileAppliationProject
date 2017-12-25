package example.com.ustadiapp.services;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import example.com.ustadiapp.SendtoServer;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by naeem on 12/11/17.
 */

public class InstanceIdService extends FirebaseInstanceIdService {
    private static final String LOG = "TESTLOG";
    private static final String PREFS_NAME="TOKEN_SERVICE";
    private String token;



//        http://www.mydomain.com/index.php?argument1=arg1&argument2=arg2

//        https://stackoverflow.com/questions/24233632/how-to-add-parameters-to-api-http-post-using-okhttp-library-in-android
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        token = FirebaseInstanceId.getInstance().getToken();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Writing data to SharedPreferences
        SharedPreferences.Editor editor = settings.edit();
        if (settings.getString("email","").equals("")){
            editor.putString("token", token);
            editor.commit();
        }else {
            String email = settings.getString("email","");
               SendtoServer sendtoServer =  new SendtoServer(email,token);
            sendtoServer.start();
        }
    }
}
