<div class="modal-body">
    <input type="hidden" name="id" value="${user.id}"/>
    <form role="form" id="add" name="add" class="form-horizontal">
        <div class="form-group">
            <label class="col-sm-3 control-label">아이디</label>
            <div class="col-sm-8">
                <input type="text" id="username" name="username" placeholder="아이디는 6-12자 사이로 입력하세요"  required class="form-control" oninput="handleOnId(this)">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">비밀번호</label>
            <div class="col-sm-8"><input type="password" name="userPassword" placeholder="비밀번호는 7-15자 사이로 입력하세요" class="form-control"></div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">비밀번호 확인</label>
            <div class="col-sm-8"><input type="password" placeholder="이메일을 입력하세요" class="form-control" id="password2"></div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">이름</label>
            <div class="col-sm-8"><input type="text" name="trueName" placeholder="이름을 입력하세요" class="form-control" oninput="handleOnName(this)"></div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">전화번호</label>
            <div class="col-sm-8"><input type="tel" id="phoneNum" placeholder="11자리 숫자로 이루어진 전화번호를 입력하세요" name="phoneNum" class="form-control"></div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">회사</label>
            <div class="col-sm-8">
                <select name="business" class="form-control input-s-sm inline">
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
                <select name="role" class="form-control input-s-sm inline"  data-toggle-name="#jiaosbox">
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
    </form>
</div>