import java.sql.DriverManager;

public class main{
    public static void main(String[] args){
        String sql = "select name from firstTable where A=1";

        Connection c = new DriverManager.getConnection();
        
        
    }
}