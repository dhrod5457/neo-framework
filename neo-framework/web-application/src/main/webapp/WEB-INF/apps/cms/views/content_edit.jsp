<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3>콘텐츠 등록</h3>

<form id="my_form" action="/upload.file" target="form_target" method="post" enctype="multipart/form-data" style="width:0px;height:0;overflow:hidden">
    <input name="file" type="file" id="file"  />
</form>

<form:form commandName="cmsPost" action="${contextPath }/cms/admin/contentProcess.cms">
	<table class="table table-bordered">
		<caption class="hide">콘텐츠 등록 테이블</caption>
		<tbody>
			<tr>
				<th><form:label path="title">제목</form:label></th>
				<td>
					<form:input path="title" cssStyle="width:95%;"/>
					<form:errors path="title" />
				</td>
			</tr>
			<tr>
				<th><form:label path="content">내용</form:label></th>
				<td>
					<form:textarea path="content" />
					<form:errors path="content" />
				</td>
			</tr>
		</tbody>
	</table>
	
	<div class="btns text-right">
		<a href="javascript:document.getElementById('cmsPost').submit();" class="btn btn-primary">등록</a>
		<a href="javascript:history.back();" class="btn btn-primary">이전</a>
	</div>
	
	<c:if test="${empty param.ID }">
		<input type="hidden" name="cmd" value="add" />
	</c:if>
	<c:if test="${!empty param.ID }">
		<input type="hidden" name="cmd" value="update" />
		<input type="hidden" name="ID" value="${param.ID }" />
	</c:if>
</form:form>

<script>
	$(window).load(function(){
		tinymce.init({
		    selector: "textarea#content",
		    theme: "modern",
		    height: 300,
		    language : "ko_KR",
		    plugins: [
		         "advlist autolink link image lists charmap print preview hr anchor pagebreak spellchecker",
		         "searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking",
		         "save table contextmenu directionality emoticons template paste textcolor"
		   ],
		   forced_root_block : false, 
		   toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image | print preview media fullpage | forecolor backcolor emoticons | code", 
		   file_browser_callback: function(field_name, url, type, win) {
			   var windowManager = tinymce.activeEditor.windowManager;
			   
			   if(windowManager.windows[0]._title == 'Insert link'){
				   windowManager.open({
					    title : 'open',
						url:'/cms/admin/popup_file_mce.cms',
						width:300,
						height:300
				   });
			   }
			   
	            if(type == 'image'){
	            	$('#my_form input').trigger('click');
	            } 
	            	
			},
		    link_list: [
		                {title: 'My page 1', value: 'http://www.tinymce.com'},
		                {title: 'My page 2', value: 'http://www.moxiecode.com'}
		            ]
		 });
		
		$('#file').change(function(){
			$('#my_form').ajaxSubmit({
				success :function(response){
					var r = response.result[0];
					var ed = '<img src="'+ r.uploadUrl+'" />';
						
					tinymce.activeEditor.execCommand('mceInsertContent', false, ed);
					tinymce.activeEditor.windowManager.close();
				}
			});
		});
	});
</script>