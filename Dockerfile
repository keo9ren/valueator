FROM airhacks/glassfish
COPY ./target/valueator.war ${DEPLOYMENT_DIR}
