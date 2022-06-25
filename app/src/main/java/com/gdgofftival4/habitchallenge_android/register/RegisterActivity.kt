package com.gdgofftival4.habitchallenge_android.register

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.databinding.ActivityRegisterBinding
import com.gdgofftival4.habitchallenge_android.extension.observeEvent
import com.gdgofftival4.habitchallenge_android.extension.repeatOnStart
import com.gdgofftival4.habitchallenge_android.extension.setTextIfNew
import com.gdgofftival4.habitchallenge_android.extension.toast
import com.gdgofftival4.habitchallenge_android.profile.EditProfileActivity
import kotlinx.coroutines.launch

class RegisterActivity : BaseBindingActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repeatOnStart {
            launch {
                viewModel.registerUiModel.collect {
                    binding.emailEdit.setTextIfNew(it.email)
                    binding.passwordEdit.setTextIfNew(it.password)
                    binding.checkPasswordEdit.setTextIfNew(it.checkPassword)
                }
            }
        }

        observeEvent(viewModel.registerEvent) { event ->
            when (event) {
                is RegisterEvent.NotMatchPassword -> toast("비밀번호가 일치하지 않습니다.")
                is RegisterEvent.Next -> {
                    EditProfileActivity.startEditProfileActivity(
                        context = this,
                        metaRegisterModel = event.metaRegisterModel
                    )
                }
            }
        }

        binding.emailEdit.doAfterTextChanged {
            viewModel.onChangeEmail(it?.toString().orEmpty())
        }

        binding.passwordEdit.doAfterTextChanged {
            viewModel.onChangePassword(it?.toString().orEmpty())
        }

        binding.checkPasswordEdit.doAfterTextChanged {
            viewModel.onChangeCheckPassword(it?.toString().orEmpty())
        }

        binding.doneBtn.setOnClickListener {
            viewModel.onClickNextButton()
        }

        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}