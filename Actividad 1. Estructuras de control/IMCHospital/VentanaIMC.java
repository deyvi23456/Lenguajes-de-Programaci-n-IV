import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaIMC extends JFrame {
     // Componentes (OBJETOS de la interfaz)
    private JPanel contenedor;
    private CardLayout cartas;
    private JPanel pnlEntrada;
    private JPanel pnlResultado;

    private JTextField txtPeso;
    private JTextField txtEstatura;
    private JButton btnAceptar;

    private JTextField txtImc;
    private JLabel lblMensaje;
    private JButton btnCerrar;

    // Constructor: arma la ventana
    public VentanaIMC() {
        setTitle("IMC HOSPITAL");
        setSize(360, 320);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centrar en pantalla
        setResizable(false);

        cartas = new CardLayout();
        contenedor = new JPanel(cartas);

        crearPanelEntrada();
        crearPanelResultado();

        contenedor.add(pnlEntrada, "entrada");
        contenedor.add(pnlResultado, "resultado");
        add(contenedor);
    }

    // ---------- Pantalla 1: captura de datos ----------
    private void crearPanelEntrada() {
        pnlEntrada = new JPanel(null);

        JLabel lblTitulo = new JLabel("IMC HOSPITAL");
        lblTitulo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
        lblTitulo.setBounds(110, 15, 200, 25);
        pnlEntrada.add(lblTitulo);

        JLabel lblPeso = new JLabel("Peso en kilogramos:");
        lblPeso.setBounds(50, 60, 200, 25);
        pnlEntrada.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(50, 88, 240, 28);
        pnlEntrada.add(txtPeso);

        JLabel lblEstatura = new JLabel("Estatura en metros:");
        lblEstatura.setBounds(50, 130, 200, 25);
        pnlEntrada.add(lblEstatura);

        txtEstatura = new JTextField();
        txtEstatura.setBounds(50, 158, 240, 28);
        pnlEntrada.add(txtEstatura);

        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(200, 220, 90, 30);
        pnlEntrada.add(btnAceptar);

        // EVENTO: qué pasa cuando se da clic en "Aceptar"
        btnAceptar.addActionListener(e -> calcular());
    }

    // ---------- Pantalla 2: resultado ----------
    private void crearPanelResultado() {
        pnlResultado = new JPanel(null);

        JLabel lblTitulo = new JLabel("IMC HOSPITAL");
        lblTitulo.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
        lblTitulo.setBounds(110, 15, 200, 25);
        pnlResultado.add(lblTitulo);

        JLabel lblSuImc = new JLabel("Su IMC es:");
        lblSuImc.setBounds(50, 60, 200, 25);
        pnlResultado.add(lblSuImc);

        txtImc = new JTextField();
        txtImc.setEditable(false);
        txtImc.setBounds(50, 88, 240, 28);
        pnlResultado.add(txtImc);

        lblMensaje = new JLabel();
        lblMensaje.setFont(new Font("Arial", Font.BOLD, 14));
        lblMensaje.setBounds(50, 130, 270, 50);
        pnlResultado.add(lblMensaje);

        btnCerrar = new JButton("Cerrar");
        btnCerrar.setBounds(200, 220, 90, 30);
        pnlResultado.add(btnCerrar);

        btnCerrar.addActionListener(e -> System.exit(0));
    }

    // ---------- Lógica del botón Aceptar ----------
    private void calcular() {
        try {
            // Acepta coma o punto como separador decimal
            double peso = Double.parseDouble(txtPeso.getText().trim().replace(',', '.'));
            double estatura = Double.parseDouble(txtEstatura.getText().trim().replace(',', '.'));

            // Validaciones
            if (peso <= 0 || peso > 500) {
                JOptionPane.showMessageDialog(this,
                        "Ingresa un peso válido (mayor a 0 y máximo 500 kg).",
                        "Dato incorrecto", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (estatura <= 0 || estatura > 2.8) {
                JOptionPane.showMessageDialog(this,
                        "Ingresa una estatura válida en metros (ejemplo: 1.70).",
                        "Dato incorrecto", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Se crea el OBJETO Paciente y se usan sus métodos
            Paciente paciente = new Paciente(peso, estatura);
            double imc = paciente.calcularIMC();
            String clasificacion = paciente.clasificar();

            txtImc.setText(String.format("%.1f", imc));
            lblMensaje.setText("<html>La persona tiene:<br>" + clasificacion + "</html>");
            lblMensaje.setForeground(obtenerColor(clasificacion));

            cartas.show(contenedor, "resultado"); // cambia de pantalla

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Solo se permiten números en ambos campos.",
                    "Dato incorrecto", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Asigna un color según la clasificación (usa SWITCH-CASE)
    private Color obtenerColor(String clasificacion) {
        switch (clasificacion) {
            case "Bajo peso":
                return new Color(0, 160, 220);
            case "Peso normal":
                return new Color(80, 160, 30);
            case "Sobrepeso":
                return new Color(230, 170, 0);
            case "Obesidad grado I":
                return new Color(230, 130, 0);
            case "Obesidad grado II":
                return new Color(230, 90, 20);
            default:
                return new Color(220, 20, 40);
        }
    }
}

