import javax.swing.*;
import java.sql.*;
import java.util.Arrays;

public class rankingF {
    public static String ranking() throws SQLException {
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
        String[] puesto1 = {};
        int puntos1 = 0;
        String[] puesto2 = {};
        int puntos2 = 0;
        String[] puesto3 = {};
        int puntos3 = 0;
        int puntos = 2;
        while (resultSet.next()) {
            puntos = (resultSet.getInt("puntos"));
            if (puntos > puntos1) {
                puntos1 = puntos;
                puesto1 = new String[]{(resultSet.getString("user")), (resultSet.getString("puntos"))};
            } else if ((puntos > puntos2)) {
                puntos2 = puntos;
                puesto2 = new String[]{(resultSet.getString("user")), (resultSet.getString("puntos"))};
            } else if ((puntos > puntos3)) {
                puesto3 = new String[]{(resultSet.getString("user")), (resultSet.getString("puntos"))};
                puntos3 = puntos;
            }
        }
        String a1;
        String a2;
        String a3;
        if (!Arrays.equals(puesto1, new String[]{})) {
            a1 = (puesto1[0]+"      "+puesto1[1]);
        }else{
            a1 = "";
        }
        if (!Arrays.equals(puesto2, new String[]{})) {
            a2 = (puesto2[0]+"      "+puesto2[1]);
        }else{
            a2 = "";
        }
        if (!Arrays.equals(puesto3, new String[]{})) {
            a3 = (puesto3[0]+"      "+puesto3[1]);
        }else{
            a3 = "";
        }



// Cerrar la conexión a la base de datos para liberar recursos.
        connectSQL.close();
// Cerrar el objeto Statement para liberar recursos y evitar posibles problemas de fuga de recursos.
        statement.close();
// Cerrar el objeto ResultSet para liberar recursos y evitar posibles problemas de fuga de recursos.
        resultSet.close();
        return (a1+" \n"+a2+" \n "+a3);
    }
}
