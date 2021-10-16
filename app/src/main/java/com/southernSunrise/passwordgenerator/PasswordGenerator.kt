package com.southernSunrise.passwordgenerator

class PasswordGenerator {
    companion object {
        var characters = mutableListOf("abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ" )
    }

    fun generatePassword(length: Int, specialWord: String = ""): String {

        val stringBuilder = StringBuilder(length)

        for (x in 0 until length) {
            val randomCharacterIndex = (characters.indices).random()
            stringBuilder.append(characters[randomCharacterIndex][characters[randomCharacterIndex].indices.random()])

        }
        stringBuilder.insert((0 until length).random(), specialWord)
        return stringBuilder.toString()

    }


}