package com.example.compmanage.controller;

import com.example.compmanage.exception.ResourceNotFoundException;
import com.example.compmanage.models.Cpu;
import com.example.compmanage.models.Cpu;
import com.example.compmanage.models.Computer;
import com.example.compmanage.repository.ComputerRepository;
import com.example.compmanage.repository.CpuRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CpuController {
    @Autowired
    private ComputerRepository computerRepository;

    @Autowired
    private CpuRepository cpuRepository;

    @GetMapping("/cpus")
    public List getAllCpus(){
        return cpuRepository.findAll();
    }

    @GetMapping("/computers/{computerId}/cpus")
    public Cpu getCpuByComputerId(@PathVariable Long computerId) throws NotFoundException {
        if(!computerRepository.existsById(computerId)) {
            throw new NotFoundException("Computer not found!");
        }

        List cpus = cpuRepository.findByComputerId(computerId);
        if(cpus.size() > 0) {
            return (Cpu) cpus.get(0);
        }else {
            throw new NotFoundException("Cpu not found!");
        }
    }

    @PostMapping("/computers/{computerId}/cpus")
    public Cpu addCpu(@PathVariable Long computerId,
                              @Valid @RequestBody Cpu cpu) throws NotFoundException {
        return computerRepository.findById(computerId)
                .map(computer -> {
                    cpu.setComputer(computer);
                    return cpuRepository.save(cpu);
                }).orElseThrow(() -> new NotFoundException("Computer not found!"));
    }

    @PutMapping("/cpus/{cpuId}")
    public Cpu updateCpu(@PathVariable Long cpuId,
                                 @Valid @RequestBody Cpu cpuUpdated) throws NotFoundException {
        return cpuRepository.findById(cpuId)
                .map(cpu -> {
                    cpu.setCpuType(cpuUpdated.getCpuType());
                    cpu.setCoreType(cpuUpdated.getCoreType());
                    cpu.setCpuGen(cpuUpdated.getCpuGen());
                    cpu.setCpuSpeed(cpuUpdated.getCpuSpeed());
                    return cpuRepository.save(cpu);
                }).orElseThrow(() -> new NotFoundException("CPU not found!"));
    }

    @DeleteMapping("/cpus/{cpuId}")
    public String deleteCpu(@PathVariable Long cpuId) throws NotFoundException {
        return cpuRepository.findById(cpuId)
                .map(cpu -> {
                    cpuRepository.delete(cpu);
                    return "CPU Deleted Successfully!";
                }).orElseThrow(() -> new NotFoundException("CPU not found!"));
    }
}
