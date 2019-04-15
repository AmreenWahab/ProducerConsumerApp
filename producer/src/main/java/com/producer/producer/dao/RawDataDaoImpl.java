package com.producer.producer.dao;

import com.producer.producer.model.RawData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class RawDataDaoImpl extends JdbcDaoSupport implements RawDataDao  {

    @Autowired
    @Qualifier ("dataSource")
    DataSource dataSource;

    @PostConstruct
    private void initialize() { setDataSource(dataSource); }


    @Override
    public void insertBulkRecordsToRawData(List<RawData> rawDataList) {

      //  System.out.println("Start BulkInsert");

        String sql = "INSERT INTO RawData " +
                "(id,target,data) VALUES (?,?,?)";

        getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                RawData rawData = rawDataList.get(i);
                preparedStatement.setInt(1,rawData.getId());
                preparedStatement.setString(2,rawData.getTarget());
                preparedStatement.setString(3,rawData.getData());

            }

            @Override
            public  int getBatchSize() {return rawDataList.size(); }
        });

      //  System.out.println("End BulkInsert");
    }


}
