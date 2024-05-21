import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class test {
    public static void main(String[] args) throws Exception {
        String sql = "SELECT * FROM public.table";
        String url = "jdbc:postgresql://localhost:5432/master";
        String username = "postgres";
        String pw = "asigphi";

        Connection con = DriverManager.getConnection(url, username, pw);

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        int i = 1;
        
        while(rs.next()){
            System.out.println("Row " + i + ": ");
            for(int j = 1;j<=rs.getMetaData().getColumnCount();j++){
                System.out.print(rs.getString(j) + " ");
            };
            System.out.println();
            i++;
        }
        
        con.close();
        rs.close();
        st.close();

    }
}
