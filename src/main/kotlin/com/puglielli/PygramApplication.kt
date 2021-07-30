package com.puglielli

import com.puglielli.adapter.`in`.PygramBot
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PygramApplication

fun main(args: Array<String>) {
    runApplication<PygramApplication>(*args)
    PygramBot().start()
}
