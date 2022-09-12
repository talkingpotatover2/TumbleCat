package com.cat.reward;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cat.DataNotFoundException;
import com.cat.account.entity.Account;
import com.cat.project.entity.Project;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RewardService {
	private final RewardRepository rewardRepository;
	private DecimalFormat formatter = new DecimalFormat("#,##0.##");
	
	public void create(String rwName, String rwDesc, BigDecimal rwMin,
			BigDecimal rwMax, Project project)
	{
		Reward r = new Reward();
		r.setRwName(rwName);
		r.setRwDesc(rwDesc);
		r.setRwMin(rwMin);
		r.setRwMax(rwMax);
		r.setProject(project);
		this.rewardRepository.save(r);
	}
	
	public Reward getReward(Long rwId) {
		Optional<Reward> reward = this.rewardRepository.findById(rwId);
		if(reward.isPresent()) {
			return reward.get();
		}else {
			throw new DataNotFoundException("project not found");
		}
	}
	
	public void delete(Reward reward) {
		this.rewardRepository.delete(reward);
	}
}
