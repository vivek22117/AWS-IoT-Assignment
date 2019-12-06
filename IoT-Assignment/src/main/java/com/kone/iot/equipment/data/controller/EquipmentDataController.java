package com.kone.iot.equipment.data.controller;

import com.kone.iot.equipment.data.domain.EquipmentRecord;
import com.kone.iot.equipment.data.exception.ApplicationException;
import com.kone.iot.equipment.data.service.EquipmentService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.lang.Integer.valueOf;

@RestController
@RequestMapping(path = "/equipment")
public class EquipmentDataController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EquipmentDataController.class);

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentDataController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @ApiOperation(value = "Get list of equipments based on count ", response = EquipmentRecord.class)
    @GetMapping(value = "/search", produces = "application/json")
    public ResponseEntity<?> getEquipmentData(@RequestParam(value = "limit") Integer limit) {
        if (limit <= 0) {
            return new ResponseEntity<>("Limit should be greater than 0", HttpStatus.BAD_REQUEST);
        }

        List<EquipmentRecord> records = equipmentService.getEquipmentByCount(limit);

        if (records.isEmpty()) {
            return new ResponseEntity<>("No records available", HttpStatus.OK);
        }
        return ResponseEntity.ok(records);
    }

    @ApiOperation(value = "Get equipment details based on equipmentNumber ", response = EquipmentRecord.class)
    @GetMapping(value = "/{equipmentNumber}", produces = "application/json")
    public ResponseEntity<?> getEquipmentById(@PathVariable final String equipmentNumber) {
        Optional<EquipmentRecord> record = equipmentService.getByEquipmentId(valueOf(equipmentNumber));

        if (!record.isPresent()) {
            return new ResponseEntity<>("No equipment available with id: " + equipmentNumber, HttpStatus.OK);
        }

        return ResponseEntity.ok(record.get());
    }

    @ApiOperation(value = "Creates a new equipment")
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createEquipment(@RequestBody EquipmentRecord record) {
        try {
            boolean saveStatus = equipmentService.save(record);
            if (saveStatus) {
                return new ResponseEntity<>("Successfully save equipment with Id " + record.getEquipmentId(), HttpStatus.CREATED);
            }
        } catch (ApplicationException ex) {
            LOGGER.error(ex.getMessage());
        }
        return new ResponseEntity<>("{ Equipment with Id " + record.getEquipmentId() + " already exist! }", HttpStatus.CONFLICT);
    }
}
