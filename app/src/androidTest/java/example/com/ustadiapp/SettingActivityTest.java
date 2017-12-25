package example.com.ustadiapp;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by naeem on 12/25/17.
 */

public class SettingActivityTest {
    @Rule
    public ActivityTestRule<SettingActivity> mTestRule = new ActivityTestRule<SettingActivity>(SettingActivity.class);
    private SettingActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = mTestRule.getActivity();
        assertNotNull(activity);
    }

    @Test
    public  void  testLaunch(){
        View view = activity.findViewById(R.id.text_setting_heading);
        assertNotNull(activity);
    }
    @After
    public void tearDown() throws Exception {
        activity=null;

    }
}
