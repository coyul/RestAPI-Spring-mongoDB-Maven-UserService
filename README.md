# RestAPI-Spring-mongoDB-Maven-UserService
Данный REST сервис отвечает следующим требованиям:
•	Доступ к сервису осуществляется через REST API
•	MongnDB используется в качестве базы данных для хранения информации
•	Данные пользователя хранятся в виде: Фамилия, Имя, Дата рождения, email и пароль.
•	Функции, которые предоставляет сервис: добавление пользователя, удаление пользователя, поиск пользователя по email
•	Пароль пользователя хранится в безопасной форме

# Для запуска сервиса необходимо
Склонировать репозиторий:
$ git clone https://github.com/coyul/RestAPI-Spring-mongoDB-Maven-UserService
Собрать Maven проект:
$ mvn tomcat8:run
Либо импортируйте проект в используемой Вами IDE как Maven проект (Maven версии 4 и выше, JDK версии 1.8)

#Работа с сервисом осуществляется
http://localhost:8181/person

#Пример работы
Добавление пользователя
http://localhost:8181/person

POST
Accept: application/json
Content-Type: application/json
{
"name":"Gleb",
"surname":"Shurikov",
"birthDate":"07.07.1977",
"email":"glebgleb@pochta.ru",
"password":"123456"
}

Удаление пользователя
http://localhost:8181/person/#Id
DELETE
Accept: application/json
Content-Type: application/json

Поиск пользователя по email
http://localhost:8181/person/email='mashapetrova@ya.ru'
GET
Accept: application/json
Content-Type: application/json