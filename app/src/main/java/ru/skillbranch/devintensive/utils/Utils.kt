package ru.skillbranch.devintensive.utils

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

    fun transliteration2(payload: String, devider: String = " "): String {
        val arrayRu: Array<String> = arrayOf("а","б","в","г","д","е","ё","ж","з","и","й","к","л","м","н","о","п","р","с","т","у","ф","х","ц","ч","ш","щ","ъ","ы","ь","э","ю","я")
        val arrayEng: Array<String> = arrayOf("a","b","v","g","d","e","e","zh","z","i","i","k","l","m","n","o","p","r","s","t","u","f","h","c","ch","sh","sh'","","i","","e","yu","ya")
        val parts : List<String>? = payload?.split(" ")
        var transliterationString: String = ""
        var symbolEng: String = ""
        var i: Int = 0

        parts?.forEach {

            part -> for(symbol in part) when {
                arrayRu.indexOf(symbol.toLowerCase().toString())==-1 -> transliterationString += symbol
                //если символ в верхнем регитсре, то переводим в нижний и ищем в массиве киррилических символов
                symbol.isUpperCase() -> {
                    symbolEng = arrayEng[arrayRu.indexOf(symbol.toLowerCase().toString())]

                    if(symbolEng.length>1){
                        symbolEng = (symbolEng[0].toString().toUpperCase() + symbolEng[1].toString())
                    }else{
                        symbolEng = symbolEng.toUpperCase()
                    }

                    transliterationString += symbolEng
                }
                else -> transliterationString += arrayEng[arrayRu.indexOf(symbol.toString())]
            }
            i++
            if(i<parts.size) {
                transliterationString = transliterationString + devider
            }
            //если не находим символ в массиве кирилличесикх символов, то оставляем как есть это латиница
        }

        return  transliterationString

    }

    fun transliteration(payload: String?, divider: String = " "): String {

        if(payload.isNullOrBlank()){
            return "Необходимо передать занчение"
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


}

