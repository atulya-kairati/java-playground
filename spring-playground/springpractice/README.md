docker compose up -d
docker compose down
docker run --name springpractice --rm -p8000:8000
    --network springpractice_db 4ager/springpractice
    --spring.datasource.url=jdbc:postgresql://db:5432/customer


### build frontend

docker compose build
docker compose up -d
