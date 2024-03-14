package com.groom.Kkri;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "exam")
public class ExamController {

    @Operation(summary = "예시 컨트롤러" , description = "exam")
    @GetMapping("/")
    public ResponseEntity<String> exam(){
        return ResponseEntity.ok("hi");
    }
}
