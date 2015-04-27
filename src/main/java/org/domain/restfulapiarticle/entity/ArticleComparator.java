package org.domain.restfulapiarticle.entity;

import java.util.Comparator;
import java.util.Date;

public class ArticleComparator<Article> implements Comparator<Article> {
	
	public int compare(Object obj1, Object obj2) {
		
		Date date1 = (Date) obj1;
		Date date2 = (Date) obj2;
		
		return date2.compareTo(date1);
	}
}
