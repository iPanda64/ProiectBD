package PlatformaStudiu;

public class LogareBackend {
    private String username;
    private String parola;
    public LogareBackend() {}
    public  LogareBackend(String username, String parola) {
        this.username = username;
        this.parola = parola;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getParola() {
        return parola;
    }

    public String getUsername() {
        return username;
    }

    public boolean valid()
    {
        if(Query.safeInput(this.username)&&Query.safeInput(this.parola))
        {
            if(this.getTip().equals("No results."))return false;
            return true;
        }
        return false;
    }
    public String getTip()
    {

        String sqlStatement = "select tip from autentificare join utilizator u on u.id = autentificare.id WHERE username = '"+this.username+"' AND parola = '"+this.parola+"'";
        Query q=new Query();
        q.init();
        String ret = q.getAtr(sqlStatement);
        q.closeConnection();
        return ret;
    }
    public int getId()
    {

        String sqlStatement = "select autentificare.id from autentificare join utilizator u on u.id = autentificare.id WHERE username = '"+this.username+"' AND parola = '"+this.parola+"'";
        Query q=new Query();
        q.init();
        String ret = q.getAtr(sqlStatement);
        q.closeConnection();
        return Integer.parseInt(ret);
    }
}
