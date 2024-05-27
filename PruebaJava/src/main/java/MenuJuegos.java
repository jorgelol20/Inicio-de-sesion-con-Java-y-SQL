import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MenuJuegos {
    //Aquí se declaran las variables para el menú.
    private JButton botonJuego1;
    private JButton botonJuego2;
    private JButton botonJuego3;
    private JButton botonSalir;
    private JPanel panelMenuJuegos;
    private JLabel puntosUser;
    private JLabel textoPuntos;
    private JTextPane ranking;
    private JLabel monedasUser;
    private JLabel textoMonedas;
    //Variables que se usan a lo largo del código
    private int monedas;
    private static int puntos;
    ;

    public static void main(String user) throws SQLException {
        JFrame frame = new JFrame("Bienvenido "+user);
        //Indica el panel que tiene que abrirse
        frame.setContentPane(new MenuJuegos(user, frame).panelMenuJuegos);
        //Indica que ocurre cuando se cierra la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Indicamos el tamaño que tendrá la ventana.
        frame.setSize(420, 260);
        //Indica que la ventana será visible
        frame.setVisible(true);
        //Indica que la ventana no será reescalable por el usuario, teniendo tamaño fijo
        frame.setResizable(false);
        //Centra la ventana cuando aparece en pantalla
        frame.setLocationRelativeTo(null);
    }

    public MenuJuegos(String user, JFrame frame) throws SQLException {
        /* Puntos consulta a la función obtener en obtenerPuntos el valor de los puntos que tiene el usuario en la
         * tabla de la base de datos. El JLabel textoPuntos obtiene el valor de puntos y se muestra en el panel.*/
        textoMonedas.setText(obtenerMonedasUser.obtenerMonedas(user));
        monedas = Integer.parseInt((obtenerMonedasUser.obtenerMonedas(user)));
        ranking.setText(rankingF.ranking());
        puntos = obtenerPuntos.obtener(user);
        textoPuntos.setText(String.valueOf(puntos));
        botonJuego1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //El valor de puntos se suma lo que devuelva juegoGacha y el valor e textoPuntos cambia al nuevo valor.
                if(monedas > 0) {
                    puntos += juegoGacha.main();
                    monedas--;
                    textoPuntos.setText(String.valueOf(puntos));
                    textoMonedas.setText(String.valueOf(monedas));
                    try {
                        reducirMonedas.reducir(user, monedas);
                    } catch (SQLException ex) {
                        /* Si pones algo aquí lo ejecutara pese a que si funciona "correctamente" dado que SI actualiza los datos de forma correcta
                         * pero se crea un SQLException y no sé porqué. He intentado arreglar el código con ayuda de chatGTP y lo que me dijo si quiera
                         * actualizaba la base de datos XD. El error está al hacer la query de Update a la base de datos (seguramente por como construyo
                         * la petición o eso ha dicho chatGTP) y me da pereza centrarme en algo que no entiendo porque falla y la solución de chatGTP ni
                         * funciona asique se queda así y aquí aprovecho para contar esto, dado que si pongo algo en el catch va a ejecutarse si o si*/
                    }
                    //Además, actualiza la puntuación en la base de datos
                    try {
                        actualizarPuntos.introducir(user, puntos);
                    } catch (SQLException ex) {
                        /* Si pones algo aquí lo ejecutara pese a que si funciona "correctamente" dado que SI actualiza los datos de forma correcta
                         * pero se crea un SQLException y no sé porqué. He intentado arreglar el código con ayuda de chatGTP y lo que me dijo si quiera
                         * actualizaba la base de datos XD. El error está al hacer la query de Update a la base de datos (seguramente por como construyo
                         * la petición o eso ha dicho chatGTP) y me da pereza centrarme en algo que no entiendo porque falla y la solución de chatGTP ni
                         * funciona asique se queda así y aquí aprovecho para contar esto, dado que si pongo algo en el catch va a ejecutarse si o si*/
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "No tienes suficientes monedas", "Crédito insuficiente", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        botonJuego2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        botonJuego3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Mini menú al iniciar la función para indicar que esto puede tardar un tiempo.
                JOptionPane.showMessageDialog(null, "Esto puede tomar un tiempo", "AVISO", JOptionPane.INFORMATION_MESSAGE);
                try {
                    ranking.setText(rankingF.ranking());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "ERROR", JOptionPane.WARNING_MESSAGE);
                    throw new RuntimeException(ex);
                }
            }
        });
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                int opciones = JOptionPane.showOptionDialog(null, "¿Qué desea realizar?",
                "Salir", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    new Object[]{"Cerrar sesión", "Salir del programa"}, 0);
                // La variable puede tener los valores 0 o 1. Estos se los da el JOptionPane.
                switch (opciones){
                    //Si es 0, volverá al panel para registrarse o iniciar sesión y oculatará MenuJuegos.
                    case 0:
                        frame.setVisible(false);
                        MainGUI.main();
                        break;
                    //Si es 1, cerrará el programa
                    case 1:
                        System.exit(0);
                        break;
                    default:
                        break;

                }

            }
        });
    }
}
