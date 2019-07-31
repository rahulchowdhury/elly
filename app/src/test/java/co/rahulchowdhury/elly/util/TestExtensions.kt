package co.rahulchowdhury.elly.util

import org.mockito.ArgumentMatchers.eq

fun <T : Any> safeArgumentEq(value: T): T = eq(value) ?: value
