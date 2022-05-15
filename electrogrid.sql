-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2022 at 09:45 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrogrid`
--

-- --------------------------------------------------------

--
-- Table structure for table `power_cut_schedule`
--

CREATE TABLE `power_cut_schedule` (
  `powercutscheduleID` int(11) NOT NULL,
  `area` varchar(200) CHARACTER SET latin1 NOT NULL,
  `starttime` varchar(30) CHARACTER SET latin1 NOT NULL,
  `endtime` varchar(30) CHARACTER SET latin1 NOT NULL,
  `type` varchar(10) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `power_cut_schedule`
--

INSERT INTO `power_cut_schedule` (`powercutscheduleID`, `area`, `starttime`, `endtime`, `type`) VALUES
(16, 'Malabe', '10.00a.m.', '12.00p.m.', 'B'),
(18, 'Jaffna', '08.00 a.m.', '10.00 a.m.', 'A'),
(19, 'Anuradhapura ', '12.00p.m.', '02.00p.m.', 'C'),
(20, 'Colombo', '10.00a.m.', '02.00p.m.', 'B');

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE `test` (
  `TestID` int(10) NOT NULL,
  `TestName` int(20) NOT NULL,
  `TestCode` int(10) NOT NULL,
  `TestDesc` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `power_cut_schedule`
--
ALTER TABLE `power_cut_schedule`
  ADD PRIMARY KEY (`powercutscheduleID`);

--
-- Indexes for table `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`TestID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `power_cut_schedule`
--
ALTER TABLE `power_cut_schedule`
  MODIFY `powercutscheduleID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `test`
--
ALTER TABLE `test`
  MODIFY `TestID` int(10) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
