package com.example.asus.customer.api;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/9.
 */
public interface ApiService {

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
     * 优秀案例
     */
    @GET("api/WENES/selectWEN_ProjectInfo")
    Observable<String> getOptimizationsData(

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
//    @Headers("Cache-Control: public, max-age=3600")
    @GET("actionapi/apphome/GetUserMessage")
    Observable<String> getLoginUserInfo(
            @Query("cardNo") String cardNo,
            @Query("token") String token
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
     *
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
     *
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
    Observable<String> getCustomerProjectNoByAppId(

            @Query("appid") String appId
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
     * 提交投诉建议
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
    /**
     * 上传头像
     */ //POST /actionapi/AN_Home/UpdateImages
    //actionapi/AN_Home/UpdateImages
    @Multipart
    @POST("actionapi/AN_Home/UpdateImages")
    Observable<String> upHeaderPicture(
            @Part("token") String token,
            @Part("cardNo") String cardNo,
            @Part MultipartBody.Part file
    );

}