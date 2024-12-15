package dev.rohit.paymentservice.respositories;

import dev.rohit.paymentservice.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByOrderId(Long orderId);

    Payment findByPaymentId(String paymentId);

    Payment findByPaymentLink(String paymentLink);


}
