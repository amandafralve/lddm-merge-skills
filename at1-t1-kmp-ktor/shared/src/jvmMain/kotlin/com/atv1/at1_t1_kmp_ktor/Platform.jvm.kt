package com.atv1.at1_t1_kmp_ktor

class JVMPlatform : Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

actual fun serverHost(): String = "localhost"