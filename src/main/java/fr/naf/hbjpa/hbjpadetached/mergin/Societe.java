package fr.naf.hbjpa.hbjpadetached.mergin;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Societe {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String nom;
	
	private String type;
	
	@OneToMany(mappedBy="societe",cascade=CascadeType.MERGE)
	private Set<Salarie> salaries =new HashSet<Salarie>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Salarie> getSalaries() {
		return salaries;
	}

	public void setSalaries(Set<Salarie> salaries) {
		this.salaries = salaries;
	}
	
	
	
}
