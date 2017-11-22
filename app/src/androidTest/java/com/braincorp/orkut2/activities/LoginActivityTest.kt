package com.braincorp.orkut2.activities

import android.support.test.espresso.intent.Intents
import android.support.test.runner.AndroidJUnit4
import com.braincorp.orkut2.robots.LoginActivityRobot
import org.junit.After
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    private val robot = LoginActivityRobot()

    @After
    fun after() {
        Intents.release()
    }

    @Test
    fun shouldOpenCreateUserActivityWhenClickingOnCreateUser() {
        robot.launchActivity()
                .clickOnCreateUser()
                .checkIfLeadsToCreateUserActivity()
    }

    @Test
    @Ignore // TODO: remove this
    fun shouldOpenHomePageActivityWhenUserNameAndPasswordAreValid() {
        robot.launchActivity()
                .typeValidUserName()
                .hideKeyboard()
                .typeValidPassword()
                .hideKeyboard()
                .clickOnSignIn()
                .checkIfLeadsToHomePageActivity()
    }

    @Test
    fun shouldShowSnackbarIfUserNameOrPasswordAreInvalid() {
        robot.launchActivity()
                .typeInvalidUserName()
                .hideKeyboard()
                .typeInvalidPassword()
                .hideKeyboard()
                .clickOnSignIn()
                .checkIfSnackbarAppears()
    }

    @Test
    fun shouldShowSnackbarIfUserNameIsEmpty() {
        robot.launchActivity()
                .typeValidPassword()
                .hideKeyboard()
                .clickOnSignIn()
                .checkIfSnackbarAppears()
    }

    @Test
    fun shouldShowSnackbarIfPasswordIsEmpty() {
        robot.launchActivity()
                .typeValidUserName()
                .hideKeyboard()
                .clickOnSignIn()
                .checkIfSnackbarAppears()
    }

    @Test
    fun shouldShowSnackbarIfUserNameAndPasswordAreEmpty() {
        robot.launchActivity()
                .clickOnSignIn()
                .checkIfSnackbarAppears()
    }

}