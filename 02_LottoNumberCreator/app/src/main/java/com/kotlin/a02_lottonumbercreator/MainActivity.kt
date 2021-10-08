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
    private val randomList = mutableListOf<Int>()
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
                    numberTextViewList[lottoList.size - 1].isVisible = true
                    numberTextViewList[lottoList.size - 1].text = number.toString()
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
                Toast.makeText(this,"이미 생성되었습니다. 초기화 후 다시 시도해 주세요 ",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            isCreate = true
            for (i in numberPicker.minValue .. numberPicker.maxValue){
                if (!lottoList.contains(i)){
                    randomList.add(i)
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
        lottoList.forEachIndexed { index, value->
            numberTextViewList[index].apply{
                text = "$value"
                isVisible = true
            }
        }
    }
}