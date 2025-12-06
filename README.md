# 💳 Bank Management System (Java + Swing + MySQL)

A modernized ATM-style **Bank Management System** built using:

- 🟦 **Java (Swing + AWT)**
- 🟩 **MySQL Database**
- 🛠 **NetBeans IDE**
- 🎨 **Custom UI Utilities**
- 📑 Multi-step Account Creation (Signup1 → Signup2 → Signup3)
- 🏧 ATM Functionalities (Deposit, Withdraw, Fast Cash, Mini Statement, Balance Enquiry, PIN Change)

---

## 🚀 Features

### 🔐 User Authentication
- Secure login using **card number + PIN**
- Auto-generated card number & PIN during signup

### 📝 Account Creation (Signup Module)
- **Signup1:** Personal details  
- **Signup2:** Additional details  
- **Signup3:** Account type, services & card generation

### 💵 ATM Operations
- Deposit
- Withdrawal
- Fast Cash
- Balance Enquiry
- Mini Statement (with PDF export)
- PIN Change

---

## 🗄 Database Structure (MySQL)

### **Table: signup**
| Column     | Type |
|------------|------|
| formno     | varchar(20) (PK) |
| name       | varchar(50) |
| fname      | varchar(50) |
| dob        | varchar(20) |
| gender     | varchar(10) |
| email      | varchar(50) |
| marital    | varchar(20) |
| address    | varchar(200) |
| city       | varchar(50) |
| state      | varchar(50) |
| pin        | varchar(10) |
| mobile     | varchar(20) |

### **Table: signup2**
(formno → FK to signup)

### **Table: signup3**
Stores account type, card number, PIN, services.

### **Table: login**
Stores card number and PIN for login authentication.

### **Table: bank**
Stores all transactions.

---

## 🔧 How to Run the Project

### **1. Install Requirements**
- Install **JDK 8+**
- Install **MySQL**
- Install **NetBeans**  13 (recommended)

### **2. Import the Database**
Create database:
```sql
CREATE DATABASE bankmanagementsystem;
```

Run all required tables.

### **3. Configure Database Connection**
In `Conn.java` update:
```java
c = DriverManager.getConnection(
  "jdbc:mysql://localhost:3306/bankmanagementsystem",
  "root",
  "your_password"
);
```

### **4. Run the Application**
Open NetBeans → Run **Login.java**

---

## 📸 Screenshots

<img width="757" height="504" alt="Screenshot 2025-12-06 161636" src="https://github.com/user-attachments/assets/4953bfd6-0fcb-4819-8342-59000d4a3aa1" />
<img width="753" height="852" alt="Screenshot 2025-12-06 161718" src="https://github.com/user-attachments/assets/c0dce3de-4c4c-4b8b-a7f6-f10e5ea4faa3" />
<img width="833" height="762" alt="Screenshot 2025-12-06 161801" src="https://github.com/user-attachments/assets/0c6c5ad6-1343-4306-aabc-d7728050aaeb" />
<img width="901" height="595" alt="Screenshot 2025-12-06 162019" src="https://github.com/user-attachments/assets/5696aa1f-1349-4fd2-b60b-9d53650c6228" />









## 👨‍💻 Developer
**Akshay Sonawane**  
Java Developer | Full-Stack Learner  
📧 akshaysonawane2510@gmail.com

---

## ⭐ Contribution
Feel free to fork and submit pull requests!

---

## 📜 License
This project is open-source for learning & academic purposes.
