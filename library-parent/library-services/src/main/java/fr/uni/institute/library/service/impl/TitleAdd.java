package fr.uni.institute.library.service.impl;

import fr.uni.institute.library.business.inventory.AudioRecord;
import fr.uni.institute.library.business.inventory.Book;
import fr.uni.institute.library.business.inventory.DefaultInventoryVisitor;
import fr.uni.institute.library.business.inventory.InventoryException;
import fr.uni.institute.library.service.ServiceException;


/**
 * 
 * @author houahidi
 *
 */
public class TitleAdd extends DefaultInventoryVisitor {
	private InventoryManagementServiceImpl service ;
	
	public TitleAdd() {
	}
	
	public TitleAdd(InventoryManagementServiceImpl service) {
		this.service = service;
	}

	public void visitAudioRecord(AudioRecord audioRecord) throws InventoryException {
		try {
			service.addAudioRecord(audioRecord) ;
		} catch (ServiceException e) {
			throw new InventoryException(e) ;
		}
	}

	public void visitBook(Book book) throws InventoryException {
		try {
			service.addBook(book) ;
		} catch (ServiceException e) {
			throw new InventoryException(e) ;
		}
	}
	
}
