<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<h3>콘텐츠 상세보기</h3>

<table class="table table-bordered">
	<caption class="hide">콘텐츠 상세보기 테이블</caption>
	<tbody>
		<tr>
			<th>제목</th>
			<td>${cmsPostDetail.title }</td>
		</tr>
		<tr>
			<th colspan="2">내용</th>
		</tr>
		<tr>
			<td colspan="2">${cmsPostDetail.content }</td>
		</tr>
	</tbody>
</table>

<div class="btns text-right">
	<a href="/cms/admin/content_edit.cms?ID=${cmsPostDetail.ID }" class="btn btn-primary">수정</a>
	<a href="/cms/admin/contentProcess.cms?ID=${cmsPostDetail.ID }&cmd=delete" class="btn btn-primary">삭제</a>
	<a href="javascript:history.back();" class="btn btn-primary">이전</a>
</div>