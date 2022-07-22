package com.charter.reward.controller;

import com.charter.reward.model.Reward;
import com.charter.reward.service.RewardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/rewards")
public class RewardController {

    private final RewardService rewardService;

    @Autowired
    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping("/{customerId}")
    public Reward getRewardByCustomerId(@PathVariable Long customerId) {
        log.info("RewardController::getRewardByCustomerId {}", customerId);

        return this.rewardService.rewardByCustomerId(customerId);
    }

//    @GetMapping
//    public List<Reward> getAllRewards(@RequestParam("name") Optional<Long> customerId) {
//
//    }
}
