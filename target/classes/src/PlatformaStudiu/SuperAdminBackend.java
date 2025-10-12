package PlatformaStudiu;

public class SuperAdminBackend extends AdminBackend {
    public SuperAdminBackend() {super();}
    public SuperAdminBackend(int id) {super(id);}
    @Override
    public Table cautaUtilizator(String nume,String prenume)
    {
        String sqlStatement = "SELECT id,nume,prenume FROM utilizator " +
                "WHERE nume LIKE '" + nume + "%' " +
                "AND prenume LIKE '" + prenume + "%' " +
                "AND tip NOT IN ('Super-Administrator');";
        return new Table(sqlStatement);
    }
    @Override
    public Table getAllUtilizatori()
    {
        return new Table("SELECT id,nume,prenume FROM utilizator WHERE tip NOT IN ('Super-Administrator');");
    }
    public void addAdmin(String CNP,String nume,String prenume,String numar_telefon,String email,String cont_Iban,String numar_contact,String username,String parola)
    {
        this.addUtilizator("Administrator",CNP,nume,prenume,numar_telefon,email,cont_Iban,numar_contact,username,parola);
    }
    public Table getAllAdmin()
    {
        return new Table("select id,nume,prenume from utilizator where tip = 'Administrator';");
    }
}