package com.heyaiz.heymov.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.heyaiz.heymov.R
import com.heyaiz.heymov.sign.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_onboarding_tree.*

class OnboardingTreeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_tree)

        btn_home.setOnClickListener {
            finishAffinity()
            val intent = Intent(this@OnboardingTreeActivity, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}
