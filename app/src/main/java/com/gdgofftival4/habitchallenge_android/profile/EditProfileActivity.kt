package com.gdgofftival4.habitchallenge_android.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.databinding.ActivityEditProfieBinding
import com.gdgofftival4.habitchallenge_android.extension.repeatOnStart
import com.gdgofftival4.habitchallenge_android.extension.setCircleImageUri
import com.gdgofftival4.habitchallenge_android.extension.setTextIfNew
import com.gdgofftival4.habitchallenge_android.login.MetaRegisterModel
import kotlinx.coroutines.launch

class EditProfileActivity : BaseBindingActivity<ActivityEditProfieBinding>(ActivityEditProfieBinding::inflate) {

    private val viewModel: EditProfileViewModel by viewModels()

    private val galleryActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            with(result) {
                val selectedImageUri: Uri? = data?.data
                if (resultCode == Activity.RESULT_OK && selectedImageUri != null) {
                    viewModel.onUpdateProfileImage(selectedImageUri)
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val metaRegisterModel = intent.getParcelableExtra<MetaRegisterModel>(EXTRA_META_REGISTER_MODEL)
        viewModel.setEditMode(metaRegisterModel)

        repeatOnStart {
            launch {
                viewModel.profileUiModel.collect {
                    binding.editImg.setCircleImageUri(it.image)
                    binding.nicknameEdit.setTextIfNew(it.nickName)
                }
            }
        }

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.buttonProfileDone.setOnClickListener {
            viewModel.onDoneProfileEdit()
        }

        binding.editImg.setOnClickListener {
            galleryActivityResultLauncher.launch(Intent().apply {
                type = "image/*"
                action = Intent.ACTION_GET_CONTENT
            })
        }

        binding.nicknameEdit.doAfterTextChanged {
            viewModel.onNickNameChanged(it?.toString().orEmpty())
        }
    }

    companion object {
        private const val EXTRA_META_REGISTER_MODEL = "metaRegisterModel"

        fun startEditProfileActivity(context: Context, metaRegisterModel: MetaRegisterModel? = null) {
            context.startActivity(Intent(context, EditProfileActivity::class.java).apply {
                putExtra(EXTRA_META_REGISTER_MODEL, metaRegisterModel)
            })
        }
    }
}