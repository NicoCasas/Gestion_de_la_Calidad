
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;

import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.InvocationTargetException;

import static org.assertj.swing.core.matcher.JButtonMatcher.withText;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class steps {
    private FrameFixture window;

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    public void setup() {

    }

    @Given("el usuario está en la pantalla principal")
    public void el_usuario_está_en_la_pantalla_principal() throws Exception {
        Agenda agenda = new Agenda();
        Gestor_Medicx gestor = new Gestor_Medicx();
        Validacion_Medicx validadorMedicxs = new Validacion_Medicx();
        Validacion_Turno validadorTurno = new Validacion_Turno();
        AgendaGUI frame = GuiActionRunner.execute(() -> new AgendaGUI("My Agenda", validadorTurno, validadorMedicxs, gestor));
        window = new FrameFixture(frame);
        window.show(); // shows the frame to test

    }

    @When("el usuario cliquea solicitar turno")
    public void el_usuario_cliquea_solicitar_turno() {
        window.button(withText("Reservar Turno")).click();
    }
    @Then("aparece la ventana para ingresar los datos del turno")
    public void aparece_la_ventana_para_ingresar_los_datos_del_turno() {
        assertTrue(window.panel("crearPanel").target().isShowing());

    }
/*
* mainPanel.setVisible(false);
		sesionPanel.setVisible(false);
		consultaPanel.setVisible(false);
		crearPanel.setVisible(false);
* */
    @AfterEach
    public void tearDown() {
        window.cleanUp();
    }

}
