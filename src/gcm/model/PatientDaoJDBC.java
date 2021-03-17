package gcm.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientDaoJDBC implements IPatientDao {
	
	Statement stmt;
	
	public PatientDaoJDBC() {
		try {
			 stmt = JdbcConnexion.connecter().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(Patient p) {
		// TODO Auto-generated method stub

			try {
			String sql="insert into patient(nss,nom,prenom,adresse,DATE_DE_NAISSANCE,ville) values("+p.getNss()+",'"+p.getNom()+"','"+p.getPrenom()+"','"+p.getAdresse()+"',"+p.getDatedeNaissance()+",'"+p.getVille()+"')";
	            stmt.execute(sql);
	            
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}


	@Override
	public void delete(int nss) {
		// TODO Auto-generated method stub
		
		try {
			
			stmt.execute("delete from patient where nss="+nss);
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(int nss, String ville, String adresse) {
		// TODO Auto-generated method stub
		
		try {
			
			String sql="update patient set ville='"+ville+"',adresse='"+adresse+"' where nss="+nss+" ";
			stmt.executeQuery(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Patient> findAll() {
		// TODO Auto-generated method stub
		List<Patient> list=new ArrayList<Patient>();
		
		try {
			
			ResultSet rs = stmt.executeQuery("select* from patient");

			while (rs.next()) {

				int nss = rs.getInt("nss");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String adresse = rs.getString("adresse");
				String ville=rs.getString("ville");
				Date datedenaissance=rs.getDate("DATE_DE_NAISSANCE");
				
				//remplir l'objet patient
				
				Patient patient=new Patient();
				patient.setNss(nss);
				patient.setNom(nom);
				patient.setPrenom(prenom);
				patient.setAdresse(adresse);
				patient.setDatedeNaissance(datedenaissance);
				patient.setVille(ville);
				
				list.add(patient);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return list;
	}
	
	public static void main(String[] args) {
		
		PatientDaoJDBC patientDaoJDBC= new PatientDaoJDBC();
		Patient p=new Patient();
		p.setNss(12);
		p.setNom("Hubert");
		p.setPrenom("Bidule");
		p.setAdresse("25,rue des machins");
		p.setCodePostal(59300);
		//p.setDatedeNaissance(new Date());
		p.setVille("Valenciennes");
		patientDaoJDBC.add(p);
		
	}

}
