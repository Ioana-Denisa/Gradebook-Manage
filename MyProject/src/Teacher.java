import java.util.HashSet;

public class Teacher
{
    private static int teacherCounter=10;
    private int id;
    private String name;
    private HashSet<Integer> discipline;

    public Teacher(String name)
    {
        this.discipline=new HashSet<>();
        this.id=teacherCounter++;
        this.name=name;

    }
    public String getName() {
        return name;
    }
    public HashSet<Integer> getDiscipline() {
        return discipline;
    }
    public int getId()
    {
        return id;
    }
    public void addDiscipline(int disciplineId)
    {
        discipline.add(disciplineId);
    }
}

