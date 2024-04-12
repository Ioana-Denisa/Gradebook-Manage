import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Date;


public class Catalog {
    private HashSet<Discipline> discipline;
    private HashSet<Student> students;
    private HashSet<Teacher> teachers;
    private HashSet<Grade> grades;

    public Catalog() {
        this.discipline = new HashSet<>();
        this.students = new HashSet<>();
        this.teachers = new HashSet<>();
        this.grades = new HashSet<>();
    }


    public void readDisciplineFile() {
        String fileName = "src/File/disciplines.txt";
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Discipline disc = new Discipline(line);
                this.discipline.add(disc);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readTeacherFile() {
        String fileName = "src/File/teachers.txt";
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Teacher teacher = new Teacher(line);
                line = scanner.nextLine();
                int size = Integer.valueOf(line);
                for (int i = 0; i < size; i++) {
                    line = scanner.nextLine();
                    int id = Integer.valueOf(line);
                    teacher.addDiscipline(id);
                    teachers.add(teacher);
                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readStudents() {
        String fileName = "src/File/students.txt";
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Student student = new Student(line);

                line = scanner.nextLine();
                int size = Integer.valueOf(line);


                for (int i = 0; i < size; i++) {
                    line = scanner.nextLine();

                    String[] tokens = line.split(" ");

                    int idDisciplina = Integer.valueOf(tokens[0]);

                    student.addDiscipline(idDisciplina);

                    int idNota = Integer.valueOf(tokens[1]);
                    student.addGrade(idNota);
                }

                students.add(student);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readGreads() {
        String fileName = "src/File/grades.txt";
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] tokens = line.split(" ");

                int grade=Integer.valueOf(tokens[0]);
                int idDisc=Integer.valueOf(tokens[1]);
                int idTeacher=Integer.valueOf(tokens[2]);
                int idStudent=Integer.valueOf(tokens[3]);

                Calendar calendar=Calendar.getInstance();

                int year=Integer.parseInt(tokens[4]);
                int month=Integer.parseInt(tokens[5]);
                int day=Integer.parseInt(tokens[6]);

                calendar.set(year,month-1,day);
                Date date=calendar.getTime();

                grades.add(new Grade(grade,idDisc,idTeacher,idStudent,date));
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readFiles() {
        readTeacherFile();
        readDisciplineFile();
        readGreads();
        readStudents();
    }


    public String getNameStudent(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId)
                return student.getNameStudent();
        }
        return "Studentul cu id ul primit nu exista";
    }

    public String getNameTeacher(int teacherId) {
        for (Teacher teacher : teachers) {
            if (teacher.getId() == teacherId)
                return teacher.getName();
        }
        return "Profesorul cu id ul respectiv nu exista";
    }

    public String getNameDiscipline(int disciplineId) {
        for (Discipline discipline : discipline) {
            if (discipline.getId() == disciplineId)
                return discipline.getNumeDisciplina();
        }
        return "Disciplina cu id ul respectv nu exista";
    }
    public void printStudentsWithId()
    {
        for(Student student:students)
        {
            System.out.println("Id: "+student.getId()+" "+student.getNameStudent());
        }
    }

    public void printTeachersWhitId()
    {
        for(Teacher teacher:teachers)
        {
            System.out.println("Id "+teacher.getId()+" "+teacher.getName());
        }
    }

    public void addGrade(int grade, int studentId, int teacherId, int disciplineId) {
        Grade gradeInsert=new Grade(grade, disciplineId, teacherId, studentId);
        this.grades.add(new Grade(grade, disciplineId, teacherId, studentId));
    }


    public Vector<String> getDisciplineForTeacher(int teacherId) {
        Vector<String> vector = new Vector<>();
        for (Teacher teacher : teachers) {
            if (teacher.getId() == teacherId) {
                for (int idDisciplina : teacher.getDiscipline()) {
                    vector.add(getNameDiscipline(idDisciplina));
                }
                break;
            }
        }
        return vector;
    }


    public void printCatalog()
    {
        for(Teacher teacher:teachers)
        {
            System.out.println("\nProfesor "+teacher.getName());
            Vector<String> disciplines=getDisciplineForTeacher(teacher.getId());
            for(String disciplina:disciplines)
            {
                System.out.println(disciplina);
                HashSet<Student> students=getStudentsToTheTeacher(teacher.getId());
                for(Student student:students)
                {
                    System.out.print(student.getNameStudent()+" :");
                    System.out.println(getAllGradesForDiscipline(student.getId(), getIdDiscipline(disciplina)));
                }

            }
        }
    }

    public int getIdDiscipline(String name){
        for(Discipline disc:discipline)
        {
            if(disc.getNumeDisciplina().equals(name))
            {
                return disc.getId();
            }
        }
        return -1;
    }
    public void writeCatalogToFile() {
        String fileName="src/File/catalog.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Teacher teacher : teachers) {
                writer.write("Profesor " + teacher.getName());
                Vector<String> disciplines = getDisciplineForTeacher(teacher.getId());
                for (String disciplina : disciplines) {
                    writer.write("\n" + disciplina);
                    HashSet<Student> students = getStudentsToTheTeacher(teacher.getId());
                    for (Student student : students) {
                        Vector< String> disc=getNameDisciplineForStudent(student.getId());

                            if(disc.contains(disciplina));
                            {
                                writer.write("\n" + student.getNameStudent() + ": ");
                                Vector<Grade> grades1=getGradesForStudent(student.getId());
                                for(Grade g:grades1)
                                {
                                    if(g.getDisciplineId()==getIdDiscipline(disciplina))
                                    {
                                        String nota=String.valueOf(g.getGrade());
                                        writer.write(nota+" ");
                                    }
                                }
                            }
                    }
                    writer.newLine();
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Vector<Grade> getGradesForStudent(int studentId) {
        Vector<Grade> grade = new Vector<>();
        for (Student student : students) {
            if (student.getId() == studentId) {
                for (Grade grade1 : grades) {
                    if (grade1.getStudentId() == studentId) {
                        grade.add(grade1);
                    }
                }
                break;
            }
        }
        return grade;
    }

    public Vector<Integer> getAllGradesForStudent(int studentId) {
        Vector<Integer> grade = new Vector<>();
        for (Student student : students) {
            if (student.getId() == studentId) {
                for (Grade grade1 : grades) {
                    if (grade1.getStudentId() == studentId) {
                        grade.add(grade1.getGrade());
                    }
                }
                break;
            }
        }
        return grade;
    }

    public Vector<Integer> getAllGradesForDiscipline(int studentId, int disciplineId)
    {
        Vector<Integer> grade = new Vector<>();
        for (Student student : students) {
            if (student.getId() == studentId) {
                for (Grade grade1 : grades) {
                    if (grade1.getStudentId() == studentId && grade1.getDisciplineId()==disciplineId) {
                        grade.add(grade1.getGrade());
                    }
                }
                break;
            }
        }
        return grade;
    }

    public Vector<Grade> getGradesForDiscipline(int studentId, int disciplineId)
    {
        Vector<Grade> grade = new Vector<>();
        for (Student student : students) {
            if (student.getId() == studentId) {
                for (Grade grade1 : grades) {
                    if (grade1.getStudentId() == studentId && grade1.getDisciplineId()==disciplineId) {
                        grade.add(grade1);
                    }
                }
                break;
            }
        }
        return grade;
    }

    public Vector<Discipline> getDisciplineForStudent(int studentId) {
        Vector<Discipline> name = new Vector<>();
        for (Student student : students) {
            if (student.getId() == studentId) {
                for (Discipline disc : discipline) {
                    for (int id : student.getDisciplineId()) {
                        if (id == disc.getId()) {
                            name.add(disc);
                        }
                    }
                }
                break;
            }
        }
        return name;
    }
    public Vector<String> getNameDisciplineForStudent(int studentId) {
        Vector<String> name = new Vector<>();
        for (Student student : students) {
            if (student.getId() == studentId) {
                for (Discipline disc : discipline) {
                    for (int id : student.getDisciplineId()) {
                        if (id == disc.getId()) {
                            name.add(disc.getNumeDisciplina());
                        }
                    }
                }
                break;
            }
        }
        return name;
    }

    public void printGradesAndDisciplineForStudent(int studentId) {
        String student =getNameStudent(studentId);
        Vector<Grade> gradeVector=new Vector<>();
        gradeVector= getGradesForStudent(studentId);
        Vector<Discipline> discipline=new Vector<>();
        discipline=getDisciplineForStudent(studentId);
        System.out.println();
        for(Discipline d:discipline)
        {
            System.out.println(d.getNumeDisciplina()+":");
            for(Grade g:gradeVector)
            {
                if(g.getDisciplineId()==d.getId())
                {
                    System.out.println("Nota "+g.getGrade() + " data: "+g.getCurrentDate()+" ");
                }
            }
            System.out.println();
        }
    }

    public HashSet<Student> getStudentsToTheTeacher(int teacherId) {
        HashSet<Student> studentsVector = new HashSet<>();
        for (Grade grade : grades) {
            if (grade.getTeacherId() == teacherId) {
                for (Student student : students) {
                    if (student.getId() == grade.getStudentId()) {
                        studentsVector.add(student);
                    }
                }
            }
        }
        return studentsVector;
    }

    public void printNameStudentsToTheTeacher(int teacherId) {
        HashSet<Student> student = new HashSet<>();
        student = getStudentsToTheTeacher(teacherId);
        System.out.println("\nElevii dumneavoastra sunt: ");
        for (Student student1 : student) {
            System.out.println(student1.getNameStudent());
        }
        System.out.println();
    }



    public void printDisciplineForTeacher(int teacherId) {
        System.out.println();
        Vector<String> vector = new Vector<>();
        vector = getDisciplineForTeacher(teacherId);
        System.out.println(vector);
    }

    public void printGradeDisciplineStudentForTeacher(int teacherId)
    {
        System.out.println();
        for (Teacher teacher : teachers) {
            if (teacher.getId()==teacherId)
            {
                System.out.println("\nProfesor "+teacher.getName());
                Vector<String> disciplines=getDisciplineForTeacher(teacher.getId());
                for(String disciplina:disciplines)
                {
                    System.out.println(disciplina);
                    HashSet<Student> students=getStudentsToTheTeacher(teacher.getId());
                    for(Student student:students)
                    {
                        System.out.println(student.getNameStudent()+" :"+getAllGradesForDiscipline(student.getId(),getIdDiscipline(disciplina)));
                    }
                    System.out.println();
                }
                break;
            }
        }

    }

    public void printIdGradeGradeDisciplineStudentForTeacher(int teacherId)
    {
        for (Teacher teacher : teachers) {
            if (teacher.getId()==teacherId)
            {
                System.out.println("\nProfesor "+teacher.getName());
                Vector<String> disciplines=getDisciplineForTeacher(teacher.getId());
                for(String disciplina:disciplines)
                {
                    System.out.println("\n"+disciplina);
                    HashSet<Student> students=getStudentsToTheTeacher(teacher.getId());
                    for(Student student:students)
                    {
                        System.out.println(student.getNameStudent()+" :");
                        Vector<Grade> grades1=getGradesForDiscipline(student.getId(),getIdDiscipline(disciplina));
                        for(Grade g:grades1)
                        {
                            System.out.println("Id: "+g.getId()+" nota: "+g.getGrade());
                        }
                    }
                    System.out.println();
                }
                break;
            }
        }

    }

    public void printDisciplineAndId(int teacherId)
    {
        for(Teacher teacher:teachers)
        {
            if(teacher.getId()==teacherId)
            {
                for(int disciplineId:teacher.getDiscipline())
                {
                    System.out.println("Id: "+disciplineId+" disciplina: "+getNameDiscipline(disciplineId));
                }
            }
        }

    }

    public void printStudentAndId(int teacherId)
    {
        System.out.println();
        HashSet<Student> studentHashSet=getStudentsToTheTeacher(teacherId);
        for(Student student:studentHashSet)
        {
            System.out.println("Id: "+student.getId()+" studentul: "+ student.getNameStudent());
        }
    }

    public double calculateAveragePerStudentForTeacher(int studentId, int disciplineId, int teacherId )
    {
        double average=0;
        int count=0;
        for(Grade grade:grades)
        {
            if(grade.getTeacherId()==teacherId && grade.getDisciplineId()==disciplineId && grade.getStudentId()==studentId)
            {
                average+=grade.getGrade();
                count++;
            }
        }
        average=average/count;

        return average;
    }

    public void printAveragesPerStudents(int teacherId)
    {
        System.out.println();
        for(Teacher teacher:teachers)
        {
            if(teacher.getId()==teacherId)
            {
                for(int idDiscipline:teacher.getDiscipline())
                {
                    System.out.println(getNameDiscipline(idDiscipline));
                    for(Student student:students)
                    {
                        if(student.getDisciplineId().contains(idDiscipline) && teacher.getId()==teacherId)
                        {
                            System.out.print("Studentul "+student.getNameStudent()+" are media: ");
                            double average=calculateAveragePerStudentForTeacher(student.getId(),idDiscipline,teacherId);
                            DecimalFormat df = new DecimalFormat("#.##");
                            String medieFormata = df.format(average);
                            System.out.println(medieFormata);
                        }
                    }
                    System.out.println();
                }
                break;
            }
        }
    }

    public void printSortedStudents(int teacherId)
    {
        System.out.println();
        HashSet<Student> studentsToTheTeacher =getStudentsToTheTeacher(teacherId);
        Set<Student> studentSet=new TreeSet<>(Comparator.comparing(Student::getNameStudent));
        for(Student student:studentsToTheTeacher)
        {
            studentSet.add(student);
        }

        for(Student student:studentSet)
        {
            System.out.println(student.getNameStudent());
        }

    }

    public void printSortedDiscipline(int teacherId)
    {
        System.out.println();
        Vector<String> disciplineForTeacher=getDisciplineForTeacher(teacherId);
        Set<Discipline> disciplinesSet=new TreeSet<>(Comparator.comparing(Discipline::getNumeDisciplina));
        for(Discipline discipline1:discipline)
        {
            if(disciplineForTeacher.contains(discipline1.getNumeDisciplina()))
                disciplinesSet.add(discipline1);
        }

        for(Discipline discipline1:disciplinesSet)
        {
            System.out.println(discipline1.getNumeDisciplina());
        }
    }

    public void printSortedGradesByDate(int teacherId)
    {
        System.out.println();
        Set<Grade> gradeSet=new TreeSet<>(Comparator.comparing(Grade::getCurrentDate));
        for(Grade grade:grades)
        {
            if(grade.getTeacherId()==teacherId)
                gradeSet.add(grade);
        }
        for(Grade grade:gradeSet)
        {
            System.out.println("Nota "+ grade.getGrade()+" data: "+grade.getCurrentDate());
        }
    }

    public Boolean isStudent(int studentId)
    {
        for(Student student:students)
        {
            if(student.getId()==studentId)
                return true;
        }
        return false;
    }

    public Boolean isTeacher(int teacherId)
    {
        for(Teacher teacher:teachers)
        {
            if(teacher.getId()==teacherId)
                return true;
        }
        return false;
    }

    public void eraseGradeForTeacher(int idGrade, int idTeacher)
    {
        Iterator<Grade> iterator = grades.iterator();
        while (iterator.hasNext()) {
            Grade grade = iterator.next();
            if (grade.getId() == idGrade && grade.getTeacherId() == idTeacher) {
                iterator.remove();
            }
        }
    }

    public void modifyGradeForTeacher(int newGrade, int idGrade, int idTeacher)
    {
        for(Grade grade:grades)
        {
            if(grade.getId()==idGrade && grade.getTeacherId()==idTeacher)
            {
                grade.setGrade(newGrade);
            }
        }
    }

    public void printAllStudents()
    {
        for(Student s:students)
            System.out.println(s.getNameStudent());
    }

    public void printAllDisciplines()
    {
       for(Discipline d:discipline)
           System.out.println(d.getNumeDisciplina());
    }

    public void printAllTeacher()
    {
        for(Teacher t:teachers)
        {
            System.out.println(t.getName());
        }
    }

}


