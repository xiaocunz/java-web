package net.xiaocun.controller;

import net.xiaocun.entity.ResponseEntity;
import net.xiaocun.entity.UploadProgressListener;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zxiaocun on 2017/4/14.
 */
@Controller
public class FileUploadDownloadController {
    // 保存上传文件的路径
    private final String UPLOAD_DIRECTORY = "D:\\xiaocun\\study";
    // 记录上传进入的session名称
    private final String UPLOAD_PROGRESS_LISTENER = "upload_progress_listener";
    // 下载文件路径
    private final String DOWNLOAD_FILE = "D:\\xiaocun\\study\\KMS10_Crack2.rar";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String printWelcome() {
        return "hello";

    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> upload(HttpServletRequest request) {
        ResponseEntity<Boolean> response = new ResponseEntity<Boolean>();

        boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);

        if (!isMultipartContent){
            response.setStatusInfo(ResponseEntity.StatusInfo.REQUEST_ERROR);
            return response;
        }

        FileItemFactory factory = new DiskFileItemFactory();

        ServletFileUpload upload = new ServletFileUpload(factory);

        UploadProgressListener listener = new UploadProgressListener();
            HttpSession session = request.getSession();
        session.setAttribute(UPLOAD_PROGRESS_LISTENER, listener);
        upload.setProgressListener(listener);

        try {
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iterator = items.iterator();

            while (iterator.hasNext()){

                FileItem fileItem = iterator.next();

                // 判断当前的fileItem是表单还是文件
                boolean isFormField = fileItem.isFormField();


                if (isFormField){
                    response.setStatusInfo(ResponseEntity.StatusInfo.REQUEST_ERROR);
                    return response;
                }else {
                    // 如果html页面上可以上传三个文件，而我只填写了一个文件，这样就会导致获取不到文件名，从而导致new File的时候报错
                    String fileName = fileItem.getName();
                    if (null == fileName || fileName.length() == 0) {
                        continue;
                    }
                    // 将文件保存到指定的目录
                    File saveFile = new File(UPLOAD_DIRECTORY,fileName);
                    fileItem.write(saveFile);
                }
            }


        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @RequestMapping(value = "/upload/progress", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Long> uploadProgress(HttpServletRequest request) {
        ResponseEntity<Long> response = new ResponseEntity();
        HttpSession session = request.getSession();
        if (null == session) {
            return null;
        }
        UploadProgressListener uploadProgressListener = (UploadProgressListener) session.getAttribute(UPLOAD_PROGRESS_LISTENER);
        if (null == uploadProgressListener) {
            return null;
        }
        long total = uploadProgressListener.getContentLength();
        long readed = uploadProgressListener.getBytesRead();
        long percent = readed / total * 100;
        response.setData(percent);
        return response;
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    @ResponseBody
    public org.springframework.http.ResponseEntity<byte[]> download() {
        org.springframework.http.ResponseEntity<byte[]> response = null;
        // TODO 资源释放
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {

            InputStream inputStream = new FileInputStream(DOWNLOAD_FILE);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            out.write(b);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", DOWNLOAD_FILE);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            response = new org.springframework.http.ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.OK);
        } catch (IOException e) {
        }
        return response;
    }

}
