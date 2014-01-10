<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<title>CMS</title>
	<link rel="stylesheet" href="${contextPath }/resources/apps/cms/assets/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${contextPath }/resources/apps/cms/css/style.css" />
	<link rel="stylesheet" href="${contextPath }/resources/apps/cms/assets/jquery-nestable/jquery.nestable.css" />
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="${contextPath }/resources/apps/cms/assets/json2.js"></script>
	<script type="text/javascript" src="${contextPath }/resources/apps/cms/assets/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${contextPath }/resources/apps/cms/assets/jquery-nestable/jquery.nestable.js"></script>
	<script type="text/javascript" src="${contextPath }/resources/apps/cms/assets/tinymce/tinymce.min.js"></script>
	<script type="text/javascript" src="${contextPath }/resources/apps/cms/assets/tinymce/jquery.tinymce.min.js"></script>
	<script type="text/javascript" src="${contextPath }/resources/apps/cms/assets/jquery.form.min.js"></script>
	
	<script type="text/javascript" src="${contextPath }/resources/apps/cms/assets/jquery-uploader/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="${contextPath }/resources/apps/cms/assets/jquery-uploader/jquery.iframe-transport.js"></script>
	<script type="text/javascript" src="${contextPath }/resources/apps/cms/assets/jquery-uploader/jquery.fileupload.js"></script>
	
	<script type="text/javascript" src="${contextPath }/resources/apps/cms/js/cms_script.js"></script>
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<div id="side-bar">
				<ul class="nav nav-list affix sidenav">
					<li class="sidebar-brand"><a href="#">관리자 모드</a></li>
					<li><a href="${contextPath }/cms/admin/menu.cms">메뉴 관리</a></li>
					<li><a href="${contextPath }/cms/admin/content.cms">컨텐츠 관리</a></li>
					<li><a href="${contextPath }/cms/admin/file.cms">파일 관리</a></li>
					<li><a href="${contextPath }/cms/admin/setting.cms">홈페이지 설정</a></li>
				</ul>
			</div>
		</div>
		<div id="contents">
			
