           <h1 align="center">AlfaBank Тестовое задание</h1>

<h2>Описание:</h2>
Создать сервис, который обращается к сервису курсов валют, и отображает gif:
1. если курс по отношению к USD за сегодня стал выше вчерашнего, то отдаем рандомную отсюда https://giphy.com/search/rich.
2. если ниже - отсюда https://giphy.com/search/broke.

<h3>Must Have</h3>
- Сервис на Spring Boot 2 + Java / Kotlin.
- Запросы приходят на HTTP endpoint (должен быть написан в соответствии с rest conventions), туда передается код валюты по отношению с которой сравнивается USD.
- Для взаимодействия с внешними сервисами используется Feign.
- Все параметры (валюта по отношению к которой смотрится курс, адреса внешних сервисов и т.д.) вынесены в настройки
- На сервис написаны тесты (для мока внешних сервисов можно использовать @mockbean или WireMock)
- Для сборки должен использоваться Gradle
- Результатом выполнения должен быть репо на GitHub с инструкцией по запуску

<h3>Nice to Have</h3>
- Сборка и запуск Docker контейнера с этим сервисом


<h2>Запуск:</h2>
1. С помощью jar-файла:
java -jar AlfaBank_test.jar
2. С помощью Gradle
./gradlew bootRun
3. С помощью Docker
docker build --tag=AlfaBank_test:latest . .
docker run -p8081:8081 AlfaBank_test:latest

<h2>Как работает?</h2>
После запуска приложения переходим на http://localhost:8081/api/{code}, где {code} это трехбуквенный код валюты, чей курс по отношению к базовому курсу мы проверяем (по умолчанию стоит USD, можно поменять в application.properties. 
После запроса получаем рандомную гифку в зависимости от курса, если курс не изменился, выводим сообщение.

