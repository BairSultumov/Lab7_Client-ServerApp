import java.io.*;
import java.net.*;
import java.util.Scanner;

class Client extends Thread
{
    public static void main(String[] args)
    {
        try
        {
            Scanner scan=new Scanner(System.in);
            Socket s = new Socket("127.0.0.1", 3128);

            final DataInputStream InFromClient = new DataInputStream(s.getInputStream());
            final DataOutputStream OutToClient = new DataOutputStream(s.getOutputStream());

            System.out.println("Введите k и n");
            int k=scan.nextInt();
            int n=scan.nextInt();

            OutToClient.writeInt(k);
            OutToClient.writeInt(n);

            int i=InFromClient.readInt();
            Double c=InFromClient.readDouble();

            System.out.println(i+":\nanswer: "+c);
        }
        catch(Exception e)
        {System.out.println("init error: "+e);} // вывод исключений
    }
}
