import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainGUI {
    //Creación de los elementos del panel principal
    private JPanel mainPanel;
    private JLabel user;
    private JTextField userText;
    private JLabel password;
    private JPasswordField passwordField1;
    private JButton signin;
    private JButton login;
    private JTextArea contraseñaMal;


    public static void main() {
        //Creación de una ventana emergente para el programa. Si no, no se muestra nada.
        //Le da nombre a la ventana.
        JFrame frame = new JFrame("AppPrueba");
        //Indica el panel que tiene que abrirse
        frame.setContentPane(new MainGUI(frame).mainPanel);
        //Indica que ocurre cuando se cierra la ventana
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        //Ajusta el tamaño justo para la ventana
        frame.setSize(420, 260);
        //Indica que la ventana es visible
        frame.setVisible(true);
        //Indica que la ventana no es reajustable
        frame.setResizable(false);
        //Indica que aparezca la ventana en el centro de la pantalla.
        frame.setLocationRelativeTo(null);
    }

    public MainGUI(JFrame frame) {
        contraseñaMal.setVisible(false);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = "";
                String contrasena = "";
                usuario = userText.getText();
                contrasena = passwordField1.getText();
                try {
                    if (iniciarSesión.iniciar(usuario, contrasena)) {
                        JOptionPane.showMessageDialog(login, "Has iniciado sesión con exito");
                        frame.setVisible(false);
                        MenuJuegos.main(usuario);

                    } else {
                        JOptionPane.showMessageDialog(login, "Incorrecto");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "ERROR", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        signin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario;
                String contrasena;
                usuario = userText.getText();
                contrasena = passwordField1.getText();
                if (passwordF(contrasena)) {
                    try {
                        if (!registrarUsuario.registrar(usuario, contrasena)) {
                            JOptionPane.showMessageDialog(login, "Te has registrado con exito");
                            frame.setVisible(false);
                            MenuJuegos.main(usuario);

                        } else {
                            JOptionPane.showMessageDialog(login, "El usuario ya existe");
                        }
                    } catch (SQLException ex) {
                        /* Si pones algo aquí lo ejecutara pese a que si funciona "correctamente" dado que SI actualiza los datos de forma correcta
                         * pero se crea un SQLException y no sé porqué. He intentado arreglar el código con ayuda de chatGTP y lo que me dijo si quiera
                         * actualizaba la base de datos XD. El error está al hacer la query de Update a la base de datos (seguramente por como construyo
                         * la petición o eso ha dicho chatGTP) y me da pereza centrarme en algo que no entiendo porque falla y la solución de chatGTP ni
                         * funciona asique se queda así y aquí aprovecho para contar esto, dado que si pongo algo en el catch va a ejecutarse si o si*/
                    }
                }else{
                    contraseñaMal.setVisible(true);
                    contraseñaMal.setText("La contraseña debe tener: \n-8 carácteres o más. \n-Un carácter especial.    -_#!$%&'()*+,./ \n-Un carácter numérico. \n-Una letra minúscula. \n-Una letra mayúscula.");
                }
            }
        });
    }
    private static boolean passwordF(String password){
        boolean mayuscula = false;
        boolean minúscula = false;
        boolean numero = false;
        boolean especial = false;
        boolean correcto = false;

            for(int i = 0; i < password.length(); i++){
                if ((int) password.charAt(i) < 91 && (int) password.charAt(i) > 64){
                    mayuscula = true;
                }
                else if ((int) password.charAt(i) < 123 && (int) password.charAt(i) > 96){
                    minúscula = true;
                }
                else if (((int) password.charAt(i) < 48 && (int) password.charAt(i) > 32) || password.charAt(i) == '_' || password.charAt(i) == '-' || password.charAt(i) == '#'){
                    especial = true;
                }
                else if ((int) password.charAt(i) < 58 && (int) password.charAt(i) > 47){
                    numero = true;
                }
            }
            if ((password.length()<8 || !especial || !numero || !minúscula || !mayuscula)){
                correcto = false;
            }else{
                correcto = true;
            }
        return correcto;
    }
}
