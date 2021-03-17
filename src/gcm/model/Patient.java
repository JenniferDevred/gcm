package gcm.model;

import java.util.Date;

public class Patient {
	
	private int nss;
	private String nom;
	private String prenom;
	private String adresse;
	private Date datedeNaissance;
	/**
	 * @return the datedeNaissance
	 */

	private String ville;
	/**
	 * @return the datedeNaissance
	 */
	public Date getDatedeNaissance() {
		return datedeNaissance;
	}
	/**
	 * @param datedeNaissance the datedeNaissance to set
	 */
	public void setDatedeNaissance(Date datedeNaissance) {
		this.datedeNaissance = datedeNaissance;
	}
	private int codePostal;
	/**
	 * @return the nss
	 */
	public int getNss() {
		return nss;
	}
	/**
	 * @param nss the nss to set
	 */
	public void setNss(int nss) {
		this.nss = nss;
	}
	@Override
	public String toString() {
		return "Patient [nss=" + nss + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", date de naissance=" + datedeNaissance
				+ ", ville=" + ville + ", codePostal=" + codePostal + "]";
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}
	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	/**
	 * @return the age
	 */

	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}
	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	/**
	 * @return the codePostal
	 */
	public int getCodePostal() {
		return codePostal;
	}
	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	
	

}
