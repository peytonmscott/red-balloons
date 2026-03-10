# Red Ballons IntelliJ Plugin

A minimalist AI coding assistant plugin for IntelliJ-based IDEs (IntelliJ IDEA, Android Studio, etc.) that integrates the [opencode](https://opencode.ai) CLI.

Inspired by [ThePrimeagen's 99](https://github.com/ThePrimeagen/99).

---
>Made with 💙 by Ricardo Markiewicz // [@gazeria](https://twitter.com/gazeria).

## Status

⚠️ **Early Development** - This plugin is in active development and may have breaking changes between versions.

**Current Limitations:**
- Single command execution only (concurrent operations not yet supported)
- Some features may be unstable or incomplete

**What to Expect:**
- Core functionality (Selection, Vibe, and Search modes) should run (and may work!)
- Community feedback is welcome and encouraged

This plugin was bootstrapped with AI assistance and is being actively refined based on user feedback.

## Features

- **Selection Mode** (`Ctrl+Shift+;`): Modify only the selected code. Changes are applied in-memory (supports Undo/`Ctrl+Z`).
- **Vibe Mode** (`Ctrl+Shift+'`): Let the AI freely modify your project files.
- **Search Mode** (`Ctrl+Shift+S`): AI-powered semantic search across your codebase with clickable results.
- **Kill Switch** (`Ctrl+Shift+Escape`): Immediately terminate the running opencode process.

## Requirements

- IntelliJ IDEA 2023.3+ or Android Studio Hedgehog+
- Java 17+
- [opencode CLI](https://opencode.ai) installed and working, accessible in your PATH

## Installation

### From Source

1. Clone this repository
2. Run `./gradlew buildPlugin`
3. Install the plugin from `build/distributions/opencode-intellij-plugin-*.zip`
   - Go to **Settings** → **Plugins** → **⚙️** → **Install Plugin from Disk...**

### Configuration

Go to **Settings** → **Tools** → **Opencode AI** to configure:

- **Opencode CLI Path**: Path to the `opencode` executable (default: `opencode`)
- **Model**: The AI model to use (leave empty for default)
- **System Prompts**: Customize the prompts for Selection and Search modes

## Usage

Go to **Tools** → **Opencode** to execute the actions from the menu.

### Selection Mode (`Ctrl+Shift+;`)

1. Select some code in your editor
2. Press `Ctrl+Shift+;`
3. Enter your instruction (e.g., "add error handling", "convert to async/await")
4. The selected code will be replaced with the AI's output
5. Use `Ctrl+Z` to undo if needed

### Vibe Mode (`Ctrl+Shift+`)

1. Press `Ctrl+Shift+V`
2. Enter your request (e.g., "add a dark mode toggle to the settings page")
3. The AI will modify your project files directly
4. The IDE will refresh to show the changes

### Search Mode (`Ctrl+Shift+S`)

1. Press `Ctrl+Shift+S`
2. Enter your search query (e.g., "where is user authentication handled?")
3. Results appear in the **Opencode Search** tool window
4. Click a result to navigate to that location

## Development

```bash
# Build the plugin
./gradlew buildPlugin

# Run the plugin in a sandbox IDE
./gradlew runIde

# Run tests
./gradlew test
```

## License

MIT