package com.ir.proj4.model;

import java.util.ArrayList;

public class Response {
	
	int numFound;
	int start;
	ArrayList<Docs> docs;
	
	public int getNumFound() {
		return numFound;
	}
	public void setNumFound(int numFound) {
		this.numFound = numFound;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	
	public ArrayList<Docs> getDocs() {
		return docs;
	}
	public void setDocs(ArrayList<Docs> docs) {
		this.docs = docs;
	}
	

}
