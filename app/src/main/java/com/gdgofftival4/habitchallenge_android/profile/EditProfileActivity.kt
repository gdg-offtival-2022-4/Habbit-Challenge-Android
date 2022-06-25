package com.gdgofftival4.habitchallenge_android.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.gdgofftival4.habitchallenge_android.R
import com.gdgofftival4.habitchallenge_android.base.BaseBindingActivity
import com.gdgofftival4.habitchallenge_android.databinding.ActivityEditProfieBinding
import com.gdgofftival4.habitchallenge_android.extension.*
import com.gdgofftival4.habitchallenge_android.home.HomeActivity
import com.gdgofftival4.habitchallenge_android.register.MetaRegisterModel
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream

class EditProfileActivity :
    BaseBindingActivity<ActivityEditProfieBinding>(ActivityEditProfieBinding::inflate) {

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

        val metaRegisterModel =
            intent.getParcelableExtra<MetaRegisterModel>(EXTRA_META_REGISTER_MODEL)
        viewModel.setEditMode(metaRegisterModel)

        repeatOnStart {
            launch {
                viewModel.profileUiModel.collect {
                    binding.imageProfile.setCircleImageUri(it.image, R.drawable.ic_profile_default)
                    binding.inputNicknameProfile.setTextIfNew(it.nickName)
                }
            }
        }

        observeEvent(viewModel.profileEvent) {
            when (it) {
                is EditProfileEvent.Success -> startActivity(Intent(this, HomeActivity::class.java))
                is EditProfileEvent.Failure -> toast("실패, ${it.message}")
            }
        }

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.buttonDoneProfile.setOnClickListener {
            kotlin.runCatching {
                val drawable = binding.imageProfile.drawable as? BitmapDrawable
                val bitmap = drawable?.bitmap ?: throw IllegalStateException("Bitmap is null.")
                bitmap.saveToFile()
            }.onSuccess {
                viewModel.onDoneProfileEdit(it)
            }.onFailure {
                toast("비트맵 변환 실패, ${it.message}", Toast.LENGTH_LONG)
            }
        }

        binding.imageProfile.setOnClickListener {
            galleryActivityResultLauncher.launch(Intent().apply {
                type = "image/*"
                action = Intent.ACTION_GET_CONTENT
            })
        }

        binding.inputNicknameProfile.doAfterTextChanged {
            viewModel.onNickNameChanged(it?.toString().orEmpty())
        }
    }

    companion object {
        private const val EXTRA_META_REGISTER_MODEL = "metaRegisterModel"

        fun startEditProfileActivity(
            context: Context,
            metaRegisterModel: MetaRegisterModel? = null
        ) {
            context.startActivity(Intent(context, EditProfileActivity::class.java).apply {
                putExtra(EXTRA_META_REGISTER_MODEL, metaRegisterModel)
            })
        }
    }

    @Throws(FileNotFoundException::class)
    private fun Bitmap.saveToFile(): File {
        val directory = File(cacheDir, "profile").apply { mkdirs() }
        val file = File(directory, "profile.jpg")
        val out = FileOutputStream(file)
        compress(Bitmap.CompressFormat.JPEG, 100, out)
        return file
    }
}