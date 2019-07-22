FROM airhacks/glassfish
COPY ./target/lbd-jax-rs.war ${DEPLOYMENT_DIR}
