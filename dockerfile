FROM bellsoft/liberica-openjdk-debian:21

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

# 🚀 Disable JIT compilation to prevent crashes
CMD ["java", "-Xint", "-jar", "app.jar"]

#not working