package ru.skillbranch.devintensive.extensions

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

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when(units){
        TimeUnits.SECOND ->  value * SECOND
        TimeUnits.MINUTE ->  value * MINUTE
        TimeUnits.HOUR ->  value * HOUR
        TimeUnits.DAY ->  value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    fun minuteStr(value: Long) = if(value in 2..4) "минуты" else "минут"
    fun hourStr(value: Long) = if(value in 2..4) "часа" else "часов"
    fun dayStr(value: Long) = if(value in 2..4) "дня" else "дней"

    val dif = date.time - this.time
    val s = when(abs(dif)){
        in 0..SECOND -> "только что"

        in SECOND..45*SECOND -> if(dif > 0) "несколько секунд назад" else "через несколько секунд"

        in 45*SECOND..75*SECOND -> if(dif > 0) "минуту назад" else "через минуту"
        in 75*SECOND..45*MINUTE -> if(dif > 0) "${dif/ MINUTE} ${minuteStr(dif/ MINUTE)} назад"
                                    else "через ${abs(dif)/ MINUTE} ${minuteStr(abs(dif)/ MINUTE)}"

        in 45*MINUTE..75*MINUTE -> if(dif > 0) "час назад" else "через час"
        in 75*MINUTE..22*HOUR -> if(dif > 0) "${dif/ HOUR} ${hourStr(dif/ HOUR)} назад"
                                    else "через ${abs(dif)/ HOUR} ${hourStr(abs(dif)/ HOUR)}"

        in 22*HOUR..26*HOUR -> if(dif > 0) "день назад" else "через день"
        in 26*HOUR..360*DAY -> if(dif > 0) "${dif/ DAY} ${dayStr(dif/ DAY)} назад"
                                    else "через ${abs(dif)/ DAY} ${dayStr(abs(dif)/ DAY)}"

        else -> if(dif > 0) "более года назад" else "более чем через год"
    }
    return s
}

enum class TimeUnits{
    SECOND{
        override fun plural(value: Int): String {
            return incline(value, "секунду", "секунды", "секунд")
        }
    },
    MINUTE{
        override fun plural(value: Int): String {
            return incline(value, "минуту", "минуты", "минут")
        }
    },
    HOUR{
        override fun plural(value: Int): String {
            return incline(value, "час", "часа", "часов")
        }
    },
    DAY{
        override fun plural(value: Int): String {
            return incline(value, "день", "дня", "дней")
        }
    };

    abstract fun plural(value:Int):String

    protected fun incline(value: Int, one: String, several: String, many: String):String{
        return when{
            value % 100 in 10..20 -> "$value $many"
            value % 10 == 1 -> "$value $one"
            value % 10 in 2..4 -> "$value $several"
            else -> "$value $many"
        }
    }
}