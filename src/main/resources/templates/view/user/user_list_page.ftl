<div class="ibox-content">
    <div class="table-responsive ">
        <table class="table table-centerbody table-striped table-condensed text-nowrap" id="editable-sample">
            <thead>
            <tr>
                <th>아이디</th>
                <th>이름</th>
                <th>전화번호</th>
                <th>역할</th>
                <th>회사</th>
                <th>등록일시</th>
                <th>상태</th>
                <th class="text-right">작업</th>
            </tr>
            </thead>
            <tbody>
            <#if page?? && page.records?? && (page.records?size > 0) >
                <#list page.records as u>
                    <tr>
                        <!-- model/auth/User.java 에서 따옴. -->
                        <td>${u.username}</td>
                        <td>${u.trueName}</td>
                        <td>${u.phoneNum}</td>
                        <td>${u.roleRemark}</td>
                        <td>${u.companyName}</td>
                        <td>${u.createTime?string("yyyy-MM-dd HH:mm")}</td>
                        <td>
                            <a class="btn btn-primary btn-circle btn-sm" onclick="setState(this)" data-id="${u.id}"><i class="fa fa-check"></i></a>
                        </td>
                        <td class="text-right text-nowrap">
                            <div class="btn-group ">
                                <button class="btn btn-white btn-sm edit" data-id="${u.id}" data-toggle="modal" data-target="#edit"><i class="fa fa-pencil"></i>수정</button>
                                <button class="btn-white  btn btn-sm rset" data-id="${u.id}" data-toggle="modal" data-target="#myModa-reset"><i class="fa fa-eye"></i>  비밀번호 변경</button>
                                <button class="btn-white  btn btn-sm delete" data-id="${u.id}" onclick="setSelectId(${u.id})"><i class="fa fa-trash"></i>  삭제</button>
                            </div>
                        </td>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>

    <form action="/user/user_list_page" id="userPageForm">
        <input type="hidden" value="${keywords! }" name="keywords"/>
        <#assign formId = "userPageForm">
        <#assign showPageId = "ibox">
        <#include "/common/page.ftl"/>
    </form>
</div>