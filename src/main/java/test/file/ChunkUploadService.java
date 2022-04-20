//package test.file;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//
///**
// * @description：
// *
// * 代码中对应的以 CustomConfig.xxxx 的自行更换为自己对应的文件路径
// * 代码中出现的 ErrorCodeEnum.xxxx 的自行更换为自己对应的错误提示码
// *
// * @Author: liuBing
// * @DateTime: 2021/12/21 15:16
// */
//@Service
//@Slf4j
//public class ChunkUploadService {
//
//
//    private static final String DELIMITER = "-";
//
//
//    /**
//     * 上传之前校验(整个文件、分片)
//     *
//     * @param md5FileVO
//     * @return
//     */
//    public Object check(CheckMd5FileVO md5FileVO) {
//        if (md5FileVO.getType() == null
//                || md5FileVO.getChunk() == null
//                || md5FileVO.getFileMd5() == null
//                || md5FileVO.getSuffix() == null
//                | md5FileVO.getFileName() == null) {
//            return Result.error(ErrorCodeEnum.MISSING_REQUIRED_ARGUMENTS);
//        }
//        Integer type = md5FileVO.getType();
//        Long chunk = md5FileVO.getChunk();
//        String fileName = md5FileVO.getFileMd5() + "." + md5FileVO.getSuffix();
//        Long fileSize = md5FileVO.getFileSize();
//        if (type == 0) {// 未分片校验
//            String destFilePath = CustomConfig.fileSave + File.separator + fileName;
//            File destFile = new File(destFilePath);
//            if (destFile.exists() && destFile.length() == fileSize) {
//                Map<String, Object> map = new HashMap<>();
//                map.put("url", fileName);
//                map.put("size", fileSize);
//                return Result.ok(map);
//            } else {
//                return Result.error(ErrorCodeEnum.FILE_NOT_EXISTS);
//            }
//        } else {// 分片校验
//            String fileMd5 = md5FileVO.getFileMd5();
//            String destFileDir = CustomConfig.fileSave + File.separator + fileMd5;
//            String destFileName = chunk + DELIMITER + fileName;
//            String destFilePath = destFileDir + File.separator + destFileName;
//            File destFile = new File(destFilePath);
//            if (destFile.exists() && destFile.length() == fileSize) {
//                return Result.error(ErrorCodeEnum.CHUNK_EXISTS);
//            } else {
//                return Result.error(ErrorCodeEnum.CHUNK_NOT_EXISTS);
//            }
//        }
//    }
//
//    /**
//     * 分片上传
//     */
//    public Object ChunkUploads(MultipartFile file, UploadVO uploadVO) {
//        String fileMd5 = uploadVO.getFileMd5();
//        String fileName = fileMd5 + "." + uploadVO.getSuffix();
//        Long chunk = uploadVO.getChunk();// 当前片
//        Long chunks = uploadVO.getChunks();// 总共多少片
//
//        // 分片目录创建
//        String chunkDirPath = CustomConfig.fileDispose + File.separator + fileMd5;
//        File chunkDir = new File(chunkDirPath);
//        if (!chunkDir.exists()) {
//            chunkDir.mkdirs();
//        }
//        // 分片文件上传
//        String chunkFileName = chunk + DELIMITER + fileName;
//        String chunkFilePath = chunkDir + File.separator + chunkFileName;
//        File chunkFile = new File(chunkFilePath);
//        try {
//            file.transferTo(chunkFile);
//        } catch (Exception e) {
//            log.error("分片上传出错", e);
//            // return Result.error(ErrorCodeEnum.CHUNK_UPLOAD_ERROR);
//        }
//        // 合并分片
//        Long chunkSize = uploadVO.getChunkSize();
//        long seek = chunkSize * chunk;
//        String destFilePath = CustomConfig.fileSave + File.separator + fileName;
//        File destFile = new File(destFilePath);
//        if (chunkFile.length() > 0) {
//            try {
//                System.out.println("合并中......");
//                FileUtil.randomAccessFile(chunkFile, destFile, seek);
//            } catch (IOException e) {
//                log.error("分片{}合并失败：{}", chunkFile.getName(), e.getMessage());
//                // return Result.error(ErrorCodeEnum.CHUNK_MERGE_FAIL);
//            }
//        }
//        if (chunk == chunks - 1) {
//            // 删除分片文件夹
//            System.out.println("删除分片文件夹......");
//            FileUtil.deleteDirectory(chunkDirPath);
//            Map<String, Object> map = new HashMap<>();
//            map.put("url", fileName);
//            // return Result.ok(map);
//        } else {
//            // return Result.error(ErrorCodeEnum.UPLOADING);
//        }
//    }
//
//
//    /**
//     * 未分片上传
//     *
//     * @param file
//     * @param uploadVO
//     * @return
//     */
//    public Object UnChunkUploads(MultipartFile file, UploadVO uploadVO) {
//        String suffix = uploadVO.getSuffix();
//        String fileName = uploadVO.getFileMd5() + "." + suffix;
//        // 文件上传
//        File destFile = new File(CustomConfig.fileSave + File.separator + fileName);
//        if (file != null && !file.isEmpty()) {
//            // 上传目录
//            File fileDir = new File(CustomConfig.fileSave);
//            if (!fileDir.exists()) {
//                fileDir.mkdirs();
//            }
//            if (destFile.exists()) {
//                destFile.delete();
//            }
//            try {
//                file.transferTo(destFile);
//                Map<String, Object> map = new HashMap<>();
//                map.put("url", fileName);
//                return Result.ok(map);
//            } catch (Exception e) {
//                log.error("文件上传出错", e);
//                return Result.error(ErrorCodeEnum.FILE_UPLOAD_ERROR);
//            }
//        }
//        return Result.error(ErrorCodeEnum.UPLOAD_FAIL);
//    }
//
//}