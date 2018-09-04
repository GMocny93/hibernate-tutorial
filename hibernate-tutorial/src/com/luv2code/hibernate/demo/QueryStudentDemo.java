package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;


public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		try {			
			
			//start a transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			//display the students
			displayStudents(theStudents);
			
			//query students: lastName='Doe'
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			
			//display the students where lastName='Doe'
			System.out.println("\nStudents where last name = Doe");
			displayStudents(theStudents);
			
			
			//query students lastaName='Doe' OR firstName='Mary'
			theStudents = session.createQuery("from Student s where s.lastName='Doe' OR s.firstName='Mary'").getResultList();
			
			//display the students where astaName='Doe' OR firstName='Mary'
			System.out.println("\nStudents where last name = Doe OR first name = Mary");
			displayStudents(theStudents);
			
			
			//query students where email LIKE '@com'
			theStudents = session.createQuery("from Student s where s.email LIKE '%@com'").getResultList();
			
			//display the students where astaName='Doe' OR firstName='Mary'
			System.out.println("\nStudents where email @com");
			displayStudents(theStudents);
						
			
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
					
			
		}
		finally {
			factory.close();
		}
		
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}



}
