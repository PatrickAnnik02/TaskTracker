# Task Tracker

Task Tracker is a CLI application designed for task management. This is an educational mini-project aimed at learning Object-Oriented Programming (OOP) using Java.

## Requirements

Before running this project, ensure you have the following installed:

* **Java Development Kit (JDK) 21** or higher.
* **Maven** (3.6.3 or higher recommended).
* **Git** (to clone the repository).

## Installation

Follow these steps to get the project running on your local machine:

**1. Clone the repository**
Open your terminal and run the following command to download the code:

```bash
git clone https://github.com/PatrickAnnik02/TaskTracker.git
```

**2. Navigate to the project directory**

```bash
`cd TaskTracker`
```

**3. Build the project**
Use Maven to compile the code and package dependencies (like Gson) into a single executable JAR file (Fat JAR).

```bash
mvn clean package
```

*Note: If the build is successful, you will see a **BUILD SUCCESS** message, and **target** folder will be created.*

**4. Run the application**
You don't need to install anything else. Run the generated JAR file directly:

```bash
java -jar target/TaskTracker-1.0-SNAPSHOT.jar list
```

(This command list the tasks to verify everything is working correctly).

## Usage

Here are some examples of how to use the application. All commands are executed via the generated JAR file.

### 1. Add a new task
This is the first step to populate your list.
```bash
java -jar target/TaskTracker-1.0-SNAPSHOT.jar add "Exercise"
```

### 2. List all tasks
View all your current tasks, including their ID, status, and creation/update times.
```bash
java -jar target/TaskTracker-1.0-SNAPSHOT.jar list
```

### Expected Output:
```text
Welcome to TaskTracker.
Write `help` to commands or `exit` to exit.
--- Task list ---
ID: 1 | [To do] Exercise
Created: 2025-12-10 10:00 | Updated: 2025-12-10 10:00
```

### 3. Update a task's status
Move a task through its lifecycle. You can mark tasks as `mark-in-progress`, `mark-done`, or `mark-todo`.

#### Example: Start working on a task
```bash
java -jar target/TaskTracker-1.0-SNAPSHOT.jar mark-in-progress 1
```

#### Example: Finish a task
```bash
java -jar target/TaskTracker-1.0-SNAPSHOT.jar mark-done 1
```

### 4. Filter tasks by status
You can view only the tasks that match a specific status (*todo*, *in-progress*, *done*).
```bash
java -jar target/TaskTracker-1.0-SNAPSHOT.jar list done
```

### Expected Output:
```text
Welcome to TaskTracker.
Write `help` to commands or `exit` to exit.
--- Task list ---
ID: 1 | [Done] Exercise
Created: 2025-12-10 10:00 | Updated: 2025-12-10 12:00
```

### 5. Update a task's description
You can update a task using the update command with the task ID.
```bash
java -jar target/TaskTracker-1.0-SNAPSHOT.jar update 1 "Read a book"
```

### 6. Delete a task
Remove a task permanently from the JSON file.
```bash
java -jar target/TaskTracker-1.0-SNAPSHOT.jar delete 1
```
