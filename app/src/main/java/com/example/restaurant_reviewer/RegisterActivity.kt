package com.example.restaurant_reviewer

import android.os.Bundle
import androidx.activity.compose.setContent

class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseLoginRegisterView(this, false)
        }
    }
}