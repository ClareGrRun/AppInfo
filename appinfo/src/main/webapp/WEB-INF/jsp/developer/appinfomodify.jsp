<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<div class="clearfix"></div>
<div class="row">
  <div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
      <div class="x_title">
        <h2>修改APP基础信息 <i class="fa fa-user"></i><small>${dev_user.devName}</small></h2>
             <div class="clearfix"></div>
      </div>
      <div class="x_content">
        <form class="form-horizontal form-label-left" action="/app_info/modify" method="post" enctype="multipart/form-data">
          <input type="hidden" name="id" id="id" value="${modifyinfo.id}">
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">软件名称 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="softwareName" class="form-control col-md-7 col-xs-12" 
               data-validate-length-range="20" data-validate-words="1" 
               name="softwareName" value="${modifyinfo.softwareName}" required="required"
                type="text">
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">APK名称 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="APKName" type="text" class="form-control col-md-7 col-xs-12" 
              name="APKName" value="${modifyinfo.APKName}" readonly="readonly">
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">支持ROM <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="supportROM" class="form-control col-md-7 col-xs-12" 
              	name="supportROM" value="${modifyinfo.supportROM}" required="required"
              	data-validate-length-range="20" data-validate-words="1" 
              	 type="text">
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">界面语言 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input id="interfaceLanguage" class="form-control col-md-7 col-xs-12" 
              data-validate-length-range="20" data-validate-words="1"  required="required"
              name="interfaceLanguage" value="${modifyinfo.interfaceLanguage}"
               type="text">
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="number">软件大小 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="number" id="softwareSize" name="softwareSize" value="${modifyinfo.softwareSize}" required="required"
              data-validate-minmax="10,500"  class="form-control col-md-7 col-xs-12">
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="number">下载次数 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="number" id="downloads" name="downloads" value="${modifyinfo.downloads}" required="required"
              data-validate-minmax="10,500"  class="form-control col-md-7 col-xs-12">
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12"  for="select">所属平台 <span class="required">*</span></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="hidden" value="${modifyinfo.flatformId}" id="fid" />
              <select name="flatformId" id="flatformId" class="form-control" required="required">
                <option value="">--请选择--</option>
                <c:forEach var="flat" items="${app_flatform}">
                  <option value="${flat.valueId}" <c:if test="${modifyinfo.flatformId==flat.valueId}">selected</c:if>>${flat.valueName}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="select">一级分类 <span class="required">*</span></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="hidden" value="${modifyinfo.categoryLevel1}" id="cl1" />
              <select name="categoryLevel1" id="queryCategoryLevel1" class="form-control"  required="required">
                <option value="">--请选择--</option>
                <c:forEach var="temp" items="${category1}">
                  <option value="${temp.id}" <c:if test="${modifyinfo.categoryLevel1==temp.id}">selected</c:if>>${temp.categoryName}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12"  for="select">二级分类 <span class="required">*</span></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
            	<input type="hidden" value="${modifyinfo.categoryLevel2}" id="cl2" />
              <select name="categoryLevel2" id="queryCategoryLevel2" class="form-control"  required="required">
                <option value="">--请选择--</option>
                <c:forEach var="temp" items="${category2}">
                  <option value="${temp.id}" <c:if test="${modifyinfo.categoryLevel2==temp.id}">selected</c:if>>${temp.categoryName}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="select">三级分类 <span class="required">*</span></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <input type="hidden" value="${modifyinfo.categoryLevel3}" id="cl3" />
              <select name="categoryLevel3" id="queryCategoryLevel3" class="form-control"  required="required">
                <option value="">--请选择--</option>
                <c:forEach var="temp" items="${category3}">
                  <option value="${temp.id}" <c:if test="${modifyinfo.categoryLevel3==temp.id}">selected</c:if>>${temp.categoryName}</option>
                </c:forEach>
              </select>
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">APP状态 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
            	<input id="statusName" type="text" class="form-control col-md-7 col-xs-12" 
              	name="statusName" value="${modifyinfo.statusName}" readonly="readonly">
            </div>
          </div>
          <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="textarea">应用简介 <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
              <textarea id="appInfo" name="appInfo" required="required" class="form-control col-md-7 col-xs-12">
              ${modifyinfo.appInfo}</textarea>
            </div>
          </div>
           <div class="item form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">LOGO图片 <span class="required">*</span>
            </label>

            <div class="col-md-6 col-sm-6 col-xs-12">
				<input type="hidden" id="logoPicPath" name="logoPicPath" value="${modifyinfo.logoPicPath}"/>
            	<input type="hidden" id="logoLocPath" name="logoLocPath" value="${modifyinfo.logoLocPath}"/>
              <img id="image" src="${modifyinfo.logoPicPath}" style="width: 60px;height: 60px"><a href="/app_info/upload?filename=${modifyinfo.logoPicPath}" id="upload">下载</a> <a id="delPic">删除</a>
				<div id="logoFile"></div>
				${fileUploadError }
            </div>
          </div>
          <div class="form-group">
            <div class="col-md-6 col-md-offset-3">
            	<c:if test="${modifyinfo.status == 3}">
                  <input type="hidden" value="" id="status" name="status">
            	 	<button id="saveAndsend" type="button" class="btn btn-success">保存并再次提交审核</button>
            	</c:if>
              <button id="send" type="submit" class="btn btn-success">保存</button>
              <button type="button" class="btn btn-primary" id="back">返回</button>
              <br/><br/>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
