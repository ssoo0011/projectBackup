package com.example.yj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = 1425848479L;

    public static final QPost post = new QPost("post");

    public final NumberPath<Long> bno = createNumber("bno", Long.class);

    public final StringPath content = createString("content");

    public final NumberPath<Integer> likeNum = createNumber("likeNum", Integer.class);

    public final DatePath<java.time.LocalDate> postDate = createDate("postDate", java.time.LocalDate.class);

    public final StringPath pub = createString("pub");

    public final ListPath<Reply, QReply> replyList = this.<Reply, QReply>createList("replyList", Reply.class, QReply.class, PathInits.DIRECT2);

    public final NumberPath<Long> scdId = createNumber("scdId", Long.class);

    public final StringPath title = createString("title");

    public final DatePath<java.sql.Date> visitDate = createDate("visitDate", java.sql.Date.class);

    public final NumberPath<Integer> visitPost = createNumber("visitPost", Integer.class);

    public final StringPath visitSpot = createString("visitSpot");

    public final StringPath writer = createString("writer");

    public QPost(String variable) {
        super(Post.class, forVariable(variable));
    }

    public QPost(Path<? extends Post> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPost(PathMetadata metadata) {
        super(Post.class, metadata);
    }

}

