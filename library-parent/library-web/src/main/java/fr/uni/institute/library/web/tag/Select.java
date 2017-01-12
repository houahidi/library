package fr.uni.institute.library.web.tag;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class Select extends TagSupport {
	private String name;

	private String itemDisplay;

	private String itemValue;

	private ArrayList listValues;

	private ArrayList listSelectedValues;

	private String getItemValue(Object aValue) {
		String itemValueStr = null;
		String itemValueMethodName = this.getItemValue();
		if (itemValueMethodName != null && !"".equals(itemValueMethodName)) {
			try {
				itemValueMethodName = "get"
						+ itemValueMethodName.substring(0, 1).toUpperCase()
						+ itemValueMethodName.substring(1);
				Method itemValueMethod = aValue.getClass().getMethod(
						itemValueMethodName, null);
				itemValueStr = itemValueMethod.invoke(aValue, null).toString();
			} catch (Exception e) {
			}
		}
		if (itemValueStr == null) {
			itemValueStr = aValue.toString();
		}
		return itemValueStr;
	}

	private String getItemDisplay(Object aValue) {
		String itemDisplayStr = null;
		String itemDisplayMethodName = this.getItemDisplay();
		if (itemDisplayMethodName != null && !"".equals(itemDisplayMethodName)) {
			try {
				itemDisplayMethodName = "get"
						+ itemDisplayMethodName.substring(0, 1).toUpperCase()
						+ itemDisplayMethodName.substring(1);
				Method itemDisplayMethod = aValue.getClass().getMethod(
						itemDisplayMethodName, null);
				itemDisplayStr = itemDisplayMethod.invoke(aValue, null)
						.toString();
			} catch (Exception e) {
			}
		}
		if (itemDisplayStr == null) {
			itemDisplayStr = aValue.toString();
		}
		return itemDisplayStr;
	}

	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		if (listValues != null && !listValues.isEmpty()) {
			StringBuffer buf = new StringBuffer();
			Iterator it = listValues.iterator();
			while (it.hasNext()) {
				Object aValue = (Object) it.next();
				buf.append("<input type='checkbox' name='");
				buf.append(this.name);
				buf.append("' value='");
				buf.append(getItemValue(aValue));
				buf.append("' ");
				if (listSelectedValues != null
						&& listSelectedValues.contains(aValue)) {
					buf.append(" checked='checked' ");
				}
				buf.append(">");
				buf.append(getItemDisplay(aValue));
				buf.append("<br/>\n");
			}
			try {
				out.println(buf.toString());
			} catch (IOException e) {
				throw new JspException(e);
			}
		}
		return Tag.SKIP_BODY;
	}

	public String getItemDisplay() {
		return itemDisplay;
	}

	public void setItemDisplay(String itemDisplay) {
		this.itemDisplay = itemDisplay;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getListSelectedValues() {
		return listSelectedValues;
	}

	public void setListSelectedValues(Object object) {
		if (object != null) {
			try {
				this.listSelectedValues = new ArrayList((Collection) object);
			} catch (Exception e) {
				System.out
						.println("setListSelectedValues(Object object) - Error : "
								+ e.getMessage());
				e.printStackTrace();
			}

		}
	}

	public Object getListValues() {
		return listValues;
	}

	public void setListValues(Object object) {
		try {
			this.listValues = new ArrayList((Collection) object);
		} catch (Exception e) {
			System.out.println("setListValues(Object object) - Error : "
					+ e.getMessage());
			e.printStackTrace();
		}
	}
}
