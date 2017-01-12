<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="i18n" uri="http://jakarta.apache.org/taglibs/i18n-1.0" %>
<i18n:bundle baseName="resources" id="bundle" localeRef="userLocale"/>
<jsp:useBean id="researchDTO" scope="session" class="fr.uni.institute.library.web.ResearchTitlesDTO" />
<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><i18n:message key="page.title.titles" /> - <c:out value="${userLocale}" /></title>
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
						<div class="menu_sub_link_sel"><i18n:message key="menu.titles" /></div>
					</div>
					<div class="menu_action">
						<div class="bg_path"> 
							<img src="images/bg_action_picto.jpg" alt="" /><a href="index.jsp" class="path"><i18n:message key="ariane.home" /></a> 
							<img src="images/bg_action_picto.jpg" alt="" /><a href="inventoryhome.jsp" class="path"><i18n:message key="ariane.inventory" /></a> 
							<img src="images/bg_action_picto.jpg" alt="" /><i18n:message key="ariane.display.titles" />
						</div>
	        			<div class="action"> 
							<img src="images/left_action.jpg" alt="" />
							<a href="#" onclick="document.forms[0].submit()"><img src="images/bt_action_valid.jpg" alt="<i18n:message key="alt.search" />" border="0"/></a>
							<a href="inventoryhome.jsp"><img src="images/bt_action_annul.jpg" alt="<i18n:message key="alt.cancel" />" border="0"/></a>
							<a href="titlecreate.do?type=book"><img src="images/bt_action_book.jpg" alt="<i18n:message key="alt.newbook" />" border="0"/></a>
							<a href="titlecreate.do?type=audio"><img src="images/bt_action_cd.jpg" alt="<i18n:message key="alt.newcd" />" border="0"/></a>
							<img src="images/right_action.jpg" alt="" /> 
						</div>
					</div>
					<div class="bg_content">
						<h1><i18n:message key="title.search.subtitle" /></h1>
						<div class="bg_table">
							<form action="titles.do" method="post">
								<table  cellpadding="0" cellspacing="0" class="table-form">
									<tr>
										<td width="59" ><strong><i18n:message key="title.name" /> :</strong></td>
										<td width="409"><input type="text" style="width:200px" name="pName" value="${researchDTO.researchParameterName}"/></td>
										<td width="88"><strong><i18n:message key="title.date" /> :</strong> </td>
										<td width="300"><input type="text" style="<c:choose><c:when test="${invalidDateFormat!=null}">field_error</c:when><c:otherwise>field</c:otherwise></c:choose>" name="pDate" value="${researchDTO.researchParameterDate}"/><c:out value="${invalidDateFormat}"></c:out></td>
									</tr>
						            <tr>
						              <td width="59" ><strong><i18n:message key="title.author" /> :</strong></td>
						              <td colspan="3"><input type="text" style="width:200px" name="pAuthor" value="${researchDTO.researchParameterAuthor}" /></td>
						            </tr>
		          				</table>
		          			</form>
	        			</div>
			        	<br />
						<c:if test="${!empty researchDTO.titles}"> 
		        			<h1><i18n:message key="title.search.subtitle2" /></h1>
							<div class="bg_table">
								<table  class="table-table" cellpadding="0" cellspacing="0">
									<thead>
										<tr>
											<th width="238"><i18n:message key="title.name" /></th>
											<th width="232"><i18n:message key="title.date" /></th>
											<th width="205"><i18n:message key="title.author" /></th>
											<th width="62"><i18n:message key="action.details" /></th>
											<th width="53"><i18n:message key="action.modify" /></th>
											<th width="66"><i18n:message key="action.delete" /></th>
										</tr>
									</thead>
									<c:forEach var="title" items="${researchDTO.titles}">
										<tr>
											<td><var>${title.name}</var></td>
											<td><var><fmt:formatDate value="${title.date}" type="date" dateStyle="short" pattern="dd/MM/yyyy" /></var></td>
											<td><var>${title.author}</var></td>
											<td>
												<c:url var="detailURL" value="titledetail.do">
													<c:param name="titleId" value="${title.id}"/>
												</c:url>
												<a href="<c:out value="${detailURL}"/>"><img src="images/bt_detail.jpg" alt="<i18n:message key="alt.details" />" border="0"/></a>
											</td>
											<td>
												<c:url var="modifyURL" value="titlemodify.do">
													<c:param name="titleId" value="${title.id}"/>
												</c:url>
												<a href="<c:out value="${modifyURL}"/>"><img src="images/bt_modif.jpg" alt="<i18n:message key="alt.modify" />" border="0"/></a>
											</td>
											<td>
												<c:url var="deleteURL" value="titledelete.do">
													<c:param name="titleId" value="${title.id}"/>
												</c:url>
												<a href="<c:out value="${deleteURL}"/>"><img src="images/bt_suppr.jpg" alt="<i18n:message key="alt.delete" />" border="0"/></a>
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</c:if>
					</div>
					<div class="bg_bottom"> </div>
				</td>
			 </tr>
		</table>
		<c:if test="${message!=null}"><script language="Javascript">alert('<c:out value="${message}" />')</script></c:if>
	</body>
</html>
