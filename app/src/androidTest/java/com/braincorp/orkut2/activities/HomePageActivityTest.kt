package com.braincorp.orkut2.activities

import android.support.test.runner.AndroidJUnit4
import com.braincorp.orkut2.robots.HomePageActivityRobot
import com.braincorp.orkut2.utils.BaseTest
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomePageActivityTest : BaseTest() {

    private val robot = HomePageActivityRobot()

    @After
    override fun after() { }

    @Test
    fun shouldShowCorrectUsersFullName() {
        robot.launchActivity()
                .checkIfFullNameMatches()
    }

}