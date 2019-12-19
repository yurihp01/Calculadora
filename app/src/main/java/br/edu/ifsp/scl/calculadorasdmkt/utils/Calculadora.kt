package br.edu.ifsp.scl.calculadorasdmkt.utils

import kotlin.math.sqrt

/* Classe de enumeração para constantes de operadores */
enum class Operador {
    RESULTADO, ADICAO, SUBTRACAO, MULTIPLICACAO, DIVISAO, RAIZ, PORCENTAGEM,
}

/* Singleton que calcula operações aritméticas básicas */
object Calculadora {
    // primeiro operando
    var operando: Float = 0.0f

    // operador que será aplicado entre primeiro e segundo operando
    var operador: Operador =
        Operador.RESULTADO

    /* calcula um valor de retorno com base no operando e operador já existentes, novo valor
     e atualiza valor de operando e operador */
    fun calcula(valor: Float, operador: Operador): Float {
        if (operador == Operador.RAIZ || operador == Operador.PORCENTAGEM) {
            operando = if (operador == Operador.RAIZ) sqrt(valor)
            else valor / 100
        } else {
            when (Calculadora.operador) {
                Operador.RESULTADO -> operando = valor
                Operador.ADICAO -> operando += valor
                Operador.SUBTRACAO -> operando -= valor
                Operador.MULTIPLICACAO -> operando *= valor
                else -> operando /= valor
            }
            Calculadora.operador = operador
        }
        return operando
    }
}