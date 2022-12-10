package bsuir.lVT_lab3.Shamko.server.service;

import bsuir.lVT_lab3.Shamko.server.console.CommandReader;
import bsuir.lVT_lab3.Shamko.server.console.ResultPrinter;
import bsuir.lVT_lab3.Shamko.server.entity.ClientInfo;
import bsuir.lVT_lab3.Shamko.server.entity.StudentInfo;
import bsuir.lVT_lab3.Shamko.server.entity.criteria.Criteria;
import bsuir.lVT_lab3.Shamko.server.entity.criteria.SearchCriteria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerLogic {
    private Criteria criteria;
    private Criteria studentCriteria;
    private ResultPrinter resultPrinter;
    private ClientInfo clientInfo;
    private Server server;
    private boolean work;
    private List<Thread> threads;
    private List<StudentInfo> students;
    public ServerLogic(){
        criteria = new Criteria(SearchCriteria.Client.getCriteriaName());
        studentCriteria = new Criteria(SearchCriteria.Student.getCriteriaName());
        threads = new ArrayList<Thread>();
        server = new Server(this);
        Thread consoleReader = new CommandReader(this);
        consoleReader.start();
        resultPrinter = new ResultPrinter();
    }

    public void startConnection() throws InterruptedException, IOException {
        clientInfo = new ClientInfo();
        clientInfo.setName("");
        clientInfo.setAllowance("");
        boolean isConnect = false;
        while (!isConnect){
            isConnect = server.makeConnection();
        }
        sendData("Please, Login\n");
        work = true;
        while (work){
            String command = server.getCommand();
            Thread newCommand = new CommandHandler(command, this);
            newCommand.start();
            if (!command.equals("EXIT")){
                threads.add(newCommand);
            }
        }
        server.close();
        System.out.println("STOPPED");
    }

    public void stopConnection() throws InterruptedException, IOException {
        for (Thread thread: threads){
            thread.join();
        }
        work = false;
        server.sendClose();
    }

    public void sendData(String data) throws IOException, InterruptedException {
        server.sendData(data);
    }

    public Criteria getClientCriteria(){
        return criteria;
    }

    public ClientInfo getClientInfo(){return clientInfo; }

    public Criteria getStudentCriteria(){return studentCriteria;}
    public ResultPrinter getResultPrinter() {
        return resultPrinter;
    }
}

