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

    fun transliteration(payload: String, devider: String = " "): String {
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

