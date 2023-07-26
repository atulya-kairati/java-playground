# JUnit 5

- Modern unit testing framework for the JVM.
- **JUnit5** = (JUnit Platform) + (JUnit Jupiter) + (JUnit Vintage).

- **JUnit Platform**: The platform is responsible for launching testing frameworks on the JVM. It defines a stable and powerful interface between JUnit and its clients, such as build tools. It basically defines an API for developing testing frameworks on top of JUnit.
- **JUnit Jupiter**: This module includes new programming and extension models for writing tests in JUnit 5.
- **JUnit Vintage**: JUnit Vintage supports running tests based on JUnit 3 and JUnit 4 on the JUnit 5 platform.

## Installation

### Maven
- Open `pom.xml` and add dependency.
```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.9.2</version>
    <scope>test</scope> <!--  dependency to be used for testing and not to be packaged with the artifact  -->
</dependency>
```

- [Assertions](https://junit.org/junit5/docs/5.0.1/api/org/junit/jupiter/api/Assertions.html).
- 