package fr.uni.institute.library.web;

import javax.servlet.http.HttpServletRequest;

import fr.uni.institute.library.business.inventory.AudioRecord;
import fr.uni.institute.library.business.inventory.Title;

public class ServletAudioRecordSave extends ServletTitleSave {
	protected void completeTitle(HttpServletRequest request, Title title){
		AudioRecord audio = (AudioRecord)title ;
		audio.setLabel(request.getParameter("titleLabel"));
		try {
			audio.setDuration(Integer.parseInt(request.getParameter("titleDuration")));
		} catch (Exception e) {	}
		audio.setTracks(request.getParameter("titleTracks"));
	}
	
	protected Title createNewTitle() {
		return new AudioRecord(0) ;
	}
	
}
