package com.mtons.mblog.web.controller.site.user;

import com.mtons.mblog.base.lang.Result;
import com.mtons.mblog.modules.data.AccountProfile;
import com.mtons.mblog.modules.service.FollowService;
import com.mtons.mblog.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author langhsu
 */
@RestController
@RequestMapping("/user")
public class FollowController extends BaseController {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private FollowService followService;

    /**
     * 关注某人
     */
    @RequestMapping("/changeFollow")
    public Result follow(Long followUserId) {
        Result data = Result.failure("操作失败");
        if (followUserId != null) {
            try {
                AccountProfile up = getProfile();
                if (followService.changeFollow(up.getId(), followUserId)) {
                    data = Result.success("已关注", null);
                } else {
                    data = Result.success("关注Ta", null);
                }
            } catch (Exception e) {
                data = Result.failure(e.getMessage());
            }
        }
        return data;
    }

}
