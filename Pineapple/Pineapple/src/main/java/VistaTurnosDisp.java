import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import org.json.JSONArray;
import org.json.JSONObject;

public class VistaTurnosDisp extends JFrame implements Observer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String ID_MEDICX_KEY = "id medico";
    private static final String NOMBRE_MEDICX_KEY = "nombre del medico";
    private static final String VALIDO_KEY = "valido";
    private ArrayList<String> id_medicos;
    private final Interfaz_Validacion_Turno validadorTurnos;
    private final Interfaz_Validacion_Medicx validadorMedicxs;
    private JPanel mainPanel;
    private JTextPane turnosTextPane;
    private JLabel titleLabel;
    private JScrollPane turnosScrollPane;

    public VistaTurnosDisp(String title, Interfaz_Validacion_Turno validadorTurnos,
            Interfaz_Validacion_Medicx validadorMedicxs, ArrayList<String> id_medicos) {
        super(title);
        this.validadorTurnos = validadorTurnos;
        this.validadorMedicxs = validadorMedicxs;
        this.id_medicos = id_medicos;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        titleLabel.setText("TURNOS DISPONIBLES DIA " + dtf.format(now));
        turnosTextPane.setEditable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit();
            }
        });
        this.setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(700, 500));
        this.pack();
        validadorTurnos.registerObserver(VistaTurnosDisp.this);
    }

    public void cargarVistaContent() {
        JSONObject jo = validadorTurnos.consultarTurnosDiponibles();
        turnosTextPane.setText("");
        if (jo.getString(VALIDO_KEY).equals("si")) {
            JSONArray ja = jo.getJSONArray("medicos");
            for (String id_medico : id_medicos) {
                JSONObject consult = new JSONObject();
                consult.put(ID_MEDICX_KEY, id_medico);
                consult = validadorMedicxs.getNomConID(consult);
                printInTextPane("Medicx: " + consult.getString(NOMBRE_MEDICX_KEY) + "\n");
                consult = ja.getJSONObject(Integer.parseInt(id_medico));
                JSONArray list = consult.getJSONArray(id_medico); // jo.get(id_medicos.get(i));
                for (int j = 0; j < list.length(); j++) {
                    String s = list.getString(j);
                    printInTextPane("\t\t" + s + "\n");
                }
            }
        }
        turnosScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    private void printInTextPane(String s) {
        int len = turnosTextPane.getDocument().getLength();
        turnosTextPane.setEditable(true);
        turnosTextPane.setCaretPosition(len);
        turnosTextPane.replaceSelection(s);
        turnosTextPane.setEditable(false);
    }

    @Override
    public void update() {
        cargarVistaContent();
    }

    private void onExit() {
        validadorTurnos.removeObserver(VistaTurnosDisp.this);
        VistaTurnosDisp.this.dispose();
    }

    {
        // GUI initializer generated by IntelliJ IDEA GUI Designer
        // >>> IMPORTANT!! <<<
        // DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer >>> IMPORTANT!! <<< DO NOT
     * edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(
                new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        turnosScrollPane = new JScrollPane();
        mainPanel.add(turnosScrollPane,
                new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1,
                        com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
                        com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
                        com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
                                | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
                        com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
                                | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
                        null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(
                new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new java.awt.Insets(0, 0, 0, 0), -1, -1));
        turnosScrollPane.setViewportView(panel1);
        titleLabel = new JLabel();
        titleLabel.setText("Label");
        panel1.add(titleLabel,
                new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1,
                        com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
                        com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
                        com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
                        com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        turnosTextPane = new JTextPane();
        panel1.add(turnosTextPane,
                new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1,
                        com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
                        com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
                        com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
                        com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null,
                        new java.awt.Dimension(150, 50), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
