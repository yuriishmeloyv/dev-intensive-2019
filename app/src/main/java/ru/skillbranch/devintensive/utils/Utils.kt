package ru.skillbranch.devintensive.utils

import android.content.Context


object Utils {

    fun parseFullName(fullName : String? ) : Pair<String?,String?>{
        val parts : List<String>? = fullName?.split(" ")
        var firstName : String? = parts?.getOrNull(0)
        firstName = when(firstName){
            "","null",null -> null
            else -> firstName
        }
        var lastName : String? = parts?.getOrNull(1)
        lastName = when(lastName){
            "","null",null -> null
            else -> lastName
        }

        return firstName to lastName
    }

    fun transliteration(payload: String?, divider: String = " "): String {

        if(payload.isNullOrBlank()){
            return ""
        }else{
            val translit: String = payload.replace(Regex("[абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ]")){

                when(it.value){
                    "а" -> "a"
                    "б" -> "b"
                    "в" -> "v"
                    "г" -> "g"
                    "д" -> "d"
                    "е" -> "e"
                    "ё" -> "e"
                    "ж" -> "zh"
                    "з" -> "z"
                    "и" -> "i"
                    "й" -> "i"
                    "к" -> "k"
                    "л" -> "l"
                    "м" -> "m"
                    "н" -> "n"
                    "о" -> "o"
                    "п" -> "p"
                    "р" -> "r"
                    "с" -> "s"
                    "т" -> "t"
                    "у" -> "u"
                    "ф" -> "f"
                    "х" -> "h"
                    "ц" -> "c"
                    "ч" -> "ch"
                    "ш" -> "sh"
                    "щ" -> "sh'"
                    "ъ" -> ""
                    "ы" -> "i"
                    "ь" -> ""
                    "э" -> "e"
                    "ю" -> "yu"
                    "я" -> "ya"
                    "А" -> "A"
                    "Б" -> "B"
                    "В" -> "V"
                    "Г" -> "G"
                    "Д" -> "D"
                    "Е" -> "E"
                    "Ё" -> "E"
                    "Ж" -> "Zh"
                    "З" -> "Z"
                    "И" -> "I"
                    "Й" -> "I"
                    "К" -> "K"
                    "Л" -> "L"
                    "М" -> "M"
                    "Н" -> "N"
                    "О" -> "O"
                    "П" -> "P"
                    "Р" -> "R"
                    "С" -> "S"
                    "Т" -> "T"
                    "У" -> "U"
                    "Ф" -> "F"
                    "Х" -> "H"
                    "Ц" -> "C"
                    "Ч" -> "Ch"
                    "Ш" -> "Sh"
                    "Щ" -> "Sh'"
                    "Ъ" -> ""
                    "Ы" -> "I"
                    "Ь" -> ""
                    "Э" -> "E"
                    "Ю" -> "Yu"
                    "Я" -> "Ya"
                    else -> it.value
                }
            }

            val massiveOfTranlit: List<String> = translit.split(" ")
            var n: Int = 0
            var translitToReturn: String = ""
            while(massiveOfTranlit.size - 1 > n){
                translitToReturn+=massiveOfTranlit.get(n) + divider
                n++
            }
            translitToReturn+=massiveOfTranlit.get(n)

            return translitToReturn
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {

        return when {
            firstName.isNullOrBlank() and lastName.isNullOrBlank() -> null
            !firstName.isNullOrBlank() and lastName.isNullOrBlank() -> firstName?.get(0)?.toUpperCase().toString()
            firstName.isNullOrBlank() and !lastName.isNullOrBlank() -> lastName?.get(0)?.toUpperCase().toString()
            !firstName.isNullOrBlank() and !lastName.isNullOrBlank() -> firstName?.get(0)?.toUpperCase().toString() + lastName?.get(0)?.toUpperCase().toString()
            else -> null
        }
    }

    fun convertPxToDp(context: Context, px: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (px / scale + 0.5f).toInt()
    }

    fun convertDpToPx(context: Context, dp: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    fun convertSpToPx(context: Context, sp: Int): Int {
        return (sp * context.resources.displayMetrics.scaledDensity).toInt()
    }


}

