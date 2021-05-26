import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

//import jdk.internal.org.jline.utils.InputStreamReader;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) throws IOException {
        
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in)) ;
        String[] str = read.readLine().trim().split(" ") ;
        int n = Integer.parseInt(str[0]) ;
        int k = Integer.parseInt(str[1]) ;
        str = read.readLine().trim().split(" ") ;
        int[] arr = new int[n] ;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]) ;
        }

        Deque<Integer> dqMax = new LinkedList<>() ;
        Deque<Integer> dqMin = new LinkedList<>() ;
        long sum = 0L ;
        for(int i=0; i<k; i++) {
            while (!dqMax.isEmpty() && arr[dqMax.peekLast()] <= arr[i]) {
                dqMax.pollLast() ;
            }
            dqMax.addLast(i);

            while (!dqMin.isEmpty() && arr[dqMin.peekLast()] >= arr[i]) {
                dqMin.pollLast() ;
            }
            dqMin.addLast(i);
        }

        for(int i=k; i<n; i++) {
            sum += arr[dqMax.peekFirst()] + arr[dqMin.peekFirst()] ;
            //System.out.println(sum);
            
            while (!dqMax.isEmpty() && dqMax.peekFirst() <= i-k) {
                dqMax.pollFirst() ;
            }
            while (!dqMin.isEmpty() && dqMin.peekFirst() <= i-k) {
                dqMin.pollFirst() ;
            }

            while (!dqMax.isEmpty() && arr[dqMax.peekLast()] <= arr[i]) {
                dqMax.pollLast() ;
            }
            dqMax.addLast(i);

            while (!dqMin.isEmpty() && arr[dqMin.peekLast()] >= arr[i]) {
                dqMin.pollLast() ;
            }
            dqMin.addLast(i);
        }
        sum += arr[dqMax.peekFirst()] + arr[dqMin.peekFirst()] ;
        
        System.out.print(sum) ;

    }
}