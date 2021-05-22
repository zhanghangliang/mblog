/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.mblog.modules.repository;

import com.mtons.mblog.modules.entity.Favorite;
import com.mtons.mblog.modules.entity.Follow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FollowRepository extends JpaRepository<Follow, Long>, JpaSpecificationExecutor<Follow> {
    Page<Follow> findAllByUserId(Pageable pageable, long userId);
    Page<Follow> findAllByFollowId(Pageable pageable, long userId);
}
