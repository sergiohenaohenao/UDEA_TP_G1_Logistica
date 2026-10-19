package vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import modelos.TipoEnvio;

public class LogisticaVista extends JFrame {

    private JTable tblEnvios;
    private JPanel pnlEditarEnvio;

    private JTextField txtCodigo, txtCliente, txtPeso, txtDistancia;
    private JComboBox cmbTipoEnvio;

    private JButton btnGuardarEnvio, btnRetirarEnvio;

    public LogisticaVista() {
        setSize(700, 600);
        setTitle("Operador Logístico");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Barra de herramientas con los dos botones
        JToolBar tbLogistica = new JToolBar();

        JButton btnAgregarEnvio = new JButton("Agregar envío");
        btnAgregarEnvio.addActionListener(evt -> {
            btnAgregarEnvioClick();
        });
        tbLogistica.add(btnAgregarEnvio);

        btnRetirarEnvio = new JButton("Retirar envío");
        tbLogistica.add(btnRetirarEnvio);

        // Panel de edición (oculto al inicio)
        pnlEditarEnvio = new JPanel();
        pnlEditarEnvio.setPreferredSize(new Dimension(pnlEditarEnvio.getWidth(), 105));
        pnlEditarEnvio.setLayout(null);

        JLabel lblCodigo = new JLabel("Número");
        lblCodigo.setBounds(10, 10, 100, 25);
        pnlEditarEnvio.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(110, 10, 120, 25);
        pnlEditarEnvio.add(txtCodigo);

        JLabel lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(250, 10, 100, 25);
        pnlEditarEnvio.add(lblTipo);

        cmbTipoEnvio = new JComboBox();
        cmbTipoEnvio.setBounds(360, 10, 120, 25);
        cmbTipoEnvio.setModel(new DefaultComboBoxModel(TipoEnvio.values()));
        pnlEditarEnvio.add(cmbTipoEnvio);

        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(10, 40, 100, 25);
        pnlEditarEnvio.add(lblCliente);

        txtCliente = new JTextField();
        txtCliente.setBounds(110, 40, 120, 25);
        pnlEditarEnvio.add(txtCliente);

        JLabel lblDistancia = new JLabel("Distancia en Km");
        lblDistancia.setBounds(250, 40, 110, 25);
        pnlEditarEnvio.add(lblDistancia);

        txtDistancia = new JTextField();
        txtDistancia.setBounds(360, 40, 120, 25);
        pnlEditarEnvio.add(txtDistancia);

        JLabel lblPeso = new JLabel("Peso");
        lblPeso.setBounds(10, 70, 100, 25);
        pnlEditarEnvio.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(110, 70, 120, 25);
        pnlEditarEnvio.add(txtPeso);

        btnGuardarEnvio = new JButton("Guardar");
        btnGuardarEnvio.setBounds(250, 70, 100, 25);
        pnlEditarEnvio.add(btnGuardarEnvio);

        JButton btnCancelarEnvio = new JButton("Cancelar");
        btnCancelarEnvio.setBounds(360, 70, 100, 25);
        btnCancelarEnvio.addActionListener(evt -> {
            btnCancelarEnvioClick();
        });
        pnlEditarEnvio.add(btnCancelarEnvio);

        pnlEditarEnvio.setVisible(false);

        // Tabla de envíos
        tblEnvios = new JTable();
        JScrollPane spEnvios = new JScrollPane(tblEnvios);

        // Contenedor vertical: arriba el panel de edición, abajo la tabla
        JPanel pnlEnvios = new JPanel();
        pnlEnvios.setLayout(new BoxLayout(pnlEnvios, BoxLayout.Y_AXIS));
        pnlEnvios.add(pnlEditarEnvio);
        pnlEnvios.add(spEnvios);

        add(tbLogistica, BorderLayout.NORTH);
        add(pnlEnvios, BorderLayout.CENTER);
    }

    public void mostrarEnvios(String[][] datos, String[] encabezados) {
        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados);
        tblEnvios.setModel(dtm);
    }

    public String getCodigo() {
        return txtCodigo.getText();
    }

    public String getCliente() {
        return txtCliente.getText();
    }

    public double getDistancia() {
        try {
            return Double.parseDouble(txtDistancia.getText());
        } catch (Exception ex) {
            return 0;
        }
    }

    public TipoEnvio getTipoEnvioSeleccionado() {
        return (TipoEnvio) cmbTipoEnvio.getSelectedItem();
    }

    public void setGuardarEnvioClick(ActionListener escuchadorEvento) {
        btnGuardarEnvio.addActionListener(escuchadorEvento);
    }

    public void setRetirarEnvioClick(ActionListener escuchadorEvento) {
        btnRetirarEnvio.addActionListener(escuchadorEvento);
    }
    
    public double getPeso() {
        try {
            return Double.parseDouble(txtPeso.getText());
        } catch (Exception ex) {
            return 0;
        }
    }

    public void ocultarEdicionEnvio() {
        txtCodigo.setText("");
        txtCliente.setText("");
        txtPeso.setText("");
        txtDistancia.setText("");
        cmbTipoEnvio.setSelectedIndex(0);
        pnlEditarEnvio.setVisible(false);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public String pedirCodigoEnvio() {
        return JOptionPane.showInputDialog(null, "Ingrese el código del envío a retirar");
    }

    private void btnAgregarEnvioClick() {
        pnlEditarEnvio.setVisible(true);
    }

    private void btnCancelarEnvioClick() {
        ocultarEdicionEnvio();
    }
}