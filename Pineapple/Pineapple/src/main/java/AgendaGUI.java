import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import org.json.*;

/*---------------CLASE AgendaGUI-----------------*/
public class AgendaGUI extends JFrame {

    private final ArrayList<String> id_medicos;
    private static final int costoConsulta = 200;
    JSONObject jo_consultado;

    /*---------------DEFINICIÓN ETIQUETAS CAMPOS JSON-----------------*/
    private static final String NOMBRE_KEY              =               "nombre";
    private static final String DNI_KEY                 =                  "dni";
    private static final String NUMERO_TARJETA_KEY      =         "n de Tarjeta";
    private static final String MONTO_KEY               =                "monto";
    private static final String COSTO_KEY               = "costo de la consulta";
    private static final String METODO_DE_PAGO_KEY      =       "metodo de pago";
    private static final String HORA_KEY                =                 "hora";
    private static final String NOMBRE_MEDICX_KEY       =    "nombre del medico";
    private static final String N_CUOTAS_KEY            =          "n de cuotas";
    private static final String VENCIMIENTO_TARJETA_KEY =          "vencimiento";
    private static final String CODSEG_TARJETA_KEY      =  "codigo de seguridad";
    private static final String ID_MEDICX_KEY           =            "id medico";
    private static final String MATRICULA_MEDICX_KEY    =            "matricula";
    private static final String CONTRASENIA_MEDICX_KEY  =          "contrasenia";
    private static final String VALIDO_KEY              =               "valido";
    private static final String ERROR_KEY               =                "error";
    private static final String TURNOS_KEY              =               "turnos";

    /*---------------DEFINICIÓN ETIQUETAS VISTAS-----------------*/
    private static final String VISTA_PRINCIPAL                     =                     "mainPanel";
    private static final String VISTA_INICIO_DE_SESION              =                   "sesionPanel";
    private static final String VISTA_CONSULTAR_TURNO               =                 "consultaPanel";
    private static final String VISTA_CREAR_TURNO                   =                    "crearPanel";
    private static final String VISTA_TURNOS_RESERVADOS_POR_MEDICO  =       "turnosReservMedicxPanel";
    private static final String VISTA_TURNO_CONSULTADO              =   "mostrarTurnoConsultadoPanel";
    private static final String VISTA_CAMBIAR_METODO_DE_PAGO        =        "cambiarMetodoPagoPanel";

    /*-----------DEFINICIÓN INTERFACES DE VALIDACIÓN----------------*/
    private final Interfaz_Validacion_Turno validadorTurnos;
    private final Interfaz_Validacion_Medicx validadorMedicx;

    /*---------------VISTAS-----------------*/
    private JPanel ViewPanel;
    /*---------------VISTA PRINCIPAL-----------------*/
    private JPanel mainPanel;
    private JPanel mainButtonsPanel;
    private JButton iniciarSesion;
    private JButton consultarTurnoButton;
    private JButton crearTurnoButton;
    private JButton verTurnosButton;
    /*---------------VISTA INICIO DE SESIÓN-----------------*/
    private JPanel sesionPanel;
    private JPanel matriculaSesionPanel;
    private JLabel matriculaLabel;
    private JLabel matriculaIncorrectaLabel;
    private JTextField matricula;
    private JPanel buttonsSesionPanel;
    private JButton backSesionButton;
    private JButton loginButton;
    private JPanel passwordSesionPanel;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JLabel passwordIncorrectaLabel;
    /*---------------VISTA CONSULTAR TURNO-----------------*/
    private JPanel consultaPanel;
    private JPanel dniConsultaPanel;
    private JLabel dniLabel1;
    private JTextField dniConsulta;
    private JPanel buttonsConsultaPanel;
    private JButton backConsultaButton;
    private JButton consultaButton;
    /*---------------VISTA CREAR TURNO-----------------*/
    private JPanel crearPanel;
    private JPanel buttonsPagoPanel;
    private JButton backCrearButton;
    private JButton reservarButton;
    private JPanel ingresoDatosCrearPanel;
    private JPanel datosPacientePagoPanel;
    private JComboBox pagoBox1;
    private JTextField nombrePaciente;
    private JTextField dniCrear;
    private JComboBox medicxsBox;
    private JComboBox horarioBox;
    private JLabel nombreLabel;
    private JLabel dniLabel2;
    private JLabel medicoLabel;
    private JLabel horarioLabel;
    private JLabel pagoLabel1;
    private JLabel costoConsultaLabel1;
    private JPanel metodoPagoPanel;
    private JPanel efectivoPanel;
    private JLabel efectivoLabel;
    private JTextField monto;
    private JPanel debitoPanel;
    private JTextField numeroTarjetaDebito;
    private JTextField codSegTarjetaDebito;
    private JLabel numeroDebitoLabel;
    private JLabel codSegDebitoLabel;
    private JLabel vencimientoDebitoLabel;
    private JPanel vencimientoDebitoPanel;
    private JComboBox mesDebitoBox;
    private JComboBox anioDebitoBox;
    private JPanel creditoPanel;
    private JTextField numeroTarjetaCredito;
    private JTextField codSegTarjetaCredito;
    private JPanel vencimientoCreditoPanel;
    private JComboBox mesCreditoBox;
    private JComboBox anioCreditoBox;
    private JLabel numeroCreditoLabel;
    private JLabel vencimientoCreditoLabel;
    private JLabel codSegCreditoLabel;
    private JComboBox cuotasBox;
    private JLabel cuotasCreditoLabel;
    /*---------------VISTA TURNOS RESERVADOS POR MEDICO-----------------*/
    private JPanel turnosReservMedicxPanel;
    private JScrollPane scrollPanel;
    private JLabel nombreDoctor;
    private JTextPane turnosMedicoTextPane;
    private JButton cerrarSesionButton;
    /*---------------VISTA TURNO CONSULTADO-----------------*/
    private JPanel mostrarTurnoConsultadoPanel;
    private JPanel infoTurnoConsultadoPanel;
    private JTextPane textoMostrarTurnoPanel;
    private JLabel tituloMostrarTurnoLabel;
    private JPanel buttosMostrarTurnoPanel;
    private JButton backButtonMostarTurno;
    private JButton cambiarMetodoDePagoButton;
    /*---------------VISTA CAMBIAR METODO DE PAGO-----------------*/
    private JPanel cambiarMetodoPagoPanel;
    private JButton realizarCambioButton;
    private JButton backCambiarButton;
    private JComboBox pagoBox2;
    private JLabel pagoLabel2;
    private JLabel costoConsultaLabel2;
    private JPanel cambiarEfectivoPanel;
    private JPanel cambiarDebitoPanel;
    private JPanel cambiarCreditoPanel;
    private JPanel cambiarButtonsPanel;
    private JTextField cambiarEfectivoMonto;
    private JLabel cambiarEfectivoMontoLabel;
    private JLabel numeroTarjetaDebitoCambiarLabel;
    private JLabel vencimientoDebitoCambiarLabel;
    private JLabel codSegDebitoCambiarLabel;
    private JTextField numeroTarjetaDebitoCambiar;
    private JTextField codSegTarjetaDebitoCambiar;
    private JTextField numeroTarjetaCreditoCambiar;
    private JLabel numeroTarjetaCreditoCambiarLabel;
    private JTextField codSegTarjetaCreditoCambiar;
    private JLabel vencimientoCreditoCambiarLabel;
    private JLabel codSegCreditoCambiarLabel;
    private JComboBox cuotasCambiarBox;
    private JLabel cuotasCreditoCambiarLabel;
    private JComboBox mesCreditoCambiarBox;
    private JComboBox anioCreditoCambiarBox;
    private JPanel nuevoMetodoPagoPanel;
    private JComboBox mesDebitoCambiarBox;
    private JComboBox anioDebitoCambiarBox;

