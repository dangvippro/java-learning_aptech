CREATE DATABASE EmployeeTransferManagement
GO
USE EmployeeTransferManagement
GO
CREATE TABLE Employee (
	empNumber VARCHAR(50) PRIMARY KEY,
	[password] VARCHAR(20) NOT NULL default('123456'),
	[fullname] VARCHAR(32) NOT NULL,
	dateOfBirth DATE NOT NULL,
	gender VARCHAR(6) NOT NULL check (gender = 'male' or gender = 'female'),
	email VARCHAR(255) NOT NULL check (email LIKE '%[A-Z0-9][@][A-Z0-9]%[.][A-Z0-9]%'),
	[address] VARCHAR(255) NOT NULL,
	[role] VARCHAR(32) NOT NULL,
	workExperience VARCHAR(32) NOT NULL,
	currentPosition VARCHAR(50) NOT NULL,
	currentDepartment VARCHAR(50) NOT NULL,
	currentProject VARCHAR(255) NOT NULL,
	allowanceLevel money default (0) NOT NULL,
	dateStartWork DATE NOT NULL,
	note VARCHAR(255)
)
GO
CREATE TABLE ProfessionalSkills (
	professionalSkillNumber VARCHAR(15) PRIMARY KEY,
	technicalSkills VARCHAR(255) NOT NULL
)
GO
CREATE TABLE ProfessionalSkillsOfEmployee (
    professionalSkillsOfEmployeeNumber VARCHAR(15) PRIMARY KEY,
	professionalSkillNumber VARCHAR(15) FOREIGN KEY REFERENCES ProfessionalSkills(professionalSkillNumber),
	empNumber VARCHAR(50) FOREIGN KEY REFERENCES Employee (empNumber)
)
GO
CREATE TABLE EducationalQualifications (
	educationalQualificationsNumber VARCHAR(15) PRIMARY KEY,
	empNumber VARCHAR(50) FOREIGN KEY REFERENCES Employee (empNumber),
	schoolName VARCHAR(255) NOT NULL,
	majors VARCHAR(255) NOT NULL,
	graduationGAPScores VARCHAR(255) NOT NULL check (graduationGAPScores >= '1' and graduationGAPScores <= '4'),
	durationOfStudy CHAR(9) NOT NULL check (durationOfStudy LIKE '%[0-9][-][0-9]%'),
)
GO
CREATE TABLE Administrator (
	adminNumber VARCHAR(50) PRIMARY KEY,
	[password] VARCHAR(20) NOT NULL,
	[fullname] VARCHAR(32) NOT NULL,
	dateOfBirth DATE NOT NULL,
	gender VARCHAR(5) NOT NULL check (gender = 'male' or gender = 'female'),
	email VARCHAR(255) NOT NULL check (email LIKE '%[A-Z0-9][@][A-Z0-9]%[.][A-Z0-9]%'),
	[address] VARCHAR(255) NOT NULL,
)
GO
CREATE TABLE TransferProject (
	transferProjectNumber VARCHAR(32) check (transferProjectNumber LIKE '%[TPJ][0-9]%') PRIMARY KEY,
	transferProjectName VARCHAR(50)
)
GO
CREATE TABLE TransferDepartment (
	transferDepartmentNumber VARCHAR(32) check (transferDepartmentNumber LIKE '%[TD][0-9]%') PRIMARY KEY,
	transferDepartmentName VARCHAR(50)
)
GO
CREATE TABLE TransferPosition (
	transferPositionNumber VARCHAR(32) check (transferPositionNumber LIKE '%[TPS][0-9]%') PRIMARY KEY,
	transferPositionName VARCHAR(50),
	allowanceLevel money default (0), 
	note VARCHAR(255)
)
GO
create table TransferRequests (
	transferRequestNumber INT IDENTITY(1,1) PRIMARY KEY,
	reason VARCHAR(255) NOT NULL,
	dateOfPosting DATE NOT NULL,
	transferRelievingDate DATE NOT NULL,
	transferJoiningDate DATE NOT NULL,
	cPosition VARCHAR(50),
	cDepartment VARCHAR(50),
	cProject VARCHAR(255),
	transferProjectNumber VARCHAR(32) FOREIGN KEY REFERENCES TransferProject (transferProjectNumber),
	transferPositionNumber VARCHAR(32) FOREIGN KEY REFERENCES TransferPosition (transferPositionNumber),
	transferDepartmentNumber VARCHAR(32) FOREIGN KEY REFERENCES TransferDepartment (transferDepartmentNumber),
	empNumber VARCHAR(50) FOREIGN KEY REFERENCES Employee(empNumber),
	adminNumber VARCHAR(50) FOREIGN KEY REFERENCES Administrator(adminNumber),
	approvalDate DATE,
	[status] VARCHAR(30)
)
GO
CREATE TABLE TransferLetters (
	transferLetterNumber VARCHAR(32) CHECK (transferLetterNumber like '%[TL][0-9]%') PRIMARY KEY,
	empNumber VARCHAR(50) FOREIGN KEY REFERENCES Employee(empNumber),
	adminNumber VARCHAR(50) FOREIGN KEY REFERENCES Administrator(adminNumber),
	reason VARCHAR(255),
	dateOfPosting DATE,
	transferRelieving DATE ,
	transferJoiningDate DATE,
	transferProjectNumber VARCHAR(32) FOREIGN KEY REFERENCES TransferProject (transferProjectNumber),
	transferPositionNumber VARCHAR(32) FOREIGN KEY REFERENCES TransferPosition (transferPositionNumber),
	transferDepartmentNumber VARCHAR(32) FOREIGN KEY REFERENCES TransferDepartment (transferDepartmentNumber),
	note VARCHAR(255)
)
GO
--Employee
INSERT INTO [dbo].[Employee]([empNumber], [password], [fullname], [dateOfBirth], [gender], [email], [address], [role], [workExperience], [currentProject], [currentDepartment], [currentPosition], [allowanceLevel], [dateStartWork], [note]) VALUES ('nva', 'nva123', 'Nguyen Vinh An', '2000-1-1', 'male', 'nva@gmail.com', 'An Khanh, Ninh Kieu, Can Tho City', 'employee', '4 years', 'eProject 1', 'DTech', 'Project manager', 100, '2022-8-28', '')
INSERT INTO [dbo].[Employee]([empNumber], [password], [fullname], [dateOfBirth], [gender], [email], [address], [role], [workExperience], [currentProject], [currentDepartment], [currentPosition], [allowanceLevel], [dateStartWork], [note]) VALUES ('nvb', 'nvb123', 'Nguyen Van Binh', '2000-2-2', 'male', 'nvb@gmail.com', 'Hung Loi, Ninh Kieu, Can Tho City', 'employee', '5 years', 'eProject 2', 'BTech', 'Product manager', 80, '2022-10-10', 'New employee')
INSERT INTO [dbo].[Employee]([empNumber], [password], [fullname], [dateOfBirth], [gender], [email], [address], [role], [workExperience], [currentProject], [currentDepartment], [currentPosition], [allowanceLevel], [dateStartWork], [note]) VALUES ('ntc', 'nvb123', 'Ngo Tuyet Chi', '1999-12-2', 'female', 'ngochi@gmail.com', 'Thuan Hung, Thot Not, Can Tho City', 'employee', '8 years', 'DFE Project', 'ATech', 'Accountant', 70, '2020-12-10', 'Good employee')
INSERT INTO [dbo].[Employee]([empNumber], [password], [fullname], [dateOfBirth], [gender], [email], [address], [role], [workExperience], [currentProject], [currentDepartment], [currentPosition], [allowanceLevel], [dateStartWork], [note]) VALUES ('lmn', 'lmn123', 'Le Minh Nghi', '2000-10-1', 'male', 'lmn@gmail.com', 'Thoi Long, On Mon, Can Tho City', 'employee', '3 years', 'eProject 1', 'BTech', 'Receptionist', 70, '2023-3-8', 'New employee')
INSERT INTO [dbo].[Employee]([empNumber], [password], [fullname], [dateOfBirth], [gender], [email], [address], [role], [workExperience], [currentProject], [currentDepartment], [currentPosition], [allowanceLevel], [dateStartWork], [note]) VALUES ('cmc', 'cmc123', 'Chau Minh Chiet', '1995-12-22', 'male', 'cmc@gmail.com', 'Thuan Hung, Thot Not, Can Tho City', 'employee', '7 years', 'IBM Project', 'CTech', 'Human resources manager', 100, '2019-11-10', 'Good employee')
INSERT INTO [dbo].[Employee]([empNumber], [password], [fullname], [dateOfBirth], [gender], [email], [address], [role], [workExperience], [currentProject], [currentDepartment], [currentPosition], [allowanceLevel], [dateStartWork], [note]) VALUES ('tad', 'tad123', 'Thai Anh Dao', '1995-12-2', 'female', 'tad@gmail.com', 'Long Hung, On Mon, Can Tho City', 'employee', '8 years', 'DFE Project', 'ETech', 'System analyst', 90, '2020-12-10', 'Good employee')
INSERT INTO [dbo].[Employee]([empNumber], [password], [fullname], [dateOfBirth], [gender], [email], [address], [role], [workExperience], [currentProject], [currentDepartment], [currentPosition], [allowanceLevel], [dateStartWork], [note]) VALUES ('tcb', 'tcb123', 'Ta Cong Bang', '2000-10-11', 'male', 'tcb@gmail.com', 'Hung Loi, Ninh Kieu, Can Tho City', 'employee', '9 years', 'AAA Project', 'CTech', 'Information manager', 110, '2020-12-25', '')
INSERT INTO [dbo].[Employee]([empNumber], [password], [fullname], [dateOfBirth], [gender], [email], [address], [role], [workExperience], [currentProject], [currentDepartment], [currentPosition], [allowanceLevel], [dateStartWork], [note]) VALUES ('lmt', 'lmt123', 'Le Minh Thu', '2002-12-22', 'female', 'lmt@gmail.com', 'Thuan Hung, Thot Not, Can Tho City', 'employee', '1 year', 'IBM Project', 'ATech', 'Office manager', 90, '2019-11-02', 'Good employee')
INSERT INTO [dbo].[Employee]([empNumber], [password], [fullname], [dateOfBirth], [gender], [email], [address], [role], [workExperience], [currentProject], [currentDepartment], [currentPosition], [allowanceLevel], [dateStartWork], [note]) VALUES ('lmhh', 'lmhh123', 'Ly Mai Huynh Hoa', '2002-12-22', 'female', 'lmt@gmail.com', 'Thuan Hung, Thot Not, Can Tho City', 'employee', '1 year', 'IBM Project', 'ATech', 'Office manager', 90, '2019-11-02', 'Good employee')
INSERT INTO [dbo].[Employee]([empNumber], [password], [fullname], [dateOfBirth], [gender], [email], [address], [role], [workExperience], [currentProject], [currentDepartment], [currentPosition], [allowanceLevel], [dateStartWork], [note]) VALUES ('lmyy', 'lmyy123', 'Le Mai Yen Yen', '2002-12-22', 'female', 'lmt@gmail.com', 'Thuan Hung, Thot Not, Can Tho City', 'employee', '1 year', 'IBM Project', 'ATech', 'Office manager', 90, '2019-11-02', 'Good employee')
INSERT INTO [dbo].[Employee]([empNumber], [password], [fullname], [dateOfBirth], [gender], [email], [address], [role], [workExperience], [currentProject], [currentDepartment], [currentPosition], [allowanceLevel], [dateStartWork], [note]) VALUES ('lmkk', 'lmkk123', 'Le Minh Kim Khuyet', '2002-12-22', 'female', 'lmt@gmail.com', 'Thuan Hung, Thot Not, Can Tho City', 'employee', '1 year', 'IBM Project', 'ATech', 'Office manager', 90, '2019-11-02', 'Good employee')
INSERT INTO [dbo].[Employee]([empNumber], [password], [fullname], [dateOfBirth], [gender], [email], [address], [role], [workExperience], [currentProject], [currentDepartment], [currentPosition], [allowanceLevel], [dateStartWork], [note]) VALUES ('dmn', 'dmn123', 'Dinh My Nghi', '2001-7-23', 'female', 'dmn@gmail.com', 'Thuan Hung, Thot Not, Can Tho City', 'employee', '2 year', 'IBB Project', 'YTech', 'Senior Acountant', 70, '2018-10-22', '')

