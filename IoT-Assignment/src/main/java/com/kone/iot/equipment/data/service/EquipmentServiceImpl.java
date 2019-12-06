package com.kone.iot.equipment.data.service;

import com.kone.iot.equipment.data.db.DynamoDBOperation;
import com.kone.iot.equipment.data.domain.EquipmentRecord;
import com.kone.iot.equipment.data.exception.ApplicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EquipmentServiceImpl.class);

    private DynamoDBOperation dynamoDBOperation;

    @Autowired
    public EquipmentServiceImpl(DynamoDBOperation dynamoDBOperation) {
        this.dynamoDBOperation = dynamoDBOperation;
    }

    @Override
    public List<EquipmentRecord> getEquipmentByCount(Integer count) {
        return dynamoDBOperation.getRecordsBasedOnCount(count);
    }

    @Override
    public boolean save(EquipmentRecord record) {
        EquipmentRecord duplicateRecord =
                dynamoDBOperation.getRecordBasedOnEquipmentId(record.getEquipmentId());
        if (!Objects.isNull(duplicateRecord)) {
            throw new ApplicationException("Equipment with Id " + record.getEquipmentId() + "" +
                    " is already available");
        }
        return dynamoDBOperation.save(record);
    }

    @Override
    public void saveDummyData(EquipmentRecord record) {
        dynamoDBOperation.save(record);
    }

    @Override
    public Optional<EquipmentRecord> getByEquipmentId(Integer id) {
        EquipmentRecord record = dynamoDBOperation.getRecordBasedOnEquipmentId(id);
        if (Objects.isNull(record)) {
            return Optional.empty();
        }
        return Optional.of(record);
    }
}
