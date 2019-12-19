package br.edu.ifsp.scl.calculadorasdmkt.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import br.edu.ifsp.scl.calculadorasdmkt.R
import br.edu.ifsp.scl.calculadorasdmkt.model.CalculatorPreferences
import br.edu.ifsp.scl.calculadorasdmkt.utils.Calculadora
import br.edu.ifsp.scl.calculadorasdmkt.utils.Operador
import br.edu.ifsp.scl.calculadorasdmkt.utils.commaOrSemicolon
import kotlinx.android.synthetic.main.fragment_calculadora_avancada.*

class CalculadoraAvancadaFragment: Fragment(), View.OnClickListener {
    private var concatenaLcd: Boolean = true
    private var separator: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calculadora_avancada, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        for (i in 0 until (view as ViewGroup).childCount) {
            val v: View = view[i]
            if (v is Button) {
                v.setOnClickListener(::onClick)
            }
        }

        separator = separator.toString().commaOrSemicolon(view.context, CalculatorPreferences)
    }

    override fun onClick(p: View?) {

        when (p) {
            // Números
            umBt, doisBt, tresBt, quatroBt, cincoBt,
            seisBt, seteBt, oitoBt, noveBt, zeroBt -> {
                // Limpa LCD se último clicado foi um operador
                if (!concatenaLcd) {
                    lcdTv.text = ""
                }
                lcdTv.append((p as Button).text.toString())
                concatenaLcd = true
            }
            // Ponto
            pontoBt -> {

                if (!lcdTv.text.toString().contains(separator.toString())) {
                    if (!concatenaLcd) {
                        lcdTv.text = "0"
                    }
                    lcdTv.append(separator)
                    concatenaLcd = true
                    return
                }
            }
            // Operadores
            adicaoBt -> cliqueOperador(Operador.ADICAO)
            subtracaoBt -> cliqueOperador(Operador.SUBTRACAO)
            multiplicacaoBt -> cliqueOperador(Operador.MULTIPLICACAO)
            divisaoBt -> cliqueOperador(Operador.DIVISAO)
            resultadoBt -> cliqueOperador(Operador.RESULTADO)
            porcentageBt -> cliqueOperador(Operador.PORCENTAGEM)
            raizBt -> cliqueOperador(Operador.RAIZ)

            //Limpar tela
            cBt -> {
                lcdTv.text = ""
                Calculadora.operando = 0.0.toFloat()
            }
            ceBt -> {
                Calculadora.operando = 0.0.toFloat()
                lcdTv.text = ""
            }
        }
    }

    private fun cliqueOperador(operador: Operador) {
            lcdTv.text = Calculadora.calcula(
                lcdTv.text.toString().replace(separator.toString(), ".").toFloat(), operador
            ).toString().replace(".", separator.toString())

        concatenaLcd = false
    }
}

