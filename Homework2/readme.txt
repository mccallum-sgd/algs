/******************************************************************************
 *  Name:     Sean McCallum
 *
 *  Hours to complete assignment (optional):
 *
 ******************************************************************************/

Programming Assignment 3: Autocomplete


/******************************************************************************
 *  Describe how your firstIndexOf() method in BinarySearchDeluxe.java
 *  finds the first index of a key that equals the search key.
 *****************************************************************************/
It first finds any element that matches the key using binary search. Then it 
compares the key with the previous element from mid, and if it's not matching 
then I know that mid is the first matching key, and I return it. If they don't 
match, then I move hi to mid-1 and continue binary searching in the left partition.
 Repeat until either the first index is found or lo and hi crossover (in which 
 case return -1).

/******************************************************************************
 *  What is the order of growth of the number of compares (in the
 *  worst case) that each of the operations in the Autocomplete
 *  data type make, as a function of the number of terms n and the
 *  number of matching terms m?
 *
 *  Recall that with order-of-growth notation, you should discard
 *  leading coefficients and lower-order terms, e.g., m^2 + m log n.
 *****************************************************************************/

constructor: N for null-checking, N log N for Arrays.sort...
N + N log N = ~ N log N

allMatches(): 2log N for call to numberOfMatches(), M for copying the matching terms over, M log M for sorting by reverse weight...
2 log N + M + M log M = ~ log N + M log M

numberOfMatches():
2log N = ~ log N

/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/


/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings or lectures, but do include
 *  any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *
 *  Also include any resources (including the web) that you may
 *  may have used in creating your design.
 *****************************************************************************/
I asked Connor C. about the performance of using a for loop to null-check the array 
in Autocomplete's constructor, and he reminded me that it's fine because it isn't 
the leading term in it's order-of-growth performance (i.e. N + N log N = ~ N log N).

/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/
- I tried to follow the template given for binary search in class (mainly the 
'while(lo + 1 <= hi)') but I couldn't get it to work. I understood what I had
to do but it just wouldn't work so I thought about it and came up with an 
algorithm that seems cleaner to me and works.
- I almost missed the edge case of if mid is 0 when looking for the first index
(and a.length-1 when looking for last), where I would get an indexoutofbounds
exception because I'm checking the element before the beginning or after the end,
which of course isn't there. I solved this by adding an or statement to my matching
condition in both methods.
- I wasn't sure what to do in byPrefixOrder for if the term was shorter than the 
specified prefix length, but after thinking about it for a while I realized that
if it is shorter than the specified prefix length then it shouldn't match at all, 
so I ranked the shorter term lower.

/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 *****************************************************************************/
I wish I could've gotten the algorithm done the way that was suggested in class.
I remember Travis going into a bit of detail about the implementation but I 
wasn't able to take notes that day (OneNote wasn't loading) so I couldn't quite
remember what he said. I think I understand how it's supposed to implemented but
again I couldn't quite get it to work and it was pretty tricky to conceptualize 
so I came up with a simpler algorithm (IMO).