--Administrator
INSERT INTO [dbo].[Administrator]([adminNumber], [password], [fullname], [dateOfBirth], [gender], [email], [address]) VALUES ('admin', 'admin123', 'Nguyen Van Lam', '1990-1-1', 'male', 'admin@gmail.com', 'Ho Chi Minh city')

--Professional Skills
INSERT INTO [dbo].[ProfessionalSkills]([professionalSkillNumber], [technicalSkills]) VALUES ('PS01', 'Java programming language')
INSERT INTO [dbo].[ProfessionalSkills]([professionalSkillNumber], [technicalSkills]) VALUES ('PS02', 'PHP programming language')
INSERT INTO [dbo].[ProfessionalSkills]([professionalSkillNumber], [technicalSkills]) VALUES ('PS03', 'Python programming language')
INSERT INTO [dbo].[ProfessionalSkills]([professionalSkillNumber], [technicalSkills]) VALUES ('PS04', 'Javascript programming language')
INSERT INTO [dbo].[ProfessionalSkills]([professionalSkillNumber], [technicalSkills]) VALUES ('PS05', 'UX Design')
INSERT INTO [dbo].[ProfessionalSkills]([professionalSkillNumber], [technicalSkills]) VALUES ('PS06', 'Data analysis')
INSERT INTO [dbo].[ProfessionalSkills]([professionalSkillNumber], [technicalSkills]) VALUES ('PS07', 'Graphic design')
INSERT INTO [dbo].[ProfessionalSkills]([professionalSkillNumber], [technicalSkills]) VALUES ('PS08', 'C# programming language')
INSERT INTO [dbo].[ProfessionalSkills]([professionalSkillNumber], [technicalSkills]) VALUES ('PS09', 'Web development')
INSERT INTO [dbo].[ProfessionalSkills]([professionalSkillNumber], [technicalSkills]) VALUES ('PS010', 'Financial management')
INSERT INTO [dbo].[ProfessionalSkills]([professionalSkillNumber], [technicalSkills]) VALUES ('PS011', 'Bookkeeping')

