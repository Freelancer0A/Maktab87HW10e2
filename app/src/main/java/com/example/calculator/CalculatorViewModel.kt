package com.example.calculator

import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    var history = arrayListOf<String>()
    private var current = 0
    private var firstNumber = 0
    private var secondNumber = 0
    private var symbol = ' '
    fun addHistory(s: String) {
        history.add(s)
    }
    fun getCurrentValue():Int{
        return current
    }
    fun getFirstNumber():Int{
        return firstNumber.toInt()
    }
    fun getSecondNumber():Int{
        return secondNumber.toInt()
    }
    fun getSymbol():Char{
        return symbol
    }
    fun setCurrentValue(value: Int){
        current = value
    }
    fun setFirstNumber(value: Int){
        firstNumber = value
    }
    fun setSecondNumber(value: Int){
        secondNumber = value
    }
    fun setSymbol(value: Char){
        symbol = value
    }
}