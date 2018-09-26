package uy.com.antel;

import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    private static String server = "localhost";

    private static String port = "5432";

    private static String database = "mydatabase";

    private static String user = "myuser";

    private static String password = "userpass";

    private static Connection connection = null;

    private static void setupDatabase(){
        //set up database
        System.out.print("Ingresar servidor [localhost]: ");
        Scanner scanner = new Scanner(System.in);
        String token = scanner.nextLine();
        if(!StringUtils.isEmpty(token)){
            server = token;
            System.out.println("Base ingresada " + server);
        }

        //set up port
        System.out.print("Ingresar puerto [5432]: ");
        scanner = new Scanner(System.in);
        token = scanner.nextLine();
        if(!StringUtils.isEmpty(token)){
            port = token;
            System.out.println("Puerto ingresado " + port);
        }

        //set up database name
        System.out.print("Ingresar base de datos [mydatabase]: ");
        scanner = new Scanner(System.in);
        token = scanner.nextLine();
        if(!StringUtils.isEmpty(token)){
            database = token;
            System.out.println("Base de datos ingresada " + database);
        }

        //set up user
        System.out.print("Ingresar usuario [myuser]: ");
        scanner = new Scanner(System.in);
        token = scanner.nextLine();
        if(!StringUtils.isEmpty(token)){
            user = token;
            System.out.println("Usuario ingresado " + user);
        }

        //set up password
        System.out.print("Ingresar password [userpass]: ");
        scanner = new Scanner(System.in);
        token = scanner.nextLine();
        if(!StringUtils.isEmpty(token)){
            password = token;
            System.out.println("Password ingresado " + password);
        }


    }

    private static void connectDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(
                "jdbc:postgresql://"+server+":"+port+"/"+ database,user, password);
    }

    private static void executeCommand() throws SQLException {
        while(true) {
            try {
                System.out.print("->");
                Scanner scanner = new Scanner(System.in);
                String token = scanner.nextLine();
                if (!"quit".equalsIgnoreCase(token)) {
                    Statement statement = connection.createStatement();
                    if (token.toLowerCase().startsWith("select")) {
                        ResultSet rs = statement.executeQuery(token);
                        while (rs.next()) {
                            for (int i =1;i<=rs.getMetaData().getColumnCount();i++){
                                System.out.print(rs.getMetaData().getColumnLabel(i) + "=");
                                System.out.print(rs.getObject(i) + "\t");
                            }
                            System.out.println();
                        }
                        rs.close();
                    } else {
                        statement.execute(token);
                        System.out.println("Sentencia ejecutada");

                    }
                } else {
                    break;
                }
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static void main( String[] args )   {
        try {

            setupDatabase();

            connectDatabase();

            executeCommand();

            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println( "Fin de la ejecución" );
    }


}
