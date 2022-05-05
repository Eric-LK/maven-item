package com.eric.utils.file;


import lombok.SneakyThrows;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Objects;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件压缩工具类
 */
public class FileCompressUtil {


    /**
     * 解压.tar.gz文件
     *
     * @param sourceFile 需解压文件
     * @param outputDir  输出目录
     */
    @SneakyThrows
    public static void unTarGz(String sourceFile, String outputDir) {
        try (TarInputStream tarIn = new TarInputStream(new GZIPInputStream(new BufferedInputStream(new FileInputStream(sourceFile))), 1024 * 2)) {
            createDirectory(outputDir, null);//创建输出目录
            TarEntry entry;
            while ((entry = tarIn.getNextEntry()) != null) {
                if (entry.isDirectory()) {//是目录
                    entry.getName();
                    createDirectory(outputDir, entry.getName());//创建空目录
                } else {//是文件
                    File tmpFile = new File(outputDir + "/" + entry.getName());
                    createDirectory(tmpFile.getParent() + "/", null);//创建输出目录
                    try (OutputStream out = new FileOutputStream(tmpFile)) {
                        int length = 0;
                        byte[] b = new byte[2048];
                        while ((length = tarIn.read(b)) != -1) {
                            out.write(b, 0, length);
                        }
                    }
                }
            }
        } catch (IOException ex) {
            throw new IOException("解压归档文件出现异常", ex);
        }
    }

    /**
     * 构建目录
     *
     * @param outputDir 文件目录
     * @param subDir    子目录
     */
    public static void createDirectory(String outputDir, String subDir) {
        File file = StringUtils.isEmpty(subDir) ?
                new File(outputDir) :
                new File(outputDir + "/" + subDir);

        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        if (!file.exists())
            file.mkdirs();
    }


    /**
     * @param inputFileName 你要压缩的文件夹(整个完整路径)
     * @param zipFileName   压缩后的文件(整个完整路径)
     */
    @SneakyThrows
    public static Boolean zip(String inputFileName, String zipFileName) {
        zip(zipFileName, new File(inputFileName));
        return true;
    }

    @SneakyThrows
    private static void zip(String zipFileName, File inputFile) {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        zip(out, inputFile, "");
        out.flush();
        out.close();
    }

    @SneakyThrows
    private static void zip(ZipOutputStream out, File f, String base) {
        if (f.isDirectory()) {
            File[] fl = f.listFiles();
            out.putNextEntry(new ZipEntry(base + "/"));
            base = base.length() == 0 ? "" : base + "/";
            for (int i = 0; i < Objects.requireNonNull(fl).length; i++) {
                zip(out, fl[i], base + fl[i].getName());
            }
        } else {
            out.putNextEntry(new ZipEntry(base));
            FileInputStream in = new FileInputStream(f);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
        }
    }
}
