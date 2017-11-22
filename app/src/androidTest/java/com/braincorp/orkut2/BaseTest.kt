package com.braincorp.orkut2

import android.support.test.espresso.intent.Intents
import org.junit.After

open class BaseTest {

    @After
    open fun after() {
        Intents.release()
    }

}