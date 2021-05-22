package com.mtons.mblog.modules.service;

import com.mtons.mblog.modules.data.FollowVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FollowService {

    /**
     * 关注某人
     *
     * @param userId       用户ID
     * @param followUserId 被关注用户ID
     * @return true 关注 false 取消关注
     */
    boolean changeFollow(long userId, long followUserId);

    /**
     * 判断是否已关注
     *
     * @param userId   登录用户id
     * @param authorId 文章用户Id
     * @return 0未关注 1已关注
     */
    Integer checkIsFollowed(Long userId, Long authorId);

    Page<FollowVO> pagingByUserId(Pageable pageable, long userId);

    Page<FollowVO> pagingByFollowId(Pageable pageable, long userId);
}
