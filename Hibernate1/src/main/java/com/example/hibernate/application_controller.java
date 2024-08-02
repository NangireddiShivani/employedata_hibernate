package com.example.hibernate;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;



public class application_controller {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int ch;
		do {
			displaymenu();
			ch=Integer.parseInt(sc.next());
			switch(ch) {
			case 1:
				insertion();
				break;
			case 2:
				deletion();
				break;
			case 3:
				upadate();
				break;
			case 4:
				getall();
				break;
			case 5:
				getbyid();
				break;
			case 6:
				exit();
				break;	
			default:
				System.out.println("invalid operation");
				break;
			}
			
		} while (ch>0);
	
	}
	private static void exit() {
		// TODO Auto-generated method stub
		
	}
	private static void getbyid() {
		// TODO Auto-generated method stub
		Scanner src=new Scanner(System.in);
		StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata md=new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf=md.buildSessionFactory();
		Session s=sf.openSession();
		System.out.println("enter id");
		int id=src.nextInt();
		Transaction t=s.beginTransaction();
		application ap= s.get(application.class, id);
		
		if(ap!=null) {
			System.out.println("id" +ap.getId());
			System.out.println("name"+ap.getName());
			System.out.println("email"+ap.getEmail());
			System.out.println("phoneno"+ap.getPhoneno());
		}
		else{
			System.out.println("no data");
		}
		t.commit();
		
		
		
		
	}
	private static void getall() {
		// TODO Auto-generated method stub
		StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata md=new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf=md.buildSessionFactory();
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		List<application> li=s.createQuery("from application",application.class).list();
		for(application a:li) {
			System.out.println("name:" +a.getName());
			System.out.println("email:" +a.getEmail());
			System.out.println("phoneno:" +a.getPhoneno());
			
		}
		t.commit();
		
	}
	private static void upadate() {
		Scanner src=new Scanner(System.in);
		StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata md=new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf=md.buildSessionFactory();
		Session s=sf.openSession();
		System.out.println("enter id");
		int id=src.nextInt();
		Transaction t=s.beginTransaction();
		application ap=s.get(application.class, id);
		if(ap!=null) {
			System.out.println(" enter new name");
			String name=src.next();
			ap.setName(name);
			System.out.println("enter new email");
			String email=src.next();
			ap.setEmail(email);
			System.out.println("enter new phoneno");
			long phoneno=src.nextLong();
			ap.setPhoneno(phoneno);
			s.update(ap);
		}
		else {
			System.out.println("id invalid");
		}
		t.commit();
		
	}
	private static void deletion() {
		// TODO Auto-generated method stub
		Scanner scr=new Scanner(System.in);
		StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata md=new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf=md.buildSessionFactory();
		Session s=sf.openSession();
		System.out.println("enter id:");
		int id=scr.nextInt();
		
		
		Transaction t=s.beginTransaction();
		application a=s.get(application.class,id);
		s.delete(a);
		System.out.println("successfully deleted");
		
		t.commit();
		
	}
	public static void insertion() {
		// TODO Auto-generated method stub
		Scanner scr=new Scanner(System.in);
		StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata md=new MetadataSources(ssr).getMetadataBuilder().build();
		SessionFactory sf=md.buildSessionFactory();
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		application a=new application();
		System.out.println("enter your name");
		String name=scr.nextLine();
		a.setName(name);
		System.out.println("enter your email");
		String email=scr.nextLine();
		a.setEmail(email);
		System.out.println("enter your phoneno");
		long phoneno=scr.nextLong();
		a.setPhoneno(phoneno);
		s.save(a);
		t.commit();
		
		
	}
	private static void displaymenu() {
		System.out.println("___________");
		System.out.println("\t1.insertion");
		System.out.println("\t2.deletion");
		System.out.println("\t3.update");
		System.out.println("\t4.getall");
		System.out.println("\t5.getbyid");
		
	}

}
