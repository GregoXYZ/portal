<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/javascripts.jspf"%>
<%@ include file="/WEB-INF/jsp/includes/styles.jspf"%>

<script src="${pageContext.request.contextPath}/js/jquery/addons/jquery.tools.min.js" type="text/javascript"></script>

<script type="text/javascript">
	// execute your scripts when DOM is ready. this is a good habit 
	$(document).ready(function() {
	    // expose the form when it's clicked or cursor is focused 
	    $("form.expose").bind("click keydown", function() { 
	 
	        $(this).expose({ 	 
	            // custom mask settings with CSS 
	            //maskId: 'mask', 
	 
	            // when exposing is done, change form's background color 
	            onLoad: function() { 
	                this.getExposed().css({backgroundColor: null}); 
	            }, 
	 
	            // when "unexposed", return to original background color 
	            onClose: function() { 
	                this.getExposed().css({backgroundColor: null}); 
	            }, 
	 
	            api: true 
	 
	        }).load(); 
	    }); 
	});
</script>

<div id="login" class="izquierda">
	<%@ include file="login.jsp" %>
</div>
<div id="register" class="derecha">
	<%@ include file="register.jsp" %>
</div>	