--Professional Skills Of Employee
INSERT INTO [dbo].[ProfessionalSkillsOfEmployee]([professionalSkillsOfEmployeeNumber],[professionalSkillNumber], [empNumber]) VALUES ('PE01', 'PS01', 'nva')
INSERT INTO [dbo].[ProfessionalSkillsOfEmployee]([professionalSkillsOfEmployeeNumber],[professionalSkillNumber], [empNumber]) VALUES ('PE02', 'PS02', 'nvb')
INSERT INTO [dbo].[ProfessionalSkillsOfEmployee]([professionalSkillsOfEmployeeNumber],[professionalSkillNumber], [empNumber]) VALUES ('PE03', 'PS03', 'cmc')
INSERT INTO [dbo].[ProfessionalSkillsOfEmployee]([professionalSkillsOfEmployeeNumber],[professionalSkillNumber], [empNumber]) VALUES ('PE04', 'PS04', 'nva')
INSERT INTO [dbo].[ProfessionalSkillsOfEmployee]([professionalSkillsOfEmployeeNumber],[professionalSkillNumber], [empNumber]) VALUES ('PE05', 'PS05', 'lmn')
INSERT INTO [dbo].[ProfessionalSkillsOfEmployee]([professionalSkillsOfEmployeeNumber],[professionalSkillNumber], [empNumber]) VALUES ('PE06', 'PS06', 'ntc')
INSERT INTO [dbo].[ProfessionalSkillsOfEmployee]([professionalSkillsOfEmployeeNumber],[professionalSkillNumber], [empNumber]) VALUES ('PE07', 'PS07', 'lmt')
INSERT INTO [dbo].[ProfessionalSkillsOfEmployee]([professionalSkillsOfEmployeeNumber],[professionalSkillNumber], [empNumber]) VALUES ('PE08', 'PS08', 'tcb')
INSERT INTO [dbo].[ProfessionalSkillsOfEmployee]([professionalSkillsOfEmployeeNumber],[professionalSkillNumber], [empNumber]) VALUES ('PE09', 'PS09', 'tad')
INSERT INTO [dbo].[ProfessionalSkillsOfEmployee]([professionalSkillsOfEmployeeNumber],[professionalSkillNumber], [empNumber]) VALUES ('PE010', 'PS010', 'lmt')
INSERT INTO [dbo].[ProfessionalSkillsOfEmployee]([professionalSkillsOfEmployeeNumber],[professionalSkillNumber], [empNumber]) VALUES ('PE011', 'PS011', 'cmc')

