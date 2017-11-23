package com.braincorp.orkut2.utils

import android.support.test.espresso.intent.Intents
import org.junit.After

open class BaseTest {

    @After
    open fun after() {
        Intents.release()
    }

}