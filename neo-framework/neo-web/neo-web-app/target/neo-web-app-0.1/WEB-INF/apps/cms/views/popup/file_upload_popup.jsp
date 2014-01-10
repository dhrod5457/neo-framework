<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.2.min.js"></script>

<ul id="file-list-box">
	<c:forEach items="${fileList }" var="fileDomain">
		<li>
			 <a href="#" data-url="${contextPath }/down.file?file=${fileDomain.fileName }" class="btn btn-small" >${fileDomain.fileName }</a>
		</li>
	</c:forEach>
</ul>

<script>
	$('#file-list-box li a').click(function(){
		$(this).attr('data-url');
		console.log(top.tinymce.activeEditor.windowManager.windows[0]);
		//top.tinymce.activeEditor.windowManager.close();
	});
</script>