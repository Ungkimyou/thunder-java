FROM azul/zulu-openjdk-alpine:11

WORKDIR /usr/thunder

COPY . .

RUN echo [INFO] Starting to build Docker image... \
&& echo [INFO] Building gradle project... \
&& ./gradlew build \
&& echo [INFO] Done!

EXPOSE 3445

ENTRYPOINT ["java", "-jar", "Thunder.jar"]
