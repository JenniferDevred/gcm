package gcm.model;

import java.util.ArrayList;
import java.util.List;

// dao : Data Access Object

public class PatientDaoversion1 implements IPatientDao {

	static private List<Patient> listPatient = new ArrayList<Patient>();

	public void add(Patient patient) {

		listPatient.add(patient);
	}

	public void delete(int nss) {

		Patient patient = new Patient();
		patient.setNss(nss);
		listPatient.remove(patient);

	}

	public void update(int nss) {

		Patient patient = new Patient();
		patient.setNss(nss);
		int index = 1;
		listPatient.add(index, patient);
	}

	public List<Patient> findAll() {
		
		List<Patient> list=listPatient;

		return list;

	}

	public void add() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(int nss, String ville, String adresse) {
		// TODO Auto-generated method stub
		
	}

}
