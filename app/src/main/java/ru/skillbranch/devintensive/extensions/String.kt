package ru.skillbranch.devintensive.extensions

fun String.truncate(countOfSimbols: Int = 16): String {
    val str = this.trim()
    return when {
        str.length>countOfSimbols+1 -> str.substring(0, countOfSimbols).trim() + "..."
        str.length==countOfSimbols+1 -> str.substring(0, countOfSimbols).trim()
        else -> str
    }
}

fun String.stripHtml(): String {
    return ""
}
