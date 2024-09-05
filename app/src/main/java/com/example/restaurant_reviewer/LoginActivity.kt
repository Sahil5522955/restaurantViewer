package com.example.restaurant_reviewer

import android.os.Bundle
import androidx.activity.compose.setContent

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseLoginRegisterView(this, true)
        }
    }
}