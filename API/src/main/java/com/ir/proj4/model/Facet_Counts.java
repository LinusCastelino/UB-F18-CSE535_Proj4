package com.ir.proj4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Facet_Counts {
	@JsonIgnore
	String facet_queries;

	Facet_Fields facet_fields;
	@JsonIgnore
	String facet_ranges;
	@JsonIgnore
	String facet_intervals;
	@JsonIgnore
	String facet_heatmaps;

	public String getFacet_queries() {
		return facet_queries;
	}

	public void setFacet_queries(String facet_queries) {
		this.facet_queries = facet_queries;
	}

	public Facet_Fields getFacet_fields() {
		return facet_fields;
	}

	public void setFacet_fields(Facet_Fields facet_fields) {
		this.facet_fields = facet_fields;
	}

	public String getFacet_ranges() {
		return facet_ranges;
	}

	public void setFacet_ranges(String facet_ranges) {
		this.facet_ranges = facet_ranges;
	}

	public String getFacet_intervals() {
		return facet_intervals;
	}

	public void setFacet_intervals(String facet_intervals) {
		this.facet_intervals = facet_intervals;
	}

	public String getFacet_heatmaps() {
		return facet_heatmaps;
	}

	public void setFacet_heatmaps(String facet_heatmaps) {
		this.facet_heatmaps = facet_heatmaps;
	}
}
