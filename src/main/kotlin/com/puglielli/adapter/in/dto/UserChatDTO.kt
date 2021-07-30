package com.puglielli.adapter.`in`.dto

data class UserChatDTO(
    val id: Long,
    val isBot: Boolean,
    val firstName: String?,
    val lastName: String?,
    var history: HistoryUserDTO? = null
) {
    override fun toString(): String {
        return "UserChatDTO(id=$id, isBot=$isBot, firstName=$firstName, lastName=$lastName, history=$history)"
    }
}

data class HistoryUserDTO(
    val id: Long,
    val action: String,
    val text: String,
    var lastHistory: HistoryUserDTO? = null
) {
    override fun toString(): String {
        return "HistoryUserDTO(id=$id, action='$action', text='$text', listLastHistory=$lastHistory)"
    }
}

fun HistoryUserDTO.includeHistory(): HistoryUserDTO {
    lastHistory = HistoryUserDTO(
        id = id,
        action = action,
        text = text,
        lastHistory = lastHistory
    )
    return this
}
