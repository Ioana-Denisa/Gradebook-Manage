

public class Discipline
{
    private static int disciplineCounter =0;
    private final int id;
    private String name;


    public Discipline(String numeDisciplina) {
        this.name = numeDisciplina;
        this.id = disciplineCounter++;
    }

    public String getNumeDisciplina() {
        return name;
    }

    public int getId()
    {
        return id;
    }


}
