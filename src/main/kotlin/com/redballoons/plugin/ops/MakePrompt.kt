package com.redballoons.plugin.ops

import com.redballoons.plugin.prompt.Prompt

object MakePrompt {
    operator fun invoke(userPrompt: String, basePrompt: String): String {
        val fullPrompt = Prompt.prompt(userPrompt, basePrompt)

        // TODO: Refs and additional rules

        return fullPrompt
    }
}