package com.example.yj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTourList is a Querydsl query type for TourList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTourList extends EntityPathBase<TourList> {

    private static final long serialVersionUID = 1134013333L;

    public static final QTourList tourList = new QTourList("tourList");

    public final StringPath imgSrc = createString("imgSrc");

    public final NumberPath<Long> scheduleId = createNumber("scheduleId", Long.class);

    public final StringPath spot = createString("spot");

    public final NumberPath<Long> tourSpotList = createNumber("tourSpotList", Long.class);

    public final StringPath visitSpot = createString("visitSpot");

    public QTourList(String variable) {
        super(TourList.class, forVariable(variable));
    }

    public QTourList(Path<? extends TourList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTourList(PathMetadata metadata) {
        super(TourList.class, metadata);
    }

}

