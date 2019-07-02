package ru.skillbranch.devintensive.extensions


import ru.skillbranch.devintensive.utils.Utils
import java.text.SimpleDateFormat
import java.util.*




const val SECOND = 1000L
const val MINUTE = 60* SECOND
const val HOUR = 60* MINUTE
const val DAY = 24* HOUR

fun Date.format(pattern:String = "HH:mm:ss dd.MM.yy"): String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date{

    var time = this.time

    time += when(units){
               TimeUnits.SECOND -> value* SECOND
               TimeUnits.MINUTE -> value* MINUTE
               TimeUnits.HOUR -> value* HOUR
               TimeUnits.DAY -> value* DAY
    }

    this.time = time

    return this

}

fun Date.humanizeDiff(): String {

    var startPhrase: String = ""
    var endPhrase: String = ""

    var different: Long = Date().time

    different -= when{
        this.time>Date().time -> this.time + 1000
        else -> this.time
    }

    var elapsedDays = different / DAY
    different = different % DAY
    var elapsedHours = different / HOUR
    different = different % HOUR
    var elapsedMinutes = different / MINUTE
    different = different % MINUTE
    var elapsedSeconds = different / SECOND

    if(elapsedDays<0 || elapsedHours < 0 || elapsedMinutes < 0 || elapsedSeconds < 0){
        startPhrase = "через "
        endPhrase = ""

        //>360д "более года назад"
        when {
            elapsedDays < -360 -> return "более чем через год"
        }

        elapsedDays = elapsedDays*-1
        elapsedHours = elapsedHours*-1
        elapsedMinutes = elapsedMinutes*-1
        elapsedSeconds = elapsedSeconds*-1
    }else{
        //>360д "более года назад"
        when {
            elapsedDays > 360 -> return "более года назад"
        }
        startPhrase = ""
        endPhrase = " назад"
    }

    //26ч - 360д "N дней назад"
    when(elapsedDays){
        in 1..360 -> return "$startPhrase$elapsedDays ${inclination(elapsedDays.toInt(),"день","дня","дней")}$endPhrase"
    }
    //22ч - 26ч "день назад"
    when(elapsedHours){
        in 22..24 -> return "${startPhrase}день$endPhrase"
    }
    //75мин 22ч "N часов назад"
    when(elapsedHours){
        in 1..22 -> return "$startPhrase$elapsedHours ${inclination(elapsedHours.toInt(),"час","часа","часов")}$endPhrase"
    }
    //45мин - 75мин "час назад"
    when(elapsedMinutes){
        in 45..60 -> return "${startPhrase}час$endPhrase"
    }
    //75с - 45мин "N минут назад"
    when(elapsedMinutes){
        in 1..60 -> return "$startPhrase$elapsedMinutes ${inclination(elapsedMinutes.toInt(),"минута","минуты","минут")}$endPhrase"
    }
    //45с - 75с "минуту назад"
    when(elapsedSeconds){
        in 45..60 -> return "${startPhrase}минуту$endPhrase"
    }
    //1с - 45с "несколько секунд назад"
    when(elapsedSeconds){
        in 2..60 -> return "$startPhrase$elapsedSeconds ${inclination(elapsedSeconds.toInt(),"секунда","секунды","секунд")}$endPhrase"
    }
    //0с - 1с "только что"
    when{
        elapsedSeconds <= 1 -> return "только что"
    }

    return ""

}

fun inclination(n: Int, form1:String, form2: String, form3: String) : String
{
    val n: Int = Math.abs(n) % 100
    val n1: Int = n % 10
    if (n in 11..19) return form3
    if (n1 in 2..4) return form2
    if (n1 == 1) return form1
    return form3
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}