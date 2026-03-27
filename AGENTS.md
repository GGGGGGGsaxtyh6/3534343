## Cursor Cloud specific instructions

This repository is a personal CTF (Capture The Flag) / security challenge workspace. The `main` branch contains only a single placeholder file (`23423`). All substantive work lives on isolated feature branches solving individual challenges.

**Key facts:**
- No application code, services, or infrastructure on `main`.
- No dependency manifests (`package.json`, `requirements.txt`, etc.).
- No build system, linter configuration, or test framework.
- No Docker, Makefile, or CI/CD configuration.
- Individual CTF challenge solutions live on separate feature branches and are self-contained.

**Development workflow:** Each CTF challenge is solved on its own branch. There is no shared application to start or test. Future agents should check out the relevant branch for the challenge they are working on.
