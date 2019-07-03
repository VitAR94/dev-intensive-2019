package ru.skillbranch.devintensive.extensions

fun String.truncate(lastIdx: Int = 16): String{
    var res = this.trim()
    return if(res.length > lastIdx + 1) res.take(lastIdx + 1) + "..." else res
}

fun String.stripHtml():String{
    return this.replace(Regex("<.*?>", RegexOption.DOT_MATCHES_ALL), "")
        .replace(Regex("&.*?;"), "")
        .replace(Regex("\\s+"), " ")
}