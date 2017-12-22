package example.com.ustadiapp.services;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

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
    private static final String EMAIL="naeemr2014@namal.edu.pk";
    private String token;
    private final OkHttpClient client = new OkHttpClient();



//        http://www.mydomain.com/index.php?argument1=arg1&argument2=arg2

//        https://stackoverflow.com/questions/24233632/how-to-add-parameters-to-api-http-post-using-okhttp-library-in-android
    public void updateToken(String email,String token) throws Exception {
        RequestBody formBody = new FormBody.Builder()
                .add("email", email)
                .add("token", token)
                .build();
        Request request = new Request.Builder()
                .url("http://10.0.3.2/register.php")
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()){
            Log.d(LOG,"Failed");
            Log.d(LOG,response.body().toString());
        }else {
            Log.d(LOG,"Sucess: "+response.body().toString());
        }
    }
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        token = FirebaseInstanceId.getInstance().getToken();
        Log.d(LOG,token);
        try {
            updateToken(EMAIL,token);
        } catch (Exception e) {
            Log.d(LOG,"Exception Message: "+e.getMessage());
        }
    }
}
