/**
 * Esta clase define objetos que contienen los datos de las tarjetas de creditos
 * asociadas
 * 
 * @author: Pineapple
 * @version: 12/06/2021
 */
public class Tarjeta_credito implements Metodo_de_pago {
    // Campos de la clase
    private long numero;
    private String vencimiento;
    private int codigo;
    private int n_cuotas;

    /**
     * Constructor para la clase Tarjeta_credito
     * 
     * @param numero      Numero de tarjeta de credito
     * @param vencimiento Fecha a partir de la cual la tarjeta ya no es valida
     * @param codigo      Codigo de seguridad de la tarjeta de credito
     * @param n_cuotas    Numero de cuotas en las que se realizara el pago
     */
    public Tarjeta_credito(long numero, String vencimiento, int codigo, int n_cuotas) {
        this.numero = numero;
        this.vencimiento = vencimiento;
        this.codigo = codigo;
        this.n_cuotas = n_cuotas;
    }

    @Override
    public boolean pagar(int monto) {
        return true;
    }

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getN_cuotas() {
		return n_cuotas;
	}

	public void setN_cuotas(int n_cuotas) {
		this.n_cuotas = n_cuotas;
	}
}