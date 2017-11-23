package com.braincorp.orkut2.robots

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import com.braincorp.orkut2.R
import com.braincorp.orkut2.activities.HomePageActivity
import com.braincorp.orkut2.utils.ObjectMocker
import org.junit.Rule

class HomePageActivityRobot {

    companion object {
        val INITIAL_TOUCH_MODE = false
        val LAUNCH_ACTIVITY = false
    }

    private val context = InstrumentationRegistry.getTargetContext()
    private val user = ObjectMocker.user()

    @Rule
    private val rule = ActivityTestRule<HomePageActivity>(HomePageActivity::class.java,
            INITIAL_TOUCH_MODE, LAUNCH_ACTIVITY)

    fun launchActivity(): HomePageActivityRobot {
        val intent = HomePageActivity.getIntent(context, user)

        rule.launchActivity(intent)
        return this
    }

    fun checkIfFullNameMatches(): HomePageActivityRobot {
        val rawText = context.getString(R.string.welcome)
        val text = String.format(rawText, user.fullName)
        onView(withId(R.id.textViewWelcome)).check(matches(withText(text)))
        return this
    }

}