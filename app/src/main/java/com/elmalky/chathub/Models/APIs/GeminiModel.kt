package com.elmalky.chathub.Models.APIs

import com.google.ai.client.generativeai.GenerativeModel

class GeminiModel {
    val apiKey = "AIzaSyDfN2VzWfEczohYmAFfOraEig1QOg-q7Xg"
    val generativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = apiKey
    )

    suspend fun makeRequest(prompt: String): String {
        val response = generativeModel.generateContent(prompt)
        return response.text.toString()
    }
}