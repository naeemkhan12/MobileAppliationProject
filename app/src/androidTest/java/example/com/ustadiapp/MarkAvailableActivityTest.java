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

public class MarkAvailableActivityTest {
    @Rule
    public ActivityTestRule<MarkAvailableActivity> mTestRule = new ActivityTestRule<MarkAvailableActivity>(MarkAvailableActivity.class);
    private MarkAvailableActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = mTestRule.getActivity();
        assertNotNull(activity);
    }

    @Test
    public  void  testLaunch(){
        View view = activity.findViewById(R.id.recycler_view);
        assertNotNull(view);
    }
    @After
    public void tearDown() throws Exception {
        activity=null;

    }
}
