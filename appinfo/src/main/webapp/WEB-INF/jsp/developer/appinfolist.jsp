<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<div class="clearfix"></div>
<div class="row">
	<div class="col-md-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					APP 信息管理维护 <i class="fa fa-user"></i><small>${dev_user.devName}
						- 您可以通过搜索或者其他的筛选项对APP的信息进行修改、删除等管理操作。^_^</small>
				</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<form method="post" action="/app_info/list" id="goPage">
					<input type="hidden" name="pageNo" value="1" id="pageNo">
			    <ul>
					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">软件名称</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input name="softwareName" type="text" class="form-control col-md-7 col-xs-12" value="${app_info.softwareName}">
							</div>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">APP状态</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<select name="status" class="form-control">
									   <option value="">--请选择--</option>
										<c:forEach var="temp" items="${app_status}">
									<option value="${temp.valueId}" <c:if test="${app_info.status==temp.valueId}">selected</c:if>>${temp.valueName}</option>
										</c:forEach>
        						</select>
							</div>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">所属平台</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<select name="flatformId" class="form-control">
									<option value="">--请选择--</option>
									<c:forEach var="flat" items="${app_flatform}">
										<option value="${flat.valueId}" <c:if test="${app_info.flatformId==flat.valueId}">selected</c:if>>${flat.valueName}</option>
									</c:forEach>
        						</select>
							</div>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">一级分类</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<select name="categoryLevel1" id="queryCategoryLevel1" class="form-control">
									   <option value="">--请选择--</option>
									<c:forEach var="temp" items="${category01}">
										<option value="${temp.id}" <c:if test="${app_info.categoryLevel1==temp.id}">selected</c:if>>${temp.categoryName}</option>
									</c:forEach>
        						</select>
							</div>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">二级分类</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
							<%--<input type="hidden" name="categorylevel2list" id="categorylevel2list"/>--%>
        						<select name="categoryLevel2" id="queryCategoryLevel2" class="form-control">
									   <option value="">--请选择--</option>
									<c:forEach var="temp" items="${category2}">
										<option value="${temp.id}" <c:if test="${app_info.categoryLevel2==temp.id}">selected</c:if>>${temp.categoryName}</option>
									</c:forEach>
        						</select>
							</div>
						</div>
					</li>
					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">三级分类</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
        						<select name="categoryLevel3" id="queryCategoryLevel3" class="form-control">
									   <option value="">--请选择--</option>
										<c:forEach var="temp" items="${category3}">
											<option value="${temp.id}" <c:if test="${app_info.categoryLevel3==temp.id}">selected</c:if>>${temp.categoryName}</option>
										</c:forEach>
        						</select>
							</div>
						</div>
					</li>
					<li><button type="submit" class="btn btn-primary"> 查 &nbsp;&nbsp;&nbsp;&nbsp;询 </button></li>
				</ul>
			</form>
		</div>
	</div>
