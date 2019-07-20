# Helios API documentation

Здесь представлены основные методы и их параметры для работы с Helios API

## Authorization
Pattern:
> /auth?

### Methods and Params
#### GET
* username - логин пользователя
* password - пароль пользователя
* redirectUri - переброс на этот путь после удачной авторизации

Этот метод записывает в cookie пользователя ключ сессии и перенаправляет его по указанному URI

## Register
Pattern:
> /register?

### Methods and Params
#### POST
* username - логин пользователя
* password - пароль пользователя
* firstName - имя
* lastName - фамилия

Создает нового пользователя

## User API
Pattern
> /user?

### Methods and Params
#### GET
* session (optional) - ключ сессии пользователя (не нужен, если ключ сессии записан в cookie)

Пример ответа:
```json
{
  "user" : {
    "id" : 42,
    "contactDetails" : null,
    "username" : "123",
    "firstName" : "Arthur",
    "lastName" : "Kupriyanov"
  },
  "queues" : [ [ "Queue1", "Queue1Fullname" ], [ "QueueShortLink", "QueueFullnameForSidebar" ], [ "dop758", "Доп к Николаеву" ] ]
}
```
## Chat API
Pattern:
> /chat?<queue-name>

<queue-name> - имя очереди

### Methods and Params
#### GET
* lastMsgId - ID последнего сообщения, начиная с которого нужно получить сообщения

Получает сообщения внутри очереди

#### PUT
* message - сообщение, которое нужно добавить
* session(optional) - ключ сессии ( не нужен, если есть cookie)

Добавляет сообщение в чат



