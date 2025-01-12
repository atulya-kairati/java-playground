## Using Redis Cache in Spring boot

### Installation (using docker)

```bash
# Pull the latest Redis image
docker pull redis:latest

# Run Redis container
docker run --name redis-container -p 6379:6379 -d redis

# check
docker exec -it redis-container redis-cli

# stop
docker stop redis-container

# clean
docker rm redis-container
```

### **Using Redis in Spring Boot**

---

#### **1. Add Dependencies**
Include Redis dependency in `pom.xml`:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

---

#### **2. Configure Redis**
Add Redis properties in `application.properties` or `application.yml`:
```properties
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.timeout=6000
spring.cache.redis.time-to-live=300000  # Cache TTL in milliseconds
```

---

#### **3. Enable Caching**
Enable caching in the main application class:
```java
@SpringBootApplication
@EnableCaching
public class MyAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAppApplication.class, args);
    }
}
```

---

#### **4. Use Redis for Caching**
Annotate methods with caching annotations:
- **@Cacheable**: Caches the method's result.
- **@CacheEvict**: Clears the cache for a specific key.
- **@CachePut**: Updates the cache with a new value.

Example:
```java
@Service
public class WeatherService {

    // this creates a cache with name "weather" and saves kv pair cityName:weatherData
    @Cacheable(value = "weather", key = "#city", unless = "#result == null")
    public WeatherResponse getWeatherByCity(String city) {
        // Fetch weather data from an API
        return new WeatherResponse(city, 22.5, "Clear sky");
    }
}
```

---

#### **5. RedisTemplate for Advanced Usage**
If you need custom caching logic, use `RedisTemplate`:
```java
@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(String key, Object value, long ttl) {
        redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
    }

    public Object find(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
```

---