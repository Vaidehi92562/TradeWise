# TradeWise - A Smart Mock Trading Platform Simulation

## Project Overview
TradeWise is a smart mock trading platform simulation developed as part of the course **21IPE333P – Essentials in Cloud and DevOps**.  
The platform allows users to explore stock trading concepts in a safe, risk-free environment using virtual money. It is designed to simulate core trading workflows such as user login, stock browsing, buy/sell actions, watchlist management, portfolio tracking, and transaction monitoring.

Along with the software engineering aspects of the application, the project also demonstrates important **Cloud and DevOps practices** including containerization, service orchestration, continuous integration, monitoring, observability, and Kubernetes-based deployment setup.

---

## Objective
The main objective of TradeWise is to provide a simple and interactive stock trading simulation platform where users can practice trading logic without using real money, while also showcasing practical implementation of DevOps tools and workflows.

---

## Course Details
- **Course Code:** 21IPE333P  
- **Course Name:** Essentials in Cloud and DevOps  
- **Faculty:** Dr. L. Anand  

---

## Team Members
- **RA2311003011138** – Aditya Prasad  
- **RA2311003011148** – Eiswar G  
- **RA2311003011168** – Vaidehi Mishra  

---

## Key Features
- User registration and login
- Dashboard with personalized user data
- Stock market page for mock trading
- Buy stock functionality
- Sell stock functionality
- Portfolio tracking
- Transaction history
- Watchlist management
- MySQL database integration
- Dockerized backend and frontend
- Docker Compose orchestration
- GitHub Actions CI pipeline
- Spring Boot Actuator health and metrics endpoints
- Prometheus monitoring
- Grafana dashboard visualization
- Kubernetes YAML-based deployment setup

---

## Tech Stack

### Frontend
- HTML
- CSS
- JavaScript

### Backend
- Java
- Spring Boot
- Maven

### Database
- MySQL

### DevOps / Cloud Tools
- Git & GitHub
- GitHub Actions
- Docker
- Docker Compose
- Spring Boot Actuator
- Prometheus
- Grafana
- Kubernetes

---

## Project Structure
```text
TradeWise/
│
├── .github/
│   └── workflows/
│       └── ci.yml
│
├── backend/
│   ├── src/
│   ├── Dockerfile
│   ├── pom.xml
│   └── mvnw
│
├── frontend/
│   ├── assets/
│   ├── index.html
│   ├── login.html
│   ├── register.html
│   ├── dashboard.html
│   ├── market.html
│   ├── portfolio.html
│   ├── transactions.html
│   ├── watchlist.html
│   └── Dockerfile
│
├── k8s/
│   ├── mysql.yaml
│   ├── backend.yaml
│   └── frontend.yaml
│
├── docker/
│   └── prometheus.yml
│
├── docker-compose.yml
└── README.md