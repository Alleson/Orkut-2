package com.braincorp.orkut2.fragments

import android.support.test.espresso.intent.Intents
import android.support.test.runner.AndroidJUnit4
import com.braincorp.orkut2.robots.OptionsFragmentRobot
import com.braincorp.orkut2.utils.BaseTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OptionsFragmentTest : BaseTest() {

    private val robot = OptionsFragmentRobot()

    @Before
    fun setup() {
        Intents.init()
    }

    @Test
    fun shouldOpenFriendsListActivityWhenClickingOnAddFriends() {
        robot.launchFragment()
                .clickOnAddFriends()
                .checkIfLeadsToFriendsListActivity()
                .checkIfShowFriendsFlagIsFalse()
    }

    @Test
    fun shouldOpenFriendsListActivityWhenClickingOnMyFriends() {
        robot.launchFragment()
                .clickOnMyFriends()
                .checkIfLeadsToFriendsListActivity()
                .checkIfShowFriendsFlagIsTrue()
    }

}