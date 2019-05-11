package bitbox.project.presentation.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import bitbox.project.domain.model.user.UserInfo
import bitbox.project.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var userInfo: UserInfo
}