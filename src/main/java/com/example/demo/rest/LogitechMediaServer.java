package com.example.demo.rest;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
{
  "id":0,
  "params":["00:04:20:17:41:dd",
  ["current_title","?"]],
  "result":{"_current_title":"Stephen Stills - Love The One You're With "},
  "method":"slim.request"
}

 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class LogitechMediaServer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4487940441811189795L;
	private Long id;
	private String params;
	private String result;
	private String method;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParams() {
		return params;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public void setParams(String params) {
		this.params = params;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Override
	public String toString() {
		return "LogitechMediaServer [id=" + id + ", params=" + params + ", result=" + result + ", method=" + method
				+ "]";
	}
}
