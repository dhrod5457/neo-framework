<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>파일 관리 <a href="file_upload.cms" class="btn btn-primary">파일 올리기</a></h3>

<style>
	#file-list-box li{
		margin-bottom:5px;
	}
	
	#file-list-box li > a{
		margin-left:4px;
	}
</style>

<ul id="file-list-box">
	<c:forEach items="${fileList }" var="fileDomain">
		<li>
			${fileDomain.fileName } <a href="${contextPath }/down.file?file=${fileDomain.fileName }" class="btn btn-small" >다운</a>
		</li>
	</c:forEach>
</ul>