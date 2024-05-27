import java.sql.*;

public class obtenerPuntos {
    public static int obtener(String user) throws SQLException {
        /* En estas líneas se establece el contacto con la base de datos que está en local. En este caso
         * el usuario y la contraseña de mysql son 'root' y sin contraseña. Además, se indica por donde contectar
         * a la base de datos y la fecha del servidor dado que aveces MySQL la necesita. El puerto es 3306 (por defecto)*/
        final String url = "jdbc:mysql://localhost:3306/appjava?serverTimezone=UTC";
        final String username = "root";
        final String password = "";

        //declaramos la variable que devolveremos
        int puntos = 0;
        //Declaramos la variable usuario que obtendrá valor de los datos de la tabla de SQL
        String usuario;
        //Realiza la conexión con la base de datos
        Connection connectSQL = DriverManager.getConnection(url, username, password);
        //Crea un Statement para conectarse
        Statement statement = connectSQL.createStatement();
        /* A la conexión le añade la solicitud SELECT * FROM USUARIOS. Este query en MySQL
         * solicita todos los datos de la tabla USUARIOS.*/
        ResultSet resultSet = statement.executeQuery("SELECT * FROM USUARIOS");
        /* Con esto recorremos todas las filas de la tabla. Así, le daremos valor a usuario en cada fila
         * y lo comprobaremos con el usuario introducido. Si coincide, obtiene los puntos de ese usuario*/
        while (resultSet.next()) {
            usuario = (resultSet.getString("user"));
            if (user.equals(usuario)) {
                puntos = (resultSet.getInt("puntos"));
            }
        }
// Cerrar la conexión a la base de datos para liberar recursos.
        connectSQL.close();
// Cerrar el objeto Statement para liberar recursos y evitar posibles problemas de fuga de recursos.
        statement.close();
// Cerrar el objeto ResultSet para liberar recursos y evitar posibles problemas de fuga de recursos.
        resultSet.close();
        return puntos;
    }
}
