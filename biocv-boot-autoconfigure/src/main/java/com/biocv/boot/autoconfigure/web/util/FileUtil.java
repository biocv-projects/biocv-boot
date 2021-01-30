package com.biocv.boot.autoconfigure.web.util;

import cn.hutool.core.date.DateUtil;
import com.biocv.boot.exception.BasicException;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.Objects;

/**
 * 文件操作工具类
 *
 * @author Tyler.Feng
 * @date 2021-01-29 11:36
 * @since 1.0.0
 */
public class FileUtil {

    public static final String uploadFilePath = "upload";
    public static final char separator = '/';
    public static String systemFilePath = "systemFile";

    /**
     * 保存文件到服务器
     *
     * @param module
     * @param business
     * @param fileName
     * @param uploadFile
     * @return java.lang.String
     * @throws
     * @author Tyler.Feng
     * @date  2021-01-29 11:35
     * @since 1.0.0
     */
    public static String saveFileToServer(String module, String business, String fileName, MultipartFile uploadFile) {
        if (StringUtils.hasLength(fileName)) {
            String filePath = createUploadFileRootPath(module, business);
            saveFile(filePath, fileName, uploadFile);
            fileName = separator + filePath + fileName;
        }
        return fileName;
    }


    public static String createUploadFileRootPath(String module, String business) {
        return uploadFilePath + separator + module + separator + business + separator + DateUtil.formatDate(new Date()) + separator;
    }

    public static void saveFile(String filePath, String fileName, MultipartFile uploadFile) {
        try {
            if (Objects.nonNull(uploadFile) && !uploadFile.isEmpty()) {
                String fullPath = systemFilePath + separator + filePath;
                File file = new File(new File(fullPath), fileName);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                if (file.exists()) {
                    file.delete();
                }
                uploadFile.transferTo(new File(file.getAbsolutePath()));
            }
        } catch (Exception e) {
            new BasicException("-------------  Save  File  Error :" + e.getMessage());
        }
    }


}
