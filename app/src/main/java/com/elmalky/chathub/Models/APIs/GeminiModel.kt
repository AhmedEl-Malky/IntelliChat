package com.elmalky.chathub.Models.APIs

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig


class GeminiModel {
    val apiKey = "AIzaSyDfN2VzWfEczohYmAFfOraEig1QOg-q7Xg"
    val config = generationConfig {
        temperature = 0f
        topK = 2
        topP = 0.5f
    }
    val generativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = apiKey,
        config

    )

    suspend fun makeRequest(prompt: String): String {
        val response = generativeModel.generateContent(prompt)
        return response.text.toString()
    }
}