package com.ir.proj4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class QueryData {
	private Response response;
	@JsonIgnore
	private String responseHeader;
	
	private Facet_Counts facet_counts;
	

	public Facet_Counts getFacet_counts() {
		return facet_counts;
	}

	public void setFacet_counts(Facet_Counts facet_counts) {
		this.facet_counts = facet_counts;
	}
	
	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public String getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(String responseHeader) {
		this.responseHeader = responseHeader;
	}
}
