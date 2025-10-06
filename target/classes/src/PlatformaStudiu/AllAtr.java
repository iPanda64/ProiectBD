package PlatformaStudiu;

public class AllAtr {
    private String query;
    private int used;
    public AllAtr(String query) {
        this.query = query;
        this.used = 0;
    }
    public String getQuery() {
        return query;
    }
    public void incrementUsed() {
        this.used+=1;
    }
    public int getUsed() {
        return used;
    }
    public void setUsed(int used) {
        this.used = used;
    }
}
