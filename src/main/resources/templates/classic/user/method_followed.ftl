<@layout.extends name="/inc/layout.ftl">
    <@layout.put block="title">
        <title>${user.name}的关注</title>
    </@layout.put>

    <@layout.put block="contents">
        <div class="row users-show">
            <div class="col-xs-12 col-md-3 side-left">
                <@layout.extends name="/inc/user_sidebar.ftl" />
            </div>
            <div class="col-xs-12 col-md-9 side-right">
                <div class="panel panel-default">
                    <@user_followed userId=user.id pageNo=pageNo>
                        <div class="panel-heading">我的关注数: ${total}</div>
                        <div class="panel-body">
                            <ul class="list-group">
                                <#list results.content as row>
                                    <#assign target = row.follow />
                                    <li class="list-group-item">
                                        <#if target??>
                                            <span>你关注了</span>
                                            <span class="avatar">
                                                <img src="<@resource src=target.avatar + '?t=' + .now?time/>" class="lazy avatar avatar-50 photo" height="30" width="30">
                                            </span>
                                            <a href="${base}/users/${target.id}" target="_blank">${target.name}</a>
                                        <#else>
                                            <a href="javascript:;" class="remove-padding-left">用户已注销</a>
                                        </#if>
                                    </li>
                                </#list>

                                <#if results.content?size == 0>
                                    <li class="list-group-item ">
                                        <div class="infos">
                                            <div class="media-heading"></div>
                                        </div>
                                    </li>
                                </#if>
                            </ul>
                        </div>
                        <div class="panel-footer">
                            <@utils.pager request.requestURI!'', results, 5/>
                        </div>
                    </@user_followed>
                </div>
            </div>
        </div>
        <!-- /end -->
    </@layout.put>
</@layout.extends>