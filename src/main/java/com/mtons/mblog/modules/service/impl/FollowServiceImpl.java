package com.mtons.mblog.modules.service.impl;

import com.mtons.mblog.base.utils.BeanMapUtils;
import com.mtons.mblog.modules.data.FavoriteVO;
import com.mtons.mblog.modules.data.FollowVO;
import com.mtons.mblog.modules.data.PostVO;
import com.mtons.mblog.modules.data.UserVO;
import com.mtons.mblog.modules.entity.Favorite;
import com.mtons.mblog.modules.entity.Follow;
import com.mtons.mblog.modules.entity.User;
import com.mtons.mblog.modules.repository.FollowRepository;
import com.mtons.mblog.modules.service.FollowService;
import com.mtons.mblog.modules.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Slf4j
@Service
@Transactional(readOnly = true)
public class FollowServiceImpl implements FollowService {

    @Autowired
    private UserService userService;

    @Autowired
    private FollowRepository followRepository;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean changeFollow(long userId, long followUserId) {
        Assert.isTrue(userId != followUserId, "无法关注自己哟");
        Optional<Follow> one = followRepository.findOne((Specification<Follow>) (root, cq, cb) -> {
            Predicate p1 = cb.equal(root.get("userId"), userId);//进行精准的匹配  （比较的属性，比较的属性的取值）
            Predicate p2 = cb.equal(root.get("followId"), followUserId);//进行精准的匹配  （比较的属性，比较的属性的取值）
            return cb.and(p1, p2);
        });
        if(one.isPresent()) {
            // 取消关注流程
            followRepository.deleteById(one.get().getId());
            return false;
        } else {
            // 关注流程
            Follow follow = new Follow();
            follow.setUserId(userId);
            follow.setFollowId(followUserId);
            followRepository.save(follow);
            return true;
        }
    }

    /**
     * 判断是否已关注
     * @param userId 登录用户id
     * @param authorId 文章用户Id
     * @return 0未关注 1已关注
     */
    @Override
    public Integer checkIsFollowed(Long userId, Long authorId) {
        Optional<Follow> one = followRepository.findOne((Specification<Follow>) (root, cq, cb) -> {
            Predicate p1 = cb.equal(root.get("userId"), userId);
            Predicate p2 = cb.equal(root.get("followId"), authorId);
            return cb.and(p1, p2);
        });
        if (one.isPresent()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Page<FollowVO> pagingByUserId(Pageable pageable, long userId) {
        Page<Follow> page = followRepository.findAllByUserId(pageable, userId);

        List<FollowVO> rets = new ArrayList<>();
        Set<Long> userIds = new HashSet<>();
        for (Follow po : page.getContent()) {
            rets.add(BeanMapUtils.copy(po));
            userIds.add(po.getUserId());
            userIds.add(po.getFollowId());
        }

        if (userIds.size() > 0) {
            Map<Long, UserVO> posts = userService.findMapByIds(userIds);

            for (FollowVO t : rets) {
                UserVO u1 = posts.get(t.getUserId());
                t.setUser(u1);
                UserVO u2 = posts.get(t.getFollowId());
                t.setFollow(u2);
            }
        }
        return new PageImpl<>(rets, pageable, page.getTotalElements());
    }

    @Override
    public Page<FollowVO> pagingByFollowId(Pageable pageable, long userId) {
        Page<Follow> page = followRepository.findAllByFollowId(pageable, userId);

        List<FollowVO> rets = new ArrayList<>();
        Set<Long> userIds = new HashSet<>();
        for (Follow po : page.getContent()) {
            rets.add(BeanMapUtils.copy(po));
            userIds.add(po.getUserId());
            userIds.add(po.getFollowId());
        }

        if (userIds.size() > 0) {
            Map<Long, UserVO> posts = userService.findMapByIds(userIds);

            for (FollowVO t : rets) {
                UserVO u1 = posts.get(t.getUserId());
                t.setUser(u1);
                UserVO u2 = posts.get(t.getFollowId());
                t.setFollow(u2);
            }
        }
        return new PageImpl<>(rets, pageable, page.getTotalElements());
    }
}
