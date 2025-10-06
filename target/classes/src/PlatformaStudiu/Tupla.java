package PlatformaStudiu;

public class Tupla {
    private String[] header;
    private String[] data;
    private int size;

    //indexare de la 1
    Tupla(int size) {
        this.header = new String[size+1];
        this.data = new String[size+1];
        this.size = size;
        header[0]=null;
        data[0]=null;
    }
    public void setData(String data,int index) {
        this.data[index] = data;
    }
    public String getData(int index) {
        return this.data[index];
    }
    public void setHeader(String header,int index) {
        this.header[index] = header;
    }
    public String getHeader(int index) {
        return this.header[index];
    }
    public String toString() {
        String finals=new String();
//        for(int i=1;i<=size;i++)
//            finals+=header[i]+"\t";
//        finals+="\n";
        for(int i=1;i<=size;i++)
            finals+=data[i]+",";
        finals+="\b";
        return finals;
    }
}
