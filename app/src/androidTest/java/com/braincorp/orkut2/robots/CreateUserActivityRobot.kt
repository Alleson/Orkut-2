package com.braincorp.orkut2.robots

import android.content.Intent
import android.support.test.espresso.Espresso.closeSoftKeyboard
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import com.braincorp.orkut2.R
import com.braincorp.orkut2.activities.CreateUserActivity
import com.braincorp.orkut2.activities.LoginActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule

class CreateUserActivityRobot {

    companion object {
        private val INITIAL_TOUCH_MODE = false
        private val LAUNCH_ACTIVITY = false
    }

    @Rule
    private val rule = IntentsTestRule<CreateUserActivity>(CreateUserActivity::class.java,
            INITIAL_TOUCH_MODE, LAUNCH_ACTIVITY)

    fun launchActivity(): CreateUserActivityRobot {
        rule.launchActivity(Intent())
        return this
    }

    fun checkIfLeadsToLoginActivity(): CreateUserActivityRobot {
        intended(hasComponent(LoginActivity::class.java.name))
        return this
    }

    fun checkIfSnackbarAppears(): CreateUserActivityRobot {
        onView(allOf(withId(android.support.design.R.id.snackbar_text),
                withText(R.string.error_creating_user))).check(matches(isDisplayed()))
        return this
    }

    fun clickOnSave(): CreateUserActivityRobot {
        onView(withId(R.id.fabSave)).perform(click())
        return this
    }

    fun hideKeyboard(): CreateUserActivityRobot {
        closeSoftKeyboard()
        return this
    }

    fun typeUserName(): CreateUserActivityRobot {
        onView(withId(R.id.editTextUserName)).perform(typeText("testuser"))
        return this
    }

    fun typePassword(): CreateUserActivityRobot {
        onView(withId(R.id.editTextPassword)).perform(typeText("testpassword"))
        return this
    }

    fun typeFullName(): CreateUserActivityRobot {
        onView(withId(R.id.editTextFullName)).perform(typeText("Test User"))
        return this
    }

    fun typeDateOfBirth(): CreateUserActivityRobot {
        onView(withId(R.id.editTextDateOfBirth)).perform(typeText("03/08/1992"))
        return this
    }

}