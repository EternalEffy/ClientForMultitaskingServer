import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(ClientMessages.MESSAGE_SET_NAME);
        Scanner console = new Scanner(System.in);
        Client client = new Client(console.nextLine());
        client.loadClient(3312);


        /*for(int i=0;i<25;i++){
            client.request(Requests.get,"{\"request\":\""+Requests.get+"\"," +
                    "\"userData\":"+"[\""+ Generator.generateUserName()+"\",\""+Generator.generateAge()+"\",\""+Generator.generateScore()+"\",\""+Generator.generateLevel()+"\"]}",String.valueOf(i));
        }

        client.request(Requests.getFile,"{\"request\":\""+Requests.getFile+"\"," +
                "\"name\":\"pornhub.mp4\"}","0");
                client.request(Requests.getFile,"{\"request\":\""+Requests.getFile+"\"," +
                "\"name\":\"pornhub.mp4\"}","0");

        client.request(Requests.stop,"{\"request\":\""+Requests.stop+"\"," +
                "\"name\":\"pornhub.mp4\"}","0");
                client.request(Requests.stop,"{\"request\":\""+Requests.stop+"\"," +
                "\"name\":\"pornhub.mp4\"}","0");

         */



        client.close();
    }
}
