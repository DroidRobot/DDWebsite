import java.sql.*;
import java.util.LinkedList;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.StringBuilder;

public class test {
    private static Connection con;
    private static Statement st;
    private static LinkedList<Account> accounts;
    public static void main(String[] args) throws Exception {
        String url = "jdbc:postgresql://localhost:5432/master";
        String username = "postgres";
        String pw = "asigphi";
        con = DriverManager.getConnection(url, username, pw);
        st = con.createStatement();

        Account driver1 = new Account("Nate", true);
        Account driver2 = new Account("Ryan Gosling", true);
        Account passenger1 = new Account("Carti", false);
        Account passenger2 = new Account("Kanye", false);

        driver1.getQueue().add(passenger1);
        System.out.println(driver1.toString());
        System.out.println(driver1.getQueue().toString()); 
        
        driver2.getQueue().add(passenger2);
        System.out.println(driver2.toString());
        System.out.println(driver2.getQueue().toString()); 

        String driver1JSON = getJSON(driver1);
        System.out.println("a;lskdfjals");
        Account newD1 = getAccount(driver1JSON);
        newD1.setName("NATE");
        System.out.println(newD1.toString());
        displayData2();
        
        con.close();
        st.close();

    }
    private static void updateDB() throws Exception{
        /*
         * To update a field of a custom Java object stored as JSON in an SQL database, you would typically follow these steps:

Retrieve the JSON Data:
Use a SELECT statement to fetch the JSON data from the database.
Parse the JSON Data:
Use a JSON parsing library like Jackson or Gson to convert the JSON string into a Java object.
Update the Object:
Modify the Java object’s field using its setter method or directly if the field is accessible.
Convert the Object Back to JSON:
Use the same JSON library to convert the updated Java object back into a JSON string.
Update the JSON Data in the Database:
Use an UPDATE statement to save the new JSON string back to the database.
         */
        String sqlUpdate = "UPDATE public.table2 SET account = ";
        sqlUpdate += "\'" + "JSONJSONJSON" + "\'" + "WHERE id =1 ";
        st.execute(sqlUpdate);

        
    }
    private static String getJSON(Account account) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(account);
    }
    private static Account getAccount(String JSON) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(JSON, Account.class);
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
