package com.example.compmanage.controller;

import com.example.compmanage.models.Mouse;
import com.example.compmanage.repository.ComputerRepository;
import com.example.compmanage.repository.MouseRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MouseController {
    @Autowired
    private ComputerRepository computerRepository;
    
    @Autowired
    private MouseRepository mouseRepository;

    @GetMapping("/mouses")
    public List getAllMouses(){
        return mouseRepository.findAll();
    }

    @GetMapping("/computers/{computerId}/mouses")
    public Mouse getMouseByComputerId(@PathVariable Long computerId) throws NotFoundException {
        if(!computerRepository.existsById(computerId)) {
            throw new NotFoundException("Computer not found!");
        }
        List mouses = mouseRepository.findByComputerId(computerId);
        if(mouses.size() > 0) {
            return (Mouse) mouses.get(0);
        }else {
            throw new NotFoundException("Mouse not found!");
        }
    }

    @PostMapping("/computers/{computerId}/mouses")
    public Mouse addMouse(@PathVariable Long computerId,
                                @Valid @RequestBody Mouse mouse) throws NotFoundException {
        return computerRepository.findById(computerId)
                .map(computer -> {
                    mouse.setComputer(computer);
                    return mouseRepository.save(mouse);
                }).orElseThrow(() -> new NotFoundException("Computer not found!"));
    }

    @PutMapping("/mouses/{mouseId}")
    public Mouse updateMouse(@PathVariable Long mouseId,
                                   @Valid @RequestBody Mouse mouseUpdated) throws NotFoundException {
        return mouseRepository.findById(mouseId)
                .map(mouse -> {
                    mouse.setMouseName(mouseUpdated.getMouseName());
                    mouse.setMouseType(mouseUpdated.getMouseType());
                    return mouseRepository.save(mouse);
                }).orElseThrow(() -> new NotFoundException("Mouse not found!"));
    }

    @DeleteMapping("/mouses/{mouseId}")
    public String deleteMouse(@PathVariable Long mouseId) throws NotFoundException {
        return mouseRepository.findById(mouseId)
                .map(mouse -> {
                    mouseRepository.delete(mouse);
                    return "Mouse Deleted Successfully!";
                }).orElseThrow(() -> new NotFoundException("Mouse not found!"));
    }
}
