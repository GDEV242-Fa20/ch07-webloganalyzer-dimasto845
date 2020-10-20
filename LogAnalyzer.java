/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer ()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader("demo.Log");
    }
    
    public int numberOfAccesses() {
     
        int total = 0;
        for(int hour = 0; hour < hourCounts.length; hour++) {
        total += hourCounts[hour];
        
    }
    return total;
    }
    
    public static void main(String [] args)
    {
     LogAnalyzer analyze = new LogAnalyzer();
     analyze.analyzeHourlyData();
     int numberOfAccesses = analyze.numberOfAccesses();
     System.out.println("Number of Accesses:" + numberOfAccesses);   
    }
    
    public int busiestHour()
    {
        int maxHour =0;
        for(int hour =1; hour<hourCounts.length; hour++){
     
        if (hourCounts[hour] > hourCounts[maxHour]){
            {
                maxHour=hour;
            }
        }
        
    }
    return maxHour;
}

    int quietestHour(int[] hourCounts)
    {
     int quietest = hourCounts[0];
     for(int i=0; i< hourCounts.length; i++)
     
     {
         if(hourCounts[i] > 0 &&
         hourCounts[i] > quietest)
         quietest = hourCounts[i];
        }
        return quietest;
    }
    
    int busiestHour(int[] hourCounts)
    {
     int busiestOne =0;
     int busiestTwo =0;
     for(int n:hourCounts){
        if(busiestOne < n){
         busiestTwo = busiestOne;
         busiestOne =n;
        }
        else if(busiestTwo < n){
            busiestTwo =n;
            
        }
        
    }return busiestOne;
}

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
