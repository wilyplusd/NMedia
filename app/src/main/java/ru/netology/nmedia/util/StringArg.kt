package ru.netology.nmedia.util

import android.os.Bundle
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

object StringArg: ReadWriteProperty<Bundle, String?> {

    override fun setValue(thisRef: Bundle, property: KProperty<*>, value: String?) {
        thisRef.putString(property.name, value)
    }

    override fun getValue(thisRef: Bundle, property: KProperty<*>): String? =
        thisRef.getString(property.name)
}

object LongArg: ReadWriteProperty<Bundle, Long> {

    override fun setValue(thisRef: Bundle, property: KProperty<*>, value: Long) {
        thisRef.putLong(property.name, value)
    }

    override fun getValue(thisRef: Bundle, property: KProperty<*>): Long =
        thisRef.getLong(property.name)
}

object IntArg: ReadWriteProperty<Bundle, Int> {

    override fun setValue(thisRef: Bundle, property: KProperty<*>, value: Int) {
        thisRef.putInt(property.name, value)
    }

    override fun getValue(thisRef: Bundle, property: KProperty<*>): Int =
        thisRef.getInt(property.name)


}








