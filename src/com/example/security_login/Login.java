package com.example.security_login;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.User;

import com.vaadin.server.Page;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class Login extends LoginDesign {
	
	private static final long serialVersionUID = 1L;
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Security_Login");
    EntityManager em = emf.createEntityManager();
    
	@SuppressWarnings("serial")
	public Login(){
		login_smbtn.addClickListener(new Button.ClickListener() {

			public void buttonClick(ClickEvent event) {
				
			    String username = username_tf.getValue();
			    String password = password_tf.getValue();
			    
			    //Query find user with username
			    Query query = em.createQuery("SELECT u FROM User u WHERE u.username = :username");
			    query.setParameter("username", username);
			            User loggedinUser = (User)query.getSingleResult();
			                if(loggedinUser.getPassword().equals(password)){				            
			                	
			                	RandGen randGen = new RandGen();
			                	String tanCodeString = randGen.gernarate();
			                	
			                	Email email = new Email();
			                	email.sendMail(loggedinUser.getEmail(), tanCodeString);
			                	
			                	loggedinUser.setTan(tanCodeString);
			                	em.getTransaction().begin();
			                    em.merge(loggedinUser);
			                    em.getTransaction().commit();
			                	
			                	Notification notify = new Notification("Er is een TAN code naar je emailadres gestuurd");			                	
			                	notify.show(Page.getCurrent());			          
			                	
			                	final Window window = new Window("Window");
			                    window.setWidth(300.0f, Unit.PIXELS);
			                    final FormLayout content = new FormLayout();
			                   
			                    //TAN-code Textfield
			                    TextField tanCode = new TextField();
			                    tanCode.setImmediate(true);
			                    tanCode.setInputPrompt("TAN-code:");
			                    tanCode.setMaxLength(10);
			                    
			                    //TAN-code Submit Button
			                    Button tanSubmit = new Button();
			                    tanSubmit.setWidth("200");
			                    tanSubmit.setCaption("Bevestig");
			                    
			                    content.addComponent(tanCode);
			                    content.addComponent(tanSubmit);
			                    window.setContent(content);
			             
			                    UI.getCurrent().addWindow(window);
			                    
			                    	tanSubmit.addClickListener(new Button.ClickListener() {
										
										@Override
										public void buttonClick(ClickEvent event) {
											
											String insertTan = tanCode.getValue();
											if(insertTan.equals(loggedinUser.getTan())){
												
												Notification notify = new Notification("Je bent nu ingelogd");
							                	notify.show(Page.getCurrent());
												
											}else{
												Notification notify = new Notification("TAN-code is incorrect!");
							                	notify.show(Page.getCurrent());
											}
											
										}
									});
			                    
			                	}else{
			                		Notification notify = new Notification("Username/Password is invalid !");
				                	notify.show(Page.getCurrent());
			                		
			                	}
			                	
			};
		});
		
	}
}
	

