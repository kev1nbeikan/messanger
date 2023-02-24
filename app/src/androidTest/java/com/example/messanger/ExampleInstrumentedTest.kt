package com.example.messanger

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.messanger.firebase.register
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.messanger", appContext.packageName)
    }

    @Test
    fun registerUser() {
        val passoword = "1"
        val mail = "newMail@mail.ru"
        Log.d("TestRunner", "start register")
        register("someone", mail, passoword).addOnCompleteListener {
            Log.d("TestRunner", it.isSuccessful.toString())
            assertEquals(it.isSuccessful, true)
        }
        Log.d("TestRunner", "and register")

    }

}
