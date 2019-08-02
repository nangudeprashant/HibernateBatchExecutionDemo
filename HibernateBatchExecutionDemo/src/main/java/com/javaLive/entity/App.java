package com.javaLive.entity;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.javaLive.databaseUtil.*;

public class App {
	final Logger logger = LoggerFactory.getLogger(App.class);
	Student student1 = new Student(73, "Name73", "Address73");
	Student student2 = new Student(74, "Name74", "Address74");
	Transaction transaction = null;

	public boolean batchInsert() {
		System.out.println("Table contents before starting any operation:");
		new App().getStudentList();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			System.out.println("\n\n");
			// save the student objects in bulk.......
			for (int i = 101; i <= 300; i++) {
				Student student1 = new Student(i, ("Name" + i), "Address" + i);
				session.save(student1);
				if (i % 5 == 0) { // 50 is the batch_size
					session.flush();
					session.clear();
					session.getTransaction().commit();
					session.beginTransaction();
				}
			}
			System.out.println("Displaying student list after inserting new entires");
			logger.info("Displaying student list after inserting new entires");
			new App().getStudentList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean batchDelete() {
		System.out.println("Table contents before starting any operation:");
		new App().getStudentList();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			System.out.println("\n\n");
			// delete the student objects
			for (int i = 1; i <= 300; i++) {
				Student student1 = new Student(i, ("Name" + i), "Address" + i);
				session.delete(student1);
				if (i % 50 == 0) { // 50 is the batch_size
					session.flush();
					session.clear();
					session.getTransaction().commit();
					session.beginTransaction();
				}
			}
			// commit transaction
			transaction.commit();
			logger.info("Displaying student list after deleting new entires");
			System.out.println("Displaying student list after deleting the entires");
			new App().getStudentList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public static void main(String[] args) {
		App obj=new App();
		obj.batchInsert();
		//obj.batchDelete();
	}

	public void getStudentList() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<Student> students = session.createQuery("from Student", Student.class).list();
			for (Student s : students) {
				System.out.println(s.toString());
			}
			;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}