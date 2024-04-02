package com.example.readcomment.service.impl;

import com.example.readcomment.dto.request.CookiesSaveRequest;
import com.example.readcomment.entity.User;
import com.example.readcomment.exception.OkException;
import com.example.readcomment.repository.OptionPriceRepository;
import com.example.readcomment.repository.UserRepository;
import com.example.readcomment.service.ExtendService;
import com.example.readcomment.utils.GetUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Service
@AllArgsConstructor
public class ExtendServiceImpl implements ExtendService {
    private static int MONTH_EXTEND=1;
    private UserRepository userRepository;
    private OptionPriceRepository optionPriceRepository;
    @Override
    public void extend(CookiesSaveRequest cookiesSaveRequest) {
        String uid = GetUtils.getUIDFromCookies(cookiesSaveRequest.getCookies());
        User user = userRepository.findOneByUid(uid);
        if(user!=null){
            Long accountBalance = user.getAccountBalance();
            Long price = optionPriceRepository.findOneById(1L).getPrice();
            if(accountBalance>=price){
                accountBalance-=price;
                user.setAccountBalance(accountBalance);

                Calendar c = Calendar.getInstance();
                c.setTime(user.getExpDate());
                c.add(Calendar.DATE, 30*MONTH_EXTEND);
                user.setExpDate(c.getTime());

                userRepository.save(user);
                throw new OkException("Đã gia hạn thành công!");
            }
            else{
                throw new OkException("Tài khoản không đủ để gia hạn!");
            }
        }
        throw new OkException("User không tồn tại");
    }
}
