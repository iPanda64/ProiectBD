package PlatformaStudiu;
import java.util.ArrayList;
import java.util.List;
public class Table  {
    private List<String> header;
    private List<ArrayList<String>> data;
    private int rowCount;
    private int colCount;

    public Table() {
        this.header = new ArrayList<String>();
        this.data = new ArrayList<>();
        this.rowCount = 0;
        this.colCount = 0;
        //this.data = new String[size][size+1];
    }
    private void setFieldsFrom(Table t) {
        this.setHeader(t.header);
        this.setColCount(t.rowCount);
        this.setRowCount(t.colCount);
        this.setData(t.data);
    }
    private void setData(List<ArrayList<String>> data) {
        this.data = data;
    }
    public Table(String queryStr){
        Query q=new Query();
        q.init();
        Table ret = q.getTable(queryStr);
        if(ret!=null)
        this.setFieldsFrom(ret);
        q.closeConnection();
    }
    public void addRow(ArrayList<String> row) {
        this.rowCount++;
        data.add(row);
    }
    public void setColCount(int colCount) {
        this.colCount = colCount;
    }
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
    public void setHeader(List<String> header) {
        this.header = header;
    }
    public List<String> getHeader() {
        return this.header;
    }
    public String getData(int row, int col) {
        return this.data.get(row).get(col);
    }
    @Override
    public String toString() {
        if (header == null || data == null || data.isEmpty()) {
            return "No data to display.";
        }

        StringBuilder result = new StringBuilder();

        // Step 1: Calculate column widths based on header and data
        int[] colWidths = new int[header.size()];
        for (int i = 0; i < header.size(); i++) {
            colWidths[i] = header.get(i).length(); // Start with header length
            for (ArrayList<String> row : data) {
                String cell = (i < row.size() && row.get(i) != null) ? row.get(i) : "";
                //String cell = row.get(i) != null ? row.get(i) : "";
                colWidths[i] = Math.max(colWidths[i], cell.length());
            }
        }

        // Step 2: Append header
        for (int i = 0; i < header.size(); i++) {
            result.append(padRight(header.get(i), colWidths[i])).append("  ");
        }
        result.append("\n");

        // Append separator line
        for (int width : colWidths) {
            result.append("-".repeat(width)).append("  ");
        }
        result.append("\n");

        // Step 3: Append data rows
        for (ArrayList<String> row : data) {
            for (int i = 0; i < row.size(); i++) {
                String cell = row.get(i) != null ? row.get(i) : "";
                result.append(padRight(cell, colWidths[i])).append("  ");
            }
            result.append("\n");
        }

        return result.toString();
    }

    public int getColCount() {
        return colCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    // Helper method to pad strings with spaces on the right
    private String padRight(String str, int length) {
        if (str.length() >= length) return str;
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < length) {
            sb.append(" ");
        }
        return sb.toString();
    }


//    public String getQuery() {
//        return query;
//    }
//    public void incrementUsed() {
//        this.used+=1;
//    }
//    public int getUsed() {
//        return used;
//    }
//    public void setUsed(int used) {
//        this.used = used;
//    }
//    public void setData(String data,int l,int c) {
//        this.data[l][c] = data;
//    }
//    public String getData(int l,int c) {
//        return this.data[l][c];
//    }
}
