<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="pagination" prefix="pagination" %>

<h3>컨텐츠 목록</h3>

<table class="table table-bordered table-hover">
	<colgroup>
		<col style="width:80%;">
	</colgroup>
	<caption class="hide">컨텐츠 리스트</caption>
	<thead>
		<tr>
			<th>제목</th>
			<th>등록일</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${cmsPostList }" var="cmsPostDomain">
			<tr>
				<td><a href="${contextPath }/cms/admin/content_detail.cms?ID=${cmsPostDomain.ID }">${cmsPostDomain.title }</a></td>
				<td><fmt:formatDate value="${cmsPostDomain.reg_date }" pattern="yyyy-MM-dd"/></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<pagination:paging>
	<pagination:mode>list</pagination:mode>
	<pagination:function>goListPage</pagination:function>
	<pagination:currentPage>${cmsPost.pageNo }</pagination:currentPage>
	<pagination:totalRows>${cmsPost.totalSize }</pagination:totalRows>
	<pagination:pageSize>${cmsPost.pageSize }</pagination:pageSize>
	<pagination:pageLimit>${cmsPost.pageLimit }</pagination:pageLimit>
</pagination:paging>

<div class="btns text-right">
	<a href="${contextPath }/cms/admin/content_edit.cms" class="btn btn-primary">컨텐츠 등록</a>
</div>

<form:form commandName="cmsPost">
	<form:hidden path="pageNo"/>
</form:form>

<script type="text/javascript">
	function goListPage(pageNo, i) {
		$('#cmsPost #pageNo').val(pageNo);
		$('#cmsPost').attr('method', 'get');
		$('#cmsPost').submit();
	}
</script>
