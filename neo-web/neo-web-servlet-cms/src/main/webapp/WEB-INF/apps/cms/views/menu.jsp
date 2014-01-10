<%@page import="kr.co.neo.nativeness.core.tree.TreeNode"%>
<%@page import="kr.co.neo.web.core.node.tree.iterator.JspTreeIterator"%>
<%@page import="kr.co.neo.nativeness.core.tree.TreeNodeManager"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="kr.co.neo.web.servlet.cms.nav.domain.NavMenu"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>메뉴 관리</h2>
<style>
.posts-box ul{
	max-height: 200px; 
	overflow-y: scroll; 
	overflow-x:hidden; 
	margin-left:0;
}
.posts-box li{
	margin-bottom:1px;
}

.posts-box li a{
	font-size:10px;
}
</style>
<form:form commandName="navMenu">
	<div class="well well-sm">
		<c:if test="${!empty navMenuGrpList }">
			<form:select path="grp" cssStyle="margin-bottom:0;">
				<c:forEach items="${navMenuGrpList }" var="navMenuGrp">
					<form:option value="${navMenuGrp.ID }">${navMenuGrp.name }</form:option>
				</c:forEach>
			</form:select>
		</c:if>

		<a href="?cmd=createMenu" class="btn btn-small btn-primary">메뉴 만들기</a>
		<c:if test="${navMenu.cmd != 'createMenu' }">
			<a href="javascript:deleteNavMenu(${navMenu.grp });" class="btn btn-small btn-primary">메뉴 삭제</a>	
		</c:if>
		
		<c:if test="${navMenu.cmd == 'createMenu' }">
			<a href="${contextPath }/cms/admin/menu.cms" class="btn btn-small">취소</a>
		</c:if>
		
	</div>
	<c:if test="${navMenu.cmd != 'createMenu' }">
		<div class="span4">
			<div class="accordion" id="accordion2">
				<div class="accordion-group">
					<div class="accordion-heading">
						<a class="accordion-toggle" data-toggle="collapse"
							data-parent="#accordion2" href="#collapseOne"> Pages </a>
					</div>
					<div id="collapseOne" class="accordion-body collapse in">
						<div class="accordion-inner">
							<div class="posts-box" >
								<ul>
									<c:forEach items="${cmsPostList }" var="cmsPostDomain">
										<li><a href="#" data-ID="${cmsPostDomain.ID }" class="btn-create-link-post">${cmsPostDomain.title }</a></li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="accordion-group">
					<div class="accordion-heading">
						<a class="accordion-toggle" data-toggle="collapse"
							data-parent="#accordion2" href="#collapseTwo"> Links </a>
					</div>
					<div id="collapseTwo" class="accordion-body collapse">
						<div class="accordion-inner">
							<div class="row-fluid">
								<form:label path="name" cssClass="span4">name</form:label>
								<form:input path="name" cssClass="span8"/>
								<form:errors path="name" />
							</div>
							<div class="row-fluid">
								<form:label path="url" cssClass="span4">url</form:label>
								<form:input path="url" cssClass="span8"/>
							</div>
							<div class="text-right">
								<a href="#" class="btn btn-small btn-create-link">생성</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if>
	
	<form:hidden path="grp"/>
	<form:hidden path="cmd" />
</form:form>

