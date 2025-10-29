package com.example.uinavegacion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.uinavegacion.domain.validation.validateEmail
import com.example.uinavegacion.domain.validation.validateStrongPass

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val submitting: Boolean = false
) {
    val valid get() = emailError == null && passwordError == null && email.isNotBlank() && password.isNotBlank()
}

class AuthViewModel : ViewModel() {
    private val _login = MutableStateFlow(LoginUiState())
    val login: StateFlow<LoginUiState> = _login.asStateFlow()

    fun onEmailChange(v: String) {
        _login.update { it.copy(email = v, emailError = validateEmail(v)) }
    }

    fun onPasswordChange(v: String) {
        _login.update { it.copy(password = v, passwordError = validateStrongPass(v)) }
    }

    fun submitLogin(onSuccess: () -> Unit, onInvalid: (String) -> Unit) = viewModelScope.launch {
        val s = _login.value
        val e = validateEmail(s.email)
        val p = validateStrongPass(s.password)
        _login.update { it.copy(emailError = e, passwordError = p) }
        if (e == null && p == null) {
            _login.update { it.copy(submitting = true) }
            // TODO: autenticar si aplica (llamar repo)
            _login.update { it.copy(submitting = false) }
            onSuccess()
        } else {
            onInvalid("Corrige los campos marcados.")
        }
    }
}


