package com.example.compmanage.controller;

import com.example.compmanage.models.Keyboard;
import com.example.compmanage.repository.ComputerRepository;
import com.example.compmanage.repository.KeyboardRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class KeyboardController {
    @Autowired
    private KeyboardRepository keyboardRepository;
    
    @Autowired
    private ComputerRepository computerRepository;

    @GetMapping("/keyboards")
    public List getAllKeyboards(){
        return keyboardRepository.findAll();
    }

    @GetMapping("/computers/{computerId}/keyboards")
    public Keyboard getKeyboardByComputerId(@PathVariable Long computerId) throws NotFoundException {
        if(!computerRepository.existsById(computerId)) {
            throw new NotFoundException("Computer not found!");
        }
        List keyboards = keyboardRepository.findByComputerId(computerId);
        if(keyboards.size() > 0) {
            return (Keyboard) keyboards.get(0);
        }else {
            throw new NotFoundException("Keyboard not found!");
        }
    }

    @PostMapping("/computers/{computerId}/keyboards")
    public Keyboard addKeyboard(@PathVariable Long computerId,
                      @Valid @RequestBody Keyboard keyboard) throws NotFoundException {
        return computerRepository.findById(computerId)
                .map(computer -> {
                    keyboard.setComputer(computer);
                    return keyboardRepository.save(keyboard);
                }).orElseThrow(() -> new NotFoundException("Computer not found!"));
    }

    @PutMapping("/keyboards/{keyboardId}")
    public Keyboard updateKeyboard(@PathVariable Long keyboardId,
                         @Valid @RequestBody Keyboard keyboardUpdated) throws NotFoundException {
        return keyboardRepository.findById(keyboardId)
                .map(keyboard -> {
                    keyboard.setKeyboardName(keyboardUpdated.getKeyboardName());
                    keyboard.setKeyboardProduct(keyboardUpdated.getKeyboardProduct());
                    return keyboardRepository.save(keyboard);
                }).orElseThrow(() -> new NotFoundException("Keyboard not found!"));
    }

    @DeleteMapping("/keyboards/{keyboardId}")
    public String deleteKeyboard(@PathVariable Long keyboardId) throws NotFoundException {
        return keyboardRepository.findById(keyboardId)
                .map(keyboard -> {
                    keyboardRepository.delete(keyboard);
                    return "Keyboard Deleted Successfully!";
                }).orElseThrow(() -> new NotFoundException("Keyboard not found!"));
    }
}
