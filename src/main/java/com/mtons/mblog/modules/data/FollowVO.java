package com.mtons.mblog.modules.data;

import com.mtons.mblog.modules.entity.Follow;

public class FollowVO extends Follow {

    private UserVO user;

    private UserVO follow;

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }

    public UserVO getFollow() {
        return follow;
    }

    public void setFollow(UserVO follow) {
        this.follow = follow;
    }
}
