package com.example.a01_bmicalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity(){
    private val goBackButton : Button by lazy{
        findViewById(R.id.goBackButton)
    }
    private val result : TextView by lazy{
        findViewById(R.id.result)
    }
    private val resultText : TextView by lazy{
        findViewById(R.id.resultText)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        initViews()
        bindViews()
    }

    private fun initViews() {
        val result1     = intent.getStringExtra("result")
        val resultText1 = intent.getStringExtra("resultText")

        result.text = result1
        resultText.text = resultText1
    }

    private fun bindViews() {
        goBackButton.setOnClickListener {
            finish()
        }
    }

}