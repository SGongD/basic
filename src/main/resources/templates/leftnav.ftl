<div class="sidebar-scroll">
    <div class="logo">
        <a href="${ctx}/index">
            <img src="${ctx }/static/images/logo_dark.png" alt="" width="55" height="40">
            베이직
        </a>
    </div>
    <div class="sidebar-collapse">
        <div class="nav-header" id="side-head">
            <div class="dropdown profile-element text-center">
                <span><img alt="image" class="img-circle " src="${ctx }/static/img/profile_small.jpg"/></span>
                <a data-toggle="dropdown" class="dropdown-toggle" href="#"><span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">Jack</strong></span>
						<span class="text-muted text-xs block">사용자설정<b class="caret"></b></span>
				</span>
                </a>
                <ul class="dropdown-menu animated fadeInRight m-t-xs">
                    <li>
                        <a href="javascript:void(0)">개인정보</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">작업</a>
                    </li>
                    <li>
                        <a href="javascript:void(0)">메세지</a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href=${ctx}/login">로그아웃</a>
                    </li>
                </ul>
            </div>
        </div>
        <ul class="nav metismenu" id="side-menu">
            <#if permission_session?? && (permission_session?size > 0) >
                <#list permission_session as one>
                    <li id="menu-${one.skey}">
                        <#if one.url??>
                        <a href="${ctx}/${one.url!''}">
                            <#else>
                            <a href="#">
                                </#if>
                                <#if one.children?? && (one.children?size > 0) >
                                <i class="fa ${one.cssClass!''}"></i>
                                <span class="nav-label">${one.name!''}</span>
                                <span class="fa arrow"></span>
                            </a>
                            <ul class="nav nav-second-level collapse">
                                <#list one.children as two>
                                    <li id="menu-${two.skey}" data-submenu-id="menu-${one.skey}">
                                        <#if two?? && two.url??>
                                        <a href="${ctx}/${two.url!''}">
                                            <#else>
                                            <a href="#">
                                                </#if>
                                                <i class="fa ${two.cssClass!''}"></i>${two.name!''}</a>
                                            <#if two.children?? && (two.children?size > 0) >
                                                <ul class="nav nav-third-level">
                                                    <#list two.children as three>
                                                        <li id="menu-${three.skey}" data-menu-id="menu-${two.skey}" data-submenu-id="menu-${one.skey}">
                                                            <a href="${ctx }/${three.url!''}">
                                                                <i class="fa ${three.cssClass!''}"></i>${three.name!''}
                                                            </a>
                                                        </li>
                                                    </#list>
                                                </ul>
                                            </#if>
                                    </li>
                                </#list>
                            </ul>
                            <#else>
                            <i class="fa ${one.cssClass!''}"></i>
                            <span class="nav-label">${one.name!''}</span>
                        </a>
                        </#if>
                    </li>
                </#list>
            </#if>
        </ul>
    </div>
</div>
