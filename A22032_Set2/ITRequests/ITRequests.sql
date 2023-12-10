CREATE DATABASE VBoxData
GO
USE VBoxData
GO
CREATE TABLE ITRequests (
	ReqID int not null primary key identity,
	ReqName varchar(255) not null,
	ReqDate date not null,
	ReqEmail varchar(255) not null,
	ReqType varchar(255),
	ReqDetails varchar(255)
)
GO
INSERT INTO ITRequests(ReqName, ReqDate, ReqEmail, ReqType, ReqDetails) VALUES 
	('nva', '2023-4-27', 'nva@gmail.com', 'Networking', ''),
	('ntb', '2023-4-28', 'ntb@gmail.com', 'Server access', '')