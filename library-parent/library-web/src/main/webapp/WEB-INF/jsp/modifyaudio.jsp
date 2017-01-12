<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="i18n" uri="http://jakarta.apache.org/taglibs/i18n-1.0" %>
<%@ taglib prefix="ml" uri="/WEB-INF/taglibs-magic-library.tld"%>
<i18n:bundle baseName="resources" id="bundle" localeRef="userLocale"/>
<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><c:choose><c:when test="${title!=null}"><i18n:message key="page.title.book.modify" /></c:when><c:otherwise><i18n:message key="page.title.book.create" /></c:otherwise></c:choose> - <c:out value="${userLocale}" /></title>
		<link href="css/uni_library_style.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="contain_all">
			<tr>
				<td>
	 				<div class="top_logo_home"><a href="#"><img src="images/logo_home.jpg" alt="UNI Library" height="106px" border="0"/></a></div>
				<div class="menu_main">
				<div class="menu_main_space">&nbsp;</div>
				<div class="menu_main_link_sel"><i18n:message
					key="menu.inventory" /></div>
				<div class="menu_main_link"><a href="#" class="menu_main_link"><i18n:message
					key="menu.loaning" /></a></div>
				<div class="menu_main_link"><a href="#" class="menu_main_link"><i18n:message
					key="menu.requests" /></a></div>
				<div class="menu_main_link"><a href="#" class="menu_main_link"><i18n:message
					key="menu.usermanagement" /></a></div>
				</div>
				<div class="menu_sub">
				<div class="menu_sub_link"><a href="categories.do"
					class="menu_sub_link"><i18n:message key="menu.categories" /></a></div>
				<div class="menu_sub_link_sel"><a href="titles.do"
					class="menu_sub_link"><i18n:message key="menu.titles" /></a></div>
				</div>
				<div class="menu_action">
				<div class="bg_path"><img src="images/bg_action_picto.jpg"
					alt="" /><a href="index.jsp" class="path"><i18n:message
					key="ariane.home" /></a> <img src="images/bg_action_picto.jpg" alt="" /><a
					href="inventoryhome.jsp" class="path"><i18n:message
					key="ariane.inventory" /></a> <img src="images/bg_action_picto.jpg"
					alt="" /><a href="titles.do" class="path"><i18n:message
					key="ariane.display.titles" /></a> <img src="images/bg_action_picto.jpg"
					alt="" /><c:choose>
					<c:when test="${title!=null}">
						<i18n:message key="ariane.audio.modify" />
					</c:when>
					<c:otherwise>
						<i18n:message key="ariane.audio.create" />
					</c:otherwise>
				</c:choose></div>
				<div class="action"><img src="images/left_action.jpg" alt="" />
				<a href="#" onclick="document.forms[0].submit()"><img
					src="images/bt_action_valid.jpg"
					alt="<i18n:message key="alt.save" />" border="0" /></a> <a
					href="titles.do"><img src="images/bt_action_annul.jpg"
					alt="<i18n:message key="alt.cancel" />" border="0" /></a> <img
					src="images/right_action.jpg" alt="" /></div>
				</div>
				<div class="bg_content">
				<h1><c:choose>
					<c:when test="${title!=null}">
						<i18n:message key="audio.modify.subtitle" />
					</c:when>
					<c:otherwise>
						<i18n:message key="audio.create.subtitle" />
					</c:otherwise>
				</c:choose></h1>
				<div class="bg_table">
				<form action="audiosave.do" method="post">
				<c:if test="${title!=null}">
					<input type="hidden" name="titleId"
						value="<c:out value="${title.id}"></c:out>" />
				</c:if>
				<table cellpadding="0" cellspacing="0" class="table-form">
					<tr>
						<td width="59"><strong><i18n:message key="title.name" />
						:</strong></td>
						<td width="409" class="table-form_content"><input
							name="titleName"
							value="<c:if test="${title!=null}"><c:out value="${title.name}"></c:out></c:if>"
							type="text" style="width: 200px" /></td>
						<td width="88"><strong><i18n:message key="title.date" />
						: </strong></td>
						<td width="300"><input type="text" name="titleDate"
							value="<c:if test="${title!=null}"><fmt:formatDate value="${title.date}" type="date" dateStyle="short" pattern="dd/MM/yyyy" /></c:if>"
							style="width: 200px" /></td>
					</tr>
					<tr>
						<td width="59"><strong><i18n:message
							key="title.author" /> :</strong></td>
						<td><input name="titleAuthor"
							value="<c:if test="${title!=null}"><c:out value="${title.author}"></c:out></c:if>"
							type="text" style="width: 200px" /></td>
						<td><strong><i18n:message key="audio.label" /> : </strong></td>
						<td><input type="text" name="titleLabel"
							value="<c:if test="${title!=null}"><c:out value="${title.label}"></c:out></c:if>"
							style="width: 200px" /></td>
					</tr>
					<tr>
						<td width="59"><strong><i18n:message
							key="audio.duration" /> :</strong></td>
						<td><input type="text" name="titleDuration"
							value="<c:if test="${title!=null}"><c:out value="${title.duration}"></c:out></c:if>"
							style="width: 200px" /></td>
						<td><strong><i18n:message key="audio.tracks" /> : </strong></td>
						<td><input type="text" name="titleTracks"
							value="<c:if test="${title!=null}"><c:out value="${title.tracks}"></c:out></c:if>"
							style="width: 200px" /></td>
					</tr>
					<tr>
						<td width="59" valign="top"><strong><i18n:message
							key="title.categories" /> :</strong></td>
						<td colspan="3"><ml:select name="titlecategoriesid"
							listValues="${categories}" listSelectedValues="${title.categories}"
							itemDisplay="name" itemValue="id" /></td>
					</tr>
				</table>
				</form>
				</div>
				</div>
				<div class="bg_bottom"></div>
			</td>
	</tr>
</table>
</body>
</html>