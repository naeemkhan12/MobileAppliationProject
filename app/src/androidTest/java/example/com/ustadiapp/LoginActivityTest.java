package example.com.ustadiapp;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by naeem on 12/25/17.
 */
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);
    private LoginActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = mTestRule.getActivity();
        assertNotNull(activity);
    }

    @Test
    public  void  testLaunch(){
        assertNotNull(activity);
    }
    @After
    public void tearDown() throws Exception {
        activity=null;

    }

}