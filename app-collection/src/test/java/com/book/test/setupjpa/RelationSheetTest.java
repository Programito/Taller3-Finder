package com.book.test.setupjpa;

import java.util.List;

import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.book.app.business.InfAppServices;
import com.book.test.tools.MockHelper;
import com.book.test.tools.TestEjbHelper;

import entities.Collection;
import entities.Image;
import entities.Item;
import entities.User;

/**
 * Test funcional que comprueba las relaciones JPA, sobre todo removal
 * @author campino
 *
 */
public class RelationSheetTest {
	
	

	@Inject
	private InfAppServices service; 
 


   @Before
    public void before() throws NamingException{   
    	EJBContainer ejbContainer = TestEjbHelper.getEjbContainer();  	
    	 ejbContainer.getContext().bind("inject", this);    	
    }
   
    
     @Test
     public void  removeChilds(){
    	  
    	 /* Borrar todo manualmente, asi garantiza no basura
    	    Hay formas mas elegantes, pero esto es un test, 
    	    no hay que ser dogmatico y code fundamentalista */
	    	 service.deleteAll(Image.class);
	    	 service.deleteAll(Item.class);
	    	 service.deleteAll(Collection.class);
	    	 service.deleteAll(User.class);

    	 
    	 /* Mock, tambien hay librerias para injectar mocks, podeis ver mokito */
	     User user = MockHelper.mockUser("User Test",MockHelper.TEST_USER_EMAIL);     	 
    	 Collection collection =  MockHelper.mockCollection("Test Collection"); 
    	 user.getCollections().add(collection); 
    	 collection.setUser(user);
    	 
    	 /* Al hacer sign todo el objeto persiste, includido la colleccion hija */
		 service.signUpUser(user); 
		 
		 /* Recuperamos el objeto de la bd, si lo encuentra
		  *  es que el objeto fue persistido sin ningun problema, osea esta en db
		  */
		 User resultUser = service.find(User.class,user.getId());  
		 
		  
		 /*
		  * Verificamnos que hay un user y un collection 
		  */
		 List<User> listUsers = service.getAll(User.class); 
		 List<Collection> listCollec = service.getAll(Collection.class); 
		  
		  Assert.assertEquals(1, listUsers.size());
		  Assert.assertEquals(1, listCollec.size());
		 
		  
		  Assert.assertEquals(collection.getName(),
				  resultUser.getCollections().iterator().next().getName());
		  
		  
		  /*
		   *  Al eliminamos el user, esperamos que por cascada la collection deberia eliminarse
		   */
		  
		  /**
		   * 
		   * service.deleteAll(User.class); 
		   *  
		   * Si intento remover todo con service.deleteAll(User.class);  se lanza una excepcion 
		   * Cannot delete or update a parent row: a foreign key constraint fails
		   * (`dbtest`.`collection`, CONSTRAINT `FK_COLLECTION_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`ID`))
		   * 
		   * Hay que utilizar el metodo removal del emptity manager para poder que el cascate funcione adecuadamente
		   * el servicio remove elimina usando este metodo. 
		   *  
		   * NOTE QUE: el objeto debe estar en estado managed para poder ser removido 
		   */ 
		  
		  //service.deleteAll(User.class); 
		
		  service.remove(User.class,resultUser.getId()); 

		  /*
		   * Verificamos que no hay mas objetos en la base de datos. 
		   */
		  listUsers = service.getAll(User.class);
		  listCollec = service.getAll(Collection.class); 	  
		  Assert.assertEquals(0, listUsers.size());
		  Assert.assertEquals(0, listCollec.size());
		  	  
		  
		  
		  
     }
     
     
     
     
  
    
     
     

}
