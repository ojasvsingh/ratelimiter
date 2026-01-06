Area Calculator API with Rate Limiting
  A SpringBoot REST API that calculates the area of a rectangle
  This API implements per-IP rate limiting using the token bucket algorithm via Bucket4j.
  Each client IP address is assigned an independent in-memory token bucket allowing up to 10 requests per minute.
  Requests exceeding this limit are rejected with HTTP 429 (Too Many Requests)

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
