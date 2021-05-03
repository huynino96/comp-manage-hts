package com.example.compmanage.controller;

import com.example.compmanage.models.Mainboard;
import com.example.compmanage.repository.ComputerRepository;
import com.example.compmanage.repository.MainboardRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainboardController {
    @Autowired
    private ComputerRepository computerRepository;
    
    @Autowired
    private MainboardRepository mainboardRepository;

    @GetMapping("/mainboards")
    public List getAllMainboards(){
        return mainboardRepository.findAll();
    }

    @GetMapping("/computers/{computerId}/mainboards")
    public Mainboard getMainboardByComputerId(@PathVariable Long computerId) throws NotFoundException {
        if(!computerRepository.existsById(computerId)) {
            throw new NotFoundException("Computer not found!");
        }
        List mainboards = mainboardRepository.findByComputerId(computerId);
        if(mainboards.size() > 0) {
            return (Mainboard) mainboards.get(0);
        }else {
            throw new NotFoundException("Mainboard not found!");
        }
    }

    @PostMapping("/computers/{computerId}/mainboards")
    public Mainboard addMainboard(@PathVariable Long computerId,
                                @Valid @RequestBody Mainboard mainboard) throws NotFoundException {
        return computerRepository.findById(computerId)
                .map(computer -> {
                    mainboard.setComputer(computer);
                    return mainboardRepository.save(mainboard);
                }).orElseThrow(() -> new NotFoundException("Computer not found!"));
    }

    @PutMapping("/mainboards/{mainboardId}")
    public Mainboard updateMainboard(@PathVariable Long mainboardId,
                                   @Valid @RequestBody Mainboard mainboardUpdated) throws NotFoundException {
        return mainboardRepository.findById(mainboardId)
                .map(mainboard -> {
                    mainboard.setMainboardName(mainboardUpdated.getMainboardName());
                    mainboard.setMainboardBrand(mainboardUpdated.getMainboardBrand());
                    mainboard.setMainboardType(mainboardUpdated.getMainboardType());
                    return mainboardRepository.save(mainboard);
                }).orElseThrow(() -> new NotFoundException("Mainboard not found!"));
    }

    @DeleteMapping("/mainboards/{mainboardId}")
    public String deleteMainboard(@PathVariable Long mainboardId) throws NotFoundException {
        return mainboardRepository.findById(mainboardId)
                .map(mainboard -> {
                    mainboardRepository.delete(mainboard);
                    return "Mainboard Deleted Successfully!";
                }).orElseThrow(() -> new NotFoundException("Mainboard not found!"));
    }
}
