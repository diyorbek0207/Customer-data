package dasturlash.uz.controller;

import dasturlash.uz.dto.CustomerDTO;
import dasturlash.uz.entity.CustomerEntity;
import dasturlash.uz.service.CustomerService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("")
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.create(customerDTO));
    }
    @GetMapping("")
    public ResponseEntity<List<CustomerDTO>> getAll() {
        return ResponseEntity.ok(customerService.findAll());
    }
}
