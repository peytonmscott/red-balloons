package com.redballoons.plugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.wm.ToolWindowManager
import com.redballoons.plugin.ops.Search
import com.redballoons.plugin.prompt.Prompt
import com.redballoons.plugin.services.OpencodeService
import com.redballoons.plugin.ui.PromptPopup
import com.redballoons.plugin.ui.SearchResultsPanel

/**
 * Search Mode Action (Ctrl+Shift+/)
 * AI-powered semantic search across the codebase.
 * Results are displayed in a Tool Window with clickable navigation.
 */
class SearchModeAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val editor = e.getData(CommonDataKeys.EDITOR)

        val popup = PromptPopup(
            project = project,
            mode = OpencodeService.ExecutionMode.SEARCH,
            editor = editor
        ) { prompt ->
            executeSearchMode(e, prompt)
        }

        popup.show()
    }

    private fun executeSearchMode(e: AnActionEvent, userPrompt: String) {
        val project = e.project ?: return
        val context = Prompt.search(project)

        context.userPrompt = userPrompt

        Search(context) { result ->
            if (result.success && result.output.isNotBlank()) {
                val searchResults = SearchResultsPanel.parseSearchOutput(
                    result.output,
                    project.basePath ?: ""
                )

                if (searchResults.isEmpty()) {
                    Messages.showInfoMessage(
                        project,
                        "No results found for: $userPrompt",
                        "Search Results"
                    )
                    return@Search
                }

                val toolWindow = ToolWindowManager.getInstance(project)
                    .getToolWindow("Red Balloons Search")

                toolWindow?.let { tw ->
                    tw.show {
                        val content = tw.contentManager.getContent(0)
                        val panel = content?.component as? SearchResultsPanel
                        panel?.setResults(searchResults, userPrompt)
                    }
                }
            } else if (!result.success) {
                val errorMsg = result.error.ifBlank { "Search failed" }
                Messages.showErrorDialog(project, errorMsg, "Opencode Search Error")
            } else {
                // Success but no output
                Messages.showInfoMessage(
                    project,
                    "No results found for: $userPrompt",
                    "Search Results"
                )
            }
        }
    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabledAndVisible = e.project != null
    }
}