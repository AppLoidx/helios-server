# Helios API documentation

Здесь представлены основные методы и их параметры для работы с Helios API

## Authorization
Pattern:
> /auth?

### Methods and Params
#### GET
* username - логин пользователя
* password - пароль пользователя

Этот метод возвращает ключ сессии в качестве ответа и записывает его в cookie пользователя

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




