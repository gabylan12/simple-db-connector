package uy.com.antel;

import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

    private static void setupDatabase(){
        //set up database
        System.out.println("Ingresar servidor [localhost]: ");
        Scanner scanner = new Scanner(System.in);
        String token = scanner.nextLine();
        if(!StringUtils.isEmpty(token)){
            server = token;
            System.out.println("Base ingresada " + server);
        }

        //set up port
        System.out.println("Ingresar puerto [5432]: ");
        scanner = new Scanner(System.in);
        token = scanner.nextLine();
        if(!StringUtils.isEmpty(token)){
            port = token;
            System.out.println("Puerto ingresado " + port);
        }

        //set up database name
        System.out.println("Ingresar base de datos [mydatabase]: ");
        scanner = new Scanner(System.in);
        token = scanner.nextLine();
        if(!StringUtils.isEmpty(token)){
            database = token;
            System.out.println("Base de datos ingresada " + database);
        }

        //set up user
        System.out.println("Ingresar usuario [myuser]: ");
        scanner = new Scanner(System.in);
        token = scanner.nextLine();
        if(!StringUtils.isEmpty(token)){
            user = token;
            System.out.println("Usuario ingresado " + user);
        }

        //set up password
        System.out.println("Ingresar password [userpass]: ");
        scanner = new Scanner(System.in);
        token = scanner.nextLine();
        if(!StringUtils.isEmpty(token)){
            password = token;
            System.out.println("Password ingresado " + password);
        }


    }

    public static void main( String[] args )   {
        try {

            setupDatabase();
            Class.forName("org.postgresql.Driver");
            Connection connection = null;
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://"+server+":"+port+"/"+ database,user, password);
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println( "Hello World!" );
    }
}
