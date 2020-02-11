package com.petstore.main;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.DownloadFileRequest;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bootstrap {
    private static final Log log = LogFactory.getLog(Bootstrap.class);
    private static final String accessKeyId = "";
    private static final String accessKeySecret = "";

    public static void main(String[] args) {
        List<String> dates = new ArrayList<>();
        dates.add("20191216");
        String hzendpoint = "http://oss-cn-hangzhou.aliyuncs.com";
        String bucketNamehz = "oss-cn-hangzhou-for-openanalytics";
        OSS ossClientHz = new OSSClientBuilder().build(hzendpoint, accessKeyId, accessKeySecret);
        for (String date : dates) {
            download(date, ossClientHz, bucketNamehz);
        }
        ossClientHz.shutdown();

        String bjendpoint = "http://oss-cn-beijing.aliyuncs.com";
        String bucketNameBj = "datawarehouse-cn-beijing";
        OSS ossClientBj = new OSSClientBuilder().build(bjendpoint, accessKeyId, accessKeySecret);
        for (String date : dates) {
            upload(date, ossClientBj, bucketNameBj);
        }
        ossClientBj.shutdown();
    }

    private static void upload(String date, OSS ossClientBj, String bucketName) {
        String dir = String.format("/Users/kenzhou/Documents/aync_task/%s", date);
        File dirFile = new File(dir);
        if (!dirFile.exists()) throw new RuntimeException();
        File[] fileList = dirFile.listFiles();
        Arrays.stream(fileList).forEach(f -> {
            System.out.println(f.getName());
            String fileName = f.getName();
            String key = String.format("dladw/async_task_bak/dt=%s/%s", date, fileName);
            String filePath = dir + "/" + fileName;
            ossClientBj.putObject(bucketName, key, new File(filePath));
        });
    }

    public static void download(String date, OSS ossClientHz, String bucketName) {
        System.out.println(date);
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
        String fileKey = "datasets/dladw/async_task/dt=%s/region=cn-beijing/";
        String prefix = String.format(fileKey, date);
        listObjectsRequest.setPrefix(prefix);
        listObjectsRequest.setDelimiter("/");
        ObjectListing listing = ossClientHz.listObjects(listObjectsRequest);
        System.out.println("Objects:");
        for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
            String key = objectSummary.getKey();
            if (key.endsWith("/")) continue;
            System.out.println(key);
            String fileName = key.substring(key.lastIndexOf("/"));
            File file = new File(String.format("/Users/kenzhou/Documents/aync_task/%s", date));
            if (!file.exists()) {
                file.mkdir();
            }
            String filePath = String.format("/Users/kenzhou/Documents/aync_task/%s/%s", date, fileName);
            ossClientHz.getObject(new GetObjectRequest(bucketName, key), new File(filePath));
        }
    }
}
