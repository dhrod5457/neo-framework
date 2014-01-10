<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3>홈페이지 설정</h3>


<form:form commandName="cmsSetting">
	<form:input path="title" />
	<form:input path="sub_title" />

	<input type="submit" />
</form:form>
