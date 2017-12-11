package example.com.ustadiapp.appserver;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import example.com.ustadiapp.model.PostMessageData;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by naeem on 12/11/17.
 */

public class SendMessage {
    private static final String LOG="TESTLOG";
    private String token;
    private String appServerKey;
    private PostMessageData data;
    private static final String FCM_URL="https://fcm.googleapis.com/fcm/send";
    private final MediaType JSON=MediaType.parse("application/json;charset=utf-8");


    public SendMessage(String token, String appServerKey, PostMessageData data) {
        this.token = token;
        this.appServerKey = appServerKey;
        this.data = data;
    }
    public Response sendMessage() throws IOException {
        data.setTo(token);
        Gson gson = new Gson();
        String json = gson.toJson(data);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON,json);
        Request request= new Request.Builder().url(FCM_URL)
                .header("Authorization",appServerKey)
                .post(requestBody)
                .build();
        Response response=client.newCall(request).execute();
        return response;
    }
}
