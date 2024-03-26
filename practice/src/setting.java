	import core.SQLExecutor;
	public class setting {
	
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			SQLExecutor executor = new SQLExecutor();
			try {
				String path = System.getProperty("user.dir").replaceAll("\\\\", "/");
				executor.ConnectRoot();
				executor.ExecuteSettingQuery("DROP DATABASE IF EXISTS `oldpopsong`;");
				executor.ExecuteSettingQuery("CREATE DATABASE  IF NOT EXISTS `oldpopsong`");
				executor.ExecuteSettingQuery("USE `oldpopsong`;");
				executor.ExecuteSettingQuery("set global local_infile=true;");
				executor.ExecuteSettingQuery(" SET NAMES utf8 ;");
				executor.ExecuteSettingQuery("CREATE TABLE `music` (\r\n"
						+	 "  `m_no` int(11) NOT NULL COMMENT '고유번호',\r\n"
						+ "  `m_name` varchar(100) DEFAULT NULL COMMENT '제목\\n',\r\n"
						+ "  `singer` varchar(50) DEFAULT NULL COMMENT '아티스트명',\r\n"
						+ "  `album` varchar(50) DEFAULT NULL COMMENT '앨범',\r\n"
						+ "  `composer` varchar(50) DEFAULT NULL COMMENT '작곡가',\r\n"
						+ "  `lyricist` varchar(50) DEFAULT NULL COMMENT '작사가',\r\n"
						+ "  `playtime` time DEFAULT NULL COMMENT '재생시간',\r\n"
						+ "  `agelimit` int(11) DEFAULT NULL COMMENT '연령제한\\n1: 19금지 / 0: 제한없음\\r',\r\n"
						+ "  `soundquality` varchar(50) DEFAULT NULL COMMENT '음질',\r\n"
						+ "  `m_img` longblob,\r\n"
						+ "  PRIMARY KEY (`m_no`)\r\n"
						+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
				executor.ExecuteSettingQuery("LOAD DATA LOCAL INFILE '"+path+"\\\\src\\\\datafiles\\\\music.txt'"
						+ "INTO TABLE `music`"
						+ "FIELDS TERMINATED BY '\\t'"
						+ "LINES TERMINATED BY '\\n'"
						+ "IGNORE 1 LINES");
				executor.ExecuteSettingQuery("CREATE TABLE `user` (\r\n"
						+ "  `u_no` int(11) NOT NULL COMMENT '고유번호',\r\n"
						+ "  `id` varchar(20) DEFAULT NULL,\r\n"
						+ "  `pw` varchar(20) DEFAULT NULL,\r\n"
						+ "  `u_name` varchar(20) DEFAULT NULL,\r\n"
						+ "  `birth` date DEFAULT NULL COMMENT '생년월일',\r\n"
						+ "  PRIMARY KEY (`u_no`)\r\n"
						+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
				executor.ExecuteSettingQuery("LOAD DATA LOCAL INFILE '"+path+"\\\\\\\\src\\\\datafiles\\\\user.txt'"
						+ "INTO TABLE `user`"
						+ "FIELDS TERMINATED BY '\\t'"
						+ "LINES TERMINATED BY '\\n'"
						+ "IGNORE 1 LINES");
				executor.ExecuteSettingQuery("CREATE TABLE `playlist` (\r\n"
						+ "  `p_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '고유번호',\r\n"
						+ "  `u_no` int(11) DEFAULT NULL COMMENT 'user 고유번호',\r\n"
						+ "  `m_no` int(11) DEFAULT NULL COMMENT 'music 고유번호',\r\n"
						+ "  PRIMARY KEY (`p_no`),\r\n"
						+ "  KEY `p_u_no_idx` (`u_no`),\r\n"
						+ "  KEY `p_m_no_idx` (`m_no`),\r\n"
						+ "  CONSTRAINT `p_m_no` FOREIGN KEY (`m_no`) REFERENCES `music` (`m_no`),\r\n"
						+ "  CONSTRAINT `p_u_no` FOREIGN KEY (`u_no`) REFERENCES `user` (`u_no`)\r\n"
						+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
				executor.ExecuteSettingQuery("LOAD DATA LOCAL INFILE '"+path+"\\\\\\\\src\\\\datafiles\\\\playlist.txt'"
						+ "INTO TABLE `playlist`"
						+ "FIELDS TERMINATED BY '\\t'"
						+ "LINES TERMINATED BY '\\n'"
						+ "IGNORE 1 LINES");
				executor.ExecuteSettingQuery("CREATE TABLE `listenlist` (\r\n"
						+ "  `l_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '고유번호',\r\n"
						+ "  `p_no` int(11) DEFAULT NULL COMMENT 'playlist 고유번호',\r\n"
						+ "  `time` varchar(14) DEFAULT NULL COMMENT '재생을 멈춘시간(yyyymmddhhmmss)',\r\n"
						+ "  `play_ox` int(11) DEFAULT NULL COMMENT '0 : 듣기 중, 1 : 듣기완료',\r\n"
						+ "  `stoptime` time DEFAULT NULL COMMENT '남은 재생시간(mm:ss)',\r\n"
						+ "  PRIMARY KEY (`l_no`),\r\n"
						+ "  KEY `l_p_no_idx` (`p_no`),\r\n"
						+ "  CONSTRAINT `l_p_no` FOREIGN KEY (`p_no`) REFERENCES `playlist` (`p_no`)\r\n"
						+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
				executor.ExecuteSettingQuery("LOAD DATA LOCAL INFILE '"+path+"\\\\\\\\src\\\\datafiles\\\\listenlist.txt'"
						+ "INTO TABLE `listenlist`"
						+ "FIELDS TERMINATED BY '\\t'"
						+ "LINES TERMINATED BY '\\n'"
						+ "IGNORE 1 LINES");
				
				executor.ExecuteSettingQuery("drop user if exists user");
				executor.ExecuteSettingQuery("create user user IDENTIFIED BY '1234'");
	   			executor.ExecuteSettingQuery("grant select, insert, delete, update on oldpopsong.* to user");
	   			System.out.println("database created successfully");
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	
	}
