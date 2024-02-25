package com.elmalky.chathub.Models.Repositories

import com.elmalky.chathub.Models.APIs.GeminiModel

class GeminiRepository {
    val model = GeminiModel()
    suspend fun makeRequest(prompt: String): String {
        return model.makeRequest(prompt)
    }
}