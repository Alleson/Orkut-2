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
import com.braincorp.orkut2.activities.HomePageActivity
import com.braincorp.orkut2.activities.LoginActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule

class LoginActivityRobot {

    companion object {
        private val INITIAL_TOUCH_MODE = false
        private val LAUNCH_ACTIVITY = false
    }

    @Rule
    val rule = IntentsTestRule<LoginActivity>(LoginActivity::class.java, INITIAL_TOUCH_MODE,
            LAUNCH_ACTIVITY)

    fun launchActivity(): LoginActivityRobot {
        rule.launchActivity(Intent())
        return this
    }

    fun checkIfLeadsToCreateUserActivity(): LoginActivityRobot {
        intended(hasComponent(CreateUserActivity::class.java.name))
        return this
    }

    fun checkIfLeadsToHomePageActivity(): LoginActivityRobot {
        intended(hasComponent(HomePageActivity::class.java.name))
        return this
    }

    fun checkIfSnackbarAppears(): LoginActivityRobot {
        onView(allOf(withId(android.support.design.R.id.snackbar_text),
                withText(R.string.invalid_user_name_or_password))).check(matches(isDisplayed()))
        return this
    }

    fun clickOnCreateUser(): LoginActivityRobot {
        onView(withId(R.id.btCreateAccount)).perform(click())
        return this
    }

    fun clickOnSignIn(): LoginActivityRobot {
        onView(withId(R.id.btSignIn)).perform(click())
        return this
    }

    fun hideKeyboard(): LoginActivityRobot {
        closeSoftKeyboard()
        return this
    }

    fun typeInvalidUserName(): LoginActivityRobot {
        onView(withId(R.id.editTextUserName)).perform(typeText("invalid"))
        return this
    }

    fun typeInvalidPassword(): LoginActivityRobot {
        onView(withId(R.id.editTextPassword)).perform(typeText("000000"))
        return this
    }

    fun typeValidUserName(): LoginActivityRobot {
        onView(withId(R.id.editTextUserName)).perform(typeText("alan"))
        return this
    }

    fun typeValidPassword(): LoginActivityRobot {
        onView(withId(R.id.editTextPassword)).perform(typeText("abcd123"))
        return this
    }

}