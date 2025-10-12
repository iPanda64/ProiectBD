package PlatformaStudiu;

import java.io.*;
import java.util.List;

public class AdminBackend extends Utilizator{
    AdminBackend(){super();}
    AdminBackend(int id){super(id);}

    public Table cautaUtilizator(String nume,String prenume)
    {
        String sqlStatement = "SELECT id,nume,prenume FROM utilizator " +
                "WHERE nume LIKE '%" + nume + "%' " +
                "AND prenume LIKE '%" + prenume + "%' " +
                "AND tip NOT IN ('Administrator', 'Super-Administrator');";
        return new Table(sqlStatement);
    }
    public Table cautaCurs(String descriere)
    {
        return new Table("SELECT * FROM curs WHERE descriere LIKE '%" + descriere + "%';");

    }
    public Table getAllUtilizatori()
    {
        return new Table("SELECT id,nume,prenume FROM utilizator WHERE tip NOT IN ('Administrator', 'Super-Administrator');");
    }
    public Table getUtilizator(int utilizator_id)
    {
        return new Table("SELECT * FROM utilizator WHERE id='" + utilizator_id + "';");
    }
    public Table getAllProfesors()
    {
        return new Table("select id,nume,prenume from utilizator where tip = 'Profesor';");
    }
    public Table getAllStudents()
    {
        return new Table("select id,nume,prenume from utilizator where tip = 'Student';");
    }
    public void changeCNP(int id,String CNP) {
        Query q=new Query();
        q.init();
        q.doUpdate("UPDATE utilizator\n" +
                "SET cnp = '"+CNP+"'\n" +
                "WHERE id = "+id+";");
        q.closeConnection();
    }
    public void changeNume(int id, String nume) {
        Query q = new Query();
        q.init();
        q.doUpdate("UPDATE utilizator SET nume = '" + nume + "' WHERE id = " + id + ";");
        q.closeConnection();
    }
    public void changePrenume(int id, String prenume) {
        Query q = new Query();
        q.init();
        q.doUpdate("UPDATE utilizator SET prenume = '" + prenume + "' WHERE id = " + id + ";");
        q.closeConnection();
    }
    public void changeNumarTelefon(int id, String numarTelefon) {
        Query q = new Query();
        q.init();
        q.doUpdate("UPDATE utilizator SET numar_telefon = '" + numarTelefon + "' WHERE id = " + id + ";");
        q.closeConnection();
    }
    public void changeEmail(int id, String email) {
        Query q = new Query();
        q.init();
        q.doUpdate("UPDATE utilizator SET email = '" + email + "' WHERE id = " + id + ";");
        q.closeConnection();
    }
    public void changeContIBAN(int id, String contIban) {
        Query q = new Query();
        q.init();
        q.doUpdate("UPDATE utilizator SET cont_IBAN = '" + contIban + "' WHERE id = " + id + ";");
        q.closeConnection();
    }
    public void changeNumarContact(int id, int numarContract) {
        Query q = new Query();
        q.init();
        q.doUpdate("UPDATE utilizator SET numar_contract  = '" + numarContract + "' WHERE id = " + id + ";");
        q.closeConnection();
    }
    public Table getAllCursuri()
    {
        return new Table("select * from curs;");
    }
    public Table getAllStudents(int curs_id)
    {
        return new Table("select id,nume,prenume from utilizator\n" +
                "join curs_student on student_id=id where\n" +
                "curs_id="+curs_id+";");
    }
    public Table getAllProfesors(int curs_id)
    {
        return new Table("select distinct utilizator.id,nume,prenume from utilizator\n" +
                "join activitate_profesor on profesor_id=id\n" +
                "join activitate on activitate_profesor.activitate_id = activitate.id where\n" +
                "curs_id="+curs_id+";");
    }
    public void inscrieProfesor(int profesor_id,int curs_id,String activitate_nume)
    {
        Query q=new Query();
        q.init();
        int pondere=0;
        String sqlStmnt1="insert into activitate (curs_id, tip, pondere) values\n" +
                "("+curs_id+" , '"+activitate_nume+"' , "+pondere+");";
        q.doUpdate(sqlStmnt1);
        String activitate_id=q.getAtr("select last_insert_id();");
        //String activitate_id=q.getAtr("select id from activitate where curs_id = " + curs_id + " and tip = '"+activitate_nume+"' and pondere = "+pondere+";");
        String sqlStmnt2="insert into activitate_profesor (activitate_id, profesor_id)\n" +
                "values ("+Integer.parseInt(activitate_id)+" , "+profesor_id+");";
        q.doUpdate(sqlStmnt2);
        q.closeConnection();
    }
    public Table getActivitate(int curs_id)
    {
        return new Table("\n" +
                "select activitate.id,tip from activitate where curs_id="+curs_id+";");
    }
    public void createActivitateNoua(int curs_id,String activitate_nume)
    {
        Query q=new Query();
        q.init();
        String sqlStmnt="insert into activitate(curs_id, tip, pondere) VALUES \n" +
                "("+curs_id+",'"+activitate_nume+"',0);";
        q.doUpdate(sqlStmnt);
        String activitateId=q.getAtr("select last_insert_id();");
        q.closeConnection();
    }
    public void inscriereProfesor(int profesor_id,int activitate_id)
    {
        Query q=new Query();
        q.init();
        String sqlStmnt2="insert into activitate_profesor (activitate_id, profesor_id)\n" +
                "values ("+activitate_id+" , "+profesor_id+");";
        q.doUpdate(sqlStmnt2);
        q.closeConnection();
    }
    public void deleteUtilizator(int utilizator_id)
    {
        Query q=new Query();
        q.init();
        String tip=q.getAtr("select tip from utilizator where id = " + utilizator_id + ";");
        if(tip.equals("Profesor"))
        {
            q.doUpdate("delete from profesor where id= "+utilizator_id+";");
        }
        else
        {
            q.doUpdate("delete from student where id= "+utilizator_id+";");
        }
        q.doUpdate("delete from utilizator where id= "+utilizator_id+";");
        q.closeConnection();
    }
    protected String addUtilizator(String tip,String CNP,String nume,String prenume,String numar_telefon,String email,String cont_Iban,String numar_contact,String username,String parola)
    {
        Query q=new Query();
        q.init();
        q.doUpdate("insert into utilizator(tip, CNP, nume, prenume, numar_telefon, email, cont_IBAN, numar_contract)\n" +
                "values('"+tip+"','"+CNP+"','"+nume+"','"+prenume+"','"+numar_telefon+"','"+email+"','"+cont_Iban+"',"+numar_contact+");");
        String id=q.getAtr("select last_insert_id();");
        q.doUpdate("insert into autentificare(id,username,parola) values('"+id+"','"+username+"','"+parola+"');");
        q.closeConnection();
        return id;
    }
    public void addProfesor(String CNP,String nume,String prenume,String numar_telefon,String email,String cont_Iban,String numar_contact,String username,String parola,String nr_ore_min,String nr_ore_max,String departament)
    {
        String id=this.addUtilizator("Profesor",CNP,nume,prenume,numar_telefon,email,cont_Iban,numar_contact,username,parola);
        Query q=new Query();
        q.init();
        System.out.println(id);
        q.doUpdate("insert into profesor (id,nr_ore_min,nr_ore_max,departament) values("+id+","+nr_ore_min+","+nr_ore_max+",'"+departament+"');");
        q.closeConnection();
    }
    public void addStudent(String CNP,String nume,String prenume,String numar_telefon,String email,String cont_Iban,String numar_contact,String username,String parola,String an,String nr_ore)
    {
        String id=this.addUtilizator("Student",CNP,nume,prenume,numar_telefon,email,cont_Iban,numar_contact,username,parola);
        Query q=new Query();
        q.init();
        System.out.println(id);
        //String id=q.getAtr("select last_insert_id();");
        q.doUpdate("insert into student (id,an,nr_ore) values("+id+","+an+","+nr_ore+");");
        q.closeConnection();
    }

    public Table getOnlyCursuri(int profesor_id)
    {
        ProfesorBackend p=new ProfesorBackend(profesor_id);
        return p.getOnlyCursuri();
    }
}