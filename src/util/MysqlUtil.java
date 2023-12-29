package util;

import java.io.*;

public class MysqlUtil {
    //备份的方法，通过runtime调用进行备份
    public static void backup(String mysqlPath, String backupfile) throws IOException {
        String commandFormat = "%s/bin/mysqldump -u%s -p%s   -hlocalhost   -P%d %s -r %s";
//        -hlocalhost：这部分表示MySQL数据库的主机地址，本例中设为本地主机。
//        -P%d：这部分表示MySQL数据库的端口号，%d 将会被替换为具体的 DBUtil.port 变量的值。
//        %s：这部分表示要备份的数据库名，%s 将会被替换为具体的 DBUtil.database 变量的值。
//        -r \"%s\"：这部分表示备份文件的路径和名称，%s 将会被替换为具体的 backupfile 变量的值，形成完整的文件路径和名称。
//        \是转义字符，让"保持原意

        String command = String.format(commandFormat, mysqlPath, DBUtil.loginName, DBUtil.password, DBUtil.port,
                DBUtil.database, backupfile);
        Runtime.getRuntime().exec(command); //执行命令行命令
    }


    // 通过Runtime调用mysql进行数据的还原
    public static void recover(String mysqlPath, String recoverfile) {
        try {
            String commandFormat = "\"%s/bin/mysql\" -u%s -p%s   %s ";
            String command = String.format(commandFormat, mysqlPath, DBUtil.loginName, DBUtil.password,
                    DBUtil.database);

            Process p = Runtime.getRuntime().exec(command);


            OutputStream out = p.getOutputStream();  //输出流


            String inStr;  //读取恢复文件
            StringBuffer sb = new StringBuffer(""); //字符缓冲区
            String outStr;  //转换读取的恢复文件
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(recoverfile), "utf8"));
            while ((inStr = br.readLine()) != null) {  //读取恢复文件的每一行br.readLine，并且加入字符串缓冲区sb
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString(); //把字符串缓冲区中的内容转换为字符串 outStr

            OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
            writer.write(outStr);  //使用输出流写入器将字符串 outStr 写入输出流 out。
            writer.flush();
            out.close();
            br.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        String mysqlPath = "/usr/local/mysql-8.2.0-macos13-arm64";
        String file = "";

        // backup(mysqlPath, file);
        // restore();
        // recover(mysqlPath, file);
        // recover(file);
        recover(mysqlPath, file);

    }

}
