-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jul 17, 2022 at 02:24 AM
-- Server version: 10.3.35-MariaDB-cll-lve
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quailakr_ocar795`
--

-- --------------------------------------------------------

--
-- Table structure for table `features`
--

CREATE TABLE `features` (
  `id` int(11) NOT NULL,
  `feature_eng` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `feature_arabic` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `cart_id` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `features`
--

INSERT INTO `features` (`id`, `feature_eng`, `feature_arabic`, `cart_id`) VALUES
(1, 'Control inside home', NULL, NULL),
(2, 'Control from anywhere in the world', NULL, NULL),
(3, 'Moods Setup Date/Time', NULL, NULL),
(4, 'Power Consumption', NULL, NULL),
(5, 'User Rights For Family Memebers', NULL, NULL),
(7, 'اردو', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `features_subscription`
--

CREATE TABLE `features_subscription` (
  `id` int(11) NOT NULL,
  `feature_id` int(11) DEFAULT NULL,
  `subscription_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `features_subscription`
--

INSERT INTO `features_subscription` (`id`, `feature_id`, `subscription_id`) VALUES
(3, 2, 3),
(2, 2, 1),
(1, 1, 1),
(4, 3, 1),
(5, 3, 2),
(6, 3, 3),
(7, 3, 5),
(8, 4, 1),
(9, 4, 2),
(10, 4, 3),
(11, 4, 4),
(12, 4, 5);

-- --------------------------------------------------------

--
-- Table structure for table `mac_inventory`
--

CREATE TABLE `mac_inventory` (
  `id` int(10) NOT NULL,
  `product_id` int(10) NOT NULL,
  `mac_address` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mac_inventory`
--

INSERT INTO `mac_inventory` (`id`, `product_id`, `mac_address`) VALUES
(56, 54, '7abc'),
(54, 52, '5abc'),
(57, 50, 'rty8'),
(58, 51, 'mnt3'),
(59, 51, 'pqe3'),
(55, 53, '6abc'),
(53, 51, '4abc'),
(52, 50, '3abc'),
(60, 50, 'plm2'),
(61, 50, 'plm3'),
(62, 50, 'plm4'),
(63, 50, 'plm5'),
(64, 51, 'okn2'),
(65, 51, 'okn3'),
(66, 51, 'okn4'),
(67, 51, 'okn5'),
(68, 53, 'qaz1'),
(69, 53, 'qaz2'),
(70, 53, 'qaz3'),
(71, 53, 'qaz4'),
(72, 52, 'uhb2'),
(73, 52, 'uhb3'),
(74, 52, 'uhb4'),
(75, 51, 'ygv2'),
(76, 51, 'ygv3'),
(77, 51, 'ygv4'),
(78, 50, '22222222'),
(82, 50, '10B65603'),
(80, 50, '33333333'),
(83, 50, 'pp'),
(84, 51, 'sss'),
(85, 53, 'hhhhh'),
(86, 51, 'fff'),
(87, 53, 'ooo'),
(88, 50, 'DB876703'),
(89, 51, '0A581C04'),
(90, 53, 'DB8AC005'),
(91, 54, 'DB8E4D02'),
(92, 51, 'wer4'),
(93, 54, 'qwe3'),
(94, 54, 'DB8E4602'),
(95, 54, 'asd2'),
(96, 54, 'asd4');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_address`
--

CREATE TABLE `ocn8_address` (
  `address_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `firstname` varchar(32) NOT NULL,
  `lastname` varchar(32) NOT NULL,
  `company` varchar(40) NOT NULL,
  `address_1` varchar(128) NOT NULL,
  `address_2` varchar(128) NOT NULL,
  `city` varchar(128) NOT NULL,
  `postcode` varchar(10) NOT NULL,
  `country_id` int(11) NOT NULL DEFAULT 0,
  `zone_id` int(11) NOT NULL DEFAULT 0,
  `custom_field` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_address`
--

INSERT INTO `ocn8_address` (`address_id`, `customer_id`, `firstname`, `lastname`, `company`, `address_1`, `address_2`, `city`, `postcode`, `country_id`, `zone_id`, `custom_field`) VALUES
(1, 1, 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 162, 2459, ''),
(2, 2, 'Sajjad', 'Akbar', '', '121 D block PCSIR staff colony', '', 'Lahore', '54000', 162, 2461, ''),
(3, 3, 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 184, 2879, ''),
(4, 4, 'Custo', '1', '', '15-b', '', 'Islamabad', '46000', 162, 2459, ''),
(5, 8, 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 162, 2459, ''),
(6, 9, 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 162, 2459, ''),
(7, 10, 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 162, 2459, ''),
(8, 21, 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 162, 2459, ''),
(9, 31, 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 162, 2459, ''),
(10, 32, 'Bushra', 'khalid', '', 'Johar town lahore', '', 'lahore', '', 162, 2461, ''),
(11, 37, 'Testing', 'Testing', '', 'H#1', '', 'Islamabad', '', 162, 2458, ''),
(12, 39, 'Check', 'Check', '', 'Check', '', 'Check', '', 162, 2458, ''),
(13, 40, 'Test', 'Test', '', 'Test', '', 'Test', '', 162, 2458, ''),
(14, 41, 'Hecta', 'pecta', '', 'hecta', '', 'ads', '', 162, 2458, ''),
(15, 42, 'Check', 'Check', '', 'Check', '', 'Check', '', 162, 2458, ''),
(16, 43, 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 162, 2461, ''),
(17, 45, 'Ayesga', 'test', '', 'Johar town lahore', '', 'lahore', '', 162, 2461, ''),
(18, 47, 'SIT', 'Livixa', '', '15-b', '', 'Islamabad', '46000', 162, 2459, ''),
(19, 48, 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 162, 2459, ''),
(20, 49, 'SIT', 'Farrukh', '', '15-b', '', 'Islamabad', '46000', 162, 2459, ''),
(21, 50, 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 162, 2459, ''),
(22, 51, 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 162, 2459, ''),
(23, 52, 'Livixa', 'SIT', '', '15-b', '', 'Islamabad', '46000', 162, 2459, ''),
(24, 53, 'Livxa', 'SIT', '', '15-b', '', 'Islamabad', '46000', 162, 2459, ''),
(25, 54, 'check', 'cehck', '', '1123', '', 'Islamabad', '', 162, 2459, ''),
(26, 55, 'Livixa', 'Final SIT', '', 'h1 s1', '', 'Islamabad', '', 162, 2459, ''),
(27, 56, 'Sit', 'Livixa Finalv2', '', 'h1 s1', '', 'Islamabad', '', 162, 2459, ''),
(28, 57, 'Livixa', 'SIT', '', 'h1 s1', '', 'Islamabad', '', 162, 2459, ''),
(29, 58, 'Livix', 'SIT', '', 'h1 s1', '', 'Islamabad', '', 162, 2459, ''),
(30, 59, 'Liv', 'xa', '', '15-b', '', 'Islamabad', '46000', 162, 2459, ''),
(31, 60, 'Livixa', 'SIT', '', '15-b', '', 'Islamabad', '46000', 162, 2459, ''),
(32, 63, 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 162, 2460, ''),
(33, 64, 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 184, 2877, ''),
(34, 43, 'bushra', 'khalid', 'eded', 'dredre', '', 'lahore', '', 162, 2461, ''),
(35, 65, 'Bushra', 'Khalid', '', 'Faisal colony no 2 House no 169 okara', '', 'Okara', '56300', 162, 2461, ''),
(36, 66, 'Bushra', 'Khalid', '', 'Faisal colony no 2 House no 169 okara', '', 'Okara', '56300', 162, 2461, ''),
(37, 67, 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 162, 2458, ''),
(38, 68, 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 162, 2458, ''),
(39, 69, 'Maliha', 'Khan', '', 'test', '', 'lahore', '', 162, 2461, ''),
(40, 70, 'Ayesha', 'Bushra', '', 'test', '', 'lahore', '', 162, 2461, ''),
(41, 71, 'Gagandeep', 'Singh', 'Webtunix AI', '#283 AKS colony Zirakpur', '', 'MOhali', '140603', 99, 1500, ''),
(42, 72, 'Aastha', 'sharma', '', 'house 273', '', 'MOhali', '', 99, 1500, '');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_api`
--

CREATE TABLE `ocn8_api` (
  `api_id` int(11) NOT NULL,
  `username` varchar(64) NOT NULL,
  `key` text NOT NULL,
  `status` tinyint(1) NOT NULL,
  `date_added` datetime NOT NULL,
  `date_modified` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_api`
--

INSERT INTO `ocn8_api` (`api_id`, `username`, `key`, `status`, `date_added`, `date_modified`) VALUES
(1, 'Default', 'xghGFie3ARvjdP0K2nS9ddCayl8VGIrIlX91ge0oDmTrFgituZu1ThsJ1pm1lG7euqIxnzUgrvx75KwcaMaKxPeW8ZDIdu443hIHO9yAMC3wB7UE5WrtEZlqh7bdWwsCjPE93DZ32R8CiFJWvCRzjD9Yenz3fCdX7gNKKNnKKVYcgxxSlKp8zIzxXNNh6ae1LmDWnpRuJvt1CdH6QIsE93SMe9ztyF5PcumMottkyhXivn4fjfoo83xYJiVTosMP', 1, '2022-06-01 08:53:41', '2022-06-01 08:53:41');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_api_ip`
--

CREATE TABLE `ocn8_api_ip` (
  `api_ip_id` int(11) NOT NULL,
  `api_id` int(11) NOT NULL,
  `ip` varchar(40) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_api_ip`
--

INSERT INTO `ocn8_api_ip` (`api_ip_id`, `api_id`, `ip`) VALUES
(1, 1, '119.160.98.228'),
(2, 1, '72.255.38.189'),
(3, 1, '103.83.89.155'),
(4, 1, '119.160.69.190'),
(5, 1, '119.73.124.141'),
(6, 1, '188.54.236.144'),
(7, 1, '119.73.124.240'),
(8, 1, '119.73.124.197'),
(9, 1, '119.160.97.158'),
(10, 1, '119.160.96.99'),
(11, 1, '58.27.236.26'),
(12, 1, '210.2.138.70'),
(13, 1, '119.160.99.206'),
(14, 1, '119.160.98.193'),
(15, 1, '188.50.139.79'),
(16, 1, '119.160.97.194'),
(17, 1, '119.160.97.193'),
(18, 1, '119.160.97.101'),
(19, 1, '119.160.2.78'),
(20, 1, '2.89.27.140'),
(21, 1, '51.253.78.85'),
(22, 1, '119.73.124.43'),
(23, 1, '223.178.213.7');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_api_session`
--

CREATE TABLE `ocn8_api_session` (
  `api_session_id` int(11) NOT NULL,
  `api_id` int(11) NOT NULL,
  `session_id` varchar(32) NOT NULL,
  `ip` varchar(40) NOT NULL,
  `date_added` datetime NOT NULL,
  `date_modified` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_api_session`
--

INSERT INTO `ocn8_api_session` (`api_session_id`, `api_id`, `session_id`, `ip`, `date_added`, `date_modified`) VALUES
(944, 1, 'fd82d5797dc9bb96007a0296f9', '223.178.213.7', '2022-07-15 13:21:46', '2022-07-15 13:21:46'),
(943, 1, '91f477c557cbb3e27803dbf438', '223.178.213.7', '2022-07-15 13:21:13', '2022-07-15 13:21:13'),
(942, 1, '3502309002b0e80523171b32e0', '223.178.213.7', '2022-07-15 13:20:48', '2022-07-15 13:21:05'),
(941, 1, 'bfe998480394bca0b50acbc0ab', '223.178.213.7', '2022-07-15 13:19:48', '2022-07-15 13:19:48'),
(940, 1, 'b5490607364022dcca335125b0', '223.178.213.7', '2022-07-15 13:19:40', '2022-07-15 13:19:40'),
(939, 1, '9528f271866b983d641ca397cc', '223.178.213.7', '2022-07-15 13:19:26', '2022-07-15 13:19:26'),
(938, 1, '55a00aa7311bf4f41808b696e7', '223.178.213.7', '2022-07-15 13:11:36', '2022-07-15 13:11:36'),
(937, 1, 'b217a9bc16b5219545b0308fc7', '223.178.213.7', '2022-07-15 13:11:18', '2022-07-15 13:11:18'),
(936, 1, '3842a535208506e3a9c486fe1c', '223.178.213.7', '2022-07-15 13:11:11', '2022-07-15 13:11:18'),
(933, 1, 'fe96c68f7116594a04bb7fda16', '223.178.213.7', '2022-07-15 13:09:44', '2022-07-15 13:09:44'),
(934, 1, '4ac0f010bceacebf598aac21c2', '223.178.213.7', '2022-07-15 13:09:58', '2022-07-15 13:09:58'),
(935, 1, '55f15fe9d200e60356e43647cf', '223.178.213.7', '2022-07-15 13:10:07', '2022-07-15 13:10:07'),
(962, 1, 'e0dbd5ac46ecf35e86b6c348b1', '223.178.213.7', '2022-07-15 13:30:24', '2022-07-15 13:30:24'),
(961, 1, '7ef465bfded8d8be43279358a1', '223.178.213.7', '2022-07-15 13:29:31', '2022-07-15 13:29:31'),
(960, 1, '7d4a488f3c9427a26fd1d3c95b', '223.178.213.7', '2022-07-15 13:29:18', '2022-07-15 13:29:18'),
(959, 1, '07c6135803be281102fca1f291', '223.178.213.7', '2022-07-15 13:29:03', '2022-07-15 13:29:03'),
(958, 1, '09644fa578d9fdb200073fd14f', '223.178.213.7', '2022-07-15 13:28:43', '2022-07-15 13:28:54'),
(955, 1, 'f7b4cea2a3db4887bd46b8307d', '223.178.213.7', '2022-07-15 13:26:51', '2022-07-15 13:28:10'),
(954, 1, 'bfee2f453c83d8834eb9f887a1', '223.178.213.7', '2022-07-15 13:26:48', '2022-07-15 13:26:48'),
(953, 1, '19d05d85cba06219b0199b826e', '223.178.213.7', '2022-07-15 13:26:45', '2022-07-15 13:26:45'),
(952, 1, '812586c84c9f704895734ba00f', '223.178.213.7', '2022-07-15 13:26:36', '2022-07-15 13:26:36'),
(951, 1, '4bd9971fe313a87e506d51cde2', '223.178.213.7', '2022-07-15 13:26:20', '2022-07-15 13:26:20'),
(946, 1, '74ccc652b000071d40ae93146f', '223.178.213.7', '2022-07-15 13:22:31', '2022-07-15 13:22:31'),
(947, 1, '24250bb9edb0f2dd37709ff89a', '223.178.213.7', '2022-07-15 13:23:51', '2022-07-15 13:24:33'),
(950, 1, 'a860f4899140b102f1e6e4748a', '223.178.213.7', '2022-07-15 13:25:04', '2022-07-15 13:25:04'),
(945, 1, 'c9d0376b744ba6edcff7767bb3', '223.178.213.7', '2022-07-15 13:21:59', '2022-07-15 13:22:22'),
(930, 1, '7d1c4a53d114cf589f79fdf2e5', '223.178.213.7', '2022-07-15 13:09:02', '2022-07-15 13:09:02'),
(929, 1, 'fd63a77c89926a8389dc4b76bc', '223.178.213.7', '2022-07-15 13:08:29', '2022-07-15 13:08:29'),
(928, 1, '1d11aa0ba46b4c6ee7d3662978', '223.178.213.7', '2022-07-15 13:08:24', '2022-07-15 13:08:24'),
(901, 1, '400b367dd4cb6812e595c0d599', '223.178.213.7', '2022-07-15 12:45:34', '2022-07-15 12:45:34'),
(957, 1, '7328195eff8b7db22487aa0341', '223.178.213.7', '2022-07-15 13:28:20', '2022-07-15 13:28:20'),
(956, 1, '41504228868c1caff68589fa9a', '223.178.213.7', '2022-07-15 13:28:17', '2022-07-15 13:28:17'),
(949, 1, '7d94772ad79af7cc655b34b1d7', '223.178.213.7', '2022-07-15 13:25:01', '2022-07-15 13:25:01'),
(948, 1, '48f6d9ff18b1befb46453abf80', '223.178.213.7', '2022-07-15 13:24:42', '2022-07-15 13:24:42'),
(917, 1, '8bdc4f426417ee69f28c8b308f', '223.178.213.7', '2022-07-15 13:01:49', '2022-07-15 13:01:49'),
(918, 1, '81e0b5844c5c751ead670668ef', '223.178.213.7', '2022-07-15 13:02:02', '2022-07-15 13:02:02'),
(919, 1, '70688bf29e0ffa6bc4e20b0f06', '223.178.213.7', '2022-07-15 13:02:25', '2022-07-15 13:02:44'),
(920, 1, 'c9ba41ad7298e83d05d87a31cf', '223.178.213.7', '2022-07-15 13:02:49', '2022-07-15 13:02:49'),
(921, 1, '5f9e6ddf4a2712b9dced90a510', '223.178.213.7', '2022-07-15 13:02:58', '2022-07-15 13:03:23'),
(922, 1, 'f843190151fe7993801f2d3136', '223.178.213.7', '2022-07-15 13:03:34', '2022-07-15 13:03:34'),
(923, 1, '3c2cc64652ad8b9e47d7dbf717', '223.178.213.7', '2022-07-15 13:04:17', '2022-07-15 13:04:17'),
(924, 1, '99c640e85ddf4bfcdb86479540', '223.178.213.7', '2022-07-15 13:04:45', '2022-07-15 13:04:45'),
(915, 1, '90b04f67a6458b19a5c47902a7', '223.178.213.7', '2022-07-15 12:57:39', '2022-07-15 12:57:39'),
(916, 1, '0c6d4a0d48756954db0e0b85b2', '223.178.213.7', '2022-07-15 12:57:47', '2022-07-15 13:01:40'),
(925, 1, 'a4a902539613029ae76954fe84', '223.178.213.7', '2022-07-15 13:04:59', '2022-07-15 13:04:59'),
(926, 1, '9ac237f9dc5258edbc7b946cd2', '223.178.213.7', '2022-07-15 13:05:26', '2022-07-15 13:05:26'),
(927, 1, 'd2ba50ab6e833c851c3b3b07c3', '223.178.213.7', '2022-07-15 13:05:35', '2022-07-15 13:05:35'),
(932, 1, '29ca1c5955fb6ac842b3ffd8c4', '223.178.213.7', '2022-07-15 13:09:23', '2022-07-15 13:09:44'),
(931, 1, '499c86551588faf4a8bb3ad260', '223.178.213.7', '2022-07-15 13:09:17', '2022-07-15 13:09:17'),
(914, 1, '20aae1fef767cbbeb865400f3b', '223.178.213.7', '2022-07-15 12:57:13', '2022-07-15 12:57:13'),
(913, 1, '24650ba6430d76130f8c1a962d', '223.178.213.7', '2022-07-15 12:49:56', '2022-07-15 12:51:31'),
(912, 1, '34143f2d4a907f2297a15827aa', '223.178.213.7', '2022-07-15 12:49:19', '2022-07-15 12:49:19'),
(911, 1, 'f2e872fe3d13573c6fcf321a3c', '223.178.213.7', '2022-07-15 12:48:50', '2022-07-15 12:48:50'),
(910, 1, 'fe100f9a1f5df37f463e0f7c1c', '223.178.213.7', '2022-07-15 12:47:47', '2022-07-15 12:47:47'),
(909, 1, 'c43b19fa3b473e1cda5079a170', '223.178.213.7', '2022-07-15 12:47:28', '2022-07-15 12:47:28'),
(908, 1, 'e192d02ddbadb2651a9ca91d15', '223.178.213.7', '2022-07-15 12:47:11', '2022-07-15 12:47:11'),
(907, 1, 'dc02c51d29cd4131ccf1c0b0c8', '223.178.213.7', '2022-07-15 12:47:08', '2022-07-15 12:47:08'),
(906, 1, '0e27ca38c76593027ec9f79f5d', '223.178.213.7', '2022-07-15 12:46:30', '2022-07-15 12:46:38'),
(905, 1, 'ca022cd3d3e7066154c6812b6e', '223.178.213.7', '2022-07-15 12:46:21', '2022-07-15 12:46:21'),
(904, 1, 'f761108ae8d62c9e239ebeae0e', '223.178.213.7', '2022-07-15 12:46:17', '2022-07-15 12:46:17'),
(903, 1, 'b3fdc4a2d6b8a7d5b419b0e928', '223.178.213.7', '2022-07-15 12:46:13', '2022-07-15 12:46:13'),
(902, 1, '203d9e80b8464309bac2abaa6e', '223.178.213.7', '2022-07-15 12:45:38', '2022-07-15 12:45:38');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_attribute`
--

CREATE TABLE `ocn8_attribute` (
  `attribute_id` int(11) NOT NULL,
  `attribute_group_id` int(11) NOT NULL,
  `sort_order` int(3) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_attribute`
--

INSERT INTO `ocn8_attribute` (`attribute_id`, `attribute_group_id`, `sort_order`) VALUES
(15, 7, 4),
(14, 7, 3),
(13, 7, 2),
(12, 7, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_attribute_description`
--

CREATE TABLE `ocn8_attribute_description` (
  `attribute_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_attribute_description`
--

INSERT INTO `ocn8_attribute_description` (`attribute_id`, `language_id`, `name`) VALUES
(13, 2, 'قراءة استهلاك الطاقة'),
(13, 1, 'Power Consumption Reading'),
(12, 2, 'التحكم عن بعد'),
(15, 2, 'إعطاء الصلاحيات '),
(15, 1, 'User Rights'),
(12, 1, 'Control Remotely'),
(14, 1, 'Moods &amp; Lifestyle'),
(14, 2, 'الأوضاع ونمط الحياة');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_attribute_group`
--

CREATE TABLE `ocn8_attribute_group` (
  `attribute_group_id` int(11) NOT NULL,
  `sort_order` int(3) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_attribute_group`
--

INSERT INTO `ocn8_attribute_group` (`attribute_group_id`, `sort_order`) VALUES
(3, 2),
(4, 1),
(5, 3),
(6, 4),
(7, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_attribute_group_description`
--

CREATE TABLE `ocn8_attribute_group_description` (
  `attribute_group_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_attribute_group_description`
--

INSERT INTO `ocn8_attribute_group_description` (`attribute_group_id`, `language_id`, `name`) VALUES
(3, 1, 'Memory'),
(4, 1, 'Technical'),
(5, 1, 'Motherboard'),
(6, 1, 'Processor'),
(3, 2, 'Memory'),
(4, 2, 'Technical'),
(5, 2, 'Motherboard'),
(6, 2, 'Processor'),
(7, 1, 'Key features'),
(7, 2, 'دلائل الميزات');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_banner`
--

CREATE TABLE `ocn8_banner` (
  `banner_id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `status` tinyint(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_banner`
--

INSERT INTO `ocn8_banner` (`banner_id`, `name`, `status`) VALUES
(6, 'HP Products', 0),
(7, 'Home Page Slideshow', 1),
(8, 'Manufacturers', 0);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_banner_image`
--

CREATE TABLE `ocn8_banner_image` (
  `banner_image_id` int(11) NOT NULL,
  `banner_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `title` varchar(64) NOT NULL,
  `link` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `sort_order` int(3) NOT NULL DEFAULT 0
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_banner_image`
--

INSERT INTO `ocn8_banner_image` (`banner_image_id`, `banner_id`, `language_id`, `title`, `link`, `image`, `sort_order`) VALUES
(155, 7, 1, 'Livixa', '', 'catalog/slide 1.PNG', 2),
(156, 7, 2, 'Livixa', '', 'catalog/demo/banners/banner_arabic.png', 0),
(114, 6, 2, 'HP Banner', 'index.php?route=product/manufacturer/info&amp;manufacturer_id=7', 'catalog/demo/compaq_presario.jpg', 0),
(149, 8, 2, 'Starbucks', '', 'catalog/demo/manufacturer/starbucks.png', 0),
(148, 8, 2, 'Disney', '', 'catalog/demo/manufacturer/disney.png', 0),
(147, 8, 2, 'Dell', '', 'catalog/demo/manufacturer/dell.png', 0),
(146, 8, 2, 'Harley Davidson', '', 'catalog/demo/manufacturer/harley.png', 0),
(145, 8, 2, 'Canon', '', 'catalog/demo/manufacturer/canon.png', 0),
(144, 8, 2, 'Burger King', '', 'catalog/demo/manufacturer/burgerking.png', 0),
(142, 8, 2, 'Sony', '', 'catalog/demo/manufacturer/sony.png', 0),
(143, 8, 2, 'Coca Cola', '', 'catalog/demo/manufacturer/cocacola.png', 0),
(139, 8, 1, 'Nintendo', '', 'catalog/demo/manufacturer/nintendo.png', 0),
(140, 8, 2, 'NFL', '', 'catalog/demo/manufacturer/nfl.png', 0),
(141, 8, 2, 'RedBull', '', 'catalog/demo/manufacturer/redbull.png', 0),
(113, 6, 1, 'HP Banner', 'index.php?route=product/manufacturer/info&amp;manufacturer_id=7', 'catalog/demo/compaq_presario.jpg', 0),
(138, 8, 1, 'Starbucks', '', 'catalog/demo/manufacturer/starbucks.png', 0),
(137, 8, 1, 'Disney', '', 'catalog/demo/manufacturer/disney.png', 0),
(136, 8, 1, 'Dell', '', 'catalog/demo/manufacturer/dell.png', 0),
(135, 8, 1, 'Harley Davidson', '', 'catalog/demo/manufacturer/harley.png', 0),
(134, 8, 1, 'Canon', '', 'catalog/demo/manufacturer/canon.png', 0),
(133, 8, 1, 'Burger King', '', 'catalog/demo/manufacturer/burgerking.png', 0),
(131, 8, 1, 'Sony', '', 'catalog/demo/manufacturer/sony.png', 0),
(132, 8, 1, 'Coca Cola', '', 'catalog/demo/manufacturer/cocacola.png', 0),
(130, 8, 1, 'RedBull', '', 'catalog/demo/manufacturer/redbull.png', 0),
(129, 8, 1, 'NFL', '', 'catalog/demo/manufacturer/nfl.png', 0),
(154, 7, 1, 'Livixa', '', 'catalog/demo/banners/banner_eng.png', 1),
(150, 8, 2, 'Nintendo', '', 'catalog/demo/manufacturer/nintendo.png', 0);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_cart`
--

CREATE TABLE `ocn8_cart` (
  `cart_id` int(11) UNSIGNED NOT NULL,
  `api_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `session_id` varchar(32) NOT NULL,
  `product_id` int(11) NOT NULL,
  `recurring_id` int(11) NOT NULL,
  `option` text NOT NULL,
  `quantity` int(5) NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_cart`
--

INSERT INTO `ocn8_cart` (`cart_id`, `api_id`, `customer_id`, `session_id`, `product_id`, `recurring_id`, `option`, `quantity`, `date_added`) VALUES
(23, 0, 1, '019efcaadd870c99f803812537', 50, 0, '[]', 1, '2022-06-09 15:20:56'),
(34, 0, 8, '23e087caac2e423f4f18f5eced', 50, 0, '[]', 1, '2022-06-13 11:36:23'),
(36, 0, 9, '23e087caac2e423f4f18f5eced', 50, 0, '[]', 1, '2022-06-13 11:45:24'),
(47, 0, 21, '23e087caac2e423f4f18f5eced', 50, 0, '[]', 1, '2022-06-13 14:06:44'),
(52, 0, 2, '1150e908f967f6cd67d452365c', 50, 0, '[]', 1, '2022-06-14 05:07:07'),
(55, 0, 31, '23e087caac2e423f4f18f5eced', 50, 0, '[]', 1, '2022-06-14 09:49:14'),
(63, 0, 32, '01002dc4c7ae012c51f68e3ba1', 51, 0, '{\"228\":\"30\"}', 1, '2022-06-15 14:38:03'),
(66, 0, 37, '773e2f71ce82b83ba5b257a477', 50, 0, '{\"232\":\"67\"}', 1, '2022-06-16 11:28:33'),
(68, 0, 39, '773e2f71ce82b83ba5b257a477', 50, 0, '{\"232\":\"70\"}', 1, '2022-06-16 11:34:43'),
(72, 0, 41, '773e2f71ce82b83ba5b257a477', 53, 0, '{\"230\":\"50\"}', 1, '2022-06-16 11:47:59'),
(77, 0, 32, '01002dc4c7ae012c51f68e3ba1', 50, 0, '{\"232\":\"67\"}', 2, '2022-06-16 12:34:39'),
(89, 0, 45, '01002dc4c7ae012c51f68e3ba1', 50, 0, '{\"232\":\"67\"}', 1, '2022-06-17 11:12:49'),
(91, 0, 45, '01002dc4c7ae012c51f68e3ba1', 53, 0, '{\"230\":\"48\"}', 1, '2022-06-17 11:16:07'),
(99, 0, 47, '08ee94a069d5233af373376e12', 50, 0, '{\"232\":\"69\"}', 5, '2022-06-18 12:27:06'),
(100, 0, 47, '08ee94a069d5233af373376e12', 53, 0, '{\"230\":\"56\"}', 2, '2022-06-18 12:27:29'),
(118, 0, 51, '86b7aeb824db683ca69e05b037', 50, 0, '{\"232\":\"71\"}', 2, '2022-06-19 14:31:02'),
(131, 0, 54, 'afacaffee95eea38ff5ff3a04f', 50, 0, '{\"232\":\"69\"}', 1, '2022-06-20 12:20:01'),
(143, 0, 48, 'ceb12f7d980bb1ce63b3a69ee9', 50, 0, '{\"232\":\"68\"}', 1, '2022-06-21 12:43:15'),
(150, 0, 63, 'd74a42c565f6a3b5739c9e2d34', 53, 0, '{\"230\":\"50\"}', 1, '2022-06-22 11:38:11'),
(154, 0, 3, 'e91dfd78a9f99dbad00a9fc19d', 53, 0, '{\"230\":\"52\"}', 1, '2022-06-22 11:45:42'),
(177, 0, 65, '5959daee9a6eb4cd0c69466a82', 50, 0, '{\"232\":\"75\"}', 1, '2022-07-03 12:47:34'),
(192, 0, 3, 'e91dfd78a9f99dbad00a9fc19d', 50, 0, '{\"232\":\"68\"}', 1, '2022-07-09 00:43:35'),
(206, 0, 43, 'a5660009259187813b32a5bf02', 53, 0, '{\"230\":\"49\"}', 1, '2022-07-14 07:15:51'),
(224, 0, 71, '915240f272837d72961162a66c', 50, 0, '{\"232\":\"75\"}', 1, '2022-07-15 11:08:58'),
(225, 0, 71, '915240f272837d72961162a66c', 54, 0, '{\"231\":\"66\"}', 1, '2022-07-15 11:09:25'),
(232, 0, 72, 'f7b4cea2a3db4887bd46b8307d', 54, 0, '{\"231\":\"66\"}', 1, '2022-07-15 13:14:59');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_category`
--

CREATE TABLE `ocn8_category` (
  `category_id` int(11) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `parent_id` int(11) NOT NULL DEFAULT 0,
  `top` tinyint(1) NOT NULL,
  `column` int(3) NOT NULL,
  `sort_order` int(3) NOT NULL DEFAULT 0,
  `status` tinyint(1) NOT NULL,
  `date_added` datetime NOT NULL,
  `date_modified` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_category`
--

INSERT INTO `ocn8_category` (`category_id`, `image`, `parent_id`, `top`, `column`, `sort_order`, `status`, `date_added`, `date_modified`) VALUES
(59, '', 0, 1, 1, 1, 1, '2022-06-01 14:22:08', '2022-06-01 14:47:01');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_category_description`
--

CREATE TABLE `ocn8_category_description` (
  `category_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `meta_title` varchar(255) NOT NULL,
  `meta_description` varchar(255) NOT NULL,
  `meta_keyword` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_category_description`
--

INSERT INTO `ocn8_category_description` (`category_id`, `language_id`, `name`, `description`, `meta_title`, `meta_description`, `meta_keyword`) VALUES
(59, 1, 'Smart Home', '&lt;p&gt;Smart home&lt;/p&gt;', 'Smart Home', '', ''),
(59, 2, 'المنزل الذكي', '&lt;p&gt;المنزل الذكي&lt;br&gt;&lt;/p&gt;', 'المنزل الذكي', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_category_filter`
--

CREATE TABLE `ocn8_category_filter` (
  `category_id` int(11) NOT NULL,
  `filter_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_category_path`
--

CREATE TABLE `ocn8_category_path` (
  `category_id` int(11) NOT NULL,
  `path_id` int(11) NOT NULL,
  `level` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_category_path`
--

INSERT INTO `ocn8_category_path` (`category_id`, `path_id`, `level`) VALUES
(59, 59, 0);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_category_to_layout`
--

CREATE TABLE `ocn8_category_to_layout` (
  `category_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  `layout_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_category_to_layout`
--

INSERT INTO `ocn8_category_to_layout` (`category_id`, `store_id`, `layout_id`) VALUES
(59, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_category_to_store`
--

CREATE TABLE `ocn8_category_to_store` (
  `category_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_category_to_store`
--

INSERT INTO `ocn8_category_to_store` (`category_id`, `store_id`) VALUES
(59, 0);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_country`
--

CREATE TABLE `ocn8_country` (
  `country_id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL,
  `iso_code_2` varchar(2) NOT NULL,
  `iso_code_3` varchar(3) NOT NULL,
  `address_format` text NOT NULL,
  `postcode_required` tinyint(1) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_country`
--

INSERT INTO `ocn8_country` (`country_id`, `name`, `iso_code_2`, `iso_code_3`, `address_format`, `postcode_required`, `status`) VALUES
(1, 'Afghanistan', 'AF', 'AFG', '', 0, 1),
(2, 'Albania', 'AL', 'ALB', '', 0, 1),
(3, 'Algeria', 'DZ', 'DZA', '', 0, 1),
(4, 'American Samoa', 'AS', 'ASM', '', 0, 1),
(5, 'Andorra', 'AD', 'AND', '', 0, 1),
(6, 'Angola', 'AO', 'AGO', '', 0, 1),
(7, 'Anguilla', 'AI', 'AIA', '', 0, 1),
(8, 'Antarctica', 'AQ', 'ATA', '', 0, 1),
(9, 'Antigua and Barbuda', 'AG', 'ATG', '', 0, 1),
(10, 'Argentina', 'AR', 'ARG', '', 0, 1),
(11, 'Armenia', 'AM', 'ARM', '', 0, 1),
(12, 'Aruba', 'AW', 'ABW', '', 0, 1),
(13, 'Australia', 'AU', 'AUS', '', 0, 1),
(14, 'Austria', 'AT', 'AUT', '', 0, 1),
(15, 'Azerbaijan', 'AZ', 'AZE', '', 0, 1),
(16, 'Bahamas', 'BS', 'BHS', '', 0, 1),
(17, 'Bahrain', 'BH', 'BHR', '', 0, 1),
(18, 'Bangladesh', 'BD', 'BGD', '', 0, 1),
(19, 'Barbados', 'BB', 'BRB', '', 0, 1),
(20, 'Belarus', 'BY', 'BLR', '', 0, 1),
(21, 'Belgium', 'BE', 'BEL', '{firstname} {lastname}\r\n{company}\r\n{address_1}\r\n{address_2}\r\n{postcode} {city}\r\n{country}', 0, 1),
(22, 'Belize', 'BZ', 'BLZ', '', 0, 1),
(23, 'Benin', 'BJ', 'BEN', '', 0, 1),
(24, 'Bermuda', 'BM', 'BMU', '', 0, 1),
(25, 'Bhutan', 'BT', 'BTN', '', 0, 1),
(26, 'Bolivia', 'BO', 'BOL', '', 0, 1),
(27, 'Bosnia and Herzegovina', 'BA', 'BIH', '', 0, 1),
(28, 'Botswana', 'BW', 'BWA', '', 0, 1),
(29, 'Bouvet Island', 'BV', 'BVT', '', 0, 1),
(30, 'Brazil', 'BR', 'BRA', '', 0, 1),
(31, 'British Indian Ocean Territory', 'IO', 'IOT', '', 0, 1),
(32, 'Brunei Darussalam', 'BN', 'BRN', '', 0, 1),
(33, 'Bulgaria', 'BG', 'BGR', '', 0, 1),
(34, 'Burkina Faso', 'BF', 'BFA', '', 0, 1),
(35, 'Burundi', 'BI', 'BDI', '', 0, 1),
(36, 'Cambodia', 'KH', 'KHM', '', 0, 1),
(37, 'Cameroon', 'CM', 'CMR', '', 0, 1),
(38, 'Canada', 'CA', 'CAN', '', 0, 1),
(39, 'Cape Verde', 'CV', 'CPV', '', 0, 1),
(40, 'Cayman Islands', 'KY', 'CYM', '', 0, 1),
(41, 'Central African Republic', 'CF', 'CAF', '', 0, 1),
(42, 'Chad', 'TD', 'TCD', '', 0, 1),
(43, 'Chile', 'CL', 'CHL', '', 0, 1),
(44, 'China', 'CN', 'CHN', '', 0, 1),
(45, 'Christmas Island', 'CX', 'CXR', '', 0, 1),
(46, 'Cocos (Keeling) Islands', 'CC', 'CCK', '', 0, 1),
(47, 'Colombia', 'CO', 'COL', '', 0, 1),
(48, 'Comoros', 'KM', 'COM', '', 0, 1),
(49, 'Congo', 'CG', 'COG', '', 0, 1),
(50, 'Cook Islands', 'CK', 'COK', '', 0, 1),
(51, 'Costa Rica', 'CR', 'CRI', '', 0, 1),
(52, 'Cote D\'Ivoire', 'CI', 'CIV', '', 0, 1),
(53, 'Croatia', 'HR', 'HRV', '', 0, 1),
(54, 'Cuba', 'CU', 'CUB', '', 0, 1),
(55, 'Cyprus', 'CY', 'CYP', '', 0, 1),
(56, 'Czech Republic', 'CZ', 'CZE', '', 0, 1),
(57, 'Denmark', 'DK', 'DNK', '', 0, 1),
(58, 'Djibouti', 'DJ', 'DJI', '', 0, 1),
(59, 'Dominica', 'DM', 'DMA', '', 0, 1),
(60, 'Dominican Republic', 'DO', 'DOM', '', 0, 1),
(61, 'East Timor', 'TL', 'TLS', '', 0, 1),
(62, 'Ecuador', 'EC', 'ECU', '', 0, 1),
(63, 'Egypt', 'EG', 'EGY', '', 0, 1),
(64, 'El Salvador', 'SV', 'SLV', '', 0, 1),
(65, 'Equatorial Guinea', 'GQ', 'GNQ', '', 0, 1),
(66, 'Eritrea', 'ER', 'ERI', '', 0, 1),
(67, 'Estonia', 'EE', 'EST', '', 0, 1),
(68, 'Ethiopia', 'ET', 'ETH', '', 0, 1),
(69, 'Falkland Islands (Malvinas)', 'FK', 'FLK', '', 0, 1),
(70, 'Faroe Islands', 'FO', 'FRO', '', 0, 1),
(71, 'Fiji', 'FJ', 'FJI', '', 0, 1),
(72, 'Finland', 'FI', 'FIN', '', 0, 1),
(74, 'France, Metropolitan', 'FR', 'FRA', '{firstname} {lastname}\r\n{company}\r\n{address_1}\r\n{address_2}\r\n{postcode} {city}\r\n{country}', 1, 1),
(75, 'French Guiana', 'GF', 'GUF', '', 0, 1),
(76, 'French Polynesia', 'PF', 'PYF', '', 0, 1),
(77, 'French Southern Territories', 'TF', 'ATF', '', 0, 1),
(78, 'Gabon', 'GA', 'GAB', '', 0, 1),
(79, 'Gambia', 'GM', 'GMB', '', 0, 1),
(80, 'Georgia', 'GE', 'GEO', '', 0, 1),
(81, 'Germany', 'DE', 'DEU', '{company}\r\n{firstname} {lastname}\r\n{address_1}\r\n{address_2}\r\n{postcode} {city}\r\n{country}', 1, 1),
(82, 'Ghana', 'GH', 'GHA', '', 0, 1),
(83, 'Gibraltar', 'GI', 'GIB', '', 0, 1),
(84, 'Greece', 'GR', 'GRC', '', 0, 1),
(85, 'Greenland', 'GL', 'GRL', '', 0, 1),
(86, 'Grenada', 'GD', 'GRD', '', 0, 1),
(87, 'Guadeloupe', 'GP', 'GLP', '', 0, 1),
(88, 'Guam', 'GU', 'GUM', '', 0, 1),
(89, 'Guatemala', 'GT', 'GTM', '', 0, 1),
(90, 'Guinea', 'GN', 'GIN', '', 0, 1),
(91, 'Guinea-Bissau', 'GW', 'GNB', '', 0, 1),
(92, 'Guyana', 'GY', 'GUY', '', 0, 1),
(93, 'Haiti', 'HT', 'HTI', '', 0, 1),
(94, 'Heard and Mc Donald Islands', 'HM', 'HMD', '', 0, 1),
(95, 'Honduras', 'HN', 'HND', '', 0, 1),
(96, 'Hong Kong', 'HK', 'HKG', '', 0, 1),
(97, 'Hungary', 'HU', 'HUN', '', 0, 1),
(98, 'Iceland', 'IS', 'ISL', '', 0, 1),
(99, 'India', 'IN', 'IND', '', 0, 1),
(100, 'Indonesia', 'ID', 'IDN', '', 0, 1),
(101, 'Iran (Islamic Republic of)', 'IR', 'IRN', '', 0, 1),
(102, 'Iraq', 'IQ', 'IRQ', '', 0, 1),
(103, 'Ireland', 'IE', 'IRL', '', 0, 1),
(104, 'Israel', 'IL', 'ISR', '', 0, 1),
(105, 'Italy', 'IT', 'ITA', '', 0, 1),
(106, 'Jamaica', 'JM', 'JAM', '', 0, 1),
(107, 'Japan', 'JP', 'JPN', '', 0, 1),
(108, 'Jordan', 'JO', 'JOR', '', 0, 1),
(109, 'Kazakhstan', 'KZ', 'KAZ', '', 0, 1),
(110, 'Kenya', 'KE', 'KEN', '', 0, 1),
(111, 'Kiribati', 'KI', 'KIR', '', 0, 1),
(112, 'North Korea', 'KP', 'PRK', '', 0, 1),
(113, 'South Korea', 'KR', 'KOR', '', 0, 1),
(114, 'Kuwait', 'KW', 'KWT', '', 0, 1),
(115, 'Kyrgyzstan', 'KG', 'KGZ', '', 0, 1),
(116, 'Lao People\'s Democratic Republic', 'LA', 'LAO', '', 0, 1),
(117, 'Latvia', 'LV', 'LVA', '', 0, 1),
(118, 'Lebanon', 'LB', 'LBN', '', 0, 1),
(119, 'Lesotho', 'LS', 'LSO', '', 0, 1),
(120, 'Liberia', 'LR', 'LBR', '', 0, 1),
(121, 'Libyan Arab Jamahiriya', 'LY', 'LBY', '', 0, 1),
(122, 'Liechtenstein', 'LI', 'LIE', '', 0, 1),
(123, 'Lithuania', 'LT', 'LTU', '', 0, 1),
(124, 'Luxembourg', 'LU', 'LUX', '', 0, 1),
(125, 'Macau', 'MO', 'MAC', '', 0, 1),
(126, 'FYROM', 'MK', 'MKD', '', 0, 1),
(127, 'Madagascar', 'MG', 'MDG', '', 0, 1),
(128, 'Malawi', 'MW', 'MWI', '', 0, 1),
(129, 'Malaysia', 'MY', 'MYS', '', 0, 1),
(130, 'Maldives', 'MV', 'MDV', '', 0, 1),
(131, 'Mali', 'ML', 'MLI', '', 0, 1),
(132, 'Malta', 'MT', 'MLT', '', 0, 1),
(133, 'Marshall Islands', 'MH', 'MHL', '', 0, 1),
(134, 'Martinique', 'MQ', 'MTQ', '', 0, 1),
(135, 'Mauritania', 'MR', 'MRT', '', 0, 1),
(136, 'Mauritius', 'MU', 'MUS', '', 0, 1),
(137, 'Mayotte', 'YT', 'MYT', '', 0, 1),
(138, 'Mexico', 'MX', 'MEX', '', 0, 1),
(139, 'Micronesia, Federated States of', 'FM', 'FSM', '', 0, 1),
(140, 'Moldova, Republic of', 'MD', 'MDA', '', 0, 1),
(141, 'Monaco', 'MC', 'MCO', '', 0, 1),
(142, 'Mongolia', 'MN', 'MNG', '', 0, 1),
(143, 'Montserrat', 'MS', 'MSR', '', 0, 1),
(144, 'Morocco', 'MA', 'MAR', '', 0, 1),
(145, 'Mozambique', 'MZ', 'MOZ', '', 0, 1),
(146, 'Myanmar', 'MM', 'MMR', '', 0, 1),
(147, 'Namibia', 'NA', 'NAM', '', 0, 1),
(148, 'Nauru', 'NR', 'NRU', '', 0, 1),
(149, 'Nepal', 'NP', 'NPL', '', 0, 1),
(150, 'Netherlands', 'NL', 'NLD', '', 0, 1),
(151, 'Netherlands Antilles', 'AN', 'ANT', '', 0, 1),
(152, 'New Caledonia', 'NC', 'NCL', '', 0, 1),
(153, 'New Zealand', 'NZ', 'NZL', '', 0, 1),
(154, 'Nicaragua', 'NI', 'NIC', '', 0, 1),
(155, 'Niger', 'NE', 'NER', '', 0, 1),
(156, 'Nigeria', 'NG', 'NGA', '', 0, 1),
(157, 'Niue', 'NU', 'NIU', '', 0, 1),
(158, 'Norfolk Island', 'NF', 'NFK', '', 0, 1),
(159, 'Northern Mariana Islands', 'MP', 'MNP', '', 0, 1),
(160, 'Norway', 'NO', 'NOR', '', 0, 1),
(161, 'Oman', 'OM', 'OMN', '', 0, 1),
(162, 'Pakistan', 'PK', 'PAK', '', 0, 1),
(163, 'Palau', 'PW', 'PLW', '', 0, 1),
(164, 'Panama', 'PA', 'PAN', '', 0, 1),
(165, 'Papua New Guinea', 'PG', 'PNG', '', 0, 1),
(166, 'Paraguay', 'PY', 'PRY', '', 0, 1),
(167, 'Peru', 'PE', 'PER', '', 0, 1),
(168, 'Philippines', 'PH', 'PHL', '', 0, 1),
(169, 'Pitcairn', 'PN', 'PCN', '', 0, 1),
(170, 'Poland', 'PL', 'POL', '', 0, 1),
(171, 'Portugal', 'PT', 'PRT', '', 0, 1),
(172, 'Puerto Rico', 'PR', 'PRI', '', 0, 1),
(173, 'Qatar', 'QA', 'QAT', '', 0, 1),
(174, 'Reunion', 'RE', 'REU', '', 0, 1),
(175, 'Romania', 'RO', 'ROM', '', 0, 1),
(176, 'Russian Federation', 'RU', 'RUS', '', 0, 1),
(177, 'Rwanda', 'RW', 'RWA', '', 0, 1),
(178, 'Saint Kitts and Nevis', 'KN', 'KNA', '', 0, 1),
(179, 'Saint Lucia', 'LC', 'LCA', '', 0, 1),
(180, 'Saint Vincent and the Grenadines', 'VC', 'VCT', '', 0, 1),
(181, 'Samoa', 'WS', 'WSM', '', 0, 1),
(182, 'San Marino', 'SM', 'SMR', '', 0, 1),
(183, 'Sao Tome and Principe', 'ST', 'STP', '', 0, 1),
(184, 'Saudi Arabia', 'SA', 'SAU', '', 0, 1),
(185, 'Senegal', 'SN', 'SEN', '', 0, 1),
(186, 'Seychelles', 'SC', 'SYC', '', 0, 1),
(187, 'Sierra Leone', 'SL', 'SLE', '', 0, 1),
(188, 'Singapore', 'SG', 'SGP', '', 0, 1),
(189, 'Slovak Republic', 'SK', 'SVK', '{firstname} {lastname}\r\n{company}\r\n{address_1}\r\n{address_2}\r\n{city} {postcode}\r\n{zone}\r\n{country}', 0, 1),
(190, 'Slovenia', 'SI', 'SVN', '', 0, 1),
(191, 'Solomon Islands', 'SB', 'SLB', '', 0, 1),
(192, 'Somalia', 'SO', 'SOM', '', 0, 1),
(193, 'South Africa', 'ZA', 'ZAF', '', 0, 1),
(194, 'South Georgia &amp; South Sandwich Islands', 'GS', 'SGS', '', 0, 1),
(195, 'Spain', 'ES', 'ESP', '', 0, 1),
(196, 'Sri Lanka', 'LK', 'LKA', '', 0, 1),
(197, 'St. Helena', 'SH', 'SHN', '', 0, 1),
(198, 'St. Pierre and Miquelon', 'PM', 'SPM', '', 0, 1),
(199, 'Sudan', 'SD', 'SDN', '', 0, 1),
(200, 'Suriname', 'SR', 'SUR', '', 0, 1),
(201, 'Svalbard and Jan Mayen Islands', 'SJ', 'SJM', '', 0, 1),
(202, 'Swaziland', 'SZ', 'SWZ', '', 0, 1),
(203, 'Sweden', 'SE', 'SWE', '{company}\r\n{firstname} {lastname}\r\n{address_1}\r\n{address_2}\r\n{postcode} {city}\r\n{country}', 1, 1),
(204, 'Switzerland', 'CH', 'CHE', '', 0, 1),
(205, 'Syrian Arab Republic', 'SY', 'SYR', '', 0, 1),
(206, 'Taiwan', 'TW', 'TWN', '', 0, 1),
(207, 'Tajikistan', 'TJ', 'TJK', '', 0, 1),
(208, 'Tanzania, United Republic of', 'TZ', 'TZA', '', 0, 1),
(209, 'Thailand', 'TH', 'THA', '', 0, 1),
(210, 'Togo', 'TG', 'TGO', '', 0, 1),
(211, 'Tokelau', 'TK', 'TKL', '', 0, 1),
(212, 'Tonga', 'TO', 'TON', '', 0, 1),
(213, 'Trinidad and Tobago', 'TT', 'TTO', '', 0, 1),
(214, 'Tunisia', 'TN', 'TUN', '', 0, 1),
(215, 'Turkey', 'TR', 'TUR', '', 0, 1),
(216, 'Turkmenistan', 'TM', 'TKM', '', 0, 1),
(217, 'Turks and Caicos Islands', 'TC', 'TCA', '', 0, 1),
(218, 'Tuvalu', 'TV', 'TUV', '', 0, 1),
(219, 'Uganda', 'UG', 'UGA', '', 0, 1),
(220, 'Ukraine', 'UA', 'UKR', '', 0, 1),
(221, 'United Arab Emirates', 'AE', 'ARE', '', 0, 1),
(222, 'United Kingdom', 'GB', 'GBR', '', 1, 1),
(223, 'United States', 'US', 'USA', '{firstname} {lastname}\r\n{company}\r\n{address_1}\r\n{address_2}\r\n{city}, {zone} {postcode}\r\n{country}', 0, 1),
(224, 'United States Minor Outlying Islands', 'UM', 'UMI', '', 0, 1),
(225, 'Uruguay', 'UY', 'URY', '', 0, 1),
(226, 'Uzbekistan', 'UZ', 'UZB', '', 0, 1),
(227, 'Vanuatu', 'VU', 'VUT', '', 0, 1),
(228, 'Vatican City State (Holy See)', 'VA', 'VAT', '', 0, 1),
(229, 'Venezuela', 'VE', 'VEN', '', 0, 1),
(230, 'Viet Nam', 'VN', 'VNM', '', 0, 1),
(231, 'Virgin Islands (British)', 'VG', 'VGB', '', 0, 1),
(232, 'Virgin Islands (U.S.)', 'VI', 'VIR', '', 0, 1),
(233, 'Wallis and Futuna Islands', 'WF', 'WLF', '', 0, 1),
(234, 'Western Sahara', 'EH', 'ESH', '', 0, 1),
(235, 'Yemen', 'YE', 'YEM', '', 0, 1),
(237, 'Democratic Republic of Congo', 'CD', 'COD', '', 0, 1),
(238, 'Zambia', 'ZM', 'ZMB', '', 0, 1),
(239, 'Zimbabwe', 'ZW', 'ZWE', '', 0, 1),
(242, 'Montenegro', 'ME', 'MNE', '', 0, 1),
(243, 'Serbia', 'RS', 'SRB', '', 0, 1),
(244, 'Aaland Islands', 'AX', 'ALA', '', 0, 1),
(245, 'Bonaire, Sint Eustatius and Saba', 'BQ', 'BES', '', 0, 1),
(246, 'Curacao', 'CW', 'CUW', '', 0, 1),
(247, 'Palestinian Territory, Occupied', 'PS', 'PSE', '', 0, 1),
(248, 'South Sudan', 'SS', 'SSD', '', 0, 1),
(249, 'St. Barthelemy', 'BL', 'BLM', '', 0, 1),
(250, 'St. Martin (French part)', 'MF', 'MAF', '', 0, 1),
(251, 'Canary Islands', 'IC', 'ICA', '', 0, 1),
(252, 'Ascension Island (British)', 'AC', 'ASC', '', 0, 1),
(253, 'Kosovo, Republic of', 'XK', 'UNK', '', 0, 1),
(254, 'Isle of Man', 'IM', 'IMN', '', 0, 1),
(255, 'Tristan da Cunha', 'TA', 'SHN', '', 0, 1),
(256, 'Guernsey', 'GG', 'GGY', '', 0, 1),
(257, 'Jersey', 'JE', 'JEY', '', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_coupon`
--

CREATE TABLE `ocn8_coupon` (
  `coupon_id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL,
  `code` varchar(20) NOT NULL,
  `type` char(1) NOT NULL,
  `discount` decimal(15,4) NOT NULL,
  `logged` tinyint(1) NOT NULL,
  `shipping` tinyint(1) NOT NULL,
  `total` decimal(15,4) NOT NULL,
  `date_start` date NOT NULL DEFAULT '0000-00-00',
  `date_end` date NOT NULL DEFAULT '0000-00-00',
  `uses_total` int(11) NOT NULL,
  `uses_customer` varchar(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_coupon`
--

INSERT INTO `ocn8_coupon` (`coupon_id`, `name`, `code`, `type`, `discount`, `logged`, `shipping`, `total`, `date_start`, `date_end`, `uses_total`, `uses_customer`, `status`, `date_added`) VALUES
(4, 'Business Customers ', 'Livixa-BC', 'P', '30.0000', 1, 1, '432.0000', '2022-07-01', '2022-12-31', 30, '10', 1, '2009-01-27 13:55:03'),
(7, 'Livixa-3months Mid Package', 'Livixa-Mid-3M', 'P', '5.0000', 1, 1, '78.0000', '2022-07-01', '2022-12-31', 0, '1', 1, '2022-06-05 14:21:01'),
(8, '12 months Mid Package ', 'Livixa-Mid-12M', 'P', '10.0000', 1, 1, '312.0000', '2022-07-01', '2022-12-31', 0, '1', 1, '2022-06-05 14:24:22'),
(9, '3 months High Package', 'Livixa-H-3M', 'P', '15.0000', 1, 1, '108.0000', '2022-07-01', '2022-12-31', 0, '1', 1, '2022-06-05 14:26:42'),
(10, 'High Package 12 months', 'Livixa-H-12M', 'P', '15.0000', 1, 1, '432.0000', '2022-07-01', '2022-12-31', 0, '1', 1, '2022-06-05 14:32:48'),
(11, 'Business Employees Loyalty Program ', 'livixa-ELP', 'P', '20.0000', 1, 1, '432.0000', '2022-07-01', '2022-12-31', 0, '1', 1, '2022-06-05 14:38:03');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_coupon_category`
--

CREATE TABLE `ocn8_coupon_category` (
  `coupon_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_coupon_category`
--

INSERT INTO `ocn8_coupon_category` (`coupon_id`, `category_id`) VALUES
(4, 59),
(7, 59),
(8, 59),
(9, 59),
(10, 59),
(11, 59);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_coupon_history`
--

CREATE TABLE `ocn8_coupon_history` (
  `coupon_history_id` int(11) NOT NULL,
  `coupon_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `amount` decimal(15,4) NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_coupon_product`
--

CREATE TABLE `ocn8_coupon_product` (
  `coupon_product_id` int(11) NOT NULL,
  `coupon_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_currency`
--

CREATE TABLE `ocn8_currency` (
  `currency_id` int(11) NOT NULL,
  `title` varchar(32) NOT NULL,
  `code` varchar(3) NOT NULL,
  `symbol_left` varchar(12) NOT NULL,
  `symbol_right` varchar(12) NOT NULL,
  `decimal_place` char(1) NOT NULL,
  `value` double(15,8) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `date_modified` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_currency`
--

INSERT INTO `ocn8_currency` (`currency_id`, `title`, `code`, `symbol_left`, `symbol_right`, `decimal_place`, `value`, `status`, `date_modified`) VALUES
(1, 'Pound Sterling', 'GBP', '£', '', '2', 0.61250001, 0, '2022-06-06 10:19:21'),
(2, 'US Dollar', 'USD', '$', '', '2', 1.00000000, 1, '2022-06-12 10:00:35'),
(3, 'Euro', 'EUR', '', '€', '2', 0.78460002, 0, '2022-06-06 10:43:44'),
(4, 'Saudi Riyal ', 'SAR', 'SAR', '', '', 1.00000000, 1, '2022-07-16 08:51:31');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_customer`
--

CREATE TABLE `ocn8_customer` (
  `customer_id` int(11) NOT NULL,
  `customer_group_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL DEFAULT 0,
  `language_id` int(11) NOT NULL,
  `firstname` varchar(32) NOT NULL,
  `lastname` varchar(32) NOT NULL,
  `email` varchar(96) NOT NULL,
  `telephone` varchar(32) NOT NULL,
  `fax` varchar(32) NOT NULL,
  `password` varchar(40) NOT NULL,
  `salt` varchar(9) NOT NULL,
  `cart` text DEFAULT NULL,
  `wishlist` text DEFAULT NULL,
  `newsletter` tinyint(1) NOT NULL DEFAULT 0,
  `address_id` int(11) NOT NULL DEFAULT 0,
  `custom_field` text NOT NULL,
  `ip` varchar(40) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `safe` tinyint(1) NOT NULL,
  `token` text NOT NULL,
  `code` varchar(40) NOT NULL,
  `date_added` datetime NOT NULL,
  `app_customer_id` int(15) DEFAULT 0
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_customer`
--

INSERT INTO `ocn8_customer` (`customer_id`, `customer_group_id`, `store_id`, `language_id`, `firstname`, `lastname`, `email`, `telephone`, `fax`, `password`, `salt`, `cart`, `wishlist`, `newsletter`, `address_id`, `custom_field`, `ip`, `status`, `safe`, `token`, `code`, `date_added`, `app_customer_id`) VALUES
(48, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'fasukhera@gmail.com', '03045527577', '', '940d2bf440e9efb8bc2b77bb2045584f442585cf', 'eviy0GNwz', NULL, NULL, 0, 19, '', '103.83.89.155', 1, 0, '', '', '2022-06-18 12:29:07', 333),
(2, 1, 0, 1, 'Sajjad', 'Akbar', 'sajjad.akbar@gmail.com', '03208400072', '', '5a3e2fca7bd30263d23ca35290bb71a0fd3c902d', 'h0RL6fNu1', NULL, NULL, 0, 2, '', '101.53.234.242', 1, 0, '', '', '2022-06-12 10:06:09', 0),
(3, 1, 0, 1, 'saleh', 'Alsaiari', 'saleh@livixa.com', '0549801399', '', 'f6e5109cceb0e9cc50dee4407033c1710bda9775', '2Y6wUPHqP', NULL, NULL, 1, 3, '', '2.89.27.140', 1, 0, '', '', '2022-06-13 09:38:45', 0),
(4, 1, 0, 1, 'Custo', '1', 'customer@gmail.com', '03045527577', '', '5d5c525593274bd717f8a38c69b7d8a2f79522b0', 'pVFYB8h2E', NULL, NULL, 0, 4, '', '103.83.89.155', 1, 0, '', '', '2022-06-13 11:09:55', 0),
(5, 1, 0, 1, 'Customer', '2', 'customer2@gmail.com', '03045527577', '', 'ee3f0ad8c2a76378372ac08504048b996703e411', 'LpCcSv7h5', NULL, NULL, 0, 0, '', '103.83.89.155', 1, 0, '', '', '2022-06-13 11:28:21', 0),
(6, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'customer4@gmail.com', 'tracking', '', '7e7c3a14b82de000611079a33fbb866d17b36aaf', 'MUGax06ly', NULL, NULL, 0, 0, '', '103.83.89.155', 1, 0, '', '', '2022-06-13 11:29:35', 0),
(7, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'customer6@gmail.com', '03045527577', '', '91c6cf59d3683c8408861724803d338cf895eec0', 'FKthTxx1D', NULL, NULL, 0, 0, '', '103.83.89.155', 1, 0, '', '', '2022-06-13 11:35:04', 0),
(8, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'customer7@gmail.com', '03045527577', '', 'ffaa79d4f85366b3ba698b6bfa46134a875c62ea', 'If4p2KotX', NULL, NULL, 0, 5, '', '119.160.96.99', 1, 0, '', '', '2022-06-13 11:36:22', 0),
(9, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'customer10@gmail.com', '03045527577', '', '4c143ddc743d5e3b1a5d50dca0dd18f66d103e44', 'P3C3MvAAE', NULL, NULL, 0, 6, '', '119.160.96.99', 1, 0, '', '', '2022-06-13 11:45:23', 0),
(10, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'customer12@gmail.com', '03045527577', '', '47eba3cd8ff6390169e73f40942f521b261cb886', 'pHtIXcWjV', NULL, NULL, 0, 7, '', '119.160.96.99', 1, 0, '', '', '2022-06-13 12:02:46', 0),
(11, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'customer13@gmail.com', '03045527577', '', '3d79307707d3bc3331d4af138d509d89001c35ee', 'Z2blCh2Iy', NULL, NULL, 0, 0, '', '119.160.96.99', 1, 0, '', '', '2022-06-13 12:22:00', 0),
(12, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'customer15@gmail.com', '03045527577', '', '27a79514da6fb993657ff2732f18734ee95eea95', 'G1Sz8i7r0', NULL, NULL, 0, 0, '', '119.160.96.99', 1, 0, '', '', '2022-06-13 12:22:47', 0),
(13, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'customer16@gmail.com', '03045527577', '', 'da0252dc1000d41259964af0eb4a9b9dc8fe5973', 'UBXc3oso3', NULL, NULL, 0, 0, '', '119.160.96.99', 1, 0, '', '', '2022-06-13 12:24:13', 0),
(14, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'customer17@gmail.com', '03045527577', '', '0cdd9aed4f553bac06fafbab9de936aa00db2d32', 'mLzgTkeok', NULL, NULL, 0, 0, '', '119.160.96.99', 1, 0, '', '', '2022-06-13 12:25:53', 0),
(15, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'customer19@gmail.com', '03045527577', '', 'd2fdab9782022ec71dc667976cc7370130675bd0', 'FzCjPfBjQ', NULL, NULL, 0, 0, '', '119.160.96.99', 1, 0, '', '', '2022-06-13 12:26:37', 0),
(16, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'customer21@gmail.com', '03045527577', '', '094b7699788915b1428a416cb6995e73ce3ca66d', 'XEnlWRovj', NULL, NULL, 0, 0, '', '119.160.96.99', 1, 0, '', '', '2022-06-13 12:30:48', 0),
(17, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'customer25@gmail.com', '03045527577', '', '56c9c4683c25cb44f1739130caea56814f1271e9', 'RTDCSMnQP', NULL, NULL, 0, 0, '', '72.255.38.189', 1, 0, '', '', '2022-06-13 13:55:35', 0),
(18, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'customer30@gmail.com', '03045527577', '', 'aea18104b1425a956f5fe3ef9abfdf8009418eeb', 'nCshRCZxG', NULL, NULL, 0, 0, '', '72.255.38.189', 1, 0, '', '', '2022-06-13 14:01:50', 0),
(19, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'customer31@gmail.com', '03045527577', '', 'a3cd2a1fcac4ae55113fe41db72fd4c1674cb558', 'axwb02z24', NULL, NULL, 0, 0, '', '72.255.38.189', 1, 0, '', '', '2022-06-13 14:04:49', 0),
(20, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'c32@gmail.com', '03045527577', '', 'd30f9d969bc4a590f4d9a39bfb139bb14fa69746', 'ZtNm3SNpj', NULL, NULL, 0, 0, '', '72.255.38.189', 1, 0, '', '', '2022-06-13 14:05:44', 0),
(21, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'c1@gmail.com', '03045527577', '', '71a0cd3afbb13ac8e42d3a3ae5b46defe8e9846f', 'QsFJVGUSG', NULL, NULL, 0, 8, '', '72.255.38.189', 1, 0, '', '', '2022-06-13 14:06:43', 0),
(22, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'c3@gmail.com', '03045527577', '', 'c256e839106f25fd1be889f88b7157f5fbce5aee', 'MpVSlgQyY', NULL, NULL, 0, 0, '', '72.255.38.189', 1, 0, '', '', '2022-06-13 14:08:54', 0),
(23, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'c5@gmail.com', '03045527577', '', 'c7d340b76882de105290afab54cf740c4766b7e5', 'VJpN7ojNW', NULL, NULL, 0, 0, '', '72.255.38.189', 1, 0, '', '', '2022-06-13 14:11:00', 0),
(24, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'c6@gmail.com', '03045527577', '', '62a7fb00bc4059b342b3c1f28c7a8d7580360786', 'DwQXthUc0', NULL, NULL, 0, 0, '', '103.83.89.155', 1, 0, '', '', '2022-06-13 14:17:42', 0),
(25, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'c9@gmail.com', '03045527577', '', '28e4cd1bac029bf0f39d3a4529f00ce11a729e08', 'XAfFqTCat', NULL, NULL, 0, 0, '', '103.83.89.155', 1, 0, '', '', '2022-06-13 14:19:12', 0),
(26, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'c10@gmail.com', '03045527577', '', 'c8a1c3efa4f0c1ff869929a60771f5e40122cb28', 'F77AqmzVW', NULL, NULL, 0, 0, '', '103.83.89.155', 1, 0, '', '', '2022-06-13 14:20:13', 0),
(27, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'a1@gmail.com', '03045527577', '', 'fcab301cfba65973553f884dec08b94fb2975652', 'tui52qeHh', NULL, NULL, 0, 0, '', '72.255.38.189', 1, 0, '', '', '2022-06-13 14:24:13', 0),
(28, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'a2@gmail.com', '03045527577', '', '6ef42bdee874050c40d20d059f7d4f4559054870', 'j7AbbvXqC', NULL, NULL, 0, 0, '', '72.255.38.189', 1, 0, '', '', '2022-06-13 14:25:20', 0),
(29, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'a4@gmail.com', '03045527577', '', '285ce509e8035989e2eb6175bdd9e3e1ab3018d7', 'JwOz9wfu9', NULL, NULL, 0, 0, '', '72.255.38.189', 1, 0, '', '', '2022-06-13 14:26:23', 0),
(30, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'b1@gmail.com', '03045527577', '', 'db6c2ad74431ec6b798c4c501ac8c3097329bde4', 'JnGrnPepk', NULL, NULL, 0, 0, '', '119.160.98.140', 1, 0, '', '', '2022-06-14 09:43:51', 0),
(31, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'b2@gmail.com', '03045527577', '', '9cfbabcdd57868b9b779c119437e00146d2386e5', 'mcZurCSwd', NULL, NULL, 0, 9, '', '119.160.98.140', 1, 0, '', '', '2022-06-14 09:49:13', 0),
(32, 1, 0, 1, 'bushra', 'khalid', 'qa@quaidventures.com', '03186059416', '', 'cc6601e63d22292cf9d855474cc514f460cc702b', 'Ww0feKkrA', NULL, NULL, 0, 10, '', '72.255.38.189', 1, 0, '', '', '2022-06-15 14:29:17', 0),
(33, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'ls2@gmail.com', '03045527577', '', '00455b551b5d979b147ad3c6bd726f8c220b13ef', '3OGZdEVWQ', NULL, NULL, 0, 0, '', '119.160.97.5', 1, 0, '', '', '2022-06-16 10:16:13', 0),
(34, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'lx1@gmail.com', '03045527577', '', 'ff5288ba911d3daafe14b199e9a863b36dd10b11', 'zNA2U5JSa', NULL, NULL, 0, 0, '', '119.160.97.5', 1, 0, '', '', '2022-06-16 10:17:04', 0),
(35, 1, 0, 1, 'Testing', 'Testing', 't1@livixa.com', '03045527577', '', 'aebd6d2c5a7f53e3c9ff952fbb1e11a2761202c3', 'Xumfs4cYe', NULL, NULL, 0, 0, '', '103.83.89.155', 1, 0, '', '', '2022-06-16 11:21:33', 0),
(36, 1, 0, 1, 'Quaid', 'Venture', 'dev102@quaidventures.com', '03045527577', '', '1702b1084b283699b34a9009ac89b3f83188f2a0', 'UpFRLLgyw', NULL, NULL, 0, 0, '', '72.255.38.189', 1, 0, '', '', '2022-06-16 11:23:50', 0),
(37, 1, 0, 1, 'Testing', 'Testing', 't2@gmail.com', '03045527577', '', 'a501d3c7f956294f37ad76b7e101239367780517', 'iMAbzPexK', NULL, NULL, 0, 11, '', '72.255.38.189', 1, 0, '', '', '2022-06-16 11:28:32', 0),
(38, 1, 0, 1, 'Check', 'Check', 't3@livixa.com', '03045527577', '', 'e2831d173f3f8d730b37d3a30a3ac7cc16c86279', 'o8DI7kME9', NULL, NULL, 0, 0, '', '72.255.38.189', 1, 0, '', '', '2022-06-16 11:33:43', 0),
(39, 1, 0, 1, 'Check', 'Check', 't4@gmail.com', '03045527577', '', '9ecf9be9e818cc871ce3dfd0d2976d28954c2e92', '6LCfzE5ND', NULL, NULL, 0, 12, '', '103.83.89.155', 1, 0, '', '', '2022-06-16 11:34:42', 0),
(40, 1, 0, 1, 'Test', 'Test', 't5@gmail.com', '03045527577', '', '488d9c9033425444d0c913a59b3a614d2b39a964', 'Nf88xyxkn', NULL, NULL, 0, 13, '', '103.83.89.155', 1, 0, '', '', '2022-06-16 11:38:03', 0),
(41, 1, 0, 1, 'Hecta', 'pecta', 'hp@gmail.com', '03045527577', '', '6a32b736e18e3d76ea5a1502970a93305c90a12c', 'GL2dsCR37', NULL, NULL, 0, 14, '', '103.83.89.155', 1, 0, '', '', '2022-06-16 11:47:58', 0),
(42, 1, 0, 1, 'Check', 'Check', 't10@gmail.com', '03045527577', '', '3c1437e18eaeb2dc0b1252a07080012fcbe5499c', 'RJ4oLNias', NULL, NULL, 0, 15, '', '72.255.38.189', 1, 0, '', '', '2022-06-16 11:49:26', 320),
(43, 1, 0, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '62d65111605642f969a825c16e6b4a2b440298c6', 'zvv7UJhZd', NULL, NULL, 0, 16, '', '103.83.89.155', 1, 0, '', '', '2022-06-16 13:04:25', 348),
(44, 1, 0, 1, 'bush', 'mac', 'bushra@tracking.me', '03186059416', '', '9d6182bfd152f8dcca60420b17f739c5fce3ec40', 'buV7kkuyh', NULL, NULL, 0, 0, '', '72.255.38.189', 1, 0, '', '', '2022-06-16 13:10:38', 0),
(45, 1, 0, 1, 'Ayesga', 'test', 'ayeshakhalid117@gmail.com', '03186059416', '', '8337d89f5a6b6fe1e2c130b857ba9f1773940f93', 'MjmnMSEGp', NULL, NULL, 0, 17, '', '72.255.38.189', 1, 0, '', '', '2022-06-17 11:12:47', 0),
(46, 1, 0, 1, 'SIT', 'Livixa', 'sitlivixa@livixa.com', '03045527577', '', 'abbcbc93acf920066cba6be32a08b666caeec354', 'Cwb0rroHz', NULL, NULL, 0, 0, '', '119.160.99.206', 1, 0, '', '', '2022-06-18 12:10:02', 0),
(47, 1, 0, 1, 'SIT', 'Livixa', 'sit@gmail.com', '03045527577', '', 'b0e246c1a5bb4aa4478605845950207c79ab8a16', 'J6TWwy2xW', NULL, NULL, 0, 18, '', '119.160.99.206', 1, 0, '', '', '2022-06-18 12:10:36', 332),
(49, 1, 0, 1, 'SIT', 'Farrukh', 'ferozsukhera@gmail.com', '03045527577', '', '6ffda2100b68a3d5397eda2aeae83a696cd9446f', 'zrSKvWXzI', NULL, NULL, 0, 20, '', '72.255.38.189', 1, 0, '', '', '2022-06-19 11:15:52', 337),
(50, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'petic25068@serosin.com', '03045527577', '', 'f26ce4f073da65c7c306d89d5477218e5a661f96', 'byID7AoKu', NULL, NULL, 0, 21, '', '103.83.89.155', 1, 0, '', '', '2022-06-19 14:02:52', 339),
(51, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'test213@gmail.com', '03045527577', '', '2565cb1683be4da4239ba726913a37613c57f04a', 'rpjlL64RQ', NULL, NULL, 0, 22, '', '103.83.89.155', 1, 0, '', '', '2022-06-19 14:31:01', 0),
(52, 1, 0, 1, 'Livixa', 'SIT', 'wojas19657@exoacre.com', '03045527577', '', 'f119551ea86e483d244750af870c6a6ff5850873', 'RRGwa8zUi', NULL, NULL, 0, 23, '', '103.83.89.155', 1, 0, '', '', '2022-06-19 14:39:17', 343),
(53, 1, 0, 1, 'Livxa', 'SIT', 'micitoh604@qqhow.com', '03045527577', '', '59e5494be4f1bd5f5a6a786b7798c5106af1ff63', 'gYLiApJor', NULL, NULL, 0, 24, '', '103.83.89.155', 1, 0, '', '', '2022-06-19 14:48:37', 344),
(54, 1, 0, 1, 'check', 'cehck', 'pitij30782@tagbert.com', '03045527577', '', '2f7dcbd84b978cccd58fdd59adc1c73b3dfec449', 'XACvhI8C8', NULL, NULL, 0, 25, '', '72.255.38.189', 1, 0, '', '', '2022-06-20 12:20:00', 0),
(55, 1, 0, 1, 'Livixa', 'Final SIT', 'mawadoj520@tagbert.com', '03045527577', '', 'df763fcb7e5e69cf18c07f37b72b6ce7c92080f1', 'X2zFcxTU6', NULL, NULL, 0, 26, '', '103.83.89.155', 1, 0, '', '', '2022-06-20 13:08:42', 349),
(56, 1, 0, 1, 'Sit', 'Livixa Finalv2', 'cadovec607@giftcv.com', '03045527577', '', '21fb0a9c4f467f0115b402bf423ed728b7f94eb7', 'PcfS32B2q', NULL, NULL, 0, 27, '', '72.255.38.189', 1, 0, '', '', '2022-06-20 13:29:28', 350),
(57, 1, 0, 1, 'Livixa', 'SIT', 'regegol640@syswift.com', '03045527577', '', '940f4e8f2a8540e99c936d2412172bf58733a4f4', 'CpEOdjNI3', NULL, NULL, 0, 28, '', '103.83.89.155', 1, 0, '', '', '2022-06-21 11:15:03', 356),
(58, 1, 0, 1, 'Livix', 'SIT', 'xejix10890@tagbert.com', '03045527577', '', 'b30f8054ba22a7e110bc795a806f74cb82a47945', 'dkjRzUdDf', NULL, NULL, 0, 29, '', '103.83.89.155', 1, 0, '', '', '2022-06-21 11:24:32', 357),
(59, 1, 0, 1, 'Liv', 'xa', 'midod56191@syswift.com', '03045527577', '', '28e6dd27a053a5c0dbc226ffb7bdc8660e5dbeaa', '4R0rZZiQc', NULL, NULL, 0, 30, '', '103.83.89.155', 1, 0, '', '', '2022-06-21 11:46:13', 358),
(60, 1, 0, 1, 'Livixa', 'SIT', 'yifeve4398@giftcv.com', '03045527577', '', 'f6307bf9b06b67036b80eb0c8b0d42db86f30eda', 'saIVBMcFF', NULL, NULL, 0, 31, '', '103.83.89.155', 1, 0, '', '', '2022-06-21 14:07:14', 360),
(61, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'jivec99449@mahazai.com', '03045527577', '', 'f0cb1d4c462ebf6d376396f814d0ae4fbcc8058d', 'DnmqfsX2v', NULL, NULL, 0, 0, '', '103.83.89.155', 1, 0, '', '', '2022-06-22 11:29:45', 0),
(62, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'jivec19449@mahazai.com', '03045527577', '', '4b6f47f8157d325bc721ebf76ee6ad9949e89831', 'ke3kTfPpt', NULL, NULL, 0, 0, '', '103.83.89.155', 1, 0, '', '', '2022-06-22 11:32:33', 0),
(63, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'jivec89119@mahazai.com', '03045527577', '', '4a5034b23176de634d8fa7b2aced9dbc5c3a6c2a', 'quI1gdMpl', NULL, NULL, 0, 32, '', '103.83.89.155', 1, 0, '', '', '2022-06-22 11:34:41', 0),
(64, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'sales@livixa.com', '03045527577', '', '0f213d5092846b96109d0bb9d5a205162754005b', '7ObVt1k5J', NULL, NULL, 0, 33, '', '188.50.139.79', 1, 0, '', '', '2022-06-22 11:42:40', 365),
(65, 1, 0, 1, 'Bushra', 'Khalid', 'depaw68688@shbiso.com', '03186059416', '', 'c2e8011f6013f58ee905b8772c10251007bc97d8', 'ORZAVxDvT', NULL, NULL, 0, 35, '', '72.255.38.189', 1, 0, '', '', '2022-07-03 11:47:10', 0),
(66, 1, 0, 1, 'Bushra', 'Khalid', 'tabawet923@jrvps.com', '03186059416', '', 'd38f25d5baf792c0087938580f552ef952ac8ed8', 'M3PzDXId2', NULL, NULL, 0, 36, '', '72.255.38.189', 1, 0, '', '', '2022-07-03 12:49:02', 0),
(67, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'tatike9462@runfons.com', '03045527577', '', '9190f68bf17b02aaa1bf66a9081c8d02c7433368', 'QCmaMun3E', NULL, NULL, 0, 37, '', '119.73.124.43', 1, 0, '', '', '2022-07-13 13:21:55', 372),
(68, 1, 0, 1, 'Faran', 'Ahmed Sukhera', 'reyajek701@satedly.com', '03045527577', '', '204772c18b785d0892a772f45a214ad352fd2fae', 'I3yeuxiw4', NULL, NULL, 0, 38, '', '119.73.124.43', 1, 0, '', '', '2022-07-13 14:20:21', 373),
(69, 1, 0, 1, 'Maliha', 'Khan', 'covig99256@opude.com', '03186059416', '', '9308b58d65836e4f1fda91c26647cbe820f7c6bb', 'vVa8eWVSQ', NULL, NULL, 0, 39, '', '103.83.89.155', 1, 0, '', '', '2022-07-14 10:20:01', 375),
(70, 1, 0, 1, 'Ayesha', 'Bushra', 'cajapo5088@lodores.com', '03186059416', '', 'cf92dbb0365aa8ab1d9c67a19eb0ffce8e4cf281', 'LWl8Of20L', NULL, NULL, 0, 40, '', '103.83.89.155', 1, 0, '', '', '2022-07-14 12:02:33', 376),
(71, 1, 0, 1, 'Gagandeep', 'Singh', 'webtunix.hr@gmail.com', '7018760757', '', 'e698a0b795da424670b2bd325e2731a2c8d6ff17', '2JLuzdoqu', NULL, NULL, 0, 41, '', '223.178.213.7', 1, 0, '', '', '2022-07-15 11:00:28', 382),
(72, 1, 0, 2, 'Aastha', 'sharma', 'gagan25alone@gmail.com', '9888825206', '', '0a848d19dc7d0da850f1b0e0fe6ab234ee766476', 'sVCPVbcjb', NULL, NULL, 0, 42, '', '223.178.213.7', 1, 0, '', '', '2022-07-15 13:14:55', 383);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_customer_activity`
--

CREATE TABLE `ocn8_customer_activity` (
  `customer_activity_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `key` varchar(64) NOT NULL,
  `data` text NOT NULL,
  `ip` varchar(40) NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_customer_affiliate`
--

CREATE TABLE `ocn8_customer_affiliate` (
  `customer_id` int(11) NOT NULL,
  `company` varchar(40) NOT NULL,
  `website` varchar(255) NOT NULL,
  `tracking` varchar(64) NOT NULL,
  `commission` decimal(4,2) NOT NULL DEFAULT 0.00,
  `tax` varchar(64) NOT NULL,
  `payment` varchar(6) NOT NULL,
  `cheque` varchar(100) NOT NULL,
  `paypal` varchar(64) NOT NULL,
  `bank_name` varchar(64) NOT NULL,
  `bank_branch_number` varchar(64) NOT NULL,
  `bank_swift_code` varchar(64) NOT NULL,
  `bank_account_name` varchar(64) NOT NULL,
  `bank_account_number` varchar(64) NOT NULL,
  `custom_field` text NOT NULL,
  `status` tinyint(1) NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_customer_approval`
--

CREATE TABLE `ocn8_customer_approval` (
  `customer_approval_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `type` varchar(9) NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_customer_group`
--

CREATE TABLE `ocn8_customer_group` (
  `customer_group_id` int(11) NOT NULL,
  `approval` int(1) NOT NULL,
  `sort_order` int(3) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_customer_group`
--

INSERT INTO `ocn8_customer_group` (`customer_group_id`, `approval`, `sort_order`) VALUES
(1, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_customer_group_description`
--

CREATE TABLE `ocn8_customer_group_description` (
  `customer_group_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `description` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_customer_group_description`
--

INSERT INTO `ocn8_customer_group_description` (`customer_group_id`, `language_id`, `name`, `description`) VALUES
(1, 1, 'Default', 'test'),
(1, 2, 'Default', 'test');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_customer_history`
--

CREATE TABLE `ocn8_customer_history` (
  `customer_history_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `comment` text NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_customer_ip`
--

CREATE TABLE `ocn8_customer_ip` (
  `customer_ip_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `ip` varchar(40) NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_customer_ip`
--

INSERT INTO `ocn8_customer_ip` (`customer_ip_id`, `customer_id`, `ip`, `date_added`) VALUES
(1, 1, '119.160.97.158', '2022-06-09 13:18:54'),
(2, 2, '72.255.38.189', '2022-06-12 10:06:09'),
(3, 3, '159.0.208.210', '2022-06-13 09:38:45'),
(4, 4, '103.83.89.155', '2022-06-13 11:09:55'),
(5, 8, '103.83.89.155', '2022-06-13 11:36:23'),
(6, 8, '119.160.96.99', '2022-06-13 11:42:23'),
(7, 9, '119.160.96.99', '2022-06-13 11:45:24'),
(8, 10, '119.160.96.99', '2022-06-13 12:02:46'),
(9, 3, '58.27.236.26', '2022-06-13 12:20:57'),
(10, 3, '111.68.108.130', '2022-06-13 12:32:26'),
(11, 3, '210.2.138.70', '2022-06-13 12:50:20'),
(12, 2, '119.160.98.187', '2022-06-13 13:04:03'),
(13, 21, '72.255.38.189', '2022-06-13 14:06:44'),
(14, 2, '103.83.89.155', '2022-06-13 14:44:46'),
(15, 2, '101.53.234.242', '2022-06-14 05:07:07'),
(16, 31, '119.160.98.140', '2022-06-14 09:49:14'),
(17, 32, '72.255.38.189', '2022-06-15 14:38:03'),
(18, 37, '72.255.38.189', '2022-06-16 11:28:33'),
(19, 37, '103.83.89.155', '2022-06-16 11:29:38'),
(20, 39, '103.83.89.155', '2022-06-16 11:34:43'),
(21, 39, '72.255.38.189', '2022-06-16 11:35:38'),
(22, 40, '103.83.89.155', '2022-06-16 11:38:04'),
(23, 40, '72.255.38.189', '2022-06-16 11:38:51'),
(24, 41, '103.83.89.155', '2022-06-16 11:47:59'),
(25, 42, '103.83.89.155', '2022-06-16 11:49:27'),
(26, 42, '72.255.38.189', '2022-06-16 11:52:11'),
(27, 43, '72.255.38.189', '2022-06-16 13:04:26'),
(28, 3, '188.51.168.233', '2022-06-16 15:37:14'),
(29, 45, '103.83.89.155', '2022-06-17 11:12:49'),
(30, 45, '72.255.38.189', '2022-06-17 11:16:07'),
(31, 47, '119.160.99.206', '2022-06-18 12:10:37'),
(32, 48, '119.160.99.206', '2022-06-18 12:29:08'),
(33, 49, '103.83.89.155', '2022-06-19 11:15:54'),
(34, 49, '72.255.38.189', '2022-06-19 11:17:35'),
(35, 50, '103.83.89.155', '2022-06-19 14:02:52'),
(36, 51, '103.83.89.155', '2022-06-19 14:31:02'),
(37, 52, '103.83.89.155', '2022-06-19 14:39:17'),
(38, 53, '103.83.89.155', '2022-06-19 14:48:38'),
(39, 43, '103.83.89.155', '2022-06-20 09:53:00'),
(40, 54, '72.255.38.189', '2022-06-20 12:20:01'),
(41, 55, '119.160.98.193', '2022-06-20 13:08:43'),
(42, 55, '103.83.89.155', '2022-06-20 13:23:34'),
(43, 56, '72.255.38.189', '2022-06-20 13:29:29'),
(44, 56, '72.255.38.189', '2022-06-20 13:29:29'),
(45, 57, '103.83.89.155', '2022-06-21 11:15:06'),
(46, 58, '103.83.89.155', '2022-06-21 11:24:33'),
(47, 59, '103.83.89.155', '2022-06-21 11:46:14'),
(48, 59, '103.83.89.155', '2022-06-21 11:46:14'),
(49, 48, '103.83.89.155', '2022-06-21 12:43:15'),
(50, 60, '103.83.89.155', '2022-06-21 14:07:15'),
(51, 63, '103.83.89.155', '2022-06-22 11:34:42'),
(52, 64, '103.83.89.155', '2022-06-22 11:42:41'),
(53, 64, '103.83.89.155', '2022-06-22 11:42:41'),
(54, 3, '188.50.139.79', '2022-06-22 11:45:42'),
(55, 64, '188.50.139.79', '2022-06-22 11:57:05'),
(56, 65, '72.255.38.189', '2022-07-03 12:40:44'),
(57, 66, '72.255.38.189', '2022-07-03 12:49:06'),
(58, 3, '2.89.27.140', '2022-07-09 00:43:35'),
(59, 67, '119.73.124.43', '2022-07-13 13:22:01'),
(60, 68, '119.73.124.43', '2022-07-13 14:20:26'),
(61, 69, '103.83.89.155', '2022-07-14 10:20:08'),
(62, 70, '103.83.89.155', '2022-07-14 12:02:37'),
(63, 71, '223.178.213.7', '2022-07-15 11:00:32'),
(64, 72, '223.178.213.7', '2022-07-15 13:14:59');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_customer_login`
--

CREATE TABLE `ocn8_customer_login` (
  `customer_login_id` int(11) NOT NULL,
  `email` varchar(96) NOT NULL,
  `ip` varchar(40) NOT NULL,
  `total` int(4) NOT NULL,
  `date_added` datetime NOT NULL,
  `date_modified` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_customer_login`
--

INSERT INTO `ocn8_customer_login` (`customer_login_id`, `email`, `ip`, `total`, `date_added`, `date_modified`) VALUES
(1, 'adm@livixa.tracking.me', '103.83.89.155', 3, '2022-06-01 13:34:35', '2022-06-13 12:31:12'),
(2, 'livixa', '72.255.38.189', 53, '2022-06-01 15:43:35', '2022-07-12 11:33:46'),
(3, 'salehpredicts@gmail.com', '188.54.236.144', 2, '2022-06-02 10:56:28', '2022-06-02 10:56:43'),
(14, '03186059416', '72.255.38.189', 1, '2022-06-30 06:17:57', '2022-06-30 06:17:57'),
(12, '03045527577', '103.83.89.155', 1, '2022-06-21 12:42:41', '2022-06-21 12:42:41'),
(8, 'admin', '58.27.236.26', 546, '2022-06-13 12:34:53', '2022-07-16 07:35:55'),
(17, 'aljazi-mohammad@outlook.com', '78.95.17.121', 3, '2022-07-08 22:01:03', '2022-07-08 22:01:56'),
(18, 'saleh@livixa.com', '2.89.27.140', 1, '2022-07-09 22:44:38', '2022-07-09 22:44:38'),
(19, 'livixa1', '94.231.106.8', 36, '2022-07-11 21:18:02', '2022-07-12 11:33:48'),
(20, 'livixa.com', '178.128.84.168', 32, '2022-07-11 21:18:03', '2022-07-12 11:33:49'),
(21, '[asdomaincom]', '51.79.160.231', 51, '2022-07-11 21:18:06', '2022-07-12 11:33:46'),
(22, 'webtunx.hr@gmail.com', '223.178.213.7', 1, '2022-07-15 10:57:56', '2022-07-15 10:57:56');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_customer_online`
--

CREATE TABLE `ocn8_customer_online` (
  `ip` varchar(40) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `url` text NOT NULL,
  `referer` text NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_customer_reward`
--

CREATE TABLE `ocn8_customer_reward` (
  `customer_reward_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL DEFAULT 0,
  `order_id` int(11) NOT NULL DEFAULT 0,
  `description` text NOT NULL,
  `points` int(8) NOT NULL DEFAULT 0,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_customer_search`
--

CREATE TABLE `ocn8_customer_search` (
  `customer_search_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `keyword` varchar(255) NOT NULL,
  `category_id` int(11) DEFAULT NULL,
  `sub_category` tinyint(1) NOT NULL,
  `description` tinyint(1) NOT NULL,
  `products` int(11) NOT NULL,
  `ip` varchar(40) NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_customer_transaction`
--

CREATE TABLE `ocn8_customer_transaction` (
  `customer_transaction_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `description` text NOT NULL,
  `amount` decimal(15,4) NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_customer_wishlist`
--

CREATE TABLE `ocn8_customer_wishlist` (
  `customer_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_customer_wishlist`
--

INSERT INTO `ocn8_customer_wishlist` (`customer_id`, `product_id`, `date_added`) VALUES
(43, 52, '2022-07-06 11:13:05');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_custom_field`
--

CREATE TABLE `ocn8_custom_field` (
  `custom_field_id` int(11) NOT NULL,
  `type` varchar(32) NOT NULL,
  `value` text NOT NULL,
  `validation` varchar(255) NOT NULL,
  `location` varchar(10) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `sort_order` int(3) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_custom_field_customer_group`
--

CREATE TABLE `ocn8_custom_field_customer_group` (
  `custom_field_id` int(11) NOT NULL,
  `customer_group_id` int(11) NOT NULL,
  `required` tinyint(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_custom_field_description`
--

CREATE TABLE `ocn8_custom_field_description` (
  `custom_field_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_custom_field_value`
--

CREATE TABLE `ocn8_custom_field_value` (
  `custom_field_value_id` int(11) NOT NULL,
  `custom_field_id` int(11) NOT NULL,
  `sort_order` int(3) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_custom_field_value_description`
--

CREATE TABLE `ocn8_custom_field_value_description` (
  `custom_field_value_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `custom_field_id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_download`
--

CREATE TABLE `ocn8_download` (
  `download_id` int(11) NOT NULL,
  `filename` varchar(160) NOT NULL,
  `mask` varchar(128) NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_download_description`
--

CREATE TABLE `ocn8_download_description` (
  `download_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_event`
--

CREATE TABLE `ocn8_event` (
  `event_id` int(11) NOT NULL,
  `code` varchar(64) NOT NULL,
  `trigger` text NOT NULL,
  `action` text NOT NULL,
  `status` tinyint(1) NOT NULL,
  `sort_order` int(3) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_event`
--

INSERT INTO `ocn8_event` (`event_id`, `code`, `trigger`, `action`, `status`, `sort_order`) VALUES
(1, 'activity_customer_add', 'catalog/model/account/customer/addCustomer/after', 'event/activity/addCustomer', 1, 0),
(2, 'activity_customer_edit', 'catalog/model/account/customer/editCustomer/after', 'event/activity/editCustomer', 1, 0),
(3, 'activity_customer_password', 'catalog/model/account/customer/editPassword/after', 'event/activity/editPassword', 1, 0),
(4, 'activity_customer_forgotten', 'catalog/model/account/customer/editCode/after', 'event/activity/forgotten', 1, 0),
(5, 'activity_transaction', 'catalog/model/account/customer/addTransaction/after', 'event/activity/addTransaction', 1, 0),
(6, 'activity_customer_login', 'catalog/model/account/customer/deleteLoginAttempts/after', 'event/activity/login', 1, 0),
(7, 'activity_address_add', 'catalog/model/account/address/addAddress/after', 'event/activity/addAddress', 1, 0),
(8, 'activity_address_edit', 'catalog/model/account/address/editAddress/after', 'event/activity/editAddress', 1, 0),
(9, 'activity_address_delete', 'catalog/model/account/address/deleteAddress/after', 'event/activity/deleteAddress', 1, 0),
(10, 'activity_affiliate_add', 'catalog/model/account/customer/addAffiliate/after', 'event/activity/addAffiliate', 1, 0),
(11, 'activity_affiliate_edit', 'catalog/model/account/customer/editAffiliate/after', 'event/activity/editAffiliate', 1, 0),
(12, 'activity_order_add', 'catalog/model/checkout/order/addOrderHistory/before', 'event/activity/addOrderHistory', 1, 0),
(13, 'activity_return_add', 'catalog/model/account/return/addReturn/after', 'event/activity/addReturn', 1, 0),
(14, 'mail_transaction', 'catalog/model/account/customer/addTransaction/after', 'mail/transaction', 1, 0),
(15, 'mail_forgotten', 'catalog/model/account/customer/editCode/after', 'mail/forgotten', 1, 0),
(16, 'mail_customer_add', 'catalog/model/account/customer/addCustomer/after', 'mail/register', 1, 0),
(17, 'mail_customer_alert', 'catalog/model/account/customer/addCustomer/after', 'mail/register/alert', 1, 0),
(18, 'mail_affiliate_add', 'catalog/model/account/customer/addAffiliate/after', 'mail/affiliate', 1, 0),
(19, 'mail_affiliate_alert', 'catalog/model/account/customer/addAffiliate/after', 'mail/affiliate/alert', 1, 0),
(20, 'mail_voucher', 'catalog/model/checkout/order/addOrderHistory/after', 'extension/total/voucher/send', 1, 0),
(21, 'mail_order_add', 'catalog/model/checkout/order/addOrderHistory/before', 'mail/order', 1, 0),
(22, 'mail_order_alert', 'catalog/model/checkout/order/addOrderHistory/before', 'mail/order/alert', 1, 0),
(23, 'statistics_review_add', 'catalog/model/catalog/review/addReview/after', 'event/statistics/addReview', 1, 0),
(24, 'statistics_return_add', 'catalog/model/account/return/addReturn/after', 'event/statistics/addReturn', 1, 0),
(25, 'statistics_order_history', 'catalog/model/checkout/order/addOrderHistory/after', 'event/statistics/addOrderHistory', 1, 0),
(26, 'admin_mail_affiliate_approve', 'admin/model/customer/customer_approval/approveAffiliate/after', 'mail/affiliate/approve', 1, 0),
(27, 'admin_mail_affiliate_deny', 'admin/model/customer/customer_approval/denyAffiliate/after', 'mail/affiliate/deny', 1, 0),
(28, 'admin_mail_customer_approve', 'admin/model/customer/customer_approval/approveCustomer/after', 'mail/customer/approve', 1, 0),
(29, 'admin_mail_customer_deny', 'admin/model/customer/customer_approval/denyCustomer/after', 'mail/customer/deny', 1, 0),
(30, 'admin_mail_reward', 'admin/model/customer/customer/addReward/after', 'mail/reward', 1, 0),
(31, 'admin_mail_transaction', 'admin/model/customer/customer/addTransaction/after', 'mail/transaction', 1, 0),
(32, 'admin_mail_return', 'admin/model/sale/return/addReturnHistory/after', 'mail/return', 1, 0),
(33, 'admin_mail_forgotten', 'admin/model/user/user/editCode/after', 'mail/forgotten', 1, 0),
(34, 'advertise_google', 'admin/model/catalog/product/deleteProduct/after', 'extension/advertise/google/deleteProduct', 1, 0),
(35, 'advertise_google', 'admin/model/catalog/product/copyProduct/after', 'extension/advertise/google/copyProduct', 1, 0),
(36, 'advertise_google', 'admin/view/common/column_left/before', 'extension/advertise/google/admin_link', 1, 0),
(37, 'advertise_google', 'admin/model/catalog/product/addProduct/after', 'extension/advertise/google/addProduct', 1, 0),
(38, 'advertise_google', 'catalog/controller/checkout/success/before', 'extension/advertise/google/before_checkout_success', 1, 0),
(39, 'advertise_google', 'catalog/view/common/header/after', 'extension/advertise/google/google_global_site_tag', 1, 0),
(40, 'advertise_google', 'catalog/view/common/success/after', 'extension/advertise/google/google_dynamic_remarketing_purchase', 1, 0),
(41, 'advertise_google', 'catalog/view/product/product/after', 'extension/advertise/google/google_dynamic_remarketing_product', 1, 0),
(42, 'advertise_google', 'catalog/view/product/search/after', 'extension/advertise/google/google_dynamic_remarketing_searchresults', 1, 0),
(43, 'advertise_google', 'catalog/view/product/category/after', 'extension/advertise/google/google_dynamic_remarketing_category', 1, 0),
(44, 'advertise_google', 'catalog/view/common/home/after', 'extension/advertise/google/google_dynamic_remarketing_home', 1, 0),
(45, 'advertise_google', 'catalog/view/checkout/cart/after', 'extension/advertise/google/google_dynamic_remarketing_cart', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_extension`
--

CREATE TABLE `ocn8_extension` (
  `extension_id` int(11) NOT NULL,
  `type` varchar(32) NOT NULL,
  `code` varchar(32) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_extension`
--

INSERT INTO `ocn8_extension` (`extension_id`, `type`, `code`) VALUES
(1, 'payment', 'cod'),
(2, 'total', 'shipping'),
(3, 'total', 'sub_total'),
(4, 'total', 'tax'),
(5, 'total', 'total'),
(6, 'module', 'banner'),
(7, 'module', 'carousel'),
(8, 'total', 'credit'),
(9, 'shipping', 'flat'),
(10, 'total', 'handling'),
(11, 'total', 'low_order_fee'),
(12, 'total', 'coupon'),
(13, 'module', 'category'),
(14, 'module', 'account'),
(15, 'total', 'reward'),
(16, 'total', 'voucher'),
(17, 'payment', 'free_checkout'),
(18, 'module', 'featured'),
(19, 'module', 'slideshow'),
(20, 'theme', 'default'),
(21, 'dashboard', 'activity'),
(22, 'dashboard', 'sale'),
(23, 'dashboard', 'recent'),
(24, 'dashboard', 'order'),
(25, 'dashboard', 'online'),
(26, 'dashboard', 'map'),
(27, 'dashboard', 'customer'),
(28, 'dashboard', 'chart'),
(29, 'report', 'sale_coupon'),
(31, 'report', 'customer_search'),
(32, 'report', 'customer_transaction'),
(33, 'report', 'product_purchased'),
(34, 'report', 'product_viewed'),
(35, 'report', 'sale_return'),
(36, 'report', 'sale_order'),
(37, 'report', 'sale_shipping'),
(38, 'report', 'sale_tax'),
(39, 'report', 'customer_activity'),
(40, 'report', 'customer_order'),
(41, 'report', 'customer_reward'),
(42, 'advertise', 'google'),
(43, 'payment', 'moyasar3');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_extension_install`
--

CREATE TABLE `ocn8_extension_install` (
  `extension_install_id` int(11) NOT NULL,
  `extension_download_id` int(11) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_extension_install`
--

INSERT INTO `ocn8_extension_install` (`extension_install_id`, `extension_download_id`, `filename`, `date_added`) VALUES
(1, 0, 'ocmod-twig-debug3.0.0.0.ocmod.zip', '2022-06-09 14:04:53');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_extension_path`
--

CREATE TABLE `ocn8_extension_path` (
  `extension_path_id` int(11) NOT NULL,
  `extension_install_id` int(11) NOT NULL,
  `path` varchar(255) NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_filter`
--

CREATE TABLE `ocn8_filter` (
  `filter_id` int(11) NOT NULL,
  `filter_group_id` int(11) NOT NULL,
  `sort_order` int(3) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_filter_description`
--

CREATE TABLE `ocn8_filter_description` (
  `filter_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `filter_group_id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_filter_group`
--

CREATE TABLE `ocn8_filter_group` (
  `filter_group_id` int(11) NOT NULL,
  `sort_order` int(3) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_filter_group_description`
--

CREATE TABLE `ocn8_filter_group_description` (
  `filter_group_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_geo_zone`
--

CREATE TABLE `ocn8_geo_zone` (
  `geo_zone_id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `description` varchar(255) NOT NULL,
  `date_added` datetime NOT NULL,
  `date_modified` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_geo_zone`
--

INSERT INTO `ocn8_geo_zone` (`geo_zone_id`, `name`, `description`, `date_added`, `date_modified`) VALUES
(3, 'UK VAT Zone', 'UK VAT', '2009-01-06 23:26:25', '2010-02-26 22:33:24'),
(4, 'UK Shipping', 'UK Shipping Zones', '2009-06-23 01:14:53', '2010-12-15 15:18:13');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_googleshopping_category`
--

CREATE TABLE `ocn8_googleshopping_category` (
  `google_product_category` varchar(10) NOT NULL,
  `store_id` int(11) NOT NULL DEFAULT 0,
  `category_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_googleshopping_product`
--

CREATE TABLE `ocn8_googleshopping_product` (
  `product_advertise_google_id` int(11) UNSIGNED NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `store_id` int(11) NOT NULL DEFAULT 0,
  `has_issues` tinyint(1) DEFAULT NULL,
  `destination_status` enum('pending','approved','disapproved') NOT NULL DEFAULT 'pending',
  `impressions` int(11) NOT NULL DEFAULT 0,
  `clicks` int(11) NOT NULL DEFAULT 0,
  `conversions` int(11) NOT NULL DEFAULT 0,
  `cost` decimal(15,4) NOT NULL DEFAULT 0.0000,
  `conversion_value` decimal(15,4) NOT NULL DEFAULT 0.0000,
  `google_product_category` varchar(10) DEFAULT NULL,
  `condition` enum('new','refurbished','used') DEFAULT NULL,
  `adult` tinyint(1) DEFAULT NULL,
  `multipack` int(11) DEFAULT NULL,
  `is_bundle` tinyint(1) DEFAULT NULL,
  `age_group` enum('newborn','infant','toddler','kids','adult') DEFAULT NULL,
  `color` int(11) DEFAULT NULL,
  `gender` enum('male','female','unisex') DEFAULT NULL,
  `size_type` enum('regular','petite','plus','big and tall','maternity') DEFAULT NULL,
  `size_system` enum('AU','BR','CN','DE','EU','FR','IT','JP','MEX','UK','US') DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `is_modified` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_googleshopping_product`
--

INSERT INTO `ocn8_googleshopping_product` (`product_advertise_google_id`, `product_id`, `store_id`, `has_issues`, `destination_status`, `impressions`, `clicks`, `conversions`, `cost`, `conversion_value`, `google_product_category`, `condition`, `adult`, `multipack`, `is_bundle`, `age_group`, `color`, `gender`, `size_type`, `size_system`, `size`, `is_modified`) VALUES
(1, 50, 0, NULL, 'pending', 0, 0, 0, '0.0000', '0.0000', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0),
(2, 51, 0, NULL, 'pending', 0, 0, 0, '0.0000', '0.0000', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0),
(3, 52, 0, NULL, 'pending', 0, 0, 0, '0.0000', '0.0000', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0),
(4, 53, 0, NULL, 'pending', 0, 0, 0, '0.0000', '0.0000', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0),
(5, 54, 0, NULL, 'pending', 0, 0, 0, '0.0000', '0.0000', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_googleshopping_product_status`
--

CREATE TABLE `ocn8_googleshopping_product_status` (
  `product_id` int(11) NOT NULL DEFAULT 0,
  `store_id` int(11) NOT NULL DEFAULT 0,
  `product_variation_id` varchar(64) NOT NULL DEFAULT '',
  `destination_statuses` text NOT NULL,
  `data_quality_issues` text NOT NULL,
  `item_level_issues` text NOT NULL,
  `google_expiration_date` int(11) NOT NULL DEFAULT 0
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_googleshopping_product_target`
--

CREATE TABLE `ocn8_googleshopping_product_target` (
  `product_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL DEFAULT 0,
  `advertise_google_target_id` int(11) UNSIGNED NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_googleshopping_target`
--

CREATE TABLE `ocn8_googleshopping_target` (
  `advertise_google_target_id` int(11) UNSIGNED NOT NULL,
  `store_id` int(11) NOT NULL DEFAULT 0,
  `campaign_name` varchar(255) NOT NULL DEFAULT '',
  `country` varchar(2) NOT NULL DEFAULT '',
  `budget` decimal(15,4) NOT NULL DEFAULT 0.0000,
  `feeds` text NOT NULL,
  `status` enum('paused','active') NOT NULL DEFAULT 'paused',
  `date_added` date DEFAULT NULL,
  `roas` int(11) NOT NULL DEFAULT 0
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_information`
--

CREATE TABLE `ocn8_information` (
  `information_id` int(11) NOT NULL,
  `bottom` int(1) NOT NULL DEFAULT 0,
  `sort_order` int(3) NOT NULL DEFAULT 0,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_information`
--

INSERT INTO `ocn8_information` (`information_id`, `bottom`, `sort_order`, `status`) VALUES
(3, 1, 3, 1),
(4, 1, 1, 1),
(5, 1, 4, 0),
(6, 1, 2, 0);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_information_description`
--

CREATE TABLE `ocn8_information_description` (
  `information_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `title` varchar(64) NOT NULL,
  `description` mediumtext NOT NULL,
  `meta_title` varchar(255) NOT NULL,
  `meta_description` varchar(255) NOT NULL,
  `meta_keyword` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_information_description`
--

INSERT INTO `ocn8_information_description` (`information_id`, `language_id`, `title`, `description`, `meta_title`, `meta_description`, `meta_keyword`) VALUES
(5, 2, 'Terms &amp; Conditions', '&lt;p&gt;\r\n	Terms &amp;amp; Conditions&lt;/p&gt;\r\n', 'Terms &amp; Conditions', '', ''),
(3, 2, 'سياسة الخصوصية', '&lt;p&gt;سياسة الخصوصية&lt;/p&gt;&lt;p&gt;&amp;nbsp;مجال&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;تنطبق سياسة الخصوصية هذه على أي أجهزة أو موقع ويب أو تطبيقات أو برامج أو خدمات تقدمها ليفيكسا والتي تشير إلى سياسة الخصوصية أو روابط لها (يشار إليها مجتمعة بـ &quot;خدمات&quot;).&amp;nbsp; تنطبق سياسة الخصوصية على جميع الموظفين والبائعين والمقاولين والشركاء والمستهلكين والمؤثرين وما إلى ذلك بغض النظر عما إذا كنت تستخدم جهاز كمبيوتر أو هاتفًا محمولًا أو جهازًا لوحيًا أو تلفزيونًا أو أي جهاز آخر للوصول إلى خدماتنا.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;المعلومات التي نجمعها&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;نقوم بجمع أنواع مختلفة من المعلومات فيما يتعلق بالخدمات ، بما في ذلك:&lt;/p&gt;&lt;p&gt;&amp;nbsp;المعلومات التي تقدمها لنا مباشرة ؛&lt;/p&gt;&lt;p&gt;&amp;nbsp;المعلومات التي نجمعها حول استخدامك لخدماتنا ؛&amp;nbsp; و&lt;/p&gt;&lt;p&gt;&amp;nbsp;المعلومات التي نحصل عليها من مصادر خارجية.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;نستخدم أيضًا المعلومات التي نجمعها بالطرق الموضحة لك في نقطة الجمع ، أو بطريقة أخرى بموافقتك.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;استخدام المعلومات وتقاسمها&lt;/p&gt;&lt;p&gt;&amp;nbsp;نستخدم المعلومات التي نجمعها (وقد ندمجها مع معلومات أخرى عنك) ، من بين أشياء أخرى:&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;تقديم الخدمات التي تطلبها ؛&lt;/p&gt;&lt;p&gt;&amp;nbsp;فهم طريقة استخدامك للخدمات حتى نتمكن من تحسين تجربتك ؛&amp;nbsp; و&lt;/p&gt;&lt;p&gt;&amp;nbsp;تقديم محتوى وإعلانات مخصصة.&lt;/p&gt;&lt;p&gt;&amp;nbsp;نستخدم أيضًا المعلومات التي نجمعها بالطرق الموضحة لك في نقطة الجمع ، أو بطريقة أخرى بموافقتك.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;قد نشارك معلوماتك مع:&lt;/p&gt;&lt;p&gt;&amp;nbsp;شركاء الأعمال - الشركات الموثوقة التي قد توفر معلومات حول المنتجات والخدمات التي قد تعجبك.&lt;/p&gt;&lt;p&gt;&amp;nbsp;مقدمو الخدمات - الشركات التي تقدم خدمات لـ ليفيكسا أو بالنيابة عنها.&lt;/p&gt;&lt;p&gt;&amp;nbsp;تطبيق القانون - عندما يُطلب منا القيام بذلك أو لحماية مجموعة ليفيكسا ومستخدميها.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;سياسة الخصوصية ليفيكسا&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;تعرف شركة ليفيكسا المحدودة والشركات التابعة لنا (&quot;ليفيكسا&quot; و &quot;نحن&quot; و &quot;لنا&quot;) مدى أهمية الحفاظ على خصوصية عملائنا ، وبالتالي فإننا نتوخى الحذر الشديد بشأن كيفية قيامنا بجمع واستخدام والكشف عن ،&amp;nbsp; نقل وتخزين المعلومات الخاصة بك.&amp;nbsp; تنطبق سياسة الخصوصية على أجهزة مجموعة ليفيكسا أو مواقع الويب أو التطبيقات عبر الإنترنت التي تشير إلى سياسة الخصوصية أو ترتبط بها (يشار إليها مجتمعة باسم &quot;خدماتنا&quot;).&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;تنطبق سياسة الخصوصية هذه على الخدمات ، ولكن سيتم الحصول على أي موافقة إضافية لأنشطة معينة لمعالجة البيانات.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;يرجى ملاحظة أن سياسة الخصوصية تنطبق على استخدامك لأجهزة مجموعة ليفيكسا (وهي من بين الخدمات التي تغطيها سياسة الخصوصية هذه ، إلى جانب مواقعنا الإلكترونية أو تطبيقاتنا أو برامجنا).&amp;nbsp; ينطبق أيضًا بغض النظر عما إذا كنت تستخدم جهاز كمبيوتر أو هاتفًا محمولًا أو جهازًا لوحيًا أو تلفزيونًا أو أي جهاز آخر للوصول إلى خدماتنا.&amp;nbsp; من المهم أن تقرأ سياسة الخصوصية بعناية لأنه في أي وقت تستخدم فيه خدماتنا ، فإنك توافق على الممارسات الموضحة في سياسة الخصوصية.&amp;nbsp; إذا كنت لا توافق على الممارسات الموضحة في سياسة الخصوصية ، فلا يجب عليك استخدام خدماتنا.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;من المهم أيضًا أن تتحقق مرة أخرى كثيرًا من وجود تحديثات لسياسة الخصوصية.&amp;nbsp; إذا قمنا بتحديث سياسة الخصوصية ، واعتبرنا أنها مادية ، فسنقوم بتسليط الضوء عليها من خلال وضع ملاحظة على الخدمات ذات الصلة.&amp;nbsp; من خلال الوصول إلى خدماتنا أو استخدامها بعد تقديمنا لهذا الإشعار ، فإنك توافق على الممارسة (الممارسات) الجديدة المحددة في التحديث.&amp;nbsp; سيكون أحدث إصدار من سياسة الخصوصية متاحًا دائمًا هنا.&amp;nbsp; يمكنك التحقق من &quot;تاريخ السريان&quot; المنشور في الجزء العلوي لمعرفة آخر مرة تم فيها تحديث سياسة الخصوصية.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;ما هي المعلومات التي نجمعها عنك؟&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;المعلومات التي تقدمها مباشرة&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;تمكّنك بعض الخدمات من تزويدنا بالمعلومات مباشرةً.&amp;nbsp; فمثلا:&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;يتيح عدد من خدماتنا للمستخدمين إنشاء حسابات أو ملفات تعريف.&amp;nbsp; فيما يتعلق بهذه الخدمات ، قد نطلب منك تقديم معلومات معينة عنك لإعداد الحساب أو الملف الشخصي.&amp;nbsp; على سبيل المثال ، يمكنك إرسال معلومات معينة عنك ، مثل اسمك وعنوان بريدك الإلكتروني ، عند إنشاء حساب تسجيل دخول معنا.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;تمكّنك بعض خدماتنا من التواصل مع أشخاص آخرين.&amp;nbsp; سيتم نقل هذه الاتصالات وتخزينها على أنظمتنا.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;معلومات حول استخدامك للخدمات&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;بالإضافة إلى المعلومات التي تقدمها ، يجوز لنا جمع معلومات حول استخدامك لخدماتنا من خلال البرامج الموجودة على جهازك والوسائل الأخرى.&amp;nbsp; على سبيل المثال ، قد نجمع:&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;معلومات الجهاز - مثل طراز الجهاز ومعرّفات الجهاز الفريدة الأخرى وعنوان معرف الأجهزة وعنوان المعرف وإصدار نظام التشغيل وإعدادات الجهاز الذي تستخدمه للوصول إلى الخدمات.&amp;nbsp; معلومات السجل - مثل وقت ومدة استخدامك للخدمة ، وشروط استعلام البحث التي تدخلها من خلال الخدمات ، وأي معلومات مخزنة في ملفات تعريف الارتباط التي قمنا بتعيينها على جهازك.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;معلومات الموقع — مثل إشارة التتبع الخاصة بجهازك أو معلومات حول نقاط وصول الواي فاي القريبة والأبراج الخلوية التي قد يتم إرسالها إلينا عند استخدام خدمات معينة.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;معلومات أخرى حول استخدامك للخدمات ، مثل التطبيقات التي تستخدمها والمواقع الإلكترونية التي تزورها وكيفية تفاعلك مع المحتوى المقدم من خلال الخدمة.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;معلومات من مصادر خارجية - قد نتلقى معلومات عنك من المصادر المتاحة علنًا وتجاريًا (وفقًا لما يسمح به القانون) ، والتي قد ندمجها مع المعلومات الأخرى التي نتلقاها منك أو عنك.&amp;nbsp; قد نتلقى أيضًا معلومات عنك من خدمات شبكات التواصل الاجتماعي التابعة لجهات خارجية عندما تختار الاتصال بهذه الخدمات.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;إذا طلبت منتجًا أو خدمة مدفوعة عبر الإنترنت ، فقد تطلب مصادر الجهات الخارجية التي تدير الأنشطة عبر الإنترنت اسمك ومعلومات الاتصال وعنوان (عناوين) الشحن والفواتير ومعلومات بطاقة الائتمان من أجل معالجة طلبك.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;المعلومات الأخرى التي نجمعها - يجوز لنا أيضًا جمع معلومات أخرى عنك أو عن جهازك أو استخدامك للخدمات بالطرق التي نصفها لك في نقطة الجمع أو بموافقتك التي يتم أخذها في الوقت الذي تبدأ فيه استخدام خدماتنا.&amp;nbsp; خدمات.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;يمكنك اختيار عدم تزويدنا بأنواع معينة من المعلومات (على سبيل المثال ، المعلومات التي نطلبها أثناء تسجيل الحساب) ، ولكن القيام بذلك قد يؤثر على قدرتك على استخدام بعض الخدمات.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;كيف نستعمل معلوماتك؟&amp;nbsp; - قد نستخدم المعلومات التي نجمعها للأغراض التالية من أجل:&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;أ.&amp;nbsp; تسجيلك أو تسجيل جهازك في الخدمة ؛&lt;/p&gt;&lt;p&gt;&amp;nbsp;ب.&amp;nbsp; تقديم خدمة أو ميزة تطلبها ؛&lt;/p&gt;&lt;p&gt;&amp;nbsp;ج.&amp;nbsp; تقديم محتوى مخصص وتقديم توصيات بناءً على أنشطتك السابقة على خدماتنا ؛&lt;/p&gt;&lt;p&gt;&amp;nbsp;د.&amp;nbsp; للإعلان ، مثل تقديم إعلانات مخصصة ومحتوى مدعوم (بما في ذلك من خلال خدمتنا الإعلانية&amp;nbsp; ، التي يمكنك معرفة المزيد عن ممارساتها هنا) وإرسال رسائل ترويجية إليك ؛&lt;/p&gt;&lt;p&gt;&amp;nbsp;ه.&amp;nbsp; لتقييم وتحليل سوقنا وعملائنا ومنتجاتنا وخدماتنا (بما في ذلك مطالبتك بآرائك حول منتجاتنا وخدماتنا وإجراء استبيانات العملاء) ؛&lt;/p&gt;&lt;p&gt;‏ F.&amp;nbsp; فهم الطريقة التي يستخدم بها الأشخاص خدماتنا حتى نتمكن من تحسينها وتطوير منتجات وخدمات جديدة ؛&lt;/p&gt;&lt;p&gt;&amp;nbsp;ز.&amp;nbsp; تقديم خدمات الصيانة لجهازك ؛&lt;/p&gt;&lt;p&gt;&amp;nbsp;ح.&amp;nbsp; إجراء سحوبات مجانية على الجوائز أو مسابقات على جوائز أو عروض ترويجية ، وفقًا لما يسمح به القانون ؛&amp;nbsp; وغير ذلك بموافقتك.&lt;/p&gt;&lt;p&gt;&amp;nbsp;أنا.&amp;nbsp; نجمع المعلومات التي نجمعها منك ونستخدمها لأغراض تتفق مع سياسة الخصوصية هذه.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;- لن نكشف عن معلوماتك لأطراف ثالثة لأغراض التسويق أو الأعمال التجارية المستقلة الخاصة بهم دون موافقتك.&amp;nbsp; ومع ذلك ، قد نكشف عن معلوماتك للكيانات التالية دون موافقتك:&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;الشركات التابعة.&amp;nbsp; قد تتم مشاركة معلوماتك بين ليفيكسا والشركات التابعة لها.&lt;/p&gt;&lt;p&gt;&amp;nbsp;شركاء العمل.&amp;nbsp; يجوز لنا أيضًا مشاركة معلوماتك مع شركاء الأعمال الموثوق بهم ، بما في ذلك شركات الاتصالات اللاسلكية.&amp;nbsp; قد تستخدم هذه الكيانات معلوماتك لتزويدك بالخدمات التي تطلبها (على سبيل المثال ، محتوى الفيديو الذي توفره نتفليكس من خلال سمارت تي ڤي) ، وإجراء تنبؤات حول اهتماماتك وقد تزودك بمواد ترويجية وإعلانات ومواد أخرى.&lt;/p&gt;&lt;p&gt;&amp;nbsp;مقدمي الخدمة.&amp;nbsp; قد نكشف أيضًا عن معلوماتك لأطراف ثالثة تقدم خدمات لنا أو نيابة عنا ، مثل الشركات التي تدير مراكز خدمة أو تساعدنا في الفوترة أو ترسل رسائل بريد إلكتروني نيابة عنا.&amp;nbsp; هذه الكيانات محدودة في قدرتها على استخدام معلوماتك لأغراض أخرى غير توفير الخدمات لنا.&lt;/p&gt;&lt;p&gt;&amp;nbsp;الأطراف الأخرى عندما يقتضي القانون ذلك أو حسب الضرورة لحماية خدماتنا.&amp;nbsp; قد تكون هناك حالات نكشف فيها عن معلوماتك لأطراف أخرى من أجل:&lt;/p&gt;&lt;p&gt;&amp;nbsp;أ.&amp;nbsp; الامتثال للقانون أو الاستجابة لإجراء قانوني إلزامي (مثل أمر تفتيش أو أمر محكمة آخر ، وما إلى ذلك) ؛&lt;/p&gt;&lt;p&gt;&amp;nbsp;ب.&amp;nbsp; التحقق من الامتثال للسياسات التي تحكم خدماتنا أو فرضه ؛&amp;nbsp; و&lt;/p&gt;&lt;p&gt;&amp;nbsp;ج.&amp;nbsp; حماية حقوق وممتلكات وسلامة ليفيكسا أو أي من الشركات التابعة لنا أو شركاء الأعمال أو العملاء.&lt;/p&gt;&lt;p&gt;الأطراف الأخرى المرتبطة بمعاملات الشركة.&amp;nbsp; قد نفصح عن معلوماتك لطرف ثالث كجزء من عملية اندماج أو تحويل ، أو في حالة الإفلاس.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;الأطراف الأخرى بموافقتك أو بتوجيه منك.&amp;nbsp; بالإضافة إلى عمليات الكشف الموضحة في سياسة الخصوصية هذه ، يجوز لنا مشاركة معلومات عنك مع أطراف ثالثة عندما توافق على هذه المشاركة أو تطلبها.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;اختياراتك.&amp;nbsp; نقدم مجموعة متنوعة من الخيارات حول كيفية استخدامنا لمعلوماتك.&amp;nbsp; يمكنك اتخاذ خيارات بشأن ما إذا كنت تريد تلقي اتصالات ترويجية منا باتباع إرشادات إلغاء الاشتراك المضمنة في الاتصال.&amp;nbsp; بالإضافة إلى ذلك ، يمكنك أيضًا عمومًا إجراء اختيارات في الإعدادات الخاصة بك في حساب تسجيل الدخول الخاص بك.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;وقد تكون قادرًا على اتخاذ خيارات في خدمات معينة أو عندما نقدم لك إشعارًا في الوقت المناسب.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;ماذا نفعل للحفاظ على المعلومات الخاصة بك آمنة؟&amp;nbsp; لقد وضعنا تدابير مادية وتقنية معقولة لحماية المعلومات التي نجمعها فيما يتعلق بالخدمات.&amp;nbsp; ومع ذلك ، يرجى ملاحظة أنه على الرغم من أننا نتخذ خطوات معقولة لحماية معلوماتك ، فلا يوجد موقع ويب أو إرسال عبر الإنترنت أو نظام كمبيوتر أو اتصال لاسلكي آمن تمامًا.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;الموافقة على النقل الدولي للبيانات: باستخدام أو المشاركة في أي خدمة و / أو تزويدنا بمعلوماتك ، فإنك توافق على جمع ونقل وتخزين ومعالجة معلوماتك خارج بلد إقامتك ، بما يتوافق مع سياسة الخصوصية هذه.&amp;nbsp; يرجى ملاحظة أن قوانين حماية البيانات وغيرها من قوانين البلدان التي قد يتم نقل معلوماتك إليها قد لا تكون شاملة مثل تلك الموجودة في بلدك.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;الوصول إلى معلوماتك: قد يكون لك الحق في طلب تفاصيل حول المعلومات التي نجمعها وتصحيح عدم الدقة في تلك المعلومات.&amp;nbsp; تظل أي حقوق قانونية أخرى للمستخدم غير متأثرة.&amp;nbsp; إذا سمح القانون بذلك ، فقد نفرض عليك رسومًا رمزية لتزويدك بهذه القدرة.&amp;nbsp; قد نرفض معالجة الطلبات التي تكون متكررة بشكل غير معقول ، أو تتطلب جهدًا تقنيًا غير متناسب ، أو تعرض خصوصية الآخرين للخطر ، أو تكون غير عملية للغاية ، أو التي لا يتطلب القانون المحلي الوصول إليها بطريقة أخرى.&amp;nbsp; إذا كنت ترغب في تقديم طلب للوصول إلى معلوماتك ، فيرجى الاتصال بقسم خدمة العملاء لدينا على www.Livixa.com&lt;/p&gt;&lt;p&gt;الاحتفاظ بالبيانات: نتخذ خطوات معقولة لضمان أننا نحتفظ بالمعلومات الخاصة بك فقط طالما كان ذلك ضروريًا للغرض الذي تم جمعها من أجله ، أو كما هو مطلوب بموجب أي عقد أو بموجب القانون المعمول به.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;روابط ومنتجات الجهات الخارجية على خدماتنا: قد ترتبط خدماتنا بمواقع ويب وخدمات تابعة لجهات خارجية خارجة عن سيطرتنا.&amp;nbsp; نحن لسنا مسؤولين عن أمن أو خصوصية أي معلومات يتم جمعها بواسطة مواقع الويب أو الخدمات الأخرى.&amp;nbsp; يجب عليك توخي الحذر ومراجعة بيانات الخصوصية المطبقة على مواقع الويب والخدمات التابعة لجهات خارجية التي تستخدمها.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;قد نوفر لك أيضًا منتجات أو خدمات معينة (على سبيل المثال ، التطبيقات المتاحة من خلال متاجر التطبيقات) التي طورتها جهات خارجية.&amp;nbsp; ليفيكسا ليست مسؤولة بأي شكل من الأشكال عن منتجات أو خدمات الطرف الثالث هذه.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;الأطراف الثالثة التي تقدم محتوى أو إعلانًا أو وظائف على خدماتنا: قد يتم توفير بعض المحتوى والإعلانات والوظائف على خدماتنا من قبل أطراف ثالثة غير تابعة لنا.&amp;nbsp; فمثلا:&lt;/p&gt;&lt;p&gt;&amp;nbsp;أ.&amp;nbsp; نحن نمكّنك من مشاهدة محتوى الفيديو الذي توفره شركات مثل نتفليكس من خلال وسمارت تي ڤي ؛&lt;/p&gt;&lt;p&gt;&amp;nbsp;ب.&amp;nbsp; تقوم الأطراف الخارجية بتطوير التطبيقات التي نوفرها من خلال التطبيقات المختلفة لمجموعة Livixa ؛&lt;/p&gt;&lt;p&gt;&amp;nbsp;ج.&amp;nbsp; قد تقوم جهات خارجية معينة بتقديم الإعلانات أو تتبع الإعلانات التي يراها المستخدمون ، وعدد المرات التي يشاهدون فيها تلك الإعلانات ، وما يفعله المستخدمون استجابةً لها ؛&amp;nbsp; و&lt;/p&gt;&lt;p&gt;&amp;nbsp;د.&amp;nbsp; نحن نمكّنك من مشاركة مواد معينة على الخدمات مع الآخرين من خلال خدمات الشبكات الاجتماعية مثل فيس بوك و تويتر و قوقل + و لينكدان.&lt;/p&gt;&lt;p&gt;&amp;nbsp;ه.&amp;nbsp; قد تجمع هذه الأطراف الثالثة أو تتلقى معلومات معينة حول استخدامك للخدمات ، بما في ذلك من خلال استخدام ملفات تعريف الارتباط والإشارات والتقنيات المماثلة ، وقد يتم جمع هذه المعلومات بمرور الوقت ودمجها مع المعلومات التي تم جمعها عبر مواقع الويب المختلفة والخدمات عبر الإنترنت.&amp;nbsp; تشارك بعض هذه الشركات في برامج مطورة صناعيًا مصممة لتزويد المستهلكين بخيارات حول ما إذا كانوا سيحصلون على إعلانات مستهدفة أم لا.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;إذا قمت بالاتصال بإحدى خدمات الشبكات الاجتماعية ، فقد نتلقى معلومات المصادقة من تلك الخدمة ونخزنها لتمكينك من تسجيل الدخول ، بالإضافة إلى المعلومات الأخرى التي تسمح لنا بتلقيها عند الاتصال بهذه الخدمات.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;أيضًا ، يرجى ملاحظة أنه إذا اخترت الاتصال بخدمة شبكات اجتماعية على جهاز يستخدمه أشخاص بالإضافة إليك ، فقد يتمكن هؤلاء المستخدمون الآخرون من رؤية المعلومات المخزنة أو المعروضة فيما يتعلق بحسابك على خدمة (خدمات الشبكات الاجتماعية).&amp;nbsp; ) التي تتصل بها.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;ملفات تعريف الارتباط والإشارات والتقنيات المماثلة: يجوز لنا ، بالإضافة إلى بعض الجهات الخارجية التي تقدم محتوى أو إعلانًا أو وظائف أخرى على خدماتنا ، استخدام ملفات تعريف الارتباط والإشارات والتقنيات الأخرى مثل إنترنت الأشياء&amp;nbsp; والذكاء الاصطناعي&amp;nbsp; &amp;nbsp;وما إلى ذلك في مناطق معينة من خدماتنا.&lt;/p&gt;&lt;p&gt;ملفات تعريف الارتباط: ملفات تعريف الارتباط هي ملفات صغيرة تخزن المعلومات على جهاز الكمبيوتر أو التلفزيون أو الهاتف المحمول أو أي جهاز آخر.&amp;nbsp; إنها تمكن الكيان الذي وضع ملف تعريف الارتباط على جهازك من التعرف عليك عبر مواقع الويب والخدمات و / أو الأجهزة و / أو جلسات التصفح المختلفة.&lt;/p&gt;&lt;p&gt;&amp;nbsp;تخدم ملفات تعريف الارتباط العديد من الأغراض المفيدة.&amp;nbsp; فمثلا:&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;أ.&amp;nbsp; يمكن أن تتذكر ملفات تعريف الارتباط بيانات اعتماد تسجيل الدخول الخاصة بك حتى لا تضطر إلى إدخال بيانات الاعتماد هذه في كل مرة تقوم فيها بتسجيل الدخول إلى إحدى الخدمات.&lt;/p&gt;&lt;p&gt;&amp;nbsp;ب.&amp;nbsp; تساعدنا ملفات تعريف الارتباط والأطراف الخارجية على فهم أي أجزاء من خدماتنا هي الأكثر شيوعًا لأنها تساعدنا في معرفة الصفحات والميزات التي يصل إليها الزائرون ومقدار الوقت الذي يقضونه على الصفحات.&amp;nbsp; من خلال دراسة هذا النوع من المعلومات ، نكون أكثر قدرة على تكييف الخدمات وتزويدك بتجربة أفضل.&lt;/p&gt;&lt;p&gt;&amp;nbsp;ج.&amp;nbsp; تساعدنا ملفات تعريف الارتباط والجهات الخارجية في فهم الإعلانات التي شاهدتها حتى لا تتلقى الإعلان نفسه في كل مرة تدخل فيها إلى خدمة.&lt;/p&gt;&lt;p&gt;&amp;nbsp;د.&amp;nbsp; تساعدنا ملفات تعريف الارتباط والأطراف الخارجية على تزويدك بالمحتوى والإعلانات ذات الصلة من خلال جمع معلومات حول استخدامك لخدماتنا ومواقع الويب والتطبيقات الأخرى.&lt;/p&gt;&lt;p&gt;&amp;nbsp;عند استخدام متصفح الويب للوصول إلى الخدمات ، يمكنك تكوين متصفحك لقبول جميع ملفات تعريف الارتباط أو رفض جميع ملفات تعريف الارتباط أو إخطارك عند إرسال ملف تعريف ارتباط.&amp;nbsp; يختلف كل متصفح عن الآخر ، لذا تحقق من قائمة &quot;المساعدة&quot; في متصفحك لمعرفة كيفية تغيير تفضيلات ملفات تعريف الارتباط.&amp;nbsp; قد يحتوي نظام تشغيل جهازك على ضوابط إضافية لملفات تعريف الارتباط.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;يرجى ملاحظة ، مع ذلك ، أنه قد يتم تصميم بعض الخدمات للعمل باستخدام ملفات تعريف الارتباط وأن تعطيل ملفات تعريف الارتباط قد يؤثر على قدرتك على استخدام هذه الخدمات أو أجزاء معينة منها.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;التخزين المحلي الآخر: قد نستخدم ، جنبًا إلى جنب مع جهات خارجية معينة ، أنواعًا أخرى من تقنيات التخزين المحلية ، مثل الكائنات المشتركة المحلية (يشار إليها أيضًا باسم &quot;ملفات تعريف الارتباط&quot;) والتخزين المحلي ، فيما يتعلق بخدماتنا.&amp;nbsp; تشبه هذه التقنيات ملفات تعريف الارتباط التي تمت مناقشتها أعلاه من حيث أنها مخزنة على جهازك ويمكن استخدامها لتخزين معلومات معينة حول أنشطتك وتفضيلاتك.&amp;nbsp; ومع ذلك ، قد تستخدم هذه التقنيات أجزاء مختلفة من جهازك من ملفات تعريف الارتباط القياسية ، وبالتالي قد لا تتمكن من التحكم فيها باستخدام أدوات وإعدادات المتصفح القياسية.&amp;nbsp; للحصول على معلومات حول تعطيل أو حذف المعلومات الموجودة في ملفات تعريف الارتباط ، يرجى النقر هنا.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;الأجهزة التنبيهية: يجوز لنا ، جنبًا إلى جنب مع جهات خارجية معينة ، استخدام تقنيات تسمى إشارات التنبيه (أو &quot;وحدات البكسل&quot;) التي تنقل المعلومات من جهازك إلى الخادم.&amp;nbsp; يمكن تضمين البرامج الملحقة للتتبع في المحتوى ومقاطع الفيديو ورسائل البريد الإلكتروني عبر الإنترنت ، ويمكن أن تسمح للخادم بقراءة أنواع معينة من المعلومات من جهازك ، ومعرفة متى شاهدت محتوى معينًا أو رسالة بريد إلكتروني معينة ، وتحديد الوقت والتاريخ اللذين شاهدتهما فيهما&amp;nbsp; المرشد وعنوان المعرف الخاص بجهازك.&amp;nbsp; نستخدم نحن وبعض الجهات الخارجية البرامج الملحقة للتتبع لعدة أغراض ، بما في ذلك تحليل استخدام خدماتنا و (بالاقتران مع ملفات تعريف الارتباط) لتوفير محتوى وإعلانات أكثر ملاءمة لك.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;من خلال الوصول إلى خدماتنا واستخدامها ، فإنك توافق على تخزين ملفات تعريف الارتباط وتقنيات التخزين المحلية الأخرى والإشارات والمعلومات الأخرى على أجهزتك.&amp;nbsp; أنت توافق أيضًا على الوصول إلى ملفات تعريف الارتباط وتقنيات التخزين المحلية والإشارات والمعلومات من قبلنا ومن قبل الأطراف الثالثة المذكورة أعلاه.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;إذا كنت ترغب في تقديم شكوى بشأن تعاملنا مع معلوماتك الشخصية ، فيمكنك الاتصال بنا باستخدام التفاصيل المنصوص عليها في سياسة الخصوصية هذه مع اسمك الكامل وتفاصيل الاتصال ووصف تفصيلي لشكواك.&amp;nbsp; سنبذل جهودًا معقولة للرد عليك في أقرب وقت ممكن للإقرار بشكواك وإبلاغك بالخطوات التالية التي سنتخذها في التعامل مع شكواك.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;مزيد من المعلومات&lt;/p&gt;&lt;p&gt;إذا كان لديك أي أسئلة محددة و:&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;أ) إذا كنت طرفًا خارجيًا مثل عميل أو تاجر وما إلى ذلك في ليفيكسا&lt;/p&gt;&lt;p&gt;&amp;nbsp;يمكنك اختيار زيارة موقعنا في أي وقت للتحقق أو مراجعة أو مراجعة المعلومات أو البيانات التالية التي قدمتها عن طريق الاتصال بنا عبر البريد الإلكتروني أو رقم خط مساعدة العملاء الموجود على موقعنا على الويب www.Livixa.com&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;ب) إذا كنت موظفًا داخليًا في ليفيكسا ، فيرجى الاتصال بمكتب المساعدة الخاص بك&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;تقديم محتوى مخصص وتقديم توصيات: قد نستخدم المعلومات التي نجمعها لتزويدك بمحتوى مخصص وتقديم توصيات تستند جزئيًا إلى أنشطتك السابقة على خدماتنا.&amp;nbsp; على سبيل المثال ، يوفر سمارت تي في الخاص بك توصيات حول محتوى الفيديو الذي قد يعجبك.&amp;nbsp; يرجى زيارة قائمة الإعدادات على جهازك أو الخدمة للحصول على معلومات حول خيارات الخصوصية التي قد تكون متاحة لك فيما يتعلق بهذه الخدمات.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;تحسينها وتطوير منتجات وخدمات جديدة: قد نستخدم المعلومات التي نجمعها لفهم الطريقة التي تستخدم بها المنتجات والخدمات التي نقدمها حتى نتمكن من تحسينها وتطوير منتجات وخدمات جديدة.&amp;nbsp; على سبيل المثال ، قد نستخدم هذه المعلومات لاتخاذ قرارات مستنيرة بشأن تصميم أجهزتنا واختيار مجموعة ليفيكسا ومحتوى الطرف الثالث الذي يمكن الوصول إليه من خلال أجهزتنا وخدماتنا.&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&amp;nbsp;الشركات التابعة: يشير مصطلح &quot;الشركات التابعة&quot; إلى الشركات ذات الصلة بـ ليفيكسا المحدودة. من خلال الملكية المشتركة أو التحكم.&lt;/p&gt;\r\n', 'Privacy Policy', '', ''),
(6, 1, 'Delivery Information', '&lt;p&gt;\r\n	Delivery Information&lt;/p&gt;\r\n', 'Delivery Information', '', ''),
(5, 1, 'Terms &amp; Conditions', '&lt;p&gt;\r\n	Terms &amp;amp; Conditions&lt;/p&gt;\r\n', 'Terms &amp; Conditions', '', ''),
(6, 2, 'معلومات التوصيل والتركيب ', '&lt;p&gt;\r\n	سيقوم الفريق بالتواصل معك وترتيب موعد للتوصيل والتركيب&amp;nbsp;&lt;/p&gt;\r\n', 'معلومات التوصيل والتركيب ', '', ''),
(4, 2, 'عن ليفيكسا', '&lt;p class=&quot;mb-4 feature-desc secondary-color-text&quot; data-en=&quot;It is an intelligent electrical switch for power saving, security, and\r\n                    a modern lifestyle. Working by WIFI protocol and can be controlled remotely from anywhere.&quot; data-arb=&quot;ليفيكسا مفاتيح كهربائية ذكية لتوفير الطاقة والأمن ونمط الحياة الحديث تعمل ببروتوكول واي فاي ويمكن التحكم بها عن بعد من أي مكان يسمح للمستخدم تثبيتها بسهولة ، دون الحاجة إلى سحب الكابلات والمهندسين. \r\n                            &quot; style=&quot;font-family: SemiBoldText; color: var(--secondary-color); font-size: 24px; margin-bottom: 1.5rem !important;&quot;&gt;&lt;span style=&quot;font-size: 18px;&quot;&gt;ل&lt;/span&gt;&lt;span style=&quot;font-size: 18px;&quot;&gt;&lt;span style=&quot;font-size: 18px;&quot;&gt;يفيكسا &lt;/span&gt;مفاتيح كهربائية ذكية لتوفير الطاقة والأمن ونمط الحياة الحديث تعمل ببروتوكول واي فاي ويمكن التحكم بها عن بعد من أي مكان يسمح&lt;/span&gt;&lt;span style=&quot;font-size: 18px;&quot;&gt;&lt;span style=&quot;font-size: 18px;&quot;&gt; &lt;/span&gt;للمستخدم تثبيتها بسهولة ، دون الحاجة إلى سحب &lt;/span&gt;&lt;span style=&quot;font-size: 18px;&quot;&gt;الكابلات &lt;/span&gt;&lt;span style=&quot;font-size: 18px;&quot;&gt;والمهندسين.&lt;/span&gt;&lt;/p&gt;&lt;p class=&quot;mb-4 feature-desc secondary-color-text&quot; data-en=&quot; It allows the user to install easily, without extra pulling cables and engineers. Users can\r\n                    program it based on events, dates, and times through Arabic/English mobile applications. Our\r\n                    switches can control full lightning, air conditioners, sockets, and cameras.&quot; data-arb=&quot;يمكن للمستخدمين برمجتها بناًء على الأحداث والتواريخ والوقت من خلال تطبيقات الهاتف المحمول باللغتين العربية والإنجليزية.\r\n                    &quot; style=&quot;font-family: SemiBoldText; color: var(--secondary-color); font-size: 24px; margin-bottom: 1.5rem !important;&quot;&gt;&lt;span style=&quot;font-size: 18px;&quot;&gt;يمكن للمستخدمين برمجتها بناًء على الأحداث و&lt;/span&gt;&lt;span style=&quot;font-size: 18px;&quot;&gt;التواريخ والوقت من خلال تطبيقات الهاتف المحمول&lt;/span&gt; &lt;span style=&quot;font-size: 18px;&quot;&gt;با&lt;/span&gt;&lt;span style=&quot;font-size: 18px;&quot;&gt;&lt;span style=&quot;font-size: 18px;&quot;&gt;للغتين &lt;/span&gt;العربية والإنجليزية.&lt;/span&gt;&lt;/p&gt;&lt;p class=&quot;mb-4 feature-desc secondary-color-text&quot; data-en=&quot; These products represent a breakthrough for the developments of other groups of peripherals\r\n                    devices. We work to contribute to the development of lifestyle, as it can be easily added to\r\n                    existing products.&quot; data-arb=&quot;يمكن أن تتحكم مفاتيحنا في الإنارة بالكامل ومكيفات الهواء والأفياش.\r\n                    تمثل هذه المنتجات طفرة في تطوير مجموعات أخرى من الأجهزة الطرفية.\r\n                     نعمل على المساهمة في تطوير نمط الحياة ، حيث يمكن إضافتها بسهولة مع المنتجات الحالية .\r\n                    تم تصميم النظام بالكامل من قبل فريق سعودي ليكون مناسًبا لثقافتنا بأسعار في متناول الجميع ومعايير جودة عالية&quot; style=&quot;font-family: SemiBoldText; color: var(--secondary-color); font-size: 24px; margin-bottom: 1.5rem !important;&quot;&gt;&lt;span style=&quot;font-size: 18px;&quot;&gt;يمكن أن تتحكم مفاتيحنا في الإنارة بالكامل ومكيفات الهواء والأفياش. تمثل هذه المنتجات طفرة في&lt;/span&gt; &lt;span style=&quot;font-size: 18px;&quot;&gt;تطوير مجموعات أخرى من الأجهزة الطرفية. نعمل على المساهمة في تطوير نمط الحياة ، حيث يمكن إضافتها بسهولة مع المنتجات الحالية . تم تصميم النظام بالكامل من قبل فريق سعودي ليكو&lt;/span&gt;ن &lt;span style=&quot;font-size: 18px;&quot;&gt;مناسًبا لثقافتنا بأسعار في متناول الجميع ومعايير جودة عالية&lt;/span&gt;&lt;/p&gt;\r\n', 'عن ليفيكسا', '', ''),
(4, 1, 'About Us', '&lt;p class=&quot;mb-4 feature-desc secondary-color-text&quot; data-en=&quot;It is an intelligent electrical switch for power saving, security, and\r\n                    a modern lifestyle. Working by WIFI protocol and can be controlled remotely from anywhere.&quot; data-arb=&quot;ليفيكسا مفاتيح كهربائية ذكية لتوفير الطاقة والأمن ونمط الحياة الحديث تعمل ببروتوكول واي فاي ويمكن التحكم بها عن بعد من أي مكان يسمح للمستخدم تثبيتها بسهولة ، دون الحاجة إلى سحب الكابلات والمهندسين. \r\n                            &quot; style=&quot;font-family: SemiBoldText; color: var(--secondary-color); font-size: 24px; margin-bottom: 1.5rem !important;&quot;&gt;&lt;span style=&quot;font-size: 18px;&quot;&gt;It is an&lt;/span&gt;&lt;span style=&quot;font-size: 18px;&quot;&gt; intelligent el&lt;/span&gt;&lt;span style=&quot;font-size: 18px;&quot;&gt;ectrical switch for power saving, security, and a modern lifestyle. Working by WIFI protocol and can be controlled remotely from anywhere.&lt;/span&gt;&lt;/p&gt;&lt;p class=&quot;mb-4 feature-desc secondary-color-text&quot; data-en=&quot; It allows the user to install easily, without extra pulling cables and engineers. Users can\r\n                    program it based on events, dates, and times through Arabic/English mobile applications. Our\r\n                    switches can control full lightning, air conditioners, sockets, and cameras.&quot; data-arb=&quot;يمكن للمستخدمين برمجتها بناًء على الأحداث والتواريخ والوقت من خلال تطبيقات الهاتف المحمول باللغتين العربية والإنجليزية.\r\n                    &quot; style=&quot;font-family: SemiBoldText; color: var(--secondary-color); font-size: 24px; margin-bottom: 1.5rem !important;&quot;&gt;&lt;span style=&quot;font-size: 18px;&quot;&gt;It allows the user to install easily, without extra pulling cables and engineers. Users can program it based on events, dates, and times through Arabic/English mobile applications. Our switches can control full lightning, air conditioners, sockets, and cameras.&lt;/span&gt;&lt;/p&gt;&lt;p class=&quot;mb-4 feature-desc secondary-color-text&quot; data-en=&quot; These products represent a breakthrough for the developments of other groups of peripherals\r\n                    devices. We work to contribute to the development of lifestyle, as it can be easily added to\r\n                    existing products.&quot; data-arb=&quot;يمكن أن تتحكم مفاتيحنا في الإنارة بالكامل ومكيفات الهواء والأفياش.\r\n                    تمثل هذه المنتجات طفرة في تطوير مجموعات أخرى من الأجهزة الطرفية.\r\n                     نعمل على المساهمة في تطوير نمط الحياة ، حيث يمكن إضافتها بسهولة مع المنتجات الحالية .\r\n                    تم تصميم النظام بالكامل من قبل فريق سعودي ليكون مناسًبا لثقافتنا بأسعار في متناول الجميع ومعايير جودة عالية&quot; style=&quot;font-family: SemiBoldText; color: var(--secondary-color); font-size: 24px; margin-bottom: 1.5rem !important;&quot;&gt;&lt;span style=&quot;font-size: 18px;&quot;&gt;These products represent a breakthrough for the developments of other groups of peripherals devices. We work to contribute to the development of lifestyle, as it can be easily added to existing products.&lt;/span&gt;&lt;/p&gt;\r\n', 'About Us', '', '');
INSERT INTO `ocn8_information_description` (`information_id`, `language_id`, `title`, `description`, `meta_title`, `meta_description`, `meta_keyword`) VALUES
(3, 1, 'Privacy Policy', '&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;b&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;SCOPE&lt;/span&gt;&lt;/u&gt;&lt;/b&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;This Privacy Policy\r\napplies to any devices, website, applications or software, services offered by &lt;/span&gt;&lt;span style=&quot;color:#383838;mso-ansi-language:EN-US&quot;&gt;Livixa&lt;/span&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;, Lloyd, Standard, Promptec, QRG Central Hospital &amp;amp;\r\nResearch Centre and QRG Health City) that refers to or links to the Privacy\r\nPolicy (collectively, our “Services”). The Privacy Policy applies to all\r\nemployees, vendors, contractors, partners, consumers and influencers etc.\r\nregardless of whether you use a computer, mobile phone, tablet, TV, or other\r\ndevice to access our Services.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;b&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;INFORMATION WE\r\nCOLLECT&lt;/span&gt;&lt;/u&gt;&lt;/b&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;We collect various\r\ntypes of information in connection with the Services, including:&lt;br&gt;\r\nInformation you provide directly to us;&lt;br&gt;\r\nInformation we collect about your use of our Services; and&lt;br&gt;\r\nInformation we obtain from third-party sources.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;We also use the\r\ninformation we collect in ways described to you at the point of collection, or\r\notherwise with your consent.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;b&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;USE AND SHARING\r\nOF INFORMATION&lt;/span&gt;&lt;/u&gt;&lt;/b&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&lt;br&gt;\r\nWe use the information we collect (and may combine it with other information\r\nabout you) to, among other things:&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Provide the Services\r\nyou request;&lt;br&gt;\r\nUnderstand the way you use the Services so that we can improve your experience;\r\nand&lt;br&gt;\r\nProvide customized content and advertising.&lt;br&gt;\r\nWe also use the information we collect in ways described to you at the point of\r\ncollection, or otherwise with your consent.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;We may share your\r\ninformation with:&lt;br&gt;\r\nBusiness partners—trusted companies that may provide information about products\r\nand services you might like.&lt;br&gt;\r\nService providers—companies that provide services for or on behalf of the &lt;/span&gt;&lt;span style=&quot;color:#383838;mso-ansi-language:EN-US&quot;&gt;Livixa&lt;/span&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;.&lt;br&gt;\r\nLaw enforcement—when we are required to do so or to protect &lt;/span&gt;&lt;span style=&quot;color:#383838;mso-ansi-language:EN-US&quot;&gt;Livixa&lt;/span&gt;&lt;span style=&quot;color:#383838&quot;&gt; &lt;span lang=&quot;EN-IN&quot;&gt;Group and its users.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/span&gt;&lt;/p&gt;&lt;p align=&quot;center&quot; style=&quot;margin: 0in 0in 7.5pt; text-align: center; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;b&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;LIVIXA PRIVACY POLICY&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/u&gt;&lt;/b&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span style=&quot;color:#383838;mso-ansi-language:EN-US&quot;&gt;Livixa&lt;/span&gt;&lt;span style=&quot;color:#383838&quot;&gt; &lt;span lang=&quot;EN-IN&quot;&gt;Limited and our affiliates (“&lt;/span&gt;&lt;/span&gt;&lt;span style=&quot;color:#383838;mso-ansi-language:EN-US&quot;&gt;Livixa&lt;/span&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;,” “we,” “us,” “our”) know how important is it to\r\nmaintain the privacy of our customers, and we therefore are very cautious about\r\nhow we collect, use, disclose, transfer and store your information. The Privacy\r\nPolicy applies to the &lt;/span&gt;&lt;span style=&quot;color:#383838;mso-ansi-language:EN-US&quot;&gt;Livixa&lt;/span&gt;&lt;span style=&quot;color:#383838&quot;&gt; &lt;span lang=&quot;EN-IN&quot;&gt;Group devices, websites or online\r\napplications that refer to or link to the Privacy Policy (collectively, our\r\n“Services”).&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;This Privacy Policy\r\napplies to the Services, however any additional consent will be obtained for\r\ncertain data processing activities.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Please note that the\r\nPrivacy Policy applies to your usage of &lt;/span&gt;&lt;span style=&quot;color:#383838;\r\nmso-ansi-language:EN-US&quot;&gt;Livixa&lt;/span&gt;&lt;span style=&quot;color:#383838&quot;&gt; &lt;span lang=&quot;EN-IN&quot;&gt;Group devices (which are among the Services covered by this Privacy\r\nPolicy, along with our websites, applications or software). It also applies\r\nregardless of whether you use a computer, mobile phone, tablet, TV, or other\r\ndevice to access our Services. It is important that you read the Privacy Policy\r\ncarefully because anytime you use our Services, you consent to the practices we\r\ndescribe in the Privacy Policy. If you do not agree to the practices described\r\nin the Privacy Policy, you should not use our Services.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;It also is important\r\nthat you check back often for updates to the Privacy Policy. If we update the\r\nPrivacy Policy, and we consider it to be material, we shall highlight the same\r\nby placing a note on relevant Services. By accessing or using our Services\r\nafter we have placed such a notice, you consent to the new practice(s)\r\nidentified in the update. The most current version of the Privacy Policy will\r\nalways be available here&amp;nbsp;. You can check the “effective date” posted at\r\nthe top to see when the Privacy Policy was last updated.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;b&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;What information do\r\nwe collect about you?&lt;/span&gt;&lt;/b&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;b&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Information you\r\nprovide directly&lt;/span&gt;&lt;/b&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Some Services enable\r\nyou to give us information directly. For example:&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;A number of our\r\nServices enable users to create accounts or profiles. In connection with these\r\nServices, we may ask you to provide certain information about yourself to set\r\nup the account or profile. For example, you can submit certain information\r\nabout yourself, such as your name and email address, when you create a login\r\naccount with us.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Some of our Services\r\nenable you to communicate with other people. Those communications will be\r\ntransmitted through and stored on our systems.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;b&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Information about\r\nyour use of the Services&lt;/span&gt;&lt;/b&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;In addition to the\r\ninformation you provide, we may collect information about your use of our\r\nServices through software on your device and other means. For example, we may\r\ncollect:&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Device information&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;—such as your hardware model and other unique\r\ndevice identifiers, MAC address, IP address, operating system version, and\r\nsettings of the device you use to access the Services. Log information-such as\r\nthe time and duration of your use of the Service, search query terms you enter\r\nthrough the Services, and any information stored in cookies that we have set on\r\nyour device.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Location\r\ninformation&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;—such as your\r\ndevice’s GPS signal or information about nearby WiFi access points and cell\r\ntowers that may be transmitted to us when you use certain Services.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Other information\r\nabout your use of the Services, such as the apps you use, the websites you\r\nvisit, and how you interact with content offered through a Service.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Information from\r\nthird-party sources&amp;nbsp;&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;- We\r\nmay receive information about you from publicly and commercially available\r\nsources (as permitted by law), which we may combine with other information we\r\nreceive from or about you. We also may receive information about you from\r\nthird-party social networking services when you choose to connect with those\r\nservices.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;If you order a product\r\nor paid service online, the third-party sources managing online activities may\r\nask for your name, contact information, shipping and billing address(es), and\r\ncredit card information in order to process your order.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Other Information\r\nWe Collect&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&amp;nbsp;- We also may\r\ncollect other information about you, your device, or your use of the Services\r\nin ways that we describe to you at the point of collection or otherwise with\r\nyour consent which is taken at the time you start using our services.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;You can choose not to\r\nprovide us with certain types of information (e.g., information we request\r\nduring account registration), but doing so may affect your ability to use some\r\nServices.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;How do we use your\r\ninformation?&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&amp;nbsp;- We may\r\nuse information we collect for the following purposes to:&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;a. register you or\r\nyour device for a Service;&lt;br&gt;\r\nb. provide a Service or feature you request;&lt;br&gt;\r\nc. provide customised content and make recommendations based on your past\r\nactivities on our Services;&lt;br&gt;\r\nd. for advertising, such as providing customised advertisements and sponsored\r\ncontent (including through our advertising service, AdHub, whose practices you\r\ncan learn more about here) and sending you promotional communications;&lt;br&gt;\r\ne. for assessment and analysis of our market, customers, products, and services\r\n(including asking you for your opinions on our products and services and\r\ncarrying out customer surveys);&lt;br&gt;\r\nf. understand the way people use our Services so that we can improve them and\r\ndevelop new products and services;&lt;br&gt;\r\ng. provide maintenance services for your device;&lt;br&gt;\r\nh. conduct free prize drawings, prize competitions or promotions, as permitted\r\nby law; and otherwise with your consent.&lt;br&gt;\r\ni. combine information we collect from you and use it for purposes consistent\r\nwith this Privacy Policy.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;To whom do we\r\ndisclose your information?&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&amp;nbsp;-\r\nWe will not disclose your information to third parties for their own\r\nindependent marketing or business purposes without your consent. However, we\r\nmay disclose your information to the following entities without your consent:&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Affiliates.&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&amp;nbsp;Your information may be shared amongst\r\nthe &lt;/span&gt;&lt;span style=&quot;color:#383838;mso-ansi-language:EN-US&quot;&gt;Livixa&lt;/span&gt;&lt;span style=&quot;color:#383838&quot;&gt; &lt;span lang=&quot;EN-IN&quot;&gt;and affiliates.&lt;br&gt;\r\n&lt;u&gt;Business Partners&lt;/u&gt;. We also may share your information with trusted\r\nbusiness partners, including wireless carriers. These entities may use your\r\ninformation to provide you with services you request (e.g., video content\r\nprovided by Netflix through SmartTV), make predictions about your interests and\r\nmay provide you with promotional materials, advertisements and other materials.&lt;br&gt;\r\n&lt;u&gt;Service Providers&lt;/u&gt;. We also may disclose your information to third\r\nparties that provide services for or on behalf of us, such as companies that\r\nrun service centres, help us with billing or that send emails on our behalf.\r\nThese entities are limited in their ability to use your information for\r\npurposes other than providing services for us.&lt;br&gt;\r\n&lt;u&gt;Other Parties When Required by Law or as Necessary to Protect Our Services&lt;/u&gt;.\r\nThere may be instances when we disclose your information to other parties to:&lt;br&gt;\r\na. Comply with the law or respond to compulsory legal process (such as a search\r\nwarrant or other court order, etc.);&lt;br&gt;\r\nb. verify or enforce compliance with the policies governing our Services; and&lt;br&gt;\r\nc. Protect the rights, property, or safety of &lt;/span&gt;&lt;/span&gt;&lt;span style=&quot;color:#383838;mso-ansi-language:EN-US&quot;&gt;Livixa&lt;/span&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;, or any of our respective affiliates, business partners,\r\nor customers.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Other Parties in\r\nConnection With Corporate Transactions&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;. We may disclose your information to a third party as\r\npart of a merger or transfer, or in the event of a bankruptcy.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Other Parties With\r\nYour Consent or At Your Direction&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;.\r\nIn addition to the disclosures described in this Privacy Policy, we may share\r\ninformation about you with third parties when you consent to or request such\r\nsharing.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Your Choices&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;. We offer a variety of choices about how we\r\nuse your information. You can make choices about whether to receive promotional\r\ncommunications from us by following the unsubscribe instructions included in\r\nthe communication. In addition, you can also generally make choices in your\r\nsettings in your login account.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;And you may be able to\r\nmake choices in specific Services or when we give you a just-in-time notice.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;What do we do to\r\nkeep your information secure?&amp;nbsp;&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;We have put in place reasonable physical and technical\r\nmeasures to safeguard the information we collect in connection with the\r\nServices. However, please note that although we take reasonable steps to\r\nprotect your information, no website, Internet transmission, computer system or\r\nwireless connection is completely secure.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Consent to\r\nInternational Transfer of Data:&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&amp;nbsp;By\r\nusing or participating in any Service and/or providing us with your\r\ninformation, you consent to the collection, transfer, storage and processing of\r\nyour information outside of your country of residence, consistent with this\r\nPrivacy Policy. Please note that the data protection and other laws of\r\ncountries to which your information may be transferred might not be as\r\ncomprehensive as those in your country.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Accessing Your\r\nInformation:&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&amp;nbsp;You may\r\nhave the right to request details about the information we collect and to\r\ncorrect inaccuracies in that information. Any further statutory user rights\r\nremain unaffected. If permitted by law, we may charge you a small fee for\r\nproviding you with this ability. We may decline to process requests that are\r\nunreasonably repetitive, require disproportionate technical effort, jeopardize\r\nthe privacy of others, are extremely impractical, or for which access is not\r\notherwise required by local law. If you would like to make a request to access\r\nyour information, please contact our customer service department at www.&lt;/span&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838;mso-ansi-language:EN-US&quot;&gt; &lt;/span&gt;&lt;span style=&quot;color:#383838;mso-ansi-language:EN-US&quot;&gt;Livixa&lt;/span&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;.com.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Data Retention:&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&amp;nbsp;We take reasonable steps to ensure that\r\nwe retain information about you only for so long as is necessary for the\r\npurpose for which it was collected, or as required under any contract or by\r\napplicable law.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Third-Party Links\r\nand Products on Our Services:&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&amp;nbsp;Our\r\nServices may link to third-party websites and services that are outside our\r\ncontrol. We are not responsible for the security or privacy of any information\r\ncollected by websites or other services. You should exercise caution, and\r\nreview the privacy statements applicable to the third-party websites and\r\nservices you use.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;We also may make\r\navailable to you certain products or services (e.g., apps available through app\r\nstores) developed by third parties. &lt;/span&gt;&lt;span style=&quot;color:#383838;\r\nmso-ansi-language:EN-US&quot;&gt;Livixa&lt;/span&gt;&lt;span style=&quot;color:#383838&quot;&gt; &lt;span lang=&quot;EN-IN&quot;&gt;is not responsible in any manner whatsoever for these third-party\r\nproducts or services.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Third Parties that\r\nProvide Content, Advertising or Functionality on Our Services:&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&amp;nbsp;Some of the content, advertising, and\r\nfunctionality on our Services may be provided by third parties that are not\r\naffiliated with us. For example:&lt;br&gt;\r\na. We enable you to watch video content provided by companies such as Netflix\r\nthrough SmartTV;&lt;br&gt;\r\nb. Third parties develop apps that we make available through the various apps\r\nof the &lt;/span&gt;&lt;span style=&quot;color:#383838;mso-ansi-language:EN-US&quot;&gt;Livixa&lt;/span&gt;&lt;span style=&quot;color:#383838&quot;&gt; &lt;span lang=&quot;EN-IN&quot;&gt;Group;&lt;br&gt;\r\nc. Certain third parties may serve advertising or keep track of which\r\nadvertisements users see, how often they see those advertisements, and what\r\nusers do in response to them; and&lt;br&gt;\r\nd. We enable you to share certain materials on the Services with others through\r\nsocial networking services such as Facebook, Twitter, Google +, and LinkedIn.&lt;br&gt;\r\ne. These third-parties may collect or receive certain information about your\r\nuse of the Services, including through the use of cookies, beacons, and similar\r\ntechnologies, and this information may be collected over time and combined with\r\ninformation collected across different websites and online services. Some of\r\nthese companies participate in industry-developed programs designed to provide\r\nconsumers choices about whether to receive targeted advertising.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;If you connect with a\r\nsocial networking service, we may receive and store authentication information\r\nfrom that service to enable you to log in, as well as other information that\r\nyou allow us to receive when you connect with these services.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Also, please note that\r\nif you choose to connect with a social networking service on a device used by\r\npeople in addition to you, those other users may be able to see information\r\nstored or displayed in connection with your account on the social networking\r\nservice(s) with which you connect.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Cookies, Beacons\r\nand Similar Technologies:&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&amp;nbsp;We,\r\nas well as certain third parties that provide content, advertising, or other\r\nfunctionality on our Services, may use cookies, beacons, and other technologies\r\nsuch as internet of things (IOT), Blockchain, Artificial Intelligence (AI)\r\netc.in certain areas of our Services.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Cookies:&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&amp;nbsp;Cookies are small files that store\r\ninformation on your computer, TV, mobile phone or other device. They enable the\r\nentity that put the cookie on your device to recognise you across different\r\nwebsites, services, devices, and/or browsing sessions.&lt;br&gt;\r\nCookies serve many useful purposes. For example:&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;a. Cookies can\r\nremember your sign-in credentials so you don’t have to enter those credentials\r\neach time you log on to a service.&lt;br&gt;\r\nb. Cookies help us and third parties understand which parts of our Services are\r\nthe most popular because they help us to see which pages and features visitors\r\nare accessing and how much time they are spending on the pages. By studying\r\nthis kind of information, we are better able to adapt the Services and provide\r\nyou with a better experience.&lt;br&gt;\r\nc. Cookies help us and third parties understand which ads you have seen so that\r\nyou don’t receive the same ad each time you access a Service.&lt;br&gt;\r\nd. Cookies help us and third parties provide you with relevant content and\r\nadvertising by collecting information about your use of our Services and other\r\nwebsites and apps.&lt;br&gt;\r\nWhen you use a web browser to access the Services, you can configure your\r\nbrowser to accept all cookies, reject all cookies, or notify you when a cookie\r\nis sent. Each browser is different, so check the “Help” menu of your browser to\r\nlearn how to change your cookie preferences. The operating system of your\r\ndevice may contain additional controls for cookies.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Please note, however,\r\nthat some Services may be designed to work using cookies and that disabling\r\ncookies may affect your ability to use those Services, or certain parts of\r\nthem.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Other Local\r\nStorage:&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&amp;nbsp;We, along with\r\ncertain third parties, may use other kinds of local storage technologies, such\r\nas Local Shared Objects (also referred to as “Flash cookies”) and HTML5 local\r\nstorage, in connection with our Services. These technologies are similar to the\r\ncookies discussed above in that they are stored on your device and can be used\r\nto store certain information about your activities and preferences. However,\r\nthese technologies may make use of different parts of your device from standard\r\ncookies, and so you might not be able to control them using standard browser\r\ntools and settings. For information about disabling or deleting information\r\ncontained in Flash cookies, please click here.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Beacons:&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&amp;nbsp;We, along with certain third parties,\r\nalso may use technologies called beacons (or “pixels”) that communicate\r\ninformation from your device to a server. Beacons can be embedded in online\r\ncontent, videos, and emails, and can allow a server to read certain types of\r\ninformation from your device, know when you have viewed particular content or a\r\nparticular email message, determine the time and date on which you viewed the\r\nbeacon, and the IP address of your device. We and certain third parties use\r\nbeacons for a variety of purposes, including to analyse the use of our Services\r\nand (in conjunction with cookies) to provide content and ads that are more\r\nrelevant to you.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;By accessing and using\r\nour Services, you consent to the storage of cookies, other local storage\r\ntechnologies, beacons and other information on your devices. You also consent\r\nto the access of such cookies, local storage technologies, beacons and\r\ninformation by us and by the third parties mentioned above.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;If you wish to make a\r\ncomplaint about our handling of your personal information, you may contact us\r\nusing the details set out in this Privacy Policy with your full name and\r\ncontact details and a detailed description of your complaint. We will use\r\nreasonable efforts to respond to you as soon as possible to acknowledge your complaint\r\nand inform you of the next steps we will take in dealing with your complaint.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Further information&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;If you have any\r\nspecific questions and:&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;b&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;a) If you are an\r\nexternal party such as customer, dealer etc. of &lt;/span&gt;&lt;/b&gt;&lt;span style=&quot;color:#383838;mso-ansi-language:EN-US&quot;&gt;Livixa&lt;/span&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&lt;br&gt;\r\nYou may choose to visit our site at any time to check or review or revise the\r\nfollowing information or data provided by you by contacting us via email or\r\ncustomer helpline number given on our website www.&lt;/span&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838;mso-ansi-language:EN-US&quot;&gt; &lt;/span&gt;&lt;span style=&quot;color:#383838;\r\nmso-ansi-language:EN-US&quot;&gt;Livixa&lt;/span&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;.com.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;b&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;b) If you are\r\ninternal staff of &lt;/span&gt;&lt;/b&gt;&lt;span style=&quot;color:#383838;mso-ansi-language:EN-US&quot;&gt;Livixa&lt;/span&gt;&lt;b&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;, please contact your respective helpdesk&lt;/span&gt;&lt;/b&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Provide customised\r\ncontent and make recommendations:&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&amp;nbsp;We\r\nmay use information we collect to provide you with customised content and make\r\nrecommendations that are based in part on your past activities on our Services.\r\nFor example, Your SmartTV provides recommendations about video content you\r\nmight like. Please visit the settings menu on your device or service for\r\ninformation about privacy choices you may have available to you in connection\r\nwith these services.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Improve them and\r\ndevelop new products and services:&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:\r\n#383838&quot;&gt;&amp;nbsp;We may use information we collect to understand the way you use\r\nthe products and services we offer so that we can improve them and develop new\r\nproducts and services. For example, we may use this information to make\r\nbetter-informed decisions about the design of our devices and the selection of &lt;/span&gt;&lt;span style=&quot;color:#383838;mso-ansi-language:EN-US&quot;&gt;Livixa&lt;/span&gt;&lt;span style=&quot;color:#383838&quot;&gt; &lt;span lang=&quot;EN-IN&quot;&gt;Group and third-party content that is\r\naccessible through our devices and services.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;margin: 0in 0in 7.5pt; background-image: initial; background-position: initial; background-size: initial; background-repeat: initial; background-attachment: initial; background-origin: initial; background-clip: initial;&quot;&gt;&lt;u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;Affiliates:&lt;/span&gt;&lt;/u&gt;&lt;span lang=&quot;EN-IN&quot; style=&quot;color:#383838&quot;&gt;&amp;nbsp;The term ‘affiliates’ refers to\r\ncompanies related to &lt;/span&gt;&lt;span style=&quot;color:#383838;mso-ansi-language:EN-US&quot;&gt;Livixa&lt;/span&gt;&lt;span style=&quot;color:#383838&quot;&gt; &lt;span lang=&quot;EN-IN&quot;&gt;Ltd. by common ownership or control.&lt;o:p&gt;&lt;/o:p&gt;&lt;/span&gt;&lt;/span&gt;&lt;/p&gt;&lt;p&gt;\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n&lt;/p&gt;&lt;p class=&quot;MsoNormal&quot;&gt;&lt;br&gt;&lt;/p&gt;\r\n', 'Privacy Policy', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_information_to_layout`
--

CREATE TABLE `ocn8_information_to_layout` (
  `information_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  `layout_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_information_to_layout`
--

INSERT INTO `ocn8_information_to_layout` (`information_id`, `store_id`, `layout_id`) VALUES
(4, 0, 4),
(6, 0, 0),
(3, 0, 0),
(5, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_information_to_store`
--

CREATE TABLE `ocn8_information_to_store` (
  `information_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_information_to_store`
--

INSERT INTO `ocn8_information_to_store` (`information_id`, `store_id`) VALUES
(3, 0),
(4, 0),
(5, 0),
(6, 0);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_language`
--

CREATE TABLE `ocn8_language` (
  `language_id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `code` varchar(5) NOT NULL,
  `locale` varchar(255) NOT NULL,
  `image` varchar(64) NOT NULL,
  `directory` varchar(32) NOT NULL,
  `sort_order` int(3) NOT NULL DEFAULT 0,
  `status` tinyint(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_language`
--

INSERT INTO `ocn8_language` (`language_id`, `name`, `code`, `locale`, `image`, `directory`, `sort_order`, `status`) VALUES
(1, 'English', 'en-gb', 'en-US,en_US.UTF-8,en_US,en-gb,english', 'gb.png', 'english', 1, 1),
(2, 'Arabic', 'ar', 'ar.UTF-8,ar,ar,arabic', '', '', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_layout`
--

CREATE TABLE `ocn8_layout` (
  `layout_id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_layout`
--

INSERT INTO `ocn8_layout` (`layout_id`, `name`) VALUES
(1, 'Home'),
(2, 'Product'),
(3, 'Category'),
(4, 'Default'),
(5, 'Manufacturer'),
(6, 'Account'),
(7, 'Checkout'),
(8, 'Contact'),
(9, 'Sitemap'),
(10, 'Affiliate'),
(11, 'Information'),
(12, 'Compare'),
(13, 'Search');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_layout_module`
--

CREATE TABLE `ocn8_layout_module` (
  `layout_module_id` int(11) NOT NULL,
  `layout_id` int(11) NOT NULL,
  `code` varchar(64) NOT NULL,
  `position` varchar(14) NOT NULL,
  `sort_order` int(3) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_layout_module`
--

INSERT INTO `ocn8_layout_module` (`layout_module_id`, `layout_id`, `code`, `position`, `sort_order`) VALUES
(2, 4, '0', 'content_top', 0),
(3, 4, '0', 'content_top', 1),
(20, 5, '0', 'column_left', 2),
(69, 10, 'account', 'column_right', 1),
(68, 6, 'account', 'column_right', 1),
(67, 1, 'carousel.29', 'content_top', 3),
(66, 1, 'slideshow.27', 'content_top', 1),
(65, 1, 'featured.28', 'content_top', 2),
(72, 3, 'category', 'column_left', 1),
(73, 3, 'banner.30', 'column_left', 2);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_layout_route`
--

CREATE TABLE `ocn8_layout_route` (
  `layout_route_id` int(11) NOT NULL,
  `layout_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  `route` varchar(64) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_layout_route`
--

INSERT INTO `ocn8_layout_route` (`layout_route_id`, `layout_id`, `store_id`, `route`) VALUES
(38, 6, 0, 'account/%'),
(17, 10, 0, 'affiliate/%'),
(44, 3, 0, 'product/category'),
(42, 1, 0, 'common/home'),
(20, 2, 0, 'product/product'),
(24, 11, 0, 'information/information'),
(23, 7, 0, 'checkout/%'),
(31, 8, 0, 'information/contact'),
(32, 9, 0, 'information/sitemap'),
(34, 4, 0, ''),
(45, 5, 0, 'product/manufacturer'),
(52, 12, 0, 'product/compare'),
(53, 13, 0, 'product/search');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_length_class`
--

CREATE TABLE `ocn8_length_class` (
  `length_class_id` int(11) NOT NULL,
  `value` decimal(15,8) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_length_class`
--

INSERT INTO `ocn8_length_class` (`length_class_id`, `value`) VALUES
(1, '1.00000000');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_length_class_description`
--

CREATE TABLE `ocn8_length_class_description` (
  `length_class_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `title` varchar(32) NOT NULL,
  `unit` varchar(4) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_length_class_description`
--

INSERT INTO `ocn8_length_class_description` (`length_class_id`, `language_id`, `title`, `unit`) VALUES
(1, 1, 'Centimeter', 'cm'),
(1, 2, 'Centimeter', 'cm');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_location`
--

CREATE TABLE `ocn8_location` (
  `location_id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `address` text NOT NULL,
  `telephone` varchar(32) NOT NULL,
  `fax` varchar(32) NOT NULL,
  `geocode` varchar(32) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `open` text NOT NULL,
  `comment` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_location`
--

INSERT INTO `ocn8_location` (`location_id`, `name`, `address`, `telephone`, `fax`, `geocode`, `image`, `open`, `comment`) VALUES
(1, 'Livixa Store ', 'Abi Baker Alsiddique Street ', '00966549801399', '', '', 'catalog/livixa logo.jpg', '24/7', '');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_manufacturer`
--

CREATE TABLE `ocn8_manufacturer` (
  `manufacturer_id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `sort_order` int(3) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_manufacturer`
--

INSERT INTO `ocn8_manufacturer` (`manufacturer_id`, `name`, `image`, `sort_order`) VALUES
(10, 'Livixa ', 'catalog/demo/sony_logo.jpg', 0);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_manufacturer_to_store`
--

CREATE TABLE `ocn8_manufacturer_to_store` (
  `manufacturer_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_manufacturer_to_store`
--

INSERT INTO `ocn8_manufacturer_to_store` (`manufacturer_id`, `store_id`) VALUES
(10, 0);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_marketing`
--

CREATE TABLE `ocn8_marketing` (
  `marketing_id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `description` text NOT NULL,
  `code` varchar(64) NOT NULL,
  `clicks` int(5) NOT NULL DEFAULT 0,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_marketing`
--

INSERT INTO `ocn8_marketing` (`marketing_id`, `name`, `description`, `code`, `clicks`, `date_added`) VALUES
(1, 'خدمة البيوت الذكية', 'تتكون هذه الخدمة من ثلاث باقات (الباقة الاقتصادية ، الباقة المتوسطة ، الباقة المتقدمة) وذلك لمدة اشتراكات شهرية أو ربع سنوية أو سنوية حيث أن المستخدم يحصل على الجهاز الذكي مجانا ', 'Subs-Marketing', 0, '2022-06-05 13:35:00');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_modification`
--

CREATE TABLE `ocn8_modification` (
  `modification_id` int(11) NOT NULL,
  `extension_install_id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `code` varchar(64) NOT NULL,
  `author` varchar(64) NOT NULL,
  `version` varchar(32) NOT NULL,
  `link` varchar(255) NOT NULL,
  `xml` mediumtext NOT NULL,
  `status` tinyint(1) NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_modification`
--

INSERT INTO `ocn8_modification` (`modification_id`, `extension_install_id`, `name`, `code`, `author`, `version`, `link`, `xml`, `status`, `date_added`) VALUES
(1, 1, '[OCN] Twig Debug', 'ocn__twig_debug', 'Hkr', '3.0.0.0', 'https://forum.opencart.name/resources/54/', '<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<modification>\n    <name>[OCN] Twig Debug</name>\n    <code>ocn__twig_debug</code>\n    <version>3.0.0.0</version>\n    <author>Hkr</author>\n    <link>https://forum.opencart.name/resources/54/</link>\n    <date>29.07.2020</date>\n\n    <file path=\"system/library/template/twig.php\">\n        <operation>\n            <search trim=\"true\"><![CDATA[\'debug\'       => false,]]></search>\n            <add position=\"replace\" trim=\"true\"><![CDATA[\n                // ocn__twig_debug\n                \'debug\'       => true,\n                // ocn__twig_debug\n            ]]></add>\n        </operation>\n        <operation>\n            <search trim=\"true\"><![CDATA[$twig = new \\Twig\\Environment($loader, $config);]]></search>\n            <add position=\"after\" trim=\"true\"><![CDATA[\n                // ocn__twig_debug\n                $twig->addExtension(new \\Twig\\Extension\\DebugExtension());\n                // ocn__twig_debug\n            ]]></add>\n        </operation>\n    </file>\n</modification>\n', 1, '2022-06-09 14:04:56');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_module`
--

CREATE TABLE `ocn8_module` (
  `module_id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `code` varchar(32) NOT NULL,
  `setting` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_module`
--

INSERT INTO `ocn8_module` (`module_id`, `name`, `code`, `setting`) VALUES
(30, 'Category', 'banner', '{\"name\":\"Category\",\"banner_id\":\"6\",\"width\":\"182\",\"height\":\"182\",\"status\":\"1\"}'),
(29, 'Home Page', 'carousel', '{\"name\":\"Home Page\",\"banner_id\":\"8\",\"width\":\"130\",\"height\":\"100\",\"status\":\"1\"}'),
(28, 'Home Page', 'featured', '{\"name\":\"Home Page\",\"product\":[\"43\",\"40\",\"42\",\"30\"],\"limit\":\"4\",\"width\":\"200\",\"height\":\"200\",\"status\":\"1\"}'),
(27, 'Home Page', 'slideshow', '{\"name\":\"Home Page\",\"banner_id\":\"7\",\"width\":\"1140\",\"height\":\"380\",\"status\":\"1\"}'),
(31, 'Banner 1', 'banner', '{\"name\":\"Banner 1\",\"banner_id\":\"6\",\"width\":\"182\",\"height\":\"182\",\"status\":\"1\"}');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_option`
--

CREATE TABLE `ocn8_option` (
  `option_id` int(11) NOT NULL,
  `type` varchar(32) NOT NULL,
  `sort_order` int(3) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_option`
--

INSERT INTO `ocn8_option` (`option_id`, `type`, `sort_order`) VALUES
(13, 'select', 0);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_option_description`
--

CREATE TABLE `ocn8_option_description` (
  `option_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_option_description`
--

INSERT INTO `ocn8_option_description` (`option_id`, `language_id`, `name`) VALUES
(13, 1, 'Subscription'),
(13, 2, 'الاشتراكات');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_option_value`
--

CREATE TABLE `ocn8_option_value` (
  `option_value_id` int(11) NOT NULL,
  `option_id` int(11) NOT NULL,
  `image` varchar(255) NOT NULL,
  `sort_order` int(3) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_option_value`
--

INSERT INTO `ocn8_option_value` (`option_value_id`, `option_id`, `image`, `sort_order`) VALUES
(57, 13, '', 8),
(58, 13, '', 9),
(55, 13, '', 6),
(54, 13, '', 5),
(52, 13, '', 3),
(51, 13, '', 2);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_option_value_description`
--

CREATE TABLE `ocn8_option_value_description` (
  `option_value_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `option_id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_option_value_description`
--

INSERT INTO `ocn8_option_value_description` (`option_value_id`, `language_id`, `option_id`, `name`) VALUES
(58, 2, 13, 'الخدمة المتقدمة  اشتراك لمدة 12 شهر'),
(58, 1, 13, 'High Service 12 months subscription'),
(57, 2, 13, 'الخدمة المتقدمة  اشتراك لمدة  ثلاثة أشهر'),
(57, 1, 13, 'High Service 3 months subscription'),
(55, 2, 13, 'الخدمة المتوسطة  اشتراك لمدة 12 شهر'),
(55, 1, 13, 'Mid Service 12 months subscription'),
(54, 1, 13, 'Mid Services 3 months subscription '),
(54, 2, 13, 'الخدمة المتوسطة  اشتراك لمدة  ثلاثة أشهر'),
(52, 2, 13, 'الخدمة العادية  اشتراك لمدة 12 شهر'),
(52, 1, 13, 'Initial Services 12 months subscription '),
(51, 1, 13, 'ِInitial Services 3 months subscription '),
(51, 2, 13, 'الخدمة العادية  اشتراك لمدة  ثلاثة أشهر');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_order`
--

CREATE TABLE `ocn8_order` (
  `order_id` int(11) NOT NULL,
  `invoice_no` int(11) NOT NULL DEFAULT 0,
  `invoice_prefix` varchar(26) NOT NULL,
  `store_id` int(11) NOT NULL DEFAULT 0,
  `store_name` varchar(64) NOT NULL,
  `store_url` varchar(255) NOT NULL,
  `customer_id` int(11) NOT NULL DEFAULT 0,
  `customer_group_id` int(11) NOT NULL DEFAULT 0,
  `firstname` varchar(32) NOT NULL,
  `lastname` varchar(32) NOT NULL,
  `email` varchar(96) NOT NULL,
  `telephone` varchar(32) NOT NULL,
  `fax` varchar(32) NOT NULL,
  `custom_field` text NOT NULL,
  `payment_firstname` varchar(32) NOT NULL,
  `payment_lastname` varchar(32) NOT NULL,
  `payment_company` varchar(60) NOT NULL,
  `payment_address_1` varchar(128) NOT NULL,
  `payment_address_2` varchar(128) NOT NULL,
  `payment_city` varchar(128) NOT NULL,
  `payment_postcode` varchar(10) NOT NULL,
  `payment_country` varchar(128) NOT NULL,
  `payment_country_id` int(11) NOT NULL,
  `payment_zone` varchar(128) NOT NULL,
  `payment_zone_id` int(11) NOT NULL,
  `payment_address_format` text NOT NULL,
  `payment_custom_field` text NOT NULL,
  `payment_method` varchar(128) NOT NULL,
  `payment_code` varchar(128) NOT NULL,
  `shipping_firstname` varchar(32) NOT NULL,
  `shipping_lastname` varchar(32) NOT NULL,
  `shipping_company` varchar(40) NOT NULL,
  `shipping_address_1` varchar(128) NOT NULL,
  `shipping_address_2` varchar(128) NOT NULL,
  `shipping_city` varchar(128) NOT NULL,
  `shipping_postcode` varchar(10) NOT NULL,
  `shipping_country` varchar(128) NOT NULL,
  `shipping_country_id` int(11) NOT NULL,
  `shipping_zone` varchar(128) NOT NULL,
  `shipping_zone_id` int(11) NOT NULL,
  `shipping_address_format` text NOT NULL,
  `shipping_custom_field` text NOT NULL,
  `shipping_method` varchar(128) NOT NULL,
  `shipping_code` varchar(128) NOT NULL,
  `comment` text NOT NULL,
  `total` decimal(15,4) NOT NULL DEFAULT 0.0000,
  `order_status_id` int(11) NOT NULL DEFAULT 0,
  `affiliate_id` int(11) NOT NULL,
  `commission` decimal(15,4) NOT NULL,
  `marketing_id` int(11) NOT NULL,
  `tracking` varchar(64) NOT NULL,
  `language_id` int(11) NOT NULL,
  `currency_id` int(11) NOT NULL,
  `currency_code` varchar(3) NOT NULL,
  `currency_value` decimal(15,8) NOT NULL DEFAULT 1.00000000,
  `ip` varchar(40) NOT NULL,
  `forwarded_ip` varchar(40) NOT NULL,
  `user_agent` varchar(255) NOT NULL,
  `accept_language` varchar(255) NOT NULL,
  `date_added` datetime NOT NULL,
  `date_modified` datetime NOT NULL,
  `mobile_api_status` int(11) DEFAULT NULL,
  `subscription_package_id` int(11) NOT NULL,
  `package_price` decimal(20,0) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_order`
--

INSERT INTO `ocn8_order` (`order_id`, `invoice_no`, `invoice_prefix`, `store_id`, `store_name`, `store_url`, `customer_id`, `customer_group_id`, `firstname`, `lastname`, `email`, `telephone`, `fax`, `custom_field`, `payment_firstname`, `payment_lastname`, `payment_company`, `payment_address_1`, `payment_address_2`, `payment_city`, `payment_postcode`, `payment_country`, `payment_country_id`, `payment_zone`, `payment_zone_id`, `payment_address_format`, `payment_custom_field`, `payment_method`, `payment_code`, `shipping_firstname`, `shipping_lastname`, `shipping_company`, `shipping_address_1`, `shipping_address_2`, `shipping_city`, `shipping_postcode`, `shipping_country`, `shipping_country_id`, `shipping_zone`, `shipping_zone_id`, `shipping_address_format`, `shipping_custom_field`, `shipping_method`, `shipping_code`, `comment`, `total`, `order_status_id`, `affiliate_id`, `commission`, `marketing_id`, `tracking`, `language_id`, `currency_id`, `currency_code`, `currency_value`, `ip`, `forwarded_ip`, `user_agent`, `accept_language`, `date_added`, `date_modified`, `mobile_api_status`, `subscription_package_id`, `package_price`) VALUES
(1, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.tracking.me/', 0, 1, 'Sajjad', 'Akbar', 'sajjad.akbar@gmail.com', '03208400072', '', '[]', 'Sajjad', 'Akbar', '', '121 D block PCSIR staff colony', '', 'Lahore', '54000', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Cash On Delivery', 'cod', 'Sajjad', 'Akbar', '', '121 D block PCSIR staff colony', '', 'Lahore', '54000', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '85.0000', 1, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '72.255.38.189', '72.255.38.189', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36', 'en-US,en;q=0.9', '2022-06-01 13:14:11', '2022-06-01 13:14:14', 0, 0, '0'),
(2, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.tracking.me/', 0, 1, 'Faran', 'Ahmed Sukhera', 'fasukhera@gmail.com', '03045527577', '', '[]', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Cash On Delivery', 'cod', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '285.0000', 1, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.160.69.190', '119.160.69.190', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.36', 'en-US,en;q=0.9', '2022-06-02 06:44:36', '2022-06-02 06:44:39', 0, 0, '0'),
(3, 1, 'INV-2021-00', 0, 'Livixa', 'http://livixa.tracking.me/', 0, 1, 'Faran', 'Ahmed Sukhera', 'fasukhera@gmail.com', '03045527577', '', '[]', 'Faran', 'Ahmed Sukhera', 'TMPL', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Cash On Delivery', 'cod', 'Faran', 'Ahmed Sukhera', 'TMPL', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '164.0000', 1, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.73.124.141', '119.73.124.141', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.36', 'en-US,en;q=0.9', '2022-06-02 07:37:50', '2022-06-02 07:37:58', 0, 0, '0'),
(4, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.tracking.me/', 0, 1, 'saleh', 'test', 'saleh@livixa.com', '0549801399', '', '[]', 'saleh', 'test', '', 'qurtubah dist ', '', 'riyadh', '13245', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Online Payment', 'moyasar3', 'saleh', 'test', '', 'qurtubah dist ', '', 'riyadh', '13245', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Flat Shipping Rate', 'flat.flat', 'test ', '285.0000', 0, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '188.54.236.144', '188.54.236.144', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36', 'en-US,en;q=0.9', '2022-06-02 11:48:19', '2022-06-02 11:48:19', 0, 0, '0'),
(5, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.tracking.me/', 0, 1, 'saleh', 'test', 'saleh@livixa.com', '0549801399', '', '[]', 'saleh', 'test', '', 'qurtubah dist ', '', 'riyadh', '13245', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'مدفوعات إلكترونية', 'moyasar3', 'saleh', 'test', '', 'qurtubah dist ', '', 'riyadh', '13245', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'تكلفة ثابتة للشحن', 'flat.flat', 'test ', '56.0000', 0, 0, '0.0000', 0, '', 2, 2, 'USD', '1.00000000', '188.54.236.144', '188.54.236.144', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36', 'en-US,en;q=0.9', '2022-06-02 12:26:38', '2022-06-02 12:26:38', 0, 0, '0'),
(6, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 0, 1, 'test', 'test', 'saleh@livixa.com', '0549801399', '', '[]', 'test', 'test', '', 'test', 'test', 'test', 'test', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'مدفوعات إلكترونية', 'moyasar3', 'test', 'test', '', 'test', 'test', 'test', 'test', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'تكلفة ثابتة للشحن', 'flat.flat', '', '15.0000', 0, 0, '0.0000', 0, '', 2, 2, 'USD', '1.00000000', '188.54.236.144', '188.54.236.144', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36', 'en-US,en;q=0.9', '2022-06-05 13:01:11', '2022-06-05 13:01:11', 0, 0, '0'),
(7, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 1, 1, 'Faran', 'Ahmed Sukhera', 'fasukhera@gmail.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Cash On Delivery', 'cod', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '963.0000', 0, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.160.97.158', '119.160.97.158', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-09 13:45:24', '2022-06-09 13:45:24', 1, 6, '963'),
(8, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 1, 1, 'Faran', 'Ahmed Sukhera', 'fasukhera@gmail.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Cash On Delivery', 'cod', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '963.0000', 0, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.160.97.158', '119.160.97.158', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-09 13:58:39', '2022-06-09 13:58:39', 1, 6, '963'),
(9, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 1, 1, 'Faran', 'Ahmed Sukhera', 'fasukhera@gmail.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Cash On Delivery', 'cod', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '963.0000', 1, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.160.97.158', '119.160.97.158', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-09 14:07:09', '2022-06-09 14:32:44', 1, 6, '963'),
(10, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 1, 1, 'Faran', 'Ahmed Sukhera', 'fasukhera@gmail.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Online Payment', 'moyasar3', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '0.0000', 0, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.160.97.158', '119.160.97.158', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-09 14:37:38', '2022-06-09 14:37:38', 1, 1, '15'),
(11, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 1, 1, 'Faran', 'Ahmed Sukhera', 'fasukhera@gmail.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Free Checkout', 'free_checkout', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '15.0000', 1, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.160.97.158', '119.160.97.158', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-09 14:37:48', '2022-06-09 14:37:51', 1, 1, '15'),
(12, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 1, 1, 'Faran', 'Ahmed Sukhera', 'fasukhera@gmail.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Free Checkout', 'free_checkout', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '0.0000', 1, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.160.97.158', '119.160.97.158', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-09 14:49:02', '2022-06-09 14:49:48', 1, 1, '15'),
(13, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 1, 1, 'Faran', 'Ahmed Sukhera', 'fasukhera@gmail.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Free Checkout', 'free_checkout', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '0.0000', 0, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.160.97.158', '119.160.97.158', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-09 14:51:30', '2022-06-09 14:51:30', 1, 1, '15'),
(14, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 1, 1, 'Faran', 'Ahmed Sukhera', 'fasukhera@gmail.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Free Checkout', 'free_checkout', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '0.0000', 0, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.160.97.158', '119.160.97.158', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-09 14:51:53', '2022-06-09 14:51:53', 1, 1, '15'),
(15, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 1, 1, 'Faran', 'Ahmed Sukhera', 'fasukhera@gmail.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Free Checkout', 'free_checkout', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '0.0000', 0, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.160.97.158', '119.160.97.158', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-09 14:54:45', '2022-06-09 14:54:45', 1, 1, '15'),
(16, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 1, 1, 'Faran', 'Ahmed Sukhera', 'fasukhera@gmail.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Free Checkout', 'free_checkout', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '0.0000', 0, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.160.97.158', '119.160.97.158', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-09 14:55:35', '2022-06-09 14:55:35', 1, 1, '15'),
(17, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 1, 1, 'Faran', 'Ahmed Sukhera', 'fasukhera@gmail.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Free Checkout', 'free_checkout', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '0.0000', 0, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.160.97.158', '119.160.97.158', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-09 15:00:26', '2022-06-09 15:00:26', 1, 1, '15'),
(18, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 1, 1, 'Faran', 'Ahmed Sukhera', 'fasukhera@gmail.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Free Checkout', 'free_checkout', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '0.0000', 0, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.160.97.158', '119.160.97.158', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-09 15:01:00', '2022-06-09 15:01:00', 1, 1, '15'),
(19, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 1, 1, 'Faran', 'Ahmed Sukhera', 'fasukhera@gmail.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Free Checkout', 'free_checkout', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '0.0000', 0, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.160.97.158', '119.160.97.158', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-09 15:02:17', '2022-06-09 15:02:17', 1, 1, '15'),
(20, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 1, 1, 'Faran', 'Ahmed Sukhera', 'fasukhera@gmail.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Free Checkout', 'free_checkout', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '0.0000', 1, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.160.97.158', '119.160.97.158', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-09 15:06:04', '2022-06-09 15:06:06', 1, 1, '15'),
(21, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 1, 1, 'Faran', 'Ahmed Sukhera', 'fasukhera@gmail.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Free Checkout', 'free_checkout', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '0.0000', 0, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.160.97.158', '119.160.97.158', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-09 15:07:51', '2022-06-09 15:07:51', 1, 9, '432'),
(22, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 1, 1, 'Faran', 'Ahmed Sukhera', 'fasukhera@gmail.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Free Checkout', 'free_checkout', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '432.0000', 17, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.160.97.158', '119.160.97.158', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-09 15:09:25', '2022-06-13 07:15:36', 1, 9, '432'),
(23, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 1, 1, 'Faran', 'Ahmed Sukhera', 'fasukhera@gmail.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Free Checkout', 'free_checkout', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '30.0000', 0, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.160.97.158', '119.160.97.158', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-09 15:14:39', '2022-06-09 15:14:39', 1, 1, '30'),
(24, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 1, 1, 'Faran', 'Ahmed Sukhera', 'fasukhera@gmail.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Free Checkout', 'free_checkout', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '30.0000', 0, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.160.97.158', '119.160.97.158', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-09 15:15:13', '2022-06-09 15:15:13', 1, 1, '30'),
(25, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 3, 1, 'saleh', 'Alsaiari', 'saleh@livixa.com', '0549801399', '', '', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'مدفوعات إلكترونية', 'moyasar3', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'تكلفة ثابتة للشحن', 'flat.flat', '', '30.0000', 0, 0, '0.0000', 0, '', 2, 4, 'SAR', '1.00000000', '159.0.208.210', '159.0.208.210', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36', 'en-US,en;q=0.9', '2022-06-13 09:40:21', '2022-06-13 09:40:21', 1, 1, '15'),
(26, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 4, 1, 'Custo', '1', 'customer@gmail.com', '03045527577', '', '', 'Custo', '1', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Free Checkout', 'free_checkout', 'Custo', '1', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '0.0000', 1, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-13 11:10:02', '2022-06-13 11:10:06', NULL, 0, '0'),
(27, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 10, 1, 'Faran', 'Ahmed Sukhera', 'customer12@gmail.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Free Checkout', 'free_checkout', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '15.0000', 1, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.160.96.99', '119.160.96.99', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-13 12:13:12', '2022-06-13 12:13:18', NULL, 1, '15'),
(28, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 3, 1, 'saleh', 'Alsaiari', 'saleh@livixa.com', '0549801399', '', '', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Online Payment', 'moyasar3', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '72.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '58.27.236.26', '58.27.236.26', 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.88 Safari/537.36', 'en-US,en;q=0.9', '2022-06-13 12:25:33', '2022-06-13 12:25:33', NULL, 7, '36'),
(29, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 3, 1, 'saleh', 'Alsaiari', 'saleh@livixa.com', '0549801399', '', '', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Cash On Delivery', 'cod', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '72.0000', 1, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '58.27.236.26', '58.27.236.26', 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.88 Safari/537.36', 'en-US,en;q=0.9', '2022-06-13 12:39:54', '2022-06-13 12:39:57', NULL, 7, '36'),
(30, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 2, 1, 'Sajjad', 'Akbar', 'sajjad.akbar@gmail.com', '03208400072', '', '', 'Sajjad', 'Akbar', '', '121 D block PCSIR staff colony', '', 'Lahore', '54000', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'Sajjad', 'Akbar', '', '121 D block PCSIR staff colony', '', 'Lahore', '54000', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', 'test payment', '30.0000', 0, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.160.98.187', '119.160.98.187', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-GB,en-US;q=0.9,en;q=0.8,ur;q=0.7', '2022-06-13 13:05:02', '2022-06-13 13:05:02', NULL, 1, '30'),
(31, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 2, 1, 'Sajjad', 'Akbar', 'sajjad.akbar@gmail.com', '03208400072', '', '', 'Sajjad', 'Akbar', '', '121 D block PCSIR staff colony', '', 'Lahore', '54000', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'Sajjad', 'Akbar', '', '121 D block PCSIR staff colony', '', 'Lahore', '54000', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', 'test payment', '36.0000', 15, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '119.160.98.187', '119.160.98.187', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-GB,en-US;q=0.9,en;q=0.8,ur;q=0.7', '2022-06-13 13:06:21', '2022-06-13 13:11:39', NULL, 7, '36'),
(32, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 2, 1, 'Sajjad', 'Akbar', 'sajjad.akbar@gmail.com', '03208400072', '', '', 'Sajjad', 'Akbar', '', '121 D block PCSIR staff colony', '', 'Lahore', '54000', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'Sajjad', 'Akbar', '', '121 D block PCSIR staff colony', '', 'Lahore', '54000', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '45.0000', 15, 0, '0.0000', 0, '', 1, 2, 'USD', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-GB,en-US;q=0.9,en;q=0.8,ur;q=0.7', '2022-06-13 14:45:06', '2022-06-13 15:50:14', NULL, 2, '45'),
(33, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 3, 1, 'saleh', 'Alsaiari', 'saleh@livixa.com', '0549801399', '', '', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Online Payment', 'moyasar3', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '90.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '159.0.208.210', '159.0.208.210', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36', 'en-US,en;q=0.9', '2022-06-15 13:18:11', '2022-06-15 13:18:11', NULL, 2, '45'),
(34, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 32, 1, 'bushra', 'khalid', 'qa@quaidventures.com', '03186059416', '', '', 'Bushra', 'khalid', '', 'Johar town lahore', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'Bushra', 'khalid', '', 'Johar town lahore', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', 'test', '195.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '72.255.38.189', '72.255.38.189', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-06-15 14:38:53', '2022-06-15 14:38:53', NULL, 1, '15'),
(35, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 37, 1, 'Testing', 'Testing', 't2@gmail.com', '03045527577', '', '', 'Testing', 'Testing', '', 'H#1', '', 'Islamabad', '', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Online Payment', 'moyasar3', 'Testing', 'Testing', '', 'H#1', '', 'Islamabad', '', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '15.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '72.255.38.189', '72.255.38.189', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:101.0) Gecko/20100101 Firefox/101.0', 'en-US,en;q=0.5', '2022-06-16 11:32:16', '2022-06-16 11:32:16', NULL, 0, '0'),
(36, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 39, 1, 'Check', 'Check', 't4@gmail.com', '03045527577', '', '', 'Check', 'Check', '', 'Check', '', 'Check', '', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Online Payment', 'moyasar3', 'Check', 'Check', '', 'Check', '', 'Check', '', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '26.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:101.0) Gecko/20100101 Firefox/101.0', 'en-US,en;q=0.5', '2022-06-16 11:35:13', '2022-06-16 11:35:13', NULL, 0, '0'),
(37, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 39, 1, 'Check', 'Check', 't4@gmail.com', '03045527577', '', '', 'Check', 'Check', '', 'Check', '', 'Check', '', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Online Payment', 'moyasar3', 'Check', 'Check', '', 'Check', '', 'Check', '', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '26.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:101.0) Gecko/20100101 Firefox/101.0', 'en-US,en;q=0.5', '2022-06-16 11:36:06', '2022-06-16 11:36:06', NULL, 0, '0'),
(38, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 39, 1, 'Check', 'Check', 't4@gmail.com', '03045527577', '', '', 'Check', 'Check', '', 'Check', '', 'Check', '', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Online Payment', 'moyasar3', 'Check', 'Check', '', 'Check', '', 'Check', '', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '26.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:101.0) Gecko/20100101 Firefox/101.0', 'en-US,en;q=0.5', '2022-06-16 11:36:46', '2022-06-16 11:36:46', NULL, 0, '0'),
(39, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 40, 1, 'Test', 'Test', 't5@gmail.com', '03045527577', '', '', 'Test', 'Test', '', 'Test', '', 'Test', '', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Online Payment', 'moyasar3', 'Test', 'Test', '', 'Test', '', 'Test', '', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '180.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '72.255.38.189', '72.255.38.189', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:101.0) Gecko/20100101 Firefox/101.0', 'en-US,en;q=0.5', '2022-06-16 11:39:12', '2022-06-16 11:39:12', NULL, 0, '0'),
(40, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 40, 1, 'Test', 'Test', 't5@gmail.com', '03045527577', '', '', 'Test', 'Test', '', 'Test', '', 'Test', '', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Online Payment', 'moyasar3', 'Test', 'Test', '', 'Test', '', 'Test', '', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '180.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '72.255.38.189', '72.255.38.189', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:101.0) Gecko/20100101 Firefox/101.0', 'en-US,en;q=0.5', '2022-06-16 11:41:42', '2022-06-16 11:41:42', NULL, 0, '0'),
(41, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 40, 1, 'Test', 'Test', 't5@gmail.com', '03045527577', '', '', 'Test', 'Test', '', 'Test', '', 'Test', '', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Cash On Delivery', 'cod', 'Test', 'Test', '', 'Test', '', 'Test', '', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '180.0000', 1, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:101.0) Gecko/20100101 Firefox/101.0', 'en-US,en;q=0.5', '2022-06-16 11:45:57', '2022-06-16 11:46:00', NULL, 0, '0'),
(42, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 42, 1, 'Check', 'Check', 't7@gmail.com', '03045527577', '', '', 'Check', 'Check', '', 'Check', '', 'Check', '', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Online Payment', 'moyasar3', 'Check', 'Check', '', 'Check', '', 'Check', '', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '36.0000', 13, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:101.0) Gecko/20100101 Firefox/101.0', 'en-US,en;q=0.5', '2022-06-16 11:52:51', '2022-06-16 11:54:03', NULL, 0, '0'),
(43, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 42, 1, 'Check', 'Check', 't7@gmail.com', '03045527577', '', '', 'Check', 'Check', '', 'Check', '', 'Check', '', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Online Payment', 'moyasar3', 'Check', 'Check', '', 'Check', '', 'Check', '', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '36.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:101.0) Gecko/20100101 Firefox/101.0', 'en-US,en;q=0.5', '2022-06-16 11:54:22', '2022-06-16 11:54:22', NULL, 0, '0'),
(44, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 42, 1, 'Check', 'Check', 't7@gmail.com', '03045527577', '', '', 'Check', 'Check', '', 'Check', '', 'Check', '', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Online Payment', 'moyasar3', 'Check', 'Check', '', 'Check', '', 'Check', '', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '36.0000', 13, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:101.0) Gecko/20100101 Firefox/101.0', 'en-US,en;q=0.5', '2022-06-16 11:55:14', '2022-06-16 11:56:20', NULL, 0, '0'),
(45, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 42, 1, 'Check', 'Check', 't7@gmail.com', '03045527577', '', '', 'Check', 'Check', '', 'Check', '', 'Check', '', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Online Payment', 'moyasar3', 'Check', 'Check', '', 'Check', '', 'Check', '', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '216.0000', 15, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:101.0) Gecko/20100101 Firefox/101.0', 'en-US,en;q=0.5', '2022-06-16 12:26:57', '2022-06-16 18:31:31', 1, 0, '0'),
(46, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 32, 1, 'bushra', 'khalid', 'qa@quaidventures.com', '03186059416', '', '', 'Bushra', 'khalid', '', 'Johar town lahore', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'Bushra', 'khalid', '', 'Johar town lahore', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '195.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '72.255.38.189', '72.255.38.189', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-06-16 12:34:59', '2022-06-16 12:34:59', NULL, 0, '0'),
(47, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '15.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '72.255.38.189', '72.255.38.189', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-06-16 13:05:08', '2022-06-16 13:05:08', NULL, 0, '0'),
(48, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', 'edfrfr', '30.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '72.255.38.189', '72.255.38.189', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-16 13:56:16', '2022-06-16 13:56:16', NULL, 0, '0'),
(49, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', 'newww', '30.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '72.255.38.189', '72.255.38.189', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-16 13:57:58', '2022-06-16 13:57:58', NULL, 0, '0'),
(50, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 3, 1, 'saleh', 'Alsaiari', 'saleh@livixa.com', '0549801399', '', '', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Online Payment', 'moyasar3', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '60.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '188.51.168.233', '188.51.168.233', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36', 'en-US,en;q=0.9', '2022-06-16 15:37:54', '2022-06-16 15:37:54', NULL, 0, '0'),
(51, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 45, 1, 'Ayesga', 'test', 'ayeshakhalid117@gmail.com', '03186059416', '', '', 'Ayesga', 'test', '', 'Johar town lahore', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'Ayesga', 'test', '', 'Johar town lahore', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '15.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-06-17 11:12:57', '2022-06-17 11:12:57', NULL, 0, '0'),
(52, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 45, 1, 'Ayesga', 'test', 'ayeshakhalid117@gmail.com', '03186059416', '', '', 'Ayesga', 'test', '', 'Johar town lahore', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'Ayesga', 'test', '', 'Johar town lahore', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '30.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '72.255.38.189', '72.255.38.189', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-06-17 11:16:21', '2022-06-17 11:16:21', NULL, 0, '0'),
(53, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 47, 1, 'SIT', 'Livixa', 'sit@gmail.com', '03045527577', '', '', 'SIT', 'Livixa', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Online Payment', 'moyasar3', 'SIT', 'Livixa', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '26.0000', 13, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '119.160.99.206', '119.160.99.206', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-18 12:10:54', '2022-06-18 12:11:52', NULL, 0, '0'),
(54, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 47, 1, 'SIT', 'Livixa', 'sit@gmail.com', '03045527577', '', '', 'SIT', 'Livixa', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Online Payment', 'moyasar3', 'SIT', 'Livixa', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '26.0000', 17, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '119.160.99.206', '119.160.99.206', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-18 12:12:10', '2022-06-18 12:17:45', 1, 0, '0'),
(55, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 48, 1, 'Faran', 'Ahmed Sukhera', 'fasukhera@gmail.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Online Payment', 'moyasar3', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '1452.0000', 17, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '119.160.99.206', '119.160.99.206', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-18 12:29:13', '2022-06-18 12:30:45', 1, 0, '0'),
(56, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 3, 1, 'saleh', 'Alsaiari', 'saleh@livixa.com', '0549801399', '', '', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'مدفوعات إلكترونية', 'moyasar3', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'تكلفة ثابتة للشحن', 'flat.flat', '', '123.0000', 0, 0, '0.0000', 0, '', 2, 4, 'SAR', '1.00000000', '188.51.168.233', '188.51.168.233', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36', 'en-US,en;q=0.9', '2022-06-19 09:18:45', '2022-06-19 09:18:45', NULL, 0, '0'),
(57, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 3, 1, 'saleh', 'Alsaiari', 'saleh@livixa.com', '0549801399', '', '', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Online Payment', 'moyasar3', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '123.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '188.51.168.233', '188.51.168.233', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36', 'en-US,en;q=0.9', '2022-06-19 09:19:20', '2022-06-19 09:19:20', NULL, 0, '0'),
(58, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 49, 1, 'SIT', 'Farrukh', 'ferozsukhera@gmail.com', '03045527577', '', '', 'SIT', 'Farrukh', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Online Payment', 'moyasar3', 'SIT', 'Farrukh', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '450.0000', 17, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-19 11:16:05', '2022-06-19 11:17:45', 1, 0, '0'),
(59, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 3, 1, 'saleh', 'Alsaiari', 'saleh@livixa.com', '0549801399', '', '', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Online Payment', 'moyasar3', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '168.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '188.51.168.233', '188.51.168.233', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36', 'en-US,en;q=0.9', '2022-06-19 13:41:41', '2022-06-19 13:41:41', NULL, 0, '0'),
(60, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 3, 1, 'saleh', 'Alsaiari', 'saleh@livixa.com', '0549801399', '', '', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Online Payment', 'moyasar3', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '213.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '188.51.168.233', '188.51.168.233', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1.2 Safari/605.1.15', 'en-us', '2022-06-19 13:52:40', '2022-06-19 13:52:40', NULL, 0, '0'),
(61, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 50, 1, 'Faran', 'Ahmed Sukhera', 'petic25068@serosin.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Online Payment', 'moyasar3', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '1080.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-19 14:03:04', '2022-06-19 14:03:04', NULL, 0, '0'),
(62, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 50, 1, 'Faran', 'Ahmed Sukhera', 'petic25068@serosin.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Online Payment', 'moyasar3', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '1080.0000', 17, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-19 14:04:35', '2022-06-19 14:07:41', 1, 0, '0'),
(63, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 52, 1, 'Livixa', 'SIT', 'wojas19657@exoacre.com', '03045527577', '', '', 'Livixa', 'SIT', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Online Payment', 'moyasar3', 'Livixa', 'SIT', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '135.0000', 17, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-19 14:39:26', '2022-06-19 14:40:56', 1, 0, '0'),
(64, 0, 'INV-2021-00', 0, 'Livixa', 'http://livixa.com/', 53, 1, 'Livxa', 'SIT', 'micitoh604@qqhow.com', '03045527577', '', '', 'Livxa', 'SIT', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Online Payment', 'moyasar3', 'Livxa', 'SIT', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '450.0000', 17, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-19 14:48:46', '2022-06-19 14:50:08', 1, 0, '0'),
(65, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '45.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-06-20 09:53:11', '2022-06-20 09:53:11', NULL, 0, '0'),
(66, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '45.0000', 13, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-06-20 09:54:01', '2022-06-20 09:54:41', NULL, 0, '0');
INSERT INTO `ocn8_order` (`order_id`, `invoice_no`, `invoice_prefix`, `store_id`, `store_name`, `store_url`, `customer_id`, `customer_group_id`, `firstname`, `lastname`, `email`, `telephone`, `fax`, `custom_field`, `payment_firstname`, `payment_lastname`, `payment_company`, `payment_address_1`, `payment_address_2`, `payment_city`, `payment_postcode`, `payment_country`, `payment_country_id`, `payment_zone`, `payment_zone_id`, `payment_address_format`, `payment_custom_field`, `payment_method`, `payment_code`, `shipping_firstname`, `shipping_lastname`, `shipping_company`, `shipping_address_1`, `shipping_address_2`, `shipping_city`, `shipping_postcode`, `shipping_country`, `shipping_country_id`, `shipping_zone`, `shipping_zone_id`, `shipping_address_format`, `shipping_custom_field`, `shipping_method`, `shipping_code`, `comment`, `total`, `order_status_id`, `affiliate_id`, `commission`, `marketing_id`, `tracking`, `language_id`, `currency_id`, `currency_code`, `currency_value`, `ip`, `forwarded_ip`, `user_agent`, `accept_language`, `date_added`, `date_modified`, `mobile_api_status`, `subscription_package_id`, `package_price`) VALUES
(67, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '45.0000', 17, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-06-20 09:54:54', '2022-06-20 10:59:46', 1, 0, '0'),
(68, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 3, 1, 'saleh', 'Alsaiari', 'saleh@livixa.com', '0549801399', '', '', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Online Payment', 'moyasar3', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '303.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '188.51.168.233', '188.51.168.233', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36', 'en-US,en;q=0.9', '2022-06-20 11:03:13', '2022-06-20 11:03:13', NULL, 0, '0'),
(69, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 3, 1, 'saleh', 'Alsaiari', 'saleh@livixa.com', '0549801399', '', '', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Online Payment', 'moyasar3', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '303.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '188.51.168.233', '188.51.168.233', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36', 'en-US,en;q=0.9', '2022-06-20 11:08:47', '2022-06-20 11:08:47', NULL, 0, '0'),
(70, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 3, 1, 'saleh', 'Alsaiari', 'saleh@livixa.com', '0549801399', '', '', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Online Payment', 'moyasar3', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '348.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '188.51.168.233', '188.51.168.233', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36', 'en-US,en;q=0.9', '2022-06-20 11:22:20', '2022-06-20 11:22:20', NULL, 0, '0'),
(71, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 54, 1, 'check', 'cehck', 'pitij30782@tagbert.com', '03045527577', '', '', 'check', 'cehck', '', '1123', '', 'Islamabad', '', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Online Payment', 'moyasar3', 'check', 'cehck', '', '1123', '', 'Islamabad', '', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '180.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '72.255.38.189', '72.255.38.189', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:101.0) Gecko/20100101 Firefox/101.0', 'en-US,en;q=0.5', '2022-06-20 12:20:30', '2022-06-20 12:20:30', NULL, 0, '0'),
(72, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 55, 1, 'Livixa', 'Final SIT', 'mawadoj520@tagbert.com', '03045527577', '', '', 'Livixa', 'Final SIT', '', 'h1 s1', '', 'Islamabad', '', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Online Payment', 'moyasar3', 'Livixa', 'Final SIT', '', 'h1 s1', '', 'Islamabad', '', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '360.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '119.160.98.193', '119.160.98.193', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:101.0) Gecko/20100101 Firefox/101.0', 'en-US,en;q=0.5', '2022-06-20 13:09:06', '2022-06-20 13:09:06', NULL, 0, '0'),
(73, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 55, 1, 'Livixa', 'Final SIT', 'mawadoj520@tagbert.com', '03045527577', '', '', 'Livixa', 'Final SIT', '', 'h1 s1', '', 'Islamabad', '', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Online Payment', 'moyasar3', 'Livixa', 'Final SIT', '', 'h1 s1', '', 'Islamabad', '', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '360.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '119.160.98.193', '119.160.98.193', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:101.0) Gecko/20100101 Firefox/101.0', 'en-US,en;q=0.5', '2022-06-20 13:11:47', '2022-06-20 13:11:47', NULL, 0, '0'),
(74, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 55, 1, 'Livixa', 'Final SIT', 'mawadoj520@tagbert.com', '03045527577', '', '', 'Livixa', 'Final SIT', '', 'h1 s1', '', 'Islamabad', '', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Online Payment', 'moyasar3', 'Livixa', 'Final SIT', '', 'h1 s1', '', 'Islamabad', '', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '360.0000', 17, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '119.160.98.193', '119.160.98.193', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:101.0) Gecko/20100101 Firefox/101.0', 'en-US,en;q=0.5', '2022-06-20 13:13:13', '2022-06-20 13:15:03', 1, 0, '0'),
(75, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 56, 1, 'Sit', 'Livixa Finalv2', 'cadovec607@giftcv.com', '03045527577', '', '', 'Sit', 'Livixa Finalv2', '', 'h1 s1', '', 'Islamabad', '', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Online Payment', 'moyasar3', 'Sit', 'Livixa Finalv2', '', 'h1 s1', '', 'Islamabad', '', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '180.0000', 17, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '72.255.38.189', '72.255.38.189', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:101.0) Gecko/20100101 Firefox/101.0', 'en-US,en;q=0.5', '2022-06-20 13:29:49', '2022-06-20 13:31:27', 1, 0, '0'),
(76, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 57, 1, 'Livixa', 'SIT', 'regegol640@syswift.com', '03045527577', '', '', 'Livixa', 'SIT', '', 'h1 s1', '', 'Islamabad', '', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Online Payment', 'moyasar3', 'Livixa', 'SIT', '', 'h1 s1', '', 'Islamabad', '', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '45.0000', 17, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:101.0) Gecko/20100101 Firefox/101.0', 'en-US,en;q=0.5', '2022-06-21 11:15:21', '2022-06-21 11:16:34', 1, 0, '0'),
(77, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 58, 1, 'Livix', 'SIT', 'xejix10890@tagbert.com', '03045527577', '', '', 'Livix', 'SIT', '', 'h1 s1', '', 'Islamabad', '', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Online Payment', 'moyasar3', 'Livix', 'SIT', '', 'h1 s1', '', 'Islamabad', '', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '432.0000', 17, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:101.0) Gecko/20100101 Firefox/101.0', 'en-US,en;q=0.5', '2022-06-21 11:24:48', '2022-06-21 11:26:14', 1, 0, '0'),
(78, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 59, 1, 'Liv', 'xa', 'midod56191@syswift.com', '03045527577', '', '', 'Liv', 'xa', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Online Payment', 'moyasar3', 'Liv', 'xa', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '216.0000', 17, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-21 11:46:28', '2022-06-21 11:47:59', 1, 0, '0'),
(79, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 60, 1, 'Livixa', 'SIT', 'yifeve4398@giftcv.com', '03045527577', '', '', 'Livixa', 'SIT', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Online Payment', 'moyasar3', 'Livixa', 'SIT', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Islamabad Capital Territory', 2459, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '432.0000', 17, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-21 14:07:40', '2022-06-22 10:44:17', 1, 0, '0'),
(80, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '[]', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '45.0000', 15, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-06-22 10:53:44', '2022-06-22 11:52:31', NULL, 0, '0'),
(81, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 63, 1, 'Faran', 'Ahmed Sukhera', 'jivec89119@mahazai.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'North-West Frontier', 2460, '', '[]', 'Online Payment', 'moyasar3', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'North-West Frontier', 2460, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '180.0000', 15, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-22 11:35:21', '2022-06-22 11:35:42', NULL, 0, '0'),
(82, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 64, 1, 'Faran', 'Ahmed Sukhera', 'sales@livixa.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Saudi Arabia', 184, 'Al Madinah', 2877, '', '[]', 'Online Payment', 'moyasar3', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Saudi Arabia', 184, 'Al Madinah', 2877, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '45.0000', 15, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-22 11:43:58', '2022-06-22 11:44:23', NULL, 0, '0'),
(83, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 3, 1, 'saleh', 'Alsaiari', 'saleh@livixa.com', '0549801399', '', '', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Online Payment', 'moyasar3', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '78.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '188.50.139.79', '188.50.139.79', 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36', 'en-US,en;q=0.9', '2022-06-22 11:46:07', '2022-06-22 11:46:07', NULL, 0, '0'),
(84, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 64, 1, 'Faran', 'Ahmed Sukhera', 'sales@livixa.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Saudi Arabia', 184, 'Al Madinah', 2877, '', '[]', 'Online Payment', 'moyasar3', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Saudi Arabia', 184, 'Al Madinah', 2877, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '78.0000', 15, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-22 11:48:11', '2022-06-22 11:48:27', NULL, 0, '0'),
(85, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 64, 1, 'Faran', 'Ahmed Sukhera', 'sales@livixa.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Saudi Arabia', 184, 'Al Madinah', 2877, '', '[]', 'Online Payment', 'moyasar3', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Saudi Arabia', 184, 'Al Madinah', 2877, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '78.0000', 17, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.67 Safari/537.36 OPR/87.0.4390.45', 'en-US,en;q=0.9', '2022-06-22 11:49:27', '2022-06-29 10:25:45', 1, 0, '0'),
(86, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '45.0000', 17, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-06-30 10:18:06', '2022-06-30 12:05:17', 1, 0, '0'),
(87, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '90.0000', 15, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-06-30 12:03:26', '2022-06-30 12:06:15', NULL, 0, '0'),
(88, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '45.0000', 15, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-06-30 12:26:16', '2022-06-30 12:26:58', NULL, 0, '0'),
(89, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '', 'bushra', 'khalid', 'eded', 'dredre', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', 'please hurry for testing', '432.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '72.255.38.189', '72.255.38.189', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-07-03 11:30:02', '2022-07-03 11:30:02', NULL, 0, '0'),
(90, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '522.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '72.255.38.189', '72.255.38.189', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-07-03 11:42:30', '2022-07-03 11:42:30', NULL, 0, '0'),
(91, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 65, 1, 'Bushra', 'Khalid', 'depaw68688@shbiso.com', '03186059416', '', '', 'Bushra', 'Khalid', '', 'Faisal colony no 2 House no 169 okara', '', 'Okara', '56300', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'Bushra', 'Khalid', '', 'Faisal colony no 2 House no 169 okara', '', 'Okara', '56300', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '390.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '72.255.38.189', '72.255.38.189', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-07-03 12:41:01', '2022-07-03 12:41:01', NULL, 0, '0'),
(92, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 65, 1, 'Bushra', 'Khalid', 'depaw68688@shbiso.com', '03186059416', '', '', 'Bushra', 'Khalid', '', 'Faisal colony no 2 House no 169 okara', '', 'Okara', '56300', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'Bushra', 'Khalid', '', 'Faisal colony no 2 House no 169 okara', '', 'Okara', '56300', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '432.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '72.255.38.189', '72.255.38.189', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-07-03 12:48:04', '2022-07-03 12:48:04', NULL, 0, '0'),
(93, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 66, 1, 'Bushra', 'Khalid', 'tabawet923@jrvps.com', '03186059416', '', '', 'Bushra', 'Khalid', '', 'Faisal colony no 2 House no 169 okara', '', 'Okara', '56300', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'Bushra', 'Khalid', '', 'Faisal colony no 2 House no 169 okara', '', 'Okara', '56300', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '432.0000', 2, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '72.255.38.189', '72.255.38.189', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-07-03 12:49:29', '2022-07-03 13:31:22', NULL, 0, '0'),
(94, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 66, 1, 'Bushra', 'Khalid', 'tabawet923@jrvps.com', '03186059416', '', '', 'Bushra', 'Khalid', '', 'Faisal colony no 2 House no 169 okara', '', 'Okara', '56300', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'Bushra', 'Khalid', '', 'Faisal colony no 2 House no 169 okara', '', 'Okara', '56300', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '108.0000', 2, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '72.255.38.189', '72.255.38.189', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-07-03 12:50:32', '2022-07-03 13:36:34', NULL, 0, '0'),
(95, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '999.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.115 Safari/537.36 OPR/88.0.4412.53', 'en-US,en;q=0.9', '2022-07-04 09:37:13', '2022-07-04 09:37:13', NULL, 0, '0'),
(96, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '999.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.115 Safari/537.36 OPR/88.0.4412.53', 'en-US,en;q=0.9', '2022-07-04 09:37:54', '2022-07-04 09:37:54', NULL, 0, '0'),
(97, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '135.0000', 15, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.115 Safari/537.36 OPR/88.0.4412.53', 'en-US,en;q=0.9', '2022-07-04 09:38:23', '2022-07-04 09:38:48', NULL, 0, '0'),
(98, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '45.0000', 15, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.115 Safari/537.36 OPR/88.0.4412.53', 'en-US,en;q=0.9', '2022-07-04 09:39:20', '2022-07-04 09:39:45', NULL, 0, '0'),
(99, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '45.0000', 2, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-07-06 11:13:58', '2022-07-06 12:16:01', NULL, 0, '0'),
(100, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '216.0000', 2, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-07-06 11:21:29', '2022-07-06 12:44:20', NULL, 0, '0'),
(101, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', 'testttt', '45.0000', 15, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-07-07 09:34:36', '2022-07-07 09:35:36', NULL, 0, '0'),
(102, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 3, 1, 'saleh', 'Alsaiari', 'saleh@livixa.com', '0549801399', '', '', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Online Payment', 'moyasar3', 'saleh', 'Alsaiari', '', 'qurtubah dist ', '', 'Riyadh', '', 'Saudi Arabia', 184, 'Ar Riyad', 2879, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '123.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '2.89.27.140', '2.89.27.140', 'Mozilla/5.0 (iPhone; CPU iPhone OS 14_7_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.1.2 Mobile/15E148 Safari/604.1', 'en-us', '2022-07-09 00:44:00', '2022-07-09 00:44:00', NULL, 0, '0'),
(103, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '312.0000', 15, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-07-13 11:10:25', '2022-07-13 11:11:08', NULL, 0, '0'),
(104, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '45.0000', 15, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-07-13 11:18:43', '2022-07-13 11:19:17', NULL, 0, '0'),
(105, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '432.0000', 17, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '72.255.38.189', '72.255.38.189', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-07-13 11:25:19', '2022-07-13 13:17:25', 1, 0, '0'),
(106, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 67, 1, 'Faran', 'Ahmed Sukhera', 'tatike9462@runfons.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Online Payment', 'moyasar3', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '45.0000', 17, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '119.73.124.43', '119.73.124.43', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.115 Safari/537.36 OPR/88.0.4412.53', 'en-US,en;q=0.9', '2022-07-13 13:22:10', '2022-07-13 13:48:18', 1, 0, '0'),
(107, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 67, 1, 'Faran', 'Ahmed Sukhera', 'tatike9462@runfons.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Online Payment', 'moyasar3', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '432.0000', 17, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '119.73.124.43', '119.73.124.43', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.115 Safari/537.36 OPR/88.0.4412.53', 'en-US,en;q=0.9', '2022-07-13 14:15:41', '2022-07-13 14:16:18', 1, 0, '0'),
(108, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 68, 1, 'Faran', 'Ahmed Sukhera', 'reyajek701@satedly.com', '03045527577', '', '', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Online Payment', 'moyasar3', 'Faran', 'Ahmed Sukhera', '', '15-b', '', 'Islamabad', '46000', 'Pakistan', 162, 'Federally Administered Tribal Areas', 2458, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '432.0000', 17, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '119.73.124.43', '119.73.124.43', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.115 Safari/537.36 OPR/88.0.4412.53', 'en-US,en;q=0.9', '2022-07-13 14:20:45', '2022-07-13 14:22:01', 1, 0, '0'),
(109, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 43, 1, 'bushra', 'khalid', 'khalidbushra97@gmail.com', '03186059416', '', '', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'bushra', 'khalid', '', 'fvfrff', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '45.0000', 0, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-07-14 07:15:59', '2022-07-14 07:15:59', NULL, 0, '0'),
(110, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 69, 1, 'Maliha', 'Khan', 'covig99256@opude.com', '03186059416', '', '', 'Maliha', 'Khan', '', 'test', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'Maliha', 'Khan', '', 'test', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '417.0000', 17, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-07-14 10:20:28', '2022-07-14 11:14:52', 1, 0, '0'),
(111, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 70, 1, 'Ayesha', 'Bushra', 'cajapo5088@lodores.com', '03186059416', '', '', 'Ayesha', 'Bushra', '', 'test', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'Ayesha', 'Bushra', '', 'test', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '465.0000', 17, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-07-14 12:02:44', '2022-07-14 12:05:50', 1, 0, '0'),
(112, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 70, 1, 'Ayesha', 'Bushra', 'cajapo5088@lodores.com', '03186059416', '', '', 'Ayesha', 'Bushra', '', 'test', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Online Payment', 'moyasar3', 'Ayesha', 'Bushra', '', 'test', '', 'lahore', '', 'Pakistan', 162, 'Punjab', 2461, '', '[]', 'Flat Shipping Rate', 'flat.flat', '', '432.0000', 17, 0, '0.0000', 0, '', 1, 4, 'SAR', '1.00000000', '103.83.89.155', '103.83.89.155', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-07-14 12:15:30', '2022-07-14 12:27:22', 1, 0, '0'),
(113, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 71, 1, 'Gagandeep', 'Singh', 'webtunix.hr@gmail.com', '7018760757', '', '', 'Gagandeep', 'Singh', 'Webtunix AI', '#283 AKS colony Zirakpur', '', 'MOhali', '140603', 'India', 99, 'Punjab', 1500, '', '[]', 'مدفوعات إلكترونية', 'moyasar3', 'Gagandeep', 'Singh', 'Webtunix AI', '#283 AKS colony Zirakpur', '', 'MOhali', '140603', 'India', 99, 'Punjab', 1500, '', '[]', 'تكلفة ثابتة للشحن', 'flat.flat', '', '1728.0000', 0, 0, '0.0000', 0, '', 2, 4, 'SAR', '1.00000000', '223.178.213.7', '223.178.213.7', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-07-15 11:03:16', '2022-07-15 11:03:16', NULL, 0, '0'),
(119, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 72, 1, 'Aastha', 'sharma', 'gagan25alone@gmail.com', '9888825206', '', '', 'Aastha', 'sharma', '', 'house 273', '', 'MOhali', '', 'India', 99, 'Punjab', 1500, '', '[]', 'مدفوعات إلكترونية', 'moyasar3', 'Aastha', 'sharma', '', 'house 273', '', 'MOhali', '', 'India', 99, 'Punjab', 1500, '', '[]', 'تكلفة ثابتة للشحن', 'flat.flat', '', '432.0000', 5, 0, '0.0000', 0, '', 2, 4, 'SAR', '1.00000000', '223.178.213.7', '223.178.213.7', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-07-15 13:15:13', '2022-07-15 13:24:35', NULL, 0, '0'),
(120, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 72, 1, 'Aastha', 'sharma', 'gagan25alone@gmail.com', '9888825206', '', '[]', 'Aastha', 'sharma', '', 'house 273', '', 'MOhali', '', 'India', 99, 'Punjab', 1500, '', '[]', 'مدفوعات إلكترونية', 'moyasar3', 'Aastha', 'sharma', '', 'house 273', '', 'MOhali', '', 'India', 99, 'Punjab', 1500, '', '[]', 'تكلفة ثابتة للشحن', 'flat.flat', '', '432.0000', 17, 0, '0.0000', 0, '', 2, 4, 'SAR', '1.00000000', '223.178.213.7', '223.178.213.7', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-07-15 13:28:10', '2022-07-15 13:28:56', 1, 0, '0'),
(115, 0, 'INV-2021-00', 0, 'Livixa', 'https://livixa.com/', 71, 1, 'Gagandeep', 'Singh', 'webtunix.hr@gmail.com', '7018760757', '', '', 'Gagandeep', 'Singh', 'Webtunix AI', '#283 AKS colony Zirakpur', '', 'MOhali', '140603', 'India', 99, 'Punjab', 1500, '', '[]', 'مدفوعات إلكترونية', 'moyasar3', 'Gagandeep', 'Singh', 'Webtunix AI', '#283 AKS colony Zirakpur', '', 'MOhali', '140603', 'India', 99, 'Punjab', 1500, '', '[]', 'تكلفة ثابتة للشحن', 'flat.flat', '', '1728.0000', 0, 0, '0.0000', 0, '', 2, 4, 'SAR', '1.00000000', '223.178.213.7', '223.178.213.7', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36', 'en-US,en;q=0.9', '2022-07-15 11:07:47', '2022-07-15 11:07:47', NULL, 0, '0');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_order_history`
--

CREATE TABLE `ocn8_order_history` (
  `order_history_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `order_status_id` int(11) NOT NULL,
  `notify` tinyint(1) NOT NULL DEFAULT 0,
  `comment` text NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_order_history`
--

INSERT INTO `ocn8_order_history` (`order_history_id`, `order_id`, `order_status_id`, `notify`, `comment`, `date_added`) VALUES
(1, 1, 1, 0, '', '2022-06-01 13:14:14'),
(2, 2, 1, 0, '', '2022-06-02 06:44:39'),
(3, 3, 1, 0, '', '2022-06-02 07:37:58'),
(4, 9, 1, 0, '', '2022-06-09 14:32:44'),
(5, 11, 1, 0, '', '2022-06-09 14:37:51'),
(6, 12, 1, 0, '', '2022-06-09 14:49:48'),
(7, 20, 1, 0, '', '2022-06-09 15:06:06'),
(8, 22, 1, 0, '', '2022-06-09 15:09:30'),
(9, 22, 17, 0, '', '2022-06-13 07:15:36'),
(10, 26, 1, 0, '', '2022-06-13 11:10:06'),
(11, 27, 1, 0, '', '2022-06-13 12:13:18'),
(12, 29, 1, 0, '', '2022-06-13 12:39:57'),
(13, 31, 15, 0, 'Payment is successfull', '2022-06-13 13:11:39'),
(14, 32, 15, 0, 'Payment is successfull', '2022-06-13 15:50:14'),
(15, 41, 1, 0, '', '2022-06-16 11:46:00'),
(16, 42, 13, 0, 'Payment Failed: Unable to process the purchase transaction (Test Environment)', '2022-06-16 11:54:03'),
(17, 44, 13, 0, 'Payment Failed: Unable to process the purchase transaction (Test Environment)', '2022-06-16 11:56:20'),
(18, 45, 15, 0, 'Payment is successfull', '2022-06-16 12:27:34'),
(19, 45, 10, 0, '', '2022-06-16 18:30:35'),
(20, 45, 15, 0, '', '2022-06-16 18:30:41'),
(21, 45, 1, 0, '', '2022-06-16 18:31:22'),
(22, 45, 15, 0, '', '2022-06-16 18:31:31'),
(23, 53, 13, 0, 'Payment Failed: Unable to process the purchase transaction (Test Environment)', '2022-06-18 12:11:52'),
(24, 54, 15, 0, 'Payment is successfull', '2022-06-18 12:13:09'),
(25, 54, 17, 0, '', '2022-06-18 12:17:45'),
(26, 55, 15, 0, 'Payment is successfull', '2022-06-18 12:29:36'),
(27, 55, 17, 0, '', '2022-06-18 12:30:45'),
(28, 58, 15, 0, 'Payment is successfull', '2022-06-19 11:16:28'),
(29, 58, 17, 0, '', '2022-06-19 11:17:45'),
(30, 62, 15, 0, 'Payment is successfull', '2022-06-19 14:05:04'),
(31, 62, 17, 0, '', '2022-06-19 14:07:41'),
(32, 63, 15, 0, 'Payment is successfull', '2022-06-19 14:39:52'),
(33, 63, 17, 0, '', '2022-06-19 14:40:56'),
(34, 64, 15, 0, 'Payment is successfull', '2022-06-19 14:49:10'),
(35, 64, 17, 0, '', '2022-06-19 14:50:08'),
(36, 66, 13, 0, 'Payment Failed: 3-D Secure transaction attempt failed (Invalid security code)', '2022-06-20 09:54:41'),
(37, 67, 15, 0, 'Payment is successfull', '2022-06-20 09:55:19'),
(38, 67, 17, 0, '', '2022-06-20 10:59:46'),
(39, 74, 15, 0, 'Payment is successfull', '2022-06-20 13:13:48'),
(40, 74, 17, 0, '', '2022-06-20 13:15:03'),
(41, 75, 15, 0, 'Payment is successfull', '2022-06-20 13:30:15'),
(42, 75, 17, 0, '', '2022-06-20 13:31:27'),
(43, 76, 15, 0, 'Payment is successfull', '2022-06-21 11:15:42'),
(44, 76, 17, 0, '', '2022-06-21 11:16:34'),
(45, 77, 15, 0, 'Payment is successfull', '2022-06-21 11:25:07'),
(46, 77, 17, 0, '', '2022-06-21 11:26:14'),
(47, 78, 15, 0, 'Payment is successfull', '2022-06-21 11:46:51'),
(48, 78, 17, 0, '', '2022-06-21 11:47:59'),
(49, 79, 15, 0, 'Payment is successfull', '2022-06-21 14:08:35'),
(50, 79, 17, 0, '', '2022-06-21 14:39:21'),
(51, 79, 15, 0, '', '2022-06-22 10:44:13'),
(52, 79, 17, 0, '', '2022-06-22 10:44:17'),
(53, 81, 15, 0, 'Payment is successfull', '2022-06-22 11:35:42'),
(54, 82, 15, 0, 'Payment is successfull', '2022-06-22 11:44:23'),
(55, 80, 15, 0, 'Payment is successfull', '2022-06-22 11:46:39'),
(58, 84, 15, 0, 'Payment is successfull', '2022-06-22 11:48:27'),
(57, 80, 15, 0, '', '2022-06-22 11:47:45'),
(59, 85, 15, 0, 'Payment is successfull', '2022-06-22 11:49:46'),
(60, 80, 15, 0, '', '2022-06-22 11:52:31'),
(61, 85, 3, 0, '', '2022-06-22 12:01:32'),
(62, 85, 17, 0, '', '2022-06-29 10:22:09'),
(63, 85, 10, 0, '', '2022-06-29 10:22:47'),
(64, 85, 17, 0, '', '2022-06-29 10:22:52'),
(65, 85, 10, 0, '', '2022-06-29 10:25:32'),
(66, 85, 17, 0, '', '2022-06-29 10:25:45'),
(67, 86, 15, 0, 'Payment is successfull', '2022-06-30 10:18:52'),
(68, 87, 15, 0, 'Payment is successfull', '2022-06-30 12:04:03'),
(69, 86, 17, 0, '', '2022-06-30 12:05:17'),
(70, 87, 15, 0, '', '2022-06-30 12:06:15'),
(71, 88, 15, 0, 'Payment is successfull', '2022-06-30 12:26:58'),
(72, 93, 15, 0, 'Payment is successfull', '2022-07-03 12:49:56'),
(73, 94, 15, 0, 'Payment is successfull', '2022-07-03 12:51:02'),
(74, 93, 2, 0, '', '2022-07-03 13:31:22'),
(75, 94, 2, 0, '', '2022-07-03 13:36:34'),
(76, 97, 15, 0, 'Payment is successfull', '2022-07-04 09:38:48'),
(77, 98, 15, 0, 'Payment is successfull', '2022-07-04 09:39:45'),
(78, 99, 15, 0, 'Payment is successfull', '2022-07-06 11:14:32'),
(79, 100, 15, 0, 'Payment is successfull', '2022-07-06 11:22:08'),
(80, 99, 2, 0, '', '2022-07-06 12:16:01'),
(81, 100, 2, 0, '', '2022-07-06 12:43:34'),
(82, 100, 2, 0, 'sddssd', '2022-07-06 12:44:20'),
(83, 101, 15, 0, 'Payment is successfull', '2022-07-07 09:35:36'),
(84, 103, 15, 0, 'Payment is successfull', '2022-07-13 11:11:08'),
(85, 104, 15, 0, 'Payment is successfull', '2022-07-13 11:19:17'),
(86, 105, 15, 0, 'Payment is successfull', '2022-07-13 11:27:23'),
(87, 105, 2, 0, 'ok', '2022-07-13 11:28:22'),
(88, 105, 17, 0, '', '2022-07-13 13:17:06'),
(89, 105, 17, 0, '', '2022-07-13 13:17:25'),
(90, 106, 15, 0, 'Payment is successfull', '2022-07-13 13:22:45'),
(91, 106, 17, 0, '', '2022-07-13 13:48:18'),
(92, 107, 15, 0, 'Payment is successfull', '2022-07-13 14:15:59'),
(93, 107, 17, 0, '', '2022-07-13 14:16:18'),
(94, 108, 15, 0, 'Payment is successfull', '2022-07-13 14:21:03'),
(95, 108, 17, 0, '', '2022-07-13 14:22:01'),
(96, 110, 15, 0, 'Payment is successfull', '2022-07-14 10:21:02'),
(97, 110, 2, 0, '', '2022-07-14 10:38:55'),
(98, 110, 17, 0, '', '2022-07-14 10:39:42'),
(99, 110, 17, 0, '', '2022-07-14 10:39:51'),
(100, 110, 17, 0, '', '2022-07-14 11:14:52'),
(101, 111, 15, 0, 'Payment is successfull', '2022-07-14 12:03:10'),
(102, 111, 17, 0, '', '2022-07-14 12:05:50'),
(103, 112, 15, 0, 'Payment is successfull', '2022-07-14 12:16:04'),
(104, 112, 17, 0, '', '2022-07-14 12:27:22'),
(127, 120, 5, 0, '', '2022-07-15 13:28:13'),
(125, 119, 3, 1, '', '2022-07-15 13:22:24'),
(126, 119, 5, 1, '', '2022-07-15 13:24:35'),
(124, 119, 17, 1, '', '2022-07-15 13:21:07'),
(128, 120, 17, 1, '', '2022-07-15 13:28:56'),
(123, 119, 13, 0, 'Payment Failed: 3-D Secure transaction attempt failed (Invalid security code)', '2022-07-15 13:19:12');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_order_option`
--

CREATE TABLE `ocn8_order_option` (
  `order_option_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `order_product_id` int(11) NOT NULL,
  `product_option_id` int(11) NOT NULL,
  `product_option_value_id` int(11) NOT NULL DEFAULT 0,
  `name` varchar(255) NOT NULL,
  `value` text NOT NULL,
  `type` varchar(32) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_order_option`
--

INSERT INTO `ocn8_order_option` (`order_option_id`, `order_id`, `order_product_id`, `product_option_id`, `product_option_value_id`, `name`, `value`, `type`) VALUES
(1, 1, 1, 226, 15, 'Select', 'Red', 'select'),
(2, 2, 2, 227, 26, 'Subscription', 'High Service 12 months subscription', 'select'),
(3, 3, 3, 230, 53, 'Subscription', 'Mid Service 12 months subscription', 'select'),
(4, 3, 4, 227, 18, 'Subscription', 'Initial Services 1 month subscription ', 'select'),
(5, 4, 5, 227, 26, 'Subscription', 'High Service 12 months subscription', 'select'),
(6, 5, 6, 227, 22, 'الاشتراك', 'اشتراك منتصف الخدمات لمدة ثلاثة اشهر', 'select'),
(7, 5, 7, 227, 19, 'الاشتراك', 'اشتراك الخدمات الأولية لمدة ثلاثة أشهر', 'select'),
(8, 6, 8, 229, 38, 'الاشتراكات', 'الباقة الاقتصادية اشتراك لمدة شهر', 'select'),
(9, 7, 9, 227, 23, 'Subscription', 'Mid Service 12 months subscription', 'select'),
(10, 8, 10, 227, 23, 'Subscription', 'Mid Service 12 months subscription', 'select'),
(11, 9, 11, 227, 23, 'Subscription', 'Mid Service 12 months subscription', 'select'),
(12, 25, 27, 230, 48, 'الاشتراكات', 'الباقة الاقتصادية اشتراك لمدة شهر', 'select'),
(13, 28, 31, 230, 54, 'Subscription', 'High Service 1 month subscription', 'select'),
(14, 29, 32, 230, 54, 'Subscription', 'High Service 1 month subscription', 'select'),
(15, 33, 36, 229, 39, 'Subscription', 'ِEconomy Services 3 months subscription ', 'select'),
(16, 34, 37, 228, 30, 'Subscription', 'Economy Services 12 months subscription ', 'select'),
(17, 35, 38, 232, 67, 'Subscription', 'Economy Services 1 month subscription ', 'select'),
(18, 36, 39, 232, 70, 'Subscription', 'Mid Services 1 month subscription ', 'select'),
(19, 37, 40, 232, 70, 'Subscription', 'Mid Services 1 month subscription ', 'select'),
(20, 38, 41, 232, 70, 'Subscription', 'Mid Services 1 month subscription ', 'select'),
(21, 39, 42, 230, 50, 'Subscription', 'Economy Services 12 months subscription ', 'select'),
(22, 40, 43, 230, 50, 'Subscription', 'Economy Services 12 months subscription ', 'select'),
(23, 41, 44, 230, 50, 'Subscription', 'Economy Services 12 months subscription ', 'select'),
(24, 42, 45, 232, 73, 'Subscription', 'High Service 1 month subscription', 'select'),
(25, 43, 46, 232, 73, 'Subscription', 'High Service 1 month subscription', 'select'),
(26, 44, 47, 232, 73, 'Subscription', 'High Service 1 month subscription', 'select'),
(27, 45, 48, 232, 73, 'Subscription', 'High Service 1 month subscription', 'select'),
(28, 45, 49, 232, 69, 'Subscription', 'Economy Services 12 months subscription ', 'select'),
(29, 46, 50, 232, 67, 'Subscription', 'Economy Services 1 month subscription ', 'select'),
(30, 46, 51, 228, 30, 'Subscription', 'Economy Services 12 months subscription ', 'select'),
(31, 47, 52, 232, 67, 'Subscription', 'Economy Services 1 month subscription ', 'select'),
(32, 48, 53, 232, 67, 'Subscription', 'Economy Services 1 month subscription ', 'select'),
(33, 49, 54, 232, 67, 'Subscription', 'Economy Services 1 month subscription ', 'select'),
(34, 50, 55, 232, 67, 'Subscription', 'Economy Services 1 month subscription ', 'select'),
(35, 50, 56, 229, 39, 'Subscription', 'ِEconomy Services 3 months subscription ', 'select'),
(36, 51, 57, 232, 67, 'Subscription', 'Economy Services 1 month subscription ', 'select'),
(37, 52, 58, 232, 67, 'Subscription', 'Economy Services 1 month subscription ', 'select'),
(38, 52, 59, 230, 48, 'Subscription', 'Economy Services 1 month subscription ', 'select'),
(39, 53, 60, 232, 70, 'Subscription', 'Mid Services 1 month subscription ', 'select'),
(40, 54, 61, 232, 70, 'Subscription', 'Mid Services 1 month subscription ', 'select'),
(41, 55, 62, 232, 71, 'Subscription', 'Mid Services 3 months subscription ', 'select'),
(42, 55, 63, 230, 56, 'Subscription', 'High Service 12 months subscription', 'select'),
(43, 56, 65, 228, 32, 'الاشتراكات', 'الخدمة المتوسطة  اشتراك لمدة  ثلاثة أشهر', 'select'),
(44, 56, 66, 229, 39, 'الاشتراكات', 'الخدمة العادية  اشتراك لمدة  ثلاثة أشهر', 'select'),
(45, 57, 68, 228, 32, 'Subscription', 'Mid Services 3 months subscription ', 'select'),
(46, 57, 69, 229, 39, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(47, 58, 70, 232, 69, 'Subscription', 'Initial Services 12 months subscription ', 'select'),
(48, 58, 71, 230, 49, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(49, 59, 73, 228, 32, 'Subscription', 'Mid Services 3 months subscription ', 'select'),
(50, 59, 74, 229, 39, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(51, 60, 76, 228, 32, 'Subscription', 'Mid Services 3 months subscription ', 'select'),
(52, 60, 77, 229, 39, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(53, 61, 78, 232, 69, 'Subscription', 'Initial Services 12 months subscription ', 'select'),
(54, 61, 79, 228, 30, 'Subscription', 'Initial Services 12 months subscription ', 'select'),
(55, 62, 80, 232, 69, 'Subscription', 'Initial Services 12 months subscription ', 'select'),
(56, 62, 81, 228, 30, 'Subscription', 'Initial Services 12 months subscription ', 'select'),
(57, 63, 82, 232, 68, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(58, 64, 83, 232, 68, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(59, 64, 84, 230, 50, 'Subscription', 'Initial Services 12 months subscription ', 'select'),
(60, 65, 86, 232, 68, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(61, 66, 87, 232, 68, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(62, 67, 88, 232, 68, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(63, 68, 90, 232, 68, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(64, 68, 91, 228, 32, 'Subscription', 'Mid Services 3 months subscription ', 'select'),
(65, 68, 92, 229, 39, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(66, 69, 94, 232, 68, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(67, 69, 95, 228, 32, 'Subscription', 'Mid Services 3 months subscription ', 'select'),
(68, 69, 96, 229, 39, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(69, 70, 98, 232, 68, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(70, 70, 99, 228, 32, 'Subscription', 'Mid Services 3 months subscription ', 'select'),
(71, 70, 100, 229, 39, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(72, 71, 101, 232, 69, 'Subscription', 'Initial Services 12 months subscription ', 'select'),
(73, 72, 102, 232, 69, 'Subscription', 'Initial Services 12 months subscription ', 'select'),
(74, 73, 103, 232, 69, 'Subscription', 'Initial Services 12 months subscription ', 'select'),
(75, 74, 104, 232, 69, 'Subscription', 'Initial Services 12 months subscription ', 'select'),
(76, 75, 105, 230, 50, 'Subscription', 'Initial Services 12 months subscription ', 'select'),
(77, 76, 106, 230, 49, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(78, 77, 107, 232, 75, 'Subscription', 'High Service 12 months subscription', 'select'),
(79, 78, 108, 230, 55, 'Subscription', 'High Service 3 months subscription', 'select'),
(80, 79, 109, 232, 75, 'Subscription', 'High Service 12 months subscription', 'select'),
(85, 80, 114, 230, 49, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(82, 81, 111, 230, 50, 'Subscription', 'Initial Services 12 months subscription ', 'select'),
(83, 82, 112, 230, 49, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(84, 83, 113, 230, 52, 'Subscription', 'Mid Services 3 months subscription ', 'select'),
(86, 84, 115, 232, 71, 'Subscription', 'Mid Services 3 months subscription ', 'select'),
(87, 85, 116, 230, 52, 'Subscription', 'Mid Services 3 months subscription ', 'select'),
(88, 86, 117, 232, 68, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(89, 87, 118, 232, 68, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(90, 88, 119, 228, 29, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(91, 89, 120, 232, 75, 'Subscription', 'High Service 12 months subscription', 'select'),
(92, 90, 121, 232, 75, 'Subscription', 'High Service 12 months subscription', 'select'),
(93, 90, 122, 230, 49, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(94, 91, 123, 232, 71, 'Subscription', 'Mid Services 3 months subscription ', 'select'),
(95, 91, 124, 232, 72, 'Subscription', 'Mid Service 12 months subscription', 'select'),
(96, 92, 125, 232, 75, 'Subscription', 'High Service 12 months subscription', 'select'),
(97, 93, 126, 232, 75, 'Subscription', 'High Service 12 months subscription', 'select'),
(98, 94, 127, 228, 35, 'Subscription', 'High Service 3 months subscription', 'select'),
(99, 95, 128, 232, 75, 'Subscription', 'High Service 12 months subscription', 'select'),
(100, 95, 129, 230, 49, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(101, 95, 130, 230, 56, 'Subscription', 'High Service 12 months subscription', 'select'),
(102, 96, 131, 232, 75, 'Subscription', 'High Service 12 months subscription', 'select'),
(103, 96, 132, 230, 49, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(104, 96, 133, 230, 56, 'Subscription', 'High Service 12 months subscription', 'select'),
(105, 97, 134, 230, 49, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(106, 98, 135, 230, 49, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(107, 99, 136, 229, 39, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(108, 100, 137, 228, 35, 'Subscription', 'High Service 3 months subscription', 'select'),
(109, 101, 138, 228, 29, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(110, 102, 139, 232, 68, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(111, 102, 140, 230, 52, 'Subscription', 'Mid Services 3 months subscription ', 'select'),
(112, 103, 141, 232, 72, 'Subscription', 'Mid Service 12 months subscription', 'select'),
(113, 104, 142, 230, 49, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(114, 105, 143, 232, 75, 'Subscription', 'High Service 12 months subscription', 'select'),
(115, 106, 144, 232, 68, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(116, 107, 145, 232, 75, 'Subscription', 'High Service 12 months subscription', 'select'),
(117, 108, 146, 232, 75, 'Subscription', 'High Service 12 months subscription', 'select'),
(118, 109, 147, 230, 49, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(119, 110, 148, 232, 68, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(120, 110, 149, 228, 35, 'Subscription', 'High Service 3 months subscription', 'select'),
(121, 110, 150, 230, 52, 'Subscription', 'Mid Services 3 months subscription ', 'select'),
(122, 111, 151, 232, 68, 'Subscription', 'ِInitial Services 3 months subscription ', 'select'),
(123, 111, 152, 228, 35, 'Subscription', 'High Service 3 months subscription', 'select'),
(124, 111, 153, 230, 53, 'Subscription', 'Mid Service 12 months subscription', 'select'),
(125, 112, 154, 231, 66, 'Subscription', 'High Service 12 months subscription', 'select'),
(126, 113, 155, 228, 36, 'الاشتراكات', 'الخدمة المتقدمة  اشتراك لمدة 12 شهر', 'select'),
(127, 113, 156, 231, 66, 'الاشتراكات', 'الخدمة المتقدمة  اشتراك لمدة 12 شهر', 'select'),
(138, 120, 167, 231, 66, 'الاشتراكات', 'الخدمة المتقدمة  اشتراك لمدة 12 شهر', 'select'),
(137, 119, 166, 231, 66, 'الاشتراكات', 'الخدمة المتقدمة  اشتراك لمدة 12 شهر', 'select'),
(130, 115, 159, 228, 36, 'الاشتراكات', 'الخدمة المتقدمة  اشتراك لمدة 12 شهر', 'select'),
(131, 115, 160, 231, 66, 'الاشتراكات', 'الخدمة المتقدمة  اشتراك لمدة 12 شهر', 'select');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_order_product`
--

CREATE TABLE `ocn8_order_product` (
  `order_product_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `model` varchar(64) NOT NULL,
  `quantity` int(4) NOT NULL,
  `price` decimal(15,4) NOT NULL DEFAULT 0.0000,
  `total` decimal(15,4) NOT NULL DEFAULT 0.0000,
  `tax` decimal(15,4) NOT NULL DEFAULT 0.0000,
  `reward` int(8) NOT NULL,
  `mac_address` text NOT NULL,
  `picture` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_order_product`
--

INSERT INTO `ocn8_order_product` (`order_product_id`, `order_id`, `product_id`, `name`, `model`, `quantity`, `price`, `total`, `tax`, `reward`, `mac_address`, `picture`) VALUES
(1, 1, 30, 'Canon EOS 5D', 'Product 3', 1, '80.0000', '80.0000', '0.0000', 200, 'sajjad_mac1', 'image_2022-05-17_173001071-removebg-preview.png'),
(2, 2, 50, ' WIFI Three gangs lighting switch', '1', 1, '280.0000', '280.0000', '0.0000', 0, '', ''),
(3, 3, 53, 'WIFI A/C switch', '1', 1, '154.0000', '154.0000', '0.0000', 0, '', ''),
(4, 3, 50, ' WIFI Three gangs lighting switch', '1', 1, '5.0000', '5.0000', '0.0000', 0, '', ''),
(5, 4, 50, ' WIFI Three gangs lighting switch', '1', 1, '280.0000', '280.0000', '0.0000', 0, '', ''),
(6, 5, 50, 'مفتاح إنارة ثلاثة خطوط', '1', 1, '39.0000', '39.0000', '0.0000', 0, '', ''),
(7, 5, 50, 'مفتاح إنارة ثلاثة خطوط', '1', 1, '12.0000', '12.0000', '0.0000', 0, '', ''),
(8, 6, 52, 'فيش واي فاي ', '1', 1, '15.0000', '15.0000', '0.0000', 0, '', ''),
(9, 7, 50, ' WIFI Three gangs lighting switch', '1', 3, '321.0000', '963.0000', '0.0000', 0, '', ''),
(10, 8, 50, ' WIFI Three gangs lighting switch', '1', 3, '321.0000', '963.0000', '0.0000', 0, '', ''),
(11, 9, 50, ' WIFI Three gangs lighting switch', '1', 3, '321.0000', '963.0000', '0.0000', 0, 'test123432,betta,lifwea', 'image_2022-05-17_173001071-removebg-preview.png,logo.png,Hospital Admissions Per Corp.png'),
(12, 10, 50, ' WIFI Three gangs lighting switch', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(13, 11, 50, ' WIFI Three gangs lighting switch', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(14, 12, 50, ' WIFI Three gangs lighting switch', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(15, 13, 50, ' WIFI Three gangs lighting switch', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(16, 14, 50, ' WIFI Three gangs lighting switch', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(17, 15, 50, ' WIFI Three gangs lighting switch', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(18, 16, 50, ' WIFI Three gangs lighting switch', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(19, 17, 50, ' WIFI Three gangs lighting switch', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(20, 18, 50, ' WIFI Three gangs lighting switch', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(21, 19, 50, ' WIFI Three gangs lighting switch', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(22, 20, 50, ' WIFI Three gangs lighting switch', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(23, 21, 50, ' WIFI Three gangs lighting switch', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(24, 22, 50, ' WIFI Three gangs lighting switch', '1', 1, '0.0000', '0.0000', '0.0000', 0, 'test', 'logo.png'),
(25, 23, 50, ' WIFI Three gangs lighting switch', '1', 2, '0.0000', '0.0000', '0.0000', 0, '', ''),
(26, 24, 50, ' WIFI Three gangs lighting switch', '1', 2, '0.0000', '0.0000', '0.0000', 0, '', ''),
(30, 27, 50, ' WIFI Three gangs lighting switch', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(29, 26, 50, ' WIFI Three gangs lighting switch', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(28, 26, 50, ' WIFI Three gangs lighting switch', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(27, 25, 53, 'مفتاح مكيف واي فاي ', '1', 1, '15.0000', '15.0000', '0.0000', 0, '', ''),
(31, 28, 53, 'WIFI A/C switch', '1', 1, '36.0000', '36.0000', '0.0000', 0, '', ''),
(32, 29, 53, 'WIFI A/C switch', '1', 1, '36.0000', '36.0000', '0.0000', 0, 'SAJJAD123', 'signal-bot.png'),
(33, 30, 50, ' WIFI Three gangs lighting switch', '1', 2, '0.0000', '0.0000', '0.0000', 0, '', ''),
(34, 31, 50, ' WIFI Three gangs lighting switch', '1', 1, '0.0000', '0.0000', '0.0000', 0, 'terst', 'Hospital Admissions Per Corp.png'),
(35, 32, 50, ' WIFI Three gangs lighting switch', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(36, 33, 52, 'WIFI socket switch', '1', 1, '45.0000', '45.0000', '0.0000', 0, '', ''),
(37, 34, 51, 'WIFI One gang lighting switch_ Free Hardware', '1', 1, '180.0000', '180.0000', '0.0000', 0, '', ''),
(38, 35, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '15.0000', '15.0000', '0.0000', 0, '', ''),
(39, 36, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '26.0000', '26.0000', '0.0000', 0, '', ''),
(40, 37, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '26.0000', '26.0000', '0.0000', 0, '', ''),
(41, 38, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '26.0000', '26.0000', '0.0000', 0, '', ''),
(42, 39, 53, 'WIFI A/C switch _ Free Hardware ', '1', 1, '180.0000', '180.0000', '0.0000', 0, '', ''),
(43, 40, 53, 'WIFI A/C switch _ Free Hardware ', '1', 1, '180.0000', '180.0000', '0.0000', 0, '', ''),
(44, 41, 53, 'WIFI A/C switch _ Free Hardware ', '1', 1, '180.0000', '180.0000', '0.0000', 0, '', ''),
(45, 42, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '36.0000', '36.0000', '0.0000', 0, '', ''),
(46, 43, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '36.0000', '36.0000', '0.0000', 0, '', ''),
(47, 44, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '36.0000', '36.0000', '0.0000', 0, '', ''),
(48, 45, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '36.0000', '36.0000', '0.0000', 0, 'mac_1_sar_36', 'logo.png'),
(49, 45, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '180.0000', '180.0000', '0.0000', 0, 'mac_2_sar_180', 'image_2022-05-17_173001071-removebg-preview.png'),
(50, 46, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '15.0000', '15.0000', '0.0000', 0, '', ''),
(51, 46, 51, 'WIFI One gang lighting switch_ Free Hardware', '1', 1, '180.0000', '180.0000', '0.0000', 0, '', ''),
(52, 47, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '15.0000', '15.0000', '0.0000', 0, '', ''),
(53, 48, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 2, '15.0000', '30.0000', '0.0000', 0, '', ''),
(54, 49, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 2, '15.0000', '30.0000', '0.0000', 0, '', ''),
(55, 50, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '15.0000', '15.0000', '0.0000', 0, '', ''),
(56, 50, 52, 'WIFI socket switch _ Free Hardware', '1', 1, '45.0000', '45.0000', '0.0000', 0, '', ''),
(57, 51, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '15.0000', '15.0000', '0.0000', 0, '', ''),
(58, 52, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '15.0000', '15.0000', '0.0000', 0, '', ''),
(59, 52, 53, 'WIFI A/C switch _ Free Hardware ', '1', 1, '15.0000', '15.0000', '0.0000', 0, '', ''),
(60, 53, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '26.0000', '26.0000', '0.0000', 0, '', ''),
(61, 54, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '26.0000', '26.0000', '0.0000', 0, 'sit_livixa', 'image_2022-06-18_171410260.png'),
(62, 55, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 2, '78.0000', '156.0000', '0.0000', 0, 'sit_faran,sit_faran2', 'logo.png,Hospital Admissions Per Corp.png'),
(63, 55, 53, 'WIFI A/C switch _ Free Hardware ', '1', 3, '432.0000', '1296.0000', '0.0000', 0, 'sit_faran3,sit_faran4,sit_faran5', 'Hospital Admissions Per Corp.png,logo.png,image_2022-05-17_173001071-removebg-preview.png'),
(64, 56, 50, ' مفتاح إنارة واي فاي ثلاثة خطوط ـ الجهاز مجانا', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(65, 56, 51, 'مفتاح إنارة خط واحد واي فاي - الجهاز مجانا', '1', 1, '78.0000', '78.0000', '0.0000', 0, '', ''),
(66, 56, 52, 'مفتاح فيش واي فاي - الجهاز مجانا', '1', 1, '45.0000', '45.0000', '0.0000', 0, '', ''),
(67, 57, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(68, 57, 51, 'WIFI One gang lighting switch_ Free Hardware', '1', 1, '78.0000', '78.0000', '0.0000', 0, '', ''),
(69, 57, 52, 'WIFI socket switch _ Free Hardware', '1', 1, '45.0000', '45.0000', '0.0000', 0, '', ''),
(70, 58, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 2, '180.0000', '360.0000', '0.0000', 0, 'sit_farukh,sit_farukh2', 'logo.png,Hospital Admissions Per Corp.png'),
(71, 58, 53, 'WIFI A/C switch _ Free Hardware ', '1', 2, '45.0000', '90.0000', '0.0000', 0, 'sit_farukh3,sit_farukh4', 'Hospital Admissions Per Corp.png,logo.png'),
(72, 59, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(73, 59, 51, 'WIFI One gang lighting switch_ Free Hardware', '1', 1, '78.0000', '78.0000', '0.0000', 0, '', ''),
(74, 59, 52, 'WIFI socket switch _ Free Hardware', '1', 2, '45.0000', '90.0000', '0.0000', 0, '', ''),
(75, 60, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(76, 60, 51, 'WIFI One gang lighting switch_ Free Hardware', '1', 1, '78.0000', '78.0000', '0.0000', 0, '', ''),
(77, 60, 52, 'WIFI socket switch _ Free Hardware', '1', 3, '45.0000', '135.0000', '0.0000', 0, '', ''),
(78, 61, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 2, '180.0000', '360.0000', '0.0000', 0, '', ''),
(79, 61, 51, 'WIFI One gang lighting switch_ Free Hardware', '1', 4, '180.0000', '720.0000', '0.0000', 0, '', ''),
(80, 62, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 2, '180.0000', '360.0000', '0.0000', 0, 'sit_livx123,sit_livx321', 'logo.png,Hospital Admissions Per Corp.png'),
(81, 62, 51, 'WIFI One gang lighting switch_ Free Hardware', '1', 4, '180.0000', '720.0000', '0.0000', 0, 'sit_livx11,sit_livx12,sit_livx_13,sit_livx_14', 'logo.png,Hospital Admissions Per Corp.png,image_2022-05-17_173001071-removebg-preview.png,image_2022-05-17_172929129.png'),
(82, 63, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 3, '45.0000', '135.0000', '0.0000', 0, 'sit_farukh99,sit_farukh98,sit_farukh97', 'Hospital Admissions Per Corp.png,logo.png,image_2022-05-17_173001071-removebg-preview.png'),
(83, 64, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 2, '45.0000', '90.0000', '0.0000', 0, 'sit_livx1,sit_livx2', 'image_2022-05-17_173001071-removebg-preview.png,logo.png'),
(84, 64, 53, 'WIFI A/C switch _ Free Hardware ', '1', 2, '180.0000', '360.0000', '0.0000', 0, 'sit_livx3,sit_livx4', 'logo.png,image_2022-05-17_173001071-removebg-preview.png'),
(85, 65, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 2, '0.0000', '0.0000', '0.0000', 0, '', ''),
(86, 65, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '45.0000', '45.0000', '0.0000', 0, '', ''),
(87, 66, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '45.0000', '45.0000', '0.0000', 0, '', ''),
(88, 67, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '45.0000', '45.0000', '0.0000', 0, 'mac_bushra', 'logo.png'),
(89, 68, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(90, 68, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 2, '45.0000', '90.0000', '0.0000', 0, '', ''),
(91, 68, 51, 'WIFI One gang lighting switch_ Free Hardware', '1', 1, '78.0000', '78.0000', '0.0000', 0, '', ''),
(92, 68, 52, 'WIFI socket switch _ Free Hardware', '1', 3, '45.0000', '135.0000', '0.0000', 0, '', ''),
(93, 69, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(94, 69, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 2, '45.0000', '90.0000', '0.0000', 0, '', ''),
(95, 69, 51, 'WIFI One gang lighting switch_ Free Hardware', '1', 1, '78.0000', '78.0000', '0.0000', 0, '', ''),
(96, 69, 52, 'WIFI socket switch _ Free Hardware', '1', 3, '45.0000', '135.0000', '0.0000', 0, '', ''),
(97, 70, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '0.0000', '0.0000', '0.0000', 0, '', ''),
(98, 70, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 2, '45.0000', '90.0000', '0.0000', 0, '', ''),
(99, 70, 51, 'WIFI One gang lighting switch_ Free Hardware', '1', 1, '78.0000', '78.0000', '0.0000', 0, '', ''),
(100, 70, 52, 'WIFI socket switch _ Free Hardware', '1', 4, '45.0000', '180.0000', '0.0000', 0, '', ''),
(101, 71, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '180.0000', '180.0000', '0.0000', 0, '', ''),
(102, 72, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 2, '180.0000', '360.0000', '0.0000', 0, '', ''),
(103, 73, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 2, '180.0000', '360.0000', '0.0000', 0, '', ''),
(104, 74, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 2, '180.0000', '360.0000', '0.0000', 0, 'sit_final_livx,sit_final_livx_1', 'logo.png,Hospital Admissions Per Corp.png'),
(105, 75, 53, 'WIFI A/C switch _ Free Hardware ', '1', 1, '180.0000', '180.0000', '0.0000', 0, 'sit_livx_final_1', 'hoonppubf.png'),
(106, 76, 53, 'WIFI A/C switch _ Free Hardware ', '1', 1, '45.0000', '45.0000', '0.0000', 0, 'liv_sit', 'hoonppubf.png'),
(107, 77, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '432.0000', '432.0000', '0.0000', 0, 'ssit_liv', 'Hospital Admissions Per Corp.png'),
(108, 78, 53, 'WIFI A/C switch _ Free Hardware ', '1', 2, '108.0000', '216.0000', '0.0000', 0, 'sit_mac_test1234,sit_mac_test_45678', 'logo.png,Hospital Admissions Per Corp.png'),
(109, 79, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '432.0000', '432.0000', '0.0000', 0, 'sstti_new1', 'logo.png'),
(114, 80, 53, 'WIFI A/C switch _ Free Hardware ', '1', 1, '45.0000', '45.0000', '0.0000', 0, '12345', ''),
(111, 81, 53, 'WIFI A/C switch _ Free Hardware ', '1', 1, '180.0000', '180.0000', '0.0000', 0, '', ''),
(112, 82, 53, 'WIFI A/C switch _ Free Hardware ', '1', 1, '45.0000', '45.0000', '0.0000', 0, '', ''),
(113, 83, 53, 'WIFI A/C switch _ Free Hardware ', '1', 1, '78.0000', '78.0000', '0.0000', 0, '', ''),
(115, 84, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '78.0000', '78.0000', '0.0000', 0, '', ''),
(116, 85, 53, 'WIFI A/C switch _ Free Hardware ', '1', 1, '78.0000', '78.0000', '0.0000', 0, '4abc', ''),
(117, 86, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '45.0000', '45.0000', '0.0000', 0, '3abc', ''),
(118, 87, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 2, '45.0000', '90.0000', '0.0000', 0, 'rty8', ''),
(119, 88, 51, 'WIFI One gang lighting switch_ Free Hardware', '1', 1, '45.0000', '45.0000', '0.0000', 0, 'mnt3', ''),
(120, 89, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '432.0000', '432.0000', '0.0000', 0, '', ''),
(121, 90, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '432.0000', '432.0000', '0.0000', 0, '', ''),
(122, 90, 53, 'WIFI A/C switch _ Free Hardware ', '1', 2, '45.0000', '90.0000', '0.0000', 0, '', ''),
(123, 91, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '78.0000', '78.0000', '0.0000', 0, '', ''),
(124, 91, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '312.0000', '312.0000', '0.0000', 0, '', ''),
(125, 92, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '432.0000', '432.0000', '0.0000', 0, '', ''),
(126, 93, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '432.0000', '432.0000', '0.0000', 0, 'plm2', ''),
(127, 94, 51, 'WIFI One gang lighting switch_ Free Hardware', '1', 1, '108.0000', '108.0000', '0.0000', 0, 'okn2', ''),
(128, 95, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '432.0000', '432.0000', '0.0000', 0, '', ''),
(129, 95, 53, 'WIFI A/C switch _ Free Hardware ', '1', 3, '45.0000', '135.0000', '0.0000', 0, '', ''),
(130, 95, 53, 'WIFI A/C switch _ Free Hardware ', '1', 1, '432.0000', '432.0000', '0.0000', 0, '', ''),
(131, 96, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '432.0000', '432.0000', '0.0000', 0, '', ''),
(132, 96, 53, 'WIFI A/C switch _ Free Hardware ', '1', 3, '45.0000', '135.0000', '0.0000', 0, '', ''),
(133, 96, 53, 'WIFI A/C switch _ Free Hardware ', '1', 1, '432.0000', '432.0000', '0.0000', 0, '', ''),
(134, 97, 53, 'WIFI A/C switch _ Free Hardware ', '1', 3, '45.0000', '135.0000', '0.0000', 0, 'qaz1,qaz2,qaz3', ''),
(135, 98, 53, 'WIFI A/C switch _ Free Hardware ', '1', 1, '45.0000', '45.0000', '0.0000', 0, 'qaz4', ''),
(136, 99, 52, 'WIFI socket switch _ Free Hardware', '1', 1, '45.0000', '45.0000', '0.0000', 0, 'uhb3', ''),
(137, 100, 51, 'WIFI One gang lighting switch_ Free Hardware', '1', 2, '108.0000', '216.0000', '0.0000', 0, 'ygv2,ygv3', ''),
(138, 101, 51, 'WIFI One gang lighting switch_ Free Hardware', '1', 1, '45.0000', '45.0000', '0.0000', 0, '', ''),
(139, 102, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '45.0000', '45.0000', '0.0000', 0, '', ''),
(140, 102, 53, 'WIFI A/C switch _ Free Hardware ', '1', 1, '78.0000', '78.0000', '0.0000', 0, '', ''),
(141, 103, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '312.0000', '312.0000', '0.0000', 0, '22222222', ''),
(142, 104, 53, 'WIFI A/C switch _ Free Hardware ', '1', 1, '45.0000', '45.0000', '0.0000', 0, '', ''),
(143, 105, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '432.0000', '432.0000', '0.0000', 0, '10B65603', ''),
(144, 106, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '45.0000', '45.0000', '0.0000', 0, 'plm3', ''),
(145, 107, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '432.0000', '432.0000', '0.0000', 0, '10B65603', ''),
(146, 108, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '432.0000', '432.0000', '0.0000', 0, '10B65603', ''),
(147, 109, 53, 'WIFI A/C switch _ Free Hardware ', '1', 1, '45.0000', '45.0000', '0.0000', 0, '', ''),
(148, 110, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '45.0000', '45.0000', '0.0000', 0, 'pp', ''),
(149, 110, 51, 'WIFI One gang lighting switch_ Free Hardware', '1', 2, '108.0000', '216.0000', '0.0000', 0, 'sss,fff', ''),
(150, 110, 53, 'WIFI A/C switch _ Free Hardware ', '1', 2, '78.0000', '156.0000', '0.0000', 0, 'ooo,hhhhh', ''),
(151, 111, 50, ' WIFI Three gangs lighting switch _ Free Hardware', '1', 1, '45.0000', '45.0000', '0.0000', 0, 'DB876703', ''),
(152, 111, 51, 'WIFI One gang lighting switch_ Free Hardware', '1', 1, '108.0000', '108.0000', '0.0000', 0, '0A581C04', ''),
(153, 111, 53, 'WIFI A/C switch _ Free Hardware ', '1', 1, '312.0000', '312.0000', '0.0000', 0, 'DB8AC005', ''),
(154, 112, 54, 'WIFI Two gangs lighting switch_ Free Hardware', '1', 1, '432.0000', '432.0000', '0.0000', 0, 'DB8E4D02', ''),
(155, 113, 51, 'مفتاح إنارة خط واحد واي فاي - الجهاز مجانا', '1', 2, '432.0000', '864.0000', '0.0000', 0, '', ''),
(156, 113, 54, 'مفتاح إنارة خطين واي فاي - الجهاز مجانا', '1', 2, '432.0000', '864.0000', '0.0000', 0, '', ''),
(166, 119, 54, 'مفتاح إنارة خطين واي فاي - الجهاز مجانا', '1', 1, '432.0000', '432.0000', '0.0000', 0, 'asd4', ''),
(167, 120, 54, 'مفتاح إنارة خطين واي فاي - الجهاز مجانا', '1', 1, '432.0000', '432.0000', '0.0000', 0, 'DB8E4602', ''),
(159, 115, 51, 'مفتاح إنارة خط واحد واي فاي - الجهاز مجانا', '1', 2, '432.0000', '864.0000', '0.0000', 0, '', ''),
(160, 115, 54, 'مفتاح إنارة خطين واي فاي - الجهاز مجانا', '1', 2, '432.0000', '864.0000', '0.0000', 0, '', '');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_order_recurring`
--

CREATE TABLE `ocn8_order_recurring` (
  `order_recurring_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `reference` varchar(255) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_quantity` int(11) NOT NULL,
  `recurring_id` int(11) NOT NULL,
  `recurring_name` varchar(255) NOT NULL,
  `recurring_description` varchar(255) NOT NULL,
  `recurring_frequency` varchar(25) NOT NULL,
  `recurring_cycle` smallint(6) NOT NULL,
  `recurring_duration` smallint(6) NOT NULL,
  `recurring_price` decimal(10,4) NOT NULL,
  `trial` tinyint(1) NOT NULL,
  `trial_frequency` varchar(25) NOT NULL,
  `trial_cycle` smallint(6) NOT NULL,
  `trial_duration` smallint(6) NOT NULL,
  `trial_price` decimal(10,4) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_order_recurring_transaction`
--

CREATE TABLE `ocn8_order_recurring_transaction` (
  `order_recurring_transaction_id` int(11) NOT NULL,
  `order_recurring_id` int(11) NOT NULL,
  `reference` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `amount` decimal(10,4) NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_order_shipment`
--

CREATE TABLE `ocn8_order_shipment` (
  `order_shipment_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `date_added` datetime NOT NULL,
  `shipping_courier_id` varchar(255) NOT NULL DEFAULT '',
  `tracking_number` varchar(255) NOT NULL DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_order_status`
--

CREATE TABLE `ocn8_order_status` (
  `order_status_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_order_status`
--

INSERT INTO `ocn8_order_status` (`order_status_id`, `language_id`, `name`) VALUES
(2, 1, 'Processing'),
(3, 1, 'Shipped'),
(7, 1, 'Canceled'),
(5, 1, 'Complete'),
(8, 1, 'Denied'),
(9, 1, 'Canceled Reversal'),
(10, 1, 'Failed'),
(11, 1, 'Refunded'),
(12, 1, 'Reversed'),
(13, 1, 'Chargeback'),
(1, 1, 'Pending'),
(16, 1, 'Voided'),
(15, 1, 'Processed'),
(14, 1, 'Expired'),
(2, 2, 'Processing'),
(3, 2, 'Shipped'),
(7, 2, 'Canceled'),
(5, 2, 'Complete'),
(8, 2, 'Denied'),
(9, 2, 'Canceled Reversal'),
(10, 2, 'Failed'),
(11, 2, 'Refunded'),
(12, 2, 'Reversed'),
(13, 2, 'Chargeback'),
(1, 2, 'Pending'),
(16, 2, 'Voided'),
(15, 2, 'Processed'),
(14, 2, 'Expired'),
(17, 1, 'Ready to Ship'),
(17, 2, 'على استعداد للسفينة');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_order_total`
--

CREATE TABLE `ocn8_order_total` (
  `order_total_id` int(10) NOT NULL,
  `order_id` int(11) NOT NULL,
  `code` varchar(32) NOT NULL,
  `title` varchar(255) NOT NULL,
  `value` decimal(15,4) NOT NULL DEFAULT 0.0000,
  `sort_order` int(3) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_order_total`
--

INSERT INTO `ocn8_order_total` (`order_total_id`, `order_id`, `code`, `title`, `value`, `sort_order`) VALUES
(1, 1, 'sub_total', 'Sub-Total', '80.0000', 1),
(2, 1, 'shipping', 'Flat Shipping Rate', '5.0000', 3),
(3, 1, 'total', 'Total', '85.0000', 9),
(4, 2, 'sub_total', 'Sub-Total', '280.0000', 1),
(5, 2, 'shipping', 'Flat Shipping Rate', '5.0000', 3),
(6, 2, 'total', 'Total', '285.0000', 9),
(7, 3, 'sub_total', 'Sub-Total', '159.0000', 1),
(8, 3, 'shipping', 'Flat Shipping Rate', '5.0000', 3),
(9, 3, 'total', 'Total', '164.0000', 9),
(10, 4, 'sub_total', 'Sub-Total', '280.0000', 1),
(11, 4, 'shipping', 'Flat Shipping Rate', '5.0000', 3),
(12, 4, 'total', 'Total', '285.0000', 9),
(13, 5, 'sub_total', 'الاجمالي', '51.0000', 1),
(14, 5, 'shipping', 'تكلفة ثابتة للشحن', '5.0000', 3),
(15, 5, 'total', 'الاجمالي النهائي', '56.0000', 9),
(16, 6, 'sub_total', 'الاجمالي', '15.0000', 1),
(17, 6, 'shipping', 'تكلفة ثابتة للشحن', '0.0000', 3),
(18, 6, 'total', 'الاجمالي النهائي', '15.0000', 9),
(19, 7, 'sub_total', 'Sub-Total', '963.0000', 1),
(20, 7, 'Packages', 'Packages', '963.0000', 2),
(21, 7, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(22, 7, 'total', 'Total', '1926.0000', 9),
(23, 8, 'sub_total', 'Sub-Total', '963.0000', 1),
(24, 8, 'Packages', 'Packages', '963.0000', 2),
(25, 8, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(26, 8, 'total', 'Total', '1926.0000', 9),
(27, 9, 'sub_total', 'Sub-Total', '963.0000', 1),
(28, 9, 'Packages', 'Packages', '963.0000', 2),
(29, 9, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(30, 9, 'total', 'Total', '1926.0000', 9),
(31, 10, 'sub_total', 'Sub-Total', '0.0000', 1),
(32, 10, 'Packages', 'Packages', '15.0000', 2),
(33, 10, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(34, 10, 'total', 'Total', '15.0000', 9),
(35, 11, 'sub_total', 'Sub-Total', '0.0000', 1),
(36, 11, 'Packages', 'Packages', '15.0000', 2),
(37, 11, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(38, 11, 'total', 'Total', '15.0000', 9),
(39, 12, 'sub_total', 'Sub-Total', '0.0000', 1),
(40, 12, 'Packages', 'Packages', '15.0000', 2),
(41, 12, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(42, 12, 'total', 'Total', '15.0000', 9),
(43, 13, 'sub_total', 'Sub-Total', '0.0000', 1),
(44, 13, 'Packages', 'Packages', '15.0000', 2),
(45, 13, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(46, 13, 'total', 'Total', '15.0000', 9),
(47, 14, 'sub_total', 'Sub-Total', '0.0000', 1),
(48, 14, 'Packages', 'Packages', '15.0000', 2),
(49, 14, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(50, 14, 'total', 'Total', '15.0000', 9),
(51, 15, 'sub_total', 'Sub-Total', '0.0000', 1),
(52, 15, 'Packages', 'Packages', '15.0000', 2),
(53, 15, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(54, 15, 'total', 'Total', '15.0000', 9),
(55, 16, 'sub_total', 'Sub-Total', '0.0000', 1),
(56, 16, 'Packages', 'Packages', '15.0000', 2),
(57, 16, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(58, 16, 'total', 'Total', '15.0000', 9),
(59, 17, 'sub_total', 'Sub-Total', '0.0000', 1),
(60, 17, 'Packages', 'Packages', '15.0000', 2),
(61, 17, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(62, 17, 'total', 'Total', '15.0000', 9),
(63, 18, 'sub_total', 'Sub-Total', '0.0000', 1),
(64, 18, 'Packages', 'Packages', '15.0000', 2),
(65, 18, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(66, 18, 'total', 'Total', '15.0000', 9),
(67, 19, 'sub_total', 'Sub-Total', '0.0000', 1),
(68, 19, 'Packages', 'Packages', '15.0000', 2),
(69, 19, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(70, 19, 'total', 'Total', '15.0000', 9),
(71, 20, 'sub_total', 'Sub-Total', '0.0000', 1),
(72, 20, 'Packages', 'Packages', '15.0000', 2),
(73, 20, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(74, 20, 'total', 'Total', '15.0000', 9),
(75, 21, 'sub_total', 'Sub-Total', '0.0000', 1),
(76, 21, 'Packages', 'Packages', '432.0000', 2),
(77, 21, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(78, 21, 'total', 'Total', '432.0000', 9),
(79, 22, 'sub_total', 'Sub-Total', '0.0000', 1),
(80, 22, 'Packages', 'Packages', '432.0000', 2),
(81, 22, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(82, 22, 'total', 'Total', '432.0000', 9),
(83, 23, 'sub_total', 'Sub-Total', '0.0000', 1),
(84, 23, 'Packages', 'Packages', '30.0000', 2),
(85, 23, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(86, 23, 'total', 'Total', '30.0000', 9),
(87, 24, 'sub_total', 'Sub-Total', '0.0000', 1),
(88, 24, 'Packages', 'Packages', '30.0000', 2),
(89, 24, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(90, 24, 'total', 'Total', '0.0000', 9),
(91, 25, 'sub_total', 'الاجمالي', '15.0000', 1),
(92, 25, 'Packages', 'Packages', '15.0000', 2),
(93, 25, 'shipping', 'تكلفة ثابتة للشحن', '0.0000', 3),
(94, 25, 'total', 'الاجمالي النهائي', '15.0000', 9),
(95, 26, 'sub_total', 'Sub-Total', '0.0000', 1),
(96, 26, 'Packages', 'Packages', '0.0000', 2),
(97, 26, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(98, 26, 'total', 'Total', '0.0000', 9),
(99, 27, 'sub_total', 'Sub-Total', '0.0000', 1),
(100, 27, 'Packages', 'Packages', '15.0000', 2),
(101, 27, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(102, 27, 'total', 'Total', '0.0000', 9),
(103, 28, 'sub_total', 'Sub-Total', '36.0000', 1),
(104, 28, 'Packages', 'Packages', '36.0000', 2),
(105, 28, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(106, 28, 'total', 'Total', '36.0000', 9),
(107, 29, 'sub_total', 'Sub-Total', '36.0000', 1),
(108, 29, 'Packages', 'Packages', '36.0000', 2),
(109, 29, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(110, 29, 'total', 'Total', '36.0000', 9),
(111, 30, 'sub_total', 'Sub-Total', '0.0000', 1),
(112, 30, 'Packages', 'Packages', '30.0000', 2),
(113, 30, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(114, 30, 'total', 'Total', '0.0000', 9),
(115, 31, 'sub_total', 'Sub-Total', '0.0000', 1),
(116, 31, 'Packages', 'Packages', '36.0000', 2),
(117, 31, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(118, 31, 'total', 'Total', '0.0000', 9),
(119, 32, 'sub_total', 'Sub-Total', '0.0000', 1),
(120, 32, 'Packages', 'Packages', '45.0000', 2),
(121, 32, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(122, 32, 'total', 'Total', '0.0000', 9),
(123, 33, 'sub_total', 'Sub-Total', '45.0000', 1),
(124, 33, 'Packages', 'Packages', '45.0000', 2),
(125, 33, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(126, 33, 'total', 'Total', '45.0000', 9),
(127, 34, 'sub_total', 'Sub-Total', '180.0000', 1),
(128, 34, 'Packages', 'Packages', '15.0000', 2),
(129, 34, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(130, 34, 'total', 'Total', '180.0000', 9),
(131, 35, 'sub_total', 'Sub-Total', '15.0000', 1),
(132, 35, 'Packages', 'Packages', '0.0000', 2),
(133, 35, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(134, 35, 'total', 'Total', '15.0000', 9),
(135, 36, 'sub_total', 'Sub-Total', '26.0000', 1),
(136, 36, 'Packages', 'Packages', '0.0000', 2),
(137, 36, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(138, 36, 'total', 'Total', '26.0000', 9),
(139, 37, 'sub_total', 'Sub-Total', '26.0000', 1),
(140, 37, 'Packages', 'Packages', '0.0000', 2),
(141, 37, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(142, 37, 'total', 'Total', '26.0000', 9),
(143, 38, 'sub_total', 'Sub-Total', '26.0000', 1),
(144, 38, 'Packages', 'Packages', '0.0000', 2),
(145, 38, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(146, 38, 'total', 'Total', '26.0000', 9),
(147, 39, 'sub_total', 'Sub-Total', '180.0000', 1),
(148, 39, 'Packages', 'Packages', '0.0000', 2),
(149, 39, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(150, 39, 'total', 'Total', '180.0000', 9),
(151, 40, 'sub_total', 'Sub-Total', '180.0000', 1),
(152, 40, 'Packages', 'Packages', '0.0000', 2),
(153, 40, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(154, 40, 'total', 'Total', '180.0000', 9),
(155, 41, 'sub_total', 'Sub-Total', '180.0000', 1),
(156, 41, 'Packages', 'Packages', '0.0000', 2),
(157, 41, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(158, 41, 'total', 'Total', '180.0000', 9),
(159, 42, 'sub_total', 'Sub-Total', '36.0000', 1),
(160, 42, 'Packages', 'Packages', '0.0000', 2),
(161, 42, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(162, 42, 'total', 'Total', '36.0000', 9),
(163, 43, 'sub_total', 'Sub-Total', '36.0000', 1),
(164, 43, 'Packages', 'Packages', '0.0000', 2),
(165, 43, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(166, 43, 'total', 'Total', '36.0000', 9),
(167, 44, 'sub_total', 'Sub-Total', '36.0000', 1),
(168, 44, 'Packages', 'Packages', '0.0000', 2),
(169, 44, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(170, 44, 'total', 'Total', '36.0000', 9),
(171, 45, 'sub_total', 'Sub-Total', '216.0000', 1),
(172, 45, 'Packages', 'Packages', '0.0000', 2),
(173, 45, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(174, 45, 'total', 'Total', '216.0000', 9),
(175, 46, 'sub_total', 'Sub-Total', '195.0000', 1),
(176, 46, 'Packages', 'Packages', '0.0000', 2),
(177, 46, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(178, 46, 'total', 'Total', '195.0000', 9),
(179, 47, 'sub_total', 'Sub-Total', '15.0000', 1),
(180, 47, 'Packages', 'Packages', '0.0000', 2),
(181, 47, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(182, 47, 'total', 'Total', '15.0000', 9),
(183, 48, 'sub_total', 'Sub-Total', '30.0000', 1),
(184, 48, 'Packages', 'Packages', '0.0000', 2),
(185, 48, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(186, 48, 'total', 'Total', '30.0000', 9),
(187, 49, 'sub_total', 'Sub-Total', '30.0000', 1),
(188, 49, 'Packages', 'Packages', '0.0000', 2),
(189, 49, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(190, 49, 'total', 'Total', '30.0000', 9),
(191, 50, 'sub_total', 'Sub-Total', '60.0000', 1),
(192, 50, 'Packages', 'Packages', '0.0000', 2),
(193, 50, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(194, 50, 'total', 'Total', '60.0000', 9),
(195, 51, 'sub_total', 'Sub-Total', '15.0000', 1),
(196, 51, 'Packages', 'Packages', '0.0000', 2),
(197, 51, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(198, 51, 'total', 'Total', '15.0000', 9),
(199, 52, 'sub_total', 'Sub-Total', '30.0000', 1),
(200, 52, 'Packages', 'Packages', '0.0000', 2),
(201, 52, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(202, 52, 'total', 'Total', '30.0000', 9),
(203, 53, 'sub_total', 'Sub-Total', '26.0000', 1),
(204, 53, 'Packages', 'Packages', '0.0000', 2),
(205, 53, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(206, 53, 'total', 'Total', '26.0000', 9),
(207, 54, 'sub_total', 'Sub-Total', '26.0000', 1),
(208, 54, 'Packages', 'Packages', '0.0000', 2),
(209, 54, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(210, 54, 'total', 'Total', '26.0000', 9),
(211, 55, 'sub_total', 'Sub-Total', '1452.0000', 1),
(212, 55, 'Packages', 'Packages', '0.0000', 2),
(213, 55, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(214, 55, 'total', 'Total', '1452.0000', 9),
(215, 56, 'sub_total', 'الاجمالي', '123.0000', 1),
(216, 56, 'Packages', 'Packages', '0.0000', 2),
(217, 56, 'shipping', 'تكلفة ثابتة للشحن', '0.0000', 3),
(218, 56, 'total', 'الاجمالي النهائي', '123.0000', 9),
(219, 57, 'sub_total', 'Sub-Total', '123.0000', 1),
(220, 57, 'Packages', 'Packages', '0.0000', 2),
(221, 57, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(222, 57, 'total', 'Total', '123.0000', 9),
(223, 58, 'sub_total', 'Sub-Total', '450.0000', 1),
(224, 58, 'Packages', 'Packages', '0.0000', 2),
(225, 58, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(226, 58, 'total', 'Total', '450.0000', 9),
(227, 59, 'sub_total', 'Sub-Total', '168.0000', 1),
(228, 59, 'Packages', 'Packages', '0.0000', 2),
(229, 59, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(230, 59, 'total', 'Total', '168.0000', 9),
(231, 60, 'sub_total', 'Sub-Total', '213.0000', 1),
(232, 60, 'Packages', 'Packages', '0.0000', 2),
(233, 60, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(234, 60, 'total', 'Total', '213.0000', 9),
(235, 61, 'sub_total', 'Sub-Total', '1080.0000', 1),
(236, 61, 'Packages', 'Packages', '0.0000', 2),
(237, 61, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(238, 61, 'total', 'Total', '1080.0000', 9),
(239, 62, 'sub_total', 'Sub-Total', '1080.0000', 1),
(240, 62, 'Packages', 'Packages', '0.0000', 2),
(241, 62, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(242, 62, 'total', 'Total', '1080.0000', 9),
(243, 63, 'sub_total', 'Sub-Total', '135.0000', 1),
(244, 63, 'Packages', 'Packages', '0.0000', 2),
(245, 63, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(246, 63, 'total', 'Total', '135.0000', 9),
(247, 64, 'sub_total', 'Sub-Total', '450.0000', 1),
(248, 64, 'Packages', 'Packages', '0.0000', 2),
(249, 64, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(250, 64, 'total', 'Total', '450.0000', 9),
(251, 65, 'sub_total', 'Sub-Total', '45.0000', 1),
(252, 65, 'Packages', 'Packages', '0.0000', 2),
(253, 65, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(254, 65, 'total', 'Total', '45.0000', 9),
(255, 66, 'sub_total', 'Sub-Total', '45.0000', 1),
(256, 66, 'Packages', 'Packages', '0.0000', 2),
(257, 66, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(258, 66, 'total', 'Total', '45.0000', 9),
(259, 67, 'sub_total', 'Sub-Total', '45.0000', 1),
(260, 67, 'Packages', 'Packages', '0.0000', 2),
(261, 67, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(262, 67, 'total', 'Total', '45.0000', 9),
(263, 68, 'sub_total', 'Sub-Total', '303.0000', 1),
(264, 68, 'Packages', 'Packages', '0.0000', 2),
(265, 68, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(266, 68, 'total', 'Total', '303.0000', 9),
(267, 69, 'sub_total', 'Sub-Total', '303.0000', 1),
(268, 69, 'Packages', 'Packages', '0.0000', 2),
(269, 69, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(270, 69, 'total', 'Total', '303.0000', 9),
(271, 70, 'sub_total', 'Sub-Total', '348.0000', 1),
(272, 70, 'Packages', 'Packages', '0.0000', 2),
(273, 70, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(274, 70, 'total', 'Total', '348.0000', 9),
(275, 71, 'sub_total', 'Sub-Total', '180.0000', 1),
(276, 71, 'Packages', 'Packages', '0.0000', 2),
(277, 71, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(278, 71, 'total', 'Total', '180.0000', 9),
(279, 72, 'sub_total', 'Sub-Total', '360.0000', 1),
(280, 72, 'Packages', 'Packages', '0.0000', 2),
(281, 72, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(282, 72, 'total', 'Total', '360.0000', 9),
(283, 73, 'sub_total', 'Sub-Total', '360.0000', 1),
(284, 73, 'Packages', 'Packages', '0.0000', 2),
(285, 73, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(286, 73, 'total', 'Total', '360.0000', 9),
(287, 74, 'sub_total', 'Sub-Total', '360.0000', 1),
(288, 74, 'Packages', 'Packages', '0.0000', 2),
(289, 74, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(290, 74, 'total', 'Total', '360.0000', 9),
(291, 75, 'sub_total', 'Sub-Total', '180.0000', 1),
(292, 75, 'Packages', 'Packages', '0.0000', 2),
(293, 75, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(294, 75, 'total', 'Total', '180.0000', 9),
(295, 76, 'sub_total', 'Sub-Total', '45.0000', 1),
(296, 76, 'Packages', 'Packages', '0.0000', 2),
(297, 76, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(298, 76, 'total', 'Total', '45.0000', 9),
(299, 77, 'sub_total', 'Sub-Total', '432.0000', 1),
(300, 77, 'Packages', 'Packages', '0.0000', 2),
(301, 77, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(302, 77, 'total', 'Total', '432.0000', 9),
(303, 78, 'sub_total', 'Sub-Total', '216.0000', 1),
(304, 78, 'Packages', 'Packages', '0.0000', 2),
(305, 78, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(306, 78, 'total', 'Total', '216.0000', 9),
(307, 79, 'sub_total', 'Sub-Total', '432.0000', 1),
(308, 79, 'Packages', 'Packages', '0.0000', 2),
(309, 79, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(310, 79, 'total', 'Total', '432.0000', 9),
(328, 80, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(327, 80, 'sub_total', 'Sub-Total', '45.0000', 1),
(315, 81, 'sub_total', 'Sub-Total', '180.0000', 1),
(316, 81, 'Packages', 'Packages', '0.0000', 2),
(317, 81, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(318, 81, 'total', 'Total', '180.0000', 9),
(319, 82, 'sub_total', 'Sub-Total', '45.0000', 1),
(320, 82, 'Packages', 'Packages', '0.0000', 2),
(321, 82, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(322, 82, 'total', 'Total', '45.0000', 9),
(323, 83, 'sub_total', 'Sub-Total', '78.0000', 1),
(324, 83, 'Packages', 'Packages', '0.0000', 2),
(325, 83, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(326, 83, 'total', 'Total', '78.0000', 9),
(329, 80, 'total', 'Total', '45.0000', 9),
(330, 84, 'sub_total', 'Sub-Total', '78.0000', 1),
(331, 84, 'Packages', 'Packages', '0.0000', 2),
(332, 84, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(333, 84, 'total', 'Total', '78.0000', 9),
(334, 85, 'sub_total', 'Sub-Total', '78.0000', 1),
(335, 85, 'Packages', 'Packages', '0.0000', 2),
(336, 85, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(337, 85, 'total', 'Total', '78.0000', 9),
(338, 86, 'sub_total', 'Sub-Total', '45.0000', 1),
(339, 86, 'Packages', 'Packages', '0.0000', 2),
(340, 86, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(341, 86, 'total', 'Total', '45.0000', 9),
(342, 87, 'sub_total', 'Sub-Total', '90.0000', 1),
(343, 87, 'Packages', 'Packages', '0.0000', 2),
(344, 87, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(345, 87, 'total', 'Total', '90.0000', 9),
(346, 88, 'sub_total', 'Sub-Total', '45.0000', 1),
(347, 88, 'Packages', 'Packages', '0.0000', 2),
(348, 88, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(349, 88, 'total', 'Total', '45.0000', 9),
(350, 89, 'sub_total', 'Sub-Total', '432.0000', 1),
(351, 89, 'Packages', 'Packages', '0.0000', 2),
(352, 89, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(353, 89, 'total', 'Total', '432.0000', 9),
(354, 90, 'sub_total', 'Sub-Total', '522.0000', 1),
(355, 90, 'Packages', 'Packages', '0.0000', 2),
(356, 90, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(357, 90, 'total', 'Total', '522.0000', 9),
(358, 91, 'sub_total', 'Sub-Total', '390.0000', 1),
(359, 91, 'Packages', 'Packages', '0.0000', 2),
(360, 91, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(361, 91, 'total', 'Total', '390.0000', 9),
(362, 92, 'sub_total', 'Sub-Total', '432.0000', 1),
(363, 92, 'Packages', 'Packages', '0.0000', 2),
(364, 92, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(365, 92, 'total', 'Total', '432.0000', 9),
(366, 93, 'sub_total', 'Sub-Total', '432.0000', 1),
(367, 93, 'Packages', 'Packages', '0.0000', 2),
(368, 93, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(369, 93, 'total', 'Total', '432.0000', 9),
(370, 94, 'sub_total', 'Sub-Total', '108.0000', 1),
(371, 94, 'Packages', 'Packages', '0.0000', 2),
(372, 94, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(373, 94, 'total', 'Total', '108.0000', 9),
(374, 95, 'sub_total', 'Sub-Total', '999.0000', 1),
(375, 95, 'Packages', 'Packages', '0.0000', 2),
(376, 95, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(377, 95, 'total', 'Total', '999.0000', 9),
(378, 96, 'sub_total', 'Sub-Total', '999.0000', 1),
(379, 96, 'Packages', 'Packages', '0.0000', 2),
(380, 96, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(381, 96, 'total', 'Total', '999.0000', 9),
(382, 97, 'sub_total', 'Sub-Total', '135.0000', 1),
(383, 97, 'Packages', 'Packages', '0.0000', 2),
(384, 97, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(385, 97, 'total', 'Total', '135.0000', 9),
(386, 98, 'sub_total', 'Sub-Total', '45.0000', 1),
(387, 98, 'Packages', 'Packages', '0.0000', 2),
(388, 98, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(389, 98, 'total', 'Total', '45.0000', 9),
(390, 99, 'sub_total', 'Sub-Total', '45.0000', 1),
(391, 99, 'Packages', 'Packages', '0.0000', 2),
(392, 99, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(393, 99, 'total', 'Total', '45.0000', 9),
(394, 100, 'sub_total', 'Sub-Total', '216.0000', 1),
(395, 100, 'Packages', 'Packages', '0.0000', 2),
(396, 100, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(397, 100, 'total', 'Total', '216.0000', 9),
(398, 101, 'sub_total', 'Sub-Total', '45.0000', 1),
(399, 101, 'Packages', 'Packages', '0.0000', 2),
(400, 101, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(401, 101, 'total', 'Total', '45.0000', 9),
(402, 102, 'sub_total', 'Sub-Total', '123.0000', 1),
(403, 102, 'Packages', 'Packages', '0.0000', 2),
(404, 102, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(405, 102, 'total', 'Total', '123.0000', 9),
(406, 103, 'sub_total', 'Sub-Total', '312.0000', 1),
(407, 103, 'Packages', 'Packages', '0.0000', 2),
(408, 103, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(409, 103, 'total', 'Total', '312.0000', 9),
(410, 104, 'sub_total', 'Sub-Total', '45.0000', 1),
(411, 104, 'Packages', 'Packages', '0.0000', 2),
(412, 104, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(413, 104, 'total', 'Total', '45.0000', 9),
(414, 105, 'sub_total', 'Sub-Total', '432.0000', 1),
(415, 105, 'Packages', 'Packages', '0.0000', 2),
(416, 105, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(417, 105, 'total', 'Total', '432.0000', 9),
(418, 106, 'sub_total', 'Sub-Total', '45.0000', 1),
(419, 106, 'Packages', 'Packages', '0.0000', 2),
(420, 106, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(421, 106, 'total', 'Total', '45.0000', 9),
(422, 107, 'sub_total', 'Sub-Total', '432.0000', 1),
(423, 107, 'Packages', 'Packages', '0.0000', 2),
(424, 107, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(425, 107, 'total', 'Total', '432.0000', 9),
(426, 108, 'sub_total', 'Sub-Total', '432.0000', 1),
(427, 108, 'Packages', 'Packages', '0.0000', 2),
(428, 108, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(429, 108, 'total', 'Total', '432.0000', 9),
(430, 109, 'sub_total', 'Sub-Total', '45.0000', 1),
(431, 109, 'Packages', 'Packages', '0.0000', 2),
(432, 109, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(433, 109, 'total', 'Total', '45.0000', 9),
(434, 110, 'sub_total', 'Sub-Total', '417.0000', 1),
(435, 110, 'Packages', 'Packages', '0.0000', 2),
(436, 110, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(437, 110, 'total', 'Total', '417.0000', 9),
(438, 111, 'sub_total', 'Sub-Total', '465.0000', 1),
(439, 111, 'Packages', 'Packages', '0.0000', 2),
(440, 111, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(441, 111, 'total', 'Total', '465.0000', 9),
(442, 112, 'sub_total', 'Sub-Total', '432.0000', 1),
(443, 112, 'Packages', 'Packages', '0.0000', 2),
(444, 112, 'shipping', 'Flat Shipping Rate', '0.0000', 3),
(445, 112, 'total', 'Total', '432.0000', 9),
(446, 113, 'sub_total', 'الاجمالي', '1728.0000', 1),
(447, 113, 'Packages', 'Packages', '0.0000', 2),
(448, 113, 'shipping', 'تكلفة ثابتة للشحن', '0.0000', 3),
(449, 113, 'total', 'الاجمالي النهائي', '1728.0000', 9),
(470, 119, 'shipping', 'تكلفة ثابتة للشحن', '0.0000', 3),
(469, 119, 'Packages', 'Packages', '0.0000', 2),
(468, 119, 'sub_total', 'الاجمالي', '432.0000', 1),
(454, 115, 'sub_total', 'الاجمالي', '1728.0000', 1),
(455, 115, 'Packages', 'Packages', '0.0000', 2),
(456, 115, 'shipping', 'تكلفة ثابتة للشحن', '0.0000', 3),
(457, 115, 'total', 'الاجمالي النهائي', '1728.0000', 9),
(473, 120, 'shipping', 'تكلفة ثابتة للشحن', '0.0000', 3),
(472, 120, 'sub_total', 'الاجمالي', '432.0000', 1),
(471, 119, 'total', 'الاجمالي النهائي', '432.0000', 9),
(474, 120, 'total', 'الاجمالي النهائي', '432.0000', 9);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_order_voucher`
--

CREATE TABLE `ocn8_order_voucher` (
  `order_voucher_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `voucher_id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `code` varchar(10) NOT NULL,
  `from_name` varchar(64) NOT NULL,
  `from_email` varchar(96) NOT NULL,
  `to_name` varchar(64) NOT NULL,
  `to_email` varchar(96) NOT NULL,
  `voucher_theme_id` int(11) NOT NULL,
  `message` text NOT NULL,
  `amount` decimal(15,4) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_product`
--

CREATE TABLE `ocn8_product` (
  `product_id` int(11) NOT NULL,
  `model` varchar(64) NOT NULL,
  `sku` varchar(64) NOT NULL,
  `upc` varchar(12) NOT NULL,
  `ean` varchar(14) NOT NULL,
  `jan` varchar(13) NOT NULL,
  `isbn` varchar(17) NOT NULL,
  `mpn` varchar(64) NOT NULL,
  `location` varchar(128) NOT NULL,
  `quantity` int(4) NOT NULL DEFAULT 0,
  `stock_status_id` int(11) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `manufacturer_id` int(11) NOT NULL,
  `shipping` tinyint(1) NOT NULL DEFAULT 1,
  `price` decimal(15,4) NOT NULL DEFAULT 0.0000,
  `points` int(8) NOT NULL DEFAULT 0,
  `tax_class_id` int(11) NOT NULL,
  `date_available` date NOT NULL DEFAULT '0000-00-00',
  `weight` decimal(15,8) NOT NULL DEFAULT 0.00000000,
  `weight_class_id` int(11) NOT NULL DEFAULT 0,
  `length` decimal(15,8) NOT NULL DEFAULT 0.00000000,
  `width` decimal(15,8) NOT NULL DEFAULT 0.00000000,
  `height` decimal(15,8) NOT NULL DEFAULT 0.00000000,
  `length_class_id` int(11) NOT NULL DEFAULT 0,
  `subtract` tinyint(1) NOT NULL DEFAULT 1,
  `minimum` int(11) NOT NULL DEFAULT 1,
  `sort_order` int(11) NOT NULL DEFAULT 0,
  `status` tinyint(1) NOT NULL DEFAULT 0,
  `viewed` int(5) NOT NULL DEFAULT 0,
  `date_added` datetime NOT NULL,
  `date_modified` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_product`
--

INSERT INTO `ocn8_product` (`product_id`, `model`, `sku`, `upc`, `ean`, `jan`, `isbn`, `mpn`, `location`, `quantity`, `stock_status_id`, `image`, `manufacturer_id`, `shipping`, `price`, `points`, `tax_class_id`, `date_available`, `weight`, `weight_class_id`, `length`, `width`, `height`, `length_class_id`, `subtract`, `minimum`, `sort_order`, `status`, `viewed`, `date_added`, `date_modified`) VALUES
(50, '1', '', '', '', '', '', '', '', 244, 7, 'catalog/Screen Shot 2022-06-02 at 4.17.32 PM.png', 0, 1, '0.0000', 0, 0, '2022-05-31', '0.25000000', 1, '0.00000000', '0.00000000', '0.00000000', 1, 1, 1, 1, 1, 208, '2022-06-01 13:42:15', '2022-07-13 11:07:19'),
(52, '1', '', '', '', '', '', '', '', 768, 6, 'catalog/Capture4.PNG', 0, 1, '0.0000', 0, 0, '2022-06-01', '0.00000000', 1, '0.00000000', '0.00000000', '0.00000000', 1, 1, 1, 1, 1, 111, '2022-06-01 14:46:53', '2022-06-22 10:42:18'),
(53, '1', '', '', '', '', '', '', '', 607, 6, 'catalog/Screen Shot 2022-03-24 at 10.13.47 PM.png', 0, 1, '0.0000', 0, 0, '2022-06-01', '0.00000000', 1, '0.00000000', '0.00000000', '0.00000000', 1, 1, 1, 1, 1, 144, '2022-06-01 14:49:33', '2022-06-20 11:31:40'),
(54, '1', '', '', '', '', '', '', '', 232, 6, 'catalog/Screen Shot 2022-03-24 at 10.13.36 PM.png', 0, 1, '0.0000', 0, 0, '2022-06-01', '0.00000000', 1, '0.00000000', '0.00000000', '0.00000000', 1, 1, 1, 1, 1, 83, '2022-06-02 13:26:34', '2022-06-22 10:42:54'),
(51, '1', '', '', '', '', '', '', '', 256, 6, 'catalog/Capture3.PNG', 0, 1, '0.0000', 0, 0, '2022-06-01', '0.00000000', 1, '0.00000000', '0.00000000', '0.00000000', 1, 1, 1, 1, 1, 108, '2022-06-01 14:44:10', '2022-06-22 10:42:03');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_product_attribute`
--

CREATE TABLE `ocn8_product_attribute` (
  `product_id` int(11) NOT NULL,
  `attribute_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `text` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_product_attribute`
--

INSERT INTO `ocn8_product_attribute` (`product_id`, `attribute_id`, `language_id`, `text`) VALUES
(53, 12, 1, 'Switch installation &gt; Home router connection &gt; Add switch to Livixa application &gt; Control &gt; Room setup on Livixa application.'),
(50, 12, 1, 'Switch installation &gt; Home router connection &gt; Add switch to Livixa application &gt; Control &gt; Room setup on Livixa application.'),
(50, 12, 2, 'تثبيت المفتاح -&gt; الاتصال بجهاز الراوتر المنزلي -&gt; إضافة المفتاح إلى تطبيق ليفيكسا -&gt; التحكم -&gt; إعداد الغرفة على تطبيق ليفيكسا'),
(50, 13, 1, 'power consumption reading as per the tariff set up, with alert set up and notification of power usage limits.'),
(50, 13, 2, 'قراءة الاستهلاك بالتعرفة المحلية للكهرباء .. وإنشاء التنبيهات بالحد الأعلى والمتوسط لاستخدام الطاقة عن طريق التنبيهات '),
(50, 14, 1, 'Sleeping Mood, Guest Mood and Custom Mood which enables users to add extra moods according to preferred lifestyle.'),
(50, 14, 2, 'أوضاع النوم والضيوف والأوضاع المخصصة، التي تمكن المستخدمين من إضافة أوضاع إضافية وفقًا لأسلوب الحياة المفضل. على سبيل المثال ، وضع الحفلة ، ووضع القراءة ، وكبار السن وما إلى ذلك.'),
(52, 12, 1, 'Switch installation &gt; Home router connection &gt; Add switch to Livixa application &gt; Control &gt; Room setup on Livixa application.'),
(53, 13, 1, 'Indicate the local tariff electricity bill and choose the consumption period (daily, Monthly or Annually).'),
(53, 13, 2, 'تحديد فاتورة الكهرباء بالتعرفة المحلية واختيار فترة الاستهلاك (يومي أو شهري أو سنوي) أدخل صفحات أسعار الاستهلاك لمعرفة إجمالي الفاتورة لكل غرفة ولمعرفة سعر الفاتورة الإجمالي لكل مفتاح'),
(53, 14, 1, 'Sleeping Mood, Guest Mood and Custom Mood which enables users to add extra moods according to preferred lifestyle.'),
(53, 14, 2, 'أوضاع النوم والضيوف والأوضاع المخصصة، التي تمكن المستخدمين من إضافة أوضاع إضافية وفقًا لأسلوب الحياة المفضل. على سبيل المثال ، وضع الحفلة ، ووضع القراءة ، وكبار السن وما إلى ذلك.'),
(54, 12, 1, 'Switch installation &gt; Home router connection &gt; Add switch to Livixa application &gt; Control &gt; Room setup on Livixa application.'),
(51, 14, 1, 'Sleeping Mood, Guest Mood and Custom Mood which enables users to add extra moods according to preferred lifestyle.'),
(51, 14, 2, 'أوضاع النوم والضيوف والأوضاع المخصصة، التي تمكن المستخدمين من إضافة أوضاع إضافية وفقًا لأسلوب الحياة المفضل. على سبيل المثال ، وضع الحفلة ، ووضع القراءة ، وكبار السن وما إلى ذلك.'),
(51, 13, 2, 'تحديد فاتورة الكهرباء بالتعرفة المحلية واختيار فترة الاستهلاك (يومي أو شهري أو سنوي) أدخل صفحات أسعار الاستهلاك لمعرفة إجمالي الفاتورة لكل غرفة ولمعرفة سعر الفاتورة الإجمالي لكل مفتاح'),
(51, 13, 1, 'Indicate the local tariff electricity bill and choose the consumption period (daily, Monthly or Annually).'),
(51, 12, 2, 'تثبيت المفتاح -&gt; الاتصال بجهاز الراوتر المنزلي -&gt; إضافة المفتاح إلى تطبيق ليفيكسا -&gt; التحكم -&gt; إعداد الغرفة على تطبيق ليفيكسا'),
(52, 13, 1, 'Indicate the local tariff electricity bill and choose the consumption period (daily, Monthly or Annually).'),
(52, 13, 2, 'تحديد فاتورة الكهرباء بالتعرفة المحلية واختيار فترة الاستهلاك (يومي أو شهري أو سنوي) أدخل صفحات أسعار الاستهلاك لمعرفة إجمالي الفاتورة لكل غرفة ولمعرفة سعر الفاتورة الإجمالي لكل مفتاح'),
(52, 14, 1, 'Sleeping Mood, Guest Mood and Custom Mood which enables users to add extra moods according to preferred lifestyle.'),
(52, 14, 2, 'أوضاع النوم والضيوف والأوضاع المخصصة، التي تمكن المستخدمين من إضافة أوضاع إضافية وفقًا لأسلوب الحياة المفضل. على سبيل المثال ، وضع الحفلة ، ووضع القراءة ، وكبار السن وما إلى ذلك.'),
(53, 12, 2, 'تثبيت المفتاح -&gt; الاتصال بجهاز الراوتر المنزلي -&gt; إضافة المفتاح إلى تطبيق ليفيكسا -&gt; التحكم -&gt; إعداد الغرفة على تطبيق ليفيكسا'),
(54, 12, 2, 'تثبيت المفتاح -&gt; الاتصال بجهاز الراوتر المنزلي -&gt; إضافة المفتاح إلى تطبيق ليفيكسا -&gt; التحكم -&gt; إعداد الغرفة على تطبيق ليفيكسا'),
(54, 13, 1, 'Indicate the local tariff electricity bill and choose the consumption period (daily, Monthly or Annually).'),
(54, 13, 2, 'تحديد فاتورة الكهرباء بالتعرفة المحلية واختيار فترة الاستهلاك (يومي أو شهري أو سنوي) أدخل صفحات أسعار الاستهلاك لمعرفة إجمالي الفاتورة لكل غرفة ولمعرفة سعر الفاتورة الإجمالي لكل مفتاح'),
(54, 14, 1, 'Sleeping Mood, Guest Mood and Custom Mood which enables users to add extra moods according to preferred lifestyle.'),
(54, 14, 2, 'أوضاع النوم والضيوف والأوضاع المخصصة، التي تمكن المستخدمين من إضافة أوضاع إضافية وفقًا لأسلوب الحياة المفضل. على سبيل المثال ، وضع الحفلة ، ووضع القراءة ، وكبار السن وما إلى ذلك.'),
(51, 12, 1, 'Switch installation &gt; Home router connection &gt; Add switch to Livixa application &gt; Control &gt; Room setup on Livixa application.'),
(52, 12, 2, 'تثبيت المفتاح -&gt; الاتصال بجهاز الراوتر المنزلي -&gt; إضافة المفتاح إلى تطبيق ليفيكسا -&gt; التحكم -&gt; إعداد الغرفة على تطبيق ليفيكسا');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_product_description`
--

CREATE TABLE `ocn8_product_description` (
  `product_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `tag` text NOT NULL,
  `meta_title` varchar(255) NOT NULL,
  `meta_description` varchar(255) NOT NULL,
  `meta_keyword` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_product_description`
--

INSERT INTO `ocn8_product_description` (`product_id`, `language_id`, `name`, `description`, `tag`, `meta_title`, `meta_description`, `meta_keyword`) VALUES
(54, 1, 'WIFI Two gangs lighting switch_ Free Hardware', '&lt;ol&gt;&lt;li style=&quot;font-family: SemiBoldText; margin-bottom: 1rem; color: var(--secondary-color); font-size: 24px;&quot;&gt;&lt;span style=&quot;font-size: 24px;&quot;&gt;Dimming ON/OFF automatically&lt;/span&gt;&lt;/li&gt;&lt;li style=&quot;font-family: SemiBoldText; margin-bottom: 1rem; color: var(--secondary-color); font-size: 24px;&quot;&gt;&lt;span style=&quot;font-size: 24px;&quot;&gt;110/220 V automatically&lt;/span&gt;&lt;/li&gt;&lt;li style=&quot;font-family: SemiBoldText; margin-bottom: 1rem; color: var(--secondary-color); font-size: 24px;&quot;&gt;&lt;span style=&quot;font-size: 24px;&quot;&gt;Works with any modern router&lt;/span&gt;&lt;/li&gt;&lt;/ol&gt;', '', 'WIFI Two gangs lighting switch_ Free Hardware', '', ''),
(54, 2, 'مفتاح إنارة خطين واي فاي - الجهاز مجانا', '&lt;p style=&quot;margin-bottom: 1rem; color: var(--secondary-color); font-family: SemiBoldText; font-size: 24px;&quot;&gt;شغيل اليدوي / التطبيق / التلقائي لكل خط إنارة&lt;/p&gt;&lt;p style=&quot;margin-bottom: 1rem; color: var(--secondary-color); font-family: SemiBoldText; font-size: 24px;&quot;&gt;&lt;span style=&quot;color: var(--secondary-color);&quot;&gt;تعمل 110/220 فولت أوتوماتيكياً&lt;/span&gt;&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin-bottom: 1rem; color: var(--secondary-color); font-family: SemiBoldText; font-size: 24px;&quot;&gt;تعمل مع اي راوتر شبكة&lt;/p&gt;&lt;p style=&quot;margin-bottom: 1rem; color: var(--secondary-color); font-family: SemiBoldText; font-size: 24px;&quot;&gt;١١٠٠ واط للخط الواحد&amp;nbsp;&lt;/p&gt;&lt;div&gt;&lt;br&gt;&lt;/div&gt;', '', 'مفتاح إنارة خط واحد', '', ''),
(50, 1, ' WIFI Three gangs lighting switch _ Free Hardware', '&lt;ol&gt;&lt;li style=&quot;font-family: SemiBoldText; margin-bottom: 1rem; color: var(--secondary-color); font-size: 24px;&quot;&gt;&lt;span style=&quot;font-size: 24px;&quot;&gt;Dimming ON/OFF automatically&lt;/span&gt;&lt;/li&gt;&lt;li style=&quot;font-family: SemiBoldText; margin-bottom: 1rem; color: var(--secondary-color); font-size: 24px;&quot;&gt;&lt;span style=&quot;font-size: 24px;&quot;&gt;110/220 V automatically&lt;/span&gt;&lt;/li&gt;&lt;li style=&quot;font-family: SemiBoldText; margin-bottom: 1rem; color: var(--secondary-color); font-size: 24px;&quot;&gt;&lt;span style=&quot;font-size: 24px;&quot;&gt;Works with any modern router&lt;/span&gt;&lt;/li&gt;&lt;/ol&gt;', '', ' WIFI Three gangs lighting switch _ Free Hardware', '', ''),
(50, 2, ' مفتاح إنارة واي فاي ثلاثة خطوط ـ الجهاز مجانا', '&lt;p style=&quot;font-family: SemiBoldText; margin-bottom: 1rem; color: var(--secondary-color); font-size: 24px;&quot;&gt;&lt;span style=&quot;font-size: 24px;&quot;&gt;التشغيل اليدوي / التطبيق / التلقائي لكل خط إنارة&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;font-family: SemiBoldText; margin-bottom: 1rem; color: var(--secondary-color); font-size: 24px;&quot;&gt;&lt;span style=&quot;color: var(--secondary-color);&quot;&gt;تعمل 110/220 فولت أوتوماتيكياً&lt;/span&gt;&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;font-family: SemiBoldText; margin-bottom: 1rem; color: var(--secondary-color); font-size: 24px;&quot;&gt;&lt;span style=&quot;font-size: 24px;&quot;&gt;تعمل مع اي راوتر شبكة&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;font-family: SemiBoldText; margin-bottom: 1rem; color: var(--secondary-color); font-size: 24px;&quot;&gt;&lt;span style=&quot;font-size: 24px;&quot;&gt;١١٠٠ واط للخط الواحد&amp;nbsp;&lt;/span&gt;&lt;/p&gt;&lt;p style=&quot;font-family: SemiBoldText; margin-bottom: 1rem; color: var(--secondary-color); font-size: 24px;&quot;&gt;&lt;span style=&quot;font-size: 24px;&quot;&gt;&lt;br&gt;&lt;/span&gt;&lt;/p&gt;', '', 'مفتاح إنارة ثلاثة خطوط', '', ''),
(53, 1, 'WIFI A/C switch _ Free Hardware ', '&lt;ol&gt;&lt;li style=&quot;font-family: SemiBoldText; margin-bottom: 1rem; color: var(--secondary-color); font-size: 24px;&quot;&gt;&lt;span style=&quot;font-size: 24px;&quot;&gt;Dimming ON/OFF automatically&lt;/span&gt;&lt;/li&gt;&lt;li style=&quot;font-family: SemiBoldText; margin-bottom: 1rem; color: var(--secondary-color); font-size: 24px;&quot;&gt;&lt;span style=&quot;font-size: 24px;&quot;&gt;110/220 V automatically&lt;/span&gt;&lt;/li&gt;&lt;li style=&quot;font-family: SemiBoldText; margin-bottom: 1rem; color: var(--secondary-color); font-size: 24px;&quot;&gt;&lt;span style=&quot;font-size: 24px;&quot;&gt;Works with any modern router&lt;/span&gt;&lt;/li&gt;&lt;/ol&gt;', '', 'WIFI A/C switch _ Free Hardware ', '', ''),
(53, 2, '  مفتاح مكيف - واي فاي الجهاز مجانا', '&lt;p style=&quot;margin-bottom: 1rem; color: var(--secondary-color); font-family: SemiBoldText; font-size: 24px;&quot;&gt;شغيل اليدوي / التطبيق / التلقائي&lt;/p&gt;&lt;p style=&quot;margin-bottom: 1rem; color: var(--secondary-color); font-family: SemiBoldText; font-size: 24px;&quot;&gt;&lt;span style=&quot;color: var(--secondary-color);&quot;&gt;تعمل 110/220 فولت أوتوماتيكياً&lt;/span&gt;&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin-bottom: 1rem; color: var(--secondary-color); font-family: SemiBoldText; font-size: 24px;&quot;&gt;تعمل مع اي راوتر شبكة&lt;/p&gt;&lt;p style=&quot;margin-bottom: 1rem; color: var(--secondary-color); font-family: SemiBoldText; font-size: 24px;&quot;&gt;مكيف شباك&amp;nbsp;&lt;/p&gt;&lt;p style=&quot;margin-bottom: 1rem; color: var(--secondary-color); font-family: SemiBoldText; font-size: 24px;&quot;&gt;٨٠٠٠ واط&amp;nbsp;&lt;/p&gt;', '', 'فيش كهربائي', '', ''),
(52, 1, 'WIFI socket switch _ Free Hardware', '&lt;ol&gt;&lt;li style=&quot;font-family: SemiBoldText; margin-bottom: 1rem; color: var(--secondary-color); font-size: 24px;&quot;&gt;&lt;span style=&quot;font-size: 24px;&quot;&gt;Dimming ON/OFF automatically&lt;/span&gt;&lt;/li&gt;&lt;li style=&quot;font-family: SemiBoldText; margin-bottom: 1rem; color: var(--secondary-color); font-size: 24px;&quot;&gt;&lt;span style=&quot;font-size: 24px;&quot;&gt;110/220 V automatically&lt;/span&gt;&lt;/li&gt;&lt;li style=&quot;font-family: SemiBoldText; margin-bottom: 1rem; color: var(--secondary-color); font-size: 24px;&quot;&gt;&lt;span style=&quot;font-size: 24px;&quot;&gt;Works with any modern router&lt;/span&gt;&lt;/li&gt;&lt;/ol&gt;', '', 'WIFI socket switch _ Free Hardware', '', ''),
(52, 2, 'مفتاح فيش واي فاي - الجهاز مجانا', '&lt;div&gt;&lt;p style=&quot;margin-bottom: 1rem; color: var(--secondary-color); font-family: SemiBoldText; font-size: 24px;&quot;&gt;شغيل اليدوي / التطبيق / التلقائي لكل خط إنارة&lt;/p&gt;&lt;p style=&quot;margin-bottom: 1rem; color: var(--secondary-color); font-family: SemiBoldText; font-size: 24px;&quot;&gt;&lt;span style=&quot;color: var(--secondary-color);&quot;&gt;تعمل 110/220 فولت أوتوماتيكياً&lt;/span&gt;&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin-bottom: 1rem; color: var(--secondary-color); font-family: SemiBoldText; font-size: 24px;&quot;&gt;تعمل مع اي راوتر شبكة&lt;/p&gt;&lt;p style=&quot;margin-bottom: 1rem; color: var(--secondary-color); font-family: SemiBoldText; font-size: 24px;&quot;&gt;١٦ أمبير&amp;nbsp;&lt;/p&gt;&lt;/div&gt;', '', 'مفتاح إنارة خطين', '', ''),
(51, 1, 'WIFI One gang lighting switch_ Free Hardware', '&lt;ol&gt;&lt;li style=&quot;font-family: SemiBoldText; margin-bottom: 1rem; color: var(--secondary-color); font-size: 24px;&quot;&gt;&lt;span style=&quot;font-size: 24px;&quot;&gt;Dimming ON/OFF automatically&lt;/span&gt;&lt;/li&gt;&lt;li style=&quot;font-family: SemiBoldText; margin-bottom: 1rem; color: var(--secondary-color); font-size: 24px;&quot;&gt;&lt;span style=&quot;font-size: 24px;&quot;&gt;110/220 V automatically&lt;/span&gt;&lt;/li&gt;&lt;li style=&quot;font-family: SemiBoldText; margin-bottom: 1rem; color: var(--secondary-color); font-size: 24px;&quot;&gt;&lt;span style=&quot;font-size: 24px;&quot;&gt;Works with any modern router&lt;/span&gt;&lt;/li&gt;&lt;/ol&gt;', '', 'WIFI One gang lighting switch_ Free Hardware', '', ''),
(51, 2, 'مفتاح إنارة خط واحد واي فاي - الجهاز مجانا', '&lt;p style=&quot;margin-bottom: 1rem; color: var(--secondary-color); font-family: SemiBoldText; font-size: 24px;&quot;&gt;تشغيل اليدوي / التطبيق / التلقائي لكل خط إنارة&lt;/p&gt;&lt;p style=&quot;margin-bottom: 1rem; color: var(--secondary-color); font-family: SemiBoldText; font-size: 24px;&quot;&gt;&lt;span style=&quot;color: var(--secondary-color);&quot;&gt;تعمل 110/220 فولت أوتوماتيكياً&lt;/span&gt;&lt;br&gt;&lt;/p&gt;&lt;p style=&quot;margin-bottom: 1rem; color: var(--secondary-color); font-family: SemiBoldText; font-size: 24px;&quot;&gt;تعمل مع اي راوتر شبكة&lt;/p&gt;&lt;p style=&quot;margin-bottom: 1rem; color: var(--secondary-color); font-family: SemiBoldText; font-size: 24px;&quot;&gt;١١٠٠ واط للخط الواحد&amp;nbsp;&lt;/p&gt;&lt;div&gt;&lt;br&gt;&lt;/div&gt;', '', 'مفتاح إنارة خط واحد', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_product_discount`
--

CREATE TABLE `ocn8_product_discount` (
  `product_discount_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `customer_group_id` int(11) NOT NULL,
  `quantity` int(4) NOT NULL DEFAULT 0,
  `priority` int(5) NOT NULL DEFAULT 1,
  `price` decimal(15,4) NOT NULL DEFAULT 0.0000,
  `date_start` date NOT NULL DEFAULT '0000-00-00',
  `date_end` date NOT NULL DEFAULT '0000-00-00'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_product_discount`
--

INSERT INTO `ocn8_product_discount` (`product_discount_id`, `product_id`, `customer_group_id`, `quantity`, `priority`, `price`, `date_start`, `date_end`) VALUES
(537, 52, 1, 0, 0, '0.0000', '0000-00-00', '0000-00-00'),
(536, 51, 1, 0, 0, '0.0000', '0000-00-00', '0000-00-00'),
(540, 50, 1, 0, 0, '0.0000', '0000-00-00', '0000-00-00'),
(531, 53, 1, 0, 0, '0.0000', '0000-00-00', '0000-00-00'),
(538, 54, 1, 0, 0, '0.0000', '0000-00-00', '0000-00-00');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_product_filter`
--

CREATE TABLE `ocn8_product_filter` (
  `product_id` int(11) NOT NULL,
  `filter_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_product_image`
--

CREATE TABLE `ocn8_product_image` (
  `product_image_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `sort_order` int(3) NOT NULL DEFAULT 0
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_product_option`
--

CREATE TABLE `ocn8_product_option` (
  `product_option_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `option_id` int(11) NOT NULL,
  `value` text NOT NULL,
  `required` tinyint(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_product_option`
--

INSERT INTO `ocn8_product_option` (`product_option_id`, `product_id`, `option_id`, `value`, `required`) VALUES
(228, 51, 13, '', 1),
(229, 52, 13, '', 1),
(230, 53, 13, '', 1),
(231, 54, 13, '', 1),
(232, 50, 13, '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_product_option_value`
--

CREATE TABLE `ocn8_product_option_value` (
  `product_option_value_id` int(11) NOT NULL,
  `product_option_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `option_id` int(11) NOT NULL,
  `option_value_id` int(11) NOT NULL,
  `quantity` int(3) NOT NULL,
  `subtract` tinyint(1) NOT NULL,
  `price` decimal(15,4) NOT NULL,
  `price_prefix` varchar(1) NOT NULL,
  `points` int(8) NOT NULL,
  `points_prefix` varchar(1) NOT NULL,
  `weight` decimal(15,8) NOT NULL,
  `weight_prefix` varchar(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_product_option_value`
--

INSERT INTO `ocn8_product_option_value` (`product_option_value_id`, `product_option_id`, `product_id`, `option_id`, `option_value_id`, `quantity`, `subtract`, `price`, `price_prefix`, `points`, `points_prefix`, `weight`, `weight_prefix`) VALUES
(75, 232, 50, 13, 58, 0, 0, '432.0000', '+', 0, '+', '0.00000000', '+'),
(74, 232, 50, 13, 57, 0, 0, '108.0000', '+', 0, '+', '0.00000000', '+'),
(72, 232, 50, 13, 55, 0, 0, '312.0000', '+', 0, '+', '0.00000000', '+'),
(71, 232, 50, 13, 54, 0, 0, '78.0000', '+', 0, '+', '0.00000000', '+'),
(69, 232, 50, 13, 52, 0, 0, '180.0000', '+', 0, '+', '0.00000000', '+'),
(68, 232, 50, 13, 51, 0, 0, '45.0000', '+', 0, '+', '0.00000000', '+'),
(29, 228, 51, 13, 51, 0, 0, '45.0000', '+', 0, '+', '0.00000000', '+'),
(30, 228, 51, 13, 52, 0, 0, '180.0000', '+', 0, '+', '0.00000000', '+'),
(32, 228, 51, 13, 54, 0, 0, '78.0000', '+', 0, '+', '0.00000000', '+'),
(33, 228, 51, 13, 55, 0, 0, '312.0000', '+', 0, '+', '0.00000000', '+'),
(35, 228, 51, 13, 57, 0, 0, '108.0000', '+', 0, '+', '0.00000000', '+'),
(36, 228, 51, 13, 58, 0, 0, '432.0000', '+', 0, '+', '0.00000000', '+'),
(46, 229, 52, 13, 58, 0, 0, '432.0000', '+', 0, '+', '0.00000000', '+'),
(45, 229, 52, 13, 57, 0, 0, '108.0000', '+', 0, '+', '0.00000000', '+'),
(43, 229, 52, 13, 55, 0, 0, '312.0000', '+', 0, '+', '0.00000000', '+'),
(42, 229, 52, 13, 54, 0, 0, '78.0000', '+', 0, '+', '0.00000000', '+'),
(40, 229, 52, 13, 52, 0, 0, '180.0000', '+', 0, '+', '0.00000000', '+'),
(39, 229, 52, 13, 51, 0, 0, '45.0000', '+', 0, '+', '0.00000000', '+'),
(56, 230, 53, 13, 58, 0, 0, '432.0000', '+', 0, '+', '0.00000000', '+'),
(55, 230, 53, 13, 57, 0, 0, '108.0000', '+', 0, '+', '0.00000000', '+'),
(53, 230, 53, 13, 55, 0, 0, '312.0000', '+', 0, '+', '0.00000000', '+'),
(52, 230, 53, 13, 54, 0, 0, '78.0000', '+', 0, '+', '0.00000000', '+'),
(50, 230, 53, 13, 52, 0, 0, '180.0000', '+', 0, '+', '0.00000000', '+'),
(49, 230, 53, 13, 51, 0, 0, '45.0000', '+', 0, '+', '0.00000000', '+'),
(66, 231, 54, 13, 58, 100, 0, '432.0000', '+', 0, '+', '0.00000000', '+'),
(65, 231, 54, 13, 57, 100, 0, '108.0000', '+', 0, '+', '0.00000000', '+'),
(63, 231, 54, 13, 55, 100, 0, '312.0000', '+', 0, '+', '0.00000000', '+'),
(62, 231, 54, 13, 54, 60, 0, '78.0000', '+', 0, '+', '0.00000000', '+'),
(60, 231, 54, 13, 52, 0, 0, '180.0000', '+', 0, '+', '0.00000000', '+'),
(59, 231, 54, 13, 51, 0, 0, '45.0000', '+', 0, '+', '0.00000000', '+');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_product_recurring`
--

CREATE TABLE `ocn8_product_recurring` (
  `product_id` int(11) NOT NULL,
  `recurring_id` int(11) NOT NULL,
  `customer_group_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_product_related`
--

CREATE TABLE `ocn8_product_related` (
  `product_id` int(11) NOT NULL,
  `related_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_product_reward`
--

CREATE TABLE `ocn8_product_reward` (
  `product_reward_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL DEFAULT 0,
  `customer_group_id` int(11) NOT NULL DEFAULT 0,
  `points` int(8) NOT NULL DEFAULT 0
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_product_special`
--

CREATE TABLE `ocn8_product_special` (
  `product_special_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `customer_group_id` int(11) NOT NULL,
  `priority` int(5) NOT NULL DEFAULT 1,
  `price` decimal(15,4) NOT NULL DEFAULT 0.0000,
  `date_start` date NOT NULL DEFAULT '0000-00-00',
  `date_end` date NOT NULL DEFAULT '0000-00-00'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_product_to_category`
--

CREATE TABLE `ocn8_product_to_category` (
  `product_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_product_to_category`
--

INSERT INTO `ocn8_product_to_category` (`product_id`, `category_id`) VALUES
(50, 59),
(51, 59),
(52, 59),
(53, 59),
(54, 59);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_product_to_download`
--

CREATE TABLE `ocn8_product_to_download` (
  `product_id` int(11) NOT NULL,
  `download_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_product_to_layout`
--

CREATE TABLE `ocn8_product_to_layout` (
  `product_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  `layout_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_product_to_layout`
--

INSERT INTO `ocn8_product_to_layout` (`product_id`, `store_id`, `layout_id`) VALUES
(50, 0, 0),
(51, 0, 0),
(52, 0, 0),
(53, 0, 0),
(54, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_product_to_store`
--

CREATE TABLE `ocn8_product_to_store` (
  `product_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL DEFAULT 0
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_product_to_store`
--

INSERT INTO `ocn8_product_to_store` (`product_id`, `store_id`) VALUES
(50, 0),
(51, 0),
(52, 0),
(53, 0),
(54, 0);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_recurring`
--

CREATE TABLE `ocn8_recurring` (
  `recurring_id` int(11) NOT NULL,
  `price` decimal(10,4) NOT NULL,
  `frequency` enum('day','week','semi_month','month','year') NOT NULL,
  `duration` int(10) UNSIGNED NOT NULL,
  `cycle` int(10) UNSIGNED NOT NULL,
  `trial_status` tinyint(4) NOT NULL,
  `trial_price` decimal(10,4) NOT NULL,
  `trial_frequency` enum('day','week','semi_month','month','year') NOT NULL,
  `trial_duration` int(10) UNSIGNED NOT NULL,
  `trial_cycle` int(10) UNSIGNED NOT NULL,
  `status` tinyint(4) NOT NULL,
  `sort_order` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_recurring_description`
--

CREATE TABLE `ocn8_recurring_description` (
  `recurring_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_return`
--

CREATE TABLE `ocn8_return` (
  `return_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `firstname` varchar(32) NOT NULL,
  `lastname` varchar(32) NOT NULL,
  `email` varchar(96) NOT NULL,
  `telephone` varchar(32) NOT NULL,
  `product` varchar(255) NOT NULL,
  `model` varchar(64) NOT NULL,
  `quantity` int(4) NOT NULL,
  `opened` tinyint(1) NOT NULL,
  `return_reason_id` int(11) NOT NULL,
  `return_action_id` int(11) NOT NULL,
  `return_status_id` int(11) NOT NULL,
  `comment` text DEFAULT NULL,
  `date_ordered` date NOT NULL DEFAULT '0000-00-00',
  `date_added` datetime NOT NULL,
  `date_modified` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_return_action`
--

CREATE TABLE `ocn8_return_action` (
  `return_action_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL DEFAULT 0,
  `name` varchar(64) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_return_action`
--

INSERT INTO `ocn8_return_action` (`return_action_id`, `language_id`, `name`) VALUES
(1, 1, 'Refunded'),
(2, 1, 'Credit Issued'),
(3, 1, 'Replacement Sent'),
(1, 2, 'Refunded'),
(2, 2, 'Credit Issued'),
(3, 2, 'Replacement Sent');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_return_history`
--

CREATE TABLE `ocn8_return_history` (
  `return_history_id` int(11) NOT NULL,
  `return_id` int(11) NOT NULL,
  `return_status_id` int(11) NOT NULL,
  `notify` tinyint(1) NOT NULL,
  `comment` text NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_return_reason`
--

CREATE TABLE `ocn8_return_reason` (
  `return_reason_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL DEFAULT 0,
  `name` varchar(128) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_return_reason`
--

INSERT INTO `ocn8_return_reason` (`return_reason_id`, `language_id`, `name`) VALUES
(1, 1, 'Dead On Arrival'),
(2, 1, 'Received Wrong Item'),
(3, 1, 'Order Error'),
(4, 1, 'Faulty, please supply details'),
(5, 1, 'Other, please supply details'),
(1, 2, 'Dead On Arrival'),
(2, 2, 'Received Wrong Item'),
(3, 2, 'Order Error'),
(4, 2, 'Faulty, please supply details'),
(5, 2, 'Other, please supply details');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_return_status`
--

CREATE TABLE `ocn8_return_status` (
  `return_status_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL DEFAULT 0,
  `name` varchar(32) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_return_status`
--

INSERT INTO `ocn8_return_status` (`return_status_id`, `language_id`, `name`) VALUES
(1, 1, 'Pending'),
(3, 1, 'Complete'),
(2, 1, 'Awaiting Products'),
(1, 2, 'Pending'),
(3, 2, 'Complete'),
(2, 2, 'Awaiting Products');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_review`
--

CREATE TABLE `ocn8_review` (
  `review_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `author` varchar(64) NOT NULL,
  `text` text NOT NULL,
  `rating` int(1) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 0,
  `date_added` datetime NOT NULL,
  `date_modified` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_review`
--

INSERT INTO `ocn8_review` (`review_id`, `product_id`, `customer_id`, `author`, `text`, `rating`, `status`, `date_added`, `date_modified`) VALUES
(1, 53, 43, 'bushra khalid', 'goodfrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr', 5, 0, '2022-07-06 12:51:32', '2022-07-06 12:54:51');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_seo_url`
--

CREATE TABLE `ocn8_seo_url` (
  `seo_url_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `query` varchar(255) NOT NULL,
  `keyword` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_seo_url`
--

INSERT INTO `ocn8_seo_url` (`seo_url_id`, `store_id`, `language_id`, `query`, `keyword`) VALUES
(852, 0, 1, 'information_id=4', 'about_us'),
(905, 0, 2, 'product_id=50', 'المنزل-الذكي'),
(904, 0, 1, 'product_id=50', 'smart-home'),
(860, 0, 1, 'manufacturer_id=10', 'sony'),
(861, 0, 1, 'information_id=6', 'delivery'),
(909, 0, 1, 'information_id=3', 'privacy'),
(855, 0, 1, 'information_id=5', 'terms');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_session`
--

CREATE TABLE `ocn8_session` (
  `session_id` varchar(32) NOT NULL,
  `data` text NOT NULL,
  `expire` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_session`
--

INSERT INTO `ocn8_session` (`session_id`, `data`, `expire`) VALUES
('1f5302c2ccc5e80cf2ec570832', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 06:28:19'),
('4f5f0e321d6bcc4c15c34689e9', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 03:50:09'),
('5030d2adeebbe32e6fe3feb25e', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 03:26:40'),
('5a44b240a0940b4d8244dc766f', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 03:24:35'),
('5f44ea937f129cc897fd2c333e', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 02:35:43'),
('64ae9eb5e8a79e5d8829d848aa', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 07:19:53'),
('64c0ae5da84c274ab1b37773c0', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 06:56:08'),
('67eef7998428230e6eeef4dc9a', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 03:23:33'),
('6b23d2786669c8560edeb4b641', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 07:20:07'),
('728c92e2a76447df45c5bd6617', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 03:56:56'),
('7f5b9f0cbe7a45c8ff9ed2ad70', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 03:35:18'),
('7fd53e1e9b269a5c023915c3a7', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 04:10:02'),
('862ab63662aa4ea4931f449622', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 03:40:52'),
('8d4752e65822edc6f3cb6d0cab', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 03:13:10'),
('933432e14eccf45144eb645300', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 04:22:52'),
('9c792f58f21670d00cdc709121', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 07:07:37'),
('badec0589358ce14f54590ece4', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 06:02:08'),
('bdd9c34b19109750b630bf1a3f', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 04:10:08'),
('c25ae92ee1e7f7eefac7d1ec12', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 03:23:04'),
('c3bfeabfa05b199f31df87aad2', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 03:23:30'),
('c77bb3015fb49b512da1e5851a', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 03:39:33'),
('c8d2771ea97cd135fc10c81c07', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 06:56:10'),
('d912c5b020a486ae900a30dc19', '{\"language\":\"ar\",\"currency\":\"SAR\"}', '2022-07-17 06:54:29');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_setting`
--

CREATE TABLE `ocn8_setting` (
  `setting_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL DEFAULT 0,
  `code` varchar(128) NOT NULL,
  `key` varchar(128) NOT NULL,
  `value` text NOT NULL,
  `serialized` tinyint(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_setting`
--

INSERT INTO `ocn8_setting` (`setting_id`, `store_id`, `code`, `key`, `value`, `serialized`) VALUES
(3889, 0, 'config', 'config_error_filename', 'error.log', 0),
(3886, 0, 'config', 'config_file_mime_allowed', 'text/plain\r\nimage/png\r\nimage/jpeg\r\nimage/gif\r\nimage/bmp\r\nimage/tiff\r\nimage/svg+xml\r\napplication/zip\r\n&quot;application/zip&quot;\r\napplication/x-zip\r\n&quot;application/x-zip&quot;\r\napplication/x-zip-compressed\r\n&quot;application/x-zip-compressed&quot;\r\napplication/rar\r\n&quot;application/rar&quot;\r\napplication/x-rar\r\n&quot;application/x-rar&quot;\r\napplication/x-rar-compressed\r\n&quot;application/x-rar-compressed&quot;\r\napplication/octet-stream\r\n&quot;application/octet-stream&quot;\r\naudio/mpeg\r\nvideo/quicktime\r\napplication/pdf', 0),
(3887, 0, 'config', 'config_error_display', '0', 0),
(3888, 0, 'config', 'config_error_log', '1', 0),
(3879, 0, 'config', 'config_compression', '0', 0),
(3880, 0, 'config', 'config_secure', '1', 0),
(3877, 0, 'config', 'config_seo_url', '0', 0),
(3878, 0, 'config', 'config_robots', 'abot\r\ndbot\r\nebot\r\nhbot\r\nkbot\r\nlbot\r\nmbot\r\nnbot\r\nobot\r\npbot\r\nrbot\r\nsbot\r\ntbot\r\nvbot\r\nybot\r\nzbot\r\nbot.\r\nbot/\r\n_bot\r\n.bot\r\n/bot\r\n-bot\r\n:bot\r\n(bot\r\ncrawl\r\nslurp\r\nspider\r\nseek\r\naccoona\r\nacoon\r\nadressendeutschland\r\nah-ha.com\r\nahoy\r\naltavista\r\nananzi\r\nanthill\r\nappie\r\narachnophilia\r\narale\r\naraneo\r\naranha\r\narchitext\r\naretha\r\narks\r\nasterias\r\natlocal\r\natn\r\natomz\r\naugurfind\r\nbackrub\r\nbannana_bot\r\nbaypup\r\nbdfetch\r\nbig brother\r\nbiglotron\r\nbjaaland\r\nblackwidow\r\nblaiz\r\nblog\r\nblo.\r\nbloodhound\r\nboitho\r\nbooch\r\nbradley\r\nbutterfly\r\ncalif\r\ncassandra\r\nccubee\r\ncfetch\r\ncharlotte\r\nchurl\r\ncienciaficcion\r\ncmc\r\ncollective\r\ncomagent\r\ncombine\r\ncomputingsite\r\ncsci\r\ncurl\r\ncusco\r\ndaumoa\r\ndeepindex\r\ndelorie\r\ndepspid\r\ndeweb\r\ndie blinde kuh\r\ndigger\r\nditto\r\ndmoz\r\ndocomo\r\ndownload express\r\ndtaagent\r\ndwcp\r\nebiness\r\nebingbong\r\ne-collector\r\nejupiter\r\nemacs-w3 search engine\r\nesther\r\nevliya celebi\r\nezresult\r\nfalcon\r\nfelix ide\r\nferret\r\nfetchrover\r\nfido\r\nfindlinks\r\nfireball\r\nfish search\r\nfouineur\r\nfunnelweb\r\ngazz\r\ngcreep\r\ngenieknows\r\ngetterroboplus\r\ngeturl\r\nglx\r\ngoforit\r\ngolem\r\ngrabber\r\ngrapnel\r\ngralon\r\ngriffon\r\ngromit\r\ngrub\r\ngulliver\r\nhamahakki\r\nharvest\r\nhavindex\r\nhelix\r\nheritrix\r\nhku www octopus\r\nhomerweb\r\nhtdig\r\nhtml index\r\nhtml_analyzer\r\nhtmlgobble\r\nhubater\r\nhyper-decontextualizer\r\nia_archiver\r\nibm_planetwide\r\nichiro\r\niconsurf\r\niltrovatore\r\nimage.kapsi.net\r\nimagelock\r\nincywincy\r\nindexer\r\ninfobee\r\ninformant\r\ningrid\r\ninktomisearch.com\r\ninspector web\r\nintelliagent\r\ninternet shinchakubin\r\nip3000\r\niron33\r\nisraeli-search\r\nivia\r\njack\r\njakarta\r\njavabee\r\njetbot\r\njumpstation\r\nkatipo\r\nkdd-explorer\r\nkilroy\r\nknowledge\r\nkototoi\r\nkretrieve\r\nlabelgrabber\r\nlachesis\r\nlarbin\r\nlegs\r\nlibwww\r\nlinkalarm\r\nlink validator\r\nlinkscan\r\nlockon\r\nlwp\r\nlycos\r\nmagpie\r\nmantraagent\r\nmapoftheinternet\r\nmarvin/\r\nmattie\r\nmediafox\r\nmediapartners\r\nmercator\r\nmerzscope\r\nmicrosoft url control\r\nminirank\r\nmiva\r\nmj12\r\nmnogosearch\r\nmoget\r\nmonster\r\nmoose\r\nmotor\r\nmultitext\r\nmuncher\r\nmuscatferret\r\nmwd.search\r\nmyweb\r\nnajdi\r\nnameprotect\r\nnationaldirectory\r\nnazilla\r\nncsa beta\r\nnec-meshexplorer\r\nnederland.zoek\r\nnetcarta webmap engine\r\nnetmechanic\r\nnetresearchserver\r\nnetscoop\r\nnewscan-online\r\nnhse\r\nnokia6682/\r\nnomad\r\nnoyona\r\nnutch\r\nnzexplorer\r\nobjectssearch\r\noccam\r\nomni\r\nopen text\r\nopenfind\r\nopenintelligencedata\r\norb search\r\nosis-project\r\npack rat\r\npageboy\r\npagebull\r\npage_verifier\r\npanscient\r\nparasite\r\npartnersite\r\npatric\r\npear.\r\npegasus\r\nperegrinator\r\npgp key agent\r\nphantom\r\nphpdig\r\npicosearch\r\npiltdownman\r\npimptrain\r\npinpoint\r\npioneer\r\npiranha\r\nplumtreewebaccessor\r\npogodak\r\npoirot\r\npompos\r\npoppelsdorf\r\npoppi\r\npopular iconoclast\r\npsycheclone\r\npublisher\r\npython\r\nrambler\r\nraven search\r\nroach\r\nroad runner\r\nroadhouse\r\nrobbie\r\nrobofox\r\nrobozilla\r\nrules\r\nsalty\r\nsbider\r\nscooter\r\nscoutjet\r\nscrubby\r\nsearch.\r\nsearchprocess\r\nsemanticdiscovery\r\nsenrigan\r\nsg-scout\r\nshai\'hulud\r\nshark\r\nshopwiki\r\nsidewinder\r\nsift\r\nsilk\r\nsimmany\r\nsite searcher\r\nsite valet\r\nsitetech-rover\r\nskymob.com\r\nsleek\r\nsmartwit\r\nsna-\r\nsnappy\r\nsnooper\r\nsohu\r\nspeedfind\r\nsphere\r\nsphider\r\nspinner\r\nspyder\r\nsteeler/\r\nsuke\r\nsuntek\r\nsupersnooper\r\nsurfnomore\r\nsven\r\nsygol\r\nszukacz\r\ntach black widow\r\ntarantula\r\ntempleton\r\n/teoma\r\nt-h-u-n-d-e-r-s-t-o-n-e\r\ntheophrastus\r\ntitan\r\ntitin\r\ntkwww\r\ntoutatis\r\nt-rex\r\ntutorgig\r\ntwiceler\r\ntwisted\r\nucsd\r\nudmsearch\r\nurl check\r\nupdated\r\nvagabondo\r\nvalkyrie\r\nverticrawl\r\nvictoria\r\nvision-search\r\nvolcano\r\nvoyager/\r\nvoyager-hc\r\nw3c_validator\r\nw3m2\r\nw3mir\r\nwalker\r\nwallpaper\r\nwanderer\r\nwauuu\r\nwavefire\r\nweb core\r\nweb hopper\r\nweb wombat\r\nwebbandit\r\nwebcatcher\r\nwebcopy\r\nwebfoot\r\nweblayers\r\nweblinker\r\nweblog monitor\r\nwebmirror\r\nwebmonkey\r\nwebquest\r\nwebreaper\r\nwebsitepulse\r\nwebsnarf\r\nwebstolperer\r\nwebvac\r\nwebwalk\r\nwebwatch\r\nwebwombat\r\nwebzinger\r\nwhizbang\r\nwhowhere\r\nwild ferret\r\nworldlight\r\nwwwc\r\nwwwster\r\nxenu\r\nxget\r\nxift\r\nxirq\r\nyandex\r\nyanga\r\nyeti\r\nyodao\r\nzao\r\nzippp\r\nzyborg', 0),
(3885, 0, 'config', 'config_file_ext_allowed', 'zip\r\ntxt\r\npng\r\njpe\r\njpeg\r\njpg\r\ngif\r\nbmp\r\nico\r\ntiff\r\ntif\r\nsvg\r\nsvgz\r\nzip\r\nrar\r\nmsi\r\ncab\r\nmp3\r\nqt\r\nmov\r\npdf\r\npsd\r\nai\r\neps\r\nps\r\ndoc', 0),
(3884, 0, 'config', 'config_file_max_size', '300000', 0),
(3881, 0, 'config', 'config_password', '1', 0),
(3882, 0, 'config', 'config_shared', '0', 0),
(3883, 0, 'config', 'config_encryption', 'GrpGxXBcH1Qs9fiQZHIyUiBJoQYOO5FOLjSb0KuFYW279liCuCBpRjgx4ejqrX1SYu2vz8pAFpj6PfrAhd8jQgPGAkEhcgQDk9db8OwjSYgbecumrLcGmgLmaOHD5StySSYY5SVIzfEyrrXXQ1HpIUHAAn3ADZIHGWiLD8zsObMoMGMsYuDf5az7Tc67U9EE2An4UmoyHMtHiqpeNYLNfXAzj5UIJlgk4y1N6oqQ4fGEhFaZ8nHl6qThDZ9eTg6LYZvIABFYZPBG11qep97cu2d3llkEODiJag0wDjvwN30NDOyxNKBm7SDf2y4atGeeEPFfhF32Ws2M5ZGep9lskIKnZl9IRo2sXHLwPOhg4tA7QkYyfdvk1pWmqP3Y4WKhm38czJDKHCZ7MqaMlBbjW5SocxWbviMWvCSHOZOYyMCpr5uYNj2Pl9miG38rwjn2z7MAxIQStY8EGlrBl2gMFzwh3DNvcpKCNfk9pWSpjVXQNFC1P3tYBjnhxtiGPHIw3FVtnBH5sBE6oV2fx4HNHgllH93e4VNREKgFIyko4aeRelmXnucLRBaiIPW0i63vKKbTTl1KAvFDDux3bVxSBZXkGayNcH37MuFkOwzQB25bf09h00BkhRqkVUwyxRr6RbAcEXRokFl8eOouN7ZGfMEmkUyUtozwCyigoHP3iClPCnJ8DTWHjA4vubzwupdgTQMINF4x5X8hdeolZLIkZjSC2FVSWR3cv7Aw0duOsPtxnvksfm39Z6FvCXvMfA8r5kBCIrrjlIIQJtto1Z32Ss5VzwK4OhWyWcgQSDUmIamXMJDIUpEtxPLMPrfa911vXpLtzWC5lz9QTNKprMRX2lVOP9lReodfL1HHcWpH3qCExhwhV3Z7mAzdTbU9sbOaFPkWThfOi1vmeOjn3uSMAIUenfuaKPaQcHnoAjSRYdQs4liRxuD3vxQmPIAyLeloZ2d8MW6GzfIJqlMC6vxvm6uUoqSGBsMSEIqZEvS8hY1u1UfhDQTMUfGkd1uD51AZ', 0),
(3876, 0, 'config', 'config_maintenance', '0', 0),
(3875, 0, 'config', 'config_mail_alert_email', '', 0),
(3873, 0, 'config', 'config_mail_smtp_timeout', '300', 0),
(3874, 0, 'config', 'config_mail_alert', '[\"account\",\"order\"]', 1),
(3872, 0, 'config', 'config_mail_smtp_port', '465', 0),
(3871, 0, 'config', 'config_mail_smtp_password', 'l!vX@1!2@', 0),
(3869, 0, 'config', 'config_mail_smtp_hostname', 'ssl://smtp.gmail.com', 0),
(3870, 0, 'config', 'config_mail_smtp_username', 'sales@livixa.com', 0),
(3868, 0, 'config', 'config_mail_parameter', '-fsales@livixa.com', 0),
(3867, 0, 'config', 'config_mail_engine', 'smtp', 0),
(3866, 0, 'config', 'config_icon', 'catalog/cart.png', 0),
(3865, 0, 'config', 'config_logo', 'catalog/livixa logo.jpg', 0),
(3864, 0, 'config', 'config_captcha_page', '[\"review\",\"return\",\"contact\"]', 1),
(3863, 0, 'config', 'config_captcha', '', 0),
(3862, 0, 'config', 'config_return_status_id', '2', 0),
(3861, 0, 'config', 'config_return_id', '0', 0),
(3860, 0, 'config', 'config_affiliate_id', '4', 0),
(3859, 0, 'config', 'config_affiliate_commission', '5', 0),
(3858, 0, 'config', 'config_affiliate_auto', '0', 0),
(3857, 0, 'config', 'config_affiliate_approval', '0', 0),
(3856, 0, 'config', 'config_affiliate_group_id', '1', 0),
(3855, 0, 'config', 'config_stock_checkout', '0', 0),
(3854, 0, 'config', 'config_stock_warning', '0', 0),
(3853, 0, 'config', 'config_stock_display', '0', 0),
(3852, 0, 'config', 'config_api_id', '1', 0),
(3851, 0, 'config', 'config_fraud_status_id', '7', 0),
(3849, 0, 'config', 'config_processing_status', '[\"5\",\"1\",\"2\",\"12\",\"3\"]', 1),
(3850, 0, 'config', 'config_complete_status', '[\"5\",\"3\"]', 1),
(3848, 0, 'config', 'config_order_status_id', '1', 0),
(3847, 0, 'config', 'config_checkout_id', '5', 0),
(3846, 0, 'config', 'config_checkout_guest', '0', 0),
(3845, 0, 'config', 'config_cart_weight', '1', 0),
(3844, 0, 'config', 'config_invoice_prefix', 'INV-2021-00', 0),
(3843, 0, 'config', 'config_account_id', '3', 0),
(3842, 0, 'config', 'config_login_attempts', '50', 0),
(3841, 0, 'config', 'config_customer_price', '0', 0),
(3840, 0, 'config', 'config_customer_group_display', '[\"1\"]', 1),
(3839, 0, 'config', 'config_customer_group_id', '1', 0),
(3838, 0, 'config', 'config_customer_search', '0', 0),
(3837, 0, 'config', 'config_customer_activity', '0', 0),
(3836, 0, 'config', 'config_customer_online', '0', 0),
(3835, 0, 'config', 'config_tax_customer', 'shipping', 0),
(94, 0, 'payment_free_checkout', 'payment_free_checkout_status', '1', 0),
(95, 0, 'payment_free_checkout', 'payment_free_checkout_order_status_id', '1', 0),
(96, 0, 'payment_free_checkout', 'payment_free_checkout_sort_order', '1', 0),
(2810, 0, 'payment_cod', 'payment_cod_sort_order', '5', 0),
(3454, 0, 'payment_moyasar3', 'payment_moyasar3_sort_order', '1', 0),
(2808, 0, 'payment_cod', 'payment_cod_geo_zone_id', '0', 0),
(2809, 0, 'payment_cod', 'payment_cod_status', '0', 0),
(571, 0, 'shipping_flat', 'shipping_flat_sort_order', '1', 0),
(570, 0, 'shipping_flat', 'shipping_flat_status', '1', 0),
(569, 0, 'shipping_flat', 'shipping_flat_geo_zone_id', '0', 0),
(568, 0, 'shipping_flat', 'shipping_flat_tax_class_id', '9', 0),
(567, 0, 'shipping_flat', 'shipping_flat_cost', '0.00', 0),
(107, 0, 'total_shipping', 'total_shipping_sort_order', '3', 0),
(108, 0, 'total_sub_total', 'total_sub_total_sort_order', '1', 0),
(109, 0, 'total_sub_total', 'total_sub_total_status', '1', 0),
(110, 0, 'total_tax', 'total_tax_status', '1', 0),
(111, 0, 'total_total', 'total_total_sort_order', '9', 0),
(112, 0, 'total_total', 'total_total_status', '1', 0),
(113, 0, 'total_tax', 'total_tax_sort_order', '5', 0),
(114, 0, 'total_credit', 'total_credit_sort_order', '7', 0),
(115, 0, 'total_credit', 'total_credit_status', '1', 0),
(116, 0, 'total_reward', 'total_reward_sort_order', '2', 0),
(117, 0, 'total_reward', 'total_reward_status', '1', 0),
(118, 0, 'total_shipping', 'total_shipping_status', '1', 0),
(119, 0, 'total_shipping', 'total_shipping_estimator', '1', 0),
(120, 0, 'total_coupon', 'total_coupon_sort_order', '4', 0),
(121, 0, 'total_coupon', 'total_coupon_status', '1', 0),
(122, 0, 'total_voucher', 'total_voucher_sort_order', '8', 0),
(123, 0, 'total_voucher', 'total_voucher_status', '1', 0),
(124, 0, 'module_category', 'module_category_status', '1', 0),
(125, 0, 'module_account', 'module_account_status', '1', 0),
(126, 0, 'theme_default', 'theme_default_product_limit', '15', 0),
(127, 0, 'theme_default', 'theme_default_product_description_length', '100', 0),
(128, 0, 'theme_default', 'theme_default_image_thumb_width', '228', 0),
(129, 0, 'theme_default', 'theme_default_image_thumb_height', '228', 0),
(130, 0, 'theme_default', 'theme_default_image_popup_width', '500', 0),
(131, 0, 'theme_default', 'theme_default_image_popup_height', '500', 0),
(132, 0, 'theme_default', 'theme_default_image_category_width', '80', 0),
(133, 0, 'theme_default', 'theme_default_image_category_height', '80', 0),
(134, 0, 'theme_default', 'theme_default_image_product_width', '228', 0),
(135, 0, 'theme_default', 'theme_default_image_product_height', '228', 0),
(136, 0, 'theme_default', 'theme_default_image_additional_width', '74', 0),
(137, 0, 'theme_default', 'theme_default_image_additional_height', '74', 0),
(138, 0, 'theme_default', 'theme_default_image_related_width', '200', 0),
(139, 0, 'theme_default', 'theme_default_image_related_height', '200', 0),
(140, 0, 'theme_default', 'theme_default_image_compare_width', '90', 0),
(141, 0, 'theme_default', 'theme_default_image_compare_height', '90', 0),
(142, 0, 'theme_default', 'theme_default_image_wishlist_width', '47', 0),
(143, 0, 'theme_default', 'theme_default_image_wishlist_height', '47', 0),
(144, 0, 'theme_default', 'theme_default_image_cart_height', '47', 0),
(145, 0, 'theme_default', 'theme_default_image_cart_width', '47', 0),
(146, 0, 'theme_default', 'theme_default_image_location_height', '50', 0),
(147, 0, 'theme_default', 'theme_default_image_location_width', '268', 0),
(148, 0, 'theme_default', 'theme_default_directory', 'default', 0),
(149, 0, 'theme_default', 'theme_default_status', '1', 0),
(150, 0, 'dashboard_activity', 'dashboard_activity_status', '1', 0),
(151, 0, 'dashboard_activity', 'dashboard_activity_sort_order', '7', 0),
(152, 0, 'dashboard_sale', 'dashboard_sale_status', '1', 0),
(153, 0, 'dashboard_sale', 'dashboard_sale_width', '3', 0),
(154, 0, 'dashboard_chart', 'dashboard_chart_status', '1', 0),
(155, 0, 'dashboard_chart', 'dashboard_chart_width', '6', 0),
(156, 0, 'dashboard_customer', 'dashboard_customer_status', '1', 0),
(157, 0, 'dashboard_customer', 'dashboard_customer_width', '3', 0),
(158, 0, 'dashboard_map', 'dashboard_map_status', '1', 0),
(159, 0, 'dashboard_map', 'dashboard_map_width', '6', 0),
(160, 0, 'dashboard_online', 'dashboard_online_status', '1', 0),
(161, 0, 'dashboard_online', 'dashboard_online_width', '3', 0),
(162, 0, 'dashboard_order', 'dashboard_order_sort_order', '1', 0),
(163, 0, 'dashboard_order', 'dashboard_order_status', '1', 0),
(164, 0, 'dashboard_order', 'dashboard_order_width', '3', 0),
(165, 0, 'dashboard_sale', 'dashboard_sale_sort_order', '2', 0),
(166, 0, 'dashboard_customer', 'dashboard_customer_sort_order', '3', 0),
(167, 0, 'dashboard_online', 'dashboard_online_sort_order', '4', 0),
(168, 0, 'dashboard_map', 'dashboard_map_sort_order', '5', 0),
(169, 0, 'dashboard_chart', 'dashboard_chart_sort_order', '6', 0),
(170, 0, 'dashboard_recent', 'dashboard_recent_status', '1', 0),
(171, 0, 'dashboard_recent', 'dashboard_recent_sort_order', '8', 0),
(172, 0, 'dashboard_activity', 'dashboard_activity_width', '4', 0),
(173, 0, 'dashboard_recent', 'dashboard_recent_width', '8', 0),
(174, 0, 'report_customer_activity', 'report_customer_activity_status', '1', 0),
(175, 0, 'report_customer_activity', 'report_customer_activity_sort_order', '1', 0),
(176, 0, 'report_customer_order', 'report_customer_order_status', '1', 0),
(177, 0, 'report_customer_order', 'report_customer_order_sort_order', '2', 0),
(178, 0, 'report_customer_reward', 'report_customer_reward_status', '1', 0),
(179, 0, 'report_customer_reward', 'report_customer_reward_sort_order', '3', 0),
(180, 0, 'report_customer_search', 'report_customer_search_sort_order', '3', 0),
(181, 0, 'report_customer_search', 'report_customer_search_status', '1', 0),
(182, 0, 'report_customer_transaction', 'report_customer_transaction_status', '1', 0),
(183, 0, 'report_customer_transaction', 'report_customer_transaction_status_sort_order', '4', 0),
(184, 0, 'report_sale_tax', 'report_sale_tax_status', '1', 0),
(185, 0, 'report_sale_tax', 'report_sale_tax_sort_order', '5', 0),
(186, 0, 'report_sale_shipping', 'report_sale_shipping_status', '1', 0),
(187, 0, 'report_sale_shipping', 'report_sale_shipping_sort_order', '6', 0),
(188, 0, 'report_sale_return', 'report_sale_return_status', '1', 0),
(189, 0, 'report_sale_return', 'report_sale_return_sort_order', '7', 0),
(190, 0, 'report_sale_order', 'report_sale_order_status', '1', 0),
(191, 0, 'report_sale_order', 'report_sale_order_sort_order', '8', 0),
(192, 0, 'report_sale_coupon', 'report_sale_coupon_status', '1', 0),
(193, 0, 'report_sale_coupon', 'report_sale_coupon_sort_order', '9', 0),
(194, 0, 'report_product_viewed', 'report_product_viewed_status', '1', 0),
(195, 0, 'report_product_viewed', 'report_product_viewed_sort_order', '10', 0),
(196, 0, 'report_product_purchased', 'report_product_purchased_status', '1', 0),
(197, 0, 'report_product_purchased', 'report_product_purchased_sort_order', '11', 0),
(198, 0, 'report_marketing', 'report_marketing_status', '1', 0),
(199, 0, 'report_marketing', 'report_marketing_sort_order', '12', 0),
(200, 0, 'developer', 'developer_theme', '1', 0),
(201, 0, 'developer', 'developer_sass', '1', 0),
(3834, 0, 'config', 'config_tax_default', 'shipping', 0),
(3833, 0, 'config', 'config_tax', '1', 0),
(3453, 0, 'payment_moyasar3', 'payment_moyasar3_status', '1', 0),
(3452, 0, 'payment_moyasar3', 'payment_moyasar3_failed_order_status_id', '13', 0),
(2807, 0, 'payment_cod', 'payment_cod_order_status_id', '1', 0),
(2806, 0, 'payment_cod', 'payment_cod_total', '0.01', 0),
(3832, 0, 'config', 'config_voucher_max', '1000', 0),
(3831, 0, 'config', 'config_voucher_min', '1', 0),
(3830, 0, 'config', 'config_review_guest', '0', 0),
(3829, 0, 'config', 'config_review_status', '1', 0),
(3828, 0, 'config', 'config_limit_admin', '20', 0),
(3827, 0, 'config', 'config_product_count', '1', 0),
(3826, 0, 'config', 'config_weight_class_id', '1', 0),
(3825, 0, 'config', 'config_length_class_id', '1', 0),
(3824, 0, 'config', 'config_currency_auto', '1', 0),
(3823, 0, 'config', 'config_currency', 'SAR', 0),
(3822, 0, 'config', 'config_admin_language', 'en-gb', 0),
(3821, 0, 'config', 'config_language', 'ar', 0),
(3820, 0, 'config', 'config_timezone', 'UTC', 0),
(3819, 0, 'config', 'config_zone_id', '', 0),
(3818, 0, 'config', 'config_country_id', '184', 0),
(3817, 0, 'config', 'config_comment', '', 0),
(3816, 0, 'config', 'config_open', '', 0),
(3815, 0, 'config', 'config_image', '', 0),
(3448, 0, 'payment_moyasar3', 'payment_moyasar3_api_key', 'pk_test_FGAQaRPaxkwLWQCPTer8tQKbczrWU98CEPfvjYDv', 0),
(3449, 0, 'payment_moyasar3', 'payment_moyasar3_api_secret_key', 'sk_test_VEN7rx1HiPnzkSiDRyMT4p1CzM9iet4J36cEwQE3', 0),
(3451, 0, 'payment_moyasar3', 'payment_moyasar3_order_status_id', '15', 0),
(3450, 0, 'payment_moyasar3', 'payment_moyasar3_payment_type', '{\"cc\":\"1\",\"stcpay\":\"1\",\"applepay\":\"1\"}', 1),
(3814, 0, 'config', 'config_fax', '', 0),
(3813, 0, 'config', 'config_telephone', '0549801399', 0),
(3812, 0, 'config', 'config_email', 'sales@livixa.com', 0),
(3811, 0, 'config', 'config_geocode', '', 0),
(3810, 0, 'config', 'config_address', 'Abi Baker Alsiddique Street, Riyadh, KSA', 0),
(3809, 0, 'config', 'config_owner', 'Livixa', 0),
(3808, 0, 'config', 'config_name', 'Livixa', 0),
(3807, 0, 'config', 'config_layout_id', '4', 0),
(3806, 0, 'config', 'config_theme', 'default', 0),
(3805, 0, 'config', 'config_meta_keyword', '', 0),
(3804, 0, 'config', 'config_meta_description', 'Livixa', 0),
(3803, 0, 'config', 'config_meta_title', 'Livixa', 0);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_shipping_courier`
--

CREATE TABLE `ocn8_shipping_courier` (
  `shipping_courier_id` int(11) NOT NULL,
  `shipping_courier_code` varchar(255) NOT NULL DEFAULT '',
  `shipping_courier_name` varchar(255) NOT NULL DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_shipping_courier`
--

INSERT INTO `ocn8_shipping_courier` (`shipping_courier_id`, `shipping_courier_code`, `shipping_courier_name`) VALUES
(1, 'dhl', 'DHL'),
(2, 'fedex', 'Fedex'),
(3, 'ups', 'UPS'),
(4, 'royal-mail', 'Royal Mail'),
(5, 'usps', 'United States Postal Service'),
(6, 'auspost', 'Australia Post');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_statistics`
--

CREATE TABLE `ocn8_statistics` (
  `statistics_id` int(11) NOT NULL,
  `code` varchar(64) NOT NULL,
  `value` decimal(15,4) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_statistics`
--

INSERT INTO `ocn8_statistics` (`statistics_id`, `code`, `value`) VALUES
(1, 'order_sale', '-14943.0000'),
(2, 'order_processing', '0.0000'),
(3, 'order_complete', '0.0000'),
(4, 'order_other', '0.0000'),
(5, 'returns', '0.0000'),
(6, 'product', '0.0000'),
(7, 'review', '1.0000');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_stock_status`
--

CREATE TABLE `ocn8_stock_status` (
  `stock_status_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_stock_status`
--

INSERT INTO `ocn8_stock_status` (`stock_status_id`, `language_id`, `name`) VALUES
(7, 1, 'In Stock'),
(8, 1, 'Pre-Order'),
(5, 1, 'Out Of Stock'),
(6, 1, '2-3 Days'),
(7, 2, 'In Stock'),
(8, 2, 'Pre-Order'),
(5, 2, 'Out Of Stock'),
(6, 2, '2-3 Days');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_store`
--

CREATE TABLE `ocn8_store` (
  `store_id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `url` varchar(255) NOT NULL,
  `ssl` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_tax_class`
--

CREATE TABLE `ocn8_tax_class` (
  `tax_class_id` int(11) NOT NULL,
  `title` varchar(32) NOT NULL,
  `description` varchar(255) NOT NULL,
  `date_added` datetime NOT NULL,
  `date_modified` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_tax_class`
--

INSERT INTO `ocn8_tax_class` (`tax_class_id`, `title`, `description`, `date_added`, `date_modified`) VALUES
(9, 'Taxable Goods', 'Taxed goods', '2009-01-06 23:21:53', '2011-09-23 14:07:50'),
(10, 'Downloadable Products', 'Downloadable', '2011-09-21 22:19:39', '2011-09-22 10:27:36');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_tax_rate`
--

CREATE TABLE `ocn8_tax_rate` (
  `tax_rate_id` int(11) NOT NULL,
  `geo_zone_id` int(11) NOT NULL DEFAULT 0,
  `name` varchar(32) NOT NULL,
  `rate` decimal(15,4) NOT NULL DEFAULT 0.0000,
  `type` char(1) NOT NULL,
  `date_added` datetime NOT NULL,
  `date_modified` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_tax_rate`
--

INSERT INTO `ocn8_tax_rate` (`tax_rate_id`, `geo_zone_id`, `name`, `rate`, `type`, `date_added`, `date_modified`) VALUES
(86, 3, 'VAT ', '0.0000', 'P', '2011-03-09 21:17:10', '2022-06-15 14:20:36'),
(87, 3, 'Eco Tax (-2.00)', '2.0000', 'F', '2011-09-21 21:49:23', '2011-09-23 00:40:19');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_tax_rate_to_customer_group`
--

CREATE TABLE `ocn8_tax_rate_to_customer_group` (
  `tax_rate_id` int(11) NOT NULL,
  `customer_group_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_tax_rate_to_customer_group`
--

INSERT INTO `ocn8_tax_rate_to_customer_group` (`tax_rate_id`, `customer_group_id`) VALUES
(86, 1),
(87, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_tax_rule`
--

CREATE TABLE `ocn8_tax_rule` (
  `tax_rule_id` int(11) NOT NULL,
  `tax_class_id` int(11) NOT NULL,
  `tax_rate_id` int(11) NOT NULL,
  `based` varchar(10) NOT NULL,
  `priority` int(5) NOT NULL DEFAULT 1
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_tax_rule`
--

INSERT INTO `ocn8_tax_rule` (`tax_rule_id`, `tax_class_id`, `tax_rate_id`, `based`, `priority`) VALUES
(121, 10, 86, 'payment', 1),
(120, 10, 87, 'store', 0),
(128, 9, 86, 'shipping', 1),
(127, 9, 87, 'shipping', 2);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_theme`
--

CREATE TABLE `ocn8_theme` (
  `theme_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  `theme` varchar(64) NOT NULL,
  `route` varchar(64) NOT NULL,
  `code` mediumtext NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_translation`
--

CREATE TABLE `ocn8_translation` (
  `translation_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `route` varchar(64) NOT NULL,
  `key` varchar(64) NOT NULL,
  `value` text NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_upload`
--

CREATE TABLE `ocn8_upload` (
  `upload_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_user`
--

CREATE TABLE `ocn8_user` (
  `user_id` int(11) NOT NULL,
  `user_group_id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(40) NOT NULL,
  `salt` varchar(9) NOT NULL,
  `firstname` varchar(32) NOT NULL,
  `lastname` varchar(32) NOT NULL,
  `email` varchar(96) NOT NULL,
  `image` varchar(255) NOT NULL,
  `code` varchar(40) NOT NULL,
  `ip` varchar(40) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_user`
--

INSERT INTO `ocn8_user` (`user_id`, `user_group_id`, `username`, `password`, `salt`, `firstname`, `lastname`, `email`, `image`, `code`, `ip`, `status`, `date_added`) VALUES
(1, 1, 'tracking', 'a595bb9d2e851010eea8d6de20fedce03ed8e49a', '4bc4033fa', 'System', 'Administrator', 'adm@quaidventures.com', '', '', '103.83.89.155', 1, '2022-06-01 08:53:41'),
(2, 1, 'admin', 'c81c2cc06213dce422ae10d0b919c2bd5dba2daa', 'TogTWPYl3', 'Admin', 'Super', 'admin@livixa.com', '', '', '', 1, '2022-06-01 14:03:08'),
(3, 1, 'saleh', '67e72e3e200949eb0cf2df51629b1b4365578f62', 'GJ1RCMwpD', 'Saleh', 'Alsaiari', 'saleh@livixa.com', '', '', '5.156.91.227', 1, '2022-06-02 12:44:18');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_user_group`
--

CREATE TABLE `ocn8_user_group` (
  `user_group_id` int(11) NOT NULL,
  `name` varchar(64) NOT NULL,
  `permission` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_user_group`
--

INSERT INTO `ocn8_user_group` (`user_group_id`, `name`, `permission`) VALUES
(1, 'Administrator', '{\"access\":[\"catalog\\/attribute\",\"catalog\\/attribute_group\",\"catalog\\/category\",\"catalog\\/download\",\"catalog\\/filter\",\"catalog\\/information\",\"catalog\\/manufacturer\",\"catalog\\/option\",\"catalog\\/product\",\"catalog\\/recurring\",\"catalog\\/review\",\"common\\/column_left\",\"common\\/developer\",\"common\\/filemanager\",\"common\\/profile\",\"common\\/security\",\"customer\\/custom_field\",\"customer\\/customer\",\"customer\\/customer_approval\",\"customer\\/customer_group\",\"design\\/banner\",\"design\\/layout\",\"design\\/theme\",\"design\\/translation\",\"design\\/seo_url\",\"event\\/statistics\",\"event\\/theme\",\"extension\\/advertise\\/google\",\"extension\\/analytics\\/google\",\"extension\\/captcha\\/basic\",\"extension\\/captcha\\/google\",\"extension\\/dashboard\\/activity\",\"extension\\/dashboard\\/chart\",\"extension\\/dashboard\\/customer\",\"extension\\/dashboard\\/map\",\"extension\\/dashboard\\/online\",\"extension\\/dashboard\\/order\",\"extension\\/dashboard\\/recent\",\"extension\\/dashboard\\/sale\",\"extension\\/extension\\/advertise\",\"extension\\/extension\\/analytics\",\"extension\\/extension\\/captcha\",\"extension\\/extension\\/dashboard\",\"extension\\/extension\\/feed\",\"extension\\/extension\\/fraud\",\"extension\\/extension\\/menu\",\"extension\\/extension\\/module\",\"extension\\/extension\\/payment\",\"extension\\/extension\\/report\",\"extension\\/extension\\/shipping\",\"extension\\/extension\\/theme\",\"extension\\/extension\\/total\",\"extension\\/feed\\/google_base\",\"extension\\/feed\\/google_sitemap\",\"extension\\/fraud\\/fraudlabspro\",\"extension\\/fraud\\/ip\",\"extension\\/fraud\\/maxmind\",\"extension\\/marketing\\/remarketing\",\"extension\\/module\\/account\",\"extension\\/module\\/amazon_login\",\"extension\\/module\\/amazon_pay\",\"extension\\/module\\/banner\",\"extension\\/module\\/bestseller\",\"extension\\/module\\/carousel\",\"extension\\/module\\/category\",\"extension\\/module\\/divido_calculator\",\"extension\\/module\\/ebay_listing\",\"extension\\/module\\/featured\",\"extension\\/module\\/filter\",\"extension\\/module\\/google_hangouts\",\"extension\\/module\\/html\",\"extension\\/module\\/information\",\"extension\\/module\\/klarna_checkout_module\",\"extension\\/module\\/latest\",\"extension\\/module\\/laybuy_layout\",\"extension\\/module\\/pilibaba_button\",\"extension\\/module\\/sagepay_direct_cards\",\"extension\\/module\\/sagepay_server_cards\",\"extension\\/module\\/slideshow\",\"extension\\/module\\/special\",\"extension\\/module\\/store\",\"extension\\/payment\\/amazon_login_pay\",\"extension\\/payment\\/authorizenet_aim\",\"extension\\/payment\\/authorizenet_sim\",\"extension\\/payment\\/bank_transfer\",\"extension\\/payment\\/bluepay_hosted\",\"extension\\/payment\\/bluepay_redirect\",\"extension\\/payment\\/cardconnect\",\"extension\\/payment\\/cardinity\",\"extension\\/payment\\/cheque\",\"extension\\/payment\\/cod\",\"extension\\/payment\\/divido\",\"extension\\/payment\\/eway\",\"extension\\/payment\\/firstdata\",\"extension\\/payment\\/firstdata_remote\",\"extension\\/payment\\/free_checkout\",\"extension\\/payment\\/g2apay\",\"extension\\/payment\\/globalpay\",\"extension\\/payment\\/globalpay_remote\",\"extension\\/payment\\/klarna_account\",\"extension\\/payment\\/klarna_checkout\",\"extension\\/payment\\/klarna_invoice\",\"extension\\/payment\\/laybuy\",\"extension\\/payment\\/liqpay\",\"extension\\/payment\\/nochex\",\"extension\\/payment\\/paymate\",\"extension\\/payment\\/paypoint\",\"extension\\/payment\\/payza\",\"extension\\/payment\\/perpetual_payments\",\"extension\\/payment\\/pilibaba\",\"extension\\/payment\\/realex\",\"extension\\/payment\\/realex_remote\",\"extension\\/payment\\/sagepay_direct\",\"extension\\/payment\\/sagepay_server\",\"extension\\/payment\\/sagepay_us\",\"extension\\/payment\\/securetrading_pp\",\"extension\\/payment\\/securetrading_ws\",\"extension\\/payment\\/skrill\",\"extension\\/payment\\/twocheckout\",\"extension\\/payment\\/web_payment_software\",\"extension\\/payment\\/worldpay\",\"extension\\/module\\/pp_braintree_button\",\"extension\\/payment\\/pp_braintree\",\"extension\\/report\\/customer_activity\",\"extension\\/report\\/customer_order\",\"extension\\/report\\/customer_reward\",\"extension\\/report\\/customer_search\",\"extension\\/report\\/customer_transaction\",\"extension\\/report\\/marketing\",\"extension\\/report\\/product_purchased\",\"extension\\/report\\/product_viewed\",\"extension\\/report\\/sale_coupon\",\"extension\\/report\\/sale_order\",\"extension\\/report\\/sale_return\",\"extension\\/report\\/sale_shipping\",\"extension\\/report\\/sale_tax\",\"extension\\/shipping\\/auspost\",\"extension\\/shipping\\/ec_ship\",\"extension\\/shipping\\/fedex\",\"extension\\/shipping\\/flat\",\"extension\\/shipping\\/free\",\"extension\\/shipping\\/item\",\"extension\\/shipping\\/parcelforce_48\",\"extension\\/shipping\\/pickup\",\"extension\\/shipping\\/royal_mail\",\"extension\\/shipping\\/ups\",\"extension\\/shipping\\/usps\",\"extension\\/shipping\\/weight\",\"extension\\/theme\\/default\",\"extension\\/total\\/coupon\",\"extension\\/total\\/credit\",\"extension\\/total\\/handling\",\"extension\\/total\\/klarna_fee\",\"extension\\/total\\/low_order_fee\",\"extension\\/total\\/reward\",\"extension\\/total\\/shipping\",\"extension\\/total\\/sub_total\",\"extension\\/total\\/tax\",\"extension\\/total\\/total\",\"extension\\/total\\/voucher\",\"localisation\\/country\",\"localisation\\/currency\",\"localisation\\/geo_zone\",\"localisation\\/language\",\"localisation\\/length_class\",\"localisation\\/location\",\"localisation\\/order_status\",\"localisation\\/return_action\",\"localisation\\/return_reason\",\"localisation\\/return_status\",\"localisation\\/stock_status\",\"localisation\\/tax_class\",\"localisation\\/tax_rate\",\"localisation\\/weight_class\",\"localisation\\/zone\",\"mail\\/affiliate\",\"mail\\/customer\",\"mail\\/forgotten\",\"mail\\/return\",\"mail\\/reward\",\"mail\\/transaction\",\"marketing\\/contact\",\"marketing\\/coupon\",\"marketing\\/marketing\",\"marketplace\\/api\",\"marketplace\\/event\",\"marketplace\\/extension\",\"marketplace\\/install\",\"marketplace\\/installer\",\"marketplace\\/marketplace\",\"marketplace\\/modification\",\"report\\/online\",\"report\\/report\",\"report\\/statistics\",\"sale\\/order\",\"sale\\/recurring\",\"sale\\/return\",\"sale\\/voucher\",\"sale\\/voucher_theme\",\"setting\\/setting\",\"setting\\/store\",\"startup\\/error\",\"startup\\/event\",\"startup\\/login\",\"startup\\/permission\",\"startup\\/router\",\"startup\\/sass\",\"startup\\/startup\",\"tool\\/backup\",\"tool\\/log\",\"tool\\/upload\",\"user\\/api\",\"user\\/user\",\"user\\/user_permission\",\"extension\\/payment\\/moyasar3\"],\"modify\":[\"catalog\\/attribute\",\"catalog\\/attribute_group\",\"catalog\\/category\",\"catalog\\/download\",\"catalog\\/filter\",\"catalog\\/information\",\"catalog\\/manufacturer\",\"catalog\\/option\",\"catalog\\/product\",\"catalog\\/recurring\",\"catalog\\/review\",\"common\\/column_left\",\"common\\/developer\",\"common\\/filemanager\",\"common\\/profile\",\"common\\/security\",\"customer\\/custom_field\",\"customer\\/customer\",\"customer\\/customer_approval\",\"customer\\/customer_group\",\"design\\/banner\",\"design\\/layout\",\"design\\/theme\",\"design\\/translation\",\"design\\/seo_url\",\"event\\/statistics\",\"event\\/theme\",\"extension\\/advertise\\/google\",\"extension\\/analytics\\/google\",\"extension\\/captcha\\/basic\",\"extension\\/captcha\\/google\",\"extension\\/dashboard\\/activity\",\"extension\\/dashboard\\/chart\",\"extension\\/dashboard\\/customer\",\"extension\\/dashboard\\/map\",\"extension\\/dashboard\\/online\",\"extension\\/dashboard\\/order\",\"extension\\/dashboard\\/recent\",\"extension\\/dashboard\\/sale\",\"extension\\/extension\\/advertise\",\"extension\\/extension\\/analytics\",\"extension\\/extension\\/captcha\",\"extension\\/extension\\/dashboard\",\"extension\\/extension\\/feed\",\"extension\\/extension\\/fraud\",\"extension\\/extension\\/menu\",\"extension\\/extension\\/module\",\"extension\\/extension\\/payment\",\"extension\\/extension\\/report\",\"extension\\/extension\\/shipping\",\"extension\\/extension\\/theme\",\"extension\\/extension\\/total\",\"extension\\/feed\\/google_base\",\"extension\\/feed\\/google_sitemap\",\"extension\\/fraud\\/fraudlabspro\",\"extension\\/fraud\\/ip\",\"extension\\/fraud\\/maxmind\",\"extension\\/marketing\\/remarketing\",\"extension\\/module\\/account\",\"extension\\/module\\/amazon_login\",\"extension\\/module\\/amazon_pay\",\"extension\\/module\\/banner\",\"extension\\/module\\/bestseller\",\"extension\\/module\\/carousel\",\"extension\\/module\\/category\",\"extension\\/module\\/divido_calculator\",\"extension\\/module\\/ebay_listing\",\"extension\\/module\\/featured\",\"extension\\/module\\/filter\",\"extension\\/module\\/google_hangouts\",\"extension\\/module\\/html\",\"extension\\/module\\/information\",\"extension\\/module\\/klarna_checkout_module\",\"extension\\/module\\/latest\",\"extension\\/module\\/laybuy_layout\",\"extension\\/module\\/pilibaba_button\",\"extension\\/module\\/sagepay_direct_cards\",\"extension\\/module\\/sagepay_server_cards\",\"extension\\/module\\/slideshow\",\"extension\\/module\\/special\",\"extension\\/module\\/store\",\"extension\\/payment\\/amazon_login_pay\",\"extension\\/payment\\/authorizenet_aim\",\"extension\\/payment\\/authorizenet_sim\",\"extension\\/payment\\/bank_transfer\",\"extension\\/payment\\/bluepay_hosted\",\"extension\\/payment\\/bluepay_redirect\",\"extension\\/payment\\/cardconnect\",\"extension\\/payment\\/cardinity\",\"extension\\/payment\\/cheque\",\"extension\\/payment\\/cod\",\"extension\\/payment\\/divido\",\"extension\\/payment\\/eway\",\"extension\\/payment\\/firstdata\",\"extension\\/payment\\/firstdata_remote\",\"extension\\/payment\\/free_checkout\",\"extension\\/payment\\/g2apay\",\"extension\\/payment\\/globalpay\",\"extension\\/payment\\/globalpay_remote\",\"extension\\/payment\\/klarna_account\",\"extension\\/payment\\/klarna_checkout\",\"extension\\/payment\\/klarna_invoice\",\"extension\\/payment\\/laybuy\",\"extension\\/payment\\/liqpay\",\"extension\\/payment\\/nochex\",\"extension\\/payment\\/paymate\",\"extension\\/payment\\/paypoint\",\"extension\\/payment\\/payza\",\"extension\\/payment\\/perpetual_payments\",\"extension\\/payment\\/pilibaba\",\"extension\\/payment\\/realex\",\"extension\\/payment\\/realex_remote\",\"extension\\/payment\\/sagepay_direct\",\"extension\\/payment\\/sagepay_server\",\"extension\\/payment\\/sagepay_us\",\"extension\\/payment\\/securetrading_pp\",\"extension\\/payment\\/securetrading_ws\",\"extension\\/payment\\/skrill\",\"extension\\/payment\\/twocheckout\",\"extension\\/payment\\/web_payment_software\",\"extension\\/payment\\/worldpay\",\"extension\\/module\\/pp_braintree_button\",\"extension\\/payment\\/pp_braintree\",\"extension\\/report\\/customer_activity\",\"extension\\/report\\/customer_order\",\"extension\\/report\\/customer_reward\",\"extension\\/report\\/customer_search\",\"extension\\/report\\/customer_transaction\",\"extension\\/report\\/marketing\",\"extension\\/report\\/product_purchased\",\"extension\\/report\\/product_viewed\",\"extension\\/report\\/sale_coupon\",\"extension\\/report\\/sale_order\",\"extension\\/report\\/sale_return\",\"extension\\/report\\/sale_shipping\",\"extension\\/report\\/sale_tax\",\"extension\\/shipping\\/auspost\",\"extension\\/shipping\\/ec_ship\",\"extension\\/shipping\\/fedex\",\"extension\\/shipping\\/flat\",\"extension\\/shipping\\/free\",\"extension\\/shipping\\/item\",\"extension\\/shipping\\/parcelforce_48\",\"extension\\/shipping\\/pickup\",\"extension\\/shipping\\/royal_mail\",\"extension\\/shipping\\/ups\",\"extension\\/shipping\\/usps\",\"extension\\/shipping\\/weight\",\"extension\\/theme\\/default\",\"extension\\/total\\/coupon\",\"extension\\/total\\/credit\",\"extension\\/total\\/handling\",\"extension\\/total\\/klarna_fee\",\"extension\\/total\\/low_order_fee\",\"extension\\/total\\/reward\",\"extension\\/total\\/shipping\",\"extension\\/total\\/sub_total\",\"extension\\/total\\/tax\",\"extension\\/total\\/total\",\"extension\\/total\\/voucher\",\"localisation\\/country\",\"localisation\\/currency\",\"localisation\\/geo_zone\",\"localisation\\/language\",\"localisation\\/length_class\",\"localisation\\/location\",\"localisation\\/order_status\",\"localisation\\/return_action\",\"localisation\\/return_reason\",\"localisation\\/return_status\",\"localisation\\/stock_status\",\"localisation\\/tax_class\",\"localisation\\/tax_rate\",\"localisation\\/weight_class\",\"localisation\\/zone\",\"mail\\/affiliate\",\"mail\\/customer\",\"mail\\/forgotten\",\"mail\\/return\",\"mail\\/reward\",\"mail\\/transaction\",\"marketing\\/contact\",\"marketing\\/coupon\",\"marketing\\/marketing\",\"marketplace\\/event\",\"marketplace\\/api\",\"marketplace\\/extension\",\"marketplace\\/install\",\"marketplace\\/installer\",\"marketplace\\/marketplace\",\"marketplace\\/modification\",\"report\\/online\",\"report\\/report\",\"report\\/statistics\",\"sale\\/order\",\"sale\\/recurring\",\"sale\\/return\",\"sale\\/voucher\",\"sale\\/voucher_theme\",\"setting\\/setting\",\"setting\\/store\",\"startup\\/error\",\"startup\\/event\",\"startup\\/login\",\"startup\\/permission\",\"startup\\/router\",\"startup\\/sass\",\"startup\\/startup\",\"tool\\/backup\",\"tool\\/log\",\"tool\\/upload\",\"user\\/api\",\"user\\/user\",\"user\\/user_permission\",\"extension\\/payment\\/moyasar3\"]}'),
(10, 'Demonstration', '');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_voucher`
--

CREATE TABLE `ocn8_voucher` (
  `voucher_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `code` varchar(10) NOT NULL,
  `from_name` varchar(64) NOT NULL,
  `from_email` varchar(96) NOT NULL,
  `to_name` varchar(64) NOT NULL,
  `to_email` varchar(96) NOT NULL,
  `voucher_theme_id` int(11) NOT NULL,
  `message` text NOT NULL,
  `amount` decimal(15,4) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_voucher_history`
--

CREATE TABLE `ocn8_voucher_history` (
  `voucher_history_id` int(11) NOT NULL,
  `voucher_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `amount` decimal(15,4) NOT NULL,
  `date_added` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_voucher_theme`
--

CREATE TABLE `ocn8_voucher_theme` (
  `voucher_theme_id` int(11) NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_voucher_theme`
--

INSERT INTO `ocn8_voucher_theme` (`voucher_theme_id`, `image`) VALUES
(8, 'catalog/demo/canon_eos_5d_2.jpg'),
(7, 'catalog/demo/gift-voucher-birthday.jpg'),
(6, 'catalog/demo/apple_logo.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_voucher_theme_description`
--

CREATE TABLE `ocn8_voucher_theme_description` (
  `voucher_theme_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_voucher_theme_description`
--

INSERT INTO `ocn8_voucher_theme_description` (`voucher_theme_id`, `language_id`, `name`) VALUES
(6, 1, 'Christmas'),
(7, 1, 'Birthday'),
(8, 1, 'General'),
(6, 2, 'Christmas'),
(7, 2, 'Birthday'),
(8, 2, 'General');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_weight_class`
--

CREATE TABLE `ocn8_weight_class` (
  `weight_class_id` int(11) NOT NULL,
  `value` decimal(15,8) NOT NULL DEFAULT 0.00000000
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_weight_class`
--

INSERT INTO `ocn8_weight_class` (`weight_class_id`, `value`) VALUES
(1, '0.25000000');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_weight_class_description`
--

CREATE TABLE `ocn8_weight_class_description` (
  `weight_class_id` int(11) NOT NULL,
  `language_id` int(11) NOT NULL,
  `title` varchar(32) NOT NULL,
  `unit` varchar(4) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_weight_class_description`
--

INSERT INTO `ocn8_weight_class_description` (`weight_class_id`, `language_id`, `title`, `unit`) VALUES
(1, 2, 'Kilogram', 'kg'),
(1, 1, 'Kilogram', 'kg');

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_zone`
--

CREATE TABLE `ocn8_zone` (
  `zone_id` int(11) NOT NULL,
  `country_id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL,
  `code` varchar(32) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_zone`
--

INSERT INTO `ocn8_zone` (`zone_id`, `country_id`, `name`, `code`, `status`) VALUES
(1, 1, 'Badakhshan', 'BDS', 1),
(2, 1, 'Badghis', 'BDG', 1),
(3, 1, 'Baghlan', 'BGL', 1),
(4, 1, 'Balkh', 'BAL', 1),
(5, 1, 'Bamian', 'BAM', 1),
(6, 1, 'Farah', 'FRA', 1),
(7, 1, 'Faryab', 'FYB', 1),
(8, 1, 'Ghazni', 'GHA', 1),
(9, 1, 'Ghowr', 'GHO', 1),
(10, 1, 'Helmand', 'HEL', 1),
(11, 1, 'Herat', 'HER', 1),
(12, 1, 'Jowzjan', 'JOW', 1),
(13, 1, 'Kabul', 'KAB', 1),
(14, 1, 'Kandahar', 'KAN', 1),
(15, 1, 'Kapisa', 'KAP', 1),
(16, 1, 'Khost', 'KHO', 1),
(17, 1, 'Konar', 'KNR', 1),
(18, 1, 'Kondoz', 'KDZ', 1),
(19, 1, 'Laghman', 'LAG', 1),
(20, 1, 'Lowgar', 'LOW', 1),
(21, 1, 'Nangrahar', 'NAN', 1),
(22, 1, 'Nimruz', 'NIM', 1),
(23, 1, 'Nurestan', 'NUR', 1),
(24, 1, 'Oruzgan', 'ORU', 1),
(25, 1, 'Paktia', 'PIA', 1),
(26, 1, 'Paktika', 'PKA', 1),
(27, 1, 'Parwan', 'PAR', 1),
(28, 1, 'Samangan', 'SAM', 1),
(29, 1, 'Sar-e Pol', 'SAR', 1),
(30, 1, 'Takhar', 'TAK', 1),
(31, 1, 'Wardak', 'WAR', 1),
(32, 1, 'Zabol', 'ZAB', 1),
(33, 2, 'Berat', 'BR', 1),
(34, 2, 'Bulqize', 'BU', 1),
(35, 2, 'Delvine', 'DL', 1),
(36, 2, 'Devoll', 'DV', 1),
(37, 2, 'Diber', 'DI', 1),
(38, 2, 'Durres', 'DR', 1),
(39, 2, 'Elbasan', 'EL', 1),
(40, 2, 'Kolonje', 'ER', 1),
(41, 2, 'Fier', 'FR', 1),
(42, 2, 'Gjirokaster', 'GJ', 1),
(43, 2, 'Gramsh', 'GR', 1),
(44, 2, 'Has', 'HA', 1),
(45, 2, 'Kavaje', 'KA', 1),
(46, 2, 'Kurbin', 'KB', 1),
(47, 2, 'Kucove', 'KC', 1),
(48, 2, 'Korce', 'KO', 1),
(49, 2, 'Kruje', 'KR', 1),
(50, 2, 'Kukes', 'KU', 1),
(51, 2, 'Librazhd', 'LB', 1),
(52, 2, 'Lezhe', 'LE', 1),
(53, 2, 'Lushnje', 'LU', 1),
(54, 2, 'Malesi e Madhe', 'MM', 1),
(55, 2, 'Mallakaster', 'MK', 1),
(56, 2, 'Mat', 'MT', 1),
(57, 2, 'Mirdite', 'MR', 1),
(58, 2, 'Peqin', 'PQ', 1),
(59, 2, 'Permet', 'PR', 1),
(60, 2, 'Pogradec', 'PG', 1),
(61, 2, 'Puke', 'PU', 1),
(62, 2, 'Shkoder', 'SH', 1),
(63, 2, 'Skrapar', 'SK', 1),
(64, 2, 'Sarande', 'SR', 1),
(65, 2, 'Tepelene', 'TE', 1),
(66, 2, 'Tropoje', 'TP', 1),
(67, 2, 'Tirane', 'TR', 1),
(68, 2, 'Vlore', 'VL', 1),
(69, 3, 'Adrar', 'ADR', 1),
(70, 3, 'Ain Defla', 'ADE', 1),
(71, 3, 'Ain Temouchent', 'ATE', 1),
(72, 3, 'Alger', 'ALG', 1),
(73, 3, 'Annaba', 'ANN', 1),
(74, 3, 'Batna', 'BAT', 1),
(75, 3, 'Bechar', 'BEC', 1),
(76, 3, 'Bejaia', 'BEJ', 1),
(77, 3, 'Biskra', 'BIS', 1),
(78, 3, 'Blida', 'BLI', 1),
(79, 3, 'Bordj Bou Arreridj', 'BBA', 1),
(80, 3, 'Bouira', 'BOA', 1),
(81, 3, 'Boumerdes', 'BMD', 1),
(82, 3, 'Chlef', 'CHL', 1),
(83, 3, 'Constantine', 'CON', 1),
(84, 3, 'Djelfa', 'DJE', 1),
(85, 3, 'El Bayadh', 'EBA', 1),
(86, 3, 'El Oued', 'EOU', 1),
(87, 3, 'El Tarf', 'ETA', 1),
(88, 3, 'Ghardaia', 'GHA', 1),
(89, 3, 'Guelma', 'GUE', 1),
(90, 3, 'Illizi', 'ILL', 1),
(91, 3, 'Jijel', 'JIJ', 1),
(92, 3, 'Khenchela', 'KHE', 1),
(93, 3, 'Laghouat', 'LAG', 1),
(94, 3, 'Muaskar', 'MUA', 1),
(95, 3, 'Medea', 'MED', 1),
(96, 3, 'Mila', 'MIL', 1),
(97, 3, 'Mostaganem', 'MOS', 1),
(98, 3, 'M\'Sila', 'MSI', 1),
(99, 3, 'Naama', 'NAA', 1),
(100, 3, 'Oran', 'ORA', 1),
(101, 3, 'Ouargla', 'OUA', 1),
(102, 3, 'Oum el-Bouaghi', 'OEB', 1),
(103, 3, 'Relizane', 'REL', 1),
(104, 3, 'Saida', 'SAI', 1),
(105, 3, 'Setif', 'SET', 1),
(106, 3, 'Sidi Bel Abbes', 'SBA', 1),
(107, 3, 'Skikda', 'SKI', 1),
(108, 3, 'Souk Ahras', 'SAH', 1),
(109, 3, 'Tamanghasset', 'TAM', 1),
(110, 3, 'Tebessa', 'TEB', 1),
(111, 3, 'Tiaret', 'TIA', 1),
(112, 3, 'Tindouf', 'TIN', 1),
(113, 3, 'Tipaza', 'TIP', 1),
(114, 3, 'Tissemsilt', 'TIS', 1),
(115, 3, 'Tizi Ouzou', 'TOU', 1),
(116, 3, 'Tlemcen', 'TLE', 1),
(117, 4, 'Eastern', 'E', 1),
(118, 4, 'Manu\'a', 'M', 1),
(119, 4, 'Rose Island', 'R', 1),
(120, 4, 'Swains Island', 'S', 1),
(121, 4, 'Western', 'W', 1),
(122, 5, 'Andorra la Vella', 'ALV', 1),
(123, 5, 'Canillo', 'CAN', 1),
(124, 5, 'Encamp', 'ENC', 1),
(125, 5, 'Escaldes-Engordany', 'ESE', 1),
(126, 5, 'La Massana', 'LMA', 1),
(127, 5, 'Ordino', 'ORD', 1),
(128, 5, 'Sant Julia de Loria', 'SJL', 1),
(129, 6, 'Bengo', 'BGO', 1),
(130, 6, 'Benguela', 'BGU', 1),
(131, 6, 'Bie', 'BIE', 1),
(132, 6, 'Cabinda', 'CAB', 1),
(133, 6, 'Cuando-Cubango', 'CCU', 1),
(134, 6, 'Cuanza Norte', 'CNO', 1),
(135, 6, 'Cuanza Sul', 'CUS', 1),
(136, 6, 'Cunene', 'CNN', 1),
(137, 6, 'Huambo', 'HUA', 1),
(138, 6, 'Huila', 'HUI', 1),
(139, 6, 'Luanda', 'LUA', 1),
(140, 6, 'Lunda Norte', 'LNO', 1),
(141, 6, 'Lunda Sul', 'LSU', 1),
(142, 6, 'Malange', 'MAL', 1),
(143, 6, 'Moxico', 'MOX', 1),
(144, 6, 'Namibe', 'NAM', 1),
(145, 6, 'Uige', 'UIG', 1),
(146, 6, 'Zaire', 'ZAI', 1),
(147, 9, 'Saint George', 'ASG', 1),
(148, 9, 'Saint John', 'ASJ', 1),
(149, 9, 'Saint Mary', 'ASM', 1),
(150, 9, 'Saint Paul', 'ASL', 1),
(151, 9, 'Saint Peter', 'ASR', 1),
(152, 9, 'Saint Philip', 'ASH', 1),
(153, 9, 'Barbuda', 'BAR', 1),
(154, 9, 'Redonda', 'RED', 1),
(155, 10, 'Antartida e Islas del Atlantico', 'AN', 1),
(156, 10, 'Buenos Aires', 'BA', 1),
(157, 10, 'Catamarca', 'CA', 1),
(158, 10, 'Chaco', 'CH', 1),
(159, 10, 'Chubut', 'CU', 1),
(160, 10, 'Cordoba', 'CO', 1),
(161, 10, 'Corrientes', 'CR', 1),
(162, 10, 'Distrito Federal', 'DF', 1),
(163, 10, 'Entre Rios', 'ER', 1),
(164, 10, 'Formosa', 'FO', 1),
(165, 10, 'Jujuy', 'JU', 1),
(166, 10, 'La Pampa', 'LP', 1),
(167, 10, 'La Rioja', 'LR', 1),
(168, 10, 'Mendoza', 'ME', 1),
(169, 10, 'Misiones', 'MI', 1),
(170, 10, 'Neuquen', 'NE', 1),
(171, 10, 'Rio Negro', 'RN', 1),
(172, 10, 'Salta', 'SA', 1),
(173, 10, 'San Juan', 'SJ', 1),
(174, 10, 'San Luis', 'SL', 1),
(175, 10, 'Santa Cruz', 'SC', 1),
(176, 10, 'Santa Fe', 'SF', 1),
(177, 10, 'Santiago del Estero', 'SD', 1),
(178, 10, 'Tierra del Fuego', 'TF', 1),
(179, 10, 'Tucuman', 'TU', 1),
(180, 11, 'Aragatsotn', 'AGT', 1),
(181, 11, 'Ararat', 'ARR', 1),
(182, 11, 'Armavir', 'ARM', 1),
(183, 11, 'Geghark\'unik\'', 'GEG', 1),
(184, 11, 'Kotayk\'', 'KOT', 1),
(185, 11, 'Lorri', 'LOR', 1),
(186, 11, 'Shirak', 'SHI', 1),
(187, 11, 'Syunik\'', 'SYU', 1),
(188, 11, 'Tavush', 'TAV', 1),
(189, 11, 'Vayots\' Dzor', 'VAY', 1),
(190, 11, 'Yerevan', 'YER', 1),
(191, 13, 'Australian Capital Territory', 'ACT', 1),
(192, 13, 'New South Wales', 'NSW', 1),
(193, 13, 'Northern Territory', 'NT', 1),
(194, 13, 'Queensland', 'QLD', 1),
(195, 13, 'South Australia', 'SA', 1),
(196, 13, 'Tasmania', 'TAS', 1),
(197, 13, 'Victoria', 'VIC', 1),
(198, 13, 'Western Australia', 'WA', 1),
(199, 14, 'Burgenland', 'BUR', 1),
(200, 14, 'Kärnten', 'KAR', 1),
(201, 14, 'Niederösterreich', 'NOS', 1),
(202, 14, 'Oberösterreich', 'OOS', 1),
(203, 14, 'Salzburg', 'SAL', 1),
(204, 14, 'Steiermark', 'STE', 1),
(205, 14, 'Tirol', 'TIR', 1),
(206, 14, 'Vorarlberg', 'VOR', 1),
(207, 14, 'Wien', 'WIE', 1),
(208, 15, 'Ali Bayramli', 'AB', 1),
(209, 15, 'Abseron', 'ABS', 1),
(210, 15, 'AgcabAdi', 'AGC', 1),
(211, 15, 'Agdam', 'AGM', 1),
(212, 15, 'Agdas', 'AGS', 1),
(213, 15, 'Agstafa', 'AGA', 1),
(214, 15, 'Agsu', 'AGU', 1),
(215, 15, 'Astara', 'AST', 1),
(216, 15, 'Baki', 'BA', 1),
(217, 15, 'BabAk', 'BAB', 1),
(218, 15, 'BalakAn', 'BAL', 1),
(219, 15, 'BArdA', 'BAR', 1),
(220, 15, 'Beylaqan', 'BEY', 1),
(221, 15, 'Bilasuvar', 'BIL', 1),
(222, 15, 'Cabrayil', 'CAB', 1),
(223, 15, 'Calilabab', 'CAL', 1),
(224, 15, 'Culfa', 'CUL', 1),
(225, 15, 'Daskasan', 'DAS', 1),
(226, 15, 'Davaci', 'DAV', 1),
(227, 15, 'Fuzuli', 'FUZ', 1),
(228, 15, 'Ganca', 'GA', 1),
(229, 15, 'Gadabay', 'GAD', 1),
(230, 15, 'Goranboy', 'GOR', 1),
(231, 15, 'Goycay', 'GOY', 1),
(232, 15, 'Haciqabul', 'HAC', 1),
(233, 15, 'Imisli', 'IMI', 1),
(234, 15, 'Ismayilli', 'ISM', 1),
(235, 15, 'Kalbacar', 'KAL', 1),
(236, 15, 'Kurdamir', 'KUR', 1),
(237, 15, 'Lankaran', 'LA', 1),
(238, 15, 'Lacin', 'LAC', 1),
(239, 15, 'Lankaran', 'LAN', 1),
(240, 15, 'Lerik', 'LER', 1),
(241, 15, 'Masalli', 'MAS', 1),
(242, 15, 'Mingacevir', 'MI', 1),
(243, 15, 'Naftalan', 'NA', 1),
(244, 15, 'Neftcala', 'NEF', 1),
(245, 15, 'Oguz', 'OGU', 1),
(246, 15, 'Ordubad', 'ORD', 1),
(247, 15, 'Qabala', 'QAB', 1),
(248, 15, 'Qax', 'QAX', 1),
(249, 15, 'Qazax', 'QAZ', 1),
(250, 15, 'Qobustan', 'QOB', 1),
(251, 15, 'Quba', 'QBA', 1),
(252, 15, 'Qubadli', 'QBI', 1),
(253, 15, 'Qusar', 'QUS', 1),
(254, 15, 'Saki', 'SA', 1),
(255, 15, 'Saatli', 'SAT', 1),
(256, 15, 'Sabirabad', 'SAB', 1),
(257, 15, 'Sadarak', 'SAD', 1),
(258, 15, 'Sahbuz', 'SAH', 1),
(259, 15, 'Saki', 'SAK', 1),
(260, 15, 'Salyan', 'SAL', 1),
(261, 15, 'Sumqayit', 'SM', 1),
(262, 15, 'Samaxi', 'SMI', 1),
(263, 15, 'Samkir', 'SKR', 1),
(264, 15, 'Samux', 'SMX', 1),
(265, 15, 'Sarur', 'SAR', 1),
(266, 15, 'Siyazan', 'SIY', 1),
(267, 15, 'Susa', 'SS', 1),
(268, 15, 'Susa', 'SUS', 1),
(269, 15, 'Tartar', 'TAR', 1),
(270, 15, 'Tovuz', 'TOV', 1),
(271, 15, 'Ucar', 'UCA', 1),
(272, 15, 'Xankandi', 'XA', 1),
(273, 15, 'Xacmaz', 'XAC', 1),
(274, 15, 'Xanlar', 'XAN', 1),
(275, 15, 'Xizi', 'XIZ', 1),
(276, 15, 'Xocali', 'XCI', 1),
(277, 15, 'Xocavand', 'XVD', 1),
(278, 15, 'Yardimli', 'YAR', 1),
(279, 15, 'Yevlax', 'YEV', 1),
(280, 15, 'Zangilan', 'ZAN', 1),
(281, 15, 'Zaqatala', 'ZAQ', 1),
(282, 15, 'Zardab', 'ZAR', 1),
(283, 15, 'Naxcivan', 'NX', 1),
(284, 16, 'Acklins', 'ACK', 1),
(285, 16, 'Berry Islands', 'BER', 1),
(286, 16, 'Bimini', 'BIM', 1),
(287, 16, 'Black Point', 'BLK', 1),
(288, 16, 'Cat Island', 'CAT', 1),
(289, 16, 'Central Abaco', 'CAB', 1),
(290, 16, 'Central Andros', 'CAN', 1),
(291, 16, 'Central Eleuthera', 'CEL', 1),
(292, 16, 'City of Freeport', 'FRE', 1),
(293, 16, 'Crooked Island', 'CRO', 1),
(294, 16, 'East Grand Bahama', 'EGB', 1),
(295, 16, 'Exuma', 'EXU', 1),
(296, 16, 'Grand Cay', 'GRD', 1),
(297, 16, 'Harbour Island', 'HAR', 1),
(298, 16, 'Hope Town', 'HOP', 1),
(299, 16, 'Inagua', 'INA', 1),
(300, 16, 'Long Island', 'LNG', 1),
(301, 16, 'Mangrove Cay', 'MAN', 1),
(302, 16, 'Mayaguana', 'MAY', 1),
(303, 16, 'Moore\'s Island', 'MOO', 1),
(304, 16, 'North Abaco', 'NAB', 1),
(305, 16, 'North Andros', 'NAN', 1),
(306, 16, 'North Eleuthera', 'NEL', 1),
(307, 16, 'Ragged Island', 'RAG', 1),
(308, 16, 'Rum Cay', 'RUM', 1),
(309, 16, 'San Salvador', 'SAL', 1),
(310, 16, 'South Abaco', 'SAB', 1),
(311, 16, 'South Andros', 'SAN', 1),
(312, 16, 'South Eleuthera', 'SEL', 1),
(313, 16, 'Spanish Wells', 'SWE', 1),
(314, 16, 'West Grand Bahama', 'WGB', 1),
(315, 17, 'Capital', 'CAP', 1),
(316, 17, 'Central', 'CEN', 1),
(317, 17, 'Muharraq', 'MUH', 1),
(318, 17, 'Northern', 'NOR', 1),
(319, 17, 'Southern', 'SOU', 1),
(320, 18, 'Barisal', 'BAR', 1),
(321, 18, 'Chittagong', 'CHI', 1),
(322, 18, 'Dhaka', 'DHA', 1),
(323, 18, 'Khulna', 'KHU', 1),
(324, 18, 'Rajshahi', 'RAJ', 1),
(325, 18, 'Sylhet', 'SYL', 1),
(326, 19, 'Christ Church', 'CC', 1),
(327, 19, 'Saint Andrew', 'AND', 1),
(328, 19, 'Saint George', 'GEO', 1),
(329, 19, 'Saint James', 'JAM', 1),
(330, 19, 'Saint John', 'JOH', 1),
(331, 19, 'Saint Joseph', 'JOS', 1),
(332, 19, 'Saint Lucy', 'LUC', 1),
(333, 19, 'Saint Michael', 'MIC', 1),
(334, 19, 'Saint Peter', 'PET', 1),
(335, 19, 'Saint Philip', 'PHI', 1),
(336, 19, 'Saint Thomas', 'THO', 1),
(337, 20, 'Brestskaya (Brest)', 'BR', 1),
(338, 20, 'Homyel\'skaya (Homyel\')', 'HO', 1),
(339, 20, 'Horad Minsk', 'HM', 1),
(340, 20, 'Hrodzyenskaya (Hrodna)', 'HR', 1),
(341, 20, 'Mahilyowskaya (Mahilyow)', 'MA', 1),
(342, 20, 'Minskaya', 'MI', 1),
(343, 20, 'Vitsyebskaya (Vitsyebsk)', 'VI', 1),
(344, 21, 'Antwerpen', 'VAN', 1),
(345, 21, 'Brabant Wallon', 'WBR', 1),
(346, 21, 'Hainaut', 'WHT', 1),
(347, 21, 'Liège', 'WLG', 1),
(348, 21, 'Limburg', 'VLI', 1),
(349, 21, 'Luxembourg', 'WLX', 1),
(350, 21, 'Namur', 'WNA', 1),
(351, 21, 'Oost-Vlaanderen', 'VOV', 1),
(352, 21, 'Vlaams Brabant', 'VBR', 1),
(353, 21, 'West-Vlaanderen', 'VWV', 1),
(354, 22, 'Belize', 'BZ', 1),
(355, 22, 'Cayo', 'CY', 1),
(356, 22, 'Corozal', 'CR', 1),
(357, 22, 'Orange Walk', 'OW', 1),
(358, 22, 'Stann Creek', 'SC', 1),
(359, 22, 'Toledo', 'TO', 1),
(360, 23, 'Alibori', 'AL', 1),
(361, 23, 'Atakora', 'AK', 1),
(362, 23, 'Atlantique', 'AQ', 1),
(363, 23, 'Borgou', 'BO', 1),
(364, 23, 'Collines', 'CO', 1),
(365, 23, 'Donga', 'DO', 1),
(366, 23, 'Kouffo', 'KO', 1),
(367, 23, 'Littoral', 'LI', 1),
(368, 23, 'Mono', 'MO', 1),
(369, 23, 'Oueme', 'OU', 1),
(370, 23, 'Plateau', 'PL', 1),
(371, 23, 'Zou', 'ZO', 1),
(372, 24, 'Devonshire', 'DS', 1),
(373, 24, 'Hamilton City', 'HC', 1),
(374, 24, 'Hamilton', 'HA', 1),
(375, 24, 'Paget', 'PG', 1),
(376, 24, 'Pembroke', 'PB', 1),
(377, 24, 'Saint George City', 'GC', 1),
(378, 24, 'Saint George\'s', 'SG', 1),
(379, 24, 'Sandys', 'SA', 1),
(380, 24, 'Smith\'s', 'SM', 1),
(381, 24, 'Southampton', 'SH', 1),
(382, 24, 'Warwick', 'WA', 1),
(383, 25, 'Bumthang', 'BUM', 1),
(384, 25, 'Chukha', 'CHU', 1),
(385, 25, 'Dagana', 'DAG', 1),
(386, 25, 'Gasa', 'GAS', 1),
(387, 25, 'Haa', 'HAA', 1),
(388, 25, 'Lhuntse', 'LHU', 1),
(389, 25, 'Mongar', 'MON', 1),
(390, 25, 'Paro', 'PAR', 1),
(391, 25, 'Pemagatshel', 'PEM', 1),
(392, 25, 'Punakha', 'PUN', 1),
(393, 25, 'Samdrup Jongkhar', 'SJO', 1),
(394, 25, 'Samtse', 'SAT', 1),
(395, 25, 'Sarpang', 'SAR', 1),
(396, 25, 'Thimphu', 'THI', 1),
(397, 25, 'Trashigang', 'TRG', 1),
(398, 25, 'Trashiyangste', 'TRY', 1),
(399, 25, 'Trongsa', 'TRO', 1),
(400, 25, 'Tsirang', 'TSI', 1),
(401, 25, 'Wangdue Phodrang', 'WPH', 1),
(402, 25, 'Zhemgang', 'ZHE', 1),
(403, 26, 'Beni', 'BEN', 1),
(404, 26, 'Chuquisaca', 'CHU', 1),
(405, 26, 'Cochabamba', 'COC', 1),
(406, 26, 'La Paz', 'LPZ', 1),
(407, 26, 'Oruro', 'ORU', 1),
(408, 26, 'Pando', 'PAN', 1),
(409, 26, 'Potosi', 'POT', 1),
(410, 26, 'Santa Cruz', 'SCZ', 1),
(411, 26, 'Tarija', 'TAR', 1),
(412, 27, 'Brcko district', 'BRO', 1),
(413, 27, 'Unsko-Sanski Kanton', 'FUS', 1),
(414, 27, 'Posavski Kanton', 'FPO', 1),
(415, 27, 'Tuzlanski Kanton', 'FTU', 1),
(416, 27, 'Zenicko-Dobojski Kanton', 'FZE', 1),
(417, 27, 'Bosanskopodrinjski Kanton', 'FBP', 1),
(418, 27, 'Srednjebosanski Kanton', 'FSB', 1),
(419, 27, 'Hercegovacko-neretvanski Kanton', 'FHN', 1),
(420, 27, 'Zapadnohercegovacka Zupanija', 'FZH', 1),
(421, 27, 'Kanton Sarajevo', 'FSA', 1),
(422, 27, 'Zapadnobosanska', 'FZA', 1),
(423, 27, 'Banja Luka', 'SBL', 1),
(424, 27, 'Doboj', 'SDO', 1),
(425, 27, 'Bijeljina', 'SBI', 1),
(426, 27, 'Vlasenica', 'SVL', 1),
(427, 27, 'Sarajevo-Romanija or Sokolac', 'SSR', 1),
(428, 27, 'Foca', 'SFO', 1),
(429, 27, 'Trebinje', 'STR', 1),
(430, 28, 'Central', 'CE', 1),
(431, 28, 'Ghanzi', 'GH', 1),
(432, 28, 'Kgalagadi', 'KD', 1),
(433, 28, 'Kgatleng', 'KT', 1),
(434, 28, 'Kweneng', 'KW', 1),
(435, 28, 'Ngamiland', 'NG', 1),
(436, 28, 'North East', 'NE', 1),
(437, 28, 'North West', 'NW', 1),
(438, 28, 'South East', 'SE', 1),
(439, 28, 'Southern', 'SO', 1),
(440, 30, 'Acre', 'AC', 1),
(441, 30, 'Alagoas', 'AL', 1),
(442, 30, 'Amapá', 'AP', 1),
(443, 30, 'Amazonas', 'AM', 1),
(444, 30, 'Bahia', 'BA', 1),
(445, 30, 'Ceará', 'CE', 1),
(446, 30, 'Distrito Federal', 'DF', 1),
(447, 30, 'Espírito Santo', 'ES', 1),
(448, 30, 'Goiás', 'GO', 1),
(449, 30, 'Maranhão', 'MA', 1),
(450, 30, 'Mato Grosso', 'MT', 1),
(451, 30, 'Mato Grosso do Sul', 'MS', 1),
(452, 30, 'Minas Gerais', 'MG', 1),
(453, 30, 'Pará', 'PA', 1),
(454, 30, 'Paraíba', 'PB', 1),
(455, 30, 'Paraná', 'PR', 1),
(456, 30, 'Pernambuco', 'PE', 1),
(457, 30, 'Piauí', 'PI', 1),
(458, 30, 'Rio de Janeiro', 'RJ', 1),
(459, 30, 'Rio Grande do Norte', 'RN', 1),
(460, 30, 'Rio Grande do Sul', 'RS', 1),
(461, 30, 'Rondônia', 'RO', 1),
(462, 30, 'Roraima', 'RR', 1),
(463, 30, 'Santa Catarina', 'SC', 1),
(464, 30, 'São Paulo', 'SP', 1),
(465, 30, 'Sergipe', 'SE', 1),
(466, 30, 'Tocantins', 'TO', 1),
(467, 31, 'Peros Banhos', 'PB', 1),
(468, 31, 'Salomon Islands', 'SI', 1),
(469, 31, 'Nelsons Island', 'NI', 1),
(470, 31, 'Three Brothers', 'TB', 1),
(471, 31, 'Eagle Islands', 'EA', 1),
(472, 31, 'Danger Island', 'DI', 1),
(473, 31, 'Egmont Islands', 'EG', 1),
(474, 31, 'Diego Garcia', 'DG', 1),
(475, 32, 'Belait', 'BEL', 1),
(476, 32, 'Brunei and Muara', 'BRM', 1),
(477, 32, 'Temburong', 'TEM', 1),
(478, 32, 'Tutong', 'TUT', 1),
(479, 33, 'Blagoevgrad', '', 1),
(480, 33, 'Burgas', '', 1),
(481, 33, 'Dobrich', '', 1),
(482, 33, 'Gabrovo', '', 1),
(483, 33, 'Haskovo', '', 1),
(484, 33, 'Kardjali', '', 1),
(485, 33, 'Kyustendil', '', 1),
(486, 33, 'Lovech', '', 1),
(487, 33, 'Montana', '', 1),
(488, 33, 'Pazardjik', '', 1),
(489, 33, 'Pernik', '', 1),
(490, 33, 'Pleven', '', 1),
(491, 33, 'Plovdiv', '', 1),
(492, 33, 'Razgrad', '', 1),
(493, 33, 'Shumen', '', 1),
(494, 33, 'Silistra', '', 1),
(495, 33, 'Sliven', '', 1),
(496, 33, 'Smolyan', '', 1),
(497, 33, 'Sofia', '', 1),
(498, 33, 'Sofia - town', '', 1),
(499, 33, 'Stara Zagora', '', 1),
(500, 33, 'Targovishte', '', 1),
(501, 33, 'Varna', '', 1),
(502, 33, 'Veliko Tarnovo', '', 1),
(503, 33, 'Vidin', '', 1),
(504, 33, 'Vratza', '', 1),
(505, 33, 'Yambol', '', 1),
(506, 34, 'Bale', 'BAL', 1),
(507, 34, 'Bam', 'BAM', 1),
(508, 34, 'Banwa', 'BAN', 1),
(509, 34, 'Bazega', 'BAZ', 1),
(510, 34, 'Bougouriba', 'BOR', 1),
(511, 34, 'Boulgou', 'BLG', 1),
(512, 34, 'Boulkiemde', 'BOK', 1),
(513, 34, 'Comoe', 'COM', 1),
(514, 34, 'Ganzourgou', 'GAN', 1),
(515, 34, 'Gnagna', 'GNA', 1),
(516, 34, 'Gourma', 'GOU', 1),
(517, 34, 'Houet', 'HOU', 1),
(518, 34, 'Ioba', 'IOA', 1),
(519, 34, 'Kadiogo', 'KAD', 1),
(520, 34, 'Kenedougou', 'KEN', 1),
(521, 34, 'Komondjari', 'KOD', 1),
(522, 34, 'Kompienga', 'KOP', 1),
(523, 34, 'Kossi', 'KOS', 1),
(524, 34, 'Koulpelogo', 'KOL', 1),
(525, 34, 'Kouritenga', 'KOT', 1),
(526, 34, 'Kourweogo', 'KOW', 1),
(527, 34, 'Leraba', 'LER', 1),
(528, 34, 'Loroum', 'LOR', 1),
(529, 34, 'Mouhoun', 'MOU', 1),
(530, 34, 'Nahouri', 'NAH', 1),
(531, 34, 'Namentenga', 'NAM', 1),
(532, 34, 'Nayala', 'NAY', 1),
(533, 34, 'Noumbiel', 'NOU', 1),
(534, 34, 'Oubritenga', 'OUB', 1),
(535, 34, 'Oudalan', 'OUD', 1),
(536, 34, 'Passore', 'PAS', 1),
(537, 34, 'Poni', 'PON', 1),
(538, 34, 'Sanguie', 'SAG', 1),
(539, 34, 'Sanmatenga', 'SAM', 1),
(540, 34, 'Seno', 'SEN', 1),
(541, 34, 'Sissili', 'SIS', 1),
(542, 34, 'Soum', 'SOM', 1),
(543, 34, 'Sourou', 'SOR', 1),
(544, 34, 'Tapoa', 'TAP', 1),
(545, 34, 'Tuy', 'TUY', 1),
(546, 34, 'Yagha', 'YAG', 1),
(547, 34, 'Yatenga', 'YAT', 1),
(548, 34, 'Ziro', 'ZIR', 1),
(549, 34, 'Zondoma', 'ZOD', 1),
(550, 34, 'Zoundweogo', 'ZOW', 1),
(551, 35, 'Bubanza', 'BB', 1),
(552, 35, 'Bujumbura', 'BJ', 1),
(553, 35, 'Bururi', 'BR', 1),
(554, 35, 'Cankuzo', 'CA', 1),
(555, 35, 'Cibitoke', 'CI', 1),
(556, 35, 'Gitega', 'GI', 1),
(557, 35, 'Karuzi', 'KR', 1),
(558, 35, 'Kayanza', 'KY', 1),
(559, 35, 'Kirundo', 'KI', 1),
(560, 35, 'Makamba', 'MA', 1),
(561, 35, 'Muramvya', 'MU', 1),
(562, 35, 'Muyinga', 'MY', 1),
(563, 35, 'Mwaro', 'MW', 1),
(564, 35, 'Ngozi', 'NG', 1),
(565, 35, 'Rutana', 'RT', 1),
(566, 35, 'Ruyigi', 'RY', 1),
(567, 36, 'Phnom Penh', 'PP', 1),
(568, 36, 'Preah Seihanu (Kompong Som or Sihanoukville)', 'PS', 1),
(569, 36, 'Pailin', 'PA', 1),
(570, 36, 'Keb', 'KB', 1),
(571, 36, 'Banteay Meanchey', 'BM', 1),
(572, 36, 'Battambang', 'BA', 1),
(573, 36, 'Kampong Cham', 'KM', 1),
(574, 36, 'Kampong Chhnang', 'KN', 1),
(575, 36, 'Kampong Speu', 'KU', 1),
(576, 36, 'Kampong Som', 'KO', 1),
(577, 36, 'Kampong Thom', 'KT', 1),
(578, 36, 'Kampot', 'KP', 1),
(579, 36, 'Kandal', 'KL', 1),
(580, 36, 'Kaoh Kong', 'KK', 1),
(581, 36, 'Kratie', 'KR', 1),
(582, 36, 'Mondul Kiri', 'MK', 1),
(583, 36, 'Oddar Meancheay', 'OM', 1),
(584, 36, 'Pursat', 'PU', 1),
(585, 36, 'Preah Vihear', 'PR', 1),
(586, 36, 'Prey Veng', 'PG', 1),
(587, 36, 'Ratanak Kiri', 'RK', 1),
(588, 36, 'Siemreap', 'SI', 1),
(589, 36, 'Stung Treng', 'ST', 1),
(590, 36, 'Svay Rieng', 'SR', 1),
(591, 36, 'Takeo', 'TK', 1),
(592, 37, 'Adamawa (Adamaoua)', 'ADA', 1),
(593, 37, 'Centre', 'CEN', 1),
(594, 37, 'East (Est)', 'EST', 1),
(595, 37, 'Extreme North (Extreme-Nord)', 'EXN', 1),
(596, 37, 'Littoral', 'LIT', 1),
(597, 37, 'North (Nord)', 'NOR', 1),
(598, 37, 'Northwest (Nord-Ouest)', 'NOT', 1),
(599, 37, 'West (Ouest)', 'OUE', 1),
(600, 37, 'South (Sud)', 'SUD', 1),
(601, 37, 'Southwest (Sud-Ouest).', 'SOU', 1),
(602, 38, 'Alberta', 'AB', 1),
(603, 38, 'British Columbia', 'BC', 1),
(604, 38, 'Manitoba', 'MB', 1),
(605, 38, 'New Brunswick', 'NB', 1),
(606, 38, 'Newfoundland and Labrador', 'NL', 1),
(607, 38, 'Northwest Territories', 'NT', 1),
(608, 38, 'Nova Scotia', 'NS', 1),
(609, 38, 'Nunavut', 'NU', 1),
(610, 38, 'Ontario', 'ON', 1),
(611, 38, 'Prince Edward Island', 'PE', 1),
(612, 38, 'Qu&eacute;bec', 'QC', 1),
(613, 38, 'Saskatchewan', 'SK', 1),
(614, 38, 'Yukon Territory', 'YT', 1),
(615, 39, 'Boa Vista', 'BV', 1),
(616, 39, 'Brava', 'BR', 1),
(617, 39, 'Calheta de Sao Miguel', 'CS', 1),
(618, 39, 'Maio', 'MA', 1),
(619, 39, 'Mosteiros', 'MO', 1),
(620, 39, 'Paul', 'PA', 1),
(621, 39, 'Porto Novo', 'PN', 1),
(622, 39, 'Praia', 'PR', 1),
(623, 39, 'Ribeira Grande', 'RG', 1),
(624, 39, 'Sal', 'SL', 1),
(625, 39, 'Santa Catarina', 'CA', 1),
(626, 39, 'Santa Cruz', 'CR', 1),
(627, 39, 'Sao Domingos', 'SD', 1),
(628, 39, 'Sao Filipe', 'SF', 1),
(629, 39, 'Sao Nicolau', 'SN', 1),
(630, 39, 'Sao Vicente', 'SV', 1),
(631, 39, 'Tarrafal', 'TA', 1),
(632, 40, 'Creek', 'CR', 1),
(633, 40, 'Eastern', 'EA', 1),
(634, 40, 'Midland', 'ML', 1),
(635, 40, 'South Town', 'ST', 1),
(636, 40, 'Spot Bay', 'SP', 1),
(637, 40, 'Stake Bay', 'SK', 1),
(638, 40, 'West End', 'WD', 1),
(639, 40, 'Western', 'WN', 1),
(640, 41, 'Bamingui-Bangoran', 'BBA', 1),
(641, 41, 'Basse-Kotto', 'BKO', 1),
(642, 41, 'Haute-Kotto', 'HKO', 1),
(643, 41, 'Haut-Mbomou', 'HMB', 1),
(644, 41, 'Kemo', 'KEM', 1),
(645, 41, 'Lobaye', 'LOB', 1),
(646, 41, 'Mambere-KadeÔ', 'MKD', 1),
(647, 41, 'Mbomou', 'MBO', 1),
(648, 41, 'Nana-Mambere', 'NMM', 1),
(649, 41, 'Ombella-M\'Poko', 'OMP', 1),
(650, 41, 'Ouaka', 'OUK', 1),
(651, 41, 'Ouham', 'OUH', 1),
(652, 41, 'Ouham-Pende', 'OPE', 1),
(653, 41, 'Vakaga', 'VAK', 1),
(654, 41, 'Nana-Grebizi', 'NGR', 1),
(655, 41, 'Sangha-Mbaere', 'SMB', 1),
(656, 41, 'Bangui', 'BAN', 1),
(657, 42, 'Batha', 'BA', 1),
(658, 42, 'Biltine', 'BI', 1),
(659, 42, 'Borkou-Ennedi-Tibesti', 'BE', 1),
(660, 42, 'Chari-Baguirmi', 'CB', 1),
(661, 42, 'Guera', 'GU', 1),
(662, 42, 'Kanem', 'KA', 1),
(663, 42, 'Lac', 'LA', 1),
(664, 42, 'Logone Occidental', 'LC', 1),
(665, 42, 'Logone Oriental', 'LR', 1),
(666, 42, 'Mayo-Kebbi', 'MK', 1),
(667, 42, 'Moyen-Chari', 'MC', 1),
(668, 42, 'Ouaddai', 'OU', 1),
(669, 42, 'Salamat', 'SA', 1),
(670, 42, 'Tandjile', 'TA', 1),
(671, 43, 'Aisen del General Carlos Ibanez', 'AI', 1),
(672, 43, 'Antofagasta', 'AN', 1),
(673, 43, 'Araucania', 'AR', 1),
(674, 43, 'Atacama', 'AT', 1),
(675, 43, 'Bio-Bio', 'BI', 1),
(676, 43, 'Coquimbo', 'CO', 1),
(677, 43, 'Libertador General Bernardo O\'Higgins', 'LI', 1),
(678, 43, 'Los Lagos', 'LL', 1),
(679, 43, 'Magallanes y de la Antartica Chilena', 'MA', 1),
(680, 43, 'Maule', 'ML', 1),
(681, 43, 'Region Metropolitana', 'RM', 1),
(682, 43, 'Tarapaca', 'TA', 1),
(683, 43, 'Valparaiso', 'VS', 1),
(684, 44, 'Anhui', 'AN', 1),
(685, 44, 'Beijing', 'BE', 1),
(686, 44, 'Chongqing', 'CH', 1),
(687, 44, 'Fujian', 'FU', 1),
(688, 44, 'Gansu', 'GA', 1),
(689, 44, 'Guangdong', 'GU', 1),
(690, 44, 'Guangxi', 'GX', 1),
(691, 44, 'Guizhou', 'GZ', 1),
(692, 44, 'Hainan', 'HA', 1),
(693, 44, 'Hebei', 'HB', 1),
(694, 44, 'Heilongjiang', 'HL', 1),
(695, 44, 'Henan', 'HE', 1),
(696, 44, 'Hong Kong', 'HK', 1),
(697, 44, 'Hubei', 'HU', 1),
(698, 44, 'Hunan', 'HN', 1),
(699, 44, 'Inner Mongolia', 'IM', 1),
(700, 44, 'Jiangsu', 'JI', 1),
(701, 44, 'Jiangxi', 'JX', 1),
(702, 44, 'Jilin', 'JL', 1),
(703, 44, 'Liaoning', 'LI', 1),
(704, 44, 'Macau', 'MA', 1),
(705, 44, 'Ningxia', 'NI', 1),
(706, 44, 'Shaanxi', 'SH', 1),
(707, 44, 'Shandong', 'SA', 1),
(708, 44, 'Shanghai', 'SG', 1),
(709, 44, 'Shanxi', 'SX', 1),
(710, 44, 'Sichuan', 'SI', 1),
(711, 44, 'Tianjin', 'TI', 1),
(712, 44, 'Xinjiang', 'XI', 1),
(713, 44, 'Yunnan', 'YU', 1),
(714, 44, 'Zhejiang', 'ZH', 1),
(715, 46, 'Direction Island', 'D', 1),
(716, 46, 'Home Island', 'H', 1),
(717, 46, 'Horsburgh Island', 'O', 1),
(718, 46, 'South Island', 'S', 1),
(719, 46, 'West Island', 'W', 1),
(720, 47, 'Amazonas', 'AMZ', 1),
(721, 47, 'Antioquia', 'ANT', 1),
(722, 47, 'Arauca', 'ARA', 1),
(723, 47, 'Atlantico', 'ATL', 1),
(724, 47, 'Bogota D.C.', 'BDC', 1),
(725, 47, 'Bolivar', 'BOL', 1),
(726, 47, 'Boyaca', 'BOY', 1),
(727, 47, 'Caldas', 'CAL', 1),
(728, 47, 'Caqueta', 'CAQ', 1),
(729, 47, 'Casanare', 'CAS', 1),
(730, 47, 'Cauca', 'CAU', 1),
(731, 47, 'Cesar', 'CES', 1),
(732, 47, 'Choco', 'CHO', 1),
(733, 47, 'Cordoba', 'COR', 1),
(734, 47, 'Cundinamarca', 'CAM', 1),
(735, 47, 'Guainia', 'GNA', 1),
(736, 47, 'Guajira', 'GJR', 1),
(737, 47, 'Guaviare', 'GVR', 1),
(738, 47, 'Huila', 'HUI', 1),
(739, 47, 'Magdalena', 'MAG', 1),
(740, 47, 'Meta', 'MET', 1),
(741, 47, 'Narino', 'NAR', 1),
(742, 47, 'Norte de Santander', 'NDS', 1),
(743, 47, 'Putumayo', 'PUT', 1),
(744, 47, 'Quindio', 'QUI', 1),
(745, 47, 'Risaralda', 'RIS', 1),
(746, 47, 'San Andres y Providencia', 'SAP', 1),
(747, 47, 'Santander', 'SAN', 1),
(748, 47, 'Sucre', 'SUC', 1),
(749, 47, 'Tolima', 'TOL', 1),
(750, 47, 'Valle del Cauca', 'VDC', 1),
(751, 47, 'Vaupes', 'VAU', 1),
(752, 47, 'Vichada', 'VIC', 1),
(753, 48, 'Grande Comore', 'G', 1),
(754, 48, 'Anjouan', 'A', 1),
(755, 48, 'Moheli', 'M', 1),
(756, 49, 'Bouenza', 'BO', 1),
(757, 49, 'Brazzaville', 'BR', 1),
(758, 49, 'Cuvette', 'CU', 1),
(759, 49, 'Cuvette-Ouest', 'CO', 1),
(760, 49, 'Kouilou', 'KO', 1),
(761, 49, 'Lekoumou', 'LE', 1),
(762, 49, 'Likouala', 'LI', 1),
(763, 49, 'Niari', 'NI', 1),
(764, 49, 'Plateaux', 'PL', 1),
(765, 49, 'Pool', 'PO', 1),
(766, 49, 'Sangha', 'SA', 1),
(767, 50, 'Pukapuka', 'PU', 1),
(768, 50, 'Rakahanga', 'RK', 1),
(769, 50, 'Manihiki', 'MK', 1),
(770, 50, 'Penrhyn', 'PE', 1),
(771, 50, 'Nassau Island', 'NI', 1),
(772, 50, 'Surwarrow', 'SU', 1),
(773, 50, 'Palmerston', 'PA', 1),
(774, 50, 'Aitutaki', 'AI', 1),
(775, 50, 'Manuae', 'MA', 1),
(776, 50, 'Takutea', 'TA', 1),
(777, 50, 'Mitiaro', 'MT', 1),
(778, 50, 'Atiu', 'AT', 1),
(779, 50, 'Mauke', 'MU', 1),
(780, 50, 'Rarotonga', 'RR', 1),
(781, 50, 'Mangaia', 'MG', 1),
(782, 51, 'Alajuela', 'AL', 1),
(783, 51, 'Cartago', 'CA', 1),
(784, 51, 'Guanacaste', 'GU', 1),
(785, 51, 'Heredia', 'HE', 1),
(786, 51, 'Limon', 'LI', 1),
(787, 51, 'Puntarenas', 'PU', 1),
(788, 51, 'San Jose', 'SJ', 1),
(789, 52, 'Abengourou', 'ABE', 1),
(790, 52, 'Abidjan', 'ABI', 1),
(791, 52, 'Aboisso', 'ABO', 1),
(792, 52, 'Adiake', 'ADI', 1),
(793, 52, 'Adzope', 'ADZ', 1),
(794, 52, 'Agboville', 'AGB', 1),
(795, 52, 'Agnibilekrou', 'AGN', 1),
(796, 52, 'Alepe', 'ALE', 1),
(797, 52, 'Bocanda', 'BOC', 1),
(798, 52, 'Bangolo', 'BAN', 1),
(799, 52, 'Beoumi', 'BEO', 1),
(800, 52, 'Biankouma', 'BIA', 1),
(801, 52, 'Bondoukou', 'BDK', 1),
(802, 52, 'Bongouanou', 'BGN', 1),
(803, 52, 'Bouafle', 'BFL', 1),
(804, 52, 'Bouake', 'BKE', 1),
(805, 52, 'Bouna', 'BNA', 1),
(806, 52, 'Boundiali', 'BDL', 1),
(807, 52, 'Dabakala', 'DKL', 1),
(808, 52, 'Dabou', 'DBU', 1),
(809, 52, 'Daloa', 'DAL', 1),
(810, 52, 'Danane', 'DAN', 1),
(811, 52, 'Daoukro', 'DAO', 1),
(812, 52, 'Dimbokro', 'DIM', 1),
(813, 52, 'Divo', 'DIV', 1),
(814, 52, 'Duekoue', 'DUE', 1),
(815, 52, 'Ferkessedougou', 'FER', 1),
(816, 52, 'Gagnoa', 'GAG', 1),
(817, 52, 'Grand-Bassam', 'GBA', 1),
(818, 52, 'Grand-Lahou', 'GLA', 1),
(819, 52, 'Guiglo', 'GUI', 1),
(820, 52, 'Issia', 'ISS', 1),
(821, 52, 'Jacqueville', 'JAC', 1),
(822, 52, 'Katiola', 'KAT', 1),
(823, 52, 'Korhogo', 'KOR', 1),
(824, 52, 'Lakota', 'LAK', 1),
(825, 52, 'Man', 'MAN', 1),
(826, 52, 'Mankono', 'MKN', 1),
(827, 52, 'Mbahiakro', 'MBA', 1),
(828, 52, 'Odienne', 'ODI', 1),
(829, 52, 'Oume', 'OUM', 1),
(830, 52, 'Sakassou', 'SAK', 1),
(831, 52, 'San-Pedro', 'SPE', 1),
(832, 52, 'Sassandra', 'SAS', 1),
(833, 52, 'Seguela', 'SEG', 1),
(834, 52, 'Sinfra', 'SIN', 1),
(835, 52, 'Soubre', 'SOU', 1),
(836, 52, 'Tabou', 'TAB', 1),
(837, 52, 'Tanda', 'TAN', 1),
(838, 52, 'Tiebissou', 'TIE', 1),
(839, 52, 'Tingrela', 'TIN', 1),
(840, 52, 'Tiassale', 'TIA', 1),
(841, 52, 'Touba', 'TBA', 1),
(842, 52, 'Toulepleu', 'TLP', 1),
(843, 52, 'Toumodi', 'TMD', 1),
(844, 52, 'Vavoua', 'VAV', 1),
(845, 52, 'Yamoussoukro', 'YAM', 1),
(846, 52, 'Zuenoula', 'ZUE', 1),
(847, 53, 'Bjelovarsko-bilogorska', 'BB', 1),
(848, 53, 'Grad Zagreb', 'GZ', 1),
(849, 53, 'Dubrovačko-neretvanska', 'DN', 1),
(850, 53, 'Istarska', 'IS', 1),
(851, 53, 'Karlovačka', 'KA', 1),
(852, 53, 'Koprivničko-križevačka', 'KK', 1),
(853, 53, 'Krapinsko-zagorska', 'KZ', 1),
(854, 53, 'Ličko-senjska', 'LS', 1),
(855, 53, 'Međimurska', 'ME', 1),
(856, 53, 'Osječko-baranjska', 'OB', 1),
(857, 53, 'Požeško-slavonska', 'PS', 1),
(858, 53, 'Primorsko-goranska', 'PG', 1),
(859, 53, 'Šibensko-kninska', 'SK', 1),
(860, 53, 'Sisačko-moslavačka', 'SM', 1),
(861, 53, 'Brodsko-posavska', 'BP', 1),
(862, 53, 'Splitsko-dalmatinska', 'SD', 1),
(863, 53, 'Varaždinska', 'VA', 1),
(864, 53, 'Virovitičko-podravska', 'VP', 1),
(865, 53, 'Vukovarsko-srijemska', 'VS', 1),
(866, 53, 'Zadarska', 'ZA', 1),
(867, 53, 'Zagrebačka', 'ZG', 1),
(868, 54, 'Camaguey', 'CA', 1),
(869, 54, 'Ciego de Avila', 'CD', 1),
(870, 54, 'Cienfuegos', 'CI', 1),
(871, 54, 'Ciudad de La Habana', 'CH', 1),
(872, 54, 'Granma', 'GR', 1),
(873, 54, 'Guantanamo', 'GU', 1),
(874, 54, 'Holguin', 'HO', 1),
(875, 54, 'Isla de la Juventud', 'IJ', 1),
(876, 54, 'La Habana', 'LH', 1),
(877, 54, 'Las Tunas', 'LT', 1),
(878, 54, 'Matanzas', 'MA', 1),
(879, 54, 'Pinar del Rio', 'PR', 1),
(880, 54, 'Sancti Spiritus', 'SS', 1),
(881, 54, 'Santiago de Cuba', 'SC', 1),
(882, 54, 'Villa Clara', 'VC', 1),
(883, 55, 'Famagusta', 'F', 1),
(884, 55, 'Kyrenia', 'K', 1),
(885, 55, 'Larnaca', 'A', 1),
(886, 55, 'Limassol', 'I', 1),
(887, 55, 'Nicosia', 'N', 1),
(888, 55, 'Paphos', 'P', 1),
(889, 56, 'Ústecký', 'U', 1),
(890, 56, 'Jihočeský', 'C', 1),
(891, 56, 'Jihomoravský', 'B', 1),
(892, 56, 'Karlovarský', 'K', 1),
(893, 56, 'Královehradecký', 'H', 1),
(894, 56, 'Liberecký', 'L', 1),
(895, 56, 'Moravskoslezský', 'T', 1),
(896, 56, 'Olomoucký', 'M', 1),
(897, 56, 'Pardubický', 'E', 1),
(898, 56, 'Plzeňský', 'P', 1),
(899, 56, 'Praha', 'A', 1),
(900, 56, 'Středočeský', 'S', 1),
(901, 56, 'Vysočina', 'J', 1),
(902, 56, 'Zlínský', 'Z', 1),
(903, 57, 'Arhus', 'AR', 1),
(904, 57, 'Bornholm', 'BH', 1),
(905, 57, 'Copenhagen', 'CO', 1),
(906, 57, 'Faroe Islands', 'FO', 1),
(907, 57, 'Frederiksborg', 'FR', 1),
(908, 57, 'Fyn', 'FY', 1),
(909, 57, 'Kobenhavn', 'KO', 1),
(910, 57, 'Nordjylland', 'NO', 1),
(911, 57, 'Ribe', 'RI', 1),
(912, 57, 'Ringkobing', 'RK', 1),
(913, 57, 'Roskilde', 'RO', 1),
(914, 57, 'Sonderjylland', 'SO', 1),
(915, 57, 'Storstrom', 'ST', 1),
(916, 57, 'Vejle', 'VK', 1),
(917, 57, 'Vestj&aelig;lland', 'VJ', 1),
(918, 57, 'Viborg', 'VB', 1),
(919, 58, '\'Ali Sabih', 'S', 1),
(920, 58, 'Dikhil', 'K', 1),
(921, 58, 'Djibouti', 'J', 1),
(922, 58, 'Obock', 'O', 1),
(923, 58, 'Tadjoura', 'T', 1),
(924, 59, 'Saint Andrew Parish', 'AND', 1),
(925, 59, 'Saint David Parish', 'DAV', 1),
(926, 59, 'Saint George Parish', 'GEO', 1),
(927, 59, 'Saint John Parish', 'JOH', 1),
(928, 59, 'Saint Joseph Parish', 'JOS', 1),
(929, 59, 'Saint Luke Parish', 'LUK', 1),
(930, 59, 'Saint Mark Parish', 'MAR', 1),
(931, 59, 'Saint Patrick Parish', 'PAT', 1),
(932, 59, 'Saint Paul Parish', 'PAU', 1),
(933, 59, 'Saint Peter Parish', 'PET', 1),
(934, 60, 'Distrito Nacional', 'DN', 1),
(935, 60, 'Azua', 'AZ', 1),
(936, 60, 'Baoruco', 'BC', 1),
(937, 60, 'Barahona', 'BH', 1),
(938, 60, 'Dajabon', 'DJ', 1),
(939, 60, 'Duarte', 'DU', 1),
(940, 60, 'Elias Pina', 'EL', 1),
(941, 60, 'El Seybo', 'SY', 1),
(942, 60, 'Espaillat', 'ET', 1),
(943, 60, 'Hato Mayor', 'HM', 1),
(944, 60, 'Independencia', 'IN', 1),
(945, 60, 'La Altagracia', 'AL', 1),
(946, 60, 'La Romana', 'RO', 1),
(947, 60, 'La Vega', 'VE', 1),
(948, 60, 'Maria Trinidad Sanchez', 'MT', 1),
(949, 60, 'Monsenor Nouel', 'MN', 1),
(950, 60, 'Monte Cristi', 'MC', 1),
(951, 60, 'Monte Plata', 'MP', 1),
(952, 60, 'Pedernales', 'PD', 1),
(953, 60, 'Peravia (Bani)', 'PR', 1),
(954, 60, 'Puerto Plata', 'PP', 1),
(955, 60, 'Salcedo', 'SL', 1),
(956, 60, 'Samana', 'SM', 1),
(957, 60, 'Sanchez Ramirez', 'SH', 1),
(958, 60, 'San Cristobal', 'SC', 1),
(959, 60, 'San Jose de Ocoa', 'JO', 1),
(960, 60, 'San Juan', 'SJ', 1),
(961, 60, 'San Pedro de Macoris', 'PM', 1),
(962, 60, 'Santiago', 'SA', 1),
(963, 60, 'Santiago Rodriguez', 'ST', 1),
(964, 60, 'Santo Domingo', 'SD', 1),
(965, 60, 'Valverde', 'VA', 1),
(966, 61, 'Aileu', 'AL', 1),
(967, 61, 'Ainaro', 'AN', 1),
(968, 61, 'Baucau', 'BA', 1),
(969, 61, 'Bobonaro', 'BO', 1),
(970, 61, 'Cova Lima', 'CO', 1),
(971, 61, 'Dili', 'DI', 1),
(972, 61, 'Ermera', 'ER', 1),
(973, 61, 'Lautem', 'LA', 1),
(974, 61, 'Liquica', 'LI', 1),
(975, 61, 'Manatuto', 'MT', 1),
(976, 61, 'Manufahi', 'MF', 1),
(977, 61, 'Oecussi', 'OE', 1),
(978, 61, 'Viqueque', 'VI', 1),
(979, 62, 'Azuay', 'AZU', 1),
(980, 62, 'Bolivar', 'BOL', 1),
(981, 62, 'Ca&ntilde;ar', 'CAN', 1),
(982, 62, 'Carchi', 'CAR', 1),
(983, 62, 'Chimborazo', 'CHI', 1),
(984, 62, 'Cotopaxi', 'COT', 1),
(985, 62, 'El Oro', 'EOR', 1),
(986, 62, 'Esmeraldas', 'ESM', 1),
(987, 62, 'Gal&aacute;pagos', 'GPS', 1),
(988, 62, 'Guayas', 'GUA', 1),
(989, 62, 'Imbabura', 'IMB', 1),
(990, 62, 'Loja', 'LOJ', 1),
(991, 62, 'Los Rios', 'LRO', 1),
(992, 62, 'Manab&iacute;', 'MAN', 1),
(993, 62, 'Morona Santiago', 'MSA', 1),
(994, 62, 'Napo', 'NAP', 1),
(995, 62, 'Orellana', 'ORE', 1),
(996, 62, 'Pastaza', 'PAS', 1),
(997, 62, 'Pichincha', 'PIC', 1),
(998, 62, 'Sucumb&iacute;os', 'SUC', 1),
(999, 62, 'Tungurahua', 'TUN', 1),
(1000, 62, 'Zamora Chinchipe', 'ZCH', 1),
(1001, 63, 'Ad Daqahliyah', 'DHY', 1),
(1002, 63, 'Al Bahr al Ahmar', 'BAM', 1),
(1003, 63, 'Al Buhayrah', 'BHY', 1),
(1004, 63, 'Al Fayyum', 'FYM', 1),
(1005, 63, 'Al Gharbiyah', 'GBY', 1),
(1006, 63, 'Al Iskandariyah', 'IDR', 1),
(1007, 63, 'Al Isma\'iliyah', 'IML', 1),
(1008, 63, 'Al Jizah', 'JZH', 1),
(1009, 63, 'Al Minufiyah', 'MFY', 1),
(1010, 63, 'Al Minya', 'MNY', 1),
(1011, 63, 'Al Qahirah', 'QHR', 1),
(1012, 63, 'Al Qalyubiyah', 'QLY', 1),
(1013, 63, 'Al Wadi al Jadid', 'WJD', 1),
(1014, 63, 'Ash Sharqiyah', 'SHQ', 1),
(1015, 63, 'As Suways', 'SWY', 1),
(1016, 63, 'Aswan', 'ASW', 1),
(1017, 63, 'Asyut', 'ASY', 1),
(1018, 63, 'Bani Suwayf', 'BSW', 1),
(1019, 63, 'Bur Sa\'id', 'BSD', 1),
(1020, 63, 'Dumyat', 'DMY', 1),
(1021, 63, 'Janub Sina\'', 'JNS', 1),
(1022, 63, 'Kafr ash Shaykh', 'KSH', 1),
(1023, 63, 'Matruh', 'MAT', 1),
(1024, 63, 'Qina', 'QIN', 1),
(1025, 63, 'Shamal Sina\'', 'SHS', 1),
(1026, 63, 'Suhaj', 'SUH', 1),
(1027, 64, 'Ahuachapan', 'AH', 1),
(1028, 64, 'Cabanas', 'CA', 1),
(1029, 64, 'Chalatenango', 'CH', 1),
(1030, 64, 'Cuscatlan', 'CU', 1),
(1031, 64, 'La Libertad', 'LB', 1),
(1032, 64, 'La Paz', 'PZ', 1),
(1033, 64, 'La Union', 'UN', 1),
(1034, 64, 'Morazan', 'MO', 1),
(1035, 64, 'San Miguel', 'SM', 1),
(1036, 64, 'San Salvador', 'SS', 1),
(1037, 64, 'San Vicente', 'SV', 1),
(1038, 64, 'Santa Ana', 'SA', 1),
(1039, 64, 'Sonsonate', 'SO', 1),
(1040, 64, 'Usulutan', 'US', 1),
(1041, 65, 'Provincia Annobon', 'AN', 1),
(1042, 65, 'Provincia Bioko Norte', 'BN', 1),
(1043, 65, 'Provincia Bioko Sur', 'BS', 1),
(1044, 65, 'Provincia Centro Sur', 'CS', 1),
(1045, 65, 'Provincia Kie-Ntem', 'KN', 1),
(1046, 65, 'Provincia Litoral', 'LI', 1),
(1047, 65, 'Provincia Wele-Nzas', 'WN', 1),
(1048, 66, 'Central (Maekel)', 'MA', 1),
(1049, 66, 'Anseba (Keren)', 'KE', 1),
(1050, 66, 'Southern Red Sea (Debub-Keih-Bahri)', 'DK', 1),
(1051, 66, 'Northern Red Sea (Semien-Keih-Bahri)', 'SK', 1),
(1052, 66, 'Southern (Debub)', 'DE', 1),
(1053, 66, 'Gash-Barka (Barentu)', 'BR', 1),
(1054, 67, 'Harjumaa (Tallinn)', 'HA', 1),
(1055, 67, 'Hiiumaa (Kardla)', 'HI', 1),
(1056, 67, 'Ida-Virumaa (Johvi)', 'IV', 1),
(1057, 67, 'Jarvamaa (Paide)', 'JA', 1),
(1058, 67, 'Jogevamaa (Jogeva)', 'JO', 1),
(1059, 67, 'Laane-Virumaa (Rakvere)', 'LV', 1),
(1060, 67, 'Laanemaa (Haapsalu)', 'LA', 1),
(1061, 67, 'Parnumaa (Parnu)', 'PA', 1),
(1062, 67, 'Polvamaa (Polva)', 'PO', 1),
(1063, 67, 'Raplamaa (Rapla)', 'RA', 1),
(1064, 67, 'Saaremaa (Kuessaare)', 'SA', 1),
(1065, 67, 'Tartumaa (Tartu)', 'TA', 1),
(1066, 67, 'Valgamaa (Valga)', 'VA', 1),
(1067, 67, 'Viljandimaa (Viljandi)', 'VI', 1),
(1068, 67, 'Vorumaa (Voru)', 'VO', 1),
(1069, 68, 'Afar', 'AF', 1),
(1070, 68, 'Amhara', 'AH', 1),
(1071, 68, 'Benishangul-Gumaz', 'BG', 1),
(1072, 68, 'Gambela', 'GB', 1),
(1073, 68, 'Hariai', 'HR', 1),
(1074, 68, 'Oromia', 'OR', 1),
(1075, 68, 'Somali', 'SM', 1),
(1076, 68, 'Southern Nations - Nationalities and Peoples Region', 'SN', 1),
(1077, 68, 'Tigray', 'TG', 1),
(1078, 68, 'Addis Ababa', 'AA', 1),
(1079, 68, 'Dire Dawa', 'DD', 1),
(1080, 71, 'Central Division', 'C', 1),
(1081, 71, 'Northern Division', 'N', 1),
(1082, 71, 'Eastern Division', 'E', 1),
(1083, 71, 'Western Division', 'W', 1),
(1084, 71, 'Rotuma', 'R', 1),
(1085, 72, 'Ahvenanmaan lääni', 'AL', 1),
(1086, 72, 'Etelä-Suomen lääni', 'ES', 1),
(1087, 72, 'Itä-Suomen lääni', 'IS', 1),
(1088, 72, 'Länsi-Suomen lääni', 'LS', 1),
(1089, 72, 'Lapin lääni', 'LA', 1),
(1090, 72, 'Oulun lääni', 'OU', 1),
(1114, 74, 'Ain', '01', 1),
(1115, 74, 'Aisne', '02', 1),
(1116, 74, 'Allier', '03', 1),
(1117, 74, 'Alpes de Haute Provence', '04', 1),
(1118, 74, 'Hautes-Alpes', '05', 1),
(1119, 74, 'Alpes Maritimes', '06', 1),
(1120, 74, 'Ard&egrave;che', '07', 1),
(1121, 74, 'Ardennes', '08', 1),
(1122, 74, 'Ari&egrave;ge', '09', 1),
(1123, 74, 'Aube', '10', 1),
(1124, 74, 'Aude', '11', 1),
(1125, 74, 'Aveyron', '12', 1),
(1126, 74, 'Bouches du Rh&ocirc;ne', '13', 1),
(1127, 74, 'Calvados', '14', 1),
(1128, 74, 'Cantal', '15', 1),
(1129, 74, 'Charente', '16', 1),
(1130, 74, 'Charente Maritime', '17', 1),
(1131, 74, 'Cher', '18', 1),
(1132, 74, 'Corr&egrave;ze', '19', 1),
(1133, 74, 'Corse du Sud', '2A', 1),
(1134, 74, 'Haute Corse', '2B', 1),
(1135, 74, 'C&ocirc;te d&#039;or', '21', 1),
(1136, 74, 'C&ocirc;tes d&#039;Armor', '22', 1),
(1137, 74, 'Creuse', '23', 1),
(1138, 74, 'Dordogne', '24', 1),
(1139, 74, 'Doubs', '25', 1),
(1140, 74, 'Dr&ocirc;me', '26', 1),
(1141, 74, 'Eure', '27', 1),
(1142, 74, 'Eure et Loir', '28', 1),
(1143, 74, 'Finist&egrave;re', '29', 1),
(1144, 74, 'Gard', '30', 1),
(1145, 74, 'Haute Garonne', '31', 1),
(1146, 74, 'Gers', '32', 1),
(1147, 74, 'Gironde', '33', 1),
(1148, 74, 'H&eacute;rault', '34', 1),
(1149, 74, 'Ille et Vilaine', '35', 1),
(1150, 74, 'Indre', '36', 1),
(1151, 74, 'Indre et Loire', '37', 1),
(1152, 74, 'Is&eacute;re', '38', 1),
(1153, 74, 'Jura', '39', 1),
(1154, 74, 'Landes', '40', 1),
(1155, 74, 'Loir et Cher', '41', 1),
(1156, 74, 'Loire', '42', 1),
(1157, 74, 'Haute Loire', '43', 1),
(1158, 74, 'Loire Atlantique', '44', 1),
(1159, 74, 'Loiret', '45', 1),
(1160, 74, 'Lot', '46', 1),
(1161, 74, 'Lot et Garonne', '47', 1),
(1162, 74, 'Loz&egrave;re', '48', 1),
(1163, 74, 'Maine et Loire', '49', 1),
(1164, 74, 'Manche', '50', 1),
(1165, 74, 'Marne', '51', 1),
(1166, 74, 'Haute Marne', '52', 1),
(1167, 74, 'Mayenne', '53', 1),
(1168, 74, 'Meurthe et Moselle', '54', 1),
(1169, 74, 'Meuse', '55', 1),
(1170, 74, 'Morbihan', '56', 1),
(1171, 74, 'Moselle', '57', 1),
(1172, 74, 'Ni&egrave;vre', '58', 1),
(1173, 74, 'Nord', '59', 1),
(1174, 74, 'Oise', '60', 1),
(1175, 74, 'Orne', '61', 1),
(1176, 74, 'Pas de Calais', '62', 1),
(1177, 74, 'Puy de D&ocirc;me', '63', 1),
(1178, 74, 'Pyr&eacute;n&eacute;es Atlantiques', '64', 1),
(1179, 74, 'Hautes Pyr&eacute;n&eacute;es', '65', 1),
(1180, 74, 'Pyr&eacute;n&eacute;es Orientales', '66', 1),
(1181, 74, 'Bas Rhin', '67', 1),
(1182, 74, 'Haut Rhin', '68', 1),
(1183, 74, 'Rh&ocirc;ne', '69', 1),
(1184, 74, 'Haute Sa&ocirc;ne', '70', 1),
(1185, 74, 'Sa&ocirc;ne et Loire', '71', 1),
(1186, 74, 'Sarthe', '72', 1),
(1187, 74, 'Savoie', '73', 1),
(1188, 74, 'Haute Savoie', '74', 1),
(1189, 74, 'Paris', '75', 1),
(1190, 74, 'Seine Maritime', '76', 1),
(1191, 74, 'Seine et Marne', '77', 1),
(1192, 74, 'Yvelines', '78', 1),
(1193, 74, 'Deux S&egrave;vres', '79', 1),
(1194, 74, 'Somme', '80', 1),
(1195, 74, 'Tarn', '81', 1),
(1196, 74, 'Tarn et Garonne', '82', 1),
(1197, 74, 'Var', '83', 1),
(1198, 74, 'Vaucluse', '84', 1),
(1199, 74, 'Vend&eacute;e', '85', 1),
(1200, 74, 'Vienne', '86', 1),
(1201, 74, 'Haute Vienne', '87', 1),
(1202, 74, 'Vosges', '88', 1),
(1203, 74, 'Yonne', '89', 1),
(1204, 74, 'Territoire de Belfort', '90', 1),
(1205, 74, 'Essonne', '91', 1),
(1206, 74, 'Hauts de Seine', '92', 1),
(1207, 74, 'Seine St-Denis', '93', 1),
(1208, 74, 'Val de Marne', '94', 1),
(1209, 74, 'Val d\'Oise', '95', 1),
(1210, 76, 'Archipel des Marquises', 'M', 1),
(1211, 76, 'Archipel des Tuamotu', 'T', 1),
(1212, 76, 'Archipel des Tubuai', 'I', 1),
(1213, 76, 'Iles du Vent', 'V', 1),
(1214, 76, 'Iles Sous-le-Vent', 'S', 1),
(1215, 77, 'Iles Crozet', 'C', 1),
(1216, 77, 'Iles Kerguelen', 'K', 1),
(1217, 77, 'Ile Amsterdam', 'A', 1),
(1218, 77, 'Ile Saint-Paul', 'P', 1),
(1219, 77, 'Adelie Land', 'D', 1),
(1220, 78, 'Estuaire', 'ES', 1),
(1221, 78, 'Haut-Ogooue', 'HO', 1),
(1222, 78, 'Moyen-Ogooue', 'MO', 1),
(1223, 78, 'Ngounie', 'NG', 1),
(1224, 78, 'Nyanga', 'NY', 1),
(1225, 78, 'Ogooue-Ivindo', 'OI', 1),
(1226, 78, 'Ogooue-Lolo', 'OL', 1),
(1227, 78, 'Ogooue-Maritime', 'OM', 1),
(1228, 78, 'Woleu-Ntem', 'WN', 1),
(1229, 79, 'Banjul', 'BJ', 1),
(1230, 79, 'Basse', 'BS', 1),
(1231, 79, 'Brikama', 'BR', 1),
(1232, 79, 'Janjangbure', 'JA', 1),
(1233, 79, 'Kanifeng', 'KA', 1),
(1234, 79, 'Kerewan', 'KE', 1),
(1235, 79, 'Kuntaur', 'KU', 1),
(1236, 79, 'Mansakonko', 'MA', 1),
(1237, 79, 'Lower River', 'LR', 1),
(1238, 79, 'Central River', 'CR', 1),
(1239, 79, 'North Bank', 'NB', 1),
(1240, 79, 'Upper River', 'UR', 1),
(1241, 79, 'Western', 'WE', 1),
(1242, 80, 'Abkhazia', 'AB', 1),
(1243, 80, 'Ajaria', 'AJ', 1),
(1244, 80, 'Tbilisi', 'TB', 1),
(1245, 80, 'Guria', 'GU', 1),
(1246, 80, 'Imereti', 'IM', 1),
(1247, 80, 'Kakheti', 'KA', 1),
(1248, 80, 'Kvemo Kartli', 'KK', 1),
(1249, 80, 'Mtskheta-Mtianeti', 'MM', 1),
(1250, 80, 'Racha Lechkhumi and Kvemo Svanet', 'RL', 1),
(1251, 80, 'Samegrelo-Zemo Svaneti', 'SZ', 1),
(1252, 80, 'Samtskhe-Javakheti', 'SJ', 1),
(1253, 80, 'Shida Kartli', 'SK', 1),
(1254, 81, 'Baden-Württemberg', 'BAW', 1),
(1255, 81, 'Bayern', 'BAY', 1),
(1256, 81, 'Berlin', 'BER', 1),
(1257, 81, 'Brandenburg', 'BRG', 1),
(1258, 81, 'Bremen', 'BRE', 1),
(1259, 81, 'Hamburg', 'HAM', 1),
(1260, 81, 'Hessen', 'HES', 1),
(1261, 81, 'Mecklenburg-Vorpommern', 'MEC', 1),
(1262, 81, 'Niedersachsen', 'NDS', 1),
(1263, 81, 'Nordrhein-Westfalen', 'NRW', 1),
(1264, 81, 'Rheinland-Pfalz', 'RHE', 1),
(1265, 81, 'Saarland', 'SAR', 1),
(1266, 81, 'Sachsen', 'SAS', 1),
(1267, 81, 'Sachsen-Anhalt', 'SAC', 1),
(1268, 81, 'Schleswig-Holstein', 'SCN', 1),
(1269, 81, 'Thüringen', 'THE', 1),
(1270, 82, 'Ashanti Region', 'AS', 1),
(1271, 82, 'Brong-Ahafo Region', 'BA', 1),
(1272, 82, 'Central Region', 'CE', 1),
(1273, 82, 'Eastern Region', 'EA', 1),
(1274, 82, 'Greater Accra Region', 'GA', 1),
(1275, 82, 'Northern Region', 'NO', 1),
(1276, 82, 'Upper East Region', 'UE', 1),
(1277, 82, 'Upper West Region', 'UW', 1),
(1278, 82, 'Volta Region', 'VO', 1),
(1279, 82, 'Western Region', 'WE', 1),
(1280, 84, 'Attica', 'AT', 1),
(1281, 84, 'Central Greece', 'CN', 1),
(1282, 84, 'Central Macedonia', 'CM', 1),
(1283, 84, 'Crete', 'CR', 1),
(1284, 84, 'East Macedonia and Thrace', 'EM', 1),
(1285, 84, 'Epirus', 'EP', 1),
(1286, 84, 'Ionian Islands', 'II', 1),
(1287, 84, 'North Aegean', 'NA', 1),
(1288, 84, 'Peloponnesos', 'PP', 1),
(1289, 84, 'South Aegean', 'SA', 1),
(1290, 84, 'Thessaly', 'TH', 1),
(1291, 84, 'West Greece', 'WG', 1),
(1292, 84, 'West Macedonia', 'WM', 1),
(1293, 85, 'Avannaa', 'A', 1),
(1294, 85, 'Tunu', 'T', 1),
(1295, 85, 'Kitaa', 'K', 1),
(1296, 86, 'Saint Andrew', 'A', 1),
(1297, 86, 'Saint David', 'D', 1),
(1298, 86, 'Saint George', 'G', 1),
(1299, 86, 'Saint John', 'J', 1),
(1300, 86, 'Saint Mark', 'M', 1),
(1301, 86, 'Saint Patrick', 'P', 1),
(1302, 86, 'Carriacou', 'C', 1),
(1303, 86, 'Petit Martinique', 'Q', 1),
(1304, 89, 'Alta Verapaz', 'AV', 1),
(1305, 89, 'Baja Verapaz', 'BV', 1),
(1306, 89, 'Chimaltenango', 'CM', 1),
(1307, 89, 'Chiquimula', 'CQ', 1),
(1308, 89, 'El Peten', 'PE', 1),
(1309, 89, 'El Progreso', 'PR', 1),
(1310, 89, 'El Quiche', 'QC', 1),
(1311, 89, 'Escuintla', 'ES', 1),
(1312, 89, 'Guatemala', 'GU', 1),
(1313, 89, 'Huehuetenango', 'HU', 1),
(1314, 89, 'Izabal', 'IZ', 1),
(1315, 89, 'Jalapa', 'JA', 1),
(1316, 89, 'Jutiapa', 'JU', 1),
(1317, 89, 'Quetzaltenango', 'QZ', 1),
(1318, 89, 'Retalhuleu', 'RE', 1),
(1319, 89, 'Sacatepequez', 'ST', 1),
(1320, 89, 'San Marcos', 'SM', 1),
(1321, 89, 'Santa Rosa', 'SR', 1),
(1322, 89, 'Solola', 'SO', 1),
(1323, 89, 'Suchitepequez', 'SU', 1),
(1324, 89, 'Totonicapan', 'TO', 1),
(1325, 89, 'Zacapa', 'ZA', 1),
(1326, 90, 'Conakry', 'CNK', 1),
(1327, 90, 'Beyla', 'BYL', 1),
(1328, 90, 'Boffa', 'BFA', 1),
(1329, 90, 'Boke', 'BOK', 1),
(1330, 90, 'Coyah', 'COY', 1),
(1331, 90, 'Dabola', 'DBL', 1),
(1332, 90, 'Dalaba', 'DLB', 1),
(1333, 90, 'Dinguiraye', 'DGR', 1),
(1334, 90, 'Dubreka', 'DBR', 1),
(1335, 90, 'Faranah', 'FRN', 1),
(1336, 90, 'Forecariah', 'FRC', 1),
(1337, 90, 'Fria', 'FRI', 1),
(1338, 90, 'Gaoual', 'GAO', 1),
(1339, 90, 'Gueckedou', 'GCD', 1),
(1340, 90, 'Kankan', 'KNK', 1),
(1341, 90, 'Kerouane', 'KRN', 1),
(1342, 90, 'Kindia', 'KND', 1),
(1343, 90, 'Kissidougou', 'KSD', 1),
(1344, 90, 'Koubia', 'KBA', 1),
(1345, 90, 'Koundara', 'KDA', 1),
(1346, 90, 'Kouroussa', 'KRA', 1),
(1347, 90, 'Labe', 'LAB', 1),
(1348, 90, 'Lelouma', 'LLM', 1),
(1349, 90, 'Lola', 'LOL', 1),
(1350, 90, 'Macenta', 'MCT', 1),
(1351, 90, 'Mali', 'MAL', 1),
(1352, 90, 'Mamou', 'MAM', 1),
(1353, 90, 'Mandiana', 'MAN', 1),
(1354, 90, 'Nzerekore', 'NZR', 1),
(1355, 90, 'Pita', 'PIT', 1),
(1356, 90, 'Siguiri', 'SIG', 1),
(1357, 90, 'Telimele', 'TLM', 1),
(1358, 90, 'Tougue', 'TOG', 1),
(1359, 90, 'Yomou', 'YOM', 1),
(1360, 91, 'Bafata Region', 'BF', 1),
(1361, 91, 'Biombo Region', 'BB', 1),
(1362, 91, 'Bissau Region', 'BS', 1),
(1363, 91, 'Bolama Region', 'BL', 1),
(1364, 91, 'Cacheu Region', 'CA', 1),
(1365, 91, 'Gabu Region', 'GA', 1),
(1366, 91, 'Oio Region', 'OI', 1),
(1367, 91, 'Quinara Region', 'QU', 1),
(1368, 91, 'Tombali Region', 'TO', 1),
(1369, 92, 'Barima-Waini', 'BW', 1),
(1370, 92, 'Cuyuni-Mazaruni', 'CM', 1),
(1371, 92, 'Demerara-Mahaica', 'DM', 1),
(1372, 92, 'East Berbice-Corentyne', 'EC', 1),
(1373, 92, 'Essequibo Islands-West Demerara', 'EW', 1),
(1374, 92, 'Mahaica-Berbice', 'MB', 1),
(1375, 92, 'Pomeroon-Supenaam', 'PM', 1),
(1376, 92, 'Potaro-Siparuni', 'PI', 1),
(1377, 92, 'Upper Demerara-Berbice', 'UD', 1),
(1378, 92, 'Upper Takutu-Upper Essequibo', 'UT', 1),
(1379, 93, 'Artibonite', 'AR', 1),
(1380, 93, 'Centre', 'CE', 1),
(1381, 93, 'Grand\'Anse', 'GA', 1),
(1382, 93, 'Nord', 'ND', 1),
(1383, 93, 'Nord-Est', 'NE', 1),
(1384, 93, 'Nord-Ouest', 'NO', 1),
(1385, 93, 'Ouest', 'OU', 1),
(1386, 93, 'Sud', 'SD', 1),
(1387, 93, 'Sud-Est', 'SE', 1),
(1388, 94, 'Flat Island', 'F', 1),
(1389, 94, 'McDonald Island', 'M', 1),
(1390, 94, 'Shag Island', 'S', 1),
(1391, 94, 'Heard Island', 'H', 1),
(1392, 95, 'Atlantida', 'AT', 1),
(1393, 95, 'Choluteca', 'CH', 1),
(1394, 95, 'Colon', 'CL', 1),
(1395, 95, 'Comayagua', 'CM', 1),
(1396, 95, 'Copan', 'CP', 1),
(1397, 95, 'Cortes', 'CR', 1),
(1398, 95, 'El Paraiso', 'PA', 1),
(1399, 95, 'Francisco Morazan', 'FM', 1),
(1400, 95, 'Gracias a Dios', 'GD', 1),
(1401, 95, 'Intibuca', 'IN', 1),
(1402, 95, 'Islas de la Bahia (Bay Islands)', 'IB', 1),
(1403, 95, 'La Paz', 'PZ', 1),
(1404, 95, 'Lempira', 'LE', 1),
(1405, 95, 'Ocotepeque', 'OC', 1),
(1406, 95, 'Olancho', 'OL', 1),
(1407, 95, 'Santa Barbara', 'SB', 1),
(1408, 95, 'Valle', 'VA', 1),
(1409, 95, 'Yoro', 'YO', 1),
(1410, 96, 'Central and Western Hong Kong Island', 'HCW', 1),
(1411, 96, 'Eastern Hong Kong Island', 'HEA', 1),
(1412, 96, 'Southern Hong Kong Island', 'HSO', 1),
(1413, 96, 'Wan Chai Hong Kong Island', 'HWC', 1),
(1414, 96, 'Kowloon City Kowloon', 'KKC', 1),
(1415, 96, 'Kwun Tong Kowloon', 'KKT', 1),
(1416, 96, 'Sham Shui Po Kowloon', 'KSS', 1),
(1417, 96, 'Wong Tai Sin Kowloon', 'KWT', 1),
(1418, 96, 'Yau Tsim Mong Kowloon', 'KYT', 1),
(1419, 96, 'Islands New Territories', 'NIS', 1),
(1420, 96, 'Kwai Tsing New Territories', 'NKT', 1),
(1421, 96, 'North New Territories', 'NNO', 1),
(1422, 96, 'Sai Kung New Territories', 'NSK', 1),
(1423, 96, 'Sha Tin New Territories', 'NST', 1),
(1424, 96, 'Tai Po New Territories', 'NTP', 1),
(1425, 96, 'Tsuen Wan New Territories', 'NTW', 1),
(1426, 96, 'Tuen Mun New Territories', 'NTM', 1),
(1427, 96, 'Yuen Long New Territories', 'NYL', 1),
(1467, 98, 'Austurland', 'AL', 1),
(1468, 98, 'Hofuoborgarsvaeoi', 'HF', 1),
(1469, 98, 'Norourland eystra', 'NE', 1),
(1470, 98, 'Norourland vestra', 'NV', 1),
(1471, 98, 'Suourland', 'SL', 1),
(1472, 98, 'Suournes', 'SN', 1),
(1473, 98, 'Vestfiroir', 'VF', 1),
(1474, 98, 'Vesturland', 'VL', 1),
(1475, 99, 'Andaman and Nicobar Islands', 'AN', 1),
(1476, 99, 'Andhra Pradesh', 'AP', 1),
(1477, 99, 'Arunachal Pradesh', 'AR', 1),
(1478, 99, 'Assam', 'AS', 1),
(1479, 99, 'Bihar', 'BI', 1),
(1480, 99, 'Chandigarh', 'CH', 1),
(1481, 99, 'Dadra and Nagar Haveli', 'DA', 1),
(1482, 99, 'Daman and Diu', 'DM', 1),
(1483, 99, 'Delhi', 'DE', 1),
(1484, 99, 'Goa', 'GO', 1),
(1485, 99, 'Gujarat', 'GU', 1),
(1486, 99, 'Haryana', 'HA', 1),
(1487, 99, 'Himachal Pradesh', 'HP', 1),
(1488, 99, 'Jammu and Kashmir', 'JA', 1),
(1489, 99, 'Karnataka', 'KA', 1),
(1490, 99, 'Kerala', 'KE', 1),
(1491, 99, 'Lakshadweep Islands', 'LI', 1),
(1492, 99, 'Madhya Pradesh', 'MP', 1),
(1493, 99, 'Maharashtra', 'MA', 1),
(1494, 99, 'Manipur', 'MN', 1),
(1495, 99, 'Meghalaya', 'ME', 1),
(1496, 99, 'Mizoram', 'MI', 1),
(1497, 99, 'Nagaland', 'NA', 1),
(1498, 99, 'Orissa', 'OR', 1),
(1499, 99, 'Puducherry', 'PO', 1),
(1500, 99, 'Punjab', 'PU', 1),
(1501, 99, 'Rajasthan', 'RA', 1),
(1502, 99, 'Sikkim', 'SI', 1),
(1503, 99, 'Tamil Nadu', 'TN', 1),
(1504, 99, 'Tripura', 'TR', 1),
(1505, 99, 'Uttar Pradesh', 'UP', 1),
(1506, 99, 'West Bengal', 'WB', 1),
(1507, 100, 'Aceh', 'AC', 1),
(1508, 100, 'Bali', 'BA', 1),
(1509, 100, 'Banten', 'BT', 1),
(1510, 100, 'Bengkulu', 'BE', 1),
(1511, 100, 'Kalimantan Utara', 'BD', 1),
(1512, 100, 'Gorontalo', 'GO', 1),
(1513, 100, 'Jakarta', 'JK', 1),
(1514, 100, 'Jambi', 'JA', 1),
(1515, 100, 'Jawa Barat', 'JB', 1),
(1516, 100, 'Jawa Tengah', 'JT', 1),
(1517, 100, 'Jawa Timur', 'JI', 1),
(1518, 100, 'Kalimantan Barat', 'KB', 1),
(1519, 100, 'Kalimantan Selatan', 'KS', 1),
(1520, 100, 'Kalimantan Tengah', 'KT', 1),
(1521, 100, 'Kalimantan Timur', 'KI', 1),
(1522, 100, 'Kepulauan Bangka Belitung', 'BB', 1),
(1523, 100, 'Lampung', 'LA', 1),
(1524, 100, 'Maluku', 'MA', 1),
(1525, 100, 'Maluku Utara', 'MU', 1),
(1526, 100, 'Nusa Tenggara Barat', 'NB', 1),
(1527, 100, 'Nusa Tenggara Timur', 'NT', 1),
(1528, 100, 'Papua', 'PA', 1),
(1529, 100, 'Riau', 'RI', 1),
(1530, 100, 'Sulawesi Selatan', 'SN', 1),
(1531, 100, 'Sulawesi Tengah', 'ST', 1),
(1532, 100, 'Sulawesi Tenggara', 'SG', 1),
(1533, 100, 'Sulawesi Utara', 'SA', 1),
(1534, 100, 'Sumatera Barat', 'SB', 1),
(1535, 100, 'Sumatera Selatan', 'SS', 1),
(1536, 100, 'Sumatera Utara', 'SU', 1),
(1537, 100, 'Yogyakarta', 'YO', 1),
(1538, 101, 'Tehran', 'TEH', 1),
(1539, 101, 'Qom', 'QOM', 1),
(1540, 101, 'Markazi', 'MKZ', 1),
(1541, 101, 'Qazvin', 'QAZ', 1),
(1542, 101, 'Gilan', 'GIL', 1),
(1543, 101, 'Ardabil', 'ARD', 1),
(1544, 101, 'Zanjan', 'ZAN', 1),
(1545, 101, 'East Azarbaijan', 'EAZ', 1),
(1546, 101, 'West Azarbaijan', 'WEZ', 1),
(1547, 101, 'Kurdistan', 'KRD', 1),
(1548, 101, 'Hamadan', 'HMD', 1),
(1549, 101, 'Kermanshah', 'KRM', 1),
(1550, 101, 'Ilam', 'ILM', 1),
(1551, 101, 'Lorestan', 'LRS', 1),
(1552, 101, 'Khuzestan', 'KZT', 1),
(1553, 101, 'Chahar Mahaal and Bakhtiari', 'CMB', 1),
(1554, 101, 'Kohkiluyeh and Buyer Ahmad', 'KBA', 1),
(1555, 101, 'Bushehr', 'BSH', 1),
(1556, 101, 'Fars', 'FAR', 1),
(1557, 101, 'Hormozgan', 'HRM', 1),
(1558, 101, 'Sistan and Baluchistan', 'SBL', 1),
(1559, 101, 'Kerman', 'KRB', 1),
(1560, 101, 'Yazd', 'YZD', 1),
(1561, 101, 'Esfahan', 'EFH', 1),
(1562, 101, 'Semnan', 'SMN', 1),
(1563, 101, 'Mazandaran', 'MZD', 1),
(1564, 101, 'Golestan', 'GLS', 1),
(1565, 101, 'North Khorasan', 'NKH', 1),
(1566, 101, 'Razavi Khorasan', 'RKH', 1),
(1567, 101, 'South Khorasan', 'SKH', 1),
(1568, 102, 'Baghdad', 'BD', 1),
(1569, 102, 'Salah ad Din', 'SD', 1),
(1570, 102, 'Diyala', 'DY', 1),
(1571, 102, 'Wasit', 'WS', 1),
(1572, 102, 'Maysan', 'MY', 1),
(1573, 102, 'Al Basrah', 'BA', 1),
(1574, 102, 'Dhi Qar', 'DQ', 1),
(1575, 102, 'Al Muthanna', 'MU', 1),
(1576, 102, 'Al Qadisyah', 'QA', 1),
(1577, 102, 'Babil', 'BB', 1),
(1578, 102, 'Al Karbala', 'KB', 1),
(1579, 102, 'An Najaf', 'NJ', 1),
(1580, 102, 'Al Anbar', 'AB', 1),
(1581, 102, 'Ninawa', 'NN', 1),
(1582, 102, 'Dahuk', 'DH', 1),
(1583, 102, 'Arbil', 'AL', 1),
(1584, 102, 'At Ta\'mim', 'TM', 1),
(1585, 102, 'As Sulaymaniyah', 'SL', 1),
(1586, 103, 'Carlow', 'CA', 1),
(1587, 103, 'Cavan', 'CV', 1),
(1588, 103, 'Clare', 'CL', 1),
(1589, 103, 'Cork', 'CO', 1),
(1590, 103, 'Donegal', 'DO', 1),
(1591, 103, 'Dublin', 'DU', 1),
(1592, 103, 'Galway', 'GA', 1),
(1593, 103, 'Kerry', 'KE', 1),
(1594, 103, 'Kildare', 'KI', 1),
(1595, 103, 'Kilkenny', 'KL', 1),
(1596, 103, 'Laois', 'LA', 1),
(1597, 103, 'Leitrim', 'LE', 1);
INSERT INTO `ocn8_zone` (`zone_id`, `country_id`, `name`, `code`, `status`) VALUES
(1598, 103, 'Limerick', 'LI', 1),
(1599, 103, 'Longford', 'LO', 1),
(1600, 103, 'Louth', 'LU', 1),
(1601, 103, 'Mayo', 'MA', 1),
(1602, 103, 'Meath', 'ME', 1),
(1603, 103, 'Monaghan', 'MO', 1),
(1604, 103, 'Offaly', 'OF', 1),
(1605, 103, 'Roscommon', 'RO', 1),
(1606, 103, 'Sligo', 'SL', 1),
(1607, 103, 'Tipperary', 'TI', 1),
(1608, 103, 'Waterford', 'WA', 1),
(1609, 103, 'Westmeath', 'WE', 1),
(1610, 103, 'Wexford', 'WX', 1),
(1611, 103, 'Wicklow', 'WI', 1),
(1612, 104, 'Be\'er Sheva', 'BS', 1),
(1613, 104, 'Bika\'at Hayarden', 'BH', 1),
(1614, 104, 'Eilat and Arava', 'EA', 1),
(1615, 104, 'Galil', 'GA', 1),
(1616, 104, 'Haifa', 'HA', 1),
(1617, 104, 'Jehuda Mountains', 'JM', 1),
(1618, 104, 'Jerusalem', 'JE', 1),
(1619, 104, 'Negev', 'NE', 1),
(1620, 104, 'Semaria', 'SE', 1),
(1621, 104, 'Sharon', 'SH', 1),
(1622, 104, 'Tel Aviv (Gosh Dan)', 'TA', 1),
(3860, 105, 'Caltanissetta', 'CL', 1),
(3842, 105, 'Agrigento', 'AG', 1),
(3843, 105, 'Alessandria', 'AL', 1),
(3844, 105, 'Ancona', 'AN', 1),
(3845, 105, 'Aosta', 'AO', 1),
(3846, 105, 'Arezzo', 'AR', 1),
(3847, 105, 'Ascoli Piceno', 'AP', 1),
(3848, 105, 'Asti', 'AT', 1),
(3849, 105, 'Avellino', 'AV', 1),
(3850, 105, 'Bari', 'BA', 1),
(3851, 105, 'Belluno', 'BL', 1),
(3852, 105, 'Benevento', 'BN', 1),
(3853, 105, 'Bergamo', 'BG', 1),
(3854, 105, 'Biella', 'BI', 1),
(3855, 105, 'Bologna', 'BO', 1),
(3856, 105, 'Bolzano', 'BZ', 1),
(3857, 105, 'Brescia', 'BS', 1),
(3858, 105, 'Brindisi', 'BR', 1),
(3859, 105, 'Cagliari', 'CA', 1),
(1643, 106, 'Clarendon Parish', 'CLA', 1),
(1644, 106, 'Hanover Parish', 'HAN', 1),
(1645, 106, 'Kingston Parish', 'KIN', 1),
(1646, 106, 'Manchester Parish', 'MAN', 1),
(1647, 106, 'Portland Parish', 'POR', 1),
(1648, 106, 'Saint Andrew Parish', 'AND', 1),
(1649, 106, 'Saint Ann Parish', 'ANN', 1),
(1650, 106, 'Saint Catherine Parish', 'CAT', 1),
(1651, 106, 'Saint Elizabeth Parish', 'ELI', 1),
(1652, 106, 'Saint James Parish', 'JAM', 1),
(1653, 106, 'Saint Mary Parish', 'MAR', 1),
(1654, 106, 'Saint Thomas Parish', 'THO', 1),
(1655, 106, 'Trelawny Parish', 'TRL', 1),
(1656, 106, 'Westmoreland Parish', 'WML', 1),
(1657, 107, 'Aichi', 'AI', 1),
(1658, 107, 'Akita', 'AK', 1),
(1659, 107, 'Aomori', 'AO', 1),
(1660, 107, 'Chiba', 'CH', 1),
(1661, 107, 'Ehime', 'EH', 1),
(1662, 107, 'Fukui', 'FK', 1),
(1663, 107, 'Fukuoka', 'FU', 1),
(1664, 107, 'Fukushima', 'FS', 1),
(1665, 107, 'Gifu', 'GI', 1),
(1666, 107, 'Gumma', 'GU', 1),
(1667, 107, 'Hiroshima', 'HI', 1),
(1668, 107, 'Hokkaido', 'HO', 1),
(1669, 107, 'Hyogo', 'HY', 1),
(1670, 107, 'Ibaraki', 'IB', 1),
(1671, 107, 'Ishikawa', 'IS', 1),
(1672, 107, 'Iwate', 'IW', 1),
(1673, 107, 'Kagawa', 'KA', 1),
(1674, 107, 'Kagoshima', 'KG', 1),
(1675, 107, 'Kanagawa', 'KN', 1),
(1676, 107, 'Kochi', 'KO', 1),
(1677, 107, 'Kumamoto', 'KU', 1),
(1678, 107, 'Kyoto', 'KY', 1),
(1679, 107, 'Mie', 'MI', 1),
(1680, 107, 'Miyagi', 'MY', 1),
(1681, 107, 'Miyazaki', 'MZ', 1),
(1682, 107, 'Nagano', 'NA', 1),
(1683, 107, 'Nagasaki', 'NG', 1),
(1684, 107, 'Nara', 'NR', 1),
(1685, 107, 'Niigata', 'NI', 1),
(1686, 107, 'Oita', 'OI', 1),
(1687, 107, 'Okayama', 'OK', 1),
(1688, 107, 'Okinawa', 'ON', 1),
(1689, 107, 'Osaka', 'OS', 1),
(1690, 107, 'Saga', 'SA', 1),
(1691, 107, 'Saitama', 'SI', 1),
(1692, 107, 'Shiga', 'SH', 1),
(1693, 107, 'Shimane', 'SM', 1),
(1694, 107, 'Shizuoka', 'SZ', 1),
(1695, 107, 'Tochigi', 'TO', 1),
(1696, 107, 'Tokushima', 'TS', 1),
(1697, 107, 'Tokyo', 'TK', 1),
(1698, 107, 'Tottori', 'TT', 1),
(1699, 107, 'Toyama', 'TY', 1),
(1700, 107, 'Wakayama', 'WA', 1),
(1701, 107, 'Yamagata', 'YA', 1),
(1702, 107, 'Yamaguchi', 'YM', 1),
(1703, 107, 'Yamanashi', 'YN', 1),
(1704, 108, '\'Amman', 'AM', 1),
(1705, 108, 'Ajlun', 'AJ', 1),
(1706, 108, 'Al \'Aqabah', 'AA', 1),
(1707, 108, 'Al Balqa\'', 'AB', 1),
(1708, 108, 'Al Karak', 'AK', 1),
(1709, 108, 'Al Mafraq', 'AL', 1),
(1710, 108, 'At Tafilah', 'AT', 1),
(1711, 108, 'Az Zarqa\'', 'AZ', 1),
(1712, 108, 'Irbid', 'IR', 1),
(1713, 108, 'Jarash', 'JA', 1),
(1714, 108, 'Ma\'an', 'MA', 1),
(1715, 108, 'Madaba', 'MD', 1),
(1716, 109, 'Almaty', 'AL', 1),
(1717, 109, 'Almaty City', 'AC', 1),
(1718, 109, 'Aqmola', 'AM', 1),
(1719, 109, 'Aqtobe', 'AQ', 1),
(1720, 109, 'Astana City', 'AS', 1),
(1721, 109, 'Atyrau', 'AT', 1),
(1722, 109, 'Batys Qazaqstan', 'BA', 1),
(1723, 109, 'Bayqongyr City', 'BY', 1),
(1724, 109, 'Mangghystau', 'MA', 1),
(1725, 109, 'Ongtustik Qazaqstan', 'ON', 1),
(1726, 109, 'Pavlodar', 'PA', 1),
(1727, 109, 'Qaraghandy', 'QA', 1),
(1728, 109, 'Qostanay', 'QO', 1),
(1729, 109, 'Qyzylorda', 'QY', 1),
(1730, 109, 'Shyghys Qazaqstan', 'SH', 1),
(1731, 109, 'Soltustik Qazaqstan', 'SO', 1),
(1732, 109, 'Zhambyl', 'ZH', 1),
(1733, 110, 'Central', 'CE', 1),
(1734, 110, 'Coast', 'CO', 1),
(1735, 110, 'Eastern', 'EA', 1),
(1736, 110, 'Nairobi Area', 'NA', 1),
(1737, 110, 'North Eastern', 'NE', 1),
(1738, 110, 'Nyanza', 'NY', 1),
(1739, 110, 'Rift Valley', 'RV', 1),
(1740, 110, 'Western', 'WE', 1),
(1741, 111, 'Abaiang', 'AG', 1),
(1742, 111, 'Abemama', 'AM', 1),
(1743, 111, 'Aranuka', 'AK', 1),
(1744, 111, 'Arorae', 'AO', 1),
(1745, 111, 'Banaba', 'BA', 1),
(1746, 111, 'Beru', 'BE', 1),
(1747, 111, 'Butaritari', 'bT', 1),
(1748, 111, 'Kanton', 'KA', 1),
(1749, 111, 'Kiritimati', 'KR', 1),
(1750, 111, 'Kuria', 'KU', 1),
(1751, 111, 'Maiana', 'MI', 1),
(1752, 111, 'Makin', 'MN', 1),
(1753, 111, 'Marakei', 'ME', 1),
(1754, 111, 'Nikunau', 'NI', 1),
(1755, 111, 'Nonouti', 'NO', 1),
(1756, 111, 'Onotoa', 'ON', 1),
(1757, 111, 'Tabiteuea', 'TT', 1),
(1758, 111, 'Tabuaeran', 'TR', 1),
(1759, 111, 'Tamana', 'TM', 1),
(1760, 111, 'Tarawa', 'TW', 1),
(1761, 111, 'Teraina', 'TE', 1),
(1762, 112, 'Chagang-do', 'CHA', 1),
(1763, 112, 'Hamgyong-bukto', 'HAB', 1),
(1764, 112, 'Hamgyong-namdo', 'HAN', 1),
(1765, 112, 'Hwanghae-bukto', 'HWB', 1),
(1766, 112, 'Hwanghae-namdo', 'HWN', 1),
(1767, 112, 'Kangwon-do', 'KAN', 1),
(1768, 112, 'P\'yongan-bukto', 'PYB', 1),
(1769, 112, 'P\'yongan-namdo', 'PYN', 1),
(1770, 112, 'Ryanggang-do (Yanggang-do)', 'YAN', 1),
(1771, 112, 'Rason Directly Governed City', 'NAJ', 1),
(1772, 112, 'P\'yongyang Special City', 'PYO', 1),
(1773, 113, 'Ch\'ungch\'ong-bukto', 'CO', 1),
(1774, 113, 'Ch\'ungch\'ong-namdo', 'CH', 1),
(1775, 113, 'Cheju-do', 'CD', 1),
(1776, 113, 'Cholla-bukto', 'CB', 1),
(1777, 113, 'Cholla-namdo', 'CN', 1),
(1778, 113, 'Inch\'on-gwangyoksi', 'IG', 1),
(1779, 113, 'Kangwon-do', 'KA', 1),
(1780, 113, 'Kwangju-gwangyoksi', 'KG', 1),
(1781, 113, 'Kyonggi-do', 'KD', 1),
(1782, 113, 'Kyongsang-bukto', 'KB', 1),
(1783, 113, 'Kyongsang-namdo', 'KN', 1),
(1784, 113, 'Pusan-gwangyoksi', 'PG', 1),
(1785, 113, 'Soul-t\'ukpyolsi', 'SO', 1),
(1786, 113, 'Taegu-gwangyoksi', 'TA', 1),
(1787, 113, 'Taejon-gwangyoksi', 'TG', 1),
(1788, 114, 'Al \'Asimah', 'AL', 1),
(1789, 114, 'Al Ahmadi', 'AA', 1),
(1790, 114, 'Al Farwaniyah', 'AF', 1),
(1791, 114, 'Al Jahra\'', 'AJ', 1),
(1792, 114, 'Hawalli', 'HA', 1),
(1793, 115, 'Bishkek', 'GB', 1),
(1794, 115, 'Batken', 'B', 1),
(1795, 115, 'Chu', 'C', 1),
(1796, 115, 'Jalal-Abad', 'J', 1),
(1797, 115, 'Naryn', 'N', 1),
(1798, 115, 'Osh', 'O', 1),
(1799, 115, 'Talas', 'T', 1),
(1800, 115, 'Ysyk-Kol', 'Y', 1),
(1801, 116, 'Vientiane', 'VT', 1),
(1802, 116, 'Attapu', 'AT', 1),
(1803, 116, 'Bokeo', 'BK', 1),
(1804, 116, 'Bolikhamxai', 'BL', 1),
(1805, 116, 'Champasak', 'CH', 1),
(1806, 116, 'Houaphan', 'HO', 1),
(1807, 116, 'Khammouan', 'KH', 1),
(1808, 116, 'Louang Namtha', 'LM', 1),
(1809, 116, 'Louangphabang', 'LP', 1),
(1810, 116, 'Oudomxai', 'OU', 1),
(1811, 116, 'Phongsali', 'PH', 1),
(1812, 116, 'Salavan', 'SL', 1),
(1813, 116, 'Savannakhet', 'SV', 1),
(1814, 116, 'Vientiane', 'VI', 1),
(1815, 116, 'Xaignabouli', 'XA', 1),
(1816, 116, 'Xekong', 'XE', 1),
(1817, 116, 'Xiangkhoang', 'XI', 1),
(1818, 116, 'Xaisomboun', 'XN', 1),
(1852, 119, 'Berea', 'BE', 1),
(1853, 119, 'Butha-Buthe', 'BB', 1),
(1854, 119, 'Leribe', 'LE', 1),
(1855, 119, 'Mafeteng', 'MF', 1),
(1856, 119, 'Maseru', 'MS', 1),
(1857, 119, 'Mohale\'s Hoek', 'MH', 1),
(1858, 119, 'Mokhotlong', 'MK', 1),
(1859, 119, 'Qacha\'s Nek', 'QN', 1),
(1860, 119, 'Quthing', 'QT', 1),
(1861, 119, 'Thaba-Tseka', 'TT', 1),
(1862, 120, 'Bomi', 'BI', 1),
(1863, 120, 'Bong', 'BG', 1),
(1864, 120, 'Grand Bassa', 'GB', 1),
(1865, 120, 'Grand Cape Mount', 'CM', 1),
(1866, 120, 'Grand Gedeh', 'GG', 1),
(1867, 120, 'Grand Kru', 'GK', 1),
(1868, 120, 'Lofa', 'LO', 1),
(1869, 120, 'Margibi', 'MG', 1),
(1870, 120, 'Maryland', 'ML', 1),
(1871, 120, 'Montserrado', 'MS', 1),
(1872, 120, 'Nimba', 'NB', 1),
(1873, 120, 'River Cess', 'RC', 1),
(1874, 120, 'Sinoe', 'SN', 1),
(1875, 121, 'Ajdabiya', 'AJ', 1),
(1876, 121, 'Al \'Aziziyah', 'AZ', 1),
(1877, 121, 'Al Fatih', 'FA', 1),
(1878, 121, 'Al Jabal al Akhdar', 'JA', 1),
(1879, 121, 'Al Jufrah', 'JU', 1),
(1880, 121, 'Al Khums', 'KH', 1),
(1881, 121, 'Al Kufrah', 'KU', 1),
(1882, 121, 'An Nuqat al Khams', 'NK', 1),
(1883, 121, 'Ash Shati\'', 'AS', 1),
(1884, 121, 'Awbari', 'AW', 1),
(1885, 121, 'Az Zawiyah', 'ZA', 1),
(1886, 121, 'Banghazi', 'BA', 1),
(1887, 121, 'Darnah', 'DA', 1),
(1888, 121, 'Ghadamis', 'GD', 1),
(1889, 121, 'Gharyan', 'GY', 1),
(1890, 121, 'Misratah', 'MI', 1),
(1891, 121, 'Murzuq', 'MZ', 1),
(1892, 121, 'Sabha', 'SB', 1),
(1893, 121, 'Sawfajjin', 'SW', 1),
(1894, 121, 'Surt', 'SU', 1),
(1895, 121, 'Tarabulus (Tripoli)', 'TL', 1),
(1896, 121, 'Tarhunah', 'TH', 1),
(1897, 121, 'Tubruq', 'TU', 1),
(1898, 121, 'Yafran', 'YA', 1),
(1899, 121, 'Zlitan', 'ZL', 1),
(1900, 122, 'Vaduz', 'V', 1),
(1901, 122, 'Schaan', 'A', 1),
(1902, 122, 'Balzers', 'B', 1),
(1903, 122, 'Triesen', 'N', 1),
(1904, 122, 'Eschen', 'E', 1),
(1905, 122, 'Mauren', 'M', 1),
(1906, 122, 'Triesenberg', 'T', 1),
(1907, 122, 'Ruggell', 'R', 1),
(1908, 122, 'Gamprin', 'G', 1),
(1909, 122, 'Schellenberg', 'L', 1),
(1910, 122, 'Planken', 'P', 1),
(1911, 123, 'Alytus', 'AL', 1),
(1912, 123, 'Kaunas', 'KA', 1),
(1913, 123, 'Klaipeda', 'KL', 1),
(1914, 123, 'Marijampole', 'MA', 1),
(1915, 123, 'Panevezys', 'PA', 1),
(1916, 123, 'Siauliai', 'SI', 1),
(1917, 123, 'Taurage', 'TA', 1),
(1918, 123, 'Telsiai', 'TE', 1),
(1919, 123, 'Utena', 'UT', 1),
(1920, 123, 'Vilnius', 'VI', 1),
(1921, 124, 'Diekirch', 'DD', 1),
(1922, 124, 'Clervaux', 'DC', 1),
(1923, 124, 'Redange', 'DR', 1),
(1924, 124, 'Vianden', 'DV', 1),
(1925, 124, 'Wiltz', 'DW', 1),
(1926, 124, 'Grevenmacher', 'GG', 1),
(1927, 124, 'Echternach', 'GE', 1),
(1928, 124, 'Remich', 'GR', 1),
(1929, 124, 'Luxembourg', 'LL', 1),
(1930, 124, 'Capellen', 'LC', 1),
(1931, 124, 'Esch-sur-Alzette', 'LE', 1),
(1932, 124, 'Mersch', 'LM', 1),
(1933, 125, 'Our Lady Fatima Parish', 'OLF', 1),
(1934, 125, 'St. Anthony Parish', 'ANT', 1),
(1935, 125, 'St. Lazarus Parish', 'LAZ', 1),
(1936, 125, 'Cathedral Parish', 'CAT', 1),
(1937, 125, 'St. Lawrence Parish', 'LAW', 1),
(1938, 127, 'Antananarivo', 'AN', 1),
(1939, 127, 'Antsiranana', 'AS', 1),
(1940, 127, 'Fianarantsoa', 'FN', 1),
(1941, 127, 'Mahajanga', 'MJ', 1),
(1942, 127, 'Toamasina', 'TM', 1),
(1943, 127, 'Toliara', 'TL', 1),
(1944, 128, 'Balaka', 'BLK', 1),
(1945, 128, 'Blantyre', 'BLT', 1),
(1946, 128, 'Chikwawa', 'CKW', 1),
(1947, 128, 'Chiradzulu', 'CRD', 1),
(1948, 128, 'Chitipa', 'CTP', 1),
(1949, 128, 'Dedza', 'DDZ', 1),
(1950, 128, 'Dowa', 'DWA', 1),
(1951, 128, 'Karonga', 'KRG', 1),
(1952, 128, 'Kasungu', 'KSG', 1),
(1953, 128, 'Likoma', 'LKM', 1),
(1954, 128, 'Lilongwe', 'LLG', 1),
(1955, 128, 'Machinga', 'MCG', 1),
(1956, 128, 'Mangochi', 'MGC', 1),
(1957, 128, 'Mchinji', 'MCH', 1),
(1958, 128, 'Mulanje', 'MLJ', 1),
(1959, 128, 'Mwanza', 'MWZ', 1),
(1960, 128, 'Mzimba', 'MZM', 1),
(1961, 128, 'Ntcheu', 'NTU', 1),
(1962, 128, 'Nkhata Bay', 'NKB', 1),
(1963, 128, 'Nkhotakota', 'NKH', 1),
(1964, 128, 'Nsanje', 'NSJ', 1),
(1965, 128, 'Ntchisi', 'NTI', 1),
(1966, 128, 'Phalombe', 'PHL', 1),
(1967, 128, 'Rumphi', 'RMP', 1),
(1968, 128, 'Salima', 'SLM', 1),
(1969, 128, 'Thyolo', 'THY', 1),
(1970, 128, 'Zomba', 'ZBA', 1),
(1971, 129, 'Johor', 'MY-01', 1),
(1972, 129, 'Kedah', 'MY-02', 1),
(1973, 129, 'Kelantan', 'MY-03', 1),
(1974, 129, 'Labuan', 'MY-15', 1),
(1975, 129, 'Melaka', 'MY-04', 1),
(1976, 129, 'Negeri Sembilan', 'MY-05', 1),
(1977, 129, 'Pahang', 'MY-06', 1),
(1978, 129, 'Perak', 'MY-08', 1),
(1979, 129, 'Perlis', 'MY-09', 1),
(1980, 129, 'Pulau Pinang', 'MY-07', 1),
(1981, 129, 'Sabah', 'MY-12', 1),
(1982, 129, 'Sarawak', 'MY-13', 1),
(1983, 129, 'Selangor', 'MY-10', 1),
(1984, 129, 'Terengganu', 'MY-11', 1),
(1985, 129, 'Kuala Lumpur', 'MY-14', 1),
(4035, 129, 'Putrajaya', 'MY-16', 1),
(1986, 130, 'Thiladhunmathi Uthuru', 'THU', 1),
(1987, 130, 'Thiladhunmathi Dhekunu', 'THD', 1),
(1988, 130, 'Miladhunmadulu Uthuru', 'MLU', 1),
(1989, 130, 'Miladhunmadulu Dhekunu', 'MLD', 1),
(1990, 130, 'Maalhosmadulu Uthuru', 'MAU', 1),
(1991, 130, 'Maalhosmadulu Dhekunu', 'MAD', 1),
(1992, 130, 'Faadhippolhu', 'FAA', 1),
(1993, 130, 'Male Atoll', 'MAA', 1),
(1994, 130, 'Ari Atoll Uthuru', 'AAU', 1),
(1995, 130, 'Ari Atoll Dheknu', 'AAD', 1),
(1996, 130, 'Felidhe Atoll', 'FEA', 1),
(1997, 130, 'Mulaku Atoll', 'MUA', 1),
(1998, 130, 'Nilandhe Atoll Uthuru', 'NAU', 1),
(1999, 130, 'Nilandhe Atoll Dhekunu', 'NAD', 1),
(2000, 130, 'Kolhumadulu', 'KLH', 1),
(2001, 130, 'Hadhdhunmathi', 'HDH', 1),
(2002, 130, 'Huvadhu Atoll Uthuru', 'HAU', 1),
(2003, 130, 'Huvadhu Atoll Dhekunu', 'HAD', 1),
(2004, 130, 'Fua Mulaku', 'FMU', 1),
(2005, 130, 'Addu', 'ADD', 1),
(2006, 131, 'Gao', 'GA', 1),
(2007, 131, 'Kayes', 'KY', 1),
(2008, 131, 'Kidal', 'KD', 1),
(2009, 131, 'Koulikoro', 'KL', 1),
(2010, 131, 'Mopti', 'MP', 1),
(2011, 131, 'Segou', 'SG', 1),
(2012, 131, 'Sikasso', 'SK', 1),
(2013, 131, 'Tombouctou', 'TB', 1),
(2014, 131, 'Bamako Capital District', 'CD', 1),
(2015, 132, 'Attard', 'ATT', 1),
(2016, 132, 'Balzan', 'BAL', 1),
(2017, 132, 'Birgu', 'BGU', 1),
(2018, 132, 'Birkirkara', 'BKK', 1),
(2019, 132, 'Birzebbuga', 'BRZ', 1),
(2020, 132, 'Bormla', 'BOR', 1),
(2021, 132, 'Dingli', 'DIN', 1),
(2022, 132, 'Fgura', 'FGU', 1),
(2023, 132, 'Floriana', 'FLO', 1),
(2024, 132, 'Gudja', 'GDJ', 1),
(2025, 132, 'Gzira', 'GZR', 1),
(2026, 132, 'Gargur', 'GRG', 1),
(2027, 132, 'Gaxaq', 'GXQ', 1),
(2028, 132, 'Hamrun', 'HMR', 1),
(2029, 132, 'Iklin', 'IKL', 1),
(2030, 132, 'Isla', 'ISL', 1),
(2031, 132, 'Kalkara', 'KLK', 1),
(2032, 132, 'Kirkop', 'KRK', 1),
(2033, 132, 'Lija', 'LIJ', 1),
(2034, 132, 'Luqa', 'LUQ', 1),
(2035, 132, 'Marsa', 'MRS', 1),
(2036, 132, 'Marsaskala', 'MKL', 1),
(2037, 132, 'Marsaxlokk', 'MXL', 1),
(2038, 132, 'Mdina', 'MDN', 1),
(2039, 132, 'Melliea', 'MEL', 1),
(2040, 132, 'Mgarr', 'MGR', 1),
(2041, 132, 'Mosta', 'MST', 1),
(2042, 132, 'Mqabba', 'MQA', 1),
(2043, 132, 'Msida', 'MSI', 1),
(2044, 132, 'Mtarfa', 'MTF', 1),
(2045, 132, 'Naxxar', 'NAX', 1),
(2046, 132, 'Paola', 'PAO', 1),
(2047, 132, 'Pembroke', 'PEM', 1),
(2048, 132, 'Pieta', 'PIE', 1),
(2049, 132, 'Qormi', 'QOR', 1),
(2050, 132, 'Qrendi', 'QRE', 1),
(2051, 132, 'Rabat', 'RAB', 1),
(2052, 132, 'Safi', 'SAF', 1),
(2053, 132, 'San Giljan', 'SGI', 1),
(2054, 132, 'Santa Lucija', 'SLU', 1),
(2055, 132, 'San Pawl il-Bahar', 'SPB', 1),
(2056, 132, 'San Gwann', 'SGW', 1),
(2057, 132, 'Santa Venera', 'SVE', 1),
(2058, 132, 'Siggiewi', 'SIG', 1),
(2059, 132, 'Sliema', 'SLM', 1),
(2060, 132, 'Swieqi', 'SWQ', 1),
(2061, 132, 'Ta Xbiex', 'TXB', 1),
(2062, 132, 'Tarxien', 'TRX', 1),
(2063, 132, 'Valletta', 'VLT', 1),
(2064, 132, 'Xgajra', 'XGJ', 1),
(2065, 132, 'Zabbar', 'ZBR', 1),
(2066, 132, 'Zebbug', 'ZBG', 1),
(2067, 132, 'Zejtun', 'ZJT', 1),
(2068, 132, 'Zurrieq', 'ZRQ', 1),
(2069, 132, 'Fontana', 'FNT', 1),
(2070, 132, 'Ghajnsielem', 'GHJ', 1),
(2071, 132, 'Gharb', 'GHR', 1),
(2072, 132, 'Ghasri', 'GHS', 1),
(2073, 132, 'Kercem', 'KRC', 1),
(2074, 132, 'Munxar', 'MUN', 1),
(2075, 132, 'Nadur', 'NAD', 1),
(2076, 132, 'Qala', 'QAL', 1),
(2077, 132, 'Victoria', 'VIC', 1),
(2078, 132, 'San Lawrenz', 'SLA', 1),
(2079, 132, 'Sannat', 'SNT', 1),
(2080, 132, 'Xagra', 'ZAG', 1),
(2081, 132, 'Xewkija', 'XEW', 1),
(2082, 132, 'Zebbug', 'ZEB', 1),
(2083, 133, 'Ailinginae', 'ALG', 1),
(2084, 133, 'Ailinglaplap', 'ALL', 1),
(2085, 133, 'Ailuk', 'ALK', 1),
(2086, 133, 'Arno', 'ARN', 1),
(2087, 133, 'Aur', 'AUR', 1),
(2088, 133, 'Bikar', 'BKR', 1),
(2089, 133, 'Bikini', 'BKN', 1),
(2090, 133, 'Bokak', 'BKK', 1),
(2091, 133, 'Ebon', 'EBN', 1),
(2092, 133, 'Enewetak', 'ENT', 1),
(2093, 133, 'Erikub', 'EKB', 1),
(2094, 133, 'Jabat', 'JBT', 1),
(2095, 133, 'Jaluit', 'JLT', 1),
(2096, 133, 'Jemo', 'JEM', 1),
(2097, 133, 'Kili', 'KIL', 1),
(2098, 133, 'Kwajalein', 'KWJ', 1),
(2099, 133, 'Lae', 'LAE', 1),
(2100, 133, 'Lib', 'LIB', 1),
(2101, 133, 'Likiep', 'LKP', 1),
(2102, 133, 'Majuro', 'MJR', 1),
(2103, 133, 'Maloelap', 'MLP', 1),
(2104, 133, 'Mejit', 'MJT', 1),
(2105, 133, 'Mili', 'MIL', 1),
(2106, 133, 'Namorik', 'NMK', 1),
(2107, 133, 'Namu', 'NAM', 1),
(2108, 133, 'Rongelap', 'RGL', 1),
(2109, 133, 'Rongrik', 'RGK', 1),
(2110, 133, 'Toke', 'TOK', 1),
(2111, 133, 'Ujae', 'UJA', 1),
(2112, 133, 'Ujelang', 'UJL', 1),
(2113, 133, 'Utirik', 'UTK', 1),
(2114, 133, 'Wotho', 'WTH', 1),
(2115, 133, 'Wotje', 'WTJ', 1),
(2116, 135, 'Adrar', 'AD', 1),
(2117, 135, 'Assaba', 'AS', 1),
(2118, 135, 'Brakna', 'BR', 1),
(2119, 135, 'Dakhlet Nouadhibou', 'DN', 1),
(2120, 135, 'Gorgol', 'GO', 1),
(2121, 135, 'Guidimaka', 'GM', 1),
(2122, 135, 'Hodh Ech Chargui', 'HC', 1),
(2123, 135, 'Hodh El Gharbi', 'HG', 1),
(2124, 135, 'Inchiri', 'IN', 1),
(2125, 135, 'Tagant', 'TA', 1),
(2126, 135, 'Tiris Zemmour', 'TZ', 1),
(2127, 135, 'Trarza', 'TR', 1),
(2128, 135, 'Nouakchott', 'NO', 1),
(2129, 136, 'Beau Bassin-Rose Hill', 'BR', 1),
(2130, 136, 'Curepipe', 'CU', 1),
(2131, 136, 'Port Louis', 'PU', 1),
(2132, 136, 'Quatre Bornes', 'QB', 1),
(2133, 136, 'Vacoas-Phoenix', 'VP', 1),
(2134, 136, 'Agalega Islands', 'AG', 1),
(2135, 136, 'Cargados Carajos Shoals (Saint Brandon Islands)', 'CC', 1),
(2136, 136, 'Rodrigues', 'RO', 1),
(2137, 136, 'Black River', 'BL', 1),
(2138, 136, 'Flacq', 'FL', 1),
(2139, 136, 'Grand Port', 'GP', 1),
(2140, 136, 'Moka', 'MO', 1),
(2141, 136, 'Pamplemousses', 'PA', 1),
(2142, 136, 'Plaines Wilhems', 'PW', 1),
(2143, 136, 'Port Louis', 'PL', 1),
(2144, 136, 'Riviere du Rempart', 'RR', 1),
(2145, 136, 'Savanne', 'SA', 1),
(2146, 138, 'Baja California Norte', 'BN', 1),
(2147, 138, 'Baja California Sur', 'BS', 1),
(2148, 138, 'Campeche', 'CA', 1),
(2149, 138, 'Chiapas', 'CI', 1),
(2150, 138, 'Chihuahua', 'CH', 1),
(2151, 138, 'Coahuila de Zaragoza', 'CZ', 1),
(2152, 138, 'Colima', 'CL', 1),
(2153, 138, 'Distrito Federal', 'DF', 1),
(2154, 138, 'Durango', 'DU', 1),
(2155, 138, 'Guanajuato', 'GA', 1),
(2156, 138, 'Guerrero', 'GE', 1),
(2157, 138, 'Hidalgo', 'HI', 1),
(2158, 138, 'Jalisco', 'JA', 1),
(2159, 138, 'Mexico', 'ME', 1),
(2160, 138, 'Michoacan de Ocampo', 'MI', 1),
(2161, 138, 'Morelos', 'MO', 1),
(2162, 138, 'Nayarit', 'NA', 1),
(2163, 138, 'Nuevo Leon', 'NL', 1),
(2164, 138, 'Oaxaca', 'OA', 1),
(2165, 138, 'Puebla', 'PU', 1),
(2166, 138, 'Queretaro de Arteaga', 'QA', 1),
(2167, 138, 'Quintana Roo', 'QR', 1),
(2168, 138, 'San Luis Potosi', 'SA', 1),
(2169, 138, 'Sinaloa', 'SI', 1),
(2170, 138, 'Sonora', 'SO', 1),
(2171, 138, 'Tabasco', 'TB', 1),
(2172, 138, 'Tamaulipas', 'TM', 1),
(2173, 138, 'Tlaxcala', 'TL', 1),
(2174, 138, 'Veracruz-Llave', 'VE', 1),
(2175, 138, 'Yucatan', 'YU', 1),
(2176, 138, 'Zacatecas', 'ZA', 1),
(2177, 139, 'Chuuk', 'C', 1),
(2178, 139, 'Kosrae', 'K', 1),
(2179, 139, 'Pohnpei', 'P', 1),
(2180, 139, 'Yap', 'Y', 1),
(2181, 140, 'Gagauzia', 'GA', 1),
(2182, 140, 'Chisinau', 'CU', 1),
(2183, 140, 'Balti', 'BA', 1),
(2184, 140, 'Cahul', 'CA', 1),
(2185, 140, 'Edinet', 'ED', 1),
(2186, 140, 'Lapusna', 'LA', 1),
(2187, 140, 'Orhei', 'OR', 1),
(2188, 140, 'Soroca', 'SO', 1),
(2189, 140, 'Tighina', 'TI', 1),
(2190, 140, 'Ungheni', 'UN', 1),
(2191, 140, 'St‚nga Nistrului', 'SN', 1),
(2192, 141, 'Fontvieille', 'FV', 1),
(2193, 141, 'La Condamine', 'LC', 1),
(2194, 141, 'Monaco-Ville', 'MV', 1),
(2195, 141, 'Monte-Carlo', 'MC', 1),
(2196, 142, 'Ulanbaatar', '1', 1),
(2197, 142, 'Orhon', '035', 1),
(2198, 142, 'Darhan uul', '037', 1),
(2199, 142, 'Hentiy', '039', 1),
(2200, 142, 'Hovsgol', '041', 1),
(2201, 142, 'Hovd', '043', 1),
(2202, 142, 'Uvs', '046', 1),
(2203, 142, 'Tov', '047', 1),
(2204, 142, 'Selenge', '049', 1),
(2205, 142, 'Suhbaatar', '051', 1),
(2206, 142, 'Omnogovi', '053', 1),
(2207, 142, 'Ovorhangay', '055', 1),
(2208, 142, 'Dzavhan', '057', 1),
(2209, 142, 'DundgovL', '059', 1),
(2210, 142, 'Dornod', '061', 1),
(2211, 142, 'Dornogov', '063', 1),
(2212, 142, 'Govi-Sumber', '064', 1),
(2213, 142, 'Govi-Altay', '065', 1),
(2214, 142, 'Bulgan', '067', 1),
(2215, 142, 'Bayanhongor', '069', 1),
(2216, 142, 'Bayan-Olgiy', '071', 1),
(2217, 142, 'Arhangay', '073', 1),
(2218, 143, 'Saint Anthony', 'A', 1),
(2219, 143, 'Saint Georges', 'G', 1),
(2220, 143, 'Saint Peter', 'P', 1),
(2221, 144, 'Agadir', 'AGD', 1),
(2222, 144, 'Al Hoceima', 'HOC', 1),
(2223, 144, 'Azilal', 'AZI', 1),
(2224, 144, 'Beni Mellal', 'BME', 1),
(2225, 144, 'Ben Slimane', 'BSL', 1),
(2226, 144, 'Boulemane', 'BLM', 1),
(2227, 144, 'Casablanca', 'CBL', 1),
(2228, 144, 'Chaouen', 'CHA', 1),
(2229, 144, 'El Jadida', 'EJA', 1),
(2230, 144, 'El Kelaa des Sraghna', 'EKS', 1),
(2231, 144, 'Er Rachidia', 'ERA', 1),
(2232, 144, 'Essaouira', 'ESS', 1),
(2233, 144, 'Fes', 'FES', 1),
(2234, 144, 'Figuig', 'FIG', 1),
(2235, 144, 'Guelmim', 'GLM', 1),
(2236, 144, 'Ifrane', 'IFR', 1),
(2237, 144, 'Kenitra', 'KEN', 1),
(2238, 144, 'Khemisset', 'KHM', 1),
(2239, 144, 'Khenifra', 'KHN', 1),
(2240, 144, 'Khouribga', 'KHO', 1),
(2241, 144, 'Laayoune', 'LYN', 1),
(2242, 144, 'Larache', 'LAR', 1),
(2243, 144, 'Marrakech', 'MRK', 1),
(2244, 144, 'Meknes', 'MKN', 1),
(2245, 144, 'Nador', 'NAD', 1),
(2246, 144, 'Ouarzazate', 'ORZ', 1),
(2247, 144, 'Oujda', 'OUJ', 1),
(2248, 144, 'Rabat-Sale', 'RSA', 1),
(2249, 144, 'Safi', 'SAF', 1),
(2250, 144, 'Settat', 'SET', 1),
(2251, 144, 'Sidi Kacem', 'SKA', 1),
(2252, 144, 'Tangier', 'TGR', 1),
(2253, 144, 'Tan-Tan', 'TAN', 1),
(2254, 144, 'Taounate', 'TAO', 1),
(2255, 144, 'Taroudannt', 'TRD', 1),
(2256, 144, 'Tata', 'TAT', 1),
(2257, 144, 'Taza', 'TAZ', 1),
(2258, 144, 'Tetouan', 'TET', 1),
(2259, 144, 'Tiznit', 'TIZ', 1),
(2260, 144, 'Ad Dakhla', 'ADK', 1),
(2261, 144, 'Boujdour', 'BJD', 1),
(2262, 144, 'Es Smara', 'ESM', 1),
(2263, 145, 'Cabo Delgado', 'CD', 1),
(2264, 145, 'Gaza', 'GZ', 1),
(2265, 145, 'Inhambane', 'IN', 1),
(2266, 145, 'Manica', 'MN', 1),
(2267, 145, 'Maputo (city)', 'MC', 1),
(2268, 145, 'Maputo', 'MP', 1),
(2269, 145, 'Nampula', 'NA', 1),
(2270, 145, 'Niassa', 'NI', 1),
(2271, 145, 'Sofala', 'SO', 1),
(2272, 145, 'Tete', 'TE', 1),
(2273, 145, 'Zambezia', 'ZA', 1),
(2274, 146, 'Ayeyarwady', 'AY', 1),
(2275, 146, 'Bago', 'BG', 1),
(2276, 146, 'Magway', 'MG', 1),
(2277, 146, 'Mandalay', 'MD', 1),
(2278, 146, 'Sagaing', 'SG', 1),
(2279, 146, 'Tanintharyi', 'TN', 1),
(2280, 146, 'Yangon', 'YG', 1),
(2281, 146, 'Chin State', 'CH', 1),
(2282, 146, 'Kachin State', 'KC', 1),
(2283, 146, 'Kayah State', 'KH', 1),
(2284, 146, 'Kayin State', 'KN', 1),
(2285, 146, 'Mon State', 'MN', 1),
(2286, 146, 'Rakhine State', 'RK', 1),
(2287, 146, 'Shan State', 'SH', 1),
(2288, 147, 'Caprivi', 'CA', 1),
(2289, 147, 'Erongo', 'ER', 1),
(2290, 147, 'Hardap', 'HA', 1),
(2291, 147, 'Karas', 'KR', 1),
(2292, 147, 'Kavango', 'KV', 1),
(2293, 147, 'Khomas', 'KH', 1),
(2294, 147, 'Kunene', 'KU', 1),
(2295, 147, 'Ohangwena', 'OW', 1),
(2296, 147, 'Omaheke', 'OK', 1),
(2297, 147, 'Omusati', 'OT', 1),
(2298, 147, 'Oshana', 'ON', 1),
(2299, 147, 'Oshikoto', 'OO', 1),
(2300, 147, 'Otjozondjupa', 'OJ', 1),
(2301, 148, 'Aiwo', 'AO', 1),
(2302, 148, 'Anabar', 'AA', 1),
(2303, 148, 'Anetan', 'AT', 1),
(2304, 148, 'Anibare', 'AI', 1),
(2305, 148, 'Baiti', 'BA', 1),
(2306, 148, 'Boe', 'BO', 1),
(2307, 148, 'Buada', 'BU', 1),
(2308, 148, 'Denigomodu', 'DE', 1),
(2309, 148, 'Ewa', 'EW', 1),
(2310, 148, 'Ijuw', 'IJ', 1),
(2311, 148, 'Meneng', 'ME', 1),
(2312, 148, 'Nibok', 'NI', 1),
(2313, 148, 'Uaboe', 'UA', 1),
(2314, 148, 'Yaren', 'YA', 1),
(2315, 149, 'Bagmati', 'BA', 1),
(2316, 149, 'Bheri', 'BH', 1),
(2317, 149, 'Dhawalagiri', 'DH', 1),
(2318, 149, 'Gandaki', 'GA', 1),
(2319, 149, 'Janakpur', 'JA', 1),
(2320, 149, 'Karnali', 'KA', 1),
(2321, 149, 'Kosi', 'KO', 1),
(2322, 149, 'Lumbini', 'LU', 1),
(2323, 149, 'Mahakali', 'MA', 1),
(2324, 149, 'Mechi', 'ME', 1),
(2325, 149, 'Narayani', 'NA', 1),
(2326, 149, 'Rapti', 'RA', 1),
(2327, 149, 'Sagarmatha', 'SA', 1),
(2328, 149, 'Seti', 'SE', 1),
(2329, 150, 'Drenthe', 'DR', 1),
(2330, 150, 'Flevoland', 'FL', 1),
(2331, 150, 'Friesland', 'FR', 1),
(2332, 150, 'Gelderland', 'GE', 1),
(2333, 150, 'Groningen', 'GR', 1),
(2334, 150, 'Limburg', 'LI', 1),
(2335, 150, 'Noord-Brabant', 'NB', 1),
(2336, 150, 'Noord-Holland', 'NH', 1),
(2337, 150, 'Overijssel', 'OV', 1),
(2338, 150, 'Utrecht', 'UT', 1),
(2339, 150, 'Zeeland', 'ZE', 1),
(2340, 150, 'Zuid-Holland', 'ZH', 1),
(2341, 152, 'Iles Loyaute', 'L', 1),
(2342, 152, 'Nord', 'N', 1),
(2343, 152, 'Sud', 'S', 1),
(2344, 153, 'Auckland', 'AUK', 1),
(2345, 153, 'Bay of Plenty', 'BOP', 1),
(2346, 153, 'Canterbury', 'CAN', 1),
(2347, 153, 'Coromandel', 'COR', 1),
(2348, 153, 'Gisborne', 'GIS', 1),
(2349, 153, 'Fiordland', 'FIO', 1),
(2350, 153, 'Hawke\'s Bay', 'HKB', 1),
(2351, 153, 'Marlborough', 'MBH', 1),
(2352, 153, 'Manawatu-Wanganui', 'MWT', 1),
(2353, 153, 'Mt Cook-Mackenzie', 'MCM', 1),
(2354, 153, 'Nelson', 'NSN', 1),
(2355, 153, 'Northland', 'NTL', 1),
(2356, 153, 'Otago', 'OTA', 1),
(2357, 153, 'Southland', 'STL', 1),
(2358, 153, 'Taranaki', 'TKI', 1),
(2359, 153, 'Wellington', 'WGN', 1),
(2360, 153, 'Waikato', 'WKO', 1),
(2361, 153, 'Wairarapa', 'WAI', 1),
(2362, 153, 'West Coast', 'WTC', 1),
(2363, 154, 'Atlantico Norte', 'AN', 1),
(2364, 154, 'Atlantico Sur', 'AS', 1),
(2365, 154, 'Boaco', 'BO', 1),
(2366, 154, 'Carazo', 'CA', 1),
(2367, 154, 'Chinandega', 'CI', 1),
(2368, 154, 'Chontales', 'CO', 1),
(2369, 154, 'Esteli', 'ES', 1),
(2370, 154, 'Granada', 'GR', 1),
(2371, 154, 'Jinotega', 'JI', 1),
(2372, 154, 'Leon', 'LE', 1),
(2373, 154, 'Madriz', 'MD', 1),
(2374, 154, 'Managua', 'MN', 1),
(2375, 154, 'Masaya', 'MS', 1),
(2376, 154, 'Matagalpa', 'MT', 1),
(2377, 154, 'Nuevo Segovia', 'NS', 1),
(2378, 154, 'Rio San Juan', 'RS', 1),
(2379, 154, 'Rivas', 'RI', 1),
(2380, 155, 'Agadez', 'AG', 1),
(2381, 155, 'Diffa', 'DF', 1),
(2382, 155, 'Dosso', 'DS', 1),
(2383, 155, 'Maradi', 'MA', 1),
(2384, 155, 'Niamey', 'NM', 1),
(2385, 155, 'Tahoua', 'TH', 1),
(2386, 155, 'Tillaberi', 'TL', 1),
(2387, 155, 'Zinder', 'ZD', 1),
(2388, 156, 'Abia', 'AB', 1),
(2389, 156, 'Abuja Federal Capital Territory', 'CT', 1),
(2390, 156, 'Adamawa', 'AD', 1),
(2391, 156, 'Akwa Ibom', 'AK', 1),
(2392, 156, 'Anambra', 'AN', 1),
(2393, 156, 'Bauchi', 'BC', 1),
(2394, 156, 'Bayelsa', 'BY', 1),
(2395, 156, 'Benue', 'BN', 1),
(2396, 156, 'Borno', 'BO', 1),
(2397, 156, 'Cross River', 'CR', 1),
(2398, 156, 'Delta', 'DE', 1),
(2399, 156, 'Ebonyi', 'EB', 1),
(2400, 156, 'Edo', 'ED', 1),
(2401, 156, 'Ekiti', 'EK', 1),
(2402, 156, 'Enugu', 'EN', 1),
(2403, 156, 'Gombe', 'GO', 1),
(2404, 156, 'Imo', 'IM', 1),
(2405, 156, 'Jigawa', 'JI', 1),
(2406, 156, 'Kaduna', 'KD', 1),
(2407, 156, 'Kano', 'KN', 1),
(2408, 156, 'Katsina', 'KT', 1),
(2409, 156, 'Kebbi', 'KE', 1),
(2410, 156, 'Kogi', 'KO', 1),
(2411, 156, 'Kwara', 'KW', 1),
(2412, 156, 'Lagos', 'LA', 1),
(2413, 156, 'Nassarawa', 'NA', 1),
(2414, 156, 'Niger', 'NI', 1),
(2415, 156, 'Ogun', 'OG', 1),
(2416, 156, 'Ondo', 'ONG', 1),
(2417, 156, 'Osun', 'OS', 1),
(2418, 156, 'Oyo', 'OY', 1),
(2419, 156, 'Plateau', 'PL', 1),
(2420, 156, 'Rivers', 'RI', 1),
(2421, 156, 'Sokoto', 'SO', 1),
(2422, 156, 'Taraba', 'TA', 1),
(2423, 156, 'Yobe', 'YO', 1),
(2424, 156, 'Zamfara', 'ZA', 1),
(2425, 159, 'Northern Islands', 'N', 1),
(2426, 159, 'Rota', 'R', 1),
(2427, 159, 'Saipan', 'S', 1),
(2428, 159, 'Tinian', 'T', 1),
(2429, 160, 'Akershus', 'AK', 1),
(2430, 160, 'Aust-Agder', 'AA', 1),
(2431, 160, 'Buskerud', 'BU', 1),
(2432, 160, 'Finnmark', 'FM', 1),
(2433, 160, 'Hedmark', 'HM', 1),
(2434, 160, 'Hordaland', 'HL', 1),
(2435, 160, 'More og Romdal', 'MR', 1),
(2436, 160, 'Nord-Trondelag', 'NT', 1),
(2437, 160, 'Nordland', 'NL', 1),
(2438, 160, 'Ostfold', 'OF', 1),
(2439, 160, 'Oppland', 'OP', 1),
(2440, 160, 'Oslo', 'OL', 1),
(2441, 160, 'Rogaland', 'RL', 1),
(2442, 160, 'Sor-Trondelag', 'ST', 1),
(2443, 160, 'Sogn og Fjordane', 'SJ', 1),
(2444, 160, 'Svalbard', 'SV', 1),
(2445, 160, 'Telemark', 'TM', 1),
(2446, 160, 'Troms', 'TR', 1),
(2447, 160, 'Vest-Agder', 'VA', 1),
(2448, 160, 'Vestfold', 'VF', 1),
(2449, 161, 'Ad Dakhiliyah', 'DA', 1),
(2450, 161, 'Al Batinah', 'BA', 1),
(2451, 161, 'Al Wusta', 'WU', 1),
(2452, 161, 'Ash Sharqiyah', 'SH', 1),
(2453, 161, 'Az Zahirah', 'ZA', 1),
(2454, 161, 'Masqat', 'MA', 1),
(2455, 161, 'Musandam', 'MU', 1),
(2456, 161, 'Zufar', 'ZU', 1),
(2457, 162, 'Balochistan', 'B', 1),
(2458, 162, 'Federally Administered Tribal Areas', 'T', 1),
(2459, 162, 'Islamabad Capital Territory', 'I', 1),
(2460, 162, 'North-West Frontier', 'N', 1),
(2461, 162, 'Punjab', 'P', 1),
(2462, 162, 'Sindh', 'S', 1),
(2463, 163, 'Aimeliik', 'AM', 1),
(2464, 163, 'Airai', 'AR', 1),
(2465, 163, 'Angaur', 'AN', 1),
(2466, 163, 'Hatohobei', 'HA', 1),
(2467, 163, 'Kayangel', 'KA', 1),
(2468, 163, 'Koror', 'KO', 1),
(2469, 163, 'Melekeok', 'ME', 1),
(2470, 163, 'Ngaraard', 'NA', 1),
(2471, 163, 'Ngarchelong', 'NG', 1),
(2472, 163, 'Ngardmau', 'ND', 1),
(2473, 163, 'Ngatpang', 'NT', 1),
(2474, 163, 'Ngchesar', 'NC', 1),
(2475, 163, 'Ngeremlengui', 'NR', 1),
(2476, 163, 'Ngiwal', 'NW', 1),
(2477, 163, 'Peleliu', 'PE', 1),
(2478, 163, 'Sonsorol', 'SO', 1),
(2479, 164, 'Bocas del Toro', 'BT', 1),
(2480, 164, 'Chiriqui', 'CH', 1),
(2481, 164, 'Cocle', 'CC', 1),
(2482, 164, 'Colon', 'CL', 1),
(2483, 164, 'Darien', 'DA', 1),
(2484, 164, 'Herrera', 'HE', 1),
(2485, 164, 'Los Santos', 'LS', 1),
(2486, 164, 'Panama', 'PA', 1),
(2487, 164, 'San Blas', 'SB', 1),
(2488, 164, 'Veraguas', 'VG', 1),
(2489, 165, 'Bougainville', 'BV', 1),
(2490, 165, 'Central', 'CE', 1),
(2491, 165, 'Chimbu', 'CH', 1),
(2492, 165, 'Eastern Highlands', 'EH', 1),
(2493, 165, 'East New Britain', 'EB', 1),
(2494, 165, 'East Sepik', 'ES', 1),
(2495, 165, 'Enga', 'EN', 1),
(2496, 165, 'Gulf', 'GU', 1),
(2497, 165, 'Madang', 'MD', 1),
(2498, 165, 'Manus', 'MN', 1),
(2499, 165, 'Milne Bay', 'MB', 1),
(2500, 165, 'Morobe', 'MR', 1),
(2501, 165, 'National Capital', 'NC', 1),
(2502, 165, 'New Ireland', 'NI', 1),
(2503, 165, 'Northern', 'NO', 1),
(2504, 165, 'Sandaun', 'SA', 1),
(2505, 165, 'Southern Highlands', 'SH', 1),
(2506, 165, 'Western', 'WE', 1),
(2507, 165, 'Western Highlands', 'WH', 1),
(2508, 165, 'West New Britain', 'WB', 1),
(2509, 166, 'Alto Paraguay', 'AG', 1),
(2510, 166, 'Alto Parana', 'AN', 1),
(2511, 166, 'Amambay', 'AM', 1),
(2512, 166, 'Asuncion', 'AS', 1),
(2513, 166, 'Boqueron', 'BO', 1),
(2514, 166, 'Caaguazu', 'CG', 1),
(2515, 166, 'Caazapa', 'CZ', 1),
(2516, 166, 'Canindeyu', 'CN', 1),
(2517, 166, 'Central', 'CE', 1),
(2518, 166, 'Concepcion', 'CC', 1),
(2519, 166, 'Cordillera', 'CD', 1),
(2520, 166, 'Guaira', 'GU', 1),
(2521, 166, 'Itapua', 'IT', 1),
(2522, 166, 'Misiones', 'MI', 1),
(2523, 166, 'Neembucu', 'NE', 1),
(2524, 166, 'Paraguari', 'PA', 1),
(2525, 166, 'Presidente Hayes', 'PH', 1),
(2526, 166, 'San Pedro', 'SP', 1),
(2527, 167, 'Amazonas', 'AM', 1),
(2528, 167, 'Ancash', 'AN', 1),
(2529, 167, 'Apurimac', 'AP', 1),
(2530, 167, 'Arequipa', 'AR', 1),
(2531, 167, 'Ayacucho', 'AY', 1),
(2532, 167, 'Cajamarca', 'CJ', 1),
(2533, 167, 'Callao', 'CL', 1),
(2534, 167, 'Cusco', 'CU', 1),
(2535, 167, 'Huancavelica', 'HV', 1),
(2536, 167, 'Huanuco', 'HO', 1),
(2537, 167, 'Ica', 'IC', 1),
(2538, 167, 'Junin', 'JU', 1),
(2539, 167, 'La Libertad', 'LD', 1),
(2540, 167, 'Lambayeque', 'LY', 1),
(2541, 167, 'Lima', 'LI', 1),
(2542, 167, 'Loreto', 'LO', 1),
(2543, 167, 'Madre de Dios', 'MD', 1),
(2544, 167, 'Moquegua', 'MO', 1),
(2545, 167, 'Pasco', 'PA', 1),
(2546, 167, 'Piura', 'PI', 1),
(2547, 167, 'Puno', 'PU', 1),
(2548, 167, 'San Martin', 'SM', 1),
(2549, 167, 'Tacna', 'TA', 1),
(2550, 167, 'Tumbes', 'TU', 1),
(2551, 167, 'Ucayali', 'UC', 1),
(2552, 168, 'Abra', 'ABR', 1),
(2553, 168, 'Agusan del Norte', 'ANO', 1),
(2554, 168, 'Agusan del Sur', 'ASU', 1),
(2555, 168, 'Aklan', 'AKL', 1),
(2556, 168, 'Albay', 'ALB', 1),
(2557, 168, 'Antique', 'ANT', 1),
(2558, 168, 'Apayao', 'APY', 1),
(2559, 168, 'Aurora', 'AUR', 1),
(2560, 168, 'Basilan', 'BAS', 1),
(2561, 168, 'Bataan', 'BTA', 1),
(2562, 168, 'Batanes', 'BTE', 1),
(2563, 168, 'Batangas', 'BTG', 1),
(2564, 168, 'Biliran', 'BLR', 1),
(2565, 168, 'Benguet', 'BEN', 1),
(2566, 168, 'Bohol', 'BOL', 1),
(2567, 168, 'Bukidnon', 'BUK', 1),
(2568, 168, 'Bulacan', 'BUL', 1),
(2569, 168, 'Cagayan', 'CAG', 1),
(2570, 168, 'Camarines Norte', 'CNO', 1),
(2571, 168, 'Camarines Sur', 'CSU', 1),
(2572, 168, 'Camiguin', 'CAM', 1),
(2573, 168, 'Capiz', 'CAP', 1),
(2574, 168, 'Catanduanes', 'CAT', 1),
(2575, 168, 'Cavite', 'CAV', 1),
(2576, 168, 'Cebu', 'CEB', 1),
(2577, 168, 'Compostela', 'CMP', 1),
(2578, 168, 'Davao del Norte', 'DNO', 1),
(2579, 168, 'Davao del Sur', 'DSU', 1),
(2580, 168, 'Davao Oriental', 'DOR', 1),
(2581, 168, 'Eastern Samar', 'ESA', 1),
(2582, 168, 'Guimaras', 'GUI', 1),
(2583, 168, 'Ifugao', 'IFU', 1),
(2584, 168, 'Ilocos Norte', 'INO', 1),
(2585, 168, 'Ilocos Sur', 'ISU', 1),
(2586, 168, 'Iloilo', 'ILO', 1),
(2587, 168, 'Isabela', 'ISA', 1),
(2588, 168, 'Kalinga', 'KAL', 1),
(2589, 168, 'Laguna', 'LAG', 1),
(2590, 168, 'Lanao del Norte', 'LNO', 1),
(2591, 168, 'Lanao del Sur', 'LSU', 1),
(2592, 168, 'La Union', 'UNI', 1),
(2593, 168, 'Leyte', 'LEY', 1),
(2594, 168, 'Maguindanao', 'MAG', 1),
(2595, 168, 'Marinduque', 'MRN', 1),
(2596, 168, 'Masbate', 'MSB', 1),
(2597, 168, 'Mindoro Occidental', 'MIC', 1),
(2598, 168, 'Mindoro Oriental', 'MIR', 1),
(2599, 168, 'Misamis Occidental', 'MSC', 1),
(2600, 168, 'Misamis Oriental', 'MOR', 1),
(2601, 168, 'Mountain', 'MOP', 1),
(2602, 168, 'Negros Occidental', 'NOC', 1),
(2603, 168, 'Negros Oriental', 'NOR', 1),
(2604, 168, 'North Cotabato', 'NCT', 1),
(2605, 168, 'Northern Samar', 'NSM', 1),
(2606, 168, 'Nueva Ecija', 'NEC', 1),
(2607, 168, 'Nueva Vizcaya', 'NVZ', 1),
(2608, 168, 'Palawan', 'PLW', 1),
(2609, 168, 'Pampanga', 'PMP', 1),
(2610, 168, 'Pangasinan', 'PNG', 1),
(2611, 168, 'Quezon', 'QZN', 1),
(2612, 168, 'Quirino', 'QRN', 1),
(2613, 168, 'Rizal', 'RIZ', 1),
(2614, 168, 'Romblon', 'ROM', 1),
(2615, 168, 'Samar', 'SMR', 1),
(2616, 168, 'Sarangani', 'SRG', 1),
(2617, 168, 'Siquijor', 'SQJ', 1),
(2618, 168, 'Sorsogon', 'SRS', 1),
(2619, 168, 'South Cotabato', 'SCO', 1),
(2620, 168, 'Southern Leyte', 'SLE', 1),
(2621, 168, 'Sultan Kudarat', 'SKU', 1),
(2622, 168, 'Sulu', 'SLU', 1),
(2623, 168, 'Surigao del Norte', 'SNO', 1),
(2624, 168, 'Surigao del Sur', 'SSU', 1),
(2625, 168, 'Tarlac', 'TAR', 1),
(2626, 168, 'Tawi-Tawi', 'TAW', 1),
(2627, 168, 'Zambales', 'ZBL', 1),
(2628, 168, 'Zamboanga del Norte', 'ZNO', 1),
(2629, 168, 'Zamboanga del Sur', 'ZSU', 1),
(2630, 168, 'Zamboanga Sibugay', 'ZSI', 1),
(2631, 170, 'Dolnoslaskie', 'DO', 1),
(2632, 170, 'Kujawsko-Pomorskie', 'KP', 1),
(2633, 170, 'Lodzkie', 'LO', 1),
(2634, 170, 'Lubelskie', 'LL', 1),
(2635, 170, 'Lubuskie', 'LU', 1),
(2636, 170, 'Malopolskie', 'ML', 1),
(2637, 170, 'Mazowieckie', 'MZ', 1),
(2638, 170, 'Opolskie', 'OP', 1),
(2639, 170, 'Podkarpackie', 'PP', 1),
(2640, 170, 'Podlaskie', 'PL', 1),
(2641, 170, 'Pomorskie', 'PM', 1),
(2642, 170, 'Slaskie', 'SL', 1),
(2643, 170, 'Swietokrzyskie', 'SW', 1),
(2644, 170, 'Warminsko-Mazurskie', 'WM', 1),
(2645, 170, 'Wielkopolskie', 'WP', 1),
(2646, 170, 'Zachodniopomorskie', 'ZA', 1),
(2647, 198, 'Saint Pierre', 'P', 1),
(2648, 198, 'Miquelon', 'M', 1),
(2649, 171, 'A&ccedil;ores', 'AC', 1),
(2650, 171, 'Aveiro', 'AV', 1),
(2651, 171, 'Beja', 'BE', 1),
(2652, 171, 'Braga', 'BR', 1),
(2653, 171, 'Bragan&ccedil;a', 'BA', 1),
(2654, 171, 'Castelo Branco', 'CB', 1),
(2655, 171, 'Coimbra', 'CO', 1),
(2656, 171, '&Eacute;vora', 'EV', 1),
(2657, 171, 'Faro', 'FA', 1),
(2658, 171, 'Guarda', 'GU', 1),
(2659, 171, 'Leiria', 'LE', 1),
(2660, 171, 'Lisboa', 'LI', 1),
(2661, 171, 'Madeira', 'ME', 1),
(2662, 171, 'Portalegre', 'PO', 1),
(2663, 171, 'Porto', 'PR', 1),
(2664, 171, 'Santar&eacute;m', 'SA', 1),
(2665, 171, 'Set&uacute;bal', 'SE', 1),
(2666, 171, 'Viana do Castelo', 'VC', 1),
(2667, 171, 'Vila Real', 'VR', 1),
(2668, 171, 'Viseu', 'VI', 1),
(2669, 173, 'Ad Dawhah', 'DW', 1),
(2670, 173, 'Al Ghuwayriyah', 'GW', 1),
(2671, 173, 'Al Jumayliyah', 'JM', 1),
(2672, 173, 'Al Khawr', 'KR', 1),
(2673, 173, 'Al Wakrah', 'WK', 1),
(2674, 173, 'Ar Rayyan', 'RN', 1),
(2675, 173, 'Jarayan al Batinah', 'JB', 1),
(2676, 173, 'Madinat ash Shamal', 'MS', 1),
(2677, 173, 'Umm Sa\'id', 'UD', 1),
(2678, 173, 'Umm Salal', 'UL', 1),
(2679, 175, 'Alba', 'AB', 1),
(2680, 175, 'Arad', 'AR', 1),
(2681, 175, 'Arges', 'AG', 1),
(2682, 175, 'Bacau', 'BC', 1),
(2683, 175, 'Bihor', 'BH', 1),
(2684, 175, 'Bistrita-Nasaud', 'BN', 1),
(2685, 175, 'Botosani', 'BT', 1),
(2686, 175, 'Brasov', 'BV', 1),
(2687, 175, 'Braila', 'BR', 1),
(2688, 175, 'Bucuresti', 'B', 1),
(2689, 175, 'Buzau', 'BZ', 1),
(2690, 175, 'Caras-Severin', 'CS', 1),
(2691, 175, 'Calarasi', 'CL', 1),
(2692, 175, 'Cluj', 'CJ', 1),
(2693, 175, 'Constanta', 'CT', 1),
(2694, 175, 'Covasna', 'CV', 1),
(2695, 175, 'Dimbovita', 'DB', 1),
(2696, 175, 'Dolj', 'DJ', 1),
(2697, 175, 'Galati', 'GL', 1),
(2698, 175, 'Giurgiu', 'GR', 1),
(2699, 175, 'Gorj', 'GJ', 1),
(2700, 175, 'Harghita', 'HR', 1),
(2701, 175, 'Hunedoara', 'HD', 1),
(2702, 175, 'Ialomita', 'IL', 1),
(2703, 175, 'Iasi', 'IS', 1),
(2704, 175, 'Ilfov', 'IF', 1),
(2705, 175, 'Maramures', 'MM', 1),
(2706, 175, 'Mehedinti', 'MH', 1),
(2707, 175, 'Mures', 'MS', 1),
(2708, 175, 'Neamt', 'NT', 1),
(2709, 175, 'Olt', 'OT', 1),
(2710, 175, 'Prahova', 'PH', 1),
(2711, 175, 'Satu-Mare', 'SM', 1),
(2712, 175, 'Salaj', 'SJ', 1),
(2713, 175, 'Sibiu', 'SB', 1),
(2714, 175, 'Suceava', 'SV', 1),
(2715, 175, 'Teleorman', 'TR', 1),
(2716, 175, 'Timis', 'TM', 1),
(2717, 175, 'Tulcea', 'TL', 1),
(2718, 175, 'Vaslui', 'VS', 1),
(2719, 175, 'Valcea', 'VL', 1),
(2720, 175, 'Vrancea', 'VN', 1),
(2721, 176, 'Abakan', 'AB', 1),
(2722, 176, 'Aginskoye', 'AG', 1),
(2723, 176, 'Anadyr', 'AN', 1),
(2724, 176, 'Arkahangelsk', 'AR', 1),
(2725, 176, 'Astrakhan', 'AS', 1),
(2726, 176, 'Barnaul', 'BA', 1),
(2727, 176, 'Belgorod', 'BE', 1),
(2728, 176, 'Birobidzhan', 'BI', 1),
(2729, 176, 'Blagoveshchensk', 'BL', 1),
(2730, 176, 'Bryansk', 'BR', 1),
(2731, 176, 'Cheboksary', 'CH', 1),
(2732, 176, 'Chelyabinsk', 'CL', 1),
(2733, 176, 'Cherkessk', 'CR', 1),
(2734, 176, 'Chita', 'CI', 1),
(2735, 176, 'Dudinka', 'DU', 1),
(2736, 176, 'Elista', 'EL', 1),
(2738, 176, 'Gorno-Altaysk', 'GA', 1),
(2739, 176, 'Groznyy', 'GR', 1),
(2740, 176, 'Irkutsk', 'IR', 1),
(2741, 176, 'Ivanovo', 'IV', 1),
(2742, 176, 'Izhevsk', 'IZ', 1),
(2743, 176, 'Kalinigrad', 'KA', 1),
(2744, 176, 'Kaluga', 'KL', 1),
(2745, 176, 'Kasnodar', 'KS', 1),
(2746, 176, 'Kazan', 'KZ', 1),
(2747, 176, 'Kemerovo', 'KE', 1),
(2748, 176, 'Khabarovsk', 'KH', 1),
(2749, 176, 'Khanty-Mansiysk', 'KM', 1),
(2750, 176, 'Kostroma', 'KO', 1),
(2751, 176, 'Krasnodar', 'KR', 1),
(2752, 176, 'Krasnoyarsk', 'KN', 1),
(2753, 176, 'Kudymkar', 'KU', 1),
(2754, 176, 'Kurgan', 'KG', 1),
(2755, 176, 'Kursk', 'KK', 1),
(2756, 176, 'Kyzyl', 'KY', 1),
(2757, 176, 'Lipetsk', 'LI', 1),
(2758, 176, 'Magadan', 'MA', 1),
(2759, 176, 'Makhachkala', 'MK', 1),
(2760, 176, 'Maykop', 'MY', 1),
(2761, 176, 'Moscow', 'MO', 1),
(2762, 176, 'Murmansk', 'MU', 1),
(2763, 176, 'Nalchik', 'NA', 1),
(2764, 176, 'Naryan Mar', 'NR', 1),
(2765, 176, 'Nazran', 'NZ', 1),
(2766, 176, 'Nizhniy Novgorod', 'NI', 1),
(2767, 176, 'Novgorod', 'NO', 1),
(2768, 176, 'Novosibirsk', 'NV', 1),
(2769, 176, 'Omsk', 'OM', 1),
(2770, 176, 'Orel', 'OR', 1),
(2771, 176, 'Orenburg', 'OE', 1),
(2772, 176, 'Palana', 'PA', 1),
(2773, 176, 'Penza', 'PE', 1),
(2774, 176, 'Perm', 'PR', 1),
(2775, 176, 'Petropavlovsk-Kamchatskiy', 'PK', 1),
(2776, 176, 'Petrozavodsk', 'PT', 1),
(2777, 176, 'Pskov', 'PS', 1),
(2778, 176, 'Rostov-na-Donu', 'RO', 1),
(2779, 176, 'Ryazan', 'RY', 1),
(2780, 176, 'Salekhard', 'SL', 1),
(2781, 176, 'Samara', 'SA', 1),
(2782, 176, 'Saransk', 'SR', 1),
(2783, 176, 'Saratov', 'SV', 1),
(2784, 176, 'Smolensk', 'SM', 1),
(2785, 176, 'St. Petersburg', 'SP', 1),
(2786, 176, 'Stavropol', 'ST', 1),
(2787, 176, 'Syktyvkar', 'SY', 1),
(2788, 176, 'Tambov', 'TA', 1),
(2789, 176, 'Tomsk', 'TO', 1),
(2790, 176, 'Tula', 'TU', 1),
(2791, 176, 'Tura', 'TR', 1),
(2792, 176, 'Tver', 'TV', 1),
(2793, 176, 'Tyumen', 'TY', 1),
(2794, 176, 'Ufa', 'UF', 1),
(2795, 176, 'Ul\'yanovsk', 'UL', 1),
(2796, 176, 'Ulan-Ude', 'UU', 1),
(2797, 176, 'Ust\'-Ordynskiy', 'US', 1),
(2798, 176, 'Vladikavkaz', 'VL', 1),
(2799, 176, 'Vladimir', 'VA', 1),
(2800, 176, 'Vladivostok', 'VV', 1),
(2801, 176, 'Volgograd', 'VG', 1),
(2802, 176, 'Vologda', 'VD', 1),
(2803, 176, 'Voronezh', 'VO', 1),
(2804, 176, 'Vyatka', 'VY', 1),
(2805, 176, 'Yakutsk', 'YA', 1),
(2806, 176, 'Yaroslavl', 'YR', 1),
(2807, 176, 'Yekaterinburg', 'YE', 1),
(2808, 176, 'Yoshkar-Ola', 'YO', 1),
(2809, 177, 'Butare', 'BU', 1),
(2810, 177, 'Byumba', 'BY', 1),
(2811, 177, 'Cyangugu', 'CY', 1),
(2812, 177, 'Gikongoro', 'GK', 1),
(2813, 177, 'Gisenyi', 'GS', 1),
(2814, 177, 'Gitarama', 'GT', 1),
(2815, 177, 'Kibungo', 'KG', 1),
(2816, 177, 'Kibuye', 'KY', 1),
(2817, 177, 'Kigali Rurale', 'KR', 1),
(2818, 177, 'Kigali-ville', 'KV', 1),
(2819, 177, 'Ruhengeri', 'RU', 1),
(2820, 177, 'Umutara', 'UM', 1),
(2821, 178, 'Christ Church Nichola Town', 'CCN', 1),
(2822, 178, 'Saint Anne Sandy Point', 'SAS', 1),
(2823, 178, 'Saint George Basseterre', 'SGB', 1),
(2824, 178, 'Saint George Gingerland', 'SGG', 1),
(2825, 178, 'Saint James Windward', 'SJW', 1),
(2826, 178, 'Saint John Capesterre', 'SJC', 1),
(2827, 178, 'Saint John Figtree', 'SJF', 1),
(2828, 178, 'Saint Mary Cayon', 'SMC', 1),
(2829, 178, 'Saint Paul Capesterre', 'CAP', 1),
(2830, 178, 'Saint Paul Charlestown', 'CHA', 1),
(2831, 178, 'Saint Peter Basseterre', 'SPB', 1),
(2832, 178, 'Saint Thomas Lowland', 'STL', 1),
(2833, 178, 'Saint Thomas Middle Island', 'STM', 1),
(2834, 178, 'Trinity Palmetto Point', 'TPP', 1),
(2835, 179, 'Anse-la-Raye', 'AR', 1),
(2836, 179, 'Castries', 'CA', 1),
(2837, 179, 'Choiseul', 'CH', 1),
(2838, 179, 'Dauphin', 'DA', 1),
(2839, 179, 'Dennery', 'DE', 1),
(2840, 179, 'Gros-Islet', 'GI', 1),
(2841, 179, 'Laborie', 'LA', 1),
(2842, 179, 'Micoud', 'MI', 1),
(2843, 179, 'Praslin', 'PR', 1),
(2844, 179, 'Soufriere', 'SO', 1),
(2845, 179, 'Vieux-Fort', 'VF', 1),
(2846, 180, 'Charlotte', 'C', 1),
(2847, 180, 'Grenadines', 'R', 1),
(2848, 180, 'Saint Andrew', 'A', 1),
(2849, 180, 'Saint David', 'D', 1),
(2850, 180, 'Saint George', 'G', 1),
(2851, 180, 'Saint Patrick', 'P', 1),
(2852, 181, 'A\'ana', 'AN', 1),
(2853, 181, 'Aiga-i-le-Tai', 'AI', 1),
(2854, 181, 'Atua', 'AT', 1),
(2855, 181, 'Fa\'asaleleaga', 'FA', 1),
(2856, 181, 'Gaga\'emauga', 'GE', 1),
(2857, 181, 'Gagaifomauga', 'GF', 1),
(2858, 181, 'Palauli', 'PA', 1),
(2859, 181, 'Satupa\'itea', 'SA', 1),
(2860, 181, 'Tuamasaga', 'TU', 1),
(2861, 181, 'Va\'a-o-Fonoti', 'VF', 1),
(2862, 181, 'Vaisigano', 'VS', 1),
(2863, 182, 'Acquaviva', 'AC', 1),
(2864, 182, 'Borgo Maggiore', 'BM', 1),
(2865, 182, 'Chiesanuova', 'CH', 1),
(2866, 182, 'Domagnano', 'DO', 1),
(2867, 182, 'Faetano', 'FA', 1),
(2868, 182, 'Fiorentino', 'FI', 1),
(2869, 182, 'Montegiardino', 'MO', 1),
(2870, 182, 'Citta di San Marino', 'SM', 1),
(2871, 182, 'Serravalle', 'SE', 1),
(2872, 183, 'Sao Tome', 'S', 1),
(2873, 183, 'Principe', 'P', 1),
(2874, 184, 'Al Bahah', 'BH', 1),
(2875, 184, 'Al Hudud ash Shamaliyah', 'HS', 1),
(2876, 184, 'Al Jawf', 'JF', 1),
(2877, 184, 'Al Madinah', 'MD', 1),
(2878, 184, 'Al Qasim', 'QS', 1),
(2879, 184, 'Ar Riyad', 'RD', 1),
(2880, 184, 'Ash Sharqiyah (Eastern)', 'AQ', 1),
(2881, 184, '\'Asir', 'AS', 1),
(2882, 184, 'Ha\'il', 'HL', 1),
(2883, 184, 'Jizan', 'JZ', 1),
(2884, 184, 'Makkah', 'ML', 1),
(2885, 184, 'Najran', 'NR', 1),
(2886, 184, 'Tabuk', 'TB', 1),
(2887, 185, 'Dakar', 'DA', 1),
(2888, 185, 'Diourbel', 'DI', 1),
(2889, 185, 'Fatick', 'FA', 1),
(2890, 185, 'Kaolack', 'KA', 1),
(2891, 185, 'Kolda', 'KO', 1),
(2892, 185, 'Louga', 'LO', 1),
(2893, 185, 'Matam', 'MA', 1),
(2894, 185, 'Saint-Louis', 'SL', 1),
(2895, 185, 'Tambacounda', 'TA', 1),
(2896, 185, 'Thies', 'TH', 1),
(2897, 185, 'Ziguinchor', 'ZI', 1),
(2898, 186, 'Anse aux Pins', 'AP', 1),
(2899, 186, 'Anse Boileau', 'AB', 1),
(2900, 186, 'Anse Etoile', 'AE', 1),
(2901, 186, 'Anse Louis', 'AL', 1),
(2902, 186, 'Anse Royale', 'AR', 1),
(2903, 186, 'Baie Lazare', 'BL', 1),
(2904, 186, 'Baie Sainte Anne', 'BS', 1),
(2905, 186, 'Beau Vallon', 'BV', 1),
(2906, 186, 'Bel Air', 'BA', 1),
(2907, 186, 'Bel Ombre', 'BO', 1),
(2908, 186, 'Cascade', 'CA', 1),
(2909, 186, 'Glacis', 'GL', 1),
(2910, 186, 'Grand\' Anse (on Mahe)', 'GM', 1),
(2911, 186, 'Grand\' Anse (on Praslin)', 'GP', 1),
(2912, 186, 'La Digue', 'DG', 1),
(2913, 186, 'La Riviere Anglaise', 'RA', 1),
(2914, 186, 'Mont Buxton', 'MB', 1),
(2915, 186, 'Mont Fleuri', 'MF', 1),
(2916, 186, 'Plaisance', 'PL', 1),
(2917, 186, 'Pointe La Rue', 'PR', 1),
(2918, 186, 'Port Glaud', 'PG', 1),
(2919, 186, 'Saint Louis', 'SL', 1),
(2920, 186, 'Takamaka', 'TA', 1),
(2921, 187, 'Eastern', 'E', 1),
(2922, 187, 'Northern', 'N', 1),
(2923, 187, 'Southern', 'S', 1),
(2924, 187, 'Western', 'W', 1),
(2925, 189, 'Banskobystrický', 'BA', 1),
(2926, 189, 'Bratislavský', 'BR', 1),
(2927, 189, 'Košický', 'KO', 1),
(2928, 189, 'Nitriansky', 'NI', 1),
(2929, 189, 'Prešovský', 'PR', 1),
(2930, 189, 'Trenčiansky', 'TC', 1),
(2931, 189, 'Trnavský', 'TV', 1),
(2932, 189, 'Žilinský', 'ZI', 1),
(2933, 191, 'Central', 'CE', 1),
(2934, 191, 'Choiseul', 'CH', 1),
(2935, 191, 'Guadalcanal', 'GC', 1),
(2936, 191, 'Honiara', 'HO', 1),
(2937, 191, 'Isabel', 'IS', 1),
(2938, 191, 'Makira', 'MK', 1),
(2939, 191, 'Malaita', 'ML', 1),
(2940, 191, 'Rennell and Bellona', 'RB', 1),
(2941, 191, 'Temotu', 'TM', 1),
(2942, 191, 'Western', 'WE', 1),
(2943, 192, 'Awdal', 'AW', 1),
(2944, 192, 'Bakool', 'BK', 1),
(2945, 192, 'Banaadir', 'BN', 1),
(2946, 192, 'Bari', 'BR', 1),
(2947, 192, 'Bay', 'BY', 1),
(2948, 192, 'Galguduud', 'GA', 1),
(2949, 192, 'Gedo', 'GE', 1),
(2950, 192, 'Hiiraan', 'HI', 1),
(2951, 192, 'Jubbada Dhexe', 'JD', 1),
(2952, 192, 'Jubbada Hoose', 'JH', 1),
(2953, 192, 'Mudug', 'MU', 1),
(2954, 192, 'Nugaal', 'NU', 1),
(2955, 192, 'Sanaag', 'SA', 1),
(2956, 192, 'Shabeellaha Dhexe', 'SD', 1),
(2957, 192, 'Shabeellaha Hoose', 'SH', 1),
(2958, 192, 'Sool', 'SL', 1),
(2959, 192, 'Togdheer', 'TO', 1),
(2960, 192, 'Woqooyi Galbeed', 'WG', 1),
(2961, 193, 'Eastern Cape', 'EC', 1),
(2962, 193, 'Free State', 'FS', 1),
(2963, 193, 'Gauteng', 'GT', 1),
(2964, 193, 'KwaZulu-Natal', 'KN', 1),
(2965, 193, 'Limpopo', 'LP', 1),
(2966, 193, 'Mpumalanga', 'MP', 1),
(2967, 193, 'North West', 'NW', 1),
(2968, 193, 'Northern Cape', 'NC', 1),
(2969, 193, 'Western Cape', 'WC', 1),
(2970, 195, 'La Coru&ntilde;a', 'CA', 1),
(2971, 195, '&Aacute;lava', 'AL', 1),
(2972, 195, 'Albacete', 'AB', 1),
(2973, 195, 'Alicante', 'AC', 1),
(2974, 195, 'Almeria', 'AM', 1),
(2975, 195, 'Asturias', 'AS', 1),
(2976, 195, '&Aacute;vila', 'AV', 1),
(2977, 195, 'Badajoz', 'BJ', 1),
(2978, 195, 'Baleares', 'IB', 1),
(2979, 195, 'Barcelona', 'BA', 1),
(2980, 195, 'Burgos', 'BU', 1),
(2981, 195, 'C&aacute;ceres', 'CC', 1),
(2982, 195, 'C&aacute;diz', 'CZ', 1),
(2983, 195, 'Cantabria', 'CT', 1),
(2984, 195, 'Castell&oacute;n', 'CL', 1),
(2985, 195, 'Ceuta', 'CE', 1),
(2986, 195, 'Ciudad Real', 'CR', 1),
(2987, 195, 'C&oacute;rdoba', 'CD', 1),
(2988, 195, 'Cuenca', 'CU', 1),
(2989, 195, 'Girona', 'GI', 1),
(2990, 195, 'Granada', 'GD', 1),
(2991, 195, 'Guadalajara', 'GJ', 1),
(2992, 195, 'Guip&uacute;zcoa', 'GP', 1),
(2993, 195, 'Huelva', 'HL', 1),
(2994, 195, 'Huesca', 'HS', 1),
(2995, 195, 'Ja&eacute;n', 'JN', 1),
(2996, 195, 'La Rioja', 'RJ', 1),
(2997, 195, 'Las Palmas', 'PM', 1),
(2998, 195, 'Leon', 'LE', 1),
(2999, 195, 'Lleida', 'LL', 1),
(3000, 195, 'Lugo', 'LG', 1),
(3001, 195, 'Madrid', 'MD', 1),
(3002, 195, 'Malaga', 'MA', 1),
(3003, 195, 'Melilla', 'ML', 1),
(3004, 195, 'Murcia', 'MU', 1),
(3005, 195, 'Navarra', 'NV', 1),
(3006, 195, 'Ourense', 'OU', 1),
(3007, 195, 'Palencia', 'PL', 1),
(3008, 195, 'Pontevedra', 'PO', 1),
(3009, 195, 'Salamanca', 'SL', 1),
(3010, 195, 'Santa Cruz de Tenerife', 'SC', 1),
(3011, 195, 'Segovia', 'SG', 1),
(3012, 195, 'Sevilla', 'SV', 1),
(3013, 195, 'Soria', 'SO', 1),
(3014, 195, 'Tarragona', 'TA', 1),
(3015, 195, 'Teruel', 'TE', 1),
(3016, 195, 'Toledo', 'TO', 1),
(3017, 195, 'Valencia', 'VC', 1),
(3018, 195, 'Valladolid', 'VD', 1),
(3019, 195, 'Vizcaya', 'VZ', 1),
(3020, 195, 'Zamora', 'ZM', 1),
(3021, 195, 'Zaragoza', 'ZR', 1),
(3022, 196, 'Central', 'CE', 1),
(3023, 196, 'Eastern', 'EA', 1),
(3024, 196, 'North Central', 'NC', 1),
(3025, 196, 'Northern', 'NO', 1),
(3026, 196, 'North Western', 'NW', 1),
(3027, 196, 'Sabaragamuwa', 'SA', 1),
(3028, 196, 'Southern', 'SO', 1),
(3029, 196, 'Uva', 'UV', 1),
(3030, 196, 'Western', 'WE', 1),
(3032, 197, 'Saint Helena', 'S', 1),
(3034, 199, 'A\'ali an Nil', 'ANL', 1),
(3035, 199, 'Al Bahr al Ahmar', 'BAM', 1),
(3036, 199, 'Al Buhayrat', 'BRT', 1),
(3037, 199, 'Al Jazirah', 'JZR', 1),
(3038, 199, 'Al Khartum', 'KRT', 1),
(3039, 199, 'Al Qadarif', 'QDR', 1),
(3040, 199, 'Al Wahdah', 'WDH', 1),
(3041, 199, 'An Nil al Abyad', 'ANB', 1),
(3042, 199, 'An Nil al Azraq', 'ANZ', 1),
(3043, 199, 'Ash Shamaliyah', 'ASH', 1),
(3044, 199, 'Bahr al Jabal', 'BJA', 1),
(3045, 199, 'Gharb al Istiwa\'iyah', 'GIS', 1),
(3046, 199, 'Gharb Bahr al Ghazal', 'GBG', 1),
(3047, 199, 'Gharb Darfur', 'GDA', 1),
(3048, 199, 'Gharb Kurdufan', 'GKU', 1),
(3049, 199, 'Janub Darfur', 'JDA', 1),
(3050, 199, 'Janub Kurdufan', 'JKU', 1),
(3051, 199, 'Junqali', 'JQL', 1),
(3052, 199, 'Kassala', 'KSL', 1),
(3053, 199, 'Nahr an Nil', 'NNL', 1),
(3054, 199, 'Shamal Bahr al Ghazal', 'SBG', 1),
(3055, 199, 'Shamal Darfur', 'SDA', 1),
(3056, 199, 'Shamal Kurdufan', 'SKU', 1),
(3057, 199, 'Sharq al Istiwa\'iyah', 'SIS', 1),
(3058, 199, 'Sinnar', 'SNR', 1),
(3059, 199, 'Warab', 'WRB', 1),
(3060, 200, 'Brokopondo', 'BR', 1),
(3061, 200, 'Commewijne', 'CM', 1),
(3062, 200, 'Coronie', 'CR', 1),
(3063, 200, 'Marowijne', 'MA', 1),
(3064, 200, 'Nickerie', 'NI', 1),
(3065, 200, 'Para', 'PA', 1),
(3066, 200, 'Paramaribo', 'PM', 1),
(3067, 200, 'Saramacca', 'SA', 1),
(3068, 200, 'Sipaliwini', 'SI', 1),
(3069, 200, 'Wanica', 'WA', 1),
(3070, 202, 'Hhohho', 'H', 1),
(3071, 202, 'Lubombo', 'L', 1),
(3072, 202, 'Manzini', 'M', 1),
(3073, 202, 'Shishelweni', 'S', 1),
(3074, 203, 'Blekinge', 'K', 1),
(3075, 203, 'Dalarna', 'W', 1),
(3076, 203, 'Gävleborg', 'X', 1),
(3077, 203, 'Gotland', 'I', 1),
(3078, 203, 'Halland', 'N', 1),
(3079, 203, 'Jämtland', 'Z', 1),
(3080, 203, 'Jönköping', 'F', 1),
(3081, 203, 'Kalmar', 'H', 1),
(3082, 203, 'Kronoberg', 'G', 1),
(3083, 203, 'Norrbotten', 'BD', 1),
(3084, 203, 'Örebro', 'T', 1),
(3085, 203, 'Östergötland', 'E', 1),
(3086, 203, 'Sk&aring;ne', 'M', 1),
(3087, 203, 'Södermanland', 'D', 1),
(3088, 203, 'Stockholm', 'AB', 1),
(3089, 203, 'Uppsala', 'C', 1),
(3090, 203, 'Värmland', 'S', 1),
(3091, 203, 'Västerbotten', 'AC', 1),
(3092, 203, 'Västernorrland', 'Y', 1),
(3093, 203, 'Västmanland', 'U', 1),
(3094, 203, 'Västra Götaland', 'O', 1),
(3095, 204, 'Aargau', 'AG', 1),
(3096, 204, 'Appenzell Ausserrhoden', 'AR', 1),
(3097, 204, 'Appenzell Innerrhoden', 'AI', 1),
(3098, 204, 'Basel-Stadt', 'BS', 1),
(3099, 204, 'Basel-Landschaft', 'BL', 1),
(3100, 204, 'Bern', 'BE', 1),
(3101, 204, 'Fribourg', 'FR', 1),
(3102, 204, 'Gen&egrave;ve', 'GE', 1),
(3103, 204, 'Glarus', 'GL', 1),
(3104, 204, 'Graubünden', 'GR', 1),
(3105, 204, 'Jura', 'JU', 1),
(3106, 204, 'Luzern', 'LU', 1),
(3107, 204, 'Neuch&acirc;tel', 'NE', 1),
(3108, 204, 'Nidwald', 'NW', 1),
(3109, 204, 'Obwald', 'OW', 1),
(3110, 204, 'St. Gallen', 'SG', 1),
(3111, 204, 'Schaffhausen', 'SH', 1),
(3112, 204, 'Schwyz', 'SZ', 1),
(3113, 204, 'Solothurn', 'SO', 1),
(3114, 204, 'Thurgau', 'TG', 1),
(3115, 204, 'Ticino', 'TI', 1),
(3116, 204, 'Uri', 'UR', 1),
(3117, 204, 'Valais', 'VS', 1),
(3118, 204, 'Vaud', 'VD', 1),
(3119, 204, 'Zug', 'ZG', 1),
(3120, 204, 'Zürich', 'ZH', 1),
(3121, 205, 'Al Hasakah', 'HA', 1),
(3122, 205, 'Al Ladhiqiyah', 'LA', 1),
(3123, 205, 'Al Qunaytirah', 'QU', 1),
(3124, 205, 'Ar Raqqah', 'RQ', 1),
(3125, 205, 'As Suwayda', 'SU', 1),
(3126, 205, 'Dara', 'DA', 1),
(3127, 205, 'Dayr az Zawr', 'DZ', 1),
(3128, 205, 'Dimashq', 'DI', 1),
(3129, 205, 'Halab', 'HL', 1),
(3130, 205, 'Hamah', 'HM', 1),
(3131, 205, 'Hims', 'HI', 1),
(3132, 205, 'Idlib', 'ID', 1),
(3133, 205, 'Rif Dimashq', 'RD', 1),
(3134, 205, 'Tartus', 'TA', 1),
(3135, 206, 'Chang-hua', 'CH', 1),
(3136, 206, 'Chia-i', 'CI', 1);
INSERT INTO `ocn8_zone` (`zone_id`, `country_id`, `name`, `code`, `status`) VALUES
(3137, 206, 'Hsin-chu', 'HS', 1),
(3138, 206, 'Hua-lien', 'HL', 1),
(3139, 206, 'I-lan', 'IL', 1),
(3140, 206, 'Kao-hsiung county', 'KH', 1),
(3141, 206, 'Kin-men', 'KM', 1),
(3142, 206, 'Lien-chiang', 'LC', 1),
(3143, 206, 'Miao-li', 'ML', 1),
(3144, 206, 'Nan-t\'ou', 'NT', 1),
(3145, 206, 'P\'eng-hu', 'PH', 1),
(3146, 206, 'P\'ing-tung', 'PT', 1),
(3147, 206, 'T\'ai-chung', 'TG', 1),
(3148, 206, 'T\'ai-nan', 'TA', 1),
(3149, 206, 'T\'ai-pei county', 'TP', 1),
(3150, 206, 'T\'ai-tung', 'TT', 1),
(3151, 206, 'T\'ao-yuan', 'TY', 1),
(3152, 206, 'Yun-lin', 'YL', 1),
(3153, 206, 'Chia-i city', 'CC', 1),
(3154, 206, 'Chi-lung', 'CL', 1),
(3155, 206, 'Hsin-chu', 'HC', 1),
(3156, 206, 'T\'ai-chung', 'TH', 1),
(3157, 206, 'T\'ai-nan', 'TN', 1),
(3158, 206, 'Kao-hsiung city', 'KC', 1),
(3159, 206, 'T\'ai-pei city', 'TC', 1),
(3160, 207, 'Gorno-Badakhstan', 'GB', 1),
(3161, 207, 'Khatlon', 'KT', 1),
(3162, 207, 'Sughd', 'SU', 1),
(3163, 208, 'Arusha', 'AR', 1),
(3164, 208, 'Dar es Salaam', 'DS', 1),
(3165, 208, 'Dodoma', 'DO', 1),
(3166, 208, 'Iringa', 'IR', 1),
(3167, 208, 'Kagera', 'KA', 1),
(3168, 208, 'Kigoma', 'KI', 1),
(3169, 208, 'Kilimanjaro', 'KJ', 1),
(3170, 208, 'Lindi', 'LN', 1),
(3171, 208, 'Manyara', 'MY', 1),
(3172, 208, 'Mara', 'MR', 1),
(3173, 208, 'Mbeya', 'MB', 1),
(3174, 208, 'Morogoro', 'MO', 1),
(3175, 208, 'Mtwara', 'MT', 1),
(3176, 208, 'Mwanza', 'MW', 1),
(3177, 208, 'Pemba North', 'PN', 1),
(3178, 208, 'Pemba South', 'PS', 1),
(3179, 208, 'Pwani', 'PW', 1),
(3180, 208, 'Rukwa', 'RK', 1),
(3181, 208, 'Ruvuma', 'RV', 1),
(3182, 208, 'Shinyanga', 'SH', 1),
(3183, 208, 'Singida', 'SI', 1),
(3184, 208, 'Tabora', 'TB', 1),
(3185, 208, 'Tanga', 'TN', 1),
(3186, 208, 'Zanzibar Central/South', 'ZC', 1),
(3187, 208, 'Zanzibar North', 'ZN', 1),
(3188, 208, 'Zanzibar Urban/West', 'ZU', 1),
(3189, 209, 'Amnat Charoen', 'Amnat Charoen', 1),
(3190, 209, 'Ang Thong', 'Ang Thong', 1),
(3191, 209, 'Ayutthaya', 'Ayutthaya', 1),
(3192, 209, 'Bangkok', 'Bangkok', 1),
(3193, 209, 'Buriram', 'Buriram', 1),
(3194, 209, 'Chachoengsao', 'Chachoengsao', 1),
(3195, 209, 'Chai Nat', 'Chai Nat', 1),
(3196, 209, 'Chaiyaphum', 'Chaiyaphum', 1),
(3197, 209, 'Chanthaburi', 'Chanthaburi', 1),
(3198, 209, 'Chiang Mai', 'Chiang Mai', 1),
(3199, 209, 'Chiang Rai', 'Chiang Rai', 1),
(3200, 209, 'Chon Buri', 'Chon Buri', 1),
(3201, 209, 'Chumphon', 'Chumphon', 1),
(3202, 209, 'Kalasin', 'Kalasin', 1),
(3203, 209, 'Kamphaeng Phet', 'Kamphaeng Phet', 1),
(3204, 209, 'Kanchanaburi', 'Kanchanaburi', 1),
(3205, 209, 'Khon Kaen', 'Khon Kaen', 1),
(3206, 209, 'Krabi', 'Krabi', 1),
(3207, 209, 'Lampang', 'Lampang', 1),
(3208, 209, 'Lamphun', 'Lamphun', 1),
(3209, 209, 'Loei', 'Loei', 1),
(3210, 209, 'Lop Buri', 'Lop Buri', 1),
(3211, 209, 'Mae Hong Son', 'Mae Hong Son', 1),
(3212, 209, 'Maha Sarakham', 'Maha Sarakham', 1),
(3213, 209, 'Mukdahan', 'Mukdahan', 1),
(3214, 209, 'Nakhon Nayok', 'Nakhon Nayok', 1),
(3215, 209, 'Nakhon Pathom', 'Nakhon Pathom', 1),
(3216, 209, 'Nakhon Phanom', 'Nakhon Phanom', 1),
(3217, 209, 'Nakhon Ratchasima', 'Nakhon Ratchasima', 1),
(3218, 209, 'Nakhon Sawan', 'Nakhon Sawan', 1),
(3219, 209, 'Nakhon Si Thammarat', 'Nakhon Si Thammarat', 1),
(3220, 209, 'Nan', 'Nan', 1),
(3221, 209, 'Narathiwat', 'Narathiwat', 1),
(3222, 209, 'Nong Bua Lamphu', 'Nong Bua Lamphu', 1),
(3223, 209, 'Nong Khai', 'Nong Khai', 1),
(3224, 209, 'Nonthaburi', 'Nonthaburi', 1),
(3225, 209, 'Pathum Thani', 'Pathum Thani', 1),
(3226, 209, 'Pattani', 'Pattani', 1),
(3227, 209, 'Phangnga', 'Phangnga', 1),
(3228, 209, 'Phatthalung', 'Phatthalung', 1),
(3229, 209, 'Phayao', 'Phayao', 1),
(3230, 209, 'Phetchabun', 'Phetchabun', 1),
(3231, 209, 'Phetchaburi', 'Phetchaburi', 1),
(3232, 209, 'Phichit', 'Phichit', 1),
(3233, 209, 'Phitsanulok', 'Phitsanulok', 1),
(3234, 209, 'Phrae', 'Phrae', 1),
(3235, 209, 'Phuket', 'Phuket', 1),
(3236, 209, 'Prachin Buri', 'Prachin Buri', 1),
(3237, 209, 'Prachuap Khiri Khan', 'Prachuap Khiri Khan', 1),
(3238, 209, 'Ranong', 'Ranong', 1),
(3239, 209, 'Ratchaburi', 'Ratchaburi', 1),
(3240, 209, 'Rayong', 'Rayong', 1),
(3241, 209, 'Roi Et', 'Roi Et', 1),
(3242, 209, 'Sa Kaeo', 'Sa Kaeo', 1),
(3243, 209, 'Sakon Nakhon', 'Sakon Nakhon', 1),
(3244, 209, 'Samut Prakan', 'Samut Prakan', 1),
(3245, 209, 'Samut Sakhon', 'Samut Sakhon', 1),
(3246, 209, 'Samut Songkhram', 'Samut Songkhram', 1),
(3247, 209, 'Sara Buri', 'Sara Buri', 1),
(3248, 209, 'Satun', 'Satun', 1),
(3249, 209, 'Sing Buri', 'Sing Buri', 1),
(3250, 209, 'Sisaket', 'Sisaket', 1),
(3251, 209, 'Songkhla', 'Songkhla', 1),
(3252, 209, 'Sukhothai', 'Sukhothai', 1),
(3253, 209, 'Suphan Buri', 'Suphan Buri', 1),
(3254, 209, 'Surat Thani', 'Surat Thani', 1),
(3255, 209, 'Surin', 'Surin', 1),
(3256, 209, 'Tak', 'Tak', 1),
(3257, 209, 'Trang', 'Trang', 1),
(3258, 209, 'Trat', 'Trat', 1),
(3259, 209, 'Ubon Ratchathani', 'Ubon Ratchathani', 1),
(3260, 209, 'Udon Thani', 'Udon Thani', 1),
(3261, 209, 'Uthai Thani', 'Uthai Thani', 1),
(3262, 209, 'Uttaradit', 'Uttaradit', 1),
(3263, 209, 'Yala', 'Yala', 1),
(3264, 209, 'Yasothon', 'Yasothon', 1),
(3265, 210, 'Kara', 'K', 1),
(3266, 210, 'Plateaux', 'P', 1),
(3267, 210, 'Savanes', 'S', 1),
(3268, 210, 'Centrale', 'C', 1),
(3269, 210, 'Maritime', 'M', 1),
(3270, 211, 'Atafu', 'A', 1),
(3271, 211, 'Fakaofo', 'F', 1),
(3272, 211, 'Nukunonu', 'N', 1),
(3273, 212, 'Ha\'apai', 'H', 1),
(3274, 212, 'Tongatapu', 'T', 1),
(3275, 212, 'Vava\'u', 'V', 1),
(3276, 213, 'Couva/Tabaquite/Talparo', 'CT', 1),
(3277, 213, 'Diego Martin', 'DM', 1),
(3278, 213, 'Mayaro/Rio Claro', 'MR', 1),
(3279, 213, 'Penal/Debe', 'PD', 1),
(3280, 213, 'Princes Town', 'PT', 1),
(3281, 213, 'Sangre Grande', 'SG', 1),
(3282, 213, 'San Juan/Laventille', 'SL', 1),
(3283, 213, 'Siparia', 'SI', 1),
(3284, 213, 'Tunapuna/Piarco', 'TP', 1),
(3285, 213, 'Port of Spain', 'PS', 1),
(3286, 213, 'San Fernando', 'SF', 1),
(3287, 213, 'Arima', 'AR', 1),
(3288, 213, 'Point Fortin', 'PF', 1),
(3289, 213, 'Chaguanas', 'CH', 1),
(3290, 213, 'Tobago', 'TO', 1),
(3291, 214, 'Ariana', 'AR', 1),
(3292, 214, 'Beja', 'BJ', 1),
(3293, 214, 'Ben Arous', 'BA', 1),
(3294, 214, 'Bizerte', 'BI', 1),
(3295, 214, 'Gabes', 'GB', 1),
(3296, 214, 'Gafsa', 'GF', 1),
(3297, 214, 'Jendouba', 'JE', 1),
(3298, 214, 'Kairouan', 'KR', 1),
(3299, 214, 'Kasserine', 'KS', 1),
(3300, 214, 'Kebili', 'KB', 1),
(3301, 214, 'Kef', 'KF', 1),
(3302, 214, 'Mahdia', 'MH', 1),
(3303, 214, 'Manouba', 'MN', 1),
(3304, 214, 'Medenine', 'ME', 1),
(3305, 214, 'Monastir', 'MO', 1),
(3306, 214, 'Nabeul', 'NA', 1),
(3307, 214, 'Sfax', 'SF', 1),
(3308, 214, 'Sidi', 'SD', 1),
(3309, 214, 'Siliana', 'SL', 1),
(3310, 214, 'Sousse', 'SO', 1),
(3311, 214, 'Tataouine', 'TA', 1),
(3312, 214, 'Tozeur', 'TO', 1),
(3313, 214, 'Tunis', 'TU', 1),
(3314, 214, 'Zaghouan', 'ZA', 1),
(3315, 215, 'Adana', 'ADA', 1),
(3316, 215, 'Adıyaman', 'ADI', 1),
(3317, 215, 'Afyonkarahisar', 'AFY', 1),
(3318, 215, 'Ağrı', 'AGR', 1),
(3319, 215, 'Aksaray', 'AKS', 1),
(3320, 215, 'Amasya', 'AMA', 1),
(3321, 215, 'Ankara', 'ANK', 1),
(3322, 215, 'Antalya', 'ANT', 1),
(3323, 215, 'Ardahan', 'ARD', 1),
(3324, 215, 'Artvin', 'ART', 1),
(3325, 215, 'Aydın', 'AYI', 1),
(3326, 215, 'Balıkesir', 'BAL', 1),
(3327, 215, 'Bartın', 'BAR', 1),
(3328, 215, 'Batman', 'BAT', 1),
(3329, 215, 'Bayburt', 'BAY', 1),
(3330, 215, 'Bilecik', 'BIL', 1),
(3331, 215, 'Bingöl', 'BIN', 1),
(3332, 215, 'Bitlis', 'BIT', 1),
(3333, 215, 'Bolu', 'BOL', 1),
(3334, 215, 'Burdur', 'BRD', 1),
(3335, 215, 'Bursa', 'BRS', 1),
(3336, 215, 'Çanakkale', 'CKL', 1),
(3337, 215, 'Çankırı', 'CKR', 1),
(3338, 215, 'Çorum', 'COR', 1),
(3339, 215, 'Denizli', 'DEN', 1),
(3340, 215, 'Diyarbakır', 'DIY', 1),
(3341, 215, 'Düzce', 'DUZ', 1),
(3342, 215, 'Edirne', 'EDI', 1),
(3343, 215, 'Elazığ', 'ELA', 1),
(3344, 215, 'Erzincan', 'EZC', 1),
(3345, 215, 'Erzurum', 'EZR', 1),
(3346, 215, 'Eskişehir', 'ESK', 1),
(3347, 215, 'Gaziantep', 'GAZ', 1),
(3348, 215, 'Giresun', 'GIR', 1),
(3349, 215, 'Gümüşhane', 'GMS', 1),
(3350, 215, 'Hakkari', 'HKR', 1),
(3351, 215, 'Hatay', 'HTY', 1),
(3352, 215, 'Iğdır', 'IGD', 1),
(3353, 215, 'Isparta', 'ISP', 1),
(3354, 215, 'İstanbul', 'IST', 1),
(3355, 215, 'İzmir', 'IZM', 1),
(3356, 215, 'Kahramanmaraş', 'KAH', 1),
(3357, 215, 'Karabük', 'KRB', 1),
(3358, 215, 'Karaman', 'KRM', 1),
(3359, 215, 'Kars', 'KRS', 1),
(3360, 215, 'Kastamonu', 'KAS', 1),
(3361, 215, 'Kayseri', 'KAY', 1),
(3362, 215, 'Kilis', 'KLS', 1),
(3363, 215, 'Kırıkkale', 'KRK', 1),
(3364, 215, 'Kırklareli', 'KLR', 1),
(3365, 215, 'Kırşehir', 'KRH', 1),
(3366, 215, 'Kocaeli', 'KOC', 1),
(3367, 215, 'Konya', 'KON', 1),
(3368, 215, 'Kütahya', 'KUT', 1),
(3369, 215, 'Malatya', 'MAL', 1),
(3370, 215, 'Manisa', 'MAN', 1),
(3371, 215, 'Mardin', 'MAR', 1),
(3372, 215, 'Mersin', 'MER', 1),
(3373, 215, 'Muğla', 'MUG', 1),
(3374, 215, 'Muş', 'MUS', 1),
(3375, 215, 'Nevşehir', 'NEV', 1),
(3376, 215, 'Niğde', 'NIG', 1),
(3377, 215, 'Ordu', 'ORD', 1),
(3378, 215, 'Osmaniye', 'OSM', 1),
(3379, 215, 'Rize', 'RIZ', 1),
(3380, 215, 'Sakarya', 'SAK', 1),
(3381, 215, 'Samsun', 'SAM', 1),
(3382, 215, 'Şanlıurfa', 'SAN', 1),
(3383, 215, 'Siirt', 'SII', 1),
(3384, 215, 'Sinop', 'SIN', 1),
(3385, 215, 'Şırnak', 'SIR', 1),
(3386, 215, 'Sivas', 'SIV', 1),
(3387, 215, 'Tekirdağ', 'TEL', 1),
(3388, 215, 'Tokat', 'TOK', 1),
(3389, 215, 'Trabzon', 'TRA', 1),
(3390, 215, 'Tunceli', 'TUN', 1),
(3391, 215, 'Uşak', 'USK', 1),
(3392, 215, 'Van', 'VAN', 1),
(3393, 215, 'Yalova', 'YAL', 1),
(3394, 215, 'Yozgat', 'YOZ', 1),
(3395, 215, 'Zonguldak', 'ZON', 1),
(3396, 216, 'Ahal Welayaty', 'A', 1),
(3397, 216, 'Balkan Welayaty', 'B', 1),
(3398, 216, 'Dashhowuz Welayaty', 'D', 1),
(3399, 216, 'Lebap Welayaty', 'L', 1),
(3400, 216, 'Mary Welayaty', 'M', 1),
(3401, 217, 'Ambergris Cays', 'AC', 1),
(3402, 217, 'Dellis Cay', 'DC', 1),
(3403, 217, 'French Cay', 'FC', 1),
(3404, 217, 'Little Water Cay', 'LW', 1),
(3405, 217, 'Parrot Cay', 'RC', 1),
(3406, 217, 'Pine Cay', 'PN', 1),
(3407, 217, 'Salt Cay', 'SL', 1),
(3408, 217, 'Grand Turk', 'GT', 1),
(3409, 217, 'South Caicos', 'SC', 1),
(3410, 217, 'East Caicos', 'EC', 1),
(3411, 217, 'Middle Caicos', 'MC', 1),
(3412, 217, 'North Caicos', 'NC', 1),
(3413, 217, 'Providenciales', 'PR', 1),
(3414, 217, 'West Caicos', 'WC', 1),
(3415, 218, 'Nanumanga', 'NMG', 1),
(3416, 218, 'Niulakita', 'NLK', 1),
(3417, 218, 'Niutao', 'NTO', 1),
(3418, 218, 'Funafuti', 'FUN', 1),
(3419, 218, 'Nanumea', 'NME', 1),
(3420, 218, 'Nui', 'NUI', 1),
(3421, 218, 'Nukufetau', 'NFT', 1),
(3422, 218, 'Nukulaelae', 'NLL', 1),
(3423, 218, 'Vaitupu', 'VAI', 1),
(3424, 219, 'Kalangala', 'KAL', 1),
(3425, 219, 'Kampala', 'KMP', 1),
(3426, 219, 'Kayunga', 'KAY', 1),
(3427, 219, 'Kiboga', 'KIB', 1),
(3428, 219, 'Luwero', 'LUW', 1),
(3429, 219, 'Masaka', 'MAS', 1),
(3430, 219, 'Mpigi', 'MPI', 1),
(3431, 219, 'Mubende', 'MUB', 1),
(3432, 219, 'Mukono', 'MUK', 1),
(3433, 219, 'Nakasongola', 'NKS', 1),
(3434, 219, 'Rakai', 'RAK', 1),
(3435, 219, 'Sembabule', 'SEM', 1),
(3436, 219, 'Wakiso', 'WAK', 1),
(3437, 219, 'Bugiri', 'BUG', 1),
(3438, 219, 'Busia', 'BUS', 1),
(3439, 219, 'Iganga', 'IGA', 1),
(3440, 219, 'Jinja', 'JIN', 1),
(3441, 219, 'Kaberamaido', 'KAB', 1),
(3442, 219, 'Kamuli', 'KML', 1),
(3443, 219, 'Kapchorwa', 'KPC', 1),
(3444, 219, 'Katakwi', 'KTK', 1),
(3445, 219, 'Kumi', 'KUM', 1),
(3446, 219, 'Mayuge', 'MAY', 1),
(3447, 219, 'Mbale', 'MBA', 1),
(3448, 219, 'Pallisa', 'PAL', 1),
(3449, 219, 'Sironko', 'SIR', 1),
(3450, 219, 'Soroti', 'SOR', 1),
(3451, 219, 'Tororo', 'TOR', 1),
(3452, 219, 'Adjumani', 'ADJ', 1),
(3453, 219, 'Apac', 'APC', 1),
(3454, 219, 'Arua', 'ARU', 1),
(3455, 219, 'Gulu', 'GUL', 1),
(3456, 219, 'Kitgum', 'KIT', 1),
(3457, 219, 'Kotido', 'KOT', 1),
(3458, 219, 'Lira', 'LIR', 1),
(3459, 219, 'Moroto', 'MRT', 1),
(3460, 219, 'Moyo', 'MOY', 1),
(3461, 219, 'Nakapiripirit', 'NAK', 1),
(3462, 219, 'Nebbi', 'NEB', 1),
(3463, 219, 'Pader', 'PAD', 1),
(3464, 219, 'Yumbe', 'YUM', 1),
(3465, 219, 'Bundibugyo', 'BUN', 1),
(3466, 219, 'Bushenyi', 'BSH', 1),
(3467, 219, 'Hoima', 'HOI', 1),
(3468, 219, 'Kabale', 'KBL', 1),
(3469, 219, 'Kabarole', 'KAR', 1),
(3470, 219, 'Kamwenge', 'KAM', 1),
(3471, 219, 'Kanungu', 'KAN', 1),
(3472, 219, 'Kasese', 'KAS', 1),
(3473, 219, 'Kibaale', 'KBA', 1),
(3474, 219, 'Kisoro', 'KIS', 1),
(3475, 219, 'Kyenjojo', 'KYE', 1),
(3476, 219, 'Masindi', 'MSN', 1),
(3477, 219, 'Mbarara', 'MBR', 1),
(3478, 219, 'Ntungamo', 'NTU', 1),
(3479, 219, 'Rukungiri', 'RUK', 1),
(3480, 220, 'Cherkas\'ka Oblast\'', '71', 1),
(3481, 220, 'Chernihivs\'ka Oblast\'', '74', 1),
(3482, 220, 'Chernivets\'ka Oblast\'', '77', 1),
(3483, 220, 'Crimea', '43', 1),
(3484, 220, 'Dnipropetrovs\'ka Oblast\'', '12', 1),
(3485, 220, 'Donets\'ka Oblast\'', '14', 1),
(3486, 220, 'Ivano-Frankivs\'ka Oblast\'', '26', 1),
(3487, 220, 'Khersons\'ka Oblast\'', '65', 1),
(3488, 220, 'Khmel\'nyts\'ka Oblast\'', '68', 1),
(3489, 220, 'Kirovohrads\'ka Oblast\'', '35', 1),
(3490, 220, 'Kyiv', '30', 1),
(3491, 220, 'Kyivs\'ka Oblast\'', '32', 1),
(3492, 220, 'Luhans\'ka Oblast\'', '09', 1),
(3493, 220, 'L\'vivs\'ka Oblast\'', '46', 1),
(3494, 220, 'Mykolayivs\'ka Oblast\'', '48', 1),
(3495, 220, 'Odes\'ka Oblast\'', '51', 1),
(3496, 220, 'Poltavs\'ka Oblast\'', '53', 1),
(3497, 220, 'Rivnens\'ka Oblast\'', '56', 1),
(3498, 220, 'Sevastopol\'', '40', 1),
(3499, 220, 'Sums\'ka Oblast\'', '59', 1),
(3500, 220, 'Ternopil\'s\'ka Oblast\'', '61', 1),
(3501, 220, 'Vinnyts\'ka Oblast\'', '05', 1),
(3502, 220, 'Volyns\'ka Oblast\'', '07', 1),
(3503, 220, 'Zakarpats\'ka Oblast\'', '21', 1),
(3504, 220, 'Zaporiz\'ka Oblast\'', '23', 1),
(3505, 220, 'Zhytomyrs\'ka oblast\'', '18', 1),
(3506, 221, 'Abū Z̧aby', 'AZ', 1),
(3507, 221, '‘Ajmān', 'AJ', 1),
(3508, 221, 'Al Fujayrah', 'FU', 1),
(3509, 221, 'Ash Shāriqah', 'SH', 1),
(3510, 221, 'Dubai', 'DU', 1),
(3511, 221, 'Ra’s al Khaymah', 'RK', 1),
(3512, 221, 'Umm al Qaywayn', 'UQ', 1),
(3513, 222, 'Aberdeen', 'ABN', 1),
(3514, 222, 'Aberdeenshire', 'ABNS', 1),
(3515, 222, 'Anglesey', 'ANG', 1),
(3516, 222, 'Angus', 'AGS', 1),
(3517, 222, 'Argyll and Bute', 'ARY', 1),
(3518, 222, 'Bedfordshire', 'BEDS', 1),
(3519, 222, 'Berkshire', 'BERKS', 1),
(3520, 222, 'Blaenau Gwent', 'BLA', 1),
(3521, 222, 'Bridgend', 'BRI', 1),
(3522, 222, 'Bristol', 'BSTL', 1),
(3523, 222, 'Buckinghamshire', 'BUCKS', 1),
(3524, 222, 'Caerphilly', 'CAE', 1),
(3525, 222, 'Cambridgeshire', 'CAMBS', 1),
(3526, 222, 'Cardiff', 'CDF', 1),
(3527, 222, 'Carmarthenshire', 'CARM', 1),
(3528, 222, 'Ceredigion', 'CDGN', 1),
(3529, 222, 'Cheshire', 'CHES', 1),
(3530, 222, 'Clackmannanshire', 'CLACK', 1),
(3531, 222, 'Conwy', 'CON', 1),
(3532, 222, 'Cornwall', 'CORN', 1),
(3533, 222, 'Denbighshire', 'DNBG', 1),
(3534, 222, 'Derbyshire', 'DERBY', 1),
(3535, 222, 'Devon', 'DVN', 1),
(3536, 222, 'Dorset', 'DOR', 1),
(3537, 222, 'Dumfries and Galloway', 'DGL', 1),
(3538, 222, 'Dundee', 'DUND', 1),
(3539, 222, 'Durham', 'DHM', 1),
(3540, 222, 'East Ayrshire', 'ARYE', 1),
(3541, 222, 'East Dunbartonshire', 'DUNBE', 1),
(3542, 222, 'East Lothian', 'LOTE', 1),
(3543, 222, 'East Renfrewshire', 'RENE', 1),
(3544, 222, 'East Riding of Yorkshire', 'ERYS', 1),
(3545, 222, 'East Sussex', 'SXE', 1),
(3546, 222, 'Edinburgh', 'EDIN', 1),
(3547, 222, 'Essex', 'ESX', 1),
(3548, 222, 'Falkirk', 'FALK', 1),
(3549, 222, 'Fife', 'FFE', 1),
(3550, 222, 'Flintshire', 'FLINT', 1),
(3551, 222, 'Glasgow', 'GLAS', 1),
(3552, 222, 'Gloucestershire', 'GLOS', 1),
(3553, 222, 'Greater London', 'LDN', 1),
(3554, 222, 'Greater Manchester', 'MCH', 1),
(3555, 222, 'Gwynedd', 'GDD', 1),
(3556, 222, 'Hampshire', 'HANTS', 1),
(3557, 222, 'Herefordshire', 'HWR', 1),
(3558, 222, 'Hertfordshire', 'HERTS', 1),
(3559, 222, 'Highlands', 'HLD', 1),
(3560, 222, 'Inverclyde', 'IVER', 1),
(3561, 222, 'Isle of Wight', 'IOW', 1),
(3562, 222, 'Kent', 'KNT', 1),
(3563, 222, 'Lancashire', 'LANCS', 1),
(3564, 222, 'Leicestershire', 'LEICS', 1),
(3565, 222, 'Lincolnshire', 'LINCS', 1),
(3566, 222, 'Merseyside', 'MSY', 1),
(3567, 222, 'Merthyr Tydfil', 'MERT', 1),
(3568, 222, 'Midlothian', 'MLOT', 1),
(3569, 222, 'Monmouthshire', 'MMOUTH', 1),
(3570, 222, 'Moray', 'MORAY', 1),
(3571, 222, 'Neath Port Talbot', 'NPRTAL', 1),
(3572, 222, 'Newport', 'NEWPT', 1),
(3573, 222, 'Norfolk', 'NOR', 1),
(3574, 222, 'North Ayrshire', 'ARYN', 1),
(3575, 222, 'North Lanarkshire', 'LANN', 1),
(3576, 222, 'North Yorkshire', 'YSN', 1),
(3577, 222, 'Northamptonshire', 'NHM', 1),
(3578, 222, 'Northumberland', 'NLD', 1),
(3579, 222, 'Nottinghamshire', 'NOT', 1),
(3580, 222, 'Orkney Islands', 'ORK', 1),
(3581, 222, 'Oxfordshire', 'OFE', 1),
(3582, 222, 'Pembrokeshire', 'PEM', 1),
(3583, 222, 'Perth and Kinross', 'PERTH', 1),
(3584, 222, 'Powys', 'PWS', 1),
(3585, 222, 'Renfrewshire', 'REN', 1),
(3586, 222, 'Rhondda Cynon Taff', 'RHON', 1),
(3587, 222, 'Rutland', 'RUT', 1),
(3588, 222, 'Scottish Borders', 'BOR', 1),
(3589, 222, 'Shetland Islands', 'SHET', 1),
(3590, 222, 'Shropshire', 'SPE', 1),
(3591, 222, 'Somerset', 'SOM', 1),
(3592, 222, 'South Ayrshire', 'ARYS', 1),
(3593, 222, 'South Lanarkshire', 'LANS', 1),
(3594, 222, 'South Yorkshire', 'YSS', 1),
(3595, 222, 'Staffordshire', 'SFD', 1),
(3596, 222, 'Stirling', 'STIR', 1),
(3597, 222, 'Suffolk', 'SFK', 1),
(3598, 222, 'Surrey', 'SRY', 1),
(3599, 222, 'Swansea', 'SWAN', 1),
(3600, 222, 'Torfaen', 'TORF', 1),
(3601, 222, 'Tyne and Wear', 'TWR', 1),
(3602, 222, 'Vale of Glamorgan', 'VGLAM', 1),
(3603, 222, 'Warwickshire', 'WARKS', 1),
(3604, 222, 'West Dunbartonshire', 'WDUN', 1),
(3605, 222, 'West Lothian', 'WLOT', 1),
(3606, 222, 'West Midlands', 'WMD', 1),
(3607, 222, 'West Sussex', 'SXW', 1),
(3608, 222, 'West Yorkshire', 'YSW', 1),
(3609, 222, 'Western Isles', 'WIL', 1),
(3610, 222, 'Wiltshire', 'WLT', 1),
(3611, 222, 'Worcestershire', 'WORCS', 1),
(3612, 222, 'Wrexham', 'WRX', 1),
(3613, 223, 'Alabama', 'AL', 1),
(3614, 223, 'Alaska', 'AK', 1),
(3615, 223, 'American Samoa', 'AS', 1),
(3616, 223, 'Arizona', 'AZ', 1),
(3617, 223, 'Arkansas', 'AR', 1),
(3618, 223, 'Armed Forces Africa', 'AF', 1),
(3619, 223, 'Armed Forces Americas', 'AA', 1),
(3620, 223, 'Armed Forces Canada', 'AC', 1),
(3621, 223, 'Armed Forces Europe', 'AE', 1),
(3622, 223, 'Armed Forces Middle East', 'AM', 1),
(3623, 223, 'Armed Forces Pacific', 'AP', 1),
(3624, 223, 'California', 'CA', 1),
(3625, 223, 'Colorado', 'CO', 1),
(3626, 223, 'Connecticut', 'CT', 1),
(3627, 223, 'Delaware', 'DE', 1),
(3628, 223, 'District of Columbia', 'DC', 1),
(3629, 223, 'Federated States Of Micronesia', 'FM', 1),
(3630, 223, 'Florida', 'FL', 1),
(3631, 223, 'Georgia', 'GA', 1),
(3632, 223, 'Guam', 'GU', 1),
(3633, 223, 'Hawaii', 'HI', 1),
(3634, 223, 'Idaho', 'ID', 1),
(3635, 223, 'Illinois', 'IL', 1),
(3636, 223, 'Indiana', 'IN', 1),
(3637, 223, 'Iowa', 'IA', 1),
(3638, 223, 'Kansas', 'KS', 1),
(3639, 223, 'Kentucky', 'KY', 1),
(3640, 223, 'Louisiana', 'LA', 1),
(3641, 223, 'Maine', 'ME', 1),
(3642, 223, 'Marshall Islands', 'MH', 1),
(3643, 223, 'Maryland', 'MD', 1),
(3644, 223, 'Massachusetts', 'MA', 1),
(3645, 223, 'Michigan', 'MI', 1),
(3646, 223, 'Minnesota', 'MN', 1),
(3647, 223, 'Mississippi', 'MS', 1),
(3648, 223, 'Missouri', 'MO', 1),
(3649, 223, 'Montana', 'MT', 1),
(3650, 223, 'Nebraska', 'NE', 1),
(3651, 223, 'Nevada', 'NV', 1),
(3652, 223, 'New Hampshire', 'NH', 1),
(3653, 223, 'New Jersey', 'NJ', 1),
(3654, 223, 'New Mexico', 'NM', 1),
(3655, 223, 'New York', 'NY', 1),
(3656, 223, 'North Carolina', 'NC', 1),
(3657, 223, 'North Dakota', 'ND', 1),
(3658, 223, 'Northern Mariana Islands', 'MP', 1),
(3659, 223, 'Ohio', 'OH', 1),
(3660, 223, 'Oklahoma', 'OK', 1),
(3661, 223, 'Oregon', 'OR', 1),
(3662, 223, 'Palau', 'PW', 1),
(3663, 223, 'Pennsylvania', 'PA', 1),
(3664, 223, 'Puerto Rico', 'PR', 1),
(3665, 223, 'Rhode Island', 'RI', 1),
(3666, 223, 'South Carolina', 'SC', 1),
(3667, 223, 'South Dakota', 'SD', 1),
(3668, 223, 'Tennessee', 'TN', 1),
(3669, 223, 'Texas', 'TX', 1),
(3670, 223, 'Utah', 'UT', 1),
(3671, 223, 'Vermont', 'VT', 1),
(3672, 223, 'Virgin Islands', 'VI', 1),
(3673, 223, 'Virginia', 'VA', 1),
(3674, 223, 'Washington', 'WA', 1),
(3675, 223, 'West Virginia', 'WV', 1),
(3676, 223, 'Wisconsin', 'WI', 1),
(3677, 223, 'Wyoming', 'WY', 1),
(3678, 224, 'Baker Island', 'BI', 1),
(3679, 224, 'Howland Island', 'HI', 1),
(3680, 224, 'Jarvis Island', 'JI', 1),
(3681, 224, 'Johnston Atoll', 'JA', 1),
(3682, 224, 'Kingman Reef', 'KR', 1),
(3683, 224, 'Midway Atoll', 'MA', 1),
(3684, 224, 'Navassa Island', 'NI', 1),
(3685, 224, 'Palmyra Atoll', 'PA', 1),
(3686, 224, 'Wake Island', 'WI', 1),
(3687, 225, 'Artigas', 'AR', 1),
(3688, 225, 'Canelones', 'CA', 1),
(3689, 225, 'Cerro Largo', 'CL', 1),
(3690, 225, 'Colonia', 'CO', 1),
(3691, 225, 'Durazno', 'DU', 1),
(3692, 225, 'Flores', 'FS', 1),
(3693, 225, 'Florida', 'FA', 1),
(3694, 225, 'Lavalleja', 'LA', 1),
(3695, 225, 'Maldonado', 'MA', 1),
(3696, 225, 'Montevideo', 'MO', 1),
(3697, 225, 'Paysandu', 'PA', 1),
(3698, 225, 'Rio Negro', 'RN', 1),
(3699, 225, 'Rivera', 'RV', 1),
(3700, 225, 'Rocha', 'RO', 1),
(3701, 225, 'Salto', 'SL', 1),
(3702, 225, 'San Jose', 'SJ', 1),
(3703, 225, 'Soriano', 'SO', 1),
(3704, 225, 'Tacuarembo', 'TA', 1),
(3705, 225, 'Treinta y Tres', 'TT', 1),
(3706, 226, 'Andijon', 'AN', 1),
(3707, 226, 'Buxoro', 'BU', 1),
(3708, 226, 'Farg\'ona', 'FA', 1),
(3709, 226, 'Jizzax', 'JI', 1),
(3710, 226, 'Namangan', 'NG', 1),
(3711, 226, 'Navoiy', 'NW', 1),
(3712, 226, 'Qashqadaryo', 'QA', 1),
(3713, 226, 'Qoraqalpog\'iston Republikasi', 'QR', 1),
(3714, 226, 'Samarqand', 'SA', 1),
(3715, 226, 'Sirdaryo', 'SI', 1),
(3716, 226, 'Surxondaryo', 'SU', 1),
(3717, 226, 'Toshkent City', 'TK', 1),
(3718, 226, 'Toshkent Region', 'TO', 1),
(3719, 226, 'Xorazm', 'XO', 1),
(3720, 227, 'Malampa', 'MA', 1),
(3721, 227, 'Penama', 'PE', 1),
(3722, 227, 'Sanma', 'SA', 1),
(3723, 227, 'Shefa', 'SH', 1),
(3724, 227, 'Tafea', 'TA', 1),
(3725, 227, 'Torba', 'TO', 1),
(3726, 229, 'Amazonas', 'AM', 1),
(3727, 229, 'Anzoategui', 'AN', 1),
(3728, 229, 'Apure', 'AP', 1),
(3729, 229, 'Aragua', 'AR', 1),
(3730, 229, 'Barinas', 'BA', 1),
(3731, 229, 'Bolivar', 'BO', 1),
(3732, 229, 'Carabobo', 'CA', 1),
(3733, 229, 'Cojedes', 'CO', 1),
(3734, 229, 'Delta Amacuro', 'DA', 1),
(3735, 229, 'Dependencias Federales', 'DF', 1),
(3736, 229, 'Distrito Federal', 'DI', 1),
(3737, 229, 'Falcon', 'FA', 1),
(3738, 229, 'Guarico', 'GU', 1),
(3739, 229, 'Lara', 'LA', 1),
(3740, 229, 'Merida', 'ME', 1),
(3741, 229, 'Miranda', 'MI', 1),
(3742, 229, 'Monagas', 'MO', 1),
(3743, 229, 'Nueva Esparta', 'NE', 1),
(3744, 229, 'Portuguesa', 'PO', 1),
(3745, 229, 'Sucre', 'SU', 1),
(3746, 229, 'Tachira', 'TA', 1),
(3747, 229, 'Trujillo', 'TR', 1),
(3748, 229, 'Vargas', 'VA', 1),
(3749, 229, 'Yaracuy', 'YA', 1),
(3750, 229, 'Zulia', 'ZU', 1),
(3751, 230, 'An Giang', 'AG', 1),
(3752, 230, 'Bac Giang', 'BG', 1),
(3753, 230, 'Bac Kan', 'BK', 1),
(3754, 230, 'Bac Lieu', 'BL', 1),
(3755, 230, 'Bac Ninh', 'BC', 1),
(3756, 230, 'Ba Ria-Vung Tau', 'BR', 1),
(3757, 230, 'Ben Tre', 'BN', 1),
(3758, 230, 'Binh Dinh', 'BH', 1),
(3759, 230, 'Binh Duong', 'BU', 1),
(3760, 230, 'Binh Phuoc', 'BP', 1),
(3761, 230, 'Binh Thuan', 'BT', 1),
(3762, 230, 'Ca Mau', 'CM', 1),
(3763, 230, 'Can Tho', 'CT', 1),
(3764, 230, 'Cao Bang', 'CB', 1),
(3765, 230, 'Dak Lak', 'DL', 1),
(3766, 230, 'Dak Nong', 'DG', 1),
(3767, 230, 'Da Nang', 'DN', 1),
(3768, 230, 'Dien Bien', 'DB', 1),
(3769, 230, 'Dong Nai', 'DI', 1),
(3770, 230, 'Dong Thap', 'DT', 1),
(3771, 230, 'Gia Lai', 'GL', 1),
(3772, 230, 'Ha Giang', 'HG', 1),
(3773, 230, 'Hai Duong', 'HD', 1),
(3774, 230, 'Hai Phong', 'HP', 1),
(3775, 230, 'Ha Nam', 'HM', 1),
(3776, 230, 'Ha Noi', 'HI', 1),
(3777, 230, 'Ha Tay', 'HT', 1),
(3778, 230, 'Ha Tinh', 'HH', 1),
(3779, 230, 'Hoa Binh', 'HB', 1),
(3780, 230, 'Ho Chi Minh City', 'HC', 1),
(3781, 230, 'Hau Giang', 'HU', 1),
(3782, 230, 'Hung Yen', 'HY', 1),
(3783, 232, 'Saint Croix', 'C', 1),
(3784, 232, 'Saint John', 'J', 1),
(3785, 232, 'Saint Thomas', 'T', 1),
(3786, 233, 'Alo', 'A', 1),
(3787, 233, 'Sigave', 'S', 1),
(3788, 233, 'Wallis', 'W', 1),
(3789, 235, 'Abyan', 'AB', 1),
(3790, 235, 'Adan', 'AD', 1),
(3791, 235, 'Amran', 'AM', 1),
(3792, 235, 'Al Bayda', 'BA', 1),
(3793, 235, 'Ad Dali', 'DA', 1),
(3794, 235, 'Dhamar', 'DH', 1),
(3795, 235, 'Hadramawt', 'HD', 1),
(3796, 235, 'Hajjah', 'HJ', 1),
(3797, 235, 'Al Hudaydah', 'HU', 1),
(3798, 235, 'Ibb', 'IB', 1),
(3799, 235, 'Al Jawf', 'JA', 1),
(3800, 235, 'Lahij', 'LA', 1),
(3801, 235, 'Ma\'rib', 'MA', 1),
(3802, 235, 'Al Mahrah', 'MR', 1),
(3803, 235, 'Al Mahwit', 'MW', 1),
(3804, 235, 'Sa\'dah', 'SD', 1),
(3805, 235, 'San\'a', 'SN', 1),
(3806, 235, 'Shabwah', 'SH', 1),
(3807, 235, 'Ta\'izz', 'TA', 1),
(3812, 237, 'Bas-Congo', 'BC', 1),
(3813, 237, 'Bandundu', 'BN', 1),
(3814, 237, 'Equateur', 'EQ', 1),
(3815, 237, 'Katanga', 'KA', 1),
(3816, 237, 'Kasai-Oriental', 'KE', 1),
(3817, 237, 'Kinshasa', 'KN', 1),
(3818, 237, 'Kasai-Occidental', 'KW', 1),
(3819, 237, 'Maniema', 'MA', 1),
(3820, 237, 'Nord-Kivu', 'NK', 1),
(3821, 237, 'Orientale', 'OR', 1),
(3822, 237, 'Sud-Kivu', 'SK', 1),
(3823, 238, 'Central', 'CE', 1),
(3824, 238, 'Copperbelt', 'CB', 1),
(3825, 238, 'Eastern', 'EA', 1),
(3826, 238, 'Luapula', 'LP', 1),
(3827, 238, 'Lusaka', 'LK', 1),
(3828, 238, 'Northern', 'NO', 1),
(3829, 238, 'North-Western', 'NW', 1),
(3830, 238, 'Southern', 'SO', 1),
(3831, 238, 'Western', 'WE', 1),
(3832, 239, 'Bulawayo', 'BU', 1),
(3833, 239, 'Harare', 'HA', 1),
(3834, 239, 'Manicaland', 'ML', 1),
(3835, 239, 'Mashonaland Central', 'MC', 1),
(3836, 239, 'Mashonaland East', 'ME', 1),
(3837, 239, 'Mashonaland West', 'MW', 1),
(3838, 239, 'Masvingo', 'MV', 1),
(3839, 239, 'Matabeleland North', 'MN', 1),
(3840, 239, 'Matabeleland South', 'MS', 1),
(3841, 239, 'Midlands', 'MD', 1),
(3861, 105, 'Campobasso', 'CB', 1),
(3863, 105, 'Caserta', 'CE', 1),
(3864, 105, 'Catania', 'CT', 1),
(3865, 105, 'Catanzaro', 'CZ', 1),
(3866, 105, 'Chieti', 'CH', 1),
(3867, 105, 'Como', 'CO', 1),
(3868, 105, 'Cosenza', 'CS', 1),
(3869, 105, 'Cremona', 'CR', 1),
(3870, 105, 'Crotone', 'KR', 1),
(3871, 105, 'Cuneo', 'CN', 1),
(3872, 105, 'Enna', 'EN', 1),
(3873, 105, 'Ferrara', 'FE', 1),
(3874, 105, 'Firenze', 'FI', 1),
(3875, 105, 'Foggia', 'FG', 1),
(3876, 105, 'Forli-Cesena', 'FC', 1),
(3877, 105, 'Frosinone', 'FR', 1),
(3878, 105, 'Genova', 'GE', 1),
(3879, 105, 'Gorizia', 'GO', 1),
(3880, 105, 'Grosseto', 'GR', 1),
(3881, 105, 'Imperia', 'IM', 1),
(3882, 105, 'Isernia', 'IS', 1),
(3883, 105, 'L&#39;Aquila', 'AQ', 1),
(3884, 105, 'La Spezia', 'SP', 1),
(3885, 105, 'Latina', 'LT', 1),
(3886, 105, 'Lecce', 'LE', 1),
(3887, 105, 'Lecco', 'LC', 1),
(3888, 105, 'Livorno', 'LI', 1),
(3889, 105, 'Lodi', 'LO', 1),
(3890, 105, 'Lucca', 'LU', 1),
(3891, 105, 'Macerata', 'MC', 1),
(3892, 105, 'Mantova', 'MN', 1),
(3893, 105, 'Massa-Carrara', 'MS', 1),
(3894, 105, 'Matera', 'MT', 1),
(3896, 105, 'Messina', 'ME', 1),
(3897, 105, 'Milano', 'MI', 1),
(3898, 105, 'Modena', 'MO', 1),
(3899, 105, 'Napoli', 'NA', 1),
(3900, 105, 'Novara', 'NO', 1),
(3901, 105, 'Nuoro', 'NU', 1),
(3904, 105, 'Oristano', 'OR', 1),
(3905, 105, 'Padova', 'PD', 1),
(3906, 105, 'Palermo', 'PA', 1),
(3907, 105, 'Parma', 'PR', 1),
(3908, 105, 'Pavia', 'PV', 1),
(3909, 105, 'Perugia', 'PG', 1),
(3910, 105, 'Pesaro e Urbino', 'PU', 1),
(3911, 105, 'Pescara', 'PE', 1),
(3912, 105, 'Piacenza', 'PC', 1),
(3913, 105, 'Pisa', 'PI', 1),
(3914, 105, 'Pistoia', 'PT', 1),
(3915, 105, 'Pordenone', 'PN', 1),
(3916, 105, 'Potenza', 'PZ', 1),
(3917, 105, 'Prato', 'PO', 1),
(3918, 105, 'Ragusa', 'RG', 1),
(3919, 105, 'Ravenna', 'RA', 1),
(3920, 105, 'Reggio Calabria', 'RC', 1),
(3921, 105, 'Reggio Emilia', 'RE', 1),
(3922, 105, 'Rieti', 'RI', 1),
(3923, 105, 'Rimini', 'RN', 1),
(3924, 105, 'Roma', 'RM', 1),
(3925, 105, 'Rovigo', 'RO', 1),
(3926, 105, 'Salerno', 'SA', 1),
(3927, 105, 'Sassari', 'SS', 1),
(3928, 105, 'Savona', 'SV', 1),
(3929, 105, 'Siena', 'SI', 1),
(3930, 105, 'Siracusa', 'SR', 1),
(3931, 105, 'Sondrio', 'SO', 1),
(3932, 105, 'Taranto', 'TA', 1),
(3933, 105, 'Teramo', 'TE', 1),
(3934, 105, 'Terni', 'TR', 1),
(3935, 105, 'Torino', 'TO', 1),
(3936, 105, 'Trapani', 'TP', 1),
(3937, 105, 'Trento', 'TN', 1),
(3938, 105, 'Treviso', 'TV', 1),
(3939, 105, 'Trieste', 'TS', 1),
(3940, 105, 'Udine', 'UD', 1),
(3941, 105, 'Varese', 'VA', 1),
(3942, 105, 'Venezia', 'VE', 1),
(3943, 105, 'Verbano-Cusio-Ossola', 'VB', 1),
(3944, 105, 'Vercelli', 'VC', 1),
(3945, 105, 'Verona', 'VR', 1),
(3946, 105, 'Vibo Valentia', 'VV', 1),
(3947, 105, 'Vicenza', 'VI', 1),
(3948, 105, 'Viterbo', 'VT', 1),
(3949, 222, 'County Antrim', 'ANT', 1),
(3950, 222, 'County Armagh', 'ARM', 1),
(3951, 222, 'County Down', 'DOW', 1),
(3952, 222, 'County Fermanagh', 'FER', 1),
(3953, 222, 'County Londonderry', 'LDY', 1),
(3954, 222, 'County Tyrone', 'TYR', 1),
(3955, 222, 'Cumbria', 'CMA', 1),
(3956, 190, 'Pomurska', '1', 1),
(3957, 190, 'Podravska', '2', 1),
(3958, 190, 'Koroška', '3', 1),
(3959, 190, 'Savinjska', '4', 1),
(3960, 190, 'Zasavska', '5', 1),
(3961, 190, 'Spodnjeposavska', '6', 1),
(3962, 190, 'Jugovzhodna Slovenija', '7', 1),
(3963, 190, 'Osrednjeslovenska', '8', 1),
(3964, 190, 'Gorenjska', '9', 1),
(3965, 190, 'Notranjsko-kraška', '10', 1),
(3966, 190, 'Goriška', '11', 1),
(3967, 190, 'Obalno-kraška', '12', 1),
(3968, 33, 'Ruse', '', 1),
(3969, 101, 'Alborz', 'ALB', 1),
(3970, 21, 'Brussels-Capital Region', 'BRU', 1),
(3971, 138, 'Aguascalientes', 'AG', 1),
(3973, 242, 'Andrijevica', '01', 1),
(3974, 242, 'Bar', '02', 1),
(3975, 242, 'Berane', '03', 1),
(3976, 242, 'Bijelo Polje', '04', 1),
(3977, 242, 'Budva', '05', 1),
(3978, 242, 'Cetinje', '06', 1),
(3979, 242, 'Danilovgrad', '07', 1),
(3980, 242, 'Herceg-Novi', '08', 1),
(3981, 242, 'Kolašin', '09', 1),
(3982, 242, 'Kotor', '10', 1),
(3983, 242, 'Mojkovac', '11', 1),
(3984, 242, 'Nikšić', '12', 1),
(3985, 242, 'Plav', '13', 1),
(3986, 242, 'Pljevlja', '14', 1),
(3987, 242, 'Plužine', '15', 1),
(3988, 242, 'Podgorica', '16', 1),
(3989, 242, 'Rožaje', '17', 1),
(3990, 242, 'Šavnik', '18', 1),
(3991, 242, 'Tivat', '19', 1),
(3992, 242, 'Ulcinj', '20', 1),
(3993, 242, 'Žabljak', '21', 1),
(3994, 243, 'Belgrade', '00', 1),
(3995, 243, 'North Bačka', '01', 1),
(3996, 243, 'Central Banat', '02', 1),
(3997, 243, 'North Banat', '03', 1),
(3998, 243, 'South Banat', '04', 1),
(3999, 243, 'West Bačka', '05', 1),
(4000, 243, 'South Bačka', '06', 1),
(4001, 243, 'Srem', '07', 1),
(4002, 243, 'Mačva', '08', 1),
(4003, 243, 'Kolubara', '09', 1),
(4004, 243, 'Podunavlje', '10', 1),
(4005, 243, 'Braničevo', '11', 1),
(4006, 243, 'Šumadija', '12', 1),
(4007, 243, 'Pomoravlje', '13', 1),
(4008, 243, 'Bor', '14', 1),
(4009, 243, 'Zaječar', '15', 1),
(4010, 243, 'Zlatibor', '16', 1),
(4011, 243, 'Moravica', '17', 1),
(4012, 243, 'Raška', '18', 1),
(4013, 243, 'Rasina', '19', 1),
(4014, 243, 'Nišava', '20', 1),
(4015, 243, 'Toplica', '21', 1),
(4016, 243, 'Pirot', '22', 1),
(4017, 243, 'Jablanica', '23', 1),
(4018, 243, 'Pčinja', '24', 1),
(4020, 245, 'Bonaire', 'BO', 1),
(4021, 245, 'Saba', 'SA', 1),
(4022, 245, 'Sint Eustatius', 'SE', 1),
(4023, 248, 'Central Equatoria', 'EC', 1),
(4024, 248, 'Eastern Equatoria', 'EE', 1),
(4025, 248, 'Jonglei', 'JG', 1),
(4026, 248, 'Lakes', 'LK', 1),
(4027, 248, 'Northern Bahr el-Ghazal', 'BN', 1),
(4028, 248, 'Unity', 'UY', 1),
(4029, 248, 'Upper Nile', 'NU', 1),
(4030, 248, 'Warrap', 'WR', 1),
(4031, 248, 'Western Bahr el-Ghazal', 'BW', 1),
(4032, 248, 'Western Equatoria', 'EW', 1),
(4036, 117, 'Ainaži, Salacgrīvas novads', '0661405', 1),
(4037, 117, 'Aizkraukle, Aizkraukles novads', '0320201', 1),
(4038, 117, 'Aizkraukles novads', '0320200', 1),
(4039, 117, 'Aizpute, Aizputes novads', '0640605', 1),
(4040, 117, 'Aizputes novads', '0640600', 1),
(4041, 117, 'Aknīste, Aknīstes novads', '0560805', 1),
(4042, 117, 'Aknīstes novads', '0560800', 1),
(4043, 117, 'Aloja, Alojas novads', '0661007', 1),
(4044, 117, 'Alojas novads', '0661000', 1),
(4045, 117, 'Alsungas novads', '0624200', 1),
(4046, 117, 'Alūksne, Alūksnes novads', '0360201', 1),
(4047, 117, 'Alūksnes novads', '0360200', 1),
(4048, 117, 'Amatas novads', '0424701', 1),
(4049, 117, 'Ape, Apes novads', '0360805', 1),
(4050, 117, 'Apes novads', '0360800', 1),
(4051, 117, 'Auce, Auces novads', '0460805', 1),
(4052, 117, 'Auces novads', '0460800', 1),
(4053, 117, 'Ādažu novads', '0804400', 1),
(4054, 117, 'Babītes novads', '0804900', 1),
(4055, 117, 'Baldone, Baldones novads', '0800605', 1),
(4056, 117, 'Baldones novads', '0800600', 1),
(4057, 117, 'Baloži, Ķekavas novads', '0800807', 1),
(4058, 117, 'Baltinavas novads', '0384400', 1),
(4059, 117, 'Balvi, Balvu novads', '0380201', 1),
(4060, 117, 'Balvu novads', '0380200', 1),
(4061, 117, 'Bauska, Bauskas novads', '0400201', 1),
(4062, 117, 'Bauskas novads', '0400200', 1),
(4063, 117, 'Beverīnas novads', '0964700', 1),
(4064, 117, 'Brocēni, Brocēnu novads', '0840605', 1),
(4065, 117, 'Brocēnu novads', '0840601', 1),
(4066, 117, 'Burtnieku novads', '0967101', 1),
(4067, 117, 'Carnikavas novads', '0805200', 1),
(4068, 117, 'Cesvaine, Cesvaines novads', '0700807', 1),
(4069, 117, 'Cesvaines novads', '0700800', 1),
(4070, 117, 'Cēsis, Cēsu novads', '0420201', 1),
(4071, 117, 'Cēsu novads', '0420200', 1),
(4072, 117, 'Ciblas novads', '0684901', 1),
(4073, 117, 'Dagda, Dagdas novads', '0601009', 1),
(4074, 117, 'Dagdas novads', '0601000', 1),
(4075, 117, 'Daugavpils', '0050000', 1),
(4076, 117, 'Daugavpils novads', '0440200', 1),
(4077, 117, 'Dobele, Dobeles novads', '0460201', 1),
(4078, 117, 'Dobeles novads', '0460200', 1),
(4079, 117, 'Dundagas novads', '0885100', 1),
(4080, 117, 'Durbe, Durbes novads', '0640807', 1),
(4081, 117, 'Durbes novads', '0640801', 1),
(4082, 117, 'Engures novads', '0905100', 1),
(4083, 117, 'Ērgļu novads', '0705500', 1),
(4084, 117, 'Garkalnes novads', '0806000', 1),
(4085, 117, 'Grobiņa, Grobiņas novads', '0641009', 1),
(4086, 117, 'Grobiņas novads', '0641000', 1),
(4087, 117, 'Gulbene, Gulbenes novads', '0500201', 1),
(4088, 117, 'Gulbenes novads', '0500200', 1),
(4089, 117, 'Iecavas novads', '0406400', 1),
(4090, 117, 'Ikšķile, Ikšķiles novads', '0740605', 1),
(4091, 117, 'Ikšķiles novads', '0740600', 1),
(4092, 117, 'Ilūkste, Ilūkstes novads', '0440807', 1),
(4093, 117, 'Ilūkstes novads', '0440801', 1),
(4094, 117, 'Inčukalna novads', '0801800', 1),
(4095, 117, 'Jaunjelgava, Jaunjelgavas novads', '0321007', 1),
(4096, 117, 'Jaunjelgavas novads', '0321000', 1),
(4097, 117, 'Jaunpiebalgas novads', '0425700', 1),
(4098, 117, 'Jaunpils novads', '0905700', 1),
(4099, 117, 'Jelgava', '0090000', 1),
(4100, 117, 'Jelgavas novads', '0540200', 1),
(4101, 117, 'Jēkabpils', '0110000', 1),
(4102, 117, 'Jēkabpils novads', '0560200', 1),
(4103, 117, 'Jūrmala', '0130000', 1),
(4104, 117, 'Kalnciems, Jelgavas novads', '0540211', 1),
(4105, 117, 'Kandava, Kandavas novads', '0901211', 1),
(4106, 117, 'Kandavas novads', '0901201', 1),
(4107, 117, 'Kārsava, Kārsavas novads', '0681009', 1),
(4108, 117, 'Kārsavas novads', '0681000', 1),
(4109, 117, 'Kocēnu novads ,bij. Valmieras)', '0960200', 1),
(4110, 117, 'Kokneses novads', '0326100', 1),
(4111, 117, 'Krāslava, Krāslavas novads', '0600201', 1),
(4112, 117, 'Krāslavas novads', '0600202', 1),
(4113, 117, 'Krimuldas novads', '0806900', 1),
(4114, 117, 'Krustpils novads', '0566900', 1),
(4115, 117, 'Kuldīga, Kuldīgas novads', '0620201', 1),
(4116, 117, 'Kuldīgas novads', '0620200', 1),
(4117, 117, 'Ķeguma novads', '0741001', 1),
(4118, 117, 'Ķegums, Ķeguma novads', '0741009', 1),
(4119, 117, 'Ķekavas novads', '0800800', 1),
(4120, 117, 'Lielvārde, Lielvārdes novads', '0741413', 1),
(4121, 117, 'Lielvārdes novads', '0741401', 1),
(4122, 117, 'Liepāja', '0170000', 1),
(4123, 117, 'Limbaži, Limbažu novads', '0660201', 1),
(4124, 117, 'Limbažu novads', '0660200', 1),
(4125, 117, 'Līgatne, Līgatnes novads', '0421211', 1),
(4126, 117, 'Līgatnes novads', '0421200', 1),
(4127, 117, 'Līvāni, Līvānu novads', '0761211', 1),
(4128, 117, 'Līvānu novads', '0761201', 1),
(4129, 117, 'Lubāna, Lubānas novads', '0701413', 1),
(4130, 117, 'Lubānas novads', '0701400', 1),
(4131, 117, 'Ludza, Ludzas novads', '0680201', 1),
(4132, 117, 'Ludzas novads', '0680200', 1),
(4133, 117, 'Madona, Madonas novads', '0700201', 1),
(4134, 117, 'Madonas novads', '0700200', 1),
(4135, 117, 'Mazsalaca, Mazsalacas novads', '0961011', 1),
(4136, 117, 'Mazsalacas novads', '0961000', 1),
(4137, 117, 'Mālpils novads', '0807400', 1),
(4138, 117, 'Mārupes novads', '0807600', 1),
(4139, 117, 'Mērsraga novads', '0887600', 1),
(4140, 117, 'Naukšēnu novads', '0967300', 1),
(4141, 117, 'Neretas novads', '0327100', 1),
(4142, 117, 'Nīcas novads', '0647900', 1),
(4143, 117, 'Ogre, Ogres novads', '0740201', 1),
(4144, 117, 'Ogres novads', '0740202', 1),
(4145, 117, 'Olaine, Olaines novads', '0801009', 1),
(4146, 117, 'Olaines novads', '0801000', 1),
(4147, 117, 'Ozolnieku novads', '0546701', 1),
(4148, 117, 'Pārgaujas novads', '0427500', 1),
(4149, 117, 'Pāvilosta, Pāvilostas novads', '0641413', 1),
(4150, 117, 'Pāvilostas novads', '0641401', 1),
(4151, 117, 'Piltene, Ventspils novads', '0980213', 1),
(4152, 117, 'Pļaviņas, Pļaviņu novads', '0321413', 1),
(4153, 117, 'Pļaviņu novads', '0321400', 1),
(4154, 117, 'Preiļi, Preiļu novads', '0760201', 1),
(4155, 117, 'Preiļu novads', '0760202', 1),
(4156, 117, 'Priekule, Priekules novads', '0641615', 1),
(4157, 117, 'Priekules novads', '0641600', 1),
(4158, 117, 'Priekuļu novads', '0427300', 1),
(4159, 117, 'Raunas novads', '0427700', 1),
(4160, 117, 'Rēzekne', '0210000', 1),
(4161, 117, 'Rēzeknes novads', '0780200', 1),
(4162, 117, 'Riebiņu novads', '0766300', 1),
(4163, 117, 'Rīga', '0010000', 1),
(4164, 117, 'Rojas novads', '0888300', 1),
(4165, 117, 'Ropažu novads', '0808400', 1),
(4166, 117, 'Rucavas novads', '0648500', 1),
(4167, 117, 'Rugāju novads', '0387500', 1),
(4168, 117, 'Rundāles novads', '0407700', 1),
(4169, 117, 'Rūjiena, Rūjienas novads', '0961615', 1),
(4170, 117, 'Rūjienas novads', '0961600', 1),
(4171, 117, 'Sabile, Talsu novads', '0880213', 1),
(4172, 117, 'Salacgrīva, Salacgrīvas novads', '0661415', 1),
(4173, 117, 'Salacgrīvas novads', '0661400', 1),
(4174, 117, 'Salas novads', '0568700', 1),
(4175, 117, 'Salaspils novads', '0801200', 1),
(4176, 117, 'Salaspils, Salaspils novads', '0801211', 1),
(4177, 117, 'Saldus novads', '0840200', 1),
(4178, 117, 'Saldus, Saldus novads', '0840201', 1),
(4179, 117, 'Saulkrasti, Saulkrastu novads', '0801413', 1),
(4180, 117, 'Saulkrastu novads', '0801400', 1),
(4181, 117, 'Seda, Strenču novads', '0941813', 1),
(4182, 117, 'Sējas novads', '0809200', 1),
(4183, 117, 'Sigulda, Siguldas novads', '0801615', 1),
(4184, 117, 'Siguldas novads', '0801601', 1),
(4185, 117, 'Skrīveru novads', '0328200', 1),
(4186, 117, 'Skrunda, Skrundas novads', '0621209', 1),
(4187, 117, 'Skrundas novads', '0621200', 1),
(4188, 117, 'Smiltene, Smiltenes novads', '0941615', 1),
(4189, 117, 'Smiltenes novads', '0941600', 1),
(4190, 117, 'Staicele, Alojas novads', '0661017', 1),
(4191, 117, 'Stende, Talsu novads', '0880215', 1),
(4192, 117, 'Stopiņu novads', '0809600', 1),
(4193, 117, 'Strenči, Strenču novads', '0941817', 1),
(4194, 117, 'Strenču novads', '0941800', 1),
(4195, 117, 'Subate, Ilūkstes novads', '0440815', 1),
(4196, 117, 'Talsi, Talsu novads', '0880201', 1),
(4197, 117, 'Talsu novads', '0880200', 1),
(4198, 117, 'Tērvetes novads', '0468900', 1),
(4199, 117, 'Tukuma novads', '0900200', 1),
(4200, 117, 'Tukums, Tukuma novads', '0900201', 1),
(4201, 117, 'Vaiņodes novads', '0649300', 1),
(4202, 117, 'Valdemārpils, Talsu novads', '0880217', 1),
(4203, 117, 'Valka, Valkas novads', '0940201', 1),
(4204, 117, 'Valkas novads', '0940200', 1),
(4205, 117, 'Valmiera', '0250000', 1),
(4206, 117, 'Vangaži, Inčukalna novads', '0801817', 1),
(4207, 117, 'Varakļāni, Varakļānu novads', '0701817', 1),
(4208, 117, 'Varakļānu novads', '0701800', 1),
(4209, 117, 'Vārkavas novads', '0769101', 1),
(4210, 117, 'Vecpiebalgas novads', '0429300', 1),
(4211, 117, 'Vecumnieku novads', '0409500', 1),
(4212, 117, 'Ventspils', '0270000', 1),
(4213, 117, 'Ventspils novads', '0980200', 1),
(4214, 117, 'Viesīte, Viesītes novads', '0561815', 1),
(4215, 117, 'Viesītes novads', '0561800', 1),
(4216, 117, 'Viļaka, Viļakas novads', '0381615', 1),
(4217, 117, 'Viļakas novads', '0381600', 1),
(4218, 117, 'Viļāni, Viļānu novads', '0781817', 1),
(4219, 117, 'Viļānu novads', '0781800', 1),
(4220, 117, 'Zilupe, Zilupes novads', '0681817', 1),
(4221, 117, 'Zilupes novads', '0681801', 1),
(4222, 43, 'Arica y Parinacota', 'AP', 1),
(4223, 43, 'Los Rios', 'LR', 1),
(4224, 220, 'Kharkivs\'ka Oblast\'', '63', 1),
(4225, 118, 'Beirut', 'LB-BR', 1),
(4226, 118, 'Bekaa', 'LB-BE', 1),
(4227, 118, 'Mount Lebanon', 'LB-ML', 1),
(4228, 118, 'Nabatieh', 'LB-NB', 1),
(4229, 118, 'North', 'LB-NR', 1),
(4230, 118, 'South', 'LB-ST', 1),
(4231, 99, 'Telangana', 'TS', 1),
(4232, 44, 'Qinghai', 'QH', 1),
(4233, 100, 'Papua Barat', 'PB', 1),
(4234, 100, 'Sulawesi Barat', 'SR', 1),
(4235, 100, 'Kepulauan Riau', 'KR', 1),
(4236, 105, 'Barletta-Andria-Trani', 'BT', 1),
(4237, 105, 'Fermo', 'FM', 1),
(4238, 105, 'Monza Brianza', 'MB', 1);

-- --------------------------------------------------------

--
-- Table structure for table `ocn8_zone_to_geo_zone`
--

CREATE TABLE `ocn8_zone_to_geo_zone` (
  `zone_to_geo_zone_id` int(11) NOT NULL,
  `country_id` int(11) NOT NULL,
  `zone_id` int(11) NOT NULL DEFAULT 0,
  `geo_zone_id` int(11) NOT NULL,
  `date_added` datetime NOT NULL,
  `date_modified` datetime NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ocn8_zone_to_geo_zone`
--

INSERT INTO `ocn8_zone_to_geo_zone` (`zone_to_geo_zone_id`, `country_id`, `zone_id`, `geo_zone_id`, `date_added`, `date_modified`) VALUES
(1, 222, 0, 4, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(2, 222, 3513, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(3, 222, 3514, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(4, 222, 3515, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(5, 222, 3516, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(6, 222, 3517, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(7, 222, 3518, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(8, 222, 3519, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(9, 222, 3520, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(10, 222, 3521, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(11, 222, 3522, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(12, 222, 3523, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(13, 222, 3524, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(14, 222, 3525, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(15, 222, 3526, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(16, 222, 3527, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(17, 222, 3528, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(18, 222, 3529, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(19, 222, 3530, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(20, 222, 3531, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(21, 222, 3532, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(22, 222, 3533, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(23, 222, 3534, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(24, 222, 3535, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(25, 222, 3536, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(26, 222, 3537, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(27, 222, 3538, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(28, 222, 3539, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(29, 222, 3540, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(30, 222, 3541, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(31, 222, 3542, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(32, 222, 3543, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(33, 222, 3544, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(34, 222, 3545, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(35, 222, 3546, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(36, 222, 3547, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(37, 222, 3548, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(38, 222, 3549, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(39, 222, 3550, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(40, 222, 3551, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(41, 222, 3552, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(42, 222, 3553, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(43, 222, 3554, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(44, 222, 3555, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(45, 222, 3556, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(46, 222, 3557, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(47, 222, 3558, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(48, 222, 3559, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(49, 222, 3560, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(50, 222, 3561, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(51, 222, 3562, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(52, 222, 3563, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(53, 222, 3564, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(54, 222, 3565, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(55, 222, 3566, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(56, 222, 3567, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(57, 222, 3568, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(58, 222, 3569, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(59, 222, 3570, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(60, 222, 3571, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(61, 222, 3572, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(62, 222, 3573, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(63, 222, 3574, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(64, 222, 3575, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(65, 222, 3576, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(66, 222, 3577, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(67, 222, 3578, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(68, 222, 3579, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(69, 222, 3580, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(70, 222, 3581, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(71, 222, 3582, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(72, 222, 3583, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(73, 222, 3584, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(74, 222, 3585, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(75, 222, 3586, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(76, 222, 3587, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(77, 222, 3588, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(78, 222, 3589, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(79, 222, 3590, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(80, 222, 3591, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(81, 222, 3592, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(82, 222, 3593, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(83, 222, 3594, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(84, 222, 3595, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(85, 222, 3596, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(86, 222, 3597, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(87, 222, 3598, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(88, 222, 3599, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(89, 222, 3600, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(90, 222, 3601, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(91, 222, 3602, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(92, 222, 3603, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(93, 222, 3604, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(94, 222, 3605, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(95, 222, 3606, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(96, 222, 3607, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(97, 222, 3608, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(98, 222, 3609, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(99, 222, 3610, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(100, 222, 3611, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(101, 222, 3612, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(102, 222, 3949, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(103, 222, 3950, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(104, 222, 3951, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(105, 222, 3952, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(106, 222, 3953, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(107, 222, 3954, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(108, 222, 3955, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(109, 222, 3972, 3, '0000-00-00 00:00:00', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `order_details_mobile_api`
--

CREATE TABLE `order_details_mobile_api` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT 'user_id of mobile app',
  `ref_customer_id` int(11) DEFAULT NULL COMMENT 'our customer_id',
  `ref_order_id` int(11) DEFAULT NULL COMMENT 'our order_id',
  `shipping_address` longtext DEFAULT NULL,
  `order_details` longtext DEFAULT NULL,
  `status` int(11) DEFAULT 0 COMMENT '0/1',
  `api_response` text DEFAULT NULL,
  `retries` int(10) NOT NULL DEFAULT 0
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `order_details_mobile_api`
--

INSERT INTO `order_details_mobile_api` (`id`, `user_id`, `ref_customer_id`, `ref_order_id`, `shipping_address`, `order_details`, `status`, `api_response`, `retries`) VALUES
(169, 0, 3, 25, 'qurtubah dist   Riyadh  Saudi Arabia Ar Riyad', '[{\"product_name\":\"????? ???? ??? ??? \",\"quantity\":\"1\",\"product_id\":\"53\",\"mac_address\":\"\",\"image\":\"\"}]', 1, '[{\"id\":117,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(165, 0, 1, 21, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch\",\"quantity\":\"1\",\"product_id\":\"50\",\"mac_address\":\"\",\"image\":\"\"}]', 1, '[{\"id\":118,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(166, 0, 1, 22, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch\",\"quantity\":\"1\",\"product_id\":\"50\",\"mac_address\":\"test\",\"image\":\"logo.png\"}]', 1, '[{\"id\":119,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(167, 0, 1, 23, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch\",\"quantity\":\"2\",\"product_id\":\"50\",\"mac_address\":\"\",\"image\":\"\"}]', 1, '[{\"id\":120,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(168, 0, 1, 24, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch\",\"quantity\":\"2\",\"product_id\":\"50\",\"mac_address\":\"\",\"image\":\"\"}]', 1, '[{\"id\":121,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(151, 0, 1, 7, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch\",\"quantity\":\"3\",\"product_id\":\"50\",\"mac_address\":\"\",\"image\":\"\"}]', 1, '[{\"id\":122,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(152, 0, 1, 8, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch\",\"quantity\":\"3\",\"product_id\":\"50\",\"mac_address\":\"\",\"image\":\"\"}]', 1, '[{\"id\":123,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(153, 0, 1, 9, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch\",\"quantity\":\"3\",\"product_id\":\"50\",\"mac_address\":\"\",\"image\":\"\"}]', 1, '[{\"id\":124,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(154, 0, 1, 10, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch\",\"quantity\":\"1\",\"product_id\":\"50\",\"mac_address\":\"\",\"image\":\"\"}]', 1, '[{\"id\":125,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(155, 0, 1, 11, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch\",\"quantity\":\"1\",\"product_id\":\"50\",\"mac_address\":\"\",\"image\":\"\"}]', 1, '[{\"id\":126,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(156, 0, 1, 12, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch\",\"quantity\":\"1\",\"product_id\":\"50\",\"mac_address\":\"\",\"image\":\"\"}]', 1, '[{\"id\":127,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(157, 0, 1, 13, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch\",\"quantity\":\"1\",\"product_id\":\"50\",\"mac_address\":\"\",\"image\":\"\"}]', 1, '[{\"id\":128,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(158, 0, 1, 14, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch\",\"quantity\":\"1\",\"product_id\":\"50\",\"mac_address\":\"\",\"image\":\"\"}]', 1, '[{\"id\":129,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(159, 0, 1, 15, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch\",\"quantity\":\"1\",\"product_id\":\"50\",\"mac_address\":\"\",\"image\":\"\"}]', 1, '[{\"id\":130,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(160, 0, 1, 16, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch\",\"quantity\":\"1\",\"product_id\":\"50\",\"mac_address\":\"\",\"image\":\"\"}]', 1, '[{\"id\":131,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(161, 0, 1, 17, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch\",\"quantity\":\"1\",\"product_id\":\"50\",\"mac_address\":\"\",\"image\":\"\"}]', 1, '[{\"id\":132,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(162, 0, 1, 18, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch\",\"quantity\":\"1\",\"product_id\":\"50\",\"mac_address\":\"\",\"image\":\"\"}]', 1, '[{\"id\":133,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(163, 0, 1, 19, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch\",\"quantity\":\"1\",\"product_id\":\"50\",\"mac_address\":\"\",\"image\":\"\"}]', 1, '[{\"id\":134,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(164, 0, 1, 20, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch\",\"quantity\":\"1\",\"product_id\":\"50\",\"mac_address\":\"\",\"image\":\"\"}]', 1, '[{\"id\":135,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(170, 320, 42, 45, 'Check  Check  Pakistan Federally Administered Tribal Areas', '[{\"product_name\":\" WIFI Three gangs lighting switch _ Free Hardware\",\"product_id\":\"50\",\"quantity\":\"1\",\"mac_address\":\"mac_1_sar_36\",\"image\":\"logo.png\",\"subscription_type\":\"7\"},{\"product_name\":\" WIFI Three gangs lighting switch _ Free Hardware\",\"product_id\":\"50\",\"quantity\":\"1\",\"mac_address\":\"mac_2_sar_180\",\"image\":\"image_2022-05-17_173001071-removebg-preview.png\",\"subscription_type\":\"3\"}]', 1, '[{\"id\":158,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(172, 332, 47, 54, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch _ Free Hardware\",\"product_id\":\"50\",\"quantity\":\"1\",\"mac_address\":\"sit_livixa\",\"image\":\"image_2022-06-18_171410260.png\",\"subscription_type\":\"4\",\"subscription_period_id\":\"2\"}]', 1, '[{\"id\":159,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(175, 333, 48, 55, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch _ Free Hardware\",\"product_id\":\"50\",\"quantity\":\"2\",\"mac_address\":\"sit_faran,sit_faran2\",\"image\":\"logo.png,Hospital Admissions Per Corp.png\",\"subscription_type\":\"2\",\"subscription_period_id\":\"2\"},{\"product_name\":\"WIFI A/C switch _ Free Hardware \",\"product_id\":\"53\",\"quantity\":\"3\",\"mac_address\":\"sit_faran3,sit_faran4,sit_faran5\",\"image\":\"Hospital Admissions Per Corp.png,logo.png,image_2022-05-17_173001071-removebg-preview.png\",\"subscription_type\":\"3\",\"subscription_period_id\":\"3\"}]', 1, '[{\"id\":162,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(176, 337, 49, 58, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch _ Free Hardware\",\"product_id\":\"50\",\"quantity\":\"2\",\"mac_address\":\"sit_farukh,sit_farukh2\",\"image\":\"logo.png,Hospital Admissions Per Corp.png\",\"subscription_type\":\"1\",\"subscription_period_id\":\"3\"},{\"product_name\":\"WIFI A/C switch _ Free Hardware \",\"product_id\":\"53\",\"quantity\":\"2\",\"mac_address\":\"sit_farukh3,sit_farukh4\",\"image\":\"Hospital Admissions Per Corp.png,logo.png\",\"subscription_type\":\"1\",\"subscription_period_id\":\"2\"}]', 1, '[{\"id\":164,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(177, 339, 50, 62, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch _ Free Hardware\",\"product_id\":\"50\",\"quantity\":\"2\",\"mac_address\":\"sit_livx123,sit_livx321\",\"image\":\"logo.png,Hospital Admissions Per Corp.png\",\"subscription_type\":\"1\",\"subscription_period_id\":\"3\"},{\"product_name\":\"WIFI One gang lighting switch_ Free Hardware\",\"product_id\":\"51\",\"quantity\":\"4\",\"mac_address\":\"sit_livx11,sit_livx12,sit_livx_13,sit_livx_14\",\"image\":\"logo.png,Hospital Admissions Per Corp.png,image_2022-05-17_173001071-removebg-preview.png,image_2022-05-17_172929129.png\",\"subscription_type\":\"1\",\"subscription_period_id\":\"3\"}]', 1, '[{\"id\":165,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(178, 343, 52, 63, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch _ Free Hardware\",\"product_id\":\"50\",\"quantity\":\"3\",\"mac_address\":\"sit_farukh99,sit_farukh98,sit_farukh97\",\"image\":\"Hospital Admissions Per Corp.png,logo.png,image_2022-05-17_173001071-removebg-preview.png\",\"subscription_type\":\"1\",\"subscription_period_id\":\"2\"}]', 1, '[{\"id\":167,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(179, 344, 53, 64, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch _ Free Hardware\",\"product_id\":\"50\",\"quantity\":\"2\",\"mac_address\":\"sit_livx1,sit_livx2\",\"image\":\"image_2022-05-17_173001071-removebg-preview.png,logo.png\",\"subscription_type\":\"1\",\"subscription_period_id\":\"2\"},{\"product_name\":\"WIFI A/C switch _ Free Hardware \",\"product_id\":\"53\",\"quantity\":\"2\",\"mac_address\":\"sit_livx3,sit_livx4\",\"image\":\"logo.png,image_2022-05-17_173001071-removebg-preview.png\",\"subscription_type\":\"1\",\"subscription_period_id\":\"3\"}]', 1, '[{\"id\":168,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(180, 348, 43, 67, 'fvfrff  lahore  Pakistan Punjab', '[{\"product_name\":\" WIFI Three gangs lighting switch _ Free Hardware\",\"product_id\":\"50\",\"quantity\":\"1\",\"mac_address\":\"mac_bushra\",\"image\":\"logo.png\",\"subscription_type\":\"1\",\"subscription_period_id\":\"2\"}]', 1, '[{\"id\":233,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(181, 349, 55, 74, 'h1 s1  Islamabad  Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch _ Free Hardware\",\"product_id\":\"50\",\"quantity\":\"2\",\"mac_address\":\"sit_final_livx,sit_final_livx_1\",\"image\":\"logo.png,Hospital Admissions Per Corp.png\",\"subscription_type\":\"1\",\"subscription_period_id\":\"3\"}]', 1, '[{\"id\":235,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(182, 350, 56, 75, 'h1 s1  Islamabad  Pakistan Islamabad Capital Territory', '[{\"product_name\":\"WIFI A/C switch _ Free Hardware \",\"product_id\":\"53\",\"quantity\":\"1\",\"mac_address\":\"sit_livx_final_1\",\"image\":\"hoonppubf.png\",\"subscription_type\":\"1\",\"subscription_period_id\":\"3\"}]', 1, '[{\"id\":236,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(183, 356, 57, 76, 'h1 s1  Islamabad  Pakistan Islamabad Capital Territory', '[{\"product_name\":\"WIFI A/C switch _ Free Hardware \",\"product_id\":\"53\",\"quantity\":\"1\",\"mac_address\":\"liv_sit\",\"image\":\"hoonppubf.png\",\"subscription_type\":\"1\",\"subscription_period_id\":\"2\"}]', 1, '[{\"id\":243,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(184, 357, 58, 77, 'h1 s1  Islamabad  Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch _ Free Hardware\",\"product_id\":\"50\",\"quantity\":\"1\",\"mac_address\":\"ssit_liv\",\"image\":\"Hospital Admissions Per Corp.png\",\"subscription_type\":\"3\",\"subscription_period_id\":\"3\"}]', 1, '[{\"id\":244,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(185, 358, 59, 78, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\"WIFI A/C switch _ Free Hardware \",\"product_id\":\"53\",\"quantity\":\"2\",\"mac_address\":\"sit_mac_test1234,sit_mac_test_45678\",\"image\":\"logo.png,Hospital Admissions Per Corp.png\",\"subscription_type\":\"3\",\"subscription_period_id\":\"2\"}]', 1, '[{\"id\":245,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(186, 360, 60, 79, '15-b  Islamabad 46000 Pakistan Islamabad Capital Territory', '[{\"product_name\":\" WIFI Three gangs lighting switch _ Free Hardware\",\"product_id\":\"50\",\"quantity\":\"1\",\"mac_address\":\"ssit_llivxa_mac_1234\",\"image\":\"logo.png\",\"subscription_type\":\"3\",\"subscription_period_id\":\"3\"}]', 1, '[{\"id\":246,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(187, 365, 64, 85, '15-b  Islamabad 46000 Saudi Arabia Al Madinah', '[{\"product_name\":\"WIFI A/C switch _ Free Hardware \",\"product_id\":\"53\",\"quantity\":\"1\",\"mac_address\":\"4abc\",\"image\":\"\",\"subscription_type\":\"2\",\"subscription_period_id\":\"2\"}]', 1, '[{\"id\":247,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(188, 348, 43, 86, 'fvfrff  lahore  Pakistan Punjab', '[{\"product_name\":\" WIFI Three gangs lighting switch _ Free Hardware\",\"product_id\":\"50\",\"quantity\":\"1\",\"mac_address\":\"3abc\",\"image\":\"\",\"subscription_type\":\"1\",\"subscription_period_id\":\"2\"}]', 1, '[{\"id\":256,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(189, 348, 43, 105, 'fvfrff  lahore  Pakistan Punjab', '[{\"product_name\":\" WIFI Three gangs lighting switch _ Free Hardware\",\"product_id\":\"50\",\"quantity\":\"1\",\"mac_address\":\"10B65603\",\"image\":\"\",\"subscription_type\":\"3\",\"subscription_period_id\":\"3\"}]', 1, '[{\"id\":259,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(190, 372, 67, 106, '15-b  Islamabad 46000 Pakistan Federally Administered Tribal Areas', '[{\"product_name\":\" WIFI Three gangs lighting switch _ Free Hardware\",\"product_id\":\"50\",\"quantity\":\"1\",\"mac_address\":\"10B65603\",\"image\":\"\",\"subscription_type\":\"1\",\"subscription_period_id\":\"2\"}]', 1, '[{\"id\":260,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(191, 372, 67, 107, '15-b  Islamabad 46000 Pakistan Federally Administered Tribal Areas', '[{\"product_name\":\" WIFI Three gangs lighting switch _ Free Hardware\",\"product_id\":\"50\",\"quantity\":\"1\",\"mac_address\":\"10B65603\",\"image\":\"\",\"subscription_type\":\"3\",\"subscription_period_id\":\"3\"}]', 1, '[{\"id\":261,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(192, 373, 68, 108, '15-b  Islamabad 46000 Pakistan Federally Administered Tribal Areas', '[{\"product_name\":\" WIFI Three gangs lighting switch _ Free Hardware\",\"product_id\":\"50\",\"quantity\":\"1\",\"mac_address\":\"10B65603\",\"image\":\"\",\"subscription_type\":\"3\",\"subscription_period_id\":\"3\"}]', 1, '[{\"id\":262,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(193, 375, 69, 110, 'test  lahore  Pakistan Punjab', '[{\"product_name\":\" WIFI Three gangs lighting switch _ Free Hardware\",\"product_id\":\"50\",\"quantity\":\"1\",\"mac_address\":\"pp\",\"image\":\"\",\"subscription_type\":\"1\",\"subscription_period_id\":\"2\"},{\"product_name\":\"WIFI One gang lighting switch_ Free Hardware\",\"product_id\":\"51\",\"quantity\":\"2\",\"mac_address\":\"fff,sss\",\"image\":\"\",\"subscription_type\":\"3\",\"subscription_period_id\":\"2\"},{\"product_name\":\"WIFI A/C switch _ Free Hardware \",\"product_id\":\"53\",\"quantity\":\"2\",\"mac_address\":\"ooo,hhhhh\",\"image\":\"\",\"subscription_type\":\"2\",\"subscription_period_id\":\"2\"}]', 1, '[{\"id\":264,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(194, 376, 70, 111, 'test  lahore  Pakistan Punjab', '[{\"product_name\":\" WIFI Three gangs lighting switch _ Free Hardware\",\"product_id\":\"50\",\"quantity\":\"1\",\"mac_address\":\"DB876703\",\"image\":\"\",\"subscription_type\":\"1\",\"subscription_period_id\":\"2\"},{\"product_name\":\"WIFI One gang lighting switch_ Free Hardware\",\"product_id\":\"51\",\"quantity\":\"1\",\"mac_address\":\"0A581C04\",\"image\":\"\",\"subscription_type\":\"3\",\"subscription_period_id\":\"2\"},{\"product_name\":\"WIFI A/C switch _ Free Hardware \",\"product_id\":\"53\",\"quantity\":\"1\",\"mac_address\":\"DB8AC005\",\"image\":\"\",\"subscription_type\":\"2\",\"subscription_period_id\":\"3\"}]', 1, '[{\"id\":265,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(195, 376, 70, 112, 'test  lahore  Pakistan Punjab', '[{\"product_name\":\"WIFI Two gangs lighting switch_ Free Hardware\",\"product_id\":\"54\",\"quantity\":\"1\",\"mac_address\":\"DB8E4D02\",\"image\":\"\",\"subscription_type\":\"3\",\"subscription_period_id\":\"3\"}]', 1, '[{\"id\":266,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(196, 382, 71, 116, '#283 AKS colony Zirakpur  MOhali 140603 India Punjab', '[{\"product_name\":\" ????? ????? ??? ??? ????? ???? ? ?????? ?????\",\"product_id\":\"50\",\"quantity\":\"1\",\"mac_address\":\"plm4\",\"image\":\"\",\"subscription_type\":\"3\",\"subscription_period_id\":\"3\"},{\"product_name\":\"????? ????? ???? ??? ??? - ?????? ?????\",\"product_id\":\"54\",\"quantity\":\"1\",\"mac_address\":\"7abc\",\"image\":\"\",\"subscription_type\":\"3\",\"subscription_period_id\":\"3\"}]', 1, '[{\"id\":271,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(197, 382, 71, 117, '#283 AKS colony Zirakpur  MOhali 140603 India Punjab', '[{\"product_name\":\"????? ????? ???? ??? ??? - ?????? ?????\",\"product_id\":\"54\",\"quantity\":\"1\",\"mac_address\":\"qwe3\",\"image\":\"\",\"subscription_type\":\"3\",\"subscription_period_id\":\"3\"},{\"product_name\":\"????? ????? ?? ???? ??? ??? - ?????? ?????\",\"product_id\":\"51\",\"quantity\":\"1\",\"mac_address\":\"wer4\",\"image\":\"\",\"subscription_type\":\"3\",\"subscription_period_id\":\"3\"}]', 1, '[{\"id\":272,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0),
(198, 383, 72, 120, 'house 273  MOhali  India Punjab', '[{\"product_name\":\"????? ????? ???? ??? ??? - ?????? ?????\",\"product_id\":\"54\",\"quantity\":\"1\",\"mac_address\":\"DB8E4602\",\"image\":\"\",\"subscription_type\":\"3\",\"subscription_period_id\":\"3\"}]', 1, '[{\"id\":273,\"status\":1,\"sh_result\":\"Record Saved.\"}]', 0);

-- --------------------------------------------------------

--
-- Table structure for table `order_mac_addresses`
--

CREATE TABLE `order_mac_addresses` (
  `id` int(10) UNSIGNED NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `order_product_id` int(11) DEFAULT NULL,
  `mac_address` varchar(50) DEFAULT NULL,
  `product_quantity` varchar(50) DEFAULT NULL,
  `picture` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `order_mac_addresses`
--

INSERT INTO `order_mac_addresses` (`id`, `order_id`, `order_product_id`, `mac_address`, `product_quantity`, `picture`) VALUES
(91, 22, 24, 'test', '1', 'logo.png'),
(95, 29, 32, 'SAJJAD123', '1', 'signal-bot.png'),
(105, 31, 34, 'terst', '1', 'Hospital Admissions Per Corp.png'),
(106, 9, 11, 'test123432', '3', 'Hospital Admissions Per Corp.png'),
(107, 9, 11, 'betta', '3', 'logo.png'),
(109, 9, 11, 'lifwea', '3', 'Hospital Admissions Per Corp.png'),
(110, 45, 48, 'mac_1_sar_36', '1', 'logo.png'),
(111, 45, 49, 'mac_2_sar_180', '1', 'image_2022-05-17_173001071-removebg-preview.png'),
(112, 54, 61, 'sit_livixa', '1', 'image_2022-06-18_171410260.png'),
(113, 55, 62, 'sit_faran', '2', 'logo.png'),
(114, 55, 62, 'sit_faran2', '2', 'Hospital Admissions Per Corp.png'),
(115, 55, 63, 'sit_faran3', '3', 'Hospital Admissions Per Corp.png'),
(116, 55, 63, 'sit_faran4', '3', 'logo.png'),
(117, 55, 63, 'sit_faran5', '3', 'image_2022-05-17_173001071-removebg-preview.png'),
(120, 58, 70, 'sit_farukh', '2', 'logo.png'),
(119, 1, 1, 'sajjad_mac1', '1', 'image_2022-05-17_173001071-removebg-preview.png'),
(121, 58, 70, 'sit_farukh2', '2', 'Hospital Admissions Per Corp.png'),
(122, 58, 71, 'sit_farukh3', '2', 'Hospital Admissions Per Corp.png'),
(123, 58, 71, 'sit_farukh4', '2', 'logo.png'),
(124, 62, 80, 'sit_livx123', '2', 'logo.png'),
(125, 62, 80, 'sit_livx321', '2', 'Hospital Admissions Per Corp.png'),
(126, 62, 81, 'sit_livx11', '4', 'logo.png'),
(127, 62, 81, 'sit_livx12', '4', 'Hospital Admissions Per Corp.png'),
(128, 62, 81, 'sit_livx_13', '4', 'image_2022-05-17_173001071-removebg-preview.png'),
(129, 62, 81, 'sit_livx_14', '4', 'image_2022-05-17_172929129.png'),
(130, 63, 82, 'sit_farukh99', '3', 'Hospital Admissions Per Corp.png'),
(131, 63, 82, 'sit_farukh98', '3', 'logo.png'),
(132, 63, 82, 'sit_farukh97', '3', 'image_2022-05-17_173001071-removebg-preview.png'),
(133, 64, 83, 'sit_livx1', '2', 'image_2022-05-17_173001071-removebg-preview.png'),
(134, 64, 83, 'sit_livx2', '2', 'logo.png'),
(135, 64, 84, 'sit_livx3', '2', 'logo.png'),
(136, 64, 84, 'sit_livx4', '2', 'image_2022-05-17_173001071-removebg-preview.png'),
(137, 67, 88, 'mac_bushra', '1', 'logo.png'),
(138, 74, 104, 'sit_final_livx', '2', 'logo.png'),
(139, 74, 104, 'sit_final_livx_1', '2', 'Hospital Admissions Per Corp.png'),
(140, 75, 105, 'sit_livx_final_1', '1', 'hoonppubf.png'),
(141, 76, 106, 'liv_sit', '1', 'hoonppubf.png'),
(142, 77, 107, 'ssit_liv', '1', 'Hospital Admissions Per Corp.png'),
(143, 78, 108, 'sit_mac_test1234', '2', 'logo.png'),
(144, 78, 108, 'sit_mac_test_45678', '2', 'Hospital Admissions Per Corp.png'),
(151, 80, 114, '12345', '1', NULL),
(150, 79, 109, 'sstti_new1', '1', NULL),
(156, 85, 116, '4abc', '1', NULL),
(157, 86, 117, '3abc', '1', NULL),
(158, 87, 118, 'rty8', '2', NULL),
(160, 93, 126, 'plm2', '1', NULL),
(163, 94, 127, 'okn2', '1', NULL),
(164, 97, 134, 'qaz1', '3', NULL),
(165, 97, 134, 'qaz2', '3', NULL),
(166, 97, 134, 'qaz3', '3', NULL),
(167, 98, 135, 'qaz4', '1', NULL),
(170, 100, 137, 'ygv2', '2', NULL),
(169, 99, 136, 'uhb3', '1', NULL),
(171, 100, 137, 'ygv3', '2', NULL),
(172, 103, 141, '22222222', '1', NULL),
(178, 108, 146, '10B65603', '1', NULL),
(176, 106, 144, 'plm3', '1', NULL),
(188, 110, 149, 'sss', '2', NULL),
(180, 110, 149, 'fff', '2', NULL),
(190, 111, 151, 'DB876703', '1', NULL),
(182, 110, 150, 'ooo', '2', NULL),
(189, 110, 150, 'hhhhh', '2', NULL),
(187, 110, 148, 'pp', '1', NULL),
(191, 111, 152, '0A581C04', '1', NULL),
(192, 111, 153, 'DB8AC005', '1', NULL),
(193, 112, 154, 'DB8E4D02', '1', NULL),
(194, 116, 161, 'plm4', '1', NULL),
(195, 116, 162, '7abc', '1', NULL),
(199, 117, 164, 'qwe3', '1', NULL),
(197, 117, 163, 'wer4', '1', NULL),
(204, 120, 167, 'DB8E4602', '1', NULL),
(201, 118, 165, 'asd2', '1', NULL),
(203, 119, 166, 'asd4', '1', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `packages`
--

CREATE TABLE `packages` (
  `id` int(11) NOT NULL,
  `package` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `packages`
--

INSERT INTO `packages` (`id`, `package`) VALUES
(1, 'Economy Package'),
(2, 'Mid Package'),
(3, 'High Package');

-- --------------------------------------------------------

--
-- Table structure for table `subscriptions`
--

CREATE TABLE `subscriptions` (
  `id` int(11) NOT NULL,
  `subscription` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `subscriptions`
--

INSERT INTO `subscriptions` (`id`, `subscription`) VALUES
(1, '1 month'),
(2, '3 months'),
(3, '12 months');

-- --------------------------------------------------------

--
-- Table structure for table `subscription_package_price`
--

CREATE TABLE `subscription_package_price` (
  `id` int(11) NOT NULL,
  `subscription_id` int(11) NOT NULL,
  `package_id` int(11) NOT NULL,
  `price` decimal(15,2) NOT NULL,
  `option_id` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 ROW_FORMAT=FIXED;

--
-- Dumping data for table `subscription_package_price`
--

INSERT INTO `subscription_package_price` (`id`, `subscription_id`, `package_id`, `price`, `option_id`) VALUES
(1, 1, 1, '15.00', 50),
(2, 2, 1, '45.00', 51),
(3, 3, 1, '180.00', 52),
(4, 1, 2, '26.00', 53),
(5, 2, 2, '78.00', 54),
(6, 3, 2, '321.00', 55),
(7, 1, 3, '36.00', 56),
(8, 2, 3, '108.00', 57),
(9, 3, 3, '432.00', 58);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `features`
--
ALTER TABLE `features`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `features_subscription`
--
ALTER TABLE `features_subscription`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `mac_inventory`
--
ALTER TABLE `mac_inventory`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `mac_address` (`mac_address`) USING BTREE;

--
-- Indexes for table `ocn8_address`
--
ALTER TABLE `ocn8_address`
  ADD PRIMARY KEY (`address_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `ocn8_api`
--
ALTER TABLE `ocn8_api`
  ADD PRIMARY KEY (`api_id`);

--
-- Indexes for table `ocn8_api_ip`
--
ALTER TABLE `ocn8_api_ip`
  ADD PRIMARY KEY (`api_ip_id`);

--
-- Indexes for table `ocn8_api_session`
--
ALTER TABLE `ocn8_api_session`
  ADD PRIMARY KEY (`api_session_id`);

--
-- Indexes for table `ocn8_attribute`
--
ALTER TABLE `ocn8_attribute`
  ADD PRIMARY KEY (`attribute_id`);

--
-- Indexes for table `ocn8_attribute_description`
--
ALTER TABLE `ocn8_attribute_description`
  ADD PRIMARY KEY (`attribute_id`,`language_id`);

--
-- Indexes for table `ocn8_attribute_group`
--
ALTER TABLE `ocn8_attribute_group`
  ADD PRIMARY KEY (`attribute_group_id`);

--
-- Indexes for table `ocn8_attribute_group_description`
--
ALTER TABLE `ocn8_attribute_group_description`
  ADD PRIMARY KEY (`attribute_group_id`,`language_id`);

--
-- Indexes for table `ocn8_banner`
--
ALTER TABLE `ocn8_banner`
  ADD PRIMARY KEY (`banner_id`);

--
-- Indexes for table `ocn8_banner_image`
--
ALTER TABLE `ocn8_banner_image`
  ADD PRIMARY KEY (`banner_image_id`);

--
-- Indexes for table `ocn8_cart`
--
ALTER TABLE `ocn8_cart`
  ADD PRIMARY KEY (`cart_id`),
  ADD KEY `cart_id` (`api_id`,`customer_id`,`session_id`,`product_id`,`recurring_id`);

--
-- Indexes for table `ocn8_category`
--
ALTER TABLE `ocn8_category`
  ADD PRIMARY KEY (`category_id`),
  ADD KEY `parent_id` (`parent_id`);

--
-- Indexes for table `ocn8_category_description`
--
ALTER TABLE `ocn8_category_description`
  ADD PRIMARY KEY (`category_id`,`language_id`),
  ADD KEY `name` (`name`);

--
-- Indexes for table `ocn8_category_filter`
--
ALTER TABLE `ocn8_category_filter`
  ADD PRIMARY KEY (`category_id`,`filter_id`);

--
-- Indexes for table `ocn8_category_path`
--
ALTER TABLE `ocn8_category_path`
  ADD PRIMARY KEY (`category_id`,`path_id`);

--
-- Indexes for table `ocn8_category_to_layout`
--
ALTER TABLE `ocn8_category_to_layout`
  ADD PRIMARY KEY (`category_id`,`store_id`);

--
-- Indexes for table `ocn8_category_to_store`
--
ALTER TABLE `ocn8_category_to_store`
  ADD PRIMARY KEY (`category_id`,`store_id`);

--
-- Indexes for table `ocn8_country`
--
ALTER TABLE `ocn8_country`
  ADD PRIMARY KEY (`country_id`);

--
-- Indexes for table `ocn8_coupon`
--
ALTER TABLE `ocn8_coupon`
  ADD PRIMARY KEY (`coupon_id`);

--
-- Indexes for table `ocn8_coupon_category`
--
ALTER TABLE `ocn8_coupon_category`
  ADD PRIMARY KEY (`coupon_id`,`category_id`);

--
-- Indexes for table `ocn8_coupon_history`
--
ALTER TABLE `ocn8_coupon_history`
  ADD PRIMARY KEY (`coupon_history_id`);

--
-- Indexes for table `ocn8_coupon_product`
--
ALTER TABLE `ocn8_coupon_product`
  ADD PRIMARY KEY (`coupon_product_id`);

--
-- Indexes for table `ocn8_currency`
--
ALTER TABLE `ocn8_currency`
  ADD PRIMARY KEY (`currency_id`);

--
-- Indexes for table `ocn8_customer`
--
ALTER TABLE `ocn8_customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `ocn8_customer_activity`
--
ALTER TABLE `ocn8_customer_activity`
  ADD PRIMARY KEY (`customer_activity_id`);

--
-- Indexes for table `ocn8_customer_affiliate`
--
ALTER TABLE `ocn8_customer_affiliate`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `ocn8_customer_approval`
--
ALTER TABLE `ocn8_customer_approval`
  ADD PRIMARY KEY (`customer_approval_id`);

--
-- Indexes for table `ocn8_customer_group`
--
ALTER TABLE `ocn8_customer_group`
  ADD PRIMARY KEY (`customer_group_id`);

--
-- Indexes for table `ocn8_customer_group_description`
--
ALTER TABLE `ocn8_customer_group_description`
  ADD PRIMARY KEY (`customer_group_id`,`language_id`);

--
-- Indexes for table `ocn8_customer_history`
--
ALTER TABLE `ocn8_customer_history`
  ADD PRIMARY KEY (`customer_history_id`);

--
-- Indexes for table `ocn8_customer_ip`
--
ALTER TABLE `ocn8_customer_ip`
  ADD PRIMARY KEY (`customer_ip_id`),
  ADD KEY `ip` (`ip`);

--
-- Indexes for table `ocn8_customer_login`
--
ALTER TABLE `ocn8_customer_login`
  ADD PRIMARY KEY (`customer_login_id`),
  ADD KEY `email` (`email`),
  ADD KEY `ip` (`ip`);

--
-- Indexes for table `ocn8_customer_online`
--
ALTER TABLE `ocn8_customer_online`
  ADD PRIMARY KEY (`ip`);

--
-- Indexes for table `ocn8_customer_reward`
--
ALTER TABLE `ocn8_customer_reward`
  ADD PRIMARY KEY (`customer_reward_id`);

--
-- Indexes for table `ocn8_customer_search`
--
ALTER TABLE `ocn8_customer_search`
  ADD PRIMARY KEY (`customer_search_id`);

--
-- Indexes for table `ocn8_customer_transaction`
--
ALTER TABLE `ocn8_customer_transaction`
  ADD PRIMARY KEY (`customer_transaction_id`);

--
-- Indexes for table `ocn8_customer_wishlist`
--
ALTER TABLE `ocn8_customer_wishlist`
  ADD PRIMARY KEY (`customer_id`,`product_id`);

--
-- Indexes for table `ocn8_custom_field`
--
ALTER TABLE `ocn8_custom_field`
  ADD PRIMARY KEY (`custom_field_id`);

--
-- Indexes for table `ocn8_custom_field_customer_group`
--
ALTER TABLE `ocn8_custom_field_customer_group`
  ADD PRIMARY KEY (`custom_field_id`,`customer_group_id`);

--
-- Indexes for table `ocn8_custom_field_description`
--
ALTER TABLE `ocn8_custom_field_description`
  ADD PRIMARY KEY (`custom_field_id`,`language_id`);

--
-- Indexes for table `ocn8_custom_field_value`
--
ALTER TABLE `ocn8_custom_field_value`
  ADD PRIMARY KEY (`custom_field_value_id`);

--
-- Indexes for table `ocn8_custom_field_value_description`
--
ALTER TABLE `ocn8_custom_field_value_description`
  ADD PRIMARY KEY (`custom_field_value_id`,`language_id`);

--
-- Indexes for table `ocn8_download`
--
ALTER TABLE `ocn8_download`
  ADD PRIMARY KEY (`download_id`);

--
-- Indexes for table `ocn8_download_description`
--
ALTER TABLE `ocn8_download_description`
  ADD PRIMARY KEY (`download_id`,`language_id`);

--
-- Indexes for table `ocn8_event`
--
ALTER TABLE `ocn8_event`
  ADD PRIMARY KEY (`event_id`);

--
-- Indexes for table `ocn8_extension`
--
ALTER TABLE `ocn8_extension`
  ADD PRIMARY KEY (`extension_id`);

--
-- Indexes for table `ocn8_extension_install`
--
ALTER TABLE `ocn8_extension_install`
  ADD PRIMARY KEY (`extension_install_id`);

--
-- Indexes for table `ocn8_extension_path`
--
ALTER TABLE `ocn8_extension_path`
  ADD PRIMARY KEY (`extension_path_id`);

--
-- Indexes for table `ocn8_filter`
--
ALTER TABLE `ocn8_filter`
  ADD PRIMARY KEY (`filter_id`);

--
-- Indexes for table `ocn8_filter_description`
--
ALTER TABLE `ocn8_filter_description`
  ADD PRIMARY KEY (`filter_id`,`language_id`);

--
-- Indexes for table `ocn8_filter_group`
--
ALTER TABLE `ocn8_filter_group`
  ADD PRIMARY KEY (`filter_group_id`);

--
-- Indexes for table `ocn8_filter_group_description`
--
ALTER TABLE `ocn8_filter_group_description`
  ADD PRIMARY KEY (`filter_group_id`,`language_id`);

--
-- Indexes for table `ocn8_geo_zone`
--
ALTER TABLE `ocn8_geo_zone`
  ADD PRIMARY KEY (`geo_zone_id`);

--
-- Indexes for table `ocn8_googleshopping_category`
--
ALTER TABLE `ocn8_googleshopping_category`
  ADD PRIMARY KEY (`google_product_category`,`store_id`),
  ADD KEY `category_id_store_id` (`category_id`,`store_id`);

--
-- Indexes for table `ocn8_googleshopping_product`
--
ALTER TABLE `ocn8_googleshopping_product`
  ADD PRIMARY KEY (`product_advertise_google_id`),
  ADD UNIQUE KEY `product_id_store_id` (`product_id`,`store_id`);

--
-- Indexes for table `ocn8_googleshopping_product_status`
--
ALTER TABLE `ocn8_googleshopping_product_status`
  ADD PRIMARY KEY (`product_id`,`store_id`,`product_variation_id`);

--
-- Indexes for table `ocn8_googleshopping_product_target`
--
ALTER TABLE `ocn8_googleshopping_product_target`
  ADD PRIMARY KEY (`product_id`,`advertise_google_target_id`);

--
-- Indexes for table `ocn8_googleshopping_target`
--
ALTER TABLE `ocn8_googleshopping_target`
  ADD PRIMARY KEY (`advertise_google_target_id`),
  ADD KEY `store_id` (`store_id`);

--
-- Indexes for table `ocn8_information`
--
ALTER TABLE `ocn8_information`
  ADD PRIMARY KEY (`information_id`);

--
-- Indexes for table `ocn8_information_description`
--
ALTER TABLE `ocn8_information_description`
  ADD PRIMARY KEY (`information_id`,`language_id`);

--
-- Indexes for table `ocn8_information_to_layout`
--
ALTER TABLE `ocn8_information_to_layout`
  ADD PRIMARY KEY (`information_id`,`store_id`);

--
-- Indexes for table `ocn8_information_to_store`
--
ALTER TABLE `ocn8_information_to_store`
  ADD PRIMARY KEY (`information_id`,`store_id`);

--
-- Indexes for table `ocn8_language`
--
ALTER TABLE `ocn8_language`
  ADD PRIMARY KEY (`language_id`),
  ADD KEY `name` (`name`);

--
-- Indexes for table `ocn8_layout`
--
ALTER TABLE `ocn8_layout`
  ADD PRIMARY KEY (`layout_id`);

--
-- Indexes for table `ocn8_layout_module`
--
ALTER TABLE `ocn8_layout_module`
  ADD PRIMARY KEY (`layout_module_id`);

--
-- Indexes for table `ocn8_layout_route`
--
ALTER TABLE `ocn8_layout_route`
  ADD PRIMARY KEY (`layout_route_id`);

--
-- Indexes for table `ocn8_length_class`
--
ALTER TABLE `ocn8_length_class`
  ADD PRIMARY KEY (`length_class_id`);

--
-- Indexes for table `ocn8_length_class_description`
--
ALTER TABLE `ocn8_length_class_description`
  ADD PRIMARY KEY (`length_class_id`,`language_id`);

--
-- Indexes for table `ocn8_location`
--
ALTER TABLE `ocn8_location`
  ADD PRIMARY KEY (`location_id`),
  ADD KEY `name` (`name`);

--
-- Indexes for table `ocn8_manufacturer`
--
ALTER TABLE `ocn8_manufacturer`
  ADD PRIMARY KEY (`manufacturer_id`);

--
-- Indexes for table `ocn8_manufacturer_to_store`
--
ALTER TABLE `ocn8_manufacturer_to_store`
  ADD PRIMARY KEY (`manufacturer_id`,`store_id`);

--
-- Indexes for table `ocn8_marketing`
--
ALTER TABLE `ocn8_marketing`
  ADD PRIMARY KEY (`marketing_id`);

--
-- Indexes for table `ocn8_modification`
--
ALTER TABLE `ocn8_modification`
  ADD PRIMARY KEY (`modification_id`);

--
-- Indexes for table `ocn8_module`
--
ALTER TABLE `ocn8_module`
  ADD PRIMARY KEY (`module_id`);

--
-- Indexes for table `ocn8_option`
--
ALTER TABLE `ocn8_option`
  ADD PRIMARY KEY (`option_id`);

--
-- Indexes for table `ocn8_option_description`
--
ALTER TABLE `ocn8_option_description`
  ADD PRIMARY KEY (`option_id`,`language_id`);

--
-- Indexes for table `ocn8_option_value`
--
ALTER TABLE `ocn8_option_value`
  ADD PRIMARY KEY (`option_value_id`);

--
-- Indexes for table `ocn8_option_value_description`
--
ALTER TABLE `ocn8_option_value_description`
  ADD PRIMARY KEY (`option_value_id`,`language_id`);

--
-- Indexes for table `ocn8_order`
--
ALTER TABLE `ocn8_order`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `ocn8_order_history`
--
ALTER TABLE `ocn8_order_history`
  ADD PRIMARY KEY (`order_history_id`);

--
-- Indexes for table `ocn8_order_option`
--
ALTER TABLE `ocn8_order_option`
  ADD PRIMARY KEY (`order_option_id`);

--
-- Indexes for table `ocn8_order_product`
--
ALTER TABLE `ocn8_order_product`
  ADD PRIMARY KEY (`order_product_id`),
  ADD KEY `order_id` (`order_id`);

--
-- Indexes for table `ocn8_order_recurring`
--
ALTER TABLE `ocn8_order_recurring`
  ADD PRIMARY KEY (`order_recurring_id`);

--
-- Indexes for table `ocn8_order_recurring_transaction`
--
ALTER TABLE `ocn8_order_recurring_transaction`
  ADD PRIMARY KEY (`order_recurring_transaction_id`);

--
-- Indexes for table `ocn8_order_shipment`
--
ALTER TABLE `ocn8_order_shipment`
  ADD PRIMARY KEY (`order_shipment_id`);

--
-- Indexes for table `ocn8_order_status`
--
ALTER TABLE `ocn8_order_status`
  ADD PRIMARY KEY (`order_status_id`,`language_id`);

--
-- Indexes for table `ocn8_order_total`
--
ALTER TABLE `ocn8_order_total`
  ADD PRIMARY KEY (`order_total_id`),
  ADD KEY `order_id` (`order_id`);

--
-- Indexes for table `ocn8_order_voucher`
--
ALTER TABLE `ocn8_order_voucher`
  ADD PRIMARY KEY (`order_voucher_id`);

--
-- Indexes for table `ocn8_product`
--
ALTER TABLE `ocn8_product`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `ocn8_product_attribute`
--
ALTER TABLE `ocn8_product_attribute`
  ADD PRIMARY KEY (`product_id`,`attribute_id`,`language_id`);

--
-- Indexes for table `ocn8_product_description`
--
ALTER TABLE `ocn8_product_description`
  ADD PRIMARY KEY (`product_id`,`language_id`),
  ADD KEY `name` (`name`);

--
-- Indexes for table `ocn8_product_discount`
--
ALTER TABLE `ocn8_product_discount`
  ADD PRIMARY KEY (`product_discount_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `ocn8_product_filter`
--
ALTER TABLE `ocn8_product_filter`
  ADD PRIMARY KEY (`product_id`,`filter_id`);

--
-- Indexes for table `ocn8_product_image`
--
ALTER TABLE `ocn8_product_image`
  ADD PRIMARY KEY (`product_image_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `ocn8_product_option`
--
ALTER TABLE `ocn8_product_option`
  ADD PRIMARY KEY (`product_option_id`);

--
-- Indexes for table `ocn8_product_option_value`
--
ALTER TABLE `ocn8_product_option_value`
  ADD PRIMARY KEY (`product_option_value_id`);

--
-- Indexes for table `ocn8_product_recurring`
--
ALTER TABLE `ocn8_product_recurring`
  ADD PRIMARY KEY (`product_id`,`recurring_id`,`customer_group_id`);

--
-- Indexes for table `ocn8_product_related`
--
ALTER TABLE `ocn8_product_related`
  ADD PRIMARY KEY (`product_id`,`related_id`);

--
-- Indexes for table `ocn8_product_reward`
--
ALTER TABLE `ocn8_product_reward`
  ADD PRIMARY KEY (`product_reward_id`);

--
-- Indexes for table `ocn8_product_special`
--
ALTER TABLE `ocn8_product_special`
  ADD PRIMARY KEY (`product_special_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `ocn8_product_to_category`
--
ALTER TABLE `ocn8_product_to_category`
  ADD PRIMARY KEY (`product_id`,`category_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `ocn8_product_to_download`
--
ALTER TABLE `ocn8_product_to_download`
  ADD PRIMARY KEY (`product_id`,`download_id`);

--
-- Indexes for table `ocn8_product_to_layout`
--
ALTER TABLE `ocn8_product_to_layout`
  ADD PRIMARY KEY (`product_id`,`store_id`);

--
-- Indexes for table `ocn8_product_to_store`
--
ALTER TABLE `ocn8_product_to_store`
  ADD PRIMARY KEY (`product_id`,`store_id`);

--
-- Indexes for table `ocn8_recurring`
--
ALTER TABLE `ocn8_recurring`
  ADD PRIMARY KEY (`recurring_id`);

--
-- Indexes for table `ocn8_recurring_description`
--
ALTER TABLE `ocn8_recurring_description`
  ADD PRIMARY KEY (`recurring_id`,`language_id`);

--
-- Indexes for table `ocn8_return`
--
ALTER TABLE `ocn8_return`
  ADD PRIMARY KEY (`return_id`);

--
-- Indexes for table `ocn8_return_action`
--
ALTER TABLE `ocn8_return_action`
  ADD PRIMARY KEY (`return_action_id`,`language_id`);

--
-- Indexes for table `ocn8_return_history`
--
ALTER TABLE `ocn8_return_history`
  ADD PRIMARY KEY (`return_history_id`);

--
-- Indexes for table `ocn8_return_reason`
--
ALTER TABLE `ocn8_return_reason`
  ADD PRIMARY KEY (`return_reason_id`,`language_id`);

--
-- Indexes for table `ocn8_return_status`
--
ALTER TABLE `ocn8_return_status`
  ADD PRIMARY KEY (`return_status_id`,`language_id`);

--
-- Indexes for table `ocn8_review`
--
ALTER TABLE `ocn8_review`
  ADD PRIMARY KEY (`review_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `ocn8_seo_url`
--
ALTER TABLE `ocn8_seo_url`
  ADD PRIMARY KEY (`seo_url_id`),
  ADD KEY `query` (`query`),
  ADD KEY `keyword` (`keyword`);

--
-- Indexes for table `ocn8_session`
--
ALTER TABLE `ocn8_session`
  ADD PRIMARY KEY (`session_id`);

--
-- Indexes for table `ocn8_setting`
--
ALTER TABLE `ocn8_setting`
  ADD PRIMARY KEY (`setting_id`);

--
-- Indexes for table `ocn8_shipping_courier`
--
ALTER TABLE `ocn8_shipping_courier`
  ADD PRIMARY KEY (`shipping_courier_id`);

--
-- Indexes for table `ocn8_statistics`
--
ALTER TABLE `ocn8_statistics`
  ADD PRIMARY KEY (`statistics_id`);

--
-- Indexes for table `ocn8_stock_status`
--
ALTER TABLE `ocn8_stock_status`
  ADD PRIMARY KEY (`stock_status_id`,`language_id`);

--
-- Indexes for table `ocn8_store`
--
ALTER TABLE `ocn8_store`
  ADD PRIMARY KEY (`store_id`);

--
-- Indexes for table `ocn8_tax_class`
--
ALTER TABLE `ocn8_tax_class`
  ADD PRIMARY KEY (`tax_class_id`);

--
-- Indexes for table `ocn8_tax_rate`
--
ALTER TABLE `ocn8_tax_rate`
  ADD PRIMARY KEY (`tax_rate_id`);

--
-- Indexes for table `ocn8_tax_rate_to_customer_group`
--
ALTER TABLE `ocn8_tax_rate_to_customer_group`
  ADD PRIMARY KEY (`tax_rate_id`,`customer_group_id`);

--
-- Indexes for table `ocn8_tax_rule`
--
ALTER TABLE `ocn8_tax_rule`
  ADD PRIMARY KEY (`tax_rule_id`);

--
-- Indexes for table `ocn8_theme`
--
ALTER TABLE `ocn8_theme`
  ADD PRIMARY KEY (`theme_id`);

--
-- Indexes for table `ocn8_translation`
--
ALTER TABLE `ocn8_translation`
  ADD PRIMARY KEY (`translation_id`);

--
-- Indexes for table `ocn8_upload`
--
ALTER TABLE `ocn8_upload`
  ADD PRIMARY KEY (`upload_id`);

--
-- Indexes for table `ocn8_user`
--
ALTER TABLE `ocn8_user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `ocn8_user_group`
--
ALTER TABLE `ocn8_user_group`
  ADD PRIMARY KEY (`user_group_id`);

--
-- Indexes for table `ocn8_voucher`
--
ALTER TABLE `ocn8_voucher`
  ADD PRIMARY KEY (`voucher_id`);

--
-- Indexes for table `ocn8_voucher_history`
--
ALTER TABLE `ocn8_voucher_history`
  ADD PRIMARY KEY (`voucher_history_id`);

--
-- Indexes for table `ocn8_voucher_theme`
--
ALTER TABLE `ocn8_voucher_theme`
  ADD PRIMARY KEY (`voucher_theme_id`);

--
-- Indexes for table `ocn8_voucher_theme_description`
--
ALTER TABLE `ocn8_voucher_theme_description`
  ADD PRIMARY KEY (`voucher_theme_id`,`language_id`);

--
-- Indexes for table `ocn8_weight_class`
--
ALTER TABLE `ocn8_weight_class`
  ADD PRIMARY KEY (`weight_class_id`);

--
-- Indexes for table `ocn8_weight_class_description`
--
ALTER TABLE `ocn8_weight_class_description`
  ADD PRIMARY KEY (`weight_class_id`,`language_id`);

--
-- Indexes for table `ocn8_zone`
--
ALTER TABLE `ocn8_zone`
  ADD PRIMARY KEY (`zone_id`);

--
-- Indexes for table `ocn8_zone_to_geo_zone`
--
ALTER TABLE `ocn8_zone_to_geo_zone`
  ADD PRIMARY KEY (`zone_to_geo_zone_id`);

--
-- Indexes for table `order_details_mobile_api`
--
ALTER TABLE `order_details_mobile_api`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `order_mac_addresses`
--
ALTER TABLE `order_mac_addresses`
  ADD PRIMARY KEY (`id`) USING BTREE,
  ADD UNIQUE KEY `idx_unique` (`mac_address`) USING BTREE;

--
-- Indexes for table `packages`
--
ALTER TABLE `packages`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `subscriptions`
--
ALTER TABLE `subscriptions`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indexes for table `subscription_package_price`
--
ALTER TABLE `subscription_package_price`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `features`
--
ALTER TABLE `features`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `features_subscription`
--
ALTER TABLE `features_subscription`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `mac_inventory`
--
ALTER TABLE `mac_inventory`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=97;

--
-- AUTO_INCREMENT for table `ocn8_address`
--
ALTER TABLE `ocn8_address`
  MODIFY `address_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `ocn8_api`
--
ALTER TABLE `ocn8_api`
  MODIFY `api_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `ocn8_api_ip`
--
ALTER TABLE `ocn8_api_ip`
  MODIFY `api_ip_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `ocn8_api_session`
--
ALTER TABLE `ocn8_api_session`
  MODIFY `api_session_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=963;

--
-- AUTO_INCREMENT for table `ocn8_attribute`
--
ALTER TABLE `ocn8_attribute`
  MODIFY `attribute_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `ocn8_attribute_group`
--
ALTER TABLE `ocn8_attribute_group`
  MODIFY `attribute_group_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `ocn8_banner`
--
ALTER TABLE `ocn8_banner`
  MODIFY `banner_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `ocn8_banner_image`
--
ALTER TABLE `ocn8_banner_image`
  MODIFY `banner_image_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=157;

--
-- AUTO_INCREMENT for table `ocn8_cart`
--
ALTER TABLE `ocn8_cart`
  MODIFY `cart_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=234;

--
-- AUTO_INCREMENT for table `ocn8_category`
--
ALTER TABLE `ocn8_category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT for table `ocn8_country`
--
ALTER TABLE `ocn8_country`
  MODIFY `country_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=258;

--
-- AUTO_INCREMENT for table `ocn8_coupon`
--
ALTER TABLE `ocn8_coupon`
  MODIFY `coupon_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `ocn8_coupon_history`
--
ALTER TABLE `ocn8_coupon_history`
  MODIFY `coupon_history_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_coupon_product`
--
ALTER TABLE `ocn8_coupon_product`
  MODIFY `coupon_product_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_currency`
--
ALTER TABLE `ocn8_currency`
  MODIFY `currency_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `ocn8_customer`
--
ALTER TABLE `ocn8_customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- AUTO_INCREMENT for table `ocn8_customer_activity`
--
ALTER TABLE `ocn8_customer_activity`
  MODIFY `customer_activity_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_customer_approval`
--
ALTER TABLE `ocn8_customer_approval`
  MODIFY `customer_approval_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_customer_group`
--
ALTER TABLE `ocn8_customer_group`
  MODIFY `customer_group_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `ocn8_customer_history`
--
ALTER TABLE `ocn8_customer_history`
  MODIFY `customer_history_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_customer_ip`
--
ALTER TABLE `ocn8_customer_ip`
  MODIFY `customer_ip_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT for table `ocn8_customer_login`
--
ALTER TABLE `ocn8_customer_login`
  MODIFY `customer_login_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `ocn8_customer_reward`
--
ALTER TABLE `ocn8_customer_reward`
  MODIFY `customer_reward_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_customer_search`
--
ALTER TABLE `ocn8_customer_search`
  MODIFY `customer_search_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_customer_transaction`
--
ALTER TABLE `ocn8_customer_transaction`
  MODIFY `customer_transaction_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_custom_field`
--
ALTER TABLE `ocn8_custom_field`
  MODIFY `custom_field_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_custom_field_value`
--
ALTER TABLE `ocn8_custom_field_value`
  MODIFY `custom_field_value_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_download`
--
ALTER TABLE `ocn8_download`
  MODIFY `download_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_event`
--
ALTER TABLE `ocn8_event`
  MODIFY `event_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `ocn8_extension`
--
ALTER TABLE `ocn8_extension`
  MODIFY `extension_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `ocn8_extension_install`
--
ALTER TABLE `ocn8_extension_install`
  MODIFY `extension_install_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `ocn8_extension_path`
--
ALTER TABLE `ocn8_extension_path`
  MODIFY `extension_path_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_filter`
--
ALTER TABLE `ocn8_filter`
  MODIFY `filter_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_filter_group`
--
ALTER TABLE `ocn8_filter_group`
  MODIFY `filter_group_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_geo_zone`
--
ALTER TABLE `ocn8_geo_zone`
  MODIFY `geo_zone_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `ocn8_googleshopping_product`
--
ALTER TABLE `ocn8_googleshopping_product`
  MODIFY `product_advertise_google_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `ocn8_information`
--
ALTER TABLE `ocn8_information`
  MODIFY `information_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `ocn8_language`
--
ALTER TABLE `ocn8_language`
  MODIFY `language_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `ocn8_layout`
--
ALTER TABLE `ocn8_layout`
  MODIFY `layout_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `ocn8_layout_module`
--
ALTER TABLE `ocn8_layout_module`
  MODIFY `layout_module_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT for table `ocn8_layout_route`
--
ALTER TABLE `ocn8_layout_route`
  MODIFY `layout_route_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT for table `ocn8_length_class`
--
ALTER TABLE `ocn8_length_class`
  MODIFY `length_class_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `ocn8_location`
--
ALTER TABLE `ocn8_location`
  MODIFY `location_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `ocn8_manufacturer`
--
ALTER TABLE `ocn8_manufacturer`
  MODIFY `manufacturer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `ocn8_marketing`
--
ALTER TABLE `ocn8_marketing`
  MODIFY `marketing_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `ocn8_modification`
--
ALTER TABLE `ocn8_modification`
  MODIFY `modification_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `ocn8_module`
--
ALTER TABLE `ocn8_module`
  MODIFY `module_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `ocn8_option`
--
ALTER TABLE `ocn8_option`
  MODIFY `option_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `ocn8_option_value`
--
ALTER TABLE `ocn8_option_value`
  MODIFY `option_value_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT for table `ocn8_order`
--
ALTER TABLE `ocn8_order`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=121;

--
-- AUTO_INCREMENT for table `ocn8_order_history`
--
ALTER TABLE `ocn8_order_history`
  MODIFY `order_history_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=129;

--
-- AUTO_INCREMENT for table `ocn8_order_option`
--
ALTER TABLE `ocn8_order_option`
  MODIFY `order_option_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=139;

--
-- AUTO_INCREMENT for table `ocn8_order_product`
--
ALTER TABLE `ocn8_order_product`
  MODIFY `order_product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=168;

--
-- AUTO_INCREMENT for table `ocn8_order_recurring`
--
ALTER TABLE `ocn8_order_recurring`
  MODIFY `order_recurring_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_order_recurring_transaction`
--
ALTER TABLE `ocn8_order_recurring_transaction`
  MODIFY `order_recurring_transaction_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_order_shipment`
--
ALTER TABLE `ocn8_order_shipment`
  MODIFY `order_shipment_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_order_status`
--
ALTER TABLE `ocn8_order_status`
  MODIFY `order_status_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `ocn8_order_total`
--
ALTER TABLE `ocn8_order_total`
  MODIFY `order_total_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=475;

--
-- AUTO_INCREMENT for table `ocn8_order_voucher`
--
ALTER TABLE `ocn8_order_voucher`
  MODIFY `order_voucher_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_product`
--
ALTER TABLE `ocn8_product`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT for table `ocn8_product_discount`
--
ALTER TABLE `ocn8_product_discount`
  MODIFY `product_discount_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=541;

--
-- AUTO_INCREMENT for table `ocn8_product_image`
--
ALTER TABLE `ocn8_product_image`
  MODIFY `product_image_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2356;

--
-- AUTO_INCREMENT for table `ocn8_product_option`
--
ALTER TABLE `ocn8_product_option`
  MODIFY `product_option_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=233;

--
-- AUTO_INCREMENT for table `ocn8_product_option_value`
--
ALTER TABLE `ocn8_product_option_value`
  MODIFY `product_option_value_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;

--
-- AUTO_INCREMENT for table `ocn8_product_reward`
--
ALTER TABLE `ocn8_product_reward`
  MODIFY `product_reward_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=547;

--
-- AUTO_INCREMENT for table `ocn8_product_special`
--
ALTER TABLE `ocn8_product_special`
  MODIFY `product_special_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=440;

--
-- AUTO_INCREMENT for table `ocn8_recurring`
--
ALTER TABLE `ocn8_recurring`
  MODIFY `recurring_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_return`
--
ALTER TABLE `ocn8_return`
  MODIFY `return_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_return_action`
--
ALTER TABLE `ocn8_return_action`
  MODIFY `return_action_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `ocn8_return_history`
--
ALTER TABLE `ocn8_return_history`
  MODIFY `return_history_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_return_reason`
--
ALTER TABLE `ocn8_return_reason`
  MODIFY `return_reason_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `ocn8_return_status`
--
ALTER TABLE `ocn8_return_status`
  MODIFY `return_status_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `ocn8_review`
--
ALTER TABLE `ocn8_review`
  MODIFY `review_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `ocn8_seo_url`
--
ALTER TABLE `ocn8_seo_url`
  MODIFY `seo_url_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=910;

--
-- AUTO_INCREMENT for table `ocn8_setting`
--
ALTER TABLE `ocn8_setting`
  MODIFY `setting_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3890;

--
-- AUTO_INCREMENT for table `ocn8_statistics`
--
ALTER TABLE `ocn8_statistics`
  MODIFY `statistics_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `ocn8_stock_status`
--
ALTER TABLE `ocn8_stock_status`
  MODIFY `stock_status_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `ocn8_store`
--
ALTER TABLE `ocn8_store`
  MODIFY `store_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_tax_class`
--
ALTER TABLE `ocn8_tax_class`
  MODIFY `tax_class_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `ocn8_tax_rate`
--
ALTER TABLE `ocn8_tax_rate`
  MODIFY `tax_rate_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=88;

--
-- AUTO_INCREMENT for table `ocn8_tax_rule`
--
ALTER TABLE `ocn8_tax_rule`
  MODIFY `tax_rule_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=129;

--
-- AUTO_INCREMENT for table `ocn8_theme`
--
ALTER TABLE `ocn8_theme`
  MODIFY `theme_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_translation`
--
ALTER TABLE `ocn8_translation`
  MODIFY `translation_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_upload`
--
ALTER TABLE `ocn8_upload`
  MODIFY `upload_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_user`
--
ALTER TABLE `ocn8_user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `ocn8_user_group`
--
ALTER TABLE `ocn8_user_group`
  MODIFY `user_group_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `ocn8_voucher`
--
ALTER TABLE `ocn8_voucher`
  MODIFY `voucher_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_voucher_history`
--
ALTER TABLE `ocn8_voucher_history`
  MODIFY `voucher_history_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `ocn8_voucher_theme`
--
ALTER TABLE `ocn8_voucher_theme`
  MODIFY `voucher_theme_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `ocn8_weight_class`
--
ALTER TABLE `ocn8_weight_class`
  MODIFY `weight_class_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `ocn8_zone`
--
ALTER TABLE `ocn8_zone`
  MODIFY `zone_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4239;

--
-- AUTO_INCREMENT for table `ocn8_zone_to_geo_zone`
--
ALTER TABLE `ocn8_zone_to_geo_zone`
  MODIFY `zone_to_geo_zone_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=110;

--
-- AUTO_INCREMENT for table `order_details_mobile_api`
--
ALTER TABLE `order_details_mobile_api`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=199;

--
-- AUTO_INCREMENT for table `order_mac_addresses`
--
ALTER TABLE `order_mac_addresses`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=205;

--
-- AUTO_INCREMENT for table `packages`
--
ALTER TABLE `packages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `subscriptions`
--
ALTER TABLE `subscriptions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `subscription_package_price`
--
ALTER TABLE `subscription_package_price`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
