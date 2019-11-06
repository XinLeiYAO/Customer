package com.example.asus.customer.commons.utils;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.common.utils.IOUtils;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.ListPartsRequest;
import com.alibaba.sdk.android.oss.model.ListPartsResult;
import com.alibaba.sdk.android.oss.model.PartETag;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.alibaba.sdk.android.oss.model.UploadPartRequest;
import com.alibaba.sdk.android.oss.model.UploadPartResult;
import com.example.asus.customer.entity.OSSBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by shuai on 2018/10/15.
 */

public class OssManager {

    /**
     * Created by Administrator on 2017/3/1.
     */
    private picResultCallback callback;//回调接口
    private OSS oss;
    private Context contexts;

    public static OssManager getInstance() {
        return OssInstance.instance;
    }

    private static class OssInstance {
        private static final OssManager instance = new OssManager();
    }
    public OSS getOss(){
        return oss;
    }
    private OssManager() {
    }

    private String bucketName;
    private String accessKeyId;
    boolean isok = false;

    /**
     * 初始化
     *
     * @param context
     * @param endpoint        https://oss-cn-beijing.aliyuncs.com
     * @param bucketName      common-rxjy
     * @param accessKeyId
     * @param accessKeySecret
     * @param token
     * @param callback
     * @return
     */
    public OssManager init(Context context, String endpoint, String bucketName,
                           String accessKeyId, String accessKeySecret, String token,
                           picResultCallback callback) {
        if (oss == null) {
            OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(accessKeyId, accessKeySecret, token);
            ClientConfiguration conf = new ClientConfiguration();
            conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
            conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
            conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
            conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次

            OSSClient ossClient = new OSSClient(context, endpoint, credentialProvider, conf);
            oss = ossClient;
            OSSLog.enableLog();
        }
        this.bucketName = bucketName;
        this.accessKeyId = accessKeyId;
        this.callback = callback;
        this.contexts = context;
        return OssInstance.instance;
    }

    private OSSBean RequestToken() {
        OSSBean ossBean = null;

        return ossBean;

    }

    /**
     * 普通上传，比如image
     **/
    OSSAsyncTask task;

    public void upload(String name, byte[] bytes) {
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(bucketName, name + ".jpg", bytes);

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
//                Log.e("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
            }
        });
        task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                int statusCode = result.getStatusCode();
//                Log.d("OssManager->", "onSuccess: "+statusCode);
                callback.getPicData(true, result.getETag(),result.getServerCallbackReturnBody());
//                Log.e("aaaaaa", "result");
//                Log.e("aaaaPutObject", "UploadSuccess");
                // 只有设置了servercallback，这个值才有数据
                String serverCallbackReturnJson = result.getServerCallbackReturnBody();
//                Log.e("aaaa servercallback", serverCallbackReturnJson);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
//                Log.e("aaaaaa", "onFailure");
////                    Log.e("aaaaaa",clientExcepion.getMessage());
//                Log.e("aaaaaa", serviceException.getErrorCode());
//                Log.e("aaaaaa", request.getBucketName());
//                Log.e("aaaaaa", request.getUploadFilePath());

                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
//                    Log.e("aaaaErrorCode", serviceException.getErrorCode());
//                    Log.e("aaaaRequestId", serviceException.getRequestId());
//                    Log.e("aaaaHostId", serviceException.getHostId());
//                    Log.e("aaaaRawMessage", serviceException.getRawMessage());
                }

            }
        });
        // task.cancel(); // 可以取消任务

        task.waitUntilFinished(); // 可以等待直到任务完成
    }

    public boolean image(String name, String filePath, final List<String> addIm, final List<String> compareList) {
//            PutObjectRequest put = new PutObjectRequest(bucketName, "<objectKey>", "<uploadFilePath>");
        PutObjectRequest put = new PutObjectRequest(bucketName, name + ".jpg", filePath);

// 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
            }
        });
        if (oss == null) {
//                Log.e("TAG","OSS=NULL");

        }
        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
//                Log.e("PutObject", "UploadSuccess");
//                Log.e("ETag", result.getETag());
//                Log.e("RequestId", result.getRequestId());
//                Log.e("UploadFilePath", request.getUploadFilePath());
//                Log.e("code", result.getStatusCode() + "");
//                Log.e("resultBody", result.getServerCallbackReturnBody());
                compareList.add("1");
//                    if (addIm.size()==compareList.size()){
//                        isok =true;
//                        ((ImageShowActivity)contexts).dismissLoading();
//                    }

            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                isok = false;
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
//                    Log.e("ErrorCode", serviceException.getErrorCode());
//                    Log.e("RequestId", serviceException.getRequestId());
//                    Log.e("HostId", serviceException.getHostId());
//                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
        return isok;
    }


    /**
     * 分片上传
     **/
    public void pullFP(String filePath, String name) throws ClientException, ServiceException {
        String uploadId;
        InitiateMultipartUploadRequest init = new InitiateMultipartUploadRequest(bucketName, name);
        InitiateMultipartUploadResult initResult = oss.initMultipartUpload(init);
        uploadId = initResult.getUploadId();
        long partSize = 128 * 1024; // 设置分片大小
        int currentIndex = 1; // 上传分片编号，从1开始

        File uploadFile = new File(filePath); // 需要分片上传的文件

        InputStream input = null;
        try {
            input = new FileInputStream(uploadFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long fileLength = uploadFile.length();
        long uploadedLength = 0;
        List<PartETag> partETags = new ArrayList<PartETag>(); // 保存分片上传的结果
        while (uploadedLength < fileLength) {
            int partLength = (int) Math.min(partSize, fileLength - uploadedLength);
            byte[] partData = new byte[0]; // 按照分片大小读取文件的一段内容
            try {
                partData = IOUtils.readStreamAsBytesArray(input, partLength);
            } catch (IOException e) {
                e.printStackTrace();
            }
            UploadPartRequest uploadPart = new UploadPartRequest(bucketName, name, uploadId, currentIndex);
            uploadPart.setPartContent(partData); // 设置分片内容
            UploadPartResult uploadPartResult = null;
            try {
                uploadPartResult = oss.uploadPart(uploadPart);
            } catch (ClientException e) {
                e.printStackTrace();
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            partETags.add(new PartETag(currentIndex, uploadPartResult.getETag())); // 保存分片上传成功后的结果
            uploadedLength += partLength;
            currentIndex++;
        }

        CompleteMultipartUploadRequest complete = new CompleteMultipartUploadRequest(bucketName, name, uploadId, partETags);
        final CompleteMultipartUploadResult completeResult = oss.completeMultipartUpload(complete);
        complete.setCallbackParam(new HashMap<String, String>() {
            {

                put("callbackUrl", "<server address>");
                put("callbackBody", "<test>");
            }
        });

        ListPartsRequest listParts = new ListPartsRequest(bucketName, name, uploadId);

        ListPartsResult result = oss.listParts(listParts);

        for (int i = 0; i < result.getParts().size(); i++) {
        }

    }

    public interface picResultCallback {
        void getPicData(boolean b, String eTag, String serverCallbackReturnBody);
    }
}

