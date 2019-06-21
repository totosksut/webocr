<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<title>OCR WebApp</title>
<script>
	var context = '${pageContext.request.contextPath}';

	$(document).ready(function() {

		$(document).ajaxStart(function() {
			$("#wait").show();
			$("#resultText").hide();
		}).ajaxStop(function() {
			$("#wait").hide();
			$("#resultText").show();
		});

		$('#btnUpload').click(function() {
			var formData = new FormData();
			formData.append('file', $('#fileUpload')[0].files[0]);
			$('#resultText').text('');
			$.ajax({
				url : context + '/fileocr',
				type : 'POST',
				async : true,
				cache : false,
				data : formData,
				processData : false, // tell jQuery not to process the data
				contentType : false, // tell jQuery not to set contentType
				success : function(data) {
					console.log(data);
					$('#resultText').text(data);
				}
			});
		});

		$('#btnClear').click(function() {
			$('#fileUpload').val(null);
			$('#resultText').text(null);
		});

	});
</script>

</head>
<body>
	<div class="panel panel-primary">
		<div class="panel-heading">OCR Panel</div>
		<div class="panel-body">
			<div class="panel panel-default">
				<div class="panel-body" align="center">
					<input type="file" id="fileUpload" name="fileUpload"><br>
					<button type="button" class="btn btn-success" id="btnUpload">submit</button>
					<button type="button" class="btn btn-danger" id="btnClear">
						clear</button>
				</div>
			</div>
			<div class="panel panel-info">
				<div class="panel-heading">Result</div>
				<div class="panel-body" align="center">
					<div id="wait" 
						style="display: none; width: 69px; height: 89px;">
						<img src='<c:url value="/img/demo_wait.gif"  />' width="64"
							height="64" /><br>Loading..
					</div>
					<textarea rows="20" cols="50" style="width: 100%" id="resultText"></textarea>
				</div>
			</div>
		</div>
	</div>
</body>
</html>