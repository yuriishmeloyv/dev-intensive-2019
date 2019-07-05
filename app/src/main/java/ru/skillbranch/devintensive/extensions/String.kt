package ru.skillbranch.devintensive.extensions

fun String.truncate(countOfSimbols: Int = 16): String {
    val str = this.trim()
    if (str.length>countOfSimbols+1){
        return str.substring(0, countOfSimbols+1) + "..."
    }else if (str.length==countOfSimbols+1){
        return str.substring(0, countOfSimbols+1)
    }
    else{
        return str
    }

}

fun String.stripHtml(): String {
    return ""
}
