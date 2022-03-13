package com.pepole.tesusaku.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pepole.tesusaku.repository.AssignMapper;
import com.pepole.tesusaku.service.AssignService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AssignServiceImpl implements AssignService {
	private final AssignMapper assignMapper;
	
	public List<String> getAssignedUsers(String suiteId) {
		return assignMapper.findUserBySuiteid(suiteId);
	}

}
