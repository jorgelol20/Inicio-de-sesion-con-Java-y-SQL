import java.sql.*;

public class comprobarEstadoSQL {
    public static boolean main() throws SQLException {
        /* En estas líneas se establece el contacto con la base de datos que está en local. En este caso
         * el usuario y la contraseña de mysql son 'root' y sin contraseña. Además, se indica por donde contectar
         * a la base de datos y la fecha del servidor dado que aveces MySQL la necesita. El puerto es 3306 (por defecto)*/
        final String url = "jdbc:mysql://localhost:3306/appjava?serverTimezone=UTC";
        final String username = "root";
        final String password = "";
        //Realiza la conexión con la base de datos
        Connection connectSQL = DriverManager.getConnection(url, username, password);
        //Crea un Statement para conectarse
        Statement statement = connectSQL.createStatement();
        /* A la conexión le añade la solicitud SELECT * FROM USUARIOS. Este query en MySQL
         * solicita todos los datos de la tabla USUARIOS.*/
        ResultSet resultSet = statement.executeQuery("SELECT * FROM USUARIOS");
// Cerrar la conexión a la base de datos para liberar recursos.
        connectSQL.close();
// Cerrar el objeto Statement para liberar recursos y evitar posibles problemas de fuga de recursos.
        statement.close();
// Cerrar el objeto ResultSet para liberar recursos y evitar posibles problemas de fuga de recursos.
        resultSet.close();
        return true;
    }
}
