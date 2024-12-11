# Eater - Backend for Delivery Service

Eater is a backend system for a delivery service, designed to efficiently handle user management, order processing, and rating systems. Built with a modern technology stack, it offers secure authentication, email verification, and support for different user roles.

## Features

- **User Roles**:
    - **Client**: Browse menus, place orders, and leave reviews.
    - **Courier**: Manage delivery tasks and update delivery status.
    - **Restaurant Owner**: Manage restaurant menus and view orders.
    - **Admin**: Oversee all operations, manage users, and resolve issues.
  

- **Core Functionality**:
    - Secure authentication and authorization using JWT.
    - Role-based access control for different endpoints.
    - Email verification for account activation.
    - Place, manage, and track orders.
    - Rating and review system for restaurants and couriers.
    - Integration with Amazon S3 for storing images.
    - Deployed in a Dockerized environment with PostgreSQL as the database.

## Technology Stack

- **Backend Framework**: Spring Boot
- **Security**: Spring Security with JWT (JSON Web Token)
- **Database**: PostgreSQL
- **Cloud Storage**: Amazon S3
- **Deployment**: Docker
- **Dynamic route planning**: Integration with Google Maps API for optimal delivery routes.
- **Others**: Hibernate, JPA

## Installation and Setup

Follow these steps to set up and run the **Eater** application.

### Prerequisites
1. Install [Docker](https://www.docker.com/).
2. Obtain the following:
    - Google Cloud API key for route planning.
    - AWS credentials for S3 storage.
    - Other Required Secrets: Such as email credentials and application keys.

### Steps

#### 1. Clone the Repository
Clone the backend repository and navigate to its directory:
```
git clone https://github.com/romanggr/eater_back.git
cd eater_back
```

#### 2. Configure the Environment File
    1. Change .env.example to .env and fill up api-keys
    2. Open the .env file and replace the placeholder values with your actual credentials and keys

#### 3. Build and Start the Application
```
docker-compose up --build
```
#### 3. Stop the Application
```
docker-compose down
```
