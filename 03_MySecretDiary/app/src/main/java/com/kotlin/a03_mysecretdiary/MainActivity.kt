package com.kotlin.a03_mysecretdiary

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.core.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() {
    private val numberPickerList : List<NumberPicker> by lazy {
        listOf(
            findViewById(R.id.numberPicker1),
            findViewById(R.id.numberPicker2),
            findViewById(R.id.numberPicker3)
        )
    }
    private val openButton : Button by lazy{
        findViewById(R.id.openButton)
    }
    private val changeButton : Button by lazy{
        findViewById(R.id.changeButton)
    }
    private var isChanging = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        bindViews()

    }

    private fun initViews() {
        numberPickerList.forEach {
            it.apply {
                minValue = 0
                maxValue = 9
            }
        }

    }

    private fun bindViews() {
        openButton.setOnClickListener {
            if (!isCorrect()){
                alertMessage(NOT_CORRECT_PW_TITLE,NOT_CORRECT_PW_MESSAGE)
                return@setOnClickListener
            }
            if (isChanging){
                alertMessage(CHANGING_PW_TITLE, CHANGING_PW_MESSAGE)
                return@setOnClickListener
            }
            val intent = Intent(this, DiaryActivity::class.java)
            startActivity(intent)
        }
        changeButton.setOnClickListener {
            if (!isChanging){//비번 변경중.
                if (!isCorrect()){
                    alertMessage(NOT_CORRECT_PW_TITLE, NOT_CORRECT_PW_MESSAGE)
                    return@setOnClickListener
                }
                isChanging = true
                colorAndMessage(it,R.color.password_change,"비밀번호 변경모드 활성화")
            }else{           //비번 저장.
                changePassword()
                isChanging = false
                colorAndMessage(it,R.color.gray,"비밀번호 변경완료")
            }

        }
    }

    private fun colorAndMessage(view : View ,changeColor: Int, toastMessage: String) {
        view.setBackgroundColor(this.getColor(changeColor))
        Toast.makeText(this,toastMessage,Toast.LENGTH_SHORT).show()
    }

    private fun isCorrect() : Boolean {
        var pw = ""
        numberPickerList.forEach {
            pw = "$pw${it.value}"
        }

        val pwPreference = getSharedPreferences("password",Context.MODE_PRIVATE)
        val savedPw =  pwPreference.getString("openKey","000")
        return pw == savedPw
    }

    private fun changePassword() {
        val pwPreferences = getSharedPreferences("password",Context.MODE_PRIVATE)
        var pw = ""
        numberPickerList.forEach {
            pw = "$pw${it.value}"
        }
        pwPreferences.edit(commit = true){
            putString("openKey",pw)
        }
    }
    private fun alertMessage(title : String,message: String){
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("확인") { _, _->}
            .create()
            .show()
    }

    companion object{
        const val NOT_CORRECT_PW_TITLE = "알림"
        const val NOT_CORRECT_PW_MESSAGE = "비밀번호가 틀렸습니다. 다시 확인해주세요"
        const val CHANGING_PW_TITLE = "확인"
        const val CHANGING_PW_MESSAGE = "비밀번호를 변경 중입니다. 변경후 시도하세요"
    }
}