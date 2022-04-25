-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.14-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table electrogrid.bill
CREATE TABLE IF NOT EXISTS `bill` (
  `Billno` int(11) NOT NULL AUTO_INCREMENT,
  `CustomerID` int(11) NOT NULL,
  `Billamount` int(11) NOT NULL,
  `Billdate` varchar(50) DEFAULT NULL,
  `Paidamount` int(11) DEFAULT NULL,
  `Status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Billno`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table electrogrid.bill: ~2 rows (approximately)
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` (`Billno`, `CustomerID`, `Billamount`, `Billdate`, `Paidamount`, `Status`) VALUES
	(1, 3, 3000, '10.11.2021', 1500, 'not paid');
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;

-- Dumping structure for table electrogrid.consumption
CREATE TABLE IF NOT EXISTS `consumption` (
  `ConsumptionID` int(11) NOT NULL AUTO_INCREMENT,
  `CustomerID` int(11) DEFAULT NULL,
  `Present_reading` int(11) DEFAULT NULL,
  `Previous_reading` int(11) DEFAULT NULL,
  `Consumptionunit` int(11) DEFAULT NULL,
  `Tax` int(11) DEFAULT NULL,
  `Due_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ConsumptionID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table electrogrid.consumption: ~0 rows (approximately)
/*!40000 ALTER TABLE `consumption` DISABLE KEYS */;
/*!40000 ALTER TABLE `consumption` ENABLE KEYS */;

-- Dumping structure for table electrogrid.customers
CREATE TABLE IF NOT EXISTS `customers` (
  `CustomerID` int(11) NOT NULL AUTO_INCREMENT,
  `CustomerName` varchar(255) CHARACTER SET utf8 NOT NULL,
  `CustomerEmail` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `CustomerType` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `CustomerContact` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `CustomerAddress` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table electrogrid.customers: ~2 rows (approximately)
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` (`CustomerID`, `CustomerName`, `CustomerEmail`, `CustomerType`, `CustomerContact`, `CustomerAddress`) VALUES
	(2, 'Dil', 'dil@gmail.com', 'Old Customer', '0763055153', 'No 90'),
	(3, 'sachin Dileepa', 'sachinD@gmail.com', 'New Customer', '0763055154', 'no 90'),
	(4, 'anjalee', 'anjalee@gmail.com', 'Old', '087382345', 'No 40, Malabe');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;

-- Dumping structure for table electrogrid.electricity
CREATE TABLE IF NOT EXISTS `electricity` (
  `ElectricityboardID` int(11) NOT NULL AUTO_INCREMENT,
  `Electricityboard` varchar(50) DEFAULT NULL,
  `Location` varchar(50) DEFAULT NULL,
  `Contactnumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`ElectricityboardID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table electrogrid.electricity: ~0 rows (approximately)
/*!40000 ALTER TABLE `electricity` DISABLE KEYS */;
/*!40000 ALTER TABLE `electricity` ENABLE KEYS */;

-- Dumping structure for table electrogrid.employee
CREATE TABLE IF NOT EXISTS `employee` (
  `EmployeeID` int(11) NOT NULL AUTO_INCREMENT,
  `EmployeeName` varchar(250) DEFAULT NULL,
  `EmployeeEmail` varchar(250) DEFAULT NULL,
  `EmployeeType` varchar(250) DEFAULT NULL,
  `EmployeeContact` int(11) DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table electrogrid.employee: ~0 rows (approximately)
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;

-- Dumping structure for table electrogrid.feedback
CREATE TABLE IF NOT EXISTS `feedback` (
  `FeedbackID` int(11) NOT NULL AUTO_INCREMENT,
  `CustomerName` varchar(255) NOT NULL,
  `CustomerEmail` varchar(50) DEFAULT NULL,
  `Feedback` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`FeedbackID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table electrogrid.feedback: ~0 rows (approximately)
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;

-- Dumping structure for table electrogrid.payments
CREATE TABLE IF NOT EXISTS `payments` (
  `PaymentID` int(11) NOT NULL AUTO_INCREMENT,
  `PaymentDate` varchar(255) NOT NULL,
  `CardNumber` varchar(255) DEFAULT NULL,
  `Amount` varchar(255) DEFAULT NULL,
  `PaymentType` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PaymentID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table electrogrid.payments: ~1 rows (approximately)
/*!40000 ALTER TABLE `payments` DISABLE KEYS */;
INSERT INTO `payments` (`PaymentID`, `PaymentDate`, `CardNumber`, `Amount`, `PaymentType`) VALUES
	(2, '12.03.2022', '1121233123', '123450.00', 'Debit Card');
/*!40000 ALTER TABLE `payments` ENABLE KEYS */;

-- Dumping structure for table electrogrid.powerstation
CREATE TABLE IF NOT EXISTS `powerstation` (
  `PowerstationID` int(11) NOT NULL AUTO_INCREMENT,
  `Stationname` varchar(255) NOT NULL,
  `Province` varchar(255) NOT NULL,
  `Location` varchar(255) DEFAULT NULL,
  `Powergenerated` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PowerstationID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table electrogrid.powerstation: ~1 rows (approximately)
/*!40000 ALTER TABLE `powerstation` DISABLE KEYS */;
INSERT INTO `powerstation` (`PowerstationID`, `Stationname`, `Province`, `Location`, `Powergenerated`) VALUES
	(1, 'colombo', 'western', 'awissawella', '236MWh');
/*!40000 ALTER TABLE `powerstation` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
