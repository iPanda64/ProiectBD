package PlatformaStudiu;

import javax.swing.*;

public class Main {

    private static void testQuery()
    {
                AllAtr q1=new AllAtr("select id from utilizator;");
        Query q = new Query();
        q.init();
        String s= q.getAtr(q1);
        while(!s.equals("No results.")){
            System.out.println(s);
            s=q.getAtr(q1);
        }
        Table q2= q.getTable("select * from utilizator;");
        while(!q2.equals("No results.")){
            System.out.println(q2);
        }
        q.closeConnection();

        q.init();
        q.doUpdate("INSERT INTO utilizator (tip, CNP, nume, prenume, numar_telefon, email, cont_IBAN, numar_contract)" + "VALUES" + "('Student', '1334567890123', 'Popescu', 'Ion', '0712345678', 'ion.popescu@example.com', 'RO49AAAA1B31007593840000', 1001);");
        q.doUpdate("delete from utilizator where tip = 'Student';");
        q.doQuery("select * from utilizator;");
    }
    private static void testProfesor()
    {
        ProfesorBackend p=new ProfesorBackend(20);
        System.out.println(p.getCatalog2());
        System.out.println(p.getHours(1,15,1,2025)[0]);
        System.out.println(p.getStudentiActivitate(1));
        System.out.println(p.getCursuri());
        System.out.println(p.getActivitatiZiCurenta());
        System.out.println(p.getActivitatiToateZilele());
        //p.changeProcentageActivitateTo(1,50);
        System.out.println(p.getCatalog());
        p.downloadCatalog();
        p.downloadActivitatiToateZilele();
        //p.notareStudent(4,1,4);
        //p.notareStudent(4,"Popescu","Ana",7);
        //p.downloadActivitatiToateZilele();
        //p.adaugareActivitateNoua("Seminar",100,"Baze de Date");

        int[] start = {0, 30, 14, 6, 1, 2025}; // 6 ianuarie 2025, ora 14:30:00
        int[] end = {0, 0, 16, 6, 1, 2025}; // 6 ianuarie 2025, ora 16:00:00
        //p.programareActivitateExistenta(1,start,end);
        //p.changeProcentageActivitateTo(13,20);
        //p.changeProcentageActivitateTo(13,10);
        //p.programareActivitateExistenta(13,start,end);
        //System.out.println(p.getCalendar(13));
    }
    private static void testTable()
    {
        Query q = new Query();
        q.init();
        Table t;
        t=q.getTable("select id,nume from utilizator;");
        System.out.println(t);
        q.closeConnection();
    }
    private static void testStudent()
    {
        StudentBackend s=new StudentBackend(1);
        System.out.println(s.getCalendar(14));
        System.out.println(s.getRecomendedParticipants(1));
        //s.joinGroup(2);
        System.out.println(s.getMyGroups());
        System.out.println(s.getRecomendedGroups());;
        //s.scrieMesaj(1,"Ce faceti");
//        System.out.println(s.mesaje(1));
//        System.out.println(s.getActivitatiZiCurenta());
//        System.out.println(s.getActivitatiToateZilele());
//        System.out.println(s.getNote());
//        System.out.println(s.getNotaCurs(2));
        System.out.println(s.getCursId(14));
        //s.createActivitateNouaGrup(1,"Relax");
        System.out.println(s.getActivitate(1));
        //System.out.println(s.getMyActivities(1));
        //s.inscriereCurs(5);
        //s.inscriereActivitate(12);
        //System.out.println(s.getCursuri());
        //System.out.println(s.getAllCurs());
        //System.out.println(s.getActivitate(1));
        //s.joinGroup(2);
        //s.createActivitateNouaGrup(2,"Practice");
        //System.out.println(s.getParticipants(1));
        //System.out.println(s.getNote());
        //System.out.println(s.getActivitatiToateZilele());
        //s.downloadActivitatiToateZilele();
        //s.inscriereCurs("Programare Orientata pe Obiecte");
        //System.out.println(s.cautareCurs("de"));
        //System.out.println(s.getMyGroups());
        //System.out.println(s.getRecomendedGroups());
        //Table t=s.getMyGroups();
        //System.out.println(s.mesaje(1));
        //s.deleteAllGroups();
        //s.joinGroup(3);
        //s.leaveGroup(3);
    }
    private static void testLogare()
    {
        LogareBackend l=new LogareBackend("ion.popescu","password123");
        System.out.println(l.getTip());
        if(l.valid()) {
            //System.out.println(l.getId()+1);
            //id este l.getId() -- foloseste pentru a accesa in utilizator
            switch (l.getTip()) {
                case "Student":
                    //mergi pe interfata de student
                case "Profesor":
                    //mergi pe interfata de profesor
                    break;
                case "Administrator":
                    //mergi pe interfata de administrator
                    break;
                case "Super_administrator":
                    //mergi pe interfata de superadministrator
                    break;
                default:
                    System.out.println("Nu exista acest tip");
                    break;
            }
        }
        else System.out.println("Username sau parola gresite.");

    }
    private static void testAdmin()
    {
        AdminBackend a=new AdminBackend(23);
        System.out.println(a.getAllStudents(1));
        System.out.println(a.getAllProfesors(1));
        System.out.println(a.getUtilizator(4));
        System.out.println(a.cautaUtilizator("Po","Bi"));

        //a.addUtilizator("Profesor","1234","Marian","Uzbekistan","0712345633","mUzi@gmail.com","-","3979","xxmuxx","1234");
        //a.deleteUtilizator(30);
        a.deleteUtilizator(16);
        a.changeNume(22,"Mosulescu");
        a.changePrenume(22,"Aurel");
        System.out.println(a.cautaCurs("Baze de date"));
        //a.inscrieProfesor(20,);
        System.out.println(a.cautaUtilizator("Serban","Florin"));
        System.out.println(a.getAllProfesors());
        System.out.println(a.getAllUtilizatori());
        System.out.println(a.getAllStudents());
        // 4 Student   1234567890126  Dumitrescu  Alex      0745678901     alex.dumitrescu@student.ro  RO49AAAA1B31007593840003  1004
        a.changeCNP(4, "1234567890126");
        a.changeEmail(4, "alex.dumitrescu@student.ro");
        a.changeContIBAN(4, "RO49AAAA1B31007593840003");
        a.changeNumarTelefon(4, "0745678901");
        a.changeNume(4, "Dumitrescu");
        a.changePrenume(4, "Alex");
        a.changeNumarContact(4, 1004);
        System.out.println(a.cautaUtilizator("Stefan","Marina"));

        SuperAdminBackend s=new SuperAdminBackend(27);
        s.changeNume(24,"Nedelcu");
        System.out.println(s.getAllUtilizatori());

        System.out.println(a.getAllCursuri());
        //a.inscrieProfesor(20,4,"Colocviu",100);

    }
    public static void main(String[] args) {
        SuperAdminBackend s=new SuperAdminBackend(26);
        s.addAdmin("1234","Marian","Uzbekistan","0712345633","mUzi@gmail.com","-","3979","xxmuxx","1234");
        //testProfesor();
        //testAdmin();
        testStudent();
        //testLogare();
    }
}
