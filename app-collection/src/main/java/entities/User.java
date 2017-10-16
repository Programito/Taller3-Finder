package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


@Entity
@NamedQueries({
	@NamedQuery(name=User.QUERY_USER_BY_EMAIL,
				query = "SELECT u FROM User u WHERE u.email =:email"),
})

public class User {
	
	public static final String QUERY_USER_BY_EMAIL="findByEmail"; 
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private String id;
	  	  
	private String name; 
	private String email;
	
	@OneToMany(mappedBy ="user", 
		 	cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<Collection> collections = new HashSet<>(); 
 
	 

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Set<Collection> getCollections() {
		return collections;
	}

	  
}
