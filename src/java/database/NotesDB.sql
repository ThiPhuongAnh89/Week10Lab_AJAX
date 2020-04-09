DROP DATABASE if exists NotesDB;
CREATE DATABASE NotesDB;

USE NotesDB;


CREATE TABLE `notes` (
  `noteid` int(11) NOT NULL AUTO_INCREMENT,
  `datecreated` datetime NOT NULL,
  `title` varchar(30) NOT NULL,
  `contents` varchar(20000) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`noteid`)
) ;

INSERT INTO `notes` (`datecreated`, `title`, `contents`) VALUES (NOW(), 'Sample note 1', 'This is a sample note.\n\nMore text in the sample note.');
INSERT INTO `notes` (`datecreated`, `title`, `contents`) VALUES (NOW(), 'Sample note 2', 'This is a sample note.\n\nMore text in the sample note.');
INSERT INTO `notes` (`datecreated`, `title`, `contents`) VALUES (NOW(), 'Sample note 3', 'This is a sample note.\n\nMore text in the sample note.');

