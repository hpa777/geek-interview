-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Янв 26 2022 г., 20:46
-- Версия сервера: 8.0.19
-- Версия PHP: 7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `cinema_db`
--

-- --------------------------------------------------------

--
-- Структура таблицы `movies`
--

CREATE TABLE `movies` (
  `id` int NOT NULL,
  `title` varchar(500) NOT NULL,
  `duration` enum('60','90','120','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `movies`
--

INSERT INTO `movies` (`id`, `title`, `duration`) VALUES
(1, 'King’s Man: Начало', '120'),
(2, 'Последний богатырь: Посланник Тьмы', '90'),
(3, 'Крик', '60'),
(4, 'Человек-паук: Нет пути домой', '120');

-- --------------------------------------------------------

--
-- Структура таблицы `sales_log`
--

CREATE TABLE `sales_log` (
  `id` int NOT NULL,
  `ticket_id` int NOT NULL,
  `ticket_number` int NOT NULL,
  `sales_time` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `sales_log`
--

INSERT INTO `sales_log` (`id`, `ticket_id`, `ticket_number`, `sales_time`) VALUES
(1, 1, 1, '2022-01-26 13:45:34'),
(2, 2, 2, '2022-01-26 13:45:34'),
(5, 6, 6, '2022-01-26 13:46:08'),
(6, 7, 7, '2022-01-26 13:46:08');

-- --------------------------------------------------------

--
-- Структура таблицы `session_schedule`
--

CREATE TABLE `session_schedule` (
  `id` int NOT NULL,
  `movie_id` int NOT NULL,
  `session_start` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `session_schedule`
--

INSERT INTO `session_schedule` (`id`, `movie_id`, `session_start`) VALUES
(1, 1, '2022-01-26 06:37:41'),
(2, 3, '2022-01-26 08:40:00'),
(3, 2, '2022-01-26 10:00:00'),
(4, 4, '2022-01-26 13:00:00'),
(5, 3, '2022-01-26 15:00:00'),
(6, 1, '2022-01-26 17:00:00'),
(7, 3, '2022-01-26 20:40:09');

-- --------------------------------------------------------

--
-- Структура таблицы `session_tickets`
--

CREATE TABLE `session_tickets` (
  `id` int NOT NULL,
  `session_id` int NOT NULL,
  `price` int NOT NULL COMMENT 'В копейках',
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `session_tickets`
--

INSERT INTO `session_tickets` (`id`, `session_id`, `price`, `description`) VALUES
(1, 1, 100, 'Обычный'),
(2, 1, 200, 'Диваны'),
(3, 2, 150, NULL),
(4, 3, 100, NULL),
(5, 4, 400, NULL),
(6, 5, 150, NULL),
(7, 6, 260, NULL),
(8, 7, 333, NULL);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `sales_log`
--
ALTER TABLE `sales_log`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ticket_number` (`ticket_number`),
  ADD KEY `ticket_id` (`ticket_id`);

--
-- Индексы таблицы `session_schedule`
--
ALTER TABLE `session_schedule`
  ADD PRIMARY KEY (`id`),
  ADD KEY `movie_id` (`movie_id`);

--
-- Индексы таблицы `session_tickets`
--
ALTER TABLE `session_tickets`
  ADD PRIMARY KEY (`id`),
  ADD KEY `session_id` (`session_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `movies`
--
ALTER TABLE `movies`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `sales_log`
--
ALTER TABLE `sales_log`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT для таблицы `session_schedule`
--
ALTER TABLE `session_schedule`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT для таблицы `session_tickets`
--
ALTER TABLE `session_tickets`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `sales_log`
--
ALTER TABLE `sales_log`
  ADD CONSTRAINT `sales_log_ibfk_1` FOREIGN KEY (`ticket_id`) REFERENCES `session_tickets` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Ограничения внешнего ключа таблицы `session_schedule`
--
ALTER TABLE `session_schedule`
  ADD CONSTRAINT `session_schedule_ibfk_1` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Ограничения внешнего ключа таблицы `session_tickets`
--
ALTER TABLE `session_tickets`
  ADD CONSTRAINT `session_tickets_ibfk_1` FOREIGN KEY (`session_id`) REFERENCES `session_schedule` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
