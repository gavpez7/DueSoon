# DueSoon

A deadline tracker for university students that answers two questions a normal calendar can't:

1. **What should I work on today?** (urgency ranking)
2. **Which weeks are going to be brutal?** (workload heatmap)

> **Status: work in progress.** The backend for courses and tasks works. The urgency ranking, heatmap, and frontend are being built. See the roadmap below for what's done and what's next.

## Problem

Calendar apps show *when* things are due, but not how much work each deadline needs or how deadlines pile up. Three assessments landing in the same week looks no different from three spread across a month, and a 5% quiz looks as important as a 30% exam.

DueSoon stores each deadline with its **weight** and an **estimated effort**, so it can rank what matters most right now and show where the crunch weeks are.

## Features

- [x] Create and list courses
- [x] Create, list, and delete tasks (assignment / quiz / exam / project), each linked to a course
- [x] Task fields: due date and time, weight (%), estimated hours, status
- [ ] Urgency score and ranked "today" list
- [ ] Unit tests for the urgency logic
- [ ] Workload heatmap by week
- [ ] Web frontend for the ranked list and heatmap
- [ ] Deployed demo

## How urgency is calculated

Each task gets a score. A higher score means "do this sooner".

```
daysLeft = max(hoursUntilDue / 24, 0.25)
score    = (weightPercent / 10) + (estimatedHours / daysLeft)
```

- **Weight / 10** makes heavier assessments score higher. A 30% exam adds 3 points, a 5% quiz adds 0.5.
- **Estimated hours / days left** is the pressure term. It measures how many hours of work you'd need per day if you started today. Lots of work with little time left produces a big number.
- **The 0.25-day floor** (6 hours) stops the score from exploding for tasks due in minutes.

Worked examples:

| Task | Weight | Effort | Due in | Score |
|---|---|---|---|---|
| Assignment | 15% | 8 h | 4 days | 1.5 + 2.0 = **3.5** |
| Quiz | 5% | 2 h | 1 day | 0.5 + 2.0 = **2.5** |
| Group project | 40% | 20 h | 10 days | 4.0 + 2.0 = **6.0** |
| Quiz (last minute) | 10% | 1 h | 1 hour | 1.0 + 4.0 = **5.0** |

The last row shows the floor at work: without it, 1 hour of work due in 1 hour would score 25 on the pressure term alone.

This is a first version of the formula. It will be tuned, and this section will be kept in sync with the code.

## Tech stack

- Java 17+
- Spring Boot (Web, Data JPA, Validation)
- H2 in-memory database (PostgreSQL planned)

## Run it locally

You need Java 17 or newer and Git.

```bash
git clone https://github.com/gavpez7/duesoon.git
cd duesoon
./mvnw spring-boot:run        # on Windows: mvnw.cmd spring-boot:run
```

The app starts on **http://localhost:8081**.

The database is in memory, so data resets every time the app restarts.

### Try the API

Create a course:

```bash
curl -X POST http://localhost:8081/api/courses \
  -H "Content-Type: application/json" \
  -d '{"code":"CS2040","name":"Data Structures","colour":"blue"}'
```

Create a task linked to it:

```bash
curl -X POST http://localhost:8081/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"title":"Assignment 1","type":"ASSIGNMENT","dueAt":"2026-11-20T23:59:00","weightPercent":15,"estimatedHours":8,"course":{"id":1}}'
```

List tasks:

```bash
curl http://localhost:8081/api/tasks
```

### Endpoints

| Method | Path | Purpose |
|---|---|---|
| GET | `/api/courses` | list courses |
| GET | `/api/courses/{id}` | get one course |
| POST | `/api/courses` | create a course |
| GET | `/api/tasks` | list tasks |
| POST | `/api/tasks` | create a task |
| DELETE | `/api/tasks/{id}` | delete a task |

## Screenshots

_Coming once the frontend and heatmap are built._

## Roadmap

1. Urgency calculator with unit tests, and a ranked `/api/tasks/today` endpoint
2. Heatmap endpoint (workload per day and week)
3. Frontend: ranked list and heatmap grid
4. Sample data loaded on startup so the demo isn't empty
5. Deployment (Docker + a free host)
6. Later: `.ics` calendar import, reminders via a chat bot
