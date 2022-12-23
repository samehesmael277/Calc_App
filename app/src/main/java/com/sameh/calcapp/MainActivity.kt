package com.sameh.calcapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val plus = "plus"
    private val minus = "myns"
    private val times = "times"
    private val div = "div"

    private lateinit var firstNumber: EditText
    private lateinit var secondNumber: EditText
    private lateinit var buttonCalc: Button
    private lateinit var resultText: TextView
    private lateinit var chooseDropMenu: AutoCompleteTextView

    private var result = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initilization()
        adaptorCreation()


        buttonCalc.setOnClickListener() {
            CheekErrorsAndSetResult()
        }
        chooseDropMenu.setOnItemClickListener { adapterView, view, i, l ->
            CheekErrorsAndSetResult()
        }

    }

    private fun initilization(){
        firstNumber = findViewById(R.id.Number1)
        secondNumber = findViewById(R.id.Number2)
        buttonCalc = findViewById(R.id.button_calc)
        resultText = findViewById(R.id.text_result)
        chooseDropMenu = findViewById(R.id.chooseMenu)
    }

    private fun adaptorCreation() {
        val my_list = listOf(plus, minus, times, div)
        val myAdapter = ArrayAdapter(this,R.layout.drop_menu_for_adaptor,my_list)
        chooseDropMenu.setAdapter(myAdapter)
    }

    private fun calcResult() {
        val f_number = firstNumber.text.toString().toDouble()
        val s_number = secondNumber.text.toString().toDouble()
        val opreation = chooseDropMenu.text.toString()
        result = when(opreation) {
            plus -> f_number + s_number
            minus -> f_number - s_number
            times -> f_number * s_number
            div -> f_number / s_number
            else -> 0.0
        }
    }

    private fun CheekErrorsAndSetResult() {
        if(firstNumber.text.toString().isNotEmpty() && secondNumber.text.toString().isNotEmpty()) {
            calcResult()
        }
        else if (firstNumber.text.toString().isEmpty() && secondNumber.text.toString().isEmpty()) {
            firstNumber.setError("You must enter number!")
            secondNumber.setError("You must enter number!")
        }
        else if (firstNumber.text.toString().isEmpty()) {
            firstNumber.setError("You must enter number!")
        }
        else {
            secondNumber.setError("You must enter number!")
        }

        resultText.setText(result.toString())

    }


}