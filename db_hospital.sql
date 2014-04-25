-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 25, 2014 at 12:10 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_hospital`
--

-- --------------------------------------------------------

--
-- Table structure for table `admitted_to`
--

CREATE TABLE IF NOT EXISTS `admitted_to` (
  `Patient_Id` int(11) NOT NULL,
  `Deptt_Id` int(11) NOT NULL,
  PRIMARY KEY (`Patient_Id`,`Deptt_Id`),
  KEY `Deptt_Id` (`Deptt_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admitted_to`
--

INSERT INTO `admitted_to` (`Patient_Id`, `Deptt_Id`) VALUES
(1, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `allocated_to`
--

CREATE TABLE IF NOT EXISTS `allocated_to` (
  `Bed_Id` int(11) NOT NULL,
  `InPatient_Id` int(11) NOT NULL,
  `Date` date NOT NULL,
  `In_Time` time NOT NULL,
  `Duration` int(11) NOT NULL,
  PRIMARY KEY (`Bed_Id`,`InPatient_Id`),
  KEY `Patient_ID` (`InPatient_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `alloted_to`
--

CREATE TABLE IF NOT EXISTS `alloted_to` (
  `Ambulance_Id` int(11) NOT NULL,
  `Driver_Id` int(11) NOT NULL,
  PRIMARY KEY (`Ambulance_Id`),
  KEY `Driver_Id` (`Driver_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `alloted_to`
--

INSERT INTO `alloted_to` (`Ambulance_Id`, `Driver_Id`) VALUES
(1, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `ambulance`
--

CREATE TABLE IF NOT EXISTS `ambulance` (
  `Ambulance_Id` int(11) NOT NULL,
  PRIMARY KEY (`Ambulance_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ambulance`
--

INSERT INTO `ambulance` (`Ambulance_Id`) VALUES
(0),
(1),
(2),
(3),
(4),
(67),
(960607);

-- --------------------------------------------------------

--
-- Table structure for table `beds`
--

CREATE TABLE IF NOT EXISTS `beds` (
  `Bed_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Bed_Status` tinyint(1) NOT NULL,
  `Maintained` tinyint(1) NOT NULL,
  PRIMARY KEY (`Bed_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE IF NOT EXISTS `bills` (
  `Bill_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Date` date NOT NULL,
  `Time` time NOT NULL,
  PRIMARY KEY (`Bill_Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `bills`
--

INSERT INTO `bills` (`Bill_Id`, `Date`, `Time`) VALUES
(1, '2014-04-16', '12:10:00'),
(2, '2014-07-11', '10:00:00'),
(3, '2013-12-24', '09:50:00'),
(4, '2014-04-01', '12:09:00');

-- --------------------------------------------------------

--
-- Table structure for table `brings`
--

CREATE TABLE IF NOT EXISTS `brings` (
  `Ambulance_Id` int(11) NOT NULL,
  `Date_Time` datetime NOT NULL,
  `User_Id` varchar(100) NOT NULL,
  PRIMARY KEY (`Ambulance_Id`,`Date_Time`,`User_Id`),
  KEY `User_Id` (`User_Id`),
  KEY `User_Id_2` (`User_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `brings`
--

INSERT INTO `brings` (`Ambulance_Id`, `Date_Time`, `User_Id`) VALUES
(1, '2014-04-01 05:00:00', 'a'),
(1, '2014-04-26 15:05:57', 'a'),
(1, '2014-04-26 15:15:18', 'a'),
(2, '2014-04-24 23:50:49', 'a'),
(2, '2014-04-24 23:52:43', 'a'),
(2, '2014-04-25 01:51:08', 'a'),
(2, '2014-04-25 03:31:48', 'a'),
(2, '2014-04-26 15:12:54', 'a');

-- --------------------------------------------------------

--
-- Table structure for table `casualty`
--

CREATE TABLE IF NOT EXISTS `casualty` (
  `Casualty_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Death_Date_Time` datetime NOT NULL,
  `DeathCertificateStatus` tinyint(1) NOT NULL,
  PRIMARY KEY (`Casualty_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE IF NOT EXISTS `company` (
  `Company_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Company_Name` varchar(100) NOT NULL,
  `Company_Address` varchar(100) NOT NULL,
  `Company_Contact` bigint(20) NOT NULL,
  `Supervisor` varchar(100) NOT NULL,
  PRIMARY KEY (`Company_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `consultant`
--

CREATE TABLE IF NOT EXISTS `consultant` (
  `Consultant_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Day` varchar(100) NOT NULL,
  `Time` time NOT NULL,
  `Category` varchar(100) NOT NULL,
  PRIMARY KEY (`Consultant_Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `consultant`
--

INSERT INTO `consultant` (`Consultant_Id`, `Day`, `Time`, `Category`) VALUES
(1, 'Monday', '00:00:12', 'Cardiologist'),
(2, 'Monday', '00:00:12', 'Cardiologist'),
(3, 'Monday', '00:00:12', 'Cardiologist'),
(4, 'Monday', '00:00:12', 'Cardiologist'),
(5, 'Monday', '00:00:12', 'Cardiologist'),
(6, 'Monday', '00:00:12', 'Cardiologist');

-- --------------------------------------------------------

--
-- Table structure for table `consults`
--

CREATE TABLE IF NOT EXISTS `consults` (
  `Patient_Id` int(11) NOT NULL,
  `Doctor_Id` int(11) NOT NULL,
  `Consult_Date_Time` datetime NOT NULL,
  PRIMARY KEY (`Patient_Id`,`Doctor_Id`),
  KEY `Doctor_Id` (`Doctor_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `consults`
--

INSERT INTO `consults` (`Patient_Id`, `Doctor_Id`, `Consult_Date_Time`) VALUES
(1, 1, '2014-04-21 06:00:00'),
(1, 3, '2014-04-21 06:00:00'),
(1, 5, '2014-04-26 06:00:00'),
(2, 1, '2014-04-21 06:15:00'),
(3, 1, '2014-04-30 07:00:00'),
(4, 2, '2014-04-29 04:00:00'),
(24, 1, '2014-05-01 06:00:00'),
(25, 28, '2014-04-25 01:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE IF NOT EXISTS `department` (
  `Department_Id` int(11) NOT NULL,
  `Department_Name` varchar(100) NOT NULL,
  PRIMARY KEY (`Department_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`Department_Id`, `Department_Name`) VALUES
(1, 'Kidney'),
(2, 'Heart');

-- --------------------------------------------------------

--
-- Table structure for table `doctors`
--

CREATE TABLE IF NOT EXISTS `doctors` (
  `Doctor_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Doctor_Name` varchar(100) NOT NULL,
  `Doctor_Age` int(11) NOT NULL,
  `Doctor_Sex` varchar(100) CHARACTER SET ascii NOT NULL,
  `Doctor_JoinDate` date NOT NULL,
  `Doctor_Qualification` varchar(100) NOT NULL,
  `Doctor_Start_Time` varchar(100) NOT NULL,
  `Doctor_End_Time` varchar(100) NOT NULL,
  `Doctor_Address` varchar(100) NOT NULL,
  `Doctor_Phone` varchar(100) NOT NULL,
  `Doctor_Password` varchar(32) NOT NULL,
  PRIMARY KEY (`Doctor_Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=30 ;

--
-- Dumping data for table `doctors`
--

INSERT INTO `doctors` (`Doctor_Id`, `Doctor_Name`, `Doctor_Age`, `Doctor_Sex`, `Doctor_JoinDate`, `Doctor_Qualification`, `Doctor_Start_Time`, `Doctor_End_Time`, `Doctor_Address`, `Doctor_Phone`, `Doctor_Password`) VALUES
(1, 'dr sureshle', 40, 'male', '2014-04-01', 'Cardiologist', '06:00:00', '07:00:00', '', '', ''),
(2, 'Dr. Reena', 40, 'female', '2014-04-02', 'Dentist', '00:00:00', '00:00:00', '', '', ''),
(3, 'Dr. Bhargav', 42, 'male', '2014-04-10', 'Cardiologist', '00:00:00', '00:00:00', '', '', ''),
(4, 'mainak', 12, 'male', '0000-00-00', 'phd', '00:00:00', '00:00:00', '', '', ''),
(5, 'mainak', 12, 'male', '0000-00-00', 'phd', '00:00:00', '00:00:00', '', '', ''),
(6, 'mainak', 12, 'male', '0000-00-00', 'phd', '00:00:00', '00:00:00', '', '', ''),
(7, 'reeta', 40, 'female', '0000-00-00', 'fAKKA', '00:00:00', '00:00:00', '', '', ''),
(8, 'reeta', 40, 'female', '0000-00-00', 'fAKKA', '00:00:00', '00:00:00', '', '', ''),
(9, 'reeta', 40, 'female', '0000-00-00', 'fAKKA', '00:00:00', '00:00:00', '', '', ''),
(10, 'GANDA', 45, 'MALE', '0000-00-00', 'FAKKA', '00:00:00', '00:00:00', '', '', ''),
(11, 'k', 12, 'male', '0000-00-00', 'yu', '00:00:00', '00:00:00', '', '', ''),
(12, 'q', 45, 'rt', '0000-00-00', 't', '00:00:00', '00:00:00', '', '', ''),
(13, '11', 11, '11', '0000-00-00', '11', '11', '11', '11', '11', '11'),
(14, '20', 20, '20', '0000-00-00', '20', '20', '20', '20', '20', '20'),
(15, 'GANDA', 12, 'male', '0000-00-00', 'phd', '', '', '335-b talwandi', '9957608684', '1234'),
(16, 'z', 1, 'z', '2012-12-12', 'z', '', '', 'z', '9085670543', 'z'),
(17, 'z', 1, 'z', '2012-12-12', 'z', '', '', 'z', '9085670543', 'z'),
(18, 'z', 1, 'z', '2012-12-12', 'z', '', '', 'z', '9085670543', 'z'),
(19, 'z', 1, 'z', '2012-12-12', 'z', '', '', 'z', '9085670543', 'z'),
(20, 'z', 1, 'z', '2012-12-12', 'z', '', '', 'z', '9085670543', 'z'),
(21, 'a', 21, 'm', '2012-12-12', 'a', '12:00', '04:00', 'fdg', 'gf', 'gfd'),
(22, 'a', 21, 'm', '2012-12-12', 'a', '12:00', '04:00', 'fdg', 'gf', 'gfd'),
(23, 'k', 20, 'k', '2012-10-10', 'k', '0 : 42', '4 : 42', 'k', '00986', 'k'),
(24, 't', 0, 'm', '0000-00-00', 'fyu', '3 : 54', '3 : 58', 'a', '800', 'ug'),
(25, 'd', 21, 'm', '2012-12-12', 'd', '1 : 1', '5 : 5', 'd', '8755', 'd'),
(26, 'Vivek', 21, 'm', '2012-12-12', 'a', '12:00', '04:00', 'z', '9085670543', '827ccb0eea8a706c4c34a16891f84e7b'),
(27, 'y', 21, 'm', '0000-00-00', 'y', '2 : 31', '14 : 31', 'y', '79086', '415290769594460e2e485922904f345d'),
(28, 'ankit', 0, 'kvkg', '0000-00-00', 'gjgi', '6 : 45', '18 : 45', 'gog', '96970', '34b35a232a8b4eeacb6a2e53563d785f'),
(29, 't', 21, 't', '0000-00-00', 't', '5 : 26', '19 : 28', 't', '680965', 'e358efa489f58062f10dd7316b65649e');

-- --------------------------------------------------------

--
-- Table structure for table `doctorsconsultant`
--

CREATE TABLE IF NOT EXISTS `doctorsconsultant` (
  `Doctor_Id` int(11) NOT NULL,
  `Consultant_Id` int(11) NOT NULL,
  PRIMARY KEY (`Doctor_Id`),
  KEY `Consultant_Id` (`Consultant_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctorsconsultant`
--

INSERT INTO `doctorsconsultant` (`Doctor_Id`, `Consultant_Id`) VALUES
(1, 1),
(6, 6);

-- --------------------------------------------------------

--
-- Table structure for table `doctorsinhouse`
--

CREATE TABLE IF NOT EXISTS `doctorsinhouse` (
  `Doctor_Id` int(11) NOT NULL,
  `In_House_Id` int(11) NOT NULL,
  PRIMARY KEY (`Doctor_Id`),
  KEY `Inhouse_Id` (`In_House_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctorsinhouse`
--

INSERT INTO `doctorsinhouse` (`Doctor_Id`, `In_House_Id`) VALUES
(9, 6),
(15, 7),
(20, 11),
(22, 12),
(23, 13),
(25, 14),
(27, 15),
(28, 16),
(29, 17);

-- --------------------------------------------------------

--
-- Table structure for table `doctorssurgeon`
--

CREATE TABLE IF NOT EXISTS `doctorssurgeon` (
  `Doctor_Id` int(11) NOT NULL,
  `Surgeon_Id` int(11) NOT NULL,
  PRIMARY KEY (`Doctor_Id`),
  KEY `Surgeon_Id` (`Surgeon_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctorssurgeon`
--

INSERT INTO `doctorssurgeon` (`Doctor_Id`, `Surgeon_Id`) VALUES
(12, 1);

-- --------------------------------------------------------

--
-- Table structure for table `doctorsview`
--

CREATE TABLE IF NOT EXISTS `doctorsview` (
  `Doctor_Id` int(11) DEFAULT NULL,
  `Doctor_Name` varchar(100) DEFAULT NULL,
  `Doctor_Age` int(11) DEFAULT NULL,
  `Doctor_Sex` varchar(100) DEFAULT NULL,
  `Doctor_JoinDate` date DEFAULT NULL,
  `Doctor_Qualification` varchar(100) DEFAULT NULL,
  `Doctor_Start_Time` time DEFAULT NULL,
  `Doctor_End_Time` time DEFAULT NULL,
  `Doctor_Address` varchar(100) DEFAULT NULL,
  `Doctor_Phone` varchar(100) DEFAULT NULL,
  `Doctor_Password` varchar(100) DEFAULT NULL,
  `User_Id` varchar(100) DEFAULT NULL,
  `User_Name` varchar(100) DEFAULT NULL,
  `User_Password` varchar(8) DEFAULT NULL,
  `User_Age` int(11) DEFAULT NULL,
  `User_Sex` varchar(100) DEFAULT NULL,
  `Patient_Id` int(11) DEFAULT NULL,
  `Patient_Disease` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Stand-in structure for view `doctorview`
--
CREATE TABLE IF NOT EXISTS `doctorview` (
`Doctor_Name` varchar(100)
,`Doctor_Age` int(11)
,`Doctor_Sex` varchar(100)
,`Doctor_JoinDate` date
,`Doctor_Qualification` varchar(100)
,`Doctor_Start_Time` varchar(100)
,`Doctor_End_Time` varchar(100)
,`Doctor_Address` varchar(100)
,`Doctor_Phone` varchar(100)
,`Doctor_Password` varchar(32)
,`User_Id` varchar(100)
,`User_Name` varchar(100)
,`User_Password` varchar(32)
,`User_Age` int(11)
,`User_Sex` varchar(100)
,`Patient_Id` int(11)
,`Patient_Disease` varchar(100)
,`Doctor_Id` int(11)
);
-- --------------------------------------------------------

--
-- Table structure for table `draws_salary_doctos_pc`
--

CREATE TABLE IF NOT EXISTS `draws_salary_doctos_pc` (
  `Doctor_ID` int(11) NOT NULL,
  `Professional_Charges_Id` int(11) NOT NULL,
  PRIMARY KEY (`Doctor_ID`),
  KEY `Professional_Charges_Id` (`Professional_Charges_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `draws_salary_staff_pc`
--

CREATE TABLE IF NOT EXISTS `draws_salary_staff_pc` (
  `Staff_ID` int(11) NOT NULL,
  `Professional_Charges_Id` int(11) NOT NULL,
  PRIMARY KEY (`Staff_ID`),
  KEY `Professional_Charges_Id` (`Professional_Charges_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `driver`
--

CREATE TABLE IF NOT EXISTS `driver` (
  `Driver_Id` int(11) NOT NULL,
  `Phone` varchar(100) NOT NULL,
  PRIMARY KEY (`Driver_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `driver`
--

INSERT INTO `driver` (`Driver_Id`, `Phone`) VALUES
(1, '9085670237'),
(2, '9085670543');

-- --------------------------------------------------------

--
-- Table structure for table `expenditures`
--

CREATE TABLE IF NOT EXISTS `expenditures` (
  `Expenditure_ID` int(11) NOT NULL,
  `Date` int(11) NOT NULL,
  `Amount` int(11) NOT NULL,
  PRIMARY KEY (`Expenditure_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `funds`
--

CREATE TABLE IF NOT EXISTS `funds` (
  `Income_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Company_Id` int(11) NOT NULL,
  PRIMARY KEY (`Income_ID`),
  KEY `Company_Id` (`Company_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `have`
--

CREATE TABLE IF NOT EXISTS `have` (
  `Ward_ID` int(11) NOT NULL,
  `Bed_ID` int(11) NOT NULL,
  PRIMARY KEY (`Ward_ID`),
  KEY `Bed_ID` (`Bed_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `housekeeping`
--

CREATE TABLE IF NOT EXISTS `housekeeping` (
  `Staff_Id` int(11) NOT NULL,
  `HouseKeeping_Id` int(11) NOT NULL,
  `Designated_to` int(11) NOT NULL,
  PRIMARY KEY (`HouseKeeping_Id`),
  KEY `Staff_Id` (`Staff_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `in`
--

CREATE TABLE IF NOT EXISTS `in` (
  `Operation_Theatre_Id` int(11) NOT NULL,
  `Operation_Id` int(11) NOT NULL,
  PRIMARY KEY (`Operation_Theatre_Id`,`Operation_Id`),
  KEY `Operation_Id` (`Operation_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `income`
--

CREATE TABLE IF NOT EXISTS `income` (
  `Income_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Amount` int(11) NOT NULL,
  PRIMARY KEY (`Income_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `income`
--

INSERT INTO `income` (`Income_ID`, `Amount`) VALUES
(1, 100),
(2, 200),
(3, 250),
(4, 1000);

-- --------------------------------------------------------

--
-- Table structure for table `incomebills`
--

CREATE TABLE IF NOT EXISTS `incomebills` (
  `Bill_Id` int(11) NOT NULL,
  `Income_Id` int(11) NOT NULL,
  PRIMARY KEY (`Income_Id`),
  KEY `Bill_Id` (`Bill_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `incomebills`
--

INSERT INTO `incomebills` (`Bill_Id`, `Income_Id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

-- --------------------------------------------------------

--
-- Table structure for table `infrastructure`
--

CREATE TABLE IF NOT EXISTS `infrastructure` (
  `Infra_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Supervisor_Id` int(11) NOT NULL,
  `Phone` varchar(20) NOT NULL,
  PRIMARY KEY (`Infra_Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=48 ;

--
-- Dumping data for table `infrastructure`
--

INSERT INTO `infrastructure` (`Infra_Id`, `Supervisor_Id`, `Phone`) VALUES
(1, 1, ''),
(2, 1, '9957608684'),
(3, 1, '9957608684'),
(4, 2, '9957608684'),
(5, 2, '9957608684'),
(6, 1, '07842434240'),
(7, 2, '9957608684'),
(8, 2, '9957608684'),
(9, 2, '9957608684'),
(10, 2, '9957608684'),
(11, 1, '9957608684'),
(12, 1, '9957608684'),
(13, 1, '9957608684'),
(14, 1, '908576143'),
(15, 2, '9957608684'),
(16, 2, '908576143'),
(17, 1, '9957608684'),
(18, 1, '9957608684'),
(19, 1, '9957608684'),
(20, 1, '9957608684'),
(21, 0, '9'),
(22, 0, '9'),
(23, 0, '9'),
(24, 0, '9'),
(25, 0, '9'),
(26, 0, '12'),
(27, 2, '2'),
(28, 0, '90'),
(29, 0, 'gf'),
(30, 0, 'ghf'),
(31, 0, 'gh'),
(32, 0, 'ghf'),
(33, 0, 'jh'),
(34, 0, 'fghnbfg'),
(35, 0, '9085670543'),
(36, 0, '908755'),
(37, 200, '9085670543'),
(38, 200, '1234'),
(39, 400, '90754'),
(40, 111, '111'),
(41, 0, 'fifigi'),
(42, 0, '6899'),
(43, 0, '679976'),
(44, 0, '69696'),
(45, 5885, '84845'),
(46, 0, '76576'),
(47, 1446, 'gkgkh');

-- --------------------------------------------------------

--
-- Table structure for table `infrastructureambulance`
--

CREATE TABLE IF NOT EXISTS `infrastructureambulance` (
  `Infra_Id` int(11) NOT NULL,
  `Ambulance_Id` int(11) NOT NULL,
  PRIMARY KEY (`Infra_Id`),
  KEY `Ambulance_Id` (`Ambulance_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `infrastructureambulance`
--

INSERT INTO `infrastructureambulance` (`Infra_Id`, `Ambulance_Id`) VALUES
(21, 0),
(10, 4),
(46, 67),
(47, 960607);

-- --------------------------------------------------------

--
-- Table structure for table `infrastructurelab_tests`
--

CREATE TABLE IF NOT EXISTS `infrastructurelab_tests` (
  `Infra_Id` int(11) NOT NULL,
  `Lab_Test_Id` int(11) NOT NULL,
  PRIMARY KEY (`Infra_Id`),
  KEY `Lab_Test_Id` (`Lab_Test_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `infrastructurelab_tests`
--

INSERT INTO `infrastructurelab_tests` (`Infra_Id`, `Lab_Test_Id`) VALUES
(22, 0),
(20, 1),
(38, 2),
(44, 3);

-- --------------------------------------------------------

--
-- Table structure for table `infrastructureoperation_theatre`
--

CREATE TABLE IF NOT EXISTS `infrastructureoperation_theatre` (
  `Infra_Id` int(11) NOT NULL,
  `Operation_Theatre_Id` int(11) NOT NULL,
  PRIMARY KEY (`Infra_Id`),
  KEY `Operation_Theatre_Id` (`Operation_Theatre_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `infrastructureoperation_theatre`
--

INSERT INTO `infrastructureoperation_theatre` (`Infra_Id`, `Operation_Theatre_Id`) VALUES
(23, 0),
(1, 1),
(18, 3);

-- --------------------------------------------------------

--
-- Table structure for table `infrastructurepharmacy`
--

CREATE TABLE IF NOT EXISTS `infrastructurepharmacy` (
  `Infra_Id` int(11) NOT NULL,
  `Pharmacy_Id` int(11) NOT NULL,
  PRIMARY KEY (`Infra_Id`),
  KEY `Pharmacy_Id` (`Pharmacy_Id`),
  KEY `Pharmacy_Id_2` (`Pharmacy_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `infrastructurepharmacy`
--

INSERT INTO `infrastructurepharmacy` (`Infra_Id`, `Pharmacy_Id`) VALUES
(41, 0),
(19, 1),
(43, 3);

-- --------------------------------------------------------

--
-- Table structure for table `infrastructurwards`
--

CREATE TABLE IF NOT EXISTS `infrastructurwards` (
  `Infra_Id` int(11) NOT NULL,
  `Ward_Id` int(11) NOT NULL,
  PRIMARY KEY (`Infra_Id`),
  KEY `Ward_Id` (`Ward_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `infrastructurwards`
--

INSERT INTO `infrastructurwards` (`Infra_Id`, `Ward_Id`) VALUES
(17, 5),
(29, 7),
(30, 8),
(32, 9),
(33, 10),
(34, 11),
(26, 12),
(40, 111),
(45, 53636);

-- --------------------------------------------------------

--
-- Table structure for table `inhouse`
--

CREATE TABLE IF NOT EXISTS `inhouse` (
  `Category` varchar(100) NOT NULL,
  `In_House_Id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`In_House_Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

--
-- Dumping data for table `inhouse`
--

INSERT INTO `inhouse` (`Category`, `In_House_Id`) VALUES
('Cardiologist', 1),
('Dentist', 2),
('Dentist', 4),
('Dentist', 5),
('Dentist', 6),
('Dentist', 7),
('z', 8),
('z', 9),
('z', 10),
('z', 11),
('fg', 12),
('ghj', 13),
('f', 14),
('Dentist', 15),
('Dentist', 16),
('Dentist', 17);

-- --------------------------------------------------------

--
-- Table structure for table `inpatient`
--

CREATE TABLE IF NOT EXISTS `inpatient` (
  `In_Patient_Id` int(11) NOT NULL AUTO_INCREMENT,
  `In_Date_Time` datetime NOT NULL,
  `Canteen_Bill` varchar(100) NOT NULL,
  PRIMARY KEY (`In_Patient_Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `inpatient`
--

INSERT INTO `inpatient` (`In_Patient_Id`, `In_Date_Time`, `Canteen_Bill`) VALUES
(1, '2014-04-15 06:14:08', '1208'),
(2, '2014-04-14 00:00:00', '1233');

-- --------------------------------------------------------

--
-- Table structure for table `inpatientcasualty`
--

CREATE TABLE IF NOT EXISTS `inpatientcasualty` (
  `In_Patient_Id` int(11) NOT NULL,
  `Casualty_Id` int(11) NOT NULL,
  PRIMARY KEY (`In_Patient_Id`),
  KEY `Casualty_Id` (`Casualty_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `inpatientoutpatient`
--

CREATE TABLE IF NOT EXISTS `inpatientoutpatient` (
  `In_Patient_Id` int(11) NOT NULL,
  `Out_Patient_Id` int(11) NOT NULL,
  PRIMARY KEY (`In_Patient_Id`),
  KEY `Out_Patient_Id` (`Out_Patient_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `issues_to`
--

CREATE TABLE IF NOT EXISTS `issues_to` (
  `Bill_Id` int(11) NOT NULL,
  `Patient_Id` int(11) NOT NULL,
  PRIMARY KEY (`Bill_Id`),
  KEY `Patient_Id` (`Patient_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `issues_to`
--

INSERT INTO `issues_to` (`Bill_Id`, `Patient_Id`) VALUES
(1, 3),
(2, 3),
(4, 3);

-- --------------------------------------------------------

--
-- Table structure for table `lab_tests`
--

CREATE TABLE IF NOT EXISTS `lab_tests` (
  `Lab_Test_Id` int(11) NOT NULL,
  `Lab_Test_Name` varchar(100) NOT NULL,
  PRIMARY KEY (`Lab_Test_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lab_tests`
--

INSERT INTO `lab_tests` (`Lab_Test_Id`, `Lab_Test_Name`) VALUES
(0, 'Blood Check'),
(1, 'Urine Test'),
(2, 'Brain Check'),
(3, 'g');

-- --------------------------------------------------------

--
-- Table structure for table `medicine`
--

CREATE TABLE IF NOT EXISTS `medicine` (
  `Medicine_Id` int(11) NOT NULL,
  `Medicine_Name` varchar(100) NOT NULL,
  `Medicine_Cost` int(11) NOT NULL,
  `Medicine_Expiry_Date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Medicine_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `medicine`
--

INSERT INTO `medicine` (`Medicine_Id`, `Medicine_Name`, `Medicine_Cost`, `Medicine_Expiry_Date`) VALUES
(1, 'Bcosules', 100, '12-Jan-2015'),
(2, 'Crocin', 400, '10-April-2015'),
(3, 'Soframycin', 250, '05-11-2014');

-- --------------------------------------------------------

--
-- Table structure for table `miscelleneous`
--

CREATE TABLE IF NOT EXISTS `miscelleneous` (
  `Expenditure_ID` int(11) NOT NULL,
  `Miscellaneous_Id` int(11) NOT NULL,
  `Issue` int(11) NOT NULL,
  PRIMARY KEY (`Miscellaneous_Id`),
  KEY `Expenditure_ID` (`Expenditure_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `nurses`
--

CREATE TABLE IF NOT EXISTS `nurses` (
  `Nurse_Id` int(11) NOT NULL,
  `Experience` int(11) NOT NULL,
  PRIMARY KEY (`Nurse_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nurses`
--

INSERT INTO `nurses` (`Nurse_Id`, `Experience`) VALUES
(1, 12),
(2, 10);

-- --------------------------------------------------------

--
-- Table structure for table `operations`
--

CREATE TABLE IF NOT EXISTS `operations` (
  `Operation_Id` int(11) NOT NULL,
  `Operation_Name` int(11) NOT NULL,
  PRIMARY KEY (`Operation_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `operation_theatre`
--

CREATE TABLE IF NOT EXISTS `operation_theatre` (
  `Operation_Theatre_Id` int(11) NOT NULL,
  PRIMARY KEY (`Operation_Theatre_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `operation_theatre`
--

INSERT INTO `operation_theatre` (`Operation_Theatre_Id`) VALUES
(0),
(1),
(3);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `Expenditure_ID` int(11) NOT NULL,
  `Ordered_to` int(11) NOT NULL,
  `Instruments` int(11) NOT NULL,
  `Order_Id` int(11) NOT NULL,
  PRIMARY KEY (`Order_Id`),
  KEY `Expenditure_ID` (`Expenditure_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `out patient`
--

CREATE TABLE IF NOT EXISTS `out patient` (
  `Out_Patient_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Out_Date_Time` datetime NOT NULL,
  PRIMARY KEY (`Out_Patient_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE IF NOT EXISTS `patient` (
  `Patient_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Patient_Disease` varchar(100) NOT NULL,
  PRIMARY KEY (`Patient_Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`Patient_Id`, `Patient_Disease`) VALUES
(1, 'Diahorea'),
(2, 'AIDS'),
(3, 'ghvf\n'),
(4, 'malaria'),
(17, ''),
(18, ''),
(19, ''),
(20, ''),
(21, ''),
(22, ''),
(23, ''),
(24, 'ghissupanti'),
(25, '');

-- --------------------------------------------------------

--
-- Table structure for table `patientinpatient`
--

CREATE TABLE IF NOT EXISTS `patientinpatient` (
  `Patient_Id` int(11) NOT NULL,
  `In_Patient_Id` int(11) NOT NULL,
  PRIMARY KEY (`Patient_Id`),
  KEY `In_Patient_Id` (`In_Patient_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patientinpatient`
--

INSERT INTO `patientinpatient` (`Patient_Id`, `In_Patient_Id`) VALUES
(1, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `patient_address`
--

CREATE TABLE IF NOT EXISTS `patient_address` (
  `Patient_ID` int(11) NOT NULL,
  `Patient_Street` varchar(100) NOT NULL,
  `Patient_locality` varchar(100) NOT NULL,
  `Patient_City` varchar(100) NOT NULL,
  `Patient_State` varchar(100) NOT NULL,
  `Patient_Country` varchar(100) NOT NULL,
  PRIMARY KEY (`Patient_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `patient_phone`
--

CREATE TABLE IF NOT EXISTS `patient_phone` (
  `Patient_ID` int(11) NOT NULL,
  `Home_Landline` int(11) NOT NULL,
  ` Home_Mobile` int(11) NOT NULL,
  `Personal` int(11) NOT NULL,
  PRIMARY KEY (`Patient_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `performed_by`
--

CREATE TABLE IF NOT EXISTS `performed_by` (
  `Operation_Id` int(11) NOT NULL,
  `Surgeon_Id` int(11) NOT NULL,
  PRIMARY KEY (`Operation_Id`,`Surgeon_Id`),
  KEY `Surgeon_Id` (`Surgeon_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `petty_cash`
--

CREATE TABLE IF NOT EXISTS `petty_cash` (
  `Expenditure_ID` int(11) NOT NULL,
  `Petty_Cash_Id` int(11) NOT NULL,
  `Purpose` int(11) NOT NULL,
  PRIMARY KEY (`Petty_Cash_Id`),
  KEY `Expenditure_ID` (`Expenditure_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pharmacy`
--

CREATE TABLE IF NOT EXISTS `pharmacy` (
  `Pharmacy_Id` int(11) NOT NULL,
  `Pharmacy_Name` varchar(100) NOT NULL,
  `Pharmacy_Address` varchar(100) NOT NULL,
  PRIMARY KEY (`Pharmacy_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pharmacy`
--

INSERT INTO `pharmacy` (`Pharmacy_Id`, `Pharmacy_Name`, `Pharmacy_Address`) VALUES
(0, 'fjcjfj', 'cjckgk gjcjvjv jcjgjfii'),
(1, 'Mainak Pharmacy', 'rampura'),
(2, 'Chaitanya Pharmacy', 'Apta Gali, Lapta Mohalla'),
(3, 'yahoo', 'a1 a2 a3');

-- --------------------------------------------------------

--
-- Table structure for table `pharmacymedicine`
--

CREATE TABLE IF NOT EXISTS `pharmacymedicine` (
  `Pharmacy_Id` int(11) NOT NULL,
  `Medicine_Id` int(11) NOT NULL,
  PRIMARY KEY (`Pharmacy_Id`,`Medicine_Id`),
  KEY `Medicine_Id` (`Medicine_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pharmacymedicine`
--

INSERT INTO `pharmacymedicine` (`Pharmacy_Id`, `Medicine_Id`) VALUES
(1, 1),
(1, 2),
(2, 2),
(1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `prescribed_medicine`
--

CREATE TABLE IF NOT EXISTS `prescribed_medicine` (
  `Pharmacy_Id` int(11) NOT NULL,
  `Patient_Id` int(11) NOT NULL,
  `Doctor_Id` int(11) NOT NULL,
  `Medicine_Id` int(11) NOT NULL,
  `Dosage` int(11) NOT NULL,
  `Days` int(11) NOT NULL,
  `Time1` varchar(100) DEFAULT NULL,
  `Time2` varchar(100) DEFAULT NULL,
  `TIme3` varchar(100) DEFAULT NULL,
  `Time4` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Pharmacy_Id`,`Patient_Id`,`Doctor_Id`,`Medicine_Id`),
  KEY `Patient_Id` (`Patient_Id`),
  KEY `Doctor_Id` (`Doctor_Id`),
  KEY `Medicine_Id` (`Medicine_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prescribed_medicine`
--

INSERT INTO `prescribed_medicine` (`Pharmacy_Id`, `Patient_Id`, `Doctor_Id`, `Medicine_Id`, `Dosage`, `Days`, `Time1`, `Time2`, `TIme3`, `Time4`) VALUES
(1, 3, 1, 1, 2, 6, '23 : 13', '4 : 13', 'NULL', 'NULL'),
(1, 3, 1, 2, 3, 5, 'NULL', 'NULL', 'NULL', 'NULL'),
(1, 3, 1, 3, 2, 4, '10 : 15', '14 : 20', 'NULL', 'NULL'),
(1, 24, 1, 2, 3, 4, '6 : 10', '6 : 15', '11 : 15', 'NULL');

-- --------------------------------------------------------

--
-- Table structure for table `prescribed_tests`
--

CREATE TABLE IF NOT EXISTS `prescribed_tests` (
  `Lab_Test_Id` int(11) NOT NULL,
  `Patient_ID` int(11) NOT NULL,
  `Doctor_ID` int(11) NOT NULL,
  PRIMARY KEY (`Lab_Test_Id`,`Patient_ID`,`Doctor_ID`),
  KEY `Patient_ID` (`Patient_ID`),
  KEY `Doctor_ID` (`Doctor_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prescribed_tests`
--

INSERT INTO `prescribed_tests` (`Lab_Test_Id`, `Patient_ID`, `Doctor_ID`) VALUES
(1, 3, 1),
(2, 3, 1),
(2, 24, 1),
(3, 24, 1);

-- --------------------------------------------------------

--
-- Table structure for table `professional_charges`
--

CREATE TABLE IF NOT EXISTS `professional_charges` (
  `Expenditure_ID` int(11) NOT NULL,
  `Professional_Charges_Id` int(11) NOT NULL,
  PRIMARY KEY (`Professional_Charges_Id`),
  KEY `Expenditure_ID` (`Expenditure_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE IF NOT EXISTS `staff` (
  `Staff_ID` int(11) NOT NULL,
  `Staff_Name` varchar(100) NOT NULL,
  `Staff_Age` int(11) NOT NULL,
  `Staff_Sex` varchar(100) NOT NULL,
  `Staff_JoinDate` datetime NOT NULL,
  `Staff_Qualification` varchar(100) NOT NULL,
  `Staff_Phone` varchar(100) NOT NULL,
  PRIMARY KEY (`Staff_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`Staff_ID`, `Staff_Name`, `Staff_Age`, `Staff_Sex`, `Staff_JoinDate`, `Staff_Qualification`, `Staff_Phone`) VALUES
(1, 'ramu', 11, 'male', '0000-00-00 00:00:00', 'driver', ''),
(2, 'manoj', 24, 'male', '0000-00-00 00:00:00', 'driver', ''),
(3, 'Sunita', 24, 'Female', '2013-11-04 05:19:00', 'Nurse', '9957608684'),
(4, 'Rajjo', 21, 'Female', '2014-01-01 07:06:00', 'Nurse', '9957608684');

-- --------------------------------------------------------

--
-- Table structure for table `staffdriver`
--

CREATE TABLE IF NOT EXISTS `staffdriver` (
  `Staff_Id` int(11) NOT NULL,
  `Driver_Id` int(11) NOT NULL,
  PRIMARY KEY (`Staff_Id`),
  KEY `Driver_Id` (`Driver_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staffdriver`
--

INSERT INTO `staffdriver` (`Staff_Id`, `Driver_Id`) VALUES
(1, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `staffhousekeeping`
--

CREATE TABLE IF NOT EXISTS `staffhousekeeping` (
  `Staff_Id` int(11) NOT NULL,
  `HouseKeeping_Id` int(11) NOT NULL,
  PRIMARY KEY (`Staff_Id`),
  KEY `Housekeeping_Id` (`HouseKeeping_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `staffnurse`
--

CREATE TABLE IF NOT EXISTS `staffnurse` (
  `Staff_Id` int(11) NOT NULL,
  `Nurse_Id` int(11) NOT NULL,
  PRIMARY KEY (`Staff_Id`),
  KEY `Nurse_Id` (`Nurse_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staffnurse`
--

INSERT INTO `staffnurse` (`Staff_Id`, `Nurse_Id`) VALUES
(3, 1),
(4, 2);

-- --------------------------------------------------------

--
-- Table structure for table `surgeon`
--

CREATE TABLE IF NOT EXISTS `surgeon` (
  `Surgeon_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Category` varchar(100) NOT NULL,
  PRIMARY KEY (`Surgeon_Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `surgeon`
--

INSERT INTO `surgeon` (`Surgeon_Id`, `Category`) VALUES
(1, 'ty');

-- --------------------------------------------------------

--
-- Table structure for table `undergoes_operations`
--

CREATE TABLE IF NOT EXISTS `undergoes_operations` (
  `Operation_Id` int(11) NOT NULL,
  `InPatient_ID` int(11) NOT NULL,
  `Surgeon_Id` int(11) NOT NULL,
  PRIMARY KEY (`Operation_Id`,`InPatient_ID`,`Surgeon_Id`),
  KEY `Surgeon_Id` (`Surgeon_Id`),
  KEY `InPatient_ID` (`InPatient_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `userpatient`
--

CREATE TABLE IF NOT EXISTS `userpatient` (
  `User_Id` varchar(100) NOT NULL,
  `Patient_Id` int(11) NOT NULL,
  PRIMARY KEY (`User_Id`,`Patient_Id`),
  KEY `Patient_Id` (`Patient_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userpatient`
--

INSERT INTO `userpatient` (`User_Id`, `Patient_Id`) VALUES
('a', 3),
('a', 17),
('a', 18),
('a', 19),
('a', 20),
('1', 22),
('1', 23),
('1', 24),
('a', 25);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `User_Id` varchar(100) NOT NULL,
  `User_Name` varchar(100) NOT NULL,
  `User_Password` varchar(32) NOT NULL,
  `User_Age` int(11) DEFAULT NULL,
  `User_Sex` varchar(100) DEFAULT NULL,
  `User_Address` varchar(100) DEFAULT NULL,
  `User_Phone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`User_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`User_Id`, `User_Name`, `User_Password`, `User_Age`, `User_Sex`, `User_Address`, `User_Phone`) VALUES
('1', 'ram', 'a', 21, 'male', 'abcd', '9085670543'),
('101', 'kali', 'hhgg', NULL, NULL, NULL, NULL),
('123', '123', '202cb962', NULL, NULL, NULL, NULL),
('7', '7', '8f14e45f', NULL, NULL, NULL, NULL),
('a', 'Chaitanya Agarwal', '', 21, 'Male', 'Apta Gali, Lapta Mohalla', '9085670543'),
('d', 'z', 'z', NULL, NULL, NULL, NULL),
('dad', 'dad', 'dad', NULL, NULL, NULL, NULL),
('h', 'h', 'h', NULL, NULL, NULL, NULL),
('Harshi', 'Harshil Lodhi', 'lodhi', 12, 'Male', NULL, NULL),
('i', 'i', '865c0c0b4ab0e063e5caa3387c1a8741', NULL, NULL, NULL, NULL),
('lokesh', 'lokesh pandiyar', 'asdf', NULL, NULL, NULL, NULL),
('Mainak', 'Mainak Sethi', 'xyz', 22, 'Male', NULL, NULL),
('Manisha', 'Manisha Karelia', 'MNC', 12, 'Female', NULL, NULL),
('p', 'p', '83878c91171338902e0fe0fb97a8c47a', NULL, NULL, NULL, NULL),
('Prince', 'Prince Kumar', 'pujari', 21, 'Male', NULL, NULL),
('r', 'r', '4b43b0ae', NULL, NULL, NULL, NULL),
('Rahul', 'Rahul Pradhan', 'xyz', 22, 'Male', NULL, NULL),
('Rakshita', 'Rakshita Jain', 'rinky', 23, 'Female', NULL, NULL),
('s', 'saurabh', 's', NULL, NULL, NULL, NULL),
('sethibaba', 'mainak', '827ccb0e', NULL, NULL, NULL, NULL),
('Vipin', 'Vipin Agarwal', '339', 21, 'Male', NULL, NULL),
('Vivek', 'Vivek Bhargav', 'xyz', 21, 'Male', NULL, NULL),
('z', 'z', 'z', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Stand-in structure for view `userview`
--
CREATE TABLE IF NOT EXISTS `userview` (
`User_Id` varchar(100)
,`User_Name` varchar(100)
,`User_Password` varchar(32)
,`User_Age` int(11)
,`User_Sex` varchar(100)
,`Patient_Id` int(11)
,`Patient_Disease` varchar(100)
,`Doctor_Id` int(11)
);
-- --------------------------------------------------------

--
-- Table structure for table `wards`
--

CREATE TABLE IF NOT EXISTS `wards` (
  `Ward_Id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Ward_Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=53637 ;

--
-- Dumping data for table `wards`
--

INSERT INTO `wards` (`Ward_Id`) VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11),
(12),
(13),
(14),
(111),
(112),
(53636);

-- --------------------------------------------------------

--
-- Table structure for table `works_for_deptt_doctor`
--

CREATE TABLE IF NOT EXISTS `works_for_deptt_doctor` (
  `Deptt_ID` int(11) NOT NULL,
  `Doctor_ID` int(11) NOT NULL,
  PRIMARY KEY (`Doctor_ID`),
  KEY `Deptt_ID` (`Deptt_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `works_for_deptt_nurses`
--

CREATE TABLE IF NOT EXISTS `works_for_deptt_nurses` (
  `Deptt_ID` int(11) NOT NULL,
  `Nurse_ID` int(11) NOT NULL,
  PRIMARY KEY (`Nurse_ID`),
  KEY `Deptt_ID` (`Deptt_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `works_for_deptt_nurses`
--

INSERT INTO `works_for_deptt_nurses` (`Deptt_ID`, `Nurse_ID`) VALUES
(1, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Structure for view `doctorview`
--
DROP TABLE IF EXISTS `doctorview`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `doctorview` AS select `doctors`.`Doctor_Name` AS `Doctor_Name`,`doctors`.`Doctor_Age` AS `Doctor_Age`,`doctors`.`Doctor_Sex` AS `Doctor_Sex`,`doctors`.`Doctor_JoinDate` AS `Doctor_JoinDate`,`doctors`.`Doctor_Qualification` AS `Doctor_Qualification`,`doctors`.`Doctor_Start_Time` AS `Doctor_Start_Time`,`doctors`.`Doctor_End_Time` AS `Doctor_End_Time`,`doctors`.`Doctor_Address` AS `Doctor_Address`,`doctors`.`Doctor_Phone` AS `Doctor_Phone`,`doctors`.`Doctor_Password` AS `Doctor_Password`,`userview`.`User_Id` AS `User_Id`,`userview`.`User_Name` AS `User_Name`,`userview`.`User_Password` AS `User_Password`,`userview`.`User_Age` AS `User_Age`,`userview`.`User_Sex` AS `User_Sex`,`userview`.`Patient_Id` AS `Patient_Id`,`userview`.`Patient_Disease` AS `Patient_Disease`,`userview`.`Doctor_Id` AS `Doctor_Id` from (`doctors` join `userview`) where (`userview`.`Doctor_Id` = `doctors`.`Doctor_Id`);

-- --------------------------------------------------------

--
-- Structure for view `userview`
--
DROP TABLE IF EXISTS `userview`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `userview` AS select `users`.`User_Id` AS `User_Id`,`users`.`User_Name` AS `User_Name`,`users`.`User_Password` AS `User_Password`,`users`.`User_Age` AS `User_Age`,`users`.`User_Sex` AS `User_Sex`,`patient`.`Patient_Id` AS `Patient_Id`,`patient`.`Patient_Disease` AS `Patient_Disease`,`consults`.`Doctor_Id` AS `Doctor_Id` from (((`users` join `userpatient`) join `patient`) join `consults`) where ((`users`.`User_Id` = `userpatient`.`User_Id`) and (`userpatient`.`Patient_Id` = `patient`.`Patient_Id`) and (`patient`.`Patient_Id` = `consults`.`Patient_Id`));

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admitted_to`
--
ALTER TABLE `admitted_to`
  ADD CONSTRAINT `admitted_to_ibfk_1` FOREIGN KEY (`Patient_Id`) REFERENCES `patient` (`Patient_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `admitted_to_ibfk_2` FOREIGN KEY (`Deptt_Id`) REFERENCES `department` (`Department_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `allocated_to`
--
ALTER TABLE `allocated_to`
  ADD CONSTRAINT `Allocated_To_ibfk_1` FOREIGN KEY (`Bed_Id`) REFERENCES `beds` (`Bed_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `alloted_to`
--
ALTER TABLE `alloted_to`
  ADD CONSTRAINT `alloted_to_ibfk_1` FOREIGN KEY (`Ambulance_Id`) REFERENCES `ambulance` (`Ambulance_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `alloted_to_ibfk_2` FOREIGN KEY (`Driver_Id`) REFERENCES `driver` (`Driver_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `brings`
--
ALTER TABLE `brings`
  ADD CONSTRAINT `brings_ibfk_1` FOREIGN KEY (`Ambulance_Id`) REFERENCES `ambulance` (`Ambulance_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `brings_ibfk_2` FOREIGN KEY (`User_Id`) REFERENCES `users` (`User_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `consults`
--
ALTER TABLE `consults`
  ADD CONSTRAINT `consults_ibfk_1` FOREIGN KEY (`Patient_Id`) REFERENCES `patient` (`Patient_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `consults_ibfk_2` FOREIGN KEY (`Doctor_Id`) REFERENCES `doctors` (`Doctor_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `doctorsconsultant`
--
ALTER TABLE `doctorsconsultant`
  ADD CONSTRAINT `doctorsconsultant_ibfk_1` FOREIGN KEY (`Doctor_Id`) REFERENCES `doctors` (`Doctor_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `doctorsconsultant_ibfk_2` FOREIGN KEY (`Consultant_Id`) REFERENCES `consultant` (`Consultant_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `doctorsinhouse`
--
ALTER TABLE `doctorsinhouse`
  ADD CONSTRAINT `doctorsinhouse_ibfk_1` FOREIGN KEY (`Doctor_Id`) REFERENCES `doctors` (`Doctor_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `doctorsinhouse_ibfk_2` FOREIGN KEY (`In_House_Id`) REFERENCES `inhouse` (`In_House_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `doctorssurgeon`
--
ALTER TABLE `doctorssurgeon`
  ADD CONSTRAINT `doctorssurgeon_ibfk_1` FOREIGN KEY (`Doctor_Id`) REFERENCES `doctors` (`Doctor_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `doctorssurgeon_ibfk_2` FOREIGN KEY (`Surgeon_Id`) REFERENCES `surgeon` (`Surgeon_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `draws_salary_doctos_pc`
--
ALTER TABLE `draws_salary_doctos_pc`
  ADD CONSTRAINT `draws_salary_doctos_pc_ibfk_1` FOREIGN KEY (`Doctor_ID`) REFERENCES `doctors` (`Doctor_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `draws_salary_doctos_pc_ibfk_2` FOREIGN KEY (`Professional_Charges_Id`) REFERENCES `professional_charges` (`Professional_Charges_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `draws_salary_staff_pc`
--
ALTER TABLE `draws_salary_staff_pc`
  ADD CONSTRAINT `draws_salary_staff_pc_ibfk_1` FOREIGN KEY (`Staff_ID`) REFERENCES `staff` (`Staff_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `draws_salary_staff_pc_ibfk_2` FOREIGN KEY (`Professional_Charges_Id`) REFERENCES `professional_charges` (`Professional_Charges_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `funds`
--
ALTER TABLE `funds`
  ADD CONSTRAINT `Funds_ibfk_1` FOREIGN KEY (`Income_ID`) REFERENCES `income` (`Income_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Funds_ibfk_2` FOREIGN KEY (`Company_Id`) REFERENCES `company` (`Company_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `have`
--
ALTER TABLE `have`
  ADD CONSTRAINT `Have_ibfk_1` FOREIGN KEY (`Ward_ID`) REFERENCES `wards` (`Ward_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Have_ibfk_2` FOREIGN KEY (`Bed_ID`) REFERENCES `beds` (`Bed_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `in`
--
ALTER TABLE `in`
  ADD CONSTRAINT `in_ibfk_1` FOREIGN KEY (`Operation_Theatre_Id`) REFERENCES `operation_theatre` (`Operation_Theatre_Id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `in_ibfk_2` FOREIGN KEY (`Operation_Id`) REFERENCES `operations` (`Operation_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `incomebills`
--
ALTER TABLE `incomebills`
  ADD CONSTRAINT `incomebills_ibfk_1` FOREIGN KEY (`Bill_Id`) REFERENCES `bills` (`Bill_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `incomebills_ibfk_2` FOREIGN KEY (`Income_Id`) REFERENCES `income` (`Income_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `infrastructureambulance`
--
ALTER TABLE `infrastructureambulance`
  ADD CONSTRAINT `infrastructureambulance_ibfk_1` FOREIGN KEY (`Infra_Id`) REFERENCES `infrastructure` (`Infra_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `infrastructureambulance_ibfk_2` FOREIGN KEY (`Ambulance_Id`) REFERENCES `ambulance` (`Ambulance_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `infrastructurelab_tests`
--
ALTER TABLE `infrastructurelab_tests`
  ADD CONSTRAINT `infrastructurelab_tests_ibfk_1` FOREIGN KEY (`Infra_Id`) REFERENCES `infrastructure` (`Infra_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `infrastructurelab_tests_ibfk_2` FOREIGN KEY (`Lab_Test_Id`) REFERENCES `lab_tests` (`Lab_Test_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `infrastructureoperation_theatre`
--
ALTER TABLE `infrastructureoperation_theatre`
  ADD CONSTRAINT `infrastructureoperation_theatre_ibfk_1` FOREIGN KEY (`Infra_Id`) REFERENCES `infrastructure` (`Infra_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `infrastructureoperation_theatre_ibfk_2` FOREIGN KEY (`Operation_Theatre_Id`) REFERENCES `operation_theatre` (`Operation_Theatre_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `infrastructurepharmacy`
--
ALTER TABLE `infrastructurepharmacy`
  ADD CONSTRAINT `infrastructurepharmacy_ibfk_1` FOREIGN KEY (`Infra_Id`) REFERENCES `infrastructure` (`Infra_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `infrastructurepharmacy_ibfk_2` FOREIGN KEY (`Pharmacy_Id`) REFERENCES `pharmacy` (`Pharmacy_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `infrastructurwards`
--
ALTER TABLE `infrastructurwards`
  ADD CONSTRAINT `infrastructurwards_ibfk_1` FOREIGN KEY (`Infra_Id`) REFERENCES `infrastructure` (`Infra_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `infrastructurwards_ibfk_2` FOREIGN KEY (`Ward_Id`) REFERENCES `wards` (`Ward_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `inpatientcasualty`
--
ALTER TABLE `inpatientcasualty`
  ADD CONSTRAINT `inpatientcasualty_ibfk_1` FOREIGN KEY (`In_Patient_Id`) REFERENCES `inpatient` (`In_Patient_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `inpatientcasualty_ibfk_2` FOREIGN KEY (`Casualty_Id`) REFERENCES `casualty` (`Casualty_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `inpatientoutpatient`
--
ALTER TABLE `inpatientoutpatient`
  ADD CONSTRAINT `inpatientoutpatient_ibfk_1` FOREIGN KEY (`In_Patient_Id`) REFERENCES `inpatient` (`In_Patient_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `inpatientoutpatient_ibfk_2` FOREIGN KEY (`Out_Patient_Id`) REFERENCES `out patient` (`Out_Patient_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `issues_to`
--
ALTER TABLE `issues_to`
  ADD CONSTRAINT `Issues_To_ibfk_1` FOREIGN KEY (`Bill_Id`) REFERENCES `bills` (`Bill_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Issues_To_ibfk_2` FOREIGN KEY (`Patient_Id`) REFERENCES `patient` (`Patient_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `miscelleneous`
--
ALTER TABLE `miscelleneous`
  ADD CONSTRAINT `miscelleneous_ibfk_1` FOREIGN KEY (`Expenditure_ID`) REFERENCES `expenditures` (`Expenditure_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`Expenditure_ID`) REFERENCES `expenditures` (`Expenditure_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `patientinpatient`
--
ALTER TABLE `patientinpatient`
  ADD CONSTRAINT `patientinpatient_ibfk_1` FOREIGN KEY (`Patient_Id`) REFERENCES `patient` (`Patient_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `patientinpatient_ibfk_2` FOREIGN KEY (`In_Patient_Id`) REFERENCES `inpatient` (`In_Patient_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `patient_address`
--
ALTER TABLE `patient_address`
  ADD CONSTRAINT `Patient_Address_ibfk_1` FOREIGN KEY (`Patient_ID`) REFERENCES `patient` (`Patient_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `patient_phone`
--
ALTER TABLE `patient_phone`
  ADD CONSTRAINT `Patient_Phone_ibfk_1` FOREIGN KEY (`Patient_ID`) REFERENCES `patient` (`Patient_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `performed_by`
--
ALTER TABLE `performed_by`
  ADD CONSTRAINT `performed_by_ibfk_1` FOREIGN KEY (`Operation_Id`) REFERENCES `operation_theatre` (`Operation_Theatre_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `performed_by_ibfk_2` FOREIGN KEY (`Surgeon_Id`) REFERENCES `surgeon` (`Surgeon_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `petty_cash`
--
ALTER TABLE `petty_cash`
  ADD CONSTRAINT `petty_cash_ibfk_1` FOREIGN KEY (`Expenditure_ID`) REFERENCES `expenditures` (`Expenditure_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pharmacymedicine`
--
ALTER TABLE `pharmacymedicine`
  ADD CONSTRAINT `pharmacymedicine_ibfk_1` FOREIGN KEY (`Pharmacy_Id`) REFERENCES `pharmacy` (`Pharmacy_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pharmacymedicine_ibfk_2` FOREIGN KEY (`Medicine_Id`) REFERENCES `medicine` (`Medicine_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `prescribed_tests`
--
ALTER TABLE `prescribed_tests`
  ADD CONSTRAINT `prescribed_tests_ibfk_1` FOREIGN KEY (`Lab_Test_Id`) REFERENCES `lab_tests` (`Lab_Test_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `prescribed_tests_ibfk_2` FOREIGN KEY (`Patient_ID`) REFERENCES `patient` (`Patient_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `prescribed_tests_ibfk_3` FOREIGN KEY (`Doctor_ID`) REFERENCES `doctors` (`Doctor_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `professional_charges`
--
ALTER TABLE `professional_charges`
  ADD CONSTRAINT `professional_charges_ibfk_1` FOREIGN KEY (`Expenditure_ID`) REFERENCES `expenditures` (`Expenditure_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `staffdriver`
--
ALTER TABLE `staffdriver`
  ADD CONSTRAINT `staffdriver_ibfk_1` FOREIGN KEY (`Staff_Id`) REFERENCES `staff` (`Staff_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `staffdriver_ibfk_2` FOREIGN KEY (`Driver_Id`) REFERENCES `driver` (`Driver_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `staffhousekeeping`
--
ALTER TABLE `staffhousekeeping`
  ADD CONSTRAINT `staffhousekeeping_ibfk_1` FOREIGN KEY (`Staff_Id`) REFERENCES `staff` (`Staff_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `staffhousekeeping_ibfk_2` FOREIGN KEY (`HouseKeeping_Id`) REFERENCES `housekeeping` (`HouseKeeping_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `staffnurse`
--
ALTER TABLE `staffnurse`
  ADD CONSTRAINT `staffnurse_ibfk_1` FOREIGN KEY (`Staff_Id`) REFERENCES `staff` (`Staff_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `staffnurse_ibfk_2` FOREIGN KEY (`Nurse_Id`) REFERENCES `nurses` (`Nurse_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `undergoes_operations`
--
ALTER TABLE `undergoes_operations`
  ADD CONSTRAINT `undergoes_operations_ibfk_1` FOREIGN KEY (`Operation_Id`) REFERENCES `operations` (`Operation_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `undergoes_operations_ibfk_3` FOREIGN KEY (`Surgeon_Id`) REFERENCES `surgeon` (`Surgeon_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `undergoes_operations_ibfk_4` FOREIGN KEY (`InPatient_ID`) REFERENCES `inpatient` (`In_Patient_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `userpatient`
--
ALTER TABLE `userpatient`
  ADD CONSTRAINT `userpatient_ibfk_1` FOREIGN KEY (`Patient_Id`) REFERENCES `patient` (`Patient_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `userpatient_ibfk_2` FOREIGN KEY (`User_Id`) REFERENCES `users` (`User_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `works_for_deptt_doctor`
--
ALTER TABLE `works_for_deptt_doctor`
  ADD CONSTRAINT `works_for_deptt_doctor_ibfk_1` FOREIGN KEY (`Doctor_ID`) REFERENCES `doctors` (`Doctor_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `works_for_deptt_doctor_ibfk_2` FOREIGN KEY (`Deptt_ID`) REFERENCES `department` (`Department_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `works_for_deptt_nurses`
--
ALTER TABLE `works_for_deptt_nurses`
  ADD CONSTRAINT `works_for_deptt_nurses_ibfk_2` FOREIGN KEY (`Nurse_ID`) REFERENCES `nurses` (`Nurse_Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `works_for_deptt_nurses_ibfk_3` FOREIGN KEY (`Deptt_ID`) REFERENCES `department` (`Department_Id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
