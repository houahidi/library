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
		<title><c:choose><c:when test="${category!=null}"><i18n:message key="page.title.category.modify" /></c:when><c:otherwise><i18n:message key="page.title.category.create" /></c:otherwise></c:choose> - <c:out value="${userLocale}" /></title>
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
						<div class="menu_sub_link">
							<a href="categories.do" class="menu_sub_link"><i18n:message key="menu.categories" /></a>
						</div>
						<div class="menu_sub_link">
							<a href="titles.do" class="menu_sub_link"><i18n:message key="menu.titles" /></a>
						</div>
					</div>
					<div class="menu_action">
						<div class="bg_path">
							<img src="images/bg_action_picto.jpg" alt="" /><a href="index.jsp" class="path"><i18n:message key="ariane.home" /></a> 
							<img src="images/bg_action_picto.jpg" alt="" /><a href="inventoryhome.jsp" class="path"><i18n:message key="ariane.inventory" /></a> 
							<img src="images/bg_action_picto.jpg" alt="" /><a href="categories.do" class="path"><i18n:message key="ariane.display.categories" /></a> 
							<img src="images/bg_action_picto.jpg" alt="" /><c:choose><c:when test="${category!=null}"><i18n:message key="ariane.category.modify" /></c:when><c:otherwise><i18n:message key="ariane.category.create" /></c:otherwise></c:choose>
						</div>
						<div class="action">
							<img src="images/left_action.jpg" alt="" />
							<a href="#" onclick="document.forms[0].submit()"><img src="images/bt_action_valid.jpg" alt="<i18n:message key="alt.save" />" border="0" /></a>
							<a href="categories.do"><img src="images/bt_action_annul.jpg" alt="<i18n:message key="alt.cancel" />" border="0" /></a>
							<img src="images/right_action.jpg" alt="" />
						</div>
					</div>
					<div class="bg_content">
						<h1><c:choose><c:when test="${category!=null}"><i18n:message key="category.modify.subtitle" /></c:when><c:otherwise><i18n:message key="category.create.subtitle" /></c:otherwise></c:choose></h1>
						<div class="bg_table">
<jsp:include page="form.addCategory.jsp" />
						</div>
					</div>
					<div class="bg_bottom"></div>
				</td>
			</tr>
		</table>
	</body>
</html>
