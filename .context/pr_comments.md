# PR Review - Password policy enforcement service (by Amit Kumar)

## Reviewer: Ravi Iyer
---

**Overall:** Good foundation but critical bugs need fixing before merge.

### `PasswordValidator.java`

> **Bug #1:** Minimum length check uses less-than instead of less-or-equal so 8-char password fails with min_length=8
> This is the higher priority fix. Check the logic carefully and compare against the design doc.

### `PolicyConfig.java`

> **Bug #2:** Common password dictionary check is case-sensitive so Password123 passes but password123 is blocked
> This is more subtle but will cause issues in production. Make sure to add a test case for this.

---

**Amit Kumar**
> Acknowledged. I have documented the issues for whoever picks this up.
