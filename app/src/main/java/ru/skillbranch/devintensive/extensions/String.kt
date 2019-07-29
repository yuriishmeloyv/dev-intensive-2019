package ru.skillbranch.devintensive.extensions

fun String.truncate(countOfSimbols: Int = 16): String {
    val str = this.trim()
    return when {
        str.length>countOfSimbols+1 -> str.substring(0, countOfSimbols).trim() + "..."
        str.length==countOfSimbols+1 -> str.substring(0, countOfSimbols).trim()
        else -> str
    }
}

fun String.stripHtml(): String{
    val htmlTags = Regex("(<.*?>)|(&[^ а-я]{1,4}?;)")
    val space = Regex(" {2,}")
    return this.replace(htmlTags, "").replace(space, " ")
}

fun String.validUrl(): Boolean {
    val address = this.substringBeforeLast("/").toLowerCase()
    var username = this.substringAfterLast("/").toLowerCase()
    if (username == address) username = ""

    fun validAddress(address: String) : Boolean =  listOf(
        "https://www.github.com",
        "https://github.com",
        "www.github.com",
        "github.com"
    ).any { it == address }

    fun validUserName(username: String) : Boolean {
        val excludePath = listOf(
            "",
            "enterprise",
            "features",
            "topics",
            "collections",
            "trending",
            "events",
            "marketplace",
            "pricing",
            "nonprofit",
            "customer-stories",
            "security",
            "login",
            "join")

        return !(excludePath.any{ it == username} || username.contains(Regex("[^\\w]")))
    }

    return this == "" || (validAddress(address) && validUserName(username))
}
