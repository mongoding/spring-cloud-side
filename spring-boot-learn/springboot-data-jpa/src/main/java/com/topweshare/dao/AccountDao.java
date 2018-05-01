package com.topweshare.dao;

import com.topweshare.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mongoding on 2017/4/20.
 */
public interface AccountDao  extends JpaRepository<Account,Integer> {
}
