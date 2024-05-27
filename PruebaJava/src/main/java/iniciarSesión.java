import java.sql.*;

public class iniciarSesión {
    public static boolean iniciar(String user, String contrasena) throws SQLException {
        /* En estas líneas se establece el contacto con la base de datos que está en local. En este caso
         * el usuario y la contraseña de mysql son 'root' y sin contraseña. Además, se indica por donde contectar
         * a la base de datos y la fecha del servidor dado que aveces MySQL la necesita. El puerto es 3306 (por defecto)*/
        final String url = "jdbc:mysql://localhost:3306/appjava?serverTimezone=UTC";
        final String username = "root";
        final String password = "";

        //Resultado siempre devolverá false por defecto.
        boolean resultado = false;

        //Estas variables recogerán su valor de la base de datos.
        String usuario = "";
        String password2 = "";

        //Realiza la conexión con la base de datos
        Connection connectSQL = DriverManager.getConnection(url, username, password);

        //Crea un Statement para conectarse
        Statement statement = connectSQL.createStatement();

        /* A la conexión le añade la solicitud SELECT * FROM USUARIOS. Este query en MySQL
         * solicita todos los datos de la tabla USUARIOS.*/
        ResultSet resultSet = statement.executeQuery("SELECT * FROM USUARIOS");

        /* Con este while recorremos la base de datos hasta que ya no haya más filas en
         * esta (se detiene de forma automática). Aquí le daremos valor en cada iteración a usuario y password2
         * y comprueba si son iguales a los introducidos por el usuario (user, contrasena)*/
        while (resultSet.next()) {
            usuario = (resultSet.getString("user"));
            password2 = (resultSet.getString("password"));
            //Esto es solo para hacer debug.
                /* System.out.println(STR."INTRODUCIDA: \{user} | MySQL: \{usuario}");
                 System.out.println(STR."INTRODUCIDA: \{contrasena} | MySQL: \{password2}"); */
            if (usuario.equals(user) && contrasena.equals(password2)) {
                resultado = true;
            }
        }
// Cerrar la conexión a la base de datos para liberar recursos.
        connectSQL.close();
// Cerrar el objeto Statement para liberar recursos y evitar posibles problemas de fuga de recursos.
        statement.close();
// Cerrar el objeto ResultSet para liberar recursos y evitar posibles problemas de fuga de recursos.
        resultSet.close();
        return resultado;
    }
}
