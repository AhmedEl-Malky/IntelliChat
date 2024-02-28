package com.elmalky.chathub.Models.Repositories

import com.elmalky.chathub.Models.APIs.GeminiModel

class GeminiRepository {
    val model = GeminiModel()
    val chatItems = mutableListOf<String>()
    suspend fun makeRequest(prompt: String) = model.makeRequest(prompt)
    fun addChatItem(item: String) {
        chatItems.add(item)
    }

    fun modifyBotChat(text: String) {
        chatItems[chatItems.size - 1] = text
    }
}