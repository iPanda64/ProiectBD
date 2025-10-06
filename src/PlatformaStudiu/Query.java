package PlatformaStudiu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;

public class Query {
    private final String url = "jdbc:mysql://127.0.0.1:3306/platforma_studiu";
    private	final String uid = "root";
    private	final String pw = "root";
    private BufferedReader reader;
    private Connection con;


    public Query() {

    }
    public static boolean safeInput(String str) {
        String unsafeChars = "';--#\"\t ";
        for (int i = 0; i < str.length(); i++) {
            if (unsafeChars.indexOf(str.charAt(i)) != -1) {
                return false;
            }
        }
        return true;
    }

    public Tupla getTupla(String queryStr)
    {
        try {
            Statement stmt = con.createStatement();
            ResultSet rst = stmt.executeQuery(queryStr);
            ResultSetMetaData rsmd = rst.getMetaData();
            // Calculate column sizes (cut off large columns to 35)
            int colCount = rsmd.getColumnCount();
            int rowCount = 0;
            Tupla t=new Tupla(colCount);
            for (int i=1; i <= colCount; i++)
            {
                String colName = rsmd.getColumnName(i);
                t.setHeader(colName,i);
            }
            while (rst.next()) {
                for (int i = 1; i <= colCount; i++) {
                    //if(rst.getObject(i)!=null)System.out.println("hey"+rst.getObject(i).toString()+"bei");
                    Object obj = rst.getObject(i);
                    if (obj != null) {
                        t.setData(obj.toString(), i);
                    }
                }
                rowCount++;
            }
            if (rowCount == 0)
                System.out.println("No results.");
            rst.close();
            return t;
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
            return null;
        }
    }
    public Table getTable(String queryStr)
    {
        try {
            Statement stmt = con.createStatement();
            ResultSet rst = stmt.executeQuery(queryStr);
            ResultSetMetaData rsmd = rst.getMetaData();
            // Calculate column sizes (cut off large columns to 35)
            int colCount = rsmd.getColumnCount();
            Table t= new Table();
            t.setColCount(colCount);
            ArrayList<String > list=new ArrayList<String>();
            for (int i=1; i <= colCount; i++)
            {
                String colName = rsmd.getColumnName(i);
                list.add(colName);
            }
            t.setHeader(list);
            int rowCount = 0;
            while (rst.next())
            {
                ArrayList<String> list1=new ArrayList<>();
                for (int i=1; i <= colCount; i++)
                {
                    //if(rst.getObject(i)!=null)System.out.println("hey"+rst.getObject(i).toString()+"bei");
                    Object obj = rst.getObject(i);
                    if (obj != null)
                    {	String data = obj.toString();
                        list1.add(data);
                    }
                }
                t.addRow(list1);
                rowCount++;
            }
            return t;
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
            return null;
        }
    }

    public void init()
    {
        // Initialize your application
        // Register the MySQL driver and make a connection
        try {	// Load driver class
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (java.lang.ClassNotFoundException e) {
            System.err.println("ClassNotFoundException: " +e);
        }

        con = null;
        try {
            con = DriverManager.getConnection(url, uid, pw);
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
            System.exit(1);
        }

        // Set up console reader
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String getAtr(String queryStr)
    {
        try {
            Statement stmt = con.createStatement();
            ResultSet rst = stmt.executeQuery(queryStr);
            ResultSetMetaData rsmd = rst.getMetaData();
            // Calculate column sizes (cut off large columns to 35)
            int colCount = rsmd.getColumnCount();
            int rowCount = 0;

            while (rst.next())
            {
                    Object obj = rst.getObject(1);
                    if(obj!=null)
                    {
                        String s=rst.getObject(1).toString();
                        rst.close();
                        return s;
                    }
                rowCount++;
            }
            if (rowCount == 0)
            {
                rst.close();
                return "No results.";
            }
            rst.close();
        }
        catch (SQLException ex) {
            return "SQLException: " + ex;
        }
        return null;
    }

    public String getAtr(AllAtr queryAtrStr)
    {
        try {
            Statement stmt = con.createStatement();
            ResultSet rst = stmt.executeQuery(queryAtrStr.getQuery());
            ResultSetMetaData rsmd = rst.getMetaData();
            // Calculate column sizes (cut off large columns to 35)
            int colCount = rsmd.getColumnCount();
            int rowCount = 0;
           // System.out.println();

            int used_temp=0;


            while (rst.next())
            {
                if(!(used_temp<queryAtrStr.getUsed()))
                {
                    Object obj = rst.getObject(1);
                    if(obj!=null)
                    {
                        queryAtrStr.incrementUsed();
                        String s=rst.getObject(1).toString();
                        rst.close();
                        return s;
                    }
                    rowCount++;

                }
                used_temp++;
            }
            rst.close();
            if (rowCount == 0)
                return "No results.";


        }
        catch (SQLException ex) {
            return "SQLException: " + ex;
        }
        return null;
    }

    public void doQuery(String queryStr)
    {
        try {
            Statement stmt = con.createStatement();
            ResultSet rst = stmt.executeQuery(queryStr);
            ResultSetMetaData rsmd = rst.getMetaData();
            // Calculate column sizes (cut off large columns to 35)
            int colCount = rsmd.getColumnCount();
            int rowCount = 0;
            int colWidth[] = new int[colCount];
            for (int i=1; i <= colCount; i++)
            {	colWidth[i-1] = rsmd.getColumnDisplaySize(i);
                if (colWidth[i-1] > 35)
                    colWidth[i-1] = 35;
            }
            //System.out.println(rst.getString(1)+"hey\n");
            System.out.println();
            // Print header
            for (int i=1; i <= colCount; i++)
            {	String colName = rsmd.getColumnName(i);
                System.out.print(colName+spaces(colWidth[i-1]-colName.length())+' ');
            }
            System.out.println();
            while (rst.next())
            {
                for (int i=1; i <= colCount; i++)
                {
                    //if(rst.getObject(i)!=null)System.out.println("hey"+rst.getObject(i).toString()+"bei");
                    Object obj = rst.getObject(i);
                    if (obj == null)
                        System.out.print(spaces(colWidth[i-1]));
                    else
                    {	String data = obj.toString();
                        System.out.print(data+spaces(colWidth[i-1]-data.length())+' ');
                    }
                }
                System.out.println();
                rowCount++;
            }
            if (rowCount == 0)
                System.out.println("No results.");

            rst.close();
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex);
        }
    }

    private static String spaces(int space)
    {
        String sp = "";
        for(int i=0;i<space;i++)
            sp = sp+" ";
        return sp;
    }

    public void closeConnection()
    {
        try {
        con.close();
             }
            catch (SQLException ex) {
                System.err.println("Exception during connection close: " + ex);
            }
    }

    public void doUpdate(String updateStr)
    {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(updateStr);
            System.out.println("Operation success!");
        }
        catch (SQLException e)
        {	System.out.println("Operation failed: "+e);
        }
    }

}
