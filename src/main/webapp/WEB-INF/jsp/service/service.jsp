<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 服务处理 -->
<div id="service">
  <div>
  <form class="form-inline">
  <div class="form-group">
    <input type="text" name="custName" class="form-control" id="exampleInputEmail3" placeholder="请输入客户名称">
  </div>

  <div class="form-group">
	<select name="type" id="" class="form-control">
		 <option value="">请选择服务类型</option>
        <option value="咨询">咨询</option>
        <option value="投诉">投诉</option>
        <option value="建议">建议</option>
	</select>
  </div>
  <input class="btn btn-default search" type="button" value="查询">
  <input class="btn btn-default" type="reset" value="重置">
  <input class="btn btn-default" type="button" value="新增" id="add">

</form>
</div>

<div>
	<table class="table table-hover">
		 <thead>
		    <tr style="background:#E8E8E8;">
		      <th>序号</th>
		      <th>客户名称</th>
		      <th>概要</th>
		      <th>服务类型</th>
		       <th>状态</th>		 
			  <th>操作</th>
		    </tr> 
		  </thead>

		  <tbody>
			<c:forEach items="${serviceinfo.list}" var ="service">
		    <tr>
		      <td>${service.svrId}</td>
		      <td>${service.svrCustName}</td>
		      <td>${service.svrTitle}</td>
		      <td>${service.svrType}</td>
		      <td class="zhuangtai">${service.svrStatus}</td>
		      <c:if test="${service.svrStatus eq '处理中'}">
		      <td>
		      	<i value="${service.svrId}" class="layui-icon layui-icon-edit editUser" title="编辑"></i> 
      	        <i value="${service.svrId}" class="layui-icon layui-icon-ok updata" title="更新"></i> 
		      </td>
		      </c:if>
		      <c:if test="${service.svrStatus eq '已处理'}">
		      <td>
		      	<i value="${service.svrId}" class="layui-icon layui-icon-edit editUser" title="编辑"></i> 
		      	<i value="${service.svrId}" class="layui-icon layui-icon-ok updata" title="更新"></i>
		      </td>
		      </c:if>
		      <c:if test="${service.svrStatus eq '已反馈'}">
		      <td>
      	        <i value="${service.svrId}" class="layui-icon layui-icon-face-smile" title="详情"></i>  
		      </td>
		      </c:if>
		      
		    </tr> 
	   		</c:forEach>
         </tbody>
    </table>
</div>
<div class="fenye">
		<table class="footTable">
			<tr>
				<td colspan="5">共${serviceinfo.total } 条记录 每页${serviceinfo.size }
					条 第${serviceinfo.pageNum } 页 <a href="javascript:void(0)"
					onclick="pageSelect(1)">首页</a> <c:choose>
						<c:when test="${serviceinfo.hasPreviousPage }">
							<a href="javascript:void(0)"
								onclick="pageSelect(${serviceinfo.prePage})">上一页</a>
						</c:when>
						<c:otherwise>
							<font color="#ABA8AB">上一页</font>
						</c:otherwise>
					</c:choose> <c:choose>
						<c:when test="${serviceinfo.hasNextPage }">
							<a href="javascript:void(0)"
								onclick="pageSelect(${serviceinfo.nextPage})">下一页</a>
						</c:when>
						<c:otherwise>
							<font color="#ABA8AB">下一页</font>
						</c:otherwise>
					</c:choose> <a href="javascript:void(0)"
					onclick="pageSelect(${serviceinfo.lastPage })">尾页</a>
				</td>
			</tr>

		</table>
	</div>
<!-- 新增模态框 -->
<div class="modal"  id="toAdd">
  <div class="modal-dialog" style="height:400px;overflow:auto;">
    <div class="modal-content">
       <form>
					<div class="modal-body">
						<div class="form-group">
							<label>服务类型:</label>
							<select name="svrType" id="" class="form-control">
							<option value="">请选择服务类型</option>
					        <option value="咨询">咨询</option>
					        <option value="投诉">投诉</option>
					        <option value="建议">建议</option>
					        </select>
						</div>
						<div class="form-group">
							<label>概要:</label>
							<input type="text" name="svrTitle" class="form-control">
						</div>
						<div class="form-group">
							<label>客户名称:</label>
							<input type="text" name="svrCustName" class="form-control">
						</div>
						<div class="form-group">
							<label>状态:</label>
							<select name="svrStatus" id="" class="form-control">
							<option value="">请选择状态</option>
					        <option value="处理中">处理中</option>
					        <option value="已处理">已处理</option>
					        <option value="已反馈">已反馈</option>
					        </select>
						</div>
						<div class="form-group">
							<label>处理人:</label>
							<input type="text" name="svrDispose" class="form-control">
						</div>
						<div class="form-group">
							<label>服务请求:</label>
							<input type="textarea" name="svrRequest" style="height:60px" class="form-control">
						</div>
						<div class="form-group">
							<label>服务处理过程:</label>
							<input type="textarea" name="svrHandle" style="height:60px" class="form-control">
						</div>
					</div>
				</form>
				<div class="modal-footer">
				    <button class="btn" style="background:#ccc">取消</button>
					<button class="btn" style="background:#ccc">保存</button>
				</div>
    </div>
  </div>