--Educational Qualifications
INSERT INTO [dbo].[EducationalQualifications]([educationalQualificationsNumber], [schoolName], [majors], [graduationGAPScores], [durationOfStudy], [empNumber]) VALUES ('EQ1', 'Can Tho University', 'IT', '3.75', '2019-2022', 'nva')
INSERT INTO [dbo].[EducationalQualifications]([educationalQualificationsNumber], [schoolName], [majors], [graduationGAPScores], [durationOfStudy], [empNumber]) VALUES ('EQ2', 'Can Tho University', 'IT', '3.45', '2022-2025', 'nvb')
INSERT INTO [dbo].[EducationalQualifications]([educationalQualificationsNumber], [schoolName], [majors], [graduationGAPScores], [durationOfStudy], [empNumber]) VALUES ('EQ3', 'Can Tho University', 'IT', '2.75', '2022-2025', 'ntc')
INSERT INTO [dbo].[EducationalQualifications]([educationalQualificationsNumber], [schoolName], [majors], [graduationGAPScores], [durationOfStudy], [empNumber]) VALUES ('EQ4', 'Can Tho University', 'IT', '3.9', '2017-2022', 'lmt')
INSERT INTO [dbo].[EducationalQualifications]([educationalQualificationsNumber], [schoolName], [majors], [graduationGAPScores], [durationOfStudy], [empNumber]) VALUES ('EQ5', 'Can Tho University', 'IT', '2.5', '2022-2025', 'lmn')
INSERT INTO [dbo].[EducationalQualifications]([educationalQualificationsNumber], [schoolName], [majors], [graduationGAPScores], [durationOfStudy], [empNumber]) VALUES ('EQ6', 'Can Tho University', 'IT', '1.75', '2021-2025', 'tcb')

