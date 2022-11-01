-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 27, 2020 at 08:31 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ptsbk`
--

-- --------------------------------------------------------

--
-- Table structure for table `akun`
--

CREATE TABLE `akun` (
  `KD_User` int(11) NOT NULL,
  `Username` varchar(10) NOT NULL,
  `Password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `akun`
--

INSERT INTO `akun` (`KD_User`, `Username`, `Password`) VALUES
(1, 'admin', 'sbkkusen');

-- --------------------------------------------------------

--
-- Table structure for table `desainkusen`
--

CREATE TABLE `desainkusen` (
  `KD_DesainKusen` int(11) NOT NULL,
  `Nama_Kusen` varchar(30) NOT NULL,
  `Ukuran` varchar(20) NOT NULL,
  `Bahan` varchar(20) NOT NULL,
  `Harga` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `desainkusen`
--

INSERT INTO `desainkusen` (`KD_DesainKusen`, `Nama_Kusen`, `Ukuran`, `Bahan`, `Harga`) VALUES
(6, 'Pintu', '200*300', 'Mahoni', 500000);

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `KD_Pelanggan` int(11) NOT NULL,
  `Nama_Pelanggan` varchar(30) NOT NULL,
  `No_Telp` varchar(30) NOT NULL,
  `Alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`KD_Pelanggan`, `Nama_Pelanggan`, `No_Telp`, `Alamat`) VALUES
(7, 'DoyenA', '089655901622', 'Antapani'),
(8, 'Tias', '081222680755', 'Cirebon');

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE `pesanan` (
  `KD_Pemesanan` int(11) NOT NULL,
  `KD_Pelanggan` int(11) NOT NULL,
  `KD_DesainKusen` int(11) NOT NULL,
  `Jumlah_Kusen` int(9) NOT NULL,
  `Harga_Total` int(9) NOT NULL,
  `Tgl_Pemesanan` date NOT NULL,
  `Tgl_Pengambilan` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pesanan`
--

INSERT INTO `pesanan` (`KD_Pemesanan`, `KD_Pelanggan`, `KD_DesainKusen`, `Jumlah_Kusen`, `Harga_Total`, `Tgl_Pemesanan`, `Tgl_Pengambilan`) VALUES
(9, 7, 6, 5, 2500000, '2020-07-27', '2020-07-31'),
(10, 8, 6, 6, 3000000, '2020-07-28', '2020-07-08');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `akun`
--
ALTER TABLE `akun`
  ADD PRIMARY KEY (`KD_User`);

--
-- Indexes for table `desainkusen`
--
ALTER TABLE `desainkusen`
  ADD PRIMARY KEY (`KD_DesainKusen`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`KD_Pelanggan`);

--
-- Indexes for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`KD_Pemesanan`),
  ADD UNIQUE KEY `KD_Pelanggan` (`KD_Pelanggan`,`KD_DesainKusen`),
  ADD KEY `pesanan_ibfk_2` (`KD_DesainKusen`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `akun`
--
ALTER TABLE `akun`
  MODIFY `KD_User` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `desainkusen`
--
ALTER TABLE `desainkusen`
  MODIFY `KD_DesainKusen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `pelanggan`
--
ALTER TABLE `pelanggan`
  MODIFY `KD_Pelanggan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `pesanan`
--
ALTER TABLE `pesanan`
  MODIFY `KD_Pemesanan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD CONSTRAINT `pesanan_ibfk_1` FOREIGN KEY (`KD_Pelanggan`) REFERENCES `pelanggan` (`KD_Pelanggan`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pesanan_ibfk_2` FOREIGN KEY (`KD_DesainKusen`) REFERENCES `desainkusen` (`KD_DesainKusen`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
