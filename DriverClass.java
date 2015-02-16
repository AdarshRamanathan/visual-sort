public class DriverClass
{
    private static VisualSort.SortingAlgorithm selectionSort = new VisualSort.SortingAlgorithm() {
       public void sort(int[] arr)
       {
           for(int i = 0 ; i < arr.length ; i++)
           {
               int min = i;
               for(int j = i + 1 ; j < arr.length ; j++)
               {
                   if(arr[j] > arr[min])
                   {
                       min = j;
                   }
               }
               int temp = arr[min];
               arr[min] = arr[i];
               arr[i] = temp;
           }
       }
    };
    
    private static VisualSort.SortingAlgorithm bubbleSort = new VisualSort.SortingAlgorithm() {
       public void sort(int[] arr)
       {
           for(int i = 0 ; i < arr.length ; i++)
           {
               for(int j = 0 ; j < arr.length - i - 1 ; j++)
               {
                   if(arr[j] > arr[j + 1])
                   {
                       int temp = arr[j];
                       arr[j] = arr[j + 1];
                       arr[j + 1] = temp;
                   }
               }
           }
       }
    };
   
    private static VisualSort.SortingAlgorithm mergeSort = new VisualSort.SortingAlgorithm() {
       public void sort(int[] arr)
       {
           mergeSort(arr, 0, arr.length);
       }
       
       private void mergeSort(int[] arr, int lb, int ub)
       {
           if(ub - lb <= 1)
               return;
           
           int mid = (lb + ub) / 2;
           mergeSort(arr, lb, mid);
           mergeSort(arr, mid, ub);
           merge(arr, lb, mid, ub);
       }
       
       private void merge(int[] arr, int lb, int mid, int ub)
       {
           int[] l = new int[mid - lb];
           int[] r = new int[ub - mid];
           
           for(int x = 0 ; x < l.length ; x++)
           {
               l[x] = arr[lb + x];
           }
           
           for(int x = 0 ; x < r.length ; x++)
           {
               r[x] = arr[mid + x];
           }
           
           int i = 0;
           int j = 0;
           
           while(i < l.length && j < r.length)
           {
               if(l[i] <= r[j])
               {
                   arr[lb + i + j] = l[i];
                   i++;
               }
               else
               {
                   arr[lb + i + j] = r[j];
                   j++;
               }
           }
           
           while(i < l.length)
           {
               arr[lb + i + j] = l[i];
               i++;
           }
           
           while(j < r.length)
           {
               arr[lb + i + j] = r[j];
               j++;
           }
       }
    };
   
    private static VisualSort.SortingAlgorithm quickSort = new VisualSort.SortingAlgorithm() {
       public void sort(int[] arr)
       {
           quicksort(arr, 0, arr.length);
       }
       
       private void quicksort(int[] arr, int lb, int ub)
       {   
           if(ub - lb <= 1)
               return;
           
           int i = lb;
           int j = ub;
           
           while(i < j)
           {
               i++;
               while(i < ub - 1 && arr[i] < arr[lb])
               {
                   i++;
               }
               
               j--;
               while(j > lb && arr[j] > arr[lb])
               {
                   j--;
               }
               
               int temp = arr[i];
               arr[i] = arr[j];
               arr[j] = temp;
           }
           
           int temp = arr[i];
           arr[i] = arr[j];
           arr[j] = temp;
           
           temp = arr[lb];
           arr[lb] = arr[j];
           arr[j] = temp;
           
           quicksort(arr, lb, j);
           quicksort(arr, j + 1, ub);
       }
    };
   
    private static VisualSort.SortingAlgorithm threadedQuickSort = new VisualSort.SortingAlgorithm() {
        public void sort(int[] arr)
        {
            quicksort(arr, 0, arr.length);
        }
       
        private void quicksort(int[] arr, int lb, int ub)
        {   
            if(ub - lb <= 1)
                return;
           
            int i = lb;
            int j = ub;
           
            while(i < j)
            {
                i++;
                while(i < ub - 1 && arr[i] < arr[lb])
                {
                    i++;
                }
               
                j--;
                while(j > lb && arr[j] > arr[lb])
                {
                    j--;
                }
                 
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
           
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            
            temp = arr[lb];
            arr[lb] = arr[j];
            arr[j] = temp;
             
            int threadJ = j;
            Thread t1 = null;
            Thread t2 = null;
            
            if(j - lb > 1E6)
            {
                t1 = new Thread() {
                    public void run()
                    {
                        quicksort(arr, lb, threadJ);
                    }
                };
                
                t1.start();
            }
            else
            {
                quicksort(arr, lb, j);
            }
           
            if(ub - j > 1E6)
            {
                t2 = new Thread() {
                    public void run()
                    {
                        quicksort(arr, threadJ + 1, ub);
                    }
                };
               
                t2.start();
            }
            else
            {
                quicksort(arr, j + 1, ub);
            }
           
            if(t1 != null)
            {
                try
                {
                    t1.join();
                }
                 catch(InterruptedException e)
                {
                    
                }
            }
            
            if(t2 != null)
            {
                try
                {
                    t2.join();
                }
                catch(InterruptedException e)
                {
                    
                }
            }
        }
    };
   
    private static VisualSort.SortingAlgorithm insertionSort = new VisualSort.SortingAlgorithm() {
        public void sort(int[] arr)
        {
            insertionsort(arr, 0, arr.length);
        }
        
        public void insertionsort(int[] arr, int lb, int ub)
        {
            for(int i = lb + 1 ; i < ub ; i++)
            {
                int j = i;
                while(j > lb && arr[j - 1] > arr[j])
                {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    j--;
                }
                
                Thread.yield();
            }
        }
    };
    
    private static VisualSort.SortingAlgorithm heapSort = new VisualSort.SortingAlgorithm() {
        public void sort(int[] arr)
        {
            heapify(arr, arr.length);
            
            int end = arr.length - 1;
            
            while(end > 0)
            {
                int temp = arr[end];
                arr[end] = arr[0];
                arr[0] = temp;
                
                end--;
                
                siftDown(arr, 0, end + 1);
            }
        }
        
        private void heapify(int[] arr, int count)
        {
            int start = (count - 2) / 2;
            
            while(start >= 0)
            {
                siftDown(arr, start, count);
                start --;
            }
        }
        
        private void siftDown(int[] arr, int lb, int ub)
        {
            int root = lb;
            
            while(root * 2 + 1 < ub)
            {
                int child = root * 2 + 1;
                int swap = root;
                
                if(arr[swap] < arr[child])
                {
                    swap = child;
                }
                
                if(child + 1 < ub && arr[swap] < arr[child + 1])
                {
                    swap = child + 1;
                }
                
                if(swap == root)
                {
                    return;
                }
                
                else
                {
                    int temp = arr[root];
                    arr[root] = arr[swap];
                    arr[swap] = temp;
                    
                    root = swap;
                }
            }
        }
    };
    
    private static VisualSort.SortingAlgorithm hybridSort = new VisualSort.SortingAlgorithm() {
        public void sort(int[] arr)
        {
            quicksort(arr, 0, arr.length);
        }
        
        private void insertionsort(int[] arr, int lb, int ub)
        {
            for(int i = 1 ; i < ub - lb ; i++)
            {
                int j = i;
                while(j > 0 && arr[lb + j - 1] > arr[lb + j])
                {
                    int temp = arr[lb + j];
                    arr[lb + j] = arr[lb + j - 1];
                    arr[lb + j - 1] = temp;
                    j--;
                }
                
                Thread.yield();
            }
        }
        
        private void quicksort(int[] arr, int lb, int ub)
        {   
            if(ub - lb <= 1)
                return;
           
            int i = lb;
            int j = ub;
           
            while(i < j)
            {
                i++;
                while(i < ub - 1 && arr[i] < arr[lb])
                {
                    i++;
                }
               
                j--;
                while(j > lb && arr[j] > arr[lb])
                {
                    j--;
                }
                 
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
           
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            
            temp = arr[lb];
            arr[lb] = arr[j];
            arr[j] = temp;
             
            int threadJ = j;
            Thread t1 = null;
            Thread t2 = null;
            
            if(j - lb > 1E6)
            {
                t1 = new Thread() {
                    public void run()
                    {
                        quicksort(arr, lb, threadJ);
                    }
                };
                
                t1.start();
            }
            else if(j - lb < 20)
            {
                insertionsort(arr, lb, j);
            }
            else
            {
                quicksort(arr, lb, j);
            }
           
            if(ub - j > 1E6)
            {
                t2 = new Thread() {
                    public void run()
                    {
                        quicksort(arr, threadJ + 1, ub);
                    }
                };
               
                t2.start();
            }
            else if(ub - j < 20)
            {
                insertionsort(arr, j + 1, ub);
            }
            else
            {
                quicksort(arr, j + 1, ub);
            }
           
            if(t1 != null)
            {
                try
                {
                    t1.join();
                }
                 catch(InterruptedException e)
                {
                    
                }
            }
            
            if(t2 != null)
            {
                try
                {
                    t2.join();
                }
                catch(InterruptedException e)
                {
                    
                }
            }
        }
    };
   
    public static void main(String[] argv)
    {
        new VisualSort(hybridSort, 640);
    }
}