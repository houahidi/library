package fr.uni.institute.library.web;

import fr.uni.institute.library.business.inventory.AudioRecord;

public class AudioRecordDTO extends TitleDTO {

	public AudioRecordDTO(AudioRecord audioRecord){
		super(audioRecord) ;
	}
	
	public String getLabel(){
		return ((AudioRecord) getTitle()).getLabel() ;
	}

	public int getDuration(){
		return ((AudioRecord) getTitle()).getDuration() ;
	}
	
	public String getTracks(){
		return ((AudioRecord) getTitle()).getTracks() ;		
	}
	
}
