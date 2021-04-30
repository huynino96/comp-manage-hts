package com.example.compmanage.controller;

import com.example.compmanage.exception.ResourceNotFoundException;
import com.example.compmanage.models.Cpu;
import com.example.compmanage.models.Cpu;
import com.example.compmanage.models.Computer;
import com.example.compmanage.repository.ComputerRepository;
import com.example.compmanage.repository.CpuRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Cpu> getCpu(){
        return cpuRepository.findAll();
    }

    @GetMapping("/cpus/{id}")
    public ResponseEntity< Cpu > getCpuById(
            @PathVariable(value = "id") Long cpuId) throws ResourceNotFoundException {
        Cpu cpu = cpuRepository.findById(cpuId)
                .orElseThrow(() -> new ResourceNotFoundException("CPU not found :: " + cpuId));
        return ResponseEntity.ok().body(cpu);
    }
    
    @PostMapping(value = "/cpus", consumes = {"application/json"})
    public Cpu createCpu(@Valid @RequestBody Cpu cpu) {
        return cpuRepository.save(cpu);
    }
}
