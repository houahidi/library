<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="i18n" uri="http://jakarta.apache.org/taglibs/i18n-1.0"%>
<i18n:bundle baseName="resources" id="bundle" localeRef="userLocale" />
<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><i18n:message key="page.title.categories" /> - <c:out value="${userLocale}" /></title>
		<link href="css/uni_library_style.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="contain_all">
			<tr>
				<td>
	 				<div class="top_logo_home"><a href="#"><img src="images/logo_home.jpg" alt="UNI Library" height="106px" border="0"/></a></div>
					<div class="menu_main">
							<div class="menu_main_space">&nbsp;</div>
							<div class="menu_main_link_sel"><i18n:message key="menu.inventory" /></div>
							<div class="menu_main_link"><a href="#" class="menu_main_link"><i18n:message key="menu.loaning" /></a></div>
							<div class="menu_main_link"><a href="#" class="menu_main_link"><i18n:message key="menu.requests" /></a></div>
							<div class="menu_main_link"><a href="#" class="menu_main_link"><i18n:message key="menu.usermanagement" /></a></div>
						</div>
						<div class="menu_sub">
							<div class="menu_sub_link_sel">
								<i18n:message key="menu.categories" />
							</div>
							<div class="menu_sub_link">
								<a href="titles.do" class="menu_sub_link"><i18n:message key="menu.titles" /></a>
							</div>
						</div>
						<div class="menu_action">
						  <div class="bg_path"> 
								<img src="images/bg_action_picto.jpg" alt="" /><a href="index.jsp" class="path"><i18n:message key="ariane.home" /></a> 
								<img src="images/bg_action_picto.jpg" alt="" /><a href="inventoryhome.jsp" class="path"><i18n:message key="ariane.inventory" /></a> 
								<img src="images/bg_action_picto.jpg" alt="" /><i18n:message key="ariane.display.categories" />
						  </div>
						  <div class="action">
						  	<img src="images/left_action.jpg" alt="" />
						  	<a href="#" onclick="document.forms[0].submit()"><img src="images/bt_action_valid.jpg" alt="<i18n:message key="alt.create" />" border="0" /></a>
						  	<a href="inventoryhome.jsp"><img src="images/bt_action_annul.jpg" alt="<i18n:message key="alt.cancel" />" border="0" /></a>
						  	<img src="images/right_action.jpg" alt="" /> 
						  </div>
						</div>
						<div class="bg_content">
							<!--
								<c:if test="${message!=null}"><div align="center"><b><c:out value="${message}" /></b></div></c:if>
							-->
							<h1><i18n:message key="categories.list.addcategory" /></h1>
							<div class="bg_table">
								<jsp:include page="form.addCategory.jsp" />
							</div>
							<br />
							<h1><i18n:message key="categories.list.displaycategories" /></h1>
							<div class="bg_table">
								<table  class="table-table" cellpadding="0" cellspacing="0">
									<thead>
										<tr>
										  <th width="576"><i18n:message key="category.name" /></th>
										  <th width="140"><i18n:message key="action.modify" /></th>
										  <th width="140"><i18n:message key="action.delete" /></th>
										</tr>
									</thead>
									<c:forEach var="category" items="${categories}">
										<tr>
											<td>${category.name}</td>
											<td>
												<c:url var="modifyURL" value="categorymodify.do">
													<c:param name="categoryId" value="${category.id}"/>
												</c:url>
												<a href="<c:out value="${modifyURL}"/>"><img src="images/bt_modif.jpg" alt="<i18n:message key="action.modify" />" border="0"/></a>
											</td>
											<td>
												<c:url var="deleteURL" value="categorydelete.do">
													<c:param name="categoryId" value="${category.id}"/>
												</c:url>
												<a href="<c:out value="${deleteURL}"/>"><img src="images/bt_suppr.jpg" alt="<i18n:message key="action.delete" />" border="0"/></a>
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
						<div class="bg_bottom"></div>
				</td>
			</tr>
		</table>
		<c:if test="${message!=null}"><script language="Javascript">alert('<c:out value="${message}" />')</script></c:if>
	</body>
</html>
