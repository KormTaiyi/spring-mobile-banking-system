package com.example.mobilebankingapi.init;

import com.example.mobilebankingapi.domain.CustomerSegment;
import com.example.mobilebankingapi.repository.CustomerSegmentRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomerSegmentInitialize {

    private final CustomerSegmentRepository customerSegmentRepository;

    @PostConstruct
    public void init() {

        if (customerSegmentRepository.count() == 0) {
            CustomerSegment segmentRegular = new CustomerSegment();
            segmentRegular.setSegment("REGULAR");
            segmentRegular.setDescription("REGULAR");
            segmentRegular.setIsDeleted(false);

            CustomerSegment segmentSilver = new CustomerSegment();
            segmentSilver.setSegment("SILVER");
            segmentSilver.setDescription("SILVER");
            segmentSilver.setIsDeleted(false);

            CustomerSegment segmentGold = new CustomerSegment();
            segmentGold.setSegment("GOLD");
            segmentGold.setDescription("GOLD");
            segmentGold.setIsDeleted(false);

            customerSegmentRepository.saveAll(
                    List.of(segmentRegular, segmentSilver, segmentGold)
            );
        }

    }

}
