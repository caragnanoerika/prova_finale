package it.polito.tdp.prova_finale.model;

import java.util.List;
import java.util.Objects;

public class PianoFormativo {
	List<Corso> corsi;
    double costoTotale;
    double durataTotale;
    double ratingMedio;
    int popolaritaTotale;
    
    
	public List<Corso> getCorsi() {
		return corsi;
	}

	public void setCorsi(List<Corso> corsi) {
		this.corsi = corsi;
	}

	public double getCostoTotale() {
		return costoTotale;
	}

	public void setCostoTotale(double costoTotale) {
		this.costoTotale = costoTotale;
	}

	public double getDurataTotale() {
		return durataTotale;
	}

	public void setDurataTotale(Double durata) {
		this.durataTotale = durata;
	}

	public double getRatingMedio() {
		return ratingMedio;
	}

	public void setRatingMedio(double ratingMedio) {
		this.ratingMedio = ratingMedio;
	}

	public int getPopolaritaTotale() {
		return popolaritaTotale;
	}

	public void setPopolaritaTotale(int popolaritaTotale) {
		this.popolaritaTotale = popolaritaTotale;
	}

	

	
	public PianoFormativo(List<Corso> corsi, double costoTotale, double durataTotale, double ratingMedio,
			int popolaritaTotale) {
		super();
		this.corsi = corsi;
		this.costoTotale = costoTotale;
		this.durataTotale = durataTotale;
		this.ratingMedio = ratingMedio;
		this.popolaritaTotale = popolaritaTotale;
	}

	@Override
	public String toString() {
		return "PianoFormativo [corsi=" + corsi + ", costoTotale=" + costoTotale + ", durataTotale=" + durataTotale
				+ ", ratingMedio=" + ratingMedio + ", popolaritaTotale=" + popolaritaTotale + ", numeroLezioniTotale="
				+  "]";
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(corsi, costoTotale, durataTotale, popolaritaTotale, ratingMedio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PianoFormativo other = (PianoFormativo) obj;
		return Objects.equals(corsi, other.corsi)
				&& Double.doubleToLongBits(costoTotale) == Double.doubleToLongBits(other.costoTotale)
				&& Double.doubleToLongBits(durataTotale) == Double.doubleToLongBits(other.durataTotale)
				&& popolaritaTotale == other.popolaritaTotale
				&& Double.doubleToLongBits(ratingMedio) == Double.doubleToLongBits(other.ratingMedio);
	}

	public double[] getValutazione() {
        return new double[]{ this.corsi.size(), this.ratingMedio, this.popolaritaTotale };
    }
	
	
    
    
}
