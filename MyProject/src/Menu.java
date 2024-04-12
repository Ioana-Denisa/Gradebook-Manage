import java.util.Scanner;

public class Menu
{
    private Catalog catalog;
    public Menu()
    {
        catalog=new Catalog();
    }

    public Menu(Catalog catalog) {
        this.catalog = catalog;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public void writeReadFiles()
    {
       // catalog.writeDates();
        catalog.readFiles();
    }

    public void printMenu1() {
        System.out.println("\n=== Meniu ===");
        System.out.println("Alege tipul de utilizator:");
        System.out.println("1. Student");
        System.out.println("2. Profesor");
        System.out.println("0. Ieșire");
        System.out.print("Alege o opțiune: ");
    }

    public void printMenuStudent() {
        System.out.println("\n=== Meniu student ===");
        System.out.println("1. Vizualizare note");
        System.out.println("2. Vizualizare discipline");
        System.out.println("3. Vizualizarea notele aferente disciplinelor");
        System.out.println("0. Inapoi");
        System.out.print("Alege o opțiune: ");
    }

   public void printMenuTeacher() {

        System.out.println("\n=== Meniu profesor ===");
        System.out.println("1. Vizualizare studenti");
        System.out.println("2. Vizualizare discipline");
        System.out.println("3. Vizualizarea studentilor cu notele aferente disciplinelor");
        System.out.println("4. Calcul medie finala");
        System.out.println("5. Sortarea stundetilor dupa nume");
        System.out.println("6. Sortarea disciplinelor dupa nume");
        System.out.println("7. Sortarea notelor dupa data calendaristica");
        System.out.println("8. Adauga nota");
        System.out.println("9. Sterge nota");
        System.out.println("10. Modifica nota");
        System.out.println("11. Afisare catalog");
        System.out.println("12. Afisare lista studenti");
        System.out.println("13. Afisare lista profesori");
        System.out.println("14. Afisare lista discipline");
        System.out.println("0. Inapoi");
        System.out.print("Alege o opțiune: ");
    }

    public void printNameStudent(int id)
    {
        System.out.println("\nBuna "+catalog.getNameStudent(id));
    }
    public void menuStudent( int id)
    {
        printNameStudent(id);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenuStudent();
            int optiune = scanner.nextInt();
            switch (optiune) {
                case 1:
                    System.out.println("\nNotele tale sunt: "+catalog.getAllGradesForStudent(id));
                    break;
                case 2:
                    System.out.println("\nDisciplinele tale sunt: "+catalog.getNameDisciplineForStudent(id));
                    break;
                case 3:
                    catalog.printGradesAndDisciplineForStudent(id);
                    break;
                case 0:
                    System.out.println("Inapoi!");
                    return;
                default:
                    System.out.println("Opțiune invalidă. Te rog să alegi din nou.");
            }
        }
    }

    public void printNameTeacher(int id)
    {
        System.out.println("\nBuna ziua, "+catalog.getNameTeacher(id));
        System.out.println();
    }
    public void menuTeacher( int id)
    {
        printNameTeacher(id);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenuTeacher();
            int optiune = scanner.nextInt();
            switch (optiune) {
                case 1:
                    catalog.printNameStudentsToTheTeacher(id);
                    break;
                case 2:
                    catalog.printDisciplineForTeacher(id);
                    break;
                case 3:
                    catalog.printGradeDisciplineStudentForTeacher(id);
                    break;
                case 4:
                    catalog.printAveragesPerStudents(id);
                    break;
                case 5:
                    catalog.printSortedStudents(id);
                    break;
                case 6:
                    catalog.printSortedDiscipline(id);
                    break;
                case 7:
                    catalog.printSortedGradesByDate(id);
                    break;
                case 8:
                    System.out.println("Pentru a introduce o nota va rugam sa alegeti id-urile corespunzatoare:");
                    catalog.printDisciplineAndId(id);
                    catalog.printStudentAndId(id);
                    System.out.println("\nStudentii si notele aferente disciplinelor sunt:");
                    catalog.printGradeDisciplineStudentForTeacher(id);

                    System.out.print("Nota: ");
                    int nota=scanner.nextInt();

                    System.out.print("Id studentului: ");
                    int idElev=scanner.nextInt();

                    System.out.print("Id disciplinei: ");
                    int idDisciplina=scanner.nextInt();

                    catalog.addGrade(nota,idElev,id,idDisciplina);
                    catalog.printGradeDisciplineStudentForTeacher(id);
                    catalog.writeCatalogToFile();

                    break;
                case 9:
                    System.out.println("\nPentru a sterge o nota va rugam selectati id-ul notei pe care doriti sa o stergeti");
                    catalog.printIdGradeGradeDisciplineStudentForTeacher(id);
                    System.out.print("Id nota: ");
                    nota=scanner.nextInt();
                    catalog.eraseGradeForTeacher(nota,id);
                    catalog.printGradeDisciplineStudentForTeacher(id);
                    catalog.writeCatalogToFile();

                    break;
                case 10:
                    System.out.println("\nPentru a modifica o nota va rugam selectati id-ul notei pe care doriti sa o modificati");
                    catalog.printIdGradeGradeDisciplineStudentForTeacher(id);
                    System.out.print("Id nota: ");
                    int idnota=scanner.nextInt();
                    System.out.print("Nota pe care vreti sa o introduceti: ");
                    nota=scanner.nextInt();
                    catalog.modifyGradeForTeacher(nota,idnota,id);
                    catalog.printGradeDisciplineStudentForTeacher(id);
                    catalog.writeCatalogToFile();
                    break;
                case 11:
                    catalog.printCatalog();
                    break;
                case 12:
                    catalog.printAllStudents();
                    break;
                case 13:
                    catalog.printAllTeacher();
                    break;
                case 14:
                    catalog.printAllDisciplines();
                    break;
                case 0:
                    System.out.println("Inapoi!");
                    return;
                default:
                    System.out.println("Opțiune invalidă. Te rog să alegi din nou.");
            }
        }
    }

   public void menuPrincipal()
    {
        catalog.readFiles();
        catalog.writeCatalogToFile();

        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            printMenu1();

            int optiune = scanner.nextInt();
            int id;
            switch (optiune) {
                case 1:
                    catalog.printStudentsWithId();
                    System.out.print("Introduce id-ul de student: ");
                    id=scanner.nextInt();
                    if(catalog.isStudent(id))
                        menuStudent(id);
                    System.out.println("Id ul introdus este incorect! Mai incercati");

                    break;
                case 2:
                    catalog.printTeachersWhitId();
                    System.out.print("Introduce id-ul de profesor: ");
                    id=scanner.nextInt();
                    if(catalog.isTeacher(id))
                        menuTeacher(id);
                    System.out.println("Id ul introdus este incorect! Mai incercati");

                    break;
                case 0:
                    System.out.println("La revedere!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opțiune invalidă. Te rog să alegi din nou.");
            }
        }
    }
}