    /*---------------CONSTRUCTOR CLASE AgendaGUI-----------------*/
    public AgendaGUI(String title, Interfaz_Validacion_Turno validadorTurnos, Interfaz_Validacion_Medicx validadorMedicx, ArrayList<String> id_medicos){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(ViewPanel);
        this.setPreferredSize(new Dimension(700, 500));
        this.pack();
        this.setLocationRelativeTo(null);

        this.validadorTurnos = validadorTurnos;
        this.validadorMedicx = validadorMedicx;
        this.id_medicos = id_medicos;
        initGUI();
        goTo(VISTA_PRINCIPAL);

        /*---------------MÉTODOS ACCIÓN BOTONES VISTA PRINCIPAL-----------------*/
        /**
         * Método acción al presionar el botón "Reservar Turno"
         * Cambia a VISTA PRINCIPAL por VISTA CREAR TURNO
         */
        crearTurnoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goTo(VISTA_CREAR_TURNO);

                pagoBox1.setSelectedItem("Efectivo");
                efectivoPanel.setVisible(true);
                debitoPanel.setVisible(false);
                creditoPanel.setVisible(false);
            }
        });
        /**
         * Método acción al presionar el botón "Consultar Turno"
         * Cambia a VISTA PRINCIPAL por VISTA CONSULTAR TURNO
         */
        consultarTurnoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goTo(VISTA_CONSULTAR_TURNO);
            }
        });
        /**
         * Método acción al presionar el botón "Ver Turnos Disponibles"
         * Despliega nueva ventana con VISTA TURNOS DISPONIBLES
         */
        verTurnosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                VistaTurnosDisp vistaTurnosDisp = new VistaTurnosDisp("My Agenda", AgendaGUI.this.validadorTurnos, AgendaGUI.this.validadorMedicx, AgendaGUI.this.id_medicos);
                vistaTurnosDisp.cargarVistaContent();
                vistaTurnosDisp.setVisible(true);
            }
        });
        /**
         * Método acción al presionar el botón "Iniciar Sesión"
         * Cambia a VISTA PRINCIPAL por VISTA INICIO DE SESIÓN
         */
        iniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goTo(VISTA_INICIO_DE_SESION);

                password.setText("");
                matricula.setText("");
                passwordIncorrectaLabel.setText("");
                matriculaIncorrectaLabel.setText("");
            }
        });

        /*---------------MÉTODOS ACCIÓN BOTONES VISTA INICIO DE SESIÓN-----------------*/
        /**
         * Método acción al presionar el botón "Iniciar Sesión"
         * Envia credenciales de logueo a Interfaz_Validacion_Medico para su validacion
         * Si son validas: cambia VISTA INICIO DE SESIÓN por VISTA TURNOS RESERVADOS POR MÉDICO
         * Si son invalidas: imprime mensaje de error
         */
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JSONObject jo = new JSONObject();
                jo.put(MATRICULA_MEDICX_KEY, matricula.getText());
                jo.put(CONTRASENIA_MEDICX_KEY, new String(password.getPassword()));
                jo = AgendaGUI.this.validadorMedicx.iniciarSesion(jo);
                if(jo.getString(VALIDO_KEY).equals("si")){
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDateTime now = LocalDateTime.now();
                    printInTextPane("DIA: " + dtf.format(now) + "\n\n", turnosMedicoTextPane);
                    nombreDoctor.setText(AgendaGUI.this.validadorMedicx.getNomConID(jo).getString(NOMBRE_MEDICX_KEY));
                    jo = AgendaGUI.this.validadorTurnos.consultarTurnosReservadosMedico(jo);
                    JSONArray ja = jo.getJSONArray(TURNOS_KEY);
                    for(int i=0; i<ja.length(); i++){
                        jo = ja.getJSONObject(i);
                        String s = "Paciente: " + jo.getString(NOMBRE_KEY) + " Hora: " + jo.getString(HORA_KEY) + "\n";
                        printInTextPane(s, turnosMedicoTextPane);
                    }

                    goTo(VISTA_TURNOS_RESERVADOS_POR_MEDICO);
                }
                else{
                    JOptionPane.showMessageDialog(null, jo.getString(ERROR_KEY));
                }
            }
        });
        /**
         * Método acción al presionar el botón "Volver"
         * Cambia a VISTA INICIO DE SESIÓN por VISTA PRINCIPAL
         */
        backSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goTo(VISTA_PRINCIPAL);
            }
        });

        /*---------------MÉTODOS ACCIÓN BOTONES VISTA CONSULTAR TURNO-----------------*/
        /**
         * Método acción al presionar el botón "Consultar"
         * Envia dni a consultar a Interfaz_Validacion_Turno para su validación
         * Si es valido: cambia VISTA CONSULTAR TURNO por VISTA TURNO CONSULTADO
         * Si es invalido: imprime mensaje de error
         */
        consultaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jo_consultado = new JSONObject();
                jo_consultado.put( DNI_KEY, dniConsulta.getText());
                jo_consultado = AgendaGUI.this.validadorTurnos.consultarTurno(jo_consultado);
                if (jo_consultado.getString(VALIDO_KEY).equals("si")){

                    goTo(VISTA_TURNO_CONSULTADO);

                    textoMostrarTurnoPanel.setText("Paciente: " + jo_consultado.getString(NOMBRE_KEY) + "\n" +
                            "Medicx: " + (AgendaGUI.this.validadorMedicx.getNomConID(jo_consultado).getString(NOMBRE_MEDICX_KEY) ) + "\n" +
                            "Hora: " + jo_consultado.getString(HORA_KEY) + "\n" +
                            "Método de Pago: " + jo_consultado.getString(METODO_DE_PAGO_KEY) + "\n");

                    textoMostrarTurnoPanel.setEditable(false);
                    tituloMostrarTurnoLabel.setText("Información del Turno:");

                    dniConsulta.setText("");
                }
                else {
                    JOptionPane.showMessageDialog(null, jo_consultado.getString(ERROR_KEY));
                }
            }
        });
        /**
         * Método acción al presionar el botón "Volver"
         * Cambia a VISTA CONSULTAR TURNO por VISTA PRINCIPAL
         */
        backConsultaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goTo(VISTA_PRINCIPAL);
                dniConsulta.setText("");
            }
        });

        /*---------------MÉTODOS ACCIÓN BOTONES VISTA CREAR TURNO-----------------*/
        /**
         * Método acción al presionar el botón "Consultar"
         * Envia datos para crear un nuevo turno a Interfaz_Validacion_Turno para su validación
         * Si son validos: imprime ticket del nuevo turno creado
         * Si son invalidos: imprime mensaje de error
         */
        reservarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JSONObject jo = new JSONObject();
                jo.put(NOMBRE_MEDICX_KEY, medicxsBox.getSelectedItem().toString());
                jo = AgendaGUI.this.validadorMedicx.getIDConNom(jo);
                jo.put(NOMBRE_KEY, nombrePaciente.getText());
                jo.put(DNI_KEY, dniCrear.getText());
                jo.put(HORA_KEY, horarioBox.getSelectedItem().toString());
                jo.put(METODO_DE_PAGO_KEY, pagoBox1.getSelectedItem().toString().toLowerCase(Locale.ROOT));
                jo.put(COSTO_KEY, ""+costoConsulta);
                if(pagoBox1.getSelectedItem().toString().equals("Efectivo")){
                    jo.put(MONTO_KEY, monto.getText());
                }
                else{
                    if (pagoBox1.getSelectedItem().toString().equals("Tarjeta De Debito")){
                        jo.put(NUMERO_TARJETA_KEY, numeroTarjetaDebito.getText());
                        jo.put(CODSEG_TARJETA_KEY, codSegTarjetaDebito.getText());
                        jo.put(VENCIMIENTO_TARJETA_KEY, mesDebitoBox.getSelectedItem().toString() + "/" + anioDebitoBox.getSelectedItem().toString());
                    }
                    else{
                        jo.put(NUMERO_TARJETA_KEY, numeroTarjetaCredito.getText());
                        jo.put(CODSEG_TARJETA_KEY, codSegTarjetaCredito.getText());
                        jo.put(VENCIMIENTO_TARJETA_KEY, mesCreditoBox.getSelectedItem().toString() + "/" + anioCreditoBox.getSelectedItem().toString());
                        jo.put(N_CUOTAS_KEY, cuotasBox.getSelectedItem().toString());
                    }
                }

                jo = AgendaGUI.this.validadorTurnos.crearTurno(jo);

                if(jo.getString(VALIDO_KEY).equals("si")){
                    //TODO finalizar implementación - imprimir ticket
                    JOptionPane.showMessageDialog(null, "Turno creado con exito!");
                    cleanCrearTurnoPanel();
                    jo.put(NOMBRE_MEDICX_KEY, medicxsBox.getSelectedItem().toString());
                    setHorarioModel(AgendaGUI.this.validadorMedicx.getIDConNom(jo).getString(NOMBRE_MEDICX_KEY));
                }
                else{
                    JOptionPane.showMessageDialog(null, jo.getString(ERROR_KEY));
                }
            }
        });
        /**
         * Método acción al presionar el botón "Volver"
         * Cambia a VISTA CREAR TURNO por VISTA PRINCIPAL
         */
        backCrearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goTo(VISTA_PRINCIPAL);
                cleanCrearTurnoPanel();
            }
        });
        /**
         * Método acción al seleccionar una opccion de desplegable de "Medicxs"
         * Llama a la funcion setHorarioModel para setear las opciones disponibles en el desplegable "Horario:" para el medico seleccionado
         */
        medicxsBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setHorarioModel(medicxsBox.getSelectedItem().toString());
            }
        });
        /*
         * TODO documentar
         */
        pagoBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                efectivoPanel.setVisible(false);
                debitoPanel.setVisible(false);
                creditoPanel.setVisible(false);

                switch (pagoBox1.getSelectedItem().toString()){
                    case "Efectivo":            efectivoPanel.setVisible(true); break;
                    case "Tarjeta De Debito":   debitoPanel.setVisible(true);   break;
                    case "Tarjeta De Credito":  creditoPanel.setVisible(true);  break;
                }
            }
        });

        /*---------------MÉTODOS ACCIÓN BOTONES VISTA TURNOS RESERVADOS POR MEDICO-----------------*/
        /**
         * Método acción al presionar el botón "Volver"
         * Cambia a VISTA TURNOS RESERVADOS POR MÉDICO por VISTA INICIO DE SESIÓN
         */
        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goTo(VISTA_INICIO_DE_SESION);

                turnosMedicoTextPane.setText("");
                password.setText("");
                matricula.setText("");
                passwordIncorrectaLabel.setText("");
                matriculaIncorrectaLabel.setText("");
            }
        });

        /*---------------MÉTODOS ACCIÓN BOTONES VISTA TURNO CONSULTADO-----------------*/
        /**
         * Método acción al presionar el botón "Cambiar Metodo De Pago"
         * Cambia VISTA TURNO CONSULTADO por VISTA CAMBIAR METODO DE PAGO
         */
        cambiarMetodoDePagoButton.addActionListener(new ActionListener() {
            @Override //TODO implementar
            public void actionPerformed(ActionEvent actionEvent) {
                goTo(VISTA_CAMBIAR_METODO_DE_PAGO);
            }
        });
        /**
         * Método acción al presionar el botón "Volver"
         * Cambia a VISTA TURNO CONSULTADO por VISTA PRINCIPAL
         */
        backButtonMostarTurno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goTo(VISTA_PRINCIPAL);
            }
        });

        /*---------------MÉTODOS ACCIÓN BOTONES VISTA CAMBIAR METODO DE PAGO-----------------*/
        /**
         * Método acción al presionar el botón "Realizar Cambio"
         * Envia datos para cambiar metodo de pago del turno consultado a Interfaz_Validacion_Turno para su validación
         * Si son validos: imprime ticket del turno con los nuevos datos
         * Si son invalidos: imprime mensaje de error
         */
        realizarCambioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jo_consultado.put(METODO_DE_PAGO_KEY, pagoBox2.getSelectedItem().toString().toLowerCase(Locale.ROOT));
                jo_consultado.put(COSTO_KEY, ""+costoConsulta);
                if(pagoBox2.getSelectedItem().toString().equals("Efectivo")){
                    jo_consultado.put(MONTO_KEY, cambiarEfectivoMonto.getText());
                }
                else{
                    if (pagoBox1.getSelectedItem().toString().equals("Tarjeta De Debito")){
                        jo_consultado.put(NUMERO_TARJETA_KEY, numeroTarjetaDebitoCambiar.getText());
                        jo_consultado.put(CODSEG_TARJETA_KEY, codSegTarjetaDebitoCambiar.getText());
                        jo_consultado.put(VENCIMIENTO_TARJETA_KEY, mesDebitoCambiarBox.getSelectedItem().toString() + "/" + anioDebitoCambiarBox.getSelectedItem().toString());
                    }
                    else{
                        jo_consultado.put(NUMERO_TARJETA_KEY, numeroTarjetaCreditoCambiar.getText());
                        jo_consultado.put(CODSEG_TARJETA_KEY, codSegTarjetaCreditoCambiar.getText());
                        jo_consultado.put(VENCIMIENTO_TARJETA_KEY, mesCreditoCambiarBox.getSelectedItem().toString() + "/" + anioCreditoCambiarBox.getSelectedItem().toString());
                        jo_consultado.put(N_CUOTAS_KEY, cuotasCambiarBox.getSelectedItem().toString());
                    }
                }

                jo_consultado = AgendaGUI.this.validadorTurnos.cambiarMetodoPago(jo_consultado);

                if(jo_consultado.getString(VALIDO_KEY).equals("si")){
                    //TODO finalizar implementación - imprimir ticket
                    JOptionPane.showMessageDialog(null, "Cambio realizado con exito!");
                    cleanCambiarMetodoDePagoPanel();
                    goTo(VISTA_PRINCIPAL);
                }
                else{
                    JOptionPane.showMessageDialog(null, jo_consultado.getString(ERROR_KEY));
                }
            }
        });
        /**
         * Método acción al presionar el botón "Volver"
         * Cambia a VISTA AMBIAR METODO DE PAGO por VISTA TURNO CONSULTADO
         */
        backCambiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goTo(VISTA_TURNO_CONSULTADO);
                cleanCambiarMetodoDePagoPanel();
            }
        });

        //TODO documentar
        pagoBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cambiarEfectivoPanel.setVisible(false);
                cambiarDebitoPanel.setVisible(false);
                cambiarCreditoPanel.setVisible(false);

                switch (pagoBox2.getSelectedItem().toString()){
                    case "Efectivo":            cambiarEfectivoPanel.setVisible(true); break;
                    case "Tarjeta De Debito":   cambiarDebitoPanel.setVisible(true);   break;
                    case "Tarjeta De Credito":  cambiarCreditoPanel.setVisible(true);  break;
                }
            }
        });
    }

    /**
     * Método de seteo de opciones para JComboBox "Horarios:"
     * Consulta los horarios disponibles de ese medico y genera el desplegable de opciones
     *
     * @param: String nombre_medico - Nombre del medico seleccionado en JComboBox "Medicxs"
     */
    private int setHorarioModel(String nombre_medico){
        JSONObject jo = new JSONObject();
        jo.put(NOMBRE_MEDICX_KEY, nombre_medico);
        jo = AgendaGUI.this.validadorMedicx.getIDConNom(jo);
        jo = AgendaGUI.this.validadorTurnos.consultarTurnosDisponiblesMedico(jo);

        if(jo.getString(VALIDO_KEY).equals("si")){
            JSONArray ja = jo.getJSONArray(TURNOS_KEY);
            String[] s = new String[ja.length()];
            for(int i=0; i<ja.length(); i++){
                jo = ja.getJSONObject(i);
                s[i] = jo.getString(HORA_KEY);
            }
            horarioBox.setModel(new DefaultComboBoxModel(s));
            return 1;
        }
        else{
            String[] s = {"No existen turnos disponibles"};
            horarioBox.setModel(new DefaultComboBoxModel(s));
            return 0;
        }
    }
    /**
     * Metodo de limpieza de la VISTA CREAR TURNO
     * Regresa todos los campos al valor original
     */
    private void cleanCrearTurnoPanel(){
        nombrePaciente.setText("");
        dniCrear.setText("");
        monto.setText("");
        pagoBox1.setSelectedItem("Efectivo");
        numeroTarjetaDebito.setText("");
        codSegTarjetaDebito.setText("");
        mesDebitoBox.setSelectedItem("1");
        anioDebitoBox.setSelectedItem("2020");
        numeroTarjetaCredito.setText("");
        codSegTarjetaCredito.setText("");
        mesCreditoBox.setSelectedItem("1");
        anioCreditoBox.setSelectedItem("2020");
        cuotasBox.setSelectedItem("1");
    }

    /**
     * Metodo de limpieza de la VISTA CAMBIAR METODO DE PAGO
     * Regresa todos los campos al valor original
     */
    private void cleanCambiarMetodoDePagoPanel(){
        pagoBox2.setSelectedItem("Efectivo");
        cambiarEfectivoMonto.setText("");
        numeroTarjetaDebitoCambiar.setText("");
        codSegTarjetaDebitoCambiar.setText("");
        mesDebitoCambiarBox.setSelectedItem("1");
        anioDebitoCambiarBox.setSelectedItem("2020");
        numeroTarjetaCreditoCambiar.setText("");
        codSegTarjetaCreditoCambiar.setText("");
        mesCreditoCambiarBox.setSelectedItem("1");
        anioCreditoCambiarBox.setSelectedItem("2020");
        cuotasCambiarBox.setSelectedItem("1");
    }

    /**
     * Metodo de escritura de texto sobre paneles de la vista
     *
     * @param s  - Texto a ser impreso en pantalla
     * @param tp - Panel donde se debe escribir el texto
     */
    private void printInTextPane(String s, JTextPane tp){
        tp.setEditable(true);
        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.replaceSelection(s);
        tp.setEditable(false);
    }

    /**
     * Metodo de cambio de vistas
     *
     * @param vista - Vista que desea ser activada
     */
    private void goTo(String vista){
        mainPanel.setVisible(false);
        sesionPanel.setVisible(false);
        consultaPanel.setVisible(false);
        crearPanel.setVisible(false);
        turnosReservMedicxPanel.setVisible(false);
        mostrarTurnoConsultadoPanel.setVisible(false);
        cambiarMetodoPagoPanel.setVisible(false);

        switch (vista){
            case VISTA_INICIO_DE_SESION: sesionPanel.setVisible(true); break;
            case VISTA_CONSULTAR_TURNO: consultaPanel.setVisible(true); break;
            case VISTA_CREAR_TURNO: crearPanel.setVisible(true); break;
            case VISTA_TURNOS_RESERVADOS_POR_MEDICO: turnosReservMedicxPanel.setVisible(true); break;
            case VISTA_TURNO_CONSULTADO: mostrarTurnoConsultadoPanel.setVisible(true); break;
            case VISTA_CAMBIAR_METODO_DE_PAGO: cambiarMetodoPagoPanel.setVisible(true); break;
            default: mainPanel.setVisible(true); break;
        }
    }

    /**
     * Método de inicializacion de la GUI
     * carga las vistas
     */
    private void initGUI(){
        mainButtonsPanel.setVisible(true);
        matriculaSesionPanel.setVisible(true);
        matriculaLabel.setText("Matricula:");
        buttonsSesionPanel.setVisible(true);
        passwordSesionPanel.setVisible(true);
        passwordLabel.setText("Password: ");
        dniConsultaPanel.setVisible(true);
        dniLabel1.setText("DNI:");
        buttonsConsultaPanel.setVisible(true);
        buttonsPagoPanel.setVisible(true);
        ingresoDatosCrearPanel.setVisible(true);
        datosPacientePagoPanel.setVisible(true);
        nombreLabel.setText("Nombre:");
        dniLabel2.setText("DNI:");
        medicoLabel.setText("Medicx:");
        horarioLabel.setText("Horario:");
        pagoLabel1.setText("Forma De Pago:");
        costoConsultaLabel1.setText("Valor consulta: $" + costoConsulta);
        metodoPagoPanel.setVisible(true);
        efectivoLabel.setText("Monto:");
        numeroDebitoLabel.setText("Nº Tarjeta:");
        codSegDebitoLabel.setText("Cod Seguridad:");
        vencimientoDebitoLabel.setText("Vencimiento:");
        vencimientoDebitoPanel.setVisible(true);
        vencimientoCreditoPanel.setVisible(true);
        numeroCreditoLabel.setText("Nº Tarjeta:");
        vencimientoCreditoLabel.setText("Vencimiento:");
        codSegCreditoLabel.setText("Cod Seguridad:");
        cuotasCreditoLabel.setText("Nº Cuotas:");
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        infoTurnoConsultadoPanel.setVisible(true);
        buttosMostrarTurnoPanel.setVisible(true);
        cambiarButtonsPanel.setVisible(true);
        pagoLabel2.setText("Forma De Pago:");
        costoConsultaLabel2.setText("Valor consulta: $" + costoConsulta);
        cambiarEfectivoMontoLabel.setText("Monto:");
        numeroTarjetaDebitoCambiarLabel.setText("Nº Tarjeta:");
        vencimientoDebitoCambiarLabel.setText("Vencimiento:");
        codSegDebitoCambiarLabel.setText("Cod Seguridad:");
        numeroTarjetaCreditoCambiarLabel.setText("Nº Tarjeta:");
        vencimientoCreditoCambiarLabel.setText("Vencimiento:");
        codSegCreditoCambiarLabel.setText("Cod Seguridad:");
        cuotasCreditoCambiarLabel.setText("Nº Cuotas:");
        nuevoMetodoPagoPanel.setVisible(true);

        String[] arr = new String[id_medicos.size()];
        for(int i=0; i<id_medicos.size(); i++){
            JSONObject jo = new JSONObject();
            jo.put(ID_MEDICX_KEY, id_medicos.get(i));
            arr[i] = validadorMedicx.getNomConID(jo).getString(NOMBRE_MEDICX_KEY);
        }
        medicxsBox.setModel(new DefaultComboBoxModel(arr));

        JSONObject jo = new JSONObject();
        if(id_medicos.size() > 0){
            jo.put(ID_MEDICX_KEY, id_medicos.get(0));
            setHorarioModel(validadorMedicx.getNomConID(jo).getString(NOMBRE_MEDICX_KEY));
        }

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        ViewPanel = new JPanel();
        ViewPanel.setLayout(new java.awt.CardLayout(0, 0));
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        ViewPanel.add(mainPanel, "Card1");
        mainButtonsPanel = new JPanel();
        mainButtonsPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 1, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        mainPanel.add(mainButtonsPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        iniciarSesion = new JButton("Iniciar Sesión");
        mainButtonsPanel.add(iniciarSesion, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(50, 50), null, 0, false));
        consultarTurnoButton = new JButton("Consultar Turno");
        mainButtonsPanel.add(consultarTurnoButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(50, 50), null, 0, false));
        crearTurnoButton = new JButton("Reservar Turno");
        mainButtonsPanel.add(crearTurnoButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(50, 50), null, 0, false));
        verTurnosButton = new JButton("Ver Turnos Disponibles");
        mainButtonsPanel.add(verTurnosButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(50, 50), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        sesionPanel = new JPanel();
        sesionPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        ViewPanel.add(sesionPanel, "Card2");
        matriculaSesionPanel = new JPanel();
        matriculaSesionPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 5, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        sesionPanel.add(matriculaSesionPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        matriculaLabel = new JLabel();
        matriculaSesionPanel.add(matriculaLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        matriculaIncorrectaLabel = new JLabel();
        matriculaSesionPanel.add(matriculaIncorrectaLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(10, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        matriculaSesionPanel.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        matriculaSesionPanel.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        matricula = new JTextField();
        matriculaSesionPanel.add(matricula, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, -1), null, 0, false));
        buttonsSesionPanel = new JPanel();
        buttonsSesionPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 5, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        sesionPanel.add(buttonsSesionPanel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        buttonsSesionPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        backSesionButton = new JButton("Volver");
        panel1.add(backSesionButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, 30), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        buttonsSesionPanel.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        loginButton = new JButton("Iniciar Sesion");
        panel2.add(loginButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, 30), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        buttonsSesionPanel.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        buttonsSesionPanel.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer7 = new com.intellij.uiDesigner.core.Spacer();
        buttonsSesionPanel.add(spacer7, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        passwordSesionPanel = new JPanel();
        passwordSesionPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 5, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        sesionPanel.add(passwordSesionPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        passwordLabel = new JLabel();
        passwordSesionPanel.add(passwordLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        password = new JPasswordField();
        passwordSesionPanel.add(password, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, -1), null, 0, false));
        passwordIncorrectaLabel = new JLabel();
        passwordSesionPanel.add(passwordIncorrectaLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(10, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer8 = new com.intellij.uiDesigner.core.Spacer();
        passwordSesionPanel.add(spacer8, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer9 = new com.intellij.uiDesigner.core.Spacer();
        passwordSesionPanel.add(spacer9, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        consultaPanel = new JPanel();
        consultaPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        ViewPanel.add(consultaPanel, "Card3");
        dniConsultaPanel = new JPanel();
        dniConsultaPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 4, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        consultaPanel.add(dniConsultaPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        dniLabel1 = new JLabel();
        dniConsultaPanel.add(dniLabel1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dniConsulta = new JTextField();
        dniConsultaPanel.add(dniConsulta, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer10 = new com.intellij.uiDesigner.core.Spacer();
        dniConsultaPanel.add(spacer10, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer11 = new com.intellij.uiDesigner.core.Spacer();
        dniConsultaPanel.add(spacer11, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        buttonsConsultaPanel = new JPanel();
        buttonsConsultaPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 5, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        consultaPanel.add(buttonsConsultaPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        buttonsConsultaPanel.add(panel3, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        backConsultaButton = new JButton("Volver");
        panel3.add(backConsultaButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, 30), null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        buttonsConsultaPanel.add(panel4, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        consultaButton = new JButton("Consultar");
        panel4.add(consultaButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, 30), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer12 = new com.intellij.uiDesigner.core.Spacer();
        buttonsConsultaPanel.add(spacer12, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer13 = new com.intellij.uiDesigner.core.Spacer();
        buttonsConsultaPanel.add(spacer13, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer14 = new com.intellij.uiDesigner.core.Spacer();
        buttonsConsultaPanel.add(spacer14, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        crearPanel = new JPanel();
        crearPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        ViewPanel.add(crearPanel, "Card4");
        buttonsPagoPanel = new JPanel();
        buttonsPagoPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 5, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        crearPanel.add(buttonsPagoPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        buttonsPagoPanel.add(panel5, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        backCrearButton = new JButton("Volver");
        panel5.add(backCrearButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, 30), null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        buttonsPagoPanel.add(panel6, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        reservarButton = new JButton("Reservar");
        panel6.add(reservarButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, 30), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer15 = new com.intellij.uiDesigner.core.Spacer();
        buttonsPagoPanel.add(spacer15, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer16 = new com.intellij.uiDesigner.core.Spacer();
        buttonsPagoPanel.add(spacer16, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer17 = new com.intellij.uiDesigner.core.Spacer();
        buttonsPagoPanel.add(spacer17, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        ingresoDatosCrearPanel = new JPanel();
        ingresoDatosCrearPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 3, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        crearPanel.add(ingresoDatosCrearPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        datosPacientePagoPanel = new JPanel();
        datosPacientePagoPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 4, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        ingresoDatosCrearPanel.add(datosPacientePagoPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        pagoBox1 = new JComboBox();
        datosPacientePagoPanel.add(pagoBox1, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, -1), null, 0, false));
        nombrePaciente = new JTextField();
        datosPacientePagoPanel.add(nombrePaciente, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, -1), null, 0, false));
        dniCrear = new JTextField();
        datosPacientePagoPanel.add(dniCrear, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, -1), null, 0, false));
        medicxsBox = new JComboBox();
        datosPacientePagoPanel.add(medicxsBox, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, -1), null, 0, false));
        horarioBox = new JComboBox();
        datosPacientePagoPanel.add(horarioBox, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, -1), null, 0, false));
        nombreLabel = new JLabel();
        datosPacientePagoPanel.add(nombreLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dniLabel2 = new JLabel();
        datosPacientePagoPanel.add(dniLabel2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        medicoLabel = new JLabel();
        datosPacientePagoPanel.add(medicoLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        horarioLabel = new JLabel();
        datosPacientePagoPanel.add(horarioLabel, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pagoLabel1 = new JLabel();
        datosPacientePagoPanel.add(pagoLabel1, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(120, -1), null, 0, false));
        costoConsultaLabel1 = new JLabel();
        datosPacientePagoPanel.add(costoConsultaLabel1, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer18 = new com.intellij.uiDesigner.core.Spacer();
        datosPacientePagoPanel.add(spacer18, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        metodoPagoPanel = new JPanel();
        metodoPagoPanel.setLayout(new java.awt.CardLayout(0, 0));
        ingresoDatosCrearPanel.add(metodoPagoPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        efectivoPanel = new JPanel();
        efectivoPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        metodoPagoPanel.add(efectivoPanel, "Card1");
        efectivoLabel = new JLabel();
        efectivoPanel.add(efectivoLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(120, -1), null, 0, false));
        monto = new JTextField();
        efectivoPanel.add(monto, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(160, -1), null, 0, false));
        debitoPanel = new JPanel();
        debitoPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 2, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        metodoPagoPanel.add(debitoPanel, "Card2");
        numeroTarjetaDebito = new JTextField();
        debitoPanel.add(numeroTarjetaDebito, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, -1), null, 0, false));
        codSegTarjetaDebito = new JTextField();
        debitoPanel.add(codSegTarjetaDebito, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, -1), null, 0, false));
        numeroDebitoLabel = new JLabel();
        debitoPanel.add(numeroDebitoLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        codSegDebitoLabel = new JLabel();
        debitoPanel.add(codSegDebitoLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(120, -1), null, 0, false));
        vencimientoDebitoLabel = new JLabel();
        debitoPanel.add(vencimientoDebitoLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        vencimientoDebitoPanel = new JPanel();
        vencimientoDebitoPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        debitoPanel.add(vencimientoDebitoPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        mesDebitoBox = new JComboBox();
        vencimientoDebitoPanel.add(mesDebitoBox, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        anioDebitoBox = new JComboBox();
        vencimientoDebitoPanel.add(anioDebitoBox, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer19 = new com.intellij.uiDesigner.core.Spacer();
        vencimientoDebitoPanel.add(spacer19, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        creditoPanel = new JPanel();
        creditoPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        metodoPagoPanel.add(creditoPanel, "Card3");
        numeroTarjetaCredito = new JTextField();
        creditoPanel.add(numeroTarjetaCredito, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, -1), null, 0, false));
        codSegTarjetaCredito = new JTextField();
        creditoPanel.add(codSegTarjetaCredito, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, -1), null, 0, false));
        vencimientoCreditoPanel = new JPanel();
        vencimientoCreditoPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        creditoPanel.add(vencimientoCreditoPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        mesCreditoBox = new JComboBox();
        vencimientoCreditoPanel.add(mesCreditoBox, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        anioCreditoBox = new JComboBox();
        vencimientoCreditoPanel.add(anioCreditoBox, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer20 = new com.intellij.uiDesigner.core.Spacer();
        vencimientoCreditoPanel.add(spacer20, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        numeroCreditoLabel = new JLabel();
        creditoPanel.add(numeroCreditoLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        vencimientoCreditoLabel = new JLabel();
        creditoPanel.add(vencimientoCreditoLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        codSegCreditoLabel = new JLabel();
        creditoPanel.add(codSegCreditoLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(120, -1), null, 0, false));
        cuotasBox = new JComboBox();
        creditoPanel.add(cuotasBox, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(20, -1), null, 0, false));
        cuotasCreditoLabel = new JLabel();
        creditoPanel.add(cuotasCreditoLabel, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JToolBar.Separator toolBar$Separator1 = new JToolBar.Separator();
        ingresoDatosCrearPanel.add(toolBar$Separator1, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JToolBar.Separator toolBar$Separator2 = new JToolBar.Separator();
        ingresoDatosCrearPanel.add(toolBar$Separator2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        turnosReservMedicxPanel = new JPanel();
        turnosReservMedicxPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        ViewPanel.add(turnosReservMedicxPanel, "Card5");
        scrollPanel = new JScrollPane();
        turnosReservMedicxPanel.add(scrollPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 2, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        scrollPanel.setViewportView(panel7);
        nombreDoctor = new JLabel();
        panel7.add(nombreDoctor, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        turnosMedicoTextPane = new JTextPane();
        panel7.add(turnosMedicoTextPane, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new java.awt.Dimension(150, 50), null, 0, false));
        final JToolBar.Separator toolBar$Separator3 = new JToolBar.Separator();
        panel7.add(toolBar$Separator3, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        turnosReservMedicxPanel.add(panel8, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        cerrarSesionButton = new JButton();
        panel8.add(cerrarSesionButton, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, 30), null, 1, false));
        final com.intellij.uiDesigner.core.Spacer spacer21 = new com.intellij.uiDesigner.core.Spacer();
        panel8.add(spacer21, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer22 = new com.intellij.uiDesigner.core.Spacer();
        panel8.add(spacer22, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        mostrarTurnoConsultadoPanel = new JPanel();
        mostrarTurnoConsultadoPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        ViewPanel.add(mostrarTurnoConsultadoPanel, "Card6");
        infoTurnoConsultadoPanel = new JPanel();
        infoTurnoConsultadoPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 3, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        mostrarTurnoConsultadoPanel.add(infoTurnoConsultadoPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textoMostrarTurnoPanel = new JTextPane();
        infoTurnoConsultadoPanel.add(textoMostrarTurnoPanel, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new java.awt.Dimension(150, 50), null, 0, false));
        tituloMostrarTurnoLabel = new JLabel();
        infoTurnoConsultadoPanel.add(tituloMostrarTurnoLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer23 = new com.intellij.uiDesigner.core.Spacer();
        infoTurnoConsultadoPanel.add(spacer23, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer24 = new com.intellij.uiDesigner.core.Spacer();
        infoTurnoConsultadoPanel.add(spacer24, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer25 = new com.intellij.uiDesigner.core.Spacer();
        infoTurnoConsultadoPanel.add(spacer25, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        buttosMostrarTurnoPanel = new JPanel();
        buttosMostrarTurnoPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 5, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        mostrarTurnoConsultadoPanel.add(buttosMostrarTurnoPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel9 = new JPanel();
        panel9.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        buttosMostrarTurnoPanel.add(panel9, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        backButtonMostarTurno = new JButton("Volver");
        panel9.add(backButtonMostarTurno, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, 30), null, 0, false));
        final JPanel panel10 = new JPanel();
        panel10.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        buttosMostrarTurnoPanel.add(panel10, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        cambiarMetodoDePagoButton = new JButton("Cambiar Metodo De Pago");
        panel10.add(cambiarMetodoDePagoButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, 30), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer26 = new com.intellij.uiDesigner.core.Spacer();
        buttosMostrarTurnoPanel.add(spacer26, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer27 = new com.intellij.uiDesigner.core.Spacer();
        buttosMostrarTurnoPanel.add(spacer27, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer28 = new com.intellij.uiDesigner.core.Spacer();
        buttosMostrarTurnoPanel.add(spacer28, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        cambiarMetodoPagoPanel = new JPanel();
        cambiarMetodoPagoPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 3, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        ViewPanel.add(cambiarMetodoPagoPanel, "Card7");
        nuevoMetodoPagoPanel = new JPanel();
        nuevoMetodoPagoPanel.setLayout(new java.awt.CardLayout(0, 0));
        cambiarMetodoPagoPanel.add(nuevoMetodoPagoPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        cambiarEfectivoPanel = new JPanel();
        cambiarEfectivoPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        nuevoMetodoPagoPanel.add(cambiarEfectivoPanel, "Card1");
        cambiarEfectivoMonto = new JTextField();
        cambiarEfectivoPanel.add(cambiarEfectivoMonto, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(160, -1), null, 0, false));
        cambiarEfectivoMontoLabel = new JLabel();
        cambiarEfectivoPanel.add(cambiarEfectivoMontoLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(120, -1), null, 0, false));
        cambiarDebitoPanel = new JPanel();
        cambiarDebitoPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 2, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        nuevoMetodoPagoPanel.add(cambiarDebitoPanel, "Card2");
        numeroTarjetaDebitoCambiar = new JTextField();
        cambiarDebitoPanel.add(numeroTarjetaDebitoCambiar, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, -1), null, 0, false));
        codSegTarjetaDebitoCambiar = new JTextField();
        cambiarDebitoPanel.add(codSegTarjetaDebitoCambiar, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, -1), null, 0, false));
        numeroTarjetaDebitoCambiarLabel = new JLabel();
        cambiarDebitoPanel.add(numeroTarjetaDebitoCambiarLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(120, -1), null, 0, false));
        codSegDebitoCambiarLabel = new JLabel();
        cambiarDebitoPanel.add(codSegDebitoCambiarLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(120, -1), null, 0, false));
        vencimientoDebitoCambiarLabel = new JLabel();
        cambiarDebitoPanel.add(vencimientoDebitoCambiarLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(120, -1), null, 0, false));
        final JPanel panel11 = new JPanel();
        panel11.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        cambiarDebitoPanel.add(panel11, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        mesDebitoCambiarBox = new JComboBox();
        panel11.add(mesDebitoCambiarBox, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        anioDebitoCambiarBox = new JComboBox();
        panel11.add(anioDebitoCambiarBox, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer29 = new com.intellij.uiDesigner.core.Spacer();
        panel11.add(spacer29, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        cambiarCreditoPanel = new JPanel();
        cambiarCreditoPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(4, 2, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        nuevoMetodoPagoPanel.add(cambiarCreditoPanel, "Card3");
        numeroTarjetaCreditoCambiar = new JTextField();
        cambiarCreditoPanel.add(numeroTarjetaCreditoCambiar, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, -1), null, 0, false));
        codSegTarjetaCreditoCambiar = new JTextField();
        cambiarCreditoPanel.add(codSegTarjetaCreditoCambiar, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, -1), null, 0, false));
        final JPanel panel12 = new JPanel();
        panel12.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        cambiarCreditoPanel.add(panel12, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        mesCreditoCambiarBox = new JComboBox();
        panel12.add(mesCreditoCambiarBox, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        anioCreditoCambiarBox = new JComboBox();
        panel12.add(anioCreditoCambiarBox, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer30 = new com.intellij.uiDesigner.core.Spacer();
        panel12.add(spacer30, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        numeroTarjetaCreditoCambiarLabel = new JLabel();
        cambiarCreditoPanel.add(numeroTarjetaCreditoCambiarLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(120, -1), null, 0, false));
        vencimientoCreditoCambiarLabel = new JLabel();
        cambiarCreditoPanel.add(vencimientoCreditoCambiarLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(120, -1), null, 0, false));
        codSegCreditoCambiarLabel = new JLabel();
        cambiarCreditoPanel.add(codSegCreditoCambiarLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(120, -1), null, 0, false));
        cuotasCambiarBox = new JComboBox();
        cambiarCreditoPanel.add(cuotasCambiarBox, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(20, -1), null, 0, false));
        cuotasCreditoCambiarLabel = new JLabel();
        cambiarCreditoPanel.add(cuotasCreditoCambiarLabel, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(120, -1), null, 0, false));
        cambiarButtonsPanel = new JPanel();
        cambiarButtonsPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 5, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        cambiarMetodoPagoPanel.add(cambiarButtonsPanel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel13 = new JPanel();
        panel13.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        cambiarButtonsPanel.add(panel13, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        backCambiarButton = new JButton("Volver");
        panel13.add(backCambiarButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, 30), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer31 = new com.intellij.uiDesigner.core.Spacer();
        cambiarButtonsPanel.add(spacer31, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel14 = new JPanel();
        panel14.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        cambiarButtonsPanel.add(panel14, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        realizarCambioButton = new JButton("Realizar Cambio");
        panel14.add(realizarCambioButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(150, 30), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer32 = new com.intellij.uiDesigner.core.Spacer();
        cambiarButtonsPanel.add(spacer32, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer33 = new com.intellij.uiDesigner.core.Spacer();
        cambiarButtonsPanel.add(spacer33, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer34 = new com.intellij.uiDesigner.core.Spacer();
        cambiarMetodoPagoPanel.add(spacer34, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer35 = new com.intellij.uiDesigner.core.Spacer();
        cambiarMetodoPagoPanel.add(spacer35, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel15 = new JPanel();
        panel15.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 3, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        cambiarMetodoPagoPanel.add(panel15, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        pagoLabel2 = new JLabel();
        panel15.add(pagoLabel2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new java.awt.Dimension(120, -1), null, 0, false));
        pagoBox2 = new JComboBox();
        panel15.add(pagoBox2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        costoConsultaLabel2 = new JLabel();
        panel15.add(costoConsultaLabel2, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return ViewPanel;
    }
}
