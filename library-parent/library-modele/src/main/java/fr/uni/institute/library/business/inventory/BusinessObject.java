package fr.uni.institute.library.business.inventory;

public abstract class BusinessObject {

	/**
	 * unique id for this business object
	 * useful to retrieve or store in a database
	 */
	private int id;

	/**
	 * Constructs a new inventory business object with a default id set to zero
	 * 
	 */
	public BusinessObject(){
		this(0);
	}

	/**
	 * Constructs a new inventory business object with only his unique id
	 * 
	 * @param id
	 *            unique id of this object
	 */
	public BusinessObject(int id){
		super() ;
		this.setId(id) ;
	}
	
	/**
	 * implementation of the Visitor GoF Design Pattern
	 * @param v
	 * @throws InventoryException
	 */
	public abstract void accept(InventoryVisitor v) throws InventoryException ;
	
	/**
	 * 
	 * @return the unique id of this business object
	 */
	public int getId() {
		return id;
	}

	/**
	 * set the unique id of this business object
	 * @param id 
	 * 
	 * Identifier
	 */
	public void setId(int id) {
		this.id = id;
	}

}
