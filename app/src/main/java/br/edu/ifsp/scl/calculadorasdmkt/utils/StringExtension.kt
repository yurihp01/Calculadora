package br.edu.ifsp.scl.calculadorasdmkt.utils

import android.content.Context
import br.edu.ifsp.scl.calculadorasdmkt.model.CalculatorPreferences
import br.edu.ifsp.scl.calculadorasdmkt.model.Separador

fun String.commaOrSemicolon(
    context: Context,
    preferences: CalculatorPreferences
) : String {
    preferences.getPreferences(context)
    return if (preferences.readString(
                preferences.SEPARATOR_TYPE_KEY) == Separador.VIRGULA.name) "," else "."
}