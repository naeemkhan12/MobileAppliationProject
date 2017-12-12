package example.com.ustadiapp.services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by naeem on 12/11/17.
 */

public class InstanceIdService extends FirebaseInstanceIdService {
    private static final String LOG = "TESTLOG";
    private String token;
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        token = FirebaseInstanceId.getInstance().getToken();

        Log.d(LOG,token);

//        TODO: save token with userid on server
//        http://www.mydomain.com/index.php?argument1=arg1&argument2=arg2



//        https://stackoverflow.com/questions/24233632/how-to-add-parameters-to-api-http-post-using-okhttp-library-in-android


        /*
        *  private final OkHttpClient client = new OkHttpClient();

      public void run() throws Exception {
        RequestBody formBody = new FormEncodingBuilder()
            .add("email", "Jurassic@Park.com")
            .add("tel", "90301171XX")
            .build();
        Request request = new Request.Builder()
            .url("https://en.wikipedia.org/w/index.php")
            .post(formBody)
            .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

        System.out.println(response.body().string());
      }*/
    }
}
