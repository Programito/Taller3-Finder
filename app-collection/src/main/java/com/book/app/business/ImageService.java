/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.book.app.business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Item;
import entities.Image;


@Stateless
public class ImageService {
	
	@PersistenceContext(unitName = "persistence-unit" )
    private EntityManager entityManager;

	public void deleteAll(){
		entityManager.createQuery("DELETE FROM Image").executeUpdate();
	}
	   
	
    public Image insert(byte[] bytes){
    	
    	Image image = new Image();
    	image.setBytes(bytes);
    	
        entityManager.persist(image);
        entityManager.flush();
        
        
        String url = "image_" + image.getId() + ".jpg";
        image.setUrl(url); 
        entityManager.persist(image);
        return image; 
    }
    
    
    public Image find(String url){
    	Image image=null; 
    	
    	try{
	    	url = url.replace("image_", ""); 
	    	url = url.replace(".jpg", "");    	
	    	int id = Integer.valueOf(url);  	
	    	image =  entityManager.find(Image.class,id);
	    	
	    	/** Hay que leer los bytes porque se declaro como LAZY */
	    	image.getBytes(); 
	    	
    	}catch(Exception e){
    		
    	}
    	
    	return image; 
    }
    
    public void remove(Item book){
    	entityManager.remove(book); 
    }
    
    public void update(Item book){
    	entityManager.merge(book); 
    }
    
	

}
