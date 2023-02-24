package com.example.messanger

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.messanger.firebase.getUser
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private lateinit var activity_scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        activity_scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        activity_scenario.close()
    }

    @Test
    fun register_test() {
        val username = "newUser"
        val email = "new@mail.ru"
        val password = "1231232312"
        onView(ViewMatchers.withId(R.id.usernameEditTextReigsterScreen)).perform(typeText(username))
        onView(ViewMatchers.withId(R.id.emailTextEditRegisterScreen)).perform(typeText(email))
        onView(ViewMatchers.withId(R.id.passwordEditTextRegisterScreen)).perform(typeText(password))
        onView(ViewMatchers.withId(R.id.loginButtonRegisterScreen)).perform(click())
        Thread.sleep(3000)
        val user = getUser()
        Assert.assertEquals(email, user?.email.toString())
    }
}