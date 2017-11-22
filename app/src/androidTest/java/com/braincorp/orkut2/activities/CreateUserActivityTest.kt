package com.braincorp.orkut2.activities

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.intent.Intents
import android.support.test.runner.AndroidJUnit4
import com.braincorp.orkut2.BaseTest
import com.braincorp.orkut2.database.UserDao
import com.braincorp.orkut2.robots.CreateUserActivityRobot
import org.junit.After
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CreateUserActivityTest : BaseTest() {

    private val database = UserDao.getInstance(InstrumentationRegistry.getTargetContext())

    private val robot = CreateUserActivityRobot()

    @After
    override fun after() {
        clearDatabase()
        Intents.release()
    }

    @Test
    @Ignore
    fun shouldOpenLoginActivityIfAllFieldsAreFilled() {
        robot.launchActivity()
                .typeUserName()
                .hideKeyboard()
                .typePassword()
                .hideKeyboard()
                .typeFullName()
                .hideKeyboard()
                .typeDateOfBirth()
                .hideKeyboard()
                .clickOnSave()
                .checkIfLeadsToLoginActivity()
    }

    @Test
    @Ignore
    fun shouldOpenLoginActivityIfUserNamePasswordAndFullNameAreFilled() {
        robot.launchActivity()
                .typeUserName()
                .hideKeyboard()
                .typePassword()
                .hideKeyboard()
                .typeFullName()
                .hideKeyboard()
                .clickOnSave()
                .checkIfLeadsToLoginActivity()
    }

    @Test
    fun shouldShowSnackbarIfAllFieldsAreEmpty() {
        robot.launchActivity()
                .clickOnSave()
                .checkIfSnackbarAppears()
    }

    @Test
    fun shouldShowSnackbarIfOnlyDateOfBirthIsFilled() {
        robot.launchActivity()
                .typeDateOfBirth()
                .hideKeyboard()
                .clickOnSave()
                .checkIfSnackbarAppears()
    }

    @Test
    fun shouldShowSnackbarIfDateOfBirthAndUserNameAreFilled() {
        robot.launchActivity()
                .typeDateOfBirth()
                .hideKeyboard()
                .typeUserName()
                .hideKeyboard()
                .clickOnSave()
                .checkIfSnackbarAppears()
    }

    @Test
    fun shouldShowSnackbarIfDateOfBirthAndFullNameAreFilled() {
        robot.launchActivity()
                .typeDateOfBirth()
                .hideKeyboard()
                .typeFullName()
                .hideKeyboard()
                .clickOnSave()
                .checkIfSnackbarAppears()
    }

    @Test
    fun shouldShowSnackbarIfDateOfBirthUserNameAndPasswordAreFilled() {
        robot.launchActivity()
                .typeDateOfBirth()
                .hideKeyboard()
                .typeUserName()
                .hideKeyboard()
                .typePassword()
                .hideKeyboard()
                .clickOnSave()
                .checkIfSnackbarAppears()
    }

    @Test
    fun shouldShowSnackbarIfOnlyUserNameIsFilled() {
        robot.launchActivity()
                .typeUserName()
                .hideKeyboard()
                .clickOnSave()
                .checkIfSnackbarAppears()
    }

    @Test
    fun shouldShowSnackbarIfUserNameAndPasswordAreFilled() {
        robot.launchActivity()
                .typeUserName()
                .hideKeyboard()
                .typePassword()
                .hideKeyboard()
                .clickOnSave()
                .checkIfSnackbarAppears()
    }

    @Test
    fun shouldShowSnackbarIfUserNameAndFullNameAreFilled() {
        robot.launchActivity()
                .typeUserName()
                .hideKeyboard()
                .typeFullName()
                .hideKeyboard()
                .clickOnSave()
                .checkIfSnackbarAppears()
    }

    @Test
    fun shouldShowSnackbarIfOnlyPasswordIsFilled() {
        robot.launchActivity()
                .typePassword()
                .hideKeyboard()
                .clickOnSave()
                .checkIfSnackbarAppears()
    }

    @Test
    fun shouldShowSnackbarIfPasswordAndFullNameAreFilled() {
        robot.launchActivity()
                .typePassword()
                .hideKeyboard()
                .typeFullName()
                .hideKeyboard()
                .clickOnSave()
                .checkIfSnackbarAppears()
    }

    @Test
    fun shouldShowSnackbarIfOnlyFullNameIsFilled() {
        robot.launchActivity()
                .typeFullName()
                .hideKeyboard()
                .clickOnSave()
                .checkIfSnackbarAppears()
    }

    private fun clearDatabase() {
        val users = database.select()
        for (user in users)
            database.delete(user)
    }

}