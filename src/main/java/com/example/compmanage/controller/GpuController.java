package com.example.compmanage.controller;


import com.example.compmanage.models.Gpu;
import com.example.compmanage.repository.ComputerRepository;
import com.example.compmanage.repository.GpuRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GpuController {
    @Autowired
    private ComputerRepository computerRepository;
    
    @Autowired
    private GpuRepository gpuRepository;

    @GetMapping("/gpus")
    public List getAllGpus(){
        return gpuRepository.findAll();
    }

    @GetMapping("/computers/{computerId}/gpus")
    public Gpu getGpuByComputerId(@PathVariable Long computerId) throws NotFoundException {
        if(!computerRepository.existsById(computerId)) {
            throw new NotFoundException("Computer not found!");
        }
        List gpus = gpuRepository.findByComputerId(computerId);
        if(gpus.size() > 0) {
            return (Gpu) gpus.get(0);
        }else {
            throw new NotFoundException("Gpu not found!");
        }
    }

    @PostMapping("/computers/{computerId}/gpus")
    public Gpu addGpu(@PathVariable Long computerId,
                      @Valid @RequestBody Gpu gpu) throws NotFoundException {
        return computerRepository.findById(computerId)
                .map(computer -> {
                    gpu.setComputer(computer);
                    return gpuRepository.save(gpu);
                }).orElseThrow(() -> new NotFoundException("Computer not found!"));
    }

    @PutMapping("/gpus/{gpuId}")
    public Gpu updateGpu(@PathVariable Long gpuId,
                         @Valid @RequestBody Gpu gpuUpdated) throws NotFoundException {
        return gpuRepository.findById(gpuId)
                .map(gpu -> {
                    gpu.setGpuType(gpuUpdated.getGpuType());
                    gpu.setGpuName(gpuUpdated.getGpuName());
                    gpu.setGpuBrand(gpuUpdated.getGpuBrand());
                    gpu.setGpuSize(gpuUpdated.getGpuSize());
                    return gpuRepository.save(gpu);
                }).orElseThrow(() -> new NotFoundException("CPU not found!"));
    }

    @DeleteMapping("/gpus/{gpuId}")
    public String deleteGpu(@PathVariable Long gpuId) throws NotFoundException {
        return gpuRepository.findById(gpuId)
                .map(gpu -> {
                    gpuRepository.delete(gpu);
                    return "GPU Deleted Successfully!";
                }).orElseThrow(() -> new NotFoundException("GPU not found!"));
    }
}
