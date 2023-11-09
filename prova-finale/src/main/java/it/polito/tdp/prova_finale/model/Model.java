package it.polito.tdp.prova_finale.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.prova_finale.db.UdemyDAO;

public class Model {
	
	private UdemyDAO dao;
	private Utente user;
	private Map<String,Utente> mappaUtenti;
	private Map<Integer,Corso> mappaCorsi;
	private Map<String,Integer> mappaLivelli; 
	private List<String> livelli;
	private Graph<Corso,DefaultEdge> grafo;
	private int livelloMin;
	private PianoFormativo bestPFP;
	private String[] gerarchia;
	List<Parametro> gerarchiaParametri;


	public Model() {
		
		this.dao = new UdemyDAO();
		this.mappaUtenti = new HashMap<String,Utente>(this.dao.getUserInfo());
		this.mappaCorsi = new HashMap<Integer,Corso>();
		this.mappaLivelli = new HashMap<String,Integer>();
		this.livelli = new ArrayList<String>(this.dao.getLivelli());

		
		creaMappaLivelli();
		
	}
	
	
	private void creaMappaLivelli(){
		for (String livello: livelli) {
			if (livello.compareTo("All Levels")==0) {
				this.mappaLivelli.put(livello, 0);
			} else if(livello.compareTo("Beginner Level")==0) {
				this.mappaLivelli.put(livello, 1);
			} else if(livello.compareTo("Intermediate Level")==0) {
				this.mappaLivelli.put(livello, 2);
			} else if(livello.compareTo("Expert Level")==0) {
				this.mappaLivelli.put(livello, 3);
			}
		}
	}
	
	public boolean checkCredentials(String username, String pwd) {
		if(mappaUtenti.get(username)!=null && mappaUtenti.get(username).getPassword().compareTo(pwd)==0){
			this.user = mappaUtenti.get(username);
			this.user.setCorsi(new ArrayList<Corso>(this.dao.getCorsiUtente(user)));
			return true;
		} else {
			return false;
		}
	}
	
	public List<Corso> getCatalogo(String livello, String materia, double costo, String search){
		
		mappaCorsi = this.dao.getCatalogo(livello, materia, costo, search);
		List<Corso> corsi = new ArrayList<Corso>(mappaCorsi.values());
		return corsi;
		
	}

	public String getUserName(String username) {
		return this.user.getNome() + " " + this.user.getCognome();
	}

	public String getUserBudget(String username) {
		String budget = String.valueOf(this.user.getBudget());
		return budget;
	}

	public List<String> getMaterie() {
		List<String> materie = new ArrayList<String>(this.dao.getMaterie());
		return materie;
	}
	
	public String[] getGerarchia() {
		return gerarchia;
	}


	public void setGerarchia(String[] gerarchia) {
		this.gerarchia = gerarchia;
	}
	
	public Map<String,Integer> getLivelli(){
		return mappaLivelli;
	}


	public double calcolaTotale(List<Corso> wishlist) {
		double totale = 0;
		for (Corso corso: wishlist) {
			totale += corso.getPrice();
		}
		return totale;
	}
	
	public Utente getUser() {
		return this.user;
	}


	public enum Parametro {
	    NUMERO_CORSI("Numero corsi"),
	    RATING_MEDIO("Rating medio"),
	    POPOLARITA("Popolarità");

	    private String nomeParametro;

	    Parametro(String nome) {
	        this.nomeParametro = nome;
	    }

	    public String getNomeParametro() {
	        return nomeParametro;
	    }

	    public static Parametro getParametroByNome(String nomeParametro) {
	        for (Parametro parametro : Parametro.values()) {
	            if (parametro.getNomeParametro().equalsIgnoreCase(nomeParametro)) {
	                return parametro;
	            }
	        }
	        throw new IllegalArgumentException("Parametro non riconosciuto: " + nomeParametro);
	    }
	}
	
	public PianoFormativo genera(Integer ore, Integer giorni, double budgetMax) {
		
		this.bestPFP = new PianoFormativo(new ArrayList<Corso>(),0,0,0,0);
		this.gerarchiaParametri = new ArrayList<>();
		
		for (String nomeParametro : gerarchia) {
		    gerarchiaParametri.add(Parametro.getParametroByNome(nomeParametro));
		}
		
		double tempoDisponibile = ore*giorni;
		PianoFormativo pf = new PianoFormativo(new ArrayList<Corso>(),0,0,0,0);

		for (Corso c: this.grafo.vertexSet()) {
			if(this.mappaLivelli.get(c.getLevel())==this.livelloMin) {
				pf.getCorsi().add(c);
				pf.setDurataTotale(calcolaTempoParziale(pf));
			    pf.setCostoTotale(calcolaCostoParziale(pf));
			    pf.setRatingMedio(calcolaRatingMedio(pf));
			    pf.setPopolaritaTotale(calcolaPopolarita(pf));
				List<Corso> successivi = new ArrayList<Corso>(Graphs.successorListOf(this.grafo, c)); 
				ricorsione(livelloMin, successivi, tempoDisponibile, budgetMax, pf);
				pf.getCorsi().remove(c); //backtracking
			}
		}
		return this.bestPFP;
		
		
	}
	
