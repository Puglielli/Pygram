package com.puglielli.adapter.`in`.mapper

import com.pengrad.telegrambot.model.Chat
import com.pengrad.telegrambot.model.Contact
import com.pengrad.telegrambot.model.Message
import com.pengrad.telegrambot.model.User
import com.puglielli.adapter.`in`.dto.ChatDTO
import com.puglielli.adapter.`in`.dto.ContactDTO
import com.puglielli.adapter.`in`.dto.ResponseChatDTO
import com.puglielli.adapter.`in`.dto.UserChatDTO

fun Message.toDTO() = ResponseChatDTO(
    text = text() ?: "",
    contact = contact()?.toDTO(),
    date = date(),
    user = from().toDTO(),
    chat = chat().toDTO()
)

fun Contact.toDTO() = ContactDTO(
    phoneNumber = phoneNumber(),
    id = phoneNumber(),
    firstName = firstName(),
    lastName = lastName(),
    vcard = vcard()
)

fun User.toDTO() = UserChatDTO(
    id = id(),
    isBot = isBot,
    firstName = firstName(),
    lastName = lastName()
)

fun Chat.toDTO() = ChatDTO(
    id = id(),
    type = type().toString()
)
