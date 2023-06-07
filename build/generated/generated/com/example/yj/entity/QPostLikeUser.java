package com.example.yj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPostLikeUser is a Querydsl query type for PostLikeUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostLikeUser extends EntityPathBase<PostLikeUser> {

    private static final long serialVersionUID = 618363329L;

    public static final QPostLikeUser postLikeUser = new QPostLikeUser("postLikeUser");

    public final NumberPath<Long> bno = createNumber("bno", Long.class);

    public final NumberPath<Long> likeId = createNumber("likeId", Long.class);

    public final StringPath likeUserId = createString("likeUserId");

    public QPostLikeUser(String variable) {
        super(PostLikeUser.class, forVariable(variable));
    }

    public QPostLikeUser(Path<? extends PostLikeUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPostLikeUser(PathMetadata metadata) {
        super(PostLikeUser.class, metadata);
    }

}

