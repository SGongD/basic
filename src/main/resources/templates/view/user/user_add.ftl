<div class="modal-body">
    <form role="form" id="add" name="add" class="form-horizontal">
        <div class="form-group">
            <label class="col-sm-3 control-label">아이디</label>
            <div class="col-sm-8">
                <input type="text" id="username" name="usseracc" placeholder="아이디는 6-12자 사이로 입력하세요"  required class="form-control" oninput="handleOnId(this)">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">비밀번호</label>
            <div class="col-sm-8"><input type="password" name="userPassword" placeholder="비밀번호는 7-15자 사이로 입력하세요" class="form-control"></div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">이름</label>
            <div class="col-sm-8"><input type="text" name="userName" placeholder="이름을 입력하세요" class="form-control" oninput="handleOnName(this)"></div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">전화번호</label>
            <div class="col-sm-8"><input type="tel" id="phoneNum" placeholder="11자리 숫자로 이루어진 전화번호를 입력하세요" name="phoneNum" class="form-control"></div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">이메일</label>
            <div class="col-sm-8"><input type="email" placeholder="이메일을 입력하세요" class="form-control" name="email"></div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">회사</label>
            <div class="col-sm-8">
                <select name="business" class="form-control input-s-sm inline">
                    <option value="">회사를 선택하세요</option>
                    <option value="1">베이직인터내셔널</option>
                    <option value="2">비드넷씨엔씨</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">역할</label>
            <div class="col-sm-8">
                <select name="jiaos" class="form-control input-s-sm inline"  data-toggle-name="#jiaosbox">
                    <option value="" >역할을 선택하세요</option>
                    <option value="1" data-box-name="lianjia">관리자</option>
                    <option value="2" data-box-name="fangdd">사용자</option>
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