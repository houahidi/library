<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="i18n" uri="http://jakarta.apache.org/taglibs/i18n-1.0" %>
<i18n:bundle baseName="resources" id="bundle" localeRef="userLocale"/>
<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><i18n:message key="page.title.audio.details" /> - <c:out value="${userLocale}" /></title>
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
						<div class="menu_sub_link_sel">
							<a href="titles.do" class="menu_sub_link"><i18n:message key="menu.titles" /></a>
						</div>
					</div>
					
					<div class="menu_action">
						<div class="bg_path"> 
							<img src="images/bg_action_picto.jpg" alt="" /><a href="index.jsp" class="path"><i18n:message key="ariane.home" /></a> 
							<img src="images/bg_action_picto.jpg" alt="" /><a href="inventoryhome.jsp" class="path"><i18n:message key="ariane.inventory" /></a> 
							<img src="images/bg_action_picto.jpg" alt="" /><a href="titles.do" class="path"><i18n:message key="ariane.display.titles" /></a>
							<img src="images/bg_action_picto.jpg" alt="" /><i18n:message key="ariane.audio.details" />
						</div>
	        			<div class="action"> 
							<img src="images/left_action.jpg" alt="" />
							<a href="titles.do"><img src="images/bt_action_valid.jpg" alt="<i18n:message key="alt.search" />" border="0"/></a>
							<a href="titles.do"><img src="images/bt_action_annul.jpg" alt="<i18n:message key="alt.cancel" />" border="0"/></a>
							<a href="titlecreate.do?type=book"><img src="images/bt_action_book.jpg" alt="<i18n:message key="alt.newbook" />" border="0"/></a>
							<a href="titlecreate.do?type=audio"><img src="images/bt_action_cd.jpg" alt="<i18n:message key="alt.newcd" />" border="0"/></a>
							<img src="images/right_action.jpg" alt="" /> 
						</div>
					</div>
					<div class="bg_content">
						<h1><i18n:message key="audio.details.subtitle" /></h1>
						<div class="bg_table">
							<table  cellpadding="0" cellspacing="0" class="table-form">
								<tr>
									<td width="59"><strong><i18n:message key="title.name" /> :</strong></td>
									<td width="409" class="table-form_content"><var>${titleDTO.name}</var></td>
									<td width="88"><strong><i18n:message key="title.date" /> : </strong></td>
									<td width="300"><var><fmt:formatDate value="${titleDTO.date}" type="date" dateStyle="short" pattern="dd/MM/yyyy" /></var></td>
								</tr>
								<tr>
									<td width="59" ><strong><i18n:message key="title.author" /> :</strong></td>
									<td><var>${titleDTO.author}</var></td>
									<td><strong><i18n:message key="audio.label" /> : </strong></td>
									<td><var>${titleDTO.label} </var></td>
								</tr>
								<tr>
									<td width="59" ><strong><i18n:message key="audio.duration" /> :</strong></td>
									<td><var>${titleDTO.duration}</var></td>
									<td><strong><i18n:message key="audio.tracks" /> : </strong></td>
									<td><var>${titleDTO.tracks}</var></td>
								</tr>
							</table>
						</div>
						<br />
						<h1><i18n:message key="title.categories" /></h1>
						<div class="bg_table">
							<table  class="table-table" cellpadding="0" cellspacing="0">
								<thead>
									<tr>
										<th width="300"><i18n:message key="category.name" /></th>
										<th width="300"><i18n:message key="category.parent" /></th>
									</tr>
								</thead>
								<c:forEach var="category" items="${titleDTO.categories}">
									<tr>
										<td>${category.name}</td>
										<td><c:if test="${ category.parent!=null }">${category.parent.name}</c:if></td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
					<div class="bg_bottom"> </div>
				</td>
			</tr>
		</table>
	</body>
</html>
