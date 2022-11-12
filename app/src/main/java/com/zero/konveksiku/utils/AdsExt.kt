package com.zero.konveksiku.utils

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.oratakashi.viewbinding.core.tools.gone
import com.oratakashi.viewbinding.core.tools.visible
import com.zero.konveksiku.BuildConfig

enum class AdsEnum {
    Banner,
    Interstitial,
    Rewarded
}

fun AdView.requestAds(adsEnum: AdsEnum, pubIDAds: String) {
    val adRequest = AdRequest.Builder()
//    when (adsEnum) {
//        AdsEnum.Banner -> {
//            if (BuildConfig.DEBUG) {
//                this.adUnitId = "ca-app-pub-3940256099942544/6300978111"
//            } else {
//                this.adUnitId = pubIDAds
//            }
//        }
//        AdsEnum.Interstitial -> {
//            if (BuildConfig.DEBUG) {
//                this.adUnitId = "ca-app-pub-3940256099942544/1033173712"
//            } else {
//                this.adUnitId = pubIDAds
//            }
//        }
//        AdsEnum.Rewarded -> {
//            if (BuildConfig.DEBUG) {
//                this.adUnitId = "ca-app-pub-3940256099942544/5224354917"
//            } else {
//                this.adUnitId = pubIDAds
//            }
//        }
//    }

    this.loadAd(adRequest.build())
    this.adListener = object : com.google.android.gms.ads.AdListener() {
        override fun onAdLoaded() {
            super.onAdLoaded()
            this@requestAds.visible()
        }

        override fun onAdFailedToLoad(p0: LoadAdError) {
            super.onAdFailedToLoad(p0)
            this@requestAds.gone()
        }
    }
}