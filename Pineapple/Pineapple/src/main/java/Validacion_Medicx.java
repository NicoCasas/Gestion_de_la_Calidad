import org.json.JSONObject;

import java.util.regex.Pattern;

public class Validacion_Medicx implements Interfaz_Validacion_Medicx {

    private static final String NOMBRE_MEDICX_KEY = "nombre del medico";
    private static final String ID_MEDICX_KEY = "id medico";
    private static final String VALIDO_KEY = "valido";
    private static final String ERROR_KEY = "error";
    private static final String MATRICULA_MEDICX_KEY = "matricula";

    public boolean validarMatricula(JSONObject json_object) {
        String matricula = json_object.getString(MATRICULA_MEDICX_KEY);
        return stringSoloCompuestoPorNumeros(matricula);
    }

    public boolean stringSoloCompuestoPorNumeros(String s) {
        if (s == null) {
            return false;
        }
        return Pattern.matches("[0-9]+", s);
    }

    public boolean validarNombre(JSONObject json_object) {
        String nombre = json_object.getString(NOMBRE_MEDICX_KEY);
        return stringSoloCompuestoPorletras(nombre);
    }

    public boolean stringSoloCompuestoPorletras(String s) {
        if (s == null) {
            return false;
        }
        return Pattern.matches("[a-z A-Z]+", s);
    }

    public boolean validarID(JSONObject json_object) {
        String id = json_object.getString(ID_MEDICX_KEY);
        return stringSoloCompuestoPorNumeros(id);
    }

}
