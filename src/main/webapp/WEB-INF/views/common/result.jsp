<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>
    
    <script type="text/javascript">
		let message = '${message}';
		if(message == "fail"){
			swal("문의가 실패되었습니다.","","fail").then(function(){
				location.href="${url}";
			})
		}else{
			
			swal("문의가 완료되었습니다.","","success").then(function(){
				location.href="${url}";
			})
		}
	</script>
</body>
</html>