package fr.uni.institute.library.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import fr.uni.institute.library.business.inventory.AudioRecord;
import fr.uni.institute.library.business.inventory.BusinessObject;
import fr.uni.institute.library.dao.AudioRecordDao;
import fr.uni.institute.library.dao.DaoException;
import fr.uni.institute.library.dao.TitleDao;

public class AudioRecordDaoJdbc extends InventoryDaoJdbc implements
		AudioRecordDao {

	private TitleDao titleDao;

	public AudioRecordDaoJdbc(Connection connection, TitleDao titleDao) {
		super(connection);
		this.titleDao = titleDao;
	}

	public void createAudioRecord(AudioRecord audioRecord) throws DaoException {
		try {
			titleDao.createTitle(audioRecord) ;
			Connection conn = this.getConnection();
			PreparedStatement pst = conn
					.prepareStatement("insert into t_audio_cd (k_puid, label, duration, tracks) values (?, ?, ?, ?)");
			pst.setInt(1, audioRecord.getId());
			pst.setString(2, audioRecord.getLabel());
			pst.setInt(3, audioRecord.getDuration());
			pst.setString(4, audioRecord.getTracks());
			pst.execute();
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

	/**
	 * calling this method supposes that audioRecord's relations have been
	 * deleted
	 */
	public void deleteAudioRecord(AudioRecord audioRecord) throws DaoException {
		try {
			Connection conn = this.getConnection();
			PreparedStatement pst = conn
					.prepareStatement("delete from t_audio_cd where k_puid=?");
			pst.setInt(1, audioRecord.getId());
			pst.execute();
			titleDao.deleteTitle(audioRecord) ;
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}

	}

	public Collection<BusinessObject> researchAllAudioRecords() throws DaoException {
		try {
			Connection conn = this.getConnection();
			ArrayList<BusinessObject> audioRecords = new ArrayList<BusinessObject>();

			Statement stmt = conn.createStatement();
			ResultSet res = stmt
					.executeQuery("select distinct * from t_title as t1, t_audio_cd as t2 where (t1.k_puid=t2.k_puid)");
			while (res.next()) {
				int puid = res.getInt("k_puid");
				String name = res.getString("name");
				Date publishedAt = res.getDate("published_at");
				String author = res.getString("author");
				String label = res.getString("label");
				int duration = res.getInt("duration");
				String tracks = res.getString("tracks");
				AudioRecord anAudioRecord = new AudioRecord(puid, name,
						publishedAt, author, label, duration, tracks);
				audioRecords.add(anAudioRecord);
			}
			return audioRecords;
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

	public AudioRecord researchAudioRecordById(int id) throws DaoException {
		try {
			Connection conn = this.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt
					.executeQuery("select distinct * from t_title as t1, t_audio_cd as t2 where (t1.k_puid=t2.k_puid) and t1.k_puid="
							+ id);
			if (res.next()) {
				int puid = res.getInt("k_puid");
				String name = res.getString("name");
				Date publishedAt = res.getDate("published_at");
				String author = res.getString("author");
				String label = res.getString("label");
				int duration = res.getInt("duration");
				String tracks = res.getString("tracks");
				AudioRecord audioRecord = new AudioRecord(puid, name,
						publishedAt, author, label, duration, tracks);
				return audioRecord;
			}
			return null;
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}

	public void updateAudioRecord(AudioRecord audioRecord) throws DaoException {
		try {
			titleDao.updateTitle(audioRecord) ;
			Connection conn = this.getConnection();
			PreparedStatement pst = conn
					.prepareStatement("update t_audio_cd set label=?, duration=?, tracks=? where k_puid=?");
			pst.setString(1, audioRecord.getLabel());
			pst.setInt(2, audioRecord.getDuration());
			pst.setString(3, audioRecord.getTracks());
			pst.setInt(4, audioRecord.getId());
			pst.execute();
		} catch (SQLException ex) {
			throw new DaoException("SQL Error : " + ex.getMessage(), ex);
		}
	}
}
