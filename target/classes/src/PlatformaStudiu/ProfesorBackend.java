package PlatformaStudiu;

import java.io.*;
import java.util.List;


public class ProfesorBackend extends Utilizator{

    ProfesorBackend(){super();}
    ProfesorBackend(int id) {super(id);}
    public Table getActivitatiZiCurenta()//nume curs, ponderea
    {
        //'2024-07-25'
        String sqlStatement="SELECT activitate.id, curs.descriere, activitate.tip, pondere\n" +
                "FROM calendar\n" +
                "JOIN activitate ON calendar.activitate_id = activitate.id\n" +
                "JOIN activitate_profesor ON activitate.id = activitate_profesor.activitate_id\n" +
                "JOIN profesor ON activitate_profesor.profesor_id = profesor.id\n" +
                "join curs on activitate.curs_id = curs.id\n" +
                "WHERE profesor_id = "+this.getId()+ " and date(current_date())=date(calendar.data_inceput)\n";
        Table ret=new Table(sqlStatement);
        return ret;
    }
    public Table getActivitatiToateZilele()
    {

        String sqlStatement = "SELECT activitate.id, curs.descriere, activitate.tip, pondere " +
                "FROM calendar " +
                "JOIN activitate ON calendar.activitate_id = activitate.id " +
                "JOIN activitate_profesor ON activitate.id = activitate_profesor.activitate_id " +
                "JOIN profesor ON activitate_profesor.profesor_id = profesor.id " +
                "JOIN curs ON activitate.curs_id = curs.id " +
                "WHERE profesor_id = " + this.getId() + " " +
                "GROUP BY activitate.id, curs.descriere, activitate.tip, pondere;";

        Table ret=new Table(sqlStatement);
        return ret;
    }
    public Table getCatalog()
    {
        String sqlStatement="SELECT\n" +
                "    curs.id,curs.descriere AS nume_curs,\n" +
                "    utilizator.nume AS student_nume,\n" +
                "    utilizator.prenume AS student_prenume,\n" +
                "    SUM(activitate_nota.nota * activitate.pondere / 100) AS nota_finala_curs\n" +
                "FROM\n" +
                "    curs\n" +
                "JOIN\n" +
                "    activitate ON activitate.curs_id = curs.id\n" +
                "JOIN\n" +
                "    activitate_nota ON activitate.id = activitate_nota.activitate_id\n" +
                "JOIN\n" +
                "    student ON activitate_nota.student_id = student.id\n" +
                "JOIN\n" +
                "    activitate_profesor ON activitate.id = activitate_profesor.activitate_id\n" +
                "JOIN\n" +
                "    utilizator ON utilizator.id = student.id\n" +
                "WHERE\n" +
                "    curs.id IN (\n" +
                "        SELECT DISTINCT curs_id\n" +
                "        FROM activitate\n" +
                "        JOIN activitate_profesor ON activitate.id = activitate_profesor.activitate_id\n" +
                "        WHERE activitate_profesor.profesor_id = "+this.getId()+"\n" +
                "    )\n" +
                "GROUP BY\n" +
                "    curs.id,\n" +
                "    curs.descriere,\n" +
                "    utilizator.id,\n" +
                "    utilizator.nume,\n" +
                "    utilizator.prenume\n" +
                "ORDER BY\n" +
                "    curs.id,\n" +
                "    utilizator.id;";
        Table ret=new Table(sqlStatement);
        List<String> s=ret.getHeader();
        s.removeFirst();
        s.addFirst("nume_curs");
        ret.setHeader(s);
        return ret;
    }
    public void downloadCatalog()
    {
        String sqlStatement="SELECT\n" +
                "    curs.descriere AS nume_curs,\n" +
                "    utilizator.nume AS student_nume,\n" +
                "    utilizator.prenume AS student_prenume,\n" +
                "    SUM(activitate_nota.nota * activitate.pondere / 100) AS nota_finala_curs\n" +
                "FROM\n" +
                "    curs\n" +
                "JOIN\n" +
                "    activitate ON activitate.curs_id = curs.id\n" +
                "JOIN\n" +
                "    activitate_nota ON activitate.id = activitate_nota.activitate_id\n" +
                "JOIN\n" +
                "    student ON activitate_nota.student_id = student.id\n" +
                "JOIN\n" +
                "    activitate_profesor ON activitate.id = activitate_profesor.activitate_id\n" +
                "JOIN\n" +
                "    utilizator ON utilizator.id = student.id\n" +
                "WHERE\n" +
                "    curs.id IN (\n" +
                "        SELECT DISTINCT curs_id\n" +
                "        FROM activitate\n" +
                "        JOIN activitate_profesor ON activitate.id = activitate_profesor.activitate_id\n" +
                "        WHERE activitate_profesor.profesor_id = "+this.getId()+"\n" +
                "    )\n" +
                "GROUP BY\n" +
                "    curs.id,\n" +
                "    curs.descriere,\n" +
                "    utilizator.id,\n" +
                "    utilizator.nume,\n" +
                "    utilizator.prenume\n" +
                "ORDER BY\n" +
                "    curs.id,\n" +
                "    utilizator.id;";
        PrintStream originalOut = System.out;
        try {
            // Redirect System.out to a file
            FileOutputStream fileOut = new FileOutputStream("catalog.txt");
            PrintStream filePrintStream = new PrintStream(fileOut);

            // Redirect System.out
            System.setOut(filePrintStream);

            // Execute the query
            Query q = new Query();
            q.init();
            q.doQuery(sqlStatement);
            q.closeConnection();

            // Restore the original System.out
            System.setOut(originalOut);

            // Close resources
            filePrintStream.close();
            fileOut.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        finally {
            System.setOut(originalOut);
        }
    }
    /**
     * 0 <= newProcentage <= 100
     * poate schimba procentajul activitatii doar daca preda acea activitate
     */
    public void changeProcentageActivitateTo(int activitate_id, int newProcentage)
    {
        //activitate_id se da corect
        String sqlStatement="UPDATE activitate\n" +
                "JOIN activitate_profesor ON activitate_profesor.activitate_id = activitate.id\n" +
                "SET activitate.pondere = "+newProcentage+"\n" +
                "WHERE activitate.id = "+activitate_id+" AND activitate_profesor.profesor_id = "+this.getId()+";\n";
        Query q=new Query();
        q.init();
        q.doUpdate(sqlStatement);
        q.closeConnection();
    }
    public void downloadActivitatiToateZilele()
    {
        String sqlStatement="SELECT\n" +
                "    curs.descriere AS nume_curs,\n" +
                "    tip,\n" +
                "    pondere,\n" +
                "    data_inceput,\n" +
                "    data_sfarsit\n" +
                "FROM\n" +
                "    activitate_profesor\n" +
                "    JOIN activitate ON activitate_profesor.activitate_id = activitate.id\n" +
                "    JOIN calendar ON activitate.id = calendar.activitate_id\n" +
                "    JOIN curs on activitate.curs_id=curs.id\n" +
                "WHERE\n" +
                "    profesor_id ="+this.getId()+";";
        PrintStream originalOut = System.out;
        try {
            // Redirect System.out to a file
            FileOutputStream fileOut = new FileOutputStream("activitati.txt");
            PrintStream filePrintStream = new PrintStream(fileOut);

            // Redirect System.out
            System.setOut(filePrintStream);

            // Execute the query
            Query q = new Query();
            q.init();
            q.doQuery(sqlStatement);
            q.closeConnection();

            // Restore the original System.out
            System.setOut(originalOut);

            // Close resources
            filePrintStream.close();
            fileOut.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        finally {
            System.setOut(originalOut);
        }
    }
    public void notareStudent(int activitate_id, int student_id,int nota)
    {
        Query query=new Query();
        query.init();
        String sqlStmnt1="select nota from activitate_nota where activitate_id="+activitate_id+" and student_id="+student_id+";";
        String checkNull=query.getAtr(sqlStmnt1);
        //System.out.println(checkNull);
        String sqlStmnt2;
        if(checkNull.equals("No results."))
            sqlStmnt2="insert into activitate_nota  (activitate_id,student_id,nota) values\n" +
                    "("+activitate_id+","+student_id+","+nota+");";
        else
            sqlStmnt2="update activitate_nota\n" +
                    "set nota="+nota+"\n" +
                    "where activitate_id="+activitate_id+" and\n" +
                    "student_id="+student_id+";";
        query.doUpdate(sqlStmnt2);
        query.closeConnection();
    }
    public void notareStudent(int activitate_id,String student_nume,String student_prenume,int nota)
    {
        Query query=new Query();
        query.init();
        String sqlStmnt="Select id from utilizator where nume='"+student_nume+"' and prenume='"+student_prenume+"';";
        String checkNull=query.getAtr(sqlStmnt);
        if(checkNull.equals("No results."))
        {
            System.out.println("Nu exista acest student");
        }
        else {

            notareStudent(activitate_id,Integer.parseInt(checkNull),nota);
        }
        query.closeConnection();
    }
    public void adaugareActivitateNoua(String activitate_tip,int pondere,String curs_nume)
    {
        Query query=new Query();
        query.init();
        String sqlCheckCurs="select id from curs where descriere='"+curs_nume+"'";
        String checkCurs=query.getAtr(sqlCheckCurs);//are id de la curs
        if(checkCurs.equals("No results.")) System.out.println("Nu exista acest curs");
        else
        {
            String sqlStmnt="insert into activitate (curs_id, tip, pondere) VALUES\n" +
                    "("+checkCurs+",'"+activitate_tip+"',"+pondere+");";
            query.doUpdate(sqlStmnt);
        }
        query.closeConnection();
    }
    public Table getStudentiActivitate(int activitate_id)
    {
        return new Table("\n" +
                "select utilizator.id as student_id,nume,prenume,nota from activitate_profesor\n" +
                "join activitate_nota on activitate_nota.activitate_id=activitate_profesor.activitate_id\n" +
                "join student on activitate_nota.student_id = student.id\n" +
                "join utilizator on student.id = utilizator.id\n" +
                "join activitate on activitate_nota.activitate_id = activitate.id\n" +
                "join curs on activitate.curs_id = curs.id\n" +
                "where profesor_id="+this.getId()+" and activitate_nota.activitate_id="+activitate_id+";");
    }

    /**
     * start si end sunt 2 vectori de lungime 6 care reprezinta
     * minut, ora, ziua, luna, anul
     * indexare de la 1 la 5
     * in aceasta ordine
     * */
    public void programareActivitateExistenta(int activitate_id,int start[],int end[])
    {
        Query query = new Query();
        query.init();

        start[0]=0;
        end[0]=0;
        String startStr = String.format("%04d-%02d-%02d %02d:%02d:%02d", start[5], start[4], start[3], start[2], start[1], start[0]);
        String endStr = String.format("%04d-%02d-%02d %02d:%02d:%02d", end[5], end[4], end[3], end[2], end[1], end[0]);

        String sqlCheckActivitate = "select id from activitate where id=" + activitate_id;
        String checkActivitate = query.getAtr(sqlCheckActivitate); // are id-ul activității
        if (checkActivitate.equals("No results.")) {
            System.out.println("Activitatea nu există.");
        } else {
            // Inserăm în calendar
            String sqlStmnt = "insert into calendar (activitate_id, data_inceput, data_sfarsit) VALUES "
                    + "(" + activitate_id + ", '" + startStr + "', '" + endStr + "');";
            query.doUpdate(sqlStmnt);
            System.out.println("Programarea a fost inserată cu succes!");
        }

        query.closeConnection();
    }
    public Table getCursuri()
    {
        return new Table("select c.descriere,a.tip as 'activitate',a.id as 'activitate_id' from curs as c join\n" +
                "    activitate as a on a.curs_id=c.id\n" +
                "join activitate_profesor on a.id = activitate_profesor.activitate_id\n" +
                "join profesor p on p.id=activitate_profesor.profesor_id\n" +
                "where profesor_id="+this.getId()+";\n");
    }
    public Table getCalendar(int activitate_id)
    {
        String sqlStmnt="SELECT\n" +
                "    MINUTE(data_inceput) AS minut,\n" +
                "    HOUR(data_inceput) AS ora,\n" +
                "    DAY(data_inceput) AS ziua,\n" +
                "    MONTH(data_inceput) AS luna,\n" +
                "    YEAR(data_inceput) AS anul\n" +
                "FROM calendar\n" +
                "WHERE activitate_id = "+activitate_id+";";
        return new Table(sqlStmnt);
    }

    public String[] getHours(int activitate_id,int day,int month,int year)
    {
        String[] rez = new String[2];
        String sqlStmnt1="select CONCAT(LPAD(HOUR(data_inceput), 2, '0'), ':', LPAD(MINUTE(data_inceput), 2, '0')) AS time_formatted\n from calendar where activitate_id = "+activitate_id+" and\n" +
                "day(data_inceput)="+day+" and\n" +
                "month(data_inceput)="+month+" and\n" +
                "year(data_inceput)="+year+";";
        String sqlStmnt2 = "SELECT CONCAT(LPAD(HOUR(data_sfarsit), 2, '0'), ':', LPAD(MINUTE(data_sfarsit), 2, '0')) AS time_formatted " +
                "FROM calendar " +
                "WHERE activitate_id = " + activitate_id + " AND " +
                "DAY(data_sfarsit) = " + day + " AND " +
                "MONTH(data_sfarsit) = " + month + " AND " +
                "YEAR(data_sfarsit) = " + year + ";";

        Query query = new Query();
        query.init();
        rez[0]= query.getAtr(sqlStmnt1);
        rez[1]=query.getAtr(sqlStmnt2);
        query.closeConnection();
        return rez;
    }

    public Table getOnlyCursuri()
    {
        return new Table("select distinct curs.id,curs.descriere from curs join\n" +
                "activitate on curs.id = activitate.curs_id\n" +
                "join activitate_profesor on activitate.id = activitate_profesor.activitate_id\n" +
                "where profesor_id="+this.getId()+";");
    }

    public Table getCatalog2()
    {
        return new Table("SELECT\n" +
                "    curs.descriere AS nume_curs,\n" +
                "    utilizator.nume AS student_nume,\n" +
                "    utilizator.prenume AS student_prenume,\n" +
                "    SUM(activitate_nota.nota * activitate.pondere / 100) AS nota_finala_curs\n" +
                "FROM\n" +
                "    curs\n" +
                "JOIN\n" +
                "    activitate ON activitate.curs_id = curs.id\n" +
                "JOIN\n" +
                "    activitate_nota ON activitate.id = activitate_nota.activitate_id\n" +
                "JOIN\n" +
                "    student ON activitate_nota.student_id = student.id\n" +
                "JOIN\n" +
                "    activitate_profesor ON activitate.id = activitate_profesor.activitate_id\n" +
                "JOIN\n" +
                "    utilizator ON utilizator.id = student.id\n" +
                "WHERE\n" +
                "    curs.id IN (\n" +
                "        SELECT DISTINCT curs_id\n" +
                "        FROM activitate\n" +
                "        JOIN activitate_profesor ON activitate.id = activitate_profesor.activitate_id\n" +
                "        WHERE activitate_profesor.profesor_id = "+this.getId()+"\n" +
                "    )\n" +
                "GROUP BY\n" +
                "    curs.id,\n" +
                "    curs.descriere,\n" +
                "    utilizator.id,\n" +
                "    utilizator.nume,\n" +
                "    utilizator.prenume\n" +
                "ORDER BY\n" +
                "    curs.id,\n" +
                "    utilizator.id;\n");
    }
}