</div>

</div>
<style>
#service{
	width: 1140px;
	overflow: hidden;
}
.pagination > .active > a, .pagination > .active > a:focus, .pagination > .active > a:hover, .pagination > .active > span, .pagination > .active > span:focus, .pagination > .active > span:hover {
    background-color: #ccc;
    border-color: #ccc;
}
.pagination > li > a, .pagination > li > span {
    color: black;
}
.fenye{
	margin-left:400px;
}
	.form-inline{
		margin-top: 15px;
		margin-left: 10px;
		margin-bottom: 20px;
	}
	.search{
		margin-left: 50px;
	}
	#add{
		margin-left: 560px;
	}
	.table th,td{
		text-align: center;
	}
	#deleteModal{
		margin-top: 100px;
	}
	#toAdd{
		margin-top: 50px;
	}
</style>
<script type="text/javascript">
	function pageSelect(curPage){
		var url = "service/findServiceByUserName/"+curPage;
		$('.layui-body').load(url)
	}
	$(function(){
		var serviceId;
		$('.layui-icon-face-smile').click(function(){
			serviceId = $(this).attr("value");
			var url = "service/toServiceDetail2/"+serviceId;
			 $('.layui-body').load(url);  
		})
		// 点击新增显示模态框
		$('#add').click(function(){
			$(".form-control").val("");
			$('#toAdd').show();
		})
		// 新增模态框关闭
		$('button:contains(取消)').on('click',function(){
			$('button[type=reset]').trigger('click');
				$('#toAdd').hide();
		})
		// 新增模态框保存
		$('button:contains(保存)').on('click',function(){
			var svrType = $("select[name=svrType]").val();
			var svrTitle = $("input[name=svrTitle]").val();
			var svrCustName = $("input[name=svrCustName]").val();
			var svrStatus = $("select[name=svrStatus]").val();
			var svrDispose = $("input[name=svrDispose]").val();
			var svrRequest = $("input[name=svrRequest]").val();
			var svrHandle = $("input[name=svrHandle]").val();
			var data={
				svrId:serviceId,
				svrType:svrType,
				svrTitle:svrTitle,
				svrCustName:svrCustName,
				svrStatus:svrStatus,
				svrDispose:svrDispose,
				svrRequest:svrRequest,
				svrHandle:svrHandle,
			};
			var url = "service/saveOrUpdate";
			$.get(url,data,function(data){
				alert(data);
				$(".layui-nav-item dd:contains('服务处理')").trigger('click');
			});
			$('#toAdd').hide();
		})	
		// 点击编辑显示模态框
		$('.editUser').click(function(){
			serviceId = $(this).attr("value");
			var url = "service/findServiceById/"+serviceId;
			$.get(url,function(data){
				$("select[name=svrType]").val(data.svrType);
				$("input[name=svrTitle]").val(data.svrTitle);
				$("input[name=svrCustName]").val(data.svrCustName);
				$("select[name=svrStatus]").val(data.svrStatus);
				$("input[name=svrDispose]").val(data.svrDispose);
				$("input[name=svrRequest]").val(data.svrRequest);
				$("input[name=svrHandle]").val(data.svrHandle);
			});
			$('#toAdd').show();
		})
		// 点击更新
		$('.updata').click(function(){
			var status = $(this).closest('tr').find('.zhuangtai').text();
			if (status=='处理中') {
				serviceId = $(this).attr("value");
				var url = "service/saveOrUpdate"; 
				var svrStatus = "已处理";
				var data={
						svrId:serviceId,
						svrStatus:svrStatus,
					};
				//console.log(data);
				$.get(url,data,function(data){
					alert(data);
					$(".layui-nav-item dd:contains('服务处理')").trigger('click');
				});
			}
		})
		// 查询
		$('.search').click(function(){
			var custName = $("input[name=custName]").val();
			var type = $("select[name=type] option:selected").val();
			var data={
					custName:custName,
					type:type
			}
			var url="service/findServiceLike/1";
			$('.layui-body').load(url,data);
		})
		
	})
</script>