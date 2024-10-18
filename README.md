# Balance Sheet

**Java Project for the Object-Oriented Programming Exam 2022/23**  
Professors: Giacomo Cabri, Nicola Capodieci

## Table of Contents
1. [Overview](#overview)
2. [Key Features](#key-features)
3. [Project Structure](#project-structure)
4. [Installation](#installation)
5. [Usage](#usage)
6. [Building the Project](#building-the-project)
7. [Technical Requirements](#technical-requirements)
8. [Documentation](#documentation)

## Overview
Managing one's income and expenses has become essential not only for businesses and commercial entities but also for families and volunteer organizations. This project presents a Java application with a graphical interface to help users manage their budget effectively.

## Key Features:
- **Budget Management**: Track income and expenses using a table displaying the following information:
  - Date
  - Description
  - Amount
  Each entry can be classified as either an income or an expense. Users can add, modify, or delete entries. The total balance (income minus expenses) is displayed based on selected timeframes (day, week, month, or year).
  
- **Save and Load Budget**: Save the budget to a file or reload it by specifying the filename.

- **Search**: Perform free-text searches within the budget to find matching entries. Highlight results, and allow users to navigate through all matches.

- **Export**: Export the budget in CSV or text format.

- **Print**: Print the budget for documentation or record-keeping purposes.

## Project Structure:
- **grafica**: Contains the base components for the graphical interface, such as frames and panels.
- **pannelli**: Holds all panels, divided based on their specific functionality within the app.
- **salvataggi**: Manages the saving and loading of the budget.
- **strutture**: Contains data structures and the main class.
- **test**: Includes manual test cases with recorded results.

## Installation

### Prerequisites:
- Java Development Kit (JDK) 8 or higher
- Git

### Steps to Clone the Repository:
1. Open a terminal and navigate to the directory where you want to clone the repository.
2. Run the following command:

   ```bash
   git clone https://github.com/lucadileo9/Balance-Sheet.git
   ```

3. Navigate into the project directory:

   ```bash
   cd Balance-Sheet
   ```

## Usage

After running the application, a graphical interface will appear allowing you to:
- Add, modify, and delete income and expense entries.
- View, search, and export the budget.
- Save and load your budget data from files.

## Building the Project

It is highly recommended to use an Integrated Development Environment (IDE) such as **Eclipse**, which was used to develop this project, or another Java-compatible IDE (e.g., IntelliJ IDEA or NetBeans) to compile and run the application. While command-line usage is possible, IDEs provide better support for managing dependencies, running tests, and building the project.

### Steps to Build in Eclipse:
1. Clone the repository as described in the [Installation](#installation) section.
2. Open Eclipse, and import the project as an **existing Java project**.
3. After importing, you can run the project directly from Eclipse.

If you prefer to use the command line, the project can still be built and run using the following commands:

1. Compile the project:
   ```bash
   javac -d bin src/**/*.java
   ```

2. Run the application:
   ```bash
   java -cp bin com.balancesheet.Main
   ```

## Technical Requirements:
- The project uses **Java** and adheres to **Object-Oriented Programming** principles such as encapsulation, inheritance, and polymorphism.
- The project is executable from the command line.
- Utilizes Java system classes for input/output management and standard library data structures with generics.
- **All documentation and comments are in Italian**, providing detailed descriptions of the project's design and functionality.

## Documentation
The project documentation has already been generated and is available in HTML format. It is located within the `doc` folder and can be viewed by opening the `index.html` file in a web browser.

The documentation, as well as the in-code comments, are entirely written in **Italian**, offering clear explanations of design choices, the structure of the software system, and how the project is organized.

To generate the documentation again (if necessary), you can use the following command:

```bash
javadoc -d doc src/**/*.java
```
