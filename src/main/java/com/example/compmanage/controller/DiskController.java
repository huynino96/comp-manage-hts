package com.example.compmanage.controller;


import com.example.compmanage.models.Disk;
import com.example.compmanage.repository.ComputerRepository;
import com.example.compmanage.repository.DiskRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DiskController {

    @Autowired
    private ComputerRepository computerRepository;

    @Autowired
    private DiskRepository diskRepository;

    @GetMapping("/disks")
    public List getAllDisks(){
        return diskRepository.findAll();
    }

    @GetMapping("/computers/{computerId}/disks")
    public Disk getDiskByComputerId(@PathVariable Long computerId) throws NotFoundException {

        if(!computerRepository.existsById(computerId)) {
            throw new NotFoundException("Computer not found!");
        }

        List disks = diskRepository.findByComputerId(computerId);
        if(disks.size() > 0) {
            return (Disk) disks.get(0);
        }else {
            throw new NotFoundException("Disk not found!");
        }
    }

    @PostMapping("/computers/{computerId}/disks")
    public Disk addDisk(@PathVariable Long computerId,
                       @Valid @RequestBody Disk disk) throws NotFoundException {
        return computerRepository.findById(computerId)
                .map(computer -> {
                    disk.setComputer(computer);
                    return diskRepository.save(disk);
                }).orElseThrow(() -> new NotFoundException("Computer not found!"));
    }

    @PutMapping("/disks/{diskId}")
    public Disk updateDisk(@PathVariable Long diskId,
                         @Valid @RequestBody Disk diskUpdated) throws NotFoundException {
        return diskRepository.findById(diskId)
                .map(disk -> {
                    disk.setDiskBrand(diskUpdated.getDiskBrand());
                    disk.setDiskType(diskUpdated.getDiskType());
                    disk.setDiskSize(diskUpdated.getDiskSize());
                    return diskRepository.save(disk);
                }).orElseThrow(() -> new NotFoundException("CPU not found!"));
    }

    @DeleteMapping("/disks/{diskId}")
    public String deleteDisk(@PathVariable Long diskId) throws NotFoundException {
        return diskRepository.findById(diskId)
                .map(disk -> {
                    diskRepository.delete(disk);
                    return "Disk Deleted Successfully!";
                }).orElseThrow(() -> new NotFoundException("Disk not found!"));
    }
}
