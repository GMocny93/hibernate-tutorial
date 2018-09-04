package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		try {			
			//create student object
			System.out.println("Creating new student object...");
			Student tempStudent = new Student("Paul", "Wall", "email@com");
			
			//start a transaction
			session.beginTransaction();
			
			//save the student object]
			System.out.println("Saving the student...");
			System.out.println(tempStudent);
			session.save(tempStudent);
						
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
			
			//add new code - read:
			//find out the student's id: primary key
			System.out.println("Save stdudent. Generated id: " + tempStudent.getId());
			
			//now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + tempStudent.getId() );
			Student myStudent = session.get(Student.class, tempStudent.getId());
			System.out.println("Get complete: " + myStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}
		finally {
			factory.close();
		}
		
	}



}