package com.braincorp.orkut2.robots

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.android21buttons.fragmenttestrule.FragmentTestRule
import com.braincorp.orkut2.R
import com.braincorp.orkut2.activities.UserListActivity
import com.braincorp.orkut2.activities.TestActivity
import com.braincorp.orkut2.fragments.OptionsFragment
import com.braincorp.orkut2.utils.ObjectMocker
import org.junit.Rule

class OptionsFragmentRobot {

    @Rule
    private val rule = FragmentTestRule<TestActivity, OptionsFragment>(TestActivity::class.java,
            OptionsFragment::class.java)

    private val user = ObjectMocker.user()

    fun launchFragment(): OptionsFragmentRobot {
        rule.launchActivity(Intent())
        rule.launchFragment(OptionsFragment.newInstance(user))
        return this
    }

    fun clickOnAddFriends(): OptionsFragmentRobot {
        onView(withId(R.id.buttonAddFriends)).perform(click())
        return this
    }

    fun clickOnMyFriends(): OptionsFragmentRobot {
        onView(withId(R.id.buttonMyFriends)).perform(click())
        return this
    }

    fun checkIfLeadsToFriendsListActivity(): OptionsFragmentRobot {
        intended(hasComponent(UserListActivity::class.java.name))
        return this
    }

    fun checkIfShowFriendsFlagIsTrue(): OptionsFragmentRobot {
        intended(hasExtra(UserListActivity.EXTRA_SHOW_FRIENDS, true))
        return this
    }

    fun checkIfShowFriendsFlagIsFalse(): OptionsFragmentRobot {
        intended(hasExtra(UserListActivity.EXTRA_SHOW_FRIENDS, false))
        return this
    }

}