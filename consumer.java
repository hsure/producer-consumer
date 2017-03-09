import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.Random;
import java.util.concurrent.Semaphore;


public class WriterThread implements Runnable{

  protected CircularFifoQueue<char> blockingQueue = null;
  protected int num;
  protected static Semaphore mutex;

  public WriterThread(CircularFifoQueue<char> blockingQueue, int num){
    this.blockingQueue = blockingQueue;   
    this.num = num;
  }

  @Override
  public void run() {
    PrintWriter writer = null;
    Random rand = new Random();
    int randnum;

    try {
        writer = new PrintWriter(new File("outputFile.txt"));

        while(true){
            String buffer = blockingQueue.take();
            n = rand.nextInt(50) + 1;
            //If this thread gets the key
            if((blockingQueue.maxsize()-blockingQueue.size() >= n)){
                	mutex.acquire();
                    
                    //If this thread gets the key
                	blockingQueue.put(buffer);
                    
                    //Check whether end of file has been reached
           			if(buffer.equals("EOF")){ 
                		break;
            		}
            		writer.println(buffer);
                    
                    mutex.release();
                }
            
            
        }               


    } catch (FileNotFoundException e) {

        e.printStackTrace();
    } catch(InterruptedException e){

    }finally{
        writer.close();
    } 

  }

}
