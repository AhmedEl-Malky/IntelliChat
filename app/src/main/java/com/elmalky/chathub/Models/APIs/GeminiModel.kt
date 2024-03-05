package com.elmalky.chathub.Models.APIs

import com.elmalky.chathub.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.generationConfig


class GeminiModel {
    val config = generationConfig {
        temperature = 0f
        topK = 2
        topP = 0.5f
    }
    val generativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = BuildConfig.apiKey,
        config

    )

    suspend fun makeRequest(prompt: String): String {
        val response = generativeModel.generateContent(prompt)
        return response.text.toString()
    }
}