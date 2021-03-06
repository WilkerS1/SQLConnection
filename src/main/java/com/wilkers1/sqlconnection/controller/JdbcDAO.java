package com.wilkers1.sqlconnection.controller;

import java.lang.SecurityException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

abstract class JdbcDAO {
	private static Connection _conn = null;
	protected static SecurityException idExp = new SecurityException("The constructor with ID is not intended for data registrations.");
	protected static SecurityException noIdExp = new SecurityException("Cannot delete registry with an unespecified ID. Risk of unintended deletion.");
	protected static ClassCastException invalidModelExp = new ClassCastException("Invalid Model class received.");
	
	protected static void execute(String query) throws SQLException, NullPointerException {
		if(_conn == null) {
			_conn = JdbUtil.getConnection();
		}
		try {
			Statement statement = _conn.createStatement();
			statement.execute(query);
			statement.close();
		} catch (NullPointerException exp) {
			throw new NullPointerException("Could not execute this action.");
		}
		JdbUtil.close();
	}
	
	protected abstract void insert(Model mdl) throws SQLException;
	protected abstract void delete(Model mdl) throws SQLException, NullPointerException, IllegalAccessException;
	
	//TODO lies
	public static void main(String[] a) {
		Scanner s = new Scanner(System.in);
		String sc = "";
		while(sc.equals(".quit!")) {
			System.out.print("\ninput> ");
			sc = s.next();
		}
		System.out.println(sc);
	}
}
