package com.eric.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @description： 文件工具
 * @Author: liuBing
 * @DateTime: 2021/12/16 18:43
 */
@Slf4j
@SuppressWarnings("all")
public class FileUtil {

    private static final String DELIMITER = "-";


    /**
     * 根据文件路径获取其字节流
     *
     * @param filePath 文件路径
     * @return 字节数组
     */
    @SneakyThrows
    public static byte[] filePathToByteArray(String filePath) {
        InputStream inputStream = new FileInputStream(filePath);
        return fileToByteArray(inputStream);
    }

    /**
     * 根据文件输入流获取其字节流
     *
     * @param inputStream 文件输入流
     * @return 字节数组
     */
    @SneakyThrows
    public static byte[] fileToByteArray(InputStream inputStream) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        byte[] buffer = new byte[inputStream.available()];
        int n;
        while ((n = inputStream.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }

        return out.toByteArray();
    }

    /**
     * 根据文件路径获取文件hashcode
     *
     * @param filePath 文件路径
     * @return 文件hashcode
     */
    @SneakyThrows
    public static String getHashCodeByFilePath(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return null;
        }
        try (FileInputStream fis = new FileInputStream(filePath)) {
            // 拿到一个MD5转换器, 如果想使用SHA-1或SHA-256， 则传入SHA-1,SHA-256
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 分多次将一个文件读入，对于大型文件而言，比较推荐这种方式，占用内存比较少。
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }

