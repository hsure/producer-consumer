import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.Random;
import java.util.concurrent.Semaphore;


public class ReaderThread implements Runnable{

  protected CircularFifoQueue<char> blockingQueue = null;
  protected int num;
  protected static Semaphore mutex;

  public ReaderThread(CircularFifoQueue<char> blockingQueue,int num){
    this.blockingQueue = blockingQueue;     
   	this.num = num;
  }

  @Override
  public void run() {
    BufferedReader br = null;
    Random rand = new Random();
    int randnum;
    
     try {
            br = new BufferedReader(new FileReader(new File("./inputFile.txt")));
            String buffer =null;
            while((buffer=br.readLine())!=null){
            	n = rand.nextInt(50) + 1;
                
                if((blockingQueue.maxsize()-blockingQueue.size() >= n)){
                	mutex.acquire();
                    //If this thread gets the key
                	blockingQueue.put(buffer);
                  mutex.release();
                }
                
            }
            blockingQueue.put("EOF");  //When end of file has been reached

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        } catch(InterruptedException e){

        }finally{
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


  }



}
