package com.example.security_login;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;


@SuppressWarnings("serial")
@Theme("security_login")
public class Security_loginUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Security_loginUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		
		final Login layout = new Login();
		layout.setMargin(true);
		setContent(layout);
		
//		Login login = new Login();
//		
//		final ApplicationDesign appDesign = new ApplicationDesign();
//		appDesign.app_panel.setContent(login);
//		setContent(appDesign);
		
	}
}