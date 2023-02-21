package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class HistoryActivity : AppCompatActivity() {
    private lateinit var viewModel: CalculatorViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        viewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]
        Toast.makeText(baseContext,viewModel.history[0],Toast.LENGTH_SHORT).show()
//        val tvs =
//            arrayListOf(R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5)
//        if (viewModel.history.size <= 5) {
//            for (i in viewModel.history.indices) {
//                findViewById<TextView>(tvs[i]).setText(i)
//            }
//        } else {
//            for (i in 0..5) {
//                findViewById<TextView>(tvs[i]).text = viewModel.history[i]
//            }
//        }
    }
}