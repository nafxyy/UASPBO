-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 10, 2023 at 04:47 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbdatapbo`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbakunpbo`
--

CREATE TABLE `tbakunpbo` (
  `id_akun` int(11) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbakunpbo`
--

INSERT INTO `tbakunpbo` (`id_akun`, `nama`, `password`, `username`, `email`) VALUES
(2, 'napsi', '12345', 'nap', 'napium@gmail.com'),
(3, 'admin', '12345', 'Admin', 'admingg@gmail.com'),
(4, 'nopal', '12345', 'nopal', 'noplal@gmail.com'),
(5, 'akunku', '212', 'akunsaya', 'we@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `tbkulkas`
--

CREATE TABLE `tbkulkas` (
  `id_brand` int(11) NOT NULL,
  `brand` varchar(255) NOT NULL,
  `harga` int(11) NOT NULL,
  `stok` int(11) NOT NULL,
  `tipe` varchar(255) NOT NULL,
  `berat` int(11) NOT NULL,
  `deskripsi` varchar(255) NOT NULL,
  `gambar` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbkulkas`
--

INSERT INTO `tbkulkas` (`id_brand`, `brand`, `harga`, `stok`, `tipe`, `berat`, `deskripsi`, `gambar`) VALUES
(8, 'Kulkas Kamu', 500000, 2, '3 Pintu', 5, 'Kulkas Andalan Kamu', 'Kulkas Kamu.jpg'),
(9, 'Kulkas Tobasi', 500000, 2, '2 Pintu', 3, 'Kulkas Tobasi Andalan Rakyat', 'Kulkas Tobasi.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbakunpbo`
--
ALTER TABLE `tbakunpbo`
  ADD PRIMARY KEY (`id_akun`);

--
-- Indexes for table `tbkulkas`
--
ALTER TABLE `tbkulkas`
  ADD PRIMARY KEY (`id_brand`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbakunpbo`
--
ALTER TABLE `tbakunpbo`
  MODIFY `id_akun` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tbkulkas`
--
ALTER TABLE `tbkulkas`
  MODIFY `id_brand` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
