package com.consumer.consumer.dao;

import com.consumer.consumer.model.RawData;
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


// update records with Processed = 'Y' back into RawData table after they have been processed successfully
    @Override
    public void updateRecordInRawData(RawData rawData) throws SQLException {
        try {
            String sql = "UPDATE RawData SET Processed = 'Y' WHERE Id = ? ";
            getJdbcTemplate().update(sql,rawData.getId());
            //getJdbcTemplate().getDataSource().getConnection().close();
        }
        catch (Exception e){
            throw e;
        }
    }

    @Override
   public List<RawData> getRecordsFromRawData(int noofRecords){
        return null;
    }


    @Override
    public List<RawData> getUnprocessedRecordsFromRawData(int noOfRecords){
     //  System.out.println("Inside getUnprocessedRecordsFromRawData RAWDATADAOIMPL");
       try{
           String sql = "SELECT * FROM RawData WHERE PROCESSED='N' LIMIT " + noOfRecords;

           List<Map <String,Object>> records = getJdbcTemplate().queryForList(sql);

           //getJdbcTemplate().getDataSource().getConnection().close();

           List<RawData> rawDataList = new ArrayList<RawData>();

           for(Map<String,Object> record : records) {
               RawData rawData = new RawData();
               rawData.setId((int) record.get("Id"));
               rawData.setData((String) record.get("Data"));
               rawData.setTarget((String) record.get("Target"));
               rawData.setProcessed((String) record.get("Processed"));
               rawDataList.add(rawData);
           }
           return rawDataList;
       } catch (Exception e) {
           System.out.println("Exception while fetching from DB");
       }
       return null;
    }

    @Override
    public RawData getRecordById(int id){
        String sql = "SELECT * FROM RawData WHERE Id = ?";
        return getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<RawData>() {
            @Override
            public RawData mapRow(ResultSet resultSet, int i) throws SQLException {
                RawData rawData = new RawData();
                rawData.setId(resultSet.getInt("Id"));
                rawData.setData(resultSet.getString("Data"));
                rawData.setTarget(resultSet.getString("Target"));
                rawData.setProcessed(resultSet.getString("Processed"));
                return rawData;
            }
        });

    }

}
