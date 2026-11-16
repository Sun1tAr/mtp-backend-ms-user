# Money Talks Project — User Service

Микросервис пользователей **Money Talks Project**. Хранит логины и хэши паролей,
отдаёт пользователей gateway и другим внутренним сервисам.

## Ответственность

- создание пользователя;
- поиск пользователя по логину и по идентификатору;
- хранение учётных данных в PostgreSQL;
- миграции схемы через Liquibase.

Сервис не публикуется наружу: доступ к нему есть только у gateway во внутренней
сети. Пароли хэшируются BCrypt в gateway; в этот сервис приходит уже готовый
`passwordHash`.

## Стек

- Java 21, Spring Boot 4.0.1;
- Spring Web MVC, Spring Data JPA, Liquibase;
- PostgreSQL;
- Lombok, Maven.

## API

| Метод | Путь | Назначение |
|---|---|---|
| `POST` | `/users/new` | Создать пользователя: `{login, passwordHash}` |
| `GET` | `/users/by-login?login=...` | Найти пользователя по логину |
| `GET` | `/users/by-id?id=...` | Найти пользователя по ID |

Пример:

```bash
curl -X POST http://localhost:8083/users/new \
  -H "Content-Type: application/json" \
  -d '{"login":"user","passwordHash":"$2a$10$..."}'

curl "http://localhost:8083/users/by-login?login=user"
curl "http://localhost:8083/users/by-id?id=1"
```

Ошибки возвращаются в едином формате `mtp-backend-commons` (`code`, `message`,
`data`). `USER_ALREADY_EXIST` — конфликт при повторном логине, `USER_NOT_FOUND` —
пользователь не найден.

## Данные

Схема по умолчанию — `mtp_user`, база — `mtp_dev`. Миграции:
`src/main/resources/db/dev/changelog.yml`.

Таблица `app_user`:

| Колонка | Тип | Ограничения |
|---|---|---|
| `id` | `bigint` | первичный ключ, autoincrement |
| `login` | `varchar(50)` | уникальный |
| `password` | `varchar` | хэш пароля (BCrypt) |

Схема `mtp_user` должна существовать до первого запуска: Liquibase создаёт
таблицы и данные, но не саму схему.

## Конфигурация

| Переменная | Назначение | По умолчанию |
|---|---|---|
| `POSTGRES_URL` | Хост PostgreSQL | `localhost` |
| `POSTGRES_PORT` | Порт PostgreSQL | `5432` |
| `POSTGRES_DB_NAME` | Имя базы | `mtp_dev` |
| `POSTGRES_USER` | Пользователь БД | `postgres` |
| `POSTGRES_PASSWORD` | Пароль БД | dev-значение в `application.yml` |
| `DB_SCHEME_NAME` | Схема сервиса | `mtp_user` |
| `LIQUIBASE_ENABLED` | Запускать миграции при старте | `true` |
| `LOGGING_LEVEL` | Уровень логирования | `debug` |

Значения по умолчанию в `application.yml` — локальные заглушки. В реальных
окружениях пароль БД задаётся только через окружение.

## Запуск

Требуется JDK 21 и PostgreSQL. Сначала установите родительский POM и общий модуль:

```bash
cd ../mtp-backend-root && ./mvnw install
cd ../mtp-backend-commons && ./mvnw install
./mvnw spring-boot:run
```

Сервис слушает порт `8083`.

Тесты:

```bash
./mvnw test
```

В каталоге `http-request` лежат примеры HTTP-запросов. Часть из них оформлена
раньше текущих путей контроллера — актуальные пути указаны в таблице API выше.

## Связанные репозитории

- [Money-Talks-Project](https://github.com/Sun1tAr/Money-Talks-Project);
- [mtp-backend-ms-gateway](https://github.com/Sun1tAr/mtp-backend-ms-gateway);
- [mtp-backend-commons](https://github.com/Sun1tAr/mtp-backend-commons).
