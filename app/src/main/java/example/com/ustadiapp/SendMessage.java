package example.com.ustadiapp;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.OkHttpClient;

/**
 * Created by naeem on 12/22/17.
 */

public class SendMessage extends AsyncTask<Void, Void,Void>{
    private static final String LOG ="TESTLOG" ;
    private String email;
    private String message;
    private String index;
    private final OkHttpClient client = new OkHttpClient();

    public SendMessage(String email,String index) {
        this.email = email;
        this.index=index;
        this.message = email+" wants to swap the following duty with you.";
    }

    @Override
    protected Void doInBackground(Void... params) {
        RequestBody formBody = new FormBody.Builder()
                .add("email", email)
                .add("message",message)
                .add("index",index)
                .build();
        Request request = new Request.Builder()
                .url("http://10.0.3.2/push.php")
                .post(formBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()){
                Log.i(LOG,"Failed to send");
            }else {
                Log.i(LOG,"Success !!!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
