package com.southernSunrise.passwordgenerator

class PasswordGenerator {

    private val characters = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!#$%&*:=?-_"

    fun generatePassword(length:Int, specialWord: String = ""): String {

        val stringBuilder = StringBuilder(length)

        for (x in 0 until length) {
            val randomCharacterIndex = (characters.indices).random()
            stringBuilder.append(characters[randomCharacterIndex])

        }
        stringBuilder.insert((0 until length).random(), specialWord)
        return stringBuilder.toString()

    }


}