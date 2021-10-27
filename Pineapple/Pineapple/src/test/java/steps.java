
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.assertj.swing.core.BasicComponentFinder;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.finder.JOptionPaneFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JOptionPaneFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;

import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.InvocationTargetException;

import static org.assertj.swing.core.BasicComponentFinder.finderWithCurrentAwtHierarchy;
import static org.assertj.swing.core.matcher.JButtonMatcher.withText;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class steps {
    private FrameFixture window;

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @Before
    public void setup() throws Exception {
        Agenda agenda = new Agenda();
        Gestor_Medicx gestor = new Gestor_Medicx();
        Validacion_Medicx validadorMedicxs = new Validacion_Medicx();
        Validacion_Turno validadorTurno = new Validacion_Turno();
        AgendaGUI frame = GuiActionRunner.execute(() -> new AgendaGUI("My Agenda", validadorTurno, validadorMedicxs, gestor));
        window = new FrameFixture(frame);
        window.show(); // shows the frame to test
        Thread.sleep(2000);
    }

    @Given("el usuario está en la pantalla principal")
    public void el_usuario_está_en_la_pantalla_principal() {

    }

    @When("el usuario cliquea solicitar turno")
    public void el_usuario_cliquea_solicitar_turno() {
        window.button(withText("Reservar Turno")).click();
    }

    @Then("aparece la ventana para ingresar los datos del turno")
    public void aparece_la_ventana_para_ingresar_los_datos_del_turno() {
        assertTrue(window.panel("crearPanel").target().isShowing());

    }

    @Given("el usuario está en la vista de crear turno")
    public void el_usuario_está_en_la_vista_de_crear_turno() {
        el_usuario_cliquea_solicitar_turno();
    }
    @When("el usuario ingresa nombre en crearPanel")
    public void el_usuario_ingresa_nombre_en_crear_panel() {
        window.textBox("nombrePaciente").enterText("nico");
    }
    @When("el usuario ingresa DNI en crearPanel")
    public void el_usuario_ingresa_dni_en_crear_panel() {
        window.textBox("dniCrear").enterText("123456");
    }
    @When("el usuario ingresa Medicx en crearPanel")
    public void el_usuario_ingresa_medicx_en_crear_panel() {
        window.comboBox("medicxsBox").selectItem("Peter Parker");
    }
    @When("el usuario ingresa monto en crearPanel")
    public void el_usuario_ingresa_monto_en_crear_panel() {
        window.textBox("monto").enterText("200");
    }
    @When("el usuario ingresa monto insuficiente en crearPanel")
    public void el_usuario_ingresa_monto_insuficiente_en_crearPanel(){
        window.textBox("monto").enterText("100");
    }
    @When("el usuario cliquea en reservar")
    public void el_usuario_cliquea_en_reservar() {
        window.button(withText("Reservar")).click();
    }
    @Then("aparece un mensaje de turno creado con exito")
    public void aparece_un_mensaje_de_turno_creado_con_exito() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JOptionPaneFixture optionPane = JOptionPaneFinder.findOptionPane().withTimeout(10000).using(window.robot());
        optionPane.requireMessage("Turno creado con exito!");
    }

    @Then("aparece un mensaje de monto insuficiente")
    public void aparece_un_mensaje_de_monto_insuficiente() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JOptionPaneFixture optionPane = JOptionPaneFinder.findOptionPane().withTimeout(10000).using(window.robot());
        optionPane.requireMessage("Error en metodo de pago");
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }

}
