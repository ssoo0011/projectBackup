package com.example.yj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVisitUserIps is a Querydsl query type for VisitUserIps
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVisitUserIps extends EntityPathBase<VisitUserIps> {

    private static final long serialVersionUID = -1650582123L;

    public static final QVisitUserIps visitUserIps = new QVisitUserIps("visitUserIps");

    public final NumberPath<Long> bno = createNumber("bno", Long.class);

    public final StringPath userIps = createString("userIps");

    public QVisitUserIps(String variable) {
        super(VisitUserIps.class, forVariable(variable));
    }

    public QVisitUserIps(Path<? extends VisitUserIps> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVisitUserIps(PathMetadata metadata) {
        super(VisitUserIps.class, metadata);
    }

}

