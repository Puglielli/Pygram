package com.puglielli.adapter.`in`.dto

data class ResponseChatDTO(
    val text: String,
    val contact: ContactDTO?,
    val date: Int,
    val user: UserChatDTO,
    val chat: ChatDTO
)
