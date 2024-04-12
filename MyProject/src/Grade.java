import java.util.Date;


public class Grade
{
    private static int noteCounter =0;
    private final int id;
    private int grade;
    private  Date currentDate;
    private int disciplineId;
    private int teacherId;
    private int studentId;


    public Grade(int note, int disciplineId, int teacherId, int studentId) {
        this.grade = note;
        this.id= noteCounter++;
        this.currentDate =new Date();
        this.disciplineId = disciplineId;
        this.teacherId = teacherId;
        this.studentId = studentId;

    }

    public Grade(int note, int disciplineId, int teacherId, int studentId, Date date) {
        this.grade = note;
        this.id= noteCounter++;
        this.currentDate =date;
        this.disciplineId = disciplineId;
        this.teacherId = teacherId;
        this.studentId = studentId;

    }

    public Grade()
    {
        this.id=noteCounter++;
        this.currentDate=new Date();
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getDisciplineId() {
        return disciplineId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getId()
    {return id;}

    public Date getCurrentDate()
    {
        return currentDate;
    }



}
