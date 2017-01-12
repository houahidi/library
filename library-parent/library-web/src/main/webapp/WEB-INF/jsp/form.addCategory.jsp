<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="i18n" uri="http://jakarta.apache.org/taglibs/i18n-1.0"%>
<i18n:bundle baseName="resources" id="bundle" localeRef="userLocale" />

							<form action="categorysave.do" method="post">
								<c:if test="${category!=null}">
									<input type="hidden" name="categoryId" value="<c:out value="${category.id}"></c:out>" />
								</c:if>
								<table cellpadding="0" cellspacing="0" class="table-form">
									<tr>
										<td width="69">
											<strong><i18n:message key="category.name" /> :</strong>
										</td>
										<td width="787" class="table-form_content">
											<input name="categoryName" type="text" style="width:200px" value="<c:out value="${category.name}" />"/>
										</td>
									</tr>
									<tr>
										<td width="69">
											<strong><i18n:message key="category.parent" /> :</strong>
										</td>
										<td width="787" class="table-form_content">
											<c:set var="parentId" value="${category.parent.id}" />
											<select name="categoryParentId">
												<option value=""></option>
												<c:forEach var="aCategory" items="${categories}">
													<option value="${aCategory.id}" <c:if test="${aCategory.id==parentId}">selected</c:if>>${aCategory.name}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
								</table>
							</form>
