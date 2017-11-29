package example.com.ustadiapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by naeem on 11/28/17.
 */

public class SignIn extends Fragment{

    private TextView mlink;
    private Context context;
    private static final String LOG="TESTLOG";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.sign_in,container,false);
         mlink = view.findViewById(R.id.sign_up);
        mlink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(LOG,"mlink clicked");
                SignInWithAuthUI signInWithAuthUI = new SignInWithAuthUI();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_frame,signInWithAuthUI);
                fragmentTransaction.commit();

            }
        });
        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
