package com.example.asus.customer.api;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/9.
 */
public interface ApiService {


    /**
     * 获取版本信息
     *///http://api.gc.rxjy.com/app/version_com.rxjy.pm.json
    @GET("actionapi/AppCurrencyHome/IsAndroidUpdated")
    Observable<String> getVersionInfo(
            @Query("Version") int version,
            @Query("AppId") int AppId
    );

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("actionapi/AppHome/Login")
    Observable<String> getLogin(
            @Field("cardNo") String cardNo,
            @Field("password") String password,
            @Field("nick_name") String nick_name,
            @Field("order_id") String order_id,
            @Field("vCode") String vCode,
            @Field("postId") int postId,
            @Field("isLogin") int isLogin
    );

    /**
     * 获取验证码
     */
    @FormUrlEncoded
    @POST("actionapi/AppHome/GetVcodeLanding")
    Observable<String> getTokenByCode(
            @Field("phone") String phone,
            @Field("postId") String postId

    );
/**
 * http://192.168.1.192:8859/api/program/getScheme?orderNo=26-75050
 */
    /**
     * 方案图片地址
     *///http://app.wenes.cn/api/program/getScheme?rwdId=
    @GET("api/program/getScheme")
    Observable<String> loadHomeData(
            @Query("rwdId") String orderNo
    );

    /**
     * 登陆
     */
    @FormUrlEncoded
    @POST("actionapi/AppHome/Login")
    Observable<String> landLogin(
            @Field("cardNo") String rwdId,
            @Field("isLogin") int isLogin,
            @Field("password") String password,
            @Field("postId") int postId


    );

    /**
     * 设计师资料
     *///http://192.168.1.192:8808/net/findVipMsgByCardNo?cardNo=00000003
    @GET("net/findVipMsgByCardNo")
    Observable<String> findVipMsgByCardNo(
            @Query("cardNo") String cardNo

    );

    /**
     * 根据订单id，获取接单模块 所有信息
     */
    @GET("order/orderInfo")
    Observable<String> getProgssData(
            @Query("rwdId") String rwdId

    );

    /**
     * 根据订单id，获取接单模块 所有信息
     */
    @GET("order/orderInfo")
    Observable<String> getFindData(
            @Query("rwdId") String rwdId,
            @Query("type") int type


    );

    /**
     * 新闻*获取用户当前城市ID
     */
    @GET("AppAgent/GetHuiYuanCityLevel")
    Observable<String> getUserId(
            @Query("card_no") String card
    );

    /**
     * 新闻列表点赞
     */
    @POST("api/appNews/appNewsContent/saveNewsPraise")
    Observable<String> getDianZan(
            @QueryMap Map<String, String> map
    );

    /**
     * 新闻*获取新闻type列表
     */
    @GET("api/appNews/appNewsPostGoupRelation/findAppPostGroupRelation")
    Observable<String> getNewsType(
            @QueryMap() Map<String, String> map
    );

    /**
     * 新闻*获取新闻列表
     */
    @GET("api/appNews/appNewsContent/findAppNewsListByCardNo")
    Observable<String> getNewsListData(
            @QueryMap() Map<String, String> map
    );

    /**
     * 获取发现页面数据
     *
     * @param
     * @param
     * @param
     * @return
     */
    @GET("api/program/getPicture")
    Observable<String> getLoAddData(
            @Query("tb_diqu") String tb_diqu,
            @Query("ci_Type") String ci_Type,
            @Query("ca_IntentionalStyle") String ca_IntentionalStyle,
            @Query("ci_Area") String ci_Area,
            @Query("tb_ca_DecBudgetPrice") String tb_ca_DecBudgetPrice
    );

    /**
     * 获取设计说明
     *
     * @param rwdId
     * @return
     */
    @GET("api/program/getProjectBrief")
    Observable<String> loadDesignData(
            @Query("rwdId") String rwdId


    );

