package com.example.mobilebankingapi;

import com.example.mobilebankingapi.repository.CustomerSegmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MobileBankingApiApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    private CustomerSegmentRepository customerSegmentRepository;

    @Test
    public void testFindCustomerSegment(){
        customerSegmentRepository.findAll()
                .forEach(customerSegment ->
                        System.out.println(customerSegment
                                .getCustomers()));
    }
}
