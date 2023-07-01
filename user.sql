CREATE TABLE `student` (
  `student_number` varchar(16) NOT NULL,
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) NOT NULL,
  `mother_name` varchar(64) NOT NULL,
  `father_name` varchar(64) NOT NULL,
  `year_of_birthday` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

ALTER TABLE `student`
  ADD PRIMARY KEY (`student_number`);

  
INSERT INTO `student` (`first_name`, `last_name`, `mother_name`, `father_name`, `year_of_birthday`, `student_number`) VALUES
('Mohammad', 'Mahdavi', 'Maryam', 'Hamed', 1991, '12345678');