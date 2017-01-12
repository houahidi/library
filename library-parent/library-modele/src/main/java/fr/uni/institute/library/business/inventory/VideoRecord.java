/**
 * 
 */
package fr.uni.institute.library.business.inventory;

import java.util.Date;

/**
 * @author Stephane Lietard
 * 
 */
public class VideoRecord extends Title {

	/**
	 * the genre of this video
	 */
	private String genre;

	/**
	 * the duration in minutes
	 */
	private int duration;

	/**
	 * a free text to describe the casting of this video
	 */
	private String cast;

	/**
	 * Constructs a new video record with only his unique id
	 * 
	 * @param id
	 *            unique id of this video record
	 */
	public VideoRecord(int id) {
		this(id, "unknown", null, "unknown", "", 0, "");
	}

	/**
	 * Constructs a new video records
	 * 
	 * @param id
	 *            unique id of this book
	 * @param name
	 *            the name
	 * @param date
	 *            the published date
	 * @param author
	 *            the author of this book
	 * @param genre
	 *            the genre of this video
	 * @param duration
	 *            the duration in minutes
	 * @param cast
	 *            a free text to describe the casting of this video
	 */
	public VideoRecord(int id, String name, Date date, String author,
			String genre, int duration, String cast) {
		super(id, name, date, author);
		this.genre = genre;
		this.duration = duration;
		this.cast = cast;
	}

	/**
	 * implementation of the Visitor GoF Design Pattern
	 * 
	 * @param v
	 *            the generic visitor who ask to visit this agregat
	 */
	public void accept(InventoryVisitor v) throws InventoryException {
		v.visitVideoRecord(this);
	}

	/**
	 * @return the cast
	 */
	public String getCast() {
		return cast;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param cast
	 *            the cast to set
	 */
	public void setCast(String cast) {
		this.cast = cast;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @param genre
	 *            the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return a textual representation of this video record
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("VideoRecord (");
		buf.append(super.toString());
		buf.append(") : ");
		buf.append(" - duration : ");
		buf.append(this.getDuration());
		buf.append("s - cast : ");
		buf.append(this.getCast());
		buf.append(" - genre : ");
		buf.append(this.getGenre());
		return buf.toString();
	}
}
