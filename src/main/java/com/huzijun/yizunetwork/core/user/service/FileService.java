package com.huzijun.yizunetwork.core.user.service;

import cn.jiguang.common.utils.StringUtils;
import com.huzijun.yizunetwork.utils.AliOSSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @Author: Zhou Daoming
 * @Desciption: 图片上传服务
 * @Date:Created in 15:04 2018/4/11
 * @Modified By:
 */
@Service
public class FileService {

    private static Logger logger = LoggerFactory.getLogger(FileService.class);

    private static Integer successCount;

    private static Integer failureCount;

    private static Integer totalCount;

    public List<String> upload(HttpServletRequest request) {



        String path = request.getSession().getServletContext().getRealPath("upload");
        // 创建一个通用的多部分解析器
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        List<String> files = new ArrayList<>();
        // 判断 request 是否有文件上传,即多部分请求

        // 转换成多部分request
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        // 取得request中的所有文件名
        List<MultipartFile> list = multiRequest.getFiles("upload_file");
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        //初始化数量
        successCount = 0;
        failureCount = 0;
        totalCount = 0;
        for (int i = 0; i < list.size(); i++) {
            // 取得上传文件
            MultipartFile file = list.get(i);
            if (file != null) {
                //原始文件名
                String fileName = file.getOriginalFilename();
                //文件扩展名
                String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
                //上传后的文件名
                String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;

                logger.info("开始文件上传，原始文件名:{},上传路径{},新文件名{}:", fileName, path, uploadFileName);

                File fileDir = new File(path);

                if (!fileDir.exists()) {
                    fileDir.setWritable(true);
                    fileDir.mkdirs();
                }
                File targetFile = new File(path + "\\" + uploadFileName);

                String url = "";
                try {
                    file.transferTo(targetFile);
                    //文件上传成功
                    //将文件上传到ftp服务器

                    //Boolean result = FTPUtils.uploadFile(Lists.newArrayList(targetFile));
                    url = AliOSSUtil.upload(targetFile, fileExtensionName);
                    if (url != null) {
                        successCount++;
                        //上传完成后将本地upload文件夹里面的文件都删除
                        targetFile.delete();
                    } else {
                        failureCount++;
                    }
                } catch (IOException e) {
                    logger.info("上传文件异常", e);
                }

                files.add(url);
            }
            totalCount++;

        }
        StringBuffer sb = new StringBuffer();
        sb.append("上传总数是:" + totalCount + ",成功:{" + successCount + "}条,失败:{" + failureCount + "}条");
        files.add(sb.toString());
        return files;


    }

    public static void main(String[] args) {
        String[] str = {"1","2","3"};
        System.out.println(Arrays.toString(str));
    }
}
