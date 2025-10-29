package com.example.uinavegacion.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.example.uinavegacion.domain.validation.*

data class RegisterUiState(
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val pass: String = "",
    val confirm: String = "",
    val nameError: String? = null,
    val emailError: String? = null,
    val phoneError: String? = null,
    val passError: String? = null,
    val confirmError: String? = null
) {
    val valid get() =
        nameError == null && emailError == null && phoneError == null && passError == null && confirmError == null &&
        name.isNotBlank() && email.isNotBlank() && phone.isNotBlank() && pass.isNotBlank() && confirm.isNotBlank()
}

class RegisterViewModel : ViewModel() {
    private val _ui = MutableStateFlow(RegisterUiState())
    val ui: StateFlow<RegisterUiState> = _ui.asStateFlow()

    fun onName(v: String)   { _ui.update { it.copy(name = v,   nameError = validateNameLettersOnly(v)) } }
    fun onEmail(v: String)  { _ui.update { it.copy(email = v,  emailError = validateEmail(v)) } }
    fun onPhone(v: String)  { _ui.update { it.copy(phone = v,  phoneError = validatePhoneDigitsOnly(v)) } }
    fun onPass(v: String)   { _ui.update { it.copy(pass = v,   passError = validateStrongPass(v), confirmError = validateConfirm(v, _ui.value.confirm)) } }
    fun onConfirm(v: String){ _ui.update { it.copy(confirm = v,confirmError = validateConfirm(_ui.value.pass, v)) } }
}
