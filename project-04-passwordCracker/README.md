# 🔓 Password Cracker (Java Multithreaded ZIP Brute-Force)

**Password Cracker** is a multithreaded Java program that attempts to brute-force the password of a protected ZIP file. It generates all possible combinations of a given character set and password length, distributing the attempts across multiple threads for faster processing.

> **Note:** This project is for **educational purposes only**. Brute-forcing passwords without permission is illegal. Use this program only on files you own or have explicit permission to test.

---

## 📌 Features

- Multithreaded brute-force password cracking  
- Configurable password length and character set  
- Automatic distribution of tasks across multiple threads  
- Progress stops immediately when the password is found  
- Measures and prints time taken to find the password  

---

## 🛠️ Technologies Used

- Java (JDK 8 or higher)  
- Zip4j library for ZIP file handling ([GitHub link](https://github.com/srikanth-lingala/zip4j))  
- Concurrency utilities: `ExecutorService`, `BlockingQueue`  

---

## 🚀 Getting Started

### Prerequisites

- Java JDK 8 or higher installed  
- Zip4j library added to your project classpath  
- A password-protected ZIP file to test  

---

### ▶️ How to Run

1. Clone or download the repository.
2. Copy the path of the file to the program, and edit size of password
3. Compile the Java files:

```bash
javac -cp zip4j-2.11.2.jar PasswordCracker.java
