package it.polito.tdp.numero.model;

import java.security.InvalidParameterException;

public class NumeroModel {

	private final int NMAX = 100;
	private final int TMAX = 8;
    private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	
	
	public NumeroModel() {
		inGioco=false; 
	}
	
	/**
	 * Avvia nuova partita 
	 */
	
	public void newGame() {
		inGioco= true; 
		
		this.segreto = (int) (Math.random() * NMAX) + 1;
		this.tentativiFatti = 0;
		}
	
	/**
	 * Metodo per effetturare un tentativo
	 * @param t il tentativo
	 * @return  1 se il tentativo è troppo alto, 0 se ha indovinato, -1 se è troppo basso
	 */
	
	public int tentativo(int t) {
		//controllo se la partita e' in corso
		if(!inGioco) {
			throw new IllegalStateException("La partita è terminata"); 
		}
		
		//controllo se l'input è corretto 
		//il controllo se l'input è del tipo giusto è lasciato nel controller: infatti questo metodo prende int
		//devo controllare l'intervallo del valore 
		if(!tentativoValido(t)) {
			throw new InvalidParameterException(String.format("Devi inserire un numero tra %d e %d",1,NMAX)); 
		
		}   
	//gestisce tentativo 
		this.tentativiFatti++; 
		if (tentativiFatti==this.TMAX) {
			//la partita è finita 
			this.inGioco=false; 
		}
		
	if(t==this.segreto) {
		this.inGioco = false; 
		return 0; 
	}
	if (t>this.segreto) {
		return 1; 
	}
	
	
		return -1; 
}
	
	
	
	public boolean tentativoValido(int t) {
		if(t<1 || t>NMAX) {
			return false; 
		}
		return true; 
	}

	public boolean isInGioco() {
		return inGioco;
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	
}
