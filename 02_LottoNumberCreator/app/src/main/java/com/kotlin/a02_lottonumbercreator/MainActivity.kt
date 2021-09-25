package com.kotlin.a02_lottonumbercreator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private val numberTextViewList : List<TextView> by lazy {
        listOf(
            findViewById(R.id.number1),
            findViewById(R.id.number2),
            findViewById(R.id.number3),
            findViewById(R.id.number4),
            findViewById(R.id.number5),
            findViewById(R.id.number6)
        )
    }
    private val numberPicker : NumberPicker by lazy {
        findViewById(R.id.numberPicker)
    }
    /*p rivate val numberPicker = findViewById<NumberPicker>(R.id.numberPicker).apply{
         minValue = 1
       maxValue = 45
    }   */
    private val addButton : Button by lazy {
       findViewById(R.id.addButton)
    }
    private val createButton : Button by lazy {
       findViewById(R.id.createButton)
    }
    private val initButton : Button by lazy {
       findViewById(R.id.initButton)
    }
    private val lottoSet = mutableSetOf<Int>() //로또 번호


    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)
       initViews()
       bindViews()
    }

    private fun initViews() {
       numberPicker.apply {
           minValue = 1
           maxValue = 45
       }
    }

    private fun bindViews() {
        addButton.setOnClickListener {
            val number = numberPicker.value
            if (!lottoSet.contains(number)){
                if (lottoSet.size < 5) {
                    lottoSet.add(number)
                    numberTextViewList[lottoSet.size - 1].isVisible = true
                    numberTextViewList[lottoSet.size - 1].text = number.toString()
                }else{
                    Toast.makeText(this, "선택은 5개까지만 가능합니다.",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }else{
                Toast.makeText(this, "이미 추가되어있는 숫자입니다",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

    }
}