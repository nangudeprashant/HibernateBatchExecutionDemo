package com.javaLive.databaseUtil;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * @author JavaLive.com Hibernate Batch processing is an easy way to add
 *         multiple statements into a batch and execute that batch by making a
 *         single round trip to the database. This tutorial shows how to create
 *         batch insert and batch update statements using JPA and Hibernate.
 * 
 *         Let’s start by trying to understand the concept of Batch processing.
 *         It’s an automatic treatment of the non-interactive jobs.
 *         Non-interactive means that there is no human intervention as, for
 *         example, form filling or manual configuration for every treated task.
 * 
 *         A good example of batch processing is the billing system of your
 *         mobile. Last day of every month you receive a billing with an amount
 *         to pay. The amount is calculated by adding the price of every call
 *         you made. The calculation is made automatically, at the end of every
 *         month. You don’t receive the invoice after every call.
 * 
 *         Consider a situation when developers need to upload a large number of
 *         records into the database using Hibernate.
 *
 */
public class HibernateUtil {
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				// Create registry
				registry = new StandardServiceRegistryBuilder().configure().build();
				// Create MetadataSources
				MetadataSources sources = new MetadataSources(registry);
				// Create Metadata
				Metadata metadata = sources.getMetadataBuilder().build();
				// Create SessionFactory
				sessionFactory = metadata.getSessionFactoryBuilder().build();
			} catch (Exception e) {
				e.printStackTrace();
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
			}
		}
		return sessionFactory;
	}

	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	/*
	 * private static final SessionFactory sessionFactory1;
	 * 
	 * static { try { sessionFactory1 = new
	 * AnnotationConfiguration().configure().buildSessionFactory(); } catch
	 * (Throwable ex) { System.err.println("Initial SessionFactory creation failed."
	 * + ex); throw new ExceptionInInitializerError(ex); } }
	 * 
	 * public static SessionFactory getSessionFactory1() { return sessionFactory1; }
	 */
}
