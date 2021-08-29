package com.devsuperior.hrpayroll.services;

import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.entities.Worker;
import com.devsuperior.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    //@Value("${hr-worker.host}")
   // private String workerHost;

    //@Autowired
    //private RestTemplate restTemplate;
    @Autowired
    private WorkerFeignClient workerFeignClient;

    public Payment getPayment(long workerId, int days) {
        //Map<String, String> uriVariable = new HashMap<>();
        //uriVariable.put("id", String.valueOf(workerId));

       // Worker worker = restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, uriVariable);

        Worker worker = workerFeignClient.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
