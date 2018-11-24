package com.ir.proj4.service;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ir.proj4.model.Response;

public class QueryData {
	private Response response;
	@JsonIgnore
	private HashMap<String, Object> responseHeader;
	private HashMap<String, Object> facet_counts;
	

	public HashMap<String, Object> getFacet_counts() {
		return facet_counts;
	}

	public void setFacet_counts(HashMap<String, Object> facet_counts) {
		this.facet_counts = facet_counts;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public HashMap<String, Object> getResponseHeader() {
		return responseHeader;
	}

	public void setResponseHeader(HashMap<String, Object> responseHeader) {
		this.responseHeader = responseHeader;
	}
}
