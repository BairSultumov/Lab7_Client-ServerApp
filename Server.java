import java.io.*;
import java.net.*;

class Server extends Thread
{
    Socket s;
    int num;

    public static void main(String[] args)
    {
        try
        {
            int i = 0;

            ServerSocket server = new ServerSocket(3128, 0,
                    InetAddress.getByName("localhost"));

            System.out.println("Сервер запущен");

            while(true)
            {
                new Server(i, server.accept());
                i++;
                System.out.println("На сервер поступил запрос №: "+i);
            }
        }
        catch(Exception e)
        {System.out.println("init error(main): "+e);} // вывод исключений
    }

    public Server(int num, Socket s)
    {
        this.num = num;
        this.s = s;

        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }

    public void run()
    {
        try
        {
            final DataInputStream FromServer = new DataInputStream(s.getInputStream());
            final DataOutputStream ToServer = new DataOutputStream(s.getOutputStream());

            //Расчеты с варианта
            int k = FromServer.readInt();
            int n = FromServer.readInt();
            double c = Math.sqrt(k*n);
            while( n-- > 0 ){
                c = Math.sqrt(c + k * n);
            }

            ToServer.writeInt(num);
            ToServer.writeDouble(c);
            s.close();
        }
        catch(Exception e)
        {System.out.println("init error(run): "+e);} // вывод исключений
    }
}