    /**
     * 量房图片
     *
     * @param rwdId
     * @return
     *///http://na.wenes.com/api/jd/GetImage?rwdid=22-119555
    @GET("api/jd/GetImage")
    Observable<String> getVolumeRoompicture(
            @Query("rwdid") String rwdId


    );

    /**
     * 获取元素、色彩、材质图片信息
     */
    @GET("api/program/getDesignElement")
    Observable<String> getCurreerPhotoData(
            @Query("cartNo") String rwdId


    );

    /**
     * a/sap/sapArticle/getAppArticleList
     */  //获取发现的数据
    //a/sap/sapArticle/getAppArticleList
    @GET("a/sap/sapArticle/getAppArticleList")
    Observable<String> getFindLoadData(
            @Query("cardNo") String cardNo,
            @Query("page") int page,
            @Query("rows") int rows
    );

    /**
     * 获取项目的状态
     */
    @GET("api/WENES/projectstatus")
    Observable<String> getProjectStatus(
            @Query("rwdid") String rwdid

    );

    /**
     * 登录后调用
     */
    @FormUrlEncoded
    @POST("/feedback/updateAppLastLoginTime")
    Observable<String> getLoginTime(
            @Field("rwdId") String rwdid

    );

    /**
     * 优秀案例
     */
    @GET("api/WENES/selectWEN_ProjectInfo")
    Observable<String> getOptimizationsData(

    );

    /**
     * 发现
     */

    @Multipart
    @POST("a/sap/sapArticle/getAppArticleList")
    Observable<String> getNewsList1(
            //  String cardNo, String appid, String postId, String page, String rows
            //http://wpsnew.rxjy.com/a/sap/sapArticle/getAppArticleList?cardNo=01012167&appId=0c5c9c88-5775-4a70-bafd-849115ef4d94&postId=8&page=1&rows=10
            @Part("cardNo") String cardNo,
            @Part("appid") String appid,
            @Part("postId") String postId,
            @Part("page") String page,
            @Part("rows") String rows
    );

    /**
     * 推荐案例
     *///http://app.wenes.cn:8859/api/program/getPicture?rwdId=12-203582
    ///  http://app.wenes.cn:8859/api/program/getPicture?rwdId=12-203582
    @GET("api/program/getPicture")
    Observable<String> getRecommendData(
            @Query("rwdId") String rwdId
    );

    /**
     * 提交合同
     */
    @Multipart
    @POST("order/updateClientInfoAndClientAuxiliary")
    Observable<String> getContractData(
            @Part("rwdId") String rwdId,
            @Part("ca") String ca
    );
    /**
     * 获取用户信息
     */
////    @Headers("Cache-Control: public, max-age=3600")
//    @GET("actionapi/apphome/GetUserMessage")
//    Observable<String> getLoginUserInfo(
//            @Query("cardNo") String cardNo,
//            @Query("token") String token
//    );

    /**
     * 获取用户信息
     */
    @FormUrlEncoded
    @POST("actionapi/AN_Home/ShowMyInfo")
    Observable<String> getLoginUserInfo(
            @Field("cardNo") String phone,
            @Field("token") String postId

    );
    //actionapi/AppLogin/GetCheckUserInfo
    //GET /actionapi/AppLogin/GetCheckUserInfo
//1判断是否已经验证过，0已经注册，可以登陆 1没注册，可以注册 -1 提示失败 -2 没注册，没绑定，不允许登陆
    //

    @GET("/actionapi/AppLogin/GetCheckUserInfo")
    Observable<String> getCheckUserInfo(
            @Query("Phone") String Phone,
            @Query("AppId") String AppId
    );

    /**
     * @param Phone
     * @param AppId 获取验证码
     * @return
     */
    ///actionapi/AppLogin/GetInsideVcodeLanding
    @GET("actionapi/AppLogin/GetInsideVcodeLanding")
    Observable<String> getInsideVcodeLanding(
            @Query("Phone") String Phone,
            @Query("AppId") String AppId
    );

