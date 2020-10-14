<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<html lang="ko">
<head>

   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
   



</head>

<body>


   <div id="container_box">
      <h2>상품 등록</h2>
      
         <form role="form" method="post" autocomplete="off">
          
          <label>1차 분류</label>
          <select class="category1">
           <option value="">전체</option>
          </select>
          
          <label>2차 분류</label>
          <select class="category2" name="cateCode">
           <option value="300">전체</option> 
          </select>
          
         <div class="inputArea">
          <label for="gdsName">상품명</label>
          <input type="text" id="gdsName" name="gdsName" />
         </div>
         
         <div class="inputArea">
          <label for="gdsName">이미지</label>
          <input type="text" id="gdsImg" name="gdsImg" />
         </div>
         
         
         <div class="inputArea">
          <label for="gdsPrice">상품가격</label>
          <input type="text" id="gdsPrice" name="gdsPrice" />
         </div>
         
         <div class="inputArea">
          <label for="gdsStock">상품수량</label>
          <input type="text" id="gdsStock" name="gdsStock" />
         </div>
         
         <div class="inputArea">
          <label for="gdsDes">상품소개</label>
          <textarea rows="5" cols="50" id="gdsDes" name="gdsDes"></textarea>
         </div>
         
         <div class="inputArea">
          <button type="submit" id="register_Btn" class="btn btn-primary">등록</button>
         </div>          

          
         </form>
      

   </div>

   <script>
      // 컨트롤러에서 데이터 받기
      var jsonData = JSON.parse('${category}');
   
      // 필요한 배열과 오브젝트 변수 생성
      var cate1Arr = new Array();
      var cate1Obj = new Object();
   
      // 1차 분류 셀렉트 박스에 삽입할 데이터 준비
      for(var i = 0; i < jsonData.length; i++) {
         
         if(jsonData[i].level == "1") {  // 레벨이 1인 데이터가 있다면 
            cate1Obj = new Object();  // 초기화
            
            // cate1Obj에 cateCode와 cateName를 저장
            cate1Obj.cateCode = jsonData[i].cateCode; 
            cate1Obj.cateName = jsonData[i].cateName;
            
            // cate1Obj에 저장된 값을 cate1Arr 배열에 저장
            cate1Arr.push(cate1Obj);
         }
      }
      
      // 1차 분류 셀렉트 박스에 데이터 삽입
      var cate1Select = $("select.category1")

      for(var i = 0; i < cate1Arr.length; i++) {

         // cate1Arr에 저장된 값을 cate1Select에 추가
         cate1Select.append("<option value='" + cate1Arr[i].cateCode + "'>"
                        + cate1Arr[i].cateName + "</option>");   
      }
      
      
      $(document).on("change", "select.category1", function(){

          var cate2Arr = new Array();
          var cate2Obj = new Object();
          
          // 2차 분류 셀렉트 박스에 삽입할 데이터 준비
          for(var i = 0; i < jsonData.length; i++) {
           
           if(jsonData[i].level == "2") {
            cate2Obj = new Object();  //초기화
            cate2Obj.cateCode = jsonData[i].cateCode;
            cate2Obj.cateName = jsonData[i].cateName;
            cate2Obj.cateCodeRef = jsonData[i].cateCodeRef;
            
            cate2Arr.push(cate2Obj);
           }
          }
          
          var cate2Select = $("select.category2");
          
          /*
          for(var i = 0; i < cate2Arr.length; i++) {
            cate2Select.append("<option value='" + cate2Arr[i].cateCode + "'>"
                 + cate2Arr[i].cateName + "</option>");
          }
          */
          
          cate2Select.children().remove();

          $("option:selected", this).each(function(){
           
           var selectVal = $(this).val();  
           cate2Select.append("<option value='" + selectVal + "'>전체</option>");
           
           for(var i = 0; i < cate2Arr.length; i++) {
            if(selectVal == cate2Arr[i].cateCodeRef) {
             cate2Select.append("<option value='" + cate2Arr[i].cateCode + "'>"
                  + cate2Arr[i].cateName + "</option>");
            }
           }
           
          });
          
         });
      
      
      
   </script>

</body>
</html>