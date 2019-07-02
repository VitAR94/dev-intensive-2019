package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = SECOND * 60
const val HOUR = MINUTE * 60
const val DAY = HOUR * 24

fun Date.format(pattern: String="HH:mm:ss dd.MM.yy"): String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnit = TimeUnit.SECOND): Date {
    var time = this.time

    time += when(units){
        TimeUnit.SECOND ->  value * SECOND
        TimeUnit.MINUTE ->  value * MINUTE
        TimeUnit.HOUR ->  value * HOUR
        TimeUnit.DAY ->  value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val dif = date.time - this.time
    val s = when(abs(dif)){
        in 0..MINUTE -> if(dif > 0) "несколько секунд назад" else "через несколько секунд"

        in MINUTE..2*MINUTE-1 -> if(dif > 0) "1 минуту назад" else "через 1 минуту"
        in 2*MINUTE..5*MINUTE-1 -> if(dif > 0) "${dif/ MINUTE} минуты назад" else "через ${abs(dif)/ MINUTE} минуты"
        in 5*MINUTE..HOUR-1 -> if(dif > 0) "${dif/ MINUTE} минут назад" else "через ${abs(dif)/ MINUTE} минут"

        in HOUR..2*HOUR-1 -> if(dif > 0) "1 час назад" else "через 1 час"
        in 2*HOUR..5*HOUR-1 -> if(dif > 0) "${dif/ HOUR} часа назад" else "через ${abs(dif)/ HOUR} часа"
        in 5*HOUR..DAY-1 -> if(dif > 0) "${dif/ HOUR} часов назад" else "через ${abs(dif)/ HOUR} часов"

        in DAY..2*DAY-1 -> if(dif > 0) "1 день назад" else "через 1 день"
        in 2*DAY..5*DAY-1 -> if(dif > 0) "${dif/ DAY} дня назад" else "через ${abs(dif)/ DAY} дня"
        in 5*DAY..365*DAY-1 -> if(dif > 0) "${dif/ DAY} дней назад" else "через ${abs(dif)/ DAY} дней"

        else -> if(dif > 0) "более года назад" else "более чем через год"
    }
    return s
}

enum class TimeUnit{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}