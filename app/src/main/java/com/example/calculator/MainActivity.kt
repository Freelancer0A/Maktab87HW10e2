package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private var canAddOperation = false
    private var canAddDecimal = true
    lateinit var tvHistory: TextView
    lateinit var tvResult: TextView
    private lateinit var viewModel: CalculatorViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]
        tvHistory = findViewById<TextView>(R.id.tvShowHistory)
        tvResult = findViewById<TextView>(R.id.tvShowResult)
    }

    fun allClearAction(v: View) {
        canAddDecimal = true
        tvHistory.text = ""
        tvResult.text = ""
    }

    fun backSpace(v: View) {
        val length = tvHistory.length()
        if (length > 0) {
            tvHistory.text = tvHistory.text.subSequence(0, length - 1)
        }
    }
    fun equalsAction(v:View){
        var result = calculateResults()
        tvResult.text = result
        viewModel.addHistory(tvHistory.text.toString())
    }

    private fun calculateResults(): String {
        val digitsOperators = digitOperators()
        if (digitsOperators.isEmpty()) return ""
        val timesDivision = timeDivisionCalculate(digitsOperators)
        if (timesDivision.isEmpty()) return ""
        val result = addSubtractCalculate(timesDivision)
        return result.toString()
    }

    private fun addSubtractCalculate(passedList: MutableList<Any>): Float {
        var result = passedList[0] as Float
        for (i in passedList.indices){
            if (passedList[i] is Char && i != passedList.lastIndex){
                val  operator = passedList[i]
                val nextDigit = passedList[i+1] as Float
                if (operator == '+') result += nextDigit
                if (operator == '-') result -+ nextDigit
            }
        }
        return result
    }

    private fun timeDivisionCalculate(passedList: MutableList<Any>): MutableList<Any> {
        var list = passedList
        while (list.contains('*') || list.contains('/')){
            list = calTimeDiv(list)
        }
        return list
    }

    private fun calTimeDiv(passedList: MutableList<Any>): MutableList<Any> {
        val newList = mutableListOf<Any>()
        var reStartIndex = passedList.size
        for (i in passedList.indices){
            if (passedList[i] is Char && i != passedList.lastIndex && i<reStartIndex){
                val operator = passedList[i]
                val prevDigits = passedList[i-1] as Float
                val nextDigits = passedList[i+1] as Float
                when (operator){
                    '*' -> {
                        newList.add(prevDigits*nextDigits)
                        reStartIndex = i+1
                    }
                    '/' ->{
                        newList.add(prevDigits/nextDigits)
                        reStartIndex = i+1
                    }
                    else ->{
                        newList.add(prevDigits)
                        newList.add(operator)
                    }
                }
            }
            if (i >reStartIndex){
                newList.add(passedList[i])
            }
        }
        return newList

    }

    private fun digitOperators():MutableList<Any>{
        val list = mutableListOf<Any>()
        var currentDigit = ""
        for (char in tvHistory.text){
            if (char.isDigit() || char == '.')
                currentDigit += char
            else{
                list.add(currentDigit.toFloat())
                currentDigit = ""
                list.add(char)
            }
        }
        if (currentDigit != "")
            list.add(currentDigit.toFloat())
        return list
    }

    fun onClickOperation(view: View) {
        if (view is Button) {
            if (view.text == ".") {
                if (canAddDecimal) {
                    tvHistory.append(view.text)
                    canAddDecimal = false
                }
            } else
                tvHistory.append(view.text)
            canAddOperation = true
        }

    }

    fun onClickOperator(view: View) {
        if (view is Button && canAddOperation) {
            tvHistory.append(view.text)
            canAddOperation = false
            canAddDecimal = true
        }
    }
    fun historyOnClick(view:View){
        startActivity(Intent(baseContext,HistoryActivity::class.java))
    }
}