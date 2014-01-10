$(document).ready(function(){
    $('#navMenuList .btn-add').click(function(){
    	var data_id = $(this).parent().attr('data-id');
    	
    	$('#nestable > ol').append('<li data-id="'+data_id+'"></li>');
    	$('#nestable').trigger('change');
    });
    
    $('.cms-menu-admin .btn-modify-open').click(function(){
    	$('.cms-menu-admin .dd-item-setting').hide();
    	$(this).parent().parent().siblings('.dd-item-setting').show();
    });
    
    $('.cms-menu-admin .btn-close').click(function(){
    	$(this).parent().parent().parent().parent().parent().hide();
    });
    
    $('.cms-menu-admin .btn-modify').click(function(){
    	var $setting = $(this).parent().parent().parent();
    	var ID = $setting.parent().attr('data-id');
    	
    	var data = $setting.find(':input').serializeArray();
			data.push({name:'cmd',value:'updateMenu'});
			data.push({name:'ID',value:ID});
			
    	$.post('/cms/admin/menuAjax.cms',data,function(){
    		location.reload();
    	});
    });
    
    $('.cms-menu-admin .btn-delete').click(function(){
    	if(confirm('정말로 삭제하시겠습니까?')){
    		var $setting = $(this).parent().parent().parent();
        	var ID = $setting.attr('data-id');
        	
        	$.post('/cms/admin/menuAjax.cms',{
    			"ID" :  ID,
    			"cmd" : 'deleteMenu'
    		},function(response){
    			location.reload();	
    		});	
    	}
    	
    });
    
    $('.pagination').addClass('pagination-centered');
});