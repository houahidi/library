package fr.uni.institute.library.service.impl;

import fr.uni.institute.library.business.inventory.AudioRecord;
import fr.uni.institute.library.business.inventory.Book;
import fr.uni.institute.library.business.inventory.DefaultInventoryVisitor;
import fr.uni.institute.library.business.inventory.InventoryException;
import fr.uni.institute.library.service.ServiceException;

public class TitleRemove extends DefaultInventoryVisitor {
	private InventoryManagementServiceImpl service;

	public TitleRemove(InventoryManagementServiceImpl service) {
		super();
		this.service = service;
	}

	public void visitAudioRecord(AudioRecord audioRecord)
			throws InventoryException {
		try {
			service.removeAudioRecord(audioRecord);
		} catch (ServiceException e) {
			throw new InventoryException(e);
		}
	}

	public void visitBook(Book book) throws InventoryException {
		try {
			service.removeBook(book) ;
		} catch (ServiceException e) {
			throw new InventoryException(e) ;
		}
	}

}
