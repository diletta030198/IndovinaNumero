package it.polito.tdp.numero.model;

import java.security.InvalidParameterException;
import java.util.HashMap;

import java.util.Map;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class NumeroModel {

	private final int NMAX = 100;
	private final int TMAX = 8;
    private int segreto;
    
    private Map <Integer,Boolean> tentativi; 
    
	//private int tentativiFatti;
    
    private IntegerProperty tentativiFatti; 
    
    
    
	private boolean inGioco = false;
	
	
	public NumeroModel() {
		inGioco=false; 
		tentativiFatti= new SimpleIntegerProperty(); 
	
		tentativi= new HashMap<Integer,Boolean>(); 
	
		for(int i=1; i<=100;i++) {
			tentativi.put(new Integer(i), false);
		}
	}
	
	/**
	 * Avvia nuova partita 
	 */
	
	public void newGame() {
		
		inGioco= true; 
		tentativi= new HashMap<Integer,Boolean>(); 
		
		for(int i=1; i<=100;i++) {
			tentativi.put(new Integer(i), false);
		}
		
		this.segreto = (int) (Math.random() * NMAX) + 1;
		this.tentativiFatti.set(0);
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
		this.setTentativiFatti(this.tentativiFatti.get()+1);
		
		this.tentativi.put(new Integer(t),true); 
		
		if (this.tentativiFatti.get()==this.TMAX) {
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
		else {
			if(this.tentativi.get(new Integer(t))==true)
					return false;
			else 
				return true; 
		}
		
		
		
	}
	
	public boolean tentativoLista(int t) {
		if(this.tentativi.containsKey(t))
			return false; 
		return true; 
	}

	public boolean isInGioco() {
		return inGioco;
	}

	public int getSegreto() {
		return segreto;
	}

	

	public int getTMAX() {
		return TMAX;
	}
	
	public final IntegerProperty tentativiFattiProperty() {
		return this.tentativiFatti; 
	}
	
	public final int getTentativiFatti() {
		return this.tentativiFattiProperty().get();
	}
	
	public final void setTentativiFatti(final int tentativiFatti) {
		this.tentativiFattiProperty().set(tentativiFatti);
	}
	
	
	
}
