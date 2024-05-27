import javax.swing.*;
import java.sql.SQLException;

public class MAIN {
    public static void main(String[] args) {
        try {
            if (comprobarEstadoSQL.main()) {
                monedasDiarias.moneda();
                MainGUI.main();
            }
        } catch (
                SQLException e) {
            JOptionPane.showMessageDialog(null, "La base de datos no se encuentra disponible en estos momentos, compruebe el estado de esta", "ERROR INÉSPERADO", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }
}
/* Esto ha sido creado para prácticar y entretenerme a la vez que aprendo a programar cosas más "complejas". Obviamente quizás ni llegue a nivel de Junio en Java
 * y por ende puede que haya código ineficiente o con sysntax MUY mejorable pero esto lo estoy haciendo para eso, aprender.*/