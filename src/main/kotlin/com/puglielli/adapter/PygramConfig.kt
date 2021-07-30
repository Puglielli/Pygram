package com.puglielli.adapter

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class PygramConfig {

    @Value("\${telegram.token}")
    val BOT_TOKEN: String = ""
}