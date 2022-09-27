<div class="ibox-content">
    <div class="table-responsive ">
        <table class="table table-centerbody table-striped table-condensed text-nowrap" id="editable-sample">
            <thead>
            <tr>
                <th>제목</th>
                <th>내용</th>
                <th>URL</th>
                <th>발생시간</th>
                <th>등록시간</th>
            </tr>
            </thead>
            <tbody>
            <#if page?? && page.records?? && (page.records?size > 0) >
                <#list page.records as n> <!-- for문 -->
                    <tr>
                        <td>${n.title }</td>
                        <td>${n.description }</td>
                        <td>${n.address }</td>
                        <td>${n.newsTime?string("yyyy-MM-dd HH:mm") }</td>
                        <td>${n.createTime?string("yyyy-MM-dd HH:mm") }</td>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>
    <form action="${ctx }/news/list_page1" id="newsPageForm">
        <input type="hidden" value="${keywords! }" name="keywords"/>

        <#assign formId = "newsPageForm">
        <#assign showPageId = "ibox">
        <#include "/common/page.ftl"/>
    </form>
</div>
