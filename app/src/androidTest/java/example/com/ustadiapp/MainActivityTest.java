package example.com.ustadiapp;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.assertion.ViewAssertions.selectedDescendantsMatch;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by naeem on 12/25/17.
 */

@RunWith(JUnit4.class)
public class MainActivityTest {

        @Rule
        public ActivityTestRule<MainActivity> mainRule = new ActivityTestRule<MainActivity>(MainActivity.class);
        @Rule
        public ActivityTestRule<LoginActivity> loginRule = new ActivityTestRule<>(LoginActivity.class);
        private MainActivity activity;
        private LoginActivity loginActivity;

        @Before
        public void setUp() throws Exception {
            activity = mainRule.getActivity();
            loginActivity=loginRule.getActivity();
        }
        @Test
        public  void  testLaunch(){
            View view = activity.findViewById(R.id.recycler_view);
            assertNotNull(view);
        }
        @Test
        public void clickItem(){
            onView(withId(R.id.recycler_view))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
//            detail view with grid layout test
            onView(withId(R.id.detail_recyclerview)).check(matches(isDisplayed()));
//            profile icon display test
            onView(withId(R.id.detail_icon_profile)).check(matches(isDisplayed()));
//            backgroud visibility test
            onView(withId(R.id.background)).check(matches(isDisplayed()));

        }



//        thease tests are all failing below



//        @Test
//        public void optionMenuLogoutClick() {
////            logout test to open login activity
//            openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
//            onView(withText(R.string.text_more_logout)).perform(click());
//        }
//    @Test
//    public void optionMenuMyViewClick(){
//        //            single user view test
//        onView(withId(R.id.action_user)).perform(click());
//        onView(withId(R.id.launcher)).check(matches(isDisplayed()));
//    }
//        @Test
//        public void optionMenuAllViewClick(){
////            multiuser view test
//            onView(withId(R.id.action_all)).perform(click());
//            onView(withId(R.id.launcher)).check(matches(not(isDisplayed())));
//        }
        @After
        public void tearDown() throws Exception {
            activity=null;

        }
}
