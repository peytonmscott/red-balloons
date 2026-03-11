package com.redballoons.plugin.ops

import com.redballoons.plugin.prompt.Context
import com.redballoons.plugin.prompt.PromptStrings
import com.redballoons.plugin.services.OpencodeService

object Search {
    operator fun invoke(context: Context, cb: (OpencodeService.ExecutionResult) -> Unit) {
        val systemPrompt = PromptStrings.semanticSearch()

        val prompt = MakePrompt(context, systemPrompt)
        context.addPromptContent(prompt)

        context.startRequest { result ->
            cb(result)
        }
    }
}