package fr.uni.institute.library.business.inventory;

public class InventoryException extends Exception {

	public InventoryException() {
	}

	public InventoryException(String message) {
		super(message);
	}

	public InventoryException(Throwable cause) {
		super(cause);
	}

	public InventoryException(String message, Throwable cause) {
		super(message, cause);
	}

}
