package com.ir.proj4.model;

import java.util.ArrayList;
import java.util.List;

public class Response {
	int numFound;
	int start;
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
	public float getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(float maxScore) {
		this.maxScore = maxScore;
	}
	public ArrayList<Docs> getDocs() {
		return docs;
	}
	public void setDocs(ArrayList<Docs> docs) {
		this.docs = docs;
	}
	float maxScore;
	ArrayList<Docs> docs;

}
