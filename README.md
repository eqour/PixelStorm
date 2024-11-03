# PixelStorm

Интерактивный онлайн холст с использованием WebSocket

### Запуск в Docker

Сборка контейнера

```bash
docker build -t pixelstorm .
```

Запуск контейнера

```bash
docker run -p 80:8080 pixelstorm
```

### Используемое ПО

- [sockjs-client](https://github.com/sockjs/sockjs-client)
- [stomp-js](https://github.com/stomp-js/stompjs/tree/develop)
- [maven](https://github.com/apache/maven)
- [spring-boot](https://github.com/spring-projects/spring-boot)
