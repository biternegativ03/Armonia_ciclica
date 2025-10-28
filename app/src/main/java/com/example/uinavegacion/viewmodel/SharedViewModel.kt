package com.example.uinavegacion.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class SharedViewModel : ViewModel() {
    var age by mutableStateOf("")
    var weight by mutableStateOf("")
    var height by mutableStateOf("")
    var contraceptiveMethod by mutableStateOf("")
    var lastPeriodDate by mutableStateOf<LocalDate?>(null)
}
