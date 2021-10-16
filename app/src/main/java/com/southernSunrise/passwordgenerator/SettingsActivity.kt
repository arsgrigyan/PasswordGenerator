package com.southernSunrise.passwordgenerator

import android.R.id
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import android.R.id.toggle
import android.annotation.SuppressLint


class SettingsActivity : AppCompatActivity() {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(findViewById(R.id.settings_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val switchButton: Switch = findViewById(R.id.switch_button)
        val symbolsUseButton: Switch = findViewById(R.id.add_symbols)
        val numbersUseButton: Switch = findViewById(R.id.add_numbers)
        val appSettingPrefs: SharedPreferences = getSharedPreferences("AppSettingPrefs", 0)
        val sharedPrefsEdit: SharedPreferences.Editor = appSettingPrefs.edit()
        val isNightModeOn: Boolean = appSettingPrefs.getBoolean("NightMode", false)
        val areSymbolsInUse: Boolean = appSettingPrefs.getBoolean("SymbolsInUse", false)
        val areNumbersInUse: Boolean = appSettingPrefs.getBoolean("NumbersInUse", false)


        if (isNightModeOn) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        switchButton.isChecked = isNightModeOn

        switchButton.setOnClickListener {
            if (isNightModeOn) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPrefsEdit.putBoolean("NightMode", false)
                sharedPrefsEdit.apply()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPrefsEdit.putBoolean("NightMode", true)
                sharedPrefsEdit.apply()
            }

        }
        if (areSymbolsInUse) {
            PasswordGenerator.characters += "!#$/%&*:=?-_"
        } else PasswordGenerator.characters.remove("!#$/%&*:=?-_")
        symbolsUseButton.isChecked = areSymbolsInUse

        symbolsUseButton.setOnClickListener {
            if (areSymbolsInUse) {
                PasswordGenerator.characters.remove("!#$/%&*:=?-_")
                sharedPrefsEdit.putBoolean("SymbolsInUse", false)
                sharedPrefsEdit.apply()
            } else {
                PasswordGenerator.characters.add("!#$/%&*:=?-_")
                sharedPrefsEdit.putBoolean("SymbolsInUse", true)
                sharedPrefsEdit.apply()
            }

        }

        if (areNumbersInUse) {
            PasswordGenerator.characters += "0123456789"
        } else PasswordGenerator.characters.remove("0123456789")
        numbersUseButton.isChecked = areNumbersInUse
        numbersUseButton.setOnClickListener {
            if (areNumbersInUse) {
                PasswordGenerator.characters -= "0123456789"
                sharedPrefsEdit.putBoolean("NumbersInUse", false)
                sharedPrefsEdit.apply()
            } else {
                PasswordGenerator.characters += "0123456789"
                sharedPrefsEdit.putBoolean("NumbersInUse", true)
                sharedPrefsEdit.apply()
            }
        }
    }

}