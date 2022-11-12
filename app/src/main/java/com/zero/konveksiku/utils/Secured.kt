package com.zero.konveksiku.utils

object Secured {
    init {
        System.loadLibrary("konveksiku")
    }

    external fun getBannerPubID(): String
}
