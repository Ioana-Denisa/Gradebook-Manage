import java.util.HashSet;


public class Student
{
    private static int studentCounter=0;
    private final int id;
    private String name;
    private HashSet<Integer> disciplineId;
    private HashSet<Integer> gradeId;

    public Student(String name)
    {
        this.id=studentCounter++;
        this.name=name  ;
        this.disciplineId=new HashSet<>();
        this.gradeId =new HashSet<>();
    }

    public String getNameStudent() {
        return name;
    }

    public HashSet<Integer> getDisciplineId() {
        return disciplineId;
    }
    public int getId()
    {
        return id;
    }
    public void addDiscipline(int idDiscipline)
    {
        disciplineId.add(idDiscipline);
    }
    public void addGrade(int idGrade)
    {
        gradeId.add(idGrade);
    }


}


