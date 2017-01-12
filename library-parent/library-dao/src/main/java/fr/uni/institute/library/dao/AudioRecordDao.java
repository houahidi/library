package fr.uni.institute.library.dao;

import java.util.Collection;

import fr.uni.institute.library.business.inventory.AudioRecord;

public interface AudioRecordDao {

	public Collection researchAllAudioRecords() throws DaoException ;

	public AudioRecord researchAudioRecordById(int id) throws DaoException ;

	public void deleteAudioRecord(AudioRecord audioRecord) throws DaoException ;

	public void updateAudioRecord(AudioRecord audioRecord) throws DaoException ;

	public void createAudioRecord(AudioRecord audioRecord) throws DaoException ;

}
