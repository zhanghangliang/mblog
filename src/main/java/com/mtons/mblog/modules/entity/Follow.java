/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.mblog.modules.entity;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.NumericField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 关注
 */
@Entity
@Table(name = "mto_follow", indexes = {
        @Index(name = "user_and_follow_id_index", columnList = "user_id, follow_id"),
        @Index(name = "follow_id_index", columnList = "follow_id")
})
public class Follow implements Serializable {
    private static final long serialVersionUID = -3629784071225114858L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 用户Id
     */
    @Field
    @NumericField
    @Column(name = "user_id")
    private long userId;

    /**
     * 被关注人Id
     */
    @Field
    @NumericField
    @Column(name = "follow_id")
    private long followId;

    public Follow() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFollowId() {
        return followId;
    }

    public void setFollowId(long followId) {
        this.followId = followId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
