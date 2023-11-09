package it.polito.tdp.prova_finale.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.prova_finale.model.Corso;
import it.polito.tdp.prova_finale.model.Utente;



public class UdemyDAO {

	public Map<String,Utente> getUserInfo(){
		final String sql = "SELECT * FROM utenti";
		Map<String,Utente> result = new HashMap<String,Utente>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.put(res.getString("username"),new Utente(res.getString("Nome"),res.getString("Cognome"),res.getString("username"),res.getString("password"),res.getDouble("portafogli")));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		return result;
	}
	
	public Map<Integer,Corso> getCatalogo(String livello, String materia, double costo, String search){
		StringBuilder sql = new StringBuilder("SELECT * FROM corsi WHERE price <= ?");
		ArrayList<Object> params = new ArrayList<>();
		params.add(costo); // costo è sempre presente

		if (livello != null) {
		    sql.append(" AND level = ?");
		    params.add(livello);
		}

		if (materia != null) {
		    sql.append(" AND subject = ?");
		    params.add(materia);
		}
		
		if(search.compareTo("")!=0) {
			sql.append(" AND course_title LIKE ?");
			params.add( "%" + search + "%");
		}

		Map<Integer,Corso> result = new HashMap<Integer,Corso>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql.toString());

			for (int i = 0; i < params.size(); i++) {
			    Object param = params.get(i);
			    if (param instanceof String) {
			        st.setString(i + 1, (String) param);
			    } else if (param instanceof Double) {
			        st.setDouble(i + 1, (Double) param);
			    }
			}
			ResultSet res = st.executeQuery();
			while (res.next()) {
				result.put(res.getInt("course_id"),new Corso(res.getInt("course_id"),
						res.getString("course_title"),res.getString("url"),res.getDouble("price"),
						res.getInt("num_subscribers"),res.getInt("num_reviews"),res.getInt("num_lectures"),
						res.getString("level"),res.getInt("rating"),res.getDouble("content_duration"),
						res.getDate("timestamp"),res.getString("subject")));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		return result;
	}
	
	public List<String> getMaterie(){
		final String sql = "SELECT DISTINCT subject FROM corsi";
		List<String> result = new ArrayList<String>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				result.add(res.getString("subject"));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		return result;
	}
	
	public List<String> getLivelli(){
		final String sql = "SELECT DISTINCT level FROM corsi";
		List<String> result = new ArrayList<String>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				result.add(res.getString("level"));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		return result;
	}
	
	public void updateAcquisti(List<Corso> acquisti, Utente user) {
		    final String sql = "INSERT INTO acquisti (username, course_id) VALUES (?, ?)";
		    
		    Connection conn = null;
		    PreparedStatement st = null;

		    try {
		        conn = DBConnect.getConnection();
		        conn.setAutoCommit(false); // Per usare la transazione

		        st = conn.prepareStatement(sql);

		        for (Corso corso : acquisti) {
		            st.setString(1, user.getUsername());
		            st.setInt(2, corso.getCourseID());
		            st.addBatch(); // Aggiunge il comando all'elenco dei batch
		        }

		        int[] results = st.executeBatch(); // Esegue il batch

		        conn.commit(); // Completa la transazione
		        // Se vuoi verificare i risultati, puoi iterare su results che contiene il numero di righe inserite per ogni statement del batch.

		    } catch (SQLException e) {
		        if (conn != null) {
		            try {
		                conn.rollback(); // Annulla la transazione in caso di errore
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		                throw new RuntimeException("Rollback failed", ex);
		            }
		        }
		        e.printStackTrace();
		        throw new RuntimeException("SQL Error", e);
		    } finally {
		        try {
		            if (st != null) {
		                st.close();
		            }
		            if (conn != null) {
		                conn.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}

	public List<Corso> getCorsiUtente(Utente user) {
		final String sql = "SELECT * "
				+ "FROM acquisti a, corsi c "
				+ "WHERE a.course_id = c.course_id AND a.username = ?";
		List<Corso> result = new ArrayList<Corso>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, user.getUsername());
			ResultSet res = st.executeQuery();
			
			while (res.next()) {
				result.add(new Corso(res.getInt("c.course_id"),
						res.getString("c.course_title"),res.getString("c.url"),res.getDouble("c.price"),
						res.getInt("c.num_subscribers"),res.getInt("c.num_reviews"),res.getInt("c.num_lectures"),
						res.getString("c.level"),res.getInt("c.rating"),res.getDouble("c.content_duration"),
						res.getDate("c.timestamp"),res.getString("c.subject")));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}
		return result;
	}

	public void uploadUser(String nome, String cognome, String username, String password2, double budget) {
	    final String sql = "INSERT INTO utenti (Nome, Cognome, username, password, portafogli) VALUES (?, ?, ?, ?, ?)";
	    
	    Connection conn = null;
	    PreparedStatement st = null;

	    try {
	        conn = DBConnect.getConnection();
	        conn.setAutoCommit(false); // Per usare la transazione

	        st = conn.prepareStatement(sql);

	        
            st.setString(1, nome);
            st.setString(2, cognome);
            st.setString(3, username);
            st.setString(4, password2);
            st.setDouble(5, budget);
            st.addBatch(); // Aggiunge il comando all'elenco dei batch
        

	        int[] results = st.executeBatch(); // Esegue il batch

	        conn.commit(); // Completa la transazione
	        // Se vuoi verificare i risultati, puoi iterare su results che contiene il numero di righe inserite per ogni statement del batch.

	    } catch (SQLException e) {
	        if (conn != null) {
	            try {
	                conn.rollback(); // Annulla la transazione in caso di errore
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	                throw new RuntimeException("Rollback failed", ex);
	            }
	        }
	        e.printStackTrace();
	        throw new RuntimeException("SQL Error", e);
	    } finally {
	        try {
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		
	}

	public void updateBudget(double budget, Utente user) {
	    final String sql = "UPDATE utenti SET portafogli = ? WHERE username = ?";
	    
	    Connection conn = null;
	    PreparedStatement st = null;

	    try {
	        conn = DBConnect.getConnection();
	        conn.setAutoCommit(false); // Per usare la transazione

	        st = conn.prepareStatement(sql);
	        
	        st.setDouble(1, budget);
	        st.setString(2, user.getUsername());

	        // Esegui l'aggiornamento del database
	        int rowsAffected = st.executeUpdate();
	        
	        // Commit la transazione solo se l'aggiornamento è avvenuto con successo
	        if (rowsAffected > 0) {
	            conn.commit(); // Completa la transazione
	        } else {
	            conn.rollback(); // Annulla la transazione se nessuna riga è stata aggiornata
	        }

	    } catch (SQLException e) {
	        if (conn != null) {
	            try {
	                conn.rollback(); // Annulla la transazione in caso di errore
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	                throw new RuntimeException("Rollback failed", ex);
	            }
	        }
	        e.printStackTrace();
	        throw new RuntimeException("SQL Error", e);
	    } finally {
	        try {
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
		
	
	
	
	
}
