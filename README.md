📘 Project Report: Library Management System
🔖 Project Title:
Library Management System in Java :
🎯 Objective:
To design and implement a console-based Library Management System
using Java, allowing users to issue/return books and enabling admins to
manage book inventory.🛠️
Technologies Used:
• Programming Language: Java
• Java Core Concepts: Classes, Objects, Inheritance, Maps, Scanner,
Date and Time (Calendar, Date)
• IDE: (Eclipse / IntelliJ IDEA / VS Code / etc.)
2
📋 Features:
Admin:
• Add new books
• View all books
• Logout
User:
• View available books
• Issue a book
• Return a book
• Logout
👥 User Roles:
• Admin: Can add books and view the inventory.
• User: Can only issue or return books.
🔐 Login Credentials (Hardcoded for Demo):
• Admin: admin
• User: user1
📚 Book Class:
Stores book information like:
3
• ID
• Title
• Author
• Issue status
• Issued to (Username)
• Due date
🔄 Flow of Execution:
1. Login → User selects a role (admin/user).
2. Role-based menu is displayed.
3. Operations performed based on selection.
4. Loop continues until logout.
📦 Data Storage:
• Data is stored in-memory using HashMaps:
o books: stores Book ID and Book object
o users: stores Username and User object
