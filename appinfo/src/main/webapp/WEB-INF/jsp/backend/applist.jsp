<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<div class="clearfix"></div>
<div class="row">

	<div class="col-md-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					APP 审核列表 <i class="fa fa-user"></i><small>${backend_user.userName}
						- 您可以通过搜索或者其他的筛选项对APP的信息进行审核操作。^_^</small>
				</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<form method="post" action="/app_check/list" id="form">
					<input type="hidden" name="pageNo" value="1" id="pageNo"/>
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
								<select name="categoryLevel1" id="queryCategoryLevel1"  class="form-control">
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
							<input type="hidden" name="categorylevel2list" id="categorylevel2list"/>
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
						<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info" style="width: 100%;">
							<thead>
								<tr role="row">
									<th class="sorting_asc" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 70px;" aria-label="First name: activate to sort column descending"
										aria-sort="ascending">软件名称</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 10px;"
										aria-label="Last name: activate to sort column ascending">
										APK名称</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 90px;"
										aria-label="Last name: activate to sort column ascending">
										软件大小(单位:M)</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 50px;"
										aria-label="Last name: activate to sort column ascending">
										所属平台</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 170px;"
										aria-label="Last name: activate to sort column ascending">
										所属分类(一级分类、二级分类、三级分类)</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 30px;"
										aria-label="Last name: activate to sort column ascending">
										状态</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 30px;"
										aria-label="Last name: activate to sort column ascending">
										下载次数</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 64px;"
										aria-label="Last name: activate to sort column ascending">
										最新版本号</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 30px;"
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
										<button type="button" class="btn btn-default checkApp" 
											appinfoid="" versionid="" status=""
											statusname=""
											data-toggle="tooltip" data-placement="top" title="" data-original-title="查看并审核APP">审核</button>
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
							${currentNo}/ ${currentPage}页</div>
					</div>
					<div class="col-sm-7">
						<div class="dataTables_paginate paging_simple_numbers"
							id="datatable-responsive_paginate">
							<ul class="pagination">
									<li class="paginate_button previous"><a
										href="javascript:page($('#form'),1);"
										aria-controls="datatable-responsive" data-dt-idx="0"
										tabindex="0">首页</a>
									</li>
									<li class="paginate_button "><a
										href="javascript:page($('#form'),${currentNo-1});"
										aria-controls="datatable-responsive" data-dt-idx="1"
										tabindex="0">上一页</a>
									</li>
									<li class="paginate_button "><a
										href="javascript:page($('#form'),${currentNo+1});"
										aria-controls="datatable-responsive" data-dt-idx="1"
										tabindex="0">下一页</a>
									</li>
									<li class="paginate_button next"><a
										href="javascript:page($('#form'),${currentPage});"
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
<script src="${pageContext.request.contextPath }/statics/localjs/applist.js"></script>
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
</script>