package com.consumer.consumer.dao;

import com.consumer.consumer.model.ProcessedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Repository
public class ProcessedDataDaoImpl extends JdbcDaoSupport implements ProcessedDataDao{
    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;

    @PostConstruct
    private void initialize() { setDataSource(dataSource); }

    @Override
    public void insertRecordToProcessedData(ProcessedData processedData ){
        String sql = "INSERT INTO ProcessedData " +
                "(id,data) VALUES (?,?)";

        getJdbcTemplate().update(sql,new Object[]{
                processedData.getId(),processedData.getData()
        });
    }

}
