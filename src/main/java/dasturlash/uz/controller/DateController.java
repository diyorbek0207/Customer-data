package dasturlash.uz.controller;

import dasturlash.uz.dto.DateDTO;
import dasturlash.uz.service.DateSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/date")
public class DateController {
    @Autowired
    private DateSevice dateSevice;

    @PostMapping("")
    public ResponseEntity<DateDTO> create(@RequestBody DateDTO date) {
        return ResponseEntity.ok(dateSevice.create(date));
    }
    @GetMapping("")
    public ResponseEntity<List<DateDTO>> getAll() {
        return ResponseEntity.ok(dateSevice.findAll());
    }
    @GetMapping("/customerId")
    public ResponseEntity<List<DateDTO>> getCustomerId() {
        return ResponseEntity.ok(dateSevice.findAllByCustomerID());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@RequestBody DateDTO date, @PathVariable("id") String id) {
        return ResponseEntity.ok(dateSevice.update(date,id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        return ResponseEntity.ok(dateSevice.delete(id));
    }
    @DeleteMapping("/{id}/admin")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Boolean> deleteAdmin(@PathVariable("id") String id) {
        return ResponseEntity.ok(dateSevice.deleteAdmin(id));
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/pagination")
    public ResponseEntity<PageImpl<DateDTO>> getPagination(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(dateSevice.pagination(page-1, size));
    }

    @GetMapping("/filter")
    public ResponseEntity<PageImpl<DateDTO>> getFilter(@RequestBody DateDTO dto,
                                                       @RequestParam int page,
                                                       @RequestParam int size) {
        return ResponseEntity.ok(dateSevice.filter(dto,page-1,size));
    }
}