            // 转换并返回包含16个元素字节数组,返回数值范围为-128到127
            byte[] md5Bytes = md.digest();
            BigInteger bigInt = new BigInteger(1, md5Bytes);//1代表绝对值
            return bigInt.toString(16);//转换为16进制
        } catch (Exception e) {
            log.error("获取文件md5的hashcode错误", e);
            return "";
        }
    }

    /**
     * 文件不分片下载
     *
     * @param filePath 文件路径
     * @param response response
     */
    @SneakyThrows
    public static void fileNotChunkDownload(String filePath, HttpServletResponse response) {
        if (StringUtils.isEmpty(filePath)) {
            return;
        }

        response.setHeader("Content-Type", "application/octet-stream");

        OutputStream outputStream = response.getOutputStream();
        outputStream.write(filePathToByteArray(filePath));
        outputStream.flush();
        outputStream.close();
    }


    /**
     * 分片文件下载
     * <p>
     * 借鉴文章：https://www.freesion.com/article/1110327673/
     *
     * @param filePath 文件路几个
     * @param startIdx 起始index
     * @param endIdx   结束index
     * @param response response
     */
    public static void fileChunkDownload(String filePath, Long startIdx, Long endIdx, HttpServletResponse response) {
        if (StringUtils.isEmpty(filePath)) {
            return;
        }

        File file = new File(filePath);
        String contentType = "application/octet-stream";
        endIdx = endIdx == null || endIdx > file.length() ? file.length() - 1 : endIdx;
        // 要下载的长度
        long contentLength = endIdx - startIdx + 1;

        // 文件名
        String fileName = file.getName();

        // 解决下载文件时文件名乱码问题
        byte[] fileNameBytes = fileName.getBytes(StandardCharsets.UTF_8);
        fileName = new String(fileNameBytes, 0, fileNameBytes.length, StandardCharsets.ISO_8859_1);

        response.setHeader("Content-Type", contentType);

        //// 各种响应头设置
        //// 支持断点续传，获取部分字节内容：
        //response.setHeader("Accept-Ranges", "bytes");
        ////http状态码要为206：表示获取部分内容
        //response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
        //response.setContentType(contentType);
        //
        ////inline表示浏览器直接使用，attachment表示下载，fileName表示下载的文件名
        //response.setHeader("Content-Disposition", "inline;filename=" + fileName);
        //response.setHeader("Content-Length", String.valueOf(contentLength));

        //已传送数据大小
        long transmitted = 0;

        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
             BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream())
        ) {
            byte[] buff = new byte[4096];
            int len = 0;
            randomAccessFile.seek(startIdx);
            // 不然会会先读取randomAccessFile，造成后面读取位置出错，找了一天才发现问题所在
            while ((transmitted + len) <= contentLength && (len = randomAccessFile.read(buff)) != -1) {
                outputStream.write(buff, 0, len);
                transmitted += len;
            }
            //处理不足buff.length部分
            if (transmitted < contentLength) {
                len = randomAccessFile.read(buff, 0, (int) (contentLength - transmitted));
                outputStream.write(buff, 0, len);
                transmitted += len;
            }

            System.out.println(outputStream);

            outputStream.flush();
            response.flushBuffer();
            randomAccessFile.close();

            log.info("下载完毕：" + startIdx + "-" + endIdx + "：" + transmitted);
        } catch (ClientAbortException e) {
            log.warn("用户停止下载：" + startIdx + "-" + endIdx + "：" + transmitted);
            //捕获此异常表示拥护停止下载
        } catch (IOException e) {
            log.error("用户下载IO异常", e);
        }
    }


    /**
     * 文件不分片上传
     *
     * @param file 文件
     */
    public static String unChunkUploads(MultipartFile file, String dirPath, String filname) {
        if (file == null || file.isEmpty()) {
            return "";
        }
        // 文件目录
        File fileDir = new File(dirPath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        // 文件上传
        String filepath = dirPath + File.separator + filname;
        File destFile = new File(filepath);
        if (destFile.exists()) {
            destFile.delete();
        }

        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            log.error("不分片 文件上传错误", e);
        }

        return filepath;
    }

    /**
     * 文件分片上传
     *
     * @param file       文件
     * @param localChunk 当前分片
     * @param filname    文件名称
     */
    @SneakyThrows
    public static void chunkUploads(MultipartFile file, String pythonBaseFilePath, Long localChunk, String filname) {
        String fileMd5 = filname;
        String fileName = fileMd5 + "." + "文件后缀名";
        Long chunks = 1L;

        // 创造分片目录
        String chunkDirPath = pythonBaseFilePath + File.separator + fileMd5;
        File chunkDir = new File(chunkDirPath);
        if (!chunkDir.exists()) {
            chunkDir.mkdirs();
        }

        // 分片文件上传
        String chunkFileName = localChunk + DELIMITER + fileName;
        String chunkFilePath = chunkDir + File.separator + chunkFileName;
        File chunkFile = new File(chunkFilePath);
        file.transferTo(chunkFile);

        // 合并分片
        Long chunkSize = 12L;
        long seek = chunkSize * localChunk;

        String destFilePath = pythonBaseFilePath + File.separator + fileName;
        File destFile = new File(destFilePath);

        if (chunkFile.length() > 0) {
            try {
                randomAccessFile(chunkFile, destFile, seek);
            } catch (IOException e) {
                log.error("分片{}合并失败：{}", chunkFile.getName(), e.getMessage());
            }
        }

        if (localChunk == chunks - 1) {
            // 删除分片文件夹
            log.info("删除分片文件夹......");
            FileUtil.deleteDirectory(chunkDirPath);
        }
    }

    /**
     * 文件合并
     */
    public static void randomAccessFile(File in, File out, Long seek) throws IOException {
        try (
                RandomAccessFile raFile = new RandomAccessFile(out, "rw");
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(in))
        ) {
            // 以读写的方式打开目标文件
            raFile.seek(seek);
            byte[] buf = new byte[1024];
            int length;
            while ((length = inputStream.read(buf)) != -1) {
                raFile.write(buf, 0, length);
            }
        }
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (File file : files) {
            // 删除子文件
            if (file.isFile()) {
                flag = deleteFile(file.getAbsolutePath());
            } else {
                // 删除子目录
                flag = deleteDirectory(file.getAbsolutePath());
            }
            if (!flag)
                break;
        }
        if (!flag)
            return false;
        // 删除当前目录
        return dirFile.delete();
    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return true;
    }


    /**
     * 保存字符串到指定路径的文件中
     *
     * @param filePath 文件路径
     * @param value    字符串内容
     */
    @SneakyThrows
    public static void saveStringFile(String filePath, String fileName, String value) {
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(filePath + fileName);
        new PrintStream(new FileOutputStream(file)).println(value);
    }
}
