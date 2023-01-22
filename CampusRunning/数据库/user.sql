/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.12 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `user` (
	`id` int (11),
	`username` varchar (60),
	`password` varchar (60)
); 
insert into `user` (`id`, `username`, `password`) values('2','admin','444');
insert into `user` (`id`, `username`, `password`) values('3','root','1234');
insert into `user` (`id`, `username`, `password`) values('10','tiam','111');
insert into `user` (`id`, `username`, `password`) values('11','bloom','222');