--Transfer Project
INSERT INTO [dbo].[TransferProject]([transferProjectNumber], [transferProjectName]) VALUES ('TPJ01', 'ATECH Project')
INSERT INTO [dbo].[TransferProject]([transferProjectNumber], [transferProjectName]) VALUES ('TPJ02', 'CCA Project')
INSERT INTO [dbo].[TransferProject]([transferProjectNumber], [transferProjectName]) VALUES ('TPJ03', 'BAA Project')
INSERT INTO [dbo].[TransferProject]([transferProjectNumber], [transferProjectName]) VALUES ('TPJ04', 'ICA Project')
INSERT INTO [dbo].[TransferProject]([transferProjectNumber], [transferProjectName]) VALUES ('TPJ05', 'IOA Project')
INSERT INTO [dbo].[TransferProject]([transferProjectNumber], [transferProjectName]) VALUES ('TPJ06', 'IA Project')
INSERT INTO [dbo].[TransferProject]([transferProjectNumber], [transferProjectName]) VALUES ('TPJ07', 'EYE Project')
INSERT INTO [dbo].[TransferProject]([transferProjectNumber], [transferProjectName]) VALUES ('TPJ08', 'KOL Project')
INSERT INTO [dbo].[TransferProject]([transferProjectNumber], [transferProjectName]) VALUES ('TPJ09', 'POL Project')
INSERT INTO [dbo].[TransferProject]([transferProjectNumber], [transferProjectName]) VALUES ('TPJ010', 'OOL Project')

--Transfer Department
INSERT INTO [dbo].[TransferDepartment]([transferDepartmentNumber], [transferDepartmentName]) VALUES ('TD01', 'HTech')
INSERT INTO [dbo].[TransferDepartment]([transferDepartmentNumber], [transferDepartmentName]) VALUES ('TD02', 'GTech')
INSERT INTO [dbo].[TransferDepartment]([transferDepartmentNumber], [transferDepartmentName]) VALUES ('TD03', 'KTech')
INSERT INTO [dbo].[TransferDepartment]([transferDepartmentNumber], [transferDepartmentName]) VALUES ('TD04', 'MTech')
INSERT INTO [dbo].[TransferDepartment]([transferDepartmentNumber], [transferDepartmentName]) VALUES ('TD05', 'NTech') 
INSERT INTO [dbo].[TransferDepartment]([transferDepartmentNumber], [transferDepartmentName]) VALUES ('TD06', 'JTech') 
INSERT INTO [dbo].[TransferDepartment]([transferDepartmentNumber], [transferDepartmentName]) VALUES ('TD07', 'FTech') 

