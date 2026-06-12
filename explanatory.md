# Beginner Explanatory Guide: FINSERV-4218: Build enterprise password policy engine

> **Task Type**: Service Task  
> **Domain/Focus**: Security, Password Management

---

## 1. The Goal (In-Depth Beginner Explanation)

### The Core Problem
In today's digital landscape, ensuring the security of user accounts is paramount. The task at hand involves creating a `PasswordPolicyEngine` that enforces a robust password policy for an enterprise application. Currently, the application lacks a systematic way to validate passwords against security standards, which can lead to weak passwords being accepted. This vulnerability can expose user accounts to unauthorized access and data breaches, putting sensitive information at risk.

The `PasswordPolicyEngine` aims to address this issue by implementing several critical checks: it will validate password complexity (length, character types), prevent the reuse of recent passwords, and check against a list of common or breached passwords. By doing so, it will significantly enhance the security posture of the application, ensuring that users create strong, unique passwords that are less susceptible to attacks.

### Jargon Buster (Key Terms Explained)
* **Password Hashing**: This is a process where a password is transformed into a fixed-size string of characters, which is typically a hash value. This transformation is one-way, meaning you cannot easily revert the hash back to the original password. For example, the password "mypassword" might be hashed to "5f4dcc3b5aa765d61d8327deb882cf99". This ensures that even if the hashed password is exposed, the original password remains secure.

* **Password Policy**: A set of rules that dictate how passwords should be created and managed. This can include requirements for length, complexity (use of uppercase letters, numbers, and symbols), and restrictions on common passwords. For instance, a password policy might require that passwords be at least 12 characters long and include at least one special character.

* **Common Passwords**: These are passwords that are frequently used and thus easily guessed by attackers. Examples include "123456", "password", and "qwerty". The `PasswordPolicyEngine` will check against a predefined list of these common passwords to prevent users from choosing easily guessable passwords.

* **Password Strength Score**: A numerical representation of how secure a password is, typically on a scale from 0 to 100. The score is calculated based on various criteria, such as length, character variety, and uniqueness. For example, a password like "P@ssw0rd123!" might score higher than "password123" due to its complexity.

### Expected Outcome
After implementing the `PasswordPolicyEngine`, the system should effectively validate passwords according to the defined security criteria. 

**Before**: Users could create weak passwords that do not meet security standards, leading to potential security breaches.

**After**: The system will reject weak passwords and provide feedback on why a password is invalid, ensuring that users create strong, secure passwords that comply with the enterprise's security policies.

---

## 2. Related Coding Concepts & Syntax (50% Theory, 50% Practice)

### Concept 1: Validation Logic
#### 📘 Theoretical Overview (50%)
Validation logic is crucial in programming as it ensures that the data being processed meets specific criteria before any operations are performed. Without proper validation, applications can encounter errors, behave unpredictably, or become vulnerable to security threats. For instance, if a password is not validated for length or complexity, a user might set a simple password like "1234", which is easily guessable.

Key mechanisms in validation logic include:
- **Condition Checks**: These are statements that evaluate whether certain conditions are true or false. For example, checking if a password length is greater than a minimum requirement.
- **Error Handling**: This involves providing feedback to users when their input does not meet validation criteria, allowing them to correct their mistakes.

#### 💻 Syntax & Practical Examples (50%)
* **Language Syntax**:
  ```java
  public Map<String, Object> validate(String password) {
      Map<String, Object> result = new HashMap<>();
      List<String> errors = new ArrayList<>();
      
      if (password.length() < minLength) {
          errors.add("Password must be at least " + minLength + " characters long.");
      }
      // Additional checks would follow...
      
      result.put("valid", errors.isEmpty());
      result.put("errors", errors);
      return result;
  }
  ```

* **Real-World Application**:
  ```java
  public Map<String, Object> validate(String password) {
      Map<String, Object> result = new HashMap<>();
      List<String> errors = new ArrayList<>();
      
      // Check for minimum length
      if (password.length() < 8) {
          errors.add("Password must be at least 8 characters long.");
      }
      // Check for uppercase letters
      if (!password.matches(".*[A-Z].*")) {
          errors.add("Password must contain at least one uppercase letter.");
      }
      // Final result
      result.put("valid", errors.isEmpty());
      result.put("errors", errors);
      return result;
  }
  ```

---

## 3. Step-by-Step Logic & Walkthrough

1. **Step 1: Locate and Analyze the Target File**
   * Navigate to the `s-w06-task-04` folder and open `PasswordPolicyEngine.java`.
   * Focus on the methods marked with TODO comments, particularly `validate()`, `checkHistory()`, and `recordPasswordChange()`.

2. **Step 2: Input Verification & Validation**
   * Begin by checking if the input password is null or empty. If it is, return an error message indicating that the password cannot be empty.

3. **Step 3: Core Implementation / Modification**
   * Implement the `validate()` method:
     - Check the length of the password against `minLength` and `maxLength`.
     - Use regular expressions to check for at least one uppercase letter, one lowercase letter, one digit, and one special character.
     - Call `isCommonPassword()` to ensure the password is not on the common passwords list.
     - Collect any errors and return a map indicating whether the password is valid and any error messages.

4. **Step 4: Output Verification & Testing**
   * After implementing the logic, run the unit tests provided in `PasswordValidatorTest.java` to ensure that all tests pass and the validation logic works as expected.

---

## 4. Detailed Walkthrough of Test Cases

### Test Case 1: Standard / Success Case
* **Description**: This test checks if a valid password is accepted by the `validate()` method.
* **Inputs**:
  ```json
  {
      "password": "StrongP@ssw0rd!"
  }
  ```
* **Step-by-Step Execution Trace**:
  1. The input password "StrongP@ssw0rd!" is received by the `validate()` function.
  2. The function checks the length (valid).
  3. It checks for uppercase letters (valid).
  4. It checks for lowercase letters (valid).
  5. It checks for digits (valid).
  6. It checks for special characters (valid).
  7. It checks against common passwords (valid).
  8. Returns the final result: `{"valid": true, "errors": []}`.

* **Expected Output**: 
  ```json
  {
      "valid": true,
      "errors": []
  }
  ```

### Test Case 2: Edge Case / Validation Fail
* **Description**: This test checks if a password that is too short is rejected.
* **Inputs**:
  ```json
  {
      "password": "short"
  }
  ```
* **Step-by-Step Execution Trace**:
  1. The input password "short" is received.
  2. The function checks the length (invalid, less than 8).
  3. The execution is halted early, and an error message is added.
  4. Returns the result indicating the password is invalid.

* **Expected Output**: 
  ```json
  {
      "valid": false,
      "errors": ["Password must be at least 8 characters long."]
  }
  ```