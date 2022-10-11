<input type="hidden" id="id" name="id" value="${user.id}"/>
<div class="form-group">
    <label class="col-sm-3 control-label">이름</label>
    <div class="col-sm-8"><input type="text" name="trueName" placeholder="이름을 입력하세요" class="form-control" value="${user.trueName}" oninput="handleOnName(this)"></div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">전화번호</label>
    <div class="col-sm-8"><input type="tel" id="phoneNum" placeholder="11자리 숫자로 이루어진 전화번호를 입력하세요" value="${user.phoneNum}" name="phoneNum" class="form-control"></div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">회사</label>
    <div class="col-sm-8">
        <select name="companyId" id="companyId" class="form-control input-s-sm inline">
            <option value="">회사를 선택하세요</option>
            <option value="1">베이직인터내셔널</option>
            <option value="2">비드넷씨엔씨</option>
            <option value="3">구글</option>
            <option value="4">네이버</option>
        </select>
    </div>
</div>
<div class="form-group">
    <label class="col-sm-3 control-label">역할</label>
    <div class="col-sm-8">
        <select name="roleCode" class="form-control input-s-sm inline" id="roleCode" data-toggle-name="#jiaosbox">
            <option value="" >역할을 선택하세요</option>
            <option value="admin_role" data-box-name="lianjia">관리자</option>
            <option value="common_role" data-box-name="fangdd">사용자</option>
        </select>
    </div>
</div>

<div class="form-group m-t-sm" >
    <div class="col-sm-6 col-sm-push-3">
        <button class="btn btn-md btn-primary " type="submit"><strong>등록</strong></button>
        <button type="button" class="btn btn-white m-l-sm" data-dismiss="modal">취소</button>
    </div>
</div>
<script>
    // 역할 선택(DB)
    $("#roleCode").val("${user.roleCode}");

    // 회사 선택(DB)
    $("#companyId").val(${user.companyId});


</script>