--Transfer Position
INSERT INTO [dbo].[TransferPosition]([transferPositionNumber], [transferPositionName], [allowanceLevel], [note]) VALUES ('TPS01', 'Manager', '200', '')
INSERT INTO [dbo].[TransferPosition]([transferPositionNumber], [transferPositionName], [allowanceLevel], [note]) VALUES ('TPS02', 'System analyst', '90', '') 
INSERT INTO [dbo].[TransferPosition]([transferPositionNumber], [transferPositionName], [allowanceLevel], [note]) VALUES ('TPS03', 'Accountant', '70', '')
INSERT INTO [dbo].[TransferPosition]([transferPositionNumber], [transferPositionName], [allowanceLevel], [note]) VALUES ('TPS04', 'Project manager', '100', '')
INSERT INTO [dbo].[TransferPosition]([transferPositionNumber], [transferPositionName], [allowanceLevel], [note]) VALUES ('TPS05', 'System administrator', '200', '')
INSERT INTO [dbo].[TransferPosition]([transferPositionNumber], [transferPositionName], [allowanceLevel], [note]) VALUES ('TPS06', 'Receptionist', '70', '') 
INSERT INTO [dbo].[TransferPosition]([transferPositionNumber], [transferPositionName], [allowanceLevel], [note]) VALUES ('TPS07', 'Information manager', '110', '')
INSERT INTO [dbo].[TransferPosition]([transferPositionNumber], [transferPositionName], [allowanceLevel], [note]) VALUES ('TPS08', 'Product manager', '80', '')
INSERT INTO [dbo].[TransferPosition]([transferPositionNumber], [transferPositionName], [allowanceLevel], [note]) VALUES ('TPS09', 'Office manage', '90', '')
INSERT INTO [dbo].[TransferPosition]([transferPositionNumber], [transferPositionName], [allowanceLevel], [note]) VALUES ('TPS010', 'Human resources manager', '100', '')

