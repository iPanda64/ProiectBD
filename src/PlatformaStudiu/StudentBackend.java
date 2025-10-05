package PlatformaStudiu;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StudentBackend extends Utilizator{
    StudentBackend(){super();
    this.deleteAllGroups();}
    StudentBackend(int id){super(id);
    this.deleteAllGroups();}
    public Table getNote()
    {
        String sqlStatement="SELECT\n" +
                "    curs.id AS curs_id,\n" +
                "    curs.descriere AS curs_nume,\n" +
                "    SUM(activitate_nota.nota * activitate.pondere / 100) AS nota_finala_curs\n" +
                "FROM\n" +
                "    activitate_nota\n" +
                "JOIN\n" +
                "    activitate ON activitate_nota.activitate_id = activitate.id\n" +
                "JOIN\n" +
                "    curs ON activitate.curs_id = curs.id\n" +
                "WHERE\n" +
                "    activitate_nota.student_id = "+this.getId()+"\n" +
                "GROUP BY\n" +
                "    curs.id, curs.descriere\n" +
                "ORDER BY\n" +
                "    curs.id;";
        return new Table(sqlStatement);
    }
    public String getCursId(int activitate_id)
    {
        Query q=new Query();
        q.init();
        String rez=q.getAtr("select curs_id from activitate where id="+activitate_id+";");
        q.closeConnection();
        return rez;
    }
    public String getNotaCurs(int curs_id)
    {
        Table curs = getNote();
        float rez=0;
        String finaly=String.valueOf(rez);
        for(int i=0;i<curs.getRowCount();++i)
        {
            if(Integer.parseInt(curs.getData(i,0))==curs_id)
            {
                rez=Float.parseFloat(curs.getData(i,2));
                finaly=String.valueOf(rez);
                break;
            }
        }
        return finaly;
    }
    public Table getActivitatiToateZilele()
    {
        String sqlStatement="select activitate.id,curs.descriere,tip,nota from activitate_nota join activitate on activitate_nota.activitate_id = activitate.id\n" +
                "join curs on activitate.curs_id = curs.id\n" +
                "where student_id="+this.getId()+";";
         return new Table(sqlStatement);

    }
    public Table getActivitatiZiCurenta()//nume curs, ponderea
    {
        //'2024-07-25'
        String sqlStatement="SELECT activitate.id, curs.descriere, activitate.tip, nota\n" +
                "FROM calendar\n" +
                "JOIN activitate ON calendar.activitate_id = activitate.id\n" +
                "JOIN activitate_nota ON activitate.id = activitate_nota.activitate_id\n" +
                "JOIN student ON activitate_nota.student_id = student.id\n" +
                "join curs on activitate.curs_id = curs.id\n" +
                "WHERE student_id = "+this.getId()+ " and date(current_date())=date(calendar.data_inceput)\n";
        Table ret=new Table(sqlStatement);
        return ret;
    }
    public void downloadActivitatiToateZilele()
    {
        String sqlStatement="SELECT\n" +
                "    curs.descriere,\n" +
                "    tip,\n" +
                "    pondere,\n" +
                "    data_inceput,\n" +
                "    data_sfarsit\n" +
                "FROM\n" +
                "    activitate_nota\n" +
                "    JOIN activitate ON activitate_nota.activitate_id = activitate.id\n" +
                "    JOIN calendar ON activitate.id = calendar.activitate_id\n" +
                "    JOIN curs on activitate.curs_id=curs.id\n" +
                "WHERE\n" +
                "    student_id ="+this.getId()+";";
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
    public void inscriereCurs(String curs_name)
    {
        Query query=new Query();
        query.init();
        String sqlStmnt="select * from curs_student join curs on curs_student.curs_id = curs.id\n" +
                "where curs.descriere='"+curs_name+"';";
        Table checkMaxStudenti=new Table(sqlStmnt);
        if(checkMaxStudenti.getColCount()>Integer.parseInt(checkMaxStudenti.getData(5,1)))
            System.out.println("Nu mai sunt locuri la acest curs");
        else
        {
            String sqlStmnt2="select * from curs_student cs join curs as c on cs.curs_id = c.id\n" +
                    "join student as s on s.id=cs.student_id\n" +
                    "where s.id="+this.getId()+" and c.descriere='"+curs_name+"';";
            String alreadyInCheck=query.getAtr(sqlStmnt2);
            if(alreadyInCheck.equals("No results."))
            {
                String cursId=query.getAtr("select id from curs where descriere='"+curs_name+"';");
                query.doUpdate("insert into curs_student(curs_id, student_id) values ("+cursId+","+this.getId()+");");
            }
            else
                System.out.println("Deja sunteti inscrisi la acest curs");
        }
        query.closeConnection();
    }
    public void inscriereCurs(int curs_id)
    {
        Query query=new Query();
        query.init();
        String sqlStmnt="select * from curs_student join curs on curs_student.curs_id = curs.id\n" +
                "where curs.id='"+curs_id+"';";
        Table checkMaxStudenti=new Table(sqlStmnt);
        System.out.println(checkMaxStudenti);
        if(checkMaxStudenti.getColCount()>Integer.parseInt(checkMaxStudenti.getData(0,4)))
            System.out.println("Nu mai sunt locuri la acest curs");
        else
        {
            String sqlStmnt2="select * from curs_student cs join curs as c on cs.curs_id = c.id\n" +
                    "join student as s on s.id=cs.student_id\n" +
                    "where s.id="+this.getId()+" and c.id='"+curs_id+"';";
            String alreadyInCheck=query.getAtr(sqlStmnt2);
            if(alreadyInCheck.equals("No results."))
            {
                query.doUpdate("insert into curs_student(curs_id, student_id) values ("+curs_id+","+this.getId()+");");
            }
            else
                System.out.println("Deja sunteti inscrisi la acest curs");
        }
        query.closeConnection();
    }
    /**
     * returneaza o tabela care contine toate cursurile care au in descriere
     * stringul primit ca input
     */
    public Table cautareCurs(String curs_name)
    {

        String sqlStmnt="select descriere from curs;";
        Table allCursNames=new Table(sqlStmnt);
        LinkedList<String> found=new LinkedList<>();
        for(int i=0;i<allCursNames.getColCount();i++)
        {
            if(allCursNames.getData(i,0).contains(curs_name))
                found.add(allCursNames.getData(i,0));
        }
        Table finaly=new Table();
        ArrayList<String>s=new ArrayList<>(1);
        s.add("cursuri");
        finaly.setHeader(s);
        finaly.setColCount(1);
        finaly.setRowCount(found.size());
        for (String course : found) {
            ArrayList<String> row = new ArrayList<>(1);
            row.add(course);
            finaly.addRow(row);
        }

        return finaly;
    }
    public Table getEveryCurs()
    {
        return new Table("select * from curs;");
    }
    public Table getAllCurs()
    {
        return new Table("select id, descriere from curs_student join curs on curs_student.curs_id = curs.id\n" +
                "where student_id="+this.getId()+";");
    }
    public Table getActivitate(int curs_id)
    {
        return new Table("\n" +
                "select activitate.id,tip from activitate where curs_id="+curs_id+";");
    }
    public Table getMyActivities(int curs_id)
    {
        return new Table("select activitate_id, tip from activitate_nota\n" +
                "join activitate on activitate_nota.activitate_id = activitate.id\n" +
                "where curs_id="+curs_id+" and student_id="+this.getId()+";");
    }
    public Table getCalendar(int activitate_id)
    {
        String sqlStmntIsInActivity="SELECT CASE\n" +
                "           WHEN EXISTS (SELECT 1 FROM activitate_nota WHERE student_id = "+this.getId()+" AND activitate_id = "+activitate_id+")\n" +
                "           THEN 1\n" +
                "           ELSE 0\n" +
                "       END AS record_exists;";
        String sqlStmnt="SELECT\n" +
                "    MINUTE(data_inceput) AS minut,\n" +
                "    HOUR(data_inceput) AS ora,\n" +
                "    DAY(data_inceput) AS ziua,\n" +
                "    MONTH(data_inceput) AS luna,\n" +
                "    YEAR(data_inceput) AS anul\n" +
                "FROM calendar\n" +
                "WHERE activitate_id = "+activitate_id+";";
        Query query=new Query();
        query.init();
        int isInActivity=Integer.parseInt(query.getAtr(sqlStmntIsInActivity));
        if(isInActivity==0)this.inscriereActivitate(activitate_id);
        return new Table(sqlStmnt);
    }
    private boolean hasActivity(String datetime1, String datetime2)
    {
        String sqlStmnt="call CheckStudentActivity("+this.getId()+" , 'datetime1' , 'datetime2') ;";
        Query q=new Query();
        q.init();
        String s=q.getAtr(sqlStmnt);
        q.closeConnection();
        if(s.equals("No results."))return false;
        return true;
    }
    public void inscriereActivitate(int activitate_id)
    {
        Query query=new Query();
        query.init();
        String sqlCheckIfIsInCurs="SELECT *\n" +
                "FROM curs_student\n" +
                "JOIN activitate ON activitate.curs_id = curs_student.curs_id\n" +
                "WHERE curs_student.student_id = "+this.getId()+"\n" +
                "AND activitate.id = "+activitate_id+";";
        String checkIfIsInCurs = query.getAtr(sqlCheckIfIsInCurs);
        if(checkIfIsInCurs.equals("No results."))
        {
            System.out.println("Studentul nu e inscris la cursul acestei activitati");
            query.closeConnection();
            return;
        }
        else {
            String sqlStmntOverlapCheck = "\n" +
                    "SELECT\n" +
                    "    CASE\n" +
                    "        WHEN EXISTS (\n" +
                    "            SELECT 1\n" +
                    "            FROM calendar c1\n" +
                    "            JOIN activitate_nota an ON c1.activitate_id = an.activitate_id\n" +
                    "            WHERE an.student_id = " + this.getId() + "\n" +
                    "            AND EXISTS (\n" +
                    "                SELECT 1\n" +
                    "                FROM calendar c2\n" +
                    "                WHERE c2.activitate_id = " + activitate_id + "\n" +
                    "                AND (\n" +
                    "                    c1.data_inceput < c2.data_sfarsit\n" +
                    "                    AND c1.data_sfarsit > c2.data_inceput\n" +
                    "                )\n" +
                    "            )\n" +
                    "        ) THEN TRUE\n" +
                    "        ELSE FALSE\n" +
                    "    END AS overlap_exists;\n";
            boolean isOverlap = false;
            String checkOverlap = query.getAtr(sqlStmntOverlapCheck);
            if (checkOverlap.equals("No results.") || checkOverlap.equals("0")) isOverlap = false;
            else isOverlap = true;
            if (isOverlap) {
                query.closeConnection();
                System.out.println("Overlap exists");
                return;
            }
            String sqlStmnt = "insert into activitate_nota(activitate_id, student_id, nota)\n" +
                    "values (" + activitate_id + "," + this.getId() + ",0);";
            query.doUpdate(sqlStmnt);
            query.closeConnection();
        }
    }
    public Table getCursuri()
    {
        return new Table("select c.descriere,a.tip as 'activitate',a.id as 'activitate_id' from curs as c join\n" +
                "    activitate as a on a.curs_id=c.id\n" +
                "join activitate_nota on a.id = activitate_nota.activitate_id\n" +
                "join student p on p.id=activitate_nota.student_id\n" +
                "where student_id="+this.getId()+";\n");
    }
    /**
     * id il iei cu Integer.parseInt(t.getData(i,0).substring(7).trim());
     * */
    public Table getMyGroups()
    {
        Table table=new Table("SELECT CONCAT('grupul ', grup.id) AS grup_name\n" +
                "FROM grup\n" +
                "JOIN grup_studenti ON grup.id = grup_studenti.grup_id\n" +
                "WHERE grup_studenti.student_id = "+this.getId()+";");
        return table;
    }
    /**
     * id il iei cu Integer.parseInt(t.getData(i,0).substring(7).trim());
     * */
    public Table getRecomendedGroups()
    {
        String sql = "SELECT CONCAT('grupul ', grup.id) AS grup_name " +
                "FROM grup " +
                "WHERE grup.id NOT IN ( " +
                "    SELECT grup_id " +
                "    FROM grup_studenti " +
                "    WHERE student_id = " + this.getId() +
                ") " +
                "AND grup.curs_id IN ( " +
                "    SELECT curs_id " +
                "    FROM curs_student " +
                "    WHERE student_id = " + this.getId() +
                ");";
        return new Table(sql);
    }
    public Table mesaje(int grup_id)
    {
        Table t = new Table(
                "SELECT " +
                        "    mesaj.continut, " +
                        "    CASE " +
                        "        WHEN mesaj.student_id = " + this.getId() + " THEN 'Yes' " +
                        "        ELSE 'No' " +
                        "    END AS is_from_student " +
                        "FROM mesaj " +
                        "WHERE grup_id = " + grup_id + ";"
        );
        return t;
    }
    public void joinGroup(int grup_id)
    {
        Query q=new Query();
        q.init();
        String sqlStmnt="INSERT INTO grup_studenti (grup_id, student_id)\n" +
                "SELECT "+grup_id+", "+this.getId()+" -- Replace 1 with the grup_id and 2 with the student_id\n" +
                "WHERE NOT EXISTS (\n" +
                "    SELECT 1\n" +
                "    FROM grup_studenti\n" +
                "    WHERE grup_id = "+grup_id+" AND student_id = "+this.getId()+"\n" +
                ");";
        q.doUpdate(sqlStmnt);
        q.closeConnection();
    }
    public void leaveGroup(int grup_id)
    {
        Query q=new Query();
        q.init();
        String sqlStmnt = "delete from grup_studenti\n" +
                "where grup_id="+grup_id+" and student_id=" + this.getId() + ";";
        q.doUpdate(sqlStmnt);
        q.closeConnection();
    }
    public Table getParticipants(int grup_id)
    {
        return new Table("select u.nume, u.prenume from grup_studenti\n" +
                "join student s on grup_studenti.student_id = s.id\n" +
                "join utilizator u on s.id = u.id\n" +
                " where grup_id="+grup_id+";");
    }
    private void deleteAllGroups()
    {
        Query q = new Query();
        q.init();
        String sqlStmnt1="DELETE FROM grup_activitate\n" +
                "WHERE grup_id IN (SELECT id FROM grup WHERE expira < CURDATE());";
        String sqlStmnt1_5=
                "DELETE FROM grup_studenti\n" +
                "WHERE grup_id IN (SELECT id FROM grup WHERE expira < current_date());\n";
        String sqlStmnt2 = "DELETE FROM grup " +
                "WHERE expira < current_date();";

        q.doUpdate(sqlStmnt1);
        q.doUpdate(sqlStmnt1_5);
        q.doUpdate(sqlStmnt2);
        q.closeConnection();
    }
    //activitate grup  //am modificato
    public void createActivitateNouaGrup(int grup_id,String activitate_nume)
    {
        Query q=new Query();
        q.init();
        String sqlStmntGetCursId="select curs_id from grup where id="+grup_id+";";
        String curs_id=q.getAtr(sqlStmntGetCursId);
        String sqlStmnt="insert into activitate(curs_id, tip, pondere) VALUES \n" +
                "("+curs_id+",'"+activitate_nume+"',null);";
        q.doUpdate(sqlStmnt);
        String activitateId=q.getAtr("select last_insert_id();");
        String sqlJoinActivitate="insert into activitate_nota(activitate_id, student_id, nota) VALUES \n" +
                "("+activitateId+","+this.getId()+",null)";
        String sqlActiGr="insert into grup_activitate(grup_id, activitate_id) VALUES ("+grup_id+","+activitateId+");";
        q.doUpdate(sqlActiGr);
        q.doUpdate(sqlJoinActivitate);
        q.closeConnection();
    }
    public Table getRecomendedParticipants(int grup_id)
    {
        Query q=new Query();
        q.init();
        String sqlStmnt="select curs.id from curs join grup on grup.curs_id=curs.id where grup.id="+grup_id+";";
        String curs_id=q.getAtr(sqlStmnt);
        q.closeConnection();
        return new Table("SELECT u.nume, u.prenume\n" +
                "FROM curs_student cs\n" +
                "JOIN student s ON cs.student_id = s.id\n" +
                "JOIN utilizator u ON s.id = u.id\n" +
                "WHERE cs.curs_id = "+curs_id+"\n" +
                "  AND NOT EXISTS (\n" +
                "      SELECT 1\n" +
                "      FROM grup_studenti gs\n" +
                "      WHERE gs.student_id = s.id\n" +
                "        AND gs.grup_id = "+grup_id+"\n" +
                "  );");
    }
    public void scrieMesaj(int grup_id,String mesaj)
    {
        Query q=new Query();
        q.init();
        String sqlStmnt="insert into mesaj(grup_id, student_id, continut) VALUES\n" +
                "("+grup_id+","+this.getId()+",'"+mesaj+"');";
        q.doUpdate(sqlStmnt);
        q.closeConnection();
    }
    public Table getActivitatiGrup(int grup_id)
    {
        return new Table("select activitate_id,tip from grup_activitate join activitate\n" +
                "on activitate_id=activitate.id where grup_id="+grup_id+";");
    }

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
}
