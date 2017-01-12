package fr.uni.institute.library.business.inventory;

import java.util.ArrayList;

public class TitlesLister extends DefaultInventoryVisitor {

	protected ArrayList list ;
	
	public TitlesLister(){
		list = new ArrayList() ;
	}

	public ArrayList getList() {
		return list;
	}

}
