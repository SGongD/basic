<div class="pages border-top">
    <div class="row">
        <div class="col-md-4">
            <div class="m-t-md">조회:  ${page.startRow } ~ ${page.endRow }. 현재 페이지 : ${page.pages },  전체 페이지 ${page.total }</div>
        </div>
        <div class="col-md-8 footable-visible">
            <ul class="pagination pull-right">
                <li class="footable-page-arrow">
                    <a data-page="1" href="javascript:void(0);" onclick="goPage(this,'${formId }','${showPageId }');">
                        «
                    </a>
                </li>
                <li class="footable-page-arrow">
                    <a data-page="${page.prePage }" href="javascript:void(0);" onclick="goPage(this,'${formId }','${showPageId }');">
                        ‹
                    </a>
                </li>
                <#if page?? && page.navigatepageNums?? && (page.navigatepageNums?size > 0)>
                    <#list page.navigatepageNums as pgnum>
                        <#if pgnum==page.current>
                            <li class="footable-page active">
                                <a data-page="${pgnum }" href="javascript:void(0);" onclick="goPage(this,'${formId }','${showPageId }');">${pgnum }</a>
                            </li>
                        <#else>
                            <li class="footable-page">
                                <a data-page="${pgnum }" href="javascript:void(0);" onclick="goPage(this,'${formId }','${showPageId }');">${pgnum }</a>
                            </li>
                        </#if>
                    </#list>
                </#if>
                <li class="footable-page-arrow">
                    <a data-page="${page.nextPage }" href="javascript:void(0);" onclick="goPage(this,'${formId }','${showPageId }');">
                        ›
                    </a>
                </li>
                <li class="footable-page-arrow">
                    <a data-page="${page.pages }" href="javascript:void(0);" onclick="goPage(this,'${formId }','${showPageId }');">
                        »
                    </a>
                </li>
            </ul>
        </div>
        <input type="hidden" name="pageNum"/>
    </div>
</div>
<script type="text/javascript">
    function goPage(objA, formId, showPageId) {
        $('#' + formId + " input[name='pageNum']").val($(objA).attr("data-page"));
        $.ajax({
            cache: true,
            type: "POST",
            url: $("#" + formId).attr("action"),
            data: $('#' + formId).serialize(),//
            async: false,
            error: function (data) {
                toastr.error('', '에러');
            },
            success: function (data) {
                $("#" + showPageId).html(data);
            }
        });
    }
</script>