</div>
<div class="col-md-12 col-sm-12 col-xs-12">
	<div class="x_panel">
		<div class="x_content">
			<p class="text-muted font-13 m-b-30"></p>
			<div id="datatable-responsive_wrapper"
				class="dataTables_wrapper form-inline dt-bootstrap no-footer">
				<div class="row">
					<div class="col-sm-12">
					<a href="/app_info/add" class="btn btn-success btn-sm">新增APP基础信息</a>
						<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info" style="width: 100%;">
							<thead>
								<tr role="row">
									<th class="sorting_asc" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="First name: activate to sort column descending"
										aria-sort="ascending">软件名称</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										APK名称</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										软件大小(单位:M)</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										所属平台</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										所属分类(一级分类、二级分类、三级分类)</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										状态</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										下载次数</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										aria-label="Last name: activate to sort column ascending">
										最新版本号</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 124px;"
										aria-label="Last name: activate to sort column ascending">
										操作</th>
								</tr>
							</thead>
							<tbody>
									<c:forEach var="temp" items="${list}">
									<tr role="row" class="odd">
										<td tabindex="0" class="sorting_1">${temp.softwareName}</td>
										<td>${temp.APKName}</td>
										<td>${temp.softwareSize}</td>
										<td>${temp.pingtaiName}</td>
										<td><span id="appInfoStatus"> ${temp.level1Name} -> ${temp.level2Name} -> ${temp.level3Name}</span></td>
										<td>${temp.statusName}</td>
										<td>${temp.downloads}</td>
										<td>${temp.versionName}</td>
										<td>
										<div class="btn-group">
                      <button type="button" class="btn btn-danger">点击操作</button>
                      <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        <span class="caret"></span>
                        <span class="sr-only">Toggle Dropdown</span>
                      </button>

                      <ul class="dropdown-menu" role="menu">
                        <li>
                        	<c:choose>
								<c:when test="${temp.status == 2 || temp.status == 5}">
									<a class="saleSwichOpen" saleSwitch="open" appinfoid="${temp.id}"  appsoftwarename="${temp.softwareName}" data-toggle="tooltip" data-placement="top" title="" data-original-title="恭喜您，您的审核已经通过，您可以点击上架发布您的APP">上架</a>
								</c:when>
								<c:when test="${temp.status == 4}">
									<a class="saleSwichClose" saleSwitch="close" appinfoid="${temp.id}"  appsoftwarename="${temp.softwareName}" data-toggle="tooltip" data-placement="top" title="" data-original-title="您可以点击下架来停止发布您的APP，市场将不提供APP的下载">下架</a>
								</c:when>
							</c:choose>
                        </li>
                        <li><a class="addVersion" appinfoid="${temp.id}" data-toggle="tooltip" data-placement="top" title="" data-original-title="新增APP版本信息">新增版本</a>
                        </li>
                        <li><a class="modifyVersion"
											appinfoid="${temp.id}" versionid="${temp.versionId}" status="${temp.status}"
											statusname="${temp.statusName}"
											data-toggle="tooltip" data-placement="top" title="" data-original-title="修改APP最新版本信息">修改版本</a>
                        </li>
                        <li><a  class="modifyAppInfo"
											appinfoid="${temp.id}" status="${temp.status}" statusname="${temp.statusName}"
											data-toggle="tooltip" data-placement="top" title="" data-original-title="修改APP基础信息">修改</a></li>
                        <li><a  class="viewApp" appinfoid="${temp.id}" versionid="${temp.versionId}"  data-toggle="tooltip" data-placement="top" title="" data-original-title="查看APP基础信息以及全部版本信息">查看</a></li>
						<li><a  class="deleteApp" appinfoid="${temp.id}"  logoPicPath=${temp.logoPicPath} data-toggle="tooltip" data-placement="top" title="" data-original-title="删除APP基础信息以及全部版本信息">删除</a></li>
                      </ul>
                    </div>
										</td>
									</tr>
									</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-5">
						<div class="dataTables_info" id="datatable-responsive_info"
							role="status" aria-live="polite">共 ${pageCount} 条记录
							${currentNo}/ ${currentPage} 页</div>
					</div>
					<div class="col-sm-7">
						<div class="dataTables_paginate paging_simple_numbers"
							id="datatable-responsive_paginate">
							<ul class="pagination">
									<li class="paginate_button previous"><a
										href="javascript:page($('#goPage'),1)"
										aria-controls="datatable-responsive" data-dt-idx="0"
										tabindex="0">首页</a>
									</li>
									<li class="paginate_button "><a
										href="javascript:page($('#goPage'),${currentNo-1})"
										aria-controls="datatable-responsive" data-dt-idx="1"
										tabindex="0">上一页</a>
									</li>
									<li class="paginate_button "><a
										href="javascript:page($('#goPage'),${currentNo+1})"
										aria-controls="datatable-responsive" data-dt-idx="1"
										tabindex="0">下一页</a>
									</li>
									<li class="paginate_button next"><a
										href="javascript:page($('#goPage'),${currentPage})"
										aria-controls="datatable-responsive" data-dt-idx="7"
										tabindex="0">最后一页</a>
									</li>
							</ul>
						</div>
					</div>
				</div>
			</div>

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

    function page(form,index) {
		$("#pageNo").val(index);
		form.submit();
    }

    $(".modifyAppInfo").click(function () {
        var id = $(this).attr("appinfoid");
		var stauts = $(this).attr("status");
		var stautsName = $(this).attr("statusname");
		if(stauts == "1" || stauts == "3"){
		    location.href="/app_info/modify?id="+id;
		}else{
            alert("该APP应用的状态为：【"+stautsName+"】,不能修改！");
        }
    });
    $(".viewApp").click(function () {
		var id = $(this).attr("appinfoid");
		var version = $(this).attr("versionid");
		location.href="/app_info/view?id="+id+"&version="+version;
    });
    $(".addVersion").click(function () {
		var id = $(this).attr("appinfoid");
		location.href="/app_version/add?id="+id;
    });
    $(".modifyVersion").click(function () {
        var obj = $(this);
        var status = obj.attr("status");
        var versionid = obj.attr("versionid");
        var appinfoid = obj.attr("appinfoid");
        if(status == "1" || status == "3"){//待审核、审核未通过状态下才可以进行修改操作
            if(versionid == null || versionid == ""){
                alert("该APP应用无版本信息，请先增加版本信息！");
            }else{
                location.href="/app_version/modify?versionId="+versionid+"&id="+appinfoid;
            }
        }else{
            alert("该APP应用的状态为：【"+obj.attr("statusname")+"】,不能修改其版本信息，只可进行【新增版本】操作！");
        }
    });
    $(".deleteApp").click(function () {
		var obj = $(this);
		var infoId = obj.attr("appinfoid");
		var logoPicPath = obj.attr("logoPicPath");
		if(confirm("将APP的信息及所有版本信息删除，确定要删除吗?")) {
            $.post("/app_info/deleteInfo", "infoId=" + infoId + "&logoPicPath=" + logoPicPath, callBack, "json");
            function callBack(data) {
                if (data=="true") {
                    alert("删除成功!");
                    obj.parent().parent().parent().parent().parent().remove();
                } else {
                    alert("删除失败!");
                }
            }
        }
    });
</script>