    /**
     * 登录  验证码登录
     */
    ///actionapi/AppLogin/GetInsideVcodeLanding
    @FormUrlEncoded
    @POST("actionapi/AppLogin/Login")
    Observable<String> getCode_Login(
            @Field("cardNo") String cardNo,//手机号或卡号
            @Field("password") String password,//密码
            @Field("vCode") String vCode,//验证码
            @Field("appId") String appId,//2 牛经济  3 瑞祥平台 4 瑞祥客户 5瑞祥施工 6瑞祥材料 7工人联盟 8瑞祥设计 9瑞祥
            @Field("isLogin") String isLogin//使用验证码登录时传1，否则不传或传空
    );

    /**
     * 获取登录验证码
     */
    @FormUrlEncoded
    @POST("actionapi/AppHome/GetVcodeLanding")
    Observable<String> getLoginCode(
            @Field("phone") String phone,
            @Field("postId") String postId

    );

    /**
     * @param cardNo
     * @param password 获取token
     * @param vCode
     * @param appId
     * @return
     */
    @FormUrlEncoded
    @POST("actionapi/AppLogin/Login")
    Observable<String> getAppLogin(
            @Field("cardNo") String cardNo,
            @Field("password") String password,
            @Field("vCode") String vCode,
            @Field("appId") String appId

    );

    /**
     * 获取任务单号
     */
    @GET("actionapi/KGManage/GetCustomerProjectNoByAppId")
    Observable<String> getCustomerProjectNoByAppId2(
            @Query("appid") String appId
    );

    /**
     * 获取用户信息
     */
    @GET("actionapi/AppPreUser/GetBuildNoLandingEWM_json")
    Observable<String> getCustomerProjectNoByAppId(
            @Query("id") String id
    );

    /**
     * https://piapi.rxjy.com/actionapi/AppCurrencyHome/GetAppHomeAd?is_app=当前APP单独ID&cardno=卡号，没有传空字符串
     * 获取开屏广告
     */
    @GET("actionapi/AppCurrencyHome/GetAppHomeAd")
    Observable<String> getAdData(
            @Query("is_app") String is_app,
            @Query("cardno") String cardno
    );

    /**
     * 扫码登陆
     */
    @FormUrlEncoded
    @POST("actionapi/AppLogin/EWMLogin")
    Observable<String> getEWMLogin(

            @Field("cardNo") String cardNo,
            @Field("password") String password,
            @Field("loginId") String loginId
    );

    /**
     * 忘记密码修改密码
     */
    @FormUrlEncoded
    @POST("actionapi/AppHome/UpdatePassword_Vcode")
    Observable<String> updatePasswordByForget(
            @Field("phone") String phoneNum,
            @Field("newPassword") String newPassword,
            @Field("vCode") String vCode
    );

    /**
     * 修改密码
     */
    @GET("actionapi/apphome/UpdatePassword")
    Observable<String> updatePassword(
            @Query("cardNo") String cardNo,
            @Query("password") String password,
            @Query("newPassword") String newPassword,
            @Query("token") String token
    );

    /**
     * 获取忘记密码验证码
     */
    @FormUrlEncoded
    @POST("actionapi/AppHome/GetVcodeUpdatePwd")
    Observable<String> getVCodeForgetPassword(
            @Field("phone") String phoneNum
    );

    /**
     * 提交反馈
     */
    @Multipart
    @POST("api/CommandPlatform/AddComplaint")
    Observable<String> subSuggestInfo(
            @Part("the_name") String the_name,
            @Part("contact") String contact,
            @Part("service_description") String type,
            @Part("complaints_type") int content,
            @Part("contract_no") String contract_no,
            @Part List<MultipartBody.Part> files
    );

