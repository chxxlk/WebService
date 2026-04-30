# AGENTS.md

Guidance for OpenCode sessions in this repo.

## Project Overview
Java workspace with two independent modules:
- `Client_Server_RMI`: Java RMI client-server
- `Client_Server_Socket`: Socket-based client-server

No Maven/Gradle build tooling; uses VS Code Java tooling.

## Module Structure
Each module uses VS Code Java defaults:
- `src/`: Source code
- `bin/`: Compiled `.class` files (committed to repo, no `.gitignore`)
- `.vscode/settings.json`: Configures source/output paths and library references

## Notes
- No test framework or test files present
- Avoid adding build tools unless explicitly requested
