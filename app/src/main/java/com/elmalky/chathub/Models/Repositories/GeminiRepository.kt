package com.elmalky.chathub.Models.Repositories

import com.elmalky.chathub.Models.APIs.GeminiModel

class GeminiRepository {
    private val model = GeminiModel()
    val _chatItems = mutableListOf<String>()
    val chatItems
        get() = _chatItems.toList()

    suspend fun makeRequest(prompt: String) = model.makeRequest(prompt)
    fun addChatItem(item: String) {
        _chatItems.add(item)
    }

    fun clearItems() {
        _chatItems.clear()
    }

    fun modifyBotChat(text: String) {
        _chatItems[_chatItems.size - 1] = text
    }
}