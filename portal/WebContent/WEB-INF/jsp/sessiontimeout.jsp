<%@ include file="/WEB-INF/jsp/includes/tagLibs.jspf"%>

<script type="text/javascript">
	$(document).ready( function() {
		ajaxPopup('/portal/login.do', null, 'uri=${uri}');
	});	
</script>
