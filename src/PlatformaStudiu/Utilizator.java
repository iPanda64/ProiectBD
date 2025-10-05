package PlatformaStudiu;
import java.io.File;  // Import the File class
import java.io.IOException;
public class Utilizator {
    private int id;

    public void setId(int id) {this.id = id;}
    public int getId() {return this.id;}
    public Utilizator() {this.id = -1;}
    public Utilizator(int id) {this.id = id;}

    public Table viewProfile() {
        String sqlStatement = "select * from utilizator WHERE id = " + id +" ;";
        Query q=new Query();
        q.init();
        Table ret = q.getTable(sqlStatement);
        q.closeConnection();
        return ret;
    }

}