	private void ricorsione(Integer livello, List<Corso> adiacenti, double tempo, 
			double budgetMax, PianoFormativo parziale) {
		double durataParziale = calcolaTempoParziale(parziale);
	    double costoParziale = calcolaCostoParziale(parziale);
	    double ratingMedio = calcolaRatingMedio(parziale);
	    int popolarita = calcolaPopolarita(parziale);

	// se la soluzione corrente è migliore della precedente, aggiorno il piano migliore 	
	    if (isBetter(parziale, this.bestPFP, gerarchiaParametri) 
	    		&& durataParziale <= tempo && costoParziale <= budgetMax) {
	        this.bestPFP = new PianoFormativo(new ArrayList<Corso>(parziale.getCorsi()), costoParziale, 
	        		durataParziale, ratingMedio, popolarita);
	    }
	    
	 // Verifico se siamo ancora entro i vincoli di tempo e budget
	    if(durataParziale < tempo && costoParziale < this.user.getBudget()) {
	        for (Corso c: adiacenti) {
	            if(!parziale.getCorsi().contains(c)) {
	                double tempoConC = durataParziale + c.getDuration();
	                double costoConC = costoParziale + c.getPrice();

	                // Verifico se aggiungendo il corso si superano i limiti
	                if(tempoConC <= tempo && costoConC <= this.user.getBudget()) {
	                    parziale.getCorsi().add(c);
	                    parziale.setDurataTotale(calcolaTempoParziale(parziale));
	    			    parziale.setCostoTotale(calcolaCostoParziale(parziale));
	    			    parziale.setRatingMedio(calcolaRatingMedio(parziale));
	    			    parziale.setPopolaritaTotale(calcolaPopolarita(parziale));
	                    Integer nextLevel = this.mappaLivelli.get(c.getLevel()) > livello 
	                    		? this.mappaLivelli.get(c.getLevel()) : livello;
	                    List<Corso> successivi = new ArrayList<Corso>(Graphs.successorListOf(this.grafo, c));
	                    ricorsione(nextLevel, successivi, tempo, budgetMax, parziale);
	                    
	                    parziale.getCorsi().remove(c); // backtracking
	                }
	            }
	        }
	    }
	}
	
	private boolean isBetter(PianoFormativo current, PianoFormativo best, List<Parametro> gerarchiaParametri) {
	    for (Parametro parametro : gerarchiaParametri) {
	        switch (parametro) {
	            case NUMERO_CORSI:
	                if (current.getCorsi().size() != best.getCorsi().size()) {
	                    return current.getCorsi().size() > best.getCorsi().size();
	                }
	                break;
	            case RATING_MEDIO:
	                if (current.getRatingMedio() != best.getRatingMedio()) {
	                    return current.getRatingMedio() > best.getRatingMedio();
	                }
	                break;
	            case POPOLARITA:
	                if (current.getPopolaritaTotale() != best.getPopolaritaTotale()) {
	                    return current.getPopolaritaTotale() > best.getPopolaritaTotale();
	                }
	                break;
	        }
	    }
	    return false;
	}

	private double calcolaCostoParziale(PianoFormativo parziale) {
		double cost=0;
		for(Corso c: parziale.getCorsi()) {
			cost += c.getPrice();
		}
		return cost;
	}


	private double calcolaTempoParziale(PianoFormativo parziale) {
		double t=0;
		for(Corso c: parziale.getCorsi()) {
			t += c.getDuration();
		}
		return t;
	}
	
	private double calcolaRatingMedio(PianoFormativo parziale) {
		double rating=0;
		for(Corso c: parziale.getCorsi()) {
			rating += c.getRating();
		}
		return rating/parziale.getCorsi().size();
	}
	
	private int calcolaPopolarita(PianoFormativo parziale){
		int subs=0;
		for(Corso c: parziale.getCorsi()) {
			subs += c.getnSubscribers();
		}
		return subs;
	}


