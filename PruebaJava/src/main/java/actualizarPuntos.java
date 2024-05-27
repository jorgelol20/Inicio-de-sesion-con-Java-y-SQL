import java.sql.*;

public class actualizarPuntos {
    //Aquí introduciremos los puntos en la tabla de la base de datos
    public static void introducir(String user, int nuevosPuntos) throws SQLException {
        /* En estas líneas se establece el contacto con la base de datos que está en local. En este caso
         * el usuario y la contraseña de mysql son 'root' y sin contraseña. Además, se indica por donde contectar
         * a la base de datos y la fecha del servidor dado que aveces MySQL la necesita. El puerto es 3306 (por defecto)*/
        final String url = "jdbc:mysql://localhost:3306/appjava?serverTimezone=UTC";
        final String username = "root";
        final String password = "";


        //Declaramos la variable usuario que obtendrá valor de los datos de la tabla de SQL
        String usuario;
        //Realiza la conexión con la base de datos
        Connection connectSQL = DriverManager.getConnection(url, username, password);
        //Crea un Statement para conectarse
        Statement statement = connectSQL.createStatement();
        /* A la conexión le añade la solicitud SELECT * FROM USUARIOS. Este query en MySQL
         * solicita todos los datos de la tabla USUARIOS.*/
        ResultSet resultSet = statement.executeQuery("SELECT * FROM USUARIOS");
        while (resultSet.next()) {
            usuario = (resultSet.getString("user"));
            if (usuario.equals(user)) {
                statement.executeUpdate("UPDATE USUARIOS SET PUNTOS = '"+nuevosPuntos+"' WHERE USER = '"+user+"';");
            }
        }
        connectSQL.close();
        statement.close();
        resultSet.close();
    }
}
