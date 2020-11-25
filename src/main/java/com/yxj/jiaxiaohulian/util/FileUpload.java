package com.yxj.jiaxiaohulian.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class FileUpload {

    //文件存储在项目的target下边的一个目录
    /*public static String upload(MultipartFile file) throws IOException {
        //获取原始文件名
        String oldFileName = file.getOriginalFilename();
        //获取文件的后缀
        String extension = "." + FilenameUtils.getExtension(file.getOriginalFilename());
        //生成新的文件名称
        String newFileName=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ UUID.randomUUID().toString().replace("-","")+extension;
        //文件大小
        Long size=file.getSize();
        //文件类型
        String type = file.getContentType();
        //处理根据日期生成目录
        String realPath= ResourceUtils.getURL("classpath:").getPath()+"/static/files";
        String dataFormat=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dateDirPath=realPath+"/"+dataFormat;
        File dateDir=new File(dateDirPath);
        if (!dateDir.exists()){
            dateDir.mkdirs();
        }
        //处理文件上传
        file.transferTo(new File(dateDir,newFileName));
        //返回的路径相对于项目的相对路径，用于存储到数据库
        return "files/"+dataFormat+"/"+newFileName;
    }*/


    //把文件存储路径引到E盘的一个绝对路径
    public static String upload(MultipartFile file) {
        //下边这段代码测试还没通过
        /*//获取原始文件名
        String oldFileName = file.getOriginalFilename();
        //获取文件的后缀
        String extension = "." + FilenameUtils.getExtension(file.getOriginalFilename());
        //生成新的文件名称
        String newFileName=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ UUID.randomUUID().toString().replace("-","")+extension;
        //文件大小
        Long size=file.getSize();
        //文件类型
        String type = file.getContentType();
        //处理根据日期生成目录
//        String realPath= ResourceUtils.getURL("classpath:").getPath()+"/static/files";
        String realPath= "E:/idea/idea2019/idea_workspace/ABiYeSheJi/jiaxiaohulianFilePath/static/files";
        String dataFormat=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dateDirPath=realPath+"/"+dataFormat;
        File dateDir=new File(dateDirPath);
        if (!dateDir.exists()){
            dateDir.mkdirs();
        }
        //处理文件上传
        file.transferTo(new File(dateDir,newFileName));
        //返回的路径相对于项目的相对路径，用于存储到数据库
//        return "files/"+dataFormat+"/"+newFileName;
        return "files/"+dataFormat+"/"+newFileName;*/

        if (file==null||file.getSize()==0L){ //如果file大小为0或null就返回null
            return null;
        }
        //下边这个也能用
        String fileName = file.getOriginalFilename();
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int height = bufferedImage.getHeight();
        int width = bufferedImage.getWidth();
        if(height == 0 || width ==0){
            return null;
        }

        String dateDir = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String picPath="/images/"+ dateDir.replace("/", "");
        String localPath = "E:/idea/idea2019/idea_workspace/ABiYeSheJi/jiaxiaohulianFilePath" +picPath;

        File picFile = new File(localPath);
        if(!picFile.exists()){
            picFile.mkdirs();
        }
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        long time = System.currentTimeMillis();
        String realPath = localPath + "/" + time + uuid + fileType;
        try {
            file.transferTo(new File(realPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picPath+ "/" + time +uuid + fileType;
    }
}











