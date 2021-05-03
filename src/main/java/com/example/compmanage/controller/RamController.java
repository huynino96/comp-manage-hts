package com.example.compmanage.controller;

import com.example.compmanage.models.Ram;
import com.example.compmanage.repository.ComputerRepository;
import com.example.compmanage.repository.RamRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RamController {
    @Autowired
    private ComputerRepository computerRepository;
    
    @Autowired
    private RamRepository ramRepository;
    @GetMapping("/rams")
    public List getAllRams(){
        return ramRepository.findAll();
    }

    @GetMapping("/computers/{computerId}/rams")
    public Ram getRamByComputerId(@PathVariable Long computerId) throws NotFoundException {
        if(!computerRepository.existsById(computerId)) {
            throw new NotFoundException("Computer not found!");
        }
        List rams = ramRepository.findByComputerId(computerId);
        if(rams.size() > 0) {
            return (Ram) rams.get(0);
        }else {
            throw new NotFoundException("Ram not found!");
        }
    }

    @PostMapping("/computers/{computerId}/rams")
    public Ram addRam(@PathVariable Long computerId,
                                @Valid @RequestBody Ram ram) throws NotFoundException {
        return computerRepository.findById(computerId)
                .map(computer -> {
                    ram.setComputer(computer);
                    return ramRepository.save(ram);
                }).orElseThrow(() -> new NotFoundException("Computer not found!"));
    }

    @PutMapping("/rams/{ramId}")
    public Ram updateRam(@PathVariable Long ramId,
                                   @Valid @RequestBody Ram ramUpdated) throws NotFoundException {
        return ramRepository.findById(ramId)
                .map(ram -> {
                    ram.setRamBrand(ramUpdated.getRamBrand());
                    ram.setRamBus(ramUpdated.getRamBus());
                    ram.setRamSize(ramUpdated.getRamSize());
                    ram.setRamType(ramUpdated.getRamType());
                    ram.setRamQuantity(ramUpdated.getRamQuantity());
                    return ramRepository.save(ram);
                }).orElseThrow(() -> new NotFoundException("RAM not found!"));
    }

    @DeleteMapping("/rams/{ramId}")
    public String deleteRam(@PathVariable Long ramId) throws NotFoundException {
        return ramRepository.findById(ramId)
                .map(ram -> {
                    ramRepository.delete(ram);
                    return "RAM Deleted Successfully!";
                }).orElseThrow(() -> new NotFoundException("RAM not found!"));
    }
    
}
