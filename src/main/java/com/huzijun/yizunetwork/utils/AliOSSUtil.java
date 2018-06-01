package com.huzijun.yizunetwork.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.commons.collections.iterators.AbstractListIteratorDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author: Zhou Daoming
 * @Desciption:
 * @Date:Created in 17:34 2018/1/9
 * @Modified By:
 */
public class AliOSSUtil {
    private final static Logger log = LoggerFactory.getLogger(AliOSSUtil.class);
    private static String END_POINT = PropertiesUtil.getValueBykey("AliOSSConfig","EndPoint");
    private static String ACCESS_KEY_ID = PropertiesUtil.getValueBykey("AliOSSConfig","AccessKeyId");
    private static String ACCESS_KEY_SECRET = PropertiesUtil.getValueBykey("AliOSSConfig","AccessKeySecret");
    private static String BUCKET_NAME = PropertiesUtil.getValueBykey("AliOSSConfig","BucketName");
    // 文件访问域名
    private static String DO_MAIN_NAME = PropertiesUtil.getValueBykey("AliOSSConfig","DoMainName");

    /**
     * 方法描述:上传文件
     *
     * @param file 文件对象
     * @return
     * @author leon 2016年12月16日 上午11:40:34
     */
    public static String upload(File file, String subName) {
        if (file == null) {
            return null;
        }
        // 创建OSS客户端
        OSSClient ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            // 判断文件容器是否存在，不存在则创建
            if (!ossClient.doesBucketExist(BUCKET_NAME)) {
                ossClient.createBucket(BUCKET_NAME);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(BUCKET_NAME);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            // 创建文件名
            String fileUrl = UUID.randomUUID().toString() + subName;
           // String fileUrl = file.getName();
            //DateUtil.dateTo8String2(new Date()) + "/" + UuidUtil.getUuidByJdk(false);
            // 上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(BUCKET_NAME, fileUrl, file));
            if (null != result) {
                return DO_MAIN_NAME + fileUrl;
            }
        } catch (OSSException oe) {
            log.error(oe.getMessage());
        } catch (ClientException ce) {
            log.error(ce.getMessage());
        } finally {
            // 关闭OSS服务，一定要关闭
            ossClient.shutdown();
        }
        return null;
    }

    /**
     * 方法描述:上传文件
     *
     * @param inputStream 文件流
     * @return
     * @throws FileNotFoundException
     * @author leon 2016年12月26日 下午3:33:13
     */
    public static String upload(InputStream inputStream) throws FileNotFoundException {
        if (inputStream == null) {
            return null;
        }
        // 创建OSS客户端
        OSSClient ossClient = new OSSClient(END_POINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            // 判断文件容器是否存在，不存在则创建
            if (!ossClient.doesBucketExist(BUCKET_NAME)) {
                ossClient.createBucket(BUCKET_NAME);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(BUCKET_NAME);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            // 创建文件路径
            String fileUrl = UUID.randomUUID().toString();
            // 上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(BUCKET_NAME, fileUrl, inputStream));
            if (null != result) {
                return DO_MAIN_NAME + fileUrl;
            }
        } catch (OSSException oe) {
            log.error(oe.getMessage());
        } catch (ClientException ce) {
            log.error(ce.getMessage());
        } finally {
            // 关闭OSS服务，一定要关闭
            ossClient.shutdown();
        }
        return null;

    }
}
