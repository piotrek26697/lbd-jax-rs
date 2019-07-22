# Build
mvn clean package && docker build -t pl.fis.piotr.musiol/lbd-jax-rs .

# RUN

docker rm -f lbd-jax-rs || true && docker run -d -p 8080:8080 -p 4848:4848 --name lbd-jax-rs pl.fis.piotr.musiol/lbd-jax-rs 