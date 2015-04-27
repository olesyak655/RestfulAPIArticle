package org.domain.restfulapiarticle.entity;

import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;
import java.util.Date;
//import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime; 
import org.joda.time.contrib.hibernate.PersistentDateTime;

@Entity 
@Table(name="article") 
/*@NamedQueries({
	@NamedQuery(name="Article.findAll", query="SELECT a FROM Article a"),
	@NamedQuery(name="Article.findById", query="SELECT a FROM Article a WHERE a.id=:id")
}) */
public class Article implements Serializable {
	private long id;
	private String titleArticle;
	private String bodyArticle;
	private String author;
	private Date dateCreate;
	private Date dateUpdate;
	
	private int version;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name="id")
	public long getId() {
		return id;
	}
	
	@Column(name="title_article")
	public String getTitleArticle() {
		return titleArticle;
	}
	
	@Column(name="body_Article")
	public String getBodyArticle() {
		return bodyArticle;
	}
	
	@Column(name="author")
	public String getAuthor() {
		return author;
	}
	
	@Column(name="date_create") 
	@Temporal(TemporalType.DATE) 
	public Date getDateCreate() {
		return dateCreate;
	}
	
	@Column(name="date_update") 
	@Temporal(TemporalType.DATE) 
	public Date getDateUpdate() {
		return dateUpdate;
	}
	
	@Version
	@Column(name="version")
	public int getVersion() {
		return version;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setTitleArticle(String titleArticle) {
		this.titleArticle = titleArticle;
	}

	public void setBodyArticle(String bodyArticle) {
		this.bodyArticle = bodyArticle;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", titleArticle=" + titleArticle
				+ ", bodyArticle=" + bodyArticle + ", author=" + author
				+ ", dateCreate=" + dateCreate + ", dateUpdate="
				+ dateUpdate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result
				+ ((titleArticle == null) ? 0 : titleArticle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (titleArticle == null) {
			if (other.titleArticle != null)
				return false;
		} else if (!titleArticle.equals(other.titleArticle))
			return false;
		return true;
	}
	
	
}
