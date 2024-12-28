package com.ducdpg.employee_demo.services.imp;

import com.ducdpg.employee_demo.entity.Account;
import com.ducdpg.employee_demo.models.account.AuthenRequest;
import com.ducdpg.employee_demo.models.account.AuthenResponse;
import com.ducdpg.employee_demo.models.account.ResgisterModel;
import com.ducdpg.employee_demo.repositories.AccountRepository;
import com.ducdpg.employee_demo.services.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AccountRepository accountRepository;

    @Override
    public void register(ResgisterModel resgisterModel) {

        Optional<Account> existedAccount = accountRepository.findByEmail(resgisterModel.getEmail());
        if (existedAccount.isPresent()) {
            throw new RuntimeException("Email already exist");
        }

        Account account = Account.builder()
                .email(resgisterModel.getEmail())
                .fullName(resgisterModel.getFullName())
                .password(passwordEncoder.encode(resgisterModel.getPassword()))
                .phoneNumber(resgisterModel.getPhoneNumber())
                .createdDate(LocalDateTime.now())
                .isDelete(false)
                .build();

        accountRepository.save(account);
    }

    @Override
    public AuthenResponse login(AuthenRequest authenRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenRequest.getEmail(), authenRequest.getPassword()));
        } catch (Exception ex) {
            throw new RuntimeException("Invalid email or password");
        }

        Account account = accountRepository.findByEmail(authenRequest.getEmail()).orElseThrow();

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("fullName", account.getFullName());
        String jwtToken = jwtService.generateAccessToken(extraClaims, account.getEmail());
        return AuthenResponse.builder()
                .accessToken(jwtToken)
                .refreshToken("")
                .build();
    }
}
