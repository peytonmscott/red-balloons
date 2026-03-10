package com.redballoons.plugin.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "RedBalloonsSettings",
    storages = [Storage("redballoons-plugin.xml")]
)
class RedBalloonsSettings : PersistentStateComponent<RedBalloonsSettings> {

    var opencodeCliPath: String = "opencode"
    var modelName: String = ""
    var selectionModeSystemPrompt: String = DEFAULT_SELECTION_PROMPT
    var searchModeSystemPrompt: String = DEFAULT_SEARCH_PROMPT

    override fun getState(): RedBalloonsSettings = this

    override fun loadState(state: RedBalloonsSettings) {
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        fun getInstance(): RedBalloonsSettings =
            ApplicationManager.getApplication().getService(RedBalloonsSettings::class.java)

        const val DEFAULT_SELECTION_PROMPT = """You are a code modification assistant. 
Given the following code snippet, apply the user's instructions.
Return ONLY the modified code, without any markdown formatting, explanations, or code fences.
Do not include ``` or language tags. Just the raw code."""

        const val DEFAULT_SEARCH_PROMPT = """You are a code search assistant.
Search the project for what the user asks and return code locations that match the description.

<Output>
/path/to/project/src/foo.js:24:8,3,Some notes here about why this matches
/path/to/project/src/bar.js:13:2,1,Notes about this location
</Output>

<Rule>Text locations are in the format of: /absolute/path/to/file.ext:lnum:cnum,X,NOTES
lnum = starting line number (1-based)
cnum = starting column number (1-based)
X = how many lines should be highlighted
NOTES = A text description of why this location is relevant (no newlines allowed)
</Rule>
<Rule>Each location is separated by new lines</Rule>
<Rule>Each path must be an absolute path</Rule>
<Rule>You must provide output without any commentary, just the text locations</Rule>
<Rule>Double check output format before returning</Rule>"""
    }
}