package com.example.uinavegacion.domain.cycle

import java.time.LocalDate

/**
 * Representa el período fértil del ciclo.
 *
 * @property start Inicio de la ventana fértil (5 días antes de ovulación)
 * @property peak Día de ovulación (máxima fertilidad)
 * @property end Fin de la ventana fértil (1 día después de ovulación)
 */
data class FertileWindow(
    val start: LocalDate,
    val peak: LocalDate,
    val end: LocalDate
)