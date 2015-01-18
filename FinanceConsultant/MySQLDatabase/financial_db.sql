-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 13, 2015 at 03:00 PM
-- Server version: 5.5.32
-- PHP Version: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `financial_db`
--
CREATE DATABASE IF NOT EXISTS `financial_db` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `financial_db`;

-- --------------------------------------------------------

--
-- Table structure for table `access_privilege`
--

CREATE TABLE IF NOT EXISTS `access_privilege` (
  `access_id` int(8) NOT NULL,
  `access_desc` varchar(20) NOT NULL,
  PRIMARY KEY (`access_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `access_privilege`
--

INSERT INTO `access_privilege` (`access_id`, `access_desc`) VALUES
(1, 'Consultant'),
(2, 'Client');

-- --------------------------------------------------------

--
-- Table structure for table `encrypteddatabase`
--

CREATE TABLE IF NOT EXISTS `encrypteddatabase` (
  `eTuple` varchar(12) NOT NULL,
  `clientID` char(8) NOT NULL,
  `clientName` varchar(20) NOT NULL,
  `consultantID` char(8) NOT NULL,
  `financial` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `financial_database`
--

CREATE TABLE IF NOT EXISTS `financial_database` (
  `clientID` char(8) NOT NULL,
  `clientName` varchar(20) NOT NULL,
  `consultantID` char(8) NOT NULL,
  `transaction` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `financial_database`
--

INSERT INTO `financial_database` (`clientID`, `clientName`, `consultantID`, `transaction`) VALUES
('abahecvd', 'john mathew', 'khdoichw', 0),
('nbsgxbcd', 'Bob Players', 'kjhswebs', 0),
('sdfsdjhd', 'Andy Cui', 'lkadwjf', 0),
('lkkasuer', 'Aniket Chaudhari', 'hfwehdfm', 0),
('uyhkacke', 'Fitria Adini', 'ljcjdslc', 0),
('qieimccj', 'Elif Ozgun', 'sdfjskjc', 0);

-- --------------------------------------------------------

--
-- Table structure for table `user_database`
--

CREATE TABLE IF NOT EXISTS `user_database` (
  `user_ID` char(8) NOT NULL,
  `name` varchar(20) NOT NULL,
  `access_id` int(11) NOT NULL,
  PRIMARY KEY (`user_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_database`
--

INSERT INTO `user_database` (`user_ID`, `name`, `access_id`) VALUES
('CF000001', 'me', 1),
('CF000002', 'ik', 2),
('CF000003', 'mi', 1),
('CF000004', 'ami', 1),
('CF000005', 'aa', 1),
('CF000006', 'as', 2),
('CF000007', 'test', 1),
('CF000008', 'reandert', 1),
('CF000009', 'cli', 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
