# GpsPoints

Тестовое задание для Android разработчика
+ Требуется написать на Kotlin или Java мобильное приложение для Android, которое запрашивает у сервера определённое количество координат точек (x, y), а затем отображает полученный ответ в виде таблицы и графика.
+ На главном экране имеется блок информационного текста, поле для ввода числа точек и одна кнопка «Поехали».
+ По нажатию на кнопку осуществляется запрос к API сервера (GET /api/test/points), с параметром количества запрашиваемых точек (count).
+ Сервер выдаёт в ответ массив точек в JSON формате, пример: {"points":[{"x":1.23, "y":2.44},{"x":2.17, "y":3.66}]}
Спецификации API (если нужно):
Swagger 2 - UI тут,
OpenAPI (Swagger 3) - UI тут
+ При неверном количестве запрошенных точек сервер возвращает ошибку. Кроме того он может просто ломаться сам-по-себе.
+ Если ответ от сервера получен, то на новом экране должна отобразиться таблица с полученными координатами точек. Ниже должен быть отображен график с точками, соединёнными прямыми линиями. Точки на графике должны следовать по возрастанию координаты x.

+ Для создания UI желательно использовать классическую систему android View/Layouts

Дополнительно можно осуществить следующие возможности работы с графиком:
+ изменения масштаба пользователем
+ соединение точек не ломаной линией, а сглаженной
+ работа в портретной и ландшафтной ориентации экрана
+ сохранение изображения графика в файл

http://hr-challenge.interactive-ventures.com/api/test/text/android