    /**
     * 修改用户信息
     */ //POST /actionapi/AN_Home/UpdateMyInfo
    @FormUrlEncoded
    @POST("actionapi/AN_Home/UpdateMyInfo")
    Observable<String> updateUserInfo(
            @Field("token") String token,
            @Field("cardNo") String cardNo,
            @Field("key") String key,
            @Field("value") String value
    );
//    /**
//     * 上传头像
//     */ //POST /actionapi/AN_Home/UpdateImages
//    //actionapi/AN_Home/UpdateImages
//    @Multipart
//    @POST("actionapi/AN_Home/UpdateImages")
//    Observable<String> upHeaderPicture(
//            @Part("token") String token,
//            @Part("cardNo") String cardNo,
//            @Part MultipartBody.Part file
//    );

    /**
     * 上传头像
     */ //POST AliUpload
    //
    @FormUrlEncoded
    @POST("AliUpload")
    Observable<String> upHeaderPicture(
            @Field("imgUrl") String imgUrl,
            @Field("cardNo") String cardNo,
            @Field("type") String type
    );

    /**
     * 获取用于初始化Oss的key、id等信息
     */
    @GET("app")
    Observable<String> getOssData();

    /**
     * 获取通知列表
     */
    @GET("actionapi/JiGuang/GetAppNoticeGroup")
    Observable<String> getInformMessage(
            @Query("CardNo") String cardno,
            @Query("IsApp") int IsApp
    );

    @GET("actionapi/JiGuang/GetSeeAppNotice")
    Observable<String> getToSeeDetails(
            @Query("Id") String Id
    );

    /**
     * 一键已读
     */
    @GET("actionapi/JiGuang/GetSeeAllMessage")
    Observable<String> setMessage(
            @Query("CardNo") String CardNo,
            @Query("Group") String Group
    );

    /**
     * 获取通知列表详情
     */
    @GET("actionapi/JiGuang/GetAppNoticeList")
//    @GET("actionapi/AppPort/GetAppNoticeList")
    Observable<String> getInformListMessage(
            @Query("cardNo") String cardno,
            @Query("Group") String Group
    );

    /**
     * 获取通知详情
     */
    @GET("actionapi/JiGuang/GetAppNoticeDetail")
    Observable<String> getInformDetailsMessage(
            @Query("Id") String Id
    );

    /**
     * 获取首页案例列表数据
     */
    @GET("/api/customerapp/getCaseList")
    Observable<String> getAnLiListDate(
            @QueryMap Map<String, String> map
    );

    /**
     * 案例詳情浏览时长
     */
    @Headers({"Content-Type:application/json", "Accept:application/json"})//需要添加头
    @POST("/api/customerapp/updateCaseClickLook")
    Observable<String> getAnLiTime(
            /*@FieldMap Map<String, String> map*/
            @Body RequestBody body
    );

    /**
     * 获取未读消息
     */
    @GET("/api/customer/getUnRead")
    Observable<String> getUnreadData(
            @QueryMap Map<String, String> map
    );

    /**
     * 获取项目名称
     */
    @GET("/api/customerapp/getProjectInfo")
    Observable<String> getObjectData(
            @QueryMap Map<String, String> map
    );

    /**
     * 首页案例列表数据  点赞
     */
    @Headers({"Content-Type:application/json", "Accept:application/json"})//需要添加头
    @POST("/api/customerapp/collect")
    Observable<String> setDianZanData(
            @Body RequestBody body
//            @FieldMap Map<String, String> map
    );


    /**
     * 获取首页量房列表数据
     */
    @GET("/api/customerapp/getLFTP")
    Observable<String> getLiangFangListDate(
            @QueryMap Map<String, String> map
    );

    /**
     * 获取彩平图数据
     */
    @GET("/api/customerapp/getFA")
    Observable<String> getFangAn2ListDate(
            @QueryMap Map<String, String> map
    );

    /**
     * 获取结构图数据
     */
    @GET("/api/customerapp/getJG")
    Observable<String> getJieGouTuListDate(
            @QueryMap Map<String, String> map
    );

    /**
     * 获取首页效果图列表数据
     */
    @GET("/api/customerapp/getXGList")
    Observable<String> getXiaoGuoListDate(
            @QueryMap Map<String, String> map
    );

