/**
 * 
 */
package buscaminas;

public class Casilla {
	private boolean mina;
	private boolean bandera;
	private boolean visible;
	private int numero;

	public Casilla() {
		this.mina = false;
		this.bandera = false;
		this.visible = false;
		this.numero = 0;
	}

	public boolean isMina() {
		return mina;
	}

	public void setMina(boolean mina) {
		this.mina = mina;
	}

	public boolean isBandera() {
		return bandera;
	}

	public void setBandera(boolean bandera) {
		this.bandera = bandera;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	 public boolean isBlanco() {
	        return !mina && numero == 0;
	    }

	@Override
	public String toString() {
		return "Casilla [mina=" + mina + ", bandera=" + bandera + ", visible=" + visible
				+ ", numero=" + numero + "]";
	}

}