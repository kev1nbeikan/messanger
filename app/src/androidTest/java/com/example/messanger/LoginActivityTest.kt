package com.example.messanger

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.messanger.firebase.getUser
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)

class LoginActivityTest {
    private lateinit var activity_scenario: ActivityScenario<LoginActivity>

    @Before
    fun setUp() {
        activity_scenario = ActivityScenario.launch(LoginActivity::class.java)
    }

    @After
    fun tearDown() {
        activity_scenario.close()
    }

    @Test
    fun login_test() {
        // before testing check are email and password exits in database
        val email = "new@mail.ru"
        val password = "1231232312"
        Espresso.onView(ViewMatchers.withId(R.id.mailEditTextLoginScreen))
            .perform(ViewActions.typeText(email))
        Espresso.onView(ViewMatchers.withId(R.id.passwordEditTextLoginScreen))
            .perform(ViewActions.typeText(password))
        Espresso.onView(ViewMatchers.withId(R.id.loginButtonLoginScreen))
            .perform(ViewActions.click())
        sleep(3000)
        val user = getUser()
        Assert.assertEquals(email, user?.email.toString())
    }
}