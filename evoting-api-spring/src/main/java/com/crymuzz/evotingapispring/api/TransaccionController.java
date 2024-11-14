package com.crymuzz.evotingapispring.api;

import com.crymuzz.evotingapispring.entity.dto.TransactionResponseDTO;
import com.crymuzz.evotingapispring.service.ITransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@Tag(name = "Transacciones", description = "Endpoint para las consultas de las transacciones")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
public class TransaccionController {

    private final ITransactionService transactionService;

    @GetMapping("/{hash}")
    public ResponseEntity<TransactionResponseDTO> transactionResponseDTO(@PathVariable String hash) {
        TransactionResponseDTO response = transactionService.findTransactionByHash(hash);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
