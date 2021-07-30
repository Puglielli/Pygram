package com.puglielli.adapter.`in`

import com.pengrad.telegrambot.TelegramBot
import com.pengrad.telegrambot.model.Update
import com.pengrad.telegrambot.model.request.ChatAction
import com.pengrad.telegrambot.request.GetUpdates
import com.pengrad.telegrambot.request.SendChatAction
import com.pengrad.telegrambot.request.SendMessage
import com.pengrad.telegrambot.response.GetUpdatesResponse
import com.puglielli.adapter.`in`.dto.HistoryUserDTO
import com.puglielli.adapter.`in`.dto.ResponseChatDTO
import com.puglielli.adapter.`in`.dto.UserChatDTO
import com.puglielli.adapter.`in`.dto.includeHistory
import com.puglielli.adapter.`in`.mapper.toDTO

class PygramBot {

    private val listClients: MutableList<UserChatDTO> = mutableListOf()

    fun start() {
        //Criação do objeto bot com as informações de acesso
        val bot = TelegramBot("")//PygramConfig().BOT_TOKEN

        //objeto responsável por receber as mensagens
        var updatesResponse: GetUpdatesResponse


        //controle de off-set, isto é, a partir deste ID será lido as mensagens pendentes na fila
        var m = 0

        //loop infinito pode ser alterado por algum timer de intervalo curto
        while (true) {

            //executa comando no Telegram para obter as mensagens pendentes a partir de um off-set (limite inicial)
            updatesResponse = bot.execute(GetUpdates().limit(1000).offset(m))

            //lista de mensagens
            val updates: List<Update> = updatesResponse.updates()

            //análise de cada ação da mensagem
            for (update in updates) {
                //atualização do off-set
                m = update.updateId() + 1

                println("Recebendo mensagem...")

                val responseChat = update.message().toDTO()

                val validClient = listClients.firstOrNull { it.id == responseChat.user.id }

                if (validClient != null) {
                    println("User: ${responseChat.user}")
                    println("Message: ${responseChat.text}")

                    if (responseChat.text == "/edit") {
                        startEvents(
                            bot = bot,
                            responseChat = responseChat,
                            chatAction = ChatAction.typing.name, // TODO - validar outros
                            message = "Informações Editadas"
                        )
                    } else {
                        startEvents(
                            bot = bot,
                            responseChat = responseChat,
                            chatAction = ChatAction.typing.name, // TODO - validar outros
                            message = "Ta porrra!! bem vindo denovo carai"
                        )
                    }

                    includeHistory(responseChat, "Other", validClient)
                } else {
                    startEvents(
                        bot = bot,
                        responseChat = responseChat,
                        chatAction = ChatAction.typing.name, // TODO - validar outros
                        message = "Sheeeeesh.... Bem Vindoooo o caraiiii ao bot do carai kkkk... Ola Meu Rei ${responseChat.user.firstName}"
                    )
                    listClients.add(responseChat.user)
                    includeHistory(responseChat, "Create", listClients.first { it.id == responseChat.user.id })
                }
            }
        }
    }

    private fun includeHistory(responseChat: ResponseChatDTO, action: String, client: UserChatDTO) {
        val historyUserDTO = HistoryUserDTO(
            id = responseChat.user.id,
            action = action,
            text = responseChat.text
        ).includeHistory()

        client.history = historyUserDTO
        listClients.add(client)

        println("Historico incluido!")
    }

    private fun startEvents(bot: TelegramBot, responseChat: ResponseChatDTO, chatAction: String, message: String) {
        while(true) {
            val sendAction = sendAction(
                bot = bot,
                chatId = responseChat.chat.id,
                chatAction = chatAction
            )
            if (sendAction) break
        }

        while(true) {
            val sendMessage = sendMessageBot(
                bot = bot,
                chatId = responseChat.chat.id,
                message = message
            )
            if (sendMessage) break
        }
    }

    private fun sendMessageBot(bot: TelegramBot, chatId: Long, message: String): Boolean {
        //envio da mensagem de resposta
        //objeto responsável por gerenciar o envio de respostas
        val sendResponse = bot
            .execute(SendMessage(chatId, message))

        //verificação de mensagem enviada com sucesso
        println("Mensagem Enviada? ${sendResponse.isOk}")
        return sendResponse.isOk
    }

    private fun sendAction(bot: TelegramBot, chatId: Long, chatAction: String): Boolean {
        //objeto responsável por gerenciar o envio de ações do chat
        val baseResponse = bot
        .execute(SendChatAction(chatId, chatAction))

        //verificação de ação de chat foi enviada com sucesso
        println("Resposta de Chat Action Enviada? ${baseResponse.isOk}")
        return baseResponse.isOk
    }
}