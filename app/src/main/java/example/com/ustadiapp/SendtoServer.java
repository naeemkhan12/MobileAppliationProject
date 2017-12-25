package example.com.ustadiapp;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by naeem on 12/25/17.
 */

public class SendtoServer extends Thread{
    private static final String LOG = "TESTLOG";
    private final OkHttpClient client = new OkHttpClient();
    private String email;
    private String token;
    public SendtoServer(String email , String token){
        this.email=email;
        this.token=token;
    }

    @Override
    public void run() {
        super.run();
        RequestBody formBody = new FormBody.Builder()
                .add("email", email)
                .add("token", token)
                .build();
        Request request = new Request.Builder()
                .url("http://10.0.3.2/register.php")
                .post(formBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
        }catch (Exception ex){
            Log.d(LOG,"Exception: "+ex.getMessage());
            ex.printStackTrace();
        }
    }
}
