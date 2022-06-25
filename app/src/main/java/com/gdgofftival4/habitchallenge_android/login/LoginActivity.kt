package com.gdgofftival4.habitchallenge_android.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.databinding.ActivityLoginBinding
import com.gdgofftival4.habitchallenge_android.extension.observeEvent
import com.gdgofftival4.habitchallenge_android.extension.repeatOnStart
import com.gdgofftival4.habitchallenge_android.extension.setTextIfNew
import com.gdgofftival4.habitchallenge_android.extension.toast
import com.gdgofftival4.habitchallenge_android.home.HomeActivity
import com.gdgofftival4.habitchallenge_android.register.RegisterActivity
import kotlinx.coroutines.launch

class LoginActivity : BaseBindingActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repeatOnStart {
            launch {
                viewModel.loginUiModel.collect {
                    binding.inputEmailLogin.setTextIfNew(it.email)
                    binding.inputPasswordLogin.setTextIfNew(it.password)
                }
            }
        }

        observeEvent(viewModel.loginEvent) { event ->
            when (event) {
                is LoginEvent.Success -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                }
                is LoginEvent.Failure -> {
                    toast("로그인에 실패했습니다. ${event.message}", Toast.LENGTH_LONG)
                }
            }
        }

        binding.inputEmailLogin.doAfterTextChanged {
            viewModel.onChangeEmail(it?.toString().orEmpty())
        }

        binding.inputPasswordLogin.doAfterTextChanged {
            viewModel.onChangePassword(it?.toString().orEmpty())
        }

        binding.buttonLogin.setOnClickListener {
            viewModel.onClickLoginButton()
        }

        binding.buttonRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}