<div class="span8">
	<div class="well" style="overflow: hidden;">
		<c:if test="${navMenu.cmd == 'createMenu' }">
			<form:form commandName="navMenuGrp" cssClass="navbar-form pull-left">
				<div class="row-fluid">
					<form:input path="name" />
					<form:errors path="name" />
					<input type="submit" class="btn btn-create-menu" value="만들기" />
				</div>
				
				<form:hidden path="cmd" />
			</form:form>
		</c:if>
		<% TreeNodeManager menuTree =  (TreeNodeManager) request.getAttribute("menuTree"); %>
		<% if(menuTree != null && !menuTree.isEmpty()) { %>
			<div class="dd cms-menu-admin" id="nestable">
				<ol class="dd-list">
					<%
						if(menuTree != null){
							menuTree.iterating(new JspTreeIterator<NavMenu>(out) {
								@Override
								public void startChild(TreeNode<NavMenu> node) throws Exception {
									write("<ol class=\"dd-list\">");
								}
								
								@Override
								public void endChild(TreeNode<NavMenu> node) throws Exception {
									write("</ol>");
								}
								
								@Override
								public void doStart(TreeNode<NavMenu> node) throws Exception {
									NavMenu menu = node.getData();
									
									write("<li class=\"dd-item dd3-item\" data-id=\"" + menu.getID() + "\">");
									write("<div class=\"dd-handle dd3-handle\"></div>");
									write("<div class=\"dd3-content\">");
										write(menu.getName());
										
										write("<div class=\"btns\">");
											write("<a href=\"#\" class=\"btn-modify-open\">수정</a>");
											
											if(!node.hasChildren()){
												write("<a href=\"#\" class=\"btn-delete\">삭제</a>");
											}
											
										write("</div>");
									write("</div>");
									
									write("<div class=\"dd-item-setting\">");
										write("<table class=\"table table-bordered\" data-id=\"" + menu.getID() + "\">");
											write("<tr>");
												write("<th>name</th>");
												write("<td><input type=\"text\" name=\"name\" value=\""+menu.getName()+"\"></td>");
											write("</tr>");
											write("<tr>");
												write("<th>url</th>");
												write("<td><input type=\"text\" name=\"url\" value=\""+menu.getUrl()+"\"></td>");
											write("</tr>");
											write("<tr>");
												write("<td colspan=\"2\" style=\"text-align:right;\">");
													write("<button type=\"button\" class=\"btn btn-small btn-modify\">수정</button>");
													write("<button type=\"button\" class=\"btn btn-small btn-close\">닫기</button>");
												write("</td>");
											write("</tr>");
										write("</table>");
									write("</div>");
								}
								
								@Override
								public void doEnd(TreeNode<NavMenu> node) throws Exception {
									write("</li>");
								}
							});
						}
					%>
				</ol>
			</div>
		<% } else { %>
			<c:if test="${navMenu.cmd != 'createMenu' }">
				메뉴를 생성해 주세요.
			</c:if>
		<% } %>
	</div>
</div>

<script>	
	function deleteNavMenu(grp){
		if(confirm('정말로 삭제하시겠습니까?')){
			$.post('${contextPath}/cms/admin/menu.cms',{
				'cmd' : 'deleteMenu',
				'ID' : grp
			},function(){
				location.href = '${contextPath}/cms/admin/menu.cms';
			});
		}
	}
	
	$(document).ready(function(){
		$('#navMenu .btn-create-link').click(function(e){
			e.preventDefault();
			
			$('#navMenu').attr('action','${contextPath}/cms/admin/menu.cms');
			$('#navMenu #cmd').val('createLink');
			
			$('#navMenu').submit();
		});
		
		$('#navMenu .btn-create-link-post').click(function(){
			$.post('${contextPath}/cms/admin/menu.cms',{
				'cmd' : 'createLink',
				'name' : $(this).text(),
				'url' :  '/page/'+$(this).attr('data-ID'),
				'grp' : $('#grp').val()
			},function(){
				location.reload();
			});
		});
		
		$('#grp').change(function(e){
			e.preventDefault();
			
			$('#navMenu').attr('action','${contextPath}/cms/admin/menu.cms');
			$('#navMenu').submit();
		});
		
		$('#navMenuGrp .btn-create-menu').click(function(e){
			e.preventDefault();
			
			$('#navMenuGrp').attr('action','${contextPath}/cms/admin/menuGrpProcess.cms');
			$('#navMenuGrp').submit();
		});
		
		$('#nestable').nestable({
	        group: 1,
	        maxDepth:5
	    }).on('change', function(e){
	    	try{
	    		var list   = e.length ? e : $(e.target);
				var s = JSON.stringify(list.nestable('serialize'));
				
				$.post('${contextPath}/cms/admin/menuAjax.cms',{
					"menuJson" : s ,
					"cmd" : 'addMenu',
					"grp" : $('#grp').val()
				},function(){
					location.reload();
				});
	    	}catch(e){}
	    });
	});
</script>