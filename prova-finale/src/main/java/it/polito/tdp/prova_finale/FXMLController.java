package it.polito.tdp.prova_finale;


import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;

import org.jgrapht.graph.DefaultEdge;

import it.polito.tdp.prova_finale.model.Corso;
import it.polito.tdp.prova_finale.model.Model;
import it.polito.tdp.prova_finale.model.PianoFormativo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class FXMLController {
	
	private Model model;

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Button btnAcquista;
    
    @FXML
    private Button btnAcquista1;

    @FXML
    private Button btnAggiungi;

    @FXML
    private Button btnCancella;

    @FXML
    private Button btnConsulta;   
    
    @FXML
    private Button btnGenera;
    
    @FXML
    private Button btnMeno;

    @FXML
    private Button btnPiu;
    
    @FXML
    private Button btnResetFiltri;
    
    @FXML
    private Button btnRimuovi;
    
    @FXML
    private Button btnRegistrami;
    
    @FXML
    private Button cancelRegistrami;
    
    @FXML
    private TextField inputNome;
    
    @FXML
    private TextField inputCognome;
    
    @FXML
    private Label msgLogin1;
    
    @FXML
    private TextField usernameRegistrazione;
    
    @FXML
    private TextField passwordConferma;

    @FXML
    private TextField passwordRegistrazione;
    
    @FXML
    private Spinner<Double> spnRegistrazione;



    @FXML
    private TableColumn<Corso, Double> colDurata;
    
    @FXML
    private TableColumn<Corso, Double> colDurata1;
    
    @FXML
    private TableColumn<Corso, Double> colDurata2;
    
    @FXML
    private TableColumn<Corso, String> colLivello;

    @FXML
    private TableColumn<Corso, String> colLivello1;

    @FXML
    private TableColumn<Corso, String> colLivello2;

    @FXML
    private TableColumn<Corso, String> colMateria;
    
    @FXML
    private TableColumn<Corso, String> colMateria1;
    
    @FXML
    private TableColumn<Corso, String> colMateria2;

    @FXML
    private TableColumn<Corso, String> colNome;
    
    @FXML
    private TableColumn<Corso, String> colNome1;
    
    @FXML
    private TableColumn<Corso, String> colNome2;

    @FXML
    private TableColumn<Corso, Double> colPrezzo;
    
    @FXML
    private TableColumn<Corso, Double> colPrezzo1;
    
    @FXML
    private TableColumn<Corso, Double> colPrezzo2;

    @FXML
    private TableColumn<Corso, Integer> colRating;
    
    @FXML
    private TableColumn<Corso, Integer> colRating1;
    
    @FXML
    private TableColumn<Corso, Integer> colRating2;



    @FXML
    private Slider costo;
    
    @FXML
    private Slider costo1;


    
    // Riquadro Dati Utente
    
    @FXML
    private Label txtNome;
    
    @FXML
    private Label txtBudget;
    
    @FXML
    private Button btnLogout;
    
    @FXML
    private ListView<String> listaCorsiUtente;
    
    @FXML
    private ImageView imgLogo;

    @FXML
    private ImageView imgUser;

    
    // Riquadro LOGIN
    
    @FXML
    private Label msgLogin;
    
    @FXML
    private Label msgPiano;

    @FXML
    private TextField usernameInput;
    
    @FXML
    private PasswordField pwdInput;
    
    @FXML
    private Button inputLogin;
    
    @FXML
    private Button cancelLogin;

    @FXML
    private Label labelInfo;

    @FXML
    private Label labelInfo1;

    @FXML
    private Tab tabPiano;

    @FXML
    private Tab tabSelezione;

    @FXML
    private TableView<Corso> tabellaP;

    @FXML
    private TableView<Corso> tabellaS;
    
    @FXML
    private TableView<Corso> tabellaS1;
    
    @FXML
    private TableView<Corso> tabellaWishlist;
    
    @FXML
    private Label msgAcquista;
    
    @FXML
    private TabPane tabPane1;


    @FXML
    private PieChart pieLivelli;
    
    @FXML
    private PieChart pieMaterie;

    @FXML
    private PieChart piePrezzo;

    @FXML
    private TextArea txtSimulazione;
    
    @FXML
    private ComboBox<String> livello;
    
    @FXML
    private ComboBox<String> livello1;

    @FXML
    private ComboBox<String> cmbMateria1;
    
    @FXML
    private ComboBox<String> cmbMateria11;

    
    @FXML
    private TextField txtSearch;
    
    //Sezione Piano Formativo
    
    @FXML
    private RadioButton radNCorsi;

    @FXML
    private RadioButton radNLezioni;

    @FXML
    private RadioButton radPopolarita;

    @FXML
    private RadioButton radRating;
    
    @FXML
    private Spinner<Integer> spnGiorni;

    @FXML
    private Spinner<Integer> spnOre;
    
    @FXML
    private TextField txtSearch1;
    
    @FXML
    private ListView<String> listViewParameters;



    @FXML
    void handleLoadData(ActionEvent event) {
    	if(this.txtNome.getText().compareTo("")!=0)
    	tabellaS.setItems(FXCollections.observableArrayList(this.model.getCatalogo(this.livello.getValue(), this.cmbMateria1.getValue(), this.costo.getValue(), this.txtSearch.getText())));
    }

    @FXML
    void handleAggiungi(ActionEvent event) {
    	Corso selectedCorso = tabellaS.getSelectionModel().getSelectedItem();
        if (selectedCorso != null) {
        	if (!tabellaWishlist.getItems().contains(selectedCorso)) {
        		if(this.listaCorsiUtente!=null) {
        			if(!this.listaCorsiUtente.getItems().contains(selectedCorso.getCourseTitle())) {
            			tabellaWishlist.getItems().add(selectedCorso);
            			this.msgAcquista.setText("Il corso selezionato è stato aggiunto alla wishlist.");
            		} else {
            			this.msgAcquista.setText("Il corso selezionato è già stato acquistato.");
            		}
        		}else {
        			tabellaWishlist.getItems().add(selectedCorso);
        		}
        		
        		
        	} else {
        		this.msgAcquista.setText("Il corso selezionato è già presente nella tua wishlist.");
        	}
        	
        }
    }
    
    @FXML
    void doResetFiltri(ActionEvent event) {
    	this.cmbMateria1.setValue(null);
    	this.livello.setValue(null);
    	this.costo.setValue(this.model.getUser().getBudget());
    	this.txtSearch.setText("");
    }
    
    @FXML
    void handleAcquisto(ActionEvent event) {
    	List<Corso> wishlist = new ArrayList<Corso>(this.tabellaWishlist.getItems());
//    	int lunghezzaInizialeAcquistati = this.listaCorsiUtente.getItems().size();
//    	List<String> nuoviacquisti = new ArrayList<String>();
    	if(this.usernameInput.getText()!=null) {
	    	if(this.model.calcolaTotale(wishlist) <= this.model.getUser().getBudget()){
	    		List<Corso> acquistiDef = new ArrayList<Corso>();
	    		for (Corso c: wishlist) {
	    			if(!this.model.getUser().getCorsi().contains(c)) {
	    				this.model.getUser().getCorsi().add(c);
	    				this.listaCorsiUtente.getItems().add(c.toString());
	    				acquistiDef.add(c);
	    				
	    				
	    				this.model.getUser().setBudget(this.model.getUser().getBudget()-c.getPrice());
	    				this.txtBudget.setText(String.valueOf(this.model.getUser().getBudget())+"$");
	    				this.costo.setMax(this.model.getUser().getBudget());
	        			this.costo.setValue(this.model.getUser().getBudget());
	        			this.costo1.setMax(this.model.getUser().getBudget());
		    			this.costo1.setValue(this.model.getUser().getBudget());
	        			aggiornaGrafico();
	        			
	        			
	    			} else {
	    				this.msgAcquista.setText("Hai già acquistato questi corsi.");
	    			}
	    			
	    			
	    		}
	    		
	    		this.tabellaWishlist.getItems().removeAll(wishlist);
	    		this.model.updateAcquisti(acquistiDef,this.model.getUser());
	    		
	    		
	    		
	    	} else {
	    		this.msgAcquista.setText("Il tuo budget non è sufficiente per procedere con l'acquisto. Rimuovi alcuni corsi.");
	    	}
    	} 
    }
    
    @FXML
    void handleAcquistoPiano(ActionEvent event) {
    	if(this.tabellaS1.getItems()!=null) {
	    	if(this.model.calcolaTotale(this.tabellaS1.getItems()) <= this.model.getUser().getBudget()){
		    	List<Corso> corsiPiano = new ArrayList<Corso>(this.tabellaS1.getItems());
		    	
		    	List<Corso> acquistiDef = new ArrayList<Corso>();
				for (Corso c: corsiPiano) {
					if(!this.model.getUser().getCorsi().contains(c)) {
						this.model.getUser().getCorsi().add(c);
						this.listaCorsiUtente.getItems().add(c.toString());
						acquistiDef.add(c);
						
						
						this.model.getUser().setBudget(this.model.getUser().getBudget()-c.getPrice());
						this.txtBudget.setText(String.valueOf(this.model.getUser().getBudget())+"$");
						this.costo.setMax(this.model.getUser().getBudget());
		    			this.costo.setValue(this.model.getUser().getBudget());
		    			this.costo1.setMax(this.model.getUser().getBudget());
		    			this.costo1.setValue(this.model.getUser().getBudget());
		    			aggiornaGrafico();
		    			
		    			
					} else {
						this.msgAcquista.setText("Hai già acquistato questi corsi.");
					}
					
					
				}
				
				this.model.updateAcquisti(acquistiDef,this.model.getUser());
	    	}
    	}
    }
    
    @FXML
    void handleRimuovi(ActionEvent event) {
    	Corso selectedCorso = tabellaWishlist.getSelectionModel().getSelectedItem();
    	if (selectedCorso != null) {
        	if (tabellaWishlist.getItems().contains(selectedCorso)) {
        		tabellaWishlist.getItems().remove(selectedCorso);
        	} 
        }else {
    		this.msgAcquista.setText("Non hai selezionato un elemento da rimuovere.");
    	}
    }
    
    @FXML
    void handleMeno(ActionEvent event) {
    	String selezionato = this.listViewParameters.getSelectionModel().getSelectedItem();
    	if(selezionato!=null) {
    		int i = this.listViewParameters.getItems().indexOf(this.listViewParameters.getSelectionModel().getSelectedItem());
    		if(i<2) {
    			String successivo = this.listViewParameters.getItems().get(i+1);
        		this.listViewParameters.getItems().set(i+1, selezionato);
        		this.listViewParameters.getItems().set(i, successivo);
        	}
    	}
    }

    @FXML
    void handlePiu(ActionEvent event) {
    	String selezionato = this.listViewParameters.getSelectionModel().getSelectedItem();
    	if(selezionato!=null) {
    		int i = this.listViewParameters.getItems().indexOf(this.listViewParameters.getSelectionModel().getSelectedItem());
    		if(i>0) {
    			String precedente = this.listViewParameters.getItems().get(i-1);
        		this.listViewParameters.getItems().set(i-1, selezionato);
        		this.listViewParameters.getItems().set(i, precedente);
        	}
    	}
    }
    
    @FXML
    void handleGenera(ActionEvent event) {
    	
    	this.tabellaS1.refresh();
    	
    	String gerarchia[] = new String[3];
    	gerarchia[0] = this.listViewParameters.getItems().get(0);
    	gerarchia[1] = this.listViewParameters.getItems().get(1);
    	gerarchia[2] = this.listViewParameters.getItems().get(2);
    	this.model.setGerarchia(gerarchia);
    	
    	PianoFormativo piano = new PianoFormativo(new ArrayList<Corso>(),0,0,0,0);
    	
    	
    	if(this.livello1.getValue()!=null) {
    		//parametri filtro
        	
        	this.model.creaGrafo(this.livello1.getValue(), this.cmbMateria11.getValue(), this.costo1.getValue(), this.txtSearch1.getText());    	
        	
        	System.out.println("#Nodi: " + this.model.getGrafo().vertexSet().size() + " ; #Archi: " + this.model.getGrafo().edgeSet().size());
        	
        	PianoFormativo generato = this.model.genera(this.spnOre.getValue(), this.spnGiorni.getValue(), this.costo1.getValue());
        	List<Corso> corsi = new ArrayList<Corso>(generato.getCorsi());
        	piano.setCorsi(corsi);
        	Double costo = generato.getCostoTotale();
        	piano.setCostoTotale(costo);
        	Double durata = generato.getDurataTotale();
        	piano.setDurataTotale(durata);
        	Double rating = generato.getRatingMedio();
        	piano.setRatingMedio(rating);
        	int popolarita = generato.getPopolaritaTotale();
        	piano.setPopolaritaTotale(popolarita);
        	
        	this.txtSimulazione.setText("Costo totale del piano: " + piano.getCostoTotale() + "$ \n");
        	this.txtSimulazione.appendText("Durata totale del piano: " + piano.getDurataTotale()+ " ore \n");
        	this.txtSimulazione.appendText("Rating medio del piano: " + String.format("%.2f", piano.getRatingMedio())+ " % \n");
        	this.txtSimulazione.appendText("Popolarità del piano (numero di subscribers): " + piano.getPopolaritaTotale()+ "\n");
        	
        	tabellaS1.setItems(FXCollections.observableArrayList(piano.getCorsi()));
    	} else {
    		this.msgPiano.setText("Devi selezionare\nun livello \ndi partenza.");
    	}
    	
    	
    }
    
    @FXML
    void doRegistrami(ActionEvent event) {
    	try {
    		String nome = this.inputNome.getText();
    		String cognome = this.inputCognome.getText();
    		double budget = this.spnRegistrazione.getValue();
    		String username = this.usernameRegistrazione.getText();
    		String password1 = this.passwordRegistrazione.getText();
    		String password2 = this.passwordConferma.getText();
    		
    		boolean[] success = this.model.checkRegistrazione(nome,cognome,username,password1,password2);
    		
    		if (success[0]==false) {
    			this.msgLogin1.setText("Il nome deve essere una stringa di sole lettere \ncon la prima lettera maiuscola.");
    			return;
    		} 
    		
    		if (success[1]==false) {
    			this.msgLogin1.setText("Il cognome deve essere una stringa di sole lettere \ncon la prima lettera maiuscola.");
    			return;
    		} 
    		
    		if (success[2]==false && !this.model.existingUsername(username)) {
    			this.msgLogin1.setText("Lo username deve essere una stringa \ndi minimo 4 e massimo 10 caratteri \ndi sole lettere e numeri.");
    			return;
    		}
    		
    		if (success[2]==false && this.model.existingUsername(username)) {
    			this.msgLogin1.setText("Questo username è già stato scelto da un altro utente.");
    			return;
    		}
    		
    		if (success[3]==false) {
    			this.msgLogin1.setText("La password deve essere una stringa di minimo 6 caratteri \ncontenente almeno un numero.");
    			return;
    		}
    		
    		if (success[3]==true && password2.compareTo(password1)!=0) {
    			this.msgLogin1.setText("Le due password sono diverse.");
    			return;
    		}
    		
    		this.msgLogin1.setText("Successo!");
    		this.model.uploadUser(nome, cognome, username, password2, budget);
    		this.model.addNewUser(nome, cognome, username, password2, budget);
    	} catch (Exception e){
    		this.msgLogin1.setText("Non hai inserito le credenziali.");
    	}
    }

    @FXML
    void handleLogin(ActionEvent event) {
    	this.listaCorsiUtente.getItems().setAll(new ArrayList<String>());
    	String username = this.usernameInput.getText();
    	String pwd = this.pwdInput.getText();
    	if (username.compareTo("")!=0 && pwd.compareTo("")!=0) {
    		if(this.model.getMappaUtenti().get(username)!=null) {
    			if (this.model.checkCredentials(username,pwd)) {
        			
        			for(Corso c: this.model.getUser().getCorsi()) {
        				this.listaCorsiUtente.getItems().add(c.toString());
        			}
        			
        			
        			this.tabSelezione.setDisable(false);
        			this.tabPiano.setDisable(false);
        			this.txtNome.setText(this.model.getUserName(username));
        			this.txtBudget.setText(this.model.getUserBudget(username) + "$");
        			this.msgLogin.setText("Benvenuto/a, " + this.model.getUser().getNome() + "!");
        			this.costo.setMax(this.model.getUser().getBudget());
        			this.costo.setValue(this.model.getUser().getBudget());
        			
        			this.costo1.setMax(this.model.getUser().getBudget());
        			this.costo1.setValue(this.model.getUser().getBudget());
        			
        			
        			aggiornaGrafico();
        			
        			this.tabPiano.setDisable(false);
        			this.tabSelezione.setDisable(false);
        			this.btnLogout.setDisable(false);
        			
        			this.inputLogin.setDisable(true);
        			
        			
        		}else {
        			this.msgLogin.setText("Le credenziali inserite sono errate.");
        		}
    		} else {
    			this.msgLogin.setText("Non esiste un utente con questo username.\nRegistrati pure!");
    		}
    		
    	} else {
    		this.msgLogin.setText("Non hai inserito username e password.");
    	}
    	
    }
    
    private void aggiornaGrafico() {
        Map<String, Integer> conteggiPerLivello = new HashMap<>();
        Map<String, Integer> conteggiPerMateria = new HashMap<>();
        Map<String, Integer> conteggiPerFascia = new HashMap<>();
        List<Corso> corsiUtente = new ArrayList<Corso>(this.model.getUser().getCorsi());
        int totaleCorsi = corsiUtente.size();

        // Calcolo del conteggio dei corsi per livello
        for (Corso corso : corsiUtente) {
            String livello = corso.getLevel(); 
            String materia = corso.getSubject();
            Double prezzo = corso.getPrice();
            String fascia = "";
            
            if(prezzo<=50) {
            	fascia = "0-50($)";
            } else if(prezzo>50 && prezzo<=100) {
            	fascia = "51-100($)";
            } else if(prezzo>100 && prezzo<=150) {
            	fascia = "101-150($)";
            } else if(prezzo>150) {
            	fascia = "150+";
            }
            
            // Aggiorno il conteggio per il livello corrente
            if (conteggiPerLivello.containsKey(livello)) {
                conteggiPerLivello.put(livello, conteggiPerLivello.get(livello) + 1);
            } else {
                conteggiPerLivello.put(livello, 1);
            }
            
            if (conteggiPerMateria.containsKey(materia)) {
                conteggiPerMateria.put(materia, conteggiPerMateria.get(materia) + 1);
            } else {
                conteggiPerMateria.put(materia, 1);
            }
            
            if (conteggiPerFascia.containsKey(fascia)) {
                conteggiPerFascia.put(fascia, conteggiPerFascia.get(fascia) + 1);
            } else {
                conteggiPerFascia.put(fascia, 1);
            }
            
        }

        // Creazione dei dati per il PieChart
        ObservableList<PieChart.Data> pieLivelliData = FXCollections.observableArrayList();
        ObservableList<PieChart.Data> pieMaterieData = FXCollections.observableArrayList();
        ObservableList<PieChart.Data> pieFasceData = FXCollections.observableArrayList();

        for (String entry : conteggiPerLivello.keySet()) {
            double percentuale = (conteggiPerLivello.get(entry) * 100.0) / totaleCorsi;
            pieLivelliData.add(new PieChart.Data(entry + " (" + String.format("%.2f%%", percentuale) + ")", conteggiPerLivello.get(entry)));
        }
        
        for (String entry : conteggiPerMateria.keySet()) {
            double percentuale = (conteggiPerMateria.get(entry) * 100.0) / totaleCorsi;
            pieMaterieData.add(new PieChart.Data(entry + " (" + String.format("%.2f%%", percentuale) + ")", conteggiPerMateria.get(entry)));
        }
        
        for (String entry : conteggiPerFascia.keySet()) {
            double percentuale = (conteggiPerFascia.get(entry) * 100.0) / totaleCorsi;
            pieFasceData.add(new PieChart.Data(entry + " (" + String.format("%.2f%%", percentuale) + ")", conteggiPerFascia.get(entry)));
        }

        // Aggiornamento del PieChart
        pieLivelli.getData().clear();
        pieMaterie.getData().clear();
        piePrezzo.getData().clear();
        pieLivelli.getData().addAll(pieLivelliData);
        pieMaterie.getData().addAll(pieMaterieData);
        piePrezzo.getData().addAll(pieFasceData);
    }

    @FXML
    void handleLogout(ActionEvent event) {
    	this.msgLogin.setText("Inserisci le tue credenziali.");
    	this.tabSelezione.setDisable(true);
    	this.tabPiano.setDisable(true);
    	this.txtNome.setText("NA");
		this.txtBudget.setText("$");
		this.model = new Model();
		
		this.listaCorsiUtente.getItems().setAll(new ArrayList<String>());
        pieLivelli.getData().clear();
        pieMaterie.getData().clear();
        piePrezzo.getData().clear();
        
        this.livello.setValue(null);
        this.livello1.setValue(null);
        this.cmbMateria1.setValue(null);
        this.txtSearch.setText("");
        this.cmbMateria11.setValue(null);
        this.txtSearch1.setText("");
        
        
        this.inputLogin.setDisable(false);
        this.btnLogout.setDisable(true);
        
        this.tabellaS1.getItems().clear();
        this.txtSimulazione.setText("");
        
        SpinnerValueFactory<Integer> oreValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12);
    	this.spnOre.setValueFactory(oreValueFactory);
    	
    	SpinnerValueFactory<Integer> giorniValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
    	this.spnGiorni.setValueFactory(giorniValueFactory);
    	
    	SpinnerValueFactory<Double> budgetFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(50.0, 500.0);
    	this.spnRegistrazione.setValueFactory(budgetFactory);
    	
        SingleSelectionModel<Tab> selectionModel = tabPane1.getSelectionModel();
        
        selectionModel.select(0);
    }

    @FXML
    void handleReset(ActionEvent event) {
    	this.usernameInput.setText("");;
    	this.pwdInput.setText("");
    	this.msgLogin.setText("Inserisci le tue credenziali.");
    }
    
    @FXML
    void handleReset1(ActionEvent event) {
    	
    	this.inputNome.setText("");
		this.inputCognome.setText("");
		
		this.usernameRegistrazione.setText("");
		this.passwordRegistrazione.setText("");
		this.passwordConferma.setText("");
		this.msgLogin1.setText("");
    }

    @FXML
    void initialize() {
    	assert btnAcquista != null : "fx:id=\"btnAcquista\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAcquista1 != null : "fx:id=\"btnAcquista1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAggiungi != null : "fx:id=\"btnAggiungi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCancella != null : "fx:id=\"btnCancella\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnConsulta != null : "fx:id=\"btnConsulta\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnGenera != null : "fx:id=\"btnGenera\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnLogout != null : "fx:id=\"btnLogout\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnMeno != null : "fx:id=\"btnMeno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnPiu != null : "fx:id=\"btnPiu\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnRimuovi != null : "fx:id=\"btnRimuovi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cancelLogin != null : "fx:id=\"cancelLogin\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbMateria1 != null : "fx:id=\"cmbMateria1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbMateria11 != null : "fx:id=\"cmbMateria11\" was not injected: check your FXML file 'Scene.fxml'.";
        assert colDurata != null : "fx:id=\"colDurata\" was not injected: check your FXML file 'Scene.fxml'.";
        assert colDurata1 != null : "fx:id=\"colDurata1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert colDurata2 != null : "fx:id=\"colDurata2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert colLivello != null : "fx:id=\"colLivello\" was not injected: check your FXML file 'Scene.fxml'.";
        assert colLivello1 != null : "fx:id=\"colLivello1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert colLivello2 != null : "fx:id=\"colLivello2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert colMateria != null : "fx:id=\"colMateria\" was not injected: check your FXML file 'Scene.fxml'.";
        assert colMateria1 != null : "fx:id=\"colMateria1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert colMateria2 != null : "fx:id=\"colMateria2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert colNome != null : "fx:id=\"colNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert colNome1 != null : "fx:id=\"colNome1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert colNome2 != null : "fx:id=\"colNome2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert colPrezzo != null : "fx:id=\"colPrezzo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert colPrezzo1 != null : "fx:id=\"colPrezzo1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert colPrezzo2 != null : "fx:id=\"colPrezzo2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert colRating != null : "fx:id=\"colRating\" was not injected: check your FXML file 'Scene.fxml'.";
        assert colRating1 != null : "fx:id=\"colRating1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert colRating2 != null : "fx:id=\"colRating2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert costo != null : "fx:id=\"costo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert costo1 != null : "fx:id=\"costo1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert imgLogo != null : "fx:id=\"imgLogo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert imgUser != null : "fx:id=\"imgUser\" was not injected: check your FXML file 'Scene.fxml'.";
        assert inputLogin != null : "fx:id=\"inputLogin\" was not injected: check your FXML file 'Scene.fxml'.";
        assert labelInfo != null : "fx:id=\"labelInfo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert listViewParameters != null : "fx:id=\"listViewParameters\" was not injected: check your FXML file 'Scene.fxml'.";
        assert listaCorsiUtente != null : "fx:id=\"listaCorsiUtente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert livello != null : "fx:id=\"livello\" was not injected: check your FXML file 'Scene.fxml'.";
        assert livello1 != null : "fx:id=\"livello1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert msgAcquista != null : "fx:id=\"msgAcquista\" was not injected: check your FXML file 'Scene.fxml'.";
        assert msgLogin != null : "fx:id=\"msgLogin\" was not injected: check your FXML file 'Scene.fxml'.";
        assert pwdInput != null : "fx:id=\"pwdInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert spnGiorni != null : "fx:id=\"spnGiorni\" was not injected: check your FXML file 'Scene.fxml'.";
        assert spnOre != null : "fx:id=\"spnOre\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tabPiano != null : "fx:id=\"tabPiano\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tabSelezione != null : "fx:id=\"tabSelezione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tabellaS != null : "fx:id=\"tabellaS\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tabellaS1 != null : "fx:id=\"tabellaS1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tabellaWishlist != null : "fx:id=\"tabellaWishlist\" was not injected: check your FXML file 'Scene.fxml'.";
        assert pieLivelli != null : "fx:id=\"torta\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtBudget != null : "fx:id=\"txtBudget\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtSearch != null : "fx:id=\"txtSearch\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtSearch1 != null : "fx:id=\"txtSearch1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtSimulazione != null : "fx:id=\"txtSimulazione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert usernameInput != null : "fx:id=\"usernameInput\" was not injected: check your FXML file 'Scene.fxml'.";

        Image image1 = new Image("/icons/Remote.png");
        Image image2 = new Image("/icons/User_icon_2.svg.png");

        this.imgLogo = new ImageView();
        this.imgUser = new ImageView();
        this.imgLogo.setImage(image1);
        this.imgUser.setImage(image2);

        colNome.setCellValueFactory(new PropertyValueFactory<Corso,String>("courseTitle"));
        colDurata.setCellValueFactory(new PropertyValueFactory<Corso,Double>("duration"));
        colRating.setCellValueFactory(new PropertyValueFactory<Corso,Integer>("rating"));
        colPrezzo.setCellValueFactory(new PropertyValueFactory<Corso,Double>("price"));
        colMateria.setCellValueFactory(new PropertyValueFactory<Corso,String>("subject"));
        colLivello.setCellValueFactory(new PropertyValueFactory<Corso,String>("level"));
        
        colNome1.setCellValueFactory(new PropertyValueFactory<Corso,String>("courseTitle"));
        colDurata1.setCellValueFactory(new PropertyValueFactory<Corso,Double>("duration"));
        colRating1.setCellValueFactory(new PropertyValueFactory<Corso,Integer>("rating"));
        colPrezzo1.setCellValueFactory(new PropertyValueFactory<Corso,Double>("price"));
        colMateria1.setCellValueFactory(new PropertyValueFactory<Corso,String>("subject"));
        colLivello1.setCellValueFactory(new PropertyValueFactory<Corso,String>("level"));
        
        colNome2.setCellValueFactory(new PropertyValueFactory<Corso,String>("courseTitle"));
        colDurata2.setCellValueFactory(new PropertyValueFactory<Corso,Double>("duration"));
        colRating2.setCellValueFactory(new PropertyValueFactory<Corso,Integer>("rating"));
        colPrezzo2.setCellValueFactory(new PropertyValueFactory<Corso,Double>("price"));
        colMateria2.setCellValueFactory(new PropertyValueFactory<Corso,String>("subject"));
        colLivello2.setCellValueFactory(new PropertyValueFactory<Corso,String>("level"));
        
        SpinnerValueFactory<Double> budgetFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(50.0, 500.0);
    	this.spnRegistrazione.setValueFactory(budgetFactory);
    
        SpinnerValueFactory<Integer> oreValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12);
    	this.spnOre.setValueFactory(oreValueFactory);
    	
    	SpinnerValueFactory<Integer> giorniValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
    	this.spnGiorni.setValueFactory(giorniValueFactory);
    	
    	this.listViewParameters.getItems().add("Numero corsi");
    	this.listViewParameters.getItems().add("Rating medio");
    	this.listViewParameters.getItems().add("Popolarità");
    	
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.cmbMateria1.getItems().setAll(this.model.getMaterie());
    	this.cmbMateria11.getItems().setAll(this.model.getMaterie());
    	this.livello.getItems().setAll(this.model.getLivelli().keySet());
    	this.livello1.getItems().setAll(this.model.getLivelli().keySet());
    	
    	
    }

}
