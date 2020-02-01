package paraman.com.twoactivities;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ActivityInputOutputTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("paraman.com.twoactivities", appContext.getPackageName());
    }

    @Test
    public void activityLaunch() {
        onView(withId(R.id.button_main_activity_send)).perform(click());
        onView(withId(R.id.textView_second_activity_heading)).check(matches(isDisplayed()));
        onView(withId(R.id.button_second_activity_send)).perform(click());
        onView(withId(R.id.text_header_reply)).check(matches(isDisplayed()));

    }

    @Test
    public void textInputOutput() {
        onView(withId(R.id.editText_main_activity_enter_message)).perform(typeText("This is a test."));
        onView(withId(R.id.button_main_activity_send)).perform(click());
        onView(withId(R.id.textView_second_activity_message_passed))
                .check(matches(withText("This is a test.")));
    }
}
