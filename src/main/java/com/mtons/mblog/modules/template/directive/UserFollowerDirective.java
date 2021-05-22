/**
 *
 */
package com.mtons.mblog.modules.template.directive;

import com.mtons.mblog.modules.data.FollowVO;
import com.mtons.mblog.modules.service.FollowService;
import com.mtons.mblog.modules.template.DirectiveHandler;
import com.mtons.mblog.modules.template.TemplateDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * 根据用户取粉丝列表
 * 即为followId为登录用户的情况
 */
@Component
public class UserFollowerDirective extends TemplateDirective {
    @Autowired
    private FollowService followService;

    @Override
    public String getName() {
        return "user_follower";
    }

    @Override
    public void execute(DirectiveHandler handler) throws Exception {
        long userId = handler.getInteger("userId", 0);
        Pageable pageable = wrapPageable(handler);

        Page<FollowVO> result = followService.pagingByFollowId(pageable, userId);
        handler.put("total", result.getTotalElements());
        handler.put(RESULTS, result).render();
    }
}
