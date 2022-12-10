package bsuir.lVT_lab3.Shamko.server.main;

import bsuir.lVT_lab3.Shamko.server.service.ServerLogic;

import java.io.IOException;

public class MainServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerLogic serverLogic = new ServerLogic();
        serverLogic.startConnection();
    }
}
