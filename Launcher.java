import org.apache.commons.collections4.queue;
import java.util.concurrent.Semaphore;

//Launcher class for starting the run
public class Launcher {

  public static void main(String[] args) {

    int num;
    CircularFifoQueue<char> queue = new CircularFifoQueue<char>(1024);
    static Semaphore semaphore = new Semaphore(1);

   Scanner in = new Scanner(System.in);
   int num = in.nextInt();
    ReaderThread reader = new ReaderThread(queue,num,mutex);
    WriterThread writer = new WriterThread(queue,num,mutex);

    new Thread(reader).start();
    new Thread(writer).start();

  }
