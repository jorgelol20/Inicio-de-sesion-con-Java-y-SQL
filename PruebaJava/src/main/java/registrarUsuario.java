import java.sql.*;

public class registrarUsuario {
    public static boolean registrar(String user, String contrasena) throws SQLException {
        /* En estas líneas se establece el contacto con la base de datos que está en local. En este caso
         * el usuario y la contraseña de mysql son 'root' y sin contraseña. Además, se indica por donde contectar
         * a la base de datos y la fecha del servidor dado que aveces MySQL la necesita. El puerto es 3306 (por defecto)*/
        final String url = "jdbc:mysql://localhost:3306/appjava?serverTimezone=UTC";
        final String username = "root";
        final String password = "";

        //Resultado siempre devolverá true por defecto.
        boolean resultado = true;

        //Estas variables recogerán su valor de la base de datos.
        boolean repetido = false;
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
         * y comprueba si son iguales a alguno ya existente en la base de datos. Si no existe tal usuario, lo crea con
         * la contraseña indicada y además de indicar que tiene 0 puntos.*/
        while (resultSet.next()) {
            usuario = (resultSet.getString("user"));

            //Esto es solo para hacer debug.
                /* System.out.println(STR."INTRODUCIDA: \{user} | MySQL: \{usuario}");
                 System.out.println(STR."INTRODUCIDA: \{contrasena} | MySQL: \{password2}"); */

            if (usuario.equals(user)) {
                resultado = true;
                repetido = true;
            }
        }
        if (!repetido){
            // I
            String query = "INSERT INTO `usuarios`(`user`, `password`, `puntos`,`monedas`) VALUES ('"+user+"','"+contrasena+"','0', '3')";
            String sqlUpdate = query;
            statement.executeUpdate(sqlUpdate);
            resultado = false;
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
