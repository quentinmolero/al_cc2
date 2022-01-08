package fr.moleroq.al.application;

import fr.moleroq.al.domain.*;
import fr.moleroq.al.kernel.Engine;

import java.time.Instant;
import java.util.Date;


@Engine
public final class MonthlyPaymentEngine extends Thread {

    private static final MonthlyPaymentEngine INSTANCE = new MonthlyPaymentEngine();
    private static UserPaymentRepository userPaymentRepository;
    private static PaymentService paymentService;
    private static PaymentRepository paymentRepository;
    private static boolean setup = false;

    private MonthlyPaymentEngine() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Already initialized");
        }
    }

    public static MonthlyPaymentEngine getInstance() {
        if (!setup) {
            throw new IllegalStateException("Setup engine before using it");
        }
        return INSTANCE;
    }

    public static void setupInstance(UserPaymentRepository userPaymentRepository, PaymentService paymentService, PaymentRepository paymentRepository) {
        MonthlyPaymentEngine.userPaymentRepository = userPaymentRepository;
        MonthlyPaymentEngine.paymentService = paymentService;
        MonthlyPaymentEngine.paymentRepository = paymentRepository;
        MonthlyPaymentEngine.setup = true;
    }

    public void run() {
        if (!setup) {
            throw new IllegalStateException("Setup engine before using it");
        }
        while (true) {
            System.out.println("Thread is running");
            userPaymentRepository.findAll()
                    .keySet()
                    .forEach(userId -> userPaymentRepository.byUserId(userId)
                            .stream().limit(1)
                            .forEach(paymentId -> {
                                Date paymentDate = paymentRepository.byId(paymentId).getPaymentDate();
                                Date renewPaymentDate = Date.from(paymentDate.toInstant().plusSeconds(2_592_000));
                                if (paymentDate.before(renewPaymentDate)) {
                                    PaymentId renewedPaymentId = paymentRepository.nextIdentity();
                                    paymentService.create(Payment.of(10_00, Date.from(Instant.now()), renewedPaymentId));
                                    userPaymentRepository.save(userId, renewedPaymentId);
                                }
                            }));
            try {
                sleep(1000 * 60 * 60 * 24);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
