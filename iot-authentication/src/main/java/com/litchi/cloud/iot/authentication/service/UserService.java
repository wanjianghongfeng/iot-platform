package com.litchi.cloud.iot.authentication.service;

import com.litchi.cloud.iot.authentication.domain.User;

public interface UserService {

	void create(User user);

	void update(User user);

}