Стартер для логирования HTTP запросов.

Запуск тестов:
```
mvn test
```
Для использования стартера выполнить сборку
```
mvn clean install
```
добавить в проект в корневой файл pom.xml зависимость
```
        <dependency>
            <groupId>com.example</groupId>
            <artifactId>custom-http-logging-starter</artifactId>
            <version>0.0.2</version>
        </dependency>
```
настроить логирование через конфиги в application.properties
```
logging.interceptor.enabled=true
logging.interceptor.level=INFO
```
Пример подключения стартера и какие логи получились, 
можно посмотреть в мр https://github.com/ljbjkgyguoj/logging-service/pull/1
