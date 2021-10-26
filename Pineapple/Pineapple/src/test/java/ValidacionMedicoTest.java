import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ValidacionMedicoTest {

	private final Validacion_Medicx validacion = new Validacion_Medicx();
	
	@Test
	void TestStringSoloCompuestoPorNumeros() {
		boolean result = validacion.stringSoloCompuestoPorNumeros("123");
		assertEquals(true, result);
	}
	
	@Test
	void TestStringSoloCompuestoPorNumerosNull() {
		boolean result = validacion.stringSoloCompuestoPorNumeros(null);
		assertEquals(false, result);
	}
	
	@Test
	void TestStringSoloCompuestoPorletras() {
		boolean result = validacion.stringSoloCompuestoPorletras("aaa");
		assertEquals(true, result);
	}
	
	@Test
	void TestStringSoloCompuestoPorletrasNull() {
		boolean result = validacion.stringSoloCompuestoPorletras(null);
		assertEquals(false, result);
	}
}
