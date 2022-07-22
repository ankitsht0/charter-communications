package com.charter.reward.service;

import com.charter.reward.model.Customer;
import com.charter.reward.model.Purchase;
import com.charter.reward.model.Reward;
import com.charter.reward.repository.CustomerRepository;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class RewardService {

    private static final String NO_PURCHASE_MESSAGE = "NO CUSTOMER PURCHASE";

    protected final Function<Instant, Month> MONTH_FX =
        instant -> LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).getMonth();

    protected final UnaryOperator<Long> BASE_REWARD_FX =
        amt -> 1l * (amt - 50);

    protected final UnaryOperator<Long> PREMIUM_REWARD_FX =
        amt -> (2l * (amt - 100)) + BASE_REWARD_FX.apply(100l);

    private final CustomerRepository customerRepository;

    @Autowired
    public RewardService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Reward rewardByCustomerId(@NotNull Long customerId) {

        return this.customerRepository.findById(customerId)
            .map(Customer::getPurchases)
            .map(this::from)
            .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Customer id %s not found", customerId)));
    }

    protected Reward from(List<Purchase> purchases) {
        if (purchases.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.OK,
                String.format("Customer has no purchases"));
        }
        log.info("purchase list {}", purchases);
        final Map<Month, Long> pointsByMonth = new HashMap<>();
        purchases.stream()
            .forEach(purchase -> {
                final Month month = MONTH_FX.apply(purchase.getTimestamp());
                pointsByMonth.put(month,
                    pointsByMonth.getOrDefault(month, 0l) + calculate(purchase.getAmount()));
            });
        return new Reward(purchases.get(0).getCustomer().getName(), pointsByMonth);
    }

    protected Long calculate(BigDecimal amount) {
        long amt = amount.longValue();
        if (amt < 50) {
            return 0l;
        } else if (amt <= 100) {
            return BASE_REWARD_FX.apply(amt);
        }

        return PREMIUM_REWARD_FX.apply(amt);
    }
}