	public void creaGrafo(String livello, String materia, double costo, String search) {
		clearGrafo();
		
		List<Corso> nonComprati = new ArrayList<Corso>(getCatalogo(null,materia,costo,search));
		nonComprati.removeAll(this.user.getCorsi());
		
		if(livello!=null) {
			this.livelloMin = this.mappaLivelli.get(livello);
		} else {
			this.livelloMin = 1;
		}
		
		List<Corso> inferiori = new ArrayList<Corso>();
		for (Corso c:nonComprati) {
			if(this.mappaLivelli.get(c.getLevel())<this.livelloMin) {//&& this.mappaLivelli.get(c.getLevel())!=0
				//nonComprati.remove(c);
				inferiori.add(c);
			}
		}
		
		nonComprati.removeAll(inferiori);
		
		Graphs.addAllVertices(this.grafo,nonComprati);
		
		for (Corso c1: this.grafo.vertexSet()) {
			for (Corso c2: this.grafo.vertexSet()) {
				if (!c1.equals(c2)) {
					if(this.mappaLivelli.get(c1.getLevel())-this.mappaLivelli.get(c2.getLevel())==-1 
							|| this.mappaLivelli.get(c1.getLevel())==this.mappaLivelli.get(c2.getLevel())) {
						this.grafo.addEdge(c1, c2);
					}
				}
			}
		}
	}


	private void clearGrafo() {
		this.grafo = new SimpleDirectedGraph<Corso,DefaultEdge>(DefaultEdge.class);
	}


	public Graph<Corso,DefaultEdge> getGrafo() {
		
		return this.grafo;
	}


	public void updateAcquisti(List<Corso> acquistiDef, Utente user) {
		this.dao.updateAcquisti(acquistiDef, user);
		
		this.dao.updateBudget(user.getBudget(),user);
	}


	public Map<String, Utente> getMappaUtenti() {
		return mappaUtenti;
	}


	public boolean[] checkRegistrazione(String nome, String cognome, String username, String password1,
			String password2) {
		
		boolean[] checks = new boolean[4];
		
//		if(nome.compareTo("")!=0 && cognome.compareTo("")!=0 && username.compareTo("")==0 && password1.compareTo("")==0 && password2.compareTo("")==0) {
			if (nome != null && !nome.isEmpty() && nome.length()<=30) {
				char primaLetteraN = nome.charAt(0);
			    boolean sololettereN = true;
			    for (char carattere : nome.toCharArray()) {
			        if (!Character.isLetter(carattere)) {
			        	sololettereN = false;
			        }
			    }
			    if(sololettereN && Character.isUpperCase(primaLetteraN)) {
			    	checks[0] = true;
			    } else {
			    	checks[0]=false;
			    }
		    } else {
		    	checks[0]=false;
		    }
		    
		    
		    
			if (cognome != null && !cognome.isEmpty() && cognome.length()<=30) {
				char primaLetteraN = cognome.charAt(0);
			    boolean sololettereN = true;
			    for (char carattere : cognome.toCharArray()) {
			        if (!Character.isLetter(carattere)) {
			        	sololettereN = false;
			        }
			    }
			    if(sololettereN && Character.isUpperCase(primaLetteraN)) {
			    	checks[1] = true;
			    } else {
			    	checks[1]=false;
			    }
		    } else {
		    	checks[1]=false;
		    }
		    
		    if (username != null && !username.isEmpty() && this.mappaUtenti.get(username)==null 
		    		&& username.length()<=10 && username.matches("^[a-zA-Z0-9]*$") && username.length()>=4) {
		        checks[2] = true;
		    } else {
		    	checks[2] = false;
		    }
		    
		    
		   
		    if (password1 != null && !password1.isEmpty() && password1.length()>=6) {
		    	boolean numero = false;
		    	for (char carattere : password1.toCharArray()) {
			        if (Character.isDigit(carattere)) {
			            numero = true;
			        }
			    }
		    	if(numero == true) {
		    		checks[3] = true;
		    	}
		    }
		    
		    
//		}
		
		
	    
		return checks;
	}


	public boolean existingUsername(String username) {
		// TODO Auto-generated method stub
		return this.mappaUtenti.get(username)!=null;
	}


	public void uploadUser(String nome, String cognome, String username, String password2, double budget) {
		this.dao.uploadUser(nome,cognome,username,password2,budget);
		
	}


	public void addNewUser(String nome, String cognome, String username, String password2, double budget) {
		if(this.mappaUtenti.get(username)==null) {
			this.mappaUtenti.put(username, new Utente(nome,cognome,username,password2,budget));
		}
		
	}
	
	
	
	
}
