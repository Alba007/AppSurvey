package com.example.demo.data_tables.repository;



import com.example.demo.data_tables.mapping.DataTablesInput;
import com.example.demo.data_tables.mapping.DataTablesOutput;
import com.example.demo.data_tables.model.DataTablesCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Query.query;

public class DataTablesRepositoryImpl<T, ID extends Serializable> extends SimpleMongoRepository<T, ID>
        implements DataTablesRepository<T, ID> {

    private static final Logger log = LoggerFactory.getLogger(DataTablesRepositoryImpl.class);

    private final MongoEntityInformation<T, ID> entityInformation;
    private final MongoOperations mongoOperations;

    public DataTablesRepositoryImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
        this.entityInformation = metadata;
        this.mongoOperations = mongoOperations;
    }

    @Override
    public DataTablesOutput<T> findAll(DataTablesInput input) {
        return findAll(input, null, null,null);
    }

    @Override
    public DataTablesOutput findAll(DataTablesInput input,Function<? super T, ?> mapper) {
        return findAll(input, null, null,null,mapper);
    }

    @Override
    public DataTablesOutput findAllNoPaginate(DataTablesInput input,Function<? super T, ?> mapper) {
        DataTablesOutput output = new DataTablesOutput<T>();
        output.setDraw(input.getDraw());

        try {
            Query query = DataTablesUtils.getQuery(this.entityInformation.getCollectionName(), input);
            List data = mongoOperations.find(query, this.entityInformation.getJavaType(), this.entityInformation.getCollectionName()).stream().map(mapper).collect(Collectors.toList());
            output.setData(data);
            output.setRecordsFiltered(data.size());
        } catch (Exception e) {
            output.setError(e.toString());
            output.setRecordsFiltered(0L);
            log.error("caught exception", e);
        }

        return output;
    }


    @Override
    public DataTablesOutput<T> findAllNoPaginate(DataTablesInput input) {
        DataTablesOutput<T> output = new DataTablesOutput<T>();
        output.setDraw(input.getDraw());

        try {
            Query query = DataTablesUtils.getQuery(this.entityInformation.getCollectionName(), input);

            List<T> data = mongoOperations.find(query, this.entityInformation.getJavaType(), this.entityInformation.getCollectionName());
            output.setData(data);
            output.setRecordsFiltered(data.size());
        } catch (Exception e) {
            output.setError(e.toString());
            output.setRecordsFiltered(0L);
            log.error("caught exception", e);
        }

        return output;
    }

    @Override
    public DataTablesOutput<T> findAll(DataTablesInput input, String hint) {
        return findAll(input, null, null,hint);
    }

    @Override
    public DataTablesOutput<T> findAll(DataTablesInput input, Criteria additionalCriteria) {
        return findAll(input, additionalCriteria, null,null);
    }

    @Override
    public DataTablesOutput<T> findAll(DataTablesInput input, Criteria additionalCriteria, String hint) {
        return findAll(input, additionalCriteria, null,hint);
    }

    private long count(Criteria crit) {
        Query q = query(crit);
        return this.mongoOperations.count(q, this.entityInformation.getCollectionName());
    }

    private <S extends T> Page<S> findAll(Query q, Pageable p, Class<S> classOfS) {

        long count = mongoOperations.count(q, this.entityInformation.getCollectionName());
        q.with(p);
        if (count == 0) {
            return new PageImpl<S>(Collections.<S> emptyList());
        }

        return new PageImpl<S>(mongoOperations.find(q, classOfS, this.entityInformation.getCollectionName()), p, count);
    }

    @Override
    public DataTablesOutput findAll(DataTablesInput input, Criteria additionalCrit, Criteria preFilteringCrit, String hint, Function<? super T, ?> mapper) {
        DataTablesOutput output = new DataTablesOutput<T>();
        output.setDraw(input.getDraw());

        try {
            Query query = DataTablesUtils.getQuery(this.entityInformation.getCollectionName(), input);

            if(hint !=null){
                query.withHint(hint);
            }

            if (additionalCrit != null) {
                query.addCriteria(additionalCrit);
            }

            if (preFilteringCrit != null) {
                query.addCriteria(preFilteringCrit);
            }

            Pageable pageable = DataTablesUtils.getPageable(input);

            Page<T> data = findAll(query, pageable, this.entityInformation.getJavaType());

            output.setData(data.getContent().stream().map(mapper).collect(Collectors.toList()));
            output.setRecordsTotal(data.getTotalElements());
            output.setRecordsFiltered(data.getTotalElements());
        } catch (Exception e) {
            output.setError(e.toString());
            output.setRecordsFiltered(0L);
            log.error("caught exception", e);
        }

        return output;
    }

    @Override
    public DataTablesOutput<T> findAll(DataTablesInput input, Criteria additionalCrit, Criteria preFilteringCrit, String hint) {
        DataTablesOutput<T> output = new DataTablesOutput<T>();
        output.setDraw(input.getDraw());

        try {
            Query query = DataTablesUtils.getQuery(this.entityInformation.getCollectionName(), input);

            if(hint !=null){
                query.withHint(hint);
            }

            if (additionalCrit != null) {
                query.addCriteria(additionalCrit);
            }

            if (preFilteringCrit != null) {
                query.addCriteria(preFilteringCrit);
            }

            Pageable pageable = DataTablesUtils.getPageable(input);

            Page<T> data = findAll(query, pageable, this.entityInformation.getJavaType());
            output.setData(data.getContent());
            output.setRecordsFiltered(data.getTotalElements());
        } catch (Exception e) {
            output.setError(e.toString());
            output.setRecordsFiltered(0L);
            log.error("caught exception", e);
        }

        return output;
    }

    @Override
    public <View> DataTablesOutput<View> findAll(Class<View> classOfView, DataTablesInput input,
                                                 AggregationOperation... operations) {
        DataTablesOutput<View> output = new DataTablesOutput<View>();
        output.setDraw(input.getDraw());

        try {
            // TODO here count() may not be accurate because Aggregation is not simply a filter
            long recordsTotal = count();
            if (recordsTotal == 0) {
                return output;
            }
            output.setRecordsTotal(recordsTotal);

            final Class<T> classOfT = this.entityInformation.getJavaType();

            Page<View> data = findPage(classOfT, classOfView, input, operations);

            output.setData(data.getContent());
            output.setRecordsFiltered(data.getTotalElements());

        } catch (Exception e) {
            output.setError(e.toString());
            output.setRecordsFiltered(0L);
            log.error("caught exception", e);
        }

        return output;
    }

    @Override
    public <View> DataTablesOutput<View> findAll(Class<View> classOfView, DataTablesInput input,
                                                 Collection<? extends AggregationOperation> operations) {
        AggregationOperation[] opArray = operations.toArray(new AggregationOperation[0]);
        return findAll(classOfView, input, opArray);
    }

    private <View> Page<View> findPage(Class<T> classOfT, Class<View> classOfView, DataTablesInput input,
                                       AggregationOperation... operations) {
        final Pageable pageable = DataTablesUtils.getPageable(input);

        final TypedAggregation<View> aggWithPage = DataTablesUtils.makeAggregation(classOfView, input, pageable,
                operations);

        final TypedAggregation<DataTablesCount> aggCount = DataTablesUtils.makeAggregationCountOnly(input, operations);
        long count = 0L;
        AggregationResults<DataTablesCount> countResult = mongoOperations.aggregate(aggCount, classOfT,
                DataTablesCount.class);

        if (countResult != null) {
            count = countResult.getUniqueMappedResult().getCount();
        }

        if (count == 0) {
            return new PageImpl<View>(Collections.<View> emptyList());
        }

        List<View> result = null;
        AggregationResults<View> aggResult = mongoOperations.aggregate(aggWithPage, classOfT, classOfView);
        if (aggResult != null) {
            result = aggResult.getMappedResults();
        }

        return new PageImpl<View>(result, pageable, count);
    }

}
