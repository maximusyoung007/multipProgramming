package util;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

import java.io.File;
import java.util.Properties;

/**
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * maximus         2024/8/23      create
 */
public class SftpUtils {

    private ChannelSftp sftp;

    private Session sshSession;

    public static final String SFTP_PROTOCAL = "sftp";

    private static final int CONNECTTIMEOUT = 15000;

    private String hostIp;

    private int hostPort;

    private String username;

    private String password;

    /**
     * 建立sftp连接
     * option 表示用何种方式建立连接 1-使用密码连接 0-使用私钥文件连接, 如果不填，默认使用密码连接
     * secret 表示密码或者密钥文件的路径
     *
     * @throws JSchException
     */
    public SftpUtils(String host, int port, String username, String secret, String option) throws JSchException {
        this.hostIp = host;
        this.hostPort = port;
        this.username = username;
        System.out.println(("Connected to " + host + "."));
        JSch jsch = new JSch();
        sshSession = jsch.getSession(username, host, port);
        if (StringUtils.isEmpty(option) || "1".equals(option)) {
            this.password = secret;
            sshSession.setPassword(secret);
        } else {
            jsch.addIdentity(secret);
        }
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
//        sshConfig.put("cipher.s2c", "aes128-ctr,aes128-cbc,3des-ctr,3des-cbc,blowfish-cbc,aes192-ctr,aes192-cbc,aes256-ctr,aes256-cbc");
//        sshConfig.put("cipher.c2s", "aes128-ctr,aes128-cbc,3des-ctr,3des-cbc,blowfish-cbc,aes192-ctr,aes192-cbc,aes256-ctr,aes256-cbc");
//        sshConfig.put("kex", "diffie-hellman-group1-sha1,diffie-hellman-group14-sha1,diffie-hellman-group-exchange-sha1,diffie-hellman-group-exchange-sha256");
        sshSession.setConfig(sshConfig);
        sshSession.connect();
        ChannelSftp channel = (ChannelSftp)sshSession.openChannel("sftp");
        channel.connect();
        sftp = channel;
    }

    /**
     * 释放连接
     */
    public void disconnect() {
        if (sftp != null) {
            sftp.quit();
        }

        if (sshSession != null) {
            sshSession.disconnect();
        }
    }

    /**
     * 从SFTP下载文件到本地
     *
     * @date 2019年10月17日 下午1:54:50
     * @param downloadFile
     * @param saveFilePath
     * @return
     * @throws SftpException
     */
    public boolean download(String downloadFile, String saveFilePath) throws SftpException {

        String relativePath = saveFilePath.substring(0, saveFilePath.lastIndexOf('/') + 1);
        boolean flag;

        // 1、本地新建文件夹
        File saveFile = new File(relativePath);
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }

        // 2、下载,同名会覆盖
        String path = saveFile.getAbsolutePath();
        sftp.get(downloadFile, saveFile.getAbsolutePath());

        flag = true;

        return flag;
    }
}
