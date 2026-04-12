package com.atv1.at1_t1_kmp_ktor

import android.os.Build

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun serverHost(): String = "192.168.1.14" // Substituir pelo IP do notebook