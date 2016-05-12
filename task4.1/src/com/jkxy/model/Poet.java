package com.jkxy.model;

public class Poet extends IdEntity {

	private String name;
	private String content;
	private String title;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Poet [name=" + name + ", content=" + content + ", title=" + title + ", id=" + id + "]";
	}

}
