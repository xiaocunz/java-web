package net.xiaocun.entity;

import org.apache.commons.fileupload.ProgressListener;

/**
 * Created by zxiaocun on 2017/4/14.
 */
public class UploadProgressListener implements ProgressListener{
    private volatile long bytesRead = 0L;
    private volatile long contentLength = 0L;
    private volatile long item = 0L;

    public UploadProgressListener() {
        super();
    }

    public void update(long aBytesRead, long aContentLength, int anItem) {
        bytesRead = aBytesRead;
        contentLength = aContentLength;
        item = anItem;
    }

    public long getBytesRead() {
        return bytesRead;
    }

    public long getContentLength() {
        return contentLength;
    }

    public long getItem() {
        return item;
    }

}