    /**
     * 获取首页施工图列表数据
     */
    @GET("/api/customerapp/getSGList")
    Observable<String> getShiGongListDate(
            @QueryMap Map<String, String> map
    );

    /**
     * 获取量房页的基本资料
     */
    @GET("/api/customerapp/getBaseInfoAll")
    Observable<String> getLiangFangJiBean(
            @QueryMap Map<String, String> map
    );

    /**
     * 获取设计素材详情数据
     */
    @GET("/api/customerapp/getMaterial")
    Observable<String> getSheJiData(
            @QueryMap Map<String, String> map
    );

    /**
     * 获取手绘数据
     */
    @GET("/api/customerapp/getHandPaint")
    Observable<String> getShouHui(
            @QueryMap Map<String, String> map
    );

    /**
     * 获取SU模型数据
     */
    @GET("/api/customerapp/getSUmodel")
    Observable<String> getSUMoXing(
            @QueryMap Map<String, String> map
    );

    /**
     * 设计师店铺
     */
    @GET("/api/customerapp/getDesignerShop")
    Observable<String> getSheJiShiData(
            @QueryMap Map<String, String> map
    );

    /**
     * 获取合同照片
     */
    @GET("/api/customerapp/getContractPicture")
    Observable<String> getHeTongData(
            @QueryMap Map<String, String> map
    );

    /**
     * 获取收藏列表
     */
    @GET("/api/customerapp/getCaseCollect")
    Observable<String> getShouCang(
            @QueryMap Map<String, String> map
    );

    /**
     * 获取预算列表
     */
    @GET("/api/customerapp/getBudget")
    Observable<String> getYuSuan(
            @QueryMap Map<String, String> map
    );

    /**
     * 获取评价列表
     */
    @GET("/api/customerapp/listComment")
    Observable<String> getPingJiaData(
            @QueryMap Map<String, String> map
    );

    /**
     * 提交效果图 中的 反馈意见
     */
    @Headers({"Content-Type:application/json", "Accept:application/json"})//需要添加头
    @POST("/api/customerapp/{tu}")
    Observable<String> setTiJiaoData(@Path("tu") String tu, @Body RequestBody body);

    /**
     * 保存量房页的基本资料
     */
    @Headers({"Content-Type:application/json", "Accept:application/json"})//需要添加头
    @POST("/api/customer/updateDecorate")
    Observable<String> getBaoCunLiangFangJiBean(
            @Body RequestBody body
    );

    /**
     * 交流
     * 投诉建议、售后服务、更换设计师
     */
//    @Headers({"Content-Type:application/json", "Accept:application/json"})//需要添加头
    @FormUrlEncoded
    @POST("/feedback/putFeedback")
    Observable<String> getTiJiao(
            @FieldMap Map<String, String> map
    );

    /**
     * 获取售后列表
     */
//    @Headers({"Content-Type:application/json", "Accept:application/json"})//需要添加头
    @GET("/feedback/listArea")
    Observable<String> getShouHouList(
            @Query("rwdid") String rwdid
    );

    /**
     * 换设计师获取原因分类
     */
//    @Headers({"Content-Type:application/json", "Accept:application/json"})//需要添加头
    ///feedback/listReason
    @GET("/feedback/listReason")
    Observable<String> getHuanRen(
    );

    /**
     * 联系
     */
    @GET("/feedback/contactUS")
    Observable<String> getLianXi(
            @Query("rwdid") String rwdid
    );

    /**
     * 再次合作
     */
//    @Headers({"Content-Type:application/json", "Accept:application/json"})//需要添加头
    @GET("/feedback/listCooperationAgain")
    Observable<String> getHeZuo(
            @Query("rwdid") String rwdid
    );

    /**
     * 再次合作
     */
    //@FormUrlEncoded
    @Headers({"Content-Type:application/json", "Accept:application/json"})//需要添加头
    @POST("/feedback/cooperationAgainAndroid")
    Observable<String> getHeZuoTiJiao(
            @Body RequestBody body
    );
}