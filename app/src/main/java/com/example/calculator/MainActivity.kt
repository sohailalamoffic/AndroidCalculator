package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //NUMBERS
        tvOne.setOnClickListener { appendOnExpression("1", true) }
        tvTwo.setOnClickListener { appendOnExpression("2", true) }
        tvThree.setOnClickListener { appendOnExpression("3", true) }
        tvFour.setOnClickListener { appendOnExpression("4", true) }
        tvFive.setOnClickListener { appendOnExpression("5", true) }
        tvSix.setOnClickListener { appendOnExpression("6", true) }
        tvSeven.setOnClickListener { appendOnExpression("7", true) }
        tvEight.setOnClickListener { appendOnExpression("8", true) }
        tvNine.setOnClickListener { appendOnExpression("9", true) }
        tvDot.setOnClickListener { appendOnExpression(".", true) }
        tvZero.setOnClickListener { appendOnExpression("0", true) }

        //OPERATORS

        tvPlus.setOnClickListener { appendOnExpression("+", true) }
        tvMinus.setOnClickListener { appendOnExpression("-", true) }
        tvMult.setOnClickListener { appendOnExpression("*", true) }
        tvDivide.setOnClickListener { appendOnExpression("/", true) }
        tvOpen.setOnClickListener { appendOnExpression("(", true) }
        tvClose.setOnClickListener { appendOnExpression(")", true) }

        tvClear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
            tvDot.isEnabled = true
        }

        tvBack.setOnClickListener {
            val string = tvExpression.text.toString()
            if (string.isNotEmpty()) {
                tvExpression.text = string.substring(0, string.length - 1)
            }
            if (!tvExpression.text.contains("."))
                tvDot.isEnabled = true
            tvResult.text = ""
        }

        tvEquals.setOnClickListener{
            try {
                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    tvResult.text = longResult.toString()
                }
                else{
                    tvResult.text = result.toString()
                }
            }catch (e : Exception){
                Log.d("Exception","message : " + e.message)
            }
            Toast.makeText(applicationContext,"This Calculator is made by Sohail" , Toast.LENGTH_SHORT).show()
        }
    }


    fun appendOnExpression(string: String, canClear: Boolean) {
        if(tvResult.text.isNotEmpty()){
            tvExpression.text = ""
        }
        if (tvExpression.text.contains("."))
            tvDot.isEnabled = false
        if (canClear) {
            tvResult.text = ""
            tvExpression.append(string)
        } else {
            tvExpression.append(tvResult.text)
            tvResult.append(string)
            tvResult.text = ""
        }
    }

}