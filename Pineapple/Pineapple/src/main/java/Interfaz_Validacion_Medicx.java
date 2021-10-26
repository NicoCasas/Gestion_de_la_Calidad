import org.json.*;

public interface Interfaz_Validacion_Medicx {
    
    boolean validarMatricula(JSONObject json_object);
    
    boolean stringSoloCompuestoPorNumeros(String s);

    boolean validarNombre(JSONObject json_object);
    
    boolean stringSoloCompuestoPorletras(String s);
    
    boolean validarID(JSONObject json_object);
}
