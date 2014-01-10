<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h3>파일 관리 <a href="${contextPath }/cms/admin/file.cms" class="btn btn-primary">파일목록으로..</a></h3>

<style>
	#file-upload-results{
		margin-top:42px;
	}
	
	.upload-image img{
		width:100px;
		height:100px;
	}
	
	.upload-box{
		float:left;
		width:100px;
		height:130px;
		margin-right:10px;
		padding:4px;
		border:1px solid #dedede;
		font-size:10px;
		word-break:break-all;
		position: relative;
	}
	
	.upload-box span{
		display: block;
		width:100%;
		text-align: center;
		margin:4px 0;
		position: absolute;
		bottom:5px;
	}
	
	.bar {
		margin-top:2px;
	    height: 15px;
	    background: green;
	}
</style>

<script type="text/template" id="upload-template-image">
	<div class="upload-image upload-box">
		<img src="" alt="" class="upload-image-img" />
		<span>SUCCESS</span>
	</div>	
</script>
<script type="text/template" id="upload-template-file">
	<div class="upload-file upload-box">
		<p></p>
		<span>SUCCESS</span>
	</div>	
</script>

<script type="text/javascript">
$(function () {
	$(document).ready(function(){
		$('#fileupload').fileupload({
			url: '${contextPath}/upload.file',
	        dataType: 'json',
	        done: function (e, data) {
	        	var $box = $('#file-upload-results');
	        	
	        	$('#progress .bar').css('width',0);
	        	
	        	var f = data.result.result[0];
				
	        	if(/image/.test(f.fileType)){
	        		var $ele = $($('#upload-template-image').html());
	        		
	        		$ele.find('.upload-image-img').attr('src',f.uploadUrl);
					$ele.appendTo($box);  		
	        	}else{
	        		var $ele = $($('#upload-template-file').html());
	        		
	        		$ele.find('p').text(f.fileName);
	        		$ele.appendTo($box);
	        	}
	        },
	        progressall: function (e, data) {
	            var progress = parseInt(data.loaded / data.total * 100, 10);
	            
	            $('#progress .bar').css('width',progress + '%');
	        }
	    });	
	});
});
</script>

<span class="btn btn-success fileinput-button">
	<span>+ 선택하여 파일을 업로드하거나 여기로 파일을 드래그하세요.</span>
	<input type="file" name="file" id="fileupload" multiple="multiple"/>
</span>

<div id="progress">
    <div class="bar" style="width: 0%;"></div>
</div>

<div id="file-upload-results">

</div>
    
