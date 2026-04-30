# AGENTS.md

## Modules
- `Client_Server_RMI` — Java RMI, port 1099, service name `HelloService`
- `Client_Server_Socket` — Socket server, port 12345, state machine (NORMAL/WAITING_FOR_OP/WAITING_FOR_NUMS)
- `RMI_StudiKasus/` — Two RMI subprojects (no VS Code config):
  - `Kalkultor_Terdistribusi` — service name `calculatorservice`
  - `Manajemen_Tugas` — service name `TaskManager`

## Build & Run
No Maven/Gradle. Uses VS Code Java tooling.
- `Client_Server_RMI/.vscode/settings.json` and `Client_Server_Socket/.vscode/settings.json` set `src/` and `bin/` paths
- `RMI_StudiKasus` subprojects have no `.vscode/` config; they need manual setup or config creation
- Compiled `.class` files in `bin/` are committed (no `.gitignore`)
- No `lib/` directories present; no external JAR dependencies

## Constraints
- No test framework
- Do not add build tools unless explicitly requested
- `RMI_StudiKasus/.gitignore` references Gradle paths but no Gradle files exist
