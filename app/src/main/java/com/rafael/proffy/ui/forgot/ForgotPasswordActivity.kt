package com.rafael.proffy.ui.forgot

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.core.widget.addTextChangedListener
import com.rafael.proffy.R
import com.rafael.proffy.databinding.ActivityForgotPasswordBinding
import com.rafael.proffy.ui.finallyComponent.FinallyActivity
import com.rafael.proffy.utils.ValidationUtils

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        val view = binding.root

        enableEdgeToEdge()
        setContentView(view)
        supportActionBar?.hide()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val keyboard = insets.getInsets(WindowInsetsCompat.Type.ime())
            v.setPadding(systemBars.left, 0, systemBars.right, maxOf(systemBars.bottom, keyboard.bottom))
            binding.containerBackground.updatePadding(top = systemBars.top)
            if (insets.isVisible(WindowInsetsCompat.Type.ime())) {
                binding.scrollView.post {
                    binding.scrollView.smoothScrollTo(0, binding.contentContainer.height)
                }
            }
            insets
        }

        val buttonGoBack = binding.buttonGoBack
        val textInputEmail = binding.textInputEditEmail
        val buttonSendEmail = binding.buttonSendEmail

        val enabledButtonColor = ContextCompat.getColor(this, R.color.green)
        val disabledButtonColor = ContextCompat.getColor(this, R.color.shape_disable)

        buttonGoBack.setOnClickListener {
            goBack()
        }

        textInputEmail.addTextChangedListener {
            val email = textInputEmail.text?.toString()?.trim() ?: ""
            val validEmail = ValidationUtils.isValidEmail(email)
            showEmailError(email.isNotEmpty() && !validEmail)
            setButtonState(validEmail, enabledButtonColor, disabledButtonColor)
        }

        buttonSendEmail.setOnClickListener {
            handleSendEmail()
        }
    }

    private fun goBack() {
        finish()
    }

    private fun showEmailError(show: Boolean) {
        binding.textInputLayoutEmail.error =
            if (show) getString(R.string.forgot_error_invalid_email) else null
    }

    private fun setButtonState(enabled: Boolean, enabledColor: Int, disabledColor: Int) {
        binding.buttonSendEmail.isEnabled = enabled
        binding.buttonSendEmail.setText(
            if (enabled) R.string.forgot_button_send else R.string.forgot_button_disabled
        )
        val color = if (enabled) enabledColor else disabledColor

        binding.buttonSendEmail.backgroundTintList = ColorStateList.valueOf(color)
        val textColor = if (enabled)
            ContextCompat.getColor(this, R.color.shape_01_white)
        else
            ContextCompat.getColor(this, R.color.text_complement)
        binding.buttonSendEmail.setTextColor(textColor)
    }

    private fun handleSendEmail() {
        val intent = Intent(this, FinallyActivity::class.java)
        intent.putExtra("textViewTitleFinally", getString(R.string.forgot_done_title))
        intent.putExtra("textViewSubtitleFinally", getString(R.string.forgot_done_subtitle))
        intent.putExtra("titleButton", getString(R.string.forgot_done_button))

        startActivity(intent)
        finish()
    }
}
