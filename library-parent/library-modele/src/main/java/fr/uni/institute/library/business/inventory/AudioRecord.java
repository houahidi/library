/**
 * 
 */
package fr.uni.institute.library.business.inventory;

import java.util.Date;

/**
 * @author Stephane Lietard
 * 
 */
public class AudioRecord extends Title {
	
	
	
	

	/**
	 * the label (or editor) of this audio record
	 */
	private String label;

	/**
	 * the duration time (in seconds)
	 */
	private int duration;

	/**
	 * a free text to describe tracks
	 */
	private String tracks;

	/**
	 * Constructs a new audio record with only his unique id
	 * 
	 * @param id
	 *            unique id of this audio record
	 */
	public AudioRecord(int id) {
		this(id, "undefined", null, "unknown", "unknown", 0, "");
	}

	/**
	 * Constructs a new audio record
	 * 
	 * @param id
	 *            unique id of this audio record
	 * @param name
	 *            the name
	 * @param date
	 *            the published date
	 * @param author
	 *            the author of this audio record
	 * @param label
	 *            the editor of this audio record
	 * @param duration
	 *            the duration time in seconds for this audio record
	 * @param tracks
	 *            a free text to describe tracks of this audio record
	 */
	public AudioRecord(int id, String name, Date date, String author,
			String label, int duration, String tracks) {
		super(id, name, date, author);
		this.label = label;
		this.duration = duration;
		this.tracks = tracks;
	}

	/**
	 * implementation of the Visitor GoF Design Pattern
	 * 
	 * @param v
	 *            the generic visitor who ask to visit this agregat
	 */
	public void accept(InventoryVisitor v) throws InventoryException {
		v.visitAudioRecord(this);
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @return the tracks
	 */
	public String getTracks() {
		return tracks;
	}

	/**
	 * @param duration
	 *            the duration in seconds to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @param tracks
	 *            the tracks to set
	 */
	public void setTracks(String tracks) {
		this.tracks = tracks;
	}

	/**
	 * a textual representation of an AudioRecord
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("AudioRecord (");
		buf.append(super.toString());
		buf.append(") : ");
		buf.append(" - duration : ");
		buf.append(this.getDuration());
		buf.append("s - label : ");
		buf.append(this.getLabel());
		return buf.toString();
	}
}
