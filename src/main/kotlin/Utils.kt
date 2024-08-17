package org.example

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule


object Utils {
    val mapper = ObjectMapper().registerModule(KotlinModule.Builder().build())

    fun readResource(name: String) =
        Utils::class.java.classLoader.getResourceAsStream(name)!!.bufferedReader().use {
            it.readText()
        }
}