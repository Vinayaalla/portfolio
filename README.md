# Vinay Aalla Portfolio

Full-stack portfolio served by Spring Boot. The HTML, CSS, and browser JavaScript provide the UI; Java 25, Spring Boot, REST endpoints, Spring Data JPA, and H2 provide the application and persistence layers.

## Requirements

- JDK 25
- Maven 3.6.3 or newer

## Run locally

From this directory, run:

```powershell
mvn spring-boot:run
```

Open <http://localhost:8080>. Project cards load from `GET /api/projects`. The contact form submits validated JSON to `POST /api/contact`, and messages persist in a local file-backed H2 database under the ignored `data/` directory.

The API also provides `GET /api/health` for a simple health response. Run the integration tests with `mvn test` and package the executable JAR with `mvn package`.

## API

- `GET /api/health` - application health response.
- `GET /api/projects` - project summaries and technology lists.
- `POST /api/contact` - validate and persist a contact message. JSON fields: `name`, `email`, and `message`.