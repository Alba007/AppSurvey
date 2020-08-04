package com.example.demo.data_tables.repository;

import com.example.demo.data_tables.mapping.DataTablesInput;
import com.example.demo.data_tables.mapping.DataTablesOutput;

import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.Collection;
import java.util.function.Function;

@NoRepositoryBean
public interface DataTablesRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

    /**
     * Returns the filtered list for the given {@link DataTablesInput}.
     *
     * @param input the {@link DataTablesInput} mapped from the Ajax request
     * @return a {@link DataTablesOutput}
     */
    DataTablesOutput<T> findAll(DataTablesInput input);

    DataTablesOutput findAll(DataTablesInput input, Function<? super T, ?> mapper);

    public DataTablesOutput findAll(DataTablesInput input, Criteria additionalCrit, Criteria preFilteringCrit, String hint, Function<? super T, ?> mapper);

    DataTablesOutput<T> findAllNoPaginate(DataTablesInput input);

    DataTablesOutput findAllNoPaginate(DataTablesInput input, Function<? super T, ?> mapper);
    /**
     * Returns the filtered list for the given {@link DataTablesInput}.
     *
     * @param input the {@link DataTablesInput} mapped from the Ajax request
     * @return a {@link DataTablesOutput}
     */
    DataTablesOutput<T> findAll(DataTablesInput input, String hint);

    /**
     * Returns the filtered list for the given {@link DataTablesInput}.
     *
     * @param input the {@link DataTablesInput} mapped from the Ajax request
     * @param additionalCriteria an additional {@link Criteria} to apply to the query
     *            (with an "AND" clause)
     * @return a {@link DataTablesOutput}
     */
    DataTablesOutput<T> findAll(DataTablesInput input, Criteria additionalCriteria);

    /**
     * Returns the filtered list for the given {@link DataTablesInput}.
     *
     * @param input the {@link DataTablesInput} mapped from the Ajax request
     * @param additionalCriteria an additional {@link Criteria} to apply to the query
     *            (with an "AND" clause)
     * @param  hint which mongoIndex to use as a String
     * @return a {@link DataTablesOutput}
     */
    DataTablesOutput<T> findAll(DataTablesInput input, Criteria additionalCriteria, String hint);

    /**
     * Returns the filtered list for the given {@link DataTablesInput}.
     *
     * @param input the {@link DataTablesInput} mapped from the Ajax request
     * @param additionalCriteria an additional {@link Criteria} to apply to the query
     *            (with an "AND" clause)
     * @param preFilteringCriteria a pre-filtering {@link Criteria} to apply to the query
     *            (with an "AND" clause)
     * @return a {@link DataTablesOutput}
     */
    DataTablesOutput<T> findAll(DataTablesInput input, Criteria additionalCriteria, Criteria preFilteringCriteria, String hint);

    /**
     * Returns the filtered list for the given {@link DataTablesInput} using the given {@link TypedAggregation}
     *
     * @param classOfView
     * @param input
     * @param operations
     * @return
     */
    <View> DataTablesOutput<View> findAll(Class<View> classOfView, DataTablesInput input,
                                          AggregationOperation... operations);

    /**
     * Returns the filtered list for the given {@link DataTablesInput} using the given {@link TypedAggregation}
     *
     * @param classOfView
     * @param input
     * @param operations
     * @return
     */
    <View> DataTablesOutput<View> findAll(Class<View> classOfView, DataTablesInput input,
                                          Collection<? extends AggregationOperation> operations);

}
