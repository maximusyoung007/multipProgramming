package simplethreaddemo;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import util.SftpUtils;

import java.util.concurrent.CountDownLatch;

/**
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * maximus         2024/8/22      使用多线程下载文件
 */
public class FileDownloaderApp {
    public static void main(String[] args) throws SftpException, JSchException {
        String localUrl = "C:/Users/lenovo/Desktop/sftpTest/";
        String localUrl1 = "C:/Users/lenovo/Desktop/sftpTest/sftpTestMul/";

        long singleTime1 = System.currentTimeMillis();
        downloadSingleThread(localUrl);
        long singleTime2 = System.currentTimeMillis();
        System.out.println("耗时：" + (singleTime2 - singleTime1) / 1000 + "s");

        long multipTime1 = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        String fileName = "test";
        for (int i = 1; i <= 10; i++) {
            fileName = fileName + i + ".pdf";
            new Thread(new FileDownloader(fileName, localUrl1, countDownLatch)).start();
            fileName = "test";
        }
        try {
            //利用countDown计时，所有线程结束后才继续运行主线程
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long multipTime2 = System.currentTimeMillis();
        System.out.println("耗时：" + (multipTime2 - multipTime1) / 1000 + "s");
    }

    //使用单线程下载文件
    private static void downloadSingleThread(String localUrl) throws JSchException, SftpException {
        SftpUtils sftpUtils = new SftpUtils("123.207.201.195", 22, "maximus", "cpcn1234!", "1");
//        SftpUtils sftpUtils = new SftpUtils(host, port, username, password, isUsePassword);

        String path = "download/";
        String fileName = "test";

        for (int i = 1; i <= 10; i++) {
            fileName = fileName + i + ".pdf";
            sftpUtils.download(path + fileName, localUrl);
            fileName = "test";
        }

        sftpUtils.disconnect();
    }

    static class FileDownloader implements Runnable {
        private String fileName;

        private String localUrl;

        private CountDownLatch latch;

        FileDownloader(String fileName, String localUrl, CountDownLatch latch) {
            this.fileName = fileName;
            this.localUrl = localUrl;
            this.latch = latch;
        }

        @Override
        public void run() {
            SftpUtils sftpUtils = null;
            try {
                sftpUtils = new SftpUtils("123.207.201.195", 22, "maximus", "cpcn1234!", "1");
                //sftpUtils = new SftpUtils(host, port, username, password, isUsePassword);

                String path = "download/";

                sftpUtils.download(path + fileName, localUrl);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                sftpUtils.disconnect();
                //在线程结束的时候再countDown
                latch.countDown();
            }
        }
    }
}

