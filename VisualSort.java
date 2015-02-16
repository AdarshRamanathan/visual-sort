import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class VisualSort extends JFrame
{   
    public static final int ELEMENT_SIZE = 2;
    public int multiplier;
    
    public interface SortingAlgorithm
    {
        abstract public void sort(int[] array);
    }
    
    private SortingAlgorithm algorithm;
    int[] sortableArray;
    
    public VisualSort(SortingAlgorithm algorithm)
    {
        super("VisualSort");

        this.algorithm = algorithm;
        this.multiplier = 1;
        
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
        doStuff();
        repaint();
    }
    
    public VisualSort(SortingAlgorithm algorithm, int multiplier)
    {
        super("VisualSort");

        if(multiplier < 0)
            throw new RuntimeException("Cannot proceed with non-positive multiplier.");
        
        this.algorithm = algorithm;
        this.multiplier = multiplier;
        
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
        doStuff();
        repaint();
    }
    
    @Override
    public void paint(Graphics g)
    {
        int h = getBounds().height;
        int w = getBounds().width;
        
        BufferedImage bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = bufferedImage.createGraphics();
        
        h /= ELEMENT_SIZE;
        w /= ELEMENT_SIZE;
        
        for(int i = 0 ; i < w ; i++)
        {
            for(int j = 0 ; j < h ; j++)
            {
                g2d.setColor(mapColor(sortableArray[multiplier * (i * h + j)]));
                g2d.fillRect(i * ELEMENT_SIZE, j * ELEMENT_SIZE, ELEMENT_SIZE, ELEMENT_SIZE);
            }
        }
        
        ((Graphics2D)g).drawImage(bufferedImage, null, 0, 0);
    }
    
    private void populateArray()
    {
        //initialize array with values from 0 to n - 1
        sortableArray = new int[multiplier * getBounds().width * getBounds().height / (ELEMENT_SIZE * ELEMENT_SIZE)];
        for(int i = 0 ; i < sortableArray.length ; i++)
        {
            sortableArray[i] = i;
        }
        
        //fisher yates shuffle
        for(int i = sortableArray.length - 1 ; i >= 0 ; i--)
        {
            int j = (int)(Math.random() * i);
            int temp = sortableArray[i];
            sortableArray[i] = sortableArray[j];
            sortableArray[j] = temp;
        }
    }
    
    private Color mapColor(int i)
    {
        double r = (i / (double)sortableArray.length) * 2 * Math.PI;
        double g = r + 2 * Math.PI / 3;
        while(g > 2 * Math.PI)
            g -= 2 * Math.PI;
        double b = g + 2 * Math.PI / 3;
        while(b > 2 * Math.PI)
            b -= 2 * Math.PI;
        
        r = Math.sin(r) / 2 + 0.5;
        g = Math.sin(g) / 2 + 0.5;
        b = Math.sin(b) / 2 + 0.5;
        
        return new Color((float)r, (float)g, (float)b);
    }
    
    private void doStuff()
    {        
        populateArray();
        repaint();
        
        JOptionPane.showMessageDialog(this, "Start sorting?");
        
        Thread t = new Thread(){
            public void run()
            {
                algorithm.sort(sortableArray);
            }
        };
        t.setPriority(Thread.MIN_PRIORITY);
        
        long startTime = System.nanoTime();
        
        t.start();
        
        while(t.getState() != Thread.State.TERMINATED)
        {
            repaint();
            try
            {
                Thread.sleep(8);
            }
            catch(InterruptedException e)
            {
                
            }
        }
        
        long endTime = System.nanoTime();
        repaint();
        JOptionPane.showMessageDialog(this, "Done sorting " + sortableArray.length + " items. That took " + (endTime - startTime) / 1E6 + " milliseconds.");
        
        setVisible(false);
        dispose();
        System.exit(0);
    }
}