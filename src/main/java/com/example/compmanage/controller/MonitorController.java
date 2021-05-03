package com.example.compmanage.controller;

import com.example.compmanage.models.Monitor;
import com.example.compmanage.repository.ComputerRepository;
import com.example.compmanage.repository.MonitorRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MonitorController {
    @Autowired
    private ComputerRepository computerRepository;
    
    @Autowired
    private MonitorRepository monitorRepository;

    @GetMapping("/monitors")
    public List getAllMonitors(){
        return monitorRepository.findAll();
    }

    @GetMapping("/computers/{computerId}/monitors")
    public Monitor getMonitorByComputerId(@PathVariable Long computerId) throws NotFoundException {
        if(!computerRepository.existsById(computerId)) {
            throw new NotFoundException("Computer not found!");
        }
        List monitors = monitorRepository.findByComputerId(computerId);
        if(monitors.size() > 0) {
            return (Monitor) monitors.get(0);
        }else {
            throw new NotFoundException("Monitor not found!");
        }
    }

    @PostMapping("/computers/{computerId}/monitors")
    public Monitor addMonitor(@PathVariable Long computerId,
                                @Valid @RequestBody Monitor monitor) throws NotFoundException {
        return computerRepository.findById(computerId)
                .map(computer -> {
                    monitor.setComputer(computer);
                    return monitorRepository.save(monitor);
                }).orElseThrow(() -> new NotFoundException("Computer not found!"));
    }

    @PutMapping("/monitors/{monitorId}")
    public Monitor updateMonitor(@PathVariable Long monitorId,
                                   @Valid @RequestBody Monitor monitorUpdated) throws NotFoundException {
        return monitorRepository.findById(monitorId)
                .map(monitor -> {
                    monitor.setMonitorBrand(monitorUpdated.getMonitorBrand());
                    monitor.setMonitorSize(monitorUpdated.getMonitorSize());
                    return monitorRepository.save(monitor);
                }).orElseThrow(() -> new NotFoundException("Monitor not found!"));
    }

    @DeleteMapping("/monitors/{monitorId}")
    public String deleteMonitor(@PathVariable Long monitorId) throws NotFoundException {
        return monitorRepository.findById(monitorId)
                .map(monitor -> {
                    monitorRepository.delete(monitor);
                    return "Monitor Deleted Successfully!";
                }).orElseThrow(() -> new NotFoundException("Monitor not found!"));
    }
}
