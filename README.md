Area Calculator API with Rate Limiting
  A SpringBoot REST API that calculates the area of a rectangle and enforces request rate limiting using Bucket4j. The API allows up to 10 requests per minute per applicaiton instance.

  Tech Stack
  - Java 17
  - SpringBoot
  - Maven
  - Bucket4j (token bucket rate limiting)
  - Postman/curl (testing)

  Features:
  - REST API for calculating area given length and width
  - JSON request/response handling
  - Token-bucket based rate limiting
  - Returns HTTP 429 TOO MANY REQUESTS when limit is exceeded
  - Clean separation of controller, model, and configuration layers
