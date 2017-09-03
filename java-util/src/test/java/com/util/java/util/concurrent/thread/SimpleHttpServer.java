package com.util.java.util.concurrent.thread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 一个基于线程池技术的Web服务器
 *
 * Web服务器用来处理Http请求,目前只能处理简单的文本和Jpg内容
 * 这个Web服务器使用main线程不断地接受客户端socket的连接,将连接以及请求提交给线程池处理
 * 这样使得Web服务器能够同时处理多个客户端请求
 */
public class SimpleHttpServer {
    //处理HttpRequest的线程池
    static ThreadPool<HttRequestHandler> threadPool = new DefaultThreadPool<HttRequestHandler>();

    static String basePath;
    static ServerSocket serverSocket;

    static int port = 8080;

    public static void setPort(int port) {
        if(port > 0) {
            SimpleHttpServer.port = port;
        }
    }

    public static void setBasePath(String basePath) {
        if(basePath != null && new File(basePath).exists() && new File(basePath).isDirectory()) {
            SimpleHttpServer.basePath = basePath;
        }
    }

    public static void start() throws Exception {

    }

    /**关闭流或者socket*/
    public static void close(Closeable... closeables) {
        if(closeables != null) {
            for(Closeable closeable : closeables) {
                try {
                    closeable.close();
                } catch (Exception ex) {

                }
            }
        }
    }

    static class HttRequestHandler implements Runnable {
        private Socket socket;
        private HttRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null;
            InputStream in = null;

            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                //由相对路径计算出绝对路径
                String filePath = basePath + header.split(" ")[1];
                out = new PrintWriter(socket.getOutputStream());
            } catch (Exception ex) {

            }
        }
    }
}
