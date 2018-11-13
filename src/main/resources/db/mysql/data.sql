-- --------------------------------------------------------
-- РҐРѕСЃС‚:                         localhost
-- Р’РµСЂСЃРёСЏ СЃРµСЂРІРµСЂР°:               10.3.8-MariaDB - mariadb.org binary distribution
-- РћРїРµСЂР°С†РёРѕРЅРЅР°СЏ СЃРёСЃС‚РµРјР°:         Win64
-- HeidiSQL Р’РµСЂСЃРёСЏ:              9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Р”Р°РјРї СЃС‚СЂСѓРєС‚СѓСЂС‹ Р±Р°Р·С‹ РґР°РЅРЅС‹С… racetracer
CREATE DATABASE IF NOT EXISTS `racetracer` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `racetracer`;

-- Р”Р°РјРї СЃС‚СЂСѓРєС‚СѓСЂС‹ РґР»СЏ С‚Р°Р±Р»РёС†Р° racetracer.activitylog
CREATE TABLE IF NOT EXISTS `activitylog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `asker` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `login` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `level` enum('Error','Guest','User','Admin') COLLATE utf8mb4_bin NOT NULL DEFAULT 'Error',
  `json_in` longtext COLLATE utf8mb4_bin NOT NULL,
  `json_out` longtext COLLATE utf8mb4_bin NOT NULL,
  `caller` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT 'Р С”Р В°Р С”Р С•Р в„– Р С”Р В»Р В°РЎРѓРЎРѓ Р Р†РЎвЂ№Р В·РЎвЂ№Р Р†Р В°Р В» main log',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='РЎРѓРЎР‹Р Т‘Р В° РЎРѓР С•РЎвЂ¦РЎР‚Р В°Р Р…РЎРЏР ВµР С Р В»Р С•Р С– Р С•Р В±Р СР ВµР Р…Р В° Р Т‘Р В°Р Р…Р Р…РЎвЂ№Р СР С‘ Р С”Р В»Р С‘Р ВµР Р…РЎвЂљР С•Р Р† РЎРѓ РЎРѓР ВµРЎР‚Р Р†Р ВµРЎР‚Р С•Р С.';

-- Р”Р°РјРї РґР°РЅРЅС‹С… С‚Р°Р±Р»РёС†С‹ racetracer.activitylog: ~0 rows (РїСЂРёР±Р»РёР·РёС‚РµР»СЊРЅРѕ)
/*!40000 ALTER TABLE `activitylog` DISABLE KEYS */;
INSERT INTO `activitylog` (`id`, `asker`, `timestamp`, `login`, `level`, `json_in`, `json_out`, `caller`) VALUES
	(1, 'AskForLogin', '2018-11-14 00:55:08', 'nobody', 'Error', '{"password":"1111111","level":"Guest","asker":"AskForLogin","login":"+79272006026","key":"galkovvladimirandreevich"}', '{"level":"Admin","asker":"AskForLogin","login":"TRUE","key":"galkovvladimirandreevich"}', ''),
	(2, 'AskForLogin', '2018-11-14 01:16:45', 'nobody', 'Error', '{"password":"1111111","level":"Guest","asker":"AskForLogin","login":"+79272006026","key":"galkovvladimirandreevich"}', '{"level":"Admin","asker":"AskForLogin","login":"TRUE","key":"galkovvladimirandreevich"}', ''),
	(3, 'AskForLogin', '2018-11-14 01:19:14', 'nobody', 'Error', '{"password":"111111","level":"Guest","asker":"AskForLogin","login":"+79272006026","key":"galkovvladimirandreevich"}', '{"level":"Guest","asker":"AskForLogin","login":"FALSE","key":"galkovvladimirandreevich"}', ''),
	(4, 'AskForLogin', '2018-11-14 01:19:28', 'nobody', 'Error', '{"password":"1111111","level":"Guest","asker":"AskForLogin","login":"+79272006026","key":"galkovvladimirandreevich"}', '{"level":"Admin","asker":"AskForLogin","login":"TRUE","key":"galkovvladimirandreevich"}', ''),
	(5, 'AskUserTable', '2018-11-14 01:19:43', '+79272006026', 'Admin', '{"exec_login":"+79272006026","asker":"AskUserTable","exec_level":"Admin","key":"galkovvladimirandreevich"}', '{"usersArr":[{"altitude":84,"password":"4454444","level":"User","latitude":53.20004181470722,"Id":97,"login":"+22222222222","longitude":50.144251165911555},{"altitude":36,"password":"1111111","level":"Admin","latitude":53.200218295678496,"Id":100,"login":"+79272006026","longitude":50.10567379184067},{"altitude":109,"password":"eddwerfd","level":"Guest","latitude":53.20044871419668,"Id":109,"login":"+88444554455","longitude":50.10598383843899}],"asker":"AskUserTable","key":"galkovvladimirandreevich"}', ''),
	(6, 'AskUserTable', '2018-11-14 01:19:43', '+79272006026', 'Admin', '{"exec_login":"+79272006026","asker":"AskUserTable","exec_level":"Admin","key":"galkovvladimirandreevich"}', '{"usersArr":[{"altitude":84,"password":"4454444","level":"User","latitude":53.20004181470722,"Id":97,"login":"+22222222222","longitude":50.144251165911555},{"altitude":36,"password":"1111111","level":"Admin","latitude":53.200218295678496,"Id":100,"login":"+79272006026","longitude":50.10567379184067},{"altitude":109,"password":"eddwerfd","level":"Guest","latitude":53.20044871419668,"Id":109,"login":"+88444554455","longitude":50.10598383843899}],"asker":"AskUserTable","key":"galkovvladimirandreevich"}', ''),
	(7, 'AskForLogin', '2018-11-14 01:23:14', 'nobody', 'Error', '{"password":"1111111","level":"Guest","asker":"AskForLogin","login":"+79272006026","key":"galkovvladimirandreevich"}', '{"level":"Admin","asker":"AskForLogin","login":"TRUE","key":"galkovvladimirandreevich"}', ''),
	(8, 'AskResultsImgTable', '2018-11-14 01:25:05', '+79272006026', 'Admin', '{"IMGType":"ALL","exec_login":"+79272006026","asker":"AskResultsImgTable","exec_level":"Admin","key":"galkovvladimirandreevich"}', '{"asker":"AskResultsImgTable","key":"galkovvladimirandreevich"}', ''),
	(9, 'AskForMainLog', '2018-11-14 01:25:14', '+79272006026', 'Admin', '{"caller":"ru.galkov.racenfctracer.ActivityAdminManager@c0fa2ec","exec_login":"+79272006026","asker":"AskForMainLog","exec_level":"Admin","key":"galkovvladimirandreevich"}', '{"asker":"AskForMainLog","rows":[{"date":"Mon Nov 12 01:55:50 SAMT 2018","altitude":137,"start_id":37,"latitude":53.200658303685486,"race_id":2,"Id":0,"login":"+22222222222","mark_label":"000077","mark":3,"longitude":50.10585484094918},{"date":"Mon Nov 12 01:56:05 SAMT 2018","altitude":136,"start_id":37,"latitude":53.20073654875159,"race_id":2,"Id":1,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.105907898396254},{"date":"Mon Nov 12 11:29:38 SAMT 2018","altitude":0,"start_id":1,"latitude":53.200274161063135,"race_id":1,"Id":2,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.14424856752157}],"key":"galkovvladimirandreevich"}', ''),
	(10, 'AskForMainLog', '2018-11-14 01:26:14', '+79272006026', 'Admin', '{"caller":"ru.galkov.racenfctracer.ActivityAdminManager@c0fa2ec","exec_login":"+79272006026","asker":"AskForMainLog","exec_level":"Admin","key":"galkovvladimirandreevich"}', '{"asker":"AskForMainLog","rows":[{"date":"Mon Nov 12 01:55:50 SAMT 2018","altitude":137,"start_id":37,"latitude":53.200658303685486,"race_id":2,"Id":0,"login":"+22222222222","mark_label":"000077","mark":3,"longitude":50.10585484094918},{"date":"Mon Nov 12 01:56:05 SAMT 2018","altitude":136,"start_id":37,"latitude":53.20073654875159,"race_id":2,"Id":1,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.105907898396254},{"date":"Mon Nov 12 11:29:38 SAMT 2018","altitude":0,"start_id":1,"latitude":53.200274161063135,"race_id":1,"Id":2,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.14424856752157}],"key":"galkovvladimirandreevich"}', ''),
	(11, 'AskForMainLog', '2018-11-14 01:27:14', '+79272006026', 'Admin', '{"caller":"ru.galkov.racenfctracer.ActivityAdminManager@c0fa2ec","exec_login":"+79272006026","asker":"AskForMainLog","exec_level":"Admin","key":"galkovvladimirandreevich"}', '{"asker":"AskForMainLog","rows":[{"date":"Mon Nov 12 01:55:50 SAMT 2018","altitude":137,"start_id":37,"latitude":53.200658303685486,"race_id":2,"Id":0,"login":"+22222222222","mark_label":"000077","mark":3,"longitude":50.10585484094918},{"date":"Mon Nov 12 01:56:05 SAMT 2018","altitude":136,"start_id":37,"latitude":53.20073654875159,"race_id":2,"Id":1,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.105907898396254},{"date":"Mon Nov 12 11:29:38 SAMT 2018","altitude":0,"start_id":1,"latitude":53.200274161063135,"race_id":1,"Id":2,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.14424856752157}],"key":"galkovvladimirandreevich"}', ''),
	(12, 'AskForMainLog', '2018-11-14 01:28:14', '+79272006026', 'Admin', '{"caller":"ru.galkov.racenfctracer.ActivityAdminManager@c0fa2ec","exec_login":"+79272006026","asker":"AskForMainLog","exec_level":"Admin","key":"galkovvladimirandreevich"}', '{"asker":"AskForMainLog","rows":[{"date":"Mon Nov 12 01:55:50 SAMT 2018","altitude":137,"start_id":37,"latitude":53.200658303685486,"race_id":2,"Id":0,"login":"+22222222222","mark_label":"000077","mark":3,"longitude":50.10585484094918},{"date":"Mon Nov 12 01:56:05 SAMT 2018","altitude":136,"start_id":37,"latitude":53.20073654875159,"race_id":2,"Id":1,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.105907898396254},{"date":"Mon Nov 12 11:29:38 SAMT 2018","altitude":0,"start_id":1,"latitude":53.200274161063135,"race_id":1,"Id":2,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.14424856752157}],"key":"galkovvladimirandreevich"}', ''),
	(13, 'AskForMainLog', '2018-11-14 01:29:14', '+79272006026', 'Admin', '{"caller":"ru.galkov.racenfctracer.ActivityAdminManager@c0fa2ec","exec_login":"+79272006026","asker":"AskForMainLog","exec_level":"Admin","key":"galkovvladimirandreevich"}', '{"asker":"AskForMainLog","rows":[{"date":"Mon Nov 12 01:55:50 SAMT 2018","altitude":137,"start_id":37,"latitude":53.200658303685486,"race_id":2,"Id":0,"login":"+22222222222","mark_label":"000077","mark":3,"longitude":50.10585484094918},{"date":"Mon Nov 12 01:56:05 SAMT 2018","altitude":136,"start_id":37,"latitude":53.20073654875159,"race_id":2,"Id":1,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.105907898396254},{"date":"Mon Nov 12 11:29:38 SAMT 2018","altitude":0,"start_id":1,"latitude":53.200274161063135,"race_id":1,"Id":2,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.14424856752157}],"key":"galkovvladimirandreevich"}', ''),
	(14, 'AskForMainLog', '2018-11-14 01:30:14', '+79272006026', 'Admin', '{"caller":"ru.galkov.racenfctracer.ActivityAdminManager@c0fa2ec","exec_login":"+79272006026","asker":"AskForMainLog","exec_level":"Admin","key":"galkovvladimirandreevich"}', '{"asker":"AskForMainLog","rows":[{"date":"Mon Nov 12 01:55:50 SAMT 2018","altitude":137,"start_id":37,"latitude":53.200658303685486,"race_id":2,"Id":0,"login":"+22222222222","mark_label":"000077","mark":3,"longitude":50.10585484094918},{"date":"Mon Nov 12 01:56:05 SAMT 2018","altitude":136,"start_id":37,"latitude":53.20073654875159,"race_id":2,"Id":1,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.105907898396254},{"date":"Mon Nov 12 11:29:38 SAMT 2018","altitude":0,"start_id":1,"latitude":53.200274161063135,"race_id":1,"Id":2,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.14424856752157}],"key":"galkovvladimirandreevich"}', ''),
	(15, 'AskForMainLog', '2018-11-14 01:31:14', '+79272006026', 'Admin', '{"caller":"ru.galkov.racenfctracer.ActivityAdminManager@c0fa2ec","exec_login":"+79272006026","asker":"AskForMainLog","exec_level":"Admin","key":"galkovvladimirandreevich"}', '{"asker":"AskForMainLog","rows":[{"date":"Mon Nov 12 01:55:50 SAMT 2018","altitude":137,"start_id":37,"latitude":53.200658303685486,"race_id":2,"Id":0,"login":"+22222222222","mark_label":"000077","mark":3,"longitude":50.10585484094918},{"date":"Mon Nov 12 01:56:05 SAMT 2018","altitude":136,"start_id":37,"latitude":53.20073654875159,"race_id":2,"Id":1,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.105907898396254},{"date":"Mon Nov 12 11:29:38 SAMT 2018","altitude":0,"start_id":1,"latitude":53.200274161063135,"race_id":1,"Id":2,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.14424856752157}],"key":"galkovvladimirandreevich"}', ''),
	(16, 'AskForMainLog', '2018-11-14 01:32:14', '+79272006026', 'Admin', '{"caller":"ru.galkov.racenfctracer.ActivityAdminManager@c0fa2ec","exec_login":"+79272006026","asker":"AskForMainLog","exec_level":"Admin","key":"galkovvladimirandreevich"}', '{"asker":"AskForMainLog","rows":[{"date":"Mon Nov 12 01:55:50 SAMT 2018","altitude":137,"start_id":37,"latitude":53.200658303685486,"race_id":2,"Id":0,"login":"+22222222222","mark_label":"000077","mark":3,"longitude":50.10585484094918},{"date":"Mon Nov 12 01:56:05 SAMT 2018","altitude":136,"start_id":37,"latitude":53.20073654875159,"race_id":2,"Id":1,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.105907898396254},{"date":"Mon Nov 12 11:29:38 SAMT 2018","altitude":0,"start_id":1,"latitude":53.200274161063135,"race_id":1,"Id":2,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.14424856752157}],"key":"galkovvladimirandreevich"}', ''),
	(17, 'AskForMainLog', '2018-11-14 01:33:14', '+79272006026', 'Admin', '{"caller":"ru.galkov.racenfctracer.ActivityAdminManager@c0fa2ec","exec_login":"+79272006026","asker":"AskForMainLog","exec_level":"Admin","key":"galkovvladimirandreevich"}', '{"asker":"AskForMainLog","rows":[{"date":"Mon Nov 12 01:55:50 SAMT 2018","altitude":137,"start_id":37,"latitude":53.200658303685486,"race_id":2,"Id":0,"login":"+22222222222","mark_label":"000077","mark":3,"longitude":50.10585484094918},{"date":"Mon Nov 12 01:56:05 SAMT 2018","altitude":136,"start_id":37,"latitude":53.20073654875159,"race_id":2,"Id":1,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.105907898396254},{"date":"Mon Nov 12 11:29:38 SAMT 2018","altitude":0,"start_id":1,"latitude":53.200274161063135,"race_id":1,"Id":2,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.14424856752157}],"key":"galkovvladimirandreevich"}', ''),
	(18, 'AskForMainLog', '2018-11-14 01:34:14', '+79272006026', 'Admin', '{"caller":"ru.galkov.racenfctracer.ActivityAdminManager@c0fa2ec","exec_login":"+79272006026","asker":"AskForMainLog","exec_level":"Admin","key":"galkovvladimirandreevich"}', '{"asker":"AskForMainLog","rows":[{"date":"Mon Nov 12 01:55:50 SAMT 2018","altitude":137,"start_id":37,"latitude":53.200658303685486,"race_id":2,"Id":0,"login":"+22222222222","mark_label":"000077","mark":3,"longitude":50.10585484094918},{"date":"Mon Nov 12 01:56:05 SAMT 2018","altitude":136,"start_id":37,"latitude":53.20073654875159,"race_id":2,"Id":1,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.105907898396254},{"date":"Mon Nov 12 11:29:38 SAMT 2018","altitude":0,"start_id":1,"latitude":53.200274161063135,"race_id":1,"Id":2,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.14424856752157}],"key":"galkovvladimirandreevich"}', ''),
	(19, 'AskForMainLog', '2018-11-14 01:35:14', '+79272006026', 'Admin', '{"caller":"ru.galkov.racenfctracer.ActivityAdminManager@c0fa2ec","exec_login":"+79272006026","asker":"AskForMainLog","exec_level":"Admin","key":"galkovvladimirandreevich"}', '{"asker":"AskForMainLog","rows":[{"date":"Mon Nov 12 01:55:50 SAMT 2018","altitude":137,"start_id":37,"latitude":53.200658303685486,"race_id":2,"Id":0,"login":"+22222222222","mark_label":"000077","mark":3,"longitude":50.10585484094918},{"date":"Mon Nov 12 01:56:05 SAMT 2018","altitude":136,"start_id":37,"latitude":53.20073654875159,"race_id":2,"Id":1,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.105907898396254},{"date":"Mon Nov 12 11:29:38 SAMT 2018","altitude":0,"start_id":1,"latitude":53.200274161063135,"race_id":1,"Id":2,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.14424856752157}],"key":"galkovvladimirandreevich"}', ''),
	(20, 'AskForMainLog', '2018-11-14 01:36:14', '+79272006026', 'Admin', '{"caller":"ru.galkov.racenfctracer.ActivityAdminManager@c0fa2ec","exec_login":"+79272006026","asker":"AskForMainLog","exec_level":"Admin","key":"galkovvladimirandreevich"}', '{"asker":"AskForMainLog","rows":[{"date":"Mon Nov 12 01:55:50 SAMT 2018","altitude":137,"start_id":37,"latitude":53.200658303685486,"race_id":2,"Id":0,"login":"+22222222222","mark_label":"000077","mark":3,"longitude":50.10585484094918},{"date":"Mon Nov 12 01:56:05 SAMT 2018","altitude":136,"start_id":37,"latitude":53.20073654875159,"race_id":2,"Id":1,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.105907898396254},{"date":"Mon Nov 12 11:29:38 SAMT 2018","altitude":0,"start_id":1,"latitude":53.200274161063135,"race_id":1,"Id":2,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.14424856752157}],"key":"galkovvladimirandreevich"}', ''),
	(21, 'AskForMainLog', '2018-11-14 01:37:14', '+79272006026', 'Admin', '{"caller":"ru.galkov.racenfctracer.ActivityAdminManager@c0fa2ec","exec_login":"+79272006026","asker":"AskForMainLog","exec_level":"Admin","key":"galkovvladimirandreevich"}', '{"asker":"AskForMainLog","rows":[{"date":"Mon Nov 12 01:55:50 SAMT 2018","altitude":137,"start_id":37,"latitude":53.200658303685486,"race_id":2,"Id":0,"login":"+22222222222","mark_label":"000077","mark":3,"longitude":50.10585484094918},{"date":"Mon Nov 12 01:56:05 SAMT 2018","altitude":136,"start_id":37,"latitude":53.20073654875159,"race_id":2,"Id":1,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.105907898396254},{"date":"Mon Nov 12 11:29:38 SAMT 2018","altitude":0,"start_id":1,"latitude":53.200274161063135,"race_id":1,"Id":2,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.14424856752157}],"key":"galkovvladimirandreevich"}', ''),
	(22, 'AskForMainLog', '2018-11-14 01:38:14', '+79272006026', 'Admin', '{"caller":"ru.galkov.racenfctracer.ActivityAdminManager@c0fa2ec","exec_login":"+79272006026","asker":"AskForMainLog","exec_level":"Admin","key":"galkovvladimirandreevich"}', '{"asker":"AskForMainLog","rows":[{"date":"Mon Nov 12 01:55:50 SAMT 2018","altitude":137,"start_id":37,"latitude":53.200658303685486,"race_id":2,"Id":0,"login":"+22222222222","mark_label":"000077","mark":3,"longitude":50.10585484094918},{"date":"Mon Nov 12 01:56:05 SAMT 2018","altitude":136,"start_id":37,"latitude":53.20073654875159,"race_id":2,"Id":1,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.105907898396254},{"date":"Mon Nov 12 11:29:38 SAMT 2018","altitude":0,"start_id":1,"latitude":53.200274161063135,"race_id":1,"Id":2,"login":"+22222222222","mark_label":"000001","mark":2,"longitude":50.14424856752157}],"key":"galkovvladimirandreevich"}', ''),
	(23, 'AskCurrentRaceStart', '2018-11-14 01:40:26', 'nobody', 'Guest', '{"exec_login":"nobody","asker":"AskCurrentRaceStart","exec_level":"Guest","key":"galkovvladimirandreevich"}', '{"start_label":"0","start_time":"2018-10-14 03:00:00.0","stop_time":"2018-10-14 11:00:00.0","start_id":36,"race_name":"?u201a???Вµ?u201a????","race_id":2,"asker":"AskCurrentRaceStart","key":"galkovvladimirandreevich","status":"TRUE"}', ''),
	(24, 'AskCurrentRaceStart', '2018-11-14 01:41:30', 'nobody', 'Guest', '{"exec_login":"nobody","asker":"AskCurrentRaceStart","exec_level":"Guest","key":"galkovvladimirandreevich"}', '{"start_label":"0","start_time":"2018-10-14 03:00:00.0","stop_time":"2018-10-14 11:00:00.0","start_id":36,"race_name":"?u201a???Вµ?u201a????","race_id":2,"asker":"AskCurrentRaceStart","key":"galkovvladimirandreevich","status":"TRUE"}', ''),
	(25, 'AskForLogin', '2018-11-14 01:41:46', 'nobody', 'Error', '{"password":"1111111","level":"Guest","asker":"AskForLogin","login":"+79272006026","key":"galkovvladimirandreevich"}', '{"level":"Admin","asker":"AskForLogin","login":"TRUE","key":"galkovvladimirandreevich"}', ''),
	(26, 'AskUserTable', '2018-11-14 01:41:52', '+79272006026', 'Admin', '{"exec_login":"+79272006026","asker":"AskUserTable","exec_level":"Admin","key":"galkovvladimirandreevich"}', '{"usersArr":[{"altitude":84,"password":"4454444","level":"User","latitude":53.20004181470722,"Id":97,"login":"+22222222222","longitude":50.144251165911555},{"altitude":95,"password":"1111111","level":"Admin","latitude":53.20042088627815,"Id":100,"login":"+79272006026","longitude":50.1062233094126},{"altitude":109,"password":"eddwerfd","level":"Guest","latitude":53.20044871419668,"Id":109,"login":"+88444554455","longitude":50.10598383843899}],"asker":"AskUserTable","key":"galkovvladimirandreevich"}', ''),
	(27, 'AskUserTable', '2018-11-14 01:41:52', '+79272006026', 'Admin', '{"exec_login":"+79272006026","asker":"AskUserTable","exec_level":"Admin","key":"galkovvladimirandreevich"}', '{"usersArr":[{"altitude":84,"password":"4454444","level":"User","latitude":53.20004181470722,"Id":97,"login":"+22222222222","longitude":50.144251165911555},{"altitude":95,"password":"1111111","level":"Admin","latitude":53.20042088627815,"Id":100,"login":"+79272006026","longitude":50.1062233094126},{"altitude":109,"password":"eddwerfd","level":"Guest","latitude":53.20044871419668,"Id":109,"login":"+88444554455","longitude":50.10598383843899}],"asker":"AskUserTable","key":"galkovvladimirandreevich"}', '');
/*!40000 ALTER TABLE `activitylog` ENABLE KEYS */;

-- Р”Р°РјРї СЃС‚СЂСѓРєС‚СѓСЂС‹ РґР»СЏ С‚Р°Р±Р»РёС†Р° racetracer.main_log
CREATE TABLE IF NOT EXISTS `main_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datetime` datetime DEFAULT NULL,
  `mark_label` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `master_mark_label` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `mark_master_altitude` double NOT NULL DEFAULT 0,
  `mark_master_latitude` double NOT NULL DEFAULT 0,
  `mark_master_longtitude` double NOT NULL DEFAULT 0,
  `login` varchar(12) COLLATE utf8mb4_bin DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `race_id` int(11) DEFAULT NULL,
  `mark_id` int(11) DEFAULT NULL,
  `start_id` int(11) DEFAULT NULL,
  `altitude` double NOT NULL DEFAULT 0,
  `longtitude` double NOT NULL DEFAULT 0,
  `latitude` double NOT NULL DEFAULT 0,
  `master_mark_delta` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `record_for_trace_mark` (`race_id`,`user_id`,`start_id`,`mark_id`,`master_mark_label`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Р”Р°РјРї РґР°РЅРЅС‹С… С‚Р°Р±Р»РёС†С‹ racetracer.main_log: ~2 rows (РїСЂРёР±Р»РёР·РёС‚РµР»СЊРЅРѕ)
/*!40000 ALTER TABLE `main_log` DISABLE KEYS */;
INSERT INTO `main_log` (`id`, `datetime`, `mark_label`, `master_mark_label`, `mark_master_altitude`, `mark_master_latitude`, `mark_master_longtitude`, `login`, `user_id`, `race_id`, `mark_id`, `start_id`, `altitude`, `longtitude`, `latitude`, `master_mark_delta`) VALUES
	(1, '2018-11-12 01:55:50', '000077', '4215', 137, 53.200658303685486, 50.10585484094918, '+22222222222', 97, 2, 3, 37, 137, 50.10585484094918, 53.200658303685486, 3),
	(2, '2018-11-12 01:56:05', '000001', '4215', 136, 53.20073654875159, 50.105907898396254, '+22222222222', 97, 2, 2, 37, 136, 50.105907898396254, 53.20073654875159, 2),
	(3, '2018-11-12 11:29:38', '000001', '8487', 0, 53.200274161063135, 50.14424856752157, '+22222222222', 97, 1, 2, 1, 0, 50.14424856752157, 53.200274161063135, 4);
/*!40000 ALTER TABLE `main_log` ENABLE KEYS */;

-- Р”Р°РјРї СЃС‚СЂСѓРєС‚СѓСЂС‹ РґР»СЏ С‚Р°Р±Р»РёС†Р° racetracer.nfc_marks
CREATE TABLE IF NOT EXISTS `nfc_marks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `counter` int(3) NOT NULL DEFAULT 0 COMMENT 'РЅРѕРјРµСЂ РІ РїРѕСЃР»РµРґРѕРІР°С‚РµР»СЊРЅРѕСЃС‚Рё С‚РѕС‡РµРє РїРѕ РїРѕСЂСЏРґРєСѓ РїСЂРѕС…РѕР¶РґРµРЅРёСЏ. РЅР°Р·РЅР°С‡Р°РµС‚СЃСЏ Р°РґРјРёРЅРѕРј.',
  `label` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `race_id` int(11) DEFAULT NULL,
  `latitude` double DEFAULT 0,
  `altitude` double DEFAULT 0,
  `longtitude` double DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `label` (`label`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Р”Р°РјРї РґР°РЅРЅС‹С… С‚Р°Р±Р»РёС†С‹ racetracer.nfc_marks: ~4 rows (РїСЂРёР±Р»РёР·РёС‚РµР»СЊРЅРѕ)
/*!40000 ALTER TABLE `nfc_marks` DISABLE KEYS */;
INSERT INTO `nfc_marks` (`id`, `counter`, `label`, `race_id`, `latitude`, `altitude`, `longtitude`) VALUES
	(1, 0, '4215', 0, 0, 0, 0),
	(2, 0, '000001', 0, 0, 0, 0),
	(3, 0, '000077', 0, 0, 0, 0),
	(4, 0, '8487', 0, 0, 0, 0);
/*!40000 ALTER TABLE `nfc_marks` ENABLE KEYS */;

-- Р”Р°РјРї СЃС‚СЂСѓРєС‚СѓСЂС‹ РґР»СЏ С‚Р°Р±Р»РёС†Р° racetracer.race
CREATE TABLE IF NOT EXISTS `race` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `active` enum('Y','N') COLLATE utf8mb4_bin NOT NULL DEFAULT 'N',
  `date` datetime DEFAULT NULL,
  `latitude` double NOT NULL DEFAULT 0,
  `altitude` double NOT NULL DEFAULT 0,
  `longtitude` double NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin CHECKSUM=1 COMMENT='Р СР ВµРЎР‚Р С•Р С—РЎР‚Р С‘РЎРЏРЎвЂљР С‘Р Вµ.\r\nР СР С•Р В¶Р ВµРЎвЂљ РЎРѓР С•Р В¶Р ВµРЎР‚Р В¶Р В°РЎвЂљРЎРЉ Р Р…Р ВµРЎРѓР С”Р С•Р В»РЎРЉР С”Р С• Р В·Р В°Р ВµР В·Р Т‘Р С•Р Р†.';

-- Р”Р°РјРї РґР°РЅРЅС‹С… С‚Р°Р±Р»РёС†С‹ racetracer.race: ~3 rows (РїСЂРёР±Р»РёР·РёС‚РµР»СЊРЅРѕ)
/*!40000 ALTER TABLE `race` DISABLE KEYS */;
INSERT INTO `race` (`id`, `label`, `active`, `date`, `latitude`, `altitude`, `longtitude`) VALUES
	(1, 'Р Р†РЎвЂљР С•РЎР‚Р С•Р в„–', 'N', NULL, 0, 0, 0),
	(2, 'РЎвЂљРЎР‚Р ВµРЎвЂљР С‘Р в„–', 'Y', NULL, 0, 0, 0),
	(3, 'РЎвЂЎР ВµРЎвЂљР Р†РЎвЂРЎР‚РЎвЂљРЎвЂ№Р в„–', 'N', NULL, 0, 0, 0);
/*!40000 ALTER TABLE `race` ENABLE KEYS */;

-- Р”Р°РјРї СЃС‚СЂСѓРєС‚СѓСЂС‹ РґР»СЏ С‚Р°Р±Р»РёС†Р° racetracer.start
CREATE TABLE IF NOT EXISTS `start` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Р С‘Р Р…Р Т‘Р ВµР С”РЎРѓ Р В·Р В°Р ВµР В·Р Т‘Р В°',
  `race_id` int(11) NOT NULL DEFAULT 0 COMMENT 'РЎРѓРЎРѓРЎвЂ№Р В»Р С”Р В° Р Р…Р Вµ РЎРѓР С•РЎР‚Р ВµР Р†Р Р…Р С•Р Р†Р В°Р Р…Р С‘Р Вµ',
  `label` varchar(32) COLLATE utf8mb4_bin NOT NULL DEFAULT '0' COMMENT 'Р Р…Р В°Р В·Р Р†Р В°Р Р…Р С‘Р Вµ Р В·Р В°Р ВµР В·Р Т‘Р В°',
  `active` enum('Y','N') COLLATE utf8mb4_bin NOT NULL DEFAULT 'N',
  `latitude` double DEFAULT 0,
  `longtitude` double DEFAULT 0,
  `altitude` double DEFAULT 0,
  `start_time` datetime DEFAULT NULL,
  `stop_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `start` (`race_id`,`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='РЎРЊРЎвЂљР С• Р В·Р В°Р ВµР В·Р Т‘.';

-- Р”Р°РјРї РґР°РЅРЅС‹С… С‚Р°Р±Р»РёС†С‹ racetracer.start: ~91 rows (РїСЂРёР±Р»РёР·РёС‚РµР»СЊРЅРѕ)
/*!40000 ALTER TABLE `start` DISABLE KEYS */;
INSERT INTO `start` (`id`, `race_id`, `label`, `active`, `latitude`, `longtitude`, `altitude`, `start_time`, `stop_time`) VALUES
	(1, 1, 'a', 'N', 0, 0, 0, '2018-10-12 06:00:00', '2018-10-12 12:00:00'),
	(2, 1, 'q', 'N', 0, 0, 0, '2017-09-01 01:00:00', '2029-09-01 11:00:00'),
	(3, 1, 'e', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(4, 1, '4', 'N', 0, 0, 0, '2017-09-01 10:00:00', '2019-09-01 10:00:00'),
	(5, 1, '5', 'N', 0, 0, 0, '2018-09-01 10:05:00', '2019-09-01 10:05:00'),
	(6, 1, '1', 'N', 0, 0, 0, '2017-09-01 10:30:00', '2019-09-01 12:00:00'),
	(7, 1, '2', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(8, 1, '3', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(9, 1, '1', 'N', 0, 0, 0, '2017-09-01 10:00:00', '2019-09-01 10:00:00'),
	(10, 1, '2', 'N', 0, 0, 0, '2017-09-01 10:30:00', '2019-09-01 12:00:00'),
	(11, 1, '1', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(12, 1, '2', 'N', 0, 0, 0, '2018-09-01 10:05:00', '2019-09-01 10:05:00'),
	(13, 1, '3', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(14, 1, '4', 'N', 0, 0, 0, '2017-09-01 10:30:00', '2019-09-01 12:00:00'),
	(15, 1, '5', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(16, 1, '6', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(17, 1, '7', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(18, 1, '8', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(19, 1, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(20, 1, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(21, 1, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(22, 1, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(23, 1, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(24, 1, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(25, 1, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(26, 1, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(27, 1, '0', 'N', 0, 0, 0, '2017-09-01 10:30:00', '2019-09-01 12:00:00'),
	(28, 1, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(29, 1, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(30, 1, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(31, 2, '0', 'N', 0, 0, 0, '2017-09-01 10:00:00', '2019-09-01 10:00:00'),
	(32, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(33, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(34, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(35, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:05:00', '2019-09-01 10:05:00'),
	(36, 2, '0', 'Y', 0, 0, 0, '2018-10-14 03:00:00', '2018-10-14 11:00:00'),
	(37, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(38, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(39, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(40, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(41, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(42, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(43, 2, '0', 'N', 0, 0, 0, '2017-09-01 10:00:00', '2019-09-01 10:00:00'),
	(44, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(45, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(46, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(47, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(48, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(49, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(50, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(51, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(52, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(53, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(54, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(55, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(56, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(57, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(58, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(59, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(60, 2, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(61, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(62, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(63, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(64, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(65, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(66, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(67, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:50:00', '2019-09-01 10:07:00'),
	(68, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(69, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(70, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(71, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(72, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(73, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(74, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(75, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(76, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(77, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(78, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(79, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(80, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(81, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(82, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(83, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(84, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(85, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(86, 3, '0', 'N', 0, 0, 0, '2017-09-01 10:30:00', '2019-09-01 12:00:00'),
	(87, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(88, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(89, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(90, 3, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00'),
	(94, 0, '0', 'N', 0, 0, 0, '2018-09-01 10:00:00', '2019-09-01 10:00:00');
/*!40000 ALTER TABLE `start` ENABLE KEYS */;

-- Р”Р°РјРї СЃС‚СЂСѓРєС‚СѓСЂС‹ РґР»СЏ С‚Р°Р±Р»РёС†Р° racetracer.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `master_mark_label` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `login` varchar(12) COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(32) COLLATE utf8mb4_bin NOT NULL,
  `level` enum('Guest','User','Admin') COLLATE utf8mb4_bin NOT NULL DEFAULT 'Guest',
  `latitude` double NOT NULL DEFAULT 0,
  `altitude` double NOT NULL DEFAULT 0,
  `longtitude` double NOT NULL DEFAULT 0,
  `registred_race_id` int(11) DEFAULT NULL,
  `registred_start_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- Р”Р°РјРї РґР°РЅРЅС‹С… С‚Р°Р±Р»РёС†С‹ racetracer.users: ~4 rows (РїСЂРёР±Р»РёР·РёС‚РµР»СЊРЅРѕ)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `master_mark_label`, `login`, `password`, `level`, `latitude`, `altitude`, `longtitude`, `registred_race_id`, `registred_start_id`) VALUES
	(97, '8487', '+22222222222', '4454444', 'User', 53.20004181470722, 84, 50.144251165911555, 0, 0),
	(100, '12', '+79272006026', '1111111', 'Admin', 53.201046427711844, 69, 50.10521722957492, 0, 0),
	(109, 'null', '+88444554455', 'eddwerfd', 'Guest', 53.20044871419668, 109, 50.10598383843899, 0, 0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Р”Р°РјРї СЃС‚СЂСѓРєС‚СѓСЂС‹ РґР»СЏ С‚Р°Р±Р»РёС†Р° racetracer.user_track
CREATE TABLE IF NOT EXISTS `user_track` (
  `id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `datetime` datetime DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `altitude` double DEFAULT NULL,
  `longtitude` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Р”Р°РјРї РґР°РЅРЅС‹С… С‚Р°Р±Р»РёС†С‹ racetracer.user_track: ~0 rows (РїСЂРёР±Р»РёР·РёС‚РµР»СЊРЅРѕ)
/*!40000 ALTER TABLE `user_track` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_track` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
