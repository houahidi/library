package fr.uni.institute.library.business.inventory;

public interface InventoryVisitor {

	public void visitBook(Book aBook) throws InventoryException ;
	public void visitCategory(Category aCategory) throws InventoryException ;
	public void visitAudioRecord(AudioRecord anAudioRecord) throws InventoryException ;
	public void visitTitle(Title aTitle) throws InventoryException ;
	public void visitVideoRecord(VideoRecord aVideoRecord) throws InventoryException ;

}