--Transfer Requests
INSERT INTO [dbo].[TransferRequests]([reason], [dateOfPosting], [transferRelievingDate], [transferJoiningDate], [transferProjectNumber], [transferDepartmentNumber], [transferPositionNumber], [approvalDate], [adminNumber], [empNumber], [status], cProject, cDepartment, cPosition) VALUES ('Promotion', '2023-8-15', '2023-8-20', '2023-5-20', 'TPJ05', 'TD01', 'TPS01', '2023-5-15', 'admin', 'nva', 'Approved', 'eProject 1', 'DTech', 'Project manager')
INSERT INTO [dbo].[TransferRequests]([reason], [dateOfPosting], [transferRelievingDate], [transferJoiningDate], [transferProjectNumber], [transferDepartmentNumber], [transferPositionNumber], [adminNumber], [empNumber], [status], cProject, cDepartment, cPosition) VALUES ('The new workspace closer to home', '2023-6-20', '2023-9-15', '2023-6-22', 'TPJ05', 'TD03', 'TPS06', 'admin', 'lmn', 'Waiting Approval', 'eProject 1', 'BTech', 'Receptionist')
INSERT INTO [dbo].[TransferRequests]([reason], [dateOfPosting], [transferRelievingDate], [transferJoiningDate], [transferProjectNumber], [transferDepartmentNumber], [transferPositionNumber], [adminNumber], [empNumber], [status], cProject, cDepartment, cPosition) VALUES ('The new position suits me better', '2023-1-2', '2023-5-13', '2023-1-13', 'TPJ04', 'TD03', 'TPS07', 'admin', 'cmc', 'Waiting Approval', 'IBM Project', 'CTech', 'Human resources manager')
INSERT INTO [dbo].[TransferRequests]([reason], [dateOfPosting], [transferRelievingDate], [transferJoiningDate], [transferProjectNumber], [transferDepartmentNumber], [transferPositionNumber], [adminNumber], [empNumber], [status], cProject, cDepartment, cPosition) VALUES ('The new workspace closer to home', '2023-1-2', '2023-4-13', '2023-1-13', 'TPJ02', 'TD04', 'TPS09', 'admin', 'lmhh', 'Waiting Approval', 'IBM Project', 'ATech', 'Office manager')
INSERT INTO [dbo].[TransferRequests]([reason], [dateOfPosting], [transferRelievingDate], [transferJoiningDate], [transferProjectNumber], [transferDepartmentNumber], [transferPositionNumber], [adminNumber], [empNumber], [status], cProject, cDepartment, cPosition) VALUES ('The new position suits me better', '2023-6-2', '2023-12-3', '2023-7-3', 'TPJ05', 'TD06', 'TPS010', 'admin', 'tad', 'Waiting Approval', 'DFE Project', 'ETech', 'System analyst')
INSERT INTO [dbo].[TransferRequests]([reason], [dateOfPosting], [transferRelievingDate], [transferJoiningDate], [transferProjectNumber], [transferDepartmentNumber], [transferPositionNumber], [adminNumber], [empNumber], [status], cProject, cDepartment, cPosition) VALUES ('The new workspace closer to home', '2023-4-2', '2023-10-3', '2023-5-3', 'TPJ03', 'TD01', 'TPS09', 'admin', 'lmkk', 'Waiting Approval', 'IBM Project', 'ATech', 'Office manager')
INSERT INTO [dbo].[TransferRequests]([reason], [dateOfPosting], [transferRelievingDate], [transferJoiningDate], [transferProjectNumber], [transferDepartmentNumber], [transferPositionNumber], [approvalDate], [adminNumber], [empNumber], [status], cProject, cDepartment, cPosition) VALUES ('The new workspace closer to home', '2022-12-8', '2023-3-15', '2022-12-20', 'TPJ03', 'TD05', 'TPS04', '2022-12-10', 'admin', 'nvb', 'Denied', 'eProject 2', 'BTech', 'Product manager')
INSERT INTO [dbo].[TransferRequests]([reason], [dateOfPosting], [transferRelievingDate], [transferJoiningDate], [transferProjectNumber], [transferDepartmentNumber], [transferPositionNumber], [adminNumber], [empNumber], [status], cProject, cDepartment, cPosition) VALUES ('The New department is missing a position', '2023-6-2', '2023-12-3', '2023-7-3', 'TPJ01', 'TD02', 'TPS09', 'admin', 'lmt', 'Waiting Approval', 'IBM Project', 'ATech', 'Office manager')
INSERT INTO [dbo].[TransferRequests]([reason], [dateOfPosting], [transferRelievingDate], [transferJoiningDate], [transferProjectNumber], [transferDepartmentNumber], [transferPositionNumber], [adminNumber], [adminNumber], [empNumber], [status], cProject, cDepartment, cPosition) VALUES ('The new workspace closer to home', '2023-6-22', '2023-12-3', '2023-7-3', 'TPJ01', 'TD04', 'TPS07','2023-7-20', 'admin', 'tcb', 'Approved', 'AAA Project', 'CTech', 'Information manager')
INSERT INTO [dbo].[TransferRequests]([reason], [dateOfPosting], [transferRelievingDate], [transferJoiningDate], [transferProjectNumber], [transferDepartmentNumber], [transferPositionNumber], [adminNumber], [empNumber], [status], cProject, cDepartment, cPosition) VALUES ('The New department is missing a position', '2023-5-22', '2023-12-3', '2023-6-3', 'TPJ04', 'TD07', 'TPS09', 'admin', 'lmyy', 'Waiting Approval', 'IBM Project', 'ATech', 'Office manager')
INSERT INTO [dbo].[TransferRequests]([reason], [dateOfPosting], [transferRelievingDate], [transferJoiningDate], [transferProjectNumber], [transferDepartmentNumber], [transferPositionNumber], [approvalDate], [adminNumber], [empNumber], [status], cProject, cDepartment, cPosition) VALUES ('The new position suits me better', '2023-5-22', '2023-12-3', '2023-6-3', 'TPJ04', 'TD05', 'TPS01', '2023-6-20', 'admin', 'ntc', 'Approved', 'DFE Project', 'ATech', 'Accountant')
INSERT INTO [dbo].[TransferRequests]([reason], [dateOfPosting], [transferRelievingDate], [transferJoiningDate], [transferProjectNumber], [transferDepartmentNumber], [transferPositionNumber], [approvalDate], [adminNumber], [empNumber], [status], cProject, cDepartment, cPosition) VALUES ('Promotion', '2023-5-21', '2023-8-10', '2023-2-10', 'TPJ04', 'TD07', 'TPS08', '2023-6-20', 'admin', 'dmn', 'Approved', 'IBB Project', 'YTech', 'Senior Acountant')

--Transfer Letters 
INSERT INTO [dbo].[TransferLetters]([transferLetterNumber], [adminNumber], [empNumber], [reason], [transferRelieving], [transferJoiningDate], [transferProjectNumber], [transferDepartmentNumber], [transferPositionNumber]) VALUES ('TL01', 'admin', 'tcb', 'busy', '2023-12-1', '2023-6-13',  'TPJ05', 'TD03', 'TPS02')
