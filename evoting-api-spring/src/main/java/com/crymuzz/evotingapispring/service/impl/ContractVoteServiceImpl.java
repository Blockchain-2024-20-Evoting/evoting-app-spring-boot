package com.crymuzz.evotingapispring.service.impl;

import com.crymuzz.evotingapispring.mapper.TransactionMapper;
import com.crymuzz.evotingapispring.service.IContractVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;


import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ContractVoteServiceImpl implements IContractVoteService {

    private final Web3j web3j;

    @Value("${web3j.contract.address}")
    private String contractAddress;

    @Value("${web3j.contract.private-key}")
    private String privateKey;

    public static final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L); // 20 Gwei
    public static final BigInteger GAS_LIMIT = BigInteger.valueOf(6_721_975L);

    private final TransactionMapper transactionMapper;
    private final TransactionServiceImpl transactionService;


    @Override
    public TransactionReceipt vote(Long electionId, Long candidateId, Long studentId) throws TransactionException, IOException {

        Credentials credentials = Credentials.create(privateKey);
        TransactionManager txManager = new RawTransactionManager(web3j, credentials);

        Function function = new Function(
                "vote",
                Arrays.asList(
                        new Uint256(BigInteger.valueOf(electionId)),
                        new Uint256(BigInteger.valueOf(candidateId)),
                        new Uint256(BigInteger.valueOf(studentId))
                ),
                Collections.emptyList()
        );
        String txData = FunctionEncoder.encode(function);
        EthSendTransaction ethSendTransaction = txManager.sendTransaction(
                GAS_PRICE,
                GAS_LIMIT,
                contractAddress,
                txData,
                BigInteger.ZERO
        );
        if (ethSendTransaction.hasError()) {
            throw new RuntimeException("Error sending transaction: " + ethSendTransaction.getError().getMessage());
        }
        String transactionHash = ethSendTransaction.getTransactionHash();
        PollingTransactionReceiptProcessor receiptProcessor = new PollingTransactionReceiptProcessor(
                web3j,
                TransactionManager.DEFAULT_POLLING_FREQUENCY,
                TransactionManager.DEFAULT_POLLING_ATTEMPTS_PER_TX_HASH
        );
        return receiptProcessor.waitForTransactionReceipt(transactionHash);
    }


    @Override
    public BigInteger getVotes(Long electionId, Long candidateId) throws IOException {
        Function function = new Function(
                "getVotes", // Nombre del método en el contrato
                Arrays.asList(
                        new Uint256(BigInteger.valueOf(electionId)),  // Argumento: electionId
                        new Uint256(BigInteger.valueOf(candidateId))  // Argumento: candidateId
                ),
                List.of(new TypeReference<Uint256>() {
                })
        );

        EthCall response = web3j.ethCall(
                org.web3j.protocol.core.methods.request.Transaction.createEthCallTransaction(
                        contractAddress, contractAddress, FunctionEncoder.encode(function)),
                org.web3j.protocol.core.DefaultBlockParameterName.LATEST
        ).send();

        // Procesar la respuesta
        List<Type> result = FunctionReturnDecoder.decode(response.getResult(), function.getOutputParameters());
        if (!result.isEmpty()) {
            return (BigInteger) result.get(0).getValue();
        } else {
            throw new RuntimeException("Error al verificar los votos");
        }
    }

    @Override
    public Boolean hasStudentVoted(Long electionId, Long studentId) throws IOException {
        // Crear la función que queremos llamar en el contrato
        Function function = new Function(
                "hasStudentVoted", // Nombre de la función en el contrato
                Arrays.asList(
                        new Uint256(BigInteger.valueOf(electionId)), // Parámetro electionId
                        new Uint256(BigInteger.valueOf(studentId))   // Parámetro studentId
                ),
                List.of(new TypeReference<Bool>() {
                }) // Tipo de retorno esperado: Bool
        );

        // Hacer la llamada al contrato
        EthCall response = web3j.ethCall(
                org.web3j.protocol.core.methods.request.Transaction.createEthCallTransaction(
                        contractAddress, contractAddress, FunctionEncoder.encode(function)),
                org.web3j.protocol.core.DefaultBlockParameterName.LATEST
        ).send();
        // Decodificar el resultado de la llamada
        List<Type> result = FunctionReturnDecoder.decode(response.getResult(), function.getOutputParameters());
        // Verificar que el resultado no esté vacío y devolver el valor booleano
        return (Boolean) result.get(0).getValue();
    }


}