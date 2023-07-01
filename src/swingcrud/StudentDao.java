/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swingcrud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Mohammad
 */
public class StudentDao {

    private final String INSERT_SQL = "INSERT INTO `student`(`first_name`, `last_name`, `mother_name`, `father_name`, `year_of_birthday`, `student_number`) "
            + "VALUES (?, ?, ?, ?, ?, ?)";
    private final String UPDATE_BY_STUDENT_NUMBER_SQL = "UPDATE `student` set `first_name` = ? , `last_name` = ?, "
            + "`mother_name` = ?, `father_name` = ?, `year_of_birthday` = ? "
            + "WHERE `student_number` = ?";
    private final String FIND_BY_STUDENT_NUMBER_SQL = "SELECT * FROM `student` WHERE student_number = ?";
    private final String FIND_ALL_SQL = "SELECT * FROM `student` ORDER BY `last_name`, `first_name`";
    private final String DELETE_BY_STUDENT_NUMBER_SQL = "DELETE FROM `student` WHERE student_number = ?";

    Connection connection;
    PreparedStatement preparedStatement;

    public StudentDao() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/university_db", "root", "");
        connection.setAutoCommit(false);
    }

    public void createUser(Student user) throws Exception {
        final boolean repetitiveRecord = findUserByStudentNumber(user.getStudentNumber()).isPresent();
        if (repetitiveRecord) {
            throw new Exception("The record is currently available in the DB.");
        }
        preparedStatement = connection.prepareStatement(INSERT_SQL);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getMotherName());
        preparedStatement.setString(4, user.getFatherName());
        preparedStatement.setInt(5, user.getYearOfBrithday());
        preparedStatement.setString(6, user.getStudentNumber());
        preparedStatement.executeUpdate();
        connection.commit();

    }

    public void updateUser(Student user) throws SQLException, Exception {
        final boolean nonExsitingRecord = findUserByStudentNumber(user.getStudentNumber()).isEmpty();
        if (nonExsitingRecord) {
            throw new Exception("We can not find the record in the DB.");
        }
        preparedStatement = connection.prepareStatement(UPDATE_BY_STUDENT_NUMBER_SQL);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getMotherName());
        preparedStatement.setString(4, user.getFatherName());
        preparedStatement.setInt(5, user.getYearOfBrithday());
        preparedStatement.setString(6, user.getStudentNumber());
        preparedStatement.executeUpdate();
        connection.commit();
    }

    public void deleteUser(String studentNumber) throws Exception {
        preparedStatement = connection.prepareStatement(DELETE_BY_STUDENT_NUMBER_SQL);
        preparedStatement.setString(1, studentNumber);
        boolean validStudentNumber = preparedStatement.executeUpdate() == 1;
        if (!validStudentNumber) {
            throw new Exception("We can not find the record in the DB.");
        }
        connection.commit();
    }

    public Optional<Student> findUserByStudentNumber(String studentNumber) throws SQLException {
        preparedStatement = connection.prepareStatement(FIND_BY_STUDENT_NUMBER_SQL);
        preparedStatement.setString(1, studentNumber);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return Optional.of(extractUser(rs));
        } else {
            return Optional.empty();
        }
    }

    public List<Student> findAllStudents() throws SQLException {
        preparedStatement = connection.prepareStatement(FIND_ALL_SQL);
        ResultSet rs = preparedStatement.executeQuery();
        List<Student> users = new ArrayList<>();
        while (rs.next()) {
            users.add(extractUser(rs));
        }
        return users;
    }

    private static Student extractUser(ResultSet rs) throws SQLException {
        return new Student(rs.getString("first_name"), rs.getString("last_name"),
                rs.getString("mother_name"), rs.getString("father_name"),
                rs.getInt("year_of_birthday"), rs.getString("student_number"));
    }

}