<%@include file="common/footer.jsp"%>
<script src="${pageContext.request.contextPath}/statics/js/jquery-1.8.3.js"></script>
<script>
    $("#queryCategoryLevel1").change(function () {
        var queryCategoryLevel1 = $("#queryCategoryLevel1").val();
        if(queryCategoryLevel1!=""&&queryCategoryLevel1!=null){
            $.post("/app_category/category","id="+queryCategoryLevel1,callBack,"json");
            function callBack(data) {
                $("#queryCategoryLevel2").html("");
                var options = "<option value=\"\">--请选择--</option>";
                for (var i =0;i<data.length;i++){
                    options += "<option value=\""+data[i].id+"\">"+data[i].categoryName+"</option>";
                }
                $("#queryCategoryLevel2").html(options);
            }
        }else{
            $("#queryCategoryLevel2").html("");
            var options = "<option value=\"\">--请选择--</option>";
            $("#queryCategoryLevel2").html(options);
        }
        $("#queryCategoryLevel3").html("");
        var options = "<option value=\"\">--请选择--</option>";
        $("#queryCategoryLevel3").html(options);
    });

    $("#queryCategoryLevel2").change(function () {
        var queryCategoryLevel2 = $("#queryCategoryLevel2").val();
        if(queryCategoryLevel2!=""&&queryCategoryLevel2!=null){
            $.post("/app_category/category","id="+queryCategoryLevel2,callBack,"json");
            function callBack(data) {
                $("#queryCategoryLevel3").html("");
                var options = "<option value=\"\">--请选择--</option>";
                for (var i =0;i<data.length;i++){
                    options += "<option value=\""+data[i].id+"\">"+data[i].categoryName+"</option>";
                }
                $("#queryCategoryLevel3").html(options);
            }
        }else{
            $("#queryCategoryLevel3").html("");
            var options = "<option value=\"\">--请选择--</option>";
            $("#queryCategoryLevel3").html(options);
        }
    });
    $("#back").on("click",function(){
        window.location.href = "list";
    });
    $("#softwareName").change(function () {
        var softwareName = $(this).val();
        if(softwareName!=null&&softwareName!=''){
            $.post("/app_info/yz","softwareName="+softwareName,callBack,"json");
            function callBack(data) {
                if(data=="false"){
                    alert("该软件名称已经存在!");
                }else{
                    alert("该软件名称可用!");
                }
            }
        }
    });
    $("#saveAndsend").click(function () {
          $("#status").val("1");
          $("form").submit();
    });
    $("#delPic").click(function () {
        var obj = $(this);
        if(confirm("确定要删除吗?")){
          var logoLocPath = $("#logoLocPath").val();
          var context = $("#context").val();
          $.post("/app_info/delPic","loc="+logoLocPath,callBack,"json");
          function callBack(data) {
              if(data=="true"){
                  $("#logoFile").html("<input type=\"file\" class=\"form-control col-md-7 col-xs-12\" name=\"attach\"  required=\"required\" id=\"a_logoPicPath\"/>");
                  $("#image").hide();
                  $("#upload").hide();
                  obj.hide();
              }
          }
        }
    })
</script>