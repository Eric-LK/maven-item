//package test.controller;
//
//
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import test.file.CheckMd5FileVO;
//import test.file.ChunkUploadService;
//import test.file.UploadVO;
//
//
///**
// * @description：
// * @Author: liuBing
// * @DateTime: 2021/12/21 15:16
// */
//@Api(tags = "文件上传")
//@CrossOrigin
//@RestController
//@RequestMapping("/file")
//public class FileController {
//
//    @Autowired
//    private ChunkUploadService chunkUploadService;
//
//
//    /**
//     * 文件检查
//     *
//     * @param md5FileVO
//     * @return
//     */
//    @ApiOperation("文件检查")
//    @PostMapping("check")
//    public Object check(CheckMd5FileVO md5FileVO) {
//        return chunkUploadService.check(md5FileVO);
//    }
//
//
//    /**
//     * 上传文件
//     *
//     * @param file
//     * @param uploadVO
//     * @return
//     */
//    @ApiOperation("上传文件")
//    @PostMapping("/save")
//    public Object save(@RequestParam("file") MultipartFile file, UploadVO uploadVO) {
//        Long chunk = uploadVO.getChunk();
//        if (chunk == null) {// 没有分片
//            return chunkUploadService.UnChunkUploads(file, uploadVO);
//        } else {// 分片
//            return chunkUploadService.ChunkUploads(file, uploadVO);
//        }
//
//    }
//}
