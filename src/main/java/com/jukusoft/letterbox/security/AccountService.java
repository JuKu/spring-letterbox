package com.jukusoft.letterbox.security;

import com.jukusoft.authentification.jwt.account.AccountDTO;
import com.jukusoft.authentification.jwt.account.IAccountService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Qualifier("iAccountService")
public class AccountService implements IAccountService {

    @Override
    public Optional<AccountDTO> loginUser(String username, String password) {
        return Optional.empty();
    }

}
