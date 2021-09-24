package com.example.a01_bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private val confirmButton : Button by lazy{
        findViewById(R.id.confirmButton)
    }
    private val height : EditText by lazy {
        findViewById(R.id.height)
    }
    private val weight : EditText by lazy {
        findViewById(R.id.weight)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        confirmButton.setOnClickListener {
            if( height.text.isEmpty() || height.text.isEmpty() || height.text.toString().toFloat() <= 0 || weight.text.toString().toFloat() <= 0){
                Toast.makeText(this, "입력값을 확인하세요",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                val bmi = weight.text.toString().toFloat() / ( (height.text.toString().toFloat() * 0.01).pow(2) )
                val bmiResult = when{
                    bmi <= 18.5 -> "저체중"
                    bmi <= 22.9 -> "정상"
                    bmi < 25.0 -> "과체중"
                    else -> "비만"
                }
                height.text.clear()
                weight.text.clear()
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("result",bmi)
                intent.putExtra("resultText",bmiResult)
                startActivity(intent)

            }
        }
        bindViews()
    }

    private fun bindViews(){

    }

}