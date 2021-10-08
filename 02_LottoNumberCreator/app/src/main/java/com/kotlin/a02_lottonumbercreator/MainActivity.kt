package com.kotlin.a02_lottonumbercreator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import kotlin.math.log

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
    private val addButton : Button by lazy {
       findViewById(R.id.addButton)
    }
    private val createButton : Button by lazy {
       findViewById(R.id.createButton)
    }
    private val initButton : Button by lazy {
       findViewById(R.id.initButton)
    }
    private val lottoList = mutableListOf<Int>() //로또 번호
    private var isCreate = false

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
            if (!lottoList.contains(number)){
                if (lottoList.size < 5) {
                    lottoList.add(number)
                    numberTextViewList[lottoList.size - 1].apply {
                        isVisible = true
                        text = number.toString()
                        setNumberColor(this,number)
                    }

                }else{
                    Toast.makeText(this, "선택은 5개까지만 가능합니다.",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }else{
                Toast.makeText(this, "이미 추가되어있는 숫자입니다",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }
        createButton.setOnClickListener {
            if (isCreate){
                lottoList.clear()
            }
            isCreate = true
            numberTextViewList.forEach {
                it.isVisible = false
            }
            val randomList = mutableListOf<Int>()
            for (i in 1 .. 45){
                if (!lottoList.contains(i)){
                    randomList.add(i) //12345..45
                }
            }
            randomList.shuffle()
            for (i in 0 .. 5-lottoList.size){
                lottoList.add(randomList[i])
            }
            setResultLottoNumber();
        }
        initButton.setOnClickListener {
            isCreate = false
            lottoList.clear()
            numberTextViewList.forEach {
                it.isVisible = false
            }
        }
    }
    private fun setResultLottoNumber(){
        lottoList.sort()
        Log.d("2",lottoList.toString())
        lottoList.forEachIndexed { index, value1->
            numberTextViewList[index].apply{
                text = "$value1"
                isVisible = true
                setNumberColor(this,value1)

            }
        }
    }

    private fun setNumberColor(textview:TextView , value :Int) {
        textview.gravity = Gravity.CENTER
        when(value){
            in 1 ..10 -> textview.background = ContextCompat.getDrawable(this,R.drawable.circle_yellow)
            in 11..20 -> textview.background = ContextCompat.getDrawable(this,R.drawable.circle_blue)
            in 21..30 -> textview.background = ContextCompat.getDrawable(this,R.drawable.circle_red)
            in 31..40 -> textview.background = ContextCompat.getDrawable(this,R.drawable.circle_gray)
            else      -> textview.background = ContextCompat.getDrawable(this,R.drawable.circle_green)

        }
    }
}