import java.sql.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.StringBuilder;

public class test {
    private static Connection con;
    private static Statement st;
    public static void main(String[] args) throws Exception {
        String url = "jdbc:postgresql://localhost:5432/master";
        String username = "postgres";
        String pw = "asigphi";

        con = DriverManager.getConnection(url, username, pw);

        st = con.createStatement();
        displayData();
        //insertData(456, "kendrick", 123);
        displayData();

        Account ac = new Account("Nate", true);
        Account ac2 = new Account("Carti", false);
        insertData2(2, ac2);
        displayData2();

        
        con.close();
        st.close();

    }
    private static void insertData2(int id, Account account) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String jsonData = mapper.writeValueAsString(account);

        StringBuilder sb = new StringBuilder();
        
        sb.append("INSERT INTO public.table2 (id, account) VALUES (");
        sb.append(id);
        sb.append(", ");
        sb.append("\'"+jsonData+"\'");
        sb.append(")");
        String statement = sb.toString();
        st.execute(statement);
    }
    private static void displayData2()throws SQLException{
        String sql = "SELECT * FROM public.table2";
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
        rs.close();

    }

    private static void insertData(int num1, String name, int num2) throws SQLException{
        StringBuilder sb = new StringBuilder();
        
        sb.append("INSERT INTO public.table (\"A\", name, number) VALUES (");
        sb.append(num1);
        sb.append(", ");
        sb.append("\'" + name + "\'");
        sb.append(",");
        sb.append(456);
        sb.append(")");
        String statement = sb.toString();
        st.execute(statement);
    }
    private static void displayData()throws SQLException{
        String sql = "SELECT * FROM public.table";
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
        rs.close();

    }
}
