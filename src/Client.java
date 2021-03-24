import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Client {
    private static Socket client;
    private static DataInputStream inStream;
    private static DataOutputStream outStream;
    private static String name;

    public Client(String name) {
        this.name = name;
    }

    public void close(){
        try {
            client.close();
            inStream.close();
            outStream.close();
        } catch (IOException e) {
            System.out.println(ClientMessages.MESSAGE_CLOSE_ERROR);
        }
    }

    public void loadClient(int port){
        try {
            client = new Socket("37.77.106.12", port);//
            inStream= new DataInputStream(client.getInputStream());
            outStream=new DataOutputStream(client.getOutputStream());

            outStream.writeUTF(name);
            outStream.flush();
            System.out.println(inStream.readUTF());

        } catch (IOException e) {
            System.out.println(ClientMessages.MESSAGE_LOAD_ERROR);
        }
    }

    public void request(String requestCode,String request,String index){
        switch (requestCode){
            case Requests.add:
                add(request,index);
                break;
            case Requests.edit:
                edit(request,index);
                break;
            case Requests.get:
                get(request,index);
                break;
            case  Requests.remove:
                remove(request,index);
                break;
            case Requests.getFile:
                getFile(request,index);
                break;
            case  Requests.stop:
                stop(request,index);
            default:
                try {
                    outStream.writeUTF(request);
                    outStream.flush();
                } catch (IOException e) {
                    try {
                        System.out.println(inStream.readUTF());
                    } catch (IOException ioException) {
                        System.out.println(ClientMessages.MESSAGE_ERROR);
                    }
                }
        }
    }

    private void add(String request,String index){
        try {
            System.out.println(ClientMessages.MESSAGE_ADD);
            outStream.writeUTF(request);
            outStream.flush();
            outStream.writeUTF(index);
            outStream.flush();
            System.out.println(inStream.readUTF());
        } catch (IOException e) {
            System.out.println(ClientMessages.MESSAGE_ADD_ERROR);
        }
    }

    private void get(String request,String index){
        try {
            System.out.println(ClientMessages.MESSAGE_GET+index);
            outStream.writeUTF(request);
            outStream.flush();
            outStream.writeUTF(index);
            outStream.flush();
            System.out.println(inStream.readUTF());
        } catch (IOException e) {
            System.out.println(ClientMessages.MESSAGE_GET_ERROR);
        }
    }

    private void edit(String request,String index){
        try {
            System.out.println(ClientMessages.MESSAGE_EDIT+ index);
            outStream.writeUTF(request);
            outStream.flush();
            outStream.writeUTF(index);
            outStream.flush();
            System.out.println(inStream.readUTF());

        } catch (IOException e) {
            System.out.println(ClientMessages.MESSAGE_EDIT_ERROR);
        }

    }

    private void remove(String request,String index){
        try {
            System.out.println(ClientMessages.MESSAGE_REMOVE+index);
            outStream.writeUTF(request);
            outStream.flush();
            outStream.writeUTF(index);
            outStream.flush();
            System.out.println(inStream.readUTF());
        } catch (IOException e) {
            System.out.println(ClientMessages.MESSAGE_REMOVE_ERROR);
        }
    }

    private void getFile(String request, String index) {
        try {
            JSONObject jsonObject = new JSONObject(request);
            System.out.println(ClientMessages.MESSAGE_GET_FILE+jsonObject.getString("name"));
            outStream.writeUTF(request);
            outStream.flush();
            outStream.writeUTF(index);
            outStream.flush();
            JSONObject json = new JSONObject(inStream.readUTF());
            if (json.getString("request").equals("OK")) {
                System.out.println(json.getString("request"));
                Files.createFile(Paths.get(jsonObject.getString("name")));
                File f = new File(jsonObject.getString("name"));
                FileOutputStream w = new FileOutputStream(f);
                BufferedOutputStream bos = new BufferedOutputStream(w);
                byte[] buffer = new byte[json.getInt("file")];
                inStream.readFully(buffer, 0, buffer.length);
                bos.write(buffer);
                bos.flush();
            }
            else System.out.println(json.getString("request"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stop(String request,String index){
        try {
            System.out.println(ClientMessages.MESSAGE_STOP_SERVER);
            outStream.writeUTF(request);
            outStream.flush();
            outStream.writeUTF(index);
            outStream.flush();
        } catch (IOException e) {
            System.out.println(ClientMessages.MESSAGE_ERROR);
        }

    }


}