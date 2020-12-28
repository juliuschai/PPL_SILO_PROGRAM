-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 28, 2020 at 02:45 PM
-- Server version: 10.5.4-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `silo`
--

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `id` int(11) NOT NULL,
  `barcode` varchar(255) NOT NULL,
  `judul` varchar(255) NOT NULL,
  `deskripsi` varchar(255) NOT NULL,
  `pemanufaktur` varchar(255) NOT NULL,
  `stock` int(11) NOT NULL,
  `url` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id`, `barcode`, `judul`, `deskripsi`, `pemanufaktur`, `stock`, `url`) VALUES
(1, '313', 'Thermogun Devices', 'Mengukur suhu tubuh', 'WHO', 1, 'https://www.tokopedia.com/rahmatoserba/aicare-thermometer-infrared-thermogun-non-contact'),
(2, '314', 'Masker KN95', 'Masker N95 Icare 5ply Asli Antivirus Facemask', 'WHO KW', 1, 'https://www.tokopedia.com/rahmatoserba/masker-kn95-masker-n95-icare-5ply-asli-antivirus-facemask');

-- --------------------------------------------------------

--
-- Table structure for table `surat_jalans`
--

CREATE TABLE `surat_jalans` (
  `nomorInvoice` varchar(255) NOT NULL,
  `nomorSj` varchar(255) NOT NULL,
  `namaCustomer` varchar(255) NOT NULL,
  `emailCustomer` varchar(255) NOT NULL,
  `tanggalOrder` datetime NOT NULL,
  `tanggalSelesai` datetime DEFAULT NULL,
  `status` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `surat_jalans`
--

INSERT INTO `surat_jalans` (`nomorInvoice`, `nomorSj`, `namaCustomer`, `emailCustomer`, `tanggalOrder`, `tanggalSelesai`, `status`) VALUES
('INV/2020/12/26/01', 'INV/2020/12/26/01', 'wasd1', 'wasd1@wasd1.com', '2020-12-27 00:00:00', '2020-12-28 00:00:00', 'completed'),
('INV/2020/12/26/02', 'INV/2020/12/26/02', 'wasd1', 'wasd1@wasd1.com', '2020-12-27 00:00:00', NULL, 'new'),
('INV/2020/12/28/01', 'INV/2020/12/28/01', 'wasd1', 'wasd1@wasd1.com', '2020-12-28 00:00:00', '2020-12-28 00:00:00', 'completed');

-- --------------------------------------------------------

--
-- Table structure for table `surat_jalan_items`
--

CREATE TABLE `surat_jalan_items` (
  `nomorSj` varchar(255) NOT NULL,
  `itemId` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `surat_jalan_items`
--

INSERT INTO `surat_jalan_items` (`nomorSj`, `itemId`, `jumlah`) VALUES
('INV/2020/12/26/01', 2, 2),
('INV/2020/12/26/01', 1, 1),
('INV/2020/12/28/01', 1, 1),
('INV/2020/12/28/01', 2, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `surat_jalans`
--
ALTER TABLE `surat_jalans`
  ADD PRIMARY KEY (`nomorSj`);

--
-- Indexes for table `surat_jalan_items`
--
ALTER TABLE `surat_jalan_items`
  ADD KEY `surat_jalan_FK` (`nomorSj`),
  ADD KEY `item_FK` (`itemId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `surat_jalan_items`
--
ALTER TABLE `surat_jalan_items`
  ADD CONSTRAINT `item_FK` FOREIGN KEY (`itemId`) REFERENCES `items` (`id`),
  ADD CONSTRAINT `surat_jalan_FK` FOREIGN KEY (`nomorSj`) REFERENCES `surat_jalans` (`nomorSj`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
