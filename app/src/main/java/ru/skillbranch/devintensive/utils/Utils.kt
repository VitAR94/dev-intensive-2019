package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?>{
        val parts: List<String>? = fullName?.trim()?.split(" ")

        val firstName = if(parts?.getOrNull(0).isNullOrBlank()) null else parts?.getOrNull(0)
        val lastName = if(parts?.getOrNull(1).isNullOrBlank()) null else parts?.getOrNull(1)

//        return Pair(firstName, lastName)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        return payload.toCharArray().joinToString(separator = "") {
            val isUpper = it.isUpperCase()
            val s = when(it.toLowerCase()){
                'а' -> "a"
                'б' -> "b"
                'в' -> "v"
                'г' -> "g"
                'д' -> "d"
                'е' -> "e"
                'ё' -> "e"
                'ж' -> "zh"
                'з' -> "z"
                'и' -> "i"
                'й' -> "i"
                'к' -> "k"
                'л' -> "l"
                'м' -> "m"
                'н' -> "n"
                'о' -> "o"
                'п' -> "p"
                'р' -> "r"
                'с' -> "s"
                'т' -> "t"
                'у' -> "u"
                'ф' -> "f"
                'х' -> "h"
                'ц' -> "c"
                'ч' -> "ch"
                'ш' -> "sh"
                'щ' -> "sh'"
                'ъ' -> ""
                'ы' -> "i"
                'ь' -> ""
                'э' -> "e"
                'ю' -> "yu"
                'я' -> "ya"
                ' ' -> divider
                else -> it.toString()
            }
            if(isUpper) s.capitalize() else s
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val init1 = if (firstName.isNullOrBlank())  null else firstName.take(1).toUpperCase()
        val init2 = if (lastName.isNullOrBlank()) null else lastName.take(1).toUpperCase()
        return if(init1 == null && init2 == null) null else init1.orEmpty()+init2.orEmpty()
    }
}