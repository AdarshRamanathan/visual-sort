Synopsis:	A simple java application that allows users to visualize an array of integer elements while it is being sorted (or otherwise processed).

Author:		Adarsh Ramanathan

Version:	0.2

Date:		2015/02/16

Usage:		Just import VisualSort and create an instance, passing a VisualSort.SortingAlgorithm instance to it, along with an optional integer multiplier.
			The sort(int[]) method of the VisualSort.SortingAlgorithm interface can be overriden for visualizing user-written algorithms.
			
			VisualSort	(Class)
				Constructor Summary
					VisualSort(VisualSort.SortingAlgorithm algorithm);
						creates an integer array with as many elements as will fit on your screen. It then proceeds to invoke the sort method of algorithm.
					
					VisualSort(VisualSort.SortingAlgorithm algorithm, int multiplier);
						similar as above, but the number of elements in the array is <multiplier> times what it would have been, and only one in <multiplier> elements is visible on screen.
						useful if a larger array is required. Can induce out-of-memory issues if an excessively large multiplier is chosen. <multiplier> must be a non-negative integer.
						
			VisualSort.SortingAlgorithm	(Interface)
				Method Summary
					sort(int[])
						this method is invoked after the array is initialized and shuffled. The values present in the array determine the pixel colors on the visual field.
						override this method to sort the array passed as a parameter (or process it in some fashion).

Notes:		Difficult to accurately visualize out-of-place sorting algorithms, since the  VisualSort instance cannot reference the extra space used.
			I may add a separate window to represent swap space et cetera at some later time. If I can think up a non-hacky way to do it.