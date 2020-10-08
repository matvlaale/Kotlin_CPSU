package ru.matvlaale.kotlin_cpsu

import java.util.*

class TimeGetter: IModel {
    override fun getTime(): String {
        val tokens = Calendar.getInstance().time.toString().split(" ")
        val time = tokens[3].split(":")
        val data = (time[0].toInt() + 3).toString() + ":" + time[1] + ":" + time[2]
        return data
    }

}