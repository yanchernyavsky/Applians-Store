package ClientFolder.sample;

import ClientFolder.sample.DatabaseTables.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientApp extends Thread{

    Socket socket;
    ObjectOutputStream Outputstream;
    ObjectInputStream  Inputstream;
    public static Integer AdminOrClientId;

    public void InitSocketAndStreams(){
        try
        {
            // открываем сокет и коннектимся к localhost:3128
            // получаем сокет сервера
            socket = new Socket("localhost", 1224);
            Outputstream = new ObjectOutputStream(socket.getOutputStream());
            Inputstream = new ObjectInputStream(socket.getInputStream());
        }
        catch(Exception e)
        {System.out.println("init error: "+e);} // вывод исключений
    }
    public void CloseStreamsAndSocket(){
        try {
            Outputstream.close();//закрытие потока вывода
            Inputstream.close();//закрытие потока ввода
            socket.close();//закрытие сокета
        }
        catch(Exception e)
        {System.out.println("init error: "+e);} // вывод исключений)
    }

    public Boolean Send(String command, User object) {
        Boolean answer = new Boolean(false);
        InitSocketAndStreams();
        try {
            Outputstream.writeObject(command);
            Inputstream.readObject();
            Outputstream.writeObject(object);
            answer = (Boolean) Inputstream.readObject();
        } catch (Exception e) {
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
        return answer;
        }

    public String SendAuth(String command, User object) {
        String answer ="";
        InitSocketAndStreams();
        try {
            Outputstream.writeObject(command);
            Inputstream.readObject();
            Outputstream.writeObject(object);
            answer = (String) Inputstream.readObject();
            AdminOrClientId = (Integer)Inputstream.readObject();
        } catch (Exception e) {
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
        return answer;
    }

    public void Send(String command, Provider object) {
        InitSocketAndStreams();
        try {
            Outputstream.writeObject(command);
            Inputstream.readObject();
            Outputstream.writeObject(object);
        } catch (Exception e) {
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
    }

    public void Send(String command, Category object) {
        InitSocketAndStreams();
        try {
            Outputstream.writeObject(command);
            Inputstream.readObject();
            Outputstream.writeObject(object);
        } catch (Exception e) {
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
    }

    public void Send(String command, Product object) {
        InitSocketAndStreams();
        try {
            Outputstream.writeObject(command);
            Inputstream.readObject();
            Outputstream.writeObject(object);
        } catch (Exception e) {
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
    }

    public void Send(String command, Client object) {
        InitSocketAndStreams();
        try {
            Outputstream.writeObject(command);
            Inputstream.readObject();
            Outputstream.writeObject(object);
        } catch (Exception e) {
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
    }

    public void Send(String command, Order object) {
        InitSocketAndStreams();
        try {
            Outputstream.writeObject(command);
            Inputstream.readObject();
            Outputstream.writeObject(object);
        } catch (Exception e) {
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
    }

    public void SendCommand(String command) {
        InitSocketAndStreams();
        try {
            Outputstream.writeObject(command);
            Inputstream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        CloseStreamsAndSocket();
    }

    public boolean Send(String command, Integer Id) {
        if (AdminOrClientId == 0 )
            return false;
        InitSocketAndStreams();
        try {
            Outputstream.writeObject(command);
            Inputstream.readObject();
            Outputstream.writeObject(Id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        CloseStreamsAndSocket();
        return true;
    }

    public boolean Send(String command, String CathName) {
        if (AdminOrClientId == 0 )
            return false;
        InitSocketAndStreams();
        try {
            Outputstream.writeObject(command);
            Inputstream.readObject();
            Outputstream.writeObject(CathName);
        } catch (Exception e) {
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
        return true;
    }

    public ArrayList<Provider> SendArrayProvider(String command){
        ArrayList<Provider> array=new ArrayList<Provider>();
        InitSocketAndStreams();
        try{
            Outputstream.writeObject(command);
            Inputstream.readObject();
            array=(ArrayList<Provider>)Inputstream.readObject();
        }
        catch(Exception e){
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
        return array;
    }

    public ArrayList<Category> SendArrayCategory(String command){
        ArrayList<Category> array=new ArrayList<Category>();
        InitSocketAndStreams();
        try{
            Outputstream.writeObject(command);
            Inputstream.readObject();
            array=(ArrayList<Category>)Inputstream.readObject();
        }
        catch(Exception e){
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
        return array;
    }

    public ArrayList<Product> SendArrayProduct(String command){
        ArrayList<Product> array=new ArrayList<Product>();
        InitSocketAndStreams();
        try{
            Outputstream.writeObject(command);
            Inputstream.readObject();
            array=(ArrayList<Product>)Inputstream.readObject();
        }
        catch(Exception e){
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
        return array;
    }

    public ArrayList<Product> SendArrayProduct(String command, Integer Number, Integer Direction){
        ArrayList<Product> array=new ArrayList<Product>();
        InitSocketAndStreams();
        try{
            Outputstream.writeObject(command);
            Inputstream.readObject();
            Outputstream.writeObject(Number);
            Outputstream.writeObject(Direction);
            array=(ArrayList<Product>)Inputstream.readObject();
        }
        catch(Exception e){
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
        return array;
    }

    public ArrayList<Client> SendArrayClient(String command){
        ArrayList<Client> array=new ArrayList<Client>();
        InitSocketAndStreams();
        try{
            Outputstream.writeObject(command);
            Inputstream.readObject();
            array=(ArrayList<Client>)Inputstream.readObject();
        }
        catch(Exception e){
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
        return array;
    }

    public ArrayList<Order> SendArrayOrder(String command){
        ArrayList<Order> array=new ArrayList<Order>();
        InitSocketAndStreams();
        try{
            Outputstream.writeObject(command);
            Inputstream.readObject();
            array=(ArrayList<Order>)Inputstream.readObject();
        }
        catch(Exception e){
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
        return array;
    }

    public ArrayList<Order> SendArrayClientOrder(String command){
        ArrayList<Order> array=new ArrayList<Order>();
        InitSocketAndStreams();
        try{
            Outputstream.writeObject(command);
            Inputstream.readObject();
            Outputstream.writeObject(AdminOrClientId);
            array=(ArrayList<Order>)Inputstream.readObject();
        }
        catch(Exception e){
            e.printStackTrace();//выполнение метода исключения е
        }
        CloseStreamsAndSocket();
        return array;
    }
    public ArrayList<User> SendArrayUsers(String command)
    {
        ArrayList<User> array = new ArrayList<>();
        InitSocketAndStreams();
        try
        {
            Outputstream.writeObject(command);
            Inputstream.readObject();
            array = (ArrayList<User>) Inputstream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return array;
    }

    public void ChangeUserRole(User user)
    {
        String cmd = "UpdateUser";
        InitSocketAndStreams();
        try
        {
            Outputstream.writeObject(cmd);
            Outputstream.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
