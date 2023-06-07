package com.example.yj.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMySchedule is a Querydsl query type for MySchedule
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMySchedule extends EntityPathBase<MySchedule> {

    private static final long serialVersionUID = 589856034L;

    public static final QMySchedule mySchedule = new QMySchedule("mySchedule");

    public final DatePath<java.sql.Date> date = createDate("date", java.sql.Date.class);

    public final StringPath imgSrc = createString("imgSrc");

    public final NumberPath<Long> scheduleId = createNumber("scheduleId", Long.class);

    public final StringPath spot = createString("spot");

    public final StringPath userId = createString("userId");

    public final StringPath visitSpot = createString("visitSpot");

    public QMySchedule(String variable) {
        super(MySchedule.class, forVariable(variable));
    }

    public QMySchedule(Path<? extends MySchedule> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMySchedule(PathMetadata metadata) {
        super(MySchedule.class, metadata);
    }

}

