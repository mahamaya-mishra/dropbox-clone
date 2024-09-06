# Dropbox Clone

## Overview
This project is a Dropbox clone application built with Spring Boot, designed to manage file uploads, downloads, updates, and deletions. It uses an H2 in-memory database for storage.

## Features
- **Upload File**: POST `/files/upload` - Uploads a file to the server.
- **Get File**: GET `/files/{fileId}` - Retrieves a file by its ID.
- **Update File**: PUT `/files/{fileId}` - Updates an existing file.
- **Delete File**: DELETE `/files/{fileId}` - Deletes a file by its ID.
- **List Files**: GET `/files` - Lists all uploaded files.

## Getting Started

### Prerequisites
- Docker
- Java 17 or later

### Building the Project

1. **Clone the repository:**
    ```bash
    git clone <repository-url>
    cd <project-directory>
    ```

2. **Build the JAR file with Maven:**
    ```bash
    mvn clean package
    ```

### Running with Docker

1. **Build the Docker image:**
    ```bash
    docker build -t springboot-h2-docker .
    ```

2. **Run the Docker container:**
    ```bash
    docker run -p 8080:8080 springboot-h2-docker
    ```

### Accessing the Application

- The application will be available at `http://localhost:8080`.

### Testing the Endpoints

**Upload File:**
```bash
curl -F "file=@path/to/your/file.txt" http://localhost:8080/files/upload
