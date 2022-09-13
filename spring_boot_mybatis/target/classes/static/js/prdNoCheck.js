/**
 * 
 */
 
  $(document).ready(function(){
 	$('#prdCheckBtn').on('click',function(){
 	
 	
 	event.preventDefault();
 	
 	
 	//var prdNo = $('#prdNo').val();
 	
 	if($('#prdNo').val()==""){
 		alert("상품번호를 입력하세요");
 		return false;
 	}else{
 	$.ajax({
 			type:"post",
 			url:"/product/prdNoCheck", // /mybatis/product/prdNoCheck
 			data:{"prdNo":$('#prdNo').val()},
 			dataType:"text",
 			success:function(result){
 				// 성공 시 수행되는 함수
 				// 반환되는 값을 result로 받음
 				if(result=="no_use")
 					alert("사용할 수 없는 상품번호입니다");
 				else
 					alert("사용 가능한 상품번호입니다");
 				
 			},
 			error:function(){
 				// 오류 있을 경우 수행되는 함수
 				alert("전송 실패");
 			},
 			complete:function(){
 				//alert("작업 완료");
 			}
 		
 		}); //ajax 끝
 	}
 	}); //submit 끝
 });