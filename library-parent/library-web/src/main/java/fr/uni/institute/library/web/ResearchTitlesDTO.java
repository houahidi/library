package fr.uni.institute.library.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ResearchTitlesDTO {
	private String researchParameterName;

	private String researchParameterAuthor;

	private String researchParameterDate;
	
	private ArrayList titles ;

	public String getResearchParameterAuthor() {
		return researchParameterAuthor;
	}

	public String getResearchParameterDate() {
		return researchParameterDate;
	}

	public Date getResearchParameterDateAsDate() throws Exception {
		Date date = null;
		if (researchParameterDate != null && !"".equals(researchParameterDate)) {
			String[] formats = { "dd/MM/yyyy", "MM/yyyy", "dd/MM/yy", "MM/yy" };
			for (int i = 0; i < formats.length; i++) {
				try {
					SimpleDateFormat df = new SimpleDateFormat(formats[i]);
					date = df.parse(researchParameterDate);
					break;
				} catch (Exception e) {
				}
			}
			if (date == null) {
				throw new Exception("invalid date dormat");
			}
		}
		return date;
	}

	public String getResearchParameterName() {
		return researchParameterName;
	}

	void setResearchParameterAuthor(String researchParameterAuthor) {
			this.researchParameterAuthor = researchParameterAuthor;
	}

	void setResearchParameterDate(String researchParameterDate) {
			this.researchParameterDate = researchParameterDate;
	}

	void setResearchParameterName(String researchParameterName) {
			this.researchParameterName = researchParameterName;
	}

	public ArrayList getTitles() {
		return titles;
	}

	void setTitles(ArrayList titles) {
		this.titles = titles;
	}

}
