package com.zero.myapplication.utils

import android.os.Bundle

// Dunno if there's a better way to extend both bundle and intents, but
// you probably can extend intents in the same way

fun Bundle.putEnum(key:String, enum: Enum<*>){
    putString(key, enum.name)
}

inline fun <reified T: Enum<T>> Bundle.getEnum(key:String): T {
    return enumValueOf(getString(key)!!)
}