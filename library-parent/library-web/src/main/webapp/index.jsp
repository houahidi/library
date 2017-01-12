<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="i18n" uri="http://jakarta.apache.org/taglibs/i18n-1.0" %>
<%@page import="java.util.Locale"%>
<c:if test="${param.lang!=null}">
	<c:set var="userLocale" scope="session" value="<%= Locale.FRANCE %>"/>
</c:if>
<i18n:bundle baseName="resources" id="bundle" localeRef="userLocale"/>
<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><i18n:message key="page.title.generic" /></title>
		<link href="css/uni_library_style1.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="contain_all">
			<tr>
				<td>
					<div class="top_logo_home"><a href="#"><img src="images/logo_home.jpg" alt="UNI Library" height="106px" border="0"/></a></div>
					<div class="menu_action_home">
						<div class="welcome"><i18n:message key="home.title"/></div>
					</div>
					<div class="bg_content_home">
						<div class="menu_home_link"><a href="inventoryhome.jsp" class="menu_home_link"><i18n:message key="menu.inventory" /></a></div>
						<div class="menu_home_link"><a href="#" class="menu_home_link"><i18n:message key="menu.loaning" /></a></div>
						<div class="menu_home_link"><a href="#" class="menu_home_link"><i18n:message key="menu.requests" /></a></div>
						<div class="menu_home_link"><a href="#" class="menu_home_link"><i18n:message key="menu.usermanagement" /></a></div>
						
					</div>
				
					
				</td>
			</tr>
			<tr>
				<td>
				<div class="bg_bottom"> </div>
				</td>
			</tr>
		</table>
	</body>
</html>
