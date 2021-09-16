package com.southernSunrise.passwordgenerator


import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val passwordGenerator = PasswordGenerator()
        val generateButton = findViewById<Button>(R.id.generateButton)
        val passwordCopier = findViewById<ImageButton>(R.id.copyPassword)
        val passwordText = findViewById<TextView>(R.id.textView)
        val specialWord = findViewById<EditText>(R.id.specialWord)
        val characterNum = findViewById<EditText>(R.id.characterNumber)




        characterNum.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val numberInput = characterNum.text.toString().trim()
                generateButton.isEnabled = numberInput.isNotEmpty() && 5 <= numberInput.toInt() && numberInput.toInt() < 16
            }
        })



       generateButton.setOnClickListener {
            val password: String = passwordGenerator.generatePassword(
                characterNum.text.toString().toInt(),
                specialWord.text.toString()
            )
            passwordText.text = password
            passwordCopier.setImageResource(R.drawable.ic_copy_2)
            Log.i("Generated password:", password)


        }



        fun copyTextToClipboard() {
            if(passwordText.text.toString() == "Click to generate"){
                Toast.makeText(this, "No password generated!", Toast.LENGTH_SHORT).show()
            }else {
                val textToCopy = passwordText.text
                val clipboardManager =
                    getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("text", textToCopy)
                clipboardManager.setPrimaryClip(clipData)
                Toast.makeText(this, "Password copied to clipboard", Toast.LENGTH_LONG).show()
            }


        }


            passwordCopier.setOnClickListener {
                    copyTextToClipboard()
                }


    }
}