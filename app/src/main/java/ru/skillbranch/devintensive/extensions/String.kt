package ru.skillbranch.devintensive.extensions

fun String.truncate(len: Int = 16): String{
    var res = this.trim()
    return if(res.length > len) res.take(len).trim() + "..." else res
}

fun String.stripHtml():String{
    return this.replace(Regex("<.*?>", RegexOption.DOT_MATCHES_ALL), "")
        .replace(Regex("&.*?;"), "")
        .replace(Regex("\\s+"), " ")
}