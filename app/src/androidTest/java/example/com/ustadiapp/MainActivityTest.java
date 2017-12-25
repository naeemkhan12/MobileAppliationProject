package example.com.ustadiapp;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertNotNull;

/**
 * Created by naeem on 12/25/17.
 */

@RunWith(JUnit4.class)
public class MainActivityTest {

        @Rule
        public ActivityTestRule<MainActivity> mTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
        private MainActivity activity;

        @Before
        public void setUp() throws Exception {
            activity = mTestRule.getActivity();
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
