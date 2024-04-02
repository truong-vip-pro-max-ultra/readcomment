package com.example.readcomment.service.impl;

import com.example.readcomment.dto.response.CommentInfoResponse;
import com.example.readcomment.dto.request.UserInfoRequest;
import com.example.readcomment.entity.User;
import com.example.readcomment.fbapi.FB;
import com.example.readcomment.repository.UserRepository;
import com.example.readcomment.service.CommentService;

import com.example.readcomment.utils.GetUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private UserRepository userRepository;
    @Override
    public CommentInfoResponse getNewComment(UserInfoRequest userInfoRequest) {
        String uid = GetUtils.getUIDFromCookies(userInfoRequest.getCookies());
        User user = userRepository.findOneByUid(uid);
        if(user!=null){
            try {
                if(user.getExpDate().getTime() > GetUtils.getDateTimeNowVietNam().getTime()){
                    return FB.getNewComment(userInfoRequest.getPostId(),userInfoRequest.getCookies(),userInfoRequest.getFb_dtsg());
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return CommentInfoResponse.builder()
                .tryUsing("false")
                .build();
    }
}
