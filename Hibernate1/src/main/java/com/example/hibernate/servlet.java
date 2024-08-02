package com.example.hibernate;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
@WebServlet("/register")
public class servlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata md=new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf=md.buildSessionFactory();
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
	String name=req.getParameter("name");
	String email=req.getParameter("email");
	long phoneno=Long.parseLong(req.getParameter("phoneno"));
	application ap=new application(0,name,email,phoneno);
	application_controller ac=new application_controller();
	    ac.insertion();
//	    if(i) {
//	    	resp.sendRedirect("register.jsp");
//	    }
//	    else {
//	    	resp.sendRedirect("error.jsp");
//	    }
	    t.commit();
//		
	}

}
