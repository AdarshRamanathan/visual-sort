Synopsis:	A simple Java framework that allows users to visualize an array of integer elements while it is being sorted (or otherwise processed).
			Allows users to define their own algorithms to process the array, via an interface.

Author:		Adarsh Ramanathan

Version:	0.2

Date:		2015/02/16

Usage:		Create a VisualSort instance, passing a VisualSort.SortingAlgorithm instance to it, and, optionally, an integer multiplier.
			The sort method of the VisualSort.SortingAlgorithm interface can be overridden for visualizing user-written algorithms.
			
			VisualSort	(Class)
				Constructor Summary
					VisualSort(VisualSort.SortingAlgorithm algorithm);
						creates an integer array with as many elements as will fit on your screen. It then proceeds to invoke the sort method of algorithm, passing the created array as a parameter.
					
					VisualSort(VisualSort.SortingAlgorithm algorithm, int multiplier);
						similar as above, but the number of elements in the array is <multiplier> times what it would have been, and only one in <multiplier> elements is visible on screen.
						useful if a larger array is required. Can induce out-of-memory issues if an excessively large multiplier is chosen. <multiplier> must be a non-negative integer.
						
			VisualSort.SortingAlgorithm	(Interface)
				Method Summary
					sort(int[] arr)
						this method is invoked after <arr> is initialized and shuffled. The state of <arr> determines the pixel colours on the visual field.
						override this method to sort <arr> with your own algorithm (or process it in some other fashion).

Reqs:		Java SE 8. May or may not work with older versions.				

Notes:		Difficult to accurately visualize out-of-place sorting algorithms, since the  VisualSort instance cannot reference the extra space used.
			I may add a separate window to represent swap space et cetera at some later time. If I can think of a non-hacky way to do it.