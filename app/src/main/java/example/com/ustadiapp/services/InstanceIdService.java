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
    }
}
