package com.southernSunrise.passwordgenerator

import android.app.Service
import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton


open class PasswordGeneratorFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val generatorFragment: View =
            inflater.inflate(R.layout.fragment_generator, container, false)
        val passwordTextView = generatorFragment.findViewById<TextView>(R.id.passwordTextView)
        val generateButton: Button = generatorFragment.findViewById(R.id.generateButton)
        val numberOfCharacters: EditText = generatorFragment.findViewById(R.id.characterNumber)
        val specialWord: EditText = generatorFragment.findViewById(R.id.specialWord)
        val copyButton: FloatingActionButton =
            generatorFragment.findViewById(R.id.floatingCopyButton)

        copyButton.setOnClickListener {
            if(passwordTextView.text.toString() != "Click to generate"){
            val clipboard: ClipboardManager =
                context?.getSystemService(Service.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("TextView", passwordTextView.text.toString())
            clipboard.setPrimaryClip(clip)
            }
        }

        generateButton.setOnClickListener {
            val passwordGenerator = PasswordGenerator()
            if (numberOfCharacters.text.toString().toInt() in 5..15) {
                passwordTextView.text =
                    passwordGenerator.generatePassword(numberOfCharacters.text.toString()
                        .toInt(), specialWord.text.toString())
            } else if (numberOfCharacters.text.isNotEmpty()) {

            }
        }

        numberOfCharacters.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val numberInput = numberOfCharacters.text.toString().trim()
                generateButton.isEnabled =
                    numberInput.isNotEmpty() && 5 <= numberInput.toInt() && numberInput.toInt() <= 15
            }
        })


        return generatorFragment

    }


}