# FINSERV-4218: Build enterprise password policy engine

**Status:** In Progress · **Priority:** High
**Sprint:** Sprint 28 · **Story Points:** 5
**Reporter:** Ravi Krishnan (Security Lead) · **Assignee:** You (Intern)
**Due:** End of sprint (Friday)
**Labels:** `backend`, `java`, `security`, `authentication`
**Task Type:** Feature Ship

---

## Description

The `PasswordHasher` class securely hashes and verifies passwords. Build the `PasswordPolicyEngine` that enforces complexity requirements, checks password history, and detects common/breached passwords. Implement TODOs in `PasswordPolicyEngine.java`.

## Acceptance Criteria

- [ ] `validate()` checks length, uppercase, lowercase, digit, special char requirements
- [ ] `checkHistory()` prevents reuse of last N passwords
- [ ] `isCommonPassword()` checks against a dictionary of common passwords
- [ ] `getStrengthScore()` returns 0-100 score for password strength
- [ ] All unit tests pass
