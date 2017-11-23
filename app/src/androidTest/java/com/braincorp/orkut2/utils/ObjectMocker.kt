package com.braincorp.orkut2.utils

import com.braincorp.orkut2.model.User
import java.util.*

object ObjectMocker {

    fun user(): User {
        return User.Builder().setId(99)
                .setUserName("test")
                .setPassword("password")
                .setFullName("Test User")
                .setDateOfBirth(Date(System.currentTimeMillis()))
                .build()
    }

}