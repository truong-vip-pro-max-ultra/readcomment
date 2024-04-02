package com.example.readcomment.service.impl;

import com.example.readcomment.dto.request.CookiesSaveRequest;
import com.example.readcomment.dto.request.UserGetInfoRequest;
import com.example.readcomment.dto.response.UserGetInfoResponse;
import com.example.readcomment.dto.response.UserInfoNameResponse;
import com.example.readcomment.entity.User;
import com.example.readcomment.exception.OkException;
import com.example.readcomment.fbapi.FB;
import com.example.readcomment.repository.UserRepository;
import com.example.readcomment.service.UserService;
import com.example.readcomment.utils.GetUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private static int MINUTE_TRY_USING = 2;
    private UserRepository userRepository;
    @Override
    public UserGetInfoResponse getInfo(UserGetInfoRequest userGetInfoRequest) {
        String cookies = userGetInfoRequest.getCookies();
        String fb_dtsg = FB.getFb_dtsg(cookies);
        String postId = FB.getPostId(userGetInfoRequest.getPostUrl(),cookies);
        byte[] decodedBytes = Base64.getDecoder().decode(postId);
        String decodedString = new String(decodedBytes);
        if(decodedString.indexOf("feedback")==-1){
            postId = "feedback:"+postId;
            postId = Base64.getEncoder().encodeToString((postId).getBytes());
        }

        UserGetInfoResponse userGetInfoResponse = UserGetInfoResponse.builder()
                .cookies(cookies)
                .fb_dtsg(fb_dtsg)
                .postId(postId)
                .build();
        return userGetInfoResponse;
    }

    @Override
    public UserInfoNameResponse getInfoNameResponse(CookiesSaveRequest cookiesSaveRequest) {
        String uid = GetUtils.getUIDFromCookies(cookiesSaveRequest.getCookies());
        User user = userRepository.findOneByUid(uid);
        return UserInfoNameResponse.builder()
                .cookies(cookiesSaveRequest.getCookies())
                .fullName(FB.getFullNameFromCookies(cookiesSaveRequest.getCookies()))
                .accountBalance(user!=null?user.getAccountBalance().toString():"")
                .expDate(user!=null? new SimpleDateFormat("dd-MM-yyyy").format(user.getExpDate()):"")
                .build();
    }

    @Override
    public void save(CookiesSaveRequest cookiesSaveRequest) {
        String uid = GetUtils.getUIDFromCookies(cookiesSaveRequest.getCookies());
        User user = userRepository.findOneByUid(uid);
        if(user==null){
            User userSave = new User();
            userSave.setUid(uid);
            userSave.setAccountBalance(0L);
            try {
                Date dateNow = GetUtils.getDateTimeNowVietNam();
                Date dateTryUsing = new Date(dateNow.getTime()+(1000 * 60 * MINUTE_TRY_USING));
                userSave.setExpDate(dateTryUsing);
            } catch (ParseException e) {
                userSave.setExpDate(new Date());
                throw new RuntimeException(e);
            }
            userRepository.save(userSave);
        }
    }

    @Override
    public void checkLiveCookies(CookiesSaveRequest cookiesSaveRequest) {
        throw new OkException(FB.checkLiveCookies(cookiesSaveRequest.getCookies()));
    }


}
