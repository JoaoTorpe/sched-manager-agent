# Sched-Agent

Sched-Agent is an AI-powered personal assistant designed to manage personal tasks using natural language.

The system integrates with external tools such as Notion, allowing to create tasks, query their schedule, and receive automated weekly notifications.

---

# Architecture

The project is composed of two main modules:

### **mcp**

A backend service implemented with **Spring Boot** responsible for:

* Tool execution
* Integrations with external services
* Business logic
* Task management

Currently, this module provides integrations with **Notion** for task storage and retrieval.

---

### **mcp-client**

A client service implemented with **Spring Boot** responsible for:

* User interaction
* Notification scheduling
* AI orchestration using LLMs
* Communication with the `mcp` module

This module integrates with the **OpenAI API** to interpret user commands and trigger actions.

---


# Features

### Task Creation

Create tasks in Notion using natural language.

Example:

```
Create a task for tomorrow to finish the report
```

The agent interprets the request and creates the task in Notion.

---

### Task Query

Retrieve tasks for:

* A specific day
* A range of days

Examples:

```
What tasks do I have tomorrow?
```

```
What do I need to do this week?
```

```
What are my commitments from Monday to Wednesday next week?
```

---

### Weekly Notification System (WIP)

The system automatically sends notifications through Telegram.

**Monday Morning**

A message containing all tasks scheduled for the current week.

Example:

```
Your tasks for this week:

- Finish architecture document
- Review pull requests
- Prepare presentation
```

**Friday Night**

A weekly report containing:

* Tasks completed
* Tasks not completed

Example:

```
Weekly Report

Completed:
✔ Review pull requests
✔ Prepare presentation

Pending:
✖ Finish architecture document
```


# Project Status

🚧 Work in Progress


