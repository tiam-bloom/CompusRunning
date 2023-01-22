/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.12 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `userinfo` (
	`id` int (11),
	`name` varchar (60),
	`email` varchar (90),
	`tel` varchar (60),
	`address` varchar (60),
	`balance` int (11)
); 
insert into `userinfo` (`id`, `name`, `email`, `tel`, `address`, `balance`) values('2','清分徐来','123@qq.com','414483','武软418','3940');
insert into `userinfo` (`id`, `name`, `email`, `tel`, `address`, `balance`) values('3','国庆快乐','321@net.com','10010','武软233','500');
insert into `userinfo` (`id`, `name`, `email`, `tel`, `address`, `balance`) values('10','凌晨','3036@qq.com','66176','666','2396');
insert into `userinfo` (`id`, `name`, `email`, `tel`, `address`, `balance`) values('11','两点半','3036@qq.com','99139','99','1');
