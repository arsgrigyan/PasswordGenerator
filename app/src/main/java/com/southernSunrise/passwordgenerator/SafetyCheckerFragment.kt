package com.southernSunrise.passwordgenerator

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible


open class SafetyCheckerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val checkerFragment =  inflater.inflate(R.layout.fragment_safetycheck, container, false)
        val passwordToBeChecked:EditText = checkerFragment.findViewById(R.id.checkEditText)
        val checkerButton:Button = checkerFragment.findViewById(R.id.checkButton)
        val answerRepresentative:TextView = checkerFragment.findViewById(R.id.representative)

        checkerButton.setOnClickListener {

            answerRepresentative.isVisible = answerRepresentative.text.toString() != "lets see" || answerRepresentative.text.isEmpty()
            when (passwordToBeChecked.text.toString().length) {
                0 -> {
                    answerRepresentative.text = "no password..."
                }
                in 1..6 -> {
                    answerRepresentative.text = "Too short :("
                }
                in 7..10 -> {
                    answerRepresentative.text = "Looks okay :)"
                }
                in 10..15 -> {
                    answerRepresentative.text = "SECURE !"
                }else -> answerRepresentative.text = "UNBREAKABLE !"

            }
        }

        passwordToBeChecked.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                answerRepresentative.isVisible = answerRepresentative.text.isEmpty()

            }
        })

        return checkerFragment
    }


    }