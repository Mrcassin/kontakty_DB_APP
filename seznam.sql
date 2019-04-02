-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Počítač: 127.0.0.1
-- Vytvořeno: Pát 29. bře 2019, 15:11
-- Verze serveru: 10.1.36-MariaDB
-- Verze PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáze: `kontakty`
--

-- --------------------------------------------------------

--
-- Struktura tabulky `seznam`
--

CREATE TABLE `seznam` (
  `id` int(10) UNSIGNED NOT NULL,
  `jmeno` varchar(30) COLLATE utf8_czech_ci NOT NULL,
  `prijmeni` varchar(30) COLLATE utf8_czech_ci NOT NULL,
  `cislo` int(10) UNSIGNED NOT NULL,
  `email` varchar(50) COLLATE utf8_czech_ci DEFAULT NULL,
  `narozeni` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `seznam`
--

INSERT INTO `seznam` (`id`, `jmeno`, `prijmeni`, `cislo`, `email`, `narozeni`) VALUES
(8, 'Daenerys', 'Targaryen', 155, 'daenerys.targaryen@got.com', '1986-10-28'),
(9, 'Jon', 'Snow', 150, 'jon.snow@got.com', '1986-12-26'),
(10, 'Arya', 'Stark', 112, 'arya.stark@got.com', '1997-04-15'),
(11, 'Tyrion', 'Lannister', 158, 'tyrion.lannister@got.com', '1969-06-11');

--
-- Klíče pro exportované tabulky
--

--
-- Klíče pro tabulku `seznam`
--
ALTER TABLE `seznam`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pro tabulky
--

--
-- AUTO_INCREMENT pro tabulku `seznam`
--
ALTER TABLE `seznam`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
