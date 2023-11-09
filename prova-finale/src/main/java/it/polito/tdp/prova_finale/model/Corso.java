package it.polito.tdp.prova_finale.model;

import java.util.Date;
import java.util.Objects;

public class Corso {

	private int courseID;
	private String courseTitle;
	private String url;
	private double price;
	private int nSubscribers;
	private int nReviews;
	private int nLectures;
	private String level;
	private int rating;
	private double duration;
	private Date start;
	private String subject;
	
	
	public int getCourseID() {
		return courseID;
	}


	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}


	public String getCourseTitle() {
		return courseTitle;
	}


	public void setCourseTitle(String couseTitle) {
		this.courseTitle = couseTitle;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getnSubscribers() {
		return nSubscribers;
	}


	public void setnSubscribers(int nSubscribers) {
		this.nSubscribers = nSubscribers;
	}


	public int getnReviews() {
		return nReviews;
	}


	public void setnReviews(int nReviews) {
		this.nReviews = nReviews;
	}


	public int getnLectures() {
		return nLectures;
	}


	public void setnLectures(int nLectures) {
		this.nLectures = nLectures;
	}


	public String getLevel() {
		return level;
	}


	public void setLevel(String level) {
		this.level = level;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public double getDuration() {
		return duration;
	}


	public void setDuration(double duration) {
		this.duration = duration;
	}


	public Date getStart() {
		return start;
	}


	public void setStart(Date start) {
		this.start = start;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public Corso(int courseID, String courseTitle, String url, double price, int nSubscribers, int nReviews,
			int nLectures, String level, int rating, double duration, Date start, String subject) {
		super();
		this.courseID = courseID;
		this.courseTitle = courseTitle;
		this.url = url;
		this.price = price;
		this.nSubscribers = nSubscribers;
		this.nReviews = nReviews;
		this.nLectures = nLectures;
		this.level = level;
		this.rating = rating;
		this.duration = duration;
		this.start = start;
		this.subject = subject;
	}


	@Override
	public int hashCode() {
		return Objects.hash(courseID, courseTitle, duration, level, nLectures, nReviews, nSubscribers, price, rating,
				start, subject, url);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		return courseID == other.courseID && Objects.equals(courseTitle, other.courseTitle)
				&& Double.doubleToLongBits(duration) == Double.doubleToLongBits(other.duration)
				&& Objects.equals(level, other.level) && nLectures == other.nLectures && nReviews == other.nReviews
				&& nSubscribers == other.nSubscribers
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && rating == other.rating
				&& Objects.equals(start, other.start) && Objects.equals(subject, other.subject)
				&& Objects.equals(url, other.url);
	}


	@Override
	public String toString() {
		return "" + courseTitle + "";
	}
	
	
	
}
