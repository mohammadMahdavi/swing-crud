package swingcrud;

/**
 *
 * @author JAMES
 */
public class Student {
    private String firstName;
    private String lastName;
    private String motherName;
    private String fatherName;
    private String studentNumber;
    private int yearOfBrithday;

    public Student(String firstName, String lastName, String matherName, String fatherName, int yearOfBrithday, String studentNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.motherName = matherName;
        this.fatherName = fatherName;
        this.yearOfBrithday = yearOfBrithday;
        this.studentNumber = studentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String matherName) {
        this.motherName = matherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public int getYearOfBrithday() {
        return yearOfBrithday;
    }

    public void setYearOfBrithday(int yearOfBrithday) {
        this.yearOfBrithday = yearOfBrithday;
    }

    
    
}
