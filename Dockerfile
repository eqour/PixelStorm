# Используем официальный образ OpenJDK 17 на Alpine
FROM openjdk:17-jdk-alpine

# Устанавливаем Maven
RUN apk add --no-cache maven

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем файлы сборки (pom.xml и src) в рабочую директорию
COPY pom.xml .
COPY src ./src

# Собираем проект с помощью Maven
RUN mvn clean package -DskipTests

# Определяем команду для запуска приложения
CMD ["java", "-jar", "target/pixelstorm.jar"]

# Указываем порт, который будет использоваться приложением
EXPOSE 8080
