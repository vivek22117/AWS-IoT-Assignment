package com.kone.iot.equipment.data.service;

import com.kone.iot.equipment.data.domain.EquipmentRecord;

import java.util.List;
import java.util.Optional;

public interface EquipmentService {

    List<EquipmentRecord> getEquipmentByCount(Integer count);

    boolean save(EquipmentRecord record);

    void saveDummyData(EquipmentRecord record);

    Optional<EquipmentRecord> getByEquipmentId(Integer id);
}
