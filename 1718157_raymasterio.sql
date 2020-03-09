# Host: localhost  (Version 5.5.5-10.1.21-MariaDB)
# Date: 2020-03-06 14:28:19
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "1718157_barang"
#

DROP TABLE IF EXISTS `1718157_barang`;
CREATE TABLE `1718157_barang` (
  `1718157_KdBarang` char(6) NOT NULL DEFAULT '',
  `1718157_NmBarang` varchar(35) DEFAULT NULL,
  `1718157_Satuan` varchar(10) DEFAULT NULL,
  `1718157_Stok` int(3) DEFAULT NULL,
  `1718157_HrgBarang` double(8,0) DEFAULT NULL,
  PRIMARY KEY (`1718157_KdBarang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

#
# Data for table "1718157_barang"
#

INSERT INTO `1718157_barang` VALUES ('BRG001','Barang','pcs',10,20000),('BRG002','barangan','pcs',20,21111);

#
# Structure for table "1718157_detil_pesan"
#

DROP TABLE IF EXISTS `1718157_detil_pesan`;
CREATE TABLE `1718157_detil_pesan` (
  `1718157_NoSP` char(6) NOT NULL DEFAULT '',
  `1718157_KdBarang` char(6) NOT NULL,
  `1718157_JmlJual` int(3) DEFAULT NULL,
  `1718157_HrgJual` double(9,0) DEFAULT NULL,
  PRIMARY KEY (`1718157_KdBarang`,`1718157_NoSP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "1718157_detil_pesan"
#

INSERT INTO `1718157_detil_pesan` VALUES ('SP0001','BRG001',3,1333),('SP0002','BRG001',2,3333),('SP0003','BRG001',4,3333),('SP0004','BRG001',3,1333),('SP0001','BRG002',3,23333),('SP0005','BRG002',4,1333);

#
# Structure for table "1718157_nota"
#

DROP TABLE IF EXISTS `1718157_nota`;
CREATE TABLE `1718157_nota` (
  `1718157_NoNota` char(6) NOT NULL DEFAULT '',
  `1718157_NoSP` char(6) NOT NULL DEFAULT '',
  `1718157_TglNota` date DEFAULT NULL,
  `1718157_JmlHarga` double(9,0) DEFAULT NULL,
  PRIMARY KEY (`1718157_NoNota`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "1718157_nota"
#

INSERT INTO `1718157_nota` VALUES ('NT0001','SP0001','2020-03-05',73998),('NT0002','SP0002','2020-03-05',6666),('NT0003','SP0003','2020-03-05',13332),('NT0004','SP0004','2020-03-05',3999);

#
# Structure for table "1718157_pelanggan"
#

DROP TABLE IF EXISTS `1718157_pelanggan`;
CREATE TABLE `1718157_pelanggan` (
  `1718157_IdPelanggan` char(4) NOT NULL DEFAULT '',
  `1718157_NmPelanggan` varchar(35) DEFAULT NULL,
  `1718157_Alamat` varchar(100) DEFAULT NULL,
  `1718157_NoTelp` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`1718157_IdPelanggan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "1718157_pelanggan"
#

INSERT INTO `1718157_pelanggan` VALUES ('P001','Oji','Jalanan','08888'),('P002','Ojiee','Ciasukl','0888'),('P003','Remek','Jalanan','08888'),('P004','Jubed','jalan','03214154');

#
# Structure for table "1718157_sp"
#

DROP TABLE IF EXISTS `1718157_sp`;
CREATE TABLE `1718157_sp` (
  `1718157_NoSP` char(6) NOT NULL DEFAULT '',
  `1718157_IdPelanggan` char(4) DEFAULT NULL,
  `1718157_TglSP` date DEFAULT NULL,
  PRIMARY KEY (`1718157_NoSP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "1718157_sp"
#

INSERT INTO `1718157_sp` VALUES ('SP0001','P001','2020-03-05'),('SP0002','P001','2020-03-05'),('SP0003','P002','2020-03-05'),('SP0004','P001','2020-03-05'),('SP0005','P001','2020-03-05');

#
# Structure for table "d_detil"
#

DROP TABLE IF EXISTS `d_detil`;
CREATE TABLE `d_detil` (
  `KdBarang` char(6) NOT NULL DEFAULT '',
  `NmBarang` varchar(35) DEFAULT NULL,
  `Satuan` varchar(10) DEFAULT NULL,
  `JumlahP` varchar(255) DEFAULT NULL,
  `HrgPesan` varchar(255) DEFAULT NULL,
  `JumlahH` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "d_detil"
#


#
# Structure for table "dpelanggan"
#

DROP TABLE IF EXISTS `dpelanggan`;
CREATE TABLE `dpelanggan` (
  `1718157_IdPelanggan` varchar(4) NOT NULL DEFAULT '',
  `1718157_NmPelanggan` varchar(35) DEFAULT NULL,
  `1718157_Alamat` varchar(100) DEFAULT NULL,
  `1718157_NoTelp` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`1718157_IdPelanggan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "dpelanggan"
#

INSERT INTO `dpelanggan` VALUES ('P001','Oji','Jalanan','08888');

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(16) NOT NULL DEFAULT '',
  `password` varchar(255) DEFAULT NULL,
  `hakakses` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "user"
#

INSERT INTO `user` VALUES ('ray','132','admin'),('ray1','